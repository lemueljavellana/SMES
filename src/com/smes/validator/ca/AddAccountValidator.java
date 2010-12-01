package com.smes.validator.ca;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.smes.domain.hibernate.Account;

public class AddAccountValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "accountDate", "accountDate");
		ValidationUtils.rejectIfEmpty(errors, "referenceNumber", "referenceNumber");
		ValidationUtils.rejectIfEmpty(errors, "amount", "amount");
		Account account = (Account) obj;
		
		if (account.getAccountDate() == null) {
			errors.rejectValue("accountDate", null,
					null, "Account date is required");
		}
		String referenceNumber = account.getReferenceNumber();
		if (referenceNumber == null || referenceNumber.isEmpty()){
			errors.rejectValue("referenceNumber", null,
					null, "Reference number is required");
		}
		
		if (account.getAmount() == 0)
			errors.rejectValue("amount", null,
					null, "Amount is required");
	}
}
