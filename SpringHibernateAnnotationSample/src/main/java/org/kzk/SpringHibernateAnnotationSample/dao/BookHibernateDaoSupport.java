package org.kzk.SpringHibernateAnnotationSample.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BookHibernateDaoSupport extends HibernateDaoSupport {

	@Autowired
	public void autoWireSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
}
