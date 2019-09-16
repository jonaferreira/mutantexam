package com.ml.exam.mutant.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ml.exam.mutant.controller.dto.DnaDTO;
import com.ml.exam.mutant.dao.entity.Dna;
import com.ml.exam.mutant.dao.impl.DnaDAO;
import com.ml.exam.mutant.model.util.StatusCode;

@RunWith(MockitoJUnitRunner.class)
public class StatisticalTest {
	
	@InjectMocks
	private Statistical statistical;
	
	@Mock
	private DnaDAO dao;
	
	@Test
	public void instanceStatistical() {
		Assert.assertNotNull(statistical);
	}

	@Test
	public void checkStatisticalSaveDnaVerified() {
		String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};

		Dna dnaEntity = new Dna();
		dnaEntity.setDna(dna.toString());
		dnaEntity.setIsMutant(false);
				
		// GIVEN a count from humans and mutants
		Mockito.doNothing().when(dao).save(dnaEntity);
		
		StatusCode statusCode= StatusCode.SUCCESS;
		DnaDTO dnaDto = new DnaDTO();
		dnaDto.setDna(dna);

		Assert.assertEquals(StatusCode.SUCCESS,statistical.saveDnaVerified(dnaDto, statusCode));
	}
	

	@Test
	public void checkStatisticalGetStaticsOfDnaVerified() {

		String responseExpected = "{\"count_mutant_dna\":40, \"count_human_dna\":100, \"ratio\":0.4}";
		// GIVEN a count from humans and mutants
		Mockito.when(dao.findAll()).thenReturn(responseExpected);
		
		Assert.assertSame(responseExpected, statistical.getStaticsOfDnaVerified());
	}
	
}
