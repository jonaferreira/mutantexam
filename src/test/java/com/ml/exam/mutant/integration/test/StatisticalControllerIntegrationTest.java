package com.ml.exam.mutant.integration.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort ;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ml.exam.mutant.ServerApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticalControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testIntegracionServiceStats() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/stats"),
				HttpMethod.GET, entity, String.class);

		Assert.isTrue(response.getBody()!=null, "Response body should not be empty");
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
