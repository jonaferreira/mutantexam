package com.ml.exam.mutant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.ml.exam.mutant.controller.StatisticalController;
import com.ml.exam.mutant.model.Statistical;

public class StatisticalControllerTest {

	private static final String URI = "/stats";
	
	private MockMvc mockMvc;

	@InjectMocks
	private StatisticalController statisticalController;

	@Mock
	private Statistical statical;


	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(statisticalController).build();
	}
	
	@Test
	public void checkTheServiceOfStats() throws Exception {
		
		String statisticsDTO = "{\"count_mutant_dna\":0,\"count_human_dna\":0,\"ratio\":0.0}";
		
		// GIVEN a stats
		Mockito.when(statical.getStaticsOfDnaVerified()).thenReturn(statisticsDTO);

		// WHEN the mutant endpoint is called
		ResultActions resultActions = mockMvc.perform(get(URI));

		// THEN there is an JSON response and the status code is 200
		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		
		org.junit.Assert.assertEquals(statisticsDTO, content);
	}

}
