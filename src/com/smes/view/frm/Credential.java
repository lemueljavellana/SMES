package com.smes.view.frm;


public class Credential {
	private String userName;
	private String password;
	private String companyName;
	private String message;
	private int companyId;
	private int userId;
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	
	@Override
	public String toString() {
		return "Credential [companyId=" + companyId + ", companyName="
				+ companyName + ", message=" + message + ", password="
				+ password + ", userId=" + userId + ", userName=" + userName
				+ "]";
	}
	
}
