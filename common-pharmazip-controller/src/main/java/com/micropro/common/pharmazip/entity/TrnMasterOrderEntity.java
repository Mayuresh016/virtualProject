package com.micropro.common.pharmazip.entity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.generated.TrnMasterOrderModel.TrnMasterOrderBody;

@Component
public class TrnMasterOrderEntity {

	private @Autowired ConnectionUtility utility;
	private @Autowired CommonDateFormater formater;

	public String crud(HttpServletRequest request, TrnMasterOrderBody body) throws JsonProcessingException {
		String token = request.getHeader("authorization").split(" ")[1];
		String rightId = request.getHeader("rightId");

		JsonObject obj = formater.serializeObject(body);
		String reqBody = obj.toString();

		return utility.executeProcedureOnPool(rightId, token, reqBody, request.getRequestURL().toString());
	}

	public String get(HttpServletRequest request, long id) {
		JsonObject response = new JsonObject();

		// Root Entity
		String query = "SELECT * FROM transactionordermaster where transactionordermasterid = " + id;

		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			JsonObject transactionordermaster = (JsonObject) data.get(0);

			// Map Entity 1
			query = "SELECT * FROM transactionorderdetail where transactionordermasterid = " + id;
			data = utility.executeQueryOnPool(query, request);

			transactionordermaster.add("transactionorderdetail", data);

			response.add("transactionordermaster", transactionordermaster);
		}

		return response.toString();
	}

}