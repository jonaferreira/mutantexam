package com.ml.exam.mutant.controller.dto;

/**
 * Class to wrap a human dna string received.
 * 
 * @author Jonathan Ivan Ferreira
 */
public class DnaDTO {
	
	private String[] dna;
	
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	public String[] getDna() {
		return dna;
	}

}
