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
import com.micropro.common.pharmazip.model.generated.MstProductModel.MstProductBody;
import com.micropro.common.pharmazip.model.generated.MstProductModel.ProductBody;
import com.micropro.common.pharmazip.model.generated.MstProductModel.ProductoprdtlBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstProductEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Product APIs" })
@RequestMapping("/rest")
public class MstProductController extends CrudService<MstProductBody> {

	private @Autowired MstProductEntity mstProductEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_product", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Product entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Product entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstProductBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstProductBody body) {
		errormsg = new HashMap<>();

		ProductBody product = body.getProduct();
		if(product.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(product.getLastoperation().equals("INSERT") || product.getLastoperation().equals("UPDATE")
				|| product.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("product.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<ProductoprdtlBody> productoprdtlList = product.getProductoprdtl();
		if (productoprdtlList == null) {
			throw new NullPointerException("product.productoprdtl[] required");
		}
		for(ProductoprdtlBody productoprdtl : productoprdtlList) {
			if(productoprdtl.getLastoperation() == null) {
				errormsg.put("productoprdtl.lastoperation", "lastoperation is required");
			} else if(productoprdtl.getLastoperation().equals("INSERT") || productoprdtl.getLastoperation().equals("UPDATE")
					|| productoprdtl.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("productoprdtl.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstProductBody body) {
		try {
			return mstProductEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_product/{PRODUCTID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Product entity with a specified 'PRODUCTID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Product entity has been retrieved successfully")
	public String get(@PathVariable(name = "PRODUCTID") long productid, HttpServletRequest request) {
		return mstProductEntity.get(request, productid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}