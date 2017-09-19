package org.rvslab.chapter2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testVanillaService() {
		Greet greet = restTemplate.getForObject("/Hello", Greet.class);
		
		assertEquals("Full authentication is required to access this resource", greet.getMessage());
	}
	
	@Test
	public void testSecureService() {
		String plainCreds = "guest:guest123";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<Greet> response = restTemplate.exchange("/Hello", HttpMethod.GET, request, Greet.class);
		assertEquals("Hello HATEOAS", response.getBody().getMessage());
	}

}
