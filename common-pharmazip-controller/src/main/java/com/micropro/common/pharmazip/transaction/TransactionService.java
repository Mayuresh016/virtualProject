package com.micropro.common.pharmazip.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.config.RedisManager;
import com.micropro.common.pharmazip.model.generated.PaymentreceiptMstModel.PaymentreceiptBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactiondetailBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactionmasterBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TrnMasterBody;
import com.micropro.custom.common.RestUtil;

@Service
public class TransactionService {

	@Autowired
	private ConnectionUtility utility;
	
	private @Autowired CommonDateFormater formater;
	
	@Autowired
	private RedisManager redisManager;
	
	/**
	 * Common method update transaction stock
	 * <p>
	 * @param query - prepared statement query 
	 * @param params - parameters as per prepared statement sequence
	 * 
	 * @author Kushal Kadu
	 */
	public synchronized int updateTransactionStock(String query, Object[] params, HttpServletRequest request) {
		return utility.executeDMLQueryOnPool(query, params, request);
	}
	
	public long postCommonTransactionWithoutStockItemInsert(TransactiondetailBody transactionDetailBody, boolean isPurchase,
			HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String transactionDetailString = mapper.writeValueAsString(transactionDetailBody);
		JsonObject transactiondetail = JsonParser.parseString(transactionDetailString).getAsJsonObject();
		
		int voucherNoInt = Integer.parseInt(transactionDetailBody.getVoucherno());
		transactiondetail.remove("modifyon");
		
		String transactionDetailid = utility.executeIdGenerationProcedure(transactionDetailBody.getOrgid().toString(),
				transactionDetailBody.getOprid().toString(), "transactiondetail",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		transactionDetailid = new JSONObject(transactionDetailid).getString("id");
		
		transactiondetail.addProperty("transactiondetailid", transactionDetailid);
		
		if (isPurchase) {
			// Check for batch
			if (transactiondetail.get("batchid").getAsLong() == 0) {
				// Create new batch
				transactiondetail.addProperty("batchid", createBatch(transactiondetail, request));
			}
			
			// Check for rate
			if (transactiondetail.get("rateid").getAsLong() == 0) {
				// Create new rate
				transactiondetail.addProperty("rateid", createRate(transactiondetail, request));
			}
		}

		if (voucherNoInt == 0) {
			transactiondetail.addProperty("tnew", 0);
			transactiondetail.addProperty("active", 0);
			transactiondetail.addProperty("vouchermode", 0);
		} else {
			transactiondetail.addProperty("tnew", 1);
			transactiondetail.addProperty("active", 1);
			transactiondetail.addProperty("vouchermode", 2);
		}
		
		return utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail,
				null, "INSERT", request);
	}

	public long postCommonTransactionWithoutStockItemUpdate(TransactiondetailBody transactionDetailBody,
			HttpServletRequest request) throws JsonProcessingException {
		int voucherNoInt = Integer.parseInt(transactionDetailBody.getVoucherno());
		if (voucherNoInt == 0) {
			deleteTransactionDetail(transactionDetailBody.getTransactiondetailid().toString(), request);
		} else {
			deactivateTransactionDetail(transactionDetailBody.getTransactionmasterid().toString(),
					transactionDetailBody.getTransactiondetailid().toString(), request);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String transactionDetailString = mapper.writeValueAsString(transactionDetailBody);
		JsonObject transactiondetail = JsonParser.parseString(transactionDetailString).getAsJsonObject();

		// Id generation
		String transactionDetailid = utility.executeIdGenerationProcedure(transactionDetailBody.getOrgid().toString(),
				transactionDetailBody.getOprid().toString(), "transactiondetail",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		transactionDetailid = new JSONObject(transactionDetailid).getString("id");

		transactiondetail.addProperty("transactiondetailid", transactionDetailid);
		transactiondetail.remove("modifyon");
		transactiondetail.addProperty("lastoperation", "INSERT");
		
		if (voucherNoInt == 0) {
			transactiondetail.addProperty("tnew", 0);
			transactiondetail.addProperty("active", 0);
		} else {
			transactiondetail.addProperty("tnew", 1);
			transactiondetail.addProperty("active", 1);
		}
		
		return utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail,
				null, "INSERT", request);

	}

	public long deleteTransactionDetail(String transactiondetailid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from transactiondetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and transactiondetailid = " + transactiondetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long deactivateTransactionDetail(String transactionmasterid, String transactiondetailid,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "update transactiondetail set active = 0 where orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
				+ adUserAccessToken.get("oprid") + " and transactionmasterid =" + transactionmasterid
				+ "  and  transactiondetailid = " + transactiondetailid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long postCommonTransactionWithoutStockItemDelete(TransactiondetailBody transactionDetailBody,
			HttpServletRequest request) {
		int voucherNoInt = Integer.parseInt(transactionDetailBody.getVoucherno());
		long result = 0;
		if (voucherNoInt == 0) {
			result = deleteTransactionDetail(transactionDetailBody.getTransactiondetailid().toString(), request);
		} else {
			result = deactivateTransactionDetail(transactionDetailBody.getTransactionmasterid().toString(),
					transactionDetailBody.getTransactiondetailid().toString(), request);
		}
		return result;
	}

	public long deleteTransactionMasterDetail(String transactionmasterid, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String query = "";
		Object[] params = new Object[] {};
		query = "delete from transactiondetail where orgid =" + adUserAccessToken.get("orgid") + " and oprid = "
				+ adUserAccessToken.get("oprid") + " and transactionmasterid = " + transactionmasterid;
		utility.executeDMLQueryOnPool(query, params, request);

		query = "delete from transactionmaster where orgid =1 and oprid=1 and transactionmasterid = "
				+ transactionmasterid;
		return utility.executeDMLQueryOnPool(query, params, request);
	}

	public long postCommonTransactionWithoutStockEscape(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		int voucherNoInt = body.getTransactionmaster().getVoucherno();
		long result = 0;
		if (voucherNoInt == 0) {
			result = deleteTransactionMasterDetail(body.getTransactionmaster().getTransactionmasterid().toString(),
					request);
		} else {
			Object[] params = new Object[] {};
			String sql = "DELETE FROM transactiondetail\r\n" + "WHERE     orgid = " + adUserAccessToken.get("orgid")
					+ "      AND oprid = " + adUserAccessToken.get("oprid") + "      AND transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid() + " " + "      AND tnew = 1";
			utility.executeDMLQueryOnPool(sql, params, request);

			sql = "update transactiondetail set active = 1 where orgid =" + adUserAccessToken.get("orgid")
					+ " and oprid =" + adUserAccessToken.get("oprid") + " and transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid() + " and active = 0";
			result = utility.executeDMLQueryOnPool(sql, params, request);
		}
		return result;

	}

	public int postCommonTransactionFinalSave(TrnMasterBody body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		TransactionmasterBody trnMaster = body.getTransactionmaster();
		int voucherNoInt = body.getTransactionmaster().getVoucherno();
		Object[] params = new Object[] {};
		String query = ""; 
		String voucherno = "";
		
		// Generating the voucher no if 0 is received in voucher no
		if (voucherNoInt == 0) {
//			query = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno\r\n" + "FROM transactionmaster "
//					+ "WHERE orgid =" + adUserAccessToken.get("orgid") + " AND oprid =" + adUserAccessToken.get("oprid")
//					+ "  AND vouchertype = '" + body.getTransactionmaster().getVouchertype() + "'";
//			JsonArray datadtl = utility.executeQueryOnPool(query, request);
//			voucherno = datadtl.get(0).getAsJsonObject().get("voucherno").getAsInt();
			
			voucherno = utility.executeInvoiceNoGenerationProcedure(trnMaster.getOrgid().toString(), trnMaster.getOprid().toString(), 
					trnMaster.getDoctypeid().toString(), trnMaster.getFiyearid().toString(), trnMaster.getStoreid().toString(), "0", 
					request.getHeader("rightId"), token, request.getRequestURL().toString());
			
			voucherno = JsonParser.parseString(voucherno).getAsJsonObject().get("id").getAsString();
		} else {
			if (body.getTransactionmaster().getVouchertype().equals("PR")) {
				query = "select * from transactiondetail where orgid = " + adUserAccessToken.get("orgid")
					+ " AND oprid = " + adUserAccessToken.get("oprid") + " AND transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid() + 
					" AND (voucherno is not null AND voucherno <> '') AND active = 0";
				JsonArray datadtl = utility.executeQueryOnPool(query, request);
				for (JsonElement element : datadtl) {
					JsonObject transactiondetail = element.getAsJsonObject();
					// Check pending quantity from product master
					BigDecimal pendingQty = BigDecimal.ZERO;
					query = "select pendingqty from product where orgid = " + transactiondetail.get("orgid").getAsString() + 
							" and productid = " + transactiondetail.get("productid").getAsString();
					JsonArray productList = utility.executeQueryOnPool(query, request);
					if (productList.size() > 0) {
						JsonObject obj = productList.get(0).getAsJsonObject();
						pendingQty = (obj.get("pendingqty") == null || obj.get("pendingqty").isJsonNull()) ? BigDecimal.ZERO : 
							obj.get("pendingqty").getAsBigDecimal();
					}
					
					// Update pending quantity in product master
					JsonObject product = new JsonObject();
					product.addProperty("productid", transactiondetail.get("productid").getAsString());
					product.addProperty("pendingqty", (transactiondetail.get("qty").getAsBigDecimal()
							.add(transactiondetail.get("free").getAsBigDecimal())).subtract(pendingQty));
					utility.executeCustomDML("product", "productid", product,
							null, "UPDATE", request);
				}
			}
			// In case the voucher no is already generated delete the records that are
			// deactivated
			String sql = "DELETE FROM transactiondetail \r\n" + "WHERE orgid = " + adUserAccessToken.get("orgid")
					+ " AND oprid = " + adUserAccessToken.get("oprid") + " AND transactionmasterid = "
					+ body.getTransactionmaster().getTransactionmasterid() + " " + " AND active = 0";
			utility.executeDMLQueryOnPool(sql, params, request);
			voucherno = "" + trnMaster.getVoucherno();
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
		
		if (body.getTransactionmaster().getVouchertype().equals("PR")) {
			query = "select * from transactiondetail where orgid = " + adUserAccessToken.get("orgid")
				+ " AND oprid = " + adUserAccessToken.get("oprid") + " AND transactionmasterid = "
				+ body.getTransactionmaster().getTransactionmasterid();
			
			JsonArray datadtl = utility.executeQueryOnPool(query, request);
			for (JsonElement element : datadtl) {
				JsonObject transactiondetail = element.getAsJsonObject();
				// Check pending quantity from product master
				BigDecimal pendingQty = BigDecimal.ZERO;
				query = "select pendingqty from product where orgid = " + transactiondetail.get("orgid").getAsString() + 
						" and productid = " + transactiondetail.get("productid").getAsString();
				JsonArray productList = utility.executeQueryOnPool(query, request);
				if (productList.size() > 0) {
					JsonObject obj = productList.get(0).getAsJsonObject();
					pendingQty = (obj.get("pendingqty") == null || obj.get("pendingqty").isJsonNull()) ? BigDecimal.ZERO : 
						obj.get("pendingqty").getAsBigDecimal();
				}
				
				// Update pending quantity in product master
				JsonObject product = new JsonObject();
				product.addProperty("productid", transactiondetail.get("productid").getAsString());
				product.addProperty("pendingqty", transactiondetail.get("qty").getAsBigDecimal()
						.add(transactiondetail.get("free").getAsBigDecimal()).add(pendingQty));
				utility.executeCustomDML("product", "productid", product,
						null, "UPDATE", request);
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
		transactionmaster.addProperty("gstadj", body.getTransactionmaster().getGstadj());
		transactionmaster.addProperty("gstdebitnote", body.getTransactionmaster().getGstdebitnote());
		transactionmaster.addProperty("printflag", body.getTransactionmaster().getPrintflag());
		transactionmaster.addProperty("mmailsend", body.getTransactionmaster().getMmailsend());
		transactionmaster.addProperty("fiyearid", body.getTransactionmaster().getFiyearid());
		transactionmaster.addProperty("trnmonth", body.getTransactionmaster().getTrnmonth());
		transactionmaster.addProperty("trnyear", body.getTransactionmaster().getTrnyear());
		transactionmaster.addProperty("invoicetime", body.getTransactionmaster().getInvoicetime().toString());
		transactionmaster.addProperty("invcrtime", body.getTransactionmaster().getInvcrtime().toString());
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
		if (body.getTransactionmaster().getDocuploadrefid() != null) {
			transactionmaster.addProperty("docuploadrefid", body.getTransactionmaster().getDocuploadrefid());
		}
		if (body.getTransactionmaster().getAgentid() != null) {
			transactionmaster.addProperty("agentid", body.getTransactionmaster().getAgentid());
		}
		JsonObject transactionmasterwhere = new JsonObject();
		transactionmasterwhere.addProperty("orgid", adUserAccessToken.get("orgid"));
		transactionmasterwhere.addProperty("oprid", adUserAccessToken.get("oprid"));
		transactionmasterwhere.addProperty("transactionmasterid", body.getTransactionmaster().getTransactionmasterid());

		utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, transactionmasterwhere,
				"UPDATE", request);
		return Integer.parseInt(voucherno);
	}

	public JsonArray getCommonTransactionResponse(String transactionmasterid, int voucherNoInt,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		String whereClause = "";
		if (voucherNoInt != 0) {
			whereClause = " and transactiondetail.active= 1";
		}
		String query = "SELECT transactiondetail.*,\r\n" + 
				"       (CASE\r\n" + 
				"           WHEN gsttp = 0 THEN 'Exempted'\r\n" + 
				"           WHEN gsttp = 1 THEN 'Taxable'\r\n" + 
				"           WHEN gsttp = 2 THEN 'Nil Rated'\r\n" + 
				"           WHEN gsttp = 3 THEN 'Zero Rated'\r\n" + 
				"           WHEN gsttp = 4 THEN 'Non Gst'\r\n" + 
				"           WHEN gsttp = 5 THEN 'Direct'\r\n" + 
				"        END)\r\n" + 
				"          gsttypedesc,\r\n" + 
				"       (SELECT comments\r\n" + 
				"        FROM commonnarration\r\n" + 
				"        WHERE commonnarrationid = linkid)\r\n" + 
				"          comments,\r\n" + 
				"       (SELECT title\r\n" + 
				"        FROM location\r\n" + 
				"        WHERE location.locationid = productoprdtl.locationid)location\r\n" + 
				"FROM transactiondetail\r\n" + 
				"     INNER JOIN productoprdtl\r\n" + 
				"        ON     transactiondetail.orgid = productoprdtl.orgid\r\n" + 
				"           AND transactiondetail.oprid = productoprdtl.oprid\r\n" + 
				"           AND transactiondetail.productid = productoprdtl.productid\r\n" + 
				"     INNER JOIN location\r\n" + 
				"        ON     productoprdtl.orgid = location.orgid\r\n" + 
				"           AND productoprdtl.oprid = location.oprid\r\n" + 
				"           AND productoprdtl.locationid = location.locationid  where transactiondetail.orgid =" + adUserAccessToken.get("orgid")
				+ " and transactiondetail.oprid=" + adUserAccessToken.get("oprid") + " and transactiondetail.transactionmasterid =" + transactionmasterid
				+ whereClause + " order by srno desc";
		return utility.executeQueryOnPool(query, request);
	}
	
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
			
			result = updateTransactionStock(query, params, request);
		}
		
		return result;
	}
	
	@Transactional
	public String invoicePosting(List<PaymentreceiptBody> body, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		Long invoiceid = body.get(0).getTransactionid();
		JsonObject transactionmaster = new JsonObject();
		
		String query = "SELECT * FROM transactionmaster where transactionmasterid = " + invoiceid;
		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			transactionmaster = data.get(0).getAsJsonObject();

			query = "SELECT * FROM transactiondetail where transactionmasterid = " + invoiceid;
			data = utility.executeQueryOnPool(query, request);
		}
		
		boolean amountCheck = false;
		if (RestUtil.getAsString(transactionmaster.get("vouchertype")).equals("PR") 
				|| (RestUtil.getAsString(transactionmaster.get("vouchertype")).equals("CN") && RestUtil.getAsString(transactionmaster.get("creditnotetype")).equals("3")) 
				|| (RestUtil.getAsString(transactionmaster.get("vouchertype")).equals("DN") && RestUtil.getAsString(transactionmaster.get("creditnotetype")).equals("3"))) {
			amountCheck = true;
		}
		
		if (amountCheck && RestUtil.getAsBigDecimal(transactionmaster.get("billamount"))
				.compareTo(RestUtil.getAsBigDecimal(transactionmaster.get("netamount"))) != 0) {
			return "Bill Amount and Net Amount not match";
		}
		
		if (!amountCheck) {
			transactionmaster.addProperty("billamount", RestUtil.getAsBigDecimal(transactionmaster.get("netamount")));
		}
		
		query = "SELECT ledgermasterid FROM ledgermaster WHERE transactionid = " + invoiceid;
		JsonArray olddata = utility.executeQueryOnPool(query, request);
		if (olddata.size() > 0) {
			JsonObject ledgerdetail = new JsonObject();
			ledgerdetail.addProperty("ledgermasterid", olddata.get(0).getAsJsonObject().get("ledgermasterid").getAsString());
			
			utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdetail,
					ledgerdetail, "DELETE", request);
			
			JsonObject ledgermst = new JsonObject();
			ledgermst.addProperty("ledgermasterid", olddata.get(0).getAsJsonObject().get("ledgermasterid").getAsString());
			ledgermst.addProperty("transactionid", invoiceid);
			
			utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermst,
					null, "DELETE", request);
		}
		
		JsonObject taxSlabs = new JsonObject();
		for (JsonElement trnDtlElement : data) {
			JsonObject transactionDtl = trnDtlElement.getAsJsonObject();
			if (taxSlabs.has(transactionDtl.get("gstpercent").getAsString())) {
				JsonObject obj = taxSlabs.getAsJsonObject(transactionDtl.get("gstpercent").getAsString());
				obj.addProperty("gstvalue", obj.get("gstvalue").getAsBigDecimal().add(transactionDtl.get("gstvalue").getAsBigDecimal()));
				obj.addProperty("igstamount", obj.get("igstamount").getAsBigDecimal().add(transactionDtl.get("igstamount").getAsBigDecimal()));
				obj.addProperty("ugstamount", obj.get("ugstamount").getAsBigDecimal().add(transactionDtl.get("ugstamount").getAsBigDecimal()));
				obj.addProperty("sgstamount", obj.get("sgstamount").getAsBigDecimal().add(transactionDtl.get("sgstamount").getAsBigDecimal()));
				obj.addProperty("cgstamount", obj.get("cgstamount").getAsBigDecimal().add(transactionDtl.get("cgstamount").getAsBigDecimal()));
			} else {
				taxSlabs.add(transactionDtl.get("gstpercent").getAsString(), transactionDtl);
			}
		}
		
		List<String> saleList = Arrays.asList("SL","SB");
		List<String> purchaseList = Arrays.asList("PR","PB");
		List<String> saleReturnList = Arrays.asList("CN","DN");
		List<String> purchaseReturnList = Arrays.asList("CN","PREB","SDCN","CCD","EBO","CCE","CCS","OIE","OIS","SIE","SIS");
		
		query = "SELECT a.identity FROM accountoprdetail a WHERE a.accountid = " + transactionmaster.get("accountid").getAsString();
		JsonArray accountOprData = utility.executeQueryOnPool(query, request);
		String identity = accountOprData.get(0).getAsJsonObject().get("identity").getAsString();
		
		JsonArray outstandingData = new JsonArray();
		// Add data in outstanding
		String outstandingid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
				transactionmaster.get("oprid").getAsString(), "outstanding",
				request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
		outstandingid = new JSONObject(outstandingid).get("id").toString();
		
		JsonObject outstanding = new JsonObject();
		outstanding.addProperty("orgid", RestUtil.getAsString(transactionmaster.get("orgid")));
		outstanding.addProperty("oprid", RestUtil.getAsString(transactionmaster.get("oprid")));
		outstanding.addProperty("outstandingid", outstandingid);
		outstanding.addProperty("transactionid", invoiceid);
		outstanding.addProperty("accountid", RestUtil.getAsString(transactionmaster.get("accountid")));
		outstanding.addProperty("identity", identity);
		outstanding.addProperty("doctype", RestUtil.getAsString(transactionmaster.get("vouchertype")));
		outstanding.addProperty("docseries", RestUtil.getAsString(transactionmaster.get("voucherseries")));
		outstanding.addProperty("billno", RestUtil.getAsString(transactionmaster.get("billno")));
		outstanding.addProperty("billdate", transactionmaster.get("billdate").isJsonNull() ? 
				null : RestUtil.getAsString(transactionmaster.get("billdate")));
		outstanding.addProperty("billamt", RestUtil.getAsBigDecimal(transactionmaster.get("billamount")));
		outstanding.addProperty("receivedamt", 0);
		outstanding.addProperty("billdiscountamt", RestUtil.getAsBigDecimal(transactionmaster.get("cashdiscountamount")));
		outstanding.addProperty("billinvoiceno", RestUtil.getAsString(transactionmaster.get("voucherno")));
		outstanding.addProperty("agencyid", RestUtil.getAsString(transactionmaster.get("agencyid")));
		outstanding.addProperty("balanceamount", RestUtil.getAsBigDecimal(transactionmaster.get("netamount")));
		outstanding.addProperty("creditamount", RestUtil.getAsBigDecimal(transactionmaster.get("creditnoteamount")));
		outstanding.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
		outstanding.addProperty("createdby", adUserAccessToken.get("adusermastid"));
		outstanding.addProperty("lastoperation", "INSERT");
		
		utility.executeCustomDML("outstanding", "outstandingid", outstanding,
				null, "INSERT", request);
		
		outstandingData.add(outstanding);
		
		// Add debit note details
		if (transactionmaster.get("adjdebitnote") != null && !transactionmaster.get("adjdebitnote").isJsonNull()) {
			String[] debits = transactionmaster.get("adjdebitnote").getAsString().split(",");
			for (int i = 0; i < debits.length; i++) {
				outstandingid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "outstanding",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				outstandingid = new JSONObject(outstandingid).get("id").toString();
				
				JsonObject debitNote = new JsonObject();
				debitNote.addProperty("orgid", RestUtil.getAsString(transactionmaster.get("orgid")));
				debitNote.addProperty("oprid", RestUtil.getAsString(transactionmaster.get("oprid")));
				debitNote.addProperty("outstandingid", outstandingid);
				debitNote.addProperty("transactionid", invoiceid);
				debitNote.addProperty("docseries", "DN");
				debitNote.addProperty("doctype", "DN");
				debitNote.addProperty("billinvoiceno", debits[i].split("-")[0]);
				debitNote.addProperty("billno", RestUtil.getAsString(transactionmaster.get("billno")));
				debitNote.addProperty("billdate", RestUtil.getAsString(transactionmaster.get("billdate")));
				debitNote.addProperty("billamt", debits[i].split("-")[1]);
				debitNote.addProperty("accountid", RestUtil.getAsString(transactionmaster.get("accountid")));
				debitNote.addProperty("identity", identity);
				debitNote.addProperty("receivedamt", 0);
				debitNote.addProperty("billdiscountamt", 0);
				debitNote.addProperty("agencyid", RestUtil.getAsString(transactionmaster.get("agencyid")));
				debitNote.addProperty("balanceamount", debits[i].split("-")[1]);
				debitNote.addProperty("creditamount", 0);
				debitNote.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
				debitNote.addProperty("createdby", adUserAccessToken.get("adusermastid"));
				debitNote.addProperty("lastoperation", "INSERT");
				
				utility.executeCustomDML("outstanding", "outstandingid", debitNote,
						null, "INSERT", request);
				
				outstandingData.add(debitNote);
			}
		}
		
		// Add credit note details
		if (transactionmaster.get("adjcreditnote") != null && !transactionmaster.get("adjcreditnote").isJsonNull()) {
			String[] credits = transactionmaster.get("adjcreditnote").getAsString().split(",");
			for (int i = 0; i < credits.length; i++) {
				JsonObject creditNote = new JsonObject();
				creditNote.addProperty("docseries", "CN");
				creditNote.addProperty("billinvoiceno", credits[i].split("-")[0]);
				creditNote.addProperty("doctype", "CN");
				creditNote.addProperty("billamt", credits[i].split("-")[1]);
				creditNote.addProperty("accountid", RestUtil.getAsString(transactionmaster.get("accountid")));
				creditNote.addProperty("identity", identity);
				
				outstandingData.add(creditNote);
			}
		}
		
		for (JsonElement outstandingelement : outstandingData) {
			outstanding = outstandingelement.getAsJsonObject();
			int ledgerEntries = 0;

			// Id generation
			String ledgermasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(), "ledgermaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgermasterid = new JSONObject(ledgermasterid).get("id").toString();
	
			JsonObject ledgermst = new JsonObject();
			ledgermst.addProperty("orgid", transactionmaster.get("orgid").getAsString());
			ledgermst.addProperty("oprid", transactionmaster.get("oprid").getAsString());
			ledgermst.addProperty("ledgermasterid", ledgermasterid);
			ledgermst.addProperty("referenceid", RestUtil.getAsString(outstanding.get("outstandingid")));
			ledgermst.addProperty("voucherdate", RestUtil.getAsString(transactionmaster.get("voucherdate")));
			ledgermst.addProperty("voucherseries", RestUtil.getAsString(outstanding.get("docseries")));
			ledgermst.addProperty("voucherno", RestUtil.getAsString(outstanding.get("billinvoiceno")));
			ledgermst.addProperty("vouchertype", RestUtil.getAsString(outstanding.get("doctype")));
			ledgermst.addProperty("vouchermode", RestUtil.getAsString(transactionmaster.get("vouchermode")));
			ledgermst.addProperty("totalamount", RestUtil.getAsBigDecimal(outstanding.get("billamt")));
			ledgermst.addProperty("transactionid", invoiceid);
			ledgermst.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
			ledgermst.addProperty("createdby", adUserAccessToken.get("adusermastid"));
			ledgermst.addProperty("lastoperation", "INSERT");
			
			String ledgerdetailid = "0";
			JsonObject ledgerdtl = new JsonObject();
			
			// Party A/C
			if (RestUtil.getAsBigDecimal(outstanding.get("billamt")).compareTo(BigDecimal.ZERO) > 0) {
				ledgerdetailid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
				
				ledgerdtl.addProperty("orgid", transactionmaster.get("orgid").getAsString());
				ledgerdtl.addProperty("oprid", transactionmaster.get("oprid").getAsString());
				ledgerdtl.addProperty("ledgermasterid", ledgermasterid);
				ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdtl.addProperty("accountid", RestUtil.getAsString(outstanding.get("accountid")));
				ledgerdtl.addProperty("identity", RestUtil.getAsString(outstanding.get("identity")));
				ledgerdtl.addProperty("vouchertype", RestUtil.getAsString(outstanding.get("doctype")));
				ledgerdtl.addProperty("voucherseries", RestUtil.getAsString(outstanding.get("docseries")));
				ledgerdtl.addProperty("voucherno", RestUtil.getAsString(outstanding.get("billinvoiceno")));
				ledgerdtl.addProperty("voucherdate", RestUtil.getAsString(transactionmaster.get("voucherdate")));
				
				if (outstanding.get("doctype").getAsString().equals("CN")) {
					ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(outstanding.get("billamt")));
					ledgerdtl.addProperty("dramt", 0);
				} else {
					ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(outstanding.get("billamt")));
					ledgerdtl.addProperty("cramt", 0);
				}
				
				ledgerdtl.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
				ledgerdtl.addProperty("createdby", adUserAccessToken.get("adusermastid"));
				ledgerdtl.addProperty("lastoperation", "INSERT");
				
				utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
						null, "INSERT", request);
				ledgerEntries++;
			}
			// Percent slab wise taxes
			Set<String> slabs = taxSlabs.keySet();
			for (String slab : slabs) {
				JsonObject transactionDtl = taxSlabs.getAsJsonObject(slab);
				String vouchertype = transactionDtl.get("vouchertype").getAsString();
				String acctag = "";
				
				if (vouchertype.equals("CN")) {
					if (transactionmaster.get("creditnotetype").getAsString().equals("0"))
						acctag = "SR";
					else
						acctag = "PR";
				} else if (saleList.contains(vouchertype)) {
					acctag = "S";
				} else if (purchaseList.contains(vouchertype)) {
					acctag = "P";
				} else if (saleReturnList.contains(vouchertype)) {
					acctag = "SR";
				} else if (purchaseReturnList.contains(vouchertype)) {
					acctag = "PR";
				}
				
				query = "SELECT accprefix, accountid FROM taxaccount where taxid = " + transactionDtl.get("taxcode").getAsString() + 
						" and acctag = '" + acctag + "'";
				JsonArray taxAccountList = utility.executeQueryOnPool(query, request);
				JsonObject taxAccount = new JsonObject();
				for (JsonElement element : taxAccountList) {
					JsonObject obj = element.getAsJsonObject();
					taxAccount.addProperty(obj.get("accprefix").getAsString(), obj.get("accountid").getAsString());
				}
				
				// GST Taxable
				if (RestUtil.getAsBigDecimal(transactionDtl.get("gstvalue")).compareTo(BigDecimal.ZERO) > 0) {
					ledgerdetailid = utility.executeIdGenerationProcedure(transactionDtl.get("orgid").getAsString(),
							transactionDtl.get("oprid").getAsString(), "ledgerdetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
					
					ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
					ledgerdtl.addProperty("accountid", RestUtil.getAsString(taxAccount.get("T")));
					
					if (outstanding.get("doctype").getAsString().equals("CN")) {
						ledgerdtl.addProperty("cramt", 0);
						ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(transactionDtl.get("gstvalue")));
					} else {
						ledgerdtl.addProperty("dramt", 0);
						ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionDtl.get("gstvalue")));
					}
					
					utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
							null, "INSERT", request);
					ledgerEntries++;
				}
				
				BigDecimal igst = RestUtil.getAsBigDecimal(transactionDtl.get("igstamount"));
				if (igst.compareTo(BigDecimal.ZERO) > 0) {
					// IGST
					if (igst.compareTo(BigDecimal.ZERO) > 0) {
						ledgerdetailid = utility.executeIdGenerationProcedure(transactionDtl.get("orgid").getAsString(),
								transactionDtl.get("oprid").getAsString(), "ledgerdetail",
								request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
						ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
						
						ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
						ledgerdtl.addProperty("accountid", RestUtil.getAsString(taxAccount.get("I")));
						
						if (outstanding.get("doctype").getAsString().equals("CN")) {
							ledgerdtl.addProperty("cramt", 0);
							ledgerdtl.addProperty("dramt", igst);
						} else {
							ledgerdtl.addProperty("dramt", 0);
							ledgerdtl.addProperty("cramt", igst);
						}
						
						utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
								null, "INSERT", request);
						ledgerEntries++;
					}
				} else {
					ledgerdetailid = utility.executeIdGenerationProcedure(transactionDtl.get("orgid").getAsString(),
							transactionDtl.get("oprid").getAsString(), "ledgerdetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
					
					BigDecimal ugst = RestUtil.getAsBigDecimal(transactionDtl.get("ugstamount"));
					if (ugst.compareTo(BigDecimal.ZERO) > 0) {
						// UGST
						ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
						ledgerdtl.addProperty("accountid", RestUtil.getAsString(taxAccount.get("U")));
						
						if (outstanding.get("doctype").getAsString().equals("CN")) {
							ledgerdtl.addProperty("cramt", 0);
							ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(transactionDtl.get("ugstamount")));
						} else {
							ledgerdtl.addProperty("dramt", 0);
							ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionDtl.get("ugstamount")));
						}
						
						utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
								null, "INSERT", request);
						ledgerEntries++;
					} else {
						// SGST
						if (RestUtil.getAsBigDecimal(transactionDtl.get("sgstamount")).compareTo(BigDecimal.ZERO) > 0) {
							ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
							ledgerdtl.addProperty("accountid", RestUtil.getAsString(taxAccount.get("S")));
							
							if (outstanding.get("doctype").getAsString().equals("CN")) {
								ledgerdtl.addProperty("cramt", 0);
								ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(transactionDtl.get("sgstamount")));
							} else {
								ledgerdtl.addProperty("dramt", 0);
								ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionDtl.get("sgstamount")));
							}
							
							utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
									null, "INSERT", request);
							ledgerEntries++;
						}
					}
					
					// CGST
					if (RestUtil.getAsBigDecimal(transactionDtl.get("cgstamount")).compareTo(BigDecimal.ZERO) > 0) {
						ledgerdetailid = utility.executeIdGenerationProcedure(transactionDtl.get("orgid").getAsString(),
								transactionDtl.get("oprid").getAsString(), "ledgerdetail",
								request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
						ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
						
						ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
						ledgerdtl.addProperty("accountid", RestUtil.getAsString(taxAccount.get("C")));
						
						if (outstanding.get("doctype").getAsString().equals("CN")) {
							ledgerdtl.addProperty("cramt", 0);
							ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(transactionDtl.get("cgstamount")));
						} else {
							ledgerdtl.addProperty("dramt", 0);
							ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionDtl.get("cgstamount")));
						}
						
						utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
								null, "INSERT", request);
						ledgerEntries++;
					}
				}
			}
			
			// CESS
			if (RestUtil.getAsBigDecimal(transactionmaster.get("cessamount")).compareTo(BigDecimal.ZERO) > 0) {
				ledgerdetailid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
				
				ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdtl.addProperty("dramt", 0);
				ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionmaster.get("cessamount")));
				ledgerdtl.addProperty("accountid", "1");
				
				utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
						null, "INSERT", request);
				ledgerEntries++;
			}
			
			// TCS
			if (RestUtil.getAsBigDecimal(transactionmaster.get("tcstamount")).compareTo(BigDecimal.ZERO) > 0) {
				ledgerdetailid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
				
				ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdtl.addProperty("dramt", 0);
				ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionmaster.get("tcstamount")));
				ledgerdtl.addProperty("accountid", "2");
				
				utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
						null, "INSERT", request);
				ledgerEntries++;
			}
			
			// ROUND OFF
			if (RestUtil.getAsBigDecimal(transactionmaster.get("roundof")).compareTo(BigDecimal.ZERO) > 0) {
				ledgerdetailid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
						transactionmaster.get("oprid").getAsString(), "ledgerdetail",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
	
				ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
				ledgerdtl.addProperty("dramt", 0);
				ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(transactionmaster.get("roundof")));
				ledgerdtl.addProperty("accountid", "3");
	
				utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl, null, "INSERT", request);
				ledgerEntries++;
			}
			
			// Add in ledger master
			if (ledgerEntries > 0)
				utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermst,
						null, "INSERT", request);
		}
		
		cashReceiptPosting(body, true, request);
		
		return "Success";
	}
	
	@Transactional
	public String cashReceipt(List<PaymentreceiptBody> body, HttpServletRequest request) {
		return cashReceiptPosting(body, false, request);
	}
	
	public String cashReceiptPosting(List<PaymentreceiptBody> body, boolean isAutoPosting, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		
		Long invoiceid = body.get(0).getTransactionid();
		JsonObject transactionmaster = new JsonObject();
		
		String query = "SELECT * FROM transactionmaster where transactionmasterid = " + invoiceid;
		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			transactionmaster = data.get(0).getAsJsonObject();
		}
		
		JsonObject ledgerData = new JsonObject();
		JsonObject outstandingData = new JsonObject();
		JsonObject paymentreceipt = new JsonObject();
		List<String> voucherTypes = Arrays.asList("CN","JV","CR","CP","BP","BR");
		String voucherno = "0";
		for (PaymentreceiptBody receipt : body) {
			paymentreceipt = formater.serializeObject(receipt);
			
			if (!voucherTypes.contains(paymentreceipt.get("doctype").getAsString()))
				continue;
			
			// Separate data for ledger
			if (ledgerData.has(paymentreceipt.get("doctype").getAsString())) {
				JsonObject obj = ledgerData.get(paymentreceipt.get("doctype").getAsString()).getAsJsonObject();
				obj.addProperty("adjustamt", RestUtil.getAsBigDecimal(obj.get("adjustamt"))
						.add(RestUtil.getAsBigDecimal(paymentreceipt.get("adjustamt"))));
				
				paymentreceipt.addProperty("voucherno", voucherno);
			} else {
				query = "SELECT (ifnull(max(cast(voucherno AS unsigned)), 0) + 1) voucherno \r\n" + "FROM paymentreceipt "
						+ "WHERE orgid = " + paymentreceipt.get("orgid").getAsString() + " AND oprid = " + paymentreceipt.get("oprid").getAsString()
						+ "  AND doctype = '" + paymentreceipt.get("doctype").getAsString() + "'";
				JsonArray datadtl = utility.executeQueryOnPool(query, request);
				voucherno = datadtl.get(0).getAsJsonObject().get("voucherno").toString();
				
				paymentreceipt.addProperty("voucherno", voucherno);
				ledgerData.add(paymentreceipt.get("doctype").getAsString(), paymentreceipt);
			}
			
			// Insert in payment receipt
			String paymentreceiptid = utility.executeIdGenerationProcedure(paymentreceipt.get("orgid").getAsString(),
					paymentreceipt.get("oprid").getAsString(), "paymentreceipt",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			paymentreceiptid = new JSONObject(paymentreceiptid).get("id").toString();
			paymentreceipt.addProperty("paymentreceiptid", paymentreceiptid);
			paymentreceipt.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
			paymentreceipt.addProperty("createdby", adUserAccessToken.get("adusermastid"));
			paymentreceipt.addProperty("lastoperation", "INSERT");
			
			utility.executeCustomDML("paymentreceipt", "paymentreceiptid", paymentreceipt,
					null, "INSERT", request);
			
			// Separate data for outstanding
			if (outstandingData.has(paymentreceipt.get("outstandingrefid").getAsString())) {
				BigDecimal amount = outstandingData.get(paymentreceipt.get("outstandingrefid").getAsString()).getAsBigDecimal()
						.add(RestUtil.getAsBigDecimal(paymentreceipt.get("adjustamt")));
				outstandingData.addProperty(paymentreceipt.get("outstandingrefid").getAsString(), amount);
			} else {
				outstandingData.addProperty(paymentreceipt.get("outstandingrefid").getAsString(), RestUtil.getAsBigDecimal(paymentreceipt.get("adjustamt")));
			}
		}
		
		// Insert in ledger
		Set<String> keys = ledgerData.keySet();
		for (String key : keys) {
			JsonObject obj = ledgerData.getAsJsonObject(key);
			
			// Master
			String ledgermasterid = utility.executeIdGenerationProcedure(obj.get("orgid").getAsString(),
					obj.get("oprid").getAsString(), "ledgermaster",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgermasterid = new JSONObject(ledgermasterid).get("id").toString();

			JsonObject ledgermst = new JsonObject();
			ledgermst.addProperty("orgid", obj.get("orgid").getAsString());
			ledgermst.addProperty("oprid", obj.get("oprid").getAsString());
			ledgermst.addProperty("ledgermasterid", ledgermasterid);
			ledgermst.addProperty("voucherdate", RestUtil.getAsString(transactionmaster.get("voucherdate")));
			ledgermst.addProperty("voucherseries", RestUtil.getAsString(obj.get("docseries")));
			ledgermst.addProperty("voucherno", RestUtil.getAsString(obj.get("billno")));
			ledgermst.addProperty("vouchertype", RestUtil.getAsString(obj.get("doctype")));
			ledgermst.addProperty("vouchermode", RestUtil.getAsString(obj.get("vouchermode")));
			ledgermst.addProperty("totalamount", RestUtil.getAsBigDecimal(obj.get("adjustamt")));
			ledgermst.addProperty("transactionid", invoiceid);
			ledgermst.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
			ledgermst.addProperty("createdby", adUserAccessToken.get("adusermastid"));
			ledgermst.addProperty("lastoperation", "INSERT");
			
			utility.executeCustomDML("ledgermaster", "ledgermasterid", ledgermst,
					null, "INSERT", request);
			
			// Detail
			String ledgerdetailid = utility.executeIdGenerationProcedure(obj.get("orgid").getAsString(),
					obj.get("oprid").getAsString(), "ledgerdetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
			
			JsonObject ledgerdtl = new JsonObject();
			ledgerdtl.addProperty("orgid", obj.get("orgid").getAsString());
			ledgerdtl.addProperty("oprid", obj.get("oprid").getAsString());
			ledgerdtl.addProperty("ledgermasterid", ledgermasterid);
			ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
			ledgerdtl.addProperty("accountid", RestUtil.getAsString(obj.get("partycode")));
			ledgerdtl.addProperty("identity", RestUtil.getAsString(obj.get("identity")));
			ledgerdtl.addProperty("vouchertype", RestUtil.getAsString(obj.get("doctype")));
			ledgerdtl.addProperty("voucherseries", RestUtil.getAsString(obj.get("docseries")));
			ledgerdtl.addProperty("voucherno", RestUtil.getAsString(obj.get("voucherno")));
			ledgerdtl.addProperty("voucherdate", RestUtil.getAsString(transactionmaster.get("voucherdate")));
			ledgerdtl.addProperty("dramt", RestUtil.getAsBigDecimal(obj.get("adjustamt")));
			ledgerdtl.addProperty("cramt", 0);
			ledgerdtl.addProperty("createdon", new Timestamp(System.currentTimeMillis()).toString());
			ledgerdtl.addProperty("createdby", adUserAccessToken.get("adusermastid"));
			ledgerdtl.addProperty("lastoperation", "INSERT");
			
			utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
					null, "INSERT", request);
			
			ledgerdetailid = utility.executeIdGenerationProcedure(obj.get("orgid").getAsString(),
					obj.get("oprid").getAsString(), "ledgerdetail",
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			ledgerdetailid = new JSONObject(ledgerdetailid).get("id").toString();
			
			ledgerdtl.addProperty("ledgerdetailid", ledgerdetailid);
			ledgerdtl.addProperty("cramt", RestUtil.getAsBigDecimal(obj.get("adjustamt")));
			ledgerdtl.addProperty("dramt", 0);
			
			utility.executeCustomDML("ledgerdetail", "ledgerdetailid", ledgerdtl,
					null, "INSERT", request);
		}
		
		// Update outstanding
		query = "SELECT outstandingid, receivedamt, balanceamount FROM outstanding where transactionid = " + invoiceid;
		JsonArray outstandings = utility.executeQueryOnPool(query, request);
		JsonObject outstanding = new JsonObject();
		for (JsonElement element : outstandings) {
			outstanding = element.getAsJsonObject();
			
			if (outstandingData.has(outstanding.get("outstandingid").getAsString())) {
				BigDecimal receivedamt = outstandingData.get(outstanding.get("outstandingid").getAsString()).getAsBigDecimal();
				outstanding.addProperty("receivedamt", receivedamt);
				outstanding.addProperty("balanceamount", RestUtil.getAsBigDecimal(outstanding.get("balanceamount")).subtract(receivedamt));
				outstanding.addProperty("modifiedon", new Timestamp(System.currentTimeMillis()).toString());
				outstanding.addProperty("modifiedby", adUserAccessToken.get("adusermastid"));
				outstanding.addProperty("lastoperation", "UPDATE");
				
				utility.executeCustomDML("outstanding", "outstandingid", outstanding,
						null, "UPDATE", request);
			}
		}
		
		return "Success";
	}
	
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
	
	public int postUpdateTcsAmt(TrnMasterBody body, HttpServletRequest request) {
		String whereClause = "", query = "";
		 query = "UPDATE accountoprdetail\r\n" + "SET tcsnetamt = ifnull(tcsnetamt, 0) + ?"
				+ " where  orgid = ?" + " AND oprid = ?" + " AND accountid = ?" 
				+ " " + whereClause;
		Object[] params = new Object[] { body.getTransactionmaster().getTcsamount(),
				body.getTransactionmaster().getOrgid(), body.getTransactionmaster().getOprid(), body.getTransactionmaster().getAccountid()};
		return utility.executeDMLQueryOnPool(query, params, request);

		 
	}
	
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
					
					updateTransactionStock(query, params, request);

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
					
					updateTransactionStock(query, params, request);

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
					
					updateTransactionStock(query, params, request);
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
	
	@Transactional
	public String postCommonTransactionWithoutStock(TrnMasterBody body,
			HttpServletRequest request) throws JsonProcessingException {
		long result = 0;
		String transactionmasterid = "";
		JsonArray transactionDetail = new JsonArray();
		JsonObject response = new JsonObject();
		
		switch (body.getTransactionmaster().getLastoperation()) {

		case "INSERT":
			JsonObject transactionmaster = formater.serializeObject(body.getTransactionmaster());
			JsonArray detailArray = transactionmaster.getAsJsonArray("transactiondetail").deepCopy();
			transactionmaster.remove("transactiondetail");
			
			transactionmasterid = utility.executeIdGenerationProcedure(transactionmaster.get("orgid").getAsString(),
					transactionmaster.get("oprid").getAsString(), request.getHeader("rightId"),
					request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
			transactionmasterid = new JSONObject(transactionmasterid).getString("id");
			
			transactionmaster.addProperty("transactionmasterid", transactionmasterid);
			
			result = utility.executeCustomDML("transactionmaster", "transactionmasterid", transactionmaster, null, 
					transactionmaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement element : detailArray) {
				JsonObject transactiondetail = element.getAsJsonObject();
				
				String transactiondetailid = utility.executeIdGenerationProcedure(transactiondetail.get("orgid").getAsString(),
						transactiondetail.get("oprid").getAsString(), request.getHeader("rightId"),
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				transactiondetailid = new JSONObject(transactiondetailid).getString("id");
				
				transactiondetail.addProperty("transactiondetailid", transactiondetailid);
				
				utility.executeCustomDML("transactiondetail", "transactiondetailid", transactiondetail, null, 
						transactiondetail.get("lastoperation").getAsString(), request);
			}

			break;

		case "UPDATE":
			for (TransactiondetailBody transactionDetailBody : body.getTransactionmaster().getTransactiondetail()) {
				transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
				switch (transactionDetailBody.getLastoperation()) {
				case "INSERT":
					result = postCommonTransactionWithoutStockItemInsert(transactionDetailBody, false, request);
					break;
				case "UPDATE":
					result = postCommonTransactionWithoutStockItemUpdate(transactionDetailBody,
							request);
					break;
				case "DELETE":
					result = postCommonTransactionWithoutStockItemDelete(transactionDetailBody,
							request);
					break;
				}
			}

			String query = "select count(*)countdet from transactiondetail where  transactionmasterid ="
					+ body.getTransactionmaster().getTransactionmasterid() + " and orgid ="
					+ body.getTransactionmaster().getOrgid() + " and oprid = "
					+ body.getTransactionmaster().getOprid();
			JsonArray countdetail = utility.executeQueryOnPool(query, request);
			if (countdetail.size() > 0) {
				int count = Integer.parseInt(countdetail.get(0).getAsJsonObject().get("countdet").toString());
				if (count == 0) {
					deleteTransactionMasterDetail(
							body.getTransactionmaster().getTransactionmasterid().toString(), request);
				}
			}
			break;

		case "ESCAPE":
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			result = postCommonTransactionWithoutStockEscape(body, request);
			break;

		case "FINALSAVE":
			transactionmasterid = body.getTransactionmaster().getTransactionmasterid().toString();
			
			int voucherno = postCommonTransactionFinalSave(body, request);
			if (voucherno > 0 && voucherno != 0)
				result = voucherno;
			break;

		case "CANCEL":
			cancelGstCreditnote(body, request);
			break;
		}
		
		if (result >= 0) {
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("message", "Record Saved Successfully");
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
			} else if (body.getTransactionmaster().getVouchertype().equals("CN") || body.getTransactionmaster().getVouchertype().equals("SDCN") || 
					body.getTransactionmaster().getVouchertype().equals("DN") || body.getTransactionmaster().getVouchertype().equals("CCD")) {
				int voucherNoInt = body.getTransactionmaster().getVoucherno();
				transactionDetail = getTransactionDetail(transactionmasterid, voucherNoInt, request);
				response.add("data", transactionDetail);
			} else {
				int voucherNoInt = body.getTransactionmaster().getVoucherno();
				transactionDetail = getCommonTransactionResponse(transactionmasterid, voucherNoInt,
						request);
				response.add("data", transactionDetail);
			}
		} else {
			return "Failed";
		}
		
		return response.toString();
	}
	
	public String cancelGstCreditnote(TrnMasterBody body, HttpServletRequest request) {
		// Select from transaction detail
		JsonObject jsonObjectResponse = new JsonObject();
		String query = "select transactiondetailid , batchid ,  qty , free , specialqty , productpack  from transactiondetail where orgid ="
				+ body.getTransactionmaster().getOrgid() + " and oprid =" + body.getTransactionmaster().getOprid()
				+ " and transactionmasterid = " + body.getTransactionmaster().getTransactionmasterid()
				+ "  and active  = 1";
		JsonArray data = utility.executeQueryOnPool(query, request);

		if (data.size() > 0) {
			for (JsonElement jsonElement : data.getAsJsonArray()) {
				// Revert logic
				JsonObject obj = jsonElement.getAsJsonObject();
				query = "update transactionstock set closing = closing + (? + ? + ?) where orgid = ? and oprid = ? and batchid = ?";
				Object[] params = new Object[] { 
						obj.get("qty").getAsLong(),
						obj.get("free").getAsLong(),
						obj.get("specialqty").getAsLong(),
						body.getTransactionmaster().getOrgid(), 
						body.getTransactionmaster().getOprid(),
						obj.get("batchid").getAsString() 
				};
				
				updateTransactionStock(query, params, request);
			}
			query = "update transactiondetail set active =? where orgid =? and oprid = ?  and transactionmasterid = ?";
			Object[] params = new Object[] {0,body.getTransactionmaster().getOrgid() , body.getTransactionmaster().getOprid(), body.getTransactionmaster().getTransactionmasterid() };
			utility.executeDMLQueryOnPool(query, params, request);
			
			query = "update transactionmaster set canflag =? , active =?  where orgid =? and oprid =?  and  transactionmasterid =?";
			params = new Object[] {1,0,body.getTransactionmaster().getOrgid() , body.getTransactionmaster().getOprid(), body.getTransactionmaster().getTransactionmasterid() };
			utility.executeDMLQueryOnPool(query, params, request);
			
			jsonObjectResponse.addProperty("code", 200);
			jsonObjectResponse.addProperty("status", "Success");
			jsonObjectResponse.addProperty("message", "Record Cancel Successfully ");
			jsonObjectResponse.addProperty("id", body.getTransactionmaster().getTransactionmasterid());
			
		}

		return jsonObjectResponse.toString();
	}
	
	public JsonArray getTransactionDetail(String transactionmasterid, int voucherNoInt,
			HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String whereClause = "";
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		if (voucherNoInt != 0) {
			whereClause = " and active = 1";
		}
		String query = "select *,(CASE\r\n" + 
				"				  WHEN gsttp = 0 THEN 'Exempted' \r\n" + 
				"			        WHEN gsttp = 1 THEN 'Taxable' \r\n" + 
				"			         WHEN gsttp = 2 THEN 'Nil Rated' \r\n" + 
				"			          WHEN gsttp = 3 THEN ' Zero Rated' \r\n" + 
				"			         WHEN gsttp = 4 THEN 'Non Gst' \r\n" + 
				"				        WHEN gsttp = 5 THEN 'Direct' \r\n" + 
				"				 END)gsttypedesc from  transactiondetail where orgid =" + adUserAccessToken.get("orgid")
				+ " and oprid=" + adUserAccessToken.get("oprid") + " and transactionmasterid ="
				+ transactionmasterid + whereClause;

		return utility.executeQueryOnPool(query, request);
	}
}
