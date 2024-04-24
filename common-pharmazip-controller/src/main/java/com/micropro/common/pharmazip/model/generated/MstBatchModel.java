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

public class MstBatchModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Batch entity")
	public static final class BatchBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHID_BODY' (Primary Key)") Long batchid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTID_BODY' (required) (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 30)") String custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 50)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SERIALNO_BODY' (Size = 50)") String serialno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MFGDATE_BODY'") Date mfgdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRYDATE_BODY'") Date expirydate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACKING_BODY' (Size = 50)") String displaypacking;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACK_BODY' (Size = 18,4)") BigDecimal pack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UNITCODE_BODY' (Size = 20)") Long unitcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 18,4)") BigDecimal purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BLOCK_BODY' (Size = 1)") Integer block;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FINYEARCODE_BODY' (Size = 5)") Integer finyearcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BNEW_BODY' (Size = 1)") Integer bnew;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADE_BODY' (Size = 18,4)") BigDecimal trade;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHSEQUENCE_BODY' (Size = 5)") Integer batchsequence;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARKS_BODY' (Size = 500)") String remarks;

		@JsonCreator
		public BatchBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("batchid") Long batchid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("custom") String custom,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("serialno") String serialno,
				@JsonProperty("mfgdate") @JsonFormat(pattern = "yyyy-MM-dd") Date mfgdate,
				@JsonProperty("expirydate") @JsonFormat(pattern = "yyyy-MM-dd") Date expirydate,
				@JsonProperty("displaypacking") String displaypacking,
				@JsonProperty("pack") BigDecimal pack,
				@JsonProperty("unitcode") Long unitcode,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("purchaserate") BigDecimal purchaserate,
				@JsonProperty("block") Integer block,
				@JsonProperty("finyearcode") Integer finyearcode,
				@JsonProperty("bnew") Integer bnew,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("trade") BigDecimal trade,
				@JsonProperty("batchsequence") Integer batchsequence,
				@JsonProperty("remarks") String remarks) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.batchid = Objects.requireNonNull(batchid, "`batchid` is required");
			this.productid = Objects.requireNonNull(productid, "`productid` is required");
			this.custom = custom;
			this.shortname = shortname;
			this.batchno = batchno;
			this.serialno = serialno;
			this.mfgdate = mfgdate;
			this.expirydate = expirydate;
			this.displaypacking = displaypacking;
			this.pack = pack;
			this.unitcode = unitcode;
			this.mrp = mrp;
			this.salerate = salerate;
			this.purchaserate = purchaserate;
			this.block = block;
			this.finyearcode = finyearcode;
			this.bnew = bnew;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.trade = trade;
			this.batchsequence = batchsequence;
			this.remarks = remarks;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getBatchid() {
			return batchid;
		}

		public Long getProductid() {
			return productid;
		}

		public String getCustom() {
			return custom;
		}

		public String getShortname() {
			return shortname;
		}

		public String getBatchno() {
			return batchno;
		}

		public String getSerialno() {
			return serialno;
		}

		public Date getMfgdate() {
			return mfgdate;
		}

		public Date getExpirydate() {
			return expirydate;
		}

		public String getDisplaypacking() {
			return displaypacking;
		}

		public BigDecimal getPack() {
			return pack;
		}

		public Long getUnitcode() {
			return unitcode;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getPurchaserate() {
			return purchaserate;
		}

		public Integer getBlock() {
			return block;
		}

		public Integer getFinyearcode() {
			return finyearcode;
		}

		public Integer getBnew() {
			return bnew;
		}

		public Integer getActive() {
			return active;
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

		public BigDecimal getTrade() {
			return trade;
		}

		public Integer getBatchsequence() {
			return batchsequence;
		}

		public String getRemarks() {
			return remarks;
		}
	}

}