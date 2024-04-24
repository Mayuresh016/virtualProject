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

public class OutstandingMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Outstanding entity")
	public static final class OutstandingBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OUTSTANDINGID_BODY' (Primary Key)") Long outstandingid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONID_BODY' (Size = 20)") Long transactionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTYPE_BODY' (Size = 20)") String doctype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCSERIES_BODY' (Size = 20)") String docseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMT_BODY' (Size = 18,4)") BigDecimal billamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RECEIVEDAMT_BODY' (Size = 18,4)") BigDecimal receivedamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TEMPAMT1_BODY' (Size = 18,4)") BigDecimal tempamt1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TEMPAMT2_BODY' (Size = 18,4)") BigDecimal tempamt2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDISCOUNTAMT_BODY' (Size = 18,4)") BigDecimal billdiscountamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BOOKINGAGENTID_BODY' (Size = 20)") Long bookingagentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEBITNOTENO_BODY' (Size = 20)") String debitnoteno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEBITNOTEDATE_BODY'") Date debitnotedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARRATION_BODY' (Size = 20)") String narration;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CDDAYS_BODY' (Size = 20)") Long cddays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DUEDATE_BODY'") Date duedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLINVOICENO_BODY' (Size = 20)") String billinvoiceno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLINVOICEDATE_BODY'") Date billinvoicedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSPERCENT_BODY' (Size = 18,4)") BigDecimal tcspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSADJUST_BODY' (Size = 18,4)") BigDecimal tcsadjust;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLTYPE_BODY' (Size = 1)") Integer billtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BALANCEAMOUNT_BODY' (Size = 18,4)") BigDecimal balanceamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITAMOUNT_BODY' (Size = 18,4)") BigDecimal creditamount;

		@JsonCreator
		public OutstandingBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("outstandingid") Long outstandingid,
				@JsonProperty("transactionid") Long transactionid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("doctype") String doctype,
				@JsonProperty("docseries") String docseries,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("billamt") BigDecimal billamt,
				@JsonProperty("receivedamt") BigDecimal receivedamt,
				@JsonProperty("tempamt1") BigDecimal tempamt1,
				@JsonProperty("tempamt2") BigDecimal tempamt2,
				@JsonProperty("billdiscountamt") BigDecimal billdiscountamt,
				@JsonProperty("bookingagentid") Long bookingagentid,
				@JsonProperty("debitnoteno") String debitnoteno,
				@JsonProperty("debitnotedate") @JsonFormat(pattern = "yyyy-MM-dd") Date debitnotedate,
				@JsonProperty("narration") String narration,
				@JsonProperty("cddays") Long cddays,
				@JsonProperty("duedate") @JsonFormat(pattern = "yyyy-MM-dd") Date duedate,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("billinvoiceno") String billinvoiceno,
				@JsonProperty("billinvoicedate") @JsonFormat(pattern = "yyyy-MM-dd") Date billinvoicedate,
				@JsonProperty("tcspercent") BigDecimal tcspercent,
				@JsonProperty("tcsadjust") BigDecimal tcsadjust,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("billtype") Integer billtype,
				@JsonProperty("balanceamount") BigDecimal balanceamount,
				@JsonProperty("creditamount") BigDecimal creditamount) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.outstandingid = Objects.requireNonNull(outstandingid, "`outstandingid` is required");
			this.transactionid = transactionid;
			this.accountid = accountid;
			this.identity = identity;
			this.doctype = doctype;
			this.docseries = docseries;
			this.billno = billno;
			this.billdate = billdate;
			this.billamt = billamt;
			this.receivedamt = receivedamt;
			this.tempamt1 = tempamt1;
			this.tempamt2 = tempamt2;
			this.billdiscountamt = billdiscountamt;
			this.bookingagentid = bookingagentid;
			this.debitnoteno = debitnoteno;
			this.debitnotedate = debitnotedate;
			this.narration = narration;
			this.cddays = cddays;
			this.duedate = duedate;
			this.agencyid = agencyid;
			this.billinvoiceno = billinvoiceno;
			this.billinvoicedate = billinvoicedate;
			this.tcspercent = tcspercent;
			this.tcsadjust = tcsadjust;
			this.lastoperation = lastoperation;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.billtype = billtype;
			this.balanceamount = balanceamount;
			this.creditamount = creditamount;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getOutstandingid() {
			return outstandingid;
		}

		public Long getTransactionid() {
			return transactionid;
		}

		public Long getAccountid() {
			return accountid;
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

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public BigDecimal getBillamt() {
			return billamt;
		}

		public BigDecimal getReceivedamt() {
			return receivedamt;
		}

		public BigDecimal getTempamt1() {
			return tempamt1;
		}

		public BigDecimal getTempamt2() {
			return tempamt2;
		}

		public BigDecimal getBilldiscountamt() {
			return billdiscountamt;
		}

		public Long getBookingagentid() {
			return bookingagentid;
		}

		public String getDebitnoteno() {
			return debitnoteno;
		}

		public Date getDebitnotedate() {
			return debitnotedate;
		}

		public String getNarration() {
			return narration;
		}

		public Long getCddays() {
			return cddays;
		}

		public Date getDuedate() {
			return duedate;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public String getBillinvoiceno() {
			return billinvoiceno;
		}

		public Date getBillinvoicedate() {
			return billinvoicedate;
		}

		public BigDecimal getTcspercent() {
			return tcspercent;
		}

		public BigDecimal getTcsadjust() {
			return tcsadjust;
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

		public Integer getBilltype() {
			return billtype;
		}

		public BigDecimal getBalanceamount() {
			return balanceamount;
		}

		public BigDecimal getCreditamount() {
			return creditamount;
		}
	}

}