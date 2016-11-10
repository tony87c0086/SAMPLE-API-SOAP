package com.uknowho.sample.soap.abstracts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.constant.ErrorCodeConstant;
import com.uknowho.sample.soap.constant.ErrorMessageConstant;
import com.uknowho.sample.soap.exception.ResponseException;
import com.uknowho.sample.soap.exception.ServiceException;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.xmlmodel.CatalogueModel;
import com.uknowho.sample.soap.xmlmodel.GetCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.MessageModel;
import com.uknowho.sample.soap.xmlmodel.ResponseModel;
import com.uknowho.sample.soap.xmlmodel.SaveCatalogueBody;

/**
 * This ServiceAbstract class is Defines Service common function 
 * 
 * Created date <22-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public abstract class SampleServiceAbstract {

	private static final Logger logger = LoggerFactory.getLogger(SampleServiceAbstract.class);
	
	protected SampleServiceAbstract() {
		//	logger.info("ServiceAbstract construction method load.");
	}
	
	protected MessageModel getMessageModel(
			final Integer code, 
			final String message) throws ServiceException {
		MessageModel messageModel = new MessageModel();
		try {
			messageModel = new MessageModel();
			messageModel.setMessage(message);
			if (code != null) {
				messageModel.setCode(code.toString());
			}
		} catch (Exception e) {
			logger.error(e.toString());
		} finally {
			if (messageModel == null) {
				messageModel = new MessageModel();
			}
		}
		return messageModel;
	}
	
	/**
	 *  Response exception will only be thrown if response mode exist and success flag is false.
	 *  Message parameter is used for other exceptions thrown catch case.
	 * @param response
	 * @param message
	 * @param serviceCode
	 * @throws ResponseException
	 */
	protected void validateResponseStatus(
			ResponseModel response, 
			final MessageModel message,
			final Integer serviceCode) throws ResponseException {
		// Other exception handler, throw response message anyway
		
		logger.debug("validateResponseStatus called.");
		
		if (message != null) {
			if (response == null) {
				response = new ResponseModel();
			}
			response.setServiceCode(serviceCode);
			response.getMessage().add(message);
			throw new ResponseException(response, ErrorMessageConstant.WEB_SERVICE_EXCEPTION);
		} else if ((response != null) && !(response.isSuccess())) {
			// Default response handler, only throw response message if failure
			response.setServiceCode(serviceCode);
			throw new ResponseException(response, ErrorMessageConstant.WEB_SERVICE_EXCEPTION);
		} else if ((response != null) && (response.isSuccess())) {
			// Set service code for default response if needed, response will only be return 
			// if request is save or update or delete
			response.setServiceCode(serviceCode);
		}
	}
	
	/** --------------------------------------------------------------------------------
	 * 									Catalogue 
	 * 	--------------------------------------------------------------------------------
	 */
	protected ResponseModel isGetCatalogueValid(final GetCatalogueBody requestBody) 
			throws ServiceException {
		ResponseModel response = null;
		boolean isValid = false;
		try {
			
			logger.debug("isGetContactValid called.");
			
			response = new ResponseModel();
			
			Integer catalogueID = requestBody.getCatalogueID();
			
			// Catalogue Type ID is mandatory
			if (catalogueID == null) {
				MessageModel message = getMessageModel(
						ErrorCodeConstant.CATALOGUE_SERVICE_ID_NOT_FOUND,
						ErrorMessageConstant.CATALOGUE_SERVICE_ID_NOT_FOUND);
				response.getMessage().add(message);
			} else {
				isValid = true;
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (response == null) {
				response = new ResponseModel();
			}
			response.setSuccess(isValid);
		}
		return response;
	}
	
	protected ResponseModel isSaveCatalogueValid(final SaveCatalogueBody requestBody) 
			throws ServiceException {
		ResponseModel response = null;
		boolean isValid = false;
		try {
			
			logger.debug("isSaveCatalogueValid called.");
			
			response = new ResponseModel();
			
			CatalogueModel model = requestBody.getCatalogue();
			List<CatalogueModel> modelList = requestBody.getCatalogueList();
			
			MessageModel message = null;
			if ((model == null) && !(DataFormat.isListValid(modelList))) {
				message = getMessageModel(ErrorCodeConstant.CATALOGUE_MODEL_NOT_FOUND,
						ErrorMessageConstant.CATALOGUE_MODEL_NOT_FOUND);
				response.getMessage().add(message);
			} else {
				isValid = true;
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (response == null) {
				response = new ResponseModel();
			}
			response.setSuccess(isValid);
		}
		return response;
	}
	
	protected ResponseModel isUpdateCatalogueValid(final SaveCatalogueBody requestBody) 
			throws ServiceException {
		ResponseModel response = null;
		boolean isValid = false;
		try {
			
			logger.debug("isUpdateCatalogueValid called.");
			
			response = new ResponseModel();
			
			CatalogueModel model = requestBody.getCatalogue();
			List<CatalogueModel> modelList = requestBody.getCatalogueList();
			
			MessageModel message = null;
			if ((model == null) && !(DataFormat.isListValid(modelList))) {
				message = getMessageModel(ErrorCodeConstant.CATALOGUE_MODEL_NOT_FOUND,
						ErrorMessageConstant.CATALOGUE_MODEL_NOT_FOUND);
				response.getMessage().add(message);
			} else {
				
				boolean catalogueValid = true;
				
				if (model != null) {
					Integer catalogueID = model.getCatalogueID();
					if (catalogueID == null) {
						catalogueValid = false;
					}
				} else if (DataFormat.isListValid(modelList)) {
					Integer catalogueID = null;
					for (CatalogueModel catalogue : modelList) {
						if (catalogue != null) {
							catalogueID = catalogue.getCatalogueID();
							if (catalogueID == null) {
								catalogueValid = false;
								break;
							}
						}
					}
				}  
				
				// Catalogue ID are mandatory while update 
				if (!catalogueValid) {
					message = getMessageModel(
							ErrorCodeConstant.CATALOGUE_SERVICE_ID_NOT_FOUND,
							ErrorMessageConstant.CATALOGUE_SERVICE_ID_NOT_FOUND);
					response.getMessage().add(message);
				} else {
					isValid = true;
				}
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (response == null) {
				response = new ResponseModel();
			}
			response.setSuccess(isValid);
		}
		return response;
	}
	
	
}
