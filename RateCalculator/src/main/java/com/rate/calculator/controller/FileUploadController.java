package com.rate.calculator.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private String filePath = "D:\\";

	@GetMapping("/")
	public String goToFileUpload(ModelMap model) {
		return "uploadFile";

	}
	
	@RequestMapping(value="/upload", method =RequestMethod.POST)
	public String uploadFile(@PathVariable("file") MultipartFile file, ModelMap model) {

		if (file.isEmpty()) {
			model.put("message", "Please upload the file");
			return "uploadFile";
		}
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			model.put("message", "Upload successful");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "uploadFile";
	}
}
