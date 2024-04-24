package com.micropro.common.pharmazip.model.generated;

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

public class InvoicepickerlinkMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Invoicepickerlink entity")
	public static final class InvoicepickerlinkBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVOICEPICKERLINKID_BODY' (Primary Key)") Long invoicepickerlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 20)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 20)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") Long voucherno;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PICKERID_BODY' (required) (Size = 20)") Long pickerid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONID_BODY' (Size = 20)") Long locationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATUS_BODY' (Size = 1)") Integer status;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;

		@JsonCreator
		public InvoicepickerlinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("invoicepickerlinkid") Long invoicepickerlinkid,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") Long voucherno,
				@JsonProperty("pickerid") Long pickerid,
				@JsonProperty("locationid") Long locationid,
				@JsonProperty("status") Integer status,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.invoicepickerlinkid = Objects.requireNonNull(invoicepickerlinkid, "`invoicepickerlinkid` is required");
			this.vouchertype = vouchertype;
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.pickerid = Objects.requireNonNull(pickerid, "`pickerid` is required");
			this.locationid = locationid;
			this.status = status;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = createdby;
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.accountid = accountid;
			this.lastoperation = lastoperation;
			this.active = Objects.requireNonNull(active, "`active` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getInvoicepickerlinkid() {
			return invoicepickerlinkid;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public Long getVoucherno() {
			return voucherno;
		}

		public Long getPickerid() {
			return pickerid;
		}

		public Long getLocationid() {
			return locationid;
		}

		public Integer getStatus() {
			return status;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public Long getModifiedby() {
			return modifiedby;
		}

		public Long getAccountid() {
			return accountid;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getActive() {
			return active;
		}
	}

}