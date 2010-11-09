package com.smes.domain.hibernate;

import java.util.Date;

public class Payment extends BaseDomain {
	private int paymentId;
	private Date paymentDate;
	private String referenceNumber;
	private String description;
	private double amount;
	private int companyId;
	private Company company;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
		return "Payment [amount=" + amount + ", company=" + company
				+ ", companyId=" + companyId + ", description=" + description
				+ ", paymentDate=" + paymentDate + ", paymentId=" + paymentId
				+ ", referenceNumber=" + referenceNumber + "]";
	}
}
