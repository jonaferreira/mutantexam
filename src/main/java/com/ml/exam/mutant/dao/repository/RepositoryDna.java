package com.ml.exam.mutant.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ml.exam.mutant.dao.entity.Dna;

/**
 * This interface can perform Spring Data CRUD operations.
 * 
 * @author Jonathan Ivan Ferreira
 *
 */
public interface RepositoryDna extends CrudRepository<Dna, Long> {
	
	/**
	 * Returns quantity of mutants stored in database.
	 * 
	 * @return Long
	 */
	@Query("SELECT COUNT (a) From Dna a WHERE a.isMutant=true")	
	public Long countMutants();

	/**
	 * Returns quantity of humans stored in database.
	 * 
	 * @return Long
	 */
	@Query("SELECT COUNT (a) From Dna a WHERE a.isMutant=false")
	public Long countNotMutants();

}
