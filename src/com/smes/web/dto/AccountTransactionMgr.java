package com.smes.web.dto;

import java.util.Collection;

import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountType;
import com.smes.domain.hibernate.Customer;

public class AccountTransactionMgr {
	private Collection<AccountTransactionDto> accountTrasactionDtos;
	private Customer customer;
	private Account account;
	private Collection<AccountType> accountTypes;
	public Collection<AccountTransactionDto> getAccountTrasactionDtos() {
		return accountTrasactionDtos;
	}
	public void setAccountTrasactionDtos(
			Collection<AccountTransactionDto> accountTrasactionDtos) {
		this.accountTrasactionDtos = accountTrasactionDtos;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Collection<AccountType> getAccountTypes() {
		return accountTypes;
	}
	public void setAccountTypes(Collection<AccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}
	@Override
	public String toString() {
		return "AccountTransactionMgr [account=" + account
				+ ", accountTrasactionDtos=" + accountTrasactionDtos
				+ ", accountTypes=" + accountTypes + ", customer=" + customer
				+ "]";
	}
	
}
