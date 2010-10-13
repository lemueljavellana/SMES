package com.smes.domain.hibernate;

public class Company extends BaseDomain {
	private int companyId;
	private String companyName;
	private String address;
	private String contactNumber;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		return "Company [address=" + address + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", contactNumber="
				+ contactNumber + "]";
	}

}
