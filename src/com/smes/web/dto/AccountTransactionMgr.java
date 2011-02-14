package com.smes.web.dto;

import java.util.Collection;

import com.smes.domain.Page;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.domain.hibernate.AccountType;
import com.smes.domain.hibernate.Customer;

public class AccountTransactionMgr {
	private Page<AccountTransaction> page;
	private Customer customer;
	private Account account;
	private Collection<AccountType> accountTypes;
	
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
	public Page<AccountTransaction> getPage() {
		return page;
	}
	public void setPage(Page<AccountTransaction> page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "AccountTransactionMgr [page=" + page + ", customer=" + customer
				+ ", account=" + account + ", accountTypes=" + accountTypes
				+ "]";
	}
	
}
