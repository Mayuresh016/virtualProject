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
import com.micropro.common.pharmazip.model.generated.MstStoreModel.MstStoreBody;
import com.micropro.common.pharmazip.model.generated.MstStoreModel.StoreBody;
import com.micropro.common.pharmazip.model.generated.MstStoreModel.LocationBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstStoreEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Store APIs" })
@RequestMapping("/rest")
public class MstStoreController extends CrudService<MstStoreBody> {

	private @Autowired MstStoreEntity mstStoreEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_store", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Store entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Store entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstStoreBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstStoreBody body) {
		errormsg = new HashMap<>();

		StoreBody store = body.getStore();
		if(store.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(store.getLastoperation().equals("INSERT") || store.getLastoperation().equals("UPDATE")
				|| store.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("store.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<LocationBody> locationList = store.getLocation();
		if (locationList == null) {
			throw new NullPointerException("store.location[] required");
		}
		for(LocationBody location : locationList) {
			if(location.getLastoperation() == null) {
				errormsg.put("location.lastoperation", "lastoperation is required");
			} else if(location.getLastoperation().equals("INSERT") || location.getLastoperation().equals("UPDATE")
					|| location.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("location.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstStoreBody body) {
		try {
			return mstStoreEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_store/{STOREID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Store entity with a specified 'STOREID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Store entity has been retrieved successfully")
	public String get(@PathVariable(name = "STOREID") long storeid, HttpServletRequest request) {
		return mstStoreEntity.get(request, storeid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}