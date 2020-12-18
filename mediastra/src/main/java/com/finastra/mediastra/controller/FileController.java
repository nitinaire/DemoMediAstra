package com.finastra.mediastra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.finastra.mediastra.entity.FileDB;
import com.finastra.mediastra.entity.HelpingHand;
import com.finastra.mediastra.exception.ResourceNotFoundException;
import com.finastra.mediastra.report.pdf.ResponseFile;
import com.finastra.mediastra.report.pdf.ResponseMessage;
import com.finastra.mediastra.repository.FileDBRepository;
import com.finastra.mediastra.repository.HelpingHandRepo;
import com.finastra.mediastra.service.FileStorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class FileController {

	@Autowired
	private FileStorageService storageService;
	@Autowired
	private HelpingHandRepo helpingHandRepo;
	@Autowired
	private FileDBRepository fileDBRepository;

	@PostMapping("employees/upload/{id}")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@PathVariable(value = "id") Long helpId) {
		String message = "";
		try {
			HelpingHand helpingHand = helpingHandRepo.findById(helpId)
					.orElseThrow(() -> new ResourceNotFoundException("helpId not found for this id :: " + helpId));
			storageService.store(file, helpingHand);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/employees/helpinghandrequestList")
	public List<FileDB> getAllRequest() {
		return fileDBRepository.findAll();
	}

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		FileDB fileDB = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}

	@PostMapping("/employees/addhelpinghand")
	public HelpingHand createHelpingrequest(@RequestBody HelpingHand helpingHand) {
		return helpingHandRepo.save(helpingHand);
	}

}
