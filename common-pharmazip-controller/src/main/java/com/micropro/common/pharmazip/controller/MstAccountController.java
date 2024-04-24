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
import com.micropro.common.pharmazip.model.generated.MstAccountModel.MstAccountBody;
import com.micropro.common.pharmazip.model.generated.MstAccountModel.AccountBody;
import com.micropro.common.pharmazip.model.generated.MstAccountModel.AccountoprdetailBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstAccountEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Account APIs" })
@RequestMapping("/rest")
public class MstAccountController extends CrudService<MstAccountBody> {

	private @Autowired MstAccountEntity mstAccountEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_account", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Account entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Account entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstAccountBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstAccountBody body) {
		errormsg = new HashMap<>();

		AccountBody account = body.getAccount();
		if(account.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(account.getLastoperation().equals("INSERT") || account.getLastoperation().equals("UPDATE")
				|| account.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("account.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<AccountoprdetailBody> accountoprdetailList = account.getAccountoprdetail();
		if (accountoprdetailList == null) {
			throw new NullPointerException("account.accountoprdetail[] required");
		}
		for(AccountoprdetailBody accountoprdetail : accountoprdetailList) {
			if(accountoprdetail.getLastoperation() == null) {
				errormsg.put("accountoprdetail.lastoperation", "lastoperation is required");
			} else if(accountoprdetail.getLastoperation().equals("INSERT") || accountoprdetail.getLastoperation().equals("UPDATE")
					|| accountoprdetail.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("accountoprdetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstAccountBody body) {
		try {
			return mstAccountEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_account/{ACCOUNTID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Account entity with a specified 'ACCOUNTID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Account entity has been retrieved successfully")
	public String get(@PathVariable(name = "ACCOUNTID") long accountid, HttpServletRequest request) {
		return mstAccountEntity.get(request, accountid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}