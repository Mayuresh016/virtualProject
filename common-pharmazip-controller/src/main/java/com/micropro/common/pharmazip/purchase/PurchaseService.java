package com.micropro.common.pharmazip.purchase;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.custom.CustomService;
import com.micropro.common.pharmazip.model.StockShortageModel.StockshortaccessBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactiondetailBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.common.pharmazip.transaction.TransactionService;

@Service
public class PurchaseService {

	@Autowired
	private ConnectionUtility utility;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CustomService customService;
	
	private @Autowired CommonDateFormater formater;
	
	// Increase stock for purchase
	public int increaseStockPurchase(TransactiondetailBody transactiondetailBody, HttpServletRequest request) {
		String whereClause = "", query = "";
		Object[] params = new Object[] {
				transactiondetailBody.getQty(), 
				transactiondetailBody.getQty(),
				transactiondetailBody.getOrgid(), 
				transactiondetailBody.getOprid(),
				transactiondetailBody.getBatchid(),
				transactiondetailBody.getStoreid()
			};

		query = "update transactionstock set closing = ifnull(closing,0) + ?"
				+ " ,rqp = ifnull(rqp,0) + ?" + "  where orgid =?"
				 + " AND oprid = ?"
				+ "  AND batchid = ? " + " and storeid= ?"
				 + " " + whereClause;
		return utility.executeDMLQueryOnPool(query, params, request);
	}
	
	@Transactional
	public String postpurchaseinvoice(List<TrnMasterBody> body, boolean isPurchaseImport, HttpServletRequest request) throws JsonProcessingException {
		String response = "", transactionmasterid = "", transactiondetailid = "";
		JsonArray transactionDetail = new JsonArray();
		long result = 0;
		
		for (TrnMasterBody trnMaster : body) {
			switch (trnMaster.getTransactionmaster().getLastoperation()) {
			// First time insert for purchase invoice
			// Adding the first item to the grid
			// Increase the stock
			case "INSERT":
				JsonObject transactionmaster = formater.serializeObject(trnMaster.getTransactionmaster());
				JsonArray transactionDtlList = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
				transactionmaster.remove("transactiondetail");
				
				transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "transactionmaster",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactionmasterid = new JSONObject(transactionmasterid).get("id").toString();
				
				for (JsonElement element : transactionDtlList) {
					JsonObject transactiondetail = element.getAsJsonObject();
					
					// Check for batch
					if (transactiondetail.get("batchid").getAsLong() == 0) {
						// Create new batch
						transactiondetail.addProperty("batchid", transactionService.createBatch(transactiondetail, request));
					}
					
					// Check for rate
					if (transactiondetail.get("rateid").getAsLong() == 0) {
						// Create new rate
						transactiondetail.addProperty("rateid", transactionService.createRate(transactiondetail, request));
					}
					
					transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
							transactiondetail.get("oprid").getAsString(), "transactiondetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					transactiondetailid = new JSONObject(transactiondetailid).get("id").toString();
					
					transactiondetail.addProperty("transactiondetailid", transactiondetailid);
					transactiondetail.addProperty("transactionmasterid", transactionmasterid);
					
					utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail,
							null, "INSERT", request);
				}
				
				transactionmaster.addProperty("transactionmasterid", transactionmasterid);
				result = utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster,
						null, "INSERT", request);
				
				//increaseStockPurchase(body.getTransactionmaster().getTransactiondetail().get(0), request);
				break;
			// Updating the master
			// Item level insert, update, delete on transaction detail
			case "UPDATE":
				transactionmasterid = trnMaster.getTransactionmaster().getTransactionmasterid().toString();
				for (TransactiondetailBody transactionDetailBody : trnMaster.getTransactionmaster().getTransactiondetail()) {
					switch (transactionDetailBody.getLastoperation()) {
					// Adding new item to exiting grid
					case "INSERT":
						result = transactionService.postCommonTransactionWithoutStockItemInsert(transactionDetailBody, true, request);
//						if (trnMaster.getTransactionmaster().getVerificationflag() == 2) {
//							increaseStockPurchase(trnMaster.getTransactionmaster().getTransactiondetail().get(0), request);
//						}
						break;
					// Updating the existing record in the grid
					case "UPDATE":
//						if (trnMaster.getTransactionmaster().getVerificationflag() == 2) {
//							result = postCommonTransactionRevertOld(trnMaster.getTransactionmaster().getTransactiondetail().get(0), request);
//						}
						result = transactionService.postCommonTransactionWithoutStockItemUpdate(transactionDetailBody, request);
//						if (trnMaster.getTransactionmaster().getVerificationflag() == 2) {
//							increaseStockPurchase(trnMaster.getTransactionmaster().getTransactiondetail().get(0), request);
//						}
						break;
					// Deleting the existing record in the grid
					case "DELETE":
						if (trnMaster.getTransactionmaster().getVerificationflag() == 2) {
							result = transactionService.postCommonTransactionRevertOld(trnMaster.getTransactionmaster().getTransactiondetail().get(0), request);
						}
						result = transactionService.postCommonTransactionWithoutStockItemDelete(transactionDetailBody, request);
						break;
					}
				}
				// Check of the details exist for the master id if does not exist delete master
				// as well
				String query = "select count(*)countdet from transactiondetail where  transactionmasterid ="
						+ trnMaster.getTransactionmaster().getTransactionmasterid() + " and orgid ="
						+ trnMaster.getTransactionmaster().getOrgid() + " and oprid = "
						+ trnMaster.getTransactionmaster().getOprid();
				JsonArray countdetail = utility.executeQueryOnPool(query, request);
				if (countdetail.size() > 0) {
					int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
					if (count == 0) {
						transactionService.deleteTransactionMasterDetail(trnMaster.getTransactionmaster().getTransactionmasterid().toString(), request);
					}
				}
	
				break;
			// Final save operations
			case "FINALSAVE":
				transactionmasterid = trnMaster.getTransactionmaster().getTransactionmasterid().toString();
				
				if (trnMaster.getTransactionmaster().getVerificationflag() == 2) {
					for (TransactiondetailBody detail : trnMaster.getTransactionmaster().getTransactiondetail()) {
						increaseStockPurchase(detail, request);
					}
				}
				int voucher = transactionService.postCommonTransactionFinalSave(trnMaster, request);
				if (voucher > 0 && voucher != 0)
					result = (voucher);
				
				transactionService.postUpdateTcsAmt(trnMaster, request);
				break;
			// Escape for post sale invoice
			case "ESCAPE":
				transactionmasterid = trnMaster.getTransactionmaster().getTransactionmasterid().toString();
				transactionService.postCreditNoteReplacementEscape(trnMaster, request);
				break;
			}
		}
		
		if (result >= 0) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", 200);
			jsonObject.addProperty("status", "Success");
			
			if (!isPurchaseImport) {
				jsonObject.addProperty("message", "Record Updated Successfully");
				TrnMasterBody trnMaster = body.get(0);
				jsonObject.addProperty("id", new BigDecimal(transactionmasterid));
				if (trnMaster.getTransactionmaster().getLastoperation().equals("FINALSAVE")
						|| trnMaster.getTransactionmaster().getLastoperation().equals("ESCAPE")) {
	
					switch (trnMaster.getTransactionmaster().getLastoperation()) {
					case "FINALSAVE":
						jsonObject.addProperty("data", result);
						break;
	
					case "ESCAPE":
						jsonObject.addProperty("data", trnMaster.getTransactionmaster().getVoucherno());
						break;
	
					}
				} else {
					int voucherNoInt = trnMaster.getTransactionmaster().getVoucherno();
					transactionDetail = transactionService.getCommonTransactionResponse(transactionmasterid, voucherNoInt, request);
					jsonObject.add("data", transactionDetail);
				}
			} else {
				jsonObject.addProperty("message", "Purchase Import Successfully");
			}
			
			response = jsonObject.toString();

		} else {
			response = "Exception";
		}
		
		return response;
	}
	
	@Transactional
	public String postStockShortage(List<StockshortaccessBody> body, HttpServletRequest request) {
		JsonArray stockShortageList = formater.serializeArray(body);
		for (JsonElement element : stockShortageList) {
			JsonObject stockShortage = element.getAsJsonObject();
			
			if (stockShortage.get("lastoperation").getAsString().equals("INSERT")) {
				String stockshortaccessid = utility.executeIdGenerationProcedure(stockShortage.get("orgid").getAsString(),
						stockShortage.get("oprid").getAsString(), "stockshortaccess",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				stockshortaccessid = new JSONObject(stockshortaccessid).get("id").toString();
				
				stockShortage.addProperty("stockshortaccessid", stockshortaccessid);
			}
			
			utility.executeCustomDML("stockshortaccess", "stockshortaccessid", stockShortage,
					null, stockShortage.get("lastoperation").getAsString(), request);
		}
		
		return "Success";
	}
}
