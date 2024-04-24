package com.micropro.common.pharmazip.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class QueryApiModel {
	
	@JsonInclude(Include.ALWAYS)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "A request body model used in POST requests of Query Api")
    public static final class QueryApiBody {
        
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM1' column") String param1;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM2' column") String param2;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM3' column") String param3;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM4' column") String param4;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM5' column") String param5;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM6' column") String param6;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM7' column") String param7;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM8' column") String param8;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM9' column") String param9;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PARAM10' column") String param10;
        private @ApiModelProperty(required = false, value = "REST representation of the 'UNCOATPARAM1' column") String uncoatparam1;
        private @ApiModelProperty(required = false, value = "REST representation of the 'UNCOATPARAM2' column") String uncoatparam2;
        private @ApiModelProperty(required = false, value = "REST representation of the 'UNCOATPARAM3' column") String uncoatparam3;
        private @ApiModelProperty(required = false, value = "REST representation of the 'UNCOATPARAM4' column") String uncoatparam4;
        private @ApiModelProperty(required = false, value = "REST representation of the 'UNCOATPARAM5' column") String uncoatparam5;
        private @ApiModelProperty(required = false, value = "REST representation of the 'WHEREPARAM' column") String whereparam;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PAGE' column") Long page;
        private @ApiModelProperty(required = false, value = "REST representation of the 'LIMIT' column") Long limit;
        private @ApiModelProperty(required = false, value = "REST representation of the 'LANG' column") String lang;
        private @ApiModelProperty(required = false, value = "REST representation of the 'TOKENORG' column") String tokenorg;
        private @ApiModelProperty(required = false, value = "REST representation of the 'TOKENOPR' column") String tokenopr;
        private @ApiModelProperty(required = false, value = "REST representation of the 'USERID' column") String userid;
        private @ApiModelProperty(required = false, value = "REST representation of the 'OPRLIST' column") String oprlist;
        private @ApiModelProperty(required = false, value = "REST representation of the 'PAGESIZE' column") String pagesize;
        private @ApiModelProperty(required = false, value = "REST representation of the 'ORIENTATION' column") String orientation;
        private @ApiModelProperty(required = false, value = "REST representation of the 'OUTPUT' column") String output;
        
        @JsonCreator
        public QueryApiBody(
                @JsonProperty("param1") String param1,
                @JsonProperty("param2") String param2,
                @JsonProperty("param3") String param3,
                @JsonProperty("param4") String param4,
                @JsonProperty("param5") String param5,
                @JsonProperty("param6") String param6,
                @JsonProperty("param7") String param7,
                @JsonProperty("param8") String param8,
                @JsonProperty("param9") String param9,
                @JsonProperty("param10") String param10,
                @JsonProperty("uncoatparam1") String uncoatparam1,
                @JsonProperty("uncoatparam2") String uncoatparam2,
                @JsonProperty("uncoatparam3") String uncoatparam3,
                @JsonProperty("uncoatparam4") String uncoatparam4,
                @JsonProperty("uncoatparam5") String uncoatparam5,
                @JsonProperty("whereparam") String whereparam,
                @JsonProperty("page") Long page,
                @JsonProperty("limit") Long limit,
                @JsonProperty("lang") String lang,
                @JsonProperty("tokenorg") String tokenorg,
                @JsonProperty("tokenopr") String tokenopr,
                @JsonProperty("userid") String userid,
                @JsonProperty("oprlist") String oprlist,
                @JsonProperty("pagesize") String pagesize,
                @JsonProperty("orientation") String orientation,
                @JsonProperty("output") String output) {
            this.param1 = param1;
            this.param2 = param2;
            this.param3 = param3;
            this.param4 = param4;
            this.param5 = param5;
            this.param6 = param6;
            this.param7 = param7;
            this.param8 = param8;
            this.param9 = param9;
            this.param10 = param10;
            this.uncoatparam1 = uncoatparam1;
            this.uncoatparam2 = uncoatparam2;
            this.uncoatparam3 = uncoatparam3;
            this.uncoatparam4 = uncoatparam4;
            this.uncoatparam5 = uncoatparam5;
            this.whereparam = whereparam;
            this.page = page;
            this.limit = limit;
            this.lang = lang;
            this.tokenorg = tokenorg;
            this.tokenopr = tokenopr;
            this.userid = userid;
            this.oprlist = oprlist;
            this.pagesize = pagesize;
            this.orientation = orientation;
            this.output = output;
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

		public String getParam8() {
			return param8;
		}

		public String getParam9() {
			return param9;
		}

		public String getParam10() {
			return param10;
		}

		public String getUncoatparam1() {
			return uncoatparam1;
		}

		public String getUncoatparam2() {
			return uncoatparam2;
		}

		public String getUncoatparam3() {
			return uncoatparam3;
		}

		public String getUncoatparam4() {
			return uncoatparam4;
		}

		public String getUncoatparam5() {
			return uncoatparam5;
		}

		public String getWhereparam() {
			return whereparam;
		}

		public Long getPage() {
			return page;
		}

		public Long getLimit() {
			return limit;
		}

		public String getLang() {
			return lang;
		}

		public String getTokenorg() {
			return tokenorg;
		}

		public String getTokenopr() {
			return tokenopr;
		}

		public String getUserid() {
			return userid;
		}

		public String getOprlist() {
			return oprlist;
		}

		public String getPagesize() {
			return pagesize;
		}

		public String getOrientation() {
			return orientation;
		}

		public String getOutput() {
			return output;
		}
	}
}
