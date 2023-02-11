package org.sindifisco;

import org.sindifisco.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path filePathLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.filePathLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.filePathLocation);
        } catch (Exception e){
            throw new FileStorageException("Não foi possivel criar o diretório onde os arquivos serão armazenados", e);
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Nome inválido para o arquivo." + fileName);
            }
            Path targetLocation = this.filePathLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e){
            throw new FileStorageException("Não foi possivel salvar o arquivo " + fileName + ". Tente novamente.", e);
        }

    }

    public Resource loadFileAsResource(String fileName){
        try {
            Path filePath = this.filePathLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            } else {
                throw new MyFileNotFoundException("O arquivo " + fileName + " não foi encontrado .");
            }
        } catch (Exception e){
            throw new MyFileNotFoundException("O arquivo " + fileName + " não foi encontrado .", e);
        }
    }
}
