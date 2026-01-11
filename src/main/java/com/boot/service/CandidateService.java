package com.boot.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class CandidateService {

	 private final S3Client s3Client;

	    @Value("${aws.s3.bucket}")
	    private String bucketName;
	    
	    @Value("${aws.region}")
	    private String region;
	    
	    public CandidateService(
	            @Value("${aws.region}") String region) {
	        this.s3Client = S3Client.builder()
	                .region(Region.of(region))   // ✅ Ohio
	                .build();
	    }
	    
	    public String uploadFile(MultipartFile file) throws IOException {
	        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

	        PutObjectRequest request = PutObjectRequest.builder()
	                .bucket(bucketName)
	                .key(key)
	                .contentType(file.getContentType())
	                .build();

	        s3Client.putObject(
	                request,
	                RequestBody.fromBytes(file.getBytes())
	        );

	        //return "https://" + bucketName + ".s3.amazonaws.com/" + key;
	        return String.format(
	                "https://%s.s3.%s.amazonaws.com/%s",
	                bucketName,
	                region,
	                key
	        );
	    }

}
