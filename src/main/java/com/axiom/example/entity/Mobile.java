package com.axiom.example.entity;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mobile {

	@Id
	private Long id;
	private String brand;
	private String phone;
	private URL picture;
	private Map<String, String> release = new HashMap<>();
	private String sim;
	private String resolution;
	private Map<String, String> hardware = new HashMap<>();

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

	public Map<String, String> getRelease() {
		return release;
	}

	public void setRelease(Map<String, String> release) {
		this.release = release;
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

	public Map<String, String> getHardware() {
		return hardware;
	}

	public void setHardware(Map<String, String> hardware) {
		this.hardware = hardware;
	}

	
}
