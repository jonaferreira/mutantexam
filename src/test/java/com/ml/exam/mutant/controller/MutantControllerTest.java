package com.ml.exam.mutant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.ml.exam.mutant.controller.dto.DnaDTO;
import com.ml.exam.mutant.model.Geneticist;
import com.ml.exam.mutant.model.Statistical;
import com.ml.exam.mutant.model.util.DnaDtoMatcher;
import com.ml.exam.mutant.model.util.StatusCode;

public class MutantControllerTest {

	private static final String CONTENT_TYPE_JSON = "application/json";

	private static final String URI = "/mutant";

	private MockMvc mockMvc;

	@InjectMocks
	private MutantController mutantController;

	@Mock
	private Statistical statistical;

	@Mock
	private Geneticist geneticist;

	private String[] dnaMutant = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };

	private String[] dnaHuman = new String[] { "ATGCCA", "CAGTGC", "TTCTGG", "AGAAGG", "CCCGTA", "TCGCTG" };

	private String[] invalidDNA = new String[] { "ATGCCA", "CAGTGC", "TTCTGG", "AGAAGG", "TCGCTG" };

	private String mockMutantBody = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";
	private String mockHumanBody = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";
	private String mockInvalidBody = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"TCGCTG\"]}";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mutantController).build();
	}

	@Test
	public void testIfHumanDNAIsRecognizedAsMutant() throws Exception {

		DnaDTO dnaDto = new DnaDTO();
		dnaDto.setDna(dnaMutant);

		// GIVEN a DNA of a mutant subject
		mockCheckIfHumanDnaIsMutant(dnaDto.getDna(), StatusCode.SUCCESS);
		mockSaveDnaVerified(dnaDto, StatusCode.SUCCESS, StatusCode.SUCCESS);

		// WHEN the mutant endpoint is called
		ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockMutantBody));

		// THEN there is an empty response and the status code is 200
		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		Assert.isTrue(content.isEmpty(), "Response body should be empty");
	}

	@Test
	public void testIfHumanDNAIsNotRecognizedAsMutant() throws Exception {

		DnaDTO dnaDto = new DnaDTO();
		dnaDto.setDna(dnaHuman);

		// GIVEN a DNA of a human subject
		mockCheckIfHumanDnaIsMutant(dnaDto.getDna(), StatusCode.FORBIDDEN);

		mockSaveDnaVerified(dnaDto, StatusCode.FORBIDDEN, StatusCode.FORBIDDEN);

		// WHEN the create endpoint is called
		ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockHumanBody));

		// THEN there is an empty response and the status code is 403
		MvcResult result = resultActions.andExpect(status().isForbidden()).andReturn();

		int codeStatus = result.getResponse().getStatus();

		Assert.isTrue(codeStatus == 403, "");
	}

	@Test
	public void testIfHumanDnaHasBadFormat() throws Exception {

		DnaDTO dnaDto = new DnaDTO();
		dnaDto.setDna(invalidDNA);

		// GIVEN a invalid DNA
		mockCheckIfHumanDnaIsMutant(dnaDto.getDna(), StatusCode.ERROR);

		// WHEN the create endpoint is called
		ResultActions resultActions = mockMvc
				.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockInvalidBody));

		// THEN there is an empty response and the status code is 400
		MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
		String content = result.getResponse().getContentAsString();
		Assert.isTrue(content.isEmpty(), "Response body should be empty");
	}
	
	private void mockCheckIfHumanDnaIsMutant(String[] dna, StatusCode expectedResult) {
		Mockito.when(geneticist.checkIfHumanDnaIsMutant(dna)).thenReturn(expectedResult);
	}

	private void mockSaveDnaVerified(DnaDTO dnaDto, StatusCode statusCode, StatusCode expectedResult) {
		DnaDtoMatcher dnaDtoMatcher = new DnaDtoMatcher(dnaDto);
		Mockito.argThat(dnaDtoMatcher);
		Mockito.when(statistical.saveDnaVerified(dnaDto, Mockito.eq(statusCode))).thenReturn(expectedResult);
	}
}
