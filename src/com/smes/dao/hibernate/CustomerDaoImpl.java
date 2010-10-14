package com.smes.dao.hibernate;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.CustomerDao;
import com.smes.domain.hibernate.Customer;

public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao{

	@Override
	protected Class<Customer> getDomainClass() {
		return Customer.class;
	}

	@Override
	public Collection<Customer> getCustomer(String name) {
		DetachedCriteria criteria = getCriteria();
		criteria.add(Restrictions.like("firstName", "%"+name+"%"));
		criteria.add(Restrictions.like("middleName", "%"+name+"%"));
		criteria.add(Restrictions.like("lastName", "%"+name+"%"));
		return null;
	}
}
