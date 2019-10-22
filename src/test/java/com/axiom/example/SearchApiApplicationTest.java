/**
 * 
 */
package com.axiom.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author admin
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SearchApiApplication.class)
public class SearchApiApplicationTest {

	@Test
	public void contextLoads() {
		
	}
}
