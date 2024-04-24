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
import com.micropro.common.pharmazip.model.generated.MstPickerModel.MstPickerBody;
import com.micropro.common.pharmazip.model.generated.MstPickerModel.PickerBody;
import com.micropro.common.pharmazip.model.generated.MstPickerModel.PickerlocationlinkBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstPickerEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Picker APIs" })
@RequestMapping("/rest")
public class MstPickerController extends CrudService<MstPickerBody> {

	private @Autowired MstPickerEntity mstPickerEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_picker", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Picker entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Picker entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstPickerBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstPickerBody body) {
		errormsg = new HashMap<>();

		PickerBody picker = body.getPicker();
		if(picker.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(picker.getLastoperation().equals("INSERT") || picker.getLastoperation().equals("UPDATE")
				|| picker.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("picker.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<PickerlocationlinkBody> pickerlocationlinkList = picker.getPickerlocationlink();
		if (pickerlocationlinkList == null) {
			throw new NullPointerException("picker.pickerlocationlink[] required");
		}
		for(PickerlocationlinkBody pickerlocationlink : pickerlocationlinkList) {
			if(pickerlocationlink.getLastoperation() == null) {
				errormsg.put("pickerlocationlink.lastoperation", "lastoperation is required");
			} else if(pickerlocationlink.getLastoperation().equals("INSERT") || pickerlocationlink.getLastoperation().equals("UPDATE")
					|| pickerlocationlink.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("pickerlocationlink.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstPickerBody body) {
		try {
			return mstPickerEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_picker/{PICKERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Picker entity with a specified 'PICKERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Picker entity has been retrieved successfully")
	public String get(@PathVariable(name = "PICKERID") long pickerid, HttpServletRequest request) {
		return mstPickerEntity.get(request, pickerid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}