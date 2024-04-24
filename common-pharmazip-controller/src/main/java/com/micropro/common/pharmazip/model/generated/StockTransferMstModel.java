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

public class StockTransferMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a StockTransferMst entity")
	public static final class StockTransferMstBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'STOCKTRANSFERMASTER_BODY' (required)") StocktransfermasterBody stocktransfermaster;

		@JsonCreator
		public StockTransferMstBody(
				@JsonProperty("stocktransfermaster") StocktransfermasterBody stocktransfermaster) {
			this.stocktransfermaster = Objects.requireNonNull(stocktransfermaster, "`stocktransfermaster` is required");
		}

		public StocktransfermasterBody getStocktransfermaster() {
			return stocktransfermaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Stocktransfermaster entity")
	public static final class StocktransfermasterBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKTRANSFERMASTERID_BODY' (Primary Key)") Long stocktransfermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REQUESTDATE_BODY'") Date requestdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REQUESTSERIES_BODY' (Size = 50)") String requestseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REQUESTNO_BODY' (Size = 50)") String requestno;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'VOUCHERTYPE_BODY' (required) (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SOURCESTOREID_BODY' (Size = 20)") Long sourcestoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SOURCENAME_BODY' (Size = 50)") String sourcename;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TARGETSTOREID_BODY' (Size = 20)") Long targetstoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TARGETNAME_BODY' (Size = 50)") String targetname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPROVEDDATE_BODY'") Date approveddate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPROVEDSERIES_BODY' (Size = 50)") String approvedseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPROVEDNO_BODY' (Size = 50)") String approvedno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INWARDDATE_BODY'") Date inwarddate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INWARDSERIES_BODY' (Size = 50)") String inwardseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INWARDNO_BODY' (Size = 50)") String inwardno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALQTY_BODY' (Size = 18,4)") BigDecimal totalqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALITEM_BODY' (Size = 5)") Integer totalitem;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AVARAGEVALUE_BODY' (Size = 18,4)") BigDecimal avaragevalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATUS_BODY' (Size = 1)") Integer status;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'STOCKTRANSFERDETAIL_BODY' (required)") List<StocktransferdetailBody> stocktransferdetail;

		@JsonCreator
		public StocktransfermasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("stocktransfermasterid") Long stocktransfermasterid,
				@JsonProperty("requestdate") @JsonFormat(pattern = "yyyy-MM-dd") Date requestdate,
				@JsonProperty("requestseries") String requestseries,
				@JsonProperty("requestno") String requestno,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("sourcestoreid") Long sourcestoreid,
				@JsonProperty("sourcename") String sourcename,
				@JsonProperty("targetstoreid") Long targetstoreid,
				@JsonProperty("targetname") String targetname,
				@JsonProperty("approveddate") @JsonFormat(pattern = "yyyy-MM-dd") Date approveddate,
				@JsonProperty("approvedseries") String approvedseries,
				@JsonProperty("approvedno") String approvedno,
				@JsonProperty("inwarddate") @JsonFormat(pattern = "yyyy-MM-dd") Date inwarddate,
				@JsonProperty("inwardseries") String inwardseries,
				@JsonProperty("inwardno") String inwardno,
				@JsonProperty("totalqty") BigDecimal totalqty,
				@JsonProperty("totalitem") Integer totalitem,
				@JsonProperty("avaragevalue") BigDecimal avaragevalue,
				@JsonProperty("status") Integer status,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("stocktransferdetail") List<StocktransferdetailBody> stocktransferdetail) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.stocktransfermasterid = Objects.requireNonNull(stocktransfermasterid, "`stocktransfermasterid` is required");
			this.requestdate = requestdate;
			this.requestseries = requestseries;
			this.requestno = requestno;
			this.vouchertype = Objects.requireNonNull(vouchertype, "`vouchertype` is required");
			this.sourcestoreid = sourcestoreid;
			this.sourcename = sourcename;
			this.targetstoreid = targetstoreid;
			this.targetname = targetname;
			this.approveddate = approveddate;
			this.approvedseries = approvedseries;
			this.approvedno = approvedno;
			this.inwarddate = inwarddate;
			this.inwardseries = inwardseries;
			this.inwardno = inwardno;
			this.totalqty = totalqty;
			this.totalitem = totalitem;
			this.avaragevalue = avaragevalue;
			this.status = status;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
			this.stocktransferdetail = stocktransferdetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getStocktransfermasterid() {
			return stocktransfermasterid;
		}

		public Date getRequestdate() {
			return requestdate;
		}

		public String getRequestseries() {
			return requestseries;
		}

		public String getRequestno() {
			return requestno;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public Long getSourcestoreid() {
			return sourcestoreid;
		}

		public String getSourcename() {
			return sourcename;
		}

		public Long getTargetstoreid() {
			return targetstoreid;
		}

		public String getTargetname() {
			return targetname;
		}

		public Date getApproveddate() {
			return approveddate;
		}

		public String getApprovedseries() {
			return approvedseries;
		}

		public String getApprovedno() {
			return approvedno;
		}

		public Date getInwarddate() {
			return inwarddate;
		}

		public String getInwardseries() {
			return inwardseries;
		}

		public String getInwardno() {
			return inwardno;
		}

		public BigDecimal getTotalqty() {
			return totalqty;
		}

		public Integer getTotalitem() {
			return totalitem;
		}

		public BigDecimal getAvaragevalue() {
			return avaragevalue;
		}

		public Integer getStatus() {
			return status;
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

		public List<StocktransferdetailBody> getStocktransferdetail() {
			return stocktransferdetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Stocktransferdetail entity")
	public static final class StocktransferdetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'STOCKTRANSFERMASTERID_BODY' (required) (Size = 20)") Long stocktransfermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKTRANSFERDETAILID_BODY' (Primary Key)") Long stocktransferdetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 200)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BOXPACK_BODY' (Size = 18,4)") BigDecimal boxpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACK_BODY' (Size = 50)") String displaypack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHID_BODY' (Size = 20)") Long batchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 50)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRYDATE_BODY'") Date expirydate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEID_BODY' (Size = 20)") Long rateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADE_BODY' (Size = 18,4)") BigDecimal trade;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 18,4)") BigDecimal purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MINQTY_BODY' (Size = 18,4)") BigDecimal minqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXQTY_BODY' (Size = 18,4)") BigDecimal maxqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTUALQTY_BODY' (Size = 18,4)") BigDecimal actualqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REQUESTQTY_BODY' (Size = 18,4)") BigDecimal requestqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPROVEDQTY_BODY' (Size = 18,4)") BigDecimal approvedqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INWARDQTY_BODY' (Size = 18,4)") BigDecimal inwardqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SOURCESTOREID_BODY' (Size = 20)") Long sourcestoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SOURCELOCATIONID_BODY' (Size = 20)") Long sourcelocationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TARGETSTOREID_BODY' (Size = 20)") Long targetstoreid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TARGETLOCATIONID_BODY' (Size = 20)") Long targetlocationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENT_BODY' (Size = 100)") String comment;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARK_BODY' (Size = 100)") String remark;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLOSING_BODY' (Size = 18,4)") BigDecimal closing;

		@JsonCreator
		public StocktransferdetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("stocktransfermasterid") Long stocktransfermasterid,
				@JsonProperty("stocktransferdetailid") Long stocktransferdetailid,
				@JsonProperty("productname") String productname,
				@JsonProperty("productid") Long productid,
				@JsonProperty("boxpack") BigDecimal boxpack,
				@JsonProperty("displaypack") String displaypack,
				@JsonProperty("batchid") Long batchid,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("expirydate") @JsonFormat(pattern = "yyyy-MM-dd") Date expirydate,
				@JsonProperty("rateid") Long rateid,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("trade") BigDecimal trade,
				@JsonProperty("purchaserate") BigDecimal purchaserate,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("minqty") BigDecimal minqty,
				@JsonProperty("maxqty") BigDecimal maxqty,
				@JsonProperty("actualqty") BigDecimal actualqty,
				@JsonProperty("requestqty") BigDecimal requestqty,
				@JsonProperty("approvedqty") BigDecimal approvedqty,
				@JsonProperty("inwardqty") BigDecimal inwardqty,
				@JsonProperty("sourcestoreid") Long sourcestoreid,
				@JsonProperty("sourcelocationid") Long sourcelocationid,
				@JsonProperty("targetstoreid") Long targetstoreid,
				@JsonProperty("targetlocationid") Long targetlocationid,
				@JsonProperty("comment") String comment,
				@JsonProperty("remark") String remark,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("closing") BigDecimal closing) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.stocktransfermasterid = Objects.requireNonNull(stocktransfermasterid, "`stocktransfermasterid` is required");
			this.stocktransferdetailid = Objects.requireNonNull(stocktransferdetailid, "`stocktransferdetailid` is required");
			this.productname = productname;
			this.productid = productid;
			this.boxpack = boxpack;
			this.displaypack = displaypack;
			this.batchid = batchid;
			this.batchno = batchno;
			this.expirydate = expirydate;
			this.rateid = rateid;
			this.mrp = mrp;
			this.trade = trade;
			this.purchaserate = purchaserate;
			this.salerate = salerate;
			this.minqty = minqty;
			this.maxqty = maxqty;
			this.actualqty = actualqty;
			this.requestqty = requestqty;
			this.approvedqty = approvedqty;
			this.inwardqty = inwardqty;
			this.sourcestoreid = sourcestoreid;
			this.sourcelocationid = sourcelocationid;
			this.targetstoreid = targetstoreid;
			this.targetlocationid = targetlocationid;
			this.comment = comment;
			this.remark = remark;
			this.active = active;
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
			this.vouchertype = vouchertype;
			this.closing = closing;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getStocktransfermasterid() {
			return stocktransfermasterid;
		}

		public Long getStocktransferdetailid() {
			return stocktransferdetailid;
		}

		public String getProductname() {
			return productname;
		}

		public Long getProductid() {
			return productid;
		}

		public BigDecimal getBoxpack() {
			return boxpack;
		}

		public String getDisplaypack() {
			return displaypack;
		}

		public Long getBatchid() {
			return batchid;
		}

		public String getBatchno() {
			return batchno;
		}

		public Date getExpirydate() {
			return expirydate;
		}

		public Long getRateid() {
			return rateid;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getTrade() {
			return trade;
		}

		public BigDecimal getPurchaserate() {
			return purchaserate;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getMinqty() {
			return minqty;
		}

		public BigDecimal getMaxqty() {
			return maxqty;
		}

		public BigDecimal getActualqty() {
			return actualqty;
		}

		public BigDecimal getRequestqty() {
			return requestqty;
		}

		public BigDecimal getApprovedqty() {
			return approvedqty;
		}

		public BigDecimal getInwardqty() {
			return inwardqty;
		}

		public Long getSourcestoreid() {
			return sourcestoreid;
		}

		public Long getSourcelocationid() {
			return sourcelocationid;
		}

		public Long getTargetstoreid() {
			return targetstoreid;
		}

		public Long getTargetlocationid() {
			return targetlocationid;
		}

		public String getComment() {
			return comment;
		}

		public String getRemark() {
			return remark;
		}

		public Integer getActive() {
			return active;
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

		public String getVouchertype() {
			return vouchertype;
		}

		public BigDecimal getClosing() {
			return closing;
		}
	}

}