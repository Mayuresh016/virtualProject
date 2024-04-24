package com.micropro.common.pharmazip.model.generated;

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

public class MstProductSchemeModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Productscheme entity")
	public static final class ProductschemeBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 20)") Long orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 20)") Long oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTSCHEMEID_BODY' (Primary Key)") Long productschemeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMETYPE_BODY' (Size = 1)") Integer schemetype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYONE_BODY' (Size = 18,4)") BigDecimal qtyone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEONE_BODY' (Size = 18,4)") BigDecimal freeone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEPRODUCTIDONE_BODY' (Size = 20)") Long freeproductidone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYTWO_BODY' (Size = 18,4)") BigDecimal qtytwo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREETWO_BODY' (Size = 18,4)") BigDecimal freetwo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEPRODUCTIDTWO_BODY' (Size = 20)") Long freeproductidtwo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QTYTHREE_BODY' (Size = 18,4)") BigDecimal qtythree;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREETHREE_BODY' (Size = 18,4)") BigDecimal freethree;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEPRODUCTIDTHREE_BODY' (Size = 20)") Long freeproductidthree;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FROMDATE_BODY'") Date fromdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TODATE_BODY'") Date todate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CAPPER_BODY' (Size = 18,4)") BigDecimal capper;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CAPQTY_BODY' (Size = 18,4)") BigDecimal capqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKED_BODY' (Size = 11)") Integer locked;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEDISCOUNTPER_BODY' (Size = 18,4)") BigDecimal schemediscountper;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BALQTY_BODY' (Size = 20)") Integer balqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 500)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLOSINGSTOCK_BODY' (Size = 18,4)") BigDecimal closingstock;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEFLAG_BODY' (Size = 1)") String schemeflag;

		@JsonCreator
		public ProductschemeBody(
				@JsonProperty("orgid") Long orgid,
				@JsonProperty("oprid") Long oprid,
				@JsonProperty("productschemeid") Long productschemeid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("schemetype") Integer schemetype,
				@JsonProperty("qtyone") BigDecimal qtyone,
				@JsonProperty("freeone") BigDecimal freeone,
				@JsonProperty("freeproductidone") Long freeproductidone,
				@JsonProperty("qtytwo") BigDecimal qtytwo,
				@JsonProperty("freetwo") BigDecimal freetwo,
				@JsonProperty("freeproductidtwo") Long freeproductidtwo,
				@JsonProperty("qtythree") BigDecimal qtythree,
				@JsonProperty("freethree") BigDecimal freethree,
				@JsonProperty("freeproductidthree") Long freeproductidthree,
				@JsonProperty("fromdate") @JsonFormat(pattern = "yyyy-MM-dd") Date fromdate,
				@JsonProperty("todate") @JsonFormat(pattern = "yyyy-MM-dd") Date todate,
				@JsonProperty("capper") BigDecimal capper,
				@JsonProperty("capqty") BigDecimal capqty,
				@JsonProperty("locked") Integer locked,
				@JsonProperty("schemediscountper") BigDecimal schemediscountper,
				@JsonProperty("active") Integer active,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("balqty") Integer balqty,
				@JsonProperty("title") String title,
				@JsonProperty("closingstock") BigDecimal closingstock,
				@JsonProperty("schemeflag") String schemeflag) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.productschemeid = Objects.requireNonNull(productschemeid, "`productschemeid` is required");
			this.productid = productid;
			this.schemetype = schemetype;
			this.qtyone = qtyone;
			this.freeone = freeone;
			this.freeproductidone = freeproductidone;
			this.qtytwo = qtytwo;
			this.freetwo = freetwo;
			this.freeproductidtwo = freeproductidtwo;
			this.qtythree = qtythree;
			this.freethree = freethree;
			this.freeproductidthree = freeproductidthree;
			this.fromdate = fromdate;
			this.todate = todate;
			this.capper = capper;
			this.capqty = capqty;
			this.locked = locked;
			this.schemediscountper = schemediscountper;
			this.active = active;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.balqty = balqty;
			this.title = title;
			this.closingstock = closingstock;
			this.schemeflag = schemeflag;
		}

		public Long getOrgid() {
			return orgid;
		}

		public Long getOprid() {
			return oprid;
		}

		public Long getProductschemeid() {
			return productschemeid;
		}

		public Long getProductid() {
			return productid;
		}

		public Integer getSchemetype() {
			return schemetype;
		}

		public BigDecimal getQtyone() {
			return qtyone;
		}

		public BigDecimal getFreeone() {
			return freeone;
		}

		public Long getFreeproductidone() {
			return freeproductidone;
		}

		public BigDecimal getQtytwo() {
			return qtytwo;
		}

		public BigDecimal getFreetwo() {
			return freetwo;
		}

		public Long getFreeproductidtwo() {
			return freeproductidtwo;
		}

		public BigDecimal getQtythree() {
			return qtythree;
		}

		public BigDecimal getFreethree() {
			return freethree;
		}

		public Long getFreeproductidthree() {
			return freeproductidthree;
		}

		public Date getFromdate() {
			return fromdate;
		}

		public Date getTodate() {
			return todate;
		}

		public BigDecimal getCapper() {
			return capper;
		}

		public BigDecimal getCapqty() {
			return capqty;
		}

		public Integer getLocked() {
			return locked;
		}

		public BigDecimal getSchemediscountper() {
			return schemediscountper;
		}

		public Integer getActive() {
			return active;
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

		public Integer getBalqty() {
			return balqty;
		}

		public String getTitle() {
			return title;
		}

		public BigDecimal getClosingstock() {
			return closingstock;
		}

		public String getSchemeflag() {
			return schemeflag;
		}
	}

}