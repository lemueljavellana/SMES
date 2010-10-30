package com.smes.dao;

import com.smes.domain.hibernate.CustomerAccountPreference;

public interface CustomerAccountPreferenceDao {
	CustomerAccountPreference getCustomerAcctPreferenceByCustomer (int customerId);
	
	CustomerAccountPreference getCustomerAccountPreferenceById (int id);
	
	void saveOrUpdate (CustomerAccountPreference pref);
	
	void delete (int customerId);
}
