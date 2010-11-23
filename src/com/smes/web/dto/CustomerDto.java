package com.smes.web.dto;

import java.util.Collection;

import com.smes.domain.Page;
import com.smes.domain.hibernate.Customer;

public class CustomerDto {
	private Collection<Customer> customers;
	private String customerName;
	private Customer customer;
	private Page<Customer> page;
	
	public void setPage(Page<Customer> page) {
		this.page = page;
	}

	public Page<Customer> getPage() {
		return page;
	}
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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "CustomerDto [customer=" + customer + ", customerName="
				+ customerName + ", customers=" + customers + ", page=" + page
				+ "]";
	}

}
