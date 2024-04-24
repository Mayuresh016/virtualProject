package com.micropro.common.pharmazip.model.generated;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class MstRatecategoryModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Ratecategory entity")
	public static final class RatecategoryBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECATEGORYID_BODY' (Primary Key)") Long ratecategoryid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'RATECATEGORYNAME_BODY' (required) (Size = 50)") String ratecategoryname;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'DEFAULTRATE_BODY' (required) (Size = 1)") Integer defaultrate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 50)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INCREMENTPERCENT_BODY' (Size = 18,4)") BigDecimal incrementpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMISSIONPERCENT_BODY' (Size = 18,4)") BigDecimal commissionpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal discountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISSUBSTOCKIEST_BODY' (Size = 1)") Integer issubstockiest;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DECREMENTPERCENT_BODY' (Size = 18,4)") BigDecimal decrementpercent;

		@JsonCreator
		public RatecategoryBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("ratecategoryid") Long ratecategoryid,
				@JsonProperty("ratecategoryname") String ratecategoryname,
				@JsonProperty("defaultrate") Integer defaultrate,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("incrementpercent") BigDecimal incrementpercent,
				@JsonProperty("commissionpercent") BigDecimal commissionpercent,
				@JsonProperty("discountpercent") BigDecimal discountpercent,
				@JsonProperty("issubstockiest") Integer issubstockiest,
				@JsonProperty("decrementpercent") BigDecimal decrementpercent) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.ratecategoryid = Objects.requireNonNull(ratecategoryid, "`ratecategoryid` is required");
			this.ratecategoryname = Objects.requireNonNull(ratecategoryname, "`ratecategoryname` is required");
			this.defaultrate = Objects.requireNonNull(defaultrate, "`defaultrate` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.incrementpercent = incrementpercent;
			this.commissionpercent = commissionpercent;
			this.discountpercent = discountpercent;
			this.issubstockiest = issubstockiest;
			this.decrementpercent = decrementpercent;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getRatecategoryid() {
			return ratecategoryid;
		}

		public String getRatecategoryname() {
			return ratecategoryname;
		}

		public Integer getDefaultrate() {
			return defaultrate;
		}

		public Integer getActive() {
			return active;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public BigDecimal getIncrementpercent() {
			return incrementpercent;
		}

		public BigDecimal getCommissionpercent() {
			return commissionpercent;
		}

		public BigDecimal getDiscountpercent() {
			return discountpercent;
		}

		public Integer getIssubstockiest() {
			return issubstockiest;
		}

		public BigDecimal getDecrementpercent() {
			return decrementpercent;
		}
	}

}