package com.micropro.common.pharmazip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;

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
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactionmasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactiondetailBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.TrnMasterEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Trn Master APIs" })
@RequestMapping("/rest")
public class TrnMasterController extends CrudService<TrnMasterBody> {

	private @Autowired TrnMasterEntity trnMasterEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/trn_master", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Transactionmaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request) {
		hRequest = request;
		String response;
		if(request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// CRUD operation
			response = crud(body);
		}

		// Response Body
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	@Override
	public boolean preCrudValidate(TrnMasterBody body) {
		errormsg = new HashMap<>();

		TransactionmasterBody transactionmaster = body.getTransactionmaster();
		if(transactionmaster.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(transactionmaster.getLastoperation().equals("INSERT") || transactionmaster.getLastoperation().equals("UPDATE")
				|| transactionmaster.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("transactionmaster.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<TransactiondetailBody> transactiondetailList = transactionmaster.getTransactiondetail();
		if (transactiondetailList == null) {
			throw new NullPointerException("transactionmaster.transactiondetail[] required");
		}
		for(TransactiondetailBody transactiondetail : transactiondetailList) {
			if(transactiondetail.getLastoperation() == null) {
				errormsg.put("transactiondetail.lastoperation", "lastoperation is required");
			} else if(transactiondetail.getLastoperation().equals("INSERT") || transactiondetail.getLastoperation().equals("UPDATE")
					|| transactiondetail.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("transactiondetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(TrnMasterBody body) {
		try {
			return trnMasterEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/trn_master/{TRANSACTIONMASTERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Transactionmaster entity with a specified 'TRANSACTIONMASTERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been retrieved successfully")
	public String get(@PathVariable(name = "TRANSACTIONMASTERID") long transactionmasterid, HttpServletRequest request) {
		return trnMasterEntity.get(request, transactionmasterid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}