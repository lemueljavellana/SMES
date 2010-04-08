package com.smes.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.smes.dao.DepositLogDao;
import com.smes.dao.WithdrawalLogDao;
import com.smes.domain.hibernate.DepositLog;
import com.smes.domain.hibernate.WithdrawalLog;
import com.smes.view.frm.BankTransaction;

/**
 * TODO: Must consider paging!!!
 * @author lemuel
 *
 */
public class BankTransactionService {
	private WithdrawalLogDao withdrawalLogDao;
	private DepositLogDao depositLogDao;

	public WithdrawalLogDao getWithdrawalLogDao() {
		return withdrawalLogDao;
	}
	
	public DepositLogDao getDepositLogDao() {
		return depositLogDao;
	}
	
	public Collection<BankTransaction> getBankTransaction (int bankId){
		List<BankTransaction> bankTransactions = new ArrayList<BankTransaction>();
		Collection<DepositLog> depositLogs = depositLogDao.getDeposits (bankId);
		Collection<WithdrawalLog> withdrawalLogs = withdrawalLogDao.getWithdrawals (bankId);
		//Populate first the bank transaction without the total.
		for (DepositLog dl : depositLogs)
			bankTransactions.add(new BankTransaction (bankId, dl.getDepositDate(),
					dl.getRemarks(), 0, dl.getAmount(), 0));
		for (WithdrawalLog wl : withdrawalLogs)
			bankTransactions.add(new BankTransaction (bankId, wl.getWithdrawalDate(),
					wl.getRemarks(), 0, wl.getAmount (), 0));
		Collections.sort (bankTransactions);
		setAvailableBalance (bankTransactions);
		return bankTransactions;
	}
	
	private void setAvailableBalance (Collection<BankTransaction> bankTransactions){
		BankTransaction prev = null;
		for (BankTransaction bt : bankTransactions){
			double amount = bt.getDepositAmount () - bt.getWithdrawalAmount();
			if (prev == null){
				bt.setAvailableAmount (amount);
				prev = bt;
			}
			bt.setAvailableAmount (prev.getAvailableAmount() + amount);
		}
	}
}
