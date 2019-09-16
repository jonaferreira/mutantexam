package com.ml.exam.mutant.integration.test;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.exam.mutant.ServerApp;
import com.ml.exam.mutant.controller.dto.DnaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testIntegracionHopedResponse() throws JSONException {
		
		testIntegracionServiceMutantCaseIsDnaIsMutant();
		testIntegracionServiceMutantCaseIsDnaIsNotMutant();
		testIntegracionServiceMutantCaseIsDnaBadFormat();
		

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/stats"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"count_mutant_dna\":1,\"count_human_dna\":1,\"ratio\":1.0}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	
	}
	
	public void testIntegracionServiceMutantCaseIsDnaIsMutant() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "AAAA", "CAGT", "TTTT", "AGAA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);
		
	}

	public void testIntegracionServiceMutantCaseIsDnaIsNotMutant() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "ATGC", "CAGT", "TTCT", "AGAA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);
	}	

	public void testIntegracionServiceMutantCaseIsDnaBadFormat() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "aTC", "C$GT", "*TcT", "A?AA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
