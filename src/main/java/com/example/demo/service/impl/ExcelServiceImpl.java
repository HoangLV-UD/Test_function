package com.example.demo.service.impl;

import com.example.demo.contrant.Constants.MESSAGE;
import com.example.demo.repository.ExcelDao;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	ExcelDao dao;

	@Override
	public Object importList(MultipartFile file) {
		try {
			return Utils.ResponeData(HttpStatus.OK, true, MESSAGE.OK, dao.importList(file));
		} catch (Exception e) {
			e.printStackTrace();
			return Utils.ResponeData(HttpStatus.EXPECTATION_FAILED, false, MESSAGE.EXPECTATION_FAILED);
		}
	}

}
