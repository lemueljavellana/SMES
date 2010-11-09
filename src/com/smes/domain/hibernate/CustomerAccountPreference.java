package com.smes.domain.hibernate;

public class CustomerAccountPreference extends BaseDomain {
	private int customerAccountPreferenceId;
	private int customerId;
	private Customer customer;
	private double interest;
	private double maximumAmount;

	public int getCustomerAccountPreferenceId() {
		return customerAccountPreferenceId;
	}
	public void setCustomerAccountPreferenceId(int customerAccountPreferenceId) {
		this.customerAccountPreferenceId = customerAccountPreferenceId;
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
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getMaximumAmount() {
		return maximumAmount;
	}
	public void setMaximumAmount(double maximumAmount) {
		this.maximumAmount = maximumAmount;
	}

	@Override
	public String toString() {
		return "CustomerAccountPreference [customer=" + customer
				+ ", customerAccountPreferenceId="
				+ customerAccountPreferenceId + ", customerId=" + customerId
				+ ", interest=" + interest + ", maximumAmount=" + maximumAmount
				+ "]";
	}
}
