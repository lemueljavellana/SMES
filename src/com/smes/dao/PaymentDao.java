package com.smes.dao;

import java.util.Collection;
import java.util.Date;

import com.smes.domain.hibernate.Payment;

public interface PaymentDao extends Dao<Payment>{
	/**
	 * Get the total payments
	 * @param customerId The customer's id
	 * @param from Start date
	 * @param to End date
	 * @return  The list of payments
	 */
	Collection<Payment> getPayments (int customerId, Date from, Date to);
	
	/**
	 * Get payments
	 * @param customerId customer id.
	 * @return The customers payment
	 */
	Collection<Payment> getPayments (int customerId);
	/**
	 * Get the total payment of the customer
	 * @param customerId customer id.
	 * @return The total payment amount
	 */
	double getTotalPayments (int customerId);
	
}
