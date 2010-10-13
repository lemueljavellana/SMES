package com.smes.domain.hibernate;

public class Customer extends BaseDomain {
	private int customerId;
	private int companyId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String contactNumber;
	private Company company;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "Customer [address=" + address + ", companyId=" + companyId
				+ ", contactNumber=" + contactNumber + ", customerId="
				+ customerId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + "]";
	}
}
