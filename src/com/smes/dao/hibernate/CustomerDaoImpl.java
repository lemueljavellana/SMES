package com.smes.dao.hibernate;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.smes.dao.CustomerDao;
import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Customer;

public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {

	@Override
	protected Class<Customer> getDomainClass() {
		return Customer.class;
	}

	@Override
	public Page<Customer> getCustomers(int companyId, String name, int pageNumber) {
		DetachedCriteria criteria = getCriteria();
		criteria.add(Restrictions.eq("companyId", companyId));
		SimpleExpression fne = Restrictions.like("firstName", "%" + name + "%");
		SimpleExpression lne = Restrictions.like("lastName", "%" + name + "%");
		criteria.add(Restrictions.or(fne, lne));
		criteria.addOrder(Order.asc("firstName"));
		PageSetting ps = new PageSetting(pageNumber);
		return getAll(companyId, ps, Order.asc("firstName"), Restrictions.or(fne, lne));
	}
	
	@Override
	public Collection<Customer> getCustomers(String fname, String lName) {
		DetachedCriteria criteria = getCriteria();
		criteria.add(Restrictions.like("firstName", fname));
		criteria.add(Restrictions.like("lastName", lName));
		criteria.addOrder(Order.asc("firstName"));
		return getAll(criteria);
	}
	
	@Override
	public Collection<Customer> getAllByCompanyId(int companyId) {
		DetachedCriteria dc = getCriteria();
		dc.add(Restrictions.eq("companyId", companyId));
		dc.addOrder(Order.asc("firstName"));
		return getAll(dc);
	}

	@Override
	public Page<Customer> getCustomers(int companyId, int pageNumber) {
		PageSetting ps = new PageSetting(pageNumber);
		return getAll(companyId, ps, Order.asc("firstName"));
	}
	
	
}
