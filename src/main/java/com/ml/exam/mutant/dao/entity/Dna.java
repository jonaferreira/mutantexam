package com.ml.exam.mutant.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

/**
 * Class used for save in the database as a table.
 * 
 * @author Jonathan Ivan Ferreira
 */
@Entity
@Table(name = "dna", schema = "public")
public class Dna {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "dna_string")
	private String dna;
	
	@Column(name = "isMutant")
	@Type(type="true_false")
	@NotNull
	private Boolean isMutant;
	
	public void setDna(String dna) {
		this.dna = dna;
	}
	
	public String getDna() {
		return dna;
	}
	
	public Boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}
	
}
