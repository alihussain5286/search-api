package com.axiom.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.axiom.example.SearchApiApplicationTest;
import com.axiom.example.model.Response;
import com.axiom.example.util.Constants;

public class SearchApiControllerTest extends SearchApiApplicationTest {

	private static final Logger logger = LoggerFactory.getLogger(SearchApiControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private UriComponentsBuilder uriComponentsBuilder;

	@BeforeEach
	public void beforeEachTest() {
		uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(new StringBuilder("http://localhost:").append(port).append("/mobile/search").toString());
	}

	@AfterEach
	public void AfterEachTest() {
		logger.info("Final Request RestTemplate Api URL::{}", uriComponentsBuilder.toUriString());
	}

	@Test
	@DisplayName("Test PriceEur=200")
	public void getAllDevicesForPriceEur() {
		uriComponentsBuilder.queryParam("priceEur", 200);
		ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				Response.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Response response = responseEntity.getBody();
		assertEquals(Constants.SUCCESS_STATUS, response.getStatus());
		assertEquals(10, response.getDevices().size());
	}

	@Test
	@DisplayName("Test sim=eSim")
	public void getAllDevicesForESim() {
		uriComponentsBuilder.queryParam("sim", "eSim");
		ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				Response.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Response response = responseEntity.getBody();
		assertEquals(Constants.SUCCESS_STATUS, response.getStatus());
		assertEquals(18, response.getDevices().size());
	}

	@Test
	@DisplayName("Test announcateDate=1999 & priceEur=200")
	public void getAllDevicesForAnnounceDateAndpriceEur() {
		uriComponentsBuilder.queryParam("announceDate", 1999).queryParam("priceEur", 200);
		ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				Response.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Response response = responseEntity.getBody();
		assertEquals(Constants.SUCCESS_STATUS, response.getStatus());
		assertEquals(2, response.getDevices().size());
	}

	@Test
	@DisplayName("Test audioJack=Yes")
	public void getAllDevicesForAudioJack() {
		uriComponentsBuilder.queryParam("audioJack", "yes");
		ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				Response.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Response response = responseEntity.getBody();
		assertEquals(Constants.SUCCESS_STATUS, response.getStatus());
		assertEquals(36, response.getDevices().size());
	}
	
	@Test
	@DisplayName("Test For All Device")
	public void getAllDevices() {
		ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				Response.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Response response = responseEntity.getBody();
		assertEquals(Constants.SUCCESS_STATUS, response.getStatus());
		assertNotNull(response.getDevices());
	}

}
