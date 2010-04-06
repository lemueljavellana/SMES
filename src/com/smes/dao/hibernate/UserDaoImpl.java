package com.smes.dao.hibernate;

import java.util.Collection;


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

	@Override
	public void saveUser(User user) {
		save(user);
	}
}
