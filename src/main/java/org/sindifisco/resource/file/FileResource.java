package org.sindifisco.resource.file;

import lombok.RequiredArgsConstructor;
import org.sindifisco.message.ResponseFile;
import org.sindifisco.message.ResponseMessage;
import org.sindifisco.model.FileDb;
import org.sindifisco.model.Usuario;
import org.sindifisco.repository.UsuarioRepository;
import org.sindifisco.security.AppUserDetailsService;
import org.sindifisco.service.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileResource {

	@Autowired
	private FileDbService fileDbService;

	@PostMapping("/file/upload")
	@PreAuthorize("hasAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			fileDbService.store(file);

			message = "Arquivo enviado com sucesso: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Não foi possivel enviar arquivo: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = fileDbService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/api/file/")
					.path(String.valueOf(dbFile.getId()))
					.toUriString();

			return new ResponseFile(
					dbFile.getName(),
					fileDownloadUri,
					dbFile.getType(),
					dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/file/{id}")
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		FileDb fileDB = fileDbService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}

}
