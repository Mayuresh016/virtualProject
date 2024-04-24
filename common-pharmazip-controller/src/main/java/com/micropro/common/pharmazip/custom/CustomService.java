package com.micropro.common.pharmazip.custom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.config.RedisManager;
import com.micropro.common.pharmazip.entity.MstCreditnoteslabEntity;
import com.micropro.common.pharmazip.entity.TrnMasterEntity;
import com.micropro.common.pharmazip.model.CheckDuplicateValidateModel.CustomCheckDuplicateValidateBody;
import com.micropro.common.pharmazip.model.CustomModel.CustomBody;
import com.micropro.common.pharmazip.model.LedgerMstModel.LedgerMstBody;
import com.micropro.common.pharmazip.model.LedgerMstModel.LedgerdetailBody;
import com.micropro.common.pharmazip.model.LedgerMstModel.LedgermasterBody;
import com.micropro.common.pharmazip.model.LedgerMstModel.PaymentreceiptBody;
import com.micropro.common.pharmazip.model.QueryApiModel.QueryApiBody;
import com.micropro.common.pharmazip.model.generated.GrnMstModel.GrnMstBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.DoctorcustomerlinkBody;
import com.micropro.common.pharmazip.model.generated.MstAgentModel.DoctorproductlinkBody;
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
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StocktransferdetailBody;
import com.micropro.common.pharmazip.model.generated.TimeExpiredModel.TimeexpiredBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactiondetailBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactionmasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterOrderModel.TransactionorderdetailBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterOrderModel.TransactionordermasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterOrderModel.TrnMasterOrderBody;
import com.micropro.common.pharmazip.transaction.TransactionService;
import com.micropro.custom.common.RestUtil;
import com.micropro.custom.services.ExceptionHandlingService.CustomException;

@Service
public class CustomService {

	@Autowired
	private ConnectionUtility utility;
	
	@Autowired
	private TransactionService transactionService;

	@Autowired
	TrnMasterEntity trnMasterEntity;
	
	@Autowired
	MstCreditnoteslabEntity mstCreditnoteslabEntity;

	@Autowired
	private RedisManager redisManager;

	private @Autowired CommonDateFormater formater;

	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : batch id , MRP , Expiry date sale rate and shortname 
	 * Output  : product id and status message 
	 * Purpose : Change MRP and expiry date for the batch 
	 * Comment : batchMrpExpiryChange in batch operations menu
	 ********************************************************************************/
	@Transactional
	public int batchMrpExpiryChange(List<CustomBody> body, HttpServletRequest request) {

		Object[] params = new Object[] {};
		int result = 0;
		for (CustomBody custombody : body) {
			// Update mrp and expiry in the batch master
			String query = "update batch set mrp = ifnull(?,mrp), expirydate = ifnull(?,expirydate) , shortname = ifnull(?, shortname), salerate = ifnull(?,salerate) , trade = ifnull(?, trade) "
					+ " , modifiedby = ?" + " , modifiedon = ? " + " , lastoperation = ?" + " where orgid = ?"
					+ " and oprid = ?" + " and batchid = ?";
			params = new Object[] { custombody.getMrp(), custombody.getExpirydate(), custombody.getShortname(),
					custombody.getSalerate(), custombody.getTrade(), custombody.getModifiedby(),
					custombody.getModifiedon(), custombody.getLastoperation(), custombody.getOrgid(),
					custombody.getOprid(), custombody.getBatchid() };
			result = utility.executeDMLQueryOnPool(query, params, request);

			// Updating the rate details in stock table
			query = "update transactionstock set rateid =?"
					+ ", mrp = ifnull(?,mrp), salerate = ifnull(?,salerate) , trade = ifnull(?, trade) , modifydby = ? "
					+ " , modifyon = ?" + " , lastoperation = ?" + " where orgid = ?" + " and oprid = ?"
					+ "  and batchid = ?";
			params = new Object[] { 
					custombody.getRateid(), 
					custombody.getMrp(), 
					custombody.getSalerate(),
					custombody.getTrade(), 
					custombody.getModifiedby(), 
					custombody.getModifiedon(),
					custombody.getLastoperation(), 
					custombody.getOrgid(), 
					custombody.getOprid(),
					custombody.getBatchid() 
			};
			
			result = transactionService.updateTransactionStock(query, params, request);
		}
		return result;
	}

	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : Batch details 
	 * Output  : Batch  id and status message 
	 * Purpose : Bifurcate batch or transfer the stock into another batch 
	 * Comment : postBatchBifurcation in batch operations menu
	 ********************************************************************************/
	@Transactional
	public long batchBifurcation(CustomBody body, HttpServletRequest request) {
		String batchid = "";
		String query = "select  batchid , pack from batch where orgid = " + body.getOrgid() + " and oprid ="
				+ body.getOprid() + "  and batchno = '" + body.getBatchno() + "'  and productid= "
				+ body.getProductid();
		Object[] params = new Object[] {};
		JsonArray data = utility.executeQueryOnPool(query, request);


		// Generate Voucher number 
	
		 query = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno\r\n"
				+ "FROM transactionmaster\r\n" + "WHERE orgid = " + body.getOrgid()
				+ " AND oprid =" + body.getOprid() + "  AND vouchertype = 'IS'";
		 JsonArray voucherdata = utility.executeQueryOnPool(query, request); 
		 
		if (data.size() > 0) {
			// If Batch exist in batch master just reduce the stock
			query = "update transactionstock set closing = closing - ? where orgid = ?   and oprid =  ?  and batchid =  ?";
			params = new Object[] { body.getQuantity(), body.getOrgid(), body.getOprid(), body.getBatchid() };
			transactionService.updateTransactionStock(query, params, request);

			// Issue Entry
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("orgid", body.getOrgid());
			jsonObject.addProperty("oprid", body.getOprid());

			// Generating master id
			String transactionmasterid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).getString("id");
			
			// Json Object set for transaction master
			jsonObject.addProperty("transactionmasterid", transactionmasterid);
			jsonObject.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObject.addProperty("vouchertype", "IS");
			jsonObject.addProperty("vouchermode", 2);
			jsonObject.addProperty("active", 1);
			jsonObject.addProperty("createdby", body.getCreatedby());
			jsonObject.addProperty("createdon", body.getCreatedon().toString());
			jsonObject.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObject.addProperty("lastoperation", "INSERT");

			// Call for save
			JsonObject jobj = new JsonObject();
			utility.executeCustomDML("transactionmaster", "transactionmasterid", jsonObject, jobj, "INSERT", request);

			// Generating detail id
			String transactiondetailid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(),  "transactiondetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactiondetailid = new JSONObject(transactiondetailid).getString("id");

			// setting to json object for transaction detail
			JsonObject jsonObjectDetail = new JsonObject();
			jsonObjectDetail.addProperty("orgid", body.getOrgid());
			jsonObjectDetail.addProperty("oprid", body.getOprid());
			jsonObjectDetail.addProperty("transactionmasterid", transactionmasterid);
			jsonObjectDetail.addProperty("transactiondetailid", transactiondetailid);
			jsonObjectDetail.addProperty("productid", body.getProductid());
			jsonObjectDetail.addProperty("batchno", body.getOldbatchno());
			jsonObjectDetail.addProperty("batchid", body.getOldbatchid());
			jsonObjectDetail.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObjectDetail.addProperty("vouchertype", "IS");
			jsonObjectDetail.addProperty("vouchermode", 2);
			jsonObjectDetail.addProperty("active", 1);
			jsonObjectDetail.addProperty("createdby", body.getCreatedby());
			jsonObjectDetail.addProperty("createdon", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("lastoperation", "INSERT");
			jsonObjectDetail.addProperty("qty", body.getQuantity());
			
			// Bifurcation flag 1-issue 2-Receipt

			jsonObjectDetail.addProperty("batchbifurcationflag", 1);
			JsonObject jobjdtl = new JsonObject();
			// Issue Entry
			utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonObjectDetail, jobjdtl, "INSERT",
					request);

			// Increasing the stock in the bifurcated batch
			query = "update transactionstock set closing = closing + ?  where orgid = ?   and oprid = ? and batchid = ?";
			params = new Object[] { body.getQuantity(), body.getOrgid(), body.getOprid(),
					data.get(0).getAsJsonObject().get("batchid") };
			transactionService.updateTransactionStock(query, params, request);

			// Receipt
			jsonObject = new JsonObject();
			jsonObject.addProperty("orgid", body.getOrgid());
			jsonObject.addProperty("oprid", body.getOprid());

			// Generating transaction master id
			transactionmasterid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).getString("id");

			// Json object for transaction master
			jsonObject.addProperty("transactionmasterid", transactionmasterid);
			jsonObject.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObject.addProperty("vouchertype", "IS");
			jsonObject.addProperty("vouchermode", 2);
			jsonObject.addProperty("active", 1);
			jsonObject.addProperty("createdby", body.getCreatedby());
			jsonObject.addProperty("createdon", body.getCreatedon().toString());
			jsonObject.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObject.addProperty("lastoperation", "INSERT");

			// Insert for transaction master
			jobj = new JsonObject();
			utility.executeCustomDML("transactionmaster", "transactionmasterid", jsonObject, jobj, "INSERT", request);

			// Generating detail id
			transactiondetailid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(),  "transactiondetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactiondetailid = new JSONObject(transactiondetailid).getString("id");

			// Json object transaction detail
			jsonObjectDetail = new JsonObject();
			jsonObjectDetail.addProperty("orgid", body.getOrgid());
			jsonObjectDetail.addProperty("oprid", body.getOprid());
			jsonObjectDetail.addProperty("transactionmasterid", transactionmasterid);
			jsonObjectDetail.addProperty("transactiondetailid", transactiondetailid);
			jsonObjectDetail.addProperty("productid", body.getProductid());
			jsonObjectDetail.addProperty("batchno", body.getBatchno());
			jsonObjectDetail.addProperty("batchid", data.get(0).getAsJsonObject().get("batchid").toString());
			jsonObjectDetail.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObjectDetail.addProperty("vouchertype", "IS");
			jsonObjectDetail.addProperty("vouchermode", 2);
			jsonObjectDetail.addProperty("active", 1);
			jsonObjectDetail.addProperty("createdby", body.getCreatedby());
			jsonObjectDetail.addProperty("createdon", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("lastoperation", "INSERT");
			jsonObjectDetail.addProperty("qty", body.getQuantity());
			// Bifurcation flag 1-issue 2-Receipt
			jsonObjectDetail.addProperty("batchbifurcationflag", 2);
			jobjdtl = new JsonObject();

			// Insert to transaction detail
			utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonObjectDetail, jobjdtl, "INSERT",
					request);
			batchid = data.get(0).getAsJsonObject().get("batchid").toString();

		} else {

			// Fetch the data for old batches
			// If the bifurcation is to be done to new batch
			query = "select  batchid , pack , mfgdate , expirydate , displaypacking , pack , mrp , salerate , purchaserate , trade from batch where orgid = "
					+ body.getOrgid() + " and oprid =" + body.getOprid() + "  and batchno = '" + body.getOldbatchno()
					+ "'  and productid= " + body.getProductid();
			data = utility.executeQueryOnPool(query, request);

			// Insert new batch in batch master
			JsonObject batchmaster = new JsonObject();
			batchmaster.addProperty("orgid", body.getOrgid());
			batchmaster.addProperty("oprid", body.getOprid());
			batchmaster.addProperty("productid", body.getProductid());
			batchmaster.addProperty("batchno", body.getBatchno());

			// Generating the batchid
			batchid = utility.executeIdGenerationProcedure(body.getOrgid().toString(), body.getOprid().toString(),
					request.getHeader("rightId"), request.getHeader("authorization").split(" ")[1],
					request.getRequestURL().toString());
			batchid = new JSONObject(batchid).getString("id");

			batchmaster.addProperty("batchid", batchid);

			if (!(body.getExpirydate() == null)) {
				batchmaster.addProperty("expirydate", body.getExpirydate().toString());
			}

			batchmaster.addProperty("displaypacking",
					data.get(0).getAsJsonObject().get("displaypacking").getAsString());
			batchmaster.addProperty("pack", data.get(0).getAsJsonObject().get("pack").toString());
			batchmaster.addProperty("mrp", data.get(0).getAsJsonObject().get("mrp").toString());
			batchmaster.addProperty("salerate", data.get(0).getAsJsonObject().get("salerate").toString());
			batchmaster.addProperty("purchaserate", data.get(0).getAsJsonObject().get("purchaserate").toString());
			batchmaster.addProperty("trade", data.get(0).getAsJsonObject().get("trade").toString());
			batchmaster.addProperty("createdby", body.getCreatedby());
			batchmaster.addProperty("createdon", body.getCreatedon().toString());
			batchmaster.addProperty("lastoperation", "INSERT");
			batchmaster.addProperty("active", 1);

			// Insert to batch master
			JsonObject jobj = new JsonObject();
			utility.executeCustomDML("batch", "batchid", batchmaster, jobj,
					batchmaster.get("lastoperation").getAsString(), request);

			// Reduce the stock from old batch
			query = "update transactionstock set closing = closing - ?  where orgid = ? and oprid = ?  and batchid = ? ";
			params = new Object[] { body.getQuantity(), body.getOrgid(), body.getOprid(),
					data.get(0).getAsJsonObject().get("batchid").toString() };
			transactionService.updateTransactionStock(query, params, request);

			// issue entry
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("orgid", body.getOrgid());
			jsonObject.addProperty("oprid", body.getOprid());

			// Generating transaction masterid
			String transactionmasterid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).getString("id");

			// Json object for transaction master
			jsonObject.addProperty("transactionmasterid", transactionmasterid);
			jsonObject.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObject.addProperty("vouchertype", "IS");
			jsonObject.addProperty("vouchermode", 2);
			jsonObject.addProperty("active", 1);
			jsonObject.addProperty("createdby", body.getCreatedby());
			jsonObject.addProperty("createdon", body.getCreatedon().toString());
			jsonObject.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObject.addProperty("lastoperation", "INSERT");

			// Insert to transaction master
			JsonObject jobjWhere = new JsonObject();
			utility.executeCustomDML("transactionmaster", "transactionmasterid", jsonObject, jobjWhere, "INSERT",
					request);

			// Generate id for transaction detail
			String transactiondetailid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(), "transactiondetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactiondetailid = new JSONObject(transactiondetailid).getString("id");

			// Json Object for transaction detail
			JsonObject jsonObjectDetail = new JsonObject();
			jsonObjectDetail.addProperty("orgid", body.getOrgid());
			jsonObjectDetail.addProperty("oprid", body.getOprid());
			jsonObjectDetail.addProperty("transactionmasterid", transactionmasterid);
			jsonObjectDetail.addProperty("transactiondetailid", transactiondetailid);
			jsonObjectDetail.addProperty("productid", body.getProductid());
			jsonObjectDetail.addProperty("batchno", body.getOldbatchno());
			jsonObjectDetail.addProperty("batchid", data.get(0).getAsJsonObject().get("batchid").toString());
			jsonObjectDetail.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
			jsonObjectDetail.addProperty("vouchertype", "IS");
			jsonObjectDetail.addProperty("vouchermode", 2);
			jsonObjectDetail.addProperty("active", 1);
			jsonObjectDetail.addProperty("createdby", body.getCreatedby());
			jsonObjectDetail.addProperty("createdon", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("voucherdate", body.getCreatedon().toString());
			jsonObjectDetail.addProperty("lastoperation", "INSERT");
			jsonObjectDetail.addProperty("qty", body.getQuantity());
			jsonObjectDetail.addProperty("batchbifurcationflag", 1); // issue entry

			// Insert into transaction detail
			JsonObject jobjdtl = new JsonObject();
			utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonObjectDetail, jobjdtl, "INSERT",
					request);

			// Insert the details in transaction stock
			query = "select  rateid from transactionstock where orgid =" + body.getOrgid() + " and oprid ="
					+ body.getOprid() + " and batchid =" + body.getOldbatchid() + " limit 1";
			JsonArray datarate = utility.executeQueryOnPool(query, request);
			if (datarate.size() > 0) {
				JsonObject transactionstock = new JsonObject();
				transactionstock.addProperty("orgid", body.getOrgid());
				transactionstock.addProperty("oprid", body.getOprid());
				// Generation stockid
				String stockid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
						body.getOprid().toString(), request.getHeader("rightId"),
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				stockid = new JSONObject(stockid).getString("id");

				// Json Object for transaction stock
				transactionstock.addProperty("transactionstockid", stockid);
				transactionstock.addProperty("productid", body.getProductid());
				transactionstock.addProperty("batchid", batchid);
				transactionstock.addProperty("rateid", datarate.get(0).getAsJsonObject().get("rateid").toString());
				transactionstock.addProperty("mrp", data.get(0).getAsJsonObject().get("mrp").toString());
				transactionstock.addProperty("trade", data.get(0).getAsJsonObject().get("trade").toString());
				transactionstock.addProperty("salerate", data.get(0).getAsJsonObject().get("salerate").toString());
				transactionstock.addProperty("purchaserate",
						data.get(0).getAsJsonObject().get("purchaserate").toString());
				transactionstock.addProperty("quantity", body.getQuantity());
				transactionstock.addProperty("new", 0);
				transactionstock.addProperty("active", 1);
				transactionstock.addProperty("createdby", body.getCreatedby());
				transactionstock.addProperty("createdon", body.getCreatedon().toString());
				transactionstock.addProperty("lastoperation", "INSERT");

				// insert in transaction stock
				utility.executeCustomDML("transactionstock", "transactionstockid", transactionstock, jobj,
						batchmaster.get("lastoperation").getAsString(), request);

				// receipt entry
				jsonObject = new JsonObject();
				jsonObject.addProperty("orgid", body.getOrgid());
				jsonObject.addProperty("oprid", body.getOprid());

				// Generating masterid
				transactionmasterid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
						body.getOprid().toString(),  "transactionmaster",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactionmasterid = new JSONObject(transactionmasterid).getString("id");
				
				// Json object for transaction master
				jsonObject.addProperty("transactionmasterid", transactionmasterid);
				jsonObject.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
				jsonObject.addProperty("vouchertype", "IS");
				jsonObject.addProperty("vouchermode", 2);
				jsonObject.addProperty("active", 1);
				jsonObject.addProperty("createdby", body.getCreatedby());
				jsonObject.addProperty("createdon", body.getCreatedon().toString());
				jsonObject.addProperty("voucherdate", body.getCreatedon().toString());
				jsonObject.addProperty("lastoperation", "INSERT");

				// Insert into transaction master
				jobjWhere = new JsonObject();
				utility.executeCustomDML("transactionmaster", "transactionmasterid", jsonObject, jobjWhere, "INSERT",
						request);

				// Generating the detailid
				transactiondetailid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
						body.getOprid().toString(),  "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).getString("id");

				// Json Object for transaction detail
				jsonObjectDetail = new JsonObject();
				jsonObjectDetail.addProperty("orgid", body.getOrgid());
				jsonObjectDetail.addProperty("oprid", body.getOprid());
				jsonObjectDetail.addProperty("transactionmasterid", transactionmasterid);
				jsonObjectDetail.addProperty("transactiondetailid", transactiondetailid);
				jsonObjectDetail.addProperty("productid", body.getProductid());
				jsonObjectDetail.addProperty("batchno", body.getBatchno());
				jsonObjectDetail.addProperty("batchid", batchid);
				jsonObjectDetail.add("voucherno", voucherdata.get(0).getAsJsonObject().get("voucherno"));
				jsonObjectDetail.addProperty("vouchertype", "IS");
				jsonObjectDetail.addProperty("vouchermode", 2);
				jsonObjectDetail.addProperty("active", 1);
				jsonObjectDetail.addProperty("createdby", body.getCreatedby());
				jsonObjectDetail.addProperty("createdon", body.getCreatedon().toString());
				jsonObjectDetail.addProperty("voucherdate", body.getCreatedon().toString());
				jsonObjectDetail.addProperty("lastoperation", "INSERT");
				jsonObjectDetail.addProperty("qty", body.getQuantity());
				jsonObjectDetail.addProperty("batchbifurcationflag", 2);
				jobjdtl = new JsonObject();
				// Insert into transaction detail
				utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonObjectDetail, jobjdtl,
						"INSERT", request);
			}

		}
		return Long.parseLong(batchid);
	}
	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : Header = Data for doctor Detail = doctorproductlink= Data for product , doctorcustomerlink = Data for customer 
	 * Output  : Doctor id and status message 
	 * Purpose : Allocate the doctor to the customer and to the product 
	 * Comment : postDoctorCustProdSetup in masters menu
	 ********************************************************************************/
	public int postDoctorCustProdSetup(MstAgentBody body, HttpServletRequest request) {
		// CRUD operation
		Object[] params = new Object[] {};
		String query = "";
		int result = 0;

		// Delete the records for given doctor in doctorcustomerlink
		if (body.getAgent().getDoctorcustomerlink().size() > 0) {
			query = "delete from doctorcustomerlink where orgid =?  and oprid =? and agentid = ?";
			params = new Object[] { body.getAgent().getOrgid(), body.getAgent().getOprid(),
					body.getAgent().getAgentid() };
			result = utility.executeDMLQueryOnPool(query, params, request);
		}

		// Delete the records for given doctor in doctorProductlink
		if (body.getAgent().getDoctorproductlink().size() > 0) {
			query = "delete from doctorproductlink where orgid =? and  oprid = ? and and agentid= ?";
			params = new Object[] { body.getAgent().getOrgid(), body.getAgent().getOprid(),
					body.getAgent().getAgentid() };
			result = utility.executeDMLQueryOnPool(query, params, request);
		}

		// Append for bulk insert product
		String productQuery = "", customerQuery = "";
		if (body.getAgent().getDoctorproductlink().size() > 0) {
			for (DoctorproductlinkBody doctorproductlinkBody : body.getAgent().getDoctorproductlink()) {
				if (doctorproductlinkBody.toString() != null) {
					productQuery = productQuery + "(" + doctorproductlinkBody.getOrgid() + ","
							+ doctorproductlinkBody.getOprid() + "," + doctorproductlinkBody.getAgentid() + ","
							+ doctorproductlinkBody.getProductid() + "," + doctorproductlinkBody.getCreatedby()
							+ " , CURDATE() , 'INSERT'" + "),";
				}
			}
		}
		// Append for bulk insert customer
		if (body.getAgent().getDoctorcustomerlink().size() > 0) {
			for (DoctorcustomerlinkBody doctorcustomerlinkBody : body.getAgent().getDoctorcustomerlink()) {
				if (doctorcustomerlinkBody.toString() != null) {
					customerQuery = customerQuery + "(" + doctorcustomerlinkBody.getOrgid() + ","
							+ doctorcustomerlinkBody.getOprid() + "," + doctorcustomerlinkBody.getAgentid() + ","
							+ doctorcustomerlinkBody.getAccountid() + "," + doctorcustomerlinkBody.getCreatedby()
							+ " ,CURDATE() , 'INSERT'" + "),";
				}
			}
		}

		// Bulk insert in doctorproductlink
		if (body.getAgent().getDoctorproductlink().size() > 0) {
			productQuery = productQuery.substring(0, productQuery.length() - 1);
			params = new Object[] {};
			query = "insert into doctorproductlink (\n" + "   orgid\n" + "  ,oprid\n" + "  ,agentid\n"
					+ "  ,productid\n" + "  ,createdby\n" + "  ,createdon\n" + "  ,lastoperation\n" + ") VALUES"
					+ productQuery;
			result = utility.executeDMLQueryOnPool(query, params, request);
		}

		// Bulk insert in doctorcustomerlink
		if (body.getAgent().getDoctorcustomerlink().size() > 0) {
			customerQuery = customerQuery.substring(0, customerQuery.length() - 1);
			params = new Object[] {};
			query = "insert into doctorcustomerlink (\n" + "   orgid\n" + "  ,oprid\n" + "  ,agentid\n"
					+ "  ,accountid\n" + "  ,createdby\n" + "  ,createdon\n" + "  ,lastoperation\n" + ") VALUES"
					+ customerQuery;
			result = utility.executeDMLQueryOnPool(query, params, request);
		}
		return result;
	}

	// Stock reduce operations in replacement
	public int postCreditNoteReplacementInsert(TrnMasterBody body, HttpServletRequest request) throws Exception {
		int update = 0;
		for (TransactiondetailBody trnDetailBody : body.getTransactionmaster().getTransactiondetail()) {
			// Reduce the stock in transactionstock
			String query = "update transactionstock set closing = closing - ? where  orgid = ?  and oprid = ?  and  batchid = ?";
			Object[] params = new Object[] { trnDetailBody.getQty(), trnDetailBody.getOprid(), trnDetailBody.getOprid(),
					trnDetailBody.getBatchid() };
			update = transactionService.updateTransactionStock(query, params, request);
		}
		return update;
	}

	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 07/01/2022 
	 * Input   : Header = Data for  transaction master  detail : details for transaction detail 
	 * Output  : Response depending upon conditions  
	 * Purpose : credit note replacement update conditions 
	 * Comment : postCreditNoteReplacementUpdate
	 ********************************************************************************/
	public String postCreditNoteReplacementUpdate(TrnMasterBody body, HttpServletRequest request) {
		String lastoperationDetail = "", response = "";
		Object[] params = new Object[] {};

		int flag = 0;
		for (TransactiondetailBody trnDetailBody : body.getTransactionmaster().getTransactiondetail()) {
			lastoperationDetail = trnDetailBody.getLastoperation();

			switch (lastoperationDetail) {
			case "INSERT":
				String query = "update transactionstock set closing =  closing - ?  where  orgid = ?   and oprid = ?  and  batchid = ?";
				params = new Object[] { trnDetailBody.getQty(), trnDetailBody.getOrgid(), trnDetailBody.getOprid(),
						trnDetailBody.getBatchid() };
				transactionService.updateTransactionStock(query, params, request);

				break;
			case "UPDATE":
				// old qty revert
				query = "SELECT  qty , batchid " + "FROM transactiondetail\r\n" + "WHERE     orgid = "
						+ trnDetailBody.getOrgid() + "      AND oprid = " + trnDetailBody.getOprid()
						+ "      AND transactionmasterid = " + trnDetailBody.getTransactionmasterid()
						+ "      AND transactiondetailid = " + trnDetailBody.getTransactiondetailid();

				JsonArray data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					JsonObject obj = data.get(0).getAsJsonObject();
					query = "update transactionstock set closing = ifnull(closing,0) + ? where  orgid = ? and oprid = ? and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsBigDecimal(),
							trnDetailBody.getOrgid(), 
							trnDetailBody.getOprid(),
							obj.get("batchid").getAsBigDecimal() 
					};
					
					transactionService.updateTransactionStock(query, params, request);

					// delete revious details
					Integer voucherNoInt = body.getTransactionmaster().getVoucherno();
					if (trnDetailBody.getVoucherno().isEmpty() || (trnDetailBody.getVoucherno() == "0")
							|| voucherNoInt == 0) {
						query = "delete from transactiondetail where  orgid = ? AND oprid = ?   AND transactionmasterid = ?   AND transactiondetailid = ?";
						params = new Object[] { trnDetailBody.getOrgid(), trnDetailBody.getOprid(),
								trnDetailBody.getTransactionmasterid(), trnDetailBody.getTransactiondetailid() };
						utility.executeDMLQueryOnPool(query, params, request);
					} else {
						JsonObject transactionDetailWhere = new JsonObject();
						transactionDetailWhere.addProperty("orgid", trnDetailBody.getOrgid());
						transactionDetailWhere.addProperty("oprid", trnDetailBody.getOprid());
						transactionDetailWhere.addProperty("transactionmasterid",
								trnDetailBody.getTransactionmasterid());
						transactionDetailWhere.addProperty("transactiondetailid",
								trnDetailBody.getTransactiondetailid());

						JsonObject transactionDetail = new JsonObject();
						transactionDetail.addProperty("active", 0);
						utility.executeCustomDML("transactiondetail", "transactiondetailid", transactionDetail,
								transactionDetailWhere, "UPDATE", request);
					}
					// new Stock
					query = "update transactionstock set closing =  closing - ? where  orgid = ?    and oprid = ?  and  batchid = ?";
					params = new Object[] { trnDetailBody.getQty(), trnDetailBody.getOrgid(), trnDetailBody.getOprid(),
							trnDetailBody.getBatchid() };
					transactionService.updateTransactionStock(query, params, request);
				}
				break;
			case "DELETE":
				Integer voucherNoInt = body.getTransactionmaster().getVoucherno();
				if (trnDetailBody.getVoucherno().isEmpty() || (trnDetailBody.getVoucherno() == "0")
						|| voucherNoInt == 0) {
					query = "delete from transactiondetail where  orgid =? AND oprid = ?   AND transactionmasterid = ?   AND transactiondetailid = ?";
					params = new Object[] { trnDetailBody.getOrgid(), trnDetailBody.getOprid(),
							trnDetailBody.getTransactionmasterid(), trnDetailBody.getTransactiondetailid() };
					utility.executeDMLQueryOnPool(query, params, request);

					query = "SELECT count(*)count from transactiondetail where transactiondetail.orgid ="
							+ trnDetailBody.getOrgid() + " AND transactiondetail.oprid =" + trnDetailBody.getOprid()
							+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
					data = utility.executeQueryOnPool(query, request);
					// If Master is not present in Details then delete Master
					if (data.get(0).getAsJsonObject().get("count").getAsInt() == 0) {
						query = "delete from transactionmaster where orgid =?  and oprid = ? and transactionmasterid = ?";
						params = new Object[] { body.getTransactionmaster().getOrgid(),
								body.getTransactionmaster().getOprid(),
								body.getTransactionmaster().getTransactionmasterid() };
						utility.executeDMLQueryOnPool(query, params, request);
					}

				} else {
					JsonObject transactionDetailWhere = new JsonObject();
					transactionDetailWhere.addProperty("orgid", trnDetailBody.getOrgid());
					transactionDetailWhere.addProperty("oprid", trnDetailBody.getOprid());
					transactionDetailWhere.addProperty("transactionmasterid", trnDetailBody.getTransactionmasterid());
					transactionDetailWhere.addProperty("transactiondetailid", trnDetailBody.getTransactiondetailid());

					JsonObject transactionDetail = new JsonObject();
					transactionDetail.addProperty("active", 0);
					utility.executeCustomDML("transactiondetail", "transactiondetailid", transactionDetail,
							transactionDetailWhere, "UPDATE", request);
				}

				// new Stock
				query = "update transactionstock set closing =  closing + ?  where  orgid = ?   and oprid = ?  and  batchid = ?";
				params = new Object[] { trnDetailBody.getQty(), trnDetailBody.getOrgid(), trnDetailBody.getOprid(),
						trnDetailBody.getBatchid() };
				transactionService.updateTransactionStock(query, params, request);
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			Gson gson = new Gson();
			String transactionDetailString = gson.toJson(body.getTransactionmaster().getTransactiondetail());
			JsonArray transactionDetailJsonObject = new Gson().fromJson(transactionDetailString, JsonArray.class);

			JsonObject jsonobject = transactionDetailJsonObject.get(0).getAsJsonObject();
			jsonobject.remove("createdon");
			jsonobject.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
			jsonobject.addProperty("lastoperation", "INSERT");
			jsonobject.remove("expiry");

			if (body.getTransactionmaster().getTransactiondetail().get(0).getExpiry() != null) {
				jsonobject.addProperty("expiry",
						body.getTransactionmaster().getTransactiondetail().get(0).getExpiry().toString());
			}

			if (body.getTransactionmaster().getVoucherdate() != null) {
				jsonobject.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
			}
			Integer voucherNoInt = body.getTransactionmaster().getVoucherno();
			if (voucherNoInt == 0) {
				jsonobject.addProperty("tnew", 0);
				jsonobject.addProperty("active", 0);
			} else {
				jsonobject.addProperty("tnew", 1);
				jsonobject.addProperty("active", 1);
			}
			String transactionDetailId = utility.executeIdGenerationProcedure(
					body.getTransactionmaster().getOrgid().toString(),
					body.getTransactionmaster().getOprid().toString(), "transactiondetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			jsonobject.addProperty("transactiondetailid", new JSONObject(transactionDetailId).getString("id"));

			JsonObject jobj = new JsonObject();
			utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonobject, jobj, "INSERT", request);

			String sql = "select * from transactiondetail where transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			JsonArray data = utility.executeQueryOnPool(sql, request);

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			jsonObject.addProperty("message", "Record Saved Successfully ");
			jsonObject.add("data", data);
			response = jsonObject.toString();

		} else {
			String sql = "select * from transactiondetail where transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			JsonArray data = utility.executeQueryOnPool(sql, request);

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			jsonObject.addProperty("message", "Record Saved Successfully ");
			jsonObject.add("data", data);
			response = jsonObject.toString();
		}
		return response;
	}

	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : master id and last operation
	 * Output  : Response depending upon conditions  
	 * Purpose : credit note replacement Escape conditions 
	 * Comment : postCreditNoteReplacementEscape
	 ********************************************************************************/
	// Moved to transaction service
	public String postCreditNoteReplacementEscape(TrnMasterBody body, HttpServletRequest request) {
		String query = "";
		Object[] params = new Object[] {};
		int voucherNoInt = body.getTransactionmaster().getVoucherno();
		// Escape condition before final save
		if (voucherNoInt == 0) {
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ "  and active  = 0";
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Revert the stock
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing + (? + ? + ?) where  orgid = ?    and oprid = ?    and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);

					// Delete from transaction detail
					query = "delete from transactiondetail where orgid=? and oprid= ? and transactiondetailid = ?";
					params = new Object[] { body.getTransactionmaster().getOrgid(),
							body.getTransactionmaster().getOprid(),
							new BigDecimal(jsonElement.getAsJsonObject().get("transactiondetailid").toString()) };
					utility.executeDMLQueryOnPool(query, params, request);

				}
			}
		} else {
			// Escape condition after final save
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ " and tnew = 1" + " and active = 1";
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Revert the stock with new=1
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing + (? + ? + ?)  where  orgid = ?   and oprid = ?   and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);

				}
			}

			// delete from transaction details
			query = "delete from transactiondetail where tnew = 1 and orgid= ? and oprid= ?";
			params = new Object[] { body.getTransactionmaster().getOrgid(),
					body.getTransactionmaster().getOprid()};
			utility.executeDMLQueryOnPool(query, params, request);
			
			// Reactivating the deleted items
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ " and active = 0";
			data = utility.executeQueryOnPool(query, request);

			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Reducing the stock of deleted elements
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing - (? + ? + ?)  where  orgid = ?  and oprid = ?  and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);
				}
			}

		}
		if ( voucherNoInt == 0) {
			query = "delete from  transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			params = new Object[] {};
			utility.executeDMLQueryOnPool(query, params, request);
		} else {
			JsonObject transactiondetail = new JsonObject();
			transactiondetail.addProperty("active", 1);
			JsonObject transactiondetailwhere = new JsonObject();

			transactiondetailwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
			transactiondetailwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
			transactiondetailwhere.addProperty("transactionmasterid",
					body.getTransactionmaster().getTransactionmasterid());
			transactiondetailwhere.addProperty("tnew", 0);
			utility.executeCustomDML("transactiondetail", "transactiondetail", transactiondetail,
					transactiondetailwhere, "UPDATE", request);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObject.addProperty("message", "Record Saved Successfully");
		jsonObject.toString();
		return jsonObject.toString();
	}

	
	/*********************************************************************************
	 * Author  : Chetan Channe 
	 * Date    : 13/06/2022 
	 * Input   : master id and last operation
	 * Output  : Response depending upon conditions  
	 * Purpose : Gst credit note Escape conditions 
	 * Comment : postGstCreditNoteEscape
	 ********************************************************************************/
	public String postGstCreditNoteEscape(TrnMasterBody body, HttpServletRequest request) {
		String query = "";
		Object[] params = new Object[] {};
		int voucherNoInt = body.getTransactionmaster().getVoucherno();
		// Escape condition before final save
		if (voucherNoInt == 0) {
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ "  and active  = 0";
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Revert the stock (-)
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing - (? + ? + ?) where  orgid = ?    and oprid = ?    and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);

					// Delete from transaction detail
					query = "delete from transactiondetail where orgid=? and oprid= ? and transactiondetailid = ?";
					params = new Object[] { body.getTransactionmaster().getOrgid(),
							body.getTransactionmaster().getOprid(),
							new BigDecimal(jsonElement.getAsJsonObject().get("transactiondetailid").toString()) };
					utility.executeDMLQueryOnPool(query, params, request);

				}
			}
		} else {
			// Escape condition after final save
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ " and active = 1" + " and tnew = 1";
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Revert the stock with active =1 and tnew = 1 (-)
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing - (? + ? + ?)  where  orgid = ?   and oprid = ?   and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);

				}
			}

			// delete from transaction details with t-new 1 
			query = "delete from transactiondetail where  tnew = 1 and orgid= ? and oprid= ?";
			params = new Object[] { body.getTransactionmaster().getOrgid(),
					body.getTransactionmaster().getOprid()};
			utility.executeDMLQueryOnPool(query, params, request);
			
			// Reactivating the deleted items
			query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
					+ " and active = 0";
			data = utility.executeQueryOnPool(query, request);

			if (data.size() > 0) {
				for (JsonElement jsonElement : data.getAsJsonArray()) {
					// Reducing the stock of deleted elements (+)
					JsonObject obj = jsonElement.getAsJsonObject();
					query = "update transactionstock set closing =  closing + (? + ? + ?)  where  orgid = ?  and oprid = ?  and  batchid = ?";
					params = new Object[] { 
							obj.get("qty").getAsLong(),
							obj.get("free").getAsLong(),
							obj.get("specialqty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);
				}
			}

		}
		if ( voucherNoInt == 0) {
			query = "delete from  transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			params = new Object[] {};
			utility.executeDMLQueryOnPool(query, params, request);
		} else {
			JsonObject transactiondetail = new JsonObject();
			transactiondetail.addProperty("active", 1);
			JsonObject transactiondetailwhere = new JsonObject();

			transactiondetailwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
			transactiondetailwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
			transactiondetailwhere.addProperty("transactionmasterid",
					body.getTransactionmaster().getTransactionmasterid());
			transactiondetailwhere.addProperty("tnew", 0);
			utility.executeCustomDML("transactiondetail", "transactiondetail", transactiondetail,
					transactiondetailwhere, "UPDATE", request);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObject.addProperty("message", "Record Saved Successfully");
		jsonObject.toString();
		return jsonObject.toString();
	}
	
	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : master id and last operation as FINAL SAVE and master fields to be updated
	 * Output  : Voucher no in data   
	 * Purpose : credit note replacement Final save  conditions 
	 * Comment : postCreditNoteReplacementFinalSave
	 ********************************************************************************/
	public String postCreditNoteReplacementFinalSave(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String query = "", response = "";
		String voucherno = "";

		TransactionmasterBody trnMaster = body.getTransactionmaster();
		Integer voucherNoInt = trnMaster.getVoucherno();

		if ((voucherNoInt == 0)) {
//			query = "SELECT (max(ifnull(cast(voucherno AS unsigned), 0)) + 1) voucherno \n" + "FROM transactionmaster "
//					+ "WHERE orgid = " + body.getTransactionmaster().getOrgid() + " AND oprid ="
//					+ body.getTransactionmaster().getOprid() + "  AND vouchertype = 'RP' AND active = 1";
			
			voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
			trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), "0", 
			request.getHeader("rightId"), token, request.getRequestURL().toString());
	
			voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
		} else {
			query = "select  voucherno from transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid();
			
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				voucherno = data.get(0).getAsJsonObject().get("voucherno").getAsString();
			} else {
				voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(),
						trnMaster.getOprid().toString(), trnMaster.getDoctypeid().toString(),
						trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(),
						trnMaster.getAgencyid().toString(), request.getHeader("rightId"), token,
						request.getRequestURL().toString());

				voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
			}
		}
		
		Object[] params = new Object[] {};
		// update in transaction master
		JsonObject transactionmaster = new JsonObject();
		transactionmaster.addProperty("voucherno", voucherno);
		transactionmaster.addProperty("vouchermode", 2);
		transactionmaster.addProperty("active", 1);
		transactionmaster.addProperty("netamount", body.getTransactionmaster().getNetamount());
		if (!body.getTransactionmaster().getVoucherdate().toString().isEmpty()) {
			transactionmaster.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
		}

		JsonObject transactionmasterwhere = new JsonObject();
		transactionmasterwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
		transactionmasterwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
		transactionmasterwhere.addProperty("transactionmasterid", body.getTransactionmaster().getTransactionmasterid());

		utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, transactionmasterwhere,
				"UPDATE", request);

		// update in transaction detail

		JsonObject transactiondetail = new JsonObject();
		transactiondetail.addProperty("voucherno", voucherno);
		transactiondetail.addProperty("vouchermode", 2);
		transactiondetail.addProperty("active", 1);
		transactiondetail.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());

		JsonObject transactiondetailwhere = new JsonObject();
		transactiondetailwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
		transactiondetailwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
		transactiondetailwhere.addProperty("transactionmasterid", body.getTransactionmaster().getTransactionmasterid());

		utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, transactiondetailwhere,
				"UPDATE", request);
		JsonObject jsonObjectFinalSave = new JsonObject();
		jsonObjectFinalSave.addProperty("code", 200);
		jsonObjectFinalSave.addProperty("status", "Success");
		jsonObjectFinalSave.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObjectFinalSave.addProperty("message", "Record Saved Successfully ");
		jsonObjectFinalSave.addProperty("data", voucherno);
		response = jsonObjectFinalSave.toString();

		// logic for updating the replacement amount in credit note bills
		if (!(body.getTransactionmaster().getReferenceid().toString().isEmpty()
				|| body.getTransactionmaster().getReferenceid() == 0)) {

			if (body.getTransactionmaster().getDescription().equals("0")
					|| body.getTransactionmaster().getDescription() == null) {
				if (body.getTransactionmaster().getNetamount().compareTo(BigDecimal.ZERO) == 0) {
					query = "update transactionmaster set replacementamount = (ifnull(replacementamount,0)+"
							+ body.getTransactionmaster().getNetamount() + ") where orgid ="
							+ body.getTransactionmaster().getOrgid() + " and oprid ="
							+ body.getTransactionmaster().getOprid() + " and transactionmasterid = "
							+ body.getTransactionmaster().getReferenceid();
					utility.executeDMLQueryOnPool(query, params, request);
				}
			} else {
				if (body.getTransactionmaster().getNetamount().compareTo(BigDecimal.ZERO) == 0) {
					BigDecimal oldnetAmount = new BigDecimal(body.getTransactionmaster().getDescription());
					BigDecimal newNetAmount = body.getTransactionmaster().getNetamount();
					BigDecimal difference = BigDecimal.ZERO;
					difference = newNetAmount.subtract(oldnetAmount);
					query = "update transactionmaster set replacementamount = (ifnull(replacementamount,0)+"
							+ difference + ") where orgid =" + body.getTransactionmaster().getOrgid() + " and oprid ="
							+ body.getTransactionmaster().getOprid() + " and transactionmasterid = "
							+ body.getTransactionmaster().getReferenceid();
					utility.executeDMLQueryOnPool(query, params, request);
				}

			}

		}

		return response;
	}

	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : master id and last operation as CANCEL 
	 * Output  : Status message   
	 * Purpose : credit note replacement CANCEL  conditions 
	 * Comment : postCreditNoteReplacementCancel
	 ********************************************************************************/
	public String postCreditNoteReplacementCancel(TrnMasterBody body, HttpServletRequest request) {
		Object[] params = new Object[] {};
	// Selecting the transaction details depending upon master id
		String query = "select transactiondetailid , batchid ,  qty , productpack  from transactiondetail where orgid ="
				+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
				+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
		JsonArray data = utility.executeQueryOnPool(query, request);
		if (data.size() > 0) {
			for (JsonElement jsonElement : data.getAsJsonArray()) {
				JsonObject obj = jsonElement.getAsJsonObject();
				// Revert the stock
				query = "update transactionstock set closing =  closing + ?"
						+ " where  orgid = ? and oprid = ? and  batchid = ?";
				params = new Object[] { 
						obj.get("qty").getAsLong(),
						body.getTransactionmaster().getOrgid(), 
						body.getTransactionmaster().getOprid(),
						obj.get("batchid").getAsString()
				};

				transactionService.updateTransactionStock(query, params, request);
			}
		}
		// Update the cancellation flag in transactionmaster
		query = "update transactionmaster set canflag = 1 where orgid =" + body.getTransactionmaster().getOrgid()
				+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
				+ body.getTransactionmaster().getTransactionmasterid();
		utility.executeDMLQueryOnPool(query, params, request);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObject.addProperty("message", "Record Saved Successfully");
		return jsonObject.toString();
	}

	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : master id and last operation as CLOSE 
	 * Output  : Status message 
	 * Purpose : Close the replacement
	 * Comment : postCreditNoteReplacementClose
	 ********************************************************************************/
	public String postCreditNoteReplacementClose(TrnMasterBody body, HttpServletRequest request) {
		Object[] params = new Object[] {};
		String query = "update transactionmaster set creditdebitstatus = 'C' where orgid ="
				+ body.getTransactionmaster().getOrgid() + " and oprid = " + body.getTransactionmaster().getOprid()
				+ "  and transactionmasterid =  " + body.getTransactionmaster().getTransactionmasterid();
		utility.executeDMLQueryOnPool(query, params, request);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObject.addProperty("message", "Record Saved Successfully");
		return jsonObject.toString();
	}

	

	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 27/10/2021 
	 * Input   : Batch id,Block and product id
	 * Output  : Product id and status message 
	 * Purpose : Locking and unlocking the batch in batch master 
	 * Comment : batchLockUnlock
	 ********************************************************************************/
	@Transactional
	public long batchLockUnlock(List<CustomBody> body, HttpServletRequest request) {
		long result = 0;
		// Update the block field as the JSON Request
		for (CustomBody customBody : body) {
			// JsonObject for batch master
			JsonObject batchJsonObject = new JsonObject();
			// 1 -Lock 0-Unlock
			batchJsonObject.addProperty("block", customBody.getBlock());
			JsonObject batchJsonObjectWhere = new JsonObject();
			// where clause for batch master update
			batchJsonObjectWhere.addProperty("orgid", customBody.getOrgid());
			batchJsonObjectWhere.addProperty("oprid", customBody.getOprid());
			batchJsonObjectWhere.addProperty("batchid", customBody.getBatchid());

			// Update query
			result = utility.executeCustomDML("batch", "batchid", batchJsonObject, batchJsonObjectWhere,
					customBody.getLastoperation(), request);
		}
		return result;
	}
	
	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 12/11/2021 
	 * Input   : agency name (Not Mandatory)
	 * Output  : Json for tree structure 
	 * Purpose : Tree structure for ZSM, RSM and ASM 
	 * Comment :
	 ********************************************************************************/
	@Transactional
	public JsonArray getZsmRsmAsmListing(String agencyName, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);

		// Select from agency master
		String query = "select  title, agencyid from agency where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + "   AND title LIKE concat(ifnull(" + agencyName
				+ ", title), '%')  and active =1 limit 15";
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonArray jsonArray = new JsonArray();
		if (data.size() > 0) {
			for (JsonElement jsonElement : data.getAsJsonArray()) {
				// Setting to JSON Object for company 
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("label", jsonElement.getAsJsonObject().get("title"));
				jsonObject.addProperty("src", "assets/img/companylogo.png");
				jsonObject.addProperty("identity", "Company");
				jsonObject.add("internalid", jsonElement.getAsJsonObject().get("agencyid"));
				JsonArray jsonArrayAgencyGroupList = new JsonArray();
				// Select from agency group as per agency above 
				query = "select agencygroupid , title from agencygroup where agencyid ="
						+ jsonElement.getAsJsonObject().get("agencyid");
				JsonArray dataAgencyGroup = utility.executeQueryOnPool(query, request);

				if (dataAgencyGroup.size() > 0) {
					for (JsonElement jsonElementAgencyGroup : dataAgencyGroup.getAsJsonArray()) {
						// Agency group JSON Object 
						JsonObject jsonObjectAgencyGroup = new JsonObject();
						jsonObjectAgencyGroup.add("label", jsonElementAgencyGroup.getAsJsonObject().get("title"));
						jsonObjectAgencyGroup.addProperty("src", "assets/img/divisionlogo.png");
						jsonObjectAgencyGroup.addProperty("identity", "Division");
						jsonObjectAgencyGroup.add("internalid",
								jsonElementAgencyGroup.getAsJsonObject().get("agencygroupid"));

						JsonArray jsonArrayAgencyZsmList = new JsonArray();
						// Zsm identity = 5 in agent table 
						query = "select agentid , name from agent where identity = 5 and agencygroupid = "
								+ jsonElementAgencyGroup.getAsJsonObject().get("agencygroupid") + " and agenttype =1";
						JsonArray dataZsm = utility.executeQueryOnPool(query, request);
						if (dataZsm.size() > 0) {
							for (JsonElement jsonElementZsm : dataZsm) {
								// Zsm 
								JsonObject jsonObjectZsm = new JsonObject();
								jsonObjectZsm.add("internalid", jsonElementZsm.getAsJsonObject().get("agentid"));
								jsonObjectZsm.add("label", jsonElementZsm.getAsJsonObject().get("name"));
								jsonObjectZsm.addProperty("src", "assets/img/ZSMlogo.png");
								jsonObjectZsm.addProperty("identity", "ZSM");

								// Rsm 
								JsonArray jsonArrayAgencyRsmList = new JsonArray();
								query = "select agentid , name from agent where identity = 5 and parentid="
										+ jsonElementZsm.getAsJsonObject().get("agentid") + " and agenttype =2";
								JsonArray dataRsm = utility.executeQueryOnPool(query, request);
								if (dataRsm.size() > 0) {

									for (JsonElement jsonElementRsm : dataRsm) {
										// Json Object RSM 
										JsonObject jsonObjectRsm = new JsonObject();
										jsonObjectRsm.add("internalid",
												jsonElementRsm.getAsJsonObject().get("agentid"));
										jsonObjectRsm.add("label", jsonElementRsm.getAsJsonObject().get("name"));
										jsonObjectRsm.addProperty("src", "assets/img/RSMlogo.png");
										jsonObjectRsm.addProperty("identity", "RSM");
										JsonArray jsonArrayAgencyAsmList = new JsonArray();

										query = "select agentid , name from agent where identity = 5 and parentid="
												+ jsonElementRsm.getAsJsonObject().get("agentid") + " and agenttype =3";
										JsonArray dataAsm = utility.executeQueryOnPool(query, request);
										if (dataAsm.size() > 0) {
											for (JsonElement jsonElementAsm : dataAsm) {
												// Json Object RSM 
												JsonObject jsonObjectAsm = new JsonObject();
												jsonObjectAsm.add("internalid",
														jsonElementAsm.getAsJsonObject().get("agentid"));
												jsonObjectAsm.add("label",
														jsonElementAsm.getAsJsonObject().get("name"));
												jsonObjectAsm.addProperty("src", "assets/img/ASMlogo.png");
												jsonObjectAsm.addProperty("identity", "ASM");

												// MR
												JsonArray jsonArrayAgencyMrList = new JsonArray();
												query = "select agentid , name from agent where identity = 5 and parentid="
														+ jsonElementAsm.getAsJsonObject().get("agentid")
														+ " and agenttype =4";
												JsonArray dataMr = utility.executeQueryOnPool(query, request);
												if (dataMr.size() > 0) {
													for (JsonElement jsonElementMr : dataMr) {
														JsonObject jsonObjectmr = new JsonObject();
														jsonObjectmr.add("internalid",
																jsonElementMr.getAsJsonObject().get("agentid"));
														jsonObjectmr.add("label",
																jsonElementMr.getAsJsonObject().get("name"));
														jsonObjectmr.addProperty("src", "assets/img/MRlogo.png");
														jsonObjectmr.addProperty("identity", "MR");
														jsonArrayAgencyMrList.add(jsonObjectmr);
													}
													jsonObjectAsm.add("children", jsonArrayAgencyMrList);
												}
												jsonArrayAgencyAsmList.add(jsonObjectAsm);
											}
											jsonObjectRsm.add("children", jsonArrayAgencyAsmList);
										}
										jsonArrayAgencyRsmList.add(jsonObjectRsm);
									}
									jsonObjectZsm.add("children", jsonArrayAgencyRsmList);
								}

								jsonArrayAgencyZsmList.add(jsonObjectZsm);
							}
							jsonObjectAgencyGroup.add("children", jsonArrayAgencyZsmList);
						}
						jsonArrayAgencyGroupList.add(jsonObjectAgencyGroup);

					}
					jsonObject.add("children", jsonArrayAgencyGroupList);

				}
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	/*********************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 15/11/2021 
	 * Input   : Multiple lock unlock and account id 
	 * Output  : Status Message 
	 * Purpose : Locking and unlocking the customer 
	 * Comment : postCustomerLockUnlock in masters
	 ********************************************************************************/
	@Transactional
	public long postCustomerLockUnlock(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		Object[] params = new Object[] {};
		StringBuilder stringbuilder = new StringBuilder("update  accountoprdetail set locked = (case ");
		for (CustomBody customBody : body) {
			stringbuilder.append(" when accountid=").append(customBody.getAccountid()).append(" then ")
			.append("" + customBody.getLocked());
		}
		
		stringbuilder.append(" else  locked  end ) where orgid =" + adUserAccessToken.get("orgid")
		+ " and oprid =" + adUserAccessToken.get("oprid"));
		return utility.executeDMLQueryOnPool(stringbuilder.toString(), params, request);
	}
	
	/*
	 * *****************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 2022-02-08
	 * Input   : Account Id and serial no
	 * output  : Status and message
	 * Purpose : Update serial no Account master
	 * Comment : postAccountSerialChange
	 ********************************************************************************/
	@Transactional
	public int postAccountSerialChange(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Object[] params = new Object[] {};
		// Bulk update for account opr detail
		StringBuilder stringbuilder = new StringBuilder("update  accountoprdetail set accountserialno = (case ");
		for (CustomBody customBody : body) {
			stringbuilder.append(" when accountid=").append(customBody.getAccountid()).append(" then ")
					.append("" + customBody.getAccountserialno());
		}

		stringbuilder.append(" else  accountserialno  end ) where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid"));
		// update method
		return utility.executeDMLQueryOnPool(stringbuilder.toString(), params, request);
	}

	@Transactional
	public int postSecondDayCashReceiptEntry(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);

		int update = 0;
		for (CustomBody customBody : body) {
			Object[] params = new Object[] { customBody.getPending(), adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"), customBody.getTransactionmasterid() };

			String query = "update transactionmaster set pending = ifnull(pending,0) -?" + " where orgid =?"
					+ " AND oprid = ? " + " and transactionmasterid = ?";
			update = utility.executeDMLQueryOnPool(query, params, request);
		}
		return update;
	}

	@Transactional
	public String getTaxAccountByid(long taxid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);

		String query = "select * from tax where orgid =" + adUserAccessToken.get("orgid") + " AND oprid ="
				+ adUserAccessToken.get("oprid") + " AND taxid = " + taxid;
		JsonArray data = utility.executeQueryOnPool(query, request);
		JsonObject jsonObjectTax = new JsonObject();
		if (data.size() > 0) {
			jsonObjectTax = data.get(0).getAsJsonObject();
			String queryAccounts = "SELECT taxaccount.taxaccountid,\r\n" + "       account.accountname,\r\n"
					+ "       taxaccount.accprefix,\r\n" + "       taxaccount.acctag , taxaccount.accountid\r\n"
					+ "FROM taxaccount taxaccount\r\n" + "     INNER JOIN account account\r\n"
					+ "        ON     taxaccount.accountid = account.accountid\r\n"
					+ "           AND taxaccount.orgid = account.orgid\r\n" + "     where taxaccount.orgid ="
					+ adUserAccessToken.get("orgid") + " and taxaccount.oprid =" + adUserAccessToken.get("oprid")
					+ "  and taxaccount.active =1 and taxaccount.taxid = " + taxid;
			JsonArray dataAccounts = utility.executeQueryOnPool(queryAccounts, request);
			if (dataAccounts.size() > 0) {
				jsonObjectTax.add("taxaccount", dataAccounts);
			}

		}
		return jsonObjectTax.toString();
	}

	public String postStockTransferInsert(TrnMasterBody body, HttpServletRequest request) throws Exception {
		long result = 0;
		String jsonresult = "";
		for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
			// Reduce stock
			result = reducestock(transactionDetailBody, request);
		}
		if (result > 0) {
			// call to generated API
			jsonresult = trnMasterEntity.crud(request, body);
			String masterid = new JSONObject(jsonresult).get("id").toString();

			// Return the inserted records
			String query = "SELECT * FROM transactiondetail where transactionmasterid = "
					+ Long.parseLong(new JSONObject(jsonresult).get("id").toString());
			JsonArray jsonArrayTrnMaster = utility.executeQueryOnPool(query, request);

			JsonObject jsonObjectInsert = new JsonObject();
			jsonObjectInsert.addProperty("code", 200);
			jsonObjectInsert.addProperty("status", "Success");
			jsonObjectInsert.addProperty("id", masterid);
			jsonObjectInsert.addProperty("message", "Record Saved Successfully ");
			jsonObjectInsert.add("data", jsonArrayTrnMaster);
			jsonresult = jsonObjectInsert.toString();
		}
		return jsonresult;
	}

	/**
	 * 
	 * @param transactionDetailBody
	 * @param request
	 * @return This method is used to reduce the stock in transactions
	 */
	public long reducestock(TransactiondetailBody transactionDetailBody, HttpServletRequest request) {
		String whereClause = "", query = "";
		
		 query = "select transactiondetailid , qty ,  free  from transactiondetail where orgid ="
				+ transactionDetailBody.getOrgid() + " and oprid =" + transactionDetailBody.getOprid()
				+ " and transactiondetailid = " + transactionDetailBody.getTransactiondetailid();
		JsonArray data = utility.executeQueryOnPool(query, request);
		
		JsonObject obj = data.get(0).getAsJsonObject();
		query = "update transactionstock set closing = ifnull(closing,0) - (? + ?)" + " ,ierq = ifnull(ierq,0) + ?"
				+ "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
		Object[] params = new Object[] { 
				obj.get("qty").getAsLong(), 
				obj.get("free").getAsLong(), 
				obj.get("qty").getAsLong(),
				transactionDetailBody.getOrgid(), 
				transactionDetailBody.getOprid(), 
				transactionDetailBody.getBatchid(),
				transactionDetailBody.getStoreid() 
		};

		return transactionService.updateTransactionStock(query, params, request);
	}

	public String postStockTransferUpdate(TrnMasterBody body, HttpServletRequest request) throws Exception {
		long result = 0;
		String jsonresult = "";
		for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
			switch (transactionDetailBody.getLastoperation()) {
			// Item level INSERT
			case "INSERT":
				// Reduce the stock
				result = reducestock(transactionDetailBody, request);
				if (result > 0) {
					// Insert in transaction detail with update in transaction master
					jsonresult = trnMasterEntity.crud(request, body);
				}
				break;
			// Item level UPDATE
			case "UPDATE":
				String query = "SELECT \r\n" + "    td.productid, td.batchid, td.qty, td.productpack , td.storeid\r\n"
						+ "FROM\r\n" + "    transactiondetail td\r\n" + "WHERE\r\n" + "    td.orgid = "
						+ transactionDetailBody.getOrgid() + " AND td.oprid =" + transactionDetailBody.getOprid()
						+ " \r\n" + "        AND td.transactiondetailid = "
						+ transactionDetailBody.getTransactiondetailid();

				JsonArray data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					// Revert the stock
					JsonObject jsonObject = data.get(0).getAsJsonObject();

					query = "update transactionstock set closing = ifnull(closing,0) + ?" + " where orgid = ?"
							+ " AND oprid = ?" + "  AND batchid = ?" + " and storeid = ?";
					Object[] params = new Object[] { 
							jsonObject.get("qty").getAsLong(),
							transactionDetailBody.getOrgid(), 
							transactionDetailBody.getOprid(),
							jsonObject.get("batchid").getAsString(),
							jsonObject.get("storeid").getAsString() 
					};
					
					transactionService.updateTransactionStock(query, params, request);
					
					// Delete the detail
					query = "delete from transactiondetail where orgid =?" + " and oprid =?"
							+ " and transactiondetailid =?";
					params = new Object[] { transactionDetailBody.getOrgid(), transactionDetailBody.getOprid(),
							transactionDetailBody.getTransactiondetailid() };
					utility.executeDMLQueryOnPool(query, params, request);
				}

				// Reduce the Stock
				result = reducestock(transactionDetailBody, request);

				if (result > 0) {

					// Insert into transaction detail
					Gson gson = new Gson();
					String transactionDetailString = gson.toJson(body.getTransactionmaster().getTransactiondetail());
					JsonArray transactionDetailJsonObject = new Gson().fromJson(transactionDetailString,
							JsonArray.class);

					JsonObject jsonobject = transactionDetailJsonObject.get(0).getAsJsonObject();
					jsonobject.remove("transactiondetailid");
					// Id generation
					String transactionDetailid = utility.executeIdGenerationProcedure(
							body.getTransactionmaster().getOrgid().toString(),
							body.getTransactionmaster().getOrgid().toString(), request.getHeader("rightId"),
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					transactionDetailid = new JSONObject(transactionDetailid).getString("id");

					jsonobject.addProperty("transactiondetailid", transactionDetailid);
					jsonobject.remove("voucherdate");
					jsonobject.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
					jsonobject.remove("lastoperation");

					jsonobject.addProperty("lastoperation", "INSERT");
					jsonobject.remove("createdon");
					jsonobject.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
					jsonobject.remove("expiry");

					JsonObject jobj = new JsonObject();
					utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonobject, jobj, "INSERT",
							request);
				}

				break;
			// Item level DELETE
			case "DELETE":

				query = "delete from transactiondetail where orgid =?" + " and oprid =?"
						+ " and transactiondetailid =?";
				Object[] params = new Object[] { transactionDetailBody.getOrgid(), transactionDetailBody.getOprid(),
						transactionDetailBody.getTransactiondetailid() };
				utility.executeDMLQueryOnPool(query, params, request);

				// Revert Stock
				query = "update transactionstock set closing = closing + ?" + " where orgid = ?" + " and oprid = ?"
						+ " and batchid = ?" + " and storeid = ?";
				params = new Object[] { transactionDetailBody.getQty(), body.getTransactionmaster().getOrgid(),
						body.getTransactionmaster().getOprid(), transactionDetailBody.getBatchid(),
						transactionDetailBody.getStoreid() };
				transactionService.updateTransactionStock(query, params, request);
				break;
			}

		}
		if (body.getTransactionmaster().getTransactionmasterid() == 0) {

			String query = "SELECT * FROM transactiondetail where transactionmasterid = "
					+ Long.parseLong(new JSONObject(jsonresult).get("id").toString());
			JsonArray jsonArrayTrnMaster = utility.executeQueryOnPool(query, request);

			JsonObject jsonObjectInsert = new JsonObject();
			jsonObjectInsert.addProperty("code", 200);
			jsonObjectInsert.addProperty("status", "Success");
			jsonObjectInsert.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			jsonObjectInsert.addProperty("message", "Record Saved Successfully ");
			jsonObjectInsert.add("data", jsonArrayTrnMaster);
			jsonresult = jsonObjectInsert.toString();

		} else {
			String query = "SELECT * FROM transactiondetail where transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			JsonArray jsonArrayTrnMaster = utility.executeQueryOnPool(query, request);
			JsonObject jsonObjectInsert = new JsonObject();
			jsonObjectInsert.addProperty("code", 200);
			jsonObjectInsert.addProperty("status", "Success");
			jsonObjectInsert.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			jsonObjectInsert.addProperty("message", "Record Saved Successfully ");
			jsonObjectInsert.add("data", jsonArrayTrnMaster);
			jsonresult = jsonObjectInsert.toString();
		}
		return jsonresult;

	}

	public String postStockTransferFinalSave(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String jsonresult = "";
		String voucherno = "";
		
		TransactionmasterBody trnMaster = body.getTransactionmaster();
		
		voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
		trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), "0", 
		request.getHeader("rightId"), token, request.getRequestURL().toString());

		voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();

		// Fetch the records in transactiondetail
		String query = "select * from transactiondetail where orgid = " + body.getTransactionmaster().getOrgid()
				+ " and oprid=" + body.getTransactionmaster().getOprid() + " and transactionmasterid= "
				+ body.getTransactionmaster().getTransactionmasterid();
		JsonArray data = utility.executeQueryOnPool(query, request);
		if (data.size() > 0) {
			for (JsonElement jsonObjectEach : data.getAsJsonArray()) {
				query = "select * from transactionstock where orgid=" + body.getTransactionmaster().getOrgid()
						+ " and oprid =" + body.getTransactionmaster().getOprid() + " and productid ="
						+ jsonObjectEach.getAsJsonObject().get("productid") + " and batchid ="
						+ jsonObjectEach.getAsJsonObject().get("batchid") + "  and  storeid ="
						+ jsonObjectEach.getAsJsonObject().get("targetstoreid");
				JsonArray dataStock = utility.executeQueryOnPool(query, request);
				// Check if batch exist in transaction stock
				// If exist update else insert new records
				if (dataStock.size() > 0) {
					JsonObject obj = jsonObjectEach.getAsJsonObject();
					query = "update transactionstock set closing = closing + ?" + " where orgid = ?" + " and oprid = ?"
							+ " and batchid = ?";
					Object[] params = new Object[] {
							obj.get("qty").getAsLong(),
							body.getTransactionmaster().getOrgid(), 
							body.getTransactionmaster().getOprid(),
							obj.get("batchid").getAsString()
					};
					
					transactionService.updateTransactionStock(query, params, request);

				} else {
					// Insert into transaction stock
					JsonObject jsonObject = new JsonObject();
					JsonObject transactionstock = new JsonObject();
					transactionstock.addProperty("orgid", body.getTransactionmaster().getOrgid());
					transactionstock.addProperty("oprid", body.getTransactionmaster().getOprid());

					// Generating the stock id
					String stockid = utility.executeIdGenerationProcedure(
							body.getTransactionmaster().getOrgid().toString(),
							body.getTransactionmaster().getOrgid().toString(), request.getHeader("rightId"),
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					stockid = new JSONObject(stockid).getString("id");

					// transaction stock json object
					transactionstock.addProperty("transactionstockid", stockid);

					transactionstock.addProperty("productid",
							jsonObjectEach.getAsJsonObject().get("productid").toString());
					transactionstock.addProperty("batchid", jsonObjectEach.getAsJsonObject().get("batchid").toString());
					transactionstock.addProperty("rateid", jsonObjectEach.getAsJsonObject().get("rateid").toString());
					transactionstock.addProperty("mrp", jsonObjectEach.getAsJsonObject().get("mrp").toString());
					transactionstock.addProperty("trade", jsonObjectEach.getAsJsonObject().get("trade").toString());
					transactionstock.addProperty("salerate",
							jsonObjectEach.getAsJsonObject().get("salerate").toString());
					transactionstock.addProperty("purchaserate",
							jsonObjectEach.getAsJsonObject().get("purchaserate").toString());
					transactionstock.addProperty("quantity", jsonObjectEach.getAsJsonObject().get("qty").toString());
					transactionstock.addProperty("new", 0);
					transactionstock.addProperty("active", 1);
					transactionstock.addProperty("createdby", body.getTransactionmaster().getCreatedby());
					transactionstock.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
					transactionstock.addProperty("storeid",
							jsonObjectEach.getAsJsonObject().get("targetstoreid").toString());
					transactionstock.addProperty("fycode", 2021);
					transactionstock.addProperty("reftrnid",
							jsonObjectEach.getAsJsonObject().get("transactiondetailid").toString());
					transactionstock.addProperty("lastoperation", "INSERT");
					// Insert into transaction stock
					utility.executeCustomDML("transactionstock", "transactionstockid", transactionstock, jsonObject,
							"INSERT", request);
				}
			}
			// Updating the voucher no and other details in transaction master and detail

			query = "update transactiondetail set voucherno =?" + " , vouchermode =2 , active =1 where orgid =?"
					+ " and oprid =?" + " and  transactionmasterid =?";
			Object[] params = new Object[] {
					voucherno,
					body.getTransactionmaster().getOrgid(), body.getTransactionmaster().getOprid(),
					body.getTransactionmaster().getTransactionmasterid() };
			utility.executeDMLQueryOnPool(query, params, request);

			query = "update  transactionmaster set  voucherno =?" + ", vouchermode =2 , active =1 where orgid =?"
					+ " and oprid =?" + " and transactionmasterid =?";
			params = new Object[] { voucherno,
					body.getTransactionmaster().getOrgid(), body.getTransactionmaster().getOprid(),
					body.getTransactionmaster().getTransactionmasterid() };
			utility.executeDMLQueryOnPool(query, params, request);
		}
		// Final save response
		JsonObject jsonObjectFinalSave = new JsonObject();
		jsonObjectFinalSave.addProperty("code", 200);
		jsonObjectFinalSave.addProperty("status", "Success");
		jsonObjectFinalSave.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObjectFinalSave.addProperty("message", "Record Saved Successfully ");
		jsonObjectFinalSave.addProperty("data", voucherno);
		jsonresult = jsonObjectFinalSave.toString();
		return jsonresult;

	}

	public String postStockTransferEscape(TrnMasterBody body, HttpServletRequest request) {
		String query = "select * from transactiondetail where orgid = " + body.getTransactionmaster().getOrgid()
				+ " and oprid=" + body.getTransactionmaster().getOprid() + " and transactionmasterid= "
				+ body.getTransactionmaster().getTransactionmasterid();
		JsonArray data = utility.executeQueryOnPool(query, request);
		if (data.size() > 0) {
			for (JsonElement jsonObjectEach : data.getAsJsonArray()) {
				JsonObject obj = jsonObjectEach.getAsJsonObject();
				query = "update transactionstock set closing = ifnull(closing,0) + ?" + " where orgid = ?"
						+ " AND oprid = ?" + "  AND batchid = ?";
				Object[] params = new Object[] { 
						obj.get("qty").getAsLong(),
						body.getTransactionmaster().getOrgid(), 
						body.getTransactionmaster().getOprid(),
						obj.get("batchid").getAsString() 
				};
				
				transactionService.updateTransactionStock(query, params, request);
			}

		}
		Object[] params = new Object[] { body.getTransactionmaster().getOrgid(), body.getTransactionmaster().getOprid(),
				body.getTransactionmaster().getTransactionmasterid() };
		// Delete transaction detail and master
		query = "delete from transactiondetail where orgid =?" + " and oprid =?" + " and transactionmasterid =?";
		utility.executeDMLQueryOnPool(query, params, request);

		query = "delete from transactionmaster where orgid =?" + " and oprid =?" + " and transactionmasterid =?";
		utility.executeDMLQueryOnPool(query, params, request);
		JsonObject jsonObjectEscape = new JsonObject();

		// Response for Escape
		jsonObjectEscape.addProperty("code", 200);
		jsonObjectEscape.addProperty("status", "Success");
		jsonObjectEscape.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
		jsonObjectEscape.addProperty("message", "Record Delete Successfully");
		String jsonresult = jsonObjectEscape.toString();
		return jsonresult;

	}

	public int postUpdateSchemeBalance(List<ProductschemeBody> body, HttpServletRequest request) {
		int update = 0;
		for (ProductschemeBody productschemeBody : body) {
			String query = "update productscheme set balqty = ?" + " where  orgid = ?" + " AND oprid = ?"
					+ " AND productschemeid = ?";
			Object[] params = new Object[] { productschemeBody.getBalqty(), productschemeBody.getOrgid(),
					productschemeBody.getOprid(), productschemeBody.getProductschemeid() };
			update = utility.executeDMLQueryOnPool(query, params, request);
		}
		return update;
	}

	/*
	 * *****************************************************************************
	 * Author : Chetan Channe 
	 * Date   : 2022-02-01 
	 * Input  : Data customer and area and outstanding 
	 * output : Json response depending upon conditions 
	 * Purpose : To perform fetch days wise record on accountoprdetail ,area and outstanding
	 * Comment :
	 ********************************************************************************/
	public JsonArray getDiscountDebitNoteEntry(CustomCheckDuplicateValidateBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray jsonArray = new JsonArray();
		String whereClauseAccounts = "", whereClauseAccountsCase = "", whereClauseArea = "", whereClauseAreaCase = "",
				whereClauseCreditDays = "", whereClauseAccName = "";

		if (body.getParam1().length() == 0) {
			whereClauseAccounts = null;
			whereClauseAccountsCase = null;
		} else {
			whereClauseAccounts = body.getParam1();
			whereClauseAccountsCase = "'" + body.getParam1() + "'";
		}

		if (body.getParam2().length() == 0) {
			whereClauseArea = null;
			whereClauseAreaCase = null;
		} else {
			whereClauseArea = body.getParam2();
			whereClauseAreaCase = "'" + body.getParam2() + "'";
		}

		if (body.getParam3().length() == 0) {
			whereClauseCreditDays = "0";
		} else {
			whereClauseCreditDays = body.getParam3();
		}

		if (body.getParam4().length() > 0) {
			whereClauseAccName = " and account.accountname like '" + body.getParam4() + "%'";
		}

		String query = "SELECT accountoprdetail.accountid\r\n" + "          internalid,\r\n"
				+ "       account.accountname\r\n" + "          accountname,\r\n"
				+ "       concat(ifnull(accountoprdetail.add1, ''),\r\n" + "              ' ',\r\n"
				+ "              ifnull(accountoprdetail.add3, ''))\r\n" + "          customeraddress,\r\n"
				+ "       outstanding.billno\r\n" + "          billno,\r\n" + "       outstanding.billdate\r\n"
				+ "          billdate,\r\n" + "       outstanding.billamt\r\n" + "          billamt,\r\n"
				+ "       outstanding.billdiscountamt\r\n" + "          billdiscountamt,\r\n"
				+ "          DATEDIFF( CURDATE(), billdate) days\r\n" + "FROM account account\r\n"
				+ "     INNER JOIN accountoprdetail accountoprdetail\r\n"
				+ "        ON     account.orgid = accountoprdetail.orgid\r\n"
				+ "           AND account.accountid = accountoprdetail.accountid\r\n"
				+ "     inner JOIN outstanding outstanding\r\n"
				+ "        ON     account.accountid = outstanding.accountid\r\n"
				+ "           AND account.orgid = outstanding.orgid\r\n"
				+ "           AND accountoprdetail.oprid = outstanding.oprid\r\n" + "      inner join area area\r\n"
				+ "       on accountoprdetail.areaid = area.areaid\r\n"
				+ "       and accountoprdetail.orgid = area.orgid\r\n" + "WHERE     accountoprdetail.orgid ="
				+ adUserAccessToken.get("orgid") + "      AND accountoprdetail.oprid = "
				+ adUserAccessToken.get("oprid") + "      AND accountoprdetail.active = 1\r\n"
				+ "      AND accountoprdetail.identity = 1\r\n" + "       and (case\r\n" + "     when "
				+ whereClauseAreaCase + " is null \r\n" + "     then  \r\n" + "     area.areaid = area.areaid\r\n"
				+ "      else \r\n" + "     area.areaid IN (" + whereClauseArea + ")\r\n" + "     end)\r\n"
				+ "     and (case when " + whereClauseAccountsCase + " is null \r\n" + "     then  \r\n"
				+ "     accountoprdetail.accountid = accountoprdetail.accountid\r\n" + "      else \r\n"
				+ "     accountoprdetail.accountid IN (" + whereClauseAccounts + ")\r\n" + "      end)\r\n"
				+ "     AND DATEDIFF(CURDATE(), date(outstanding.billdate)) >= IFNULL(" + whereClauseCreditDays
				+ ",0)   " + whereClauseAccName;
		jsonArray = utility.executeQueryOnPool(query, request);

		return jsonArray;

	}

	@Transactional
	public int postDiscountDebitNoteEntry(CustomBody customBody, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Object[] params = new Object[] {};
		StringBuilder customQuery = new StringBuilder();
		String query = "";
		int result = 0;
		// Append for bulk insert transaction master
		String transactionQuery = "";
		String voucherno = "";
		if (customBody.getTransactionmaster().size() > 0) {
			for (TransactionmasterBody transactionmasterBody : customBody.getTransactionmaster()) {
				String transactionMasterid = utility.executeIdGenerationProcedure(
						transactionmasterBody.getOrgid().toString(), transactionmasterBody.getOprid().toString(),
						"transactionmaster", request.getHeader("authorization").split(" ")[1],
						request.getRequestURL().toString());
				transactionMasterid = new JSONObject(transactionMasterid).getString("id");

				// Generating voucher number
//				String sql = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno\r\n"
//						+ "FROM transactionmaster\r\n" + "WHERE orgid =" + adUserAccessToken.get("orgid")
//						+ " AND oprid =" + adUserAccessToken.get("oprid") + "  AND vouchertype = 'DN'";
//				JsonArray datadtl = utility.executeQueryOnPool(sql, request);
				
				voucherno = utility.executeInvoiceNoGenerationProcedure(transactionmasterBody.getOrgid().toString(), transactionmasterBody.getOprid().toString(), 
						transactionmasterBody.getDoctypeid().toString(), transactionmasterBody.getFiyearid().toString(), transactionmasterBody.getStoreid().toString(), 
						"0", request.getHeader("rightId"), token, request.getRequestURL().toString());

				voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
				if (transactionmasterBody.toString() != null) {
					transactionQuery = transactionQuery + "(" + transactionmasterBody.getOrgid() + ","
							+ transactionmasterBody.getOprid() + ","
							+ transactionMasterid + ","
							+ transactionmasterBody.getAccountid() + "," + transactionmasterBody.getCreatedby() + ","
							+ voucherno + " , CURDATE() , 'INSERT' , 2 , 1"
							+ ",'" + transactionmasterBody.getBilldate() + "','" + transactionmasterBody.getBillno()
							+ "'," + transactionmasterBody.getCreditdays() + ",'"
							+ transactionmasterBody.getCustomername() + "','" + transactionmasterBody.getCustomeradd1()
							+ "','" + transactionmasterBody.getCustomeradd2() + "','"
							+ transactionmasterBody.getCustomeradd3() + "',"
							+ transactionmasterBody.getCashdiscountamount() + "," + transactionmasterBody.getNetamount()
							+ ",'" + transactionmasterBody.getVoucherdate() + "','"
							+ transactionmasterBody.getVouchertype() + "'),";
				}
			}

			// Bulk insert in transactionmaster
			if (customBody.getTransactionmaster().size() > 0) {
				transactionQuery = transactionQuery.substring(0, transactionQuery.length() - 1);
				query = "insert into transactionmaster (\n" + "   orgid\n" + "  ,oprid\n" + "  ,transactionmasterid\n"
						+ "  ,accountid\n" + "  ,createdby\n" + "  ,voucherno\n" + "  ,createdon\n"
						+ "  ,lastoperation\n" + "  ,vouchermode\n" + "  ,active\n" + " ,billdate\n" + " ,billno\n"
						+ " ,creditdays\n" + " ,customername\n" + " ,customeradd1\n" + " ,customeradd2\n"
						+ " ,customeradd3\n" + " ,cashdiscountamount\n" + " ,netamount\n" + " ,voucherdate\n"
						+ " ,vouchertype\n" + ") VALUES" + transactionQuery;
				result = utility.executeDMLQueryOnPool(query, params, request);
			}
		} else {
			JsonArray jsonArray = new JsonArray();
			String whereClauseAccounts, whereClauseAccountsCase, whereClauseArea, whereClauseAreaCase;
			int whereClauseCreditDays;

			if (customBody.getAccountid() == 0) {
				whereClauseAccounts = null;
				whereClauseAccountsCase = null;
			} else {
				whereClauseAccounts = customBody.getAccountid().toString();
				whereClauseAccountsCase = customBody.getAccountid().toString();
			}

			if (customBody.getAreaid() == 0) {
				whereClauseArea = null;
				whereClauseAreaCase = null;
			} else {
				whereClauseArea = customBody.getAreaid().toString();
				whereClauseAreaCase = customBody.getAreaid().toString();
			}

			if (customBody.getCreditdays() == 0) {
				whereClauseCreditDays = 0;
			} else {
				whereClauseCreditDays = customBody.getCreditdays();
			}

			query = "SELECT accountoprdetail.accountid  internalid,\r\n"
					+ "       account.accountname  accountname,\r\n"
					+ "       accountoprdetail.add1  customeraddress1,\r\n"
					+ "       accountoprdetail.add3  customeraddress3,\r\n"
					+ "       outstanding.billno  billno,\r\n" 
					+ "       outstanding.billdate  billdate,\r\n" 
					+ "       outstanding.billamt billamt,\r\n"
					+ "       outstanding.billdiscountamt  billdiscountamt,\r\n"
					+ "       DATEDIFF( CURDATE(), billdate) days,\r\n"
					+ "       account.doctypeid doctypeid,\r\n"
					+ "       account.fiyearid fiyearid,\r\n"
					+ "       account.storeid storeid,\r\n"
					+ "       account.agencyid agencyid\r\n"
					+ "FROM account account\r\n"
					+ "     INNER JOIN accountoprdetail accountoprdetail\r\n"
					+ "        ON     account.orgid = accountoprdetail.orgid\r\n"
					+ "           AND account.accountid = accountoprdetail.accountid\r\n"
					+ "     inner JOIN outstanding outstanding\r\n"
					+ "        ON     account.accountid = outstanding.accountid\r\n"
					+ "           AND account.orgid = outstanding.orgid\r\n"
					+ "           AND accountoprdetail.oprid = outstanding.oprid\r\n" + "      inner join area area\r\n"
					+ "       on accountoprdetail.areaid = area.areaid\r\n"
					+ "       and accountoprdetail.orgid = area.orgid\r\n" + "WHERE     accountoprdetail.orgid ="
					+ adUserAccessToken.get("orgid") + "      AND accountoprdetail.oprid = "
					+ adUserAccessToken.get("oprid") + "      AND accountoprdetail.active = 1\r\n"
					+ "      AND accountoprdetail.identity = 1\r\n" + "       and (case\r\n" + "     when "
					+ whereClauseAreaCase + " is null \r\n" + "     then  \r\n" + "     area.areaid = area.areaid\r\n"
					+ "      else \r\n" + "     area.areaid IN (" + whereClauseArea + ")\r\n" + "     end)\r\n"
					+ "     and (case when " + whereClauseAccountsCase + " is null \r\n" + "     then  \r\n"
					+ "     accountoprdetail.accountid = accountoprdetail.accountid\r\n" + "      else \r\n"
					+ "     accountoprdetail.accountid IN (" + whereClauseAccounts + ")\r\n" + "      end)\r\n"
					+ "     AND DATEDIFF(CURDATE(), date(outstanding.billdate)) >= IFNULL(" + whereClauseCreditDays
					+ ",0)  ";
			jsonArray = utility.executeQueryOnPool(query, request);

			// Append for bulk insert
			if (jsonArray.size() > 0) {
				for (JsonElement jsonObjectEach : jsonArray) {
					JsonObject obj = jsonObjectEach.getAsJsonObject();
					String transactionMasterid = utility.executeIdGenerationProcedure(
							adUserAccessToken.get("orgid").toString(), adUserAccessToken.get("oprid").toString(),
							"transactionmaster", request.getHeader("authorization").split(" ")[1],
							request.getRequestURL().toString());
					transactionMasterid = new JSONObject(transactionMasterid).getString("id");
					// Generating voucher number
					
					voucherno = utility.executeInvoiceNoGenerationProcedure(adUserAccessToken.get("orgid"), adUserAccessToken.get("oprid"), 
							RestUtil.getAsString(obj.get("doctypeid")), RestUtil.getAsString(obj.get("fiyearid")), RestUtil.getAsString(obj.get("storeid")), 
							RestUtil.getAsString(obj.get("agencyid")), 
							request.getHeader("rightId"), token, request.getRequestURL().toString());
					
					voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();

					customQuery = customQuery.append("(" + adUserAccessToken.get("orgid") + ","
							+ adUserAccessToken.get("oprid") + "," + transactionMasterid + ","
							+ obj.get("internalid") + ","
							+ obj.get("accountname") + ","
							+ obj.get("customeraddress1") + ","
							+ obj.get("customeraddress3") + ","
							+ obj.get("billno") + ","
							+ obj.get("billdate") + ","
							+ obj.get("billamt") + ","
							+ obj.get("billdiscountamt") + ","
							+ obj.get("days") + "," + adUserAccessToken.get("adusermastid")
							+ "," + voucherno
							+ ", CURDATE() , 'INSERT' , 2 , 1 , 'DN' , CURDATE()" + "),");
				}
			}

			// Bulk Insert
			customQuery = new StringBuilder(customQuery.substring(0, customQuery.length() - 1));
			query = "insert into transactionmaster (\n" + "   orgid\n" + "  ,oprid\n" + "  ,transactionmasterid\n"
					+ "  ,accountid\n" + "  ,customername\n" + "  ,customeradd1\n" + "  ,customeradd3\n" + " ,billno\n"
					+ " ,billdate\n" + " ,netamount\n" + " ,cashdiscountamount\n" + " ,creditdays\n" + "  ,createdby\n"
					+ "  ,voucherno\n" + "  ,createdon\n" + "  ,lastoperation\n" + "  ,vouchermode\n" + "  ,active\n"
					+ " ,vouchertype\n" + " ,voucherdate\n" + ") VALUES" + customQuery;
			result = utility.executeDMLQueryOnPool(query, params, request);

		}
		return result;
	}
	
	/*
	 * *****************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 2020-02-18 
	 * Input   : Id and Sequence No 
	 * output  : Success and Failure Status 
	 * Purpose : Setting the sequence in area master
	 * Comment :
	 ********************************************************************************/
	@Transactional
	public int postareamastersequence(List<CustomBody> body, HttpServletRequest request) {

		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Object[] params = new Object[] {};
		
		// Bulk update for account opr detail
		String tablename = "", key = "";

		// Toggle for table name
		switch (body.get(0).getAreaflag()) {
		case "AREA":
			tablename = "area";
			key = "areaid";
			break;

		case "CITY":
			tablename = "city";
			key = "cityid";
			break;

		case "STATE":
			tablename = "state";
			key = "stateid";
			break;

		}
		StringBuilder stringbuilder = new StringBuilder("update  " + tablename + " set sequenceno = (case ");
		for (CustomBody custombody : body) {
			stringbuilder.append(" when " + key + "=").append(custombody.getInternalid()).append(" then ")
					.append("" + custombody.getSequenceno());

		}
		stringbuilder.append(" else  sequenceno  end ) where orgid =" + adUserAccessToken.get("orgid"));
		// update method
		return utility.executeDMLQueryOnPool(stringbuilder.toString(), params, request);

	}

	// Moved to transaction service 
	public long postCommonTransactionRevertOld(TransactiondetailBody body, HttpServletRequest request) {
		String query = "";
		long result = 0;
		query = "SELECT qty, free, specialqty, batchid, productid FROM transactiondetail\r\n" 
				+ " WHERE orgid = " + body.getOrgid() + " AND oprid = " + body.getOprid() 
				+ " AND transactionmasterid = " + body.getTransactionmasterid() 
				+ " AND transactiondetailid = " + body.getTransactiondetailid();
		JsonArray data = utility.executeQueryOnPool(query, request);

		for (JsonElement element : data) {
			JsonObject obj = element.getAsJsonObject();
			query = "update transactionstock set closing =  closing - (? + ? + ?), rqp = rqp - (? + ?), "
					+ "rfqp = rfqp - ? where  orgid = ? and oprid = ? and  batchid = ? and productid = ?";
			
			Object[] params = new Object[] {
					RestUtil.getAsBigDecimal(obj.get("qty")),
					RestUtil.getAsBigDecimal(obj.get("free")),
					RestUtil.getAsBigDecimal(obj.get("specialqty")),
					RestUtil.getAsBigDecimal(obj.get("qty")),
					RestUtil.getAsBigDecimal(obj.get("specialqty")),
					RestUtil.getAsBigDecimal(obj.get("free")),
					body.getOrgid(),
					body.getOprid(),
					RestUtil.getAsString(obj.get("batchid")),
					RestUtil.getAsString(obj.get("productid"))
			};
			
			result = transactionService.updateTransactionStock(query, params, request);
		}
		
		return result;
	}

	/*
	 * *****************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 2020-02-17 
	 * Input   : Area id and flag for delete common for city state and country 
	 * output  : Response depending upon conditions
	 * Purpose : 
	 * Comment :
	 ********************************************************************************/
	@Transactional
	public String postDeleteArea(CustomBody body, HttpServletRequest request) {
		String query = "", stateid = "", cityid = "", areaid = "";
		boolean checkflag = false;
		long result = 0;
		JsonObject jsonObject = new JsonObject();
		Object[] params = new Object[] {};
		switch (body.getAreaflag()) {
		case "Country":
			query = "select group_concat(stateid)stateid from state where orgid =" + body.getOrgid()
					+ " and countryid = " + body.getInternalid();
			JsonArray dataState = utility.executeQueryOnPool(query, request);
			if (dataState.size() > 0) {
				stateid = dataState.get(0).getAsJsonObject().get("stateid").toString();
				if (stateid.length() > 0) {
					query = "select group_concat(cityid) cityid from city where orgid=" + body.getOrgid()
							+ " and stateid in(" + stateid + ")";
					JsonArray dataCity = utility.executeQueryOnPool(query, request);
					if (dataCity.size() > 0) {
						cityid = dataCity.get(0).getAsJsonObject().get("cityid").toString();
						query = "select group_concat(areaid)areaid from area where cityid in(" + cityid
								+ ") and orgid =" + body.getOrgid();
						JsonArray dataArea = utility.executeQueryOnPool(query, request);
						if (dataArea.size() > 0) {
							// Check in customer master
							areaid = dataArea.get(0).getAsJsonObject().get("areaid").toString();
							query = "select ifnull(count(*),0) countCustomer from accountoprdetail where areaid in("
									+ areaid + ")";

							JsonArray dataAccounts = utility.executeQueryOnPool(query, request);
							if (dataAccounts.get(0).getAsJsonObject().get("countcustomer").getAsInt() != 0) {
								jsonObject.addProperty("code", 200);
								jsonObject.addProperty("status", "FAILURE");
								jsonObject.addProperty("message", "Country cannot be deleted already assigned");

							} else {
								checkflag = true;
								query = "delete from area where areaid in (" + areaid + ") and orgid = "
										+ body.getOrgid();
								result = utility.executeDMLQueryOnPool(query, params, request);

								if (result >= 0) {
									query = "delete from city where cityid in (" + cityid + ") and orgid = "
											+ body.getOrgid();
									result = utility.executeDMLQueryOnPool(query, params, request);
									if (result >= 0) {
										query = "delete from state where stateid in (" + stateid + ") and orgid = "
												+ body.getOrgid();
										result = utility.executeDMLQueryOnPool(query, params, request);
										if (result >= 0) {
											query = "delete from country where countryid in (" + body.getInternalid()
													+ ") and orgid = " + body.getOrgid();
											result = utility.executeDMLQueryOnPool(query, params, request);
										}
									}

								}
							}

						}

					}

				}
			}

			if (checkflag) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully ");
			}

			break;
		case "State":
			query = "select group_concat(cityid) cityid from city where orgid=" + body.getOrgid() + " and stateid in("
					+ body.getInternalid() + ")";
			JsonArray dataCity = utility.executeQueryOnPool(query, request);
			if (dataCity.size() > 0) {
				cityid = dataCity.get(0).getAsJsonObject().get("cityid").toString();

				query = "select group_concat(areaid)areaid from area where cityid in(" + cityid + ") and orgid ="
						+ body.getOrgid();
				JsonArray dataArea = utility.executeQueryOnPool(query, request);
				if (dataArea.size() > 0) {
					areaid = dataArea.get(0).getAsJsonObject().get("areaid").toString();
					areaid = dataArea.get(0).getAsJsonObject().get("areaid").toString();
					query = "select ifnull(count(*),0) countCustomer from accountoprdetail where areaid in(" + areaid
							+ ")";

					JsonArray dataAccounts = utility.executeQueryOnPool(query, request);
					if (dataAccounts.get(0).getAsJsonObject().get("countcustomer").getAsInt() != 0) {
						jsonObject.addProperty("code", 200);
						jsonObject.addProperty("status", "FAILURE");
						jsonObject.addProperty("message", "Country cannot be deleted already assigned");

					} else {
						checkflag = true;
						query = "delete from area where areaid in (" + areaid + ") and orgid = " + body.getOrgid();
						result = utility.executeDMLQueryOnPool(query, params, request);

						if (result >= 0) {
							query = "delete from city where cityid in (" + cityid + ") and orgid = " + body.getOrgid();
							result = utility.executeDMLQueryOnPool(query, params, request);
							if (result >= 0) {
								query = "delete from state where stateid in (" + body.getInternalid() + ") and orgid = "
										+ body.getOrgid();
								result = utility.executeDMLQueryOnPool(query, params, request);

							}

						}
					}

				}

			}

			if (checkflag) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully ");
			}
			break;

		case "City":
			query = "select group_concat(areaid)areaid from area where cityid in(" + body.getInternalid()
					+ ") and orgid =" + body.getOrgid();
			JsonArray dataArea = utility.executeQueryOnPool(query, request);
			if (dataArea.size() > 0) {
				areaid = dataArea.get(0).getAsJsonObject().get("areaid").toString();
				query = "select ifnull(count(*),0) countCustomer from accountoprdetail where areaid in(" + areaid + ")";

				JsonArray dataAccounts = utility.executeQueryOnPool(query, request);
				if (dataAccounts.get(0).getAsJsonObject().get("countcustomer").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Country cannot be deleted already assigned");

				} else {
					checkflag = true;
					query = "delete from area where areaid in (" + areaid + ") and orgid = " + body.getOrgid();
					result = utility.executeDMLQueryOnPool(query, params, request);
					if (result >= 0) {
						query = "delete from city where cityid in (" + body.getInternalid() + ") and orgid = "
								+ body.getOrgid();
						result = utility.executeDMLQueryOnPool(query, params, request);
					}
				}
			}

			if (checkflag) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully ");
			}
			break;
		case "Area":
			query = "select ifnull(count(*),0) countCustomer from accountoprdetail where areaid in("
					+ body.getInternalid() + ")";
			JsonArray dataAccounts = utility.executeQueryOnPool(query, request);
			if (dataAccounts.get(0).getAsJsonObject().get("countcustomer").getAsInt() != 0) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "FAILURE");
				jsonObject.addProperty("message", "Country cannot be deleted already assigned");

			} else {
				checkflag = true;
				query = "delete from area where areaid in (" + body.getInternalid() + ") and orgid = "
						+ body.getOrgid();
				result = utility.executeDMLQueryOnPool(query, params, request);

			}
			if (checkflag) {
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("message", "Record Saved Successfully ");
			}
			break;

		}
		return jsonObject.toString();
	}

	/*
	 * *****************************************************************************
	 * Author  : Aayush Dahake 
	 * Date    : 2022-02-22 
	 * Input   : Route id and sequence no
	 * output  : Status and message
	 * Purpose : To update the sequence no in route 
	 * Comment :
	 ********************************************************************************/
	@Transactional
	public String postRouteSequence(List<CustomBody> body, HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);

		int result = 0;
		// Bulk update for route master sequence number
		Object[] params = new Object[] {};
		StringBuilder query = new StringBuilder("update route set sequenceno = (case ");

		for (CustomBody customBody : body) {
			query.append(" when  routeid =" + customBody.getInternalid() + " then " + customBody.getSequenceno());
		}
		if (query.length() > 0) {
			query.append(" else sequenceno  end  ) where orgid = " + adUserAccessToken.get("orgid") + " and oprid ="
					+ adUserAccessToken.get("oprid"));
			result = utility.executeDMLQueryOnPool(query.toString(), params, request);
		}
		// Bulk update for route master sequence number ends here

		// JsonObject as per requirement
		if (result >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully ");
		}
		return jsonObject.toString();
	}

	@Transactional
	public String updateClaim(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonObject jsonObject = new JsonObject();
		String voucherno = "";

//		String query = "select ((ifnull(max(voucherno),0))+1)as voucherno from transactionmaster " + "  where orgid = "
//				+ adUserAccessToken.get("orgid") + " AND oprid =" + adUserAccessToken.get("oprid");
//
//		JsonArray jsonArray = utility.executeQueryOnPool(query, request);
		
		TransactionmasterBody trnMaster = body.getTransactionmaster();
		voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
				trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), 
				"0", request.getHeader("rightId"), token, request.getRequestURL().toString());

		voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();

		Object[] params = new Object[] {};

		// Update in transaction master
		String query = "update transactionmaster set vouchermode =2 , active =1 , voucherno ="
				+ voucherno + "  where orgid="
				+ adUserAccessToken.get("orgid") + " and oprid = " + adUserAccessToken.get("oprid")
				+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
		utility.executeDMLQueryOnPool(query.toString(), params, request);

		// Update in transaction detail
		query = "update transactiondetail set vouchermode= 2 , active =1 and voucherno="
				+ voucherno + " where orgid ="
				+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
				+ " and transactionmasterid =" + body.getTransactionmaster().getTransactionmasterid();
		utility.executeDMLQueryOnPool(query.toString(), params, request);

		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("message", "Record Saved Successfully ");
		jsonObject.addProperty("data", voucherno);

		return jsonObject.toString();
	}

	@Transactional
	public String postRouteAreaLink(List<RoutearealinkBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Object[] params = new Object[] {};
		int result = 0;
		String finalResultQuery = "";
		StringBuilder insertQuery = new StringBuilder(
				"insert into routearealink(orgid , oprid,  routeid , areaid , comment , createdby , createdon , lastoperation , active , sequenceno) values");
		// Delete from route area link
		JsonObject jsonObject = new JsonObject();
		String query = "delete from routearealink where orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and routeid =" + body.get(0).getRouteid();
		result = utility.executeDMLQueryOnPool(query.toString(), params, request);

		if (body.get(0).getDeleteflag() == 0) {
			// logic for sequence no
			query = "select (ifnull(max(sequenceno),0)) sequenceno  from routearealink where orgid ="
					+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid") + " and routeid ="
					+ body.get(0).getRouteid();
			JsonArray data = utility.executeQueryOnPool(query, request);
	
			int sequenceno = Integer.parseInt(data.get(0).getAsJsonObject().get("sequenceno").toString());
	
			if (result >= 0) {
	
				for (RoutearealinkBody routearealinkbody : body) {
					sequenceno = sequenceno + 1;
					insertQuery.append("(" + adUserAccessToken.get("orgid") + "," + adUserAccessToken.get("oprid") + ","
							+ body.get(0).getRouteid() + "," + routearealinkbody.getAreaid() + ",'"
							+ routearealinkbody.getComment() + "' ," + routearealinkbody.getCreatedby() + "," + "'"
							+ routearealinkbody.getCreatedon() + "'" + ",'" + routearealinkbody.getLastoperation() + "'"
							+ ",1," + sequenceno);
					insertQuery.append("),");
				}
	
				finalResultQuery = insertQuery.substring(0, insertQuery.length() - 1).toString();
				result = utility.executeDMLQueryOnPool(finalResultQuery, params, request);
			}
		}
		if (result >= 0) {
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("id", body.get(0).getRouteid());
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Saved Successfully ");
		}

		return jsonObject.toString();
	}

	@Transactional
	public void deleteTransactionDetailByMasterId(TrnMasterBody body, HttpServletRequest request) {
		String query = "delete from transactiondetail where orgid =" + body.getTransactionmaster().getOrgid()
				+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
				+ body.getTransactionmaster().getTransactionmasterid();
		Object[] params = new Object[] {};
		utility.executeDMLQueryOnPool(query, params, request);

	}

	@Transactional
	public long postProductLockUnlock(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		long result = 0;
		for (CustomBody customBody : body) {
			// Json object for product master
			JsonObject productJsonObject = new JsonObject();
			// 0-Unlocked 1-locked
			productJsonObject.addProperty("locked", customBody.getLocked());
			// Where clause for update
			JsonObject productJsonObjectWhere = new JsonObject();
			productJsonObjectWhere.addProperty("orgid", adUserAccessToken.get("orgid"));
			productJsonObjectWhere.addProperty("oprid", adUserAccessToken.get("oprid"));
			productJsonObjectWhere.addProperty("productid", customBody.getProductid());
			// Update in productoprdtl
			result = utility.executeCustomDML("productoprdtl", "productoprdtlid", productJsonObject,
					productJsonObjectWhere, customBody.getLastoperation(), request);
		}
		return result;
	}

	@Transactional
	public long postSecondDayCashInvoiceEntry(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		long result = 0;
		for (CustomBody customBody : body) {
			JsonObject jsonObjectTranMaster = new JsonObject();
			jsonObjectTranMaster.addProperty("pending", customBody.getNetamount());

			JsonObject jsonObjectTranMasterWhere = new JsonObject();

			jsonObjectTranMasterWhere.addProperty("transactionmasterid", customBody.getTransactionmasterid());
			jsonObjectTranMasterWhere.addProperty("orgid", adUserAccessToken.get("orgid"));
			jsonObjectTranMasterWhere.addProperty("oprid", adUserAccessToken.get("oprid"));
			// Update in transaction master
			result = utility.executeCustomDML("transactionmaster", "transactionmasterid", jsonObjectTranMaster,
					jsonObjectTranMasterWhere, customBody.getLastoperation(), request);
		}
		return result;
	}

	@Transactional
	public long batchSerialChange(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		StringBuilder stringbuilder = new StringBuilder("update  batch set batchsequence = (case ");
		// updating batch sequence as per batchid and productid
		for (CustomBody customBody : body) {
			stringbuilder.append(" when batchid=").append(customBody.getBatchid()).append(" then ")
					.append("" + customBody.getBatchsequence());
		}

		stringbuilder.append(" else  batchsequence  end ) , modifiedby = " + body.get(0).getModifyby()
				+ " , modifiedon = '" +body.get(0).getModifyon() + "' , lastoperation = '"+body.get(0).getLastoperation()+ "' where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid"));

		Object[] params = new Object[] {};
		return utility.executeDMLQueryOnPool(stringbuilder.toString(), params, request);
	}

	@Transactional
	public long postCreditDebitNoteTransfer(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		long result = 0;
		// updating credit debit status as per transactionmasterid
		for (CustomBody customBody : body) {

			String query = "update transactionmaster set creditdebitstatus = ?" + " , modifyby = ?" + " , modifyon= ?"
					+ "  where  orgid = ?" + " AND oprid = ?" + " AND transactionmasterid = ?";
			Object[] params = new Object[] { customBody.getCreditdebitstatus(), customBody.getModifiedby(),
					customBody.getModifiedon(), adUserAccessToken.get("orgid"), adUserAccessToken.get("oprid"),
					customBody.getTransactionmasterid() };
			utility.executeDMLQueryOnPool(query, params, request);
		}
		return result;
	}

	@Transactional
	public String postDoctorProdSetupSelectAll(MstAgentBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		StringBuilder productQuery = new StringBuilder();
		Object[] params = new Object[] {};

		// delete data for doctor if it already exist
		String query = "delete from doctorproductlink where orgid =" + body.getAgent().getOrgid() + "  and oprid ="
				+ body.getAgent().getOprid() + " and agentid = " + body.getAgent().getAgentid();
		utility.executeDMLQueryOnPool(query, params, request);

		// Select All the products from product master for present oper
		query = "SELECT productid FROM productoprdtl WHERE orgid =" + adUserAccessToken.get("orgid") + " AND oprid ="
				+ adUserAccessToken.get("oprid") + " AND active =1";
		data = utility.executeQueryOnPool(query, request);
		// Append for bulk insert
		if (data.size() > 0) {
			for (JsonElement jsonObjectEach : data) {
				productQuery = productQuery
						.append("(" + adUserAccessToken.get("orgid") + "," + adUserAccessToken.get("oprid") + ","
								+ body.getAgent().getAgentid() + "," + jsonObjectEach.getAsJsonObject().get("productid")
								+ "," + body.getAgent().getCreatedby() + " , CURDATE() , 'INSERT'" + "),");
			}
		}

		// Bulk Insert
		productQuery = new StringBuilder(productQuery.substring(0, productQuery.length() - 1));
		query = "insert into doctorproductlink (\n" + "   orgid\n" + "  ,oprid\n" + "  ,agentid\n" + "  ,productid\n"
				+ "  ,createdby\n" + "  ,createdon\n" + "  ,lastoperation\n" + ") VALUES" + productQuery;
		utility.executeDMLQueryOnPool(query, params, request);

		// Response return doctor id
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getAgent().getAgentid()); // Doctorid
		jsonObject.addProperty("message", "Record Saved Successfully ");
		return jsonObject.toString();
	}

	@Transactional
	public String postDoctorCustSetupSelectAll(MstAgentBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonArray data = new JsonArray();
		Object[] params = new Object[] {};
		StringBuilder customerQuery = new StringBuilder();
		String query = "";
		// delete data for doctor if it already exist
		query = "delete from doctorcustomerlink where orgid =" + body.getAgent().getOrgid() + "  and oprid ="
				+ body.getAgent().getOprid() + " and agentid = " + body.getAgent().getAgentid();
		utility.executeDMLQueryOnPool(query, params, request);

		// Select All the customer from account oprdetail
		query = "SELECT accountid from accountoprdetail " + " WHERE accountoprdetail.orgid = "
				+ adUserAccessToken.get("orgid") + " AND accountoprdetail.oprid =" + adUserAccessToken.get("oprid")
				+ " AND identity = 1";
		data = utility.executeQueryOnPool(query, request);
		// Append for bulk insert
		if (data.size() > 0) {
			for (JsonElement jsonObjectElement : data) {
				customerQuery = customerQuery.append("(" + adUserAccessToken.get("orgid") + ","
						+ adUserAccessToken.get("oprid") + "," + body.getAgent().getAgentid() + ","
						+ jsonObjectElement.getAsJsonObject().get("accountid") + "," + body.getAgent().getCreatedby()
						+ " ,CURDATE() , 'INSERT'" + "),");
			}
		}

		// Bulk Insert
		customerQuery = new StringBuilder(customerQuery.substring(0, customerQuery.length() - 1));
		query = "insert into doctorcustomerlink (\n" + "   orgid\n" + "  ,oprid\n" + "  ,agentid\n" + "  ,accountid\n"
				+ "  ,createdby\n" + "  ,createdon\n" + "  ,lastoperation\n" + ") VALUES" + customerQuery;
		utility.executeDMLQueryOnPool(query, params, request);

		// Response
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getAgent().getAgentid()); // Doctor id
		jsonObject.addProperty("message", "Record Saved Successfully ");

		return jsonObject.toString();
	}

	@Transactional
	public String postInvoiceSeries(InvoiceseriesBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Saved Successfully ");
		
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		// Condition on is default in invoice series only one is is default = 'Y'
		String query = "update invoiceseries set isdefault = 'N' where isdefault = 'Y' and orgid = ? and oprid =? and storeid = ?";
		Object[] params = new Object[] { adUserAccessToken.get("orgid"), adUserAccessToken.get("oprid"),
				body.getStoreid() };
		
		int result = utility.executeDMLQueryOnPool(query, params, request);
		
		if (result >= 0) {
			JsonObject invoiceseries = formater.serializeObject(body);
			
			if (invoiceseries.get("lastoperation").getAsString().equals("INSERT")) {
				String invoiceseriesid = utility.executeIdGenerationProcedure(invoiceseries.get("orgid").getAsString(),
						invoiceseries.get("oprid").getAsString(),  "invoiceseries",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				invoiceseriesid = new JSONObject(invoiceseriesid).get("id").toString();
				
				invoiceseries.addProperty("invoiceseriesid", invoiceseriesid);
			}
			response.addProperty("id", invoiceseries.get("invoiceseriesid").getAsString());
			
			utility.executeCustomDML("invoiceseries", "invoiceseriesid", invoiceseries, null,
					invoiceseries.get("lastoperation").getAsString(), request);
		}
		
		return response.toString();
	}

	@Transactional
	public String checkandinsertbatch(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String jsonobjectbatch = "";
		String batchid = "0";

		String query = " select batchid countbatch from batch where productid ="
				+ body.getTransactionmaster().getTransactiondetail().get(0).getProductid() + " and batchno = '"
				+ body.getTransactionmaster().getTransactiondetail().get(0).getBatchno() + "'";
		JsonArray data = utility.executeQueryOnPool(query, request);
		if (data.size() > 0) {

			batchid = data.get(0).getAsJsonObject().get("countbatch").getAsString();

		} else {
			JsonObject batch = new JsonObject();
			JsonObject batchWhere = new JsonObject();
			batch.addProperty("orgid", adUserAccessToken.get("orgid"));
			batch.addProperty("oprid", adUserAccessToken.get("oprid"));

			jsonobjectbatch = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid").toString(),
					adUserAccessToken.get("oprid").toString(), "batch",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());

			batchid = new JSONObject(jsonobjectbatch).getString("id");

			batch.addProperty("batchid", batchid);
			batch.addProperty("productid", body.getTransactionmaster().getTransactiondetail().get(0).getProductid());
			batch.addProperty("batchno", body.getTransactionmaster().getTransactiondetail().get(0).getBatchno());
			if (body.getTransactionmaster().getTransactiondetail().get(0).getExpiry() != null) {
				batch.addProperty("expirydate",
						body.getTransactionmaster().getTransactiondetail().get(0).getExpiry().toString());
			}

			batch.addProperty("displaypacking",
					body.getTransactionmaster().getTransactiondetail().get(0).getDisplaypacking());
			batch.addProperty("pack", body.getTransactionmaster().getTransactiondetail().get(0).getProductpack());
			batch.addProperty("mrp", body.getTransactionmaster().getTransactiondetail().get(0).getMrp());
			batch.addProperty("salerate", body.getTransactionmaster().getTransactiondetail().get(0).getSalerate());
			batch.addProperty("purchaserate",
					body.getTransactionmaster().getTransactiondetail().get(0).getPurchaserate());
			batch.addProperty("block", 0);
			batch.addProperty("finyearcode", 0);
			batch.addProperty("bnew", 0);
			batch.addProperty("active", 1);
			batch.addProperty("createdby", body.getTransactionmaster().getCreatedby());
			batch.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
			batch.addProperty("lastoperation", "INSERT");
			batch.addProperty("trade", body.getTransactionmaster().getTransactiondetail().get(0).getTrade());
			batch.addProperty("batchsequence", 1);

			utility.executeCustomDML("batch", "batchid", batch, batchWhere, "INSERT", request);
		}
		return batchid;
	}

	public BigDecimal checkandinsertrate(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "select  rateid FROM rate where orgid =" + adUserAccessToken.get("orgid") + "  and oprid ="
				+ adUserAccessToken.get("oprid") + " and mrp ="
				+ body.getTransactionmaster().getTransactiondetail().get(0).getMrp() + " and traderate ="
				+ body.getTransactionmaster().getTransactiondetail().get(0).getTrade();
		JsonArray data = utility.executeQueryOnPool(query, request);
		String jsonobjectrate = "";
		if (data.size() > 0) {

		} else {
			JsonObject rate = new JsonObject();
			rate.addProperty("orgid", adUserAccessToken.get("orgid"));
			rate.addProperty("oprid", adUserAccessToken.get("oprid"));

			jsonobjectrate = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid").toString(),
					adUserAccessToken.get("oprid").toString(), "rate", request.getHeader("authorization").split(" ")[1],
					request.getRequestURL().toString());

			String rateid = new JSONObject(jsonobjectrate).getString("id");
			rate.addProperty("rateid", rateid);
			rate.addProperty("productid", body.getTransactionmaster().getTransactiondetail().get(0).getProductid());
			rate.addProperty("mrp", body.getTransactionmaster().getTransactiondetail().get(0).getMrp());
			rate.addProperty("traderate", body.getTransactionmaster().getTransactiondetail().get(0).getTrade());
			rate.addProperty("purchaserate",
					body.getTransactionmaster().getTransactiondetail().get(0).getPurchaserate());
			rate.addProperty("salerate", body.getTransactionmaster().getTransactiondetail().get(0).getSalerate());

		}

		return null;
	}

	public long increaseBatchStock(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";

		query = "select transactiondetailid , qty ,  free , orgid, oprid, batchid, storeid, linkid from transactiondetail where orgid ="
				+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
				+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
		JsonArray data = utility.executeQueryOnPool(query, request);

		JsonObject obj = data.get(0).getAsJsonObject();
		query = "update transactionstock set closing = ifnull(closing,0) + (? + ?)" + " ,rsqcn = ifnull(rsqcn,0) + (? + ?)"
				+ "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? " + " and storeid = ?";
		Object[] params = new Object[] { data.get(0).getAsJsonObject().get("qty").getAsInt(),
				obj.get("free").getAsLong(),
				obj.get("qty").getAsLong(),
				obj.get("free").getAsLong(),
				obj.get("orgid").getAsString(),
				obj.get("oprid").getAsString(),
				obj.get("batchid").getAsString(),
				obj.get("storeid").getAsString() 
		};
		
		return transactionService.updateTransactionStock(query, params, request);
	}

	@Transactional
	public String postexpirytoclaimconversion(CustomBody body, JsonArray data, HttpServletRequest request) {
		int result = 0;
		String query = "";
		
		// Json Object for transaction master
		JsonObject transactionmaster = new JsonObject();
		JsonObject jsonresult = new JsonObject();
		
		transactionmaster.addProperty("orgid", new BigDecimal(body.getOrgid()));
		transactionmaster.addProperty("oprid", new BigDecimal(body.getOprid()));
		// Generating master id
		String transactionmasterid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
				body.getOprid().toString(), "transactionmaster", request.getHeader("authorization").split(" ")[1],
				request.getRequestURL().toString());
		transactionmasterid = new JSONObject(transactionmasterid).getString("id");

		transactionmaster.addProperty("transactionmasterid", new BigDecimal(transactionmasterid));
		transactionmaster.addProperty("voucherno", "0");
		transactionmaster.addProperty("voucherdate", body.getVoucherdate().toString());
		transactionmaster.addProperty("paymentmode", 2);
		transactionmaster.addProperty("grossamount", 0);
		transactionmaster.addProperty("createdby", body.getCreatedby());
		transactionmaster.addProperty("createdon", body.getCreatedon().toString());
		transactionmaster.addProperty("vouchermode", 1);
		transactionmaster.addProperty("active", 0);
		transactionmaster.addProperty("vouchertype", body.getVouchertype());
		transactionmaster.addProperty("agencyid", body.getAgencyid());
		transactionmaster.addProperty("agencygroupid", body.getAgencygroupid());
		JsonObject jsonObjectWhere = new JsonObject();
		utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, jsonObjectWhere,
				"INSERT", request);

		StringBuilder insertQuery = new StringBuilder();

		insertQuery = insertQuery.append("insert into transactiondetail (\r\n" + "   orgid\r\n" + "  ,oprid\r\n"
				+ "  ,transactionmasterid\r\n" + "  ,transactiondetailid\r\n" +

				"  ,voucherno\r\n" + "  ,voucherdate\r\n" + "  ,productid\r\n" + "  ,productname\r\n"
				+ "  ,productpack\r\n" + "  ,qty\r\n" + "  ,free\r\n" + "  ,batchno\r\n" + "  ,mrp\r\n"
				+ "  ,salerate\r\n" + "  ,itemvalue\r\n" + "  ,expiry\r\n" + "  ,rate\r\n" + "  ,srno\r\n"
				+ "  ,taxcode\r\n" +

				"  ,gstvalue\r\n" + "  ,hsncode\r\n" + "  ,gstpercent\r\n" + "  ,igstpercent\r\n" + "  ,cgstpercent\r\n"
				+ "  ,sgstpercent\r\n" + "  ,igstamount\r\n" + "  ,cgstamount\r\n" + "  ,sgstamount\r\n"
				+ "  ,gsttp\r\n" + "  ,cesspercent\r\n" + "  ,cessamount\r\n" + "  ,additionalcesspercent\r\n"
				+ "  ,additionalcessamount\r\n" + "  ,companycode\r\n" + "  ,linkid\r\n" +

				"  ,createdby\r\n" + "  ,createdon\r\n" + "  ,modifyby\r\n" + "  ,modifyon\r\n" +

				"  ,lastoperation\r\n" + "  ,active\r\n" + "  ,tnew\r\n" + "  ,vouchertype\r\n" + "  ,rateid\r\n"
				+ "  ,batchid\r\n" + "  ,vouchermode\r\n" + "  ,displaypacking\r\n" + "  ,storeid\r\n" +

				"  ,purchaserate\r\n" + "  ,trade\r\n" +

				"  ,ugstpercent\r\n" + "  ,ugstamount , allowcashdiscount  \r\n" + ") VALUES");
		// Adding the entries to transaction detail
		for (JsonElement jsonElement : data.getAsJsonArray()) {
			String transactiondetailid = utility.executeIdGenerationProcedure(body.getOrgid().toString(),
					body.getOprid().toString(), "transactiondetail", request.getHeader("authorization").split(" ")[1],
					request.getRequestURL().toString());

			transactiondetailid = new JSONObject(transactiondetailid).getString("id");

			insertQuery.append("(" + body.getOrgid() + "," + body.getOprid() + "," + new BigDecimal(transactionmasterid)
					+ "," + new BigDecimal(transactiondetailid) + ",0,'" + body.getVoucherdate() + "',"
					+ jsonElement.getAsJsonObject().get("productid") + ","
					+ jsonElement.getAsJsonObject().get("productname") + ","
					+ jsonElement.getAsJsonObject().get("productpack") + " ,ifnull("
					+ jsonElement.getAsJsonObject().get("qty") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("free") + ",0) ," + jsonElement.getAsJsonObject().get("batchno")
					+ " ,ifnull(" + jsonElement.getAsJsonObject().get("mrp") + ",0) ,ifnull("
					+ jsonElement.getAsJsonObject().get("srate") + ",0),"
					+ jsonElement.getAsJsonObject().get("itemvalue") + "," + jsonElement.getAsJsonObject().get("expiry")
					+ ",ifnull(" + jsonElement.getAsJsonObject().get("rate") + ",0),"
					+ jsonElement.getAsJsonObject().get("srno") + "," + jsonElement.getAsJsonObject().get("taxcode")
					+ ", ifnull(" + jsonElement.getAsJsonObject().get("gstvalue") + ",0),"
					+ jsonElement.getAsJsonObject().get("hsncode") + ",ifnull("
					+ jsonElement.getAsJsonObject().get("gstpercent") + ",0) ," + " ifnull("
					+ jsonElement.getAsJsonObject().get("igstpercent") + ",0)," + " ifnull("
					+ jsonElement.getAsJsonObject().get("cgstpercent") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("sgstpercent") + ",0),"
					+ jsonElement.getAsJsonObject().get("igstamount") + ",ifnull("
					+ jsonElement.getAsJsonObject().get("cgstamount") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("sgstamount") + ",0),"
					+ jsonElement.getAsJsonObject().get("gsttp") + ",ifnull("
					+ jsonElement.getAsJsonObject().get("cesspercent") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("cessamount") + ",0) , ifnull("
					+ jsonElement.getAsJsonObject().get("additionalcesspercent") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("additionalcessamount") + ",0), ifnull("
					+ jsonElement.getAsJsonObject().get("companycode") + ",0),"
					+ jsonElement.getAsJsonObject().get("linkid") + ",344 ,'" + body.getCreatedon() + "',"
					+ body.getModifyby() + "," + body.getModifyon() + ",'" + body.getLastoperation() + "',0,0,'"
					+ body.getVouchertype() + "'," + jsonElement.getAsJsonObject().get("rateid") + ","
					+ jsonElement.getAsJsonObject().get("batchid") + ",0,"
					+ jsonElement.getAsJsonObject().get("displaypacking") + ",1 ,"
					+ jsonElement.getAsJsonObject().get("purchaserate") + ",ifnull("
					+ jsonElement.getAsJsonObject().get("trade") + ",0),0,0," +jsonElement.getAsJsonObject().get("allowcashdiscount")+"),");

		}
		// Bulk insert in single query
		insertQuery = new StringBuilder(insertQuery.substring(0, insertQuery.length() - 1));
		Object[] params = new Object[] {};
		result = utility.executeDMLQueryOnPool(insertQuery.toString(), params, request);
		
		if (result >= 0) {
			query = "select * from transactiondetail where transactionmasterid =" + transactionmasterid;
			data = utility.executeQueryOnPool(query, request);
			jsonresult.addProperty("code", 200);
			jsonresult.addProperty("status", "Success");
			jsonresult.addProperty("id", transactionmasterid);
			jsonresult.add("data", data);
		}
		return jsonresult.toString();
	}

	public int postUpdateAddCRLimit(CustomBody body, HttpServletRequest request) {
		int update = 0;
		String query = "UPDATE accountoprdetail\r\n" + "SET addcrdays = ifnull(addcrdays, 0) + ?,"
				+ "    addbilllimit = ifnull(addbilllimit, 0) + ?," + "    addcrlimit = ifnull(addcrlimit, 0) + ?"
				+ " where  orgid = ?" + " AND oprid = ?" + " AND accountid = ?";
		Object[] params = new Object[] { body.getAddcrdays(), body.getAddbilllimit(), body.getAddcrlimit(),
				body.getOrgid(), body.getOprid(), body.getAccountid()};
		update = utility.executeDMLQueryOnPool(query, params, request);

		return update;
	}

	/*********************************************************************************
	 * Author  : Kushal Kadu  
	 * Date    : 04/04/2022 
	 * Input   : Header = Data for  transaction master  detail : details for transaction detail 
	 * Output  : Response depending upon conditions  
	 * Purpose : convert sale order to sale invoice 
	 * Comment : postSaleOrderToSaleInvoice
	 ********************************************************************************/
	@Transactional
	public String postSaleOrderToSaleInvoice(List<String> transactionorders, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Map<String,String> setup = new HashMap<>();
		String stockShort = "";
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		String transactionordermasterid = transactionorders.get(0);
		
		String query = "SELECT d.sysparimeterkey code, d.spmvalname name FROM setupparamdtl d \r\n" + 
				"where d.setupparammasterid in(23,7) \r\n" + 
				"and d.sysparimeterkey in ('WS_SCHMISMACH','WS_QTYMISCMATCH','WS_SOBPREFRE','WS_ALNEAREXP','WS_BLQTYSLORD','WG_EXPWNDAYS')";
		JsonArray setupparam = utility.executeQueryOnPool(query, request);
		for (JsonElement element : setupparam) {
			setup.put(element.getAsJsonObject().get("code").getAsString(), element.getAsJsonObject().get("name").getAsString());
		}
		
		// fetch data from transaction order master
		query = "SELECT * FROM transactionordermaster where transactionordermasterid = "
				+ transactionordermasterid;
		JsonArray data = utility.executeQueryOnPool(query, request);

		if (data.size() > 0) {
			JsonObject orderMaster = data.get(0).getAsJsonObject().deepCopy();
			
			String customerState = "";
			String sellerState = "";
			boolean iscusut = false;
			boolean isselut = false;
			String gstType = "";
			query = "SELECT d.accountid accountid, d.gsttype gsttype, s.stateno stateno, s.isut isut \r\n" + 
					"FROM accountoprdetail d \r\n" + 
					"left outer join state s on s.stateid = d.stateid \r\n" + 
					"where d.accountid = " + orderMaster.get("accountid").getAsLong();
			JsonArray customerdata = utility.executeQueryOnPool(query, request);
			if (customerdata.size() > 0) {
				JsonObject obj = customerdata.get(0).getAsJsonObject();
				customerState = RestUtil.getAsString(obj.get("stateno"));
				if (RestUtil.getAsString(obj.get("isut")).equals("1"))
					iscusut = true;
			}
			
			query = "SELECT o.adougsttype gsttype, st.stateno stateno, st.isut isut \r\n" + 
					"FROM adoprunitmst o \r\n" + 
					"left outer join state st on st.stateid = o.adoumstateid \r\n" + 
					"where o.adoumid = " + adUserAccessToken.get("oprid");
			JsonArray oprdata = utility.executeQueryOnPool(query, request);
			if (oprdata.size() > 0) {
				JsonObject obj = oprdata.get(0).getAsJsonObject();
				
				gstType = RestUtil.getAsString(obj.get("gsttype"));
				sellerState = RestUtil.getAsString(obj.get("stateno"));
				if (RestUtil.getAsString(obj.get("isut")).equals("1"))
					isselut = true;
			}
			
			// update in transaction master
			JsonObject transactionmaster = new JsonObject();
			
			// Generating transaction master id
			String transactionmasterid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid").toString(),
					adUserAccessToken.get("oprid").toString(), "transactionmaster", token,
					request.getRequestURL().toString());

			transactionmasterid = new JSONObject(transactionmasterid).getString("id");

			LocalDate dateObj = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dateObj.format(formatter);

			BigDecimal totalGross = BigDecimal.ZERO;
			BigDecimal totalNet = BigDecimal.ZERO;
			BigDecimal totalGst = BigDecimal.ZERO;
			BigDecimal totalCgst = BigDecimal.ZERO;
			BigDecimal totalSgst = BigDecimal.ZERO;
			BigDecimal totalIgst = BigDecimal.ZERO;
			BigDecimal totalUgst = BigDecimal.ZERO;
			//BigDecimal totalCess = BigDecimal.ZERO;
			
			// fetch data from transaction order detail
			JsonArray detailArray = new JsonArray();
			for (String transactionorderid : transactionorders) {
				query = "SELECT * FROM transactionorderdetail where transactionordermasterid = "
						+ transactionorderid;
				data = utility.executeQueryOnPool(query, request);
				for (int i = 0; i < data.size(); i++) {
					detailArray.add(data.get(i));
				}
			}

			JsonArray transactiondtl = new JsonArray();
			if (detailArray.size() > 0) {
				Calendar fromdate = Calendar.getInstance();
				Calendar todate = Calendar.getInstance();
				Calendar currentdate = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (JsonElement jsonElement : detailArray) {
					JsonObject orderDetail = jsonElement.getAsJsonObject();
					
					// Check for scheme
					query = "select p.productschemeid schemeid, p.schemetype schemetype, p.qtyone qtyone, \r\n" + 
							"p.freeone freeone, p.qtytwo qtytwo, p.freetwo freetwo, p.qtythree qtythree, \r\n" + 
							"p.freethree freethree \r\n" + 
							"from productscheme p \r\n" + 
							"where p.orgid = " + adUserAccessToken.get("orgid") + " \r\n" + 
							"and p.oprid = " + adUserAccessToken.get("oprid") + " \r\n" + 
							"and p.productid = "  + RestUtil.getAsNumber(orderDetail.get("productid"));
					
					JsonArray schemedata = utility.executeQueryOnPool(query, request);
					if (schemedata.size() > 0) {
						JsonObject obj = schemedata.get(0).getAsJsonObject();
						BigDecimal qty1 = RestUtil.getAsBigDecimal(obj.get("qtyone"));
						BigDecimal qty2 = RestUtil.getAsBigDecimal(obj.get("qtytwo"));
						BigDecimal qty3 = RestUtil.getAsBigDecimal(obj.get("qtythree"));
						BigDecimal free1 = RestUtil.getAsBigDecimal(obj.get("freeone"));
						BigDecimal free2 = RestUtil.getAsBigDecimal(obj.get("freetwo"));
						BigDecimal free3 = RestUtil.getAsBigDecimal(obj.get("freethree"));
						
						BigDecimal orderqty = RestUtil.getAsBigDecimal(orderDetail.get("qty"));
						BigDecimal freeqty = BigDecimal.ZERO;
						
						if (orderqty.compareTo(qty1) < 0) {
							orderDetail.addProperty("free", freeqty);
						} else if (orderqty.compareTo(qty1) >= 0 && orderqty.compareTo(qty2) < 0) {
							freeqty = free1;
						} else if (orderqty.compareTo(qty2) >= 0 && orderqty.compareTo(qty3) < 0) {
							freeqty = free2;
						} else if (orderqty.compareTo(qty3) >= 0) {
							freeqty = free3;
						}
						
						if (RestUtil.getAsBigDecimal(orderDetail.get("free")).compareTo(freeqty) != 0) {
							if (setup.get("WS_SCHMISMACH").equals("As Per Scheme")) {
								orderDetail.addProperty("free", freeqty);
							} else if (setup.get("WS_SCHMISMACH").equals("Reject")) {
								// Delete from transaction order
								query = "delete from transactionorderdetail where transactionorderdetailid = ?";
								Object[] params = new Object[] { RestUtil.getAsNumber(orderDetail.get("transactionorderdetailid")) };
								utility.executeDMLQueryOnPool(query, params, request);
								
								continue;
							}
						}
					}
					
					query = "select s.quantity stock, b.batchid batchid, b.batchno batch, b.serialno serialno, b.mfgdate mfgdate, b.expirydate expirydate, \r\n" + 
							"b.displaypacking displaypacking, b.pack packing, b.unitcode unitcode, r.mrp mrp, r.traderate traderate, \r\n" + 
							"r.purchaserate purchaserate, r.salerate salerate, p.hsncode hsncode, t.taxvalue taxvalue, t.igst igst, t.cgst cgst, t.sgst sgst, t.ugst ugst, \r\n" + 
							"p.agencicyid agencyid, p.agencygroupid agencygroupid \r\n" + 
							"from transactionstock s \r\n" + 
							"left outer join batch b on b.batchid = s.batchid and b.active = 1\r\n" + 
							"left outer join rate r on r.rateid = s.rateid and r.active = 1 \r\n" + 
							"left outer join product p on p.productid = s.productid and p.active = 1 \r\n" + 
							"left outer join tax t on t.taxid = p.taxid and t.active = 1 \r\n" + 
							"where s.active = 1 \r\n" + 
							"and s.quantity > 0 \r\n" +
							"and s.orgid = " + adUserAccessToken.get("orgid") + " \r\n" + 
							"and s.oprid = " + adUserAccessToken.get("oprid") + " \r\n" + 
							"and s.productid = " + RestUtil.getAsNumber(orderDetail.get("productid")) + " \r\n";
					
					if (setup.get("WS_SOBPREFRE").equals("FIFO")) {
						query = query + "order by b.serialno";
					} else {
						query = query + "order by s.quantity desc";
					}
					
					JsonArray batchdata = utility.executeQueryOnPool(query, request);
					
					BigDecimal orderQty = BigDecimal.ZERO;
					BigDecimal freeQty = BigDecimal.ZERO;
					Long agencyid = 0L;
					Long agencygroupid = 0L;
					Map<String, JsonObject> batches = new HashMap<>();
					int count = 1;
					
					for (JsonElement element : batchdata) {
						JsonObject obj = element.getAsJsonObject();
						if (orderDetail.get("qty").isJsonNull())
							orderDetail.addProperty("qty", BigDecimal.ZERO);
						if (orderDetail.get("free").isJsonNull())
							orderDetail.addProperty("free", BigDecimal.ZERO);
						
						orderQty = orderDetail.get("qty").deepCopy().getAsBigDecimal();
						agencyid = (Long) RestUtil.getAsNumber(obj.get("agencyid"));
						agencygroupid = (Long) RestUtil.getAsNumber(obj.get("agencygroupid"));
						
						if (!obj.get("expirydate").isJsonNull()) {
							try {
								fromdate.setTime(sdf.parse(obj.get("expirydate").getAsString()));
								todate.setTime(sdf.parse(obj.get("expirydate").getAsString()));
								if (currentdate.after(todate)) {
									break;
								} else if (setup.get("WS_ALNEAREXP").equals("Yes") && Integer.parseInt(setup.get("WG_EXPWNDAYS")) > 0) {
									fromdate.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(setup.get("WG_EXPWNDAYS")));
									
									if (currentdate.after(fromdate) && currentdate.before(todate)) {
										break;
									}
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						
						if (RestUtil.getAsBigDecimal(obj.get("stock")).compareTo(orderQty.add(RestUtil.getAsBigDecimal(orderDetail.get("free")))) >= 0) {
							orderDetail.addProperty("qty", BigDecimal.ZERO);
							freeQty = orderDetail.get("free").deepCopy().getAsBigDecimal();
							orderDetail.addProperty("free", BigDecimal.ZERO);
						} else if (RestUtil.getAsBigDecimal(obj.get("stock")).compareTo(orderQty) >= 0) {
							orderDetail.addProperty("qty", BigDecimal.ZERO);
						} else {
							orderQty = RestUtil.getAsBigDecimal(obj.get("stock"));
							orderDetail.addProperty("qty", orderDetail.get("qty").deepCopy().getAsBigDecimal().subtract(orderQty));
						}
						
						JsonObject batch = new JsonObject();
						batch.addProperty("batchid", RestUtil.getAsString(obj.get("batchid")));
						batch.addProperty("batch", RestUtil.getAsString(obj.get("batch")));
						batch.addProperty("orderqty", orderQty);
						batch.addProperty("freeqty", freeQty);
						batch.addProperty("mrp", RestUtil.getAsString(obj.get("mrp")));
						batch.addProperty("purchaserate", RestUtil.getAsString(obj.get("purchaserate")));
						batch.addProperty("salerate", RestUtil.getAsString(obj.get("salerate")));
						batch.addProperty("traderate", RestUtil.getAsString(obj.get("traderate")));
						batch.addProperty("displaypacking", RestUtil.getAsString(obj.get("displaypacking")));
						batch.addProperty("expirydate", obj.get("expirydate").isJsonNull() ? null : obj.get("expirydate").getAsString());
						batch.addProperty("hsncode", RestUtil.getAsString(obj.get("hsncode")));
						batch.addProperty("taxvalue", RestUtil.getAsString(obj.get("taxvalue")));
						batch.addProperty("igst", RestUtil.getAsString(obj.get("igst")));
						batch.addProperty("cgst", RestUtil.getAsString(obj.get("cgst")));
						batch.addProperty("sgst", RestUtil.getAsString(obj.get("sgst")));
						batch.addProperty("ugst", RestUtil.getAsString(obj.get("ugst")));
						
						batches.put("" + count, batch);
						
						if (RestUtil.getAsBigDecimal(orderDetail.get("qty")).compareTo(BigDecimal.ZERO) == 0 && 
								RestUtil.getAsBigDecimal(orderDetail.get("free")).compareTo(BigDecimal.ZERO) == 0)
							break;
						
						count++;
					}

					boolean deleteFlag = false;
					if (RestUtil.getAsBigDecimal(orderDetail.get("qty")).compareTo(BigDecimal.ZERO) > 0) {
						if (stockShort.equals(""))
							stockShort = "Following products are quantity mismatch or stock shortage\n";
						
						if (setup.get("WS_QTYMISCMATCH").equals("Issue Available")) {
							if (setup.get("WS_BLQTYSLORD").equals("Transfer to Short supply")) {
								// Add to short supply
								
								// Generate id for transaction detail
								String shortsupplyid = utility.executeIdGenerationProcedure(
										adUserAccessToken.get("orgid").toString(), adUserAccessToken.get("oprid").toString(),
										"shortsupply", request.getHeader("authorization").split(" ")[1],
										request.getRequestURL().toString());
								shortsupplyid = new JSONObject(shortsupplyid).getString("id");
								
								// JSON Object for short supply
								JsonObject shortSupply = new JsonObject();
								shortSupply.addProperty("orgid", adUserAccessToken.get("orgid"));
								shortSupply.addProperty("oprid", adUserAccessToken.get("oprid"));
								shortSupply.addProperty("shortsupplyid", new BigDecimal(shortsupplyid));
								shortSupply.addProperty("orderno", 0);
								shortSupply.addProperty("orderdate", RestUtil.getAsString(orderDetail.get("voucherdate")));
								shortSupply.addProperty("accountid", RestUtil.getAsNumber(orderMaster.get("accountid")));
								shortSupply.addProperty("agencyid", agencyid);
								shortSupply.addProperty("agencygroupid", agencygroupid);
								shortSupply.addProperty("productid", RestUtil.getAsString(orderDetail.get("productid")));
								shortSupply.addProperty("purchasepack", RestUtil.getAsBigDecimal(orderDetail.get("productpack")));
								shortSupply.addProperty("displaypacking", RestUtil.getAsString(orderDetail.get("displaypacking")));
								shortSupply.addProperty("qty", RestUtil.getAsBigDecimal(orderDetail.get("qty")));
								shortSupply.addProperty("salerate", RestUtil.getAsBigDecimal(orderDetail.get("salerate")));
								shortSupply.addProperty("itemvalue", RestUtil.getAsBigDecimal(orderDetail.get("qty"))
										.multiply(RestUtil.getAsBigDecimal(orderDetail.get("salerate"))).setScale(4, RoundingMode.HALF_UP));
								shortSupply.addProperty("active", 0);
								shortSupply.addProperty("createdby", RestUtil.getAsNumber(orderDetail.get("createdby")));
								shortSupply.addProperty("createdon", date);
								shortSupply.addProperty("lastoperation", "INSERT");
								
								// Insert into short supply
								utility.executeCustomDML("shortsupply", "shortsupplyid", shortSupply, null,
										"INSERT", request);
								
								stockShort = stockShort + RestUtil.getAsString(orderDetail.get("productname")) + " - " + RestUtil.getAsBigDecimal(orderDetail.get("qty")) + "\n";
							} else {
								deleteFlag = true;
								
								stockShort = stockShort + RestUtil.getAsString(orderDetail.get("productname")) + " - " + RestUtil.getAsBigDecimal(orderDetail.get("qty")) + "\n";
							}
						} else {
							deleteFlag = true;
						}
					}
					
					if (deleteFlag) {
						// Delete from transaction order
						query = "delete from transactionorderdetail where transactionorderdetailid = ?";
						Object[] params = new Object[] { RestUtil.getAsNumber(orderDetail.get("transactionorderdetailid"))};
						utility.executeDMLQueryOnPool(query, params, request);
						
						continue;
					}
					
					for (int i = 1; i <= batches.size(); i++) {
						JsonObject obj = batches.get("" + i);
						BigDecimal grossAmount = RestUtil.getAsBigDecimal(obj.get("salerate")).multiply(RestUtil.getAsBigDecimal(obj.get("orderqty"))).setScale(4, RoundingMode.HALF_UP);
						
						// Calculate GST
						BigDecimal cgst = BigDecimal.ZERO;
						BigDecimal sgst = BigDecimal.ZERO;
						BigDecimal igst = BigDecimal.ZERO;
						BigDecimal ugst = BigDecimal.ZERO;
						BigDecimal cgstAmt = BigDecimal.ZERO;
						BigDecimal sgstAmt = BigDecimal.ZERO;
						BigDecimal igstAmt = BigDecimal.ZERO;
						BigDecimal ugstAmt = BigDecimal.ZERO;
						
						BigDecimal gstAmount = BigDecimal.ZERO;
						if (gstType.equals("2")) {
							if (customerState.equals(sellerState)) {
								if (iscusut && isselut) {
									cgst = RestUtil.getAsBigDecimal(obj.get("cgst"));
									ugst = RestUtil.getAsBigDecimal(obj.get("ugst"));
									cgstAmt = cgst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									ugstAmt = ugst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									gstAmount = cgstAmt.add(ugstAmt);
								} else {
									cgst = RestUtil.getAsBigDecimal(obj.get("cgst"));
									sgst = RestUtil.getAsBigDecimal(obj.get("sgst"));
									cgstAmt = cgst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									sgstAmt = sgst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									gstAmount = cgstAmt.add(sgstAmt);
								}
							} else {
								if (iscusut && isselut) {
									cgst = RestUtil.getAsBigDecimal(obj.get("cgst"));
									ugst = RestUtil.getAsBigDecimal(obj.get("ugst"));
									cgstAmt = cgst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									ugstAmt = ugst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									gstAmount = cgstAmt.add(ugstAmt);
								} else {
									igst = RestUtil.getAsBigDecimal(obj.get("igst"));
									igstAmt = igst.divide(BigDecimal.valueOf(100)).multiply(grossAmount);
									gstAmount = igstAmt;
								}
							}
						}
						
						// Add to header total
						totalGross = totalGross.add(grossAmount);
						totalNet = totalNet.add(grossAmount).add(gstAmount);
						totalGst = totalGst.add(gstAmount);
						totalCgst = totalCgst.add(cgstAmt);
						totalSgst = totalSgst.add(sgstAmt);
						totalIgst = totalIgst.add(igstAmt);
						totalUgst = totalUgst.add(ugstAmt);
						
						// Generate id for transaction detail
						String transactiondetailid = utility.executeIdGenerationProcedure(
								adUserAccessToken.get("orgid").toString(), adUserAccessToken.get("oprid").toString(),
								"transactiondetail", request.getHeader("authorization").split(" ")[1],
								request.getRequestURL().toString());
						transactiondetailid = new JSONObject(transactiondetailid).getString("id");
						
						// JSON Object for transaction detail
						JsonObject jsonObjectDetail = new JsonObject();
						jsonObjectDetail.addProperty("orgid", adUserAccessToken.get("orgid"));
						jsonObjectDetail.addProperty("oprid", adUserAccessToken.get("oprid"));
						jsonObjectDetail.addProperty("transactionmasterid", new BigDecimal(transactionmasterid));
						jsonObjectDetail.addProperty("transactiondetailid", new BigDecimal(transactiondetailid));
						jsonObjectDetail.addProperty("productid", RestUtil.getAsNumber(orderDetail.get("productid")));
						jsonObjectDetail.addProperty("productname", RestUtil.getAsString(orderDetail.get("productname")));
						jsonObjectDetail.addProperty("qty", RestUtil.getAsBigDecimal(obj.get("orderqty")));
						jsonObjectDetail.addProperty("free", RestUtil.getAsBigDecimal(obj.get("freeqty")));
						jsonObjectDetail.addProperty("mrp", RestUtil.getAsBigDecimal(obj.get("mrp")));
						jsonObjectDetail.addProperty("purchaserate", RestUtil.getAsBigDecimal(obj.get("purchaserate")));
						jsonObjectDetail.addProperty("salerate", RestUtil.getAsBigDecimal(obj.get("salerate")));
						jsonObjectDetail.addProperty("trade", RestUtil.getAsBigDecimal(obj.get("traderate")));
						jsonObjectDetail.addProperty("batchid", RestUtil.getAsString(obj.get("batchid")));
						jsonObjectDetail.addProperty("batchno", RestUtil.getAsString(obj.get("batch")));
						jsonObjectDetail.addProperty("productpack", RestUtil.getAsBigDecimal(orderDetail.get("productpack")));
						jsonObjectDetail.addProperty("displaypacking", RestUtil.getAsString(obj.get("displaypacking")));
						jsonObjectDetail.addProperty("expiry", obj.get("expirydate").isJsonNull() ? null : obj.get("expirydate").getAsString());
						jsonObjectDetail.addProperty("hsncode", RestUtil.getAsString(obj.get("hsncode")));
						jsonObjectDetail.addProperty("cgstpercent", cgst);
						jsonObjectDetail.addProperty("sgstpercent", sgst);
						jsonObjectDetail.addProperty("ugstpercent", ugst);
						jsonObjectDetail.addProperty("igstpercent", igst);
						jsonObjectDetail.addProperty("cgstamount", cgstAmt.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("sgstamount", sgstAmt.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("ugstamount", ugstAmt.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("igstamount", igstAmt.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("gstpercent", RestUtil.getAsBigDecimal(obj.get("taxvalue")));
						jsonObjectDetail.addProperty("gstamount", gstAmount.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("itemvalue", grossAmount.setScale(4, RoundingMode.HALF_UP));
						jsonObjectDetail.addProperty("voucherdate", date);
						jsonObjectDetail.addProperty("voucherno", "0");
						jsonObjectDetail.addProperty("vouchertype", "SL");
						jsonObjectDetail.addProperty("vouchermode", 1);
						jsonObjectDetail.addProperty("active", 0);
						jsonObjectDetail.addProperty("createdby", RestUtil.getAsNumber(orderDetail.get("createdby")));
						jsonObjectDetail.addProperty("createdon", date);
						jsonObjectDetail.addProperty("lastoperation", "INSERT");
	
						// Insert into transaction detail
						utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonObjectDetail, null,
								"INSERT", request);
						
						transactiondtl.add(jsonObjectDetail);
					}
				} // End detail loop
				
				// JSON object for transaction master
				transactionmaster.addProperty("orgid", adUserAccessToken.get("orgid").toString());
				transactionmaster.addProperty("oprid", adUserAccessToken.get("oprid").toString());
				transactionmaster.addProperty("transactionmasterid", new BigDecimal(transactionmasterid));
				transactionmaster.addProperty("accountid", RestUtil.getAsNumber(orderMaster.get("accountid")));
				transactionmaster.addProperty("customername", RestUtil.getAsString(orderMaster.get("customername")));
				transactionmaster.addProperty("customeradd1", RestUtil.getAsString(orderMaster.get("customeradd1")));
				transactionmaster.addProperty("customeradd2", RestUtil.getAsString(orderMaster.get("customeradd2")));
				transactionmaster.addProperty("customeradd3", RestUtil.getAsString(orderMaster.get("customeradd3")));
				transactionmaster.addProperty("paymentmode", 1);
				transactionmaster.addProperty("voucherdate", date);
				transactionmaster.addProperty("grossamount", totalGross.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("netamount", totalNet.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("gstamount", totalGst.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("cgstamount", totalCgst.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("sgstamount", totalSgst.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("igstamount", totalIgst.setScale(4, RoundingMode.HALF_UP));
				transactionmaster.addProperty("ugstamount", totalUgst.setScale(4, RoundingMode.HALF_UP));
				
				transactionmaster.addProperty("createdby", RestUtil.getAsNumber(orderMaster.get("createdby")));
				transactionmaster.addProperty("createdon", date);
				transactionmaster.addProperty("vouchermode", 1);
				transactionmaster.addProperty("vouchertype", "SL");
				transactionmaster.addProperty("active", 0);
				transactionmaster.addProperty("lastoperation", "INSERT");

				// Insert for transaction master
				JsonObject jobjmst = new JsonObject();
				utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, jobjmst, "INSERT",
						request);
			}

			transactionmaster.add("transactiondetail", transactiondtl);

			response.addProperty("id", transactionmasterid);
			response.add("data", transactionmaster);

		} else {
			response.addProperty("id", "0");
			response.add("data", new JsonObject());
		}
		response.addProperty("stockshort", stockShort);
		
		return response.toString();
	}

	// Reduce stock for sale
	public synchronized int reduceStockSale(TransactiondetailBody transactiondetailBody, HttpServletRequest request) {
		String whereClause = "", query = "";
		Object[] params = new Object[] {
				transactiondetailBody.getQty() == null ? BigDecimal.ZERO : transactiondetailBody.getQty(),
				transactiondetailBody.getFree() == null ? BigDecimal.ZERO : transactiondetailBody.getFree(),
				transactiondetailBody.getSpecialqty() == null ? BigDecimal.ZERO : transactiondetailBody.getSpecialqty(),
				transactiondetailBody.getQty() == null ? BigDecimal.ZERO : transactiondetailBody.getQty(),
				transactiondetailBody.getFree() == null ? BigDecimal.ZERO : transactiondetailBody.getFree(),
				transactiondetailBody.getSpecialqty() == null ? BigDecimal.ZERO : transactiondetailBody.getSpecialqty(),		
				transactiondetailBody.getOrgid(), 
				transactiondetailBody.getOprid(),
				transactiondetailBody.getProductid(),
				transactiondetailBody.getBatchid(),
				transactiondetailBody.getStoreid()
			};

		query = "UPDATE transactionstock SET closing = ifnull(closing,0) - (? + ? + ?),"
					+ " iqs = ifnull(iqs,0) + ? , ifqs = ifnull(ifqs,0) + ? , ispsq = ifnull(ispsq,0) + ?" 
					+ " WHERE orgid = ?"
					+ " AND oprid = ?" + " AND productid = ? "
					+ " AND batchid = ? " + " AND storeid = ? "
					+ whereClause;
		return transactionService.updateTransactionStock(query, params, request);
	}

	public String postClaimFinalSave(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String response = "";
		Integer voucherNoInt = body.getTransactionmaster().getVoucherno();
		String query = "",whereClause="";
		String voucherno = "";
		TransactionmasterBody transactionmasterBody = body.getTransactionmaster();
		if ((voucherNoInt == 0)) {
			voucherno = utility.executeInvoiceNoGenerationProcedure(transactionmasterBody.getOrgid().toString(), transactionmasterBody.getOprid().toString(), 
					transactionmasterBody.getDoctypeid().toString(), transactionmasterBody.getFiyearid().toString(), transactionmasterBody.getStoreid().toString(), 
					"0", request.getHeader("rightId"), token, request.getRequestURL().toString());
			
			voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
//			query = "SELECT (ifnull(max(cast(voucherno AS unsigned)),0) + 1) voucherno  \n" + "FROM transactionmaster "
//					+ "WHERE orgid = " + body.getTransactionmaster().getOrgid() + " AND oprid ="
//					+ body.getTransactionmaster().getOprid() + "  AND vouchertype = '"+body.getTransactionmaster().getVouchertype()+"' AND active = 1";
			
			// reduce the stock from claim salable
			switch (body.getTransactionmaster().getVouchertype()) {
			// salable
			case "CCS":
			case "OIS":
			case "SIS":
				query = "select transactiondetailid , qty ,  free , orgid, oprid, batchid, storeid, linkid from transactiondetail where orgid ="
						+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
						+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
				JsonArray data = utility.executeQueryOnPool(query, request);

				if (data.size() > 0) {
					for (JsonElement transactiondetailJsonElement : data) {
						JsonObject obj = transactiondetailJsonElement.getAsJsonObject();
						query = "update transactionstock set closing = ifnull(closing,0) - (? + ?)"
								+ " ,ierq = ifnull(ierq,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
								+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
						Object[] params = new Object[] {
								obj.get("qty").getAsLong(),
								obj.get("free").getAsLong(),
								obj.get("qty").getAsLong(),
								obj.get("orgid").getAsString(),
								obj.get("oprid").getAsString(),
								obj.get("batchid").getAsString(),
								obj.get("storeid").getAsString() 
						};
						
						transactionService.updateTransactionStock(query, params, request);
						// customService.reducestock(body.getTransactionmaster().getTransactiondetail().get(0),
						// request);
					}
				}
				break;
			// Expired
			case "CCE":
			case "OIE":
			case "SIE":
			case "LIE":
				query = "select transactiondetailid , qty ,  free , orgid, oprid, productid, batchid, storeid, fiyearid, mrp, salerate , purchaserate, companycode, trade, locationid from transactiondetail where orgid ="
						+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
						+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
				data = utility.executeQueryOnPool(query, request);

				if (data.size() > 0) {
					for (JsonElement transactiondetailJsonElement : data) {

						query = "select * from transactionexpstock where orgid="
								+ body.getTransactionmaster().getOrgid() + " and oprid ="
								+ body.getTransactionmaster().getOprid() + " and productid ="
								+ data.get(0).getAsJsonObject().get("productid").getAsBigDecimal() + " and batchid ="
								+ data.get(0).getAsJsonObject().get("batchid").getAsBigDecimal() + "  and  storeid ="
								+ data.get(0).getAsJsonObject().get("storeid").getAsBigDecimal();
						JsonArray dataStock = utility.executeQueryOnPool(query, request);

						// Check if batch exist in transaction stock
						// If exist update else insert new records
						if (dataStock.size() > 0) {
							// Increase/Inward stock in transaction stock according to store id
							switch (body.getTransactionmaster().getVouchertype()) {
							// Expired
							case "CCE":
								whereClause = "";
								query = "update transactionexpstock set closing = ifnull(closing,0) - ?"
										+ " ,iexp = ifnull(iexp,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
										+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
								Object[] params = new Object[] {
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("orgid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("oprid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("batchid").getAsBigDecimal(),
										transactiondetailJsonElement.getAsJsonObject().get("storeid")
												.getAsBigDecimal() };
								utility.executeDMLQueryOnPool(query, params, request);
								break;
								// OutwardInvoice
							case "OIE":
								whereClause = "";
								query = "update transactionexpstock set closing = ifnull(closing,0) - ?"
										+ " ,iclaim = ifnull(iclaim,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
										+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
								 params = new Object[] {
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("orgid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("oprid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("batchid").getAsBigDecimal(),
										transactiondetailJsonElement.getAsJsonObject().get("storeid")
												.getAsBigDecimal() };
								utility.executeDMLQueryOnPool(query, params, request);
								break;
								// Scrap
							case "SIE":
								whereClause = "";
								query = "update transactionexpstock set closing = ifnull(closing,0) - ?"
										+ " ,iscrap = ifnull(iscrap,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
										+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
								 params = new Object[] {
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("orgid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("oprid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("batchid").getAsBigDecimal(),
										transactiondetailJsonElement.getAsJsonObject().get("storeid")
												.getAsBigDecimal() };
								utility.executeDMLQueryOnPool(query, params, request);
								break;
								// Lost
							case "LIE":
								whereClause = "";
								query = "update transactionexpstock set closing = ifnull(closing,0) - ?"
										+ " ,ilost = ifnull(ilost,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
										+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
								 params = new Object[] {
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("qty").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("orgid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("oprid").getAsInt(),
										transactiondetailJsonElement.getAsJsonObject().get("batchid").getAsBigDecimal(),
										transactiondetailJsonElement.getAsJsonObject().get("storeid")
												.getAsBigDecimal() };
								utility.executeDMLQueryOnPool(query, params, request);
								break;
							}
						}
					}
				}
				break;
			}
		} else {
			query = "select  voucherno from transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid();
			
			JsonArray data = utility.executeQueryOnPool(query, request);
			voucherno = data.get(0).getAsJsonObject().get("voucherno").getAsString();
		}
		
			JsonObject transactionmaster = new JsonObject();
			transactionmaster.addProperty("voucherno", voucherno);
			transactionmaster.addProperty("vouchermode", 2);
			transactionmaster.addProperty("active", 1);
			transactionmaster.addProperty("netamount", body.getTransactionmaster().getNetamount());
			if (!body.getTransactionmaster().getVoucherdate().toString().isEmpty()) {
				transactionmaster.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
			}
			transactionmaster.addProperty("voucherseries", body.getTransactionmaster().getVoucherseries().toString());
			transactionmaster.addProperty("accountid", body.getTransactionmaster().getAccountid());
			transactionmaster.addProperty("grossamount", body.getTransactionmaster().getGrossamount());
			transactionmaster.addProperty("cashdiscountpercent", body.getTransactionmaster().getCashdiscountpercent());
			transactionmaster.addProperty("cashdiscountamount", body.getTransactionmaster().getCashdiscountamount());
			transactionmaster.addProperty("productdiscountamount",
					body.getTransactionmaster().getProductdiscountamount());
			transactionmaster.addProperty("agentid", body.getTransactionmaster().getAgentid());
			transactionmaster.addProperty("netamount", body.getTransactionmaster().getNetamount());
			transactionmaster.addProperty("canflag", body.getTransactionmaster().getCanflag());
			transactionmaster.addProperty("debitnoteamount", body.getTransactionmaster().getDebitnoteamount());
			transactionmaster.addProperty("creditnoteamount", body.getTransactionmaster().getCreditnoteamount());
			transactionmaster.addProperty("addcommissionpercent",
					body.getTransactionmaster().getAddcommissionpercent());
			transactionmaster.addProperty("packingamount", body.getTransactionmaster().getPackingamount());
			transactionmaster.addProperty("commisionpercent", body.getTransactionmaster().getCommisionpercent());
			transactionmaster.addProperty("commissionamount", body.getTransactionmaster().getCommissionamount());
			transactionmaster.addProperty("otheradditionnarration",
					body.getTransactionmaster().getOtheradditionnarration());
			transactionmaster.addProperty("otheradditionamount", body.getTransactionmaster().getOtheradditionamount());
			transactionmaster.addProperty("otherdeductionnarration",
					body.getTransactionmaster().getOtherdeductionnarration());
			transactionmaster.addProperty("otherdeductionamount",
					body.getTransactionmaster().getOtherdeductionamount());
			transactionmaster.addProperty("gstamount", body.getTransactionmaster().getGstamount());
			transactionmaster.addProperty("igstamount", body.getTransactionmaster().getIgstamount());
			transactionmaster.addProperty("cgstamount", body.getTransactionmaster().getCgstamount());
			transactionmaster.addProperty("sgstamount", body.getTransactionmaster().getSpecialdiscountamt());
			transactionmaster.addProperty("cessamount", body.getTransactionmaster().getCessamount());
			transactionmaster.addProperty("tcsamount", body.getTransactionmaster().getTcsamount());
			transactionmaster.addProperty("customername", body.getTransactionmaster().getCustomername());
			transactionmaster.addProperty("customeradd1", body.getTransactionmaster().getCustomeradd1());
			transactionmaster.addProperty("customeradd2", body.getTransactionmaster().getCustomeradd2());
			transactionmaster.addProperty("customeradd3", body.getTransactionmaster().getCustomeradd3());
			transactionmaster.addProperty("pack", body.getTransactionmaster().getPack());
			transactionmaster.addProperty("itemcount", body.getTransactionmaster().getItemcount());
			transactionmaster.addProperty("qtycount", body.getTransactionmaster().getQtycount());
			transactionmaster.addProperty("salevalue", body.getTransactionmaster().getSalevalue());
			transactionmaster.addProperty("exemptedvalue", body.getTransactionmaster().getExemptedvalue());
			transactionmaster.addProperty("nilratedvalue", body.getTransactionmaster().getNilratedvalue());
			transactionmaster.addProperty("nongstvalue", body.getTransactionmaster().getNongstvalue());
			transactionmaster.addProperty("totalgstamount", body.getTransactionmaster().getTotalgstamount());
			transactionmaster.addProperty("roundof", body.getTransactionmaster().getRoundof());
			transactionmaster.addProperty("description", body.getTransactionmaster().getDescription());
			transactionmaster.addProperty("claimno", voucherno);
			if(body.getTransactionmaster().getClaimdate() !=null) {
				transactionmaster.addProperty("claimdate", body.getTransactionmaster().getVoucherdate().toString());
			}
			if (body.getTransactionmaster().getGstnet() != null) {
				transactionmaster.addProperty("gstnet", body.getTransactionmaster().getGstnet());
			}
			transactionmaster.addProperty("creditdebitnarration",
					body.getTransactionmaster().getCreditdebitnarration());
			transactionmaster.addProperty("creditdebitstatus", body.getTransactionmaster().getCreditdebitstatus());
			transactionmaster.addProperty("ratediffdebitcreditnoteflag",
					body.getTransactionmaster().getRatediffdebitcreditnoteflag());
			transactionmaster.addProperty("post", body.getTransactionmaster().getPost());
			transactionmaster.addProperty("gstdebitnoteflag", body.getTransactionmaster().getGstdebitnoteflag());
			transactionmaster.addProperty("mradj", body.getTransactionmaster().getMradj());
			transactionmaster.addProperty("mrpvalue", body.getTransactionmaster().getMrpvalue());
			transactionmaster.addProperty("replacementamount", body.getTransactionmaster().getReplacementamount());
			transactionmaster.addProperty("replacmentclose", body.getTransactionmaster().getReplacmentclose());
			
			JsonObject transactionmasterwhere = new JsonObject();
			transactionmasterwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
			transactionmasterwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
			transactionmasterwhere.addProperty("transactionmasterid",
					body.getTransactionmaster().getTransactionmasterid());

			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster,
					transactionmasterwhere, "UPDATE", request);	
			
			JsonObject transactiondetail = new JsonObject();
			transactiondetail.addProperty("voucherno", voucherno);
			transactiondetail.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
			transactiondetail.addProperty("vouchermode", 2);
			transactiondetail.addProperty("active", 1);
			transactiondetail.addProperty("voucherseries", body.getTransactionmaster().getVoucherseries().toString());
			
			JsonObject transactiondetailwhere = new JsonObject();
			transactiondetailwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
			transactiondetailwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
			transactiondetailwhere.addProperty("transactionmasterid",
					body.getTransactionmaster().getTransactionmasterid());
			
			utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail,
					transactiondetailwhere, "UPDATE", request);	
			
			if (body.getTransactionmaster().getVouchertype().equals("CCE")
					|| body.getTransactionmaster().getVouchertype().equals("OIE")
					|| body.getTransactionmaster().getVouchertype().equals("SIE")) {
				
				query = "SELECT group_concat(transactiondetail.transactiondetailid) transactiondetailid\r\n"
						+ "				FROM transactionmaster transactionmaster\r\n"
						+ "	  INNER JOIN transactiondetail transactiondetail\r\n"
						+ "		  ON     transactionmaster.orgid = transactiondetail.orgid\r\n"
						+ "		 AND transactionmaster.oprid = transactiondetail.oprid\r\n"
						+ "   AND transactionmaster.transactionmasterid =\r\n"
						+ " transactiondetail.transactionmasterid\r\n" + "WHERE     transactionmaster.orgid = "
						+ adUserAccessToken.get("orgid") + "" + " AND transactionmaster.oprid = "
						+ adUserAccessToken.get("oprid") + ""
						+ " AND transactionmaster.vouchertype IN ('PREB','CN')\r\n"
						+ " AND transactionmaster.agencyid = " + body.getTransactionmaster().getAgencyid();

				JsonArray transactionDetail = utility.executeQueryOnPool(query, request);

				Object[] params = new Object[] {};
				query = "update transactiondetail set refclaimno = " + voucherno
						+ " , refclaimdate =" + body.getTransactionmaster().getVoucherdate().toString()
						+ " , refclaimseries =" + body.getTransactionmaster().getVoucherseries().toString()
						+ " where orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
						+ adUserAccessToken.get("oprid") + " and transactiondetailid in ("
						+ transactionDetail.get(0).getAsJsonObject().get("transactiondetailid") + ")";
				utility.executeDMLQueryOnPool(query, params, request);
			}
			
			JsonObject jsonObjectFinalSave = new JsonObject();
			jsonObjectFinalSave.addProperty("code", 200);
			jsonObjectFinalSave.addProperty("status", "Success");
			jsonObjectFinalSave.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			jsonObjectFinalSave.addProperty("message", "Record Saved Successfully ");
			jsonObjectFinalSave.addProperty("data", voucherno);
			response = jsonObjectFinalSave.toString();
		
		return response;	
	}


	@Transactional
	public String postcreditnotereason(List<CreditnoteslabBody> body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		for (CreditnoteslabBody creditnoteslabBody : body) {
			JsonObject creditnoteslab = formater.serializeObject(creditnoteslabBody);
			
			if (creditnoteslab.get("lastoperation").getAsString().equals("INSERT")) {
				// Insert in payment receipt
				String creditnoteslabid = utility.executeIdGenerationProcedure(creditnoteslab.get("orgid").getAsString(),
						creditnoteslab.get("oprid").getAsString(), "paymentreceipt",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				creditnoteslabid = new JSONObject(creditnoteslabid).get("id").toString();
				
				creditnoteslab.addProperty("creditnoteslabid", creditnoteslabid);
			}
			
			utility.executeCustomDML("creditnoteslab", "creditnoteslabid", creditnoteslab,
					null, creditnoteslab.get("lastoperation").getAsString(), request);
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Saved Successfully ");
		return response.toString();
	}

	// Moved to transaction service
	public int postUpdateTcsAmt(TrnMasterBody body, HttpServletRequest request) {
		String whereClause = "", query = "";
		 query = "UPDATE accountoprdetail\r\n" + "SET tcsnetamt = ifnull(tcsnetamt, 0) + ?"
				+ " where  orgid = ?" + " AND oprid = ?" + " AND accountid = ?" 
				+ " " + whereClause;
		Object[] params = new Object[] { body.getTransactionmaster().getTcsamount(),
				body.getTransactionmaster().getOrgid(), body.getTransactionmaster().getOprid(), body.getTransactionmaster().getAccountid()};
		return utility.executeDMLQueryOnPool(query, params, request);

		 
	}
	
	public void insertintoOutstanding(TrnMasterBody body, int voucher, HttpServletRequest request) {

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("orgid", body.getTransactionmaster().getOrgid());
		jsonObject.addProperty("oprid", body.getTransactionmaster().getOprid());

		String outstandingid = utility.executeIdGenerationProcedure(body.getTransactionmaster().getOrgid().toString(),
				body.getTransactionmaster().getOprid().toString(), "outstanding",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		outstandingid = new JSONObject(outstandingid).getString("id");

		jsonObject.addProperty("accountid", body.getTransactionmaster().getAccountid());
		jsonObject.addProperty("transactionid", body.getTransactionmaster().getTransactionmasterid());

		jsonObject.addProperty("outstandingid", outstandingid);
		jsonObject.addProperty("doctype", body.getTransactionmaster().getVouchertype());
		jsonObject.addProperty("docseries", body.getTransactionmaster().getVoucherseries());
		jsonObject.addProperty("billinvoiceno", voucher);
		jsonObject.addProperty("billdate", body.getTransactionmaster().getVoucherdate().toString());

		if (body.getTransactionmaster().getCreditnoteamount().longValue() > 0) {
			jsonObject.addProperty("receivedamt",
					body.getTransactionmaster().getCreditnoteamount() == null ? BigDecimal.ZERO
							: body.getTransactionmaster().getCreditnoteamount());
		} else if (body.getTransactionmaster().getDebitnoteamount().longValue() > 0) {
			jsonObject.addProperty("billamt", body.getTransactionmaster().getDebitnoteamount() == null ? BigDecimal.ZERO
					: body.getTransactionmaster().getDebitnoteamount());
		} else {
			jsonObject.addProperty("receivedamt", body.getTransactionmaster().getNetamount() == null ? BigDecimal.ZERO
					: body.getTransactionmaster().getNetamount());
			jsonObject.addProperty("billamt", body.getTransactionmaster().getBillamount() == null ? BigDecimal.ZERO
					: body.getTransactionmaster().getBillamount());
		}
		jsonObject.addProperty("identity", 1);
		jsonObject.addProperty("billtype", body.getTransactionmaster().getPaymentmode());
		jsonObject.addProperty("cddays", body.getTransactionmaster().getCreditdays());
		jsonObject.addProperty("agencyid", body.getTransactionmaster().getAgencyid());
		jsonObject.addProperty("createdby", body.getTransactionmaster().getCreatedby());
		jsonObject.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
		jsonObject.addProperty("lastoperation", "INSERT");

		// Call for save
		JsonObject jobj = new JsonObject();
		utility.executeCustomDML("outstanding", "outstandingid", jsonObject, jobj, "INSERT", request);

	}
	
	public int postUpdaterefNoDate(TrnMasterBody body, HttpServletRequest request) {
		String whereClause = "", query = "";
		
		  query = "UPDATE transactionmaster\r\n" + "SET refnodate = Concat('" + 
		  body.getTransactionmaster().getVoucherno() + " ' ', " + body.getTransactionmaster().getVoucherdate() + "') where  orgid = ?" +
		  " AND oprid = ?" + " AND referenceid = ?" + " AND vouchertype = 'DM'" + " " +
		  whereClause; Object[] params = new Object[] {
		  body.getTransactionmaster().getOrgid(),
		  body.getTransactionmaster().getOprid(),
		  body.getTransactionmaster().getTransactionmasterid()};
		  return utility.executeDMLQueryOnPool(query, params, request);
		 
		 
	}
	
	/*********************************************************************************
	 * Author  : Kushal Kadu  
	 * Date    : 06/04/2022 
	 * Input   : Header = Data for  transaction master  detail : details for transaction detail 
	 * Output  : Response depending upon conditions  
	 * Purpose : convert DM to sale invoice 
	 * Comment : postDMToSaleInvoice
	 ********************************************************************************/
	@Transactional
	public String postDMToSaleInvoice(HttpServletRequest request, List<String> transactionorders, String voucherdate) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		String voucherinfo = null;
		
		JsonArray invoiceList = new JsonArray();
		JsonObject masterData = new JsonObject();
		JsonObject distictData = new JsonObject();
		JsonObject dmMap = new JsonObject();
		
		JsonObject transactionMaster = new JsonObject();
		JsonArray transactionDetail = new JsonArray();
		JsonObject distinctProduct = new JsonObject();
		
		for (String transactionid : transactionorders) {
			// fetch data from transaction master
			String query = "SELECT * FROM transactionmaster where transactionmasterid = "
					+ transactionid;
			JsonArray data = utility.executeQueryOnPool(query, request);
			
			if (data.size() > 0) {
				JsonObject obj = data.get(0).getAsJsonObject();
				String accountId = obj.get("accountid").deepCopy().getAsString();
				
				if (masterData.has(accountId)) {
					transactionMaster = masterData.getAsJsonObject(accountId);
					transactionDetail = transactionMaster.getAsJsonArray("transactiondetail");
					distinctProduct = distictData.getAsJsonObject(accountId);
					
					transactionMaster.addProperty("grossamount", transactionMaster.get("grossamount").getAsBigDecimal().add(obj.get("grossamount").getAsBigDecimal()));
					transactionMaster.addProperty("cashdiscountpercent", transactionMaster.get("cashdiscountpercent").getAsBigDecimal().add(obj.get("cashdiscountpercent").getAsBigDecimal()));
					transactionMaster.addProperty("cashdiscountamount", transactionMaster.get("cashdiscountamount").getAsBigDecimal().add(obj.get("cashdiscountamount").getAsBigDecimal()));
					transactionMaster.addProperty("productdiscountamount", transactionMaster.get("productdiscountamount").getAsBigDecimal().add(obj.get("productdiscountamount").getAsBigDecimal()));
					transactionMaster.addProperty("netamount", transactionMaster.get("netamount").getAsBigDecimal().add(obj.get("netamount").getAsBigDecimal()));
					transactionMaster.addProperty("debitnoteamount", transactionMaster.get("debitnoteamount").getAsBigDecimal().add(obj.get("debitnoteamount").getAsBigDecimal()));
					transactionMaster.addProperty("creditnoteamount", transactionMaster.get("creditnoteamount").getAsBigDecimal().add(obj.get("creditnoteamount").getAsBigDecimal()));
					transactionMaster.addProperty("pending", transactionMaster.get("pending").getAsBigDecimal().add(obj.get("pending").getAsBigDecimal()));
					transactionMaster.addProperty("addcommissionpercent", transactionMaster.get("addcommissionpercent").getAsBigDecimal().add(obj.get("addcommissionpercent").getAsBigDecimal()));
					transactionMaster.addProperty("roundof", transactionMaster.get("roundof").getAsBigDecimal().add(obj.get("roundof").getAsBigDecimal()));
					transactionMaster.addProperty("gstamount", transactionMaster.get("gstamount").getAsBigDecimal().add(obj.get("gstamount").getAsBigDecimal()));
					transactionMaster.addProperty("igstamount", transactionMaster.get("igstamount").getAsBigDecimal().add(obj.get("igstamount").getAsBigDecimal()));
					transactionMaster.addProperty("cgstamount", transactionMaster.get("cgstamount").getAsBigDecimal().add(obj.get("cgstamount").getAsBigDecimal()));
					transactionMaster.addProperty("sgstamount", transactionMaster.get("sgstamount").getAsBigDecimal().add(obj.get("sgstamount").getAsBigDecimal()));
					transactionMaster.addProperty("cessamount", transactionMaster.get("cessamount").getAsBigDecimal().add(obj.get("cessamount").getAsBigDecimal()));
					transactionMaster.addProperty("gstnet", transactionMaster.get("gstnet").getAsBigDecimal().add(obj.get("gstnet").getAsBigDecimal()));
					transactionMaster.addProperty("salevalue", transactionMaster.get("salevalue").getAsBigDecimal().add(obj.get("salevalue").getAsBigDecimal()));
					transactionMaster.addProperty("totalgstamount", transactionMaster.get("totalgstamount").getAsBigDecimal().add(obj.get("totalgstamount").getAsBigDecimal()));
				} else {
					// Generate id for transaction master
					String transactionmasterid = utility.executeIdGenerationProcedure(
							adUserAccessToken.get("orgid").toString(), adUserAccessToken.get("oprid").toString(),
							"transactionmaster", request.getHeader("authorization").split(" ")[1],
							request.getRequestURL().toString());
					transactionmasterid = new JSONObject(transactionmasterid).getString("id");
					
					obj.addProperty("transactionmasterid", transactionmasterid);
					obj.addProperty("voucherseries", "INC");
					obj.addProperty("vouchertype", "SL");
					
					transactionMaster = obj;
				}
				
				// Add transaction details
				query = "SELECT * FROM transactiondetail where transactionmasterid = "
						+ transactionid;
				data = utility.executeQueryOnPool(query, request);
				for (int i = 0; i < data.size(); i++) {
					obj = data.get(i).getAsJsonObject();
					
					if (distinctProduct.has(obj.get("productid").getAsString() + "_" + obj.get("batchno").getAsString())) {
						JsonObject product = distinctProduct.getAsJsonObject(obj.get("productid").getAsString() + "_" + obj.get("batchno").getAsString());
						
						product.addProperty("qty", product.get("qty").getAsBigDecimal().add(obj.get("qty").getAsBigDecimal()));
						product.addProperty("free", product.get("free").getAsBigDecimal().add(obj.get("free").getAsBigDecimal()));
						product.addProperty("productdiscountpercent", product.get("productdiscountpercent").getAsBigDecimal().add(obj.get("productdiscountpercent").getAsBigDecimal()));
						product.addProperty("itemvalue", product.get("itemvalue").getAsBigDecimal().add(obj.get("itemvalue").getAsBigDecimal()));
						product.addProperty("cgstamount", product.get("cgstamount").getAsBigDecimal().add(obj.get("cgstamount").getAsBigDecimal()));
						product.addProperty("sgstamount", product.get("sgstamount").getAsBigDecimal().add(obj.get("sgstamount").getAsBigDecimal()));
						product.addProperty("ugstamount", product.get("ugstamount").getAsBigDecimal().add(obj.get("ugstamount").getAsBigDecimal()));
						product.addProperty("gstamount", product.get("gstamount").getAsBigDecimal().add(obj.get("gstamount").getAsBigDecimal()));
						
						distinctProduct.add(obj.get("productid").getAsString() + "_" + obj.get("batchno").getAsString(), product);
					} else {
						// Generate id for transaction detail
						String transactiondetailid = utility.executeIdGenerationProcedure(
								adUserAccessToken.get("orgid").toString(), adUserAccessToken.get("oprid").toString(),
								"transactionmaster", request.getHeader("authorization").split(" ")[1],
								request.getRequestURL().toString());
						transactiondetailid = new JSONObject(transactiondetailid).getString("id");
						
						obj.addProperty("transactiondetailid", transactiondetailid);
						obj.addProperty("transactionmasterid", transactionMaster.get("transactionmasterid").getAsString());
						obj.addProperty("voucherseries", "INC");
						obj.addProperty("vouchertype", "SL");
						
						distinctProduct.add(obj.get("productid").getAsString() + "_" + obj.get("batchno").getAsString(), obj);
					}
				}

				Set<String> keys = distinctProduct.keySet();
				transactionDetail = new JsonArray();
				for (String key : keys) {
					transactionDetail.add(distinctProduct.getAsJsonObject(key));
				}

				transactionMaster.add("transactiondetail", transactionDetail);
				
				masterData.add(accountId, transactionMaster);
				distictData.add(accountId, distinctProduct);
				
				String transactionmasterid = transactionMaster.get("transactionmasterid").getAsString();
				if (dmMap.has(transactionmasterid)) {
					dmMap.addProperty(transactionmasterid, dmMap.get(transactionmasterid).getAsString() + "," + transactionid);
				} else {
					dmMap.addProperty(transactionmasterid, transactionid);
				}
			}
		}

		Set<String> mstKeys = masterData.keySet();
		boolean isMultiOrder = false;
		if (mstKeys.size() > 1)
			isMultiOrder = true;
		
		for (String key : mstKeys) {
			// Insert in transaction
			JsonObject transactionmaster = masterData.getAsJsonObject(key).deepCopy();
			
			String refnodate = null;
			if (isMultiOrder) {
				transactionmaster.addProperty("vouchermode", "2");
				transactionmaster.addProperty("voucherseries", "IND");
				transactionmaster.addProperty("active", "1");
				transactionmaster.addProperty("voucherdate", voucherdate);
				
				refnodate = "0-" + voucherdate;
				
				if (voucherinfo == null)
					voucherinfo = refnodate;
				else
					voucherinfo = voucherinfo + "," + refnodate;
			} else {
				transactionmaster.addProperty("vouchermode", "1");
				transactionmaster.addProperty("active", "0");
				transactionmaster.add("voucherno", JsonNull.INSTANCE);
				transactionmaster.addProperty("voucherdate", new Date(System.currentTimeMillis()).toString());
			}
			
			// Update DM for invoice reference id
			String query = "UPDATE transactionmaster SET referenceid = ?, refnodate = ? WHERE transactionmasterid in (?)";
			Object[] params = new Object[] {
					transactionMaster.get("transactionmasterid").getAsString(),
					refnodate,
					dmMap.get(transactionMaster.get("transactionmasterid").getAsString()).getAsString().trim()
			};
			utility.executeDMLQueryOnPool(query, params, request);
			
			JsonArray transactiondetaillist = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			for (JsonElement element : transactiondetaillist) {
				JsonObject transactiondetail = element.getAsJsonObject();
				if (isMultiOrder) {
					transactiondetail.addProperty("vouchermode", "2");
					transactiondetail.addProperty("active", "1");
					transactiondetail.addProperty("voucherseries", "IND");
					transactiondetail.addProperty("voucherdate", voucherdate);
				} else {
					transactiondetail.addProperty("vouchermode", "1");
					transactiondetail.add("voucherno", JsonNull.INSTANCE);
					transactiondetail.addProperty("active", "0");
					transactiondetail.addProperty("voucherdate", new Date(System.currentTimeMillis()).toString());
				}
				
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail,
						null, "INSERT", request);
			}
			
			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster,
					null, "INSERT", request);
			
			// Return new invoice list
			invoiceList.add(masterData.getAsJsonObject(key));
		}
		
		if (isMultiOrder) {
			response.addProperty("message", "Invoice Generated Successfully (" + voucherinfo + ")");
			response.add("data", new JsonArray());
		} else {
			response.addProperty("message", "Invoice Generated Successfully");
			response.add("data", invoiceList);
		}

		return response.toString();
	}
	
	/**
	 * 
	 * @param transactionordermaster id
	 * @param voucherNo
	 * @param request
	 * @return jsonarray for transactionorderdetails according to
	 *         transactionordermaster id
	 */
	public JsonArray getTransactionOrderDetail(String transactionordermasterid, int voucherNo,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String whereClause = "";
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		if (voucherNo != 0) {
			whereClause = " and active= 1";
		}
		String query = "select * from  transactionorderdetail where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid=" + adUserAccessToken.get("oprid") + " and transactionordermasterid ="
				+ transactionordermasterid + whereClause;

		return utility.executeQueryOnPool(query, request);
	}

	/**
	 * 
	 * @param transactionorderdetail id
	 * @param request
	 * @return delete transaction order details according to transactionorderdetail
	 *         id
	 */
	public long deleteTransactionOrderDetail(String transactionorderdetailid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from transactionorderdetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and transactionorderdetailid = " + transactionorderdetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	/**
	 * 
	 * @param transactionordermaster id
	 * @param request
	 * @return delete master and details according to transactionordermaster id
	 */
	public long deleteTransactionOrderMasterDetail(String transactionordermasterid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from transactionorderdetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and transactionordermasterid = " + transactionordermasterid;
		utility.executeDMLQueryOnPool(query, params, request);

		query = "delete from transactionordermaster where orgid =1 and oprid=1 and transactionordermasterid = "
				+ transactionordermasterid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	/**
	 * 
	 * @param transactionordermaster id
	 * @param request
	 * @return deactive transactionorderdetail according to transactionordermaster
	 *         and orderdetail id
	 */
	public long deactivateTransactionOrderDetail(String transactionordermasterid, String transactionorderdetailid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "update transactionorderdetail set active =0 where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactionordermasterid ="
				+ transactionordermasterid + "  and  transactionorderdetailid = " + transactionorderdetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long postSaleOrderTransactionFinalSave(TrnMasterOrderBody body, HttpServletRequest request) {
		String voucherno = "";
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		int voucherNoInt = Integer.parseInt(body.getTransactionordermaster().getVoucherno());
		if (body.getTransactionordermaster().getVoucherno().equals("0")
				|| body.getTransactionordermaster().getVoucherno().isEmpty() || voucherNoInt == 0) {
			String sql = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno\r\n"
					+ "FROM transactionordermaster\r\n" + "WHERE orgid =" + adUserAccessToken.get("orgid")
					+ " AND oprid =" + adUserAccessToken.get("oprid") + "  AND vouchertype = 'SO'";
			JsonArray datadtl = utility.executeQueryOnPool(sql, request);
			voucherno = datadtl.get(0).getAsJsonObject().get("voucherno").toString();
		} else {
			Object[] params = new Object[] {};
			String sql = "DELETE FROM transactionorderdetail\r\n" + "WHERE     orgid = "
					+ adUserAccessToken.get("orgid") + "      AND oprid = " + adUserAccessToken.get("oprid")
					+ "      AND transactionordermasterid = "
					+ body.getTransactionordermaster().getTransactionordermasterid() + " " + "      AND active = 0";
			utility.executeDMLQueryOnPool(sql, params, request);
			voucherno = body.getTransactionordermaster().getVoucherno();
		}

		Gson gson = new Gson();
		String transactionordermasterString = gson.toJson(body.getTransactionordermaster());
		JsonObject transactionOrderMaster = new Gson().fromJson(transactionordermasterString, JsonObject.class);

		transactionOrderMaster.addProperty("voucherno", voucherno);
		transactionOrderMaster.addProperty("vouchermode", 2);
		transactionOrderMaster.addProperty("active", 1);
		transactionOrderMaster.addProperty("voucherdate", body.getTransactionordermaster().getVoucherdate().toString());
		transactionOrderMaster.addProperty("createdon", body.getTransactionordermaster().getCreatedon().toString());
		transactionOrderMaster.addProperty("netamount", body.getTransactionordermaster().getNetamount());
		transactionOrderMaster.addProperty("lastoperation", "UPDATE");

		transactionOrderMaster.remove("transactionorderdetail");
		JsonObject transactionOrderMasterWhere = new JsonObject();
		transactionOrderMasterWhere.addProperty("orgid", adUserAccessToken.get("orgid"));
		transactionOrderMasterWhere.addProperty("orgid", adUserAccessToken.get("oprid"));
		transactionOrderMasterWhere.addProperty("transactionordermasterid",
				body.getTransactionordermaster().getTransactionordermasterid());

		utility.executeCustomDML("transactionordermaster", "transactionordermasterid", transactionOrderMaster,
				transactionOrderMasterWhere, "UPDATE", request);

		JsonObject transactionOrderDetailJsonObject = new JsonObject();
		transactionOrderDetailJsonObject.addProperty("voucherno", voucherno);
		transactionOrderDetailJsonObject.addProperty("vouchermode", 2);
		transactionOrderDetailJsonObject.addProperty("active", 1);
		transactionOrderDetailJsonObject.addProperty("lastoperation", "UPDATE");

		JsonObject transactionOrderDetailWhere = new JsonObject();
		transactionOrderDetailWhere.addProperty("orgid", adUserAccessToken.get("orgid"));
		transactionOrderDetailWhere.addProperty("orgid", adUserAccessToken.get("oprid"));
		transactionOrderDetailWhere.addProperty("transactionordermasterid",
				body.getTransactionordermaster().getTransactionordermasterid());
		utility.executeCustomDML("transactionorderdetail", "transactionorderdetailid", transactionOrderDetailJsonObject,
				transactionOrderDetailWhere, "UPDATE", request);
		return Long.parseLong(voucherno);
	}

	public long reduceStockGstCreditNote(TrnMasterBody body, HttpServletRequest request) {
		int result = 0;
		String whereClause = "";
		String query = "select qty, free, batchid, storeid, linkid from transactiondetail where orgid ="
				+ body.getTransactionmaster().getOrgid() + "  and oprid =" + body.getTransactionmaster().getOprid()
				+ " and transactionmasterid =" + body.getTransactionmaster().getTransactionmasterid() + " "
				+ " AND active = 0";
		JsonArray data = utility.executeQueryOnPool(query, request);
		
		switch (data.get(0).getAsJsonObject().get("linkid").getAsString()) {
		// Salable
		case "16":
			if (data.size() > 0) {
				JsonObject obj = data.get(0).getAsJsonObject();

				query = "update transactionstock set closing = ifnull(closing,0) - (? + ?)"
						+ " ,rsqcn = ifnull(rsqcn,0) - (? + ?)" + "  where orgid = ? AND oprid = ?"
						+ "  AND batchid = ? and storeid = ?";
				Object[] params = new Object[] { 
						obj.get("qty").getAsLong(), 
						obj.get("free").getAsLong(),
						obj.get("qty").getAsLong(), 
						obj.get("free").getAsLong(), 
						body.getTransactionmaster().getOrgid(),
						body.getTransactionmaster().getOprid(), 
						obj.get("batchid").getAsString(),
						body.getTransactionmaster().getStoreid() 
				};

				result = transactionService.updateTransactionStock(query, params, request);
			}
			
			break;

		// Expired
		case "220301000100007":
			if (data.size() > 0) {
				JsonObject obj = data.get(0).getAsJsonObject();
				query = "update transactionstock set closing = ifnull(closing,0) + ?" + " ,rsqcn = ifnull(rsqcn,0) + ?"
						+ "  where orgid = ? AND oprid = ?" + "  AND batchid = ? and storeid = ?";
				Object[] params = new Object[] { 
						obj.get("qty").getAsLong(), 
						obj.get("qty").getAsLong(),
						body.getTransactionmaster().getOrgid(),
						body.getTransactionmaster().getOprid(),
						obj.get("batchid").getAsString(), 
						body.getTransactionmaster().getStoreid() 
				};
				
				result = transactionService.updateTransactionStock(query, params, request);
			}

			break;

		// Breakage
		case "220301000100002":
			if (data.size() > 0) {
				JsonObject obj = data.get(0).getAsJsonObject();
				query = "update transactionstock set closing = ifnull(closing,0) - (? + ?)"
						+ " ,ibrqp = ifnull(ibrqp,0) + ?" + "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? "
						+ " and storeid= ?" + " " + whereClause;
				Object[] params = new Object[] { 
						obj.get("qty").getAsInt(), 
						obj.get("free").getAsInt(),
						obj.get("qty").getAsInt(), 
						obj.get("orgid").getAsInt(), 
						obj.get("oprid").getAsInt(),
						obj.get("batchid").getAsBigInteger(), 
						obj.get("storeid").getAsInt()
				};
	
				result = transactionService.updateTransactionStock(query, params, request);
			}
			
			break;

		// Transfer
		case "220401000100012":
			if (data.size() > 0) {
				JsonObject obj = data.get(0).getAsJsonObject();
				query = "update transactionstock set closing = ifnull(closing,0) - (? + ?)" + " ,ictq = ifnull(ictq,0) + ?"
						+ "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? " + " and storeid= ?" + " "
						+ whereClause;
				Object[] params = new Object[] { 
						obj.get("qty").getAsInt(), 
						obj.get("free").getAsInt(),
						obj.get("qty").getAsInt(), 
						obj.get("orgid").getAsInt(), 
						obj.get("oprid").getAsInt(),
						obj.get("batchid").getAsBigInteger(), 
						obj.get("storeid").getAsInt()
				};
				
				result = transactionService.updateTransactionStock(query, params, request);
			}
			
			break;
		}
		
		return result;
	}

	public long rvertStockPostClaim(TrnMasterBody body, HttpServletRequest request) {
		int result = 0; String whereClause ="";
		
		String query = "select transactiondetailid , qty ,  free , orgid, oprid, productid, batchid, storeid, fiyearid, mrp, salerate , purchaserate, companycode, trade, locationid, linkid from transactiondetail where orgid ="
				+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
				+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
		JsonArray data = utility.executeQueryOnPool(query, request);

		if (data.size() > 0) {
			for (JsonElement transactiondetailJsonElement : data) {

				query = "select * from transactionexpstock where orgid="
						+ body.getTransactionmaster().getOrgid() + " and oprid ="
						+ body.getTransactionmaster().getOprid() + " and productid ="
						+ data.get(0).getAsJsonObject().get("productid").getAsBigDecimal() + " and batchid ="
						+ data.get(0).getAsJsonObject().get("batchid").getAsBigDecimal() + "  and  storeid ="
						+ data.get(0).getAsJsonObject().get("storeid").getAsBigDecimal();
				JsonArray dataStock = utility.executeQueryOnPool(query, request);

				// Check if batch exist in transaction stock
				// If exist update else insert new records
				if (dataStock.size() > 0) {
					// Increase/Inward stock in transaction stock according to store id
					switch (data.get(0).getAsJsonObject().get("linkid").getAsString()) {
					// Expired
					case "220301000100007":
						if (data.size() > 0) {
							query = "update transactionexpstock set closing = ifnull(closing,0) +"
									+ data.get(0).getAsJsonObject().get("qty") + " ,rexp = ifnull(rexp,0)+"
									+ data.get(0).getAsJsonObject().get("qty") + "  where orgid ="
									+ body.getTransactionmaster().getOrgid() + " AND oprid ="
									+ body.getTransactionmaster().getOprid() + "  AND batchid = "
									+ data.get(0).getAsJsonObject().get("batchid") + " and storeid="
									+ body.getTransactionmaster().getStoreid();
							Object[] params = new Object[] {};
							result = utility.executeDMLQueryOnPool(query, params, request);
						}

						break;
						
			          // Breakage
						case "220301000100002":
							query = "update transactionexpstock set closing = ifnull(closing,0) + (? + ?)"
									+ " ,rbrk = ifnull(rbrk,0) + ?" + "  where orgid = ?" + " AND oprid = ?"
									+ "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
							Object[] params = new Object[] { data.get(0).getAsJsonObject().get("qty").getAsInt(),
									data.get(0).getAsJsonObject().get("free").getAsInt(),
									data.get(0).getAsJsonObject().get("qty").getAsInt(),
									data.get(0).getAsJsonObject().get("orgid").getAsInt(),
									data.get(0).getAsJsonObject().get("oprid").getAsInt(),
									data.get(0).getAsJsonObject().get("batchid").getAsBigInteger(),
									data.get(0).getAsJsonObject().get("storeid").getAsInt() };
							result = utility.executeDMLQueryOnPool(query, params, request);
							break;
							
						// Transfer	
						case "220401000100012":
							query = "update transactionexpstock set closing = ifnull(closing,0) + (? + ?)"
									+ " ,roth = ifnull(roth,0) + ?" + "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? "
									+ " and storeid= ?" + " " + whereClause;
							params = new Object[] { data.get(0).getAsJsonObject().get("qty").getAsInt(),
									data.get(0).getAsJsonObject().get("free").getAsInt(),
									data.get(0).getAsJsonObject().get("qty").getAsInt(),
									data.get(0).getAsJsonObject().get("orgid").getAsInt(),
									data.get(0).getAsJsonObject().get("oprid").getAsInt(),
									data.get(0).getAsJsonObject().get("batchid").getAsBigInteger(),
									data.get(0).getAsJsonObject().get("storeid").getAsInt() };
							result = utility.executeDMLQueryOnPool(query, params, request);
							break;	
					 
					}
				} else {
					// Insert into transaction stock

					JsonObject jsonObject = new JsonObject();
					JsonObject jsonObjecttransactionstock = new JsonObject();
					jsonObjecttransactionstock.addProperty("orgid", body.getTransactionmaster().getOrgid());
					jsonObjecttransactionstock.addProperty("oprid", body.getTransactionmaster().getOprid());

					// Generating the stock id
					String stockid = utility.executeIdGenerationProcedure(
							body.getTransactionmaster().getOrgid().toString(),
							body.getTransactionmaster().getOrgid().toString(), "transactionexpstock",
							request.getHeader("authorization").split(" ")[1],
							request.getRequestURL().toString());
					stockid = new JSONObject(stockid).get("id").toString();

					// transaction stock json object
					jsonObjecttransactionstock.addProperty("transactionexpstockid", stockid);

					jsonObjecttransactionstock.addProperty("productid",
							transactiondetailJsonElement.getAsJsonObject().get("productid").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("batchid",
							transactiondetailJsonElement.getAsJsonObject().get("batchid").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("mrp",
							transactiondetailJsonElement.getAsJsonObject().get("mrp").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("trade",
							transactiondetailJsonElement.getAsJsonObject().get("trade").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("salerate",
							transactiondetailJsonElement.getAsJsonObject().get("salerate").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("purchaserate", transactiondetailJsonElement
							.getAsJsonObject().get("purchaserate").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("closing",
							transactiondetailJsonElement.getAsJsonObject().get("qty").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("active", 1);
					jsonObjecttransactionstock.addProperty("createdby",
							body.getTransactionmaster().getCreatedby());
					jsonObjecttransactionstock.addProperty("createdon",
							body.getTransactionmaster().getCreatedon().toString());
					jsonObjecttransactionstock.addProperty("storeid",
							transactiondetailJsonElement.getAsJsonObject().get("storeid").getAsBigDecimal());
					jsonObjecttransactionstock.addProperty("fycode", body.getTransactionmaster().getFiyearid());
					jsonObjecttransactionstock.addProperty("lastoperation", "INSERT");
					// Insert into transaction stock Expiry
					utility.executeCustomDML("transactionexpstock", "transactionexpstockid",
							jsonObjecttransactionstock, jsonObject, "INSERT", request);
				}
			}
		}
		return result;
	}
	
	public String finalSaveGstCreditnote(TrnMasterBody body, HttpServletRequest request) throws JsonProcessingException {
		// Getting the data from token
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String voucherno = "", transactionmasterid="";
//		int vouchernonumeric= 0;
		JsonObject jsonObjectresponse = new JsonObject();

		JsonObject transactionfinalJsonobject = new JsonObject();
		String response = "";
		// Checking the setup option 'Invoicewise Seprate Cr.Note Item Split'
		Map<String, String> setup = new HashMap<>();
		String query = "SELECT d.sysparimeterkey code, d.spmvalname name FROM setupparamdtl d \r\n"
				+ "where d.setupparammasterid in(4) \r\n" + "and d.sysparimeterkey in ('WC_INVSEPCRNISPL')";

		JsonArray setupparam = utility.executeQueryOnPool(query, request);
		for (JsonElement element : setupparam) {
			setup.put(element.getAsJsonObject().get("code").getAsString(),
					element.getAsJsonObject().get("name").getAsString());
		}

		// Getting the data from transaction master
		query = "select  * from transactionmaster where  orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and  transactionmasterid ="
				+ body.getTransactionmaster().getTransactionmasterid();
		JsonArray transactionmasterJsonArray = utility.executeQueryOnPool(query, request);

		switch (setup.get("WC_INVSEPCRNISPL")) {
		case "Yes":
			
			TransactionmasterBody trnMaster = body.getTransactionmaster();
			// Query to fetch the data according to group of voucher series, dates and
			// voucher no
			query = "SELECT td.returnvoucherseries,\r\n" + "       td.returnvoucherno,\r\n"
					+ "       td.returnvoucherdate,\r\n"
					+ "       group_concat(td.transactiondetailid) transactiondetailid\r\n"
					+ "FROM transactiondetail td\r\n" + "     INNER JOIN transactionmaster tm\r\n"
					+ "        ON     td.orgid = tm.orgid\r\n" + "           AND td.oprid = tm.oprid\r\n"
					+ "           AND td.transactionmasterid = tm.transactionmasterid\r\n" + "WHERE     td.orgid = "
					+ adUserAccessToken.get("orgid") + "\r\n" + "      AND td.oprid = " + adUserAccessToken.get("oprid")
					+ "\r\n" + "      AND td.transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid() + "\r\n"
					+ "GROUP BY td.returnvoucherno, td.returnvoucherseries, td.returnvoucherdate;";

			JsonArray transactiondetailids = utility.executeQueryOnPool(query, request);
			for (JsonElement transactiondetailid : transactiondetailids) {
				// JsonObject for transaction master
				JsonObject transactionmaster = transactionmasterJsonArray.get(0).getAsJsonObject();
				JsonArray transactiondetailjsonarray = new JsonArray();
				transactionmaster.addProperty("transactionmasterid", 0);
				transactionmaster.addProperty("lastoperation", "INSERT");
				transactionmaster.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
				transactionmaster.addProperty("replacmentclose", body.getTransactionmaster().getReplacmentclose());
				transactionmaster.addProperty("creditdebitstatus", body.getTransactionmaster().getCreditdebitstatus());
				transactionmaster.addProperty("cashdiscountpercent", body.getTransactionmaster().getCashdiscountpercent());
				transactionmaster.addProperty("cashdiscountamount", body.getTransactionmaster().getCashdiscountamount());
				transactionmaster.addProperty("post", body.getTransactionmaster().getPost());
				transactionmaster.addProperty("ratediffdebitcreditnoteflag", body.getTransactionmaster().getRatediffdebitcreditnoteflag());
				transactionmaster.addProperty("creditnoteflag", body.getTransactionmaster().getCreditnoteflag());
				transactionmaster.addProperty("creditnotetype", body.getTransactionmaster().getCreditnotetype());
				transactionmaster.addProperty("cdp", body.getTransactionmaster().getCdp());
				transactionmaster.addProperty("roundof", body.getTransactionmaster().getRoundof());
//				if(voucherno.length()==0 || voucherno.isEmpty()) {
//					query = "SELECT (max(ifnull(cast(voucherno AS unsigned), 0)) + 1) voucherno \n"
//							+ "FROM transactionmaster " + "WHERE orgid = " + body.getTransactionmaster().getOrgid()
//							+ " AND oprid =" + body.getTransactionmaster().getOprid()
//							+ "  AND vouchertype = 'CN' AND active = 1";
//
//					JsonArray data = utility.executeQueryOnPool(query, request);
//					transactionmaster.add("voucherno", data.get(0).getAsJsonObject().get("voucherno"));
//					voucherno = data.get(0).getAsJsonObject().get("voucherno").toString();
//					vouchernonumeric = Integer.parseInt(voucherno);
//				}else
//				{
//					vouchernonumeric = vouchernonumeric+1;
//					voucherno = voucherno+","+vouchernonumeric;
//					transactionmaster.addProperty("voucherno", vouchernonumeric);
//				}
				
				if (voucherno.length()==0 || voucherno.isEmpty()) {
					voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
							trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), 
							"0", request.getHeader("rightId"), token, request.getRequestURL().toString());
					
					voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
				} else {
					query = "select  voucherno from transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
							+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
							+ body.getTransactionmaster().getTransactionmasterid();
					
					JsonArray data = utility.executeQueryOnPool(query, request);
					voucherno = data.get(0).getAsJsonObject().get("voucherno").getAsString();
				}
				
				transactionmaster.addProperty("voucherno", voucherno);
				// Getting the details
				query = "select * from transactiondetail where orgid =" + adUserAccessToken.get("orgid")
						+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactiondetailid = "
						+ transactiondetailid.getAsJsonObject().get("transactiondetailid");
				JsonArray transactiondetailArray = utility.executeQueryOnPool(query, request);
				BigDecimal cgstamount = BigDecimal.ZERO, sgstamount = BigDecimal.ZERO, ugstamount = BigDecimal.ZERO,
						igstamount = BigDecimal.ZERO, gstamount = BigDecimal.ZERO, grossamount = BigDecimal.ZERO , cessamount= BigDecimal.ZERO,netamount = BigDecimal.ZERO;
				if (transactiondetailArray.size() > 0) {
					for (JsonElement transactiondetailJsonElement : transactiondetailArray) {

						JsonObject transactiondetail = transactiondetailJsonElement.getAsJsonObject();
						transactiondetail.addProperty("transactionmasterid", 0);
						transactiondetail.addProperty("transactiondetailid", 0);
						transactiondetail.addProperty("lastoperation", "INSERT");
						transactiondetail.addProperty("vouchermode", 2);
						transactiondetail.addProperty("active", 1);
						transactiondetail.addProperty("createdby", body.getTransactionmaster().getCreatedby());
						transactiondetail.addProperty("createdon",
								body.getTransactionmaster().getCreatedon().toString());
						transactiondetail.addProperty("voucherno", voucherno);
						transactiondetail.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());

						// Additions to be updated in transaction master
						cgstamount = cgstamount.add(
								transactiondetailJsonElement.getAsJsonObject().get("cgstamount").getAsBigDecimal());
						sgstamount = sgstamount.add(
								transactiondetailJsonElement.getAsJsonObject().get("sgstamount").getAsBigDecimal());
						ugstamount = ugstamount.add(
								transactiondetailJsonElement.getAsJsonObject().get("ugstamount").getAsBigDecimal());
						igstamount = igstamount.add(
								transactiondetailJsonElement.getAsJsonObject().get("igstamount").getAsBigDecimal());
						gstamount = gstamount
								.add(transactiondetailJsonElement.getAsJsonObject().get("gstamount").getAsBigDecimal());
						grossamount = grossamount.add(transactiondetailJsonElement.getAsJsonObject().get("itemvalue").getAsBigDecimal());
						cessamount = cessamount.add(transactiondetailJsonElement.getAsJsonObject().get("cessamount")
								.getAsBigDecimal().add(transactiondetailJsonElement.getAsJsonObject()
										.get("additionalcessamount").getAsBigDecimal()));
						transactiondetailjsonarray.add(transactiondetail);

					}
					transactionmaster.addProperty("vouchermode", 2);
					transactionmaster.addProperty("active", 1);
					transactionmaster.addProperty("cgstamount", cgstamount);
					transactionmaster.addProperty("sgstamount", sgstamount);
					transactionmaster.addProperty("igstamount", igstamount);
					transactionmaster.addProperty("ugstamount", ugstamount);
					transactionmaster.addProperty("gstamount", gstamount);
					transactionmaster.addProperty("grossamount", grossamount);
					transactionmaster.addProperty("cessamount", cessamount);
					transactionmaster.addProperty("mrpvalue", body.getTransactionmaster().getMrpvalue());
					transactionmaster.addProperty("tcscharge", body.getTransactionmaster().getTcscharge());
					transactionmaster.addProperty("printflag", body.getTransactionmaster().getPrintflag());
					transactionmaster.addProperty("qtycount", body.getTransactionmaster().getQtycount());
					transactionmaster.addProperty("mmailsend", body.getTransactionmaster().getMmailsend());
					transactionmaster.addProperty("customername", body.getTransactionmaster().getCustomername());
					transactionmaster.addProperty("customeradd1", body.getTransactionmaster().getCustomeradd1());
					transactionmaster.addProperty("customeradd2", body.getTransactionmaster().getCustomeradd2());
					transactionmaster.addProperty("customeradd3", body.getTransactionmaster().getCustomeradd3());
					transactionmaster.addProperty("totalgstamount", body.getTransactionmaster().getTotalgstamount());
					netamount  = grossamount.add(gstamount).add(cessamount);
					transactionmaster.addProperty("netamount", netamount);
					transactionmaster.add("transactiondetail", transactiondetailjsonarray);
					
				}
				
				transactionfinalJsonobject.add("transactionmaster", transactionmaster);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				body = gson.fromJson(transactionfinalJsonobject, TrnMasterBody.class);
				response =  trnMasterEntity.crud(request, body);
				if (transactionmasterid.length() == 0) {
					transactionmasterid = new JSONObject(response).getString("id");
				} else {
					transactionmasterid = transactionmasterid + "," + new JSONObject(response).getString("id");
				}
			}
			jsonObjectresponse.addProperty("code", 200);
			jsonObjectresponse.addProperty("status", "Success");
			jsonObjectresponse.addProperty("message", "Record Saved Successfully ");
			jsonObjectresponse.addProperty("id", transactionmasterid);
			jsonObjectresponse.addProperty("data", voucherno);
			
			transactionService.deleteTransactionMasterDetail(
					body.getTransactionmaster().getTransactionmasterid().toString(), request);

			break;
		case "No":
			long result = 0;
			int voucher = Integer.parseInt(postTransactionStockFinalSave(body, request));
			if (voucher > 0 && voucher != 0)
				result = 1;
			
			if(result>0) {
				jsonObjectresponse.addProperty("code", 200);
				jsonObjectresponse.addProperty("status", "Success");
				jsonObjectresponse.addProperty("message", "Record Saved Successfully ");
				jsonObjectresponse.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
				jsonObjectresponse.addProperty("data", voucher);
			}
			
		break;	
		}
		return jsonObjectresponse.toString();

	}
	
	// Moved to purchase service
	// Increase stock for purchase
	public synchronized int increaseStockPurchase(TransactiondetailBody transactiondetailBody, HttpServletRequest request) {
		String whereClause = "", query = "";
		
		query = "SELECT transactionstockid, closing, rqp, rfqp FROM transactionstock "
				+ "WHERE orgid = " + transactiondetailBody.getOrgid() 
				+ " AND oprid = " + transactiondetailBody.getOprid() 
				+ " AND batchid = " + transactiondetailBody.getBatchid() 
				+ " AND storeid = " + transactiondetailBody.getStoreid()
				+ " AND productid = " + transactiondetailBody.getProductid();
		JsonArray transactionStockList = utility.executeQueryOnPool(query, request);

		int rowAffected = 0;
		if (transactionStockList.size() > 0) {
			JsonObject trnStk = transactionStockList.get(0).getAsJsonObject();
			query = "select qty, free, specialqty from transactiondetail where orgid = " + transactiondetailBody.getOrgid()
				+ " AND oprid = " + transactiondetailBody.getOprid() + " AND transactionmasterid = "
				+ transactiondetailBody.getTransactionmasterid() + " AND productid = " + transactiondetailBody.getProductid() 
				+ " AND batchid = " + transactiondetailBody.getBatchid() + " AND active = 0";
			
			JsonArray datadtl = utility.executeQueryOnPool(query, request);
			BigDecimal oldQty = BigDecimal.ZERO;
			BigDecimal oldFree = BigDecimal.ZERO;
			BigDecimal newQty = transactiondetailBody.getQty() == null ? BigDecimal.ZERO : transactiondetailBody.getQty();
			BigDecimal newFree = transactiondetailBody.getFree() == null ? BigDecimal.ZERO : transactiondetailBody.getFree();
			BigDecimal speciallQty = transactiondetailBody.getSpecialqty() == null ? BigDecimal.ZERO : transactiondetailBody.getSpecialqty();
			newQty = newQty.add(speciallQty);
			
			BigDecimal closingQty = RestUtil.getAsBigDecimal(trnStk.get("closing"));
			BigDecimal rqp = RestUtil.getAsBigDecimal(trnStk.get("rqp"));
			BigDecimal rfqp = RestUtil.getAsBigDecimal(trnStk.get("rfqp"));
			for (JsonElement element : datadtl) {
				JsonObject obj = element.getAsJsonObject();
				oldQty = RestUtil.getAsBigDecimal(obj.get("qty")).add(RestUtil.getAsBigDecimal(obj.get("specialqty")));
				oldFree = RestUtil.getAsBigDecimal(obj.get("free"));
			}
			
			if (datadtl.size() > 0) {
				if (oldQty.add(oldFree).compareTo(newQty.add(newFree)) > 0) {
					closingQty = closingQty.subtract(oldQty.add(oldFree).subtract(newQty.add(newFree)));
				} else if (oldQty.add(oldFree).compareTo(newQty.add(newFree)) < 0) {
					closingQty = closingQty.add(newQty.add(newFree).subtract(oldQty.add(oldFree)));
				}
				
				if (oldQty.compareTo(newQty) > 0) {
					rqp = rqp.subtract(oldQty.subtract(newQty));
				} else if (oldQty.compareTo(newQty) < 0) {
					rqp = rqp.add(newQty.subtract(oldQty));
				}
				
				if (oldFree.compareTo(newFree) > 0) {
					rfqp = rfqp.subtract(oldFree.subtract(newFree));
				} else if (oldFree.compareTo(newFree) < 0) {
					rfqp = rfqp.add(newFree.subtract(oldFree));
				}
			} else {
				closingQty = closingQty.add(newQty.add(newFree));
				rqp = rqp.add(newQty);
				rfqp = rfqp.add(newFree);
			}
			
			query = "UPDATE transactionstock SET closing = ?,"
					+ " rqp = ? AND rfqp = ?" 
					+ " WHERE orgid = ?"
					+ " AND oprid = ?"
					+ " AND batchid = ? " + " AND storeid = ? AND productid = ?"
					+ whereClause;
			
			Object[] params = new Object[] {
					closingQty,
					rqp,
					rfqp,
					transactiondetailBody.getOrgid(), 
					transactiondetailBody.getOprid(),
					transactiondetailBody.getBatchid(),
					transactiondetailBody.getStoreid(),
					transactiondetailBody.getProductid()
				};
			
			rowAffected = transactionService.updateTransactionStock(query, params, request);
		} else {
			String transactionstockid = utility.executeIdGenerationProcedure(transactiondetailBody.getOrgid().toString(),
					transactiondetailBody.getOprid().toString(), "transactionstock",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionstockid = new JSONObject(transactionstockid).get("id").toString();
			
			query = "INSERT INTO transactionstock (transactionstockid, orgid, oprid, batchid, storeid, productid, rateid, closing, rqp, rfqp, active, createdby, createdon) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? + ? + ?, ? + ?, ?, ?, ?, ?)";
			
			Object[] params = new Object[] {
					transactionstockid,
					transactiondetailBody.getOrgid(), 
					transactiondetailBody.getOprid(),
					transactiondetailBody.getBatchid(),
					transactiondetailBody.getStoreid(),
					transactiondetailBody.getProductid(),
					transactiondetailBody.getRateid(),
					transactiondetailBody.getQty() == null ? BigDecimal.ZERO : transactiondetailBody.getQty(),
					transactiondetailBody.getFree() == null ? BigDecimal.ZERO : transactiondetailBody.getFree(),
					transactiondetailBody.getSpecialqty() == null ? BigDecimal.ZERO : transactiondetailBody.getSpecialqty(),
					transactiondetailBody.getQty() == null ? BigDecimal.ZERO : transactiondetailBody.getQty(),
					transactiondetailBody.getSpecialqty() == null ? BigDecimal.ZERO : transactiondetailBody.getSpecialqty(),
					transactiondetailBody.getFree() == null ? BigDecimal.ZERO : transactiondetailBody.getFree(),
					1,
					transactiondetailBody.getCreatedby(),
					transactiondetailBody.getCreatedon().toString()
				};
			
			rowAffected = utility.executeDMLQueryOnPool(query, params, request);
		}
		
		return rowAffected;
	}

	@Transactional
	public String postStockTransferRequest(StockTransferMstBody body, HttpServletRequest request)
			throws Exception {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String response = "", stocktransfermasterid = "", stocktransferdetailid = "", requestno = "", approvedno = "" ;
		// JsonArray stocktransferDetail = new JsonArray();
		ObjectMapper mapper = new ObjectMapper();
		long result = 0;
		
		switch (body.getStocktransfermaster().getLastoperation()) {
		// First time insert for stock transfer request
		
		case "INSERT":
			String query = "SELECT (ifnull(max(cast(requestno AS unsigned)), 0) + 1) requestno\r\n"
					+ "FROM stocktransfermaster\r\n" + "WHERE orgid = " + body.getStocktransfermaster().getOrgid()
					+ " AND oprid =" + body.getStocktransfermaster().getOprid() + "  AND vouchertype = 'SR'";
			JsonArray data = utility.executeQueryOnPool(query, request);
			String stocktransferMasterString = mapper.writeValueAsString(body.getStocktransfermaster());
			JsonObject stocktransfermaster = (JsonObject) JsonParser.parseString(stocktransferMasterString);
			JsonArray stocktransferDtlList = stocktransfermaster.getAsJsonArray("stocktransferdetail").deepCopy();
			stocktransfermaster.remove("stocktransferdetail");
			
			stocktransfermasterid = utility.executeIdGenerationProcedure(stocktransfermaster.get("orgid").getAsString(),
					stocktransfermaster.get("oprid").getAsString(), "stocktransfermaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			stocktransfermasterid = new JSONObject(stocktransfermasterid).get("id").toString();
			
			for (JsonElement element : stocktransferDtlList) {
				JsonObject stocktransferdetail = element.getAsJsonObject();
				
				stocktransferdetailid = utility.executeIdGenerationProcedure(stocktransferdetail.get("orgid").getAsString(),
						stocktransferdetail.get("oprid").getAsString(), "stocktransferdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				stocktransferdetailid = new JSONObject(stocktransferdetailid).get("id").toString();
				
				stocktransferdetail.addProperty("stocktransferdetailid", stocktransferdetailid);
				stocktransferdetail.addProperty("stocktransfermasterid", stocktransfermasterid);
				
				utility.executeCustomDML("stocktransferdetail", "transactiondetailid", stocktransferdetail,
						null, "INSERT", request);
			}
			
			stocktransfermaster.addProperty("stocktransfermasterid", stocktransfermasterid);
			stocktransfermaster.add("requestno", data.get(0).getAsJsonObject().get("requestno"));
			requestno = data.get(0).getAsJsonObject().get("requestno").toString();
			result = utility.executeCustomDML("stocktransfermaster", "stocktransfermasterid", stocktransfermaster,
					null, "INSERT", request);
			
			break;
		// Updating the stock transfer
		// Item level insert, update, delete on stock transfer detail
		case "UPDATE":
			stocktransfermasterid = body.getStocktransfermaster().getStocktransfermasterid().toString();
			query = "update stocktransfermaster set status = 3" + " where orgid = ?"
					+ " AND oprid = ?" + "  AND stocktransfermasterid = ?";
			Object[] params = new Object[] {body.getStocktransfermaster().getOrgid(), body.getStocktransfermaster().getOprid(),
					body.getStocktransfermaster().getStocktransfermasterid()};
			utility.executeDMLQueryOnPool(query, params, request);
			for (StocktransferdetailBody stockTransferDetailBody : body.getStocktransfermaster().getStocktransferdetail()) {
			//	JsonObject stocktransferdetail = stockTransferDetailBody.getAsJsonObject();
				switch (stockTransferDetailBody.getLastoperation()) {
				// Updating the existing record stock transfer detail
				case "UPDATE":
				// fetch the record from transaction stock and batch master
					query = "select s.closing closing, b.batchid batchid, b.batchno batchno, b.expirydate expirydate, \r\n" + 
							"s.mrp mrp, s.trade trade, s.rateid rateid,\r\n" + 
							"s.purchaserate purchaserate, s.salerate salerate \r\n" + 
							"from transactionstock s \r\n" + 
							"left outer join batch b on b.batchid = s.batchid and b.active = 1\r\n" + 
							"where s.active = 1 \r\n" + 
							"and s.closing > 0 \r\n" +
							"and s.orgid = " + adUserAccessToken.get("orgid") + " \r\n" + 
							"and s.oprid = " + adUserAccessToken.get("oprid") + " \r\n" + 
							"and s.storeid = " + stockTransferDetailBody.getTargetstoreid() + " \r\n" +
							"and s.productid = " + stockTransferDetailBody.getProductid() + " \r\n";
					
						query = query + "order by s.closing desc";
				
					JsonArray batchdata = utility.executeQueryOnPool(query, request);
				
					if (batchdata.size() > 0) {
						// Object[] params = new Object[] {};
						
						// update in transaction detail

						JsonObject stocktransferdetail = new JsonObject();
						stocktransferdetail.add("batchid", batchdata.get(0).getAsJsonObject().get("batchid"));
						stocktransferdetail.add("batchno", batchdata.get(0).getAsJsonObject().get("batchno"));
						stocktransferdetail.add("expirydate", batchdata.get(0).getAsJsonObject().get("expirydate"));
						stocktransferdetail.add("mrp", batchdata.get(0).getAsJsonObject().get("mrp"));
						stocktransferdetail.add("trade", batchdata.get(0).getAsJsonObject().get("trade"));
						stocktransferdetail.add("rateid", batchdata.get(0).getAsJsonObject().get("rateid"));
						stocktransferdetail.add("purchaserate", batchdata.get(0).getAsJsonObject().get("purchaserate"));
						stocktransferdetail.add("salerate", batchdata.get(0).getAsJsonObject().get("salerate"));
						stocktransferdetail.add("closing", batchdata.get(0).getAsJsonObject().get("closing"));
						stocktransferdetail.addProperty("approvedqty", stockTransferDetailBody.getApprovedqty());
						stocktransferdetail.addProperty("vouchertype", stockTransferDetailBody.getVouchertype());
						
						
						JsonObject stocktransferdetailwhere = new JsonObject();
						stocktransferdetailwhere.addProperty("orgid", stockTransferDetailBody.getOrgid());
						stocktransferdetailwhere.addProperty("oprid", stockTransferDetailBody.getOprid());
						stocktransferdetailwhere.addProperty("stocktransfermasterid",
								stockTransferDetailBody.getStocktransfermasterid());
						stocktransferdetailwhere.addProperty("stocktransferdetailid",
								stockTransferDetailBody.getStocktransferdetailid());
						
						utility.executeCustomDML("stocktransferdetail", "stocktransferdetailid", stocktransferdetail,
								stocktransferdetailwhere, "UPDATE", request);
	
					}
					
					 query = "SELECT \r\n" + "    std.productid, std.batchid, std.approvedqty, std.displaypack , std.targetstoreid\r\n"
							+ "FROM\r\n" + "    stocktransferdetail std\r\n" + "WHERE\r\n" + "    std.orgid = "
							+ stockTransferDetailBody.getOrgid() + " AND std.oprid =" + stockTransferDetailBody.getOprid()
							+ " \r\n" + "        AND std.stocktransferdetailid = "
							+ stockTransferDetailBody.getStocktransferdetailid();

					 data = utility.executeQueryOnPool(query, request);
					if (data.size() > 0) {
						// Reduce the stock
						JsonObject jsonObject = data.get(0).getAsJsonObject();

						query = "update transactionstock set closing = ifnull(closing,0) - ?" + " where orgid = ?"
								+ " AND oprid = ?" + "  AND batchid = ?" + " and storeid = ?";
						params = new Object[] { 
								jsonObject.get("approvedqty").getAsLong(),
								stockTransferDetailBody.getOrgid(), 
								stockTransferDetailBody.getOprid(),
								jsonObject.get("batchid").getAsString(),
								jsonObject.get("targetstoreid").getAsString() 
						};
						 
						transactionService.updateTransactionStock(query, params, request);
					}
					JsonObject jsonObjectFinalSave = new JsonObject();
					jsonObjectFinalSave.addProperty("code", 200);
					jsonObjectFinalSave.addProperty("status", "Success");
					jsonObjectFinalSave.addProperty("id", body.getStocktransfermaster().getStocktransfermasterid());
					jsonObjectFinalSave.addProperty("message", "Record UPDATE Successfully");
					response = jsonObjectFinalSave.toString();
					
					break;
					// Item level delete 
				case "DELETE":
					query = "delete from stocktransferdetail where  orgid = ? AND oprid = ?   AND stocktransfermasterid = ?   AND stocktransferdetailid = ?";
					params = new Object[] { stockTransferDetailBody.getOrgid(), stockTransferDetailBody.getOprid(),
							stockTransferDetailBody.getStocktransfermasterid(), stockTransferDetailBody.getStocktransferdetailid()};
					utility.executeDMLQueryOnPool(query, params, request);
					
					break;
				}
			}
         break;
         
         // Delete After Process Call
		case "DELETE":
			for (StocktransferdetailBody stockTransferDetailBody : body.getStocktransfermaster().getStocktransferdetail()) {

			// Revert Stock
			query = "SELECT \r\n" + "    std.productid, std.batchid, std.approvedqty, std.targetstoreid\r\n"
					+ "FROM\r\n" + "    stocktransferdetail std\r\n" + "WHERE\r\n" + "    std.orgid = "
					+ stockTransferDetailBody.getOrgid() + " AND std.oprid =" + stockTransferDetailBody.getOprid()
					+ " \r\n" + "        AND std.stocktransferdetailid = "
					+ stockTransferDetailBody.getStocktransferdetailid();

			 data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				// Revert the stock
				JsonObject jsonObject = data.get(0).getAsJsonObject();

				query = "update transactionstock set closing = ifnull(closing,0) + ?" + " where orgid = ?"
						+ " AND oprid = ?" + "  AND batchid = ?" + " and storeid = ?";
				 params = new Object[] { 
						 jsonObject.get("approvedqty").getAsLong(),
						 stockTransferDetailBody.getOrgid(), 
						 stockTransferDetailBody.getOprid(),
						 jsonObject.get("batchid").getAsString(),
						 jsonObject.get("targetstoreid").getAsString()
				 };
				 
				 transactionService.updateTransactionStock(query, params, request);
			}
			query = "delete from stocktransferdetail where orgid =?" + " and oprid =?"
					+ " and stocktransferdetailid =?";
		    params = new Object[] { stockTransferDetailBody.getOrgid(), stockTransferDetailBody.getOprid(),
					stockTransferDetailBody.getStocktransferdetailid()};
			utility.executeDMLQueryOnPool(query, params, request);
		}
	    break;
         
		// Final save operations for Approved Call
		case "FINALSAVE":
			// Generating the approved no 
				query = "SELECT (ifnull(max(cast(approvedno AS unsigned)), 0) + 1) approvedno\r\n" + "FROM stocktransfermaster "
						+ "WHERE orgid =" + adUserAccessToken.get("orgid") + " AND oprid =" + adUserAccessToken.get("oprid")
						+ "  AND vouchertype = '" + body.getStocktransfermaster().getVouchertype() + "'";
				JsonArray datadtl = utility.executeQueryOnPool(query, request);
				approvedno = datadtl.get(0).getAsJsonObject().get("approvedno").toString();

			// Updating the master records
			stocktransfermaster = new JsonObject();
			stocktransfermaster.addProperty("approvedno", approvedno);
			stocktransfermaster.addProperty("approveddate", body.getStocktransfermaster().getApproveddate().toString());
			stocktransfermaster.addProperty("approvedseries", body.getStocktransfermaster().getApprovedseries());
			stocktransfermaster.addProperty("vouchertype", body.getStocktransfermaster().getVouchertype());
			stocktransfermaster.addProperty("status", 1);
	
			
			for (StocktransferdetailBody stockTransferDetailBody : body.getStocktransfermaster().getStocktransferdetail()) {
				
				query = "SELECT \r\n" + "    std.productid, std.batchid, std.approvedqty, std.displaypack , std.targetstoreid\r\n"
						+ "FROM\r\n" + "    stocktransferdetail std\r\n" + "WHERE\r\n" + "    std.orgid = "
						+ stockTransferDetailBody.getOrgid() + " AND std.oprid =" + stockTransferDetailBody.getOprid()
						+ " \r\n" + "        AND std.stocktransferdetailid = "
						+ stockTransferDetailBody.getStocktransferdetailid();

				 data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					// Revert the stock
					JsonObject jsonObject = data.get(0).getAsJsonObject();

					query = "update transactionstock set closing = ifnull(closing,0) + ?" + " where orgid = ?"
							+ " AND oprid = ?" + "  AND batchid = ?" + " and storeid = ?";
					 params = new Object[] { 
							 jsonObject.get("approvedqty").getAsLong(),
							 stockTransferDetailBody.getOrgid(), 
							 stockTransferDetailBody.getOprid(),
							 jsonObject.get("batchid").getAsString(),
							 jsonObject.get("targetstoreid").getAsString()
					 };
					 
					 transactionService.updateTransactionStock(query, params, request);
				}
				// update in transaction detail

				JsonObject stocktransferdetail = new JsonObject();
				stocktransferdetail.addProperty("approvedqty", stockTransferDetailBody.getApprovedqty());
				stocktransferdetail.addProperty("vouchertype", stockTransferDetailBody.getVouchertype());
				stocktransferdetail.addProperty("batchid", stockTransferDetailBody.getBatchid());
				stocktransferdetail.addProperty("batchno", stockTransferDetailBody.getBatchno());
				stocktransferdetail.addProperty("expirydate", stockTransferDetailBody.getExpirydate().toString());
				stocktransferdetail.addProperty("mrp", stockTransferDetailBody.getMrp());
				stocktransferdetail.addProperty("salerate", stockTransferDetailBody.getSalerate());
				stocktransferdetail.addProperty("trade", stockTransferDetailBody.getTrade());
				stocktransferdetail.addProperty("purchaserate", stockTransferDetailBody.getPurchaserate());
				
				JsonObject stocktransferdetailwhere = new JsonObject();
				stocktransferdetailwhere.addProperty("orgid", stockTransferDetailBody.getOrgid());
				stocktransferdetailwhere.addProperty("oprid", stockTransferDetailBody.getOprid());
				stocktransferdetailwhere.addProperty("stocktransfermasterid",
						stockTransferDetailBody.getStocktransfermasterid());
				stocktransferdetailwhere.addProperty("stocktransferdetailid",
						stockTransferDetailBody.getStocktransferdetailid());
				
				utility.executeCustomDML("stocktransferdetail", "stocktransferdetailid", stocktransferdetail,
						stocktransferdetailwhere, "UPDATE", request);
				
				// reduce stock from transaction stock
				String whereClause = "";
				query = "update transactionstock set closing = ifnull(closing,0) - ?" + " ,iinttran = ifnull(iinttran,0) + ?"
						+ "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? " + " and storeid= ?" + " " + whereClause;
			    params = new Object[] { 
			    		stockTransferDetailBody.getApprovedqty(), 
			    		stockTransferDetailBody.getApprovedqty(),
						stockTransferDetailBody.getOrgid(), 
						stockTransferDetailBody.getOprid(), 
						stockTransferDetailBody.getBatchid(),
						stockTransferDetailBody.getTargetstoreid() 
				};

			    transactionService.updateTransactionStock(query, params, request);
			
			}
			
			JsonObject stocktransfermasterwhere = new JsonObject();
			stocktransfermasterwhere.addProperty("orgid", adUserAccessToken.get("orgid"));
			stocktransfermasterwhere.addProperty("oprid", adUserAccessToken.get("oprid"));
			stocktransfermasterwhere.addProperty("stocktransfermasterid", body.getStocktransfermaster().getStocktransfermasterid());
			utility.executeCustomDML("stocktransfermaster", "stocktransfermasterid", stocktransfermaster, stocktransfermasterwhere,
					"UPDATE", request);

			break;
		// Escape for post stock transfer
		case "CANCEL":
			// First Revert Stock 
			 query = "select * from stocktransferdetail where orgid = " + body.getStocktransfermaster().getOrgid()
					+ " and oprid=" + body.getStocktransfermaster().getOprid() + " and stocktransfermasterid= "
					+ body.getStocktransfermaster().getStocktransfermasterid();
			 data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonObjectEach : data.getAsJsonArray()) {
					JsonObject obj = jsonObjectEach.getAsJsonObject();
					query = "update transactionstock set closing = ifnull(closing,0) + ?" + " where orgid = ?"
							+ " AND oprid = ?" + "  AND batchid = ?"  + " and storeid= ?";
					params = new Object[] { 
							obj.get("approvedqty").getAsLong(),
							body.getStocktransfermaster().getOrgid(), 
							body.getStocktransfermaster().getOprid(),
							obj.get("batchid").getAsString(),
							obj.get("targetstoreid").getAsString()
					};
					
					transactionService.updateTransactionStock(query, params, request);
				}
			}
			
			params = new Object[] { body.getStocktransfermaster().getOrgid(), body.getStocktransfermaster().getOprid(),
					body.getStocktransfermaster().getStocktransfermasterid()};
			// Delete transaction detail and master
			query = "delete from stocktransferdetail where orgid =?" + " and oprid =?" + " and stocktransfermasterid =?";
			utility.executeDMLQueryOnPool(query, params, request);

			query = "delete from stocktransfermaster where orgid =?" + " and oprid =?" + " and stocktransfermasterid =?";
			utility.executeDMLQueryOnPool(query, params, request);
			JsonObject jsonObjectEscape = new JsonObject();

			// Response for Escape
			jsonObjectEscape.addProperty("code", 200);
			jsonObjectEscape.addProperty("status", "Success");
			jsonObjectEscape.addProperty("message", "Record Delete Successfully");
			
			break;
		}
		if (result >= 0) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Updated Successfully");
			jsonObject.addProperty("id", body.getStocktransfermaster().getStocktransfermasterid().toString());
			
			if (body.getStocktransfermaster().getLastoperation().equals("FINALSAVE")
					|| body.getStocktransfermaster().getLastoperation().equals("CANCEL")) {

				switch (body.getStocktransfermaster().getLastoperation()) {
				case "FINALSAVE":
					jsonObject.addProperty("data", approvedno);
					break;

				case "CANCEL":
					String sql = "select * from stocktransferdetail";
					JsonArray data = utility.executeQueryOnPool(sql, request);
					jsonObject.add("datadtl", data);
					break;

				}
			} else {
				String sql = "select * from stocktransferdetail where stocktransfermasterid = "
						+ stocktransfermasterid;
				JsonArray data = utility.executeQueryOnPool(sql, request);
				jsonObject.addProperty("data", requestno);
				jsonObject.add("datadtl", data);
			}
			response = jsonObject.toString();

		} else {
			response = "Exception";
		}
		
		return response;
	}

	@Transactional
	public String postStockTransferInwardRequest(StockTransferMstBody body, HttpServletRequest request) {
		String response = "",inwardno = "";

		// Generating the approved no
		String query = "SELECT (ifnull(max(cast(inwardno AS unsigned)), 0) + 1) inwardno\r\n"
				+ "FROM stocktransfermaster " + "WHERE orgid = " + body.getStocktransfermaster().getOrgid() + " AND oprid = "
				+ body.getStocktransfermaster().getOprid() + "  AND vouchertype = '"
				+ body.getStocktransfermaster().getVouchertype() + "'";
		JsonArray datadtl = utility.executeQueryOnPool(query, request);
		inwardno = datadtl.get(0).getAsJsonObject().get("inwardno").toString();

		// Updating the master records
		JsonObject stocktransfermaster = new JsonObject();
		stocktransfermaster.addProperty("inwardno", inwardno);
		stocktransfermaster.addProperty("inwarddate", body.getStocktransfermaster().getInwarddate().toString());
		stocktransfermaster.addProperty("inwardseries", body.getStocktransfermaster().getInwardseries());
		stocktransfermaster.addProperty("vouchertype", body.getStocktransfermaster().getVouchertype());
		stocktransfermaster.addProperty("status", 2);

		for (StocktransferdetailBody stockTransferDetailBody : body.getStocktransfermaster().getStocktransferdetail()) {
			// update in transaction detail
			JsonObject stocktransferdetail = new JsonObject();

			stocktransferdetail.addProperty("inwardqty", stockTransferDetailBody.getInwardqty());
			stocktransferdetail.addProperty("vouchertype", stockTransferDetailBody.getVouchertype());
			JsonObject stocktransferdetailwhere = new JsonObject();
			stocktransferdetailwhere.addProperty("orgid", stockTransferDetailBody.getOrgid());
			stocktransferdetailwhere.addProperty("oprid", stockTransferDetailBody.getOprid());
			stocktransferdetailwhere.addProperty("stocktransfermasterid",
					stockTransferDetailBody.getStocktransfermasterid());
			stocktransferdetailwhere.addProperty("stocktransferdetailid",
					stockTransferDetailBody.getStocktransferdetailid());

			utility.executeCustomDML("stocktransferdetail", "stocktransferdetailid", stocktransferdetail,
					stocktransferdetailwhere, "UPDATE", request);

			query = "select * from transactionstock where orgid=" + stockTransferDetailBody.getOrgid()
					+ " and oprid =" + stockTransferDetailBody.getOprid() + " and productid ="
					+ stockTransferDetailBody.getProductid() + " and batchid ="
					+ stockTransferDetailBody.getBatchid() + "  and  storeid ="
					+ stockTransferDetailBody.getSourcestoreid();
			JsonArray dataStock = utility.executeQueryOnPool(query, request);
			
			// Check if batch exist in transaction stock
			// If exist update else insert new records
			if (dataStock.size() > 0) {
				// Increase/Inward stock in transaction stock according to store id 
				String whereClause = "";
				query = "update transactionstock set closing = ifnull(closing,0) + ?" + " ,rinttran = ifnull(rinttran,0) + ?"
						+ "  where orgid = ?" + " AND oprid = ?" + "  AND batchid = ? " + " and storeid= ?" + " "
						+ whereClause;
				Object[] params = new Object[] { 
						stockTransferDetailBody.getInwardqty(),
						stockTransferDetailBody.getInwardqty(), 
						stockTransferDetailBody.getOrgid(),
						stockTransferDetailBody.getOprid(), 
						stockTransferDetailBody.getBatchid(),
						stockTransferDetailBody.getSourcestoreid()
				};
				
				transactionService.updateTransactionStock(query, params, request);
			} else {
				// Insert into transaction stock
				query = "select  rateid from transactionstock where orgid =" + stockTransferDetailBody.getOrgid() + " and oprid ="
						+ stockTransferDetailBody.getOprid() + " and batchid =" + stockTransferDetailBody.getBatchid() + " limit 1";
				JsonArray datarate = utility.executeQueryOnPool(query, request);
				JsonObject jsonObject = new JsonObject();
				JsonObject jsonObjecttransactionstock = new JsonObject();
				jsonObjecttransactionstock.addProperty("orgid", stockTransferDetailBody.getOrgid());
				jsonObjecttransactionstock.addProperty("oprid", stockTransferDetailBody.getOprid());

				// Generating the stock id
				String stockid = utility.executeIdGenerationProcedure(
						stockTransferDetailBody.getOrgid().toString(),
						stockTransferDetailBody.getOrgid().toString(), "transactionstock",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				stockid = new JSONObject(stockid).get("id").toString();

				// transaction stock json object
				jsonObjecttransactionstock.addProperty("transactionstockid", stockid);

				jsonObjecttransactionstock.addProperty("productid",
						stockTransferDetailBody.getProductid());
				jsonObjecttransactionstock.addProperty("batchid", stockTransferDetailBody.getBatchid());
				jsonObjecttransactionstock.addProperty("rateid", datarate.get(0).getAsJsonObject().get("rateid").toString());
				jsonObjecttransactionstock.addProperty("mrp", stockTransferDetailBody.getMrp());
				jsonObjecttransactionstock.addProperty("trade", stockTransferDetailBody.getTrade());
				jsonObjecttransactionstock.addProperty("salerate",
						stockTransferDetailBody.getSalerate());
				jsonObjecttransactionstock.addProperty("purchaserate",
						stockTransferDetailBody.getPurchaserate());
				jsonObjecttransactionstock.addProperty("closing", stockTransferDetailBody.getInwardqty());
				jsonObjecttransactionstock.addProperty("new", 0);
				jsonObjecttransactionstock.addProperty("active", 1);
				jsonObjecttransactionstock.addProperty("createdby", stockTransferDetailBody.getCreatedby());
				jsonObjecttransactionstock.addProperty("createdon", stockTransferDetailBody.getCreatedon().toString());
				jsonObjecttransactionstock.addProperty("storeid",
						stockTransferDetailBody.getSourcestoreid().toString());
				jsonObjecttransactionstock.addProperty("fycode", 2022);
				jsonObjecttransactionstock.addProperty("reftrnid",
						stockTransferDetailBody.getStocktransferdetailid().toString());
				jsonObjecttransactionstock.addProperty("lastoperation", "INSERT");
				// Insert into transaction stock
				utility.executeCustomDML("transactionstock", "transactionstockid", jsonObjecttransactionstock, jsonObject,
						"INSERT", request);
			}
		}

		JsonObject stocktransfermasterwhere = new JsonObject();
		stocktransfermasterwhere.addProperty("orgid", body.getStocktransfermaster().getOrgid());
		stocktransfermasterwhere.addProperty("oprid", body.getStocktransfermaster().getOprid());
		stocktransfermasterwhere.addProperty("stocktransfermasterid",
				body.getStocktransfermaster().getStocktransfermasterid());
		utility.executeCustomDML("stocktransfermaster", "stocktransfermasterid", stocktransfermaster,
				stocktransfermasterwhere, "UPDATE", request);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", body.getStocktransfermaster().getStocktransfermasterid());
		jsonObject.addProperty("data", inwardno);
		jsonObject.addProperty("message", "Record Inward Successfully");
		response = jsonObject.toString();
		
		return response;
	}
	
	@Transactional
	public JsonArray getStockTransferDetail(String stocktransfermasterid, int requestno,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String whereClause = "";
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		if (requestno != 0) {
			whereClause = " and active= 1";
		}
		String query = "select * from  stocktransferdetail where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid=" + adUserAccessToken.get("oprid") + " and stocktransfermasterid ="
				+ stocktransfermasterid + whereClause;

		return utility.executeQueryOnPool(query, request);
	}
	
	@Transactional
	public String postCreditNoteReplacement(TrnMasterBody body, HttpServletRequest request) throws Exception {
		String lastoperation = body.getTransactionmaster().getLastoperation(), response = "";

		switch (lastoperation) {
		case "INSERT":
			int update = postCreditNoteReplacementInsert(body, request);
			if (update > 0) {
				response = trnMasterEntity.crud(request, body);

				String sql = "select * from transactiondetail where transactionmasterid = "
						+ new JSONObject(response).get("id").toString();
				JsonArray data = utility.executeQueryOnPool(sql, request);

				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "Success");
				jsonObject.addProperty("id", new JSONObject(response).get("id").toString());
				jsonObject.addProperty("message", "Record Saved Successfully ");
				jsonObject.add("data", data);
				response = jsonObject.toString();
			}
			break;

		case "UPDATE":
			// Update in case of credit note replacement which internally include insert,
			// update delete at item level
			response = postCreditNoteReplacementUpdate(body, request);
			break;

		case "ESCAPE":
			// Basket level Escape and after final save escape operations
			response = postCreditNoteReplacementEscape(body, request);
			break;

		case "FINALSAVE":
			// Final save operations
			response = postCreditNoteReplacementFinalSave(body, request);
			break;

		case "CANCEL":
			// Cancel invoice operations in replacement
			response = postCreditNoteReplacementCancel(body, request);
			break;

		case "CLOSE":
			// Updating the
			response = postCreditNoteReplacementClose(body, request);
			break;

		}
		return response;
	}
	
	@Transactional
	public String postCustomerAgencyDiscount(List<CustomeragencydiscountBody> body,
			HttpServletRequest request) throws JsonSyntaxException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Object[] params = new Object[] {};
		String whereClause = "";

		if (body.get(0).getAgencygroupid() != 0) {
			whereClause = whereClause + " and agencygroupid=" + body.get(0).getAgencygroupid();
		}
		// delete previous details as per agency id
		String query = "delete from customeragencydiscount where  agencyid =" + body.get(0).getAgencyid() + " "
				+ whereClause;
		utility.executeDMLQueryOnPool(query, params, request);

		// Re-Insert data using the generated call
		JsonArray customerAgencyList = JsonParser.parseString(mapper.writeValueAsString(body)).getAsJsonArray();
		for (JsonElement element : customerAgencyList) {
			JsonObject customerAgency = element.getAsJsonObject();
			
			if (customerAgency.get("lastoperation").getAsString().equals("INSERT")) {
				String customerAgencyid = utility.executeIdGenerationProcedure(customerAgency.get("orgid").getAsString(),
						customerAgency.get("oprid").getAsString(), "customeragencydiscount",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				customerAgencyid = new JSONObject(customerAgencyid).get("id").toString();
				customerAgency.addProperty("customeragencydiscountid", customerAgencyid);
			}
			
			utility.executeCustomDML("customeragencydiscount", "customeragencydiscountid", customerAgency,
					null, customerAgency.get("lastoperation").getAsString(), request);
		}
		
		return "Success";
	}
	
	@Transactional
	public String postStockTransfer(TrnMasterBody body, HttpServletRequest request)
			throws Exception {
		String jsonresult = "";
		switch (body.getTransactionmaster().getLastoperation()) {
		// First time insert
		case "INSERT":
			jsonresult = postStockTransferInsert(body, request);
			break;
		case "UPDATE":
			jsonresult = postStockTransferUpdate(body, request);
			break;
		case "FINALSAVE":
			jsonresult = postStockTransferFinalSave(body, request);
			break;
		case "ESCAPE":
			jsonresult = postStockTransferEscape(body, request);
			break;
		}
		
		return jsonresult;
	}
	
	@Transactional
	public String postPickerLocationlink(MstPickerBody body, HttpServletRequest request)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		// Token Information
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		Object[] params = new Object[] {};

		// Deleting the locations attached to the picker
		String query = "delete from pickerlocationlink where orgid=" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and pickerid =" + body.getPicker().getPickerid();
		utility.executeDMLQueryOnPool(query, params, request);

		JsonObject picker = JsonParser.parseString(mapper.writeValueAsString(body.getPicker())).getAsJsonObject();
		JsonArray pickerlocationlink = picker.getAsJsonArray("pickerlocationlink").deepCopy();
		picker.remove("pickerlocationlink");
		
		if (picker.get("lastoperation").getAsString().equals("INSERT")) {
			String customerAgencyid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"), "picker",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			customerAgencyid = new JSONObject(customerAgencyid).get("id").toString();
			picker.addProperty("pickerid", customerAgencyid);
		}
		utility.executeCustomDML("picker", "pickerid", picker,
				null, picker.get("lastoperation").getAsString(), request);
		
		for (JsonElement element : pickerlocationlink) {
			JsonObject pickerlocation = element.getAsJsonObject();
			
			if (pickerlocation.get("lastoperation").getAsString().equals("INSERT")) {
				String customerAgencyid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
						adUserAccessToken.get("oprid"), "pickerlocationlink",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				customerAgencyid = new JSONObject(customerAgencyid).get("id").toString();
				pickerlocation.addProperty("pickerlocationlinkid", customerAgencyid);
			}
			utility.executeCustomDML("pickerlocationlink", "pickerlocationlinkid", pickerlocation,
					null, pickerlocation.get("lastoperation").getAsString(), request);
		}
		
		return "Success";
	}
	
	@Transactional
	public String postSalesOrder(@RequestBody @Validated TrnMasterOrderBody body, HttpServletRequest request) {
		String transactionordermasterid = "", voucherno = "";
		JsonArray transactionOrderDetail = new JsonArray();
		ObjectMapper mapper = new ObjectMapper();
		String transactionorder = "";
		try {
			transactionorder = mapper.writeValueAsString(body.getTransactionordermaster());
		} catch (JsonProcessingException e) {
			throw new CustomException("Invalid Json body");
		}
		
		JsonObject transactionordermaster = JsonParser.parseString(transactionorder).getAsJsonObject();

		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		switch (body.getTransactionordermaster().getLastoperation()) {

		// Call for first time insert
		case "INSERT":
			
			transactionOrderDetail = transactionordermaster.getAsJsonArray("transactionorderdetail").deepCopy();
			transactionordermaster.remove("transactionorderdetail");
			
			transactionordermasterid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"), "transactionordermaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionordermasterid = new JSONObject(transactionordermasterid).get("id").toString();
			
			for (JsonElement element : transactionOrderDetail) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactionorderdetailid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
						adUserAccessToken.get("oprid"), "transactionorderdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactionorderdetailid = new JSONObject(transactionorderdetailid).get("id").toString();
				
				transactiondetail.addProperty("transactionorderdetailid", transactionorderdetailid);
				transactiondetail.addProperty("transactionordermasterid", transactionordermasterid);
				
				utility.executeCustomDML("transactionorderdetail", "transactionorderdetailid", transactiondetail,
						null, "INSERT", request);
			}
			
			transactionordermaster.addProperty("transactionordermasterid", transactionordermasterid);
			utility.executeCustomDML("transactionordermaster", "transactionordermasterid", transactionordermaster,
					null, "INSERT", request);
			
			break;

		case "UPDATE":
			transactionordermasterid = body.getTransactionordermaster().getTransactionordermasterid().toString();
			voucherno = body.getTransactionordermaster().getVoucherno();
			for (TransactionorderdetailBody transactionOrderDetailBody : body.getTransactionordermaster()
					.getTransactionorderdetail()) {
				int voucherNoInt = Integer.parseInt(body.getTransactionordermaster().getVoucherno());
				switch (transactionOrderDetailBody.getLastoperation()) {

				// Insert the new items in the GRID
				case "INSERT":
					if (body.getTransactionordermaster().getVoucherno().equals("0")
							|| body.getTransactionordermaster().getVoucherno().isEmpty() || voucherNoInt == 0) {
						//response = trnMasterOrderEntity.crud(request, body);
					} else {
						// Parsing the body
						Gson gson = new Gson();
						String transactionOrderDetailString = gson
								.toJson(body.getTransactionordermaster().getTransactionorderdetail());
						JsonArray transactionOrderDetailJsonObject = new Gson()
								.fromJson(transactionOrderDetailString, JsonArray.class);

						JsonArray transactionorderdetail = new JsonArray();
						JsonObject jsonObject = transactionOrderDetailJsonObject.get(0).getAsJsonObject();
						jsonObject.remove("tnew");
						jsonObject.addProperty("tnew", 1);
						jsonObject.addProperty("active", 1);
						transactionorderdetail.add(jsonObject);

						String transactionordermasterString = gson.toJson(body.getTransactionordermaster(),
								TransactionordermasterBody.class);
						JsonObject transactionordermasterJsonObject = new Gson()
								.fromJson(transactionordermasterString, JsonObject.class);
						transactionordermasterJsonObject.remove("transactionorderdetail");
						transactionordermasterJsonObject.add("transactionorderdetail", transactionorderdetail);

						JsonObject jsonObjectTransactionOrderMaster = new JsonObject();
						jsonObjectTransactionOrderMaster.add("transactionordermaster",
								transactionordermasterJsonObject);
						body = gson.fromJson(jsonObjectTransactionOrderMaster, TrnMasterOrderBody.class);
						//response = trnMasterOrderEntity.crud(request, body);
					}
					
					break;
				// Updating the existing item in the grid
				case "UPDATE":
					if (body.getTransactionordermaster().getVoucherno().equals("0")
							|| body.getTransactionordermaster().getVoucherno().isEmpty() || voucherNoInt == 0) {
						deleteTransactionOrderDetail(
								transactionOrderDetailBody.getTransactionorderdetailid().toString(), request);
					} else {
						deactivateTransactionOrderDetail(
								body.getTransactionordermaster().getTransactionordermasterid().toString(),
								transactionOrderDetailBody.getTransactionorderdetailid().toString(), request);
					}
					Gson gson = new Gson();
					String transactionOrderDetailString = gson
							.toJson(body.getTransactionordermaster().getTransactionorderdetail());
					JsonArray transactionOrderDetailJsonObject = new Gson().fromJson(transactionOrderDetailString,
							JsonArray.class);
					JsonObject jsonObject = transactionOrderDetailJsonObject.get(0).getAsJsonObject();
					jsonObject.remove("transactionorderdetailid");
					jsonObject.remove("lastoperation");
					jsonObject.addProperty("transactionorderdetailid", 0);
					jsonObject.addProperty("lastoperation", "INSERT");
					// Before final save active will be 0 and new will be 0
					if (body.getTransactionordermaster().getVoucherno().equals("0")) {
						jsonObject.addProperty("tnew", 0);
						jsonObject.addProperty("active", 0);
					} else {
						jsonObject.addProperty("tnew", 1);
						jsonObject.addProperty("active", 1);
					}
					transactionOrderDetailJsonObject = new JsonArray();
					transactionOrderDetailJsonObject.add(jsonObject);

					String transactionordermasterString = gson.toJson(body.getTransactionordermaster(),
							TransactionordermasterBody.class);
					JsonObject transactionordermasterJsonObject = new Gson().fromJson(transactionordermasterString,
							JsonObject.class);
					transactionordermasterJsonObject.remove("transactionorderdetail");
					transactionordermasterJsonObject.add("transactionorderdetail",
							transactionOrderDetailJsonObject);

					JsonObject jsonObjectTransactionOrderMaster = new JsonObject();
					jsonObjectTransactionOrderMaster.add("transactionordermaster",
							transactionordermasterJsonObject);
					body = gson.fromJson(jsonObjectTransactionOrderMaster, TrnMasterOrderBody.class);
					//response = trnMasterOrderEntity.crud(request, body);

					break;
				// Deleting the existing item in the grid
				case "DELETE":
					deleteTransactionOrderDetail(
							transactionOrderDetailBody.getTransactionorderdetailid().toString(), request);
					break;
				}
			}
			transactionOrderDetail = getTransactionOrderDetail(transactionordermasterid,
					Integer.parseInt(voucherno), request);
			break;

		// Escape functionality
		case "ESCAPE":
			int voucherNoInt = Integer.parseInt(body.getTransactionordermaster().getVoucherno());
			transactionordermasterid = body.getTransactionordermaster().getTransactionordermasterid().toString();
			if (body.getTransactionordermaster().getVoucherno().equals("0")
					|| body.getTransactionordermaster().getVoucherno().isEmpty() || voucherNoInt == 0) {
				// Delete both master and details
				deleteTransactionOrderMasterDetail(
						body.getTransactionordermaster().getTransactionordermasterid().toString(), request);
			} else {
				// Delete from transaction detail
				Object[] params = new Object[] {};
				String sql = "DELETE FROM transactionorderdetail\r\n" + "WHERE     orgid = "
						+ adUserAccessToken.get("orgid") + "      AND oprid = " + adUserAccessToken.get("oprid")
						+ "      AND transactionordermasterid = "
						+ body.getTransactionordermaster().getTransactionordermasterid() + " "
						+ "      AND tnew = 1";
				utility.executeDMLQueryOnPool(sql, params, request);

				// Update in transaction detail
				sql = "update transactionorderdetail set active = 1 where orgid =" + adUserAccessToken.get("orgid")
						+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactionordermasterid ="
						+ body.getTransactionordermaster().getTransactionordermasterid() + " and active = 0";
				utility.executeDMLQueryOnPool(sql, params, request);
			}
			break;
		// Final save operations
		case "FINALSAVE":
			transactionordermasterid = body.getTransactionordermaster().getTransactionordermasterid().toString();
			voucherno = Long.toString(postSaleOrderTransactionFinalSave(body, request));

			break;
		// Cancel bill operations
		case "CANCEL":
			JsonObject transactionOrderMasterWhere = new JsonObject();
			transactionordermasterid = body.getTransactionordermaster().getTransactionordermasterid().toString();
			JsonObject transactionOrderMaster = new JsonObject();
			transactionOrderMaster.addProperty("canflag", "1");
			transactionOrderMasterWhere = new JsonObject();
			transactionOrderMasterWhere.addProperty("orgid", adUserAccessToken.get("orgid"));
			transactionOrderMasterWhere.addProperty("oprid", adUserAccessToken.get("oprid"));
			transactionOrderMasterWhere.addProperty("transactionordermasterid",
					body.getTransactionordermaster().getTransactionordermasterid());
			utility.executeCustomDML("transactionordermaster", "transactionordermasterid", transactionOrderMaster,
					transactionOrderMasterWhere, "UPDATE", request);
			break;

		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 200);
		jsonObject.addProperty("status", "Success");
		jsonObject.addProperty("id", transactionordermasterid);
		jsonObject.addProperty("message", "Record Saved Successfully ");
		// Response and data according to cases
		if (!body.getTransactionordermaster().getLastoperation().equals("ESCAPE")) {

			if (!body.getTransactionordermaster().getLastoperation().equals("FINALSAVE")
					&& !body.getTransactionordermaster().getLastoperation().equals("CANCEL")) {
				jsonObject.add("data", transactionOrderDetail);
			} else {
				if (!body.getTransactionordermaster().getLastoperation().equals("CANCEL")) {
					jsonObject.addProperty("data", voucherno);
				}

			}
		}
		return jsonObject.toString();
	}
	
	// Moved to transaction service
	public String createBatch(JsonObject transactionDtl, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		JsonObject batch = new JsonObject();
		
		String batchid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
				adUserAccessToken.get("oprid"), "batch",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		batchid = new JSONObject(batchid).get("id").toString();
		
		batch.addProperty("orgid", adUserAccessToken.get("orgid"));
		batch.addProperty("oprid", adUserAccessToken.get("oprid"));
		batch.addProperty("batchid", batchid);
		batch.addProperty("productid", transactionDtl.get("productid").getAsLong());
		batch.addProperty("shortname", transactionDtl.get("shortname").getAsString());
		batch.addProperty("batchno", transactionDtl.get("batchno").getAsString());
		batch.addProperty("serialno", 1);
		batch.addProperty("displaypacking", transactionDtl.get("displaypacking").getAsString());
		batch.addProperty("pack", transactionDtl.get("productpack").getAsBigDecimal());
		batch.addProperty("mrp", transactionDtl.get("mrp").getAsBigDecimal());
		batch.addProperty("salerate", transactionDtl.get("salerate").getAsBigDecimal());
		batch.addProperty("purchaserate", transactionDtl.get("purchaserate").getAsBigDecimal());
		batch.addProperty("trade", transactionDtl.get("trade").getAsBigDecimal());
		batch.addProperty("expirydate", transactionDtl.get("expiry").getAsString());
		batch.addProperty("block", 0);
		batch.addProperty("bnew", 0);
		batch.addProperty("active", 1);
		batch.addProperty("createdon", transactionDtl.get("createdon").getAsString());
		batch.addProperty("createdby", transactionDtl.get("createdby").getAsLong());
		batch.addProperty("lastoperation", "INSERT");
		
		utility.executeCustomDML("batch", "batchid", batch, null, "INSERT", request);
		return batchid;
	}
	
	// Moved to transaction service
	public String createRate(JsonObject transactionDtl, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		JsonObject rate = new JsonObject();
		String rateid = "0";
		String sql = "select r.rateid from rate r where r.productid = " + transactionDtl.get("productid").getAsLong() + 
				" and r.mrp = " + transactionDtl.get("mrp").getAsBigDecimal() + " and r.traderate = " + transactionDtl.get("trade").getAsBigDecimal();
		JsonArray data = utility.executeQueryOnPool(sql, request);
		
		if (data.size() > 0) {
			rateid = data.get(0).getAsJsonObject().get("rateid").getAsString();
		} else {
			rateid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"), "rate",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			rateid = new JSONObject(rateid).get("id").toString();
			
			rate.addProperty("orgid", adUserAccessToken.get("orgid"));
			rate.addProperty("oprid", adUserAccessToken.get("oprid"));
			rate.addProperty("rateid", rateid);
			rate.addProperty("productid", transactionDtl.get("productid").getAsLong());
			rate.addProperty("mrp", transactionDtl.get("mrp").getAsBigDecimal());
			rate.addProperty("salerate", transactionDtl.get("salerate").getAsBigDecimal());
			rate.addProperty("purchaserate", transactionDtl.get("purchaserate").getAsBigDecimal());
			rate.addProperty("trade", transactionDtl.get("trade").getAsBigDecimal());
			rate.addProperty("block", 0);
			rate.addProperty("rnew", 0);
			rate.addProperty("active", 1);
			rate.addProperty("createdon", transactionDtl.get("createdon").getAsString());
			rate.addProperty("createdby", transactionDtl.get("createdby").getAsLong());
			rate.addProperty("lastoperation", "INSERT");
			
			utility.executeCustomDML("rate", "rateid", rate, null, "INSERT", request);
		}
		
		return rateid;
	}
	
	public long postCommonLedgerItemInsert(LedgerdetailBody ledgerDetailBody,
			HttpServletRequest request) {
		Gson gson = new Gson();
		String ledgerDetailString = gson.toJson(ledgerDetailBody);
		JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString, JsonObject.class);
		// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
		int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
		
		ledgerDetailJsonObject.remove("transactiondetailid");
		ledgerDetailJsonObject.remove("voucherdate");
		ledgerDetailJsonObject.remove("createdon");
		ledgerDetailJsonObject.remove("modifyon");
		ledgerDetailJsonObject.remove("legdtdisamt"); 
		ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
		ledgerDetailJsonObject.addProperty("voucherdate", ledgerDetailBody.getVoucherdate().toString());
		
		String ledgerdetailid = utility.executeIdGenerationProcedure(ledgerDetailBody.getOrgid().toString(),
				ledgerDetailBody.getOprid().toString(), "ledgerdetail",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
		
		ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);

		if (voucherNoInt == 0) {
			//transactionDetailJsonObject.addProperty("tnew", 0);
			ledgerDetailJsonObject.addProperty("active", 0);
			ledgerDetailJsonObject.addProperty("vouchermode", 0);
		} else {
			//transactionDetailJsonObject.addProperty("tnew", 1);
			ledgerDetailJsonObject.addProperty("active", 1);
			ledgerDetailJsonObject.addProperty("vouchermode", 2);
		}
		JsonObject jsonObjectWhere = new JsonObject();
		return utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
				jsonObjectWhere, "INSERT", request);
	}
	
	public long postCommonPaymentReceiptInsert(PaymentreceiptBody paymentReceiptBody,
			HttpServletRequest request) {
		Gson gson = new Gson();
		String paymentReceiptString = gson.toJson(paymentReceiptBody);
		JsonObject paymentReceiptJsonObject = new Gson().fromJson(paymentReceiptString, JsonObject.class);
		// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
		int voucherNoInt = Integer.parseInt(paymentReceiptBody.getBillinvoiceno().toString());
		
		paymentReceiptJsonObject.remove("paymentreceiptid");
		paymentReceiptJsonObject.remove("billdate");
		paymentReceiptJsonObject.remove("billno");
		paymentReceiptJsonObject.remove("createdon");
		paymentReceiptJsonObject.remove("modifiedon");
		
		paymentReceiptJsonObject.addProperty("createdon", paymentReceiptBody.getCreatedon().toString());
		paymentReceiptJsonObject.addProperty("billdate", paymentReceiptBody.getBillinvoicedate().toString());
		paymentReceiptJsonObject.addProperty("billno", voucherNoInt);
		String paymentreceiptid = utility.executeIdGenerationProcedure(paymentReceiptBody.getOrgid().toString(),
				paymentReceiptBody.getOprid().toString(), "paymentreceipt",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		paymentreceiptid = new JSONObject(paymentreceiptid).get("id").toString();
		
		paymentReceiptJsonObject.addProperty("paymentreceiptid", paymentreceiptid);

		if (voucherNoInt == 0) {
			//transactionDetailJsonObject.addProperty("tnew", 0);
			paymentReceiptJsonObject.addProperty("active", 0);
			paymentReceiptJsonObject.addProperty("vouchermode", 0);
		} else {
			//transactionDetailJsonObject.addProperty("tnew", 1);
			paymentReceiptJsonObject.addProperty("active", 1);
			paymentReceiptJsonObject.addProperty("vouchermode", 2);
		}
		JsonObject jsonObjectWhere = new JsonObject();
		return utility.executeCustomDML("paymentreceipt", "paymentreceiptid", paymentReceiptJsonObject,
				jsonObjectWhere, "INSERT", request);
	}
	
	public long postCommonPaymentReceiptUpdate(PaymentreceiptBody paymentReceiptBody,
			HttpServletRequest request) {
		Gson gson = new Gson();
		String paymentReceiptString = gson.toJson(paymentReceiptBody);
		JsonObject paymentReceiptJsonObject = new Gson().fromJson(paymentReceiptString, JsonObject.class);
		// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
		int voucherNoInt = Integer.parseInt(paymentReceiptBody.getBillinvoiceno().toString());
		
		if (voucherNoInt == 0) {
			deletePaymentReceipt(paymentReceiptBody.getPaymentreceiptid().toString(), request);
		} else {
			deactivatePaymentReceipt(paymentReceiptBody.getLedgermasterid().toString(),
					paymentReceiptBody.getPaymentreceiptid().toString(), request);
		}
		paymentReceiptJsonObject.remove("paymentreceiptid");
		paymentReceiptJsonObject.remove("billdate");
		paymentReceiptJsonObject.remove("createdon");
		paymentReceiptJsonObject.remove("modifiedon");
		
		paymentReceiptJsonObject.addProperty("createdon", paymentReceiptBody.getCreatedon().toString());
		paymentReceiptJsonObject.addProperty("billdate", paymentReceiptBody.getBillinvoicedate().toString());
		
		String paymentreceiptid = utility.executeIdGenerationProcedure(paymentReceiptBody.getOrgid().toString(),
				paymentReceiptBody.getOprid().toString(), "paymentreceipt",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		paymentreceiptid = new JSONObject(paymentreceiptid).get("id").toString();
		
		paymentReceiptJsonObject.addProperty("paymentreceiptid", paymentreceiptid);

		if (voucherNoInt == 0) {
			//transactionDetailJsonObject.addProperty("tnew", 0);
			paymentReceiptJsonObject.addProperty("active", 0);
			paymentReceiptJsonObject.addProperty("vouchermode", 0);
		} else {
			//transactionDetailJsonObject.addProperty("tnew", 1);
			paymentReceiptJsonObject.addProperty("active", 1);
			paymentReceiptJsonObject.addProperty("vouchermode", 2);
		}
		JsonObject jsonObjectWhere = new JsonObject();
		return utility.executeCustomDML("paymentreceipt", "paymentreceiptid", paymentReceiptJsonObject,
				jsonObjectWhere, "INSERT", request);
	}
	
	public long postCommonLedgerItemUpdate(LedgerdetailBody ledgerDetailBody,
			HttpServletRequest request) {
		Gson gson = new Gson();
		String ledgerDetailString = gson.toJson(ledgerDetailBody);
		JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString, JsonObject.class);
		// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
		int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
		if (voucherNoInt == 0) {
			deleteLedgerDetail(ledgerDetailBody.getLedgerdetailid().toString(), request);
		} else {
			deactivateLedgerDetail(ledgerDetailBody.getLedgermasterid().toString(),
					ledgerDetailBody.getLedgerdetailid().toString(), request);
		}
		ledgerDetailJsonObject.remove("transactiondetailid");
		ledgerDetailJsonObject.remove("voucherdate");
		ledgerDetailJsonObject.remove("createdon");
		ledgerDetailJsonObject.remove("modifyon");
		ledgerDetailJsonObject.remove("legdtdisamt"); 
		ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
		ledgerDetailJsonObject.addProperty("voucherdate", ledgerDetailBody.getVoucherdate().toString());
		
		String ledgerdetailid = utility.executeIdGenerationProcedure(ledgerDetailBody.getOrgid().toString(),
				ledgerDetailBody.getOprid().toString(), "ledgerdetail",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
		
		ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);

		if (voucherNoInt == 0) {
			//transactionDetailJsonObject.addProperty("tnew", 0);
			ledgerDetailJsonObject.addProperty("active", 0);
			ledgerDetailJsonObject.addProperty("vouchermode", 0);
		} else {
			//transactionDetailJsonObject.addProperty("tnew", 1);
			ledgerDetailJsonObject.addProperty("active", 1);
			ledgerDetailJsonObject.addProperty("vouchermode", 2);
		}
		JsonObject jsonObjectWhere = new JsonObject();
		return utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
				jsonObjectWhere, "INSERT", request);
	}
	
	public String postCommonLedgerFinalSave(LedgerMstBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		int voucherNoInt = Integer.parseInt(body.getLedgermaster().getVoucherno().toString());
		Object[] params = new Object[] {};
		String query = "", voucherno = "";
		LedgermasterBody ledgerMaster = body.getLedgermaster();
		// Generating the voucher no if 0 is received in voucher no
		if (voucherNoInt == 0) {
			voucherno = utility.executeInvoiceNoGenerationProcedure(ledgerMaster.getOrgid().toString(),
					ledgerMaster.getOprid().toString(), ledgerMaster.getDoctypeid().toString(),
					ledgerMaster.getFiyearid().toString(), ledgerMaster.getStoreid().toString(), "0",
					request.getHeader("rightId"), token, request.getRequestURL().toString());

			voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
			 
		} else {
			// In case the voucher no is already generated delete the records that are
			// deactivated from ledger detail
			String sql = "DELETE FROM ledgerdetail\r\n" + "WHERE     orgid = " + adUserAccessToken.get("orgid")
					+ "      AND oprid = " + adUserAccessToken.get("oprid") + "      AND ledgermasterid = "
					+ body.getLedgermaster().getLedgermasterid() + " " + "      AND active = 0";
			utility.executeDMLQueryOnPool(sql, params, request);
			voucherno = body.getLedgermaster().getVoucherno().toString();
			
			// And from payment receipt
			
			sql = "DELETE FROM paymentreceipt\r\n" + "WHERE     orgid = " + adUserAccessToken.get("orgid")
			+ "      AND oprid = " + adUserAccessToken.get("oprid") + "      AND ledgermasterid = "
			+ body.getLedgermaster().getLedgermasterid() + " " + "      AND active = 0";
	          utility.executeDMLQueryOnPool(sql, params, request);
	          
	          // Update amount in outstanding table from payment receipt after final save
	          query = "select  orgid , oprid, outstandingrefid , adjustamt from paymentreceipt where orgid ="
	  				+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
	  				+ " and ledgermasterid = " + body.getLedgermaster().getLedgermasterid();
	  		JsonArray data = utility.executeQueryOnPool(query, request);

	  		
	  		if (data.size() > 0) {
				for (JsonElement jsonObjectEach : data.getAsJsonArray()) {
					query = "update outstanding set receivedamt = ifnull(receivedamt,0) + ?" + " ,balanceamount = ifnull(balanceamount,0) + ?"
			  				+ "  where orgid = ?" + " AND oprid = ? " + " and outstandingid = ?";
			  		 params = new Object[] { jsonObjectEach.getAsJsonObject().get("adjustamt").getAsBigDecimal(),
			  				jsonObjectEach.getAsJsonObject().get("orgid").getAsInt(),
			  				jsonObjectEach.getAsJsonObject().get("oprid").getAsInt(),
			  				jsonObjectEach.getAsJsonObject().get("outstandingrefid").getAsBigDecimal()};
			  		 utility.executeDMLQueryOnPool(query, params, request);
				}
			} 
		}

		// Update the voucher no , mode , active  in final save in ledger detail
		query = "update ledgerdetail set voucherno =" + voucherno
				+ " , vouchermode =2 , active =1 ,  voucherdate = '"
				+ body.getLedgermaster().getVoucherdate() + "' where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + " and  ledgermasterid = "
				+ body.getLedgermaster().getLedgermasterid();
		utility.executeDMLQueryOnPool(query, params, request);
		
		// Update the voucher no , mode , active in final save in payment receipt
		query = "update paymentreceipt set voucherno =" + voucherno + " , vouchermode =2 , active =1 ,  voucherdate = '"
				+ body.getLedgermaster().getVoucherdate() + "' where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + " and  ledgermasterid = "
				+ body.getLedgermaster().getLedgermasterid();
		utility.executeDMLQueryOnPool(query, params, request);


		// Updating the master records
		JsonObject ledgermaster = new JsonObject();
		ledgermaster.addProperty("voucherno", voucherno);
		ledgermaster.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
		ledgermaster.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
		ledgermaster.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
		ledgermaster.addProperty("entrytype", body.getLedgermaster().getEntrytype());
		ledgermaster.addProperty("totalamount", body.getLedgermaster().getTotalamount());
		ledgermaster.addProperty("totaldiscount", body.getLedgermaster().getTotaldiscount());
		ledgermaster.addProperty("purpose", body.getLedgermaster().getPurpose());
		ledgermaster.addProperty("referenceid", body.getLedgermaster().getReferenceid());
		ledgermaster.addProperty("vouchermode", 2);
		ledgermaster.addProperty("companycode", body.getLedgermaster().getCompanycode());
		ledgermaster.addProperty("description", body.getLedgermaster().getDescription());
		ledgermaster.addProperty("active", 1);
		ledgermaster.remove("ledgeraccountid");
		ledgermaster.remove("doctypeid");
		ledgermaster.remove("fiyearid");
		ledgermaster.remove("storeid");
		JsonObject ledgermasterwhere = new JsonObject();
		ledgermasterwhere.addProperty("orgid", adUserAccessToken.get("orgid"));
		ledgermasterwhere.addProperty("oprid", adUserAccessToken.get("oprid"));
		ledgermasterwhere.addProperty("ledgermasterid", body.getLedgermaster().getLedgermasterid());

		utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermaster, ledgermasterwhere,
				"UPDATE", request);
		 
		return voucherno;
	}
	
	public String postCommonLedgerDiscountEntry(LedgerMstBody body, String ledgermasterid, HttpServletRequest request)
			throws JsonProcessingException {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		// int voucherNoInt =
		// Integer.parseInt(body.getLedgermaster().getVoucherno().toString());
		// Object[] params = new Object[] {};
		String  response = "", disledgermasterid = "", ledgerdetailid = "";
		long result = 0;
		ObjectMapper mapper = new ObjectMapper();
		String ledgerMasterString = mapper.writeValueAsString(body.getLedgermaster());
		JsonObject ledgermaster = (JsonObject) JsonParser.parseString(ledgerMasterString);
		JsonArray ledgerDetailList = ledgermaster.getAsJsonArray("ledgerdetail").deepCopy();
		ledgermaster.remove("ledgerdetail");
		//JsonArray paymentReceiptList = ledgermaster.getAsJsonArray("paymentreceipt").deepCopy();
		ledgermaster.remove("paymentreceipt");

		disledgermasterid = utility.executeIdGenerationProcedure(ledgermaster.get("orgid").getAsString(),
				ledgermaster.get("oprid").getAsString(), "ledgermaster",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		disledgermasterid = new JSONObject(disledgermasterid).getString("id");

		ledgermaster.addProperty("orgid", adUserAccessToken.get("orgid"));
		ledgermaster.addProperty("oprid", adUserAccessToken.get("oprid"));
		ledgermaster.addProperty("ledgermasterid", disledgermasterid);
		ledgermaster.addProperty("voucherno", 0);
		ledgermaster.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
		ledgermaster.addProperty("voucherseries", "DS");
		ledgermaster.addProperty("vouchertype", "JV");
		ledgermaster.addProperty("totalamount", body.getLedgermaster().getTotalamount());
		ledgermaster.addProperty("totaldiscount", body.getLedgermaster().getTotaldiscount());
		ledgermaster.addProperty("purpose", body.getLedgermaster().getPurpose());
		ledgermaster.addProperty("referenceid", body.getLedgermaster().getReferenceid());
		ledgermaster.addProperty("vouchermode", 1);
		ledgermaster.addProperty("createdon", body.getLedgermaster().getCreatedon().toString());
		ledgermaster.addProperty("createdby", body.getLedgermaster().getCreatedby());
		ledgermaster.addProperty("companycode", body.getLedgermaster().getCompanycode());
		ledgermaster.addProperty("description", body.getLedgermaster().getDescription());
		ledgermaster.addProperty("active", 0);
		ledgermaster.addProperty("lastoperation", "INSERT");
		ledgermaster.remove("ledgeraccountid");
		ledgermaster.remove("doctypeid");
		ledgermaster.remove("fiyearid");
		ledgermaster.remove("storeid");
		// Call for save
		JsonObject jobj = new JsonObject();

		 utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermaster, jobj, "INSERT", request);

		String discountid = "220801000100002";
		// Insert ledger detail record

		for (JsonElement element : ledgerDetailList) {
			JsonObject ledgerdetail = element.getAsJsonObject();
			
			ledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
					ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgerdetailid = new JSONObject(ledgerdetailid).getString("id");

			ledgerdetail.addProperty("ledgerdetailid", ledgerdetailid);
			ledgerdetail.addProperty("ledgermasterid", disledgermasterid);
			ledgerdetail.addProperty("orgid", adUserAccessToken.get("orgid"));
			ledgerdetail.addProperty("oprid", adUserAccessToken.get("oprid"));
			ledgerdetail.addProperty("createdon", body.getLedgermaster().getCreatedon().toString());
			ledgerdetail.addProperty("createdby", body.getLedgermaster().getCreatedby());
			ledgerdetail.addProperty("cramt", 0);
			ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getLegdtdisamt());
			// Discount ID Default
			ledgerdetail.addProperty("accountid",body.getLedgermaster().getLedgerdetail().get(0).getAccountid());
			ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
			ledgerdetail.addProperty("referenceid", ledgermasterid);
			ledgerdetail.addProperty("voucherseries", "DS");
			ledgerdetail.addProperty("vouchertype", "JV");
			ledgerdetail.remove("legdtdisamt"); 
			
			 utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT", request);

		}
		
		if (body.getLedgermaster().getLedgerdetail().get(0).getCramt().compareTo(BigDecimal.ZERO) == 0) {
			
			for (JsonElement element : ledgerDetailList) {
				JsonObject ledgerdetail = element.getAsJsonObject();

				ledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
						ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).getString("id");
				ledgerdetail.addProperty("createdon", body.getLedgermaster().getCreatedon().toString());
				ledgerdetail.addProperty("createdby", body.getLedgermaster().getCreatedby());
				ledgerdetail.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdetail.addProperty("ledgermasterid", disledgermasterid);
				ledgerdetail.addProperty("orgid",body.getLedgermaster().getOrgid() );
				ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
				
				// Discount ID Default
				ledgerdetail.addProperty("accountid",discountid);
				ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
				ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
				ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
				ledgerdetail.addProperty("identity", 10); // identity expenditure
				ledgerdetail.addProperty("dramt", 0);
				ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getLegdtdisamt());
				ledgerdetail.addProperty("referenceid", ledgermasterid);
				ledgerdetail.addProperty("voucherseries", "DS");
				ledgerdetail.addProperty("vouchertype", "JV");
				ledgerdetail.remove("legdtdisamt"); 
				
				 utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT", request);
			}
		}
		if (result >= 0) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			jsonObject.addProperty("message", "Record Updated Successfully");
			jsonObject.addProperty("id", disledgermasterid);
	
			response = jsonObject.toString();

		} else {
			response = "Exception";
		}
		return response;
	}
	
	public long deleteLedgerMasterDetail(String ledgermasterid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from ledgerdetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and ledgermasterid = " + ledgermasterid;
		utility.executeDMLQueryOnPool(query, params, request);
		
		query = "delete from paymentreceipt where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and ledgermasterid = " + ledgermasterid;
		utility.executeDMLQueryOnPool(query, params, request);

		query = "delete from ledgerdetail where orgid = " + adUserAccessToken.get("orgid")
				+ " and oprid = " + adUserAccessToken.get("oprid") + "  and referenceid = "
				+ ledgermasterid;
		 utility.executeDMLQueryOnPool(query, params, request); 
		 
		query = "delete from ledgermaster where orgid = " + adUserAccessToken.get("orgid") + " and oprid = "+ adUserAccessToken.get("oprid") + "  and ledgermasterid = "
				+ ledgermasterid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}
	
	public long deleteLedgerDetail(String ledgerdetailid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from ledgerdetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and ledgerdetailid = " + ledgerdetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long deactivateLedgerDetail(String ledgermasterid, String ledgerdetailid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "update ledgerdetail set active = 0 where orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and ledgermasterid =" + ledgermasterid
				+ "  and  ledgerdetailid = " + ledgerdetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long postCommonLedgerItemDelete(LedgerdetailBody ledgerDetailBody,
			HttpServletRequest request) {
		int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
		long result = 0;
		if (voucherNoInt == 0) {
			deleteLedgerDetail(ledgerDetailBody.getLedgerdetailid().toString(), request);
		} else {
			deactivateLedgerDetail(ledgerDetailBody.getLedgermasterid().toString(),
					ledgerDetailBody.getLedgerdetailid().toString(), request);
		}
		return result;
	}

	public long deletePaymentReceipt(String paymentreceiptid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from paymentreceipt where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and paymentreceiptid = " + paymentreceiptid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long deactivatePaymentReceipt(String ledgermasterid, String paymentreceiptid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "update paymentreceipt set active = 0 where orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and ledgermasterid =" + ledgermasterid
				+ "  and  paymentreceiptid = " + paymentreceiptid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long postPaymentReceiptItemDelete(PaymentreceiptBody paymentReceiptBody,
			HttpServletRequest request) {
		int voucherNoInt = Integer.parseInt(paymentReceiptBody.getBillinvoiceno().toString());
		long result = 0;
		if (voucherNoInt == 0) {
			deletePaymentReceipt(paymentReceiptBody.getPaymentreceiptid().toString(), request);
		} else {
			deactivatePaymentReceipt(paymentReceiptBody.getLedgermasterid().toString(),
					paymentReceiptBody.getPaymentreceiptid().toString(), request);
		}
		return result;
	}
	
	@Transactional
	public String postAccountPosting(LedgerMstBody body, HttpServletRequest request) throws JsonProcessingException {
		String response = "", ledgermasterid = "", ledgerdetailid = "", paymentreceiptid = "",cbledgerdetailid="";
		long result = 0;

		switch (body.getLedgermaster().getLastoperation()) {

		case "INSERT":

			JsonObject ledgermaster = formater.serializeObject(body.getLedgermaster());
			JsonArray ledgerDetailList = ledgermaster.getAsJsonArray("ledgerdetail").deepCopy();
			ledgermaster.remove("ledgerdetail");

			JsonArray paymentReceiptList = ledgermaster.getAsJsonArray("paymentreceipt").deepCopy();
			ledgermaster.remove("paymentreceipt");

			ledgermasterid = utility.executeIdGenerationProcedure(ledgermaster.get("orgid").getAsString(),
					ledgermaster.get("oprid").getAsString(), "ledgermaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgermasterid = new JSONObject(ledgermasterid).getString("id");

			for (JsonElement element : ledgerDetailList) {
				JsonObject ledgerdetail = element.getAsJsonObject();

				ledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
						ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).getString("id");

				ledgerdetail.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
				ledgerdetail.remove("legdtdisamt"); 
				
				utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT", request);
			}

			if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CR")
					|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BR")
					|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CP")
					|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BP")) {

				// cash and bank entry call
				for (JsonElement element : ledgerDetailList) {
					JsonObject ledgerdetail = element.getAsJsonObject();

				    cbledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
							ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					cbledgerdetailid = new JSONObject(cbledgerdetailid).getString("id");

					ledgerdetail.addProperty("ledgerdetailid", cbledgerdetailid);
					ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
					ledgerdetail.addProperty("orgid", body.getLedgermaster().getOrgid());
					ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
					// Ledger Account Id for Cash and bank
					ledgerdetail.addProperty("accountid", body.getLedgermaster().getLedgeraccountid());
					ledgerdetail.addProperty("identity", 9);
					ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
					ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
					ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
					ledgerdetail.remove("legdtdisamt"); 
					if (body.getLedgermaster().getVouchertype().equals("CR")
							|| body.getLedgermaster().getVouchertype().equals("BR")) {
						ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getDramt());
						ledgerdetail.addProperty("dramt", 0);
					} else {
						ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getCramt());
						ledgerdetail.addProperty("cramt", 0);
					}
					result = utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT",
							request);
				}
			}

			// Update the cbledgerdetailid as reverse code in ledger detail
			Object[] params = new Object[] {};
			String query = "update ledgerdetail set reversecode =" + cbledgerdetailid
					+  " where  ledgermasterid = "
					+ ledgermasterid + " and ledgerdetailid = " + ledgerdetailid;
			utility.executeDMLQueryOnPool(query, params, request);
			
			if (body.getLedgermaster().getLedgerdetail().get(0).getAdjusted() == 1) {
				for (JsonElement element : paymentReceiptList) {
					JsonObject paymentreceipt = element.getAsJsonObject();

					paymentreceiptid = utility.executeIdGenerationProcedure(paymentreceipt.get("orgid").getAsString(),
							paymentreceipt.get("oprid").getAsString(), "paymentreceipt",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					paymentreceiptid = new JSONObject(paymentreceiptid).getString("id");

					paymentreceipt.addProperty("paymentreceiptid", paymentreceiptid);
					paymentreceipt.addProperty("ledgermasterid", ledgermasterid);

					utility.executeCustomDML("paymentreceipt", "paymentreceiptid", paymentreceipt, null, "INSERT",
							request);
				}
			}

			// ledger master insert call
			ledgermaster.addProperty("ledgermasterid", ledgermasterid);
			ledgermaster.remove("ledgeraccountid");
			ledgermaster.remove("doctypeid");
			ledgermaster.remove("fiyearid");
			ledgermaster.remove("storeid");
			// ledgermaster.add("voucherno",
			// data.get(0).getAsJsonObject().get("voucherno"));
			// String voucherno = data.get(0).getAsJsonObject().get("voucherno").toString();
			result = utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermaster, null, "INSERT", request);

			
			if (body.getLedgermaster().getLedgerdetail().get(0).getLegdtdisamt().compareTo(BigDecimal.ZERO) > 0) {
				
				// ledger master discount insert call 
				response = postCommonLedgerDiscountEntry(body, ledgermasterid, request);
				
				// Update the referenceid  as disc. ledgermst id in ledger detail
		         params = new Object[] {};
				 query = "update ledgerdetail set referenceid =" + new JSONObject(response).getString("id")
						+  " where  ledgermasterid = "
						+ ledgermasterid;
				utility.executeDMLQueryOnPool(query, params, request);
			}
			break;

		case "UPDATE":
			ledgermasterid = body.getLedgermaster().getLedgermasterid().toString();
			 ledgermaster = formater.serializeObject(body.getLedgermaster());
			 ledgerDetailList = ledgermaster.getAsJsonArray("ledgerdetail").deepCopy();
			 query = "";
			 params = new Object[] {};
			for (LedgerdetailBody ledgerDetailBody : body.getLedgermaster().getLedgerdetail()) {
				switch (ledgerDetailBody.getLastoperation()) {
				// Adding new item to exiting grid
				case "INSERT":

					// Adding new item to exiting grid
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("JV")) {

						result = postCommonLedgerItemInsert(ledgerDetailBody, request);
					}
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CP")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BP")) {

						if (body.getLedgermaster().getLedgerdetail().get(0).getLegdtdisamt()
								.compareTo(BigDecimal.ZERO) > 0) {
							// ledger master discount insert call
							response = postCommonLedgerDiscountEntry(body, ledgermasterid, request);

							// Customer entry party wise
							Gson gson = new Gson();
							String ledgerDetailString = gson.toJson(ledgerDetailBody);
							JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString,
									JsonObject.class);
							// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
							int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());

							ledgerDetailJsonObject.remove("transactiondetailid");
							ledgerDetailJsonObject.remove("voucherdate");
							ledgerDetailJsonObject.remove("createdon");
							ledgerDetailJsonObject.remove("modifyon");
							ledgerDetailJsonObject.remove("legdtdisamt");
							ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
							ledgerDetailJsonObject.addProperty("voucherdate",
									ledgerDetailBody.getVoucherdate().toString());

							ledgerdetailid = utility.executeIdGenerationProcedure(
									ledgerDetailBody.getOrgid().toString(), ledgerDetailBody.getOprid().toString(),
									"ledgerdetail", request.getHeader("authorization").split(" ")[1],
									request.getRequestURL().toString());
							ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();

							ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);
							ledgerDetailJsonObject.addProperty("referenceid", new JSONObject(response).getString("id"));

							if (voucherNoInt == 0) {
								ledgerDetailJsonObject.addProperty("tnew", 0);
								ledgerDetailJsonObject.addProperty("active", 0);
								ledgerDetailJsonObject.addProperty("vouchermode", 0);
							} else {
								ledgerDetailJsonObject.addProperty("tnew", 1);
								ledgerDetailJsonObject.addProperty("active", 1);
								ledgerDetailJsonObject.addProperty("vouchermode", 2);
							}
							JsonObject jsonObjectWhere = new JsonObject();
							utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
									jsonObjectWhere, "INSERT", request);

							// Cash and bank account entry

							for (JsonElement element : ledgerDetailList) {
								JsonObject ledgerdetail = element.getAsJsonObject();

								cbledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
										ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
										request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
								cbledgerdetailid = new JSONObject(cbledgerdetailid).getString("id");

								ledgerdetail.addProperty("ledgerdetailid", cbledgerdetailid);
								ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
								ledgerdetail.addProperty("orgid", body.getLedgermaster().getOrgid());
								ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
								// Ledger Account Id for Cash and bank
								ledgerdetail.addProperty("accountid", body.getLedgermaster().getLedgeraccountid());
								ledgerdetail.addProperty("identity", 9);
								ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
								ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
								ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
								ledgerdetail.addProperty("referenceid", new JSONObject(response).getString("id"));
								ledgerdetail.remove("legdtdisamt"); 
								if (body.getLedgermaster().getVouchertype().equals("CR")
										|| body.getLedgermaster().getVouchertype().equals("BR")) {
									ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getDramt());
									ledgerdetail.addProperty("dramt", 0);
								} else {
									ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getCramt());
									ledgerdetail.addProperty("cramt", 0);
								}
								result = utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT",
										request);
							}
							
							// Update the cbledgerdetailid as reverse code in ledger detail
							 params = new Object[] {};
							 query = "update ledgerdetail set reversecode =" + cbledgerdetailid
									+  " where  ledgermasterid = "
									+ ledgermasterid + " and ledgerdetailid = " + ledgerdetailid;
							utility.executeDMLQueryOnPool(query, params, request);

						} else {
							
							// Customer entry party wise
							Gson gson = new Gson();
							String ledgerDetailString = gson.toJson(ledgerDetailBody);
							JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString,
									JsonObject.class);
							// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
							int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());

							ledgerDetailJsonObject.remove("transactiondetailid");
							ledgerDetailJsonObject.remove("voucherdate");
							ledgerDetailJsonObject.remove("createdon");
							ledgerDetailJsonObject.remove("modifyon");
							ledgerDetailJsonObject.remove("legdtdisamt");
							ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
							ledgerDetailJsonObject.addProperty("voucherdate",
									ledgerDetailBody.getVoucherdate().toString());

							ledgerdetailid = utility.executeIdGenerationProcedure(
									ledgerDetailBody.getOrgid().toString(), ledgerDetailBody.getOprid().toString(),
									"ledgerdetail", request.getHeader("authorization").split(" ")[1],
									request.getRequestURL().toString());
							ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();

							ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);
							ledgerDetailJsonObject.addProperty("referenceid", 0);

							if (voucherNoInt == 0) {
								ledgerDetailJsonObject.addProperty("tnew", 0);
								ledgerDetailJsonObject.addProperty("active", 0);
								ledgerDetailJsonObject.addProperty("vouchermode", 0);
							} else {
								ledgerDetailJsonObject.addProperty("tnew", 1);
								ledgerDetailJsonObject.addProperty("active", 1);
								ledgerDetailJsonObject.addProperty("vouchermode", 2);
							}
							JsonObject jsonObjectWhere = new JsonObject();
							utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
									jsonObjectWhere, "INSERT", request);
							
							// Cash and bank account entry

							for (JsonElement element : ledgerDetailList) {
								JsonObject ledgerdetail = element.getAsJsonObject();

								cbledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
										ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
										request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
								cbledgerdetailid = new JSONObject(cbledgerdetailid).getString("id");

								ledgerdetail.addProperty("ledgerdetailid", cbledgerdetailid);
								ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
								ledgerdetail.addProperty("orgid", body.getLedgermaster().getOrgid());
								ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
								// Ledger Account Id for Cash and bank
								ledgerdetail.addProperty("accountid", body.getLedgermaster().getLedgeraccountid());
								ledgerdetail.addProperty("identity", 9);
								ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
								ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
								ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
								ledgerdetail.addProperty("referenceid", 0);
								ledgerdetail.remove("legdtdisamt"); 
								if (body.getLedgermaster().getVouchertype().equals("CR")
										|| body.getLedgermaster().getVouchertype().equals("BR")) {
									ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getDramt());
									ledgerdetail.addProperty("dramt", 0);
								} else {
									ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getCramt());
									ledgerdetail.addProperty("cramt", 0);
								}
								result = utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT",
										request);
							}
							
							// Update the cbledgerdetailid as reverse code in ledger detail
							 params = new Object[] {};
							 query = "update ledgerdetail set reversecode =" + cbledgerdetailid
									+  " where  ledgermasterid = "
									+ ledgermasterid + " and ledgerdetailid = " + ledgerdetailid;
							utility.executeDMLQueryOnPool(query, params, request);
						}
					}
					
					break;
					
				// Updating the existing record in the grid
				case "UPDATE":
					// Updating the existing record
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("JV")) {
						result = postCommonLedgerItemUpdate(ledgerDetailBody, request);
					}
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CP")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BP")) {

						query = "select referenceid from ledgerdetail where ledgermasterid ="
								+ body.getLedgermaster().getLedgermasterid() + " and orgid ="
								+ body.getLedgermaster().getOrgid() + " and oprid = "
								+ body.getLedgermaster().getOprid();
						JsonArray data = utility.executeQueryOnPool(query, request);
						if (data.size() > 0) {
							query = "delete from ledgermaster where orgid = " + body.getLedgermaster().getOrgid()
									+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgermasterid = "
									+ data.get(0).getAsJsonObject().get("referenceid").getAsString();
							utility.executeDMLQueryOnPool(query, params, request);
						}
						query = "delete from ledgerdetail where orgid =" + body.getLedgermaster().getOrgid()
								+ " and oprid = " + body.getLedgermaster().getOprid() + " and ledgermasterid = "
								+ body.getLedgermaster().getLedgermasterid();
						utility.executeDMLQueryOnPool(query, params, request);

						query = "delete from paymentreceipt where orgid =" + body.getLedgermaster().getOrgid()
								+ " and oprid = " + body.getLedgermaster().getOprid() + " and ledgermasterid = "
								+ body.getLedgermaster().getLedgermasterid();
						utility.executeDMLQueryOnPool(query, params, request);

						query = "delete from ledgerdetail where orgid = " + body.getLedgermaster().getOrgid()
								+ " and oprid = " + body.getLedgermaster().getOprid() + "  and referenceid = "
								+ body.getLedgermaster().getLedgermasterid();
						utility.executeDMLQueryOnPool(query, params, request);

						// ledger master discount insert call
						if (body.getLedgermaster().getLedgerdetail().get(0).getLegdtdisamt()
								.compareTo(BigDecimal.ZERO) > 0) {

							// discount insert call
							response = postCommonLedgerDiscountEntry(body, ledgermasterid, request);

							// customer party wise entry
							Gson gson = new Gson();
							String ledgerDetailString = gson.toJson(ledgerDetailBody);
							JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString,
									JsonObject.class);
							// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
							int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
							if (voucherNoInt == 0) {
								deleteLedgerDetail(ledgerDetailBody.getLedgerdetailid().toString(), request);
							} else {
								deactivateLedgerDetail(ledgerDetailBody.getLedgermasterid().toString(),
										ledgerDetailBody.getLedgerdetailid().toString(), request);
							}
							ledgerDetailJsonObject.remove("transactiondetailid");
							ledgerDetailJsonObject.remove("voucherdate");
							ledgerDetailJsonObject.remove("createdon");
							ledgerDetailJsonObject.remove("modifyon");
							ledgerDetailJsonObject.remove("legdtdisamt");
							ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
							ledgerDetailJsonObject.addProperty("voucherdate",
									ledgerDetailBody.getVoucherdate().toString());

							ledgerdetailid = utility.executeIdGenerationProcedure(
									ledgerDetailBody.getOrgid().toString(), ledgerDetailBody.getOprid().toString(),
									"ledgerdetail", request.getHeader("authorization").split(" ")[1],
									request.getRequestURL().toString());
							ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();

							ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);
							ledgerDetailJsonObject.addProperty("referenceid", new JSONObject(response).getString("id"));
							if (voucherNoInt == 0) {
								ledgerDetailJsonObject.addProperty("tnew", 0);
								ledgerDetailJsonObject.addProperty("active", 0);
								ledgerDetailJsonObject.addProperty("vouchermode", 0);
							} else {
								ledgerDetailJsonObject.addProperty("tnew", 1);
								ledgerDetailJsonObject.addProperty("active", 1);
								ledgerDetailJsonObject.addProperty("vouchermode", 2);
							}
							JsonObject jsonObjectWhere = new JsonObject();
							utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
									jsonObjectWhere, "INSERT", request);

							// cash and bank account entry

							for (JsonElement element : ledgerDetailList) {
								JsonObject ledgerdetail = element.getAsJsonObject();

								cbledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
										ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
										request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
								cbledgerdetailid = new JSONObject(cbledgerdetailid).getString("id");

								ledgerdetail.addProperty("ledgerdetailid", cbledgerdetailid);
								ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
								ledgerdetail.addProperty("orgid", body.getLedgermaster().getOrgid());
								ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
								// Ledger Account Id for Cash and bank
								ledgerdetail.addProperty("accountid", body.getLedgermaster().getLedgeraccountid());
								ledgerdetail.addProperty("identity", 9);
								ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
								ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
								ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
								ledgerdetail.addProperty("referenceid", new JSONObject(response).getString("id"));
								ledgerdetail.remove("legdtdisamt"); 
								if (body.getLedgermaster().getVouchertype().equals("CR")
										|| body.getLedgermaster().getVouchertype().equals("BR")) {
									ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getDramt());
									ledgerdetail.addProperty("dramt", 0);
								} else {
									ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getCramt());
									ledgerdetail.addProperty("cramt", 0);
								}
								result = utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT",
										request);
							}
							
							// Update the cbledgerdetailid as reverse code in ledger detail
							 params = new Object[] {};
							 query = "update ledgerdetail set reversecode =" + cbledgerdetailid
									+  " where  ledgermasterid = "
									+ ledgermasterid + " and ledgerdetailid = " + ledgerdetailid;
							utility.executeDMLQueryOnPool(query, params, request);
						} else {
							// customer party wise entry
							Gson gson = new Gson();
							String ledgerDetailString = gson.toJson(ledgerDetailBody);
							JsonObject ledgerDetailJsonObject = new Gson().fromJson(ledgerDetailString,
									JsonObject.class);
							// JsonObject jsonObject = transactionDetailJsonObject.get(0).getAsJsonObject();
							int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
							if (voucherNoInt == 0) {
								deleteLedgerDetail(ledgerDetailBody.getLedgerdetailid().toString(), request);
							} else {
								deactivateLedgerDetail(ledgerDetailBody.getLedgermasterid().toString(),
										ledgerDetailBody.getLedgerdetailid().toString(), request);
							}
							ledgerDetailJsonObject.remove("transactiondetailid");
							ledgerDetailJsonObject.remove("voucherdate");
							ledgerDetailJsonObject.remove("createdon");
							ledgerDetailJsonObject.remove("modifyon");
							ledgerDetailJsonObject.remove("legdtdisamt");
							ledgerDetailJsonObject.addProperty("createdon", ledgerDetailBody.getCreatedon().toString());
							ledgerDetailJsonObject.addProperty("voucherdate",
									ledgerDetailBody.getVoucherdate().toString());

							ledgerdetailid = utility.executeIdGenerationProcedure(
									ledgerDetailBody.getOrgid().toString(), ledgerDetailBody.getOprid().toString(),
									"ledgerdetail", request.getHeader("authorization").split(" ")[1],
									request.getRequestURL().toString());
							ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();

							ledgerDetailJsonObject.addProperty("ledgerdetailid", ledgerdetailid);
							ledgerDetailJsonObject.addProperty("referenceid", 0);
							if (voucherNoInt == 0) {
							    ledgerDetailJsonObject.addProperty("tnew", 0);
								ledgerDetailJsonObject.addProperty("active", 0);
								ledgerDetailJsonObject.addProperty("vouchermode", 0);
							} else {
								ledgerDetailJsonObject.addProperty("tnew", 1);
								ledgerDetailJsonObject.addProperty("active", 1);
								ledgerDetailJsonObject.addProperty("vouchermode", 2);
							}
							JsonObject jsonObjectWhere = new JsonObject();
							utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerDetailJsonObject,
									jsonObjectWhere, "INSERT", request);

							// cash and bank account entry

							for (JsonElement element : ledgerDetailList) {
								JsonObject ledgerdetail = element.getAsJsonObject();

								cbledgerdetailid = utility.executeIdGenerationProcedure(ledgerdetail.get("orgid").getAsString(),
										ledgerdetail.get("oprid").getAsString(), "ledgerdetail",
										request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
								cbledgerdetailid = new JSONObject(cbledgerdetailid).getString("id");

								ledgerdetail.addProperty("ledgerdetailid", cbledgerdetailid);
								ledgerdetail.addProperty("ledgermasterid", ledgermasterid);
								ledgerdetail.addProperty("orgid", body.getLedgermaster().getOrgid());
								ledgerdetail.addProperty("oprid", body.getLedgermaster().getOprid());
								// Ledger Account Id for Cash and bank
								ledgerdetail.addProperty("accountid", body.getLedgermaster().getLedgeraccountid());
								ledgerdetail.addProperty("identity", 9);
								ledgerdetail.addProperty("voucherdate", body.getLedgermaster().getVoucherdate().toString());
								ledgerdetail.addProperty("voucherseries", body.getLedgermaster().getVoucherseries());
								ledgerdetail.addProperty("vouchertype", body.getLedgermaster().getVouchertype());
								ledgerdetail.addProperty("referenceid", 0);
								ledgerdetail.remove("legdtdisamt"); 
								if (body.getLedgermaster().getVouchertype().equals("CR")
										|| body.getLedgermaster().getVouchertype().equals("BR")) {
									ledgerdetail.addProperty("cramt", body.getLedgermaster().getLedgerdetail().get(0).getDramt());
									ledgerdetail.addProperty("dramt", 0);
								} else {
									ledgerdetail.addProperty("dramt", body.getLedgermaster().getLedgerdetail().get(0).getCramt());
									ledgerdetail.addProperty("cramt", 0);
								}
								result = utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail, null, "INSERT",
										request);
							}
							
							// Update the cbledgerdetailid as reverse code in ledger detail
							 params = new Object[] {};
							 query = "update ledgerdetail set reversecode =" + cbledgerdetailid
									+  " where  ledgermasterid = "
									+ ledgermasterid + " and ledgerdetailid = " + ledgerdetailid;
							utility.executeDMLQueryOnPool(query, params, request);
						}
					}
					break;
					
				// Deleting the existing record in the grid
				case "DELETE":
					 int voucherNoInt = Integer.parseInt(ledgerDetailBody.getVoucherno());
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("JV")) {
						result = postCommonLedgerItemDelete(ledgerDetailBody, request);
					}
					if (body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BR")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("CP")
							|| body.getLedgermaster().getLedgerdetail().get(0).getVouchertype().equals("BP")) {
						if (voucherNoInt == 0) {
						if(body.getLedgermaster().getLedgerdetail().get(0).getReferenceid() > 0) {
							//delete ledger detail for discount 
							query = "delete from ledgerdetail where orgid = " + body.getLedgermaster().getOrgid()
									+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgermasterid = "
									+ body.getLedgermaster().getLedgerdetail().get(0).getReferenceid();
							utility.executeDMLQueryOnPool(query, params, request);
							// delete ledger master for discount 
							query = "delete from ledgermaster where orgid = " + body.getLedgermaster().getOrgid()
									+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgermasterid = "
									+ body.getLedgermaster().getLedgerdetail().get(0).getReferenceid();
							utility.executeDMLQueryOnPool(query, params, request);
						}
				
							query = "delete from ledgerdetail where orgid = " + body.getLedgermaster().getOrgid()
									+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgerdetailid = "
									+ body.getLedgermaster().getLedgerdetail().get(0).getReversecode();
							utility.executeDMLQueryOnPool(query, params, request);
							
							query = "delete from ledgerdetail where orgid = " + body.getLedgermaster().getOrgid()
									+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgerdetailid = "
									+ body.getLedgermaster().getLedgerdetail().get(0).getLedgerdetailid();
							utility.executeDMLQueryOnPool(query, params, request);
						
					}
						else {
							if(body.getLedgermaster().getLedgerdetail().get(0).getReferenceid() > 0) {
								//delete ledger detail for discount 
								query = "update ledgerdetail set active = 0 where orgid = " + body.getLedgermaster().getOrgid()
										+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgermasterid = "
										+ body.getLedgermaster().getLedgerdetail().get(0).getReferenceid();
								utility.executeDMLQueryOnPool(query, params, request);
								// delete ledger master for discount 
								query = "update ledgermaster set active = 0 where orgid = " + body.getLedgermaster().getOrgid()
										+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgermasterid = "
										+ body.getLedgermaster().getLedgerdetail().get(0).getReferenceid();
								utility.executeDMLQueryOnPool(query, params, request);
							}
					
								query = "update ledgerdetail set active = 0 where orgid = " + body.getLedgermaster().getOrgid()
										+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgerdetailid = "
										+ body.getLedgermaster().getLedgerdetail().get(0).getReversecode();
								utility.executeDMLQueryOnPool(query, params, request);
								
								query = "update ledgerdetail set active = 0 where orgid = " + body.getLedgermaster().getOrgid()
										+ " and oprid = " + body.getLedgermaster().getOprid() + "  and ledgerdetailid = "
										+ body.getLedgermaster().getLedgerdetail().get(0).getLedgerdetailid();
								utility.executeDMLQueryOnPool(query, params, request);
							
						}
				}
					break;
				}
			}

			if (body.getLedgermaster().getLedgerdetail().get(0).getAdjusted() == 1) {

				for (PaymentreceiptBody paymentReceiptBody : body.getLedgermaster().getPaymentreceipt()) {
					switch (paymentReceiptBody.getLastoperation()) {
					// Adding new item to exiting grid
					case "INSERT":
						result = postCommonPaymentReceiptInsert(paymentReceiptBody, request);
						break;
						
					// Updating the existing record in the grid
					case "UPDATE":
						result = postCommonPaymentReceiptUpdate(paymentReceiptBody, request);
						break;
						
					// Deleting the existing record in the grid
					case "DELETE":
						result = postPaymentReceiptItemDelete(paymentReceiptBody, request);
						break;
					}
				}
			}
			
			// Check of the details exist for the master id if does not exist delete master
			// as well
			 query = "select count(*) countdet from ledgerdetail where ledgermasterid ="
					+ body.getLedgermaster().getLedgermasterid() + " and orgid =" + body.getLedgermaster().getOrgid()
					+ " and oprid = " + body.getLedgermaster().getOprid();
			JsonArray countdetail = utility.executeQueryOnPool(query, request);
			if (countdetail.size() > 0) {
				int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
				if (count == 0) {
					deleteLedgerMasterDetail(body.getLedgermaster().getLedgermasterid().toString(), request);
				}
			}
			break;

		case "FINALSAVE":

			int voucherno = Integer.parseInt(postCommonLedgerFinalSave(body, request));
			if (voucherno > 0 && voucherno != 0)
				result = voucherno;
			break;
			
		case "ESCAPE":
			int voucherNoInt = Integer.parseInt(body.getLedgermaster().getVoucherno().toString());
			//long result = 0;
			if (voucherNoInt == 0) {
			    deleteLedgerMasterDetail(body.getLedgermaster().getLedgermasterid().toString(), request);
				//result = deletePaymentReceipt(body.getLedgermaster().getPaymentreceipt().get(0).getPaymentreceiptid().toString(), request);
			} else {
			    params = new Object[] {};
				query = "delete from ledgerdetail where orgid =" + body.getLedgermaster().getOrgid() + " and oprid = "
					+ body.getLedgermaster().getOprid() + " and ledgerdetailid = " + ledgerdetailid + " " + "      AND tnew = 1";
				utility.executeDMLQueryOnPool(query, params, request);
				
				query = "delete from paymentreceipt where orgid =" + body.getLedgermaster().getOrgid() + " and oprid = "
					+ body.getLedgermaster().getOprid() + " and paymentreceiptid = " + paymentreceiptid + " " + "      AND tnew = 1";
					
				query = "update ledgerdetail set active = 1 where orgid =" + body.getLedgermaster().getOrgid() + " and oprid ="
					+ body.getLedgermaster().getOprid() + " and ledgermasterid =" + ledgermasterid
					+ "  and  ledgerdetailid = " + ledgerdetailid + " and active = 0";
				result = utility.executeDMLQueryOnPool(query, params, request);
				
				query = "update paymentreceipt set active = 0 where orgid =" + body.getLedgermaster().getOrgid() + " and oprid ="
					+ body.getLedgermaster().getOprid() + " and ledgermasterid =" + ledgermasterid
					+ "  and  paymentreceiptid = " + paymentreceiptid + " and active = 0";
			    result = utility.executeDMLQueryOnPool(query, params, request);
			}
			break;
	    		
		}
		
		if (result >= 0) {
			JsonObject responseObject = new JsonObject();
			responseObject.addProperty("code", 200);
			responseObject.addProperty("status", "Success");
			if (!body.getLedgermaster().getLastoperation().equals("ESCAPE")){
			responseObject.addProperty("message", "Record Updated Successfully");
			responseObject.addProperty("id", ledgermasterid);
			}
			if (body.getLedgermaster().getLastoperation().equals("FINALSAVE")
					|| body.getLedgermaster().getLastoperation().equals("ESCAPE")) {

				switch (body.getLedgermaster().getLastoperation()) {
				case "FINALSAVE":
					responseObject.addProperty("data", result);
					responseObject.addProperty("id", body.getLedgermaster().getLedgermasterid());
					break;

				case "ESCAPE":
					// Response for Escape
					responseObject.addProperty("code", 200);
					responseObject.addProperty("status", "Success");
					break;

				}
			} else {
				if (body.getLedgermaster().getVouchertype().equals("CR")
						|| body.getLedgermaster().getVouchertype().equals("BR")
						|| body.getLedgermaster().getVouchertype().equals("CP")
						|| body.getLedgermaster().getVouchertype().equals("BP")) {
					
					String sql ="SELECT LD.ledgerdetailid,\r\n" + 
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
							"        WHERE     ledgerdetail.orgid = 1\r\n" + 
							"              AND ledgerdetail.oprid = 1\r\n" + 
							"              AND ledgerdetail.ledgermasterid = LD.referenceid\r\n" + 
							"              AND ledgerdetail.vouchertype = 'JV'\r\n" + 
							"              AND voucherseries = 'DS'\r\n" + 
							"              AND ledgerdetail.identity = " + body.getLedgermaster().getLedgerdetail().get(0).getIdentity() + ")" + "legdtdisamt,\r\n" +
							"(SELECT account.accountname \r\n" + 
									"					   FROM account\r\n" + 
									"					 INNER JOIN ledgerdetail ON  account.accountid = ledgerdetail.accountid  \r\n" + 
									"		 WHERE     ledgerdetail.orgid = " + body.getLedgermaster().getOrgid() +  
									"			 AND ledgerdetail.oprid = " + body.getLedgermaster().getOprid() +  
									"			  AND ledgerdetail.ledgerdetailid = LD.reversecode) \r\n" + 
									"		 legdparentname,\r\n" +
									"(SELECT ledgerdetail.accountid\r\n" + 
									"	 FROM ledgerdetail\r\n" + 
									" WHERE     ledgerdetail.orgid = " + body.getLedgermaster().getOrgid() +  
									"    AND ledgerdetail.oprid = " + body.getLedgermaster().getOprid() +  
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
							"            AND LD.orgid =1\r\n" + 
							"            AND LD.oprid =1\r\n" + 
							"            AND LD.identity = " + body.getLedgermaster().getLedgerdetail().get(0).getIdentity() + 
							"            AND LD.vouchertype = '" + body.getLedgermaster().getVouchertype() +"'      \r\n" + 
							"group by LD.accountid,LD.identity,LD.ledgerdetailid;";
					JsonArray data = utility.executeQueryOnPool(sql, request);
					responseObject.add("data", data);
				} else {
					String sql = "SELECT ledgerdetail.*, account.accountname "
							+ "FROM ledgerdetail INNER JOIN account ON ledgerdetail.accountid = account.accountid "
							+ "WHERE ledgermasterid = " + ledgermasterid;
					JsonArray data = utility.executeQueryOnPool(sql, request);
					responseObject.add("data", data);
				}
			}
			
			response = responseObject.toString();
		} else {
			response = "Exception";
		}

		return response;
	}
	
	@Transactional
	public String postgrnmaster(GrnMstBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		int grnno = 0;
		String grnmasterid = "0";
		ObjectMapper mapper = new ObjectMapper();
		
		JsonObject grnmaster = JsonParser.parseString(mapper.writeValueAsString(body.getGrnmaster())).getAsJsonObject();
		if (grnmaster.get("lastoperation").getAsString().equals("INSERT")) {
			// Generating new GRN No
			String query = "select ((ifnull(max(grnno),0))+1)as grn from grnmaster" + " where orgid = " + grnmaster.get("orgid").getAsString()
					+ " AND oprid =" + grnmaster.get("oprid").getAsString();
			grnno = Integer.parseInt(utility.executeQueryOnPool(query, request).getAsJsonArray().get(0)
					.getAsJsonObject().get("grn").toString());

			// adding Consignment no
			grnmaster.addProperty("grnno", grnno);
			
			grnmasterid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"), "grnmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			grnmasterid = new JSONObject(grnmasterid).get("id").toString();
			grnmaster.addProperty("grnmasterid", grnmasterid);
		} else {
			// copy to variable in case of update
			grnno = grnmaster.get("grnno").getAsInt();
			grnmasterid = grnmaster.get("grnmasterid").getAsString();
		}
		
		JsonArray grndtlList = grnmaster.getAsJsonArray("grndetail").deepCopy();
		grnmaster.remove("grndetail");
		
		for (JsonElement element : grndtlList) {
			JsonObject transactiondetail = element.getAsJsonObject();
			
			if (transactiondetail.get("lastoperation").getAsString().equals("INSERT")) {
				String grndetailid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
						adUserAccessToken.get("oprid"), "grndetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				grndetailid = new JSONObject(grndetailid).get("id").toString();
				
				transactiondetail.addProperty("grndetailid", grndetailid);
				transactiondetail.addProperty("grnmasterid", grnmasterid);
			}
			
			utility.executeCustomDML("grndetail", "grndetailid", transactiondetail,
					null, transactiondetail.get("lastoperation").getAsString(), request);
		}
		
		utility.executeCustomDML("grnmaster", "grnmasterid", grnmaster,
				null, grnmaster.get("lastoperation").getAsString(), request);
		
		return "" + grnno;
	}

	@Transactional
	public String postProductLocationLink(List<ProductlocationlinkBody> body, HttpServletRequest request) {
		String response = "", productlocationlinkid = "";
		long result = 0;
		
		for (ProductlocationlinkBody productlocationlinkbody : body) {
			JsonObject Productlocationlink = new JsonObject();
			
				String query = "SELECT \r\n" + "  pl.productlocationlinkid,   pl.productid, pl.storeid\r\n" + "FROM\r\n"
						+ "    productlocationlink  pl\r\n" + "WHERE\r\n" + "    pl.orgid = "
						+ productlocationlinkbody.getOrgid() + " AND pl.oprid =" + productlocationlinkbody.getOprid()
						+ " AND pl.productid =" + productlocationlinkbody.getProductid() + " AND pl.storeid =" + productlocationlinkbody.getStoreid();

				JsonArray data = utility.executeQueryOnPool(query, request);

				if (data.size() <= 0) {
					productlocationlinkid = utility.executeIdGenerationProcedure(
							productlocationlinkbody.getOrgid().toString(),
							productlocationlinkbody.getOprid().toString(), "productlocationlink",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					productlocationlinkid = new JSONObject(productlocationlinkid).get("id").toString();
					
					Productlocationlink.addProperty("productlocationlinkid", productlocationlinkid);
					Productlocationlink.addProperty("active", 1);
					Productlocationlink.addProperty("orgid", productlocationlinkbody.getOrgid());
					Productlocationlink.addProperty("oprid", productlocationlinkbody.getOprid());
					Productlocationlink.addProperty("productid", productlocationlinkbody.getProductid());
					Productlocationlink.addProperty("locationid", productlocationlinkbody.getLocationid());
					Productlocationlink.addProperty("storeid", productlocationlinkbody.getStoreid());
					Productlocationlink.addProperty("lastoperation", productlocationlinkbody.getLastoperation());
					Productlocationlink.addProperty("createdby", productlocationlinkbody.getCreatedby());
					Productlocationlink.addProperty("createdon", productlocationlinkbody.getCreatedon().toString());

					result = utility.executeCustomDML("productlocationlink", "productlocationlinkid", Productlocationlink, null,
							"INSERT", request);
				} else {
					Object[] params = new Object[] {};
					// Delete previous record
					query = "delete from productlocationlink where orgid = " + productlocationlinkbody.getOrgid()
							+ " and oprid = " + productlocationlinkbody.getOprid() + " and productlocationlinkid = "
							+ data.get(0).getAsJsonObject().get("productlocationlinkid");
					utility.executeDMLQueryOnPool(query, params, request);

					// Insert new record
					productlocationlinkid = utility.executeIdGenerationProcedure(
							productlocationlinkbody.getOrgid().toString(), productlocationlinkbody.getOprid().toString(),
							"productlocationlink", request.getHeader("authorization").split(" ")[1],
							request.getRequestURL().toString());
					productlocationlinkid = new JSONObject(productlocationlinkid).get("id").toString();
					
					Productlocationlink.addProperty("productlocationlinkid", productlocationlinkid);
					Productlocationlink.addProperty("active", 1);
					Productlocationlink.addProperty("orgid", productlocationlinkbody.getOrgid());
					Productlocationlink.addProperty("oprid", productlocationlinkbody.getOprid());
					Productlocationlink.addProperty("productid", productlocationlinkbody.getProductid());
					Productlocationlink.addProperty("locationid", productlocationlinkbody.getLocationid());
					Productlocationlink.addProperty("storeid", productlocationlinkbody.getStoreid());
					Productlocationlink.addProperty("lastoperation", productlocationlinkbody.getLastoperation());
					Productlocationlink.addProperty("createdby", productlocationlinkbody.getCreatedby());
					Productlocationlink.addProperty("createdon", productlocationlinkbody.getCreatedon().toString());

					result = utility.executeCustomDML("productlocationlink", "productlocationlinkid", Productlocationlink, null,
							"INSERT", request);
				}
			}
	
		if (result > 0) {
			JsonObject responseObject = new JsonObject();
			responseObject.addProperty("code", 200);
			responseObject.addProperty("status", "Success");
			responseObject.addProperty("message", "Record Save Successfully");

			String sql = "select * from productlocationlink where productlocationlinkid = " + productlocationlinkid;
			JsonArray data = utility.executeQueryOnPool(sql, request);
			responseObject.add("datadtl", data);

			response = responseObject.toString();
		} else {
			response = "Exception";
		}

		return response;
	}

	
	@SuppressWarnings("unused")
	public String postTransactionStockFinalSave(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		int voucherNoInt = body.getTransactionmaster().getVoucherno();
		Object[] params = new Object[] {};
		String query = ""; String voucherno = "0";
		long result = 0;
		// Generating the voucher no if 0 is received in voucher no
		if (voucherNoInt == 0) {
			TransactionmasterBody trnMaster = body.getTransactionmaster();
			
			voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
					trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), "0", 
					request.getHeader("rightId"), token, request.getRequestURL().toString());
			
			voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();

		} else {
			// After final save modify first just reduce stock of deactivated items
			
			 query = "select transactiondetailid , qty ,  free , orgid, oprid, batchid, storeid, linkid from transactiondetail where orgid ="
						+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
						+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid() + " " + " AND active = 0";
				JsonArray data = utility.executeQueryOnPool(query, request);

				if (data.size() > 0) {
					for (JsonElement transactiondetailJsonElement : data) {
						switch (data.get(0).getAsJsonObject().get("linkid").getAsString()) {
						// Salable
						case "16":
							result = reduceStockGstCreditNote(body, request);
							break;
						// Expired
						case "220301000100007":
							// Breakage
						case "220301000100002":
							// Transfer
						case "220401000100012":
							result = increaseBatchStock(body, request);
							break;
						}
					}
				}
			// In case the voucher no is already generated delete the records that are deactivated
			String sql = "DELETE FROM transactiondetail \r\n" + "WHERE orgid = " + adUserAccessToken.get("orgid")
					+ " AND oprid = " + adUserAccessToken.get("oprid") + " AND transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid() + " " + " AND active = 0";
			utility.executeDMLQueryOnPool(sql, params, request);
			
			voucherno = body.getTransactionmaster().getVoucherno().toString();
			
		}

		// Update the voucher no , mode , active and new in final save
		query = "update transactiondetail set voucherno = " + voucherno
				+ " , refexpiryno = " + voucherno + 
				" ,vouchermode = 2 , active = 1 ,tnew = 0 ,  voucherdate = '"
				+ body.getTransactionmaster().getVoucherdate() + "' ,  refexpirydate = '"  
				+ body.getTransactionmaster().getVoucherdate() + "' ,  refexpiryseries = '" 
				+ body.getTransactionmaster().getVoucherseries() + "' where orgid = " + adUserAccessToken.get("orgid")
				+ " and oprid = " + adUserAccessToken.get("oprid") + " and  transactionmasterid = "
				+ body.getTransactionmaster().getTransactionmasterid();
		utility.executeDMLQueryOnPool(query, params, request);
		
		//  new updated record stock increases in salable and reduce in Expired
		 query = "select transactiondetailid , qty ,  free , orgid, oprid, batchid, storeid, linkid from transactiondetail where orgid ="
					+ adUserAccessToken.get("orgid") + " and oprid =" + adUserAccessToken.get("oprid")
					+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid();
			JsonArray data = utility.executeQueryOnPool(query, request);

			if (data.size() > 0) {
				for (JsonElement transactiondetailJsonElement : data) {
					switch (data.get(0).getAsJsonObject().get("linkid").getAsString()) {
					// Salable
					case "16":
						result = increaseBatchStock(body, request);
						break;
					// Expired
					case "220301000100007":
						// Breakage
					case "220301000100002":
						// Transfer
					case "220401000100012":
						if (body.getTransactionmaster().getVouchertype().equals("PREB")) {
						result = reduceStockGstCreditNote(body, request);
						}
						result = rvertStockPostClaim(body, request);
						break;
					}
				}
			}
		
		// Updating the master records
		JsonObject transactionmaster = new JsonObject();
		transactionmaster.addProperty("voucherno", voucherno);
		transactionmaster.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
		transactionmaster.addProperty("voucherseries", body.getTransactionmaster().getVoucherseries().toString());
		transactionmaster.addProperty("netamount", body.getTransactionmaster().getNetamount());
		transactionmaster.addProperty("customername", body.getTransactionmaster().getCustomername());
		transactionmaster.addProperty("customeradd1", body.getTransactionmaster().getCustomeradd1());
		transactionmaster.addProperty("customeradd2", body.getTransactionmaster().getCustomeradd2());
		transactionmaster.addProperty("customeradd3", body.getTransactionmaster().getCustomeradd3());
		transactionmaster.addProperty("accountid", body.getTransactionmaster().getAccountid());
		transactionmaster.addProperty("reason", body.getTransactionmaster().getReason());
		transactionmaster.addProperty("agencyid", body.getTransactionmaster().getAgencyid());
		transactionmaster.addProperty("cgstamount", body.getTransactionmaster().getCgstamount());
		transactionmaster.addProperty("sgstamount", body.getTransactionmaster().getSgstamount());
		transactionmaster.addProperty("igstamount", body.getTransactionmaster().getIgstamount());
		transactionmaster.addProperty("ugstamount", body.getTransactionmaster().getUgstamount());
		transactionmaster.addProperty("cessamount", body.getTransactionmaster().getCessamount());
		transactionmaster.addProperty("tcscharge", body.getTransactionmaster().getTcscharge());
		transactionmaster.addProperty("tcsamount", body.getTransactionmaster().getTcsamount());
		transactionmaster.addProperty("otheradditionamount", body.getTransactionmaster().getOtheradditionamount());
		transactionmaster.addProperty("otherdeductionamount", body.getTransactionmaster().getOtherdeductionamount());
		transactionmaster.addProperty("description", body.getTransactionmaster().getDescription());
		transactionmaster.addProperty("vouchermode", 2);
		transactionmaster.addProperty("gstamount", body.getTransactionmaster().getGstamount());
		transactionmaster.addProperty("totalgstamount", body.getTransactionmaster().getTotalgstamount());
		transactionmaster.addProperty("active", 1);
		transactionmaster.addProperty("creditnoteamount", body.getTransactionmaster().getCreditnoteamount());
		transactionmaster.addProperty("debitnoteamount", body.getTransactionmaster().getDebitnoteamount());
		transactionmaster.addProperty("adjcreditnoteno", body.getTransactionmaster().getAdjcreditnoteno());
		transactionmaster.addProperty("pending", body.getTransactionmaster().getPending());
		transactionmaster.addProperty("grossamount", body.getTransactionmaster().getGrossamount());
		transactionmaster.addProperty("mrpvalue", body.getTransactionmaster().getMrpvalue());
		transactionmaster.addProperty("roundof", body.getTransactionmaster().getRoundof());
		transactionmaster.addProperty("deliverytypedescid", body.getTransactionmaster().getDeliverytypedescid());
		transactionmaster.addProperty("ratediffdebitcreditnoteflag", body.getTransactionmaster().getRatediffdebitcreditnoteflag());
		if (body.getTransactionmaster().getAdjcreditnotedate() != null) {
			transactionmaster.addProperty("adjcreditnotedate",
					body.getTransactionmaster().getAdjcreditnotedate().toString());
		}

		transactionmaster.addProperty("billamount", body.getTransactionmaster().getBillamount());
		if (body.getTransactionmaster().getBilldate() != null) {
			transactionmaster.addProperty("billdate", body.getTransactionmaster().getBilldate().toString());
		}
		if (body.getTransactionmaster().getBillno() != null) {
			transactionmaster.addProperty("billno", body.getTransactionmaster().getBillno().toString());
		}
		if (body.getTransactionmaster().getCalculaterate() != null) {
			transactionmaster.addProperty("calculaterate", body.getTransactionmaster().getCalculaterate());
		}
		if (body.getTransactionmaster().getCreditdebitstatus() != null) {
			transactionmaster.addProperty("creditdebitstatus", body.getTransactionmaster().getCreditdebitstatus());
		}
		if (body.getTransactionmaster().getGstnet() != null) {
			transactionmaster.addProperty("gstnet", body.getTransactionmaster().getGstnet());
		}
		if (body.getTransactionmaster().getNongstvalue() != null) {
			transactionmaster.addProperty("nongstvalue", body.getTransactionmaster().getNongstvalue());
		}
		if (body.getTransactionmaster().getSalevalue() != null) {
			transactionmaster.addProperty("salevalue", body.getTransactionmaster().getSalevalue());
		}
		if (body.getTransactionmaster().getZeroratedvalue() != null) {
			transactionmaster.addProperty("zeroratedvalue", body.getTransactionmaster().getZeroratedvalue());
		}
		if (body.getTransactionmaster().getNilratedvalue() != null) {
			transactionmaster.addProperty("nilratedvalue", body.getTransactionmaster().getNilratedvalue());
		}
		if (body.getTransactionmaster().getExemptedvalue() != null) {
			transactionmaster.addProperty("exemptedvalue", body.getTransactionmaster().getExemptedvalue());
		}

		if (body.getTransactionmaster().getDeliverytypeid() != null) {
			transactionmaster.addProperty("deliverytypeid", body.getTransactionmaster().getDeliverytypeid());
		}
		if (body.getTransactionmaster().getDeliverytypedescid() != null) {
			transactionmaster.addProperty("deliverytypedescid", body.getTransactionmaster().getDeliverytypedescid());
		}
		if (body.getTransactionmaster().getDeliverytypedesc() != null) {
			transactionmaster.addProperty("deliverytypedesc", body.getTransactionmaster().getDeliverytypedesc());
		}
		if (body.getTransactionmaster().getPriority() != null) {
			transactionmaster.addProperty("priority", body.getTransactionmaster().getPriority());
		}
		
		transactionmaster.addProperty("creditnoteflag ", body.getTransactionmaster().getCreditnoteflag());
		transactionmaster.addProperty("creditnotetype", body.getTransactionmaster().getCreditnotetype());
		transactionmaster.addProperty("cdp", body.getTransactionmaster().getCdp());
		transactionmaster.addProperty("cashdiscountpercent ", body.getTransactionmaster().getCashdiscountpercent());
		transactionmaster.addProperty("cashdiscountamount ", body.getTransactionmaster().getCashdiscountamount());
		transactionmaster.addProperty("productdiscountamount", body.getTransactionmaster().getProductdiscountamount());
		transactionmaster.addProperty("packingamount", body.getTransactionmaster().getPackingamount());
		transactionmaster.addProperty("post", body.getTransactionmaster().getPost());
		transactionmaster.addProperty("paymentmode", body.getTransactionmaster().getPaymentmode());
		transactionmaster.addProperty("duedate", body.getTransactionmaster().getDuedate() == null ? null : body.getTransactionmaster().getDuedate().toString());
		transactionmaster.addProperty("qtycount", body.getTransactionmaster().getQtycount() == null ? null : body.getTransactionmaster().getQtycount().toString());
		transactionmaster.addProperty("itemcount", body.getTransactionmaster().getItemcount() == null ? null : body.getTransactionmaster().getItemcount().toString());
		transactionmaster.addProperty("verificationflag", body.getTransactionmaster().getVerificationflag());
		transactionmaster.addProperty("fiyearid", body.getTransactionmaster().getFiyearid());
		transactionmaster.addProperty("trnmonth", body.getTransactionmaster().getTrnmonth());
		transactionmaster.addProperty("trnyear", body.getTransactionmaster().getTrnyear());
		transactionmaster.addProperty("invoicetime", body.getTransactionmaster().getInvoicetime().toString());
		transactionmaster.addProperty("invcrtime", body.getTransactionmaster().getInvcrtime().toString());
		JsonObject transactionmasterwhere = new JsonObject();
		transactionmasterwhere.addProperty("orgid", adUserAccessToken.get("orgid"));
		transactionmasterwhere.addProperty("oprid", adUserAccessToken.get("oprid"));
		transactionmasterwhere.addProperty("transactionmasterid", body.getTransactionmaster().getTransactionmasterid());

		utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, transactionmasterwhere,
				"UPDATE", request);
		return voucherno;
	}
	
	public JsonArray getCompanyOrderModify(QueryApiBody body, HttpServletRequest request) {
		JsonArray orders = new JsonArray();
		
		 String query = "select 0 orderdetailid, p.productid productid, p.productname productname, p.displaypacking pack, p.boxpack boxpack, g.title agencygroup, \r\n" + 
				 "ifnull((select sum(dt.qty+dt.free) from transactionmaster ms inner join transactiondetail dt \r\n" + 
				 "on dt.transactionmasterid = ms.transactionmasterid where ms.agencyid = " + nullCheck(body.getParam2()) + " \r\n" + 
				 "and ms.canflag = 0 \r\n" + 
				 "and ms.active = 1 \r\n" + 
				 "and ms.vouchertype = 'PR' \r\n" + 
				 "and ifnull(ms.agencygroupid,'XX') = ifnull(m.agencygroupid,'XX') \r\n" + 
				 "and ms.accountid = ifnull(" + nullCheck(body.getParam4()) + ",ms.accountid) \r\n";
		 
		 if (body.getParam1().equals("CurMonth") || body.getParam1().equals("LastMonth")) {
			 query = query + "and ms.trnmonth = (CONVERT(ifnull(" + nullCheck(body.getParam5()) + ",m.trnmonth),DECIMAL) - 1) \r\n";
		 } else if (body.getParam1().equals("LastYear")) {
			 query = query + "and ms.trnyear = (CONVERT(ifnull(" + nullCheck(body.getParam6()) + ",m.trnyear),DECIMAL) - 1) \r\n";
		 } else if (body.getParam1().equals("DateWise")) {
			 query = query + "and ms.voucherdate between SUBDATE(ifnull(" + nullCheck(body.getParam7()) + 
					 ",ms.voucherdate), INTERVAL 30 DAY) and ifnull(" + nullCheck(body.getParam7()) + ",ms.voucherdate) \r\n";
		 }
		 
		 query = query + "and dt.productid = d.productid \r\n" + 
		 		"group by dt.productid),0) lastsale, \r\n";
		 
		 if (nullCheck(body.getParam9()).equals("Y")) {
			 query = query + "ifnull((select sum(dt.qty+dt.free) from transactionmaster ms inner join transactiondetail dt \r\n" + 
			 		"on dt.transactionmasterid = ms.transactionmasterid where ms.agencyid = " + nullCheck(body.getParam2()) + " \r\n" + 
			 		"and ms.canflag = 0 \r\n" + 
			 		"and ms.active = 1 \r\n" + 
			 		"and ms.vouchertype = 'PR' \r\n" + 
			 		"and ifnull(ms.issubstockiest,0) = 0 \r\n" + 
			 		"and ifnull(ms.agencygroupid,'XX') = ifnull(m.agencygroupid,'XX') \r\n" + 
			 		"and ms.accountid = ifnull(" + nullCheck(body.getParam4()) + ",ms.accountid) \r\n" + 
			 		"and ms.trnmonth = ifnull(" + nullCheck(body.getParam5()) + ",ms.trnmonth) \r\n" + 
			 		"and ms.trnyear = ifnull(" + nullCheck(body.getParam6()) + ",ms.trnyear) \r\n" + 
			 		"and ms.voucherdate between ifnull(" + nullCheck(body.getParam7()) + ",ms.voucherdate) and ifnull(" + nullCheck(body.getParam8()) + 
			 		",ms.voucherdate)  \r\n" + 
			 		"and dt.productid = d.productid \r\n" + 
			 		"group by dt.productid),0) currsale, \r\n" + 
			 		"ifnull((select sum(dt.qty+dt.free) from transactionmaster ms inner join transactiondetail dt \r\n" + 
			 		"on dt.transactionmasterid = ms.transactionmasterid where ms.agencyid = " + nullCheck(body.getParam2()) + " \r\n" + 
			 		"and ms.canflag = 0 \r\n" + 
			 		"and ms.active = 1 \r\n" + 
			 		"and ms.vouchertype = 'PR' \r\n" + 
			 		"and ifnull(ms.issubstockiest,0) = 1 \r\n" + 
			 		"and ifnull(ms.agencygroupid,'XX') = ifnull(m.agencygroupid,'XX') \r\n" + 
			 		"and ms.accountid = ifnull(" + nullCheck(body.getParam4()) + ",ms.accountid) \r\n" + 
			 		"and ms.trnmonth = ifnull(" + nullCheck(body.getParam5()) + ",ms.trnmonth) \r\n" + 
			 		"and ms.trnyear = ifnull(" + nullCheck(body.getParam6()) + ",ms.trnyear) \r\n" + 
			 		"and ms.voucherdate between ifnull(" + nullCheck(body.getParam7()) + ",ms.voucherdate) and ifnull(" + nullCheck(body.getParam8()) + 
			 		",ms.voucherdate)  \r\n" + 
			 		"and dt.productid = d.productid \r\n" + 
			 		"group by dt.productid),0) substockiestsale, \r\n";
		 } else {
			 query = query + "sum(d.qty+d.free) currsale, \r\n" + 
					 "0 substockiestsale, \r\n";
		 }
		 
		 query = query + "ifnull((select ts.closing from transactionstock ts where ts.batchid = d.batchid and ts.storeid = d.storeid),0) stock, \r\n" + 
		 		"ifnull(p.pendingqty,0) pendingqty, \r\n" + 
		 		"0 orderqty, \r\n" + 
		 		"ifnull((select concat(round(sc.qtyone),'+',round(sc.freeone)) from productscheme sc where sc.productid = p.productid),'0+0') scheme, \r\n" + 
		 		"ifnull((select round(sc.qtyone) from productscheme sc where sc.productid = p.productid),0) schemeqty, \r\n" + 
		 		"ifnull((select round(sc.freeone) from productscheme sc where sc.productid = p.productid),0) schemefree, \r\n" + 
		 		"ifnull((select pt.title from producttype pt where pt.producttypeid = p.producttypeid),'NA') producttype, \r\n" + 
		 		"'Normal' productcategory, \r\n" + 
		 		"d.voucherno voucherno, \r\n" + 
		 		"d.voucherdate voucherdate, \r\n" + 
		 		"d.batchno batchno, \r\n" + 
		 		"d.mrp mrp, \r\n" + 
		 		"d.salerate salerate, \r\n" + 
		 		"0 qty, \r\n" + 
		 		"0 free, \r\n" + 
		 		"d.specialqty specialqty, \r\n" + 
		 		"d.productdiscountpercent discountpercent, \r\n" + 
		 		"d.productdiscountamount discountamount, \r\n" + 
		 		"p.coldstorage coldstorage \r\n" +
		 		"from product p \r\n" + 
		 		"left join transactionmaster m on m.agencyid = p.agencicyid and ifnull(m.agencygroupid,'XX') = ifnull(p.agencygroupid,'XX') \r\n" + 
		 		"inner join transactiondetail d \r\n" + 
		 		"on d.transactionmasterid = m.transactionmasterid \r\n" + 
		 		"left join agencygroup g \r\n" + 
		 		"on g.agencygroupid = m.agencygroupid \r\n" + 
		 		"where p.agencicyid = " + nullCheck(body.getParam2()) + " \r\n" + 
		 		"and m.canflag = 0 \r\n" + 
		 		"and m.active = 1 \r\n" + 
		 		"and m.vouchertype = 'PR' \r\n" + 
		 		"and ifnull(p.agencygroupid,'XX') = ifnull(" + nullCheck(body.getParam3()) + ",ifnull(p.agencygroupid,'XX')) \r\n" + 
		 		"and m.accountid = ifnull(" + nullCheck(body.getParam4()) + ",m.accountid) \r\n" + 
		 		"group by p.productid";
		 
		JsonArray data = utility.executeQueryOnPool(query, request);
		
		query = "SELECT d.orderdetailid orderdetailid, d.productcode productid, d.quantity quantity, d.orderfree orderfree "
				+ "FROM orderdetail d WHERE d.ordermasterid = ifnull(" + nullCheck(body.getParam10()) + ",0)";
		JsonArray orderList = utility.executeQueryOnPool(query, request);
		
		JsonObject orderMap = new JsonObject();
		for (JsonElement element : orderList) {
			JsonObject obj = element.getAsJsonObject();
			orderMap.add(obj.get("productid").getAsString(), obj);
		}
		
		JsonArray productCopy = data.deepCopy();
		int index = 0;
		int count = 0;
		for (JsonElement element : data) {
			JsonObject obj = element.getAsJsonObject();
			
			if (orderMap.has(obj.get("productid").getAsString())) {
				JsonObject detail = orderMap.get(obj.get("productid").getAsString()).getAsJsonObject();
				obj.addProperty("orderdetailid", detail.get("orderdetailid").getAsString());
				obj.addProperty("qty", detail.get("quantity").getAsString());
				obj.addProperty("free", detail.get("orderfree").getAsString());
				
				orders.add(obj.deepCopy());
				productCopy.remove(index - count);
				count++;
			}
			index++;
		}
		
		orders.addAll(productCopy);
		
		return orders;
	}
	
	public JsonArray getAgencyWiseOrderModify(QueryApiBody body, HttpServletRequest request) {
		JsonArray orders = new JsonArray();
		
		 String query = "select p.productid productid, p.productname productname, p.displaypacking pack, p.boxpack boxpack, a.title agencyname, \r\n" +
		 		"(CASE WHEN ifnull(a.orderdays,0) = 0 THEN 30 ELSE a.orderdays END) orderdays, g.title agencygroup, \r\n" + 
		 		"ifnull((select sum(dt.qty+dt.free) from transactionmaster ms inner join transactiondetail dt \r\n" + 
		 		"on dt.transactionmasterid = ms.transactionmasterid where ms.agencyid = m.agencyid \r\n" + 
		 		"and ms.canflag = 0 \r\n" + 
		 		"and ms.active = 1 \r\n" + 
		 		"and ms.vouchertype = 'PR' \r\n" + 
		 		"and ifnull(ms.agencygroupid,'XX') = ifnull(m.agencygroupid,'XX') \r\n" + 
		 		"and ms.accountid = ifnull(" + nullCheck(body.getParam4()) + ",ms.accountid) \r\n" + 
		 		"and ms.trnmonth = (CONVERT(ifnull(" + nullCheck(body.getParam5()) + ",m.trnmonth),DECIMAL) - 1) \r\n" + 
		 		"and dt.productid = p.productid \r\n" + 
		 		"group by dt.productid),0) lastsale, \r\n" + 
		 		"sum(d.qty+d.free) currsale, \r\n" + 
		 		"ifnull((select ts.closing from transactionstock ts where ts.batchid = d.batchid and ts.storeid = d.storeid),0) stock, \r\n" + 
		 		"ifnull(p.pendingqty,0) pendingqty, \r\n" + 
		 		"0 orderqty, \r\n" + 
		 		"ifnull((select concat(round(sc.qtyone),'+',round(sc.freeone)) from productscheme sc where sc.productid = p.productid),'0+0') scheme, \r\n" + 
		 		"ifnull((select round(sc.qtyone) from productscheme sc where sc.productid = p.productid),0) schemeqty, \r\n" + 
		 		"ifnull((select round(sc.freeone) from productscheme sc where sc.productid = p.productid),0) schemefree, \r\n" + 
		 		"ifnull((select pt.title from producttype pt where pt.producttypeid = p.producttypeid),'NA') producttype, \r\n" + 
		 		"'Normal' productcategory, \r\n" + 
		 		"d.voucherno voucherno, \r\n" + 
		 		"d.voucherdate voucherdate, \r\n" + 
		 		"d.batchno batchno, \r\n" + 
		 		"d.mrp mrp, \r\n" + 
		 		"d.salerate salerate, \r\n" + 
		 		"d.qty qty, \r\n" + 
		 		"d.free free, \r\n" + 
		 		"d.specialqty specialqty, \r\n" + 
		 		"d.productdiscountpercent discountpercent, \r\n" + 
		 		"d.productdiscountamount discountamount, \r\n" + 
		 		"p.coldstorage coldstorage \r\n" + 
		 		"from shortsupply s \r\n" + 
		 		"inner join product p on p.agencicyid = s.agencyid and ifnull(p.agencygroupid,'XX') = ifnull(s.agencygroupid,'XX')  \r\n" + 
		 		"inner join productoprdtl pd on pd.productid = p.productid \r\n" + 
		 		"left join transactionmaster m on m.agencyid = s.agencyid and ifnull(m.agencygroupid,'XX') = ifnull(s.agencygroupid,'XX')\r\n" + 
		 		"inner join transactiondetail d \r\n" + 
		 		"on d.transactionmasterid = m.transactionmasterid \r\n" + 
		 		"left join agencygroup g \r\n" + 
		 		"on g.agencygroupid = s.agencygroupid \r\n" + 
		 		"left join agency a \r\n" + 
		 		"on a.agencyid = s.agencyid\r\n" + 
		 		"where s.agencyid = ifnull(" + nullCheck(body.getParam2()) + ",s.agencyid) \r\n" + 
		 		"and (CASE WHEN (" + nullCheck(body.getParam6()) + " = 'Y' || pd.minstockquantity > 0) THEN \r\n" + 
		 		"ifnull((select ts.closing from transactionstock ts where ts.batchid = d.batchid and ts.storeid = d.storeid),0) < pd.minstockquantity \r\n" + 
		 		"OR p.productid = s.productid \r\n" + 
		 		"ELSE p.productid = s.productid \r\n" + 
		 		"END)\r\n" + 
		 		"and m.canflag = 0 \r\n" + 
		 		"and m.active = 1 \r\n" + 
		 		"and m.vouchertype = 'PR' \r\n" + 
		 		"and ifnull(s.agencygroupid,'XX') = ifnull(" + nullCheck(body.getParam3()) + ",ifnull(s.agencygroupid,'XX')) \r\n" + 
		 		"and m.accountid = ifnull(" + nullCheck(body.getParam4()) + ",m.accountid) \r\n" + 
		 		"group by p.productid";
		 
		JsonArray data = utility.executeQueryOnPool(query, request);
		
		query = "SELECT d.orderdetailid orderdetailid, d.productcode productid, d.quantity quantity, d.orderfree orderfree "
				+ "FROM orderdetail d WHERE d.ordermasterid = ifnull(" + nullCheck(body.getParam10()) + ",0)";
		JsonArray orderList = utility.executeQueryOnPool(query, request);
		
		JsonObject orderMap = new JsonObject();
		for (JsonElement element : orderList) {
			JsonObject obj = element.getAsJsonObject();
			orderMap.add(obj.get("productid").getAsString(), obj);
		}
		
		JsonArray productCopy = data.deepCopy();
		int index = 0;
		int count = 0;
		for (JsonElement element : data) {
			JsonObject obj = element.getAsJsonObject();
			
			if (orderMap.has(obj.get("productid").getAsString())) {
				JsonObject detail = orderMap.get(obj.get("productid").getAsString()).getAsJsonObject();
				obj.addProperty("orderdetailid", detail.get("orderdetailid").getAsString());
				obj.addProperty("qty", detail.get("quantity").getAsString());
				obj.addProperty("free", detail.get("orderfree").getAsString());
				
				orders.add(obj.deepCopy());
				productCopy.remove(index - count);
				count++;
			}
			index++;
		}
		
		orders.addAll(productCopy);
		
		return orders;
	}
	
	@Transactional
	public String physicalStockAdjustment(TrnMasterBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		String voucherno = "", query = "", transactionid = "" + body.getTransactionmaster().getTransactionmasterid();
		long result = 0;
		
		switch (body.getTransactionmaster().getLastoperation()) {
		// First time insert
		case "INSERT":
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				// Reduce stock
				result = reducestock(transactionDetailBody, request);
			}
			if (result > 0) {
				JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
				JsonArray detailArray = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
				transactionmaster.remove("transactiondetail");
				
				// Master
				String transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "transactionmaster",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
				transactionid = transactionmasterid;
				
				transactionmaster.addProperty("transactionmasterid", transactionmasterid);
				
				utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster,
						null, "INSERT", request);
				
				// Detail
				for (JsonElement element : detailArray) {
					JsonObject transactiondetail = element.getAsJsonObject();
					
					String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
							transactiondetail.get("oprid").getAsString(), "transactiondetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
					
					transactiondetail.addProperty("transactiondetailid", transactiondetailid);
					
					utility.executeCustomDML("transactiondetail", "transactiondetailid", transactionmaster,
							null, "INSERT", request);
				}
			}
			break;

		case "FINALSAVE":
			query = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno\r\n"
					+ "FROM transactionmaster\r\n" + "WHERE orgid = " + body.getTransactionmaster().getOrgid()
					+ " AND oprid =" + body.getTransactionmaster().getOprid() + "  AND vouchertype = 'IS'";
			JsonArray dataVoucherno = utility.executeQueryOnPool(query, request);

			Object[] params = new Object[] {};
			query = "update transactiondetail set voucherno =" + dataVoucherno.get(0).getAsJsonObject().get("voucherno")
					+ " , vouchermode =2 , active =1 where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and  transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid();
			utility.executeDMLQueryOnPool(query, params, request);

			query = "update  transactionmaster set  voucherno ="
					+ dataVoucherno.get(0).getAsJsonObject().get("voucherno")
					+ ", vouchermode =2 , active =1 where orgid=" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid();
			utility.executeDMLQueryOnPool(query, params, request);
			
			voucherno = dataVoucherno.get(0).getAsJsonObject().get("voucherno").toString();

			break;

		case "ESCAPE":
			query = "select * from transactiondetail where orgid = " + body.getTransactionmaster().getOrgid()
					+ " and oprid=" + body.getTransactionmaster().getOprid() + " and transactionmasterid= "
					+ body.getTransactionmaster().getTransactionmasterid();
			JsonArray data = utility.executeQueryOnPool(query, request);
			if (data.size() > 0) {
				for (JsonElement jsonObjectEach : data.getAsJsonArray()) {
					query = "update transactionstock set closing = ifnull(closing,0) + ?"
							+ " where orgid = ? AND oprid = ? AND batchid = ?";
					params = new Object[] {
							jsonObjectEach.getAsJsonObject().get("qty"),
							body.getTransactionmaster().getOrgid(),
							body.getTransactionmaster().getOrgid(),
							jsonObjectEach.getAsJsonObject().get("qty")
					};
					
					transactionService.updateTransactionStock(query, params, request);
				}

			}

			params = new Object[] {};
			query = "delete from transactiondetail where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid();
			utility.executeDMLQueryOnPool(query, params, request);

			query = "delete from transactionmaster where orgid =" + body.getTransactionmaster().getOrgid()
					+ " and oprid =" + body.getTransactionmaster().getOprid() + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid();
			utility.executeDMLQueryOnPool(query, params, request);
			
			break;

		case "UPDATE":
			params = new Object[] {};
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {

				query = "SELECT \r\n" + "    td.productid, td.batchid, td.qty, td.productpack\r\n" + "FROM\r\n"
						+ "    transactiondetail td\r\n" + "WHERE\r\n" + "    td.orgid = "
						+ transactionDetailBody.getOrgid() + " AND td.oprid =" + transactionDetailBody.getOprid()
						+ " \r\n" + "        AND td.transactiondetailid = "
						+ transactionDetailBody.getTransactiondetailid();

				data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					JsonObject jsonObject = data.get(0).getAsJsonObject();
					query = "update transactionstock set closing = ifnull(closing,0) + ?"
							+ " where orgid = ? AND oprid = ? AND batchid = ?";
					params = new Object[] {
							jsonObject.get("qty").getAsLong(),
							transactionDetailBody.getOrgid(),
							transactionDetailBody.getOprid(),
							jsonObject.get("batchid").getAsString()
					};
					
					transactionService.updateTransactionStock(query, params, request);

					query = "delete from transactiondetail where orgid =" + transactionDetailBody.getOrgid()
							+ " and oprid =" + transactionDetailBody.getOprid() + " and transactiondetailid ="
							+ transactionDetailBody.getTransactiondetailid();
					utility.executeDMLQueryOnPool(query, params, request);
				}

				result = reducestock(transactionDetailBody, request);
			}

			if (result > 0) {

				Gson gson = new Gson();
				String transactionDetailString = gson.toJson(body.getTransactionmaster().getTransactiondetail());
				JsonArray transactionDetailJsonObject = new Gson().fromJson(transactionDetailString, JsonArray.class);

				JsonObject jsonobject = transactionDetailJsonObject.get(0).getAsJsonObject();
				jsonobject.remove("transactiondetailid");
				jsonobject.addProperty("transactiondetailid", 0);
				jsonobject.remove("voucherdate");
				jsonobject.addProperty("voucherdate", body.getTransactionmaster().getVoucherdate().toString());
				jsonobject.remove("lastoperation");

				jsonobject.addProperty("lastoperation", "INSERT");
				jsonobject.remove("createdon");
				jsonobject.addProperty("createdon", body.getTransactionmaster().getCreatedon().toString());
				jsonobject.remove("expiry");

				JsonObject jobj = new JsonObject();
				utility.executeCustomDML("transactiondetail", "transactiondetailid", jsonobject, jobj, "INSERT",
						request);
			}

			break;

		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("id", transactionid);
		response.addProperty("message", "Record Saved Successfully ");
		response.addProperty("data", voucherno);
		return null;
	}
	
	@Transactional
	public String postStockShortagefinalsave(TempBody body, HttpServletRequest request) {
		String voucherno = "";
		String query = "select * from temp where orgid = " + body.getOrgid() + " and oprid = " + body.getOprid()
				+ "  and tempmasterid = " + body.getTempmasterid() + " and diffqty > 0";
		
		JsonArray data = utility.executeQueryOnPool(query, request);

		String sql = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno \r\n"
				+ "FROM transactionmaster \r\n" + "WHERE orgid =" + body.getOrgid() + " AND oprid = "
				+ body.getOprid() + "  AND vouchertype = 'IS'";
		JsonArray datadtl = utility.executeQueryOnPool(sql, request);
		voucherno = datadtl.get(0).getAsJsonObject().get("voucherno").toString();
		
		// Transaction master
		String transactionmasterid = utility.executeIdGenerationProcedure("" + body.getOrgid(),
				"" + body.getOprid(), "transactionmaster",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
		
		JsonObject transactionmaster = new JsonObject();
		transactionmaster.addProperty("voucherno", voucherno);
		transactionmaster.addProperty("vouchertype", "IS");
		transactionmaster.addProperty("vouchermode", 2);
		transactionmaster.addProperty("active", 1);
		transactionmaster.addProperty("createdby", body.getCreatedby());
		transactionmaster.addProperty("createdon", body.getCreatedon().toString());
		transactionmaster.addProperty("transactionmasterid", transactionmasterid);
		transactionmaster.addProperty("lastoperation", "INSERT");
		transactionmaster.addProperty("referenceid", body.getTempmasterid());
		transactionmaster.addProperty("orgid", body.getOrgid());
		transactionmaster.addProperty("oprid", body.getOprid());
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dateObj.format(formatter);
		transactionmaster.addProperty("voucherdate", date);
		
		utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null, "INSERT",
				request);

		// Transaction detail
		JsonObject transactiondetail = new JsonObject();
		if (data.size() > 0) {
			for (JsonElement element : data) {
				JsonObject detailobj = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure("" + body.getOrgid(),
						"" + body.getOprid(), "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
				
				transactiondetail = new JsonObject();
				transactiondetail.addProperty("orgid", body.getOrgid());
				transactiondetail.addProperty("oprid", body.getOprid());
				transactiondetail.addProperty("transactionmasterid", transactionmasterid);
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				transactiondetail.addProperty("lastoperation", "INSERT");
				transactiondetail.addProperty("createdby", body.getCreatedby());
				transactiondetail.addProperty("createdon", body.getCreatedon().toString());
				transactiondetail.addProperty("vouchertype", "IS");
				transactiondetail.addProperty("voucherno", voucherno);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();

				transactiondetail.addProperty("voucherdate", dtf.format(now));

				transactiondetail.addProperty("vouchermode", 2);
				transactiondetail.addProperty("active", 1);
				transactiondetail.addProperty("productid", detailobj.get("productid").toString());
				transactiondetail.addProperty("batchid", detailobj.get("batchid").toString());
				transactiondetail.addProperty("qty", detailobj.get("diffqty").toString());
				
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null, "INSERT",
						request);

				query = "update transactionstock set closing = ifnull(closing,0) - ? "
						+ "where orgid = ? and oprid = ? and batchid = ?";
				Object[] params = new Object[] {
						detailobj.get("diffqty").getAsLong(),
						body.getOrgid(),
						body.getOprid(),
						detailobj.get("batchid").getAsString()
				};
				
				transactionService.updateTransactionStock(query, params, request);

				params = new Object[] {};
				query = "delete from temp where orgid = " + body.getOrgid() + " and oprid = " + body.getOprid()
						+ " and tempid = " + detailobj.get("tempid").toString();
				utility.executeDMLQueryOnPool(query, params, request);
			}
		}
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Saved Successfully ");
		response.addProperty("id", transactionmasterid);
		response.addProperty("data", voucherno);
		
		return response.toString();
	}
	
	@Transactional
	public String timeExpiredSave(List<TimeexpiredBody> body, HttpServletRequest request) {
		
		for (TimeexpiredBody timeExpiredBody : body) {
			JsonObject timeexpired = formater.serializeObject(timeExpiredBody);
			
			utility.executeCustomDML("timeexpired", "timeexpiredid", timeexpired, null, 
					timeexpired.get("lastoperation").getAsString(), request);
		}
		
		return null;
	}
	
	@Transactional
	public String postShortSupply(ShortsupplyBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);

		String voucherno = "", query = "", shortsupplyid = "";

		switch (body.getLastoperation()) {

		case "INSERT":
			JsonObject shortSupply = formater.serializeObject(body);
			shortsupplyid = utility.executeIdGenerationProcedure("" + body.getOrgid(),
					"" + body.getOprid(), "shortsupply",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			shortsupplyid = new JSONObject(shortsupplyid).get("id").toString();
			shortSupply.addProperty("shortsupplyid", shortsupplyid);
			
			if ((body.getOrderno() == null) || body.getOrderno() == 0) {
				query = "SELECT (ifnull(max(cast(orderno AS unsigned)), 0) + 1) orderno\r\n" + "FROM shortsupply \r\n"
						+ " WHERE shortsupply.orgid = " + adUserAccessToken.get("orgid") + " AND shortsupply.oprid ="
						+ adUserAccessToken.get("oprid");
				JsonArray dataOrderno = utility.executeQueryOnPool(query, request);
				voucherno = dataOrderno.get(0).getAsJsonObject().get("orderno").toString();
				
				shortSupply.addProperty("orderno", voucherno);
			}
			
			utility.executeCustomDML("shortsupply", "shortsupplyid", shortSupply, null, 
					shortSupply.get("lastoperation").getAsString(), request);
			break;

		case "UPDATE":
			shortSupply = formater.serializeObject(body);
			shortsupplyid = "" + body.getShortsupplyid();
			utility.executeCustomDML("shortsupply", "shortsupplyid", shortSupply, null, 
					shortSupply.get("lastoperation").getAsString(), request);
			break;

		case "DELETE":
			shortSupply = formater.serializeObject(body);
			shortsupplyid = "" + body.getShortsupplyid();
			utility.executeCustomDML("shortsupply", "shortsupplyid", shortSupply, null, 
					shortSupply.get("lastoperation").getAsString(), request);
			break;

		case "ESCAPE":
			Object[] params = new Object[] {};
			query = "delete from shortsupply where orgid =" + body.getOrgid() + " and oprid =" + body.getOprid()
					+ " and orderno ='" + body.getOrderno() + "'";
			utility.executeDMLQueryOnPool(query, params, request);

			break;

		case "FINALSAVE":
			params = new Object[] {};
			query = "update shortsupply set " + "  vouchermode =2 , active =1 , comment = '" + body.getComment()
					+ "' where orgid =" + body.getOrgid() + " and oprid =" + body.getOprid() + " and orderno = '"
					+ body.getOrderno() + "'";
			utility.executeDMLQueryOnPool(query, params, request);
			
			break;
		}

		query = " SELECT shortsupply.*, product.productname, account.accountname , product.displaypacking \r\n"
				+ "FROM shortsupply shortsupply\r\n" + "     INNER JOIN productoprdtl productoprdtl\r\n"
				+ "        ON     shortsupply.orgid = productoprdtl.orgid\r\n"
				+ "           AND shortsupply.oprid = productoprdtl.oprid\r\n"
				+ "           AND shortsupply.productid = productoprdtl.productid\r\n" + "     INNER JOIN product\r\n"
				+ "        ON     productoprdtl.orgid = product.orgid\r\n"
				+ "           AND productoprdtl.productid = product.productid\r\n"
				+ "     INNER JOIN accountoprdetail\r\n"
				+ "        ON     shortsupply.accountid = accountoprdetail.accountid\r\n"
				+ "           AND shortsupply.orgid = accountoprdetail.orgid\r\n"
				+ "           AND shortsupply.oprid = accountoprdetail.oprid\r\n" + "     INNER JOIN account\r\n"
				+ "        ON     accountoprdetail.accountid = account.accountid\r\n"
				+ "           AND accountoprdetail.orgid = account.orgid  where shortsupply.orgid =" + body.getOrgid()
				+ " and shortsupply.oprid =" + body.getOprid() + " and shortsupply.orderno ='" + body.getOrderno()
				+ "'";
		JsonArray jsonArrayShortSupply = utility.executeQueryOnPool(query, request);

		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Saved Successfully ");

		if (!body.getLastoperation().equals("ESCAPE") && !body.getLastoperation().equals("FINALSAVE")) {
			response.addProperty("id", shortsupplyid);
			response.add("data", jsonArrayShortSupply);
		} else {
			if (body.getLastoperation().equals("FINALSAVE")) {
				response.addProperty("data", body.getOrderno());
			}
		}

		return response.toString();
	}
	
	public String postStockShortage(@RequestBody @Validated CustomBody body, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		String query = "";
		switch (body.getLastoperation()) {

		// Insert for postStockShortage
		case "INSERT":
			if (body.getTempmasterid().equals(Long.parseLong("0"))) {
				String tempmasterid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
						adUserAccessToken.get("oprid"), request.getHeader("rightId"),
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				tempmasterid = new JSONObject(tempmasterid).get("id").toString();
				
				for (TempBody eachTempBody : body.getTempBody()) {
					JsonObject tempBody = formater.serializeObject(eachTempBody);
					tempBody.addProperty("tempmasterid", tempmasterid);
					tempBody.addProperty("productid", body.getProductid());
					
					utility.executeCustomDML("temp", "tempid", tempBody, null, 
							tempBody.get("lastoperation").getAsString(), request);
				}

				response.addProperty("id", tempmasterid);
				response.addProperty("message", "Record Saved Successfully ");
			} else {
				for (TempBody eachTempBody : body.getTempBody()) {
					JsonObject tempBody = formater.serializeObject(eachTempBody);
					utility.executeCustomDML("temp", "tempid", tempBody, null, 
							tempBody.get("lastoperation").getAsString(), request);
				}
			}
			
			break;

		// Update for post Stock shortage
		case "UPDATE":
			for (TempBody eachTempBody : body.getTempBody()) {
				JsonObject tempBody = formater.serializeObject(eachTempBody);
				utility.executeCustomDML("temp", "tempid", tempBody, null, 
						tempBody.get("lastoperation").getAsString(), request);
			}
			
			response.addProperty("message", "Record Saved  Successfully ");
			
			break;

		// Escape for post Stock shortage
		case "ESCAPE":
			Object[] params = new Object[] {};
			query = "delete from temp where orgid =" + body.getOrgid() + " and oprid =" + body.getOprid()
					+ " and tempmasterid =" + body.getTempmasterid();
			utility.executeDMLQueryOnPool(query, params, request);

			response.addProperty("message", "Record Delete Successfully ");
			
			break;

		// Delete for post Stock shortage
		case "DELETE":
			params = new Object[] {};
			query = "delete from temp where orgid =" + body.getOrgid() + " and oprid =" + body.getOprid()
					+ " and productid =" + body.getProductid() + " and tempmasterid ='" + body.getTempmasterid() + "'";
			utility.executeDMLQueryOnPool(query, params, request);

			response.addProperty("message", "Record Delete Successfully ");
			
			break;
		}
		
		return response.toString();
	}
	
	@Transactional
	public String postRateDifferenceCreditNote(List<CustomBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		Object[] params = new Object[] {};
		// updating the data in sale
		String query = "", narration = "";
		String transactionmasterid = "0";
		
		for (CustomBody customBody : body) {

			query = " SELECT group_concat(transactiondetail.transactiondetailid) transactiondetailid\r\n"
					+ "       \r\n" + "FROM transactionmaster transactionmaster\r\n"
					+ "     INNER JOIN transactiondetail transactiondetail\r\n"
					+ "        ON     transactionmaster.orgid = transactiondetail.orgid\r\n"
					+ "           AND transactionmaster.oprid = transactiondetail.oprid\r\n"
					+ "           AND transactionmaster.transactionmasterid =\r\n"
					+ "               transactiondetail.transactionmasterid\r\n"
					+ "WHERE     transactionmaster.orgid = " + adUserAccessToken.get("orgid") + ""
					+ "      AND transactionmaster.oprid = " + adUserAccessToken.get("oprid") + ""
					+ "      AND transactiondetail.voucherdate BETWEEN '" + customBody.getFromdate() + "' AND '"
					+ customBody.getTodate() + "'\r\n" + "      AND transactionmaster.vouchertype = 'SL'\r\n"
					+ "      AND transactiondetail.batchid = " + customBody.getBatchid() + ""
					+ "      AND transactiondetail.salerate = " + customBody.getSalerate() + ""
					+ "      AND transactiondetail.productid = " + customBody.getProductid() + ""
					+ "     AND transactionmaster.accountid = " + customBody.getAccountid();

			JsonArray transactionDetail = utility.executeQueryOnPool(query, request);

			query = "update transactiondetail set ratedifflag = 1 where orgid =" + adUserAccessToken.get("orgid")
					+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactiondetailid in ("
					+ transactionDetail.get(0).getAsJsonObject().get("transactiondetailid") + ")";
			utility.executeDMLQueryOnPool(query, params, request);

			JsonObject transactionmaster = new JsonObject();
			transactionmaster.addProperty("orgid", adUserAccessToken.get("orgid"));
			transactionmaster.addProperty("oprid", adUserAccessToken.get("oprid"));

			transactionmasterid = utility.executeIdGenerationProcedure(adUserAccessToken.get("orgid"),
					adUserAccessToken.get("oprid"),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();

			transactionmaster.addProperty("transactionmasterid", transactionmasterid);

			query = "select displaypacking , productname FROM product where productid = " + customBody.getProductid();
			JsonArray productArray = utility.executeQueryOnPool(query, request);

			narration = customBody.getProductname() + "" + productArray.get(0).getAsJsonObject().get("displaypacking")
					+ "Diff. From " + customBody.getFromdate() + " " + customBody.getTodate() + " diff Rs. "
					+ customBody.getDiffamount() + "*" + customBody.getQuantity();

			transactionmaster.addProperty("creditdebitnarration", narration); 

			query = "SELECT (max(ifnull(cast(voucherno AS unsigned), 0)) + 1) voucherno \n" + "FROM transactionmaster "
					+ "WHERE orgid = " + adUserAccessToken.get("orgid") + " AND oprid ="
					+ adUserAccessToken.get("oprid") + "  AND vouchertype = '" + customBody.getVouchertype()
					+ "' AND active = 1";

			JsonArray data = utility.executeQueryOnPool(query, request);
			transactionmaster.add("voucherno", data.get(0).getAsJsonObject().get("voucherno"));
			transactionmaster.addProperty("vouchermode", 2);
			transactionmaster.addProperty("active", 1);
			transactionmaster.addProperty("ratediffdebitcreditnoteflag", 1);
			transactionmaster.addProperty("creditnoteflag", 0);
			transactionmaster.addProperty("creditdebitstatus", 'P');
			transactionmaster.addProperty("creditnotetype", 3);
			transactionmaster.addProperty("cdp", 1);
			transactionmaster.addProperty("creditnoteamount", customBody.getCreditnoteamount());
			transactionmaster.addProperty("debitnoteamount", customBody.getDebitnoteamount());
			transactionmaster.addProperty("accountid", customBody.getAccountid());
			transactionmaster.addProperty("modifyby", customBody.getModifyby());
			transactionmaster.addProperty("modifyon", customBody.getModifyon().toString());
			transactionmaster.addProperty("vouchertype", customBody.getVouchertype());
			transactionmaster.addProperty("voucherdate", customBody.getVoucherdate().toString());
			transactionmaster.addProperty("lastoperation", customBody.getLastoperation());
			JsonObject jsonObjectWhere = new JsonObject();
			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, jsonObjectWhere,
					"INSERT", request);
			if(customBody.getVouchertype().equals("CN")) {
				query = "update transactiondetail set ratediffcreditnoteno = " + data.get(0).getAsJsonObject().get("voucherno")  
					+	" , vouchermode =2 , active =1  where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactiondetailid in ("
				+ transactionDetail.get(0).getAsJsonObject().get("transactiondetailid") + ")";
		        utility.executeDMLQueryOnPool(query, params, request);
			} else {
				query = "update transactiondetail set ratediffdebitnoteno = " + data.get(0).getAsJsonObject().get("voucherno") 
						 + 	" , vouchermode =2 , active =1  where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactiondetailid in ("
				+ transactionDetail.get(0).getAsJsonObject().get("transactiondetailid") + ")";
		        utility.executeDMLQueryOnPool(query, params, request);
			}
		}
		
		return transactionmasterid;
	}
	
	@Transactional
	public int getFlagStatusUpdate(QueryApiBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		Object[] params = new Object[] {};
		int result = 0;
		switch (body.getParam1()) {

		case "1":
			String query = "update  transactionmaster set  packingstatus = 1" + ", packingtime = '"
					+ java.time.LocalTime.now() + "'  where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" + body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;

		case "2":
			query = "update  transactionmaster set  verifacationstatus = 1" + ", verificationtime = '"
					+ java.time.LocalTime.now() + "' where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" +  body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;

		case "3":
			query = "update  transactionmaster set  dispatchstatus = 1" + ", dispatchtime = '"
					+ java.time.LocalTime.now() + "' where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" +  body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;
		// update print flag ,packing and verification stage	
		case "4":
			query = "update  transactionmaster set  printflag = 1" + ", packingstatus = 1" + ", packingtime = '" + java.time.LocalTime.now() + 
					"', verifacationstatus = 1" + ", verificationtime = '"
					+ java.time.LocalTime.now() + "' where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" +  body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;	
		// Update picker status and print flag	
		case "5":
			String sql = "SELECT (ifnull(max(cast(transummeryprintcount AS unsigned)), 0) + 1) transummeryprintcount from transactionmaster where vouchertype = 'SL'";
			JsonArray datadtl = utility.executeQueryOnPool(sql, request);
			int transummeryprintcount = datadtl.get(0).getAsJsonObject().get("transummeryprintcount").getAsInt();
			
			 query = "update  transactionmaster set  transummeryprintcount = " + transummeryprintcount + ", printflag = 1" + ", pickerstatus = 1" + ", pickertime = '"
					+ java.time.LocalTime.now() + "'  where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" +  body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;
		// update only print flag	
		case "6":
			 query = "update  transactionmaster set  printflag = 1" + "'  where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
					+ adUserAccessToken.get("oprid") + " and transactionmasterid in (" +  body.getParam3() + ")"
					+ " and vouchertype = '" + body.getParam2() + "' ";
			params = new Object[] {};
			result = utility.executeDMLQueryOnPool(query, params, request);
			break;

		}
		
		return result;
	}
	
	@Transactional
	public String postexpirybreakage(TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String transactionmasterid = "";
		long result = 0;
		
		switch (body.getTransactionmaster().getLastoperation()) {
		// First time insert for expiry breakage
		// Adding the first item to the grid
		// Reduce the stock
		case "INSERT":
			JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
			JsonArray transactiondetaillist = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
			
			transactionmaster.addProperty("transactionmasterid", transactionmasterid);
			result = utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null,
					transactionmaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement element : transactiondetaillist) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
						transactiondetail.get("oprid").getAsString(),  "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
				
				transactiondetail.addProperty("transactionmasterid", transactionmasterid);
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null,
						transactiondetail.get("lastoperation").getAsString(), request);
			}
			break;
		// Updating the master
		// Item level insert, update, delete on transaction detail
		case "UPDATE":
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
				switch (transactionDetailBody.getLastoperation()) {
				// Adding new item to exiting grid
				case "INSERT":
					result = transactionService.postCommonTransactionWithoutStockItemInsert(transactionDetailBody, false, request);
					break;
				// Updating the existing record in the grid
				case "UPDATE":
					result = transactionService.postCommonTransactionWithoutStockItemUpdate(transactionDetailBody, request);
					break;
				// Deleting the existing record in the grid
				case "DELETE":
					result = transactionService.postCommonTransactionWithoutStockItemDelete(transactionDetailBody, request);
					break;
				}
			}

			// Check of the details exist for the master id if does not exist delete master
			// as well
			String query = "select count(*)countdet from transactiondetail where  transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid() + " and orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid = "
					+ body.getTransactionmaster().getOprid();
			JsonArray countdetail = utility.executeQueryOnPool(query, request);
			if (countdetail.size() > 0) {
				int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
				if (count == 0) {
					transactionService.deleteTransactionMasterDetail(body.getTransactionmaster().getTransactionmasterid().toString(), request);
				}
			}

			break;
		case "FINALSAVE":
			// Final save operations
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			int voucher = Integer.parseInt(postTransactionStockFinalSave(body, request));
			if (voucher > 0 && voucher != 0)
				result = (voucher);
			break;

		case "ESCAPE":
			// Escape for post expiry breakage
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			transactionService.postCommonTransactionWithoutStockEscape(body, request);
			break;
		}
		
		JsonObject response = new JsonObject();
		if (result >= 0) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Record Updated Successfully");
			response.addProperty("id", transactionmasterid);
			if (body.getTransactionmaster().getLastoperation().equals("FINALSAVE")
					|| body.getTransactionmaster().getLastoperation().equals("ESCAPE")) {

				switch (body.getTransactionmaster().getLastoperation()) {
				case "FINALSAVE":
					response.addProperty("data", result);
					break;

				case "ESCAPE":
					response.addProperty("data", body.getTransactionmaster().getVoucherno());
					break;

				}
			} else {
				int voucherNoInt = body.getTransactionmaster().getVoucherno();
				JsonArray transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid, voucherNoInt, request);
				response.add("data", transactionDetail);
			}
		}
		
		return response.toString();
	}
	
	@Transactional
	public String postClaim(TrnMasterBody body, HttpServletRequest request) throws JsonProcessingException {
		String response = "", transactionmasterid = "0";
		JsonObject responseObj = new JsonObject();
		
		switch (body.getTransactionmaster().getLastoperation()) {
		case "INSERT":
			JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
			JsonArray transactiondetaillist = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
			
			transactionmaster.addProperty("transactionmasterid", transactionmasterid);
			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null,
					transactionmaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement element : transactiondetaillist) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
						transactiondetail.get("oprid").getAsString(),  "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
				
				transactiondetail.addProperty("transactionmasterid", transactionmasterid);
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null,
						transactiondetail.get("lastoperation").getAsString(), request);
			}

			responseObj.addProperty("code", 200);
			responseObj.addProperty("status", "Success");
			responseObj.addProperty("message", "Record Updated Successfully");
			responseObj.addProperty("id", new BigDecimal(transactionmasterid));
			int voucherNoInt = 0;
			JsonArray transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid, voucherNoInt, request);
			responseObj.add("data", transactionDetail);
			response = responseObj.toString();
			break;
		case "UPDATE":
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
				switch (transactionDetailBody.getLastoperation()) {
				// Adding new item to exiting grid
				case "INSERT":
					transactionService.postCommonTransactionWithoutStockItemInsert(transactionDetailBody, false, request);
					break;
				// Updating the existing record in the grid
				case "UPDATE":
					transactionService.postCommonTransactionWithoutStockItemUpdate(transactionDetailBody, request);
					transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
					break;
				// Deleting the existing record in the grid
				case "DELETE":
					postCommonTransactionRevertOld(body.getTransactionmaster().getTransactiondetail().get(0), request);
					transactionService.postCommonTransactionWithoutStockItemDelete(transactionDetailBody, request);
					
					transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
					break;
				}
			}

			responseObj = new JsonObject();
			responseObj.addProperty("code", 200);
			responseObj.addProperty("status", "Success");
			responseObj.addProperty("message", "Record Updated Successfully");
			responseObj.addProperty("id", new BigDecimal(transactionmasterid));
			voucherNoInt = 0;
			transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid, voucherNoInt, request);
			responseObj.add("data", transactionDetail);
			response = responseObj.toString();
			break;

		case "ESCAPE":
			response = postCreditNoteReplacementEscape(body, request);
			break;

		case "FINALSAVE":
			response = postClaimFinalSave(body, request);
			break;
		
		case "CANCEL":
			response = transactionService.cancelGstCreditnote(body, request);
		     break;	
		}
		
		return response;
	}
	
	@Transactional
	public String postGstCreditNote(TrnMasterBody body, HttpServletRequest request)
			throws JsonProcessingException {
		String response = "", transactionmasterid = "";
		long result = 0;
		
		switch (body.getTransactionmaster().getLastoperation()) {
		case "INSERT":
			// Insert
			JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
			JsonArray transactiondetaillist = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
			
			transactionmaster.addProperty("transactionmasterid", transactionmasterid);
			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null,
					transactionmaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement element : transactiondetaillist) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
						transactiondetail.get("oprid").getAsString(),  "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
				
				transactiondetail.addProperty("transactionmasterid", transactionmasterid);
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null,
						transactiondetail.get("lastoperation").getAsString(), request);
			}
			break;
			
		case "UPDATE":
			// Updating the master records
			transactionmaster = new JsonObject();
			transactionmaster.addProperty("cashdiscountpercent", body.getTransactionmaster().getCashdiscountpercent());

			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				transactionmasterid = String.valueOf(body.getTransactionmaster().getTransactionmasterid());
				switch (transactionDetailBody.getLastoperation()) {
				case "INSERT":
					result = transactionService.postCommonTransactionWithoutStockItemInsert(transactionDetailBody, false, request);
					break;
					
				case "UPDATE":
					result = transactionService.postCommonTransactionWithoutStockItemUpdate(transactionDetailBody, request);
					break;

				case "DELETE":
					result = transactionService.postCommonTransactionWithoutStockItemDelete(transactionDetailBody, request);
					// Delete master if no element exist in details
					String query = "select count(*)countdet from transactiondetail where  transactionmasterid ="
							+ body.getTransactionmaster().getTransactionmasterid() + " and orgid ="
							+ body.getTransactionmaster().getOrgid() + " and oprid = "
							+ body.getTransactionmaster().getOprid();
					JsonArray countdetail = utility.executeQueryOnPool(query, request);
					if (countdetail.size() > 0) {
						int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
						if (count == 0) {
							transactionService.deleteTransactionMasterDetail(body.getTransactionmaster().getTransactionmasterid().toString(), request);
						}
					}

					break;
				}

			}

			JsonObject transactionmasterwhere = new JsonObject();
			transactionmasterwhere.addProperty("orgid", body.getTransactionmaster().getOrgid());
			transactionmasterwhere.addProperty("oprid", body.getTransactionmaster().getOprid());
			transactionmasterwhere.addProperty("transactionmasterid",
					body.getTransactionmaster().getTransactionmasterid());

			utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster,
					transactionmasterwhere, "UPDATE", request);
			break;

		case "ESCAPE":
			result = transactionService.postCommonTransactionWithoutStockEscape(body, request);
			break;
			
		case "FINALSAVE":
			response = finalSaveGstCreditnote(body, request);
			int voucher = new JSONObject(response).getInt("data");
			if(body.getTransactionmaster().getVouchertype().equals("DN")) {
				insertintoOutstanding(body, voucher, request);
			}
			break;

		case "CANCEL":
			response = transactionService.cancelGstCreditnote(body, request);
			break;
		}
		
		if (result >= 0) {
			if (!body.getTransactionmaster().getLastoperation().equals("FINALSAVE")) {
				JsonObject responseObject = new JsonObject();
				responseObject.addProperty("code", 200);
				responseObject.addProperty("status", "Success");
				responseObject.addProperty("message", "Record Updated Successfully");
				if (!body.getTransactionmaster().getLastoperation().equals("ESCAPE")) {
					int voucherNoInt = body.getTransactionmaster().getVoucherno();
					JsonArray transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid,
							voucherNoInt, request);
					responseObject.add("data", transactionDetail);
				}
				response = responseObject.toString();
			}
		}
		
		return response;
	}
	
	@Transactional
	public String postsaleinvoice(TrnMasterBody body, HttpServletRequest request) throws JsonProcessingException {
		String response = "", transactionmasterid = "";
		long result = 0;
		
		switch (body.getTransactionmaster().getLastoperation()) {
		// First time insert for sale invoice
		// Adding the first item to the grid
		// Reduce the stock
		case "INSERT":
			JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
			JsonArray transactiondetaillist = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(),  "transactionmaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
			
			transactionmaster.addProperty("transactionmasterid", transactionmasterid);
			result = utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null,
					transactionmaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement element : transactiondetaillist) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
						transactiondetail.get("oprid").getAsString(),  "transactiondetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
				
				transactiondetail.addProperty("transactionmasterid", transactionmasterid);
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null,
						transactiondetail.get("lastoperation").getAsString(), request);
			}
			
			// Reduce sale stock
			reduceStockSale(body.getTransactionmaster().getTransactiondetail().get(0), request);
			break;
			
		// Updating the master
		// Item level insert, update, delete on transaction detail
		case "UPDATE":
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				switch (transactionDetailBody.getLastoperation()) {
				// Adding new item to exiting grid
				case "INSERT":
					reduceStockSale(body.getTransactionmaster().getTransactiondetail().get(0),
							request);
					result = transactionService.postCommonTransactionWithoutStockItemInsert(transactionDetailBody, false, request);
					break;
				// Updating the existing record in the grid
				case "UPDATE":
					result = postCommonTransactionRevertOld(
							body.getTransactionmaster().getTransactiondetail().get(0), request);
					result = transactionService.postCommonTransactionWithoutStockItemUpdate(transactionDetailBody,
							request);
					reduceStockSale(body.getTransactionmaster().getTransactiondetail().get(0),
							request);
					break;
				// Deleting the existing record in the grid
				case "DELETE":
					result = postCommonTransactionRevertOld(
							body.getTransactionmaster().getTransactiondetail().get(0), request);
					result = transactionService.postCommonTransactionWithoutStockItemDelete(transactionDetailBody,
							request);
					break;
				}
			}
			// Check of the details exist for the master id if does not exist delete master
			// as well
			String query = "select count(*) countdet from transactiondetail where transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid() + " and orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid = "
					+ body.getTransactionmaster().getOprid();
			JsonArray countdetail = utility.executeQueryOnPool(query, request);
			if (countdetail.size() > 0) {
				int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
				if (count == 0) {
					transactionService.deleteTransactionMasterDetail(
							body.getTransactionmaster().getTransactionmasterid().toString(), request);
				}
			}

			break;
		// Final save operations
		case "FINALSAVE":
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			int voucher = transactionService.postCommonTransactionFinalSave(body, request);
			//customService.insertintoOutstanding(body, voucher, request);
			if (voucher > 0 && voucher != 0)
				result = voucher;
			postUpdateTcsAmt(body, request);
			break;
		// Escape for post sale invoice
		case "ESCAPE":
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			postCreditNoteReplacementEscape(body, request);
			break;
			
		case "CANCEL":
			response = transactionService.cancelGstCreditnote(body, request);
		break;		
		}
		
		if (result >= 0) {
			JsonObject responseObject = new JsonObject();
			responseObject.addProperty("code", 200);
			responseObject.addProperty("status", "Success");
			responseObject.addProperty("message", "Record Updated Successfully");
			responseObject.addProperty("id", transactionmasterid);
			if (body.getTransactionmaster().getLastoperation().equals("FINALSAVE")
					|| body.getTransactionmaster().getLastoperation().equals("ESCAPE")) {

				switch (body.getTransactionmaster().getLastoperation()) {
				case "FINALSAVE":
					responseObject.addProperty("voucherno", result);
					responseObject.addProperty("voucherseries", body.getTransactionmaster().getVoucherseries().toString());
					responseObject.addProperty("netamount", body.getTransactionmaster().getNetamount());
					break;

				case "ESCAPE":
					responseObject.addProperty("data", body.getTransactionmaster().getVoucherno());
					break;

				}
			} else {
				int voucherNoInt = body.getTransactionmaster().getVoucherno();
				JsonArray transactionDetail = new JsonArray();
				transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid, voucherNoInt, request);
				responseObject.add("data", transactionDetail);
			}
			response = responseObject.toString();

		}
		
		return response;
	}
	
	@Transactional
	public String postPickerUserlink(List<PickeruserlinkBody> body, HttpServletRequest request) {
		long usermasterid = body.get(0).getAdusermstid();
		
		for (PickeruserlinkBody pickeruserlinkBody : body) {
			// delete previous record according to picker id
			String query = "";
			Object[] params = new Object[] {};
			query = "delete from pickeruserlink where orgid =" + pickeruserlinkBody.getOrgid() + " and oprid = "
					+ pickeruserlinkBody.getOprid() + " and pickerid = " + pickeruserlinkBody.getPickerid();
			utility.executeDMLQueryOnPool(query, params, request);
		}
		
		for (PickeruserlinkBody pickeruserlinkBody : body) {
			JsonObject pickeruserlink = formater.serializeObject(pickeruserlinkBody);
			
			// Insert/Update picker user link master
			if (usermasterid != 0) {
				if (pickeruserlink.get("lastoperation").getAsString().equals("INSERT")) {
					String pickeruserlinkid = utility.executeIdGenerationProcedure(pickeruserlink.get("orgid").getAsString(),
							pickeruserlink.get("oprid").getAsString(),  "pickeruserlink",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					pickeruserlinkid = new JSONObject(pickeruserlinkid).getString("id");
					
					pickeruserlink.addProperty("pickeruserlinkid", pickeruserlinkid);
				}
				
				utility.executeCustomDML("pickeruserlink", "pickeruserlinkid", pickeruserlink, null,
						pickeruserlink.get("lastoperation").getAsString(), request);
			}
		}
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Updated Successfully");
		
		return response.toString();
	}
	
	@Transactional
	public String postgodownuserlink(List<GodownuserlinkBody> body, HttpServletRequest request) {
		long usermasterid = body.get(0).getAdusermstid();
		
		for (GodownuserlinkBody godownuserlinkBody : body) {
			// delete previous record according to store id
			Object[] params = new Object[] {};
			String query = "delete from godownuserlink where orgid = " + godownuserlinkBody.getOrgid() + " and oprid = "
					+ godownuserlinkBody.getOprid() + " and storeid = " + godownuserlinkBody.getStoreid();
			utility.executeDMLQueryOnPool(query, params, request);
		}
		
		for (GodownuserlinkBody godownuserlinkBody : body) {
			JsonObject godownuserlink = formater.serializeObject(godownuserlinkBody);
			// Insert/Update godown user link master
			if (usermasterid != 0) {
				if (godownuserlink.get("lastoperation").getAsString().equals("INSERT")) {
					String godownuserlinkid = utility.executeIdGenerationProcedure(godownuserlink.get("orgid").getAsString(),
							godownuserlink.get("oprid").getAsString(),  "godownuserlink",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					godownuserlinkid = new JSONObject(godownuserlinkid).getString("id");
					
					godownuserlink.addProperty("godownuserlinkid", godownuserlinkid);
				}
				
				utility.executeCustomDML("godownuserlink", "godownuserlinkid", godownuserlink, null,
						godownuserlink.get("lastoperation").getAsString(), request);
			}
		}
		
		JsonObject response = new JsonObject();
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		response.addProperty("message", "Record Updated Successfully");
		
		return response.toString();
	}
	
	private String nullCheck(String param) {
		if (param == null) {
			return "null";
		} else if (param.trim().equals("") || param.trim().equals("null")) {
			return "null";
		} else {
			if (param.trim().charAt(0) == '\'') {
				return param;
			} else {
				return "'" + param + "'";
			}
		}
	}
	
}
