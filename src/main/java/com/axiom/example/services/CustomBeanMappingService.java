/**
 * 
 */
package com.axiom.example.services;

import java.util.Map;

import com.axiom.example.exception.ApiException;

/**
 * @author admin
 *
 */
@FunctionalInterface
public interface CustomBeanMappingService {

	public <T> T prepareObjectForSearch(Map<String,String> requestedKeys,T t) throws ApiException;
}
