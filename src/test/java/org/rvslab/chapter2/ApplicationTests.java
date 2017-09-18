package org.rvslab.chapter2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
		
		assertEquals("Hello World!", greet.getMessage());
	}

}
