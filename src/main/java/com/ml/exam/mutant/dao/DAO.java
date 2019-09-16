package com.ml.exam.mutant.dao;

/**
 * 
 * @author Jonathan Ivan Ferreira
 *
 * @param <T>
 */
public interface DAO<T> {
	
	public void save(T entity);
	
	public String findAll();
}
