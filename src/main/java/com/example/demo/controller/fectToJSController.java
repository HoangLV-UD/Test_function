package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.contrant.Constants.MESSAGE;
import com.example.demo.dto.PhoneDTO;
import com.example.demo.utils.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/js")
public class fectToJSController {
	@GetMapping(value = "/phone-list")
	@ResponseBody
	public Object listPhone() {

		List<PhoneDTO> dto = new ArrayList<>();
		PhoneDTO d = new PhoneDTO();
		d.setName("iphone");
		d.setPrice(12000);
		d.setQuantity("3");
		dto.add(d);
//		return Utils.ResponeData(HttpStatus.OK, true, MESSAGE.OK, dto);
		return dto;
	}
}
