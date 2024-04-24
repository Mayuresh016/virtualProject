package com.micropro.common.pharmazip.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class OrderConversionModel {
	
	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to convert any order by ids")
	public static final class OrderConversionBody{
		
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDS' (TEXT)") String ids;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE'") String voucherdate;
		
		
		@JsonCreator
		public OrderConversionBody(
				@JsonProperty("ids") String ids,
				@JsonProperty("voucherdate") String voucherdate) {
			this.ids = ids;
			this.voucherdate = voucherdate;
		}

		public String getIds() {
			return ids;
		}

		public String getVoucherdate() {
			return voucherdate;
		}
		
	}

}
