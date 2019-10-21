package com.axiom.example.entity;

import java.io.Serializable;

public class Release implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String announceDate;
	private Double priceEur;
	
	public String getAnnounceDate() {
		return announceDate;
	}
	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}
	public Double getPriceEur() {
		return priceEur;
	}
	public void setPriceEur(Double priceEur) {
		this.priceEur = priceEur;
	}

}
