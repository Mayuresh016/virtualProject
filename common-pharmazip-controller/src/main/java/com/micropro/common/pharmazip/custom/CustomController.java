package com.micropro.common.pharmazip.custom;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.config.RedisManager;
import com.micropro.common.pharmazip.entity.DruglicenseMasterEntity;
import com.micropro.common.pharmazip.entity.GrnMstEntity;
import com.micropro.common.pharmazip.entity.MstAgentEntity;
import com.micropro.common.pharmazip.entity.MstBatchEntity;
import com.micropro.common.pharmazip.entity.MstCustomerAgencyDiscountEntity;
import com.micropro.common.pharmazip.entity.MstGodownuserlinkEntity;
import com.micropro.common.pharmazip.entity.MstInvoiceseriesEntity;
import com.micropro.common.pharmazip.entity.MstPickerEntity;
import com.micropro.common.pharmazip.entity.MstPickeruserlinkEntity;
import com.micropro.common.pharmazip.entity.MstProductSchemeEntity;
import com.micropro.common.pharmazip.entity.MstShortSupplyEntity;
import com.micropro.common.pharmazip.entity.MstStoreEntity;
import com.micropro.common.pharmazip.entity.MstTempEntity;
import com.micropro.common.pharmazip.entity.TrnMasterEntity;
import com.micropro.common.pharmazip.entity.TrnMasterOrderEntity;
import com.micropro.common.pharmazip.model.CheckDuplicateValidateModel.CustomCheckDuplicateValidateBody;
import com.micropro.common.pharmazip.model.CustomModel.CustomBody;
import com.micropro.common.pharmazip.model.LedgerMstModel.LedgerMstBody;
import com.micropro.common.pharmazip.model.OrderConversionModel.OrderConversionBody;
import com.micropro.common.pharmazip.model.QueryApiModel.QueryApiBody;
import com.micropro.common.pharmazip.model.generated.DruglicenseMasterModel.DruglicensemasterBody;
import com.micropro.common.pharmazip.model.generated.GrnMstModel.GrnMstBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.MstAgentBody;
import com.micropro.common.pharmazip.model.generated.MstCreditnoteslabModel.CreditnoteslabBody;
import com.micropro.common.pharmazip.model.generated.MstCustomerAgencyDiscountModel.CustomeragencydiscountBody;
import com.micropro.common.pharmazip.model.generated.MstGodownuserlinkModel.GodownuserlinkBody;
import com.micropro.common.pharmazip.model.generated.MstInvoiceseriesModel.InvoiceseriesBody;
import com.micropro.common.pharmazip.model.generated.MstPickerModel.MstPickerBody;
import com.micropro.common.pharmazip.model.generated.MstPickeruserlinkModel.PickeruserlinkBody;
import com.micropro.common.pharmazip.model.generated.MstProductSchemeModel.ProductschemeBody;
import com.micropro.common.pharmazip.model.generated.MstRouteAreaLinkModel.RoutearealinkBody;
import com.micropro.common.pharmazip.model.generated.MstShortSupplyModel.ShortsupplyBody;
import com.micropro.common.pharmazip.model.generated.MstTempModel.TempBody;
import com.micropro.common.pharmazip.model.generated.ProductLocationLinkModel.ProductlocationlinkBody;
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StockTransferMstBody;
import com.micropro.common.pharmazip.model.generated.TimeExpiredModel.TimeexpiredBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterOrderModel.TrnMasterOrderBody;
import com.micropro.common.pharmazip.transaction.TransactionService;
import com.micropro.custom.services.ExceptionHandlingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Generated code This controller contains custom code.
 * <p>
 * This file is safe to edit.
 * 
 * @author Micropro
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "Custom APIs" })
@RequestMapping("/rest")
public class CustomController extends ExceptionHandlingService {

	@Autowired
	private ConnectionUtility utility;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	@Autowired
	DruglicenseMasterEntity drugLicMasterEntity;

	@Autowired
	MstBatchEntity mstBatchEntity;

	@Autowired
	MstAgentEntity mstAgentEntity;

	@Autowired
	TrnMasterEntity trnMasterEntity;

	@Autowired
	MstCustomerAgencyDiscountEntity mstCustomerAgencyDiscountEntity;

	@Autowired
	MstStoreEntity mstStoreEntity;

	@Autowired
	MstProductSchemeEntity mstProductSchemeEntity;

	@Autowired
	MstPickerEntity mstPickerEntity;

	@Autowired
	GrnMstEntity grnMasterEntity;

	@Autowired
	MstShortSupplyEntity mstShortSupplyEntity;

	@Autowired
	private RedisManager redisManager;

	@Autowired
	MstTempEntity mstTempEntity;

	@Autowired
	CustomService customService;

	@Autowired
	TrnMasterOrderEntity trnMasterOrderEntity;

	@Autowired
	MstInvoiceseriesEntity mstInvoiceseriesEntity;
	
	@Autowired
	MstPickeruserlinkEntity mstPickeruserlinkEntity;
	
	@Autowired
	MstGodownuserlinkEntity mstGodownuserlinkEntity;
	
	@Autowired
	TransactionService transactionService;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/connection_pool_state", produces = "application/json")
	@ApiOperation(value = "Retrieve connection pool state", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if pool state has been retrieved successfully")
	public String getPoolState() {
		JsonObject response = new JsonObject();

		response.addProperty("activeConnections", utility.getActiveConnectionCount());
		response.addProperty("idleConnections", utility.getIdleConnectionCount());
		response.addProperty("totalConnection", utility.getTotalConnectionCount());

		return response.toString();
	}

	@Override
	public Connection getConnection() {
		return utility.getConnection();
	}

	/******************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 10/11/2021 
	 * Input   : Data for the table Druglicensemaster 
	 * output  : Internal id for Drug Licenses master and status message 
	 * Purpose : To save Drug Liscences 
	 * comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postdrug_licensemaster", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Druglicensemaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Druglicensemaster entity has been created successfully")
	public String crudReq(@RequestBody @Validated List<DruglicensemasterBody> body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// Call to the generated API for drug Licenses master
			for (DruglicensemasterBody drugLicMasterBody : body)
				try {
					response = drugLicMasterEntity.crud(request, drugLicMasterBody);
				} catch (JsonProcessingException e) {
					throw new CustomException("Invalid Json body");
				}
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}
	
	
	/*********************************************************************************
	 * Author : Aayush Dahake Date : 15/11/2021 Input : batch id , MRP , Expiry date
	 * sale rate and shortname Output : product id and status message Purpose :
	 * Change MRP and expiry date for the batch Comment : batchMrpExpiryChange in
	 * batch operations menu
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postBatchMrpExpiryChange", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on BatchMaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a BatchMaster entity has been created successfully")
	public String batchMrpExpiryChange(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		String productcode = "";
		int update = 0;
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// CRUD operation
			productcode = body.get(0).getProductid().toString();
			update = customService.batchMrpExpiryChange(body, request);
			// Return product code in response
			if (update >= 0) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("id", productcode);
				jsonObject.addProperty("message", "Record Saved Successfully ");
				response = jsonObject.toString();
			}
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 15/11/2021 Input : Batch details Output : Batch
	 * id and status message Purpose : Bifurcate batch or transfer the stock into
	 * another batch Comment : postBatchBifurcation in batch operations menu
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postbatchBifurcation", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postbatchBifurcation entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a BatchMaster entity has been created successfully")
	public String batchBifurcation(@RequestBody @Validated CustomBody body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		// String batchid = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			long batchid = customService.batchBifurcation(body, request);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", batchid);
			jsonObject.addProperty("message", "Record Saved Successfully ");
			response = jsonObject.toString();
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 15/11/2021 Input : Header = Data for doctor
	 * Detail = doctorproductlink= Data for product , doctorcustomerlink = Data for
	 * customer Output : Doctor id and status message Purpose : Allocate the doctor
	 * to the customer and to the product Comment : postDoctorCustProdSetup in
	 * masters menu
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postDoctorCustProdSetup", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Agent entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Agent entity has been created successfully")
	public String postDoctorCustProdSetup(@RequestBody @Validated MstAgentBody body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			int result = customService.postDoctorCustProdSetup(body, request);
			if (result >= 0) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("id", body.getAgent().getAgentid()); // doctorid
				jsonObject.addProperty("message", "Record Saved Successfully ");
				response = jsonObject.toString();
			}
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	// Move to transaction controller
	/*********************************************************************************
	 * Author : Aayush Dahake Date : 07/01/2022 Input : Transaction master detail
	 * data Output : Response depending upon conditions Purpose : Credit note
	 * replacement Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCreditNoteReplacement", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Trnmaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Trnmaster entity has been created successfully")
	public String postCreditNoteReplacement(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws Exception {
		return customService.postCreditNoteReplacement(body, request);
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 27/10/2021 Input : Batchid,Block and productid
	 * Output : Productid and status message Purpose : Locking and unlocking the
	 * batch in batch master Comment : batchLockUnlock
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postBatchLockUnlock", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Batch entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Batch entity has been created successfully")
	public String batchLockUnlock(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request)
			throws Exception {
		String response = "";
		JsonObject jsonObject = new JsonObject();
		// Get the product id
		Long productid = body.get(0).getProductid();
		// Update the block field as the JSON Request
		long update = customService.batchLockUnlock(body, request);

		// Response return product id
		if (update >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", productid);
			jsonObject.addProperty("message", "Record Saved Successfully");
			response = jsonObject.toString();
		}
		return response;

	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : Input : Output : Purpose : Comment : Not in use
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/trn_master_basket/{vouchertype}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Transactionmaster entity with a specified 'vouchertype' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been retrieved successfully")
	public String getbasketdetails(@PathVariable(name = "vouchertype") String voucherType, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "SELECT * FROM transactionmaster where vouchertype = '" + voucherType
				+ "'  and (vouchermode = 0 or vouchermode = 1 or  vouchermode = '')   and orgid ="
				+ adUserAccessToken.get("orgid") + " and  oprid =" + adUserAccessToken.get("oprid");
		String response = "";
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonArray datadtl = new JsonArray();
		JsonArray dataMst = new JsonArray();

		if (data.size() > 0) {
			for (JsonElement jsonElement : data.getAsJsonArray()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject = jsonElement.getAsJsonObject();
				query = "SELECT * FROM transactiondetail where transactionmasterid = "
						+ jsonElement.getAsJsonObject().get("transactionmasterid");
				datadtl = utility.executeQueryOnPool(query, request);
				jsonObject.add("transactiondetail", datadtl);
				dataMst.add(jsonObject);
			}
		}
		response = dataMst.toString();
		return response;
	}

	/*********************************************************************************
	 * Author : Chetan channe Date : 19/11/2021 Input : Data For Customer Agency
	 * Discount table Output : productid,locked and status message Purpose :
	 * Functionality for the save and delete previous entity. Comment :
	 * postCustomerAgencyDiscount
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCustomerAgencyDiscount", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postCustomerAgencyDiscount entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postCustomerAgencyDiscount entity has been created successfully")
	public String postCustomerAgencyDiscount(@RequestBody @Validated List<CustomeragencydiscountBody> body,
			HttpServletRequest request) throws Exception {
		
		String response = customService.postCustomerAgencyDiscount(body, request);

		// Response return customer id
		if (response.length() > 0) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", body.get(0).getAccountid().toString());
			jsonObject.addProperty("message", "Record Saved Successfully");
			response = jsonObject.toString();
		}
		
		return response;
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 12/11/2021 Input : agency name (Not Mandatory)
	 * Output : Json for tree structure Purpose : Tree structure for ZSM, RSM and
	 * ASM Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getzsm_rsm_asm_listing/{agencyname}", produces = "application/json")
	@ApiOperation(value = "Retrieve a custom entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getZsmRsmAsmListing(@PathVariable(name = "agencyname") String agencyName,
			HttpServletRequest request) {
		JsonArray jsonArray = customService.getZsmRsmAsmListing(agencyName, request);
		return jsonArray.toString();

	}

	/*********************************************************************************
	 * Author : Chetan channe Date : 10/11/2021 Input : netamount ,
	 * transactionmasterid Output : productid,locked and status message Purpose :
	 * Functionality for the Locking/Unlocking the Product. Comment :
	 * postProductLockUnlock in masters
	 ********************************************************************************/

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postProductLockUnlock", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Product entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Product entity has been created successfully")
	public String postProductLockUnlock(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request)
			throws Exception {
		String response = "";
		long update = 0;
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// Get the product id

			update = customService.postProductLockUnlock(body, request);
			// Return product code in response
			if (update >= 0) {
				// Response return product id
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully");
				response = jsonObject.toString();
			}
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 15/11/2021 Input : Multiple lock unlock and
	 * account id Output : Status Message Purpose : Locking and unlocking the
	 * customer Comment : postCustomerLockUnlock in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCustomerLockUnlock", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Account entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Account entity has been updated successfully")
	public String postCustomerLockUnlock(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request)
			throws Exception {
		// Updating locked in accountoprdetail
		long update = customService.postCustomerLockUnlock(body, request);
		JsonObject jsonObject = new JsonObject();
		if (update >= 0) {
			// Response
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully");
		}
		return jsonObject.toString();
	}

	/*********************************************************************************
	 * Author : Chetan channe Date : 17/11/2021 Input : transaction master id and
	 * net amount Output : Status Message Purpose : Functionality for save of second
	 * day cash entry form Comment : second day cash entry form in Sales Menu
	 ********************************************************************************/

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postSecondDayCashInvoiceEntry", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Account entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been updated successfully")
	public String postSecondDayCashInvoiceEntry(@RequestBody @Validated List<CustomBody> body,
			HttpServletRequest request) throws Exception {
		String response = "";
		long update = 0;
		update = customService.postSecondDayCashInvoiceEntry(body, request);
		// Return product code in response
		if (update >= 0) {
			// Response return product id
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully");
			response = jsonObject.toString();
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/*********************************************************************************
	 * Author : Aayush dahake Date : 17/11/2021 Input : transaction master id and
	 * pending amount Output : Status Message Purpose : Functionality for save of
	 * second day cash receipt entry form Comment : second day cash receipt entry
	 * form in Sales Menu.
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postSecondDayCashReceiptEntry", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postSecondDayCashReceiptEntry entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postSecondDayCashReceiptEntry entity has been updated successfully")
	public String postSecondDayCashReceiptEntry(@RequestBody @Validated List<CustomBody> body,
			HttpServletRequest request) throws Exception {
		// substract the given pending amount from pending in transaction master
		int update = 0;
		update = customService.postSecondDayCashReceiptEntry(body, request);
		// Response
		JsonObject jsonObject = new JsonObject();
		if (update >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully");
		}
		return jsonObject.toString();

	}

	/*********************************************************************************
	 * Author : Aayush dahake Date : 18/11/2021 Input : tax id from tax master
	 * Output : accounts with their names allocated to a tax id
	 * Purpose : Update  mode of tax structure for fetching accounts of a purticular tax 
	 * Comment : tax structure in tax master , To be shifted in Query API
	 ********************************************************************************/

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_tax_account/{taxid}", produces = "application/json")
	@ApiOperation(value = "Retrieve a custom entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getTaxAccountByid(@PathVariable(name = "taxid") long taxid, HttpServletRequest request) {
		// Select the data from tax master depending upon the id provided
		return customService.getTaxAccountByid(taxid, request);
	}

	// Move to transaction controller
	/*********************************************************************************
	 * Author : Aayush dahake Date : 20/11/2021 Input : Data for transaction master and detail 
	 * Output : Data from transaction detail in case of insert update and voucher no in case of final save 
	 * Purpose : Use in Stock transfer form 
	 * Comment: store transfer form
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockTransfer", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on TransactionmasterBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a TransactionmasterBody entity has been updated successfully")
	public String postStockTransfer(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws Exception {
		String jsonresult = customService.postStockTransfer(body, request);
		return jsonresult.toString();
	}

	/*
	 * *****************************************************************************
	 * Author : Chetan Channe Author : Aayush Dahake (Modifications according to
	 * String builder) Date : 24/11/2021 Input : batchsequence, batchid and
	 * productid output : Productid and status message Purpose : Functionality to
	 * update sequence/ serial no for batch Comment : postBatchSerialChange in Batch
	 * Operations menu
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postBatchSerialChange", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Batch entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Batch entity has been updated successfully")
	public String batchSerialChange(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request)
			throws Exception {
		String response = "";
		long update = 0;
		update = customService.batchSerialChange(body, request);
		// Return product code in response
		if (update >= 0) {
			// Response return product id
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", body.get(0).getProductid().toString());
			jsonObject.addProperty("message", "Record Saved Successfully");
			response = jsonObject.toString();
		}

		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}

	}

	/******************************************************************************
	 * Author : Chetan Channe Date : 29/11/2021 Input : balance qty and
	 * productschemeid output : productschemeid and status message Purpose :
	 * Functionality to update the scheme balance Comment : postUpdateSchemeBalance
	 * in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postUpdateSchemeBalance", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postUpdateSchemeBalance entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postUpdateSchemeBalance entity has been updated successfully")
	public String postUpdateSchemeBalance(@RequestBody @Validated List<ProductschemeBody> body,
			HttpServletRequest request) throws Exception {
		String response = "";
		JsonObject jsonObject = new JsonObject();
		// updating balance quantity as per product scheme id
		int update = customService.postUpdateSchemeBalance(body, request);
		if (update > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Updated Successfully");
		}
		// Response
		response = jsonObject.toString();
		return response;
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 01/12/2021 
	 * Input : picker id 
	 * Output : Data related to picker and locations 
	 * Purpose : common functionality for both list of locationpickerlink Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getlocation_picker_listing/{pickerid}", produces = "application/json")
	@ApiOperation(value = "Retrieve a locationpicker entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getPickerLocationListing(@PathVariable(name = "pickerid") Long pickerid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray jsonArray = new JsonArray();
		String whereClause = "";

		if (pickerid != 0) {
			whereClause = whereClause + " and  picker.pickerid =" + pickerid;
		} else {

			// Modified by : Aayush Dahake
			// Modified on : 06/12/2021
			// If picker id is 0 then show that data whose entry not present in picker
			// location link as per PrBhange on
			whereClause = whereClause
					+ " and (pickerlocationlink.pickerlocationlinkid is null or pickerlocationlink.pickerlocationlinkid =0)";
		}
		String query = "SELECT pickerlocationlink.pickerlocationlinkid internalid,\r\n"
				+ "       location.title locationname,\r\n" + "       picker.pickername pickername,\r\n"
				+ "       picker.pickerdescription pickerdescription,\r\n"
				+ "       location.locationid locationid,\r\n" + "       pickerlocationlink.pickerid pickerid\r\n"
				+ "FROM location location\r\n" + "     LEFT JOIN pickerlocationlink pickerlocationlink\r\n"
				+ "        ON     location.orgid = pickerlocationlink.orgid\r\n"
				+ "           AND location.oprid = pickerlocationlink.oprid\r\n"
				+ "           AND location.locationid = pickerlocationlink.locationid\r\n" + "           \r\n"
				+ "     LEFT JOIN picker picker\r\n" + "        ON     picker.orgid = pickerlocationlink.orgid\r\n"
				+ "           AND picker.oprid = pickerlocationlink.oprid\r\n"
				+ "           AND picker.pickerid = pickerlocationlink.pickerid\r\n" + "WHERE location.orgid ="
				+ adUserAccessToken.get("orgid") + " AND location.oprid =" + adUserAccessToken.get("oprid")
				+ " AND location.active = 1" + "" + whereClause;
		jsonArray = utility.executeQueryOnPool(query, request);

		return jsonArray.toString();

	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 23/12/2021 Input : ratecategoryname Output :
	 * Message and status depending upon conditions Purpose : Check duplicate in
	 * rate category form Comment : rate category in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getrate_category_check_duplicate/{ratecategoryname}", produces = "application/json")
	@ApiOperation(value = "Retrieve a ratecategory entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String checkduplicaterate(@PathVariable(name = "ratecategoryname") String ratecategoryname,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		// Checking if the category exist in rate category
		String query = "SELECT ratecategoryid internalid, ratecategoryname ratecategoryname\r\n"
				+ "FROM ratecategory\r\n" + "WHERE ratecategory.orgid = " + adUserAccessToken.get("orgid")
				+ " AND oprid = " + adUserAccessToken.get("oprid") + "  AND ratecategoryname ='" + ratecategoryname
				+ "'";
		data = utility.executeQueryOnPool(query, request);
		// Message as per discussion with Swapnil dhorey and Arshad Rangoonwala
		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			jsonObject.addProperty("message", "Duplicate entry found for " + ratecategoryname);

		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");

		}
		return jsonObject.toString();

	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 06/12/2021 Input : HEADER : Picker DETAIL :
	 * Picker location link Output : Internal id for picker Purpose : delete
	 * locations accociated to picker and re insert again Comment :
	 * postPickerLocationlink in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postPickerLocationlink", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postUpdateSchemeBalance entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a picker entity has been updated successfully")
	public String postPickerLocationlink(@RequestBody @Validated MstPickerBody body, HttpServletRequest request)
			throws Exception {
		JsonObject response = new JsonObject();
		
		try {
			customService.postPickerLocationlink(body, request);
		} catch (Exception ex) {
			throw new CustomException("Bad Request");
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Saved Successfully");
		return response.toString();
	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 21/12/2021 Input : taxdescription and taxvalue
	 * Output : Message and status depending upon conditions Purpose : Check
	 * Duplicate for tax structure Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/gettax_check_duplicate/{taxdescription}/{taxvalue}", produces = "application/json")
	@ApiOperation(value = "Retrieve a tax entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity tax has been retrieved successfully")
	public String checkduplicatetax(@PathVariable(name = "taxdescription") String taxdescription,
			@PathVariable("taxvalue") BigDecimal taxvalue, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();

		// Check the combination of tax description and tax value in tax master if found
		// give the message as duplicate entry
		// If exist check in tax master
		String query = "SELECT taxid, taxdescription\r\n" + "FROM tax\r\n" + "WHERE orgid ="
				+ adUserAccessToken.get("orgid") + " AND oprid = " + adUserAccessToken.get("oprid")
				+ " AND taxdescription ='" + taxdescription + " ' AND taxvalue = " + taxvalue;
		data = utility.executeQueryOnPool(query, request);
		// Message as per discussion with Praful Bhange
		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			jsonObject.addProperty("message", "Duplicate entry found for " + taxdescription);

		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");

		}
		return jsonObject.toString();

	}

	/******************************************************************************
	 * Author : Chetan Channe Date : 15/12/2021 Input : content name output :
	 * Message and status depending upon conditions Purpose : Check Duplicate in
	 * content master Comment :
	 ********************************************************************************/

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getcontentname_check_duplicate/{name}", produces = "application/json")
	@ApiOperation(value = "Retrieve a content entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a content entity has been retrieved successfully")
	public String checkduplicatecontentname(@PathVariable(name = "name") String name, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		// Checking if the name exist in content
		String query = "SELECT contentid internalid, name name\r\n" + "FROM content\r\n" + "WHERE content.orgid = "
				+ adUserAccessToken.get("orgid") + " AND name ='" + name + "'";
		data = utility.executeQueryOnPool(query, request);

		JsonObject jsonObject = new JsonObject();

		if (data.size() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			jsonObject.addProperty("message", "Duplicate entry found for " + name);

		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");

		}
		return jsonObject.toString();

	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 21/12/2021 Input : Data for Grn master table
	 * Output : Internalid , status message and consignment no Purpose : Operations
	 * on GRN Master Comment : Consignment master in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_grn_master", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Grnmaster entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Grnmaster entity has been created successfully")
	public String postgrnmaster(@RequestBody @Validated GrnMstBody body, HttpServletRequest request)
			throws JsonProcessingException {
		JsonObject response = new JsonObject();
		
		String grnno = customService.postgrnmaster(body, request);
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Saved successfully");

		// Adding GRN No to response as per Swapnil Dhorey
		response.addProperty("data", grnno);

		return response.toString();
	}

	/*****************************************************************************
	 * Author : Chetan Channe Date : 20/12/2021 Input : hsncode, taxid output :
	 * Message and status depending upon conditions Purpose : Check the combination
	 * of hsncode and taxid in Product master Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/gethsncode_check_deactivate/{hsncode}/{taxid}", produces = "application/json")
	@ApiOperation(value = "Retrieve a hsncode entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a hsncode entity has been retrieved successfully")
	public String checkdeactivatehsncode(@PathVariable("hsncode") String hsncode, @PathVariable("taxid") Long taxid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		/**
		 * Check the combination of hsncode and taxid in product master if found give
		 * the message as Hsn code already attach to the product cannot be deactivated
		 * If exist check in product master
		 **/
		String query = "SELECT productoprdtl.productid, hsncode\r\n" + "FROM productoprdtl productoprdtl\r\n"
				+ "     INNER JOIN product product\r\n"
				+ "        ON     productoprdtl.productid = product.productid\r\n"
				+ "AND productoprdtl.orgid = product.orgid" + " WHERE productoprdtl.orgid = "
				+ adUserAccessToken.get("orgid") + " AND productoprdtl.oprid =" + adUserAccessToken.get("oprid")
				+ " AND product.hsncode ='" + hsncode + "' AND product.taxid = " + taxid;
		data = utility.executeQueryOnPool(query, request);

		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			jsonObject.addProperty("message",
					"Hsn code " + hsncode + " is already attach to the product cannot be deactivated");
		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");

		}
		return jsonObject.toString();

	}

	/*********************************************************************************
	 * Author : Aayush Dahake Date : 27/12/2021 Input : agency name or short name
	 * Output : Message and status depending upon conditions Purpose : Check
	 * Duplicate in Agency master Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getagency_check_duplicate/{agencyname}/{shortname}", produces = "application/json")
	@ApiOperation(value = "Retrieve a agency entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a agency entity has been retrieved successfully")
	public String checkduplicateagency(@PathVariable("agencyname") String agencyname,
			@PathVariable("shortname") String shortname, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		String query = "";
		// Check if the agency name or shortname exist in agency master
		query = "select  count(*)count  from agency agency where agency.orgid =" + adUserAccessToken.get("orgid")
				+ " AND agency.oprid =" + adUserAccessToken.get("oprid") + " and  agency.title = ifnull(" + agencyname
				+ ",agency.title) and  agency.shortname = ifnull(" + shortname + ",agency.shortname) ";
		data = utility.executeQueryOnPool(query, request);
		JsonObject jsonObject = new JsonObject();

		// Return Agency name or the short name depending upon the conditions
		if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			if (!(agencyname.equals("null"))) {
				jsonObject.addProperty("message", agencyname.replace("'", "") + " already exist");
			} else if (!(shortname.equals("null"))) {
				jsonObject.addProperty("message", shortname.replace("'", "") + " already exist");
			}
		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
		}
		return jsonObject.toString();

	}

	/*
	 * *****************************************************************************
	 * Author : Chetan Channe Date : 27/12/2021 Input : hsncode, taxid output :
	 * Message and status depending upon conditions Purpose : Check the combination
	 * of hsncode and taxid in hsntaxlink Comment : checkduplicatehsncode
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/gethsncode_check_duplicate/{hsncode}/{taxid}", produces = "application/json")
	@ApiOperation(value = "Retrieve a hsncode entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a hsncode entity has been retrieved successfully")
	public String checkduplicatehsncode(@PathVariable("hsncode") String hsncode, @PathVariable("taxid") Long taxid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		/**
		 * Check the combination of hsncode and taxid in hsntaxlink if found give the
		 * message as Duplicate entry found for this hsn code If exist check in
		 * hsntaxlink
		 **/
		String query = "SELECT count(*)count  \r\n" + "FROM hsntaxlink\r\n" + " WHERE hsntaxlink.orgid = "
				+ adUserAccessToken.get("orgid") + " AND hsntaxlink.oprid =" + adUserAccessToken.get("oprid")
				+ " AND hsntaxlink.hsncode ='" + hsncode + "' AND hsntaxlink.taxid = " + taxid
				+ " AND hsntaxlink.active = 1";
		data = utility.executeQueryOnPool(query, request);
		

		JsonObject jsonObject = new JsonObject();
		if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			// Message as per praful bhange
			jsonObject.addProperty("message", " Duplicate entry found for this hsn code " + hsncode);
		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");

		}
		return jsonObject.toString();
	}

	/*
	 * *****************************************************************************
	 * Author : Aayush Dahake Date : 28/12/2021 Input : Agentid(Doctorid), Other
	 * Required details output : Agentid(Doctorid) and status message Purpose :
	 * Doctor product form Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postDoctorProdSetupSelectAll", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Agent entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Agent entity has been created successfully")
	public String postDoctorProdSetupSelectAll(@RequestBody @Validated MstAgentBody body, HttpServletRequest request) {
		String response = "";

		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {

			response = customService.postDoctorProdSetupSelectAll(body, request);
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/*
	 * *****************************************************************************
	 * Author : Chetan Channe Date : 28/12/2021 Input : Agentid(Doctorid)Other
	 * Required details output : Agentid(Doctorid) and status message Purpose :
	 * Doctor customer form Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postDoctorCustSetupSelectAll", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Agent entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Agent entity has been created successfully")
	public String postDoctorCustSetupSelectAll(@RequestBody @Validated MstAgentBody body, HttpServletRequest request) {
		String response = "";

		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {

			response = customService.postDoctorCustSetupSelectAll(body, request);
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}

	}

	/*
	 * *****************************************************************************
	 * Author : Aayush Dahake Date : /12/2021 Input : oprtype id -1 for whole sale
	 * output : data for setup parameter master and detail Purpose : fetch the setup
	 * globally Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/fetch_setup_values/{OPRTYPEID}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Setupparammaster entity with a specified 'OPRTYPEID' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Setupparammaster entity has been retrieved successfully")
	public String fetchSetupValues(@PathVariable(name = "OPRTYPEID") long oprtypeid, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		String query = "select  setupparammasterid, smmname from setupparammaster where oprtypeid = " + oprtypeid;
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonArray dataArray = new JsonArray();
		if (data.size() > 0) {
			for (JsonElement jsonElement : data) {
				JsonObject rootObject = jsonElement.getAsJsonObject();
				query = "SELECT  setupparamdtlid, spmname, spmvalname , spmval , sysparimeterkey "
						+ "FROM setupparamdtl where setupparammasterid = " + rootObject.get("setupparammasterid")
						+ " ORDER BY spmsequence";
				JsonArray datadtl = utility.executeQueryOnPool(query, request);
				if (datadtl.size() > 0) {
					rootObject.add("setupparamdtl", datadtl);
					dataArray.add(rootObject);
				}

			}
		}

		JsonObject setupparammaster = new JsonObject();
		setupparammaster.add("setupparammaster", dataArray);

		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.add("data", setupparammaster);
		return response.toString();
	}

	// Move to sale controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-01-12 
	 * Input : Data related to sales order
	 * output : Json depending upon conditions 
	 * Purpose : sale order form Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postSalesOrder", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postSalesOrder entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a BatchMaster entity has been created successfully")
	public String postSalesOrder(@RequestBody @Validated TrnMasterOrderBody body, HttpServletRequest request) {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postSalesOrder(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 10/01/2022 Input : Batch related data output :
	 * Product Id and status message Purpose : Update batch narration Comment :
	 * updating remarks in batch narration
	 ********************************************************************************/
	@PostMapping(path = "/postBatchNarrationEntry", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on batch entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a BatchMaster entity has been created successfully")
	public String postBatchNarration(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		long productid = body.get(0).getProductid();
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else { 
			// Multiple batches update by batchid
			body.forEach(batch -> {
				JsonObject mstBatch = new JsonObject();
				mstBatch.addProperty("remarks", batch.getRemarks());

				JsonObject mstBatchwhere = new JsonObject();
				mstBatchwhere.addProperty("orgid", adUserAccessToken.get("orgid"));
				mstBatchwhere.addProperty("oprid", adUserAccessToken.get("oprid"));
				mstBatchwhere.addProperty("batchid", batch.getBatchid());

				utility.executeCustomDML("batch", "batchid", mstBatch, mstBatchwhere, "UPDATE", hRequest);
			});
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", productid);
			jsonObject.addProperty("message", "Record Saved Successfully ");
			response = jsonObject.toString();
		}
		return response.toString();
	}

	// Move to transaction master
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postPhysicalStockAdjustment", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on stockUpdate entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity has been updated successfully")
	public String physicalStockAdjustment(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request) {
		return customService.physicalStockAdjustment(body, request);
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 10/01/2022 Input : Batch related data output :
	 * Product Id and status message Purpose : Update batch narration Comment :
	 * updating remarks in batch narration
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postShortSupply", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postShortSupply entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postShortSupply entity has been created successfully")
	public String postShortSupply(@RequestBody @Validated ShortsupplyBody body, HttpServletRequest request) {
		return customService.postShortSupply(body, request);
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 10/01/2022 Input : Batch related data output :
	 * Product Id and status message Purpose : Update batch narration Comment :
	 * updating remarks in batch narration
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockShortage", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on stockUpdate entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity has been updated successfully")
	public String postStockShortage(@RequestBody @Validated CustomBody body, HttpServletRequest request) {
		return customService.postStockShortage(body, request);
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 10/01/2022 Input : Batch related data output :
	 * Product Id and status message Purpose : Update batch narration Comment :
	 * updating remarks in batch narration
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_mst_temp/{tempmasterid}/{createdby}", produces = "application/json")
	@ApiOperation(value = "Retrieve a custom entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getMstTempByid(@PathVariable(name = "tempmasterid") long tempmasterid,
			@PathVariable(name = "createdby") long createdby, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "", mstid = "", userid = "";

		if (tempmasterid == 0) {
			mstid = null;
		} else {

			mstid = String.valueOf(tempmasterid);
		}

		if (createdby == 0) {
			userid = null;
		} else {
			userid = String.valueOf(createdby);
		}
		// Product Level details

		JsonArray jsonArrayObject = new JsonArray();
		query = "SELECT DISTINCT\r\n" + "       (temp.productid),\r\n" + "       temp.productname,\r\n"
				+ "       temp.displaypack,\r\n" + "       temp.mrp,\r\n" + "       temp.srate,\r\n"
				+ "       (SELECT sum(ifnull(tempclose.currentstock, 0))\r\n" + "        FROM temp tempclose\r\n"
				+ "        WHERE     tempclose.orgid = temp.orgid\r\n"
				+ "              AND tempclose.oprid = temp.oprid\r\n"
				+ "              AND tempclose.tempmasterid = temp.tempmasterid\r\n"
				+ "              AND tempclose.productid = temp.productid)\r\n" + "          currentstock,\r\n"
				+ "       (SELECT sum( case when tempclose.actualstock = 0 then tempclose.currentstock else \r\n"
				+ "        tempclose.actualstock\r\n" + "       end)\r\n" + "        FROM temp tempclose\r\n"
				+ "        WHERE     tempclose.orgid = temp.orgid\r\n"
				+ "              AND tempclose.oprid = temp.oprid\r\n"
				+ "              AND tempclose.tempmasterid = temp.tempmasterid\r\n"
				+ "              AND tempclose.productid = temp.productid group by tempclose.productid)actualstock,\r\n"
				+ "       (SELECT sum(ifnull(tempclose.diffqty, 0))\r\n" + "        FROM temp tempclose\r\n"
				+ "        WHERE     tempclose.orgid = temp.orgid\r\n"
				+ "              AND tempclose.oprid = temp.oprid\r\n"
				+ "              AND tempclose.tempmasterid = temp.tempmasterid\r\n"
				+ "              AND tempclose.productid = temp.productid)\r\n"
				+ "          diffqty\r\n , temp.createdby,\r\n"
				+ "	   temp.createdon , temp.tempmasterid , location.title location ,  agency.title agency"
				+ "  FROM temp temp INNER JOIN productoprdtl productoprdtl\r\n"
				+ "        ON     temp.orgid = productoprdtl.orgid\r\n"
				+ "           AND temp.oprid = productoprdtl.oprid\r\n"
				+ "           AND temp.productid = productoprdtl.productid    LEFT JOIN location\r\n"
				+ "        ON     productoprdtl.orgid = location.orgid\r\n"
				+ "           AND productoprdtl.oprid = location.oprid\r\n"
				+ "           AND productoprdtl.locationid = location.locationid   INNER JOIN product\r\n"
				+ "        ON     product.orgid = productoprdtl.orgid\r\n"
				+ "           AND product.productid = productoprdtl.productid\r\n" + "     INNER JOIN agency agency\r\n"
				+ "        ON     product.orgid = agency.orgid\r\n"
				+ "           AND product.agencicyid = agency.agencyid   \r\n" + " where temp.orgid ="
				+ adUserAccessToken.get("orgid") + " AND temp.oprid =" + adUserAccessToken.get("oprid")
				+ " and  temp.tempmasterid = ifnull(" + mstid + ",temp.tempmasterid) and  temp.createdby = ifnull("
				+ userid + ",temp.createdby) group by temp.productid";
		JsonArray data = utility.executeQueryOnPool(query, request);

		if (data.size() > 0) {
			for (JsonElement jsonElement : data) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				// Batch Level details
				query = "SELECT temp.*,\r\n" + "       batch.batchid internalid,\r\n"
						+ "       batch.shortname shortname,\r\n" + "       batch.purchaserate purchaserate,\r\n"
						+ "       batch.remarks remarks,\r\n" + "       batch.trade trade,\r\n"
						+ "       0 godownstock\r\n" + "FROM temp \r\n" + "INNER JOIN batch batch\r\n"
						+ "        ON     temp.batchid = batch.batchid\r\n"
						+ "           AND temp.oprid = batch.oprid\r\n"
						+ "           AND temp.orgid = batch.orgid where temp.orgid =" + adUserAccessToken.get("orgid")
						+ " and temp.oprid =" + adUserAccessToken.get("oprid") + " and temp.productid ="
						+ jsonObject.get("productid") + " and  temp.tempmasterid = ifnull(" + mstid
						+ ",temp.tempmasterid) and  temp.createdby = ifnull(" + userid + ",temp.createdby)";

				jsonObject.addProperty("orgid", Integer.parseInt(adUserAccessToken.get("orgid")));
				jsonObject.addProperty("oprid", Integer.parseInt(adUserAccessToken.get("oprid")));
				JsonArray dataDtl = utility.executeQueryOnPool(query, request);
				jsonObject.add("tempbody", dataDtl);

				jsonArrayObject.add(jsonObject);
			}

		}

		return jsonArrayObject.toString();

	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 10/01/2022 Input : Batch related data output :
	 * Product Id and status message Purpose : Update batch narration Comment :
	 * updating remarks in batch narration
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockShortagefinalsave", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postStockShortagefinalsave entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Tempmaster entity has been created successfully")
	public String postStockShortagefinalsave(@RequestBody @Validated TempBody body, HttpServletRequest request) {
		return customService.postStockShortagefinalsave(body, request);
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : Input : trnmasterid and voucherid output : Data
	 * from transaction master and detail Purpose : Common api for basket operations
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/trn_master_detail_by_id/{transactionmasterid}/{vouchertype}", produces = "application/json")
	@ApiOperation(value = "Retrieve a Transactionmaster entity with a specified 'transactionmasterid' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been retrieved successfully")
	public String getTransactionByid(@PathVariable(name = "transactionmasterid") String transactionmasterid,
			@PathVariable(name = "vouchertype") String vouchertype, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "", labelquery = "", tablename = "";
		JsonArray jsonArrayColumnListMaster = new JsonArray();
		JsonArray jsonArrayColumnListDetail = new JsonArray();
		switch (vouchertype) {
		case "SL":
		case "DM":
			query = "SELECT tm.transactionmasterid,\r\n" + "       tm.grossamount,\r\n"
					+ "       tm.productdiscountamount,\r\n" + "       tm.cashdiscountamount,\r\n"
					+ "       tm.gstamount,\r\n" + "       tm.cessamount,\r\n" + "       tm.creditnoteamount,\r\n"
					+ "       tm.debitnoteamount,\r\n" + "       tm.netamount,\r\n"
					+ "       tm.addcommissionpercent,\r\n" + "       tm.packingamount,\r\n"
					+ "       tm.otheradditionamount,\r\n" + "       tm.otherdeductionamount,\r\n" + "       (CASE\r\n"
					+ "           WHEN tm.paymentmode = 1 THEN 'Cash'\r\n"
					+ "           WHEN tm.paymentmode = 2 THEN 'Credit'\r\n"
					+ "           WHEN tm.paymentmode = 3 THEN 'Card'\r\n"
					+ "           WHEN tm.paymentmode = 4 THEN 'Free'\r\n" + "           ELSE 'Cash'\r\n"
					+ "        END)\r\n" + "          paymentmode ,  tm.voucherno,\r\n"
					+ "       tm.voucherdate , agent.name agentname, (case when tm.post = 1 then 'Yes'\r\n"
					+ "       else 'No' end)updateinaccounts, tm.customername name \r\n"
					+ "FROM transactionmaster tm\r\n" + "     LEFT JOIN agent agent\r\n"
					+ "        ON     tm.agentid = agent.agentid\r\n" + "           AND tm.orgid = agent.orgid\r\n"
					+ "           AND tm.oprid = agent.oprid    where  tm.orgid = " + adUserAccessToken.get("orgid")
					+ "      AND tm.oprid = " + adUserAccessToken.get("oprid") + ""
					+ "      AND tm.transactionmasterid = " + transactionmasterid + "" + "      AND tm.vouchertype = '"
					+ vouchertype + "' ";

			tablename = "'SALETRANSACTIONMASTER'";
			break;

		case "PR":
			query = "SELECT   tm.transactionmasterid,tm.voucherno,\r\n" + "       tm.voucherdate,\r\n"
					+ "       tm.productdiscountamount,\r\n" + "       tm.creditnoteamount,\r\n"
					+ "       tm.debitnoteamount,\r\n" + "       tm.igstamount,\r\n" + "       tm.cgstamount,\r\n"
					+ "       tm.sgstamount,\r\n" + "       tm.cessamount,\r\n" + "       tm.tcsamount,\r\n"
					+ "       tm.grossamount,\r\n" + "       tm.otheradditionamount,\r\n"
					+ "       tm.otherdeductionamount,\r\n" + "       tm.netamount,\r\n"
					+ "       tm.specialdiscountamt,\r\n" + "       agent.name,\r\n" + "       tm.agencyid ,  (CASE\r\n"
					+ "           WHEN tm.paymentmode = 1 THEN 'Cash'\r\n"
					+ "           WHEN tm.paymentmode = 2 THEN 'Credit'\r\n"
					+ "           WHEN tm.paymentmode = 3 THEN 'Card'\r\n"
					+ "           WHEN tm.paymentmode = 4 THEN 'Free'\r\n" + "           ELSE 'Cash'\r\n"
					+ "        END)\r\n" + "          paymentmode , (case when tm.post = 1 then 'Yes'\r\n"
					+ "       else 'No' end)updateinaccounts \r\n" + "FROM transactionmaster tm\r\n"
					+ "     left JOIN agent agent\r\n" + "        ON     tm.orgid = agent.orgid\r\n"
					+ "           AND tm.oprid = agent.oprid\r\n" + "           AND tm.agentid = agent.agentid\r\n"
					+ "     INNER JOIN agency agency\r\n" + "        ON     tm.agencyid = agency.agencyid\r\n"
					+ "           AND tm.oprid = agency.oprid\r\n" + "           AND tm.orgid = agency.orgid\r\n"
					+ " WHERE     tm.orgid = " + adUserAccessToken.get("orgid") + "" + "      AND tm.oprid = "
					+ adUserAccessToken.get("oprid") + "" + "      AND tm.transactionmasterid = " + transactionmasterid
					+ "" + "      AND tm.vouchertype = 'PR'";

			tablename = "'PURCHASETRANSACTIONMASTER'";
			break;

		case "SO":
			query = "SELECT tom.transactionordermasterid transactionmasterid,\r\n" + "       tom.voucherno,\r\n"
					+ "       tom.voucherdate,\r\n" + "       tom.netamount,\r\n"
					+ "       (CASE WHEN tom.post = 1 THEN 'Yes' ELSE 'No' END) updateinaccounts,\r\n"
					+ "       tom.accountid,\r\n" + "       tom.customername name,\r\n"
					+ "        concat(ifnull(tom.customeradd1, ''),\r\n" + "              ' ',\r\n"
					+ "              ifnull(tom.customeradd2, ''),\r\n" + "              ' ',\r\n"
					+ "              ifnull(tom.customeradd3, ''))\r\n" + "          customeraddress\r\n"
					+ "FROM transactionordermaster tom" + " WHERE     tom.vouchertype = 'SO'\r\n"
					+ "      AND tom.orgid = " + adUserAccessToken.get("orgid") + "      AND tom.oprid = "
					+ adUserAccessToken.get("oprid") + "      AND tom.transactionordermasterid =" + transactionmasterid;
			tablename = "'SALEORDERTRANSACTIONMASTER'";
			break;
		case "RP":
			query = "SELECT tm.transactionmasterid,\r\n" + "       tm.voucherno,\r\n" + "       tm.voucherdate,\r\n"
					+ "       tm.netamount,\r\n" + "       (SELECT trnmastercrnote.mrpvalue\r\n"
					+ "        FROM transactionmaster trnmastercrnote\r\n"
					+ "        WHERE     trnmastercrnote.orgid = " + adUserAccessToken.get("orgid") + ""
					+ "              AND trnmastercrnote.oprid = " + adUserAccessToken.get("oprid") + ""
					+ "              AND trnmastercrnote.voucherno = tm.adjcreditnoteno\r\n"
					+ "              AND trnmastercrnote.vouchertype = 'CN'\r\n"
					+ "              AND trnmastercrnote.vouchermode = 2\r\n"
					+ "              AND trnmastercrnote.active = 1)" + "crnotemrpamount,\r\n"
					+ "       (SELECT trnmastercrnote.replacementamount\r\n"
					+ "        FROM transactionmaster trnmastercrnote\r\n"
					+ "        WHERE     trnmastercrnote.orgid = " + adUserAccessToken.get("orgid") + ""
					+ "              AND trnmastercrnote.oprid = " + adUserAccessToken.get("oprid") + ""
					+ "              AND trnmastercrnote.voucherno = tm.adjcreditnoteno\r\n"
					+ "              AND trnmastercrnote.vouchertype = 'CN'\r\n"
					+ "              AND trnmastercrnote.vouchermode = 2\r\n"
					+ "              AND trnmastercrnote.active = 1)\r\n" + "          replacementamount,\r\n"
					+ "       ((SELECT trnmastercrnote.mrpvalue\r\n"
					+ "           FROM transactionmaster trnmastercrnote\r\n"
					+ "           WHERE     trnmastercrnote.orgid = " + adUserAccessToken.get("orgid") + ""
					+ "                 AND trnmastercrnote.oprid =" + adUserAccessToken.get("oprid") + ""
					+ "                 AND trnmastercrnote.voucherno = tm.adjcreditnoteno\r\n"
					+ "                 AND trnmastercrnote.vouchertype = 'CN'\r\n"
					+ "                 AND trnmastercrnote.vouchermode = 2\r\n"
					+ "                 AND trnmastercrnote.active = 1)\r\n"
					+ "        - (SELECT trnmastercrnote.replacementamount\r\n"
					+ "           FROM transactionmaster trnmastercrnote\r\n"
					+ "           WHERE     trnmastercrnote.orgid = " + adUserAccessToken.get("orgid") + ""
					+ "                 AND trnmastercrnote.oprid =" + adUserAccessToken.get("oprid") + ""
					+ "                 AND trnmastercrnote.voucherno = tm.adjcreditnoteno\r\n"
					+ "                 AND trnmastercrnote.vouchertype = 'CN'\r\n"
					+ "                 AND trnmastercrnote.vouchermode = 2\r\n"
					+ "                 AND trnmastercrnote.active = 1))\r\n" + "          balance,\r\n"
					+ "       tm.netamount , (case when tm.post = 1 then 'Yes'\r\n"
					+ "       else 'No' end)updateinaccounts , tm.customername name\r\n"
					+ "FROM transactionmaster tm\r\n" + "WHERE     tm.vouchertype = 'RP'\r\n" + "      AND tm.orgid = "
					+ adUserAccessToken.get("orgid") + "      AND tm.oprid = " + adUserAccessToken.get("oprid")
					+ "      AND tm.transactionmasterid = " + transactionmasterid;

			tablename = "'REPTRANSACTIONMASTER'";
			break;
		case "CN":
		case "DN":
		case "SDCN":
		case "CCD":
			query = "SELECT tm.transactionmasterid,\r\n" + "       tm.grossamount,\r\n" + "       tm.creditnoteflag,\r\n" + "       tm.cashdiscountpercent,\r\n"
					+ "       tm.productdiscountamount,\r\n" + "       tm.cashdiscountamount,\r\n"
					+ "       tm.gstamount,\r\n" + "       tm.netamount, (case when tm.post = 1 then 'Yes'\r\n"
					+ "       else 'No' end)updateinaccounts ,tm.customername name , ifnull(cgstamount,0) cgstamount, ifnull(sgstamount,0) sgstamount, ifnull(igstamount,0) igstamount, ifnull(ugstamount,0)  ugstamount, ifnull(cessamount,0)  cessamount ,  ifnull(otheradditionamount,0)  otheradditionamount, ifnull(otherdeductionamount,0) otherdeductionamount , reason , agencyid , agencygroupid, billno , creditnoteamount , debitnoteamount , adjcreditnoteno , billamount , billdate , adjcreditnotedate , tm.description \r\n"
					+ "FROM transactionmaster tm\r\n" + "WHERE     tm.orgid = " + adUserAccessToken.get("orgid")
					+ "      AND tm.oprid = " + adUserAccessToken.get("oprid") + "      AND tm.transactionmasterid = "
					+ transactionmasterid;
			tablename = "'CREDITNOTETRANSACTIONMASTER'";
			break;

		case "IS":
			query = " SELECT transactionmaster.transactionmasterid , transactionmaster.voucherdate\r\n"
					+ "          voucherdate,\r\n" + "       sum(ifnull(transactiondetail.qty, 0))totalquantity,\r\n"
					+ "       (SELECT count(*)\r\n" + "        FROM transactiondetail\r\n"
					+ "        WHERE     transactiondetail.orgid = 1\r\n"
					+ "              AND transactiondetail.oprid = 1\r\n"
					+ "              AND transactiondetail.transactionmasterid =\r\n"
					+ "                  transactionmaster.transactionmasterid) totalitem , 'Stock  shortage' description\r\n"
					+ "FROM transactionmaster transactionmaster \r\n" + "     INNER JOIN transactiondetail\r\n"
					+ "        ON     transactionmaster.orgid = transactiondetail.orgid\r\n"
					+ "           AND transactionmaster.oprid = transactiondetail.oprid\r\n"
					+ "           AND transactionmaster.transactionmasterid =\r\n"
					+ "               transactiondetail.transactionmasterid\r\n"
					+ "WHERE     transactionmaster.vouchertype = 'IS'\r\n" + "      AND transactionmaster.orgid = "
					+ adUserAccessToken.get("orgid") + "      AND transactionmaster.oprid = "
					+ adUserAccessToken.get("oprid") + "      AND transactionmaster.transactionmasterid = "
					+ transactionmasterid;
			tablename = "'STOCKISSUERANSACTIONMASTER'";
			break;

		case "PREB":
			query = "select transactionmasterid, agencyid , agencygroupid , qtycount , itemcount  from  transactionmaster where orgid ="
					+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
					+ " and transactionmasterid = " + transactionmasterid;
			tablename = "'EXPIRYBREAKAGETRNMASTER'";
			break;
		case "CCS":
		case "CCE":
		case "OIE":	
		case "OIS":	
		case "SIE":
		case "SIS":	
			query = " select transactionmaster.transactionmasterid ,   qtycount , itemcount , grossamount , productdiscountamount , cashdiscountamount ,netamount , gstamount , tcsamount , roundof , otheradditionamount, otherdeductionamount , otheradditionnarration , otherdeductionnarration , gstinno , gsttype , invoicelrdetail.billno,\r\n"
					+ "       invoicelrdetail.transportid,\r\n" + "       invoicelrdetail.lrno,\r\n"
					+ "       invoicelrdetail.lrdate,\r\n" + "       invoicelrdetail.noofcases,\r\n"
					+ "       invoicelrdetail.billamt,\r\n" + "       invoicelrdetail.paidtype,\r\n"
					+ "       invoicelrdetail.paidamount , (case when  gsttype = 0 then 'Unregistered'\r\n"
					+ "when gsttype = 1 then 'Registered'\r\n"
					+ "when  gsttype = 2 then 'Composite' end)gsttypedesc , (case when invoicelrdetail.paidtype = 0 then 'To Pay' when   invoicelrdetail.paidtype =1 then 'Paid' else 'To pay' end)paidtypedesc  , (select  transportname  from transport where transportid = invoicelrdetail.transportid  and orgid =invoicelrdetail.orgid and oprid =invoicelrdetail.oprid)transportname    from  transactionmaster  LEFT JOIN invoicelrdetail\r\n"
					+ "        ON     transactionmaster.orgid = invoicelrdetail.orgid\r\n"
					+ "           AND transactionmaster.oprid = invoicelrdetail.oprid\r\n"
					+ "           AND transactionmaster.transactionmasterid =\r\n"
					+ "               invoicelrdetail.transactionmasterid where transactionmaster.orgid = "
					+ adUserAccessToken.get("orgid") + " and transactionmaster.oprid =" + adUserAccessToken.get("oprid")
					+ " and transactionmaster.transactionmasterid = " + transactionmasterid;
			tablename = "'COMPANYCLAIMMASTER'";
			break;
		case "SA":
		case "SI":
			query = "SELECT * FROM stocktransfermaster \r\n" + "where  orgid = " + adUserAccessToken.get("orgid")
					+ "      AND oprid = " + adUserAccessToken.get("oprid") + ""
					+ "      AND stocktransfermasterid = " + transactionmasterid + "" + "   AND  vouchertype = '"
					+ vouchertype + "' ";

			tablename = "'STOCKTRANSFERMASTER'";
			break;
		}
		
		String response = "";
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonArray datadtl = new JsonArray();

		labelquery = "select  columname field , labelen headers from resourcebundleconfig  where tablename like "
				+ tablename;
		jsonArrayColumnListMaster = utility.executeQueryOnPool(labelquery, request);

		labelquery = "select  columname field , labelen headers from resourcebundleconfig  where tablename like 'transactiondetail'";
		jsonArrayColumnListDetail = utility.executeQueryOnPool(labelquery, request);
		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.add("transactionmaster", data);

			String detailtablename = "", detailmasterid = "";
			switch (vouchertype) {
			case "SO":
				detailtablename = "transactionorderdetail";
				detailmasterid = "transactionordermasterid";
				break;
			case "SA":
			case "SI":
				detailtablename = "stocktransferdetail";
				detailmasterid = "stocktransfermasterid";
				break;
			default:
				detailtablename = "transactiondetail";
				detailmasterid = "transactionmasterid";
			}

			if (data.get(0).getAsJsonObject().has("cdp") && data.get(0).getAsJsonObject().get("cdp").getAsInt() == 1 || data.get(0).getAsJsonObject().has("cdp")) {
				datadtl = transactionService.getCommonTransactionResponse(transactionmasterid, 0, request);
			} else {
				query = "SELECT * FROM " + detailtablename + "  where " + detailmasterid + " = " + transactionmasterid;
				
				datadtl = utility.executeQueryOnPool(query, request);
			}
			jsonObject.add("transactiondetail", datadtl);
			jsonObject.add("transactionmastercolumnheaders", jsonArrayColumnListMaster);
			jsonObject.add("transactiondetailcolumnheaders", jsonArrayColumnListDetail);

		}
		
		response = jsonObject.toString();
		return response;
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 20/01/2022 Input : token releated output : Tree
	 * structure for COUNTRY -> CITY-> STATE Purpose : Tree for country city state
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_country_state_city_listing", produces = "application/json")
	@ApiOperation(value = "Retrieve a custom entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getCountryCityStateListing(HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonObject jsonObjectOrganization = new JsonObject();
		JsonArray jsonArrayOrganization = new JsonArray();
		String query = "select * from  adorgmst where adomid = " + adUserAccessToken.get("orgid");
		JsonArray dataorg = utility.executeQueryOnPool(query, request);
		if (dataorg.size() > 0) {

			for (JsonElement jsonElementorganization : dataorg.getAsJsonArray()) {

				jsonObjectOrganization.add("label", jsonElementorganization.getAsJsonObject().get("adomorgname"));
				jsonObjectOrganization.add("id", jsonElementorganization.getAsJsonObject().get("adomid"));
				jsonObjectOrganization.add("createdby", jsonElementorganization.getAsJsonObject().get("createdby"));
				jsonObjectOrganization.add("createdon", jsonElementorganization.getAsJsonObject().get("createdon"));
				jsonObjectOrganization.addProperty("src", "assets/img/area-region/Organization.png");
				jsonObjectOrganization.addProperty("identity", "Organization");

				JsonArray country = new JsonArray();
				query = "select * from country where orgid =" + adUserAccessToken.get("orgid");
				JsonArray dataCountry = utility.executeQueryOnPool(query, request);
				if (dataCountry.size() > 0) {
					for (JsonElement jsonElement : dataCountry.getAsJsonArray()) {
						JsonArray state = new JsonArray();
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("label", jsonElement.getAsJsonObject().get("title"));
						jsonObject.add("id", jsonElement.getAsJsonObject().get("countryid"));
						jsonObject.add("createdby", jsonElement.getAsJsonObject().get("createdby"));
						jsonObject.add("createdon", jsonElement.getAsJsonObject().get("createdon"));
						jsonObject.add("countrycode", jsonElement.getAsJsonObject().get("countrycode"));
						jsonObject.addProperty("src", "assets/img/area-region/Country.png");
						jsonObject.addProperty("identity", "Country");

						query = "select * from state where countryid =" + jsonElement.getAsJsonObject().get("countryid")
								+ "  order by  sequenceno asc ";
						JsonArray dataState = utility.executeQueryOnPool(query, request);
						if (dataState.size() > 0) {
							for (JsonElement jsonElementState : dataState.getAsJsonArray()) {
								JsonArray city = new JsonArray();
								JsonObject jsonObjectState = new JsonObject();
								jsonObjectState.add("label", jsonElementState.getAsJsonObject().get("title"));
								jsonObjectState.add("id", jsonElementState.getAsJsonObject().get("stateid"));
								jsonObjectState.add("isut", jsonElementState.getAsJsonObject().get("isut"));
								jsonObjectState.add("createdby", jsonElementState.getAsJsonObject().get("createdby"));
								jsonObjectState.add("createdon", jsonElementState.getAsJsonObject().get("createdon"));
								jsonObjectState.add("sequenceno", jsonElementState.getAsJsonObject().get("sequenceno"));
								jsonObjectState.add("stateno", jsonElementState.getAsJsonObject().get("stateno"));
								jsonObjectState.addProperty("src", "assets/img/area-region/State.png");
								jsonObjectState.addProperty("identity", "State");

								query = "select * from city where stateid = "
										+ jsonElementState.getAsJsonObject().get("stateid")
										+ " order by  sequenceno asc ";
								JsonArray dataCity = utility.executeQueryOnPool(query, request);
								if (dataCity.size() > 0) {
									for (JsonElement jsonElementCity : dataCity.getAsJsonArray()) {
										JsonArray area = new JsonArray();
										JsonObject jsonObjectCity = new JsonObject();
										jsonObjectCity.add("label", jsonElementCity.getAsJsonObject().get("title"));
										jsonObjectCity.add("id", jsonElementCity.getAsJsonObject().get("cityid"));
										jsonObjectCity.add("issez", jsonElementCity.getAsJsonObject().get("issez"));
										jsonObjectCity.add("createdby",
												jsonElementCity.getAsJsonObject().get("createdby"));
										jsonObjectCity.add("createdon",
												jsonElementCity.getAsJsonObject().get("createdon"));
										jsonObjectCity.add("sequenceno",
												jsonElementCity.getAsJsonObject().get("sequenceno"));
										jsonObjectCity.addProperty("src", "assets/img/area-region/City.png");
										jsonObjectCity.addProperty("identity", "City");

										query = "select * from area where cityid = "
												+ jsonElementCity.getAsJsonObject().get("cityid")
												+ " order by  sequenceno asc ";
										JsonArray dataArea = utility.executeQueryOnPool(query, request);
										if (dataArea.size() > 0) {
											for (JsonElement jsonElementArea : dataArea.getAsJsonArray()) {
												JsonObject jsonObjectArea = new JsonObject();
												jsonObjectArea.add("label",
														jsonElementArea.getAsJsonObject().get("title"));
												jsonObjectArea.add("id",
														jsonElementArea.getAsJsonObject().get("areaid"));
												jsonObjectArea.add("createdby",
														jsonElementArea.getAsJsonObject().get("createdby"));
												jsonObjectArea.add("createdon",
														jsonElementArea.getAsJsonObject().get("createdon"));
												jsonObjectArea.add("sequenceno",
														jsonElementArea.getAsJsonObject().get("sequenceno"));
												jsonObjectArea.addProperty("src", "assets/img/area-region/Area.png");
												jsonObjectArea.addProperty("identity", "Area");
												jsonObjectArea.add("pincode",
														jsonElementArea.getAsJsonObject().get("pincode"));
												jsonObjectArea.add("edepincode",
														jsonElementArea.getAsJsonObject().get("edepincode"));

												area.add(jsonObjectArea);
											}
											jsonObjectCity.add("children", area);
										}
										city.add(jsonObjectCity);
									}
									jsonObjectState.add("children", city);
								}
								state.add(jsonObjectState);
							}
							jsonObject.add("children", state);
						}

						country.add(jsonObject);
					}

					jsonObjectOrganization.add("children", country);
				}
				jsonArrayOrganization.add(jsonObjectOrganization);
			}

		}
		return jsonArrayOrganization.toString();
	}

	/* *****************************************************************************
	 * Author : Chetan Channe Date : 20/01/2022 Input : creditdebitstatus and
	 * transactionmasterid output : transactionmasterid and status message Purpose :
	 * Functionality to update the creditdebitstatus Comment :
	 * postCreditDebitTransfer in Transactionmaster
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCreditDebitNoteTransfer", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postCreditDebitTransfer entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postCreditDebitTransfer entity has been updated successfully")
	public String postCreditDebitNoteTransfer(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request)
			throws Exception {

		String response = "";
		long update = 0;
		update = customService.postCreditDebitNoteTransfer(body, request);
		// Return product code in response
		if (update >= 0) {
			// Response return product id
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", body.get(0).getProductid().toString());
			jsonObject.addProperty("message", "Record Saved Successfully");
			response = jsonObject.toString();
		}

		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}

	}

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postRateDifferenceCreditNote", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postRateDifferenceCreditNote entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a TrnMaster entity has been created successfully")
	public String postRateDifferenceCreditNote(@RequestBody @Validated List<CustomBody> body,
			HttpServletRequest request) {
		String id = customService.postRateDifferenceCreditNote(body, request);
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Updated Successfully");
		response.addProperty("Id", id);

		return response.toString();
	}

	/* *****************************************************************************
	 * Author : Chetan Channe Date : 2022-02-01 Input : Data related to
	 * TransactionorderMaster & orderbooking schedule output : Json response
	 * depending upon conditions Purpose : To perform fetch order record on
	 * Transaction TransactionorderMaster & orderbooking Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/getCRMOrderListing/{voucherdate}/{crmtype}/{accountname}", produces = "application/json")
	@ApiOperation(value = "Retrieve a CRMOrderList entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getCrmOrderListing(@PathVariable(name = "voucherdate") Date voucherdate,
			@PathVariable(name = "crmtype") Integer crmtype, @PathVariable(name = "accountname") String accountname,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray jsonArray = new JsonArray();
		String joinclause = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String voucherdateString = sdf.format(voucherdate);

		LocalDate currentDate = LocalDate.parse(voucherdateString);
		// Get days and dates from fulldate
		int orderdate = currentDate.getDayOfMonth();
		DayOfWeek orderday = currentDate.getDayOfWeek();
		// System.out.println(orderdate +" "+ orderdays);

		switch (crmtype) {
		case 1:
			joinclause = "inner join  transactionordermaster on   accountoprdetail.accountid = transactionordermaster.accountid \n ";
			break;

		case 2:
			joinclause = " inner join  transactionordermaster on     accountoprdetail.accountid not in(transactionordermaster.accountid)  \n ";
			break;

		case 3:
			joinclause = "";
			break;

		}
		String query = "SELECT account.accountid\r\n" + "          internalid,\r\n" + "       account.accountname\r\n"
				+ "          accountname,\r\n" + "       concat(ifnull(accountoprdetail.add1, ''),\r\n"
				+ "              ' ',\r\n" + "              ifnull(accountoprdetail.add2, ''),\r\n"
				+ "              ' ',\r\n" + "              ifnull(accountoprdetail.add3, ''))\r\n"
				+ "          customeraddress,\r\n" + "       (CASE\r\n" + "     WHEN (SELECT count(*)\r\n"
				+ "                 FROM transactionordermaster\r\n"
				+ "                 WHERE     transactionordermaster.orgid = 1\r\n"
				+ "                       AND transactionordermaster.oprid = 1\r\n"
				+ "                        AND transactionordermaster.mobileordertype = 2\r\n"
				+ "                       AND transactionordermaster.accountid =\r\n"
				+ "                           accountoprdetail.accountid) >\r\n" + "                0"
				+ "           THEN\r\n" + "              'Booked'\r\n" + "           ELSE\r\n"
				+ "              'Pending'\r\n" + "        END)\r\n" + "          status,\r\n"
				+ "       concat(ifnull(orderbookingschedule.scheduledate, ''),\r\n" + "              ' ',\r\n"
				+ "              ifnull(orderbookingschedule.scheduletime, ''))\r\n" + "          schedule,\r\n"
				+ "       accountoprdetail.cpphone\r\n" + "          cpphone,\r\n"
				+ "       accountoprdetail.cpname\r\n" + "          cpname,\r\n"
				+ "       accountoprdetail.creditdays\r\n" + "          creditdays,\r\n"
				+ "       accountoprdetail.creditlimit\r\n" + "          creditlimit,\r\n"
				+ "       accountoprdetail.lastbilldate\r\n" + "          lastbilldate,\r\n"
				+ "       accountoprdetail.ledgerbalance\r\n" + "          ledgerbalance,\r\n"
				+ "       accountoprdetail.mobileno\r\n" + "          mobileno\r\n" + "FROM account account\r\n"
				+ "     INNER JOIN accountoprdetail accountoprdetail\r\n"
				+ "        ON     account.orgid = accountoprdetail.orgid\r\n"
				+ "           AND account.accountid = accountoprdetail.accountid\r\n"
				+ "     LEFT JOIN orderbookingschedule\r\n"
				+ "        ON     accountoprdetail.orgid = orderbookingschedule.orgid\r\n"
				+ "           AND accountoprdetail.oprid = orderbookingschedule.oprid\r\n"
				+ "           AND account.accountid = orderbookingschedule.accountid" + " " + joinclause + "\n"
				+ "WHERE accountoprdetail.orgid =" + adUserAccessToken.get("orgid") + " AND accountoprdetail.oprid ="
				+ adUserAccessToken.get("oprid") + " AND accountoprdetail.identity = 1"
				+ " AND accountoprdetail.active = 1 and  account.accountname  like concat(ifnull(" + accountname
				+ ",account.accountname),'%')\r\n" + "and  FIND_IN_SET('" + orderdate + "', accountoprdetail.orderdates)"
				+ " OR FIND_IN_SET('" + orderday + "', accountoprdetail.orderdays)";
		jsonArray = utility.executeQueryOnPool(query, request);
		return jsonArray.toString();

	}

	
	/* *****************************************************************************
	 * Author : Chetan Channe Date : 2022-06-21 Input : Data related to
	 * Transaction Status  output : Json response
	 * depending upon conditions Purpose : To perform update order record on
	 * Transaction TransactionorderMaster  Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/getFlagStatusUpdate", produces = "application/json")
	@ApiOperation(value = "Retrieve all columns in respective workflow stages data", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if column list has been retrieved successfully")
	public String getFlagStatusUpdate(@RequestBody QueryApiBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		int result = customService.getFlagStatusUpdate(body, request);
		
		if (result > 0) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
		} else {
			response.addProperty("code", 400);
			response.addProperty("status", "Failure");
		}
		return response.toString();
	}
	
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-02-08 Input : Account Id and serial no
	 * output : Status and message Purpose : Update serial no Account master Comment
	 * : postAccountSerialChange
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postAccountSerialChange", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postAccountSerialChange entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a TrnMaster entity has been created successfully")
	public String postAccountSerialChange(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request) {
		int update = customService.postAccountSerialChange(body, request);
		JsonObject jsonObject = new JsonObject();
		if (update >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully ");
		}
		return jsonObject.toString();

	}

	/* *****************************************************************************
	 * Author : Chetan Channe Date : 2022-02-01 Input : Data customer and area and
	 * outstanding output : Json response depending upon conditions Purpose : To
	 * perform fetch days wise record on accountoprdetail ,area and outstanding
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/getDiscountDebitNoteEntry", consumes = "application/json")
	@ApiOperation(value = "Retrieve a DiscountDebitNoteEntry entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a entity entity has been retrieved successfully")
	public String getDiscountDebitNoteEntry(@RequestBody @Validated CustomCheckDuplicateValidateBody body,
			HttpServletRequest request) {
		JsonArray data = customService.getDiscountDebitNoteEntry(body, request);
		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.add("data", data);
		} else {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "FAILURE");
			jsonObject.addProperty("message", "Record not found");
		}

		return jsonObject.toString();
	}

	/* *****************************************************************************
	 * Author : Chetan Channe Date : Input : output : Purpose : Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postDiscountDebitNoteEntry", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postDiscountDebitNoteEntry(@RequestBody @Validated CustomBody body, HttpServletRequest request) {
		hRequest = request;
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			int result = customService.postDiscountDebitNoteEntry(body, request);
			if (result >= 0) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully ");
				response = jsonObject.toString();
			}
		}
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2020-02-18 Input : Id and Sequence No output :
	 * Success and Failure Status Purpose : Setting the sequence in area master
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postareamastersequence", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postAccountSerialChange entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a TrnMaster entity has been created successfully")
	public String postareamastersequence(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request) {
		int update = customService.postareamastersequence(body, request);
		JsonObject jsonObject = new JsonObject();
		if (update >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully ");
		}
		return jsonObject.toString();
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2020-02-16 Input : Transaction master and
	 * detail output : Response depending upon conditions Purpose : Expiry Breakage
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postExpiryBreakage", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction entity has been created successfully")
	public String postexpirybreakage(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postexpirybreakage(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2020-02-17 Input : Area id and flag for delete
	 * common for city state and country output : Response depending upon conditions
	 * Purpose : Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postdeleteArea", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a area  entity has been created successfully")
	public String postDeleteArea(@RequestBody @Validated CustomBody body, HttpServletRequest request) throws Exception {
		return customService.postDeleteArea(body, request);
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-02-22 Input : Route id and sequence no
	 * output : Status and message Purpose : To update the sequence no in route
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postRouteSequence", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Updating the sequence no in route master")
	public String postRouteSequence(@RequestBody @Validated List<CustomBody> body, HttpServletRequest request) {
		return customService.postRouteSequence(body, request);
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-02-28 , 2022-03-15 Input : transaction
	 * master body output : Response depending upon the conditions Purpose : Comment
	 * :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postClaim", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Creating the claim from expiry breakage")
	public String postClaim(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request) throws JsonProcessingException {
		return customService.postClaim(body, request);
	}

	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-02-28 Input : RoutearealinkBody output :
	 * Response depending upon the conditions Purpose : Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postRouteAreaLink", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postRouteAreaLink(@RequestBody @Validated List<RoutearealinkBody> body, HttpServletRequest request)
			throws Exception {
		return customService.postRouteAreaLink(body, request);
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-02-28 Input : InvoiceseriesBody output :
	 * Common Response Purpose : Save call for postInvoiceSeries Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postInvoiceSeries", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Creating the claim from expiry breakage")
	public String postInvoiceSeries(@RequestBody @Validated InvoiceseriesBody body, HttpServletRequest request)
			throws Exception {
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			// Check if isdefault is 'Y'
			if (body.getIsdefault().equals("Y")) {
				response = customService.postInvoiceSeries(body, request);
			} else {
				JsonObject responseObj = new JsonObject();
				responseObj.addProperty("code", 200);
				responseObj.addProperty("status", "Success");
				responseObj.addProperty("message", "Record Saved Successfully ");
				response = responseObj.toString();
			}
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-03-08 
	 * Input : Transaction master and detail 
	 * output : Response Depending upon conditions 
	 * Purpose : Save call for post gst Credit note 
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postGstCreditNote", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Creating the claim from expiry breakage")
	public String postGstCreditNote(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {		
		return customService.postGstCreditNote(body, request);
	}

	/* *****************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 2022-03-08 
	 * Input   : Custom body which include agency id , groupid , subgroupid 
	 * output  : Response Depending upon conditions
	 * Purpose : Expiry to claim conversion 
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postExpiryToClaimConversion", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Custom entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Creating the claim from expiry breakage")
	public String postexpirytoclaimconversion(@RequestBody @Validated CustomBody body, HttpServletRequest request) {

		String query = "SELECT transactiondetail.transactiondetailid internalid,\r\n"
				+ "       transactiondetail.transactionmasterid transactionmasterid,\r\n"
				+ "       transactiondetail.productname productname,\r\n" + "transactiondetail.productid productid,\r\n"
				+ "       transactiondetail.batchno batchno,\r\n" + "       transactiondetail.batchid batchid ,\r\n"
				+ "       transactiondetail.expiry expiry,\r\n" + "       transactiondetail.qty qty,\r\n"
				+ "       transactiondetail.free free,\r\n" + "       transactiondetail.mrp mrp,\r\n"
				+ "       transactiondetail.rate rate,\r\n"
				+ "       transactiondetail.itemvalue itemvalue ,   transactionmaster.gstamount\r\n"
				+ "          gstamount,\r\n" + "       (SELECT location.title\r\n" + "        FROM location\r\n"
				+ "        WHERE     location.orgid = 1\r\n" + "              AND location.oprid = 1\r\n"
				+ "              AND location.locationid = productoprdtl.locationid)\r\n"
				+ "          location , transactiondetail.trade trade , transactiondetail.purchaserate purchaserate  , '' scheme , transactiondetail.cessamount cessamount, transactiondetail.cashdiscountpercent cashdiscountpercent ,  transactiondetail.displaypacking displaypacking , transactiondetail.linkid linkid ,  transactiondetail.hsncode hsncode ,  transactiondetail.gstpercent gstpercent , gstvalue  gstvalue , transactiondetail.additionalcessamount additionalcessamount , transactiondetail.productdiscountpercent productdiscountpercent, transactiondetail.productdiscountamount productdiscountamount , transactiondetail.cesspercent cesspercent ,  ifnull(productoprdtl.allowproductdiscount,0) \r\n"
				+ "          allowcashdiscount ,  (SELECT producttype.title\r\n"
				+ "        FROM producttype producttype\r\n" + "        WHERE     producttype.orgid = 1\r\n"
				+ "              AND producttype.oprid = 1\r\n"
				+ "              AND producttype.producttypeid = product.producttypeid)\r\n"
				+ "          producttype,\r\n" + "       (SELECT content.name\r\n" + "        FROM content\r\n"
				+ "        WHERE content.orgid = 1 AND content.contentid = product.contentid)\r\n"
				+ "          contentname  , 0 lastreturn  , (case when  transactiondetail.gsttp  = 0 then 'Exempted'\r\n"
				+ "			when transactiondetail.gsttp = 1 then 'Taxable' \r\n"
				+ "      when transactiondetail.gsttp = 2 then  'Zero Rated' \r\n"
				+ "			when transactiondetail.gsttp = 3 then  'Non Gst' \r\n"
				+ "			when transactiondetail.gsttp = 4 then  'Nil rated' when transactiondetail.gsttp =5 then 'Direct'\r\n"
				+ "			end) gsttypedesc ,  transactiondetail.gsttp  gsttype ,   transactiondetail.productpack productpack  , transactiondetail.shortname shortname , transactiondetail.additionalcesspercent additionalcesspercent  \r\n"
				+ "FROM transactiondetail transactiondetail\r\n"
				+ "     INNER JOIN transactionmaster transactionmaster\r\n"
				+ "        ON     transactiondetail.orgid = transactionmaster.orgid\r\n"
				+ "           AND transactiondetail.oprid = transactionmaster.oprid\r\n"
				+ "           AND transactiondetail.transactionmasterid =\r\n"
				+ "               transactionmaster.transactionmasterid\r\n"
				+ "  INNER JOIN productoprdtl productoprdtl\r\n"
				+ "        ON     transactiondetail.orgid = productoprdtl.orgid\r\n"
				+ "           AND transactiondetail.oprid = productoprdtl.oprid\r\n"
				+ "           AND transactiondetail.productid = productoprdtl.productid\r\n"
				+ "INNER JOIN product product\r\n" + "        ON     product.orgid = productoprdtl.orgid\r\n"
				+ "           AND product.productid = productoprdtl.productid\r\n"
				+ "WHERE     transactiondetail.orgid = 1\r\n" + "      AND transactiondetail.oprid = 1\r\n"
				+ "      AND transactiondetail.vouchertype IN ('PREB','CN')\r\n"
				+ "      AND transactiondetail.linkid NOT IN (16)\r\n"
				+ "      AND transactiondetail.vouchermode = 2\r\n" + "      AND transactiondetail.active = 1\r\n"
				+ "      and ifnull(transactiondetail.companycode,'XX') = ifnull(" + body.getAgencyid()
				+ " ,ifnull(transactiondetail.companycode,'XX'))\r\n"
				+ "      and ifnull(transactionmaster.agencygroupid,'XX') = ifnull(" + body.getAgencygroupid()
				+ " ,ifnull(transactionmaster.agencygroupid,'XX'))";

		JsonArray data = utility.executeQueryOnPool(query, request);
		
		return customService.postexpirytoclaimconversion(body, data, request);
	}

	/* *****************************************************************************
	 * Author : Chetan Channe Date : 22/03/2022 Input : update CR Add limit,
	 * bill,days according accountid output : success code and status message
	 * Purpose : Functionality to update the scheme balance Comment :
	 * postUpdateAddCRLimit in accountoprdetail
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postUpdateAddCRLimit", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on postUpdateAddCRLimit entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a postUpdateAddCRLimit entity has been updated successfully")
	public String postUpdateAddCRLimit(@RequestBody @Validated CustomBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		// updating CR Limit bill and days as per account id
		int update = customService.postUpdateAddCRLimit(body, request);
		if (update >= 0) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Record Updated Successfully");
		}
		
		return response.toString();
	}

	/*********************************************************************************
	 * Author : Kushal Kadu Date : 04/04/2022 Input : Header = Data for transaction
	 * master detail : details for transaction detail Output : Response depending
	 * upon conditions Purpose : convert sale order to sale invoice Comment :
	 * postSaleOrderToSaleInvoice
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postSaleOrderToSaleInvoice", produces = "application/json")
	@ApiOperation(value = "Retrieve a Transactionmaster entity with a specified 'IDs'", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been retrieved successfully")
	public String postSaleOrderToSaleInvoice(@RequestBody @Validated OrderConversionBody body,
			HttpServletRequest request) {
		String[] ids = body.getIds().split(",");
		List<String> transactionidlist = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			transactionidlist.add(ids[i].trim());
		}

		return customService.postSaleOrderToSaleInvoice(transactionidlist, request);
	}

	// Move to transaction controller
	/* *****************************************************************************
	 * Author : Chetan Channe 
	 * Date : 2022-03-22 
	 * Input : Transaction master and detail 
	 * Output : Response depending upon conditions 
	 * Purpose : Sale Invoice
	 * Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postSaleInvoice", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on TrnMasterBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postsaleinvoice(@RequestBody @Validated TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postsaleinvoice(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}
	
	/* *****************************************************************************
	 * Author : Aayush Dahake Date : 2022-03-26 Input : CreditnoteslabBody output :
	 * JSON body which inculdes status message Purpose : Adding reasons for credit
	 * note Comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postcreditnotereason", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on TrnMasterBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a transaction  entity has been created successfully")
	public String postcreditnotereason(@RequestBody @Validated List<CreditnoteslabBody> body, HttpServletRequest request) {
		return customService.postcreditnotereason(body, request);
	}

	/*********************************************************************************
	 * Author : Kushal Kadu 
	 * Date : 07/04/2022 
	 * Input : Header = Data for transaction master detail : details for transaction detail 
	 * Output : Response depending upon conditions 
	 * Purpose : convert DM to sale invoice 
	 * Comment : postDMToSaleInvoice
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postDMToSaleInvoice", produces = "application/json")
	@ApiOperation(value = "Retrieve a Transactionmaster entity with a specified 'IDs'", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Transactionmaster entity has been retrieved successfully")
	public String postDMToSaleInvoice(@RequestBody @Validated OrderConversionBody body, HttpServletRequest request) {
		String[] ids = body.getIds().split(",");
		List<String> transactionidlist = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			transactionidlist.add(ids[i].trim());
		}

		return customService.postDMToSaleInvoice(request, transactionidlist, body.getVoucherdate());
	}

	/*********************************************************************************
	 * Author : Chetan Channe 
	 * Date : 26/04/2022 
	 * Input : Header = Data for Stock transfer master detail : details for stock transfer detail 
	 * Output : Response depending upon conditions 
	 * Purpose : Stock Transfer Request
	 * Comment : 
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockTransferRequest", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on StockTransferMstBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a StockTransfer  entity has been created successfully")
	public String postStockTransferRequest(@RequestBody @Validated StockTransferMstBody body, HttpServletRequest request)
			throws Exception {
		hRequest = request;
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postStockTransferRequest(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}
	
	

	/******************************************************************************
	 * Author  : Chetan Channe
	 * Date    : 27/07/2022 
	 * Input   : Data for the table Pickeruserlink 
	 * output  : Internal id for Pickeruserlink master and status message 
	 * Purpose : To save Picker user link 
	 * comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postPickerUserlink", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on PickerUserlink entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a PickerUserlink entity has been created successfully")
	public String postPickerUserlink(@RequestBody @Validated List<PickeruserlinkBody> body, HttpServletRequest request) {
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postPickerUserlink(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}
	
	
	/******************************************************************************
	 * Author  : Chetan Channe
	 * Date    : 29/07/2022 
	 * Input   : Data for the table Godownuserlink 
	 * output  : Internal id for Godownuserlink master and status message 
	 * Purpose : To save Godown user link 
	 * comment :
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postGodownUserlink", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Godownuserlink entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Godownuserlink entity has been created successfully")
	public String postgodownuserlink(@RequestBody @Validated List<GodownuserlinkBody> body, HttpServletRequest request) {
		String response = "";
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postgodownuserlink(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else {
			return response;
		}
	}
	
	
	/*********************************************************************************
	 * Author : Chetan Channe 
	 * Date : 03/06/2022 
	 * Input : Header = Data for account posting : details for Ledger master detail detail 
	 * Output : Response depending upon conditions 
	 * Purpose : for account posting 
	 * Comment : 
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postAccountPosting", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on StockTransferMstBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a StockTransfer  entity has been created successfully")
	public String postAccountPosting(@RequestBody @Validated LedgerMstBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postAccountPosting(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}
	
	/*********************************************************************************
	 * Author : Chetan Channe 
	 * Date : 26/04/2022 
	 * Input : Header = Data for Stock transfer master detail : details for stock transfer detail 
	 * Output : Response depending upon conditions 
	 * Purpose : Stock Transfer Inward Request
	 * Comment : 
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postStockTransferInwardRequest", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on StockTransferMstBody entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a StockTransfer  entity has been created successfully")
	public String postStockTransferInwardRequest(@RequestBody @Validated StockTransferMstBody body,
			HttpServletRequest request) {
		String response = "";

		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postStockTransferInwardRequest(body, request);
		}

		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	}
	
	/*********************************************************************************
	 * Author : Chetan Channe 
	 * Date : 26/04/2022 
	 * Input : Header = Data for Stock transfer master detail : details for stock transfer detail 
	 * Output : Response depending upon conditions 
	 * Purpose : Stock Transfer Inward Request
	 * Comment : 
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postProductLocationLink", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on ProductLocationLink entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a StockTransfer  entity has been created successfully")
	public String postProductLocationLink(@RequestBody @Validated List<ProductlocationlinkBody> body,
			HttpServletRequest request) {
		String response = "";
		
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {
			response = customService.postProductLocationLink(body, request);
		}
		
		if (response.equals("Error")) {
			throw new ValidationException(new JSONObject(errormsg).toString());
		} else if (response.equals("Exception")) {
			throw new CustomException("Bad Request");
		} else {
			return response;
		}
	
		
	}
	
	/*********************************************************************************
	 * Author : Kushal Kadu 
	 * Purpose : To get Company order detail list in modify mode with previous qty and free
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/getCompanyOrderModify", produces = "application/json")
	@ApiOperation(value = "Retrieve all columns in respective order with previous data", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if column list has been retrieved successfully")
	public String getCompanyOrderModify(@RequestBody QueryApiBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.add("data", customService.getCompanyOrderModify(body, request));
		
		return response.toString();
	}
	
	/*********************************************************************************
	 * Author : Kushal Kadu 
	 * Purpose : To get Agency wise order detail list in modify mode with previous qty and free
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/getAgencyWiseOrderModify", produces = "application/json")
	@ApiOperation(value = "Retrieve all columns in respective order with previous data", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if column list has been retrieved successfully")
	public String getAgencyWiseOrderModify(@RequestBody QueryApiBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.add("data", customService.getAgencyWiseOrderModify(body, request));
		
		return response.toString();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/time_expired_all", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on all Timeexpired entities", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a Timeexpired entities has been created successfully")
	public String timeExpiredSave(@RequestBody @Validated List<TimeexpiredBody> body, HttpServletRequest request) {
		
		customService.timeExpiredSave(body, request);
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Saved successfully");
		
		return response.toString();
	}
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/ledger_master_detail_by_id/{ledgermasterid}/{vouchertype}", produces = "application/json")
	@ApiOperation(value = "Retrieve a ledgermaster entity with a specified 'ledgermasterid' column value", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a ledgermaster entity has been retrieved successfully")
	public String getLedgermasterByid(@PathVariable(name = "ledgermasterid") String ledgermasterid,
			@PathVariable(name = "vouchertype") String vouchertype, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "", labelquery = "", tablename = "";
		JsonArray jsonArrayColumnListMaster = new JsonArray();
		JsonArray jsonArrayColumnListDetail = new JsonArray();
		switch (vouchertype) {
		case "CR":
		case "BR":
		case "CP":
		case "BP":
		case "JV":
			query = "SELECT * FROM ledgermaster \r\n" + "where  orgid = " + adUserAccessToken.get("orgid")
					+ "      AND oprid = " + adUserAccessToken.get("oprid") + ""
					+ "      AND ledgermasterid = " + ledgermasterid + "" + "   AND  vouchertype = '"
					+ vouchertype + "' ";

			tablename = "'LEDGERMASTER'";
			break;
		}
		
		String response = "";
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonArray datadtl = new JsonArray();

		labelquery = "select  columname field , labelen headers from resourcebundleconfig  where tablename like "
				+ tablename;
		jsonArrayColumnListMaster = utility.executeQueryOnPool(labelquery, request);

		labelquery = "select  columname field , labelen headers from resourcebundleconfig  where tablename like 'ledgerdetail'";
		jsonArrayColumnListDetail = utility.executeQueryOnPool(labelquery, request);
		JsonObject jsonObject = new JsonObject();
		if (data.size() > 0) {
			jsonObject.add("transactionmaster", data);

			if (vouchertype.equals("CR") || vouchertype.equals("BR") || vouchertype.equals("CP") || vouchertype.equals("BP")) {
				query = "SELECT LD.ledgerdetailid,\r\n" + 
						"LD.ledgermasterid,\r\n" + 
						"LD.referenceid,\r\n" + 
						"LD.reversecode,\r\n" + 
						"LD.accountid,\r\n" + 
						"LD.identity,\r\n" + 
						"LD.vouchertype,\r\n" + 
						"LD.voucherseries,\r\n" + 
						"LD.voucherno,\r\n" + 
						"LD.dramt,\r\n" + 
						"LD.cramt,\r\n" + 
						"LD.anar,\r\n" + 
						"(SELECT ifnull(ledgerdetail.dramt, 0)\r\n" + 
						"        FROM ledgerdetail\r\n" + 
						"        WHERE     ledgerdetail.orgid = " + adUserAccessToken.get("orgid") + 
						"              AND ledgerdetail.oprid = " + adUserAccessToken.get("oprid") +  
						"              AND ledgerdetail.ledgermasterid = LD.referenceid\r\n" + 
						"              AND ledgerdetail.vouchertype = 'JV'\r\n" + 
						"              AND voucherseries = 'DS'\r\n" + 
						"              AND ledgerdetail.identity = 1) as legdtdisamt,\r\n" + 
						"             (SELECT account.accountname \r\n" + 
						"					   FROM account\r\n" + 
						"					 INNER JOIN ledgerdetail ON  account.accountid = ledgerdetail.accountid  \r\n" + 
						"		 WHERE     ledgerdetail.orgid = " + adUserAccessToken.get("orgid") +  
						"			 AND ledgerdetail.oprid = " + adUserAccessToken.get("oprid") +  
						"			  AND ledgerdetail.ledgerdetailid = LD.reversecode) \r\n" + 
						"		 legdparentname,\r\n" + 
						"(SELECT ledgerdetail.accountid\r\n" + 
						"	 FROM ledgerdetail\r\n" + 
						" WHERE     ledgerdetail.orgid = " + adUserAccessToken.get("orgid") +  
						"    AND ledgerdetail.oprid = " + adUserAccessToken.get("oprid") +  
						"  AND ledgerdetail.ledgerdetailid = LD.reversecode) \r\n" + 
						" legdtparentid,\r\n" + 
						"LD.anar,\r\n" + 
						"LD.billtype,\r\n" + 
						"LD.billseries,\r\n" + 
						"LD.billno,\r\n" + 
						"LD.billdate,\r\n" + 
						"LD.adjusted,\r\n" + 
						"ACC.accountname,\r\n" + 
						"(CASE\r\n" + 
						"   WHEN LD.adjusted = 1 THEN \"T\"\r\n" + 
						"   WHEN LD.adjusted = 0 THEN \"F\"\r\n" + 
						"END)\r\n" + 
						"  adjustedesc\r\n" + 
						"FROM ledgerdetail LD\r\n" + 
						"     INNER JOIN account ACC ON LD.accountid = ACC.accountid\r\n" + 
						"WHERE     LD.ledgermasterid = " + ledgermasterid + 
						"            AND LD.orgid =" + adUserAccessToken.get("orgid") +
						"            AND LD.oprid =" + adUserAccessToken.get("oprid") +  
						"            AND LD.identity = 1\r\n" + 
						"            AND LD.vouchertype = '"+ vouchertype +"'    \r\n" + 
						"            AND LD.identity not in (9,13,2)\r\n" + 
						"group by LD.accountid,LD.identity,LD.ledgerdetailid";
				datadtl = utility.executeQueryOnPool(query, request);

			} else {
				query = "SELECT ledgerdetail.*, account.accountname "
						+ "FROM ledgerdetail INNER JOIN account ON ledgerdetail.accountid = account.accountid "
						+ "WHERE ledgermasterid = " + ledgermasterid + " AND vouchertype = '" + vouchertype + "' ";
				
				datadtl = utility.executeQueryOnPool(query, request);
			}
			jsonObject.add("transactiondetail", datadtl);
			jsonObject.add("transactionmastercolumnheaders", jsonArrayColumnListMaster);
			jsonObject.add("transactiondetailcolumnheaders", jsonArrayColumnListDetail);

		}
		
		response = jsonObject.toString();
		return response;
	}
	 
}