package com.smes.domain;

import java.util.Date;

import com.smes.domain.hibernate.BaseDomain;
import com.smes.domain.hibernate.User;

/**
 * Utility for domain audit.
 * @author lemuel
 *
 */
public class DomainAuditUtil {
	/**
	 * Add audit in the domain. 
	 * @param domain The domain which will be added an audit.
	 * @param user the user who modified the domain.
	 * @param date the date that the user modify the domain.
	 */
	public static void addAudit (BaseDomain domain, User user, Date date){
		domain.setCreatedBy(user.getUserId());
		domain.setCreatedDate(date);
		domain.setModifiedBy(user.getUserId());
		domain.setModifiedDate(date);
	}
	
	/**
	 * Update the existing audit of a domain.
	 * @param domain domain that will be updated
	 * @param user The user who modified the domain.
	 * @param date The date the user modify the domain.
	 */
	public static void updateAudit (BaseDomain domain, User user, Date date){
		domain.setModifiedBy(user.getUserId());
		domain.setModifiedDate(date);
	}
}
