package com.smes.web.dto;

import java.util.Collection;

import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountType;

public class AccountDto extends Account {
	
	private Collection<AccountType> accountTypes;

	public Collection<AccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(Collection<AccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}

	@Override
	public String toString() {
		return "AccountDto [accountTypes=" + accountTypes
				+ ", getAccountDate()=" + getAccountDate()
				+ ", getAccountId()=" + getAccountId() + ", getAccountType()="
				+ getAccountType() + ", getAccountTypeId()="
				+ getAccountTypeId() + ", getAmount()=" + getAmount()
				+ ", getCompany()=" + getCompany() + ", getCompanyId()="
				+ getCompanyId() + ", getCustomer()=" + getCustomer()
				+ ", getCustomerId()=" + getCustomerId()
				+ ", getDescription()=" + getDescription()
				+ ", getReferenceNumber()=" + getReferenceNumber()
				+ ", toString()=" + super.toString() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedBy()=" + getModifiedBy()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
