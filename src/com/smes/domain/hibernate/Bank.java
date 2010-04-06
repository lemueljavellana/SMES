package com.smes.domain.hibernate;

public class Bank extends BaseDomain {
	private int bankId;
	private String bankName;
	private String address;
	private String contactNumber;
	
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Bank [address=" + address + ", bankId=" + bankId
				+ ", bankName=" + bankName + ", contactNumber=" + contactNumber
				+ "]";
	}
	
}
