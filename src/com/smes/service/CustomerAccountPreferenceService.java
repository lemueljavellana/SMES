package com.smes.service;

import com.smes.dao.CustomerAccountPreferenceDao;
import com.smes.domain.hibernate.CustomerAccountPreference;

public class CustomerAccountPreferenceService {
	private CustomerAccountPreferenceDao customerAccountPreferenceDao;
	
	public void setCustomerAccountPreferenceDao(
			CustomerAccountPreferenceDao customerAccountPreferenceDao) {
		this.customerAccountPreferenceDao = customerAccountPreferenceDao;
	}
	
	public void saveOrUpdate (CustomerAccountPreference pref){
		customerAccountPreferenceDao.saveOrUpdate(pref);
	}
	
	public void delete (int id){
		customerAccountPreferenceDao.delete(id);
	}
	
	public CustomerAccountPreference getCustomerAccountPreferenceByCustomer (int customerId){
		return customerAccountPreferenceDao.getCustomerAcctPreferenceByCustomer(customerId);
	}
	
	public CustomerAccountPreference getCustomerAccountPreference (int id) {
		return customerAccountPreferenceDao.getCustomerAccountPreferenceById(id);
	}
}
