package com.ml.exam.mutant.integration.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort ;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ml.exam.mutant.ServerApp;
import com.ml.exam.mutant.controller.dto.DnaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MutantControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testIntegracionServiceMutantCaseIsDnaIsMutant() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "AAAA", "CAGT", "TTTT", "AGAA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);

		HttpStatus status = response.getStatusCode();

		Assert.assertEquals(HttpStatus.OK, status);
		
	}

	@Test
	public void testIntegracionServiceMutantCaseIsDnaIsNotMutant() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "ATGC", "CAGT", "TTCT", "AGAA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);

		HttpStatus status = response.getStatusCode();

		Assert.assertEquals(HttpStatus.FORBIDDEN, status);
	}	


	@Test
	public void testIntegracionServiceMutantCaseIsDnaBadFormat() {

		DnaDTO dnaDTO = new DnaDTO();

		String[] dna = { "aTC", "C$GT", "*TcT", "A?AA" };

		dnaDTO.setDna(dna);

		HttpEntity<DnaDTO> entity = new HttpEntity<DnaDTO>(dnaDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mutant"), HttpMethod.POST, entity, String.class);

		HttpStatus status = response.getStatusCode();

		Assert.assertEquals(HttpStatus.BAD_REQUEST, status);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
