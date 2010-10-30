package com.smes.dao.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.CustomerAccountPreferenceDao;
import com.smes.domain.hibernate.CustomerAccountPreference;

public class CustomerAccountPreferenceDaoImp extends BaseDao<CustomerAccountPreference> implements CustomerAccountPreferenceDao {
	
	@Override
	protected Class<CustomerAccountPreference> getDomainClass() {
		return CustomerAccountPreference.class;
	}
	
	@Override
	public CustomerAccountPreference getCustomerAcctPreferenceByCustomer(int customerId) {
		DetachedCriteria dc = getCriteria();
		dc.add(Restrictions.eq("customerId", customerId));
		return get(dc);
	}
	@Override
	public CustomerAccountPreference getCustomerAccountPreferenceById(int id) {
		return get(id);
	}
}
