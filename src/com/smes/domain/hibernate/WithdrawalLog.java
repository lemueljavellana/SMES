package com.smes.domain.hibernate;

import java.util.Date;

public class WithdrawalLog extends BaseDomain {
	private int withdrawalLogId;
	private Date withdrawalDate;
	private double amount;
	private String remarks;
	private int bankId;
	private Bank bank;
	public WithdrawalLog (){
		//needed for hibernate.
	}
	
	private WithdrawalLog (int withdrawalLogId, Date withdrawalDate,
			String remarks, double amount, int bankId){
		this.withdrawalLogId = withdrawalLogId;
		this.withdrawalDate = withdrawalDate;
		this.remarks = remarks;
		this.amount = amount;
		this.bankId = bankId;
	}

	/**
	 * Get the instance of this class. The audit of this class has no value. If you want to get its value, persist this object.
	 * @param withdrawalLogId The withdrawal log id.
	 * @param withdrawalDate The withdrawal date.
	 * @param remarks The remarks for this withdrawal.
	 * @param amount the amount to be withdrawn
	 * @param bankId the bank id that is associated with this withdrawal.
	 * @return
	 */
	public static WithdrawalLog getInstanceOf (int withdrawalLogId, Date withdrawalDate,
			String remarks, double amount, int bankId){
		return new WithdrawalLog(withdrawalLogId, withdrawalDate, remarks, amount, bankId);
	}
	
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
