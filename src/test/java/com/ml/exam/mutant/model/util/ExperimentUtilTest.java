package com.ml.exam.mutant.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class ExperimentUtilTest {

	@Test
	public void checkDnaIsNull() {
		String[] dna = null;
		assertFalse(ExperimentUtil.validateMoleculesHumanDna(dna));
	}
	
	@Test
	public void checkDnaIsEmpty() {
		String[] dna = {""};
		assertTrue(ExperimentUtil.validateMoleculesHumanDna(dna));
	}
	
	@Test
	public void checkDnaIsNotEmpty() {
		String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
		assertTrue(ExperimentUtil.validateMoleculesHumanDna(dna));
	}
	
	@Test
	public void checkDnaHasMoleculesValid() {
		String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
		assertTrue(ExperimentUtil.validateMoleculesHumanDna(dna));
	}
	
	@Test
	public void checkDnaHasMoleculesInvalid() {
		String[] dna = {"RTRC", "GTGR", "TRCT", "AAGR"};
		assertFalse(ExperimentUtil.validateMoleculesHumanDna(dna));
	}
	
	@Test
	public void checkHumanDnaChainIsSquare() {
		String[] dna = {"RTRC", "GTGR", "TRCT", "AAGR"};
		assertTrue(ExperimentUtil.validateHumanDnaIsSquare(dna));
	}
	
	@Test
	public void checkHumanDnaChainIsNotSquare() {
		String[] dna = {"RTRC", "GTGRASA", "TRCT", "AAGR"};
		assertFalse(ExperimentUtil.validateHumanDnaIsSquare(dna));
	}	
	@Test
	public void isMutant() {
		String[] dna = {"AAAA", "AAAA", "AAAA", "CAGT"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkHorizontallyIfHumanDnaIsNotMutant() {
		String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkHorizontallyIfHumanDnaIsMutant() {
		String[] dna = {"AAAA", "CCCC", "TTTT", "GGGG"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	//Test to verificate mutant genes horizontally
	@Test
	public void checkIfThereAreMoreOfOneSecuencesMutantDnaHorizontally() {
		String[] dna = {"AAAA", "CCCC", "TTTT", "GGGG"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreLessOfOneSecuencesMutantDnaHorizontally() {
		String[] dna = {"AAAA", "CAGT", "CAGT", "CAGT"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreNotSecuencesMoleculesMutantDnaHorizontally() {
		String[] dna = {"CAGT", "AGTC", "GCAT", "AGTC"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}

	//Test to verificate mutant genes vertically
	@Test
	public void checkIfThereAreMoreOfOneSecuencesMutantDnaVertically() {
		String[] dna = {"ACTG", "ACTG", "ACTG", "ACTG"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreLessOfOneSecuencesMutantDnaVertically() {
		String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreNotSecuencesMoleculesMutantDnaVertically() {
		String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}
	
	//Test to verificate mutant genes diagonally
	@Test
	public void checkIfThereAreMoreOfOneSecuencesMutantDnaDiagonally() {
		String[] dna = {"TCAGT", "CTGTA", "AGTAC", "GTATT", "AACTA"};
		Assert.assertTrue(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreLessOfOneSecuencesMutantDnaDiagonally() {
		String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}
	
	@Test
	public void checkIfThereAreNotSecuencesMoleculesMutantDnaDiagonally() {
		String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
		Assert.assertFalse(ExperimentUtil.isMutant(dna));
	}
	
}
