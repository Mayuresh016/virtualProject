package com.micropro.common.pharmazip.model;

import java.math.BigDecimal;
import java.sql.Date;
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

public class StockShortageModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Stockshortaccess entity")
	public static final class StockshortaccessBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKSHORTACCESSID_BODY' (Primary Key)") Long stockshortaccessid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSACTIONDETAILID_BODY' (Size = 20)") Long transactiondetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 20)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERQTY_BODY' (Size = 18,4)") BigDecimal orderqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RECIEVEDQTY_BODY' (Size = 18,4)") BigDecimal recievedqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DIFFQTY_BODY' (Size = 18,4)") BigDecimal diffqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHID_BODY' (Size = 20)") Long batchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 20)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCODE_BODY' (Size = 20)") Long companycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERTYPE_BODY' (Size = 10)") String vouchertype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJBILLNO_BODY' (Size = 20)") String adjbillno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJBILLDATE_BODY'") Date adjbilldate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJAMOUNT_BODY' (Size = 18,4)") BigDecimal adjamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADJTYPE_BODY' (Size = 5)") String adjtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTAGETYPE_BODY' (Size = 1)") String shortagetype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARRATION_BODY'") String narration;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;

		@JsonCreator
		public StockshortaccessBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("stockshortaccessid") Long stockshortaccessid,
				@JsonProperty("transactiondetailid") Long transactiondetailid,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("productid") Long productid,
				@JsonProperty("orderqty") BigDecimal orderqty,
				@JsonProperty("recievedqty") BigDecimal recievedqty,
				@JsonProperty("diffqty") BigDecimal diffqty,
				@JsonProperty("batchid") Long batchid,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("companycode") Long companycode,
				@JsonProperty("vouchertype") String vouchertype,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("adjbillno") String adjbillno,
				@JsonProperty("adjbilldate") @JsonFormat(pattern = "yyyy-MM-dd") Date adjbilldate,
				@JsonProperty("adjamount") BigDecimal adjamount,
				@JsonProperty("adjtype") String adjtype,
				@JsonProperty("shortagetype") String shortagetype,
				@JsonProperty("narration") String narration,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description) {
			this.orgid = orgid;
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.stockshortaccessid = Objects.requireNonNull(stockshortaccessid, "`stockshortaccessid` is required");
			this.transactiondetailid = transactiondetailid;
			this.voucherno = voucherno;
			this.productid = productid;
			this.orderqty = orderqty;
			this.recievedqty = recievedqty;
			this.diffqty = diffqty;
			this.batchid = batchid;
			this.batchno = batchno;
			this.companycode = companycode;
			this.vouchertype = vouchertype;
			this.billno = billno;
			this.billdate = billdate;
			this.adjbillno = adjbillno;
			this.adjbilldate = adjbilldate;
			this.adjamount = adjamount;
			this.adjtype = adjtype;
			this.shortagetype = shortagetype;
			this.narration = narration;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.lastoperation = lastoperation;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getStockshortaccessid() {
			return stockshortaccessid;
		}

		public Long getTransactiondetailid() {
			return transactiondetailid;
		}

		public String getVoucherno() {
			return voucherno;
		}

		public Long getProductid() {
			return productid;
		}

		public BigDecimal getOrderqty() {
			return orderqty;
		}

		public BigDecimal getRecievedqty() {
			return recievedqty;
		}

		public BigDecimal getDiffqty() {
			return diffqty;
		}

		public Long getBatchid() {
			return batchid;
		}

		public String getBatchno() {
			return batchno;
		}

		public Long getCompanycode() {
			return companycode;
		}

		public String getVouchertype() {
			return vouchertype;
		}

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public String getAdjbillno() {
			return adjbillno;
		}

		public Date getAdjbilldate() {
			return adjbilldate;
		}

		public BigDecimal getAdjamount() {
			return adjamount;
		}

		public String getAdjtype() {
			return adjtype;
		}

		public String getShortagetype() {
			return shortagetype;
		}

		public String getNarration() {
			return narration;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
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
	}

}