package com.smes.domain;

import java.util.Date;

/**
 * Almost the same in base domain. I guess for object oriented and appropriate object
 * name.
 * @author lemuel
 *
 */
public class AuditLog {
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private AuditLog (){
		// do nothing. Should use the getInstanceof
	}
	private AuditLog (int createdBy, Date createdDate, 
			int modifiedBy, Date modifiedDate){
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public static AuditLog getInstanceOf (int createdBy, Date createdDate, 
			int modifiedBy, Date modifiedDate){
		return new AuditLog(createdBy, createdDate, modifiedBy, modifiedDate);
	}

	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
