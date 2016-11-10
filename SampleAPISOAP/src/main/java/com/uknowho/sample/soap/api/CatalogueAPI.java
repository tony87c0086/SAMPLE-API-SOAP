package com.uknowho.sample.soap.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.uknowho.sample.soap.config.APIPathAdvice;
import com.uknowho.sample.soap.exception.ResponseException;
import com.uknowho.sample.soap.service.CatalogueService;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.xmlmodel.CatalogueModel;
import com.uknowho.sample.soap.xmlmodel.DeleteCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.DeleteCatalogueRequest;
import com.uknowho.sample.soap.xmlmodel.DeleteCatalogueResponse;
import com.uknowho.sample.soap.xmlmodel.GetCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.GetCatalogueRequest;
import com.uknowho.sample.soap.xmlmodel.GetCatalogueResponse;
import com.uknowho.sample.soap.xmlmodel.ListCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.ListCatalogueRequest;
import com.uknowho.sample.soap.xmlmodel.ListCatalogueResponse;
import com.uknowho.sample.soap.xmlmodel.ResponseModel;
import com.uknowho.sample.soap.xmlmodel.SaveCatalogueBody;
import com.uknowho.sample.soap.xmlmodel.SaveCatalogueRequest;
import com.uknowho.sample.soap.xmlmodel.SaveCatalogueResponse;

/**
 * This CatalogueAPI class is define catalogue controller. 
 * 
 * Created date <12-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Endpoint
public class CatalogueAPI {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueAPI.class);
	
	@Autowired
	private CatalogueService catalogueService;
	
	@PayloadRoot(localPart = "getCatalogueRequest", namespace = APIPathAdvice.NAME_SPACE)
    @ResponsePayload
	public GetCatalogueResponse getCatalogueAPI(
			@RequestPayload final GetCatalogueRequest request) {	
		GetCatalogueResponse response = new GetCatalogueResponse();
		ResponseModel responseMessage = new ResponseModel();
		try {
			
			logger.debug("getCatalogueAPI called.");
			
			GetCatalogueBody requestBody = request.getRequestBody();
			CatalogueModel responseModel = catalogueService.getCatalogueService(requestBody);
			
			if (responseModel != null) {
				response.setCatalogue(responseModel);
			}
			
			responseMessage.setSuccess(true);
			
		} catch (ResponseException e) {
			logger.error("getCatalogueAPI throws APIException.");
			responseMessage = e.getResponseModel();
		} catch (Exception e) {
			logger.error("getCatalogueAPI throws APIException.");
			responseMessage.setSuccess(false);
			responseMessage.setMoreInfo(e.toString());
		} finally {
			response.setResponse(responseMessage);
		}
		return response; 
	}
	
	@PayloadRoot(localPart = "listCatalogueRequest", namespace = APIPathAdvice.NAME_SPACE)
    @ResponsePayload
	public ListCatalogueResponse listCatalogueTypeAPI(
			@RequestPayload final ListCatalogueRequest request) {		
		ListCatalogueResponse response = new ListCatalogueResponse();
		ResponseModel responseMessage = new ResponseModel();
		try {
			
			logger.debug("listCatalogueTypeAPI called.");
			
			ListCatalogueBody requestBody = request.getRequestBody();
			List<CatalogueModel> responseList = catalogueService.
					listCatalogueService(requestBody);
			
			if (DataFormat.isListValid(responseList)) {
				response.getCatalogueList().addAll(responseList);
			}
			
			responseMessage.setSuccess(true);
			
		} catch (ResponseException e) {
			logger.error("listCatalogueTypeAPI throws APIException.");
			responseMessage = e.getResponseModel();
		} catch (Exception e) {
			logger.error("listCatalogueTypeAPI throws APIException.");
			responseMessage.setSuccess(false);
			responseMessage.setMoreInfo(e.toString());
		} finally {
			response.setResponse(responseMessage);
		}
		return response; 
	}
	
	@PayloadRoot(localPart = "saveCatalogueRequest", namespace = APIPathAdvice.NAME_SPACE)
    @ResponsePayload
	public SaveCatalogueResponse saveCatalogueAPI(
			@RequestPayload final SaveCatalogueRequest request) {	
		SaveCatalogueResponse response = new SaveCatalogueResponse();
		ResponseModel responseMessage = new ResponseModel();
		try {
			
			logger.debug("saveCatalogueAPI called.");
			
			SaveCatalogueBody requestBody = request.getRequestBody();
			responseMessage = catalogueService.saveCatalogueService(requestBody);
			
		} catch (ResponseException e) {
			logger.error("saveCatalogueAPI throws APIException.");
			responseMessage = e.getResponseModel();
		} catch (Exception e) {
			logger.error("saveCatalogueAPI throws APIException.");
			responseMessage.setSuccess(false);
			responseMessage.setMoreInfo(e.toString());
		} finally {
			response.setResponse(responseMessage);
		}
		return response; 
	}
	
	@PayloadRoot(localPart = "updateCatalogueRequest", namespace = APIPathAdvice.NAME_SPACE)
    @ResponsePayload
	public SaveCatalogueResponse updateCatalogueAPI(
			@RequestPayload SaveCatalogueRequest request) {	
		SaveCatalogueResponse response = new SaveCatalogueResponse();
		ResponseModel responseMessage = new ResponseModel();
		try {
			
			logger.debug("updateCatalogueAPI called.");
			
			SaveCatalogueBody requestBody = request.getRequestBody();
			responseMessage = catalogueService.updateCatalogueService(requestBody);
			
		} catch (ResponseException e) {
			logger.error("updateCatalogueAPI throws APIException.");
			responseMessage = e.getResponseModel();
		} catch (Exception e) {
			logger.error("updateCatalogueAPI throws APIException.");
			responseMessage.setSuccess(false);
			responseMessage.setMoreInfo(e.toString());
		} finally {
			response.setResponse(responseMessage);
		}
		return response; 
	}
	
	@PayloadRoot(localPart = "deleteCatalogueRequest", namespace = APIPathAdvice.NAME_SPACE)
    @ResponsePayload
	public DeleteCatalogueResponse deleteCatalogueAPI(
			@RequestPayload final DeleteCatalogueRequest request) {	
		DeleteCatalogueResponse response = new DeleteCatalogueResponse();
		ResponseModel responseMessage = new ResponseModel();
		try {
			
			logger.debug("deleteCatalogueAPI called.");
			
			DeleteCatalogueBody requestBody = request.getRequestBody();
			responseMessage = catalogueService.deleteCatalogueService(requestBody);
			
		} catch (ResponseException e) {
			logger.error("deleteCatalogueAPI throws APIException.");
			responseMessage = e.getResponseModel();
		} catch (Exception e) {
			logger.error("deleteCatalogueAPI throws APIException.");
			responseMessage.setSuccess(false);
			responseMessage.setMoreInfo(e.toString());
		} finally {
			response.setResponse(responseMessage);
		}
		return response; 
	}
}
