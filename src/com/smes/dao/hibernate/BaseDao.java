package com.smes.dao.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smes.dao.Dao;
import com.smes.domain.Page;
import com.smes.domain.PageSetting;
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
		return getAll(getCriteriaByCompanyId(companyId));
	}
	
	private DetachedCriteria getCriteriaByCompanyId(int companyId) {
		DetachedCriteria dc = getCriteria();
		dc.add(Restrictions.eq("companyId", companyId));
		return dc;
	}

	@Override
	public T get(DetachedCriteria criteria) {
		Collection<T> result = getAll(criteria);
		if (result == null || result.isEmpty())
			return null;
		return result.iterator().next();
	}
	
	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void persist (T t){
		getHibernateTemplate().persist(t);
	}
	
	public DetachedCriteria getCriteria (){
		return DetachedCriteria.forClass(getDomainClass());
	}
	
	@Override
	public Page<T> getAll(int companyId, PageSetting pageSetting) {
		// getting the data
		return getAll(companyId, pageSetting, null);
	}
	
	@Override
	public Page<T> getAll(int companyId, PageSetting pageSetting, Order order) {	
		return getAll(companyId, pageSetting, order, null);
	}
	@Override
	public Page<T> getAll (int companyId, PageSetting pageSetting, Order order, Criterion ...criteria) {
		DetachedCriteria dc = getCriteriaByCompanyId(companyId);
		if (criteria != null)
			for (Criterion c : criteria)
				dc.add(c);
		if (order != null)
			dc.addOrder(order);
		List<?> result = getHibernateTemplate().findByCriteria(dc, pageSetting.getStartResult(), pageSetting.getMaxResult());
		dc = getCriteriaByCompanyId(companyId);
		if (criteria != null)
			for (Criterion c : criteria)
				dc.add(c);
		dc.setProjection(Projections.rowCount());
		List<?> count = getHibernateTemplate().findByCriteria(dc);		
		return new Page (pageSetting, result, (Integer) count.iterator().next());
	}
	
	public <A> Page<A> getAllAsPage (String sql, PageSetting pageSetting, QueryResultHandler<A> handler){
		String sqlCount = "SELECT count(*) as TOTAL_COUNT FROM (" + sql +") as tc";
		List<A> result = null;
		Session session = null;
		int totalRecords = 0;
		try {
			session = getSession();
			SQLQuery query = null;
			boolean addPaging = false;
			if (pageSetting.getMaxResult() != PageSetting.NO_PAGE_CONSTRAINT){
				query = session.createSQLQuery(sql +" " + "LIMIT ?,?");
				addPaging = true;
			} else {
				query = session.createSQLQuery(sql);
			}
			int lastIndex = handler.setParamater(query) + 1;
			if (addPaging){
				query.setParameter(lastIndex++, pageSetting.getStartResult());
				query.setParameter(lastIndex, pageSetting.getMaxResult());
			}
			List<Object[]> queryResult = query.list();
			result = handler.convert(queryResult);
			query = session.createSQLQuery(sqlCount);
			handler.setParamater(query);
			List<?> count = query.list();
			if (count.size() > 0)
				totalRecords = Integer.valueOf(count.get(count.size()-1).toString());
		} finally {
			session.close();
		}
		return new Page<A> (pageSetting,  result, totalRecords);
	}
	
	public interface QueryResultHandler<A> {
		/**
		 * Convert the necessary list of Query result the the desired object
		 */
		List<A> convert (List<Object[]> queryResult);
		
		/**
		 * Set the parameter of the query.
		 * @param query The {@link SQLQuery}
		 * @return the last index in the set parameter.
		 */
		int setParamater (SQLQuery query);
	}
}
