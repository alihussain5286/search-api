/**
 * 
 */
package com.axiom.example.services;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axiom.example.exception.ApiException;
import com.axiom.example.util.ErrorConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author admin
 *
 */
@Service
public class JsonObjectMappingServiceImpl implements CustomBeanMappingService {

	private static final Logger logger= LoggerFactory.getLogger(JsonObjectMappingServiceImpl.class);

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Method will create the final Object with all the values attached to matching requested Keys.
	 * If the request key is wrong then its value will not be updated.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T prepareObjectForSearch(Map<String, String> requestedKeys, T t) throws ApiException {
		try {
			String json=objectMapper.writeValueAsString(t);
			JSONObject jsonObject= new JSONObject(json);
			jsonObject.keySet().forEach(key->{
				Object obj=jsonObject.get(key);
				if(obj instanceof JSONObject) {
					jsonObject.getJSONObject(key).keySet().forEach(nestedKey->
						checkAndUpdateJsonObjectForKeyValue(requestedKeys, jsonObject.getJSONObject(key), nestedKey));
				}else {
					checkAndUpdateJsonObjectForKeyValue(requestedKeys, jsonObject, key);
				}
			});
			t= (T) objectMapper.readValue(jsonObject.toString(), t.getClass());
			logger.info("Final Object with all the key values Updated::{}",jsonObject);
		}catch(Exception e) {
			logger.error("Exception in prepareObjectForSearch|JsonObjectMappingServiceImpl ::{}",e);
			throw new ApiException(ErrorConstants.INTERNAL_ERROR);
		}
		return t;
	}

	
	/**
	 * @param requestedKeys
	 * @param jsonObject
	 * @param key
	 */
	private void checkAndUpdateJsonObjectForKeyValue(Map<String,String> requestedKeys,JSONObject jsonObject,String key) {
		requestedKeys.forEach((requestedKey,requestedValue)->{
			if(key.equalsIgnoreCase(requestedKey)) {
				jsonObject.put(key, requestedValue);
			}
		});
	}
}
