package com.hertzbit.restapplication.service;

import com.hertzbit.restapplication.exceptions.ExceptionController;
import com.hertzbit.restapplication.model.UploadedFile;
import com.hertzbit.restapplication.repository.FileUploadRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {


    private static final Logger LOGGER = LogManager.getLogger(FileUploadService.class);
    private String uploadFolderPath = "/Users/chetankrishna/Downloads/rest-application/";

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public void uploadToLocal (MultipartFile multipartFile) {

        try {
            byte[] byteDataFromMultipartFile = multipartFile.getBytes();
            Path localRepositoryPath = Paths.get(uploadFolderPath + multipartFile.getOriginalFilename());
            Files.write(localRepositoryPath, byteDataFromMultipartFile);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public UploadedFile uploadToDatabase (MultipartFile multipartFile) {

        UploadedFile uploadedFile = new UploadedFile();
        try {
            uploadedFile.setFileData(multipartFile.getBytes());
            uploadedFile.setFileType(multipartFile.getContentType());
            uploadedFile.setFileName(multipartFile.getOriginalFilename());
            this.fileUploadRepository.save(uploadedFile);
            return uploadedFile;
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
    }

    public UploadedFile downloadFromDatabase (String fileId) {

        UploadedFile uploadedFile =  null;
        try {
            uploadedFile = this.fileUploadRepository.getReferenceById(fileId);
            return uploadedFile;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
    }
}
