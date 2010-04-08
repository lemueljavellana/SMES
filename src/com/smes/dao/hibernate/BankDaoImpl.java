package com.smes.dao.hibernate;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.BankDao;
import com.smes.domain.hibernate.Bank;

/**
 * Hibernate implementation for bank dao.
 * @author Lemuel Javellana
 *
 */
public class BankDaoImpl extends BaseDao<Bank> implements BankDao{

	@Override
	public Class<Bank> getDomainClass() {
		return Bank.class;
	}

	@Override
	public void deleteBank(Bank bank) {
		delete(bank);
	}

	@Override
	public void deleteBank(int bankId) {
		delete(bankId);
	}

	@Override
	public Collection<Bank> getAllBanks() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Bank.class);
		criteria.addOrder(Order.asc("bankName"));
		return getAll(criteria);
	}

	@Override
	public Bank getBank(int bankId) {	
		return get(bankId);
	}

	@Override
	public Bank getBank(String bankName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Bank.class);
		criteria.add(Restrictions.eq("bankName", bankName));
		criteria.addOrder(Order.asc("bankName"));
		return get(criteria);
	}

	@Override
	public void saveBank(Bank bank) {
		save(bank);
	}
}
