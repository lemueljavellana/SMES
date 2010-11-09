package com.smes.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.impl.CriteriaImpl;

public class SMESDetachedCriteria extends DetachedCriteria {

	protected SMESDetachedCriteria(String entityName) {
		super(entityName);
	}
	
	public static SMESDetachedCriteria forClass (Class clazz){
		return new SMESDetachedCriteria (clazz.getName());
	}
}
