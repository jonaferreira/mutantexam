package com.ml.exam.mutant.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ml.exam.mutant.controller.dto.DnaDTO;
import com.ml.exam.mutant.dao.entity.Dna;
import com.ml.exam.mutant.dao.impl.DnaDAO;
import com.ml.exam.mutant.model.util.StatusCode;

/**
 * Class knows the steps to save dna verified or makes a static with this information saved.
 * 
 * @author Jonathan Ivan Ferreira
 */
@Service
public class Statistical {
	
	private static final Logger logger = LoggerFactory.getLogger(Statistical.class);
			
	@Autowired
	private DnaDAO dao;
	
	public StatusCode saveDnaVerified(DnaDTO dnaDto, StatusCode statusCode) {		
		logger.info("It is saving the dna verified.");
		
		try{
			Dna entity = new Dna();
		
			entity.setDna(new Gson().toJson( dnaDto.getDna() ) );
			boolean isMutant = statusCode==StatusCode.SUCCESS ? true : false;
			entity.setIsMutant(isMutant);
		
			dao.save(entity);
		
		} catch(Exception e) {
			statusCode=StatusCode.ERROR;
			logger.error("The following error occurred: " + e.getMessage());
		}
		
		return statusCode;
	}

	public String getStaticsOfDnaVerified(){
		logger.info("it is getting the statics of the dnas verified.");
		return dao.findAll();
	}
}
