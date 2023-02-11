package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Utils {
	public static Object ResponeData(HttpStatus code, Boolean status, String msg, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code.value());
		map.put("status", status);
		map.put("message", msg);
		map.put("data", data);
		return map;
	}
	
	public static Object ResponeData(HttpStatus code, Boolean status, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code.value());
		map.put("status", status);
		map.put("message", msg);
		map.put("data", null);
		return map;
	}
}
