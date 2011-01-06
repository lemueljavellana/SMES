package com.smes.domain.hibernate;

import java.util.Date;

public class Account extends BaseDomain {
	private int accountId;
	private int customerId;
	private Customer customer;
	private Date accountDate;
	private Date dueDate;
	private String referenceNumber;
	private String description;
	private int companyId;
	private Company company;
	private int accountTypeId;
	private AccountType accountType;
	private double amount;

	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
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
	public Date getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Account [accountDate=" + accountDate + ", accountId="
				+ accountId + ", accountType=" + accountType
				+ ", accountTypeId=" + accountTypeId + ", amount=" + amount
				+ ", company=" + company + ", companyId=" + companyId
				+ ", customer=" + customer + ", customerId=" + customerId
				+ ", description=" + description + ", referenceNumber="
				+ referenceNumber + "]";
	}
}
