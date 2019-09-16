package com.ml.exam.mutant.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ml.exam.mutant.dao.entity.Dna;
import com.ml.exam.mutant.dao.repository.RepositoryDna;

@RunWith(MockitoJUnitRunner.class)
public class DnaDAOTest {

	@InjectMocks
	private DnaDAO dnaDAO;
	
	@Mock
	private RepositoryDna repository;
	
	@Test
	public void checkFunctionalOfDnaSave(){
		try{
			String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
	
			Dna dnaEntity = new Dna();
			dnaEntity.setDna(dna.toString());
			dnaEntity.setIsMutant(false);
	
			Mockito.when(repository.save(dnaEntity)).thenReturn(dnaEntity);
			
			dnaDAO.save(dnaEntity);
		
		} catch(Exception e) {
			Assert.fail("There is a problem in the functioning of method.");
		}
	}
	
	@Test
	public void checkFunctionalOfFindAllWithDefaultrResponse(){
		try{
			String dnaExpected = "{\"count_mutant_dna\":0,\"count_human_dna\":0,\"ratio\":0.0}";

			Mockito.when(repository.countMutants()).thenReturn(0L);
			Mockito.when(repository.countNotMutants()).thenReturn(0L);
			
			Assert.assertEquals(dnaExpected, dnaDAO.findAll());
		
		} catch(Exception e) {
			Assert.fail("There is a problem in the functioning of method.");
		}
	}
	
	
	@Test
	public void checkFunctionalOfFindAllWithRadioIsOne(){
		try{
			String dnaExpected = "{\"count_mutant_dna\":1,\"count_human_dna\":1,\"ratio\":1.0}";

			Mockito.when(repository.countMutants()).thenReturn(1L);
			Mockito.when(repository.countNotMutants()).thenReturn(1L);
			
			Assert.assertEquals(dnaExpected, dnaDAO.findAll());
		
		} catch(Exception e) {
			Assert.fail("There is a problem in the functioning of method.");
		}
	}
	
}
