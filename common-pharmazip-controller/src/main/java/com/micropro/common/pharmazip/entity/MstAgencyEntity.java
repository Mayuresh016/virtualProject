package com.micropro.common.pharmazip.entity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.generated.MstAgencyModel.AgencyBody;

@Component
public class MstAgencyEntity {

	private @Autowired ConnectionUtility utility;
	private @Autowired CommonDateFormater formater;

	public String crud(HttpServletRequest request, AgencyBody body) throws JsonProcessingException {
		String token = request.getHeader("authorization").split(" ")[1];
		String rightId = request.getHeader("rightId");

		JsonObject obj = formater.serializeObject(body);
		String reqBody = "{\"agency\":"  + obj.toString() + "}";

		return utility.executeProcedureOnPool(rightId, token, reqBody, request.getRequestURL().toString());
	}

	public String get(HttpServletRequest request, long id) {
		JsonObject response = new JsonObject();

		// Root Entity
		String query = "SELECT * FROM agency where agencyid = " + id;

		JsonArray data = utility.executeQueryOnPool(query, request);
		if(data.size() > 0) {
			response = (JsonObject) data.get(0);
		}

		return response.toString();
	}

}