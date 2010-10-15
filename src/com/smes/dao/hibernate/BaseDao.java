package com.smes.dao.hibernate;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smes.dao.Dao;
import com.smes.domain.hibernate.BaseDomain;

/**
 * Handle the basic processing of the hibernate base doa.
 * @author lemuel
 *
 * @param <T> Domain object
 */
public abstract class BaseDao<T extends BaseDomain> extends HibernateDaoSupport implements Dao<T>{
	
	/**
	 * Get the domain class type.
	 * @return The domain class type.
	 */
	protected abstract Class<T> getDomainClass ();
	
	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(int id) {
		T t = get (id);
		delete(t);
	}

	@Override
	public T get(int id) {
		return getHibernateTemplate().get(getDomainClass(), id);	
	}

	@Override
	public Collection<T> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(getDomainClass());
		return getAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> getAll(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Collection<T> getAllByCompanyId(int companyId) {
		DetachedCriteria dc = getCriteria();
		dc.add(Restrictions.eq("companyId", companyId));
		return getAll(dc);
	}

	@Override
	public T get(DetachedCriteria criteria) {
		Collection<T> result = getAll(criteria);
		if (result == null || result.isEmpty())
			return null;
		return result.iterator().next();
	}
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void persist (T t){
		getHibernateTemplate().persist(t);
	}
	
	public DetachedCriteria getCriteria (){
		return DetachedCriteria.forClass(getDomainClass());
	}
}
