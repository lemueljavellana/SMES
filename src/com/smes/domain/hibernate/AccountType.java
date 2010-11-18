package com.smes.domain.hibernate;

public class AccountType extends BaseDomain {
	private int accountTypeId;
	private String name;
	private int companyId;
	private Company company;
	
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
 
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ", company="
				+ company + ", companyId=" + companyId + ", name=" + name + "]";
	}
}
