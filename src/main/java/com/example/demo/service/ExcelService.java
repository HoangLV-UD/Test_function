package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

	Object importList(MultipartFile file);

}
