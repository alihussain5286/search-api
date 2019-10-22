/**
 * 
 */
package com.axiom.example.model;

import java.util.List;

import com.axiom.example.entity.Device;

/**
 * @author admin
 *
 */
public class Response {
	
	private String status;
	private List<Device> devices;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

}
