package com.google.petshop.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadImage(MultipartFile file,String path);
	
	InputStream getImage(String path,String fileName);
}
