package com.ml.exam.mutant.dao.impl;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ml.exam.mutant.controller.dto.StatisticsDTO;
import com.ml.exam.mutant.dao.DAO;
import com.ml.exam.mutant.dao.entity.Dna;
import com.ml.exam.mutant.dao.repository.RepositoryDna;

/**
 * This class is responsible to get data from a datasource.
 * 
 * @author Jonathan Ivan Ferreira
 */
@Service
public class DnaDAO implements DAO<Dna> {
	
	private static final Logger logger = LoggerFactory.getLogger(DnaDAO.class);
	
	@Autowired
	private RepositoryDna repository;
	

	/**
	 * This method is saving in the database of the human dna verified.
	 * 
	 */
	public void save(Dna entity) {
		logger.info("It is saving in the database of the human dna verified.");
		repository.save(entity);
	}

	/**
	 * This method is finding in the database all the human dnas verified.
	 * 
	 * return String Json response
	 */
	public String findAll() {
		logger.info("It is finding in the database all the human dnas verified.");
		
		StatisticsDTO statisticsDTO = new StatisticsDTO();
		statisticsDTO.setMutants(repository.countMutants());
		statisticsDTO.setHumans(repository.countNotMutants());

		String response = formatResponse(statisticsDTO);
		
		return response;
	}

	/**
	 * Make Json response
	 * 
	 * @param statisticsDTO
	 * @return String formatted
	 */
	private String formatResponse(StatisticsDTO statisticsDTO) {
		Double ratio = 0D;
		String response = "";
		
		if (statisticsDTO.getMutants() > 0 && statisticsDTO.getHumans() > 0) {
			ratio = statisticsDTO.getMutants().doubleValue() / statisticsDTO.getHumans();
			String ratioString = new DecimalFormat("#.##").format(ratio);
			statisticsDTO.setRatio(Double.valueOf(ratioString));
		} else {
			statisticsDTO.setRatio(0.0);
		}
		
		response = (statisticsDTO != null) ? new Gson().toJson(statisticsDTO) : "";
		return response;
	}

}
