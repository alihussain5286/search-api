package com.axiom.example.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.axiom.example.entity.Device;
import com.axiom.example.entity.Hardware;
import com.axiom.example.entity.Release;
import com.axiom.example.exception.ApiException;
import com.axiom.example.repository.DeviceRepository;
import com.axiom.example.util.ErrorConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 */
@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger logger= LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private ObjectMapper objectMapper;


	@Override
	public List<Device> getAvailableDevice(Map<String, String> requestKeys) throws ApiException {
		Device device = new Device(new Hardware(),new Release());
		List<Device> deviceList=null;
		try {
			device=prepareDeviceForSearch(requestKeys);
			deviceList=deviceRepository.findAll(Example.of(device,ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
		} catch (ApiException e) {
			logger.error("ApiException in SearchServiceImpl|getAvailableDevice:{}",e);
			throw new ApiException(e.getErrorCode());
		}catch (Exception e) {
			logger.error("Exception in SearchServiceImpl|getAvailableDevice:{}",e);
			throw new ApiException(ErrorConstants.INTERNAL_ERROR);
		}
		return deviceList;
	}


	private Device prepareDeviceForSearch(Map<String, String> requestKeys) throws ApiException {
		Device device = new Device(new Hardware(),new Release());
		try {
			String json=objectMapper.writeValueAsString(device);
			JSONObject jsonObject= new JSONObject(json);
			for (Map.Entry<String, String> entry : requestKeys.entrySet()){
				String key=entry.getKey();
				String value=entry.getValue();
				if(jsonObject.has(key)){
					jsonObject.put(key, value);
				}else {
					JSONObject release =jsonObject.getJSONObject("release");
					JSONObject hardware =jsonObject.getJSONObject("hardware");
					if(release.has(key)) {
						release.put(key, value);
					}else if(hardware.has(key)) {
						hardware.put(key, value);
					}else {
						throw new ApiException(ErrorConstants.KEY_INVALID);
					}
				}
			}
			device=	objectMapper.readValue(jsonObject.toString(), Device.class);
		} catch (ApiException e) {
			logger.error("ApiException in SearchServiceImpl|prepareDeviceForSearch:{}",e);
			throw new ApiException(e.getErrorCode());
		}catch (Exception e) {
			logger.error("Exception in SearchServiceImpl|prepareDeviceForSearch:{}",e);
			throw new ApiException(ErrorConstants.INTERNAL_ERROR);
		}
		return device;
	}


}
