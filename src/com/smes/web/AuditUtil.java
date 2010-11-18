package com.smes.web;

import java.util.Date;

import com.smes.domain.hibernate.BaseDomain;

public class AuditUtil {
	
	/**
	 * Work in progress
	 * @param domain
	 */
	public static void addAudit (BaseDomain domain){
		domain.setCreatedBy(1);
		domain.setCreatedDate(new Date());
		domain.setModifiedBy(1);
		domain.setModifiedDate(new Date ());
	}
}
