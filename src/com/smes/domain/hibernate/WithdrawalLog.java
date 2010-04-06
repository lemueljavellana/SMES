package com.smes.domain.hibernate;

import java.util.Date;

public class WithdrawalLog extends BaseDomain {
	private int withdrawalLogId;
	private Date withdrawalDate;
	private double amount;
	private String remarks;
	private int bankId;
	private Bank bank;
	
	public int getWithdrawalLogId() {
		return withdrawalLogId;
	}
	public void setWithdrawalLogId(int withdrawalLogId) {
		this.withdrawalLogId = withdrawalLogId;
	}
	public Date getWithdrawalDate() {
		return withdrawalDate;
	}
	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "WithdrawalLog [amount=" + amount + ", bank=" + bank
				+ ", bankId=" + bankId + ", remarks=" + remarks
				+ ", withdrawalDate=" + withdrawalDate + ", withdrawalLogId="
				+ withdrawalLogId + "]";
	}
	
	
}
