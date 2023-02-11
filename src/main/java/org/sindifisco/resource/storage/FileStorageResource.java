package org.sindifisco.resource.storage;

import lombok.extern.log4j.Log4j;
import org.sindifisco.FileStorageService;
import org.sindifisco.vo.UploadFileResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
public class FileStorageResource {

    public static final Logger logger = LoggerFactory.getLogger(FileStorageResource.class);
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file){

        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/download/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseVO(
                fileName,fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/upload/multiples")
    @PreAuthorize("hasAnyAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
    public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{fileName:.+}")
//    @PreAuthorize("hasAnyAuthority('ROLE_READ') and #oauth2.hasScope('read')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e){
            logger.info("Tipo de arquivo não determinado");
        }

        if(contentType == null){
            contentType = "application/octet-stram";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
