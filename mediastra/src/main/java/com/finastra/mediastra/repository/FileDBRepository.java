package com.finastra.mediastra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.mediastra.entity.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}