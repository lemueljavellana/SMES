package com.smes.domain.hibernate;

import java.util.Date;

public class DepositLog extends BaseDomain {
	private int depositLogId;
	private Date depositDate;
	private String remarks;
	private double amount;
	private int bankId;
	private Bank bank;
	
	public int getDepositLogId() {
		return depositLogId;
	}
	
	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public void setDepositLogId(int depositLogId) {
		this.depositLogId = depositLogId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "DepositLog [amount=" + amount + ", bank=" + bank + ", bankId="
				+ bankId + ", depositDate=" + depositDate + ", depositLogId="
				+ depositLogId + ", remarks=" + remarks + "]";
	}
	
}
