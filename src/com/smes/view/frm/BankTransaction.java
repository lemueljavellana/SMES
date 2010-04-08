package com.smes.view.frm;

import java.util.Date;

public class BankTransaction implements Comparable<BankTransaction>{
	private int bankId;
	private Date date;
	private String remarks;
	private double withdrawalAmount;
	private double depositAmount;
	private double availableAmount;
	
	public BankTransaction (int bankId, Date date, String remarks, double withdrawalAmount, double depositAmount, double availableAmount){
		this.bankId = bankId;
		this.date = date;
		this.remarks = remarks;
		this.withdrawalAmount = withdrawalAmount;
		this.depositAmount = depositAmount;
		this.availableAmount = availableAmount;
	}

	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public double getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public int compareTo(BankTransaction o) {
		long dateLong = date.getTime();
		long oDateLong = o.date.getTime();
		return dateLong > oDateLong ? 1 : -1;
	}
	@Override
	public String toString() {
		return "BankTransaction [availableAmount=" + availableAmount
				+ ", bankId=" + bankId + ", date=" + date + ", depositAmount="
				+ depositAmount + ", remarks=" + remarks
				+ ", withdrawalAmount=" + withdrawalAmount + "]";
	}
	
}
