package com.ml.exam.mutant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ml.exam.mutant.model.Statistical;

/**
 * This class is a handler HTTP REST. 
 * 
 * @author Jonathan Ivan Ferreira
 */
@RestController
public class StatisticalController {

	private static final Logger logger = LoggerFactory.getLogger(StatisticalController.class);
		
	@Autowired
	private Statistical statistical;
	
	/**
	 * This method returns a Json Object with the statics number mutants and humans.
	 * 
	 * @return String json ibject
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	@ResponseBody
	public String stats() {
		logger.info("Inside the service stats");
		return statistical.getStaticsOfDnaVerified();
	}

}
