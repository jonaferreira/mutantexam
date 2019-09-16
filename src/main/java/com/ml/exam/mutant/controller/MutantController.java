package com.ml.exam.mutant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ml.exam.mutant.controller.dto.DnaDTO;
import com.ml.exam.mutant.model.Geneticist;
import com.ml.exam.mutant.model.Statistical;
import com.ml.exam.mutant.model.util.StatusCode;

/**
 * This class is a handler HTTP REST. 
 * 
 * @author Jonathan Ivan Ferreira
 */
@RestController
public class MutantController {

	private static final Logger logger = LoggerFactory.getLogger(MutantController.class);
		
	@Autowired
	private Geneticist geneticist;
	
	@Autowired
	private Statistical statistical;
	
	/**
	 * This method receives a Json object wrapped by DnaDTO
	 * and Returns an HttpStatus depending the human dna sequence checked.
	 * 
	 * @param Json object wrapped by DnaDTO
	 * @return HttpStatus
	 */
	@RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<String> mutant(@RequestBody DnaDTO dnaDto) {
		
		logger.info("Inside the service mutant");
		
		StatusCode statusResult = geneticist.checkIfHumanDnaIsMutant(dnaDto.getDna());

		if (statusResult == StatusCode.ERROR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
		statusResult = statistical.saveDnaVerified(dnaDto, statusResult); 
		
		if(statusResult==StatusCode.SUCCESS){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

}
