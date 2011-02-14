package com.smes.web.dto;

import com.smes.domain.hibernate.AccountTransaction_toBeDeleted;

public class AccountTransactionDto {
	private int referenceId;
	private String transactionType;
	private String date;
	private String referenceNumber;
	private String description;
	private String payment;
	private String account;
	private String accountWithInterest;
	private String runningTotal;
	public static AccountTransactionDto getInstance (AccountTransaction_toBeDeleted at){
		AccountTransactionDto dto = new AccountTransactionDto();
		dto.referenceId = at.getReferenceId();
		dto.transactionType = String.valueOf(at.getTransactionType());
		dto.date = FormatUtil.format(at.getReferenceDate());
		dto.referenceNumber = at.getRefereneNumber();
		dto.description = at.getDescription();
		TransactionType tt = 
			TransactionType.getTransactionType(at.getTransactionType());
		switch (tt){
			case ACCOUNT:
				dto.payment = "";
				dto.account = FormatUtil.format(at.getAmount());
				dto.accountWithInterest =
					FormatUtil.format(at.getAmountWithInterest());
				break;
			case PAYMENT:
				dto.accountWithInterest = "";
				dto.account = "";
				dto.payment = FormatUtil.format(Math.abs(at.getAmount()));
				break;
		}
		dto.runningTotal = FormatUtil.format(at.getRunningTotal());
		return dto;
	}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRunningTotal() {
		return runningTotal;
	}

	public void setRunningTotal(String runningTotal) {
		this.runningTotal = runningTotal;
	}

	public String getAccountWithInterest() {
		return accountWithInterest;
	}

	public void setAccountWithInterest(String accountWithInterest) {
		this.accountWithInterest = accountWithInterest;
	}

	@Override
	public String toString() {
		return "AccountTransactionDto [account=" + account
				+ ", amountWithInterest=" + accountWithInterest + ", date="
				+ date + ", description=" + description + ", payment="
				+ payment + ", referenceId=" + referenceId
				+ ", referenceNumber=" + referenceNumber + ", runningTotal="
				+ runningTotal + ", transactionType=" + transactionType + "]";
	}
}
