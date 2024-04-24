package com.micropro.common.pharmazip.model.generated;

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

public class MstRateModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Rate entity")
	public static final class RateBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATEID_BODY' (Primary Key)") Long rateid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTID_BODY' (required) (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADERATE_BODY' (Size = 18,4)") BigDecimal traderate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 18,4)") BigDecimal purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ARATE_BODY' (Size = 18,4)") BigDecimal arate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BRATE_BODY' (Size = 18,4)") BigDecimal brate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CRATE_BODY' (Size = 18,4)") BigDecimal crate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DRATE_BODY' (Size = 18,4)") BigDecimal drate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NRVRATE_BODY' (Size = 18,4)") BigDecimal nrvrate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BLOCK_BODY' (Size = 1)") Integer block;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RNEW_BODY' (Size = 1)") Integer rnew;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECATEGORYCODE_BODY' (Size = 20)") Integer ratecategorycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public RateBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("rateid") Long rateid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("traderate") BigDecimal traderate,
				@JsonProperty("purchaserate") BigDecimal purchaserate,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("arate") BigDecimal arate,
				@JsonProperty("brate") BigDecimal brate,
				@JsonProperty("crate") BigDecimal crate,
				@JsonProperty("drate") BigDecimal drate,
				@JsonProperty("nrvrate") BigDecimal nrvrate,
				@JsonProperty("block") Integer block,
				@JsonProperty("rnew") Integer rnew,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("ratecategorycode") Integer ratecategorycode,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.rateid = Objects.requireNonNull(rateid, "`rateid` is required");
			this.productid = Objects.requireNonNull(productid, "`productid` is required");
			this.mrp = mrp;
			this.traderate = traderate;
			this.purchaserate = purchaserate;
			this.salerate = salerate;
			this.arate = arate;
			this.brate = brate;
			this.crate = crate;
			this.drate = drate;
			this.nrvrate = nrvrate;
			this.block = block;
			this.rnew = rnew;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.ratecategorycode = ratecategorycode;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getRateid() {
			return rateid;
		}

		public Long getProductid() {
			return productid;
		}

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getTraderate() {
			return traderate;
		}

		public BigDecimal getPurchaserate() {
			return purchaserate;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getArate() {
			return arate;
		}

		public BigDecimal getBrate() {
			return brate;
		}

		public BigDecimal getCrate() {
			return crate;
		}

		public BigDecimal getDrate() {
			return drate;
		}

		public BigDecimal getNrvrate() {
			return nrvrate;
		}

		public Integer getBlock() {
			return block;
		}

		public Integer getRnew() {
			return rnew;
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

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Integer getRatecategorycode() {
			return ratecategorycode;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getDescription() {
			return description;
		}

		public String getLastoperation() {
			return lastoperation;
		}
	}

}