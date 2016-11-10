package com.uknowho.sample.soap.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.uknowho.sample.soap.config.Configuration;
import com.uknowho.sample.soap.constant.ErrorCodeConstant;
import com.uknowho.sample.soap.constant.ErrorMessageConstant;
import com.uknowho.sample.soap.dao.CatalogueDao;
import com.uknowho.sample.soap.entity.Catalogue;
import com.uknowho.sample.soap.exception.ServiceException;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.xmlmodel.PaginationModel;
import com.uknowho.sample.soap.xmlmodel.SortModel;

/**
 * This CatalogueDaoHibernate class implements the CatalogueDao interface.
 * 
 * Created date <08-Sep-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 * @param <Catalogue> a Catalogue Entity object 
 * @param <Integer> the primary key of the Catalogue
 *
 */

@Repository("catalogueDao")
public class CatalogueDaoHibernate 
	extends GenericDaoHibernate<Catalogue, Integer> implements CatalogueDao {

	private static final Logger logger = LoggerFactory.getLogger(Catalogue.class);
	
	public CatalogueDaoHibernate() {
		super(Catalogue.class);
	}	
	
	@Override
	public List<Catalogue> list() {
		return listWithCriteria(null, null, null);
	}

	@Override
	public Catalogue get(Integer ID) {
		Catalogue object = null;
		List<Catalogue> objectList = listWithCriteria(Restrictions.
				eq("catalogueID", ID), null, null);
		if ((objectList != null) && (objectList.size()>0)) {
			object = objectList.get(0);
		}
		return object;
	}
	
	@Override
	public Catalogue save(Catalogue object) throws ServiceException {
		try {
			
			getSession().saveOrUpdate(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getCatalogueID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_SAVE_FAILURE, 
					"Error while saving Catalogue details");
		} 
		return object;
	}
	
	@Override
	public Catalogue update(Catalogue object) throws ServiceException {
		try {
			
			getSession().update(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getCatalogueID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_UPDATE_FAILURE, 
					"Error while updating Catalogue details");
		} 
		return object;
	}

	@Override
	public Catalogue merge(Catalogue object) {
		try {
			
			getSession().merge(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getCatalogueID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_MERGE_FAILURE, 
					"Error while merging Catalogue details");
		} 
		return object;
	}

	@SuppressWarnings("unchecked")
	private List<Catalogue> listWithCriteria(Criterion restrictions,
			List<SortModel> sortList, 
			PaginationModel pagination) {
		List<Catalogue> objectList = new ArrayList<Catalogue>();
		try {
			Criteria criteria =  getSession().createCriteria(Catalogue.class);
			
			// Sort order
			if (DataFormat.isListValid(sortList)) {
				for (SortModel sort : sortList) {
					if (sort != null) {
						if (sort.isDescend()) {
							criteria.addOrder(Order.desc(sort.getField()));
						} else {
							criteria.addOrder(Order.asc(sort.getField()));
						}
					}
				}
			} else {
				criteria.addOrder(Order.asc("groupID")).addOrder(Order.asc("sortOrder"));
			}
			
			// Limit result
			if (pagination != null) {
				criteria.setFirstResult(pagination.getFirstResult());
				criteria.setMaxResults(pagination.getMaxResult());
			} else {
				criteria.setFirstResult(Configuration.MIN_DEFAULT_RETURN_RECORD);
				criteria.setMaxResults(Configuration.MAX_DEFAULT_RETURN_RECORD);
			}
			
			if (restrictions != null) {
				criteria.add(restrictions);
			}
			objectList = criteria.list();
			
			if ((objectList == null) || (objectList.isEmpty())) {
				logger.warn(ErrorMessageConstant.RECORD_NOT_FOUND
							+ "Catalogue.");
				objectList = new ArrayList<Catalogue>();
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new ServiceException(ErrorCodeConstant.HIBERNATE_EXCEPTION,
					"DB Error : " + e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION,
					"Unknown Error : " + e.getMessage());
		}
		return objectList;
	}
	
	@Override
	public List<Catalogue> list(
			Integer parentID,
			Integer typeID, 
			Integer groupID, 
			Boolean active) throws ServiceException {
		return list(parentID, typeID, groupID, active, null, null);
	}

	@Override
	public List<Catalogue> list(
			Integer parentID,
			Integer typeID, 
			Integer groupID, 
			Boolean active, 
			List<SortModel> sortList, 
			PaginationModel pagination) throws ServiceException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (parentID != null) {
			resMap.put("parentID", parentID);
		}
		if (typeID != null) {
			resMap.put("typeID", typeID);
		}
		if (groupID != null) {
			resMap.put("groupID", groupID);
		}
		if (active != null) {
			resMap.put("active", active);
		}
		return listWithCriteria(Restrictions.allEq(resMap), sortList, pagination);
	}

}
