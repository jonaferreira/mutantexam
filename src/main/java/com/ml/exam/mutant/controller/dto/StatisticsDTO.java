package com.ml.exam.mutant.controller.dto;

/**
 * This class wraps statistics data.
 * 
 * @author Jonathan Ivan Ferreira
 */
public class StatisticsDTO {

	private Long count_mutant_dna;
	private Long count_human_dna;
	private Double ratio;

	public Long getMutants() {
		return count_mutant_dna;
	}

	public void setMutants(Long mutants) {
		this.count_mutant_dna = mutants;
	}

	public Long getHumans() {
		return count_human_dna;
	}

	public void setHumans(Long humans) {
		this.count_human_dna = humans;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
}
