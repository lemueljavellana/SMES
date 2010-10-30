package com.smes.domain.hibernate;

public class CustomerAccountPreference extends BaseDomain {
	private int customerAccountPreferenceId;
	private int customerId;
	private Customer customer;
	private double interest;
	
	public int getCustomerAccountPreferenceId() {
		return customerAccountPreferenceId;
	}

	public void setCustomerAccountPreferenceId(int customerAccountPreference) {
		this.customerAccountPreferenceId = customerAccountPreference;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public double getInterest() {
		return interest;
	}

	@Override
	public String toString() {
		return "CustomerAccountPreference [customer=" + customer
				+ ", customerAccountPreferenceId="
				+ customerAccountPreferenceId + ", customerId=" + customerId
				+ ", interest=" + interest + "]";
	}
}
