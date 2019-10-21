package com.axiom.example.entity;

import java.io.Serializable;
import java.net.URL;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String brand;
	private String phone;
	private URL picture;
	private String sim;
	private String resolution;
	private Release release;
	private Hardware hardware;

	public Device() {
		
	}
	
	public Device(Hardware hardware,Release release) {
		this.hardware=hardware;
		this.release=release;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public URL getPicture() {
		return picture;
	}

	public void setPicture(URL picture) {
		this.picture = picture;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	//	public Map<String, String> getHardware() {
	//		return hardware;
	//	}
	//
	//	public void setHardware(Map<String, String> hardware) {
	//		this.hardware = hardware;
	//	}
	//
	//	public Map<String, String> getRelease() {
	//		return release;
	//	}
	//
	//	public void setRelease(Map<String, String> release) {
	//		this.release = release;
	//	}

}
