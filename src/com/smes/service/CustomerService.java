package com.smes.service;

import java.util.Collection;


import com.smes.dao.CustomerDao;
import com.smes.domain.Page;
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
	
	public Customer getCustomer (String fName, String lName, int companyId) {
		return customerDao.getCustomers(fName, lName, companyId).iterator().next();
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

	public Page<Customer> getCustomers (String name, int companyId, int pageNumber){
		return customerDao.getCustomers(companyId, name, pageNumber);
	}

	public boolean uniqueCustomer (String firstName, String lastName, int companyId){
		Collection<Customer> customers = customerDao.getCustomers(firstName, lastName, companyId);
		return customers == null || customers.size() < 1; 
	}
	
	public Page<Customer> getCustomers (int companyId, int pageNumber) {
		return customerDao.getCustomers(companyId, pageNumber);
	}
}
