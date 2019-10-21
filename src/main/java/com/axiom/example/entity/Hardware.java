package com.axiom.example.entity;

import java.io.Serializable;

public class Hardware implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String audioJack;
	private String gps;
	private String battery;
	
	public String getAudioJack() {
		return audioJack;
	}
	public void setAudioJack(String audioJack) {
		this.audioJack = audioJack;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}

}
