package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Time;
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

public class TrnMasterModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a TrnMaster entity")
	public static final class TrnMasterBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONMASTER_BODY' (required)") TransactionmasterBody transactionmaster;

		@JsonCreator
		public TrnMasterBody(
				@JsonProperty("transactionmaster") TransactionmasterBody transactionmaster) {
			this.transactionmaster = Objects.requireNonNull(transactionmaster, "`transactionmaster` is required");
		}

		public TransactionmasterBody getTransactionmaster() {
			return transactionmaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Transactionmaster entity")
	public static final class TransactionmasterBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONMASTERID_BODY' (Primary Key)") Long transactionmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 10)") Integer voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTMODE_BODY' (Size = 1)") Integer paymentmode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 20)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GROSSAMOUNT_BODY' (Size = 18,4)") BigDecimal grossamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal cashdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTAMOUNT_BODY' (Size = 18,4)") BigDecimal cashdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTDISCOUNTAMOUNT_BODY' (Size = 18,4)") BigDecimal productdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTID_BODY' (Size = 20)") Long agentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NETAMOUNT_BODY' (Size = 18,4)") BigDecimal netamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CANFLAG_BODY' (Size = 1)") Integer canflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEBITNOTEAMOUNT_BODY' (Size = 18,4)") BigDecimal debitnoteamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTEAMOUNT_BODY' (Size = 18,4)") BigDecimal creditnoteamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRINTFLAG_BODY' (Size = 1)") Integer printflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PENDING_BODY' (Size = 18,4)") BigDecimal pending;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDCOMMISSIONPERCENT_BODY' (Size = 18,4)") BigDecimal addcommissionpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VANISSUENO_BODY' (Size = 20)") Long vanissueno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACKINGAMOUNT_BODY' (Size = 18,4)") BigDecimal packingamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMISIONPERCENT_BODY' (Size = 18,4)") BigDecimal commisionpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMISSIONAMOUNT_BODY' (Size = 18,4)") BigDecimal commissionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERADDITIONNARRATION_BODY' (Size = 500)") String otheradditionnarration;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERADDITIONAMOUNT_BODY' (Size = 18,4)") BigDecimal otheradditionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERDEDUCTIONNARRATION_BODY' (Size = 500)") String otherdeductionnarration;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERDEDUCTIONAMOUNT_BODY' (Size = 18,4)") BigDecimal otherdeductionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ROUNDOF_BODY' (Size = 18,4)") BigDecimal roundof;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MEDEUPLOAD_BODY' (Size = 1)") Integer medeupload;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MMAILSEND_BODY' (Size = 1)") Integer mmailsend;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTAMOUNT_BODY' (Size = 18,4)") BigDecimal gstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal igstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal cgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal sgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESSAMOUNT_BODY' (Size = 18,4)") BigDecimal cessamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTNET_BODY' (Size = 18,4)") BigDecimal gstnet;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSCHARGE_BODY' (Size = 18,4)") BigDecimal tcscharge;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSPERCENT_BODY' (Size = 18,4)") BigDecimal tcspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSAMOUNT_BODY' (Size = 18,4)") BigDecimal tcsamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERNAME_BODY' (Size = 50)") String customername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD1_BODY' (Size = 100)") String customeradd1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD2_BODY' (Size = 100)") String customeradd2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD3_BODY' (Size = 100)") String customeradd3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRNO_BODY' (Size = 30)") String lrno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRDATE_BODY'") Date lrdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORT_BODY' (Size = 50)") String transport;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACK_BODY' (Size = 6)") Integer pack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AMTSMS_BODY' (Size = 1)") Integer amtsms;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPATCHSMS_BODY' (Size = 1)") Integer dispatchsms;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERNO_BODY' (Size = 30)") String orderno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDATE_BODY'") Date orderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWBNO_BODY' (Size = 50)") String ewbno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDAYS_BODY' (Size = 6)") Integer creditdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DUEDATE_BODY'") Date duedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUENO_BODY' (Size = 20)") String chequeno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUEDATE_BODY'") Date chequedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNAME_BODY' (Size = 30)") String bankname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD_BODY' (Size = 100)") String bankadd;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKCITY_BODY' (Size = 30)") String bankcity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTINNO_BODY' (Size = 100)") String gstinno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTTYPE_BODY' (Size = 1)") Integer gsttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMCOUNT_BODY' (Size = 6)") Integer itemcount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYCOUNT_BODY' (Size = 6)") Integer qtycount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALEVALUE_BODY' (Size = 18,4)") BigDecimal salevalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXEMPTEDVALUE_BODY' (Size = 18,4)") BigDecimal exemptedvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NILRATEDVALUE_BODY' (Size = 18,4)") BigDecimal nilratedvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NONGSTVALUE_BODY' (Size = 18,4)") BigDecimal nongstvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PHARMARACKUPLOAD_BODY' (Size = 1)") Integer pharmarackupload;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETAILIOUPLOAD_BODY' (Size = 1)") Integer retailioupload;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERCREATEDBY_BODY' (Size = 20)") Long mobileordercreatedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERNO_BODY' (Size = 30)") String mobileorderno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERDATE_BODY'") Date mobileorderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLAIMNO_BODY' (Size = 30)") String claimno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLAIMDATE_BODY'") Date claimdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal totalgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDEBITNARRATION_BODY' (Size = 250)") String creditdebitnarration;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDEBITSTATUS_BODY' (Size = 1)") String creditdebitstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEDIFFDEBITCREDITNOTEFLAG_BODY' (Size = 1)") Integer ratediffdebitcreditnoteflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'POST_BODY' (Size = 1)") Integer post;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTDEBITNOTEFLAG_BODY' (Size = 1)") Integer gstdebitnoteflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRADJ_BODY' (Size = 1)") Integer mradj;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRPVALUE_BODY' (Size = 18,4)") BigDecimal mrpvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REPLACEMENTAMOUNT_BODY' (Size = 18,4)") BigDecimal replacementamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REPLACMENTCLOSE_BODY' (Size = 1)") Integer replacmentclose;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CDP_BODY' (Size = 1)") Integer cdp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTEMESS_BODY' (Size = 100)") String creditnotemess;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTEFLAG_BODY' (Size = 1)") Integer creditnoteflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTETYPE_BODY' (Size = 1)") Integer creditnotetype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTADJ_BODY' (Size = 1)") Integer gstadj;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJUSTINVOICENO_BODY' (Size = 20)") String adjustinvoiceno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJUSTINVOICESERIES_BODY' (Size = 20)") String adjustinvoiceseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJUSTINVOICEDATE_BODY'") Date adjustinvoicedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTDEBITNOTE_BODY' (Size = 1)") Integer gstdebitnote;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSPERCENT_BODY' (Size = 18,4)") BigDecimal tdspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSAMOUNT_BODY' (Size = 18,4)") BigDecimal tdsamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSCHARGE_BODY' (Size = 18,4)") BigDecimal tdscharge;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJCREDITNOTENO_BODY' (Size = 20)") String adjcreditnoteno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJCREDITNOTEDATE_BODY'") Date adjcreditnotedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCEID_BODY' (Size = 20)") Long referenceid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REPLACEMENTCRNOTENO_BODY' (Size = 20)") String replacementcrnoteno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REPLACEMENTCRNOTEDATE_BODY'") Date replacementcrnotedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALDISCOUNTAMT_BODY' (Size = 18,4)") BigDecimal specialdiscountamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REASON_BODY' (Size = 50)") String reason;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal ugstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMOUNT_BODY' (Size = 18,4)") BigDecimal billamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 20)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CALCULATERATE_BODY' (Size = 1)") Integer calculaterate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FIXADDITIONAMOUNT_BODY' (Size = 18,4)") BigDecimal fixadditionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ZERORATEDVALUE_BODY' (Size = 18,4)") BigDecimal zeroratedvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELIVERYTYPEDESC_BODY' (Size = 500)") String deliverytypedesc;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFNODATE_BODY'") String refnodate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRIORITY_BODY' (Size = 1)") Integer priority;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELIVERYTYPEDESCID_BODY' (Size = 20)") Long deliverytypedescid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTYPEID_BODY' (Size = 20)") Long doctypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FIYEARID_BODY' (Size = 20)") Long fiyearid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRNMONTH_BODY' (Size = 2)") Integer trnmonth;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRNYEAR_BODY' (Size = 4)") Integer trnyear;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERTIME_BODY'") Time pickertime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACKINGTIME_BODY'") Time packingtime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VERIFICATIONTIME_BODY'") Time verificationtime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPATCHTIME_BODY'") Time dispatchtime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVOICETIME_BODY'") Time invoicetime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVCRTIME_BODY'") Time invcrtime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERSTATUS_BODY' (Size = 1)") Integer pickerstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACKINGSTATUS_BODY' (Size = 1)") Integer packingstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VERIFACATIONSTATUS_BODY' (Size = 1)") Integer verifacationstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPATCHSTATUS_BODY' (Size = 1)") Integer dispatchstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VERIFICATIONFLAG_BODY' (Size = 1)") Integer verificationflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISSUBSTOCKIEST_BODY' (Size = 1)") Integer issubstockiest;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECATEGORYID_BODY' (Size = 20)") Long ratecategoryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTTYPE_BODY' (Size = 1)") Integer agenttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELIVERYTYPEID_BODY' (Size = 1)") Integer deliverytypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSUMMERYPRINTCOUNT_BODY' (Size = 10)") Integer transummeryprintcount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCUPLOADREFID_BODY' (Size = 20)") Long docuploadrefid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJDEBITNOTE_BODY'") String adjdebitnote;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJCREDITNOTE_BODY'") String adjcreditnote;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOREID_BODY' (Size = 20)") Long storeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IMPORTSETUPFLAG_BODY' (Size = 1)") Integer importsetupflag;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONDETAIL_BODY' (required)") List<TransactiondetailBody> transactiondetail;

		@JsonCreator
		public TransactionmasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("transactionmasterid") Long transactionmasterid,
				@JsonProperty("voucherno") Integer voucherno,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("paymentmode") Integer paymentmode,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("grossamount") BigDecimal grossamount,
				@JsonProperty("cashdiscountpercent") BigDecimal cashdiscountpercent,
				@JsonProperty("cashdiscountamount") BigDecimal cashdiscountamount,
				@JsonProperty("productdiscountamount") BigDecimal productdiscountamount,
				@JsonProperty("agentid") Long agentid,
				@JsonProperty("netamount") BigDecimal netamount,
				@JsonProperty("canflag") Integer canflag,
				@JsonProperty("debitnoteamount") BigDecimal debitnoteamount,
				@JsonProperty("creditnoteamount") BigDecimal creditnoteamount,
				@JsonProperty("printflag") Integer printflag,
				@JsonProperty("pending") BigDecimal pending,
				@JsonProperty("addcommissionpercent") BigDecimal addcommissionpercent,
				@JsonProperty("vanissueno") Long vanissueno,
				@JsonProperty("packingamount") BigDecimal packingamount,
				@JsonProperty("commisionpercent") BigDecimal commisionpercent,
				@JsonProperty("commissionamount") BigDecimal commissionamount,
				@JsonProperty("otheradditionnarration") String otheradditionnarration,
				@JsonProperty("otheradditionamount") BigDecimal otheradditionamount,
				@JsonProperty("otherdeductionnarration") String otherdeductionnarration,
				@JsonProperty("otherdeductionamount") BigDecimal otherdeductionamount,
				@JsonProperty("roundof") BigDecimal roundof,
				@JsonProperty("medeupload") Integer medeupload,
				@JsonProperty("mmailsend") Integer mmailsend,
				@JsonProperty("gstamount") BigDecimal gstamount,
				@JsonProperty("igstamount") BigDecimal igstamount,
				@JsonProperty("cgstamount") BigDecimal cgstamount,
				@JsonProperty("sgstamount") BigDecimal sgstamount,
				@JsonProperty("cessamount") BigDecimal cessamount,
				@JsonProperty("gstnet") BigDecimal gstnet,
				@JsonProperty("tcscharge") BigDecimal tcscharge,
				@JsonProperty("tcspercent") BigDecimal tcspercent,
				@JsonProperty("tcsamount") BigDecimal tcsamount,
				@JsonProperty("customername") String customername,
				@JsonProperty("customeradd1") String customeradd1,
				@JsonProperty("customeradd2") String customeradd2,
				@JsonProperty("customeradd3") String customeradd3,
				@JsonProperty("lrno") String lrno,
				@JsonProperty("lrdate") @JsonFormat(pattern = "yyyy-MM-dd") Date lrdate,
				@JsonProperty("transport") String transport,
				@JsonProperty("pack") Integer pack,
				@JsonProperty("amtsms") Integer amtsms,
				@JsonProperty("dispatchsms") Integer dispatchsms,
				@JsonProperty("orderno") String orderno,
				@JsonProperty("orderdate") @JsonFormat(pattern = "yyyy-MM-dd") Date orderdate,
				@JsonProperty("ewbno") String ewbno,
				@JsonProperty("creditdays") Integer creditdays,
				@JsonProperty("duedate") @JsonFormat(pattern = "yyyy-MM-dd") Date duedate,
				@JsonProperty("chequeno") String chequeno,
				@JsonProperty("chequedate") @JsonFormat(pattern = "yyyy-MM-dd") Date chequedate,
				@JsonProperty("bankname") String bankname,
				@JsonProperty("bankadd") String bankadd,
				@JsonProperty("bankcity") String bankcity,
				@JsonProperty("gstinno") String gstinno,
				@JsonProperty("gsttype") Integer gsttype,
				@JsonProperty("itemcount") Integer itemcount,
				@JsonProperty("qtycount") Integer qtycount,
				@JsonProperty("salevalue") BigDecimal salevalue,
				@JsonProperty("exemptedvalue") BigDecimal exemptedvalue,
				@JsonProperty("nilratedvalue") BigDecimal nilratedvalue,
				@JsonProperty("nongstvalue") BigDecimal nongstvalue,
				@JsonProperty("pharmarackupload") Integer pharmarackupload,
				@JsonProperty("retailioupload") Integer retailioupload,
				@JsonProperty("mobileordercreatedby") Long mobileordercreatedby,
				@JsonProperty("mobileorderno") String mobileorderno,
				@JsonProperty("mobileorderdate") @JsonFormat(pattern = "yyyy-MM-dd") Date mobileorderdate,
				@JsonProperty("claimno") String claimno,
				@JsonProperty("claimdate") @JsonFormat(pattern = "yyyy-MM-dd") Date claimdate,
				@JsonProperty("totalgstamount") BigDecimal totalgstamount,
				@JsonProperty("creditdebitnarration") String creditdebitnarration,
				@JsonProperty("creditdebitstatus") String creditdebitstatus,
				@JsonProperty("ratediffdebitcreditnoteflag") Integer ratediffdebitcreditnoteflag,
				@JsonProperty("post") Integer post,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("gstdebitnoteflag") Integer gstdebitnoteflag,
				@JsonProperty("mradj") Integer mradj,
				@JsonProperty("mrpvalue") BigDecimal mrpvalue,
				@JsonProperty("replacementamount") BigDecimal replacementamount,
				@JsonProperty("replacmentclose") Integer replacmentclose,
				@JsonProperty("cdp") Integer cdp,
				@JsonProperty("creditnotemess") String creditnotemess,
				@JsonProperty("creditnoteflag") Integer creditnoteflag,
				@JsonProperty("creditnotetype") Integer creditnotetype,
				@JsonProperty("gstadj") Integer gstadj,
				@JsonProperty("adjustinvoiceno") String adjustinvoiceno,
				@JsonProperty("adjustinvoiceseries") String adjustinvoiceseries,
				@JsonProperty("adjustinvoicedate") @JsonFormat(pattern = "yyyy-MM-dd") Date adjustinvoicedate,
				@JsonProperty("gstdebitnote") Integer gstdebitnote,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("tdspercent") BigDecimal tdspercent,
				@JsonProperty("tdsamount") BigDecimal tdsamount,
				@JsonProperty("tdscharge") BigDecimal tdscharge,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("adjcreditnoteno") String adjcreditnoteno,
				@JsonProperty("adjcreditnotedate") @JsonFormat(pattern = "yyyy-MM-dd") Date adjcreditnotedate,
				@JsonProperty("referenceid") Long referenceid,
				@JsonProperty("replacementcrnoteno") String replacementcrnoteno,
				@JsonProperty("replacementcrnotedate") @JsonFormat(pattern = "yyyy-MM-dd") Date replacementcrnotedate,
				@JsonProperty("specialdiscountamt") BigDecimal specialdiscountamt,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("reason") String reason,
				@JsonProperty("ugstamount") BigDecimal ugstamount,
				@JsonProperty("billamount") BigDecimal billamount,
				@JsonProperty("agencygroupid") Long agencygroupid,
				@JsonProperty("calculaterate") Integer calculaterate,
				@JsonProperty("fixadditionamount") BigDecimal fixadditionamount,
				@JsonProperty("zeroratedvalue") BigDecimal zeroratedvalue,
				@JsonProperty("deliverytypedesc") String deliverytypedesc,
				@JsonProperty("refnodate") String refnodate,
				@JsonProperty("priority") Integer priority,
				@JsonProperty("deliverytypedescid") Long deliverytypedescid,
				@JsonProperty("doctypeid") Long doctypeid,
				@JsonProperty("fiyearid") Long fiyearid,
				@JsonProperty("trnmonth") Integer trnmonth,
				@JsonProperty("trnyear") Integer trnyear,
				@JsonProperty("pickertime") @JsonFormat(pattern = "HH:mm:ss") Time pickertime,
				@JsonProperty("packingtime") @JsonFormat(pattern = "HH:mm:ss") Time packingtime,
				@JsonProperty("verificationtime") @JsonFormat(pattern = "HH:mm:ss") Time verificationtime,
				@JsonProperty("dispatchtime") @JsonFormat(pattern = "HH:mm:ss") Time dispatchtime,
				@JsonProperty("invoicetime") @JsonFormat(pattern = "HH:mm:ss") Time invoicetime,
				@JsonProperty("invcrtime") @JsonFormat(pattern = "HH:mm:ss") Time invcrtime,
				@JsonProperty("pickerstatus") Integer pickerstatus,
				@JsonProperty("packingstatus") Integer packingstatus,
				@JsonProperty("verifacationstatus") Integer verifacationstatus,
				@JsonProperty("dispatchstatus") Integer dispatchstatus,
				@JsonProperty("verificationflag") Integer verificationflag,
				@JsonProperty("issubstockiest") Integer issubstockiest,
				@JsonProperty("ratecategoryid") Long ratecategoryid,
				@JsonProperty("agenttype") Integer agenttype,
				@JsonProperty("deliverytypeid") Integer deliverytypeid,
				@JsonProperty("transummeryprintcount") Integer transummeryprintcount,
				@JsonProperty("docuploadrefid") Long docuploadrefid,
				@JsonProperty("adjdebitnote") String adjdebitnote,
				@JsonProperty("adjcreditnote") String adjcreditnote,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("importsetupflag") Integer importsetupflag,
				@JsonProperty("transactiondetail") List<TransactiondetailBody> transactiondetail) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.transactionmasterid = Objects.requireNonNull(transactionmasterid, "`transactionmasterid` is required");
			this.voucherno = voucherno;
			this.accountid = accountid;
			this.voucherdate = voucherdate;
			this.paymentmode = paymentmode;
			this.voucherseries = voucherseries;
			this.grossamount = grossamount;
			this.cashdiscountpercent = cashdiscountpercent;
			this.cashdiscountamount = cashdiscountamount;
			this.productdiscountamount = productdiscountamount;
			this.agentid = agentid;
			this.netamount = netamount;
			this.canflag = canflag;
			this.debitnoteamount = debitnoteamount;
			this.creditnoteamount = creditnoteamount;
			this.printflag = printflag;
			this.pending = pending;
			this.addcommissionpercent = addcommissionpercent;
			this.vanissueno = vanissueno;
			this.packingamount = packingamount;
			this.commisionpercent = commisionpercent;
			this.commissionamount = commissionamount;
			this.otheradditionnarration = otheradditionnarration;
			this.otheradditionamount = otheradditionamount;
			this.otherdeductionnarration = otherdeductionnarration;
			this.otherdeductionamount = otherdeductionamount;
			this.roundof = roundof;
			this.medeupload = medeupload;
			this.mmailsend = mmailsend;
			this.gstamount = gstamount;
			this.igstamount = igstamount;
			this.cgstamount = cgstamount;
			this.sgstamount = sgstamount;
			this.cessamount = cessamount;
			this.gstnet = gstnet;
			this.tcscharge = tcscharge;
			this.tcspercent = tcspercent;
			this.tcsamount = tcsamount;
			this.customername = customername;
			this.customeradd1 = customeradd1;
			this.customeradd2 = customeradd2;
			this.customeradd3 = customeradd3;
			this.lrno = lrno;
			this.lrdate = lrdate;
			this.transport = transport;
			this.pack = pack;
			this.amtsms = amtsms;
			this.dispatchsms = dispatchsms;
			this.orderno = orderno;
			this.orderdate = orderdate;
			this.ewbno = ewbno;
			this.creditdays = creditdays;
			this.duedate = duedate;
			this.chequeno = chequeno;
			this.chequedate = chequedate;
			this.bankname = bankname;
			this.bankadd = bankadd;
			this.bankcity = bankcity;
			this.gstinno = gstinno;
			this.gsttype = gsttype;
			this.itemcount = itemcount;
			this.qtycount = qtycount;
			this.salevalue = salevalue;
			this.exemptedvalue = exemptedvalue;
			this.nilratedvalue = nilratedvalue;
			this.nongstvalue = nongstvalue;
			this.pharmarackupload = pharmarackupload;
			this.retailioupload = retailioupload;
			this.mobileordercreatedby = mobileordercreatedby;
			this.mobileorderno = mobileorderno;
			this.mobileorderdate = mobileorderdate;
			this.claimno = claimno;
			this.claimdate = claimdate;
			this.totalgstamount = totalgstamount;
			this.creditdebitnarration = creditdebitnarration;
			this.creditdebitstatus = creditdebitstatus;
			this.ratediffdebitcreditnoteflag = ratediffdebitcreditnoteflag;
			this.post = post;
			this.billno = billno;
			this.billdate = billdate;
			this.gstdebitnoteflag = gstdebitnoteflag;
			this.mradj = mradj;
			this.mrpvalue = mrpvalue;
			this.replacementamount = replacementamount;
			this.replacmentclose = replacmentclose;
			this.cdp = cdp;
			this.creditnotemess = creditnotemess;
			this.creditnoteflag = creditnoteflag;
			this.creditnotetype = creditnotetype;
			this.gstadj = gstadj;
			this.adjustinvoiceno = adjustinvoiceno;
			this.adjustinvoiceseries = adjustinvoiceseries;
			this.adjustinvoicedate = adjustinvoicedate;
			this.gstdebitnote = gstdebitnote;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.tdspercent = tdspercent;
			this.tdsamount = tdsamount;
			this.tdscharge = tdscharge;
			this.lastoperation = lastoperation;
			this.active = active;
			this.vouchertype = vouchertype;
			this.vouchermode = vouchermode;
			this.adjcreditnoteno = adjcreditnoteno;
			this.adjcreditnotedate = adjcreditnotedate;
			this.referenceid = referenceid;
			this.replacementcrnoteno = replacementcrnoteno;
			this.replacementcrnotedate = replacementcrnotedate;
			this.specialdiscountamt = specialdiscountamt;
			this.agencyid = agencyid;
			this.reason = reason;
			this.ugstamount = ugstamount;
			this.billamount = billamount;
			this.agencygroupid = agencygroupid;
			this.calculaterate = calculaterate;
			this.fixadditionamount = fixadditionamount;
			this.zeroratedvalue = zeroratedvalue;
			this.deliverytypedesc = deliverytypedesc;
			this.refnodate = refnodate;
			this.priority = priority;
			this.deliverytypedescid = deliverytypedescid;
			this.doctypeid = doctypeid;
			this.fiyearid = fiyearid;
			this.trnmonth = trnmonth;
			this.trnyear = trnyear;
			this.pickertime = pickertime;
			this.packingtime = packingtime;
			this.verificationtime = verificationtime;
			this.dispatchtime = dispatchtime;
			this.invoicetime = invoicetime;
			this.invcrtime = invcrtime;
			this.pickerstatus = pickerstatus;
			this.packingstatus = packingstatus;
			this.verifacationstatus = verifacationstatus;
			this.dispatchstatus = dispatchstatus;
			this.verificationflag = verificationflag;
			this.issubstockiest = issubstockiest;
			this.ratecategoryid = ratecategoryid;
			this.agenttype = agenttype;
			this.deliverytypeid = deliverytypeid;
			this.transummeryprintcount = transummeryprintcount;
			this.docuploadrefid = docuploadrefid;
			this.adjdebitnote = adjdebitnote;
			this.adjcreditnote = adjcreditnote;
			this.storeid = storeid;
			this.importsetupflag = importsetupflag;
			this.transactiondetail = transactiondetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTransactionmasterid() {
			return transactionmasterid;
		}

		public Integer getVoucherno() {
			return voucherno;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public Integer getPaymentmode() {
			return paymentmode;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public BigDecimal getGrossamount() {
			return grossamount;
		}

		public BigDecimal getCashdiscountpercent() {
			return cashdiscountpercent;
		}

		public BigDecimal getCashdiscountamount() {
			return cashdiscountamount;
		}

		public BigDecimal getProductdiscountamount() {
			return productdiscountamount;
		}

		public Long getAgentid() {
			return agentid;
		}

		public BigDecimal getNetamount() {
			return netamount;
		}

		public Integer getCanflag() {
			return canflag;
		}

		public BigDecimal getDebitnoteamount() {
			return debitnoteamount;
		}

		public BigDecimal getCreditnoteamount() {
			return creditnoteamount;
		}

		public Integer getPrintflag() {
			return printflag;
		}

		public BigDecimal getPending() {
			return pending;
		}

		public BigDecimal getAddcommissionpercent() {
			return addcommissionpercent;
		}

		public Long getVanissueno() {
			return vanissueno;
		}

		public BigDecimal getPackingamount() {
			return packingamount;
		}

		public BigDecimal getCommisionpercent() {
			return commisionpercent;
		}

		public BigDecimal getCommissionamount() {
			return commissionamount;
		}

		public String getOtheradditionnarration() {
			return otheradditionnarration;
		}

		public BigDecimal getOtheradditionamount() {
			return otheradditionamount;
		}

		public String getOtherdeductionnarration() {
			return otherdeductionnarration;
		}

		public BigDecimal getOtherdeductionamount() {
			return otherdeductionamount;
		}

		public BigDecimal getRoundof() {
			return roundof;
		}

		public Integer getMedeupload() {
			return medeupload;
		}

		public Integer getMmailsend() {
			return mmailsend;
		}

		public BigDecimal getGstamount() {
			return gstamount;
		}

		public BigDecimal getIgstamount() {
			return igstamount;
		}

		public BigDecimal getCgstamount() {
			return cgstamount;
		}

		public BigDecimal getSgstamount() {
			return sgstamount;
		}

		public BigDecimal getCessamount() {
			return cessamount;
		}

		public BigDecimal getGstnet() {
			return gstnet;
		}

		public BigDecimal getTcscharge() {
			return tcscharge;
		}

		public BigDecimal getTcspercent() {
			return tcspercent;
		}

		public BigDecimal getTcsamount() {
			return tcsamount;
		}

		public String getCustomername() {
			return customername;
		}

		public String getCustomeradd1() {
			return customeradd1;
		}

		public String getCustomeradd2() {
			return customeradd2;
		}

		public String getCustomeradd3() {
			return customeradd3;
		}

		public String getLrno() {
			return lrno;
		}

		public Date getLrdate() {
			return lrdate;
		}

		public String getTransport() {
			return transport;
		}

		public Integer getPack() {
			return pack;
		}

		public Integer getAmtsms() {
			return amtsms;
		}

		public Integer getDispatchsms() {
			return dispatchsms;
		}

		public String getOrderno() {
			return orderno;
		}

		public Date getOrderdate() {
			return orderdate;
		}

		public String getEwbno() {
			return ewbno;
		}

		public Integer getCreditdays() {
			return creditdays;
		}

		public Date getDuedate() {
			return duedate;
		}

		public String getChequeno() {
			return chequeno;
		}

		public Date getChequedate() {
			return chequedate;
		}

		public String getBankname() {
			return bankname;
		}

		public String getBankadd() {
			return bankadd;
		}

		public String getBankcity() {
			return bankcity;
		}

		public String getGstinno() {
			return gstinno;
		}

		public Integer getGsttype() {
			return gsttype;
		}

		public Integer getItemcount() {
			return itemcount;
		}

		public Integer getQtycount() {
			return qtycount;
		}

		public BigDecimal getSalevalue() {
			return salevalue;
		}

		public BigDecimal getExemptedvalue() {
			return exemptedvalue;
		}

		public BigDecimal getNilratedvalue() {
			return nilratedvalue;
		}

		public BigDecimal getNongstvalue() {
			return nongstvalue;
		}

		public Integer getPharmarackupload() {
			return pharmarackupload;
		}

		public Integer getRetailioupload() {
			return retailioupload;
		}

		public Long getMobileordercreatedby() {
			return mobileordercreatedby;
		}

		public String getMobileorderno() {
			return mobileorderno;
		}

		public Date getMobileorderdate() {
			return mobileorderdate;
		}

		public String getClaimno() {
			return claimno;
		}

		public Date getClaimdate() {
			return claimdate;
		}

		public BigDecimal getTotalgstamount() {
			return totalgstamount;
		}

		public String getCreditdebitnarration() {
			return creditdebitnarration;
		}

		public String getCreditdebitstatus() {
			return creditdebitstatus;
		}

		public Integer getRatediffdebitcreditnoteflag() {
			return ratediffdebitcreditnoteflag;
		}

		public Integer getPost() {
			return post;
		}

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public Integer getGstdebitnoteflag() {
			return gstdebitnoteflag;
		}

		public Integer getMradj() {
			return mradj;
		}

		public BigDecimal getMrpvalue() {
			return mrpvalue;
		}

		public BigDecimal getReplacementamount() {
			return replacementamount;
		}

		public Integer getReplacmentclose() {
			return replacmentclose;
		}

		public Integer getCdp() {
			return cdp;
		}

		public String getCreditnotemess() {
			return creditnotemess;
		}

		public Integer getCreditnoteflag() {
			return creditnoteflag;
		}

		public Integer getCreditnotetype() {
			return creditnotetype;
		}

		public Integer getGstadj() {
			return gstadj;
		}

		public String getAdjustinvoiceno() {
			return adjustinvoiceno;
		}

		public String getAdjustinvoiceseries() {
			return adjustinvoiceseries;
		}

		public Date getAdjustinvoicedate() {
			return adjustinvoicedate;
		}

		public Integer getGstdebitnote() {
			return gstdebitnote;
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

		public Long getModifyby() {
			return modifyby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public BigDecimal getTdspercent() {
			return tdspercent;
		}

		public BigDecimal getTdsamount() {
			return tdsamount;
		}

		public BigDecimal getTdscharge() {
			return tdscharge;
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

		public String getAdjcreditnoteno() {
			return adjcreditnoteno;
		}

		public Date getAdjcreditnotedate() {
			return adjcreditnotedate;
		}

		public Long getReferenceid() {
			return referenceid;
		}

		public String getReplacementcrnoteno() {
			return replacementcrnoteno;
		}

		public Date getReplacementcrnotedate() {
			return replacementcrnotedate;
		}

		public BigDecimal getSpecialdiscountamt() {
			return specialdiscountamt;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public String getReason() {
			return reason;
		}

		public BigDecimal getUgstamount() {
			return ugstamount;
		}

		public BigDecimal getBillamount() {
			return billamount;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public Integer getCalculaterate() {
			return calculaterate;
		}

		public BigDecimal getFixadditionamount() {
			return fixadditionamount;
		}

		public BigDecimal getZeroratedvalue() {
			return zeroratedvalue;
		}

		public String getDeliverytypedesc() {
			return deliverytypedesc;
		}

		public String getRefnodate() {
			return refnodate;
		}

		public Integer getPriority() {
			return priority;
		}

		public Long getDeliverytypedescid() {
			return deliverytypedescid;
		}

		public Long getDoctypeid() {
			return doctypeid;
		}

		public Long getFiyearid() {
			return fiyearid;
		}

		public Integer getTrnmonth() {
			return trnmonth;
		}

		public Integer getTrnyear() {
			return trnyear;
		}

		public Time getPickertime() {
			return pickertime;
		}

		public Time getPackingtime() {
			return packingtime;
		}

		public Time getVerificationtime() {
			return verificationtime;
		}

		public Time getDispatchtime() {
			return dispatchtime;
		}

		public Time getInvoicetime() {
			return invoicetime;
		}

		public Time getInvcrtime() {
			return invcrtime;
		}

		public Integer getPickerstatus() {
			return pickerstatus;
		}

		public Integer getPackingstatus() {
			return packingstatus;
		}

		public Integer getVerifacationstatus() {
			return verifacationstatus;
		}

		public Integer getDispatchstatus() {
			return dispatchstatus;
		}

		public Integer getVerificationflag() {
			return verificationflag;
		}

		public Integer getIssubstockiest() {
			return issubstockiest;
		}

		public Long getRatecategoryid() {
			return ratecategoryid;
		}

		public Integer getAgenttype() {
			return agenttype;
		}

		public Integer getDeliverytypeid() {
			return deliverytypeid;
		}

		public Integer getTransummeryprintcount() {
			return transummeryprintcount;
		}

		public Long getDocuploadrefid() {
			return docuploadrefid;
		}

		public String getAdjdebitnote() {
			return adjdebitnote;
		}

		public String getAdjcreditnote() {
			return adjcreditnote;
		}

		public Long getStoreid() {
			return storeid;
		}

		public Integer getImportsetupflag() {
			return importsetupflag;
		}

		public List<TransactiondetailBody> getTransactiondetail() {
			return transactiondetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Transactiondetail entity")
	public static final class TransactiondetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONMASTERID_BODY' (required) (Size = 20)") Long transactionmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONDETAILID_BODY' (Primary Key)") Long transactiondetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 10)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 50)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTPACK_BODY' (Size = 18,4)") BigDecimal productpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTY_BODY' (Size = 18,4)") BigDecimal qty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREE_BODY' (Size = 18,4)") BigDecimal free;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 30)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal productdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTDISCOUNTAMOUNT_BODY' (Size = 18,4)") BigDecimal productdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal cashdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTAMOUNT_BODY' (Size = 18,4)") BigDecimal cashdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DMNO_BODY' (Size = 20)") String dmno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DMDATE_BODY'") Date dmdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMVALUE_BODY' (Size = 18,4)") BigDecimal itemvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRY_BODY'") Date expiry;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATE_BODY' (Size = 1)") Integer rate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SRNO_BODY' (Size = 6)") Integer srno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXCODE_BODY' (Size = 20)") Long taxcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALQTY_BODY' (Size = 18,4)") BigDecimal specialqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEDIFFCREDITNOTENO_BODY' (Size = 20)") String ratediffcreditnoteno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEDIFFDEBITNOTENO_BODY' (Size = 20)") String ratediffdebitnoteno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYNET_BODY' (Size = 18,4)") BigDecimal qtynet;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREENET_BODY' (Size = 18,4)") BigDecimal freenet;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTVALUE_BODY' (Size = 18,4)") BigDecimal gstvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'HSNCODE_BODY' (Size = 20)") String hsncode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTPERCENT_BODY' (Size = 18,4)") BigDecimal gstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGSTPERCENT_BODY' (Size = 18,4)") BigDecimal igstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGSTPERCENT_BODY' (Size = 18,4)") BigDecimal cgstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGSTPERCENT_BODY' (Size = 18,4)") BigDecimal sgstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal igstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal cgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal sgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTTP_BODY' (Size = 1)") Integer gsttp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESSPERCENT_BODY' (Size = 18,4)") BigDecimal cesspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESSAMOUNT_BODY' (Size = 18,4)") BigDecimal cessamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONALCESSPERCENT_BODY' (Size = 18,4)") BigDecimal additionalcesspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONALCESSAMOUNT_BODY' (Size = 18,4)") BigDecimal additionalcessamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCODE_BODY' (Size = 20)") Long companycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LINKID_BODY' (Size = 20)") Long linkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LESSPERCENT_BODY' (Size = 18,4)") BigDecimal lesspercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLAIMRATE_BODY' (Size = 18,4)") BigDecimal claimrate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERSERIES_BODY' (Size = 100)") String returnvoucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERNO_BODY' (Size = 100)") String returnvoucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETURNVOUCHERDATE_BODY'") Date returnvoucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFEXPIRYDATE_BODY'") Date refexpirydate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFEXPIRYNO_BODY' (Size = 20)") Long refexpiryno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFEXPIRYSERIES_BODY' (Size = 20)") String refexpiryseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFCLAIMNO_BODY' (Size = 20)") Long refclaimno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFCLAIMSERIES_BODY' (Size = 20)") String refclaimseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFCLAIMDATE_BODY'") Date refclaimdate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TNEW_BODY' (Size = 1)") Integer tnew;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEID_BODY' (Size = 20)") Long rateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHID_BODY' (Size = 20)") Long batchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACKING_BODY' (Size = 20)") String displaypacking;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOREID_BODY' (Size = 20)") Long storeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SOURCESTOREID_BODY' (Size = 20)") Long sourcestoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TARGETSTOREID_BODY' (Size = 20)") Long targetstoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 18,4)") BigDecimal purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADE_BODY' (Size = 18,4)") BigDecimal trade;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALDISCOUNTAMOUNT_BODY' (Size = 18,4)") BigDecimal specialdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal specialdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASETYPE_BODY' (Size = 1)") Integer purchasetype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHBIFURCATIONFLAG_BODY' (Size = 1)") Integer batchbifurcationflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGSTPERCENT_BODY' (Size = 18,4)") BigDecimal ugstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal ugstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEDIFFLAG_BODY' (Size = 1)") Integer ratedifflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISCOLDSTORAGE_BODY' (Size = 1)") Integer iscoldstorage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISEXPIRED_BODY' (Size = 1)") Integer isexpired;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWCASHDISCOUNT_BODY' (Size = 1)") Integer allowcashdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTAMOUNT_BODY' (Size = 18,4)") BigDecimal gstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FIYEARID_BODY' (Size = 20)") Long fiyearid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONID_BODY' (Size = 20)") Long locationid;

		@JsonCreator
		public TransactiondetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("transactionmasterid") Long transactionmasterid,
				@JsonProperty("transactiondetailid") Long transactiondetailid,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("productid") Long productid,
				@JsonProperty("productname") String productname,
				@JsonProperty("productpack") BigDecimal productpack,
				@JsonProperty("qty") BigDecimal qty,
				@JsonProperty("free") BigDecimal free,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("productdiscountpercent") BigDecimal productdiscountpercent,
				@JsonProperty("productdiscountamount") BigDecimal productdiscountamount,
				@JsonProperty("cashdiscountpercent") BigDecimal cashdiscountpercent,
				@JsonProperty("cashdiscountamount") BigDecimal cashdiscountamount,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("dmno") String dmno,
				@JsonProperty("dmdate") @JsonFormat(pattern = "yyyy-MM-dd") Date dmdate,
				@JsonProperty("itemvalue") BigDecimal itemvalue,
				@JsonProperty("expiry") @JsonFormat(pattern = "yyyy-MM-dd") Date expiry,
				@JsonProperty("rate") Integer rate,
				@JsonProperty("srno") Integer srno,
				@JsonProperty("taxcode") Long taxcode,
				@JsonProperty("specialqty") BigDecimal specialqty,
				@JsonProperty("ratediffcreditnoteno") String ratediffcreditnoteno,
				@JsonProperty("ratediffdebitnoteno") String ratediffdebitnoteno,
				@JsonProperty("qtynet") BigDecimal qtynet,
				@JsonProperty("freenet") BigDecimal freenet,
				@JsonProperty("gstvalue") BigDecimal gstvalue,
				@JsonProperty("hsncode") String hsncode,
				@JsonProperty("gstpercent") BigDecimal gstpercent,
				@JsonProperty("igstpercent") BigDecimal igstpercent,
				@JsonProperty("cgstpercent") BigDecimal cgstpercent,
				@JsonProperty("sgstpercent") BigDecimal sgstpercent,
				@JsonProperty("igstamount") BigDecimal igstamount,
				@JsonProperty("cgstamount") BigDecimal cgstamount,
				@JsonProperty("sgstamount") BigDecimal sgstamount,
				@JsonProperty("gsttp") Integer gsttp,
				@JsonProperty("cesspercent") BigDecimal cesspercent,
				@JsonProperty("cessamount") BigDecimal cessamount,
				@JsonProperty("additionalcesspercent") BigDecimal additionalcesspercent,
				@JsonProperty("additionalcessamount") BigDecimal additionalcessamount,
				@JsonProperty("companycode") Long companycode,
				@JsonProperty("linkid") Long linkid,
				@JsonProperty("lesspercent") BigDecimal lesspercent,
				@JsonProperty("claimrate") BigDecimal claimrate,
				@JsonProperty("returnvoucherseries") String returnvoucherseries,
				@JsonProperty("returnvoucherno") String returnvoucherno,
				@JsonProperty("returnvoucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date returnvoucherdate,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("refexpirydate") @JsonFormat(pattern = "yyyy-MM-dd") Date refexpirydate,
				@JsonProperty("refexpiryno") Long refexpiryno,
				@JsonProperty("refexpiryseries") String refexpiryseries,
				@JsonProperty("refclaimno") Long refclaimno,
				@JsonProperty("refclaimseries") String refclaimseries,
				@JsonProperty("refclaimdate") @JsonFormat(pattern = "yyyy-MM-dd") Date refclaimdate,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("tnew") Integer tnew,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("rateid") Long rateid,
				@JsonProperty("batchid") Long batchid,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("displaypacking") String displaypacking,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("sourcestoreid") Long sourcestoreid,
				@JsonProperty("targetstoreid") Long targetstoreid,
				@JsonProperty("purchaserate") BigDecimal purchaserate,
				@JsonProperty("trade") BigDecimal trade,
				@JsonProperty("specialdiscountamount") BigDecimal specialdiscountamount,
				@JsonProperty("specialdiscountpercent") BigDecimal specialdiscountpercent,
				@JsonProperty("purchasetype") Integer purchasetype,
				@JsonProperty("batchbifurcationflag") Integer batchbifurcationflag,
				@JsonProperty("ugstpercent") BigDecimal ugstpercent,
				@JsonProperty("ugstamount") BigDecimal ugstamount,
				@JsonProperty("ratedifflag") Integer ratedifflag,
				@JsonProperty("iscoldstorage") Integer iscoldstorage,
				@JsonProperty("isexpired") Integer isexpired,
				@JsonProperty("allowcashdiscount") Integer allowcashdiscount,
				@JsonProperty("gstamount") BigDecimal gstamount,
				@JsonProperty("fiyearid") Long fiyearid,
				@JsonProperty("locationid") Long locationid) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.transactionmasterid = Objects.requireNonNull(transactionmasterid, "`transactionmasterid` is required");
			this.transactiondetailid = Objects.requireNonNull(transactiondetailid, "`transactiondetailid` is required");
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.voucherdate = voucherdate;
			this.productid = productid;
			this.productname = productname;
			this.productpack = productpack;
			this.qty = qty;
			this.free = free;
			this.batchno = batchno;
			this.mrp = mrp;
			this.salerate = salerate;
			this.productdiscountpercent = productdiscountpercent;
			this.productdiscountamount = productdiscountamount;
			this.cashdiscountpercent = cashdiscountpercent;
			this.cashdiscountamount = cashdiscountamount;
			this.shortname = shortname;
			this.dmno = dmno;
			this.dmdate = dmdate;
			this.itemvalue = itemvalue;
			this.expiry = expiry;
			this.rate = rate;
			this.srno = srno;
			this.taxcode = taxcode;
			this.specialqty = specialqty;
			this.ratediffcreditnoteno = ratediffcreditnoteno;
			this.ratediffdebitnoteno = ratediffdebitnoteno;
			this.qtynet = qtynet;
			this.freenet = freenet;
			this.gstvalue = gstvalue;
			this.hsncode = hsncode;
			this.gstpercent = gstpercent;
			this.igstpercent = igstpercent;
			this.cgstpercent = cgstpercent;
			this.sgstpercent = sgstpercent;
			this.igstamount = igstamount;
			this.cgstamount = cgstamount;
			this.sgstamount = sgstamount;
			this.gsttp = gsttp;
			this.cesspercent = cesspercent;
			this.cessamount = cessamount;
			this.additionalcesspercent = additionalcesspercent;
			this.additionalcessamount = additionalcessamount;
			this.companycode = companycode;
			this.linkid = linkid;
			this.lesspercent = lesspercent;
			this.claimrate = claimrate;
			this.returnvoucherseries = returnvoucherseries;
			this.returnvoucherno = returnvoucherno;
			this.returnvoucherdate = returnvoucherdate;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.refexpirydate = refexpirydate;
			this.refexpiryno = refexpiryno;
			this.refexpiryseries = refexpiryseries;
			this.refclaimno = refclaimno;
			this.refclaimseries = refclaimseries;
			this.refclaimdate = refclaimdate;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = active;
			this.tnew = tnew;
			this.vouchertype = vouchertype;
			this.rateid = rateid;
			this.batchid = batchid;
			this.vouchermode = vouchermode;
			this.displaypacking = displaypacking;
			this.storeid = storeid;
			this.sourcestoreid = sourcestoreid;
			this.targetstoreid = targetstoreid;
			this.purchaserate = purchaserate;
			this.trade = trade;
			this.specialdiscountamount = specialdiscountamount;
			this.specialdiscountpercent = specialdiscountpercent;
			this.purchasetype = purchasetype;
			this.batchbifurcationflag = batchbifurcationflag;
			this.ugstpercent = ugstpercent;
			this.ugstamount = ugstamount;
			this.ratedifflag = ratedifflag;
			this.iscoldstorage = iscoldstorage;
			this.isexpired = isexpired;
			this.allowcashdiscount = allowcashdiscount;
			this.gstamount = gstamount;
			this.fiyearid = fiyearid;
			this.locationid = locationid;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTransactionmasterid() {
			return transactionmasterid;
		}

		public Long getTransactiondetailid() {
			return transactiondetailid;
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

		public Long getProductid() {
			return productid;
		}

		public String getProductname() {
			return productname;
		}

		public BigDecimal getProductpack() {
			return productpack;
		}

		public BigDecimal getQty() {
			return qty;
		}

		public BigDecimal getFree() {
			return free;
		}

		public String getBatchno() {
			return batchno;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getProductdiscountpercent() {
			return productdiscountpercent;
		}

		public BigDecimal getProductdiscountamount() {
			return productdiscountamount;
		}

		public BigDecimal getCashdiscountpercent() {
			return cashdiscountpercent;
		}

		public BigDecimal getCashdiscountamount() {
			return cashdiscountamount;
		}

		public String getShortname() {
			return shortname;
		}

		public String getDmno() {
			return dmno;
		}

		public Date getDmdate() {
			return dmdate;
		}

		public BigDecimal getItemvalue() {
			return itemvalue;
		}

		public Date getExpiry() {
			return expiry;
		}

		public Integer getRate() {
			return rate;
		}

		public Integer getSrno() {
			return srno;
		}

		public Long getTaxcode() {
			return taxcode;
		}

		public BigDecimal getSpecialqty() {
			return specialqty;
		}

		public String getRatediffcreditnoteno() {
			return ratediffcreditnoteno;
		}

		public String getRatediffdebitnoteno() {
			return ratediffdebitnoteno;
		}

		public BigDecimal getQtynet() {
			return qtynet;
		}

		public BigDecimal getFreenet() {
			return freenet;
		}

		public BigDecimal getGstvalue() {
			return gstvalue;
		}

		public String getHsncode() {
			return hsncode;
		}

		public BigDecimal getGstpercent() {
			return gstpercent;
		}

		public BigDecimal getIgstpercent() {
			return igstpercent;
		}

		public BigDecimal getCgstpercent() {
			return cgstpercent;
		}

		public BigDecimal getSgstpercent() {
			return sgstpercent;
		}

		public BigDecimal getIgstamount() {
			return igstamount;
		}

		public BigDecimal getCgstamount() {
			return cgstamount;
		}

		public BigDecimal getSgstamount() {
			return sgstamount;
		}

		public Integer getGsttp() {
			return gsttp;
		}

		public BigDecimal getCesspercent() {
			return cesspercent;
		}

		public BigDecimal getCessamount() {
			return cessamount;
		}

		public BigDecimal getAdditionalcesspercent() {
			return additionalcesspercent;
		}

		public BigDecimal getAdditionalcessamount() {
			return additionalcessamount;
		}

		public Long getCompanycode() {
			return companycode;
		}

		public Long getLinkid() {
			return linkid;
		}

		public BigDecimal getLesspercent() {
			return lesspercent;
		}

		public BigDecimal getClaimrate() {
			return claimrate;
		}

		public String getReturnvoucherseries() {
			return returnvoucherseries;
		}

		public String getReturnvoucherno() {
			return returnvoucherno;
		}

		public Date getReturnvoucherdate() {
			return returnvoucherdate;
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

		public Long getModifyby() {
			return modifyby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Date getRefexpirydate() {
			return refexpirydate;
		}

		public Long getRefexpiryno() {
			return refexpiryno;
		}

		public String getRefexpiryseries() {
			return refexpiryseries;
		}

		public Long getRefclaimno() {
			return refclaimno;
		}

		public String getRefclaimseries() {
			return refclaimseries;
		}

		public Date getRefclaimdate() {
			return refclaimdate;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getActive() {
			return active;
		}

		public Integer getTnew() {
			return tnew;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public Long getRateid() {
			return rateid;
		}

		public Long getBatchid() {
			return batchid;
		}

		public Integer getVouchermode() {
			return vouchermode;
		}

		public String getDisplaypacking() {
			return displaypacking;
		}

		public Long getStoreid() {
			return storeid;
		}

		public Long getSourcestoreid() {
			return sourcestoreid;
		}

		public Long getTargetstoreid() {
			return targetstoreid;
		}

		public BigDecimal getPurchaserate() {
			return purchaserate;
		}

		public BigDecimal getTrade() {
			return trade;
		}

		public BigDecimal getSpecialdiscountamount() {
			return specialdiscountamount;
		}

		public BigDecimal getSpecialdiscountpercent() {
			return specialdiscountpercent;
		}

		public Integer getPurchasetype() {
			return purchasetype;
		}

		public Integer getBatchbifurcationflag() {
			return batchbifurcationflag;
		}

		public BigDecimal getUgstpercent() {
			return ugstpercent;
		}

		public BigDecimal getUgstamount() {
			return ugstamount;
		}

		public Integer getRatedifflag() {
			return ratedifflag;
		}

		public Integer getIscoldstorage() {
			return iscoldstorage;
		}

		public Integer getIsexpired() {
			return isexpired;
		}

		public Integer getAllowcashdiscount() {
			return allowcashdiscount;
		}

		public BigDecimal getGstamount() {
			return gstamount;
		}

		public Long getFiyearid() {
			return fiyearid;
		}

		public Long getLocationid() {
			return locationid;
		}
	}

}