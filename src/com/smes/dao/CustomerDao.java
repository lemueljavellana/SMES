package com.smes.dao;

import java.util.Collection;

import com.smes.domain.hibernate.Customer;

/**
 * 
 * @author lemuel
 */
public interface CustomerDao extends Dao <Customer>{
	/**
	 * Get the customer
	 * @param name customer name, either first name, middle name, last name
	 * @return
	 */
	Collection<Customer> getCustomer (String name);
}
