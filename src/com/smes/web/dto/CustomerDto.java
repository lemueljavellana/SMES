package com.smes.web.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.smes.domain.hibernate.Customer;

public class CustomerDto {
	private Collection<Customer> customers;
	private String customerName;
	private Customer customer;

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}
	
	public Collection<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public static CustomerDto getInstaceof (Collection<Customer> customers){
		CustomerDto dto = new CustomerDto();
		dto.customers = customers;
		return dto;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
}
