package com.smes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smes.domain.hibernate.User;
import com.smes.view.frm.Credential;

public class ControllerUtil {
	public static void verifyUser (HttpServletRequest request, HibernateDaoSupport dao){
		Credential c = (Credential)request.getSession().getAttribute(Credential.class.getName());
		HibernateTemplate ht = dao.getHibernateTemplate();
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", c.getUserName()));
		List<User> users = ht.findByCriteria(criteria);
		if (users == null || users.isEmpty())
			throw new RuntimeException ("invalid user");
	}
}
