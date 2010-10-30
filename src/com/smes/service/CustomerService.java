package com.smes.service;

import java.util.Collection;


import com.smes.dao.CustomerDao;
import com.smes.domain.hibernate.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void saveCustomer (Customer customer){
		customerDao.saveOrUpdate(customer);
	}

	public Customer getCustomer (Integer customerId) {
		return customerDao.get(customerId);
	}

	public void deleteCustomer (Customer customer){
		customerDao.get(customer.getCompanyId());
	}

	public void deleteCustomer (int customerId){
		customerDao.delete(customerId);
	}
	
	public void updateCustomer (Customer customer){
		customerDao.persist(customer);
	}

	public Collection<Customer> getAllCustomers (int companyId){
		return customerDao.getAllByCompanyId(companyId);
	}

	public Collection<Customer> getCustomers (String name, int companyId){
		return customerDao.getCustomers(companyId, name);
	}

	public boolean uniqueCustomer (String firstName, String lastName){
		Collection<Customer> customers = customerDao.getCustomers(firstName, lastName);
		return customers == null || customers.size() < 1; 
	}
}