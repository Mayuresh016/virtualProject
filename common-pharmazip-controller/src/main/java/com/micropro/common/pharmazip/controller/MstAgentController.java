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
import com.micropro.common.pharmazip.model.generated.MstAgentModel.MstAgentBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.AgentBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.DoctorproductlinkBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.DoctorcustomerlinkBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstAgentEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Agent APIs" })
@RequestMapping("/rest")
public class MstAgentController extends CrudService<MstAgentBody> {

	private @Autowired MstAgentEntity mstAgentEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_agent", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Agent entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Agent entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstAgentBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstAgentBody body) {
		errormsg = new HashMap<>();

		AgentBody agent = body.getAgent();
		if(agent.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(agent.getLastoperation().equals("INSERT") || agent.getLastoperation().equals("UPDATE")
				|| agent.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("agent.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<DoctorproductlinkBody> doctorproductlinkList = agent.getDoctorproductlink();
		if (doctorproductlinkList == null) {
			throw new NullPointerException("agent.doctorproductlink[] required");
		}
		for(DoctorproductlinkBody doctorproductlink : doctorproductlinkList) {
			if(doctorproductlink.getLastoperation() == null) {
				errormsg.put("doctorproductlink.lastoperation", "lastoperation is required");
			} else if(doctorproductlink.getLastoperation().equals("INSERT") || doctorproductlink.getLastoperation().equals("UPDATE")
					|| doctorproductlink.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("doctorproductlink.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		List<DoctorcustomerlinkBody> doctorcustomerlinkList = agent.getDoctorcustomerlink();
		if (doctorcustomerlinkList == null) {
			throw new NullPointerException("agent.doctorcustomerlink[] required");
		}
		for(DoctorcustomerlinkBody doctorcustomerlink : doctorcustomerlinkList) {
			if(doctorcustomerlink.getLastoperation() == null) {
				errormsg.put("doctorcustomerlink.lastoperation", "lastoperation is required");
			} else if(doctorcustomerlink.getLastoperation().equals("INSERT") || doctorcustomerlink.getLastoperation().equals("UPDATE")
					|| doctorcustomerlink.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("doctorcustomerlink.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstAgentBody body) {
		try {
			return mstAgentEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_agent/{AGENTID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Agent entity with a specified 'AGENTID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Agent entity has been retrieved successfully")
	public String get(@PathVariable(name = "AGENTID") long agentid, HttpServletRequest request) {
		return mstAgentEntity.get(request, agentid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}