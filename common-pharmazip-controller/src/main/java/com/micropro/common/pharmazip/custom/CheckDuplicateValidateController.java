package com.micropro.common.pharmazip.custom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.config.RedisManager;
import com.micropro.common.pharmazip.model.CheckDuplicateValidateModel.CustomCheckDuplicateValidateBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = ".", tags = { "CheckDuplicateValidateController APIs" })
@RequestMapping("/rest")
public class CheckDuplicateValidateController {

	@Autowired
	private ConnectionUtility utility;

	Map<String, String> errormsg; // <Field, Error_msg>
	HttpServletRequest hRequest;

	@Autowired
	private RedisManager redisManager;

	/*********************************************************************************
	 * / 
	 * Author : Aayush Dahake 
	 * Date : 21/12/2021 
	 * Input : Right Id and parameters
	 * Output : Status and Message Purpose : Common Check Duplicate functionality
	 * Comment : Consignment master in masters
	 ********************************************************************************/
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/postCommonCheckDuplicate", consumes = "application/json")
	@ApiOperation(value = "CRUD operation on Check duplicate entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if a CustomCheckDuplicateValidateBody entity has been created successfully")
	public String postCommonCheckDuplicate(@RequestBody @Validated CustomCheckDuplicateValidateBody body,
			HttpServletRequest request) {
		hRequest = request;
		String response = "";
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		Map<String, String> adUserAccessToken = redisManager.checkToken(token);
		JsonObject jsonObject = new JsonObject();
		JsonArray data = new JsonArray();
		if (request.getHeader("rightId") == null || request.getHeader("rightId").equals("0")) {
			response = "Error";
			errormsg = new HashMap<>();
			errormsg.put("rightId", "'rightId' required in request header");
		} else {

			switch (request.getHeader("rightId")) {

			// Category master
			case "OB21000416":
				// Check if category name exist in rate category
				String query = "SELECT ratecategoryid internalid, ratecategoryname ratecategoryname\r\n"
						+ "FROM ratecategory\r\n" + "WHERE ratecategory.orgid = " + adUserAccessToken.get("orgid")
						+ " AND oprid = " + adUserAccessToken.get("oprid") + "  AND UPPER(ratecategoryname) =UPPER('"
						+ body.getParam1() + "')";
				data = utility.executeQueryOnPool(query, request);
				// Message as per discussion with Swapnil dhorey and Arshad Rangoonwala
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message",
							"Duplicate entry found for  category " + body.getParam1().toUpperCase());

				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}

				break;

			// Tax master
			case "OB21000392":
				// Query to check the combination of tax and tax description
				query = "SELECT taxid, taxdescription\r\n" + "FROM tax\r\n" + "WHERE orgid ="
						+ adUserAccessToken.get("orgid") + " AND oprid = " + adUserAccessToken.get("oprid")
						+ " AND UPPER(taxdescription) =UPPER('" + body.getParam1() + "')  AND taxvalue = "
						+ body.getParam2();
				data = utility.executeQueryOnPool(query, request);
				// Message as per discussion with Praful Bhange
				jsonObject = new JsonObject();
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found for " + body.getParam1());

				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");

				}
				break;

			// Product master
			case "OB21000389":
				// Query to check the combination of Product Name and Display packing
				query = "SELECT productname, displaypacking\r\n" + "FROM product\r\n" + "WHERE orgid ="
						+ adUserAccessToken.get("orgid") + " AND UPPER(productname) =UPPER('" + body.getParam1()
						+ "')  AND UPPER(displaypacking) =UPPER('" + body.getParam2() + "')";
				data = utility.executeQueryOnPool(query, request);
				// Message as per discussion with Rizwan Qureshi
				jsonObject = new JsonObject();
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found for " + body.getParam1());

				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");

				}
				break;

			// Transaction master
			case "OB21000340":
				// Query to check the combination of Supplier Name and Bill No.
				query = "SELECT customername, billno\r\n" + "FROM transactionmaster\r\n" + "WHERE orgid ="
						+ adUserAccessToken.get("orgid") + " AND UPPER(customername) =UPPER('" + body.getParam1()
						+ "')  AND UPPER(billno) =UPPER('" + body.getParam2() + "')";
				data = utility.executeQueryOnPool(query, request);
				// Message as per discussion with Rizwan Qureshi
				jsonObject = new JsonObject();
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found for " + body.getParam1());

				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");

				}
				break;

			// Agency master
			case "OB21000387":
				// Check if the agency name or short name exist in agency master
				String agencyname = "", shortname = "";
				// Agency name
				if (body.getParam1().equals("blank")) {
					agencyname = "null";
				} else {
					if (!String.valueOf(body.getParam1().charAt(0)).contains("'")) {
						agencyname = "'" + body.getParam1() + "'";
					} else {
						agencyname = body.getParam1();
					}

				}
				// shortname
				if (body.getParam2().equals("blank")) {
					shortname = "null";
				} else {

					if (!String.valueOf(body.getParam2().charAt(0)).contains("'")) {
						shortname = "'" + body.getParam2() + "'";
					} else {
						shortname = body.getParam2();
					}
				}
				// Query to check count for agency name and short name
				query = "select  count(*)count  from agency agency where agency.orgid ="
						+ adUserAccessToken.get("orgid") + " AND agency.oprid =" + adUserAccessToken.get("oprid")
						+ " and  upper(agency.title) = ifnull(upper(" + agencyname + ")"
						+ ",upper(agency.title)) and  upper(agency.shortname) = ifnull(upper(" + shortname
						+ "),upper(agency.shortname)) ";
				data = utility.executeQueryOnPool(query, request);
				jsonObject = new JsonObject();

				// Return Agency name or the short name depending upon the conditions
				if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					if (!(body.getParam1().equals("blank"))) {
						jsonObject.addProperty("message",
								body.getParam1().replace("'", "").toUpperCase() + " already exist");
					} else if (!(body.getParam2().equals("blank"))) {
						jsonObject.addProperty("message",
								body.getParam2().replace("'", "").toUpperCase() + " already exist");
					}
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;
				
				// Agency group master
				case "OB21000388":
					// Check if the agency group name or agency group short name exist in agency group master
					String agencygrpname = "", grpshortname = "";
					// Agency name
					if (body.getParam1().equals("blank")) {
						agencygrpname = "null";
					} else {
						if (!String.valueOf(body.getParam1().charAt(0)).contains("'")) {
							agencygrpname = "'" + body.getParam1() + "'";
						} else {
							agencygrpname = body.getParam1();
						}

					}
					// agency group short name
					if (body.getParam2().equals("blank")) {
						grpshortname = "null";
					} else {

						if (!String.valueOf(body.getParam2().charAt(0)).contains("'")) {
							grpshortname = "'" + body.getParam2() + "'";
						} else {
							grpshortname = body.getParam2();
						}
					}
					// Query to check count for agency group name and short name
					query = "select  count(*)count  from agencygroup agencygroup where agencygroup.orgid ="
							+ adUserAccessToken.get("orgid") + " AND agencygroup.oprid ="
							+ adUserAccessToken.get("oprid") + " AND agencygroup.agencyid =" + body.getParam3()
							+ " and  upper(agencygroup.title) = ifnull(upper(" + agencygrpname + ")"
							+ ",upper(agencygroup.title)) and  upper(agencygroup.shortname) = ifnull(upper("
							+ grpshortname + "),upper(agencygroup.shortname)) ";
					data = utility.executeQueryOnPool(query, request);
					jsonObject = new JsonObject();

					// Return Agency name or the short name depending upon the conditions
					if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
						jsonObject.addProperty("code", 200);
						jsonObject.addProperty("status", "FAILURE");
						if (!(body.getParam1().equals("blank"))) {
							jsonObject.addProperty("message",
									body.getParam1().replace("'", "").toUpperCase() + " already exist");
						} else if (!(body.getParam2().equals("blank"))) {
							jsonObject.addProperty("message",
									body.getParam2().replace("'", "").toUpperCase() + " already exist");
						}
					} else {
						jsonObject.addProperty("code", 200);
						jsonObject.addProperty("status", "Success");
					}
					break;

			case "OB21000391":
				query = "SELECT count(*)count  \r\n" + "FROM hsntaxlink\r\n" + " WHERE hsntaxlink.orgid = "
						+ adUserAccessToken.get("orgid") + " AND hsntaxlink.oprid =" + adUserAccessToken.get("oprid")
						+ " AND hsntaxlink.hsncode ='" + body.getParam1() + "' AND hsntaxlink.active = 1";
				data = utility.executeQueryOnPool(query, request);

				jsonObject = new JsonObject();
				if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					// Message as per praful bhange
					jsonObject.addProperty("message", " Duplicate entry found for this hsn code " + body.getParam1());
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;

			// Check Duplicate on GST No. from accountoprdetl
			case "OB22000006":
				query = "SELECT count(*)count  \r\n" + "FROM accountoprdetail\r\n" + " WHERE accountoprdetail.orgid = "
						+ adUserAccessToken.get("orgid") + " AND accountoprdetail.oprid ="
						+ adUserAccessToken.get("oprid") + " AND accountoprdetail.gstno ='" + body.getParam1()
						+ "' AND accountoprdetail.active = 1";
				data = utility.executeQueryOnPool(query, request);

				jsonObject = new JsonObject();
				if (data.get(0).getAsJsonObject().get("count").getAsInt() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					// Message as per arshad rangoonwala
					jsonObject.addProperty("message", " Duplicate entry found for this GST No. " + body.getParam1());
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;

			// Generic master
			case "OB21000390":
				// Checking if the name exist in content
				query = "SELECT contentid internalid, name name\r\n" + "FROM content\r\n" + "WHERE content.orgid = "
						+ adUserAccessToken.get("orgid") + " AND name ='" + body.getParam1() + "'";
				data = utility.executeQueryOnPool(query, request);

				jsonObject = new JsonObject();

				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found for " + body.getParam1());

				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;

			// Check Duplicate for voucher narration entry
			case "OB22000002":
				query = "SELECT *\r\n" + "FROM commonnarration\r\n" + "WHERE upper(comments) = upper('"
						+ body.getParam1() + "') and  enumvalue=" + body.getParam2();

				data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					// Message as per swapnil dhore added on 2022-01-14 3:58 pm
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;
			// Default case
			case "OB00000148":
				String code = "", name = "";
				query = "";
				if (body.getParam1().length() == 0) {
					code = null;
				} else {
					code = "'" + body.getParam1() + "'";
				}

				if (body.getParam2().length() == 0) {
					name = null;
				} else {
					name = "'" + body.getParam2() + "'";
				}
				switch (body.getParam5()) {
				case "Country":
					query = "SELECT count(*)as areacount\r\n" + "  FROM country\r\n" + " WHERE     orgid = "
							+ adUserAccessToken.get("orgid") + "\n"
							+ "       AND ifnull(upper(countrycode),'XX') = ifnull(upper(" + code
							+ "), ifnull(upper(countrycode),'XX'))\r\n" + "       AND upper(title) = ifnull(upper("
							+ name + "), upper(title))";
					break;

				case "State":
					query = "SELECT count(*)as areacount\r\n" + "  FROM  state\r\n" + " WHERE     orgid = 1\r\n"
							+ "       AND ifnull(upper(stateno),'XX') = ifnull(upper(" + code
							+ "), ifnull(upper(stateno),'XX'))\r\n" + "       AND upper(title) = ifnull(upper(" + name
							+ "), upper(title))\r\n" + "       ";
					break;

				case "City":
					query = "SELECT count(*)as areacount\r\n" + "  FROM   city\r\n" + " WHERE     orgid = "
							+ adUserAccessToken.get("orgid") + "     AND upper(title) = ifnull(upper(" + name
							+ "), upper(title))\r\n" + "     ";
					break;

				case "Area":
					query = "SELECT count(*)as areacount\r\n" + "  FROM   area \r\n" + " WHERE     orgid = "
							+ adUserAccessToken.get("orgid") + "     AND upper(title) = ifnull(upper(" + name
							+ "), upper(title))\r\n" + "     ";
					break;
				}
				data = utility.executeQueryOnPool(query, request);
				if (data.get(0).getAsJsonObject().get("areacount").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");

				}
				break;
			// Check duplicate for route master
			case "OB22000008":
				query = "select count(*)routecount from route where upper(routename) =  upper('" + body.getParam1()
						+ "') and orgid =" + adUserAccessToken.get("orgid") + " and oprid ="
						+ adUserAccessToken.get("oprid");
				data = utility.executeQueryOnPool(query, request);
				if (data.get(0).getAsJsonObject().get("routecount").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}

				break;

			// Check duplicate for invoice series
			case "OB22000010":
				query = "select count(*) countseries from invoiceseries where upper(invoiceseries) = upper('"
						+ body.getParam1() + "') and orgid =" + adUserAccessToken.get("orgid");
				data = utility.executeQueryOnPool(query, request);
				if (data.get(0).getAsJsonObject().get("countseries").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;

			case "OB21000414":
				query = "select count(*)location from location where orgid =" + adUserAccessToken.get("orgid")
						+ " and oprid =" + adUserAccessToken.get("oprid") + " and storeid =" + body.getParam1()
						+ " and upper(title) =upper('" + body.getParam2() + "')";
				data = utility.executeQueryOnPool(query, request);
				if (data.get(0).getAsJsonObject().get("location").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}
				break;
			// Validation for rate discontinue
			case "OB21000342":
				query = "SELECT count(*)stock\r\n" + "FROM transactionstock\r\n"
						+ "     WHERE     transactionstock.orgid = " + adUserAccessToken.get("orgid") + " \r\n"
						+ "      AND transactionstock.oprid = " + adUserAccessToken.get("oprid") + "\r\n"
						+ "      AND transactionstock.productid =" + body.getParam1() + " \r\n"
						+ "      AND transactionstock.rateid = " + body.getParam2() + "\r\n"
						+ "      AND  transactionstock.quantity > 0";
				data = utility.executeQueryOnPool(query, request);
				if (data.get(0).getAsJsonObject().get("stock").getAsInt() != 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Stock available for the rate cannot be discontinued");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}

				break;

			// Check Duplicate for supplier
			case "OB21000386":
				query = "select accountname accountname from account where upper(accountname) = upper('"
						+ body.getParam1() + "') and orgid =" + adUserAccessToken.get("orgid");
				data = utility.executeQueryOnPool(query, request);
				if (data.size() > 0) {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "FAILURE");
					jsonObject.addProperty("message", "Duplicate entry found");
				} else {
					jsonObject.addProperty("code", 200);
					jsonObject.addProperty("status", "Success");
				}

				break;

			default:
				jsonObject.addProperty("code", 200);
				jsonObject.addProperty("status", "FAILURE");
				jsonObject.addProperty("message", "Invalid Right Id");

			}
		}
		response = jsonObject.toString();
		return response;
	}

}
