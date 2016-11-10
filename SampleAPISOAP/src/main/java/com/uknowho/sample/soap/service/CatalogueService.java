package com.uknowho.sample.soap.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.uknowho.sample.soap.abstracts.SampleServiceAbstract;
import com.uknowho.sample.soap.config.Configuration;
import com.uknowho.sample.soap.constant.ErrorCodeConstant;
import com.uknowho.sample.soap.constant.ErrorMessageConstant;
import com.uknowho.sample.soap.exception.ResponseException;
import com.uknowho.sample.soap.exception.ServiceException;
import com.uknowho.sample.soap.manager.CatalogueManager;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.xmlmodel.CatalogueModel;
import com.uknowho.sample.soap.xmlmodel.DeleteCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.GetCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.ListCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.MessageModel;
import com.uknowho.sample.soap.xmlmodel.PaginationModel;
import com.uknowho.sample.soap.xmlmodel.ResponseModel;
import com.uknowho.sample.soap.xmlmodel.SaveCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.SortModel;

/**
 * This CatalogueService class is Defines for catalogue related service.
 * 
 * 
 * Created date <12-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service("catalogueService")
public class CatalogueService extends SampleServiceAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogueService.class);

	private final Integer serviceCode = Configuration.CATALOGUE_SERVICE_CODE;
	
	@Autowired
	private CatalogueManager catalogueManager;
	
	public CatalogueService() {
		logger.info("CatalogueService default constrction method load.");
	}
	
	public CatalogueModel getCatalogueService(final GetCatalogueBody requestBody)
			throws ResponseException {
		CatalogueModel model = null;
		ResponseModel response = null;
		MessageModel message = null;
		try {
			
			logger.debug("getCatalogueService called.");
			
			response = isGetCatalogueValid(requestBody);
			if (response.isSuccess()) {
				Integer catalogueID = requestBody.getCatalogueID();
				if (catalogueID != null) {
					model = catalogueManager.fetchCatalogueModel(catalogueID);
				} else {
					model = catalogueManager.getCatalogueModel();
				}
			} 
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			message = getMessageModel(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			validateResponseStatus(response, message, serviceCode);
		}
		return model;
	}
	
	
	public List<CatalogueModel> listCatalogueService(final ListCatalogueBody requestBody)
			throws ResponseException {
		List<CatalogueModel> modelList = null;
		ResponseModel response = null;
		MessageModel message = null;
		try {
			
			logger.debug("listCatalogueService called.");
			
			if (requestBody != null) {
				
				Integer parentID = requestBody.getParentID();
				Integer typeID = requestBody.getTypeID();
				Integer groupID = requestBody.getGroupID();
				Boolean active = requestBody.isActive();
				List<SortModel> sortList = requestBody.getSort();
				PaginationModel pagination = requestBody.getPagination();
				
				if ((DataFormat.isListValid(sortList)) || (pagination != null)) {
					modelList = catalogueManager.listCatalogueModel(
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
					modelList = catalogueManager.listCatalogueModel(
							parentID,
							typeID,
							groupID, 
							active);
				} else {
					modelList = catalogueManager.listCatalogueModel();
				}
			} 
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			message = getMessageModel(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			validateResponseStatus(response, message, serviceCode);
		}
		return modelList;
	}
	
	public ResponseModel saveCatalogueService(final SaveCatalogueBody requestBody)
			throws ResponseException {
		ResponseModel response = null;
		MessageModel message = null;
		try {
			
			logger.debug("saveCatalogueService called.");
			
			response = isSaveCatalogueValid(requestBody);
			if (response.isSuccess()) {
				
				CatalogueModel model = requestBody.getCatalogue();
				List<CatalogueModel> modelList = requestBody.getCatalogueList();
				boolean master = requestBody.isMaster();
				
				Integer catalogueID = null;
				List<Integer> catalogueIDList = null;
				if (model != null) {
					catalogueID = catalogueManager.saveCatalogueModel(model, master);
				} else if (DataFormat.isListValid(modelList)) {
					catalogueIDList = catalogueManager.saveCatalogueModelList(modelList, master);
				}
				
				if ((catalogueID == null) && !(DataFormat.isListValid(catalogueIDList))) {
					response.setSuccess(false);
				} else {
					if (catalogueID != null) {
						response.setRecordID(catalogueID.toString());
					} else if (DataFormat.isListValid(catalogueIDList)) {
						for (Integer ID : catalogueIDList) {
							if (ID != null) {
								response.getRecordIDList().add(ID.toString());
							}
						}
					}
				}
			} 
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			message = getMessageModel(e.getErrorCode(), e.getMessage());
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.RECORD_SAVE_FAILURE, 
					ErrorMessageConstant.RECORD_SAVE_FAILURE);
		} catch (Exception e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			validateResponseStatus(response, message, serviceCode);
		}
		return response;
	}
	
	public ResponseModel updateCatalogueService(final SaveCatalogueBody requestBody)
			throws ResponseException {
		ResponseModel response = null;
		MessageModel message = null;
		try {
			
			logger.debug("updateCatalogueService called.");
			
			response = isUpdateCatalogueValid(requestBody);
			if (response.isSuccess()) {
				
				CatalogueModel model = requestBody.getCatalogue();
				List<CatalogueModel> modelList = requestBody.getCatalogueList();
				boolean master = requestBody.isMaster();
				
				Integer catalogueID = null;
				List<Integer> catalogueIDList = null;
				if (model != null) {
					catalogueID = catalogueManager.updateCatalogueModel(model, master);
				} else if (DataFormat.isListValid(modelList)) {
					catalogueIDList = catalogueManager.
							updateCatalogueModelList(modelList, master);
				}
				
				if ((catalogueID == null) && !(DataFormat.isListValid(catalogueIDList))) {
					response.setSuccess(false);
				} else {
					if (catalogueID != null) {
						response.setRecordID(catalogueID.toString());
					} else if (DataFormat.isListValid(catalogueIDList)) {
						for (Integer ID : catalogueIDList) {
							if (ID != null) {
								response.getRecordIDList().add(ID.toString());
							}
						}
					}
				}
			} 
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			message = getMessageModel(e.getErrorCode(), e.getMessage());
		} catch (HibernateOptimisticLockingFailureException e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.RECORD_UPDATE_FAILURE, 
					ErrorMessageConstant.RECORD_UPDATE_FAILURE);
		} catch (Exception e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			validateResponseStatus(response, message, serviceCode);
		}
		return response;
	}
	
	public ResponseModel deleteCatalogueService(final DeleteCatalogueBody requestBody)
			throws ResponseException {
		ResponseModel response = null;
		MessageModel message = null;
		try {
			
			logger.debug("deleteCatalogueService called.");
			
			response = new ResponseModel();
			
			Integer catalogueID = requestBody.getCatalogueID();
			List<Integer> catalogueIDList = requestBody.getCatalogueIDList();
			
			boolean isSuccess = false;
			if (catalogueID != null) {
				isSuccess = catalogueManager.deleteCatalogueModel(catalogueID);
				response.setRecordID(catalogueID.toString());
			} else if (DataFormat.isListValid(catalogueIDList)) {
				isSuccess = catalogueManager.deleteCatalogueModelList(catalogueIDList);
				for (Integer ID : catalogueIDList) {
					if (ID != null) {
						response.getRecordIDList().add(ID.toString());
					}
				}
			}
			
			response.setSuccess(isSuccess);
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			message = getMessageModel(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			message = getMessageModel(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			validateResponseStatus(response, message, serviceCode);
		}
		return response;
	}
	
}
