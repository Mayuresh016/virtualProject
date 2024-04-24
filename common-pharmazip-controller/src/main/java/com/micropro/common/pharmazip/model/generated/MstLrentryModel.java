package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
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

public class MstLrentryModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Lrentry entity")
	public static final class LrentryBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 20)") Long orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 20)") Long oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRENTRYID_BODY' (Primary Key)") Long lrentryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONMASTERID_BODY' (Size = 20)") Long transactionmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERID_BODY' (Size = 20)") Long customerid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTERID_BODY' (Size = 20)") String transporterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRNO_BODY' (Size = 20)") Long lrno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRDATE_BODY'") Date lrdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOOFCASES_BODY' (Size = 20)") Long noofcases;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRAMOUNT_BODY' (Size = 18,4)") BigDecimal lramount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRPAYMENTSTATUS_BODY' (Size = 1)") String lrpaymentstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISTANCEINKM_BODY' (Size = 20)") String distanceinkm;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMOUNT_BODY' (Size = 18,4)") BigDecimal billamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARKS_BODY' (Size = 100)") String remarks;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODEOFTRANSPORT_BODY' (Size = 1)") Integer modeoftransport;

		@JsonCreator
		public LrentryBody(
				@JsonProperty("orgid") Long orgid,
				@JsonProperty("oprid") Long oprid,
				@JsonProperty("lrentryid") Long lrentryid,
				@JsonProperty("transactionmasterid") Long transactionmasterid,
				@JsonProperty("customerid") Long customerid,
				@JsonProperty("billno") String billno,
				@JsonProperty("transporterid") String transporterid,
				@JsonProperty("lrno") Long lrno,
				@JsonProperty("lrdate") @JsonFormat(pattern = "yyyy-MM-dd") Date lrdate,
				@JsonProperty("noofcases") Long noofcases,
				@JsonProperty("lramount") BigDecimal lramount,
				@JsonProperty("lrpaymentstatus") String lrpaymentstatus,
				@JsonProperty("distanceinkm") String distanceinkm,
				@JsonProperty("billamount") BigDecimal billamount,
				@JsonProperty("remarks") String remarks,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("modeoftransport") Integer modeoftransport) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.lrentryid = Objects.requireNonNull(lrentryid, "`lrentryid` is required");
			this.transactionmasterid = transactionmasterid;
			this.customerid = customerid;
			this.billno = billno;
			this.transporterid = transporterid;
			this.lrno = lrno;
			this.lrdate = lrdate;
			this.noofcases = noofcases;
			this.lramount = lramount;
			this.lrpaymentstatus = lrpaymentstatus;
			this.distanceinkm = distanceinkm;
			this.billamount = billamount;
			this.remarks = remarks;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.modeoftransport = modeoftransport;
		}

		public Long getOrgid() {
			return orgid;
		}

		public Long getOprid() {
			return oprid;
		}

		public Long getLrentryid() {
			return lrentryid;
		}

		public Long getTransactionmasterid() {
			return transactionmasterid;
		}

		public Long getCustomerid() {
			return customerid;
		}

		public String getBillno() {
			return billno;
		}

		public String getTransporterid() {
			return transporterid;
		}

		public Long getLrno() {
			return lrno;
		}

		public Date getLrdate() {
			return lrdate;
		}

		public Long getNoofcases() {
			return noofcases;
		}

		public BigDecimal getLramount() {
			return lramount;
		}

		public String getLrpaymentstatus() {
			return lrpaymentstatus;
		}

		public String getDistanceinkm() {
			return distanceinkm;
		}

		public BigDecimal getBillamount() {
			return billamount;
		}

		public String getRemarks() {
			return remarks;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getDescription() {
			return description;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getModeoftransport() {
			return modeoftransport;
		}
	}

}