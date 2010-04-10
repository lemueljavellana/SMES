package com.smes.view.frm;

import java.util.Collection;
import java.util.Date;

import com.smes.domain.hibernate.Bank;

public class BankTransactionFrm {
	private Bank bank;
	private Date dateFrom;
	private Date dateTo;
	private Collection<BankTransaction> bankTransactions;
	private Collection<Bank> banks;
	
	public void setBankTransactions(Collection<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public Collection<BankTransaction> getBankTransactions() {
		return bankTransactions;
	}

	public Collection<Bank> getBanks() {
		return banks;
	}

	public void setBanks(Collection<Bank> banks) {
		this.banks = banks;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return "BankTransactionFrm [bank=" + bank + ", bankTransactions="
				+ bankTransactions + ", banks=" + banks + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + "]";
	}
	
}
