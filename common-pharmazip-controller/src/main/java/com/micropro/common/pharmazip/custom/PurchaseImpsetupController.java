package com.micropro.common.pharmazip.custom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.model.PurchaseImpsetupModel.PurchaseimportsetupBody;
import com.micropro.custom.services.ExceptionHandlingService.ValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "PurchaseImport APIs" })
@RequestMapping("/rest")
public class PurchaseImpsetupController {

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	@Autowired
	PurchaseImpsetupService purchaseImpsetupService;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/purchase_impsetup_by_id/{ID}", produces = "application/json")
	@ApiOperation(value = "Get Purchaseimportsetup by ID", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if setup has been retrieved successfully")
	public String getPurchaseImpsetupById(@PathVariable(name = "ID") long id, HttpServletRequest request) {
		return	purchaseImpsetupService.getPurchaseImpsetupById(id, request);
	}
	
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/purchase_impsetup", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on PurchaseimportsetupBody", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Faatagmst entity has been created successfully")
	public String purchaseImpsetup(@RequestBody @Validated PurchaseimportsetupBody body, HttpServletRequest request) 
			throws JsonProcessingException {
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Saved successfully");
		errormsg = new HashMap<>();
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			errormsg.put("rightId", "'rightId' required in request header");
		} else if(body.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(body.getLastoperation().equals("INSERT") || body.getLastoperation().equals("UPDATE")
				|| body.getLastoperation().equals("DELETE")) {
			// CRUD operation
			long resId = purchaseImpsetupService.purchaseImpsetup(body, request);
			response.addProperty("id", resId);
		} else {
			errormsg.put("account.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}
		
		if (!errormsg.isEmpty()) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response.toString();
		}

	}
	
}
