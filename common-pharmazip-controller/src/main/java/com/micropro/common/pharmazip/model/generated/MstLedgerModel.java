package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class MstLedgerModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstLedger entity")
	public static final class MstLedgerBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'LEDGERMASTER_BODY' (required)") LedgermasterBody ledgermaster;

		@JsonCreator
		public MstLedgerBody(
				@JsonProperty("ledgermaster") LedgermasterBody ledgermaster) {
			this.ledgermaster = Objects.requireNonNull(ledgermaster, "`ledgermaster` is required");
		}

		public LedgermasterBody getLedgermaster() {
			return ledgermaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Ledgermaster entity")
	public static final class LedgermasterBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LEDGERMASTERID_BODY' (Primary Key)") Long ledgermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 250)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 20)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 10)") Integer voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ENTRYTYPE_BODY' (Size = 50)") String entrytype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURPOSE_BODY' (Size = 250)") String purpose;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCEID_BODY' (Size = 20)") Long referenceid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCETEXT_BODY' (Size = 50)") String referencetext;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCODE_BODY' (Size = 1)") Integer companycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALAMOUNT_BODY' (Size = 18,4)") BigDecimal totalamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALDISCOUNT_BODY' (Size = 18,4)") BigDecimal totaldiscount;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Date createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Date modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONID_BODY' (Size = 20)") Long transactionid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LEDGERDETAIL_BODY' (required)") List<LedgerdetailBody> ledgerdetail;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PAYMENTRECEIPT_BODY' (required)") List<PaymentreceiptBody> paymentreceipt;

		@JsonCreator
		public LedgermasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("ledgermasterid") Long ledgermasterid,
				@JsonProperty("title") String title,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") Integer voucherno,
				@JsonProperty("entrytype") String entrytype,
				@JsonProperty("purpose") String purpose,
				@JsonProperty("referenceid") Long referenceid,
				@JsonProperty("referencetext") String referencetext,
				@JsonProperty("companycode") Integer companycode,
				@JsonProperty("totalamount") BigDecimal totalamount,
				@JsonProperty("totaldiscount") BigDecimal totaldiscount,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd") Date createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd") Date modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("transactionid") Long transactionid,
				@JsonProperty("ledgerdetail") List<LedgerdetailBody> ledgerdetail,
				@JsonProperty("paymentreceipt") List<PaymentreceiptBody> paymentreceipt) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.ledgermasterid = Objects.requireNonNull(ledgermasterid, "`ledgermasterid` is required");
			this.title = title;
			this.voucherdate = voucherdate;
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.entrytype = entrytype;
			this.purpose = purpose;
			this.referenceid = referenceid;
			this.referencetext = referencetext;
			this.companycode = companycode;
			this.totalamount = totalamount;
			this.totaldiscount = totaldiscount;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
			this.active = active;
			this.vouchertype = vouchertype;
			this.vouchermode = vouchermode;
			this.transactionid = transactionid;
			this.ledgerdetail = ledgerdetail;
			this.paymentreceipt = paymentreceipt;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getLedgermasterid() {
			return ledgermasterid;
		}

		public String getTitle() {
			return title;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public Integer getVoucherno() {
			return voucherno;
		}

		public String getEntrytype() {
			return entrytype;
		}

		public String getPurpose() {
			return purpose;
		}

		public Long getReferenceid() {
			return referenceid;
		}

		public String getReferencetext() {
			return referencetext;
		}

		public Integer getCompanycode() {
			return companycode;
		}

		public BigDecimal getTotalamount() {
			return totalamount;
		}

		public BigDecimal getTotaldiscount() {
			return totaldiscount;
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

		public Integer getActive() {
			return active;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public Integer getVouchermode() {
			return vouchermode;
		}

		public Long getTransactionid() {
			return transactionid;
		}

		public List<LedgerdetailBody> getLedgerdetail() {
			return ledgerdetail;
		}

		public List<PaymentreceiptBody> getPaymentreceipt() {
			return paymentreceipt;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Ledgerdetail entity")
	public static final class LedgerdetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LEDGERDETAILID_BODY' (Primary Key)") Long ledgerdetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 20)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DRAMT_BODY' (Size = 18,4)") BigDecimal dramt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CRAMT_BODY' (Size = 18,4)") BigDecimal cramt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ANAR_BODY' (Size = 500)") String anar;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUENO_BODY' (Size = 20)") String chequeno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUEDATE_BODY'") Date chequedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REVERSECODE_BODY' (Size = 20)") Long reversecode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLTYPE_BODY' (Size = 1)") Integer billtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLSERIES_BODY' (Size = 20)") String billseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJUSTED_BODY' (Size = 1)") Integer adjusted;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUEPASSDATE_BODY'") Date chequepassdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISCHEQUEBOUNCE_BODY' (Size = 1)") Integer ischequebounce;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUERETURNDATE_BODY'") Date chequereturndate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLINVOICEDATE_BODY'") Date billinvoicedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERTYPE_BODY' (Size = 20)") String returnvouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERSERIES_BODY' (Size = 20)") String returnvoucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERNO_BODY' (Size = 20)") String returnvoucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 20)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LEDGERMASTERID_BODY' (required) (Size = 20)") Long ledgermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TNEW_BODY' (Size = 1)") Integer tnew;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCEID_BODY' (Size = 20)") Long referenceid;

		@JsonCreator
		public LedgerdetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("ledgerdetailid") Long ledgerdetailid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("dramt") BigDecimal dramt,
				@JsonProperty("cramt") BigDecimal cramt,
				@JsonProperty("anar") String anar,
				@JsonProperty("chequeno") String chequeno,
				@JsonProperty("chequedate") @JsonFormat(pattern = "yyyy-MM-dd") Date chequedate,
				@JsonProperty("reversecode") Long reversecode,
				@JsonProperty("billtype") Integer billtype,
				@JsonProperty("billseries") String billseries,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("adjusted") Integer adjusted,
				@JsonProperty("chequepassdate") @JsonFormat(pattern = "yyyy-MM-dd") Date chequepassdate,
				@JsonProperty("ischequebounce") Integer ischequebounce,
				@JsonProperty("chequereturndate") @JsonFormat(pattern = "yyyy-MM-dd") Date chequereturndate,
				@JsonProperty("billinvoicedate") @JsonFormat(pattern = "yyyy-MM-dd") Date billinvoicedate,
				@JsonProperty("returnvouchertype") String returnvouchertype,
				@JsonProperty("returnvoucherseries") String returnvoucherseries,
				@JsonProperty("returnvoucherno") String returnvoucherno,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("ledgermasterid") Long ledgermasterid,
				@JsonProperty("active") Integer active,
				@JsonProperty("tnew") Integer tnew,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("referenceid") Long referenceid) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.ledgerdetailid = Objects.requireNonNull(ledgerdetailid, "`ledgerdetailid` is required");
			this.accountid = accountid;
			this.identity = identity;
			this.vouchertype = vouchertype;
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.voucherdate = voucherdate;
			this.dramt = dramt;
			this.cramt = cramt;
			this.anar = anar;
			this.chequeno = chequeno;
			this.chequedate = chequedate;
			this.reversecode = reversecode;
			this.billtype = billtype;
			this.billseries = billseries;
			this.billno = billno;
			this.billdate = billdate;
			this.adjusted = adjusted;
			this.chequepassdate = chequepassdate;
			this.ischequebounce = ischequebounce;
			this.chequereturndate = chequereturndate;
			this.billinvoicedate = billinvoicedate;
			this.returnvouchertype = returnvouchertype;
			this.returnvoucherseries = returnvoucherseries;
			this.returnvoucherno = returnvoucherno;
			this.agencyid = agencyid;
			this.lastoperation = lastoperation;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.ledgermasterid = Objects.requireNonNull(ledgermasterid, "`ledgermasterid` is required");
			this.active = active;
			this.tnew = tnew;
			this.vouchermode = vouchermode;
			this.referenceid = referenceid;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getLedgerdetailid() {
			return ledgerdetailid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Integer getIdentity() {
			return identity;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public String getVoucherno() {
			return voucherno;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public BigDecimal getDramt() {
			return dramt;
		}

		public BigDecimal getCramt() {
			return cramt;
		}

		public String getAnar() {
			return anar;
		}

		public String getChequeno() {
			return chequeno;
		}

		public Date getChequedate() {
			return chequedate;
		}

		public Long getReversecode() {
			return reversecode;
		}

		public Integer getBilltype() {
			return billtype;
		}

		public String getBillseries() {
			return billseries;
		}

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public Integer getAdjusted() {
			return adjusted;
		}

		public Date getChequepassdate() {
			return chequepassdate;
		}

		public Integer getIschequebounce() {
			return ischequebounce;
		}

		public Date getChequereturndate() {
			return chequereturndate;
		}

		public Date getBillinvoicedate() {
			return billinvoicedate;
		}

		public String getReturnvouchertype() {
			return returnvouchertype;
		}

		public String getReturnvoucherseries() {
			return returnvoucherseries;
		}

		public String getReturnvoucherno() {
			return returnvoucherno;
		}

		public Long getAgencyid() {
			return agencyid;
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

		public Long getReferenceid() {
			return referenceid;
		}
	}

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