package com.smes.domain.hibernate;

public class UserGroupSetting {
	private int userGroupSettingsId;
	private int groupId;
	private String moduleName;
	private boolean allow;
	private Group group;
	
	public int getUserGroupSettingsId() {
		return userGroupSettingsId;
	}
	public void setUserGroupSettingsId(int userGroupSettingsId) {
		this.userGroupSettingsId = userGroupSettingsId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public boolean isAllow() {
		return allow;
	}
	public void setAllow(boolean allow) {
		this.allow = allow;
	}
	
}
