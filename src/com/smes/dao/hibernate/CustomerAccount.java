package com.smes.dao.hibernate;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.smes.domain.hibernate.BaseDomain;

public abstract class CustomerAccount<T extends BaseDomain> extends BaseDao<T> {
	public Collection<T> getByCompanyId (int companyId) {
		DetachedCriteria dc = getCriteria();
		dc.add(Restrictions.eq("companyId", companyId));
		return getAll(dc);
	}
}
