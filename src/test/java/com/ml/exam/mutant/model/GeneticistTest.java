package com.ml.exam.mutant.model;

import org.junit.Assert;
import org.junit.Test;

import com.ml.exam.mutant.model.util.StatusCode;

public class GeneticistTest {

	@Test
	public void instanceGeneticist() {
		Geneticist geneticist = new Geneticist(); 
		Assert.assertNotNull(geneticist);
	}
	
	@Test
	public void geneticistChecksHumanDnaIsMutant() {
		String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA"};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.SUCCESS,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksHumanDnaIsNotMutant() {
		String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.FORBIDDEN,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksIfHumanDnaIsEmpty() {
		String[] dna = {""};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.ERROR,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksIfHumanDnaIsNull() {
		String[] dna = {""};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.ERROR,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksIfHumanDnaIsSquare() {
		String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA"};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.SUCCESS,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksIfHumanDnaIsNotSquare() {
		String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA", "AAAA"};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.ERROR,geneticist.checkIfHumanDnaIsMutant(dna));
	}
	
	@Test
	public void geneticistChecksIfMoleculesOfHumanDnaAreOnlyString() {
		String[] dna = {"AAA1", "C2AT", "C*AT", "AAGA"};
		Geneticist geneticist = new Geneticist(); 
		Assert.assertEquals(StatusCode.ERROR,geneticist.checkIfHumanDnaIsMutant(dna));
	}
}
