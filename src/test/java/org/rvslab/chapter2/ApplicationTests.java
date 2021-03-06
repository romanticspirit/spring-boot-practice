package org.rvslab.chapter2;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Ignore
	@Test
	public void testVanillaService() {
		Greet greet = restTemplate.getForObject("http://localhost:8080/Hello", Greet.class);
		
		assertEquals("Full authentication is required to access this resource", greet.getMessage());
	}
	
	@Ignore
	@Test
	public void testSecureService() {
		String plainCreds = "guest:guest123";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<Greet> response = restTemplate.exchange("/Hello", HttpMethod.GET, request, Greet.class);
		assertEquals("Hello HATEOAS", response.getBody().getMessage());
	}
	
	@Test
	public void testOAuthService() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		resource.setUsername("guest");
		resource.setPassword("guest123");
		resource.setAccessTokenUri("http://localhost:8080/oauth/token");
		
		resource.setClientId("trustedclient");
		resource.setClientSecret("trustedclient123");
		resource.setGrantType("password");
		
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
		
		Greet greet = restTemplate.getForObject("http://localhost:8080/Hello", Greet.class);
		assertEquals("Hello HATEOAS", greet.getMessage());
	}

}
