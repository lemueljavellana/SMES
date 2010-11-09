package com.smes.domain.hibernate;

public class AccountType extends BaseDomain {
	private int accountTypeId;
	private String name;

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ", name=" + name
				+ "]";
	}
}
