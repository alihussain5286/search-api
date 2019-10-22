package com.axiom.example.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.axiom.example.entity.Device;
import com.axiom.example.exception.ApiException;
import com.axiom.example.model.Response;
import com.axiom.example.repository.DeviceRepository;
import com.axiom.example.util.Constants;
import com.axiom.example.util.ErrorConstants;

/**
 */
@Service
public class DeviceSearchServiceImpl implements SearchService {

	private static final Logger logger= LoggerFactory.getLogger(DeviceSearchServiceImpl.class);

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private CustomBeanMappingService customBeanMappingService;

	@Override
	public <T> Object getAvailableDevice(Map<String, String> requestKeys,T t) throws ApiException {
		Response response = new Response();
		logger.info("<----------------Enter getAvailableDevice|DeviceSearchServiceImpl---------------->");
		try {
			Device device=(Device) customBeanMappingService.prepareObjectForSearch(requestKeys,t);
			List<Device> deviceList=deviceRepository.findAll(Example.of(device,ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
			response.setStatus(Constants.SUCCESS_STATUS);
			response.setDevices(deviceList);
		}catch (Exception e) {
			logger.error("Exception in DeviceSearchServiceImpl|getAvailableDevice:{}",e);
			throw new ApiException(ErrorConstants.INTERNAL_ERROR);
		}
		logger.info("<----------------Exit getAvailableDevice|DeviceSearchServiceImpl---------------->");
		return response;
	}
}
