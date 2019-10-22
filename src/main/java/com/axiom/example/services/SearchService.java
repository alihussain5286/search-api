package com.axiom.example.services;

import java.util.Map;

import com.axiom.example.exception.ApiException;

@FunctionalInterface
public interface SearchService {

	public <T> Object getAvailableDevice(Map<String, String> requestKeys, T t) throws ApiException;

}
