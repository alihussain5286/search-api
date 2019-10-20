package com.axiom.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.axiom.example.entity.Hardware;
import com.axiom.example.entity.Mobile;
import com.axiom.example.repository.MobileRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class SearchApiApplication {

	@Autowired
	private MobileRepository mobileRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SearchApiApplication.class, args);
	}
	
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper;
	}
	
	@Bean
	InitializingBean sendDatabase() {
		return () ->{
			Mobile mobile=new Mobile();
			mobile.setId(new Long(785));
			mobile.setBrand("Apple");
			Map<String,String> hardware=new HashMap<>();
			hardware.put("hard", "test");
			mobile.setHardware(hardware);
			mobile=mobileRepository.save(mobile);
			
		Optional<Mobile> mobile2=	mobileRepository.findById(mobile.getId());
			
		};
	}
}
