package com.micropro.common.pharmazip.transaction;

import java.sql.Connection;
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
import com.micropro.common.pharmazip.model.generated.PaymentreceiptMstModel.PaymentreceiptBody;
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
@Api(description = ".", tags = { "Transaction APIs" })
@RequestMapping("/rest")
public class TransactionController extends ExceptionHandlingService {
	
	@Autowired
	private ConnectionUtility utility;
	
	@Autowired
	TransactionService service;
	
	Map<String, String> errormsg; // <Field, Error_msg>
	
	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/invoicePosting", consumes = "application/json")
	@ApiOperation(value = "Process invoice posting", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction has successful")
	public String invoicePosting(@RequestBody List<PaymentreceiptBody> body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		
		String status = service.invoicePosting(body, request);
		
		if (status.equals("Success")) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Saved successfully");
		} else {
			throw new CustomException(status);
		}
		
		return response.toString();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/cashReceipt", consumes = "application/json")
	@ApiOperation(value = "Process cash receipt", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction has successful")
	public String cashReceipt(@RequestBody List<PaymentreceiptBody> body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		
		String status = service.cashReceipt(body, request);
		
		if (status.equals("Success")) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Saved successfully");
		} else {
			throw new CustomException(status);
		}
		
		return response.toString();
	}
	
	/* *****************************************************************************
	 * Author  : Chetan Channe 
	 * Date    : 2022-01-17 
	 * Input   : Data related to Transaction Master & detail 
	 * output  : Json response depending upon conditions 
	 * Purpose : To perform crud operation on Transaction Master & detail 
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCommonTransactionWithoutStock", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postCommonTransactionWithoutStock entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a TrnMaster entity has been created successfully")
	public String postCommonTransactionWithoutStock(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request) {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			try {
				response = service.postCommonTransactionWithoutStock(body, request);
			} catch (JsonProcessingException e) {
				throw new CustomException("Invalid Json body");
			}
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Failed")){
			throw new CustomException("Failed to update record");
		} else {
			return response;
		}
	}

}
