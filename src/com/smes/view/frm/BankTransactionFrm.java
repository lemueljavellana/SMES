package com.smes.view.frm;

import java.util.Collection;

import com.smes.domain.hibernate.Bank;

public class BankTransactionFrm {
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

	@Override
	public String toString() {
		return "BankTransactionFrm [bankTransactions=" + bankTransactions
				+ ", banks=" + banks + "]";
	}
}
