package com.boot.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.boot.entity.Candidate;
import com.boot.repository.CandidateRepository;
import com.boot.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	private final CandidateService service;
	private final CandidateRepository repository;
	
	public CandidateController(CandidateRepository repository, CandidateService service) {
        this.repository = repository;
        this.service = service;
    }
	
	 @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public Candidate createCandidate(
	            @RequestParam String name,
	            @RequestParam Integer age,
	            @RequestPart MultipartFile photo) throws IOException {
		 String photoUrl = service.uploadFile(photo);
		 Candidate candidate = new Candidate();
	        candidate.setName(name);
	        candidate.setAge(age);
	        candidate.setPhotoUrl(photoUrl);
	        return repository.save(candidate);
	    }
}
