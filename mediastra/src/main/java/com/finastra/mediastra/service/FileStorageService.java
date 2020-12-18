package com.finastra.mediastra.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.finastra.mediastra.entity.FileDB;
import com.finastra.mediastra.entity.HelpingHand;
import com.finastra.mediastra.repository.FileDBRepository;
import com.finastra.mediastra.repository.HelpingHandRepo;

@Service
public class FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;
	@Autowired
	private HelpingHandRepo helpingHandRepo;

	public FileDB store(MultipartFile file, HelpingHand helpingHand) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(helpingHand.getName(), helpingHand.getReason(), fileName,
				file.getContentType(), file.getBytes());

		return fileDBRepository.save(fileDB);
	}

	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}