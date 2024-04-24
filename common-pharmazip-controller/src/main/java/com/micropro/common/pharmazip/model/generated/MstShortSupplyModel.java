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

public class MstShortSupplyModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Shortsupply entity")
	public static final class ShortsupplyBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTSUPPLYID_BODY' (Primary Key)") Long shortsupplyid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERNO_BODY' (required) (Size = 20)") Long orderno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDATE_BODY'") Date orderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 50)") String accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 20)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 50)") String productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASEPACK_BODY' (Size = 18,4)") BigDecimal purchasepack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTY_BODY' (Size = 18,4)") BigDecimal qty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 18,4)") BigDecimal salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMVALUE_BODY' (Size = 18,4)") BigDecimal itemvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACKINGID_BODY' (Size = 1)") Integer packingid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CONVERGENFLAG_BODY' (Size = 1)") String convergenflag;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENT_BODY' (Size = 500)") String comment;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERMODE_BODY' (Size = 1)") Integer vouchermode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BOXPACK_BODY' (Size = 18,4)") BigDecimal boxpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASEPACK_BODY' (Size = 18,4)") BigDecimal casepack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACK_BODY' (Size = 18,4)") BigDecimal pack;

		@JsonCreator
		public ShortsupplyBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("shortsupplyid") Long shortsupplyid,
				@JsonProperty("orderno") Long orderno,
				@JsonProperty("orderdate") @JsonFormat(pattern = "yyyy-MM-dd") Date orderdate,
				@JsonProperty("accountid") String accountid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("agencygroupid") Long agencygroupid,
				@JsonProperty("productid") String productid,
				@JsonProperty("purchasepack") BigDecimal purchasepack,
				@JsonProperty("qty") BigDecimal qty,
				@JsonProperty("salerate") BigDecimal salerate,
				@JsonProperty("itemvalue") BigDecimal itemvalue,
				@JsonProperty("packingid") Integer packingid,
				@JsonProperty("convergenflag") String convergenflag,
				@JsonProperty("active") Integer active,
				@JsonProperty("comment") String comment,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("vouchermode") Integer vouchermode,
				@JsonProperty("boxpack") BigDecimal boxpack,
				@JsonProperty("casepack") BigDecimal casepack,
				@JsonProperty("pack") BigDecimal pack) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.shortsupplyid = Objects.requireNonNull(shortsupplyid, "`shortsupplyid` is required");
			this.orderno = Objects.requireNonNull(orderno, "`orderno` is required");
			this.orderdate = orderdate;
			this.accountid = accountid;
			this.agencyid = agencyid;
			this.agencygroupid = agencygroupid;
			this.productid = productid;
			this.purchasepack = purchasepack;
			this.qty = qty;
			this.salerate = salerate;
			this.itemvalue = itemvalue;
			this.packingid = packingid;
			this.convergenflag = convergenflag;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.comment = comment;
			this.sessionid = sessionid;
			this.description = description;
			this.macinfo = macinfo;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.vouchermode = vouchermode;
			this.boxpack = boxpack;
			this.casepack = casepack;
			this.pack = pack;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getShortsupplyid() {
			return shortsupplyid;
		}

		public Long getOrderno() {
			return orderno;
		}

		public Date getOrderdate() {
			return orderdate;
		}

		public String getAccountid() {
			return accountid;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public String getProductid() {
			return productid;
		}

		public BigDecimal getPurchasepack() {
			return purchasepack;
		}

		public BigDecimal getQty() {
			return qty;
		}

		public BigDecimal getSalerate() {
			return salerate;
		}

		public BigDecimal getItemvalue() {
			return itemvalue;
		}

		public Integer getPackingid() {
			return packingid;
		}

		public String getConvergenflag() {
			return convergenflag;
		}

		public Integer getActive() {
			return active;
		}

		public String getComment() {
			return comment;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getDescription() {
			return description;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getVouchermode() {
			return vouchermode;
		}

		public BigDecimal getBoxpack() {
			return boxpack;
		}

		public BigDecimal getCasepack() {
			return casepack;
		}

		public BigDecimal getPack() {
			return pack;
		}
	}

}