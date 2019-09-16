package com.ml.exam.mutant.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.ml.exam.mutant.model.util.ExperimentUtil;
import com.ml.exam.mutant.model.util.StatusCode;

/**
 * Class knows the steps to determinate if a human dna is mutant or not.  
 * 
 * @author Jonathan Ivan Ferreira
 */
@Service
public class Geneticist {
	
	private static final Logger logger = LoggerFactory.getLogger(Geneticist.class);
			
	/**
	 * This method is handler of know if a human dna is mutant or not.
	 * Also It verifies the correct format of human dna received.
	 * Return a code of response.
	 * 
	 * @param dna
	 * @return
	 */
	public StatusCode checkIfHumanDnaIsMutant(String[] dna){
		
		logger.info("It is checking If a human dna is mutant.");
		
		if(ExperimentUtil.validateMoleculesHumanDna(dna)==false){
			return StatusCode.ERROR;
		}
		
		if(ExperimentUtil.validateHumanDnaIsSquare(dna)==false){
			return StatusCode.ERROR;
		}
		
		if(ExperimentUtil.isMutant(dna)==false){
			return StatusCode.FORBIDDEN;
		}
		
		return StatusCode.SUCCESS;
	}

}
