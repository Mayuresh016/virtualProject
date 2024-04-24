package com.micropro.common.pharmazip.purchase;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.StockShortageModel.StockshortaccessBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.custom.services.ExceptionHandlingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Purchase controller contains custom code.
 * <p>
 * This file is safe to edit.
 * 
 * @author Micropro
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Purchase APIs" })
@RequestMapping("/rest")
public class PurchaseController extends ExceptionHandlingService {

	@Autowired
	private ConnectionUtility utility;
	
	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;
	
	@Autowired
	PurchaseService service;
	
	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}
	
	/*********************************************************************************
	 * Author : Kushal Kadu 
	 * Date : 16/04/2022 
	 * Input : Header = Data for transaction master detail : details for transaction detail 
	 * Output : Response depending upon conditions 
	 * Purpose : post purchase invoice 
	 * Comment : postPurchaseInvoice
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postPurchaseInvoice", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on TrnMasterBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postpurchaseinvoice(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			List<TrnMasterBody> trnMasterList = new ArrayList<>();
			trnMasterList.add(body);
			response = service.postpurchaseinvoice(trnMasterList, false, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}
	
	/*********************************************************************************
	 * Author : Kushal Kadu 
	 * Date : 16/04/2022 
	 * Input : Header = Data for transaction master detail : details for transaction detail 
	 * Output : Response depending upon conditions 
	 * Purpose : post purchase import 
	 * Comment : postPurchaseImport
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postPurchaseImport", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on TrnMasterBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postPurchaseImport(@RequestBody @Validated List<TrnMasterBody> body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = service.postpurchaseinvoice(body, true, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockshortageAccess", consumes = "application/json")
	@ApiOperation(value = "Post stock shortage or access data", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction has successful")
	public String postStockShortage(@RequestBody List<StockshortaccessBody> body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		
		String status = service.postStockShortage(body, request);
		
		if (status.equals("Success")) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Saved successfully");
		} else {
			throw new CustomException(status);
		}
		
		return response.toString();
	}
}
