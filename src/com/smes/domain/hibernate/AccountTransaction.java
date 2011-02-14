package com.smes.domain.hibernate;

import com.smes.web.dto.FormatUtil;



/**
 * This is a class that holds the information for account table 
 * 
 * @author Lemuel Javellana
 *
 */
public class AccountTransaction extends Account {
	private double earnedInterest;
	private double totalAmount;
	private double runningTotal;

	public double getEarnedInterest() {
		return earnedInterest;
	}
	public void setEarnedInterest(double earnedInterest) {
		this.earnedInterest = earnedInterest;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getRunningTotal() {
		return runningTotal;
	}
	public void setRunningTotal(double runningTotal) {
		this.runningTotal = runningTotal;
	}

	//TODO: Better way of handling for data format.  
	public String getFormattedAccountDate () {
		return FormatUtil.format(getAccountDate());
	}
	
	public String getFormattedDueDate () {
		return FormatUtil.format(getDueDate());
	}
	
	public String getFormattedAmount () {
		return FormatUtil.format(getAmount());
	}
	
	public String getFormattedEarnedInterest () {
		return FormatUtil.format(earnedInterest);
	}
	
	public String getFormattedTotalAmount () {
		return FormatUtil.format(totalAmount);
	}
	
	public String getFormattedRunningTotal () {
		return FormatUtil.format(runningTotal);
	}
}
