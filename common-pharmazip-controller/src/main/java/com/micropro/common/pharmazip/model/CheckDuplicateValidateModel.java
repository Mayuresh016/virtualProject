package com.micropro.common.pharmazip.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class CheckDuplicateValidateModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a CustomCheckDuplicateValidate entity")
	public static final class CustomCheckDuplicateValidateBody{
		
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM1_BODY' (Size = 100)") String param1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM2_BODY' (Size = 100)") String param2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM3_BODY' (Size = 100)") String param3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM4_BODY' (Size = 100)") String param4;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM5_BODY' (Size = 100)") String param5;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM6_BODY' (Size = 100)") String param6;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM7_BODY' (Size = 100)") String param7;
		
		
		@JsonCreator
		public CustomCheckDuplicateValidateBody(@JsonProperty("param1") String param1,@JsonProperty("param2") String param2,@JsonProperty("param3") String param3, @JsonProperty("param4") String param4, @JsonProperty("param5") String param5, @JsonProperty("param6") String param6 , @JsonProperty("param7") String param7) {
			this.param1 = param1;
			this.param2 = param2;
			this.param3 = param3;
			this.param4 = param4;
			this.param5 = param5;
			this.param6 = param6;
			
		}

		public String getParam1() {
			return param1;
		}

		public String getParam2() {
			return param2;
		}

		public String getParam3() {
			return param3;
		}

		public String getParam4() {
			return param4;
		}

		public String getParam5() {
			return param5;
		}

		public String getParam6() {
			return param6;
		}

		public String getParam7() {
			return param7;
		}
	
		
	}
}