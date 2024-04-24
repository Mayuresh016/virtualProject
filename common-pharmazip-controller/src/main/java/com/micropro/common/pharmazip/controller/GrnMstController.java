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
import com.micropro.common.pharmazip.model.generated.GrnMstModel.GrnMstBody;
import com.micropro.common.pharmazip.model.generated.GrnMstModel.GrnmasterBody;
import com.micropro.common.pharmazip.model.generated.GrnMstModel.GrndetailBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.GrnMstEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Grn Mst APIs" })
@RequestMapping("/rest")
public class GrnMstController extends CrudService<GrnMstBody> {

	private @Autowired GrnMstEntity grnMstEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/grn_mst", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Grnmaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Grnmaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated GrnMstBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(GrnMstBody body) {
		errormsg = new HashMap<>();

		GrnmasterBody grnmaster = body.getGrnmaster();
		if(grnmaster.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(grnmaster.getLastoperation().equals("INSERT") || grnmaster.getLastoperation().equals("UPDATE")
				|| grnmaster.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("grnmaster.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<GrndetailBody> grndetailList = grnmaster.getGrndetail();
		if (grndetailList == null) {
			throw new NullPointerException("grnmaster.grndetail[] required");
		}
		for(GrndetailBody grndetail : grndetailList) {
			if(grndetail.getLastoperation() == null) {
				errormsg.put("grndetail.lastoperation", "lastoperation is required");
			} else if(grndetail.getLastoperation().equals("INSERT") || grndetail.getLastoperation().equals("UPDATE")
					|| grndetail.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("grndetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(GrnMstBody body) {
		try {
			return grnMstEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/grn_mst/{GRNMASTERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Grnmaster entity with a specified 'GRNMASTERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Grnmaster entity has been retrieved successfully")
	public String get(@PathVariable(name = "GRNMASTERID") long grnmasterid, HttpServletRequest request) {
		return grnMstEntity.get(request, grnmasterid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}