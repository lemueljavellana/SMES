package com.smes.domain.hibernate;

public class AccountTransaction_toBeDeleted extends PaymentAccountView {
	private double runningTotal;

	public double getRunningTotal() {
		return runningTotal;
	}

	public void setRunningTotal(double runningTotal) {
		this.runningTotal = runningTotal;
	}

	@Override
	public String toString() {
		return "AccountTransaction [runningTotal=" + runningTotal + "]" + super.toString();
	}
	
}
