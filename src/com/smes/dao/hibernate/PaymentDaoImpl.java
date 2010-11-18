package com.smes.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import com.smes.dao.PaymentDao;
import com.smes.domain.hibernate.Payment;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao{

	@Override
	protected Class<Payment> getDomainClass() {
		return Payment.class;
	}

	@Override
	public Collection<Payment> getPayments(int customerId, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Payment> getPayments(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalPayments(int customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
