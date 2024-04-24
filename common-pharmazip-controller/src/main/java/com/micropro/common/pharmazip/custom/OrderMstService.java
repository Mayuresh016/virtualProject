package com.micropro.common.pharmazip.custom;

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
import com.micropro.common.pharmazip.model.OrderMstModel.OrderMstBody;

@Service
public class OrderMstService {

	private @Autowired ConnectionUtility utility;
	private @Autowired CommonDateFormater formater;

	public String crud(HttpServletRequest request, OrderMstBody body) throws JsonProcessingException {
		String token = request.getHeader("authorization").split(" ")[1];
		String rightId = request.getHeader("rightId");

		JsonObject obj = formater.serializeObject(body);
		String reqBody = obj.toString();

		return utility.executeProcedureOnPool(rightId, token, reqBody, request.getRequestURL().toString());
	}
	
	@Transactional
	public String saveList(HttpServletRequest request, List<OrderMstBody> body) throws JsonProcessingException {
		JsonArray orderMstList = formater.serializeArray(body);
		String ordermasterid = "0";
		
		for (JsonElement element : orderMstList) {
			JsonObject obj = element.getAsJsonObject();
			JsonObject ordermaster = obj.getAsJsonObject("ordermaster");
			JsonArray dtlList = ordermaster.getAsJsonArray("orderdetail").deepCopy();
			ordermaster.remove("orderdetail");
			
			if (ordermaster.get("lastoperation").getAsString().equals("INSERT")) {
				ordermasterid = utility.executeIdGenerationProcedure(ordermaster.get("orgid").getAsString(),
						ordermaster.get("oprid").getAsString(), "ordermaster",
						request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
				ordermasterid = new JSONObject(ordermasterid).get("id").toString();
				
				ordermaster.addProperty("ordermasterid", ordermasterid);
			}
			
			utility.executeCustomDML("ordermaster", "ordermasterid", ordermaster,
					null, ordermaster.get("lastoperation").getAsString(), request);
			
			for (JsonElement dtl : dtlList) {
				JsonObject orderdetail = dtl.getAsJsonObject();
				
				if (orderdetail.get("lastoperation").getAsString().equals("INSERT")) {
					String orderdetailid = utility.executeIdGenerationProcedure(orderdetail.get("orgid").getAsString(),
							orderdetail.get("oprid").getAsString(), "orderdetail",
							request.getHeader("authorization").split(" ")[1], request.getRequestURL().toString());
					orderdetailid = new JSONObject(orderdetailid).get("id").toString();
					
					orderdetail.addProperty("ordermasterid", ordermasterid);
					orderdetail.addProperty("orderdetailid", orderdetailid);
				}
				
				utility.executeCustomDML("orderdetail", "orderdetailid", orderdetail,
						null, orderdetail.get("lastoperation").getAsString(), request);
			}
		}

		return "Success";
	}

	public String get(HttpServletRequest request, long id) {
		JsonObject response = new JsonObject();

		// Root Entity
		String query = "SELECT * FROM ordermaster where ordermasterid = " + id;

		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			JsonObject ordermaster = (JsonObject) data.get(0);

			// Map Entity 1
			query = "SELECT * FROM orderdetail where ordermasterid = " + id;
			data = utility.executeQueryOnPool(query, request);

			ordermaster.add("orderdetail", data);

			response.add("ordermaster", ordermaster);
		}

		return response.toString();
	}

}