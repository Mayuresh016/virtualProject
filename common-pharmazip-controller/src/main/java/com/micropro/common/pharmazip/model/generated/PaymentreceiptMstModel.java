package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
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

public class PaymentreceiptMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Paymentreceipt entity")
	public static final class PaymentreceiptBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTRECEIPTID_BODY' (Primary Key)") Long paymentreceiptid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OUTSTANDINGREFID_BODY' (required) (Size = 10)") Long outstandingrefid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARTYCODE_BODY' (Size = 10)") Long partycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTYPE_BODY' (Size = 5)") String doctype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCSERIES_BODY' (Size = 5)") String docseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 5)") Integer billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJUSTAMT_BODY' (Size = 18,4)") BigDecimal adjustamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRTYPE_BODY' (Size = 1)") String prtype;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Date createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Date modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LEDGERMASTERID_BODY' (Size = 20)") Long ledgermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TNEW_BODY' (Size = 1)") Integer tnew;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONID_BODY' (Size = 20)") Long transactionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 10)") Integer voucherno;

		@JsonCreator
		public PaymentreceiptBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("paymentreceiptid") Long paymentreceiptid,
				@JsonProperty("outstandingrefid") Long outstandingrefid,
				@JsonProperty("partycode") Long partycode,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("doctype") String doctype,
				@JsonProperty("docseries") String docseries,
				@JsonProperty("billno") Integer billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("adjustamt") BigDecimal adjustamt,
				@JsonProperty("prtype") String prtype,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd") Date createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd") Date modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("ledgermasterid") Long ledgermasterid,
				@JsonProperty("active") Integer active,
				@JsonProperty("tnew") Integer tnew,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("transactionid") Long transactionid,
				@JsonProperty("voucherno") Integer voucherno) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.paymentreceiptid = Objects.requireNonNull(paymentreceiptid, "`paymentreceiptid` is required");
			this.outstandingrefid = Objects.requireNonNull(outstandingrefid, "`outstandingrefid` is required");
			this.partycode = partycode;
			this.identity = identity;
			this.doctype = doctype;
			this.docseries = docseries;
			this.billno = billno;
			this.billdate = billdate;
			this.adjustamt = adjustamt;
			this.prtype = prtype;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
			this.ledgermasterid = ledgermasterid;
			this.active = active;
			this.tnew = tnew;
			this.vouchermode = vouchermode;
			this.transactionid = transactionid;
			this.voucherno = voucherno;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getPaymentreceiptid() {
			return paymentreceiptid;
		}

		public Long getOutstandingrefid() {
			return outstandingrefid;
		}

		public Long getPartycode() {
			return partycode;
		}

		public Integer getIdentity() {
			return identity;
		}

		public String getDoctype() {
			return doctype;
		}

		public String getDocseries() {
			return docseries;
		}

		public Integer getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public BigDecimal getAdjustamt() {
			return adjustamt;
		}

		public String getPrtype() {
			return prtype;
		}

		public Date getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Date getModifiedon() {
			return modifiedon;
		}

		public Long getModifiedby() {
			return modifiedby;
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

		public Long getLedgermasterid() {
			return ledgermasterid;
		}

		public Integer getActive() {
			return active;
		}

		public Integer getTnew() {
			return tnew;
		}

		public Integer getVouchermode() {
			return vouchermode;
		}

		public Long getTransactionid() {
			return transactionid;
		}

		public Integer getVoucherno() {
			return voucherno;
		}
	}

}