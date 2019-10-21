package com.axiom.example.services;

import java.util.List;
import java.util.Map;

import com.axiom.example.entity.Device;
import com.axiom.example.exception.ApiException;

public interface SearchService {
 
   public List<Device> getAvailableDevice(Map<String,String> requestKeys)throws ApiException;

}
