package com.hertzbit.restapplication.repository;

import com.hertzbit.restapplication.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile, String> {
}
