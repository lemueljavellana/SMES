package com.smes.domain.hibernate;

import java.util.Date;

public class PaymentAccountView extends BaseDomain {
	private int referenceId;
	private String transactionType;
	private Date referenceDate;
	private String refereneNumber;
	private String description;
	private double amount;
	private int companyId;
	private Company company;

	public int getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String getRefereneNumber() {
		return refereneNumber;
	}
	public void setRefereneNumber(String refereneNumber) {
		this.refereneNumber = refereneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
		return "PaymentAccountView [amount=" + amount + ", company=" + company
				+ ", companyId=" + companyId + ", description=" + description
				+ ", referenceDate=" + referenceDate + ", referenceId="
				+ referenceId + ", refereneNumber=" + refereneNumber
				+ ", transactionType=" + transactionType + "]";
	}

}
