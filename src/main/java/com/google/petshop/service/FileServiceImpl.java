package com.google.petshop.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jayway.jsonpath.internal.Path;

@Component
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(MultipartFile file, String path) {
		
		String originalFilename = file.getOriginalFilename();
		
		String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String newFileName = UUID.randomUUID().toString()+ extention;
		
		File folder = new File(path);
		
		if(!folder.exists()) {
			
			folder.mkdirs();
		}
		
		try {
			Files.copy(file.getInputStream(),Paths.get(path+File.separator+newFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newFileName;
	}

	@Override
	public InputStream getImage(String path, String fileName) {
		
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(path+fileName);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return fis;
	}

}
