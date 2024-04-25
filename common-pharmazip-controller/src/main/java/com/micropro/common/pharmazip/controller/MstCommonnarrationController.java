package com.micropro.common.pharmazip.controller;

import java.util.HashMap;
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
import com.micropro.common.pharmazip.model.generated.MstCommonnarrationModel.CommonnarrationBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstCommonnarrationEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Commonnarration APIs" })
@RequestMapping("/rest")
public class MstCommonnarrationController extends CrudService<CommonnarrationBody> {

	private @Autowired MstCommonnarrationEntity mstCommonnarrationEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_commonnarration", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Commonnarration entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Commonnarration entity has been created successfully")
	public String crudReq(@RequestBody @Validated CommonnarrationBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(CommonnarrationBody body) {
		errormsg = new HashMap<>();

		if(body.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(body.getLastoperation().equals("INSERT") || body.getLastoperation().equals("UPDATE")
				|| body.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(CommonnarrationBody body) {
		try {
			return mstCommonnarrationEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_commonnarration/{COMMONNARRATIONID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Commonnarration entity with a specified 'COMMONNARRATIONID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Commonnarration entity has been retrieved successfully")
	public String get(@PathVariable(name = "COMMONNARRATIONID") long commonnarrationid, HttpServletRequest request) {
		return mstCommonnarrationEntity.get(request, commonnarrationid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}