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

public class MstTempModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Temp entity")
	public static final class TempBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TEMPMASTERID_BODY' (required) (Size = 20)") Long tempmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TEMPID_BODY' (Primary Key)") Long tempid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTID_BODY' (required) (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 50)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACK_BODY' (Size = 50)") String displaypack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASEPACK_BODY' (Size = 20)") Long purchasepack;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'BATCHID_BODY' (required) (Size = 20)") Long batchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 50)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRYDATE_BODY'") Date expirydate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 18,4)") BigDecimal mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SRATE_BODY' (Size = 18,4)") BigDecimal srate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTUALSTOCK_BODY' (Size = 18,4)") BigDecimal actualstock;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DIFFQTY_BODY' (Size = 18,4)") BigDecimal diffqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'STOREID_BODY' (required) (Size = 20)") Long storeid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CURRENTSTOCK_BODY' (Size = 18,4)") BigDecimal currentstock;

		@JsonCreator
		public TempBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("tempmasterid") Long tempmasterid,
				@JsonProperty("tempid") Long tempid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("productname") String productname,
				@JsonProperty("displaypack") String displaypack,
				@JsonProperty("purchasepack") Long purchasepack,
				@JsonProperty("batchid") Long batchid,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("expirydate") @JsonFormat(pattern = "yyyy-MM-dd") Date expirydate,
				@JsonProperty("mrp") BigDecimal mrp,
				@JsonProperty("srate") BigDecimal srate,
				@JsonProperty("actualstock") BigDecimal actualstock,
				@JsonProperty("diffqty") BigDecimal diffqty,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("currentstock") BigDecimal currentstock) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.tempmasterid = Objects.requireNonNull(tempmasterid, "`tempmasterid` is required");
			this.tempid = Objects.requireNonNull(tempid, "`tempid` is required");
			this.productid = Objects.requireNonNull(productid, "`productid` is required");
			this.productname = productname;
			this.displaypack = displaypack;
			this.purchasepack = purchasepack;
			this.batchid = Objects.requireNonNull(batchid, "`batchid` is required");
			this.batchno = batchno;
			this.expirydate = expirydate;
			this.mrp = mrp;
			this.srate = srate;
			this.actualstock = actualstock;
			this.diffqty = diffqty;
			this.agencyid = agencyid;
			this.storeid = Objects.requireNonNull(storeid, "`storeid` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.currentstock = currentstock;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTempmasterid() {
			return tempmasterid;
		}

		public Long getTempid() {
			return tempid;
		}

		public Long getProductid() {
			return productid;
		}

		public String getProductname() {
			return productname;
		}

		public String getDisplaypack() {
			return displaypack;
		}

		public Long getPurchasepack() {
			return purchasepack;
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

		public BigDecimal getMrp() {
			return mrp;
		}

		public BigDecimal getSrate() {
			return srate;
		}

		public BigDecimal getActualstock() {
			return actualstock;
		}

		public BigDecimal getDiffqty() {
			return diffqty;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public Long getStoreid() {
			return storeid;
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

		public BigDecimal getCurrentstock() {
			return currentstock;
		}
	}

}