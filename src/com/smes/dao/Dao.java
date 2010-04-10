package com.smes.dao;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Defines the basic attribute of a DAO (Data Access Object).
 * @author Lemuel M. Javellana
 *
 */
public interface Dao <T> {
	/**
	 * Get the object 
	 * @param id The object's id.
	 * @return the object.
	 */
	T get (int id);
	/**
	 * Get the object given the criteria.
	 * @param criteria The criteria in search of the object.
	 * @return the expected object. Null if not found.
	 */
	T get (DetachedCriteria criteria);
	/**
	 * Get all of the objects
	 * @return the objects that belong to this dao.
	 */
	Collection<T> getAll ();
	
	/**
	 * Get all of the objects given the criteria.
	 * @param criteria The criteria that will be used in getting the objects.
	 * @return The objects that belongs to this dao.
	 */
	Collection<T> getAll (DetachedCriteria criteria);
	/**
	 * Save the object.
	 * @param t The object to be save. All object that does not have id will be
	 * inserted otherwise is updated.
	 */
	void save (T t);
	
	/**
	 * Delete the object.
	 * @param t The object to be deleted.
	 */
	void delete (T t);
	
	/**
	 * Delete the object
	 * @param id The object id that will be deleted.
	 */
	void delete (int id);
	
	/**
	 * Persist the object.
	 * @param t the object to be persisted.
	 */
	void persist (T t);
}
