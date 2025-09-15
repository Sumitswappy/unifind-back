package com.collegesearch.collegesearch.controller;

import com.collegesearch.collegesearch.response.FileResponse;
import com.collegesearch.collegesearch.service.FileService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("files")
@CrossOrigin(maxAge=500)
public class FileController {

    @Autowired
    private FileService fileService;

    @PutMapping
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
            FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            return new ResponseEntity<>(fileResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // PUT mapping for multiple files upload
    @PutMapping("/multiple")
    public ResponseEntity<List<FileResponse>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        List<FileResponse> fileResponses = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileName = fileService.storeFile(file);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
                FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
                fileResponses.add(fileResponse);
            }
            return new ResponseEntity<>(fileResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine content type.");
        }

        // If content type is not determinable, set to default
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Return response with content type and file as body
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
