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
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StockTransferMstBody;
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StocktransfermasterBody;
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StocktransferdetailBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.StockTransferMstEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Stock Transfer Mst APIs" })
@RequestMapping("/rest")
public class StockTransferMstController extends CrudService<StockTransferMstBody> {

	private @Autowired StockTransferMstEntity stockTransferMstEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/stock_transfer_mst", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Stocktransfermaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Stocktransfermaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated StockTransferMstBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(StockTransferMstBody body) {
		errormsg = new HashMap<>();

		StocktransfermasterBody stocktransfermaster = body.getStocktransfermaster();
		if(stocktransfermaster.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(stocktransfermaster.getLastoperation().equals("INSERT") || stocktransfermaster.getLastoperation().equals("UPDATE")
				|| stocktransfermaster.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("stocktransfermaster.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<StocktransferdetailBody> stocktransferdetailList = stocktransfermaster.getStocktransferdetail();
		if (stocktransferdetailList == null) {
			throw new NullPointerException("stocktransfermaster.stocktransferdetail[] required");
		}
		for(StocktransferdetailBody stocktransferdetail : stocktransferdetailList) {
			if(stocktransferdetail.getLastoperation() == null) {
				errormsg.put("stocktransferdetail.lastoperation", "lastoperation is required");
			} else if(stocktransferdetail.getLastoperation().equals("INSERT") || stocktransferdetail.getLastoperation().equals("UPDATE")
					|| stocktransferdetail.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("stocktransferdetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(StockTransferMstBody body) {
		try {
			return stockTransferMstEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/stock_transfer_mst/{STOCKTRANSFERMASTERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Stocktransfermaster entity with a specified 'STOCKTRANSFERMASTERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Stocktransfermaster entity has been retrieved successfully")
	public String get(@PathVariable(name = "STOCKTRANSFERMASTERID") long stocktransfermasterid, HttpServletRequest request) {
		return stockTransferMstEntity.get(request, stocktransfermasterid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}