package com.smes.service;

import com.smes.dao.CustomerDao;
import com.smes.domain.hibernate.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/*public CustomerDao getCustomerDao() {
		return customerDao;
	}*/

	public void saveCustomer (Customer customer){
		customerDao.save(customer);
	}

	public Customer getCustomer (Integer customerId) {
		return customerDao.get(customerId);
	}

	public void deleteCustomer (Customer customer){
		customerDao.get(customer.getCompanyId());
	}

	public void updateCustomer (Customer customer){
		customerDao.persist(customer);
	}
}
