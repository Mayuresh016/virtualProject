package com.micropro.common.pharmazip.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micropro.common.pharmazip.model.generated.MstTempModel.TempBody;
import com.micropro.common.pharmazip.model.generated.TrnMasterModel.TransactionmasterBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class CustomModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Custom entity")
	public static final class CustomBody {
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OLDBATCHID_BODY' (required)") Long oldbatchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHID_BODY' (required)") Long batchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRYDATE_BODY'") Date expirydate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 18,4)") BigDecimal purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADE_BODY' (Size = 18,4)") BigDecimal trade;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEID_BODY' (Primary Key)") Long rateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 50)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OLDBATCHNO_BODY' (Size = 50)") String oldbatchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QUANTITY_BODY' (Size = 50)") Long quantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACK_BODY' (Size = 50)") String displaypacking;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACK_BODY' (Size = 18,4)") BigDecimal pack;
		private final @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MFGDATE_BODY'") Date mfgdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BLOCK_BODY' (Size = 1)") Integer block;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKED_BODY' (Size = 1)") Integer locked;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTION_BODY' (Size = 1)") Integer action;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY'  (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANMASTER_BODY'  (Size = 20)") Long transactionmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NETAMOUNT_BODY' (Size = 18,4)") BigDecimal netamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PENDING_BODY' (Size = 18,4)") BigDecimal pending;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXID_BODY' (Size = 20)") Long taxid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'HSNTAXLINKID_BODY'") Long hsntaxlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHSEQUENCE_BODY' (Size = 5)") Integer batchsequence;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARKS_BODY' (Size = 500)") String remarks;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TEMP_BODY'") List<TempBody> tempBody;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TEMPMASTERID_BODY'(Size = 20)") Long tempmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDEBITSTATUS_BODY' (Size = 1)") String creditdebitstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FROM_DATE_BODY'") Date fromdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TO_DATE_BODY'") Date todate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 50)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHARGE_AMOUNT_BODY' (Size = 18,4)") BigDecimal chargeamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTUAL_CHARGE_AMOUNT_BODY' (Size = 18,4)") BigDecimal actualchargeamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DIFF_AMOUNT_BODY' (Size = 18,4)") BigDecimal diffamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONMASTER_BODY'") List<TransactionmasterBody> transactionmaster;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTSERIALNO_BODY' (Size = 10)") Integer accountserialno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREA_FLAG_BODY' (Size = 10)") String areaflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INTERNAL_ID_BODY' (Size = 10)") Long internalid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTSERIALNO_BODY' (Size = 10)") Integer sequenceno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Primary Key)") Long areaid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDAYS_BODY' (Size = 6)") Integer creditdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Primary Key)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDCRDAYS_BODY' (Size = 20)") Long addcrdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDBILLLIMIT_BODY' (Size = 20)") Long addbilllimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDCRLIMIT_BODY' (Size = 18,4)") BigDecimal addcrlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHECK_FLAG_BODY' (Size = 10)") String checkflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEBITNOTEAMOUNT_BODY' (Size = 18,4)") BigDecimal debitnoteamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTEAMOUNT_BODY' (Size = 18,4)") BigDecimal creditnoteamount;
		

		// tempmasterid
		@JsonCreator
		public CustomBody(@JsonProperty("orgid") Integer orgid, @JsonProperty("oprid") Integer oprid,
				@JsonProperty("batchid") Long batchid, @JsonProperty("productid") Long productid,
				@JsonProperty("expirydate") @JsonFormat(pattern = "yyyy-MM-dd") Date expirydate,
				@JsonProperty("mrp") BigDecimal mrp, @JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("purchaserate") BigDecimal purchaserate, @JsonProperty("trade") BigDecimal trade,
				@JsonProperty("lastoperation") String lastoperation, @JsonProperty("rateid") Long rateid,
				@JsonProperty("shortname") String shortname, @JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("batchno") String batchno, @JsonProperty("quantity") Long quantity,
				@JsonProperty("oldbatchid") Long oldbatchid, @JsonProperty("oldbatchno") String oldbatchno,
				@JsonProperty("displaypacking") String displaypacking, @JsonProperty("pack") BigDecimal pack,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("mfgdate") @JsonFormat(pattern = "yyyy-MM-dd") Date mfgdate,
				@JsonProperty("block") Integer block, @JsonProperty("locked") Integer locked,
				@JsonProperty("action") Integer action, @JsonProperty("accountid") Long accountid,
				@JsonProperty("transactionmasterid") Long transactionmasterid,
				@JsonProperty("netamount") BigDecimal netamount, @JsonProperty("pending") BigDecimal pending,
				@JsonProperty("taxid") Long taxid, @JsonProperty("hsntaxlinkid") Long hsntaxlinkid,
				@JsonProperty("active") Integer active, @JsonProperty("batchsequence") Integer batchsequence,
				@JsonProperty("remarks") String remarks, @JsonProperty("tempbody") List<TempBody> tempBody,
				@JsonProperty("tempmasterid") Long tempmasterid,
				@JsonProperty("creditdebitstatus") String creditdebitstatus,
				@JsonProperty("transactionmaster") List<TransactionmasterBody> transactionmaster,
				@JsonProperty("fromdate") @JsonFormat(pattern = "yyyy-MM-dd") Date fromdate,
				@JsonProperty("todate") @JsonFormat(pattern = "yyyy-MM-dd") Date todate,
				@JsonProperty("productname") String productname, @JsonProperty("chargeamount") BigDecimal chargeamount,
				@JsonProperty("actualchargeamount") BigDecimal actualchargeamount,
				@JsonProperty("diffamount") BigDecimal diffamount, @JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("accountserialno") Integer accountserialno, @JsonProperty("areaflag") String areaflag,
				@JsonProperty("internalid") Long internalid, @JsonProperty("sequenceno") Integer sequenceno,
				@JsonProperty("areaid") Long areaid, @JsonProperty("creditdays") Integer creditdays,
				@JsonProperty("agencyid") Long agencyid ,@JsonProperty("agencygroupid") Long agencygroupid , @JsonProperty("voucherdate") Date voucherdate , @JsonProperty("addcrdays") Long addcrdays,
				@JsonProperty("addbilllimit") Long addbilllimit,@JsonProperty("addcrlimit") BigDecimal addcrlimit,@JsonProperty("checkflag") String checkflag,@JsonProperty("debitnoteamount") BigDecimal debitnoteamount,
				@JsonProperty("creditnoteamount") BigDecimal creditnoteamount) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			// this.batchid = Objects.requireNonNull(batchid, "`batchid` is required");
			// this.productid = Objects.requireNonNull(productid, "`productid` is
			// required");
			this.productid = productid;
			this.batchid = batchid;
			this.expirydate = expirydate;
			this.mrp = mrp;
			this.salerate = salerate;
			this.purchaserate = purchaserate;
			this.trade = trade;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.rateid = rateid;
			this.shortname = shortname;
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.batchno = batchno;
			this.quantity = quantity;
			this.oldbatchid = oldbatchid;
			this.oldbatchno = oldbatchno;
			this.displaypacking = displaypacking;
			this.pack = pack;
			this.createdby = createdby;
			this.createdon = createdon;
			this.mfgdate = mfgdate;
			this.block = block;
			this.locked = locked;
			this.action = action;
			this.accountid = accountid;
			this.transactionmasterid = transactionmasterid;
			this.netamount = netamount;
			this.pending = pending;
			this.taxid = taxid;
			this.hsntaxlinkid = hsntaxlinkid;
			this.active = active;
			this.batchsequence = batchsequence;
			this.remarks = remarks;
			this.tempBody = tempBody;
			this.tempmasterid = tempmasterid;
			this.creditdebitstatus = creditdebitstatus;
			this.transactionmaster = transactionmaster;
			this.fromdate = fromdate;
			this.productname = productname;
			this.chargeamount = chargeamount;
			this.actualchargeamount = actualchargeamount;
			this.diffamount = diffamount;
			this.todate = todate;
			this.vouchertype = vouchertype;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.accountserialno = accountserialno;
			this.areaflag = areaflag;
			this.internalid = internalid;
			this.sequenceno = sequenceno;
			this.areaid = areaid;
			this.creditdays = creditdays;
			this.agencyid = agencyid;
			this.agencygroupid = agencygroupid;
			this.voucherdate = voucherdate;
			this.addcrdays = addcrdays;
			this.addbilllimit = addbilllimit;
			this.addcrlimit = addcrlimit;
			this.checkflag = checkflag;
			this.debitnoteamount = debitnoteamount;
			this.creditnoteamount = creditnoteamount;
		}

		public BigDecimal getDebitnoteamount() {
			return debitnoteamount;
		}

		public void setDebitnoteamount(BigDecimal debitnoteamount) {
			this.debitnoteamount = debitnoteamount;
		}

		public BigDecimal getCreditnoteamount() {
			return creditnoteamount;
		}

		public void setCreditnoteamount(BigDecimal creditnoteamount) {
			this.creditnoteamount = creditnoteamount;
		}

		public String getCheckflag() {
			return checkflag;
		}

		public void setCheckflag(String checkflag) {
			this.checkflag = checkflag;
		}

		public Long getBatchid() {
			return batchid;
		}

		public void setBatchid(Long batchid) {
			this.batchid = batchid;
		}

		public Date getExpirydate() {
			return expirydate;
		}

		public void setExpirydate(Date expirydate) {
			this.expirydate = expirydate;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public void setMrp(BigDecimal mrp) {
			this.mrp = mrp;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public void setSalerate(BigDecimal salerate) {
			this.salerate = salerate;
		}

		public BigDecimal getPurchaserate() {
			return purchaserate;
		}

		public void setPurchaserate(BigDecimal purchaserate) {
			this.purchaserate = purchaserate;
		}

		public BigDecimal getTrade() {
			return trade;
		}

		public void setTrade(BigDecimal trade) {
			this.trade = trade;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getProductid() {
			return productid;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Long getRateid() {
			return rateid;
		}

		public void setRateid(Long rateid) {
			this.rateid = rateid;
		}

		public String getShortname() {
			return shortname;
		}

		public void setShortname(String shortname) {
			this.shortname = shortname;
		}

		public Long getModifiedby() {
			return modifiedby;
		}

		public void setModifiedby(Long modifiedby) {
			this.modifiedby = modifiedby;
		}

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public void setModifiedon(Timestamp modifiedon) {
			this.modifiedon = modifiedon;
		}

		public String getBatchno() {
			return batchno;
		}

		public void setBatchno(String batchno) {
			this.batchno = batchno;
		}

		public Long getQuantity() {
			return quantity;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}

		public Long getOldbatchid() {
			return oldbatchid;
		}

		public void setOldbatchid(Long oldbatchid) {
			this.oldbatchid = oldbatchid;
		}

		public String getOldbatchno() {
			return oldbatchno;
		}

		public void setOldbatchno(String oldbatchno) {
			this.oldbatchno = oldbatchno;
		}

		public String getDisplaypacking() {
			return displaypacking;
		}

		public void setDisplaypacking(String displaypacking) {
			this.displaypacking = displaypacking;
		}

		public BigDecimal getPack() {
			return pack;
		}

		public void setPack(BigDecimal pack) {
			this.pack = pack;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Date getMfgdate() {
			return mfgdate;
		}

		public void setMfgdate(Date mfgdate) {
			this.mfgdate = mfgdate;
		}

		public Integer getBlock() {
			return block;
		}

		public void setBlock(Integer block) {
			this.block = block;
		}

		public Integer getLocked() {
			return locked;
		}

		public void setLocked(Integer locked) {
			this.locked = locked;
		}

		public Integer getAction() {
			return action;
		}

		public void setAction(Integer action) {
			this.action = action;
		}

		public Long getAccountid() {
			return accountid;
		}

		public void setAccountid(Long accountid) {
			this.accountid = accountid;
		}

		public Long getTransactionmasterid() {
			return transactionmasterid;
		}

		public void setTransactionmasterid(Long transactionmasterid) {
			this.transactionmasterid = transactionmasterid;
		}

		public BigDecimal getNetamount() {
			return netamount;
		}

		public void setNetamount(BigDecimal netamount) {
			this.netamount = netamount;
		}

		public BigDecimal getPending() {
			return pending;
		}

		public void setPending(BigDecimal pending) {
			this.pending = pending;
		}

		public Long getTaxid() {
			return taxid;
		}

		public void setTaxid(Long taxid) {
			this.taxid = taxid;
		}

		public Long getHsntaxlinkid() {
			return hsntaxlinkid;
		}

		public void setHsntaxlinkid(Long hsntaxlinkid) {
			this.hsntaxlinkid = hsntaxlinkid;
		}

		public Integer getActive() {
			return active;
		}

		public void setActive(Integer active) {
			this.active = active;
		}

		public Integer getBatchsequence() {
			return batchsequence;
		}

		public void setBatchsequence(Integer batchsequence) {
			this.batchsequence = batchsequence;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public List<TempBody> getTempBody() {
			return tempBody;
		}

		public void setTempBody(List<TempBody> tempBody) {
			this.tempBody = tempBody;
		}

		public Long getTempmasterid() {
			return tempmasterid;
		}

		public void setTempmasterid(Long tempmasterid) {
			this.tempmasterid = tempmasterid;
		}

		public String getCreditdebitstatus() {
			return creditdebitstatus;
		}

		public void setCreditdebitstatus(String creditdebitstatus) {
			this.creditdebitstatus = creditdebitstatus;
		}

		public List<TransactionmasterBody> getTransactionmaster() {
			return transactionmaster;
		}

		public void setTransactionmaster(List<TransactionmasterBody> transactionmaster) {
			this.transactionmaster = transactionmaster;
		}

		public Date getFromdate() {
			return fromdate;
		}

		public Date getTodate() {
			return todate;
		}

		public void setFromdate(Date fromdate) {
			this.fromdate = fromdate;
		}

		public void setTodate(Date todate) {
			this.todate = todate;
		}

		public String getProductname() {
			return productname;
		}

		public void setProductname(String productname) {
			this.productname = productname;
		}

		public BigDecimal getChargeamount() {
			return chargeamount;
		}

		public BigDecimal getActualchargeamount() {
			return actualchargeamount;
		}

		public void setChargeamount(BigDecimal chargeamount) {
			this.chargeamount = chargeamount;
		}

		public void setActualchargeamount(BigDecimal actualchargeamount) {
			this.actualchargeamount = actualchargeamount;
		}

		public BigDecimal getDiffamount() {
			return diffamount;
		}

		public void setDiffamount(BigDecimal diffamount) {
			this.diffamount = diffamount;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public void setVouchertype(String vouchertype) {
			this.vouchertype = vouchertype;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public void setModifyby(Long modifyby) {
			this.modifyby = modifyby;
		}

		public void setModifyon(Timestamp modifyon) {
			this.modifyon = modifyon;
		}

		public Integer getAccountserialno() {
			return accountserialno;
		}

		public void setProductid(Long productid) {
			this.productid = productid;
		}

		public void setAccountserialno(Integer accountserialno) {
			this.accountserialno = accountserialno;
		}

		public String getAreaflag() {
			return areaflag;
		}

		public void setAreaflag(String areaflag) {
			this.areaflag = areaflag;
		}

		public Long getInternalid() {
			return internalid;
		}

		public void setInternalid(Long internalid) {
			this.internalid = internalid;
		}

		public Integer getSequenceno() {
			return sequenceno;
		}

		public void setSequenceno(Integer sequenceno) {
			this.sequenceno = sequenceno;
		}

		public Long getAreaid() {
			return areaid;
		}

		public void setAreaid(Long areaid) {
			this.areaid = areaid;
		}

		public Integer getCreditdays() {
			return creditdays;
		}

		public void setCreditdays(Integer creditdays) {
			this.creditdays = creditdays;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public void setAgencyid(Long agencyid) {
			this.agencyid = agencyid;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public void setAgencygroupid(Long agencygroupid) {
			this.agencygroupid = agencygroupid;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public void setVoucherdate(Date voucherdate) {
			this.voucherdate = voucherdate;
		}

		public Long getAddcrdays() {
			return addcrdays;
		}

		public void setAddcrdays(Long addcrdays) {
			this.addcrdays = addcrdays;
		}

		public Long getAddbilllimit() {
			return addbilllimit;
		}

		public void setAddbilllimit(Long addbilllimit) {
			this.addbilllimit = addbilllimit;
		}

		public BigDecimal getAddcrlimit() {
			return addcrlimit;
		}

		public void setAddcrlimit(BigDecimal addcrlimit) {
			this.addcrlimit = addcrlimit;
		}
		

	}
}
