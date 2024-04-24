package com.micropro.common.pharmazip.model.generated;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class MstCustomerAgencyDiscountModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Customeragencydiscount entity")
	public static final class CustomeragencydiscountBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERAGENCYDISCOUNTID_BODY' (Primary Key)") Long customeragencydiscountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 20)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYSUBGROUPID_BODY' (Size = 20)") Long agencysubgroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal discountpercent;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 250)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;

		@JsonCreator
		public CustomeragencydiscountBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("customeragencydiscountid") Long customeragencydiscountid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("agencygroupid") Long agencygroupid,
				@JsonProperty("agencysubgroupid") Long agencysubgroupid,
				@JsonProperty("discountpercent") BigDecimal discountpercent,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("active") Integer active) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.customeragencydiscountid = Objects.requireNonNull(customeragencydiscountid, "`customeragencydiscountid` is required");
			this.accountid = accountid;
			this.agencyid = agencyid;
			this.agencygroupid = agencygroupid;
			this.agencysubgroupid = agencysubgroupid;
			this.discountpercent = discountpercent;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.active = Objects.requireNonNull(active, "`active` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getCustomeragencydiscountid() {
			return customeragencydiscountid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public Long getAgencysubgroupid() {
			return agencysubgroupid;
		}

		public BigDecimal getDiscountpercent() {
			return discountpercent;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getModifiedby() {
			return modifiedby;
		}

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public String getLastoperation() {
			return lastoperation;
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

		public Integer getActive() {
			return active;
		}
	}

}