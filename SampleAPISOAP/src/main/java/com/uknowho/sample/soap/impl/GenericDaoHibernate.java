package com.uknowho.sample.soap.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.uknowho.sample.soap.dao.GenericDao;

/**
 * This GenericDaoHibernate class implements GenericDao interfaces for hibernate connections. 
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class GenericDaoHibernate<T,PK extends Serializable> implements GenericDao<T,PK>{

	private Class<T> persistentClass;
	
	protected static final Logger logger = LoggerFactory.
			getLogger(GenericDaoHibernate.class);

	@Resource
	private SessionFactory sessionFactory;
	
 
	public GenericDaoHibernate() {
		super();
	}
	
	/**
	 * Constructor to take entity class to persist
	 * This constructor to be used when subclassing
	 * 
	 * @param persistentClass the class type to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
    /**
     * Constructor to take entity class to persist and sessionFactory for easy 
     * creation of DAO.
     *
     * @param persistentClass the the class type to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(
    		final Class<T> persistentClass, 
    		SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        //defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }
    
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
   }
    
    public SessionFactory getSessionFactory() {
    	return this.sessionFactory;
    }
    
    public Session getSession() throws HibernateException {
    	Session hbmSession = getSessionFactory().getCurrentSession();
    	
    	if (hbmSession == null) {
    		hbmSession = getSessionFactory().openSession();
    	}
    	return hbmSession;
    }
	
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<T> list() {
		Session hbmSession = getSession();
		return hbmSession.createCriteria(persistentClass).list();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> listDistinct() {
    	Collection<T> result = new LinkedHashSet<T>(list());
    	return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK ID) {
    	T entity = getEntity(ID);
    	if (entity == null) {
    		logger.warn("Object Not Found. '" + this.persistentClass + 
    				"' object with id '" + ID + "'..");
    		throw new ObjectRetrievalFailureException(this.persistentClass, ID);
    	}
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK ID) {
    	T entity = getEntity(ID);
		return (entity != null);
	}
    
	public List<T> search(String searchTerm) {
    	// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		Session hbmSession = getSession();
		return (T) hbmSession.save(object);
	}
	
	@Override
	public T update(T object) {
		Session hbmSession = getSession();
		hbmSession.update(object);
		return (T) object;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T object) {
		Session hbmSession = getSession();
		return (T) hbmSession.merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean delete(T object) {
		Session hbmSession = getSession();
		hbmSession.delete(object);
		return (object != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean delete(PK ID) {
		T entity = getEntity(ID);
		getSession().delete(entity);
		return (entity != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked"})
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		Session hbmSession = getSession();
		Query namedQuery = hbmSession.getNamedQuery(queryName);
		for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }
		return namedQuery.list();
	}

	/**
	 * {@inheritDoc}
	 */
	public void reindex() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub
		
	}

	private T getEntity(PK ID) {
		T entity;
		Session hbmSession = getSession();
    	IdentifierLoadAccess<T> byId = hbmSession.byId(persistentClass);
    	entity = (T) byId.load(ID);
		return entity;
	}

}

