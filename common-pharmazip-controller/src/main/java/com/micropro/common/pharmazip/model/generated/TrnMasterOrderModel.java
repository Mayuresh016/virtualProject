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

public class TrnMasterOrderModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a TrnMasterOrder entity")
	public static final class TrnMasterOrderBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONORDERMASTER_BODY' (required)") TransactionordermasterBody transactionordermaster;

		@JsonCreator
		public TrnMasterOrderBody(
				@JsonProperty("transactionordermaster") TransactionordermasterBody transactionordermaster) {
			this.transactionordermaster = Objects.requireNonNull(transactionordermaster, "`transactionordermaster` is required");
		}

		public TransactionordermasterBody getTransactionordermaster() {
			return transactionordermaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Transactionordermaster entity")
	public static final class TransactionordermasterBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONORDERMASTERID_BODY' (Primary Key)") Long transactionordermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 20)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTID_BODY' (Size = 20)") Long agentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERNAME_BODY' (Size = 50)") String customername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD1_BODY' (Size = 100)") String customeradd1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD2_BODY' (Size = 100)") String customeradd2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERADD3_BODY' (Size = 100)") String customeradd3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NETAMOUNT_BODY' (Size = 18,4)") BigDecimal netamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTMODE_BODY' (Size = 1)") Integer paymentmode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CANFLAG_BODY' (Size = 1)") Integer canflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMCOUNT_BODY' (Size = 6)") Integer itemcount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYCOUNT_BODY' (Size = 6)") Integer qtycount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERCREATEDBY_BODY' (Size = 20)") Long mobileordercreatedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERNO_BODY' (Size = 30)") String mobileorderno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERDATE_BODY'") Date mobileorderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'POST_BODY' (Size = 1)") Integer post;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCEID_BODY' (Size = 1)") Integer referenceid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REASON_BODY' (Size = 50)") String reason;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERTIME_BODY' (Size = 50)") String mobileordertime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEIMEINO_BODY' (Size = 50)") String mobileimeino;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILEORDERTYPE_BODY' (Size = 1)") Integer mobileordertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSTATUS_BODY' (Size = 1)") Integer orderstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARATION_BODY' (Size = 50)") String naration;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONORDERDETAIL_BODY' (required)") List<TransactionorderdetailBody> transactionorderdetail;

		@JsonCreator
		public TransactionordermasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("transactionordermasterid") Long transactionordermasterid,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("agentid") Long agentid,
				@JsonProperty("customername") String customername,
				@JsonProperty("customeradd1") String customeradd1,
				@JsonProperty("customeradd2") String customeradd2,
				@JsonProperty("customeradd3") String customeradd3,
				@JsonProperty("netamount") BigDecimal netamount,
				@JsonProperty("paymentmode") Integer paymentmode,
				@JsonProperty("canflag") Integer canflag,
				@JsonProperty("itemcount") Integer itemcount,
				@JsonProperty("qtycount") Integer qtycount,
				@JsonProperty("mobileordercreatedby") Long mobileordercreatedby,
				@JsonProperty("mobileorderno") String mobileorderno,
				@JsonProperty("mobileorderdate") @JsonFormat(pattern = "yyyy-MM-dd") Date mobileorderdate,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("post") Integer post,
				@JsonProperty("referenceid") Integer referenceid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("reason") String reason,
				@JsonProperty("mobileordertime") String mobileordertime,
				@JsonProperty("mobileimeino") String mobileimeino,
				@JsonProperty("mobileordertype") Integer mobileordertype,
				@JsonProperty("orderstatus") Integer orderstatus,
				@JsonProperty("naration") String naration,
				@JsonProperty("transactionorderdetail") List<TransactionorderdetailBody> transactionorderdetail) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.transactionordermasterid = Objects.requireNonNull(transactionordermasterid, "`transactionordermasterid` is required");
			this.voucherno = voucherno;
			this.accountid = accountid;
			this.voucherdate = voucherdate;
			this.voucherseries = voucherseries;
			this.agentid = agentid;
			this.customername = customername;
			this.customeradd1 = customeradd1;
			this.customeradd2 = customeradd2;
			this.customeradd3 = customeradd3;
			this.netamount = netamount;
			this.paymentmode = paymentmode;
			this.canflag = canflag;
			this.itemcount = itemcount;
			this.qtycount = qtycount;
			this.mobileordercreatedby = mobileordercreatedby;
			this.mobileorderno = mobileorderno;
			this.mobileorderdate = mobileorderdate;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = lastoperation;
			this.active = active;
			this.vouchertype = vouchertype;
			this.vouchermode = vouchermode;
			this.post = post;
			this.referenceid = referenceid;
			this.agencyid = agencyid;
			this.reason = reason;
			this.mobileordertime = mobileordertime;
			this.mobileimeino = mobileimeino;
			this.mobileordertype = mobileordertype;
			this.orderstatus = orderstatus;
			this.naration = naration;
			this.transactionorderdetail = transactionorderdetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTransactionordermasterid() {
			return transactionordermasterid;
		}

		public String getVoucherno() {
			return voucherno;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public Long getAgentid() {
			return agentid;
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

		public BigDecimal getNetamount() {
			return netamount;
		}

		public Integer getPaymentmode() {
			return paymentmode;
		}

		public Integer getCanflag() {
			return canflag;
		}

		public Integer getItemcount() {
			return itemcount;
		}

		public Integer getQtycount() {
			return qtycount;
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

		public Integer getPost() {
			return post;
		}

		public Integer getReferenceid() {
			return referenceid;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public String getReason() {
			return reason;
		}

		public String getMobileordertime() {
			return mobileordertime;
		}

		public String getMobileimeino() {
			return mobileimeino;
		}

		public Integer getMobileordertype() {
			return mobileordertype;
		}

		public Integer getOrderstatus() {
			return orderstatus;
		}

		public String getNaration() {
			return naration;
		}

		public List<TransactionorderdetailBody> getTransactionorderdetail() {
			return transactionorderdetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Transactionorderdetail entity")
	public static final class TransactionorderdetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONORDERMASTERID_BODY' (required) (Size = 20)") Long transactionordermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONORDERDETAILID_BODY' (Primary Key)") Long transactionorderdetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 10)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 50)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTY_BODY' (Size = 18,4)") BigDecimal qty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREE_BODY' (Size = 18,4)") BigDecimal free;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMVALUE_BODY' (Size = 18,4)") BigDecimal itemvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTPACK_BODY' (Size = 18,4)") BigDecimal productpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SRNO_BODY' (Size = 6)") Integer srno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCODE_BODY' (Size = 20)") Long companycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TNEW_BODY' (Size = 1)") Integer tnew;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSTATUS_BODY' (Size = 1)") Integer orderstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACKING_BODY' (Size = 20)") String displaypacking;

		@JsonCreator
		public TransactionorderdetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("transactionordermasterid") Long transactionordermasterid,
				@JsonProperty("transactionorderdetailid") Long transactionorderdetailid,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("productid") Long productid,
				@JsonProperty("productname") String productname,
				@JsonProperty("qty") BigDecimal qty,
				@JsonProperty("free") BigDecimal free,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("itemvalue") BigDecimal itemvalue,
				@JsonProperty("productpack") BigDecimal productpack,
				@JsonProperty("srno") Integer srno,
				@JsonProperty("companycode") Long companycode,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("active") Integer active,
				@JsonProperty("tnew") Integer tnew,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("orderstatus") Integer orderstatus,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("displaypacking") String displaypacking) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.transactionordermasterid = Objects.requireNonNull(transactionordermasterid, "`transactionordermasterid` is required");
			this.transactionorderdetailid = Objects.requireNonNull(transactionorderdetailid, "`transactionorderdetailid` is required");
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.voucherdate = voucherdate;
			this.productid = productid;
			this.productname = productname;
			this.qty = qty;
			this.free = free;
			this.salerate = salerate;
			this.mrp = mrp;
			this.itemvalue = itemvalue;
			this.productpack = productpack;
			this.srno = srno;
			this.companycode = companycode;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.active = active;
			this.tnew = tnew;
			this.vouchertype = vouchertype;
			this.vouchermode = vouchermode;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.orderstatus = orderstatus;
			this.shortname = shortname;
			this.displaypacking = displaypacking;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTransactionordermasterid() {
			return transactionordermasterid;
		}

		public Long getTransactionorderdetailid() {
			return transactionorderdetailid;
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

		public BigDecimal getQty() {
			return qty;
		}

		public BigDecimal getFree() {
			return free;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getItemvalue() {
			return itemvalue;
		}

		public BigDecimal getProductpack() {
			return productpack;
		}

		public Integer getSrno() {
			return srno;
		}

		public Long getCompanycode() {
			return companycode;
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

		public Integer getActive() {
			return active;
		}

		public Integer getTnew() {
			return tnew;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public Integer getVouchermode() {
			return vouchermode;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getOrderstatus() {
			return orderstatus;
		}

		public String getShortname() {
			return shortname;
		}

		public String getDisplaypacking() {
			return displaypacking;
		}
	}

}