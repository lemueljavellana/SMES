package com.smes.dao;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.smes.domain.Page;
import com.smes.domain.PageSetting;

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
	 * Get objects given company id;
	 * @param companyId The company id.
	 * @return The objects that belong to the company.
	 */
	Collection<T> getAllByCompanyId (int companyId);
	/**
	 * Save the object.
	 * @param t The object to be save. All object that does not have id will be
	 * inserted otherwise is updated.
	 */
	void saveOrUpdate (T t);
	
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

	/**
	 * Get all data controlled by page. 
	 * @param companyId company id;
	 * @param pageSetting the page setting
	 * @return The page result.
	 */
	Page<T> getAll (int companyId, PageSetting pageSetting);
	
	/**
	 * Get all data controlled by page. 
	 * @param companyId company id;
	 * @param pageSetting the page setting
	 * @param order set the order of the result
	 * @return The page result.
	 */
	Page<T> getAll (int companyId, PageSetting pageSetting, Order order);
	
	/**
	 * Get all data controlled by page
	 * @param companyId Company id
	 * @param pageSetting the page setting
	 * @param order set the order of the result
	 * @param criteria criteria
	 * @return The page result
	 */
	Page<T> getAll (int companyId, PageSetting pageSetting,
					Order order, Criterion ...criteria);
}
