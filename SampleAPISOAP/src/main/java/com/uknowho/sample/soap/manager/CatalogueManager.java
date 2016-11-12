package com.uknowho.sample.soap.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uknowho.sample.soap.abstracts.ManagerAbstract;
import com.uknowho.sample.soap.constant.ErrorCodeConstant;
import com.uknowho.sample.soap.constant.ErrorMessageConstant;
import com.uknowho.sample.soap.dao.CatalogueDao;
import com.uknowho.sample.soap.entity.Catalogue;
import com.uknowho.sample.soap.exception.ServiceException;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.xmlmodel.CatalogueModel;
import com.uknowho.sample.soap.xmlmodel.PaginationModel;
import com.uknowho.sample.soap.xmlmodel.SortModel;

/**
 * This CatalogueManager class is Defines for catalogue related methods.
 * 
 * 
 * Created date <08-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service("catalogueManager")
public class CatalogueManager extends ManagerAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogueManager.class);
	
	@Autowired
	private CatalogueDao catalogueDao;
	
	public CatalogueManager() {
		logger.info("CatalogueManager default constrction method load.");
	}
	
	// Create new catalogue model
	public CatalogueModel getCatalogueModel() throws ServiceException {
		CatalogueModel target = null;
		try {
			
			target = new CatalogueModel();
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return target;
	}
	
	// Fetch exist catalogue 
	public CatalogueModel fetchCatalogueModel(final Integer catalogueID)
			throws ServiceException {
		CatalogueModel target = null;
		try {
			
			Catalogue entity = catalogueDao.get(catalogueID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.CATALOGUE_NOT_FOUND, 
						ErrorMessageConstant.CATALOGUE_NOT_FOUND);
			} 
			
			target = popularCatalogueToModel(entity);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return target;
	}
	
	// List all catalogue catalogue model
	public List<CatalogueModel> listCatalogueModel() throws ServiceException {
		return listCatalogueModel(null, null, null, null);
	}
	
	/**
	 * @param parentID
	 * @param typeID
	 * @param groupID
	 * @param active
	 * @return List<CatalogueModel>
	 * @throws ServiceException
	 */
	public List<CatalogueModel> listCatalogueModel(
			final Integer parentID,
			final Integer typeID,
			final Integer groupID,
			final Boolean active) throws ServiceException {
		return listCatalogueModel(parentID, typeID, groupID, active, null, null);
	}
		
	/**
	 * @param parentID
	 * @param typeID
	 * @param groupID
	 * @param active
	 * @param sortList
	 * @param pagination
	 * @return List<CatalogueModel>
	 * @throws ServiceException
	 */
	public List<CatalogueModel> listCatalogueModel(
			final Integer parentID,
			final Integer typeID,
			final Integer groupID,
			final Boolean active,
			final List<SortModel> sortList,
			final PaginationModel pagination) throws ServiceException {
		List<CatalogueModel> targetList = null;
		try {
			
			targetList = new ArrayList<CatalogueModel>();
			
			List<Catalogue> catalogueList = null;
			if ((DataFormat.isListValid(sortList)) || (pagination != null)) {
				catalogueList = catalogueDao.list(
						parentID, 
						typeID, 
						groupID, 
						active, 
						sortList,
						pagination);
			} else if ((parentID != null)
					|| (typeID != null)
					|| (groupID != null) 
					|| (active != null)) {
				catalogueList = catalogueDao.list(parentID, typeID, groupID, active);
			} else {
				catalogueList = catalogueDao.list();
			}
			
			if (DataFormat.isListValid(catalogueList)) {
				for (Catalogue catalogueEntity : catalogueList) {
					if (catalogueEntity != null) {
						targetList.add(popularCatalogueToModel(catalogueEntity));
					}
				}
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (targetList == null) {
				targetList = new ArrayList<CatalogueModel>();
			}
		}
		return targetList;
	}

	public List<Integer> saveCatalogueModelList(
			final List<CatalogueModel> modelList, 
			final Boolean master) throws ServiceException {
		List<Integer> catalogueIDList = null;
		if (DataFormat.isListValid(modelList)) {
			catalogueIDList = new ArrayList<Integer>();
			Integer catalogueID = null;
			for (CatalogueModel model : modelList) {
				if (model != null) {
					catalogueID = saveCatalogueModel(model, master);
					catalogueIDList.add(catalogueID);
				}
			}
		}
		return catalogueIDList;
	}
	
	// Save new catalogue to DB
	public Integer saveCatalogueModel(final CatalogueModel model, final Boolean master)
			throws ServiceException {
		Integer catalogueID = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.CATALOGUE_MODEL_NOT_FOUND, 
						ErrorMessageConstant.CATALOGUE_MODEL_NOT_FOUND);
			}
			
			Catalogue entity = buildCatalogue(model);

			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
						ErrorMessageConstant.MODEL_MAPPING_EXCEPTION);
			}
			
			entity = catalogueDao.save(entity);
			
			if (entity != null) {
				catalogueID = entity.getCatalogueID();
			}

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return catalogueID;
	}
	
	public List<Integer> updateCatalogueModelList(
			final List<CatalogueModel> modelList, 
			final Boolean master) throws ServiceException {
		List<Integer> catalogueIDList = null;
		if (DataFormat.isListValid(modelList)) {
			catalogueIDList = new ArrayList<Integer>();
			Integer catalogueID = null;
			for (CatalogueModel model : modelList) {
				if (model != null) {
					catalogueID = updateCatalogueModel(model, master);
					catalogueIDList.add(catalogueID);
				}
			}
		}
		return catalogueIDList;
	}
	
	// Update existing catalogue in DB
	public Integer updateCatalogueModel(final CatalogueModel model, final Boolean master)
			throws ServiceException {
		Integer catalogueID = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.CATALOGUE_MODEL_NOT_FOUND, 
						ErrorMessageConstant.CATALOGUE_MODEL_NOT_FOUND);
			}
			
			catalogueID = model.getCatalogueID();
			Catalogue entity = catalogueDao.get(catalogueID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.CATALOGUE_NOT_FOUND, 
						ErrorMessageConstant.CATALOGUE_NOT_FOUND);
			}
			
			entity = loadCatalogueFromModel(entity, model);
			entity = catalogueDao.update(entity);

			if (entity != null) {
				catalogueID = entity.getCatalogueID();
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return catalogueID;
	}
	
	public boolean deleteCatalogueModelList(final List<Integer> catalogueIDList)
			throws ServiceException {
		boolean isSuccess = false;
		if (DataFormat.isListValid(catalogueIDList)) {
			for (Integer catalogueID : catalogueIDList) {
				if (catalogueID != null) {
					deleteCatalogueModel(catalogueID);
				}
			}
		}
		isSuccess = true;
		return isSuccess;
	}
	
	// Delete existing catalogue in DB
	public boolean deleteCatalogueModel(final Integer catalogueID)
			throws ServiceException {
		boolean isSuccess = false;
		try {
			// Data integrity Check 
			if (catalogueID == null) {
				throw new ServiceException(ErrorCodeConstant.CATALOGUE_NOT_FOUND, 
						ErrorMessageConstant.CATALOGUE_NOT_FOUND);
			}
			
			isSuccess = catalogueDao.delete(catalogueID);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return isSuccess;
	}
	
}
