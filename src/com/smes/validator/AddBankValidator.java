package com.smes.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smes.dao.hibernate.BankDaoImpl;
import com.smes.domain.hibernate.Bank;

public class AddBankValidator implements Validator {

	private BankDaoImpl bankDao;
	
	public BankDaoImpl getBankDao() {
		return bankDao;
	}

	public void setBankDao(BankDaoImpl bankDao) {
		this.bankDao = bankDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Bank.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bank bank = (Bank)target;
		if (bank.getBankName() == null
				|| bank.getBankName().equals("")){
			errors.rejectValue("bankName", "required", "required");
		} else if (bank.getBankId() == 0){
			System.out.println(bank.getBankId());
			Bank newBank = bankDao.getBank(bank.getBankName());
			if (newBank != null)
				errors.rejectValue("bankName", "bankName.error",
		                null, "Bank name already exists");
		}
		if (bank.getAddress() == null || bank.getAddress().equals(""))
			errors.rejectValue("address", "bankName.error",
	                null, "address is required");
		if (bank.getContactNumber() == null || bank.getContactNumber().equals(""))
			errors.rejectValue("contactNumber", "bankName.error",
	                null, "Invalaid contact number");
	}
}
