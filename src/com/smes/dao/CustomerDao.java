package com.smes.dao;

import java.util.Collection;

import com.smes.domain.Page;
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
	Page<Customer> getCustomers (int companyId, String name, int pageNumber);
	
	/**
	 * Get the customer given first name and last name
	 * @param fname The first name of the customer
	 * @param lName The last name of the customer
	 * @param companyId the company id.
	 * @return The list of the customers.
	 */
	Collection<Customer> getCustomers (String fname, String lName, int companyId);	
	
	Page<Customer> getCustomers (int companyId, int pageNumber);
}
