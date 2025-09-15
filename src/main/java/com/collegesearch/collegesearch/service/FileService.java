package com.collegesearch.collegesearch.service;

import com.collegesearch.collegesearch.FileConfig.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service

public class FileService {
    private final Path fileloc;

    public FileService(FileProperties fileproperties) {
        this.fileloc = Paths.get(fileproperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileloc);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory to upload", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Ensure file name is unique
            if (filename.contains("..")) {
                throw new FileStorageException("Invalid file name: " + filename);
            }
          
            Path targetLocation = this.fileloc.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException ex) {
            throw new FileStorageException("Failed to store file " + filename, ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileloc.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("File not found: " + fileName);
            }
        } catch (IOException ex) {
            throw new FileStorageException("File not found: " + fileName, ex);
        }
    }
}

class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
