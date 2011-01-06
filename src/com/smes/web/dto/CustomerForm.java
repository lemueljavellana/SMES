package com.smes.web.dto;

import com.smes.domain.hibernate.Customer;
import com.smes.domain.hibernate.CustomerAccountPreference;

public class CustomerForm {
	private Customer customer;
	private CustomerAccountPreference customerAccountPreference;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerAccountPreference getCustomerAccountPreference() {
		return customerAccountPreference;
	}

	public void setCustomerAccountPreference(
			CustomerAccountPreference customerAccountPreference) {
		this.customerAccountPreference = customerAccountPreference;
	}

	@Override
	public String toString() {
		return "AddEditAccountDto [customer=" + customer
				+ ", customerAccountPreference=" + customerAccountPreference
				+ "]";
	}
}
