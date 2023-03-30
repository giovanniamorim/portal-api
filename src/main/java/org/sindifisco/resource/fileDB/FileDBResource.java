package org.sindifisco.resource.fileDB;

import lombok.RequiredArgsConstructor;
import org.sindifisco.message.ResponseFile;
import org.sindifisco.message.ResponseMessage;
import org.sindifisco.model.FileDB;
import org.sindifisco.repository.fileDB.FileDBRepository;
import org.sindifisco.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileDBResource {

    @Autowired
    private FileDBService filesService;

    @Autowired
    private FileDBRepository fileDBRepository;

    @PostMapping("/files/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if(fileDBRepository.existsByName(file.getOriginalFilename())){
            message = "Já existe um arquivo com o nome: " + file.getOriginalFilename() + ". Acesse o gerenciador de arquivos e delete-o primeiramente";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } else {
            try {
                filesService.store(file);
                message = "Arquivo enviado com sucesso: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Não foi possivel enviar arquivo: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

    }

    @GetMapping("/files/list")
    public Page<FileDB> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable){
        return fileDBRepository.findAll(pageable);
    }



    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = filesService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/file/find?name=")
                    .path((dbFile.getName()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/file/find")
    public ResponseEntity<byte[]> getFile(@RequestParam String name) {
        FileDB fileDB = filesService.findByName(name);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileDB fileDB = filesService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @PutMapping("/file/{id}")
    public ResponseEntity<FileDB> updateFile(
            @PathVariable Long id, @Valid @RequestBody FileDB newFileDB, @RequestParam("file") MultipartFile file){
        return fileDBRepository.findById(id)
                .map(fileDB -> {
                    fileDB.setName(newFileDB.getName());
                    fileDB.setType(newFileDB.getType());
                    fileDB.setData(newFileDB.getData());

                    FileDB fileDBUpdated = fileDBRepository.save(fileDB);
                    return ResponseEntity.ok().body(fileDBUpdated);
                }).orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Arquivo não encontrado"));
    }

    @DeleteMapping("/file/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteFileDB(@PathVariable Long id) {
        fileDBRepository.findById(id).map(fileDB -> {
            fileDBRepository.delete(fileDB);
            return TYPE;
        }).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Arquivo não encontrado"));
    }


}
