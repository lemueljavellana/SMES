package com.smes.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.DepositLogDao;
import com.smes.domain.hibernate.Bank;
import com.smes.domain.hibernate.DepositLog;

public class DepositLogDaoImpl extends BaseDao<DepositLog> implements DepositLogDao{

	@Override
	protected Class<DepositLog> getDomainClass() {
		return DepositLog.class;
	}

	@Override
	public void deleteDeposit(int depositLogId) {
		delete(depositLogId);
	}

	@Override
	public DepositLog getDeposit(int depositLogId) {
		return get(depositLogId);
	}

	@Override
	public Collection<DepositLog> getDeposits(Date frm, Date to) {
		DetachedCriteria criteria = getCriteria();
		criteria.add(Restrictions.between("depositDate", frm, to));
		criteria.addOrder(Order.asc("depositDate"));

		return getAll(criteria);
	}

	@Override
	public Collection<DepositLog> getDeposits(Bank bank) {
		return getDeposits(bank.getBankId());
	}

	@Override
	public Collection<DepositLog> getDeposits(int bankId) {
		DetachedCriteria criteria = 
			DetachedCriteria.forClass(getDomainClass());
		criteria.add(Restrictions.eq("bankId", bankId));
		criteria.addOrder(Order.asc("depositDate"));
		return getAll(criteria);
	}

	@Override
	public void persistObject(DepositLog depositLog) {
		persist(depositLog);
	}

	@Override
	public void saveDeposit(DepositLog depositLog) {
		save(depositLog);
	}
	
}
