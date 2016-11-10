package com.uknowho.sample.soap.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;


/**
 * This GenericDao class provides interfaces for base hibernate entity.  
 * 
 * Created date <21-Feb-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 * @param <T> a type variable 
 * @param <PK> the primary key of the type variable T
 * 
 */

public interface GenericDao <T, PK extends Serializable>{
	 /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
	List<T> list();
	
    /**
     * Gets all records without duplicates.
     * <p>Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods</p>
     * @return List of populated objects
     */
	List<T> listDistinct();
	
    /**
    * Generic method to get an object based on class and identifier. An
    * ObjectRetrievalFailureException Runtime Exception is thrown if
    * nothing is found.
    *
    * @param ID the identifier (primary key) of the object to get
    * @return a populated object
    * @see org.springframework.orm.ObjectRetrievalFailureException
    */
	T get(PK ID);
   
	 /**
     * Gets all records that match a search term. "*" will get them all.
     * @param searchTerm the term to search for
     * @return the matching records
     * @throws ServiceException
     */
	List<T> search(String searchTerm) throws ServiceException;
	    
    /**
     * Checks for existence of an object of type T using the id arg.
     * @param ID the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK ID);
    
    /**
     * Generic method to save an object - handles save or insert.
     * @param object the object to save
     * @return the persisted object with auto generated id
     */
    T save(T object);
    
    /**
     * Generic method to update an object - handles update.
     * @param object the object to save
     * @return the persisted object
     */
    T update(T object);
    
    /**
     * Generic method to merge an object - handles merge.
     * @param object the object to save
     * @return the persisted object
     */
    T merge(T object);
    
    /**
     * Generic method to delete (soft delete only) an object
     * @param object the object to delete
     */
    boolean delete(T object);
    
    /**
     * Generic method to delete (soft delete only) an object
     * @param ID the identifier (primary key) of the object to delete
     */
    boolean delete(PK ID);
    
    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
    
    /**
     * Generic method to regenerate full text index of the persistent class T
     */
    void reindex();
    
    /**
     * Generic method to regenerate full text index of all indexed classes
     * @param async true to perform the reindexing asynchronously
     */
    void reindexAll(boolean async);
}

