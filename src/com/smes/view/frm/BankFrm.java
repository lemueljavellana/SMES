package com.smes.view.frm;

import java.util.List;

import com.smes.domain.hibernate.Bank;

public class BankFrm {
	private List<Bank> banks;
	private Bank bank;

	@Override
	public String toString() {
		return "BankFrm [bank=" + bank + ", banks=" + banks + "]";
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
}
