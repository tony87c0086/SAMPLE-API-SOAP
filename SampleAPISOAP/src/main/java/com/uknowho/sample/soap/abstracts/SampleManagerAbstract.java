package com.uknowho.sample.soap.abstracts;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.constant.ErrorCodeConstant;
import com.uknowho.sample.soap.constant.ErrorMessageConstant;
import com.uknowho.sample.soap.entity.Catalogue;
import com.uknowho.sample.soap.exception.ServiceException;
import com.uknowho.sample.soap.utility.DateAdapter;
import com.uknowho.sample.soap.utility.IDGenerator;
import com.uknowho.sample.soap.xmlmodel.CatalogueModel;

/**
 * This ManagerAbstract class is Defines manager common function 
 * 
 * Created date <22-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public abstract class SampleManagerAbstract {

	private static final Logger logger = LoggerFactory.getLogger(SampleManagerAbstract.class);
	
	protected IDGenerator IDGenerator;
	
	protected SampleManagerAbstract() {
		//	logger.info("ManagerAbstract construction method load.");
		IDGenerator = new IDGenerator();
	}
	
	/** --------------------------------------------------------------------------------
	 * 									Catalogue 
	 * 	--------------------------------------------------------------------------------
	 */
	
	// Copy contact catalogue from entity to model
	protected CatalogueModel popularCatalogueToModel(final Catalogue entity) 
			throws IllegalArgumentException, ServiceException {
		CatalogueModel target = null;
		try {
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			target = new CatalogueModel();
			
			target.setCatalogueID(entity.getCatalogueID());
			target.setCatalogueName(entity.getCatalogueName());
			target.setParentID(entity.getParentID());
			target.setTypeID(entity.getTypeID());
			target.setTypeName(entity.getTypeName());
			target.setGroupID(entity.getGroupID());
			target.setGroupName(entity.getGroupName());
			target.setSortOrder(entity.getSortOrder());
			target.setCreatedDate(DateAdapter.printDateTime(entity.getCreatedDate()));
			target.setActive(entity.getActive());
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_TO_MODEL_EXCEPTION);
		} 
		return target;
	}
	
	// Copy catalogue information from model to entity
	protected Catalogue loadCatalogueFromModel(
			final Catalogue entity,
			final CatalogueModel model) throws IllegalArgumentException, ServiceException {
		try {
			// Data integrity Check begin
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			// Data integrity Check end
			
			entity.setCatalogueID(model.getCatalogueID());
			entity.setParentID(model.getParentID());
			
			entity.setCatalogueName(model.getCatalogueName());
			entity.setGroupID(model.getGroupID());
			entity.setGroupName(model.getGroupName());
			entity.setSortOrder(model.getSortOrder());
			entity.setTypeID(model.getTypeID());
			entity.setTypeName(model.getTypeName());
			
			Date createDate = DateAdapter.parseDateTime(model.getCreatedDate());
			if (createDate == null) {
				createDate = DateAdapter.getDateTime();
			}
			entity.setCreatedDate(createDate);
			
			entity.setActive(model.isActive());
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_FROM_MODEL_EXCEPTION);
		} 
		return entity;
	}
	
	// Create new catalogue entity based on model
	protected Catalogue buildCatalogue(final CatalogueModel model) 
			throws IllegalArgumentException, ServiceException {
		Catalogue target = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			
			target = new Catalogue();
			
			if (model.getCatalogueID() != null) {
				target.setCatalogueID(model.getCatalogueID());
			} 
			
			// Copy contact details from model
			loadCatalogueFromModel(target, model);
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					CREATE_ENTITY_EXCEPTION);
		} 
		return target;
	}
	
}
