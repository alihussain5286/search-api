package com.axiom.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;

import com.axiom.example.entity.Device;
import com.axiom.example.repository.DeviceRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class SearchApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(SearchApiApplication.class);

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${devices.rest.api}")
	private String deviceApi;

	public static void main(String[] args) {
		SpringApplication.run(SearchApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public Logbook logBook() {
		return Logbook.create();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		return mapper;
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			logger.info("Calling RestApi to get Devices List");
			ResponseEntity<List<Device>> response = restTemplate.exchange(deviceApi, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Device>>() {
					});
			deviceRepository.saveAll(response.getBody());
			logger.info("Device List loaded in DB");
		};
	}
}
