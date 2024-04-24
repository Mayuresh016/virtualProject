package com.micropro.common.pharmazip.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.common.CommonDateFormater;
import com.micropro.common.pharmazip.common.ConnectionUtility;
import com.micropro.common.pharmazip.model.PurchaseImpsetupModel.PurchaseimportsetupBody;

@Service
public class PurchaseImpsetupService {

	@Autowired
	private ConnectionUtility utility;
	
	private @Autowired CommonDateFormater formater;

	public String getPurchaseImpsetupById(long id, HttpServletRequest request) {
		JsonObject response = new JsonObject();
		
		String query = "select * from purchaseimportsetup where id = '" + id + "'";
		JsonArray data = utility.executeQueryOnPool(query, request);

		if (data.size() > 0) {
			response = (JsonObject) data.get(0);
		}
		return response.toString();
	}

	
	@Transactional
	public long purchaseImpsetup(PurchaseimportsetupBody body, HttpServletRequest request)
			throws JsonProcessingException {
		JsonObject purchaseBodyObject = formater.serializeObject(body);

		long resId = utility.executeCustomDML("purchaseimportsetup", "id", purchaseBodyObject, null, body.getLastoperation(), request);
		
		return resId;
	}

}
