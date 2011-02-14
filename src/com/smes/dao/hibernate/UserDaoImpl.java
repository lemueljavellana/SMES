package com.smes.dao.hibernate;

import java.util.Collection;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.UserDao;
import com.smes.domain.hibernate.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	public User getUser(int userId) {
		return get(userId);
	}

	@Override
	public User getUser(String userName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return get(criteria);
	}

	@Override
	public Collection<User> getUsers() {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.addOrder(Order.asc("userName"));
		return getAll(criteria);
	}

	public boolean isValidUser (String userName, String password, String companyName) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_NAME, USER_ID, COMPANY_ID ")
			.append("FROM (SELECT U.USER_NAME, U.USER_ID, C.COMPANY_ID FROM SMES_USER AS U ")
				.append("INNER JOIN (SELECT COMPANY_ID, COMPANY_NAME FROM COMPANY) AS C ON U.COMPANY_ID = C.COMPANY_ID ")
			.append("WHERE U.USER_NAME LIKE ?")
			.append("AND U.PASSWORD LIKE SHA1(?)) AS T");
			//.append("AND C.COMPANY_NAME LIKE ?) AS T");
		Session session = null;
		try {
			session = getSession();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setParameter(0, userName);
			query.setParameter(1, password);
			//query.setParameter(2, companyName);
			return query.list().size() > 0;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public void saveUser(User user) {
		saveOrUpdate(user);
	}
}
