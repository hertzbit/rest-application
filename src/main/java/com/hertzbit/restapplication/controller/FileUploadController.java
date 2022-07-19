package com.hertzbit.restapplication.controller;

import com.hertzbit.restapplication.exceptions.CannotUploadFileException;
import com.hertzbit.restapplication.exceptions.UploadedFileNotFoundException;
import com.hertzbit.restapplication.model.UploadedFile;
import com.hertzbit.restapplication.response.FileUploadResponse;
import com.hertzbit.restapplication.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    public FileUploadService fileUploadService;
    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("fileName") MultipartFile multipartFile) {

        this.fileUploadService.uploadToLocal(multipartFile);
    }

    @PostMapping("/upload/database")
    public ResponseEntity<FileUploadResponse> uploadDatabase(@RequestParam("fileName") MultipartFile multipartFile) {

        UploadedFile uploadedFile = this.fileUploadService.uploadToDatabase(multipartFile);
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        if (uploadedFile != null) {
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/database/")
                    .path(uploadedFile.getFileId())
                    .toUriString();
            fileUploadResponse.setFileId(uploadedFile.getFileId());
            fileUploadResponse.setFileType(uploadedFile.getFileType());
            fileUploadResponse.setDownloadUri(downloadUri);
            fileUploadResponse.setUploadStatus(true);
            fileUploadResponse.setMessage("File Uploaded Successfully !");
            return new ResponseEntity<>(fileUploadResponse, HttpStatus.OK);
        }
        throw new CannotUploadFileException("File cannot be uploaded !");
    }

    @GetMapping("/download/database/{fileId}")
    public ResponseEntity<Resource> downloadFromDatabase (@PathVariable("fileId") String fileId) {

        UploadedFile uploadedFile = this.fileUploadService.downloadFromDatabase(fileId);
        if (uploadedFile != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " +
                            uploadedFile.getFileName())
                    .body(new ByteArrayResource(uploadedFile.getFileData()));
        }
        throw new UploadedFileNotFoundException("The requested file with id : " + fileId + " doesn't exists");
    }
}
