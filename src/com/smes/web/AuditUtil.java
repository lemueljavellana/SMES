package com.smes.web;

import java.util.Date;

import com.smes.domain.hibernate.BaseDomain;
import com.smes.view.frm.Credential;

public class AuditUtil {
	
	/**
	 * Work in progress
	 * @param domain
	 */
	public static void addAudit (BaseDomain domain, Credential credential){
		domain.setCreatedBy(credential.getUserId());
		Date currentDate = new Date ();
		domain.setCreatedDate(currentDate);
		domain.setModifiedBy(credential.getUserId());
		domain.setModifiedDate(currentDate);
	}
}
