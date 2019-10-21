package com.axiom.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axiom.example.entity.Device;
import com.axiom.example.exception.ApiException;
import com.axiom.example.services.SearchService;

@RestController
@RequestMapping("/device")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/search")
	public List<Device> searchMobile(HttpServletRequest request, @RequestParam Map<String, String> search) throws ApiException {
		return searchService.getAvailableDevice(search);
	}
}
