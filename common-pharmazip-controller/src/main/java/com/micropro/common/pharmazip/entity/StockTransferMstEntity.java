package com.micropro.common.pharmazip.entity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.generated.StockTransferMstModel.StockTransferMstBody;

@Component
public class StockTransferMstEntity {

	private @Autowired ConnectionUtility utility;
	private @Autowired CommonDateFormater formater;

	public String crud(HttpServletRequest request, StockTransferMstBody body) throws JsonProcessingException {
		String token = request.getHeader("authorization").split(" ")[1];
		String rightId = request.getHeader("rightId");

		JsonObject obj = formater.serializeObject(body);
		String reqBody = obj.toString();

		return utility.executeProcedureOnPool(rightId, token, reqBody, request.getRequestURL().toString());
	}

	public String get(HttpServletRequest request, long id) {
		JsonObject response = new JsonObject();

		// Root Entity
		String query = "SELECT * FROM stocktransfermaster where stocktransfermasterid = " + id;

		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			JsonObject stocktransfermaster = (JsonObject) data.get(0);

			// Map Entity 1
			query = "SELECT * FROM stocktransferdetail where stocktransfermasterid = " + id;
			data = utility.executeQueryOnPool(query, request);

			stocktransfermaster.add("stocktransferdetail", data);

			response.add("stocktransfermaster", stocktransfermaster);
		}

		return response.toString();
	}

}