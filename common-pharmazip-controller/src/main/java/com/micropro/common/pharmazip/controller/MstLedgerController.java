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
import com.micropro.common.pharmazip.model.generated.MstLedgerModel.MstLedgerBody;
import com.micropro.common.pharmazip.model.generated.MstLedgerModel.LedgermasterBody;
import com.micropro.common.pharmazip.model.generated.MstLedgerModel.LedgerdetailBody;
import com.micropro.common.pharmazip.model.generated.MstLedgerModel.PaymentreceiptBody;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.entity.MstLedgerEntity;
import com.micropro.custom.services.CrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Mst Ledger APIs" })
@RequestMapping("/rest")
public class MstLedgerController extends CrudService<MstLedgerBody> {

	private @Autowired MstLedgerEntity mstLedgerEntity;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	private @Autowired ConnectionUtility utility;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/mst_ledger", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Ledgermaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Ledgermaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated MstLedgerBody body, HttpServletRequest request) {
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
	public boolean preCrudValidate(MstLedgerBody body) {
		errormsg = new HashMap<>();

		LedgermasterBody ledgermaster = body.getLedgermaster();
		if(ledgermaster.getLastoperation() == null) {
			errormsg.put("lastoperation", "lastoperation is required");
		} else if(ledgermaster.getLastoperation().equals("INSERT") || ledgermaster.getLastoperation().equals("UPDATE")
				|| ledgermaster.getLastoperation().equals("DELETE")) {
		} else {
			errormsg.put("ledgermaster.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
		}

		List<LedgerdetailBody> ledgerdetailList = ledgermaster.getLedgerdetail();
		if (ledgerdetailList == null) {
			throw new NullPointerException("ledgermaster.ledgerdetail[] required");
		}
		for(LedgerdetailBody ledgerdetail : ledgerdetailList) {
			if(ledgerdetail.getLastoperation() == null) {
				errormsg.put("ledgerdetail.lastoperation", "lastoperation is required");
			} else if(ledgerdetail.getLastoperation().equals("INSERT") || ledgerdetail.getLastoperation().equals("UPDATE")
					|| ledgerdetail.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("ledgerdetail.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		List<PaymentreceiptBody> paymentreceiptList = ledgermaster.getPaymentreceipt();
		if (paymentreceiptList == null) {
			throw new NullPointerException("ledgermaster.paymentreceipt[] required");
		}
		for(PaymentreceiptBody paymentreceipt : paymentreceiptList) {
			if(paymentreceipt.getLastoperation() == null) {
				errormsg.put("paymentreceipt.lastoperation", "lastoperation is required");
			} else if(paymentreceipt.getLastoperation().equals("INSERT") || paymentreceipt.getLastoperation().equals("UPDATE")
					|| paymentreceipt.getLastoperation().equals("DELETE")) {
			} else {
				errormsg.put("paymentreceipt.lastoperation", "lastoperation should be 'INSERT', 'UPDATE' or 'DELETE'");
			}

		}

		return errormsg.isEmpty();
	}

	@Override
	public String crudImpl(MstLedgerBody body) {
		try {
			return mstLedgerEntity.crud(hRequest, body);
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/mst_ledger/{LEDGERMASTERID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Ledgermaster entity with a specified 'LEDGERMASTERID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Ledgermaster entity has been retrieved successfully")
	public String get(@PathVariable(name = "LEDGERMASTERID") long ledgermasterid, HttpServletRequest request) {
		return mstLedgerEntity.get(request, ledgermasterid);
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

}