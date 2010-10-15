package com.smes.web.dto;

import java.util.Collection;

import com.smes.domain.hibernate.Customer;

public class CustomerListDto {
	private Collection<Customer> customers;
	
	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}
	
	public Collection<Customer> getCustomers() {
		return customers;
	}
	
	public static CustomerListDto getInstaceof (Collection<Customer> customers){
		CustomerListDto dto = new CustomerListDto();
		dto.customers = customers;
		return dto;
	}
}
