package com.micropro.common.pharmazip.custom;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.OrderMstModel.OrderMstBody;
import com.micropro.common.pharmazip.model.OrderMstModel.OrderdetailBody;
import com.micropro.common.pharmazip.model.OrderMstModel.OrdermasterBody;
import com.micropro.custom.services.ExceptionHandlingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Order Mst APIs" })
@RequestMapping("/rest")
public class OrderMstController extends ExceptionHandlingService {

	private @Autowired OrderMstService orderMstService;

	Map<String, String> errormsg; // <Field, Error_msg>

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/order_mst", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Ordermaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Ordermaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated OrderMstBody body, HttpServletRequest request) {
		String response;
		errormsg = new HashMap<>();
		if(request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			OrdermasterBody ordermaster = body.getOrdermaster();
			if(ordermaster.getLastoperation() == null) {
				errormsg.put("lastoperation", "lastoperation is required");
			} else if(ordermaster.getLastoperation().equals("INSERT") || ordermaster.getLastoperation().equals("UPDATE")
					|| ordermaster.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("ordermaster.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

			List<OrderdetailBody> orderdetailList = ordermaster.getOrderdetail();
			if (orderdetailList == null) {
				throw new NullPointerException("ordermaster.orderdetail[] required");
			}
			for(OrderdetailBody orderdetail : orderdetailList) {
				if(orderdetail.getLastoperation() == null) {
					errormsg.put("orderdetail.lastoperation", "lastoperation is required");
				} else if(orderdetail.getLastoperation().equals("INSERT") || orderdetail.getLastoperation().equals("UPDATE")
						|| orderdetail.getLastoperation().equals("DELETE")) {
				} else {
					errormsg.put("orderdetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
				}

			}
			
			if (errormsg.isEmpty()) {
				// CRUD operation
				try {
					response = orderMstService.crud(request, body);
				} catch (JsonProcessingException e) {
					throw new CustomException("Invalid Json body");
				}
			} else {
				response = "Error";
			}
		}

		// Response Body
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			JsonObject responseBody = new JsonObject();
			responseBody.addProperty("code", 200);
			responseBody.addProperty("status", "Success");
			responseBody.addProperty("message", "Saved successfully");
			return responseBody.toString();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/order_mst_list", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Ordermaster list", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Ordermaster list has been saved successfully")
	public String saveList(@RequestBody @Validated List<OrderMstBody> body, HttpServletRequest request) {
		String response;
		if(request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// CRUD operation
			try {
				response = orderMstService.saveList(request, body);
			} catch (JsonProcessingException e) {
				throw new CustomException("Invalid Json body");
			}
		}

		// Response Body
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			JsonObject responseBody = new JsonObject();
			responseBody.addProperty("code", 200);
			responseBody.addProperty("status", "Success");
			responseBody.addProperty("message", "Saved successfully");
			return responseBody.toString();
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/order_mst/{ORDERMASTERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Ordermaster entity with a specified 'ORDERMASTERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Ordermaster entity has been retrieved successfully")
	public String get(@PathVariable(name = "ORDERMASTERID") long ordermasterid, HttpServletRequest request) {
		return orderMstService.get(request, ordermasterid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}