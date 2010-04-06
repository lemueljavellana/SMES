package com.smes.service;

import java.util.Collection;

import com.smes.dao.BankDao;
import com.smes.domain.hibernate.Bank;

public class BankService {
	private BankDao bankDao;

	public BankDao getBankDao() {
		return bankDao;
	}
	
	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}
	
	public void saveBank (Bank bank){
		bankDao.saveBank(bank);
	}
	
	public void deleteBank (Bank bank){
		bankDao.deleteBank(bank);
	}
	
	public void deleteBank (int bankId){
		bankDao.deleteBank(bankId);
	}
	
	public Bank getBank (int bankId){
		return bankDao.getBank(bankId);
	}
	
	public Collection<Bank> getBanks (){
		return bankDao.getAllBanks();
	}
}
