package com.micropro.common.pharmazip.model.generated;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
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

public class MstProductModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstProduct entity")
	public static final class MstProductBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCT_BODY' (required)") ProductBody product;

		@JsonCreator
		public MstProductBody(
				@JsonProperty("product") ProductBody product) {
			this.product = Objects.requireNonNull(product, "`product` is required");
		}

		public ProductBody getProduct() {
			return product;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Product entity")
	public static final class ProductBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Primary Key)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 50)") Integer custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BARCODE_BODY' (Size = 20)") String barcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SAP_BODY' (Size = 50)") String sap;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IMS_BODY' (Size = 20)") String ims;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDEID_BODY' (Size = 20)") String edeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 500)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 250)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPLAYPACKING_BODY' (Size = 20)") String displaypacking;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PACK_BODY' (Size = 10)") Integer pack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASEPACK_BODY' (Size = 18,4)") BigDecimal purchasepack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BOXPACK_BODY' (Size = 18,4)") BigDecimal boxpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASEPACK_BODY' (Size = 18,4)") BigDecimal casepack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCICYID_BODY' (Size = 20)") Long agencicyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'HSNCODE_BODY' (Size = 10)") String hsncode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTTYPEID_BODY' (Size = 6)") Integer producttypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWCASHDISCOUNT_BODY' (Size = 1)") Integer allowcashdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXCASHDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal maxcashdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MARKETTEDBY_BODY' (Size = 100)") String markettedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 20)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DRUGTYPEID_BODY' (Size = 20)") Long drugtypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GENERIC_BODY' (Size = 1)") Integer generic;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ANTITB_BODY' (Size = 1)") Integer antitb;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARCOTICS_BODY' (Size = 1)") Integer narcotics;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXID_BODY' (Size = 20)") Long taxid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETAILERMARGIN_BODY' (Size = 18,4)") BigDecimal retailermargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKIESTMARGIN_BODY' (Size = 18,4)") BigDecimal stockiestmargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UNDERDPCO_BODY' (Size = 1)") Integer underdpco;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 250)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VALUATION_BODY' (Size = 11)") Integer valuation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDEAREA_BODY' (Size = 50)") String edearea;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLDSTORAGE_BODY' (Size = 1)") Integer coldstorage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CONTENTID_BODY' (Size = 20)") Long contentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXCATEGORY_BODY' (Size = 1)") String taxcategory;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMTYPE_BODY' (Size = 1)") Integer itemtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UPLOADFLAG_BODY' (Size = 1)") Integer uploadflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMIDENTITY_BODY' (Size = 1)") Integer itemidentity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOSTOCKPRODUCT_BODY' (Size = 1)") Integer nostockproduct;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEFAULTBATCHID_BODY' (Size = 20)") Long defaultbatchid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PENDINGQTY_BODY' (Size = 18,4)") BigDecimal pendingqty;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTOPRDTL_BODY' (required)") List<ProductoprdtlBody> productoprdtl;

		@JsonCreator
		public ProductBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("custom") Integer custom,
				@JsonProperty("barcode") String barcode,
				@JsonProperty("sap") String sap,
				@JsonProperty("ims") String ims,
				@JsonProperty("edeid") String edeid,
				@JsonProperty("productname") String productname,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("displaypacking") String displaypacking,
				@JsonProperty("pack") Integer pack,
				@JsonProperty("purchasepack") BigDecimal purchasepack,
				@JsonProperty("boxpack") BigDecimal boxpack,
				@JsonProperty("casepack") BigDecimal casepack,
				@JsonProperty("agencicyid") Long agencicyid,
				@JsonProperty("hsncode") String hsncode,
				@JsonProperty("producttypeid") Integer producttypeid,
				@JsonProperty("allowcashdiscount") Integer allowcashdiscount,
				@JsonProperty("maxcashdiscountpercent") BigDecimal maxcashdiscountpercent,
				@JsonProperty("markettedby") String markettedby,
				@JsonProperty("agencygroupid") Long agencygroupid,
				@JsonProperty("drugtypeid") Long drugtypeid,
				@JsonProperty("generic") Integer generic,
				@JsonProperty("antitb") Integer antitb,
				@JsonProperty("narcotics") Integer narcotics,
				@JsonProperty("taxid") Long taxid,
				@JsonProperty("retailermargin") BigDecimal retailermargin,
				@JsonProperty("stockiestmargin") BigDecimal stockiestmargin,
				@JsonProperty("underdpco") Integer underdpco,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("valuation") Integer valuation,
				@JsonProperty("edearea") String edearea,
				@JsonProperty("coldstorage") Integer coldstorage,
				@JsonProperty("contentid") Long contentid,
				@JsonProperty("taxcategory") String taxcategory,
				@JsonProperty("itemtype") Integer itemtype,
				@JsonProperty("uploadflag") Integer uploadflag,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("itemidentity") Integer itemidentity,
				@JsonProperty("nostockproduct") Integer nostockproduct,
				@JsonProperty("active") Integer active,
				@JsonProperty("defaultbatchid") Long defaultbatchid,
				@JsonProperty("pendingqty") BigDecimal pendingqty,
				@JsonProperty("productoprdtl") List<ProductoprdtlBody> productoprdtl) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.productid = Objects.requireNonNull(productid, "`productid` is required");
			this.custom = custom;
			this.barcode = barcode;
			this.sap = sap;
			this.ims = ims;
			this.edeid = edeid;
			this.productname = productname;
			this.shortname = shortname;
			this.displaypacking = displaypacking;
			this.pack = pack;
			this.purchasepack = purchasepack;
			this.boxpack = boxpack;
			this.casepack = casepack;
			this.agencicyid = agencicyid;
			this.hsncode = hsncode;
			this.producttypeid = producttypeid;
			this.allowcashdiscount = allowcashdiscount;
			this.maxcashdiscountpercent = maxcashdiscountpercent;
			this.markettedby = markettedby;
			this.agencygroupid = agencygroupid;
			this.drugtypeid = drugtypeid;
			this.generic = generic;
			this.antitb = antitb;
			this.narcotics = narcotics;
			this.taxid = taxid;
			this.retailermargin = retailermargin;
			this.stockiestmargin = stockiestmargin;
			this.underdpco = underdpco;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.description = description;
			this.sessionid = sessionid;
			this.valuation = valuation;
			this.edearea = edearea;
			this.coldstorage = coldstorage;
			this.contentid = contentid;
			this.taxcategory = taxcategory;
			this.itemtype = itemtype;
			this.uploadflag = uploadflag;
			this.lastoperation = lastoperation;
			this.itemidentity = itemidentity;
			this.nostockproduct = nostockproduct;
			this.active = active;
			this.defaultbatchid = defaultbatchid;
			this.pendingqty = pendingqty;
			this.productoprdtl = productoprdtl;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Long getProductid() {
			return productid;
		}

		public Integer getCustom() {
			return custom;
		}

		public String getBarcode() {
			return barcode;
		}

		public String getSap() {
			return sap;
		}

		public String getIms() {
			return ims;
		}

		public String getEdeid() {
			return edeid;
		}

		public String getProductname() {
			return productname;
		}

		public String getShortname() {
			return shortname;
		}

		public String getDisplaypacking() {
			return displaypacking;
		}

		public Integer getPack() {
			return pack;
		}

		public BigDecimal getPurchasepack() {
			return purchasepack;
		}

		public BigDecimal getBoxpack() {
			return boxpack;
		}

		public BigDecimal getCasepack() {
			return casepack;
		}

		public Long getAgencicyid() {
			return agencicyid;
		}

		public String getHsncode() {
			return hsncode;
		}

		public Integer getProducttypeid() {
			return producttypeid;
		}

		public Integer getAllowcashdiscount() {
			return allowcashdiscount;
		}

		public BigDecimal getMaxcashdiscountpercent() {
			return maxcashdiscountpercent;
		}

		public String getMarkettedby() {
			return markettedby;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public Long getDrugtypeid() {
			return drugtypeid;
		}

		public Integer getGeneric() {
			return generic;
		}

		public Integer getAntitb() {
			return antitb;
		}

		public Integer getNarcotics() {
			return narcotics;
		}

		public Long getTaxid() {
			return taxid;
		}

		public BigDecimal getRetailermargin() {
			return retailermargin;
		}

		public BigDecimal getStockiestmargin() {
			return stockiestmargin;
		}

		public Integer getUnderdpco() {
			return underdpco;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public String getSessionid() {
			return sessionid;
		}

		public Integer getValuation() {
			return valuation;
		}

		public String getEdearea() {
			return edearea;
		}

		public Integer getColdstorage() {
			return coldstorage;
		}

		public Long getContentid() {
			return contentid;
		}

		public String getTaxcategory() {
			return taxcategory;
		}

		public Integer getItemtype() {
			return itemtype;
		}

		public Integer getUploadflag() {
			return uploadflag;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getItemidentity() {
			return itemidentity;
		}

		public Integer getNostockproduct() {
			return nostockproduct;
		}

		public Integer getActive() {
			return active;
		}

		public Long getDefaultbatchid() {
			return defaultbatchid;
		}

		public BigDecimal getPendingqty() {
			return pendingqty;
		}

		public List<ProductoprdtlBody> getProductoprdtl() {
			return productoprdtl;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Productoprdtl entity")
	public static final class ProductoprdtlBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTID_BODY' (required) (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTOPRDTLID_BODY' (Primary Key)") Long productoprdtlid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MINSTOCKQUANTITY_BODY' (Size = 5)") Integer minstockquantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXSTOCKQUANITY_BODY' (Size = 5)") Integer maxstockquanity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHELFMINQUANTITY_BODY' (Size = 5)") Integer shelfminquantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHELFMAXQUANTITY_BODY' (Size = 5)") Integer shelfmaxquantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTMOVEDATE_BODY'") Date lastmovedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTPURCHASEDATE_BODY'") Timestamp lastpurchasedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTSALEDATE_BODY'") Timestamp lastsaledate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTSALERATE_BODY' (Size = 18,4)") BigDecimal lastsalerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTMRP_BODY' (Size = 18,4)") BigDecimal lastmrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTPURCHASERATE_BODY' (Size = 18,4)") BigDecimal lastpurchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHOWONLINE_BODY' (Size = 1)") Integer showonline;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AVERAGELANDINGRATE_BODY' (Size = 18,4)") BigDecimal averagelandingrate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOVEMENT_BODY' (Size = 1)") Integer movement;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PREFERENCE_BODY' (Size = 1)") Integer preference;
		private @ApiModelProperty(required = false, value = "REST representation of the 'KEEPWATCH_BODY' (Size = 1)") Integer keepwatch;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REORDERLEVEL_BODY' (Size = 5)") Integer reorderlevel;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REORDERQUANTITY_BODY' (Size = 5)") Integer reorderquantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWSCHEME_BODY' (Size = 1)") Integer allowscheme;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CURRENTSTOCK_BODY' (Size = 5)") Integer currentstock;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKED_BODY' (Size = 1)") Integer locked;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONID_BODY' (Size = 20)") Long locationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXCASHDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal maxcashdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETAILERMARGIN_BODY' (Size = 18,4)") BigDecimal retailermargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKIESTMARGIN_BODY' (Size = 18,4)") BigDecimal stockiestmargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEZEMRP_BODY' (Size = 1)") Integer freezemrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWNEGATIVE_BODY' (Size = 1)") Integer allownegative;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREEZESALERATE_BODY' (Size = 1)") Integer freezesalerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MINSALEQUANTITY_BODY' (Size = 5)") Integer minsalequantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXSALEQUANTITY_BODY' (Size = 5)") Integer maxsalequantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXPRODUCTDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal maxproductdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWSPECIALQUANTITY_BODY' (Size = 1)") Integer allowspecialquantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLORCODE_BODY' (Size = 100)") String colorcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STORETRANSFERQTY_BODY' (Size = 18,4)") BigDecimal storetransferqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWEXPBRK_BODY' (Size = 1)") Integer allowexpbrk;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 500)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPLIANCE_BODY' (Size = 1)") Integer compliance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPLIANCEDESC_BODY' (Size = 250)") String compliancedesc;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWPRODUCTDISCOUNT_BODY' (Size = 1)") Integer allowproductdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ONLINESTOCKPERCENTLIMIT_BODY' (Size = 18,4)") BigDecimal onlinestockpercentlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESSPERCENTAGE_BODY' (Size = 18,4)") BigDecimal cesspercentage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONALCESSAMOUNT_BODY' (Size = 18,4)") BigDecimal additionalcessamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALIASNAME_BODY' (Size = 500)") String aliasname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTSALEQTY_BODY' (Size = 18,4)") BigDecimal lastsaleqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTPURCHASEQTY_BODY' (Size = 18,4)") BigDecimal lastpurchaseqty;

		@JsonCreator
		public ProductoprdtlBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("productoprdtlid") Long productoprdtlid,
				@JsonProperty("minstockquantity") Integer minstockquantity,
				@JsonProperty("maxstockquanity") Integer maxstockquanity,
				@JsonProperty("shelfminquantity") Integer shelfminquantity,
				@JsonProperty("shelfmaxquantity") Integer shelfmaxquantity,
				@JsonProperty("lastmovedate") @JsonFormat(pattern = "yyyy-MM-dd") Date lastmovedate,
				@JsonProperty("lastpurchasedate") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp lastpurchasedate,
				@JsonProperty("lastsaledate") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp lastsaledate,
				@JsonProperty("lastsalerate") BigDecimal lastsalerate,
				@JsonProperty("lastmrp") BigDecimal lastmrp,
				@JsonProperty("lastpurchaserate") BigDecimal lastpurchaserate,
				@JsonProperty("showonline") Integer showonline,
				@JsonProperty("averagelandingrate") BigDecimal averagelandingrate,
				@JsonProperty("movement") Integer movement,
				@JsonProperty("preference") Integer preference,
				@JsonProperty("keepwatch") Integer keepwatch,
				@JsonProperty("reorderlevel") Integer reorderlevel,
				@JsonProperty("reorderquantity") Integer reorderquantity,
				@JsonProperty("allowscheme") Integer allowscheme,
				@JsonProperty("currentstock") Integer currentstock,
				@JsonProperty("locked") Integer locked,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("locationid") Long locationid,
				@JsonProperty("maxcashdiscountpercent") BigDecimal maxcashdiscountpercent,
				@JsonProperty("retailermargin") BigDecimal retailermargin,
				@JsonProperty("stockiestmargin") BigDecimal stockiestmargin,
				@JsonProperty("freezemrp") Integer freezemrp,
				@JsonProperty("allownegative") Integer allownegative,
				@JsonProperty("freezesalerate") Integer freezesalerate,
				@JsonProperty("minsalequantity") Integer minsalequantity,
				@JsonProperty("maxsalequantity") Integer maxsalequantity,
				@JsonProperty("maxproductdiscountpercent") BigDecimal maxproductdiscountpercent,
				@JsonProperty("allowspecialquantity") Integer allowspecialquantity,
				@JsonProperty("colorcode") String colorcode,
				@JsonProperty("storetransferqty") BigDecimal storetransferqty,
				@JsonProperty("allowexpbrk") Integer allowexpbrk,
				@JsonProperty("comments") String comments,
				@JsonProperty("active") Integer active,
				@JsonProperty("compliance") Integer compliance,
				@JsonProperty("compliancedesc") String compliancedesc,
				@JsonProperty("allowproductdiscount") Integer allowproductdiscount,
				@JsonProperty("onlinestockpercentlimit") BigDecimal onlinestockpercentlimit,
				@JsonProperty("cesspercentage") BigDecimal cesspercentage,
				@JsonProperty("additionalcessamount") BigDecimal additionalcessamount,
				@JsonProperty("aliasname") String aliasname,
				@JsonProperty("lastsaleqty") BigDecimal lastsaleqty,
				@JsonProperty("lastpurchaseqty") BigDecimal lastpurchaseqty) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.productid = Objects.requireNonNull(productid, "`productid` is required");
			this.productoprdtlid = Objects.requireNonNull(productoprdtlid, "`productoprdtlid` is required");
			this.minstockquantity = minstockquantity;
			this.maxstockquanity = maxstockquanity;
			this.shelfminquantity = shelfminquantity;
			this.shelfmaxquantity = shelfmaxquantity;
			this.lastmovedate = lastmovedate;
			this.lastpurchasedate = lastpurchasedate;
			this.lastsaledate = lastsaledate;
			this.lastsalerate = lastsalerate;
			this.lastmrp = lastmrp;
			this.lastpurchaserate = lastpurchaserate;
			this.showonline = showonline;
			this.averagelandingrate = averagelandingrate;
			this.movement = movement;
			this.preference = preference;
			this.keepwatch = keepwatch;
			this.reorderlevel = reorderlevel;
			this.reorderquantity = reorderquantity;
			this.allowscheme = allowscheme;
			this.currentstock = currentstock;
			this.locked = locked;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.description = description;
			this.lastoperation = lastoperation;
			this.locationid = locationid;
			this.maxcashdiscountpercent = maxcashdiscountpercent;
			this.retailermargin = retailermargin;
			this.stockiestmargin = stockiestmargin;
			this.freezemrp = freezemrp;
			this.allownegative = allownegative;
			this.freezesalerate = freezesalerate;
			this.minsalequantity = minsalequantity;
			this.maxsalequantity = maxsalequantity;
			this.maxproductdiscountpercent = maxproductdiscountpercent;
			this.allowspecialquantity = allowspecialquantity;
			this.colorcode = colorcode;
			this.storetransferqty = storetransferqty;
			this.allowexpbrk = allowexpbrk;
			this.comments = comments;
			this.active = active;
			this.compliance = compliance;
			this.compliancedesc = compliancedesc;
			this.allowproductdiscount = allowproductdiscount;
			this.onlinestockpercentlimit = onlinestockpercentlimit;
			this.cesspercentage = cesspercentage;
			this.additionalcessamount = additionalcessamount;
			this.aliasname = aliasname;
			this.lastsaleqty = lastsaleqty;
			this.lastpurchaseqty = lastpurchaseqty;
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

		public Long getProductoprdtlid() {
			return productoprdtlid;
		}

		public Integer getMinstockquantity() {
			return minstockquantity;
		}

		public Integer getMaxstockquanity() {
			return maxstockquanity;
		}

		public Integer getShelfminquantity() {
			return shelfminquantity;
		}

		public Integer getShelfmaxquantity() {
			return shelfmaxquantity;
		}

		public Date getLastmovedate() {
			return lastmovedate;
		}

		public Timestamp getLastpurchasedate() {
			return lastpurchasedate;
		}

		public Timestamp getLastsaledate() {
			return lastsaledate;
		}

		public BigDecimal getLastsalerate() {
			return lastsalerate;
		}

		public BigDecimal getLastmrp() {
			return lastmrp;
		}

		public BigDecimal getLastpurchaserate() {
			return lastpurchaserate;
		}

		public Integer getShowonline() {
			return showonline;
		}

		public BigDecimal getAveragelandingrate() {
			return averagelandingrate;
		}

		public Integer getMovement() {
			return movement;
		}

		public Integer getPreference() {
			return preference;
		}

		public Integer getKeepwatch() {
			return keepwatch;
		}

		public Integer getReorderlevel() {
			return reorderlevel;
		}

		public Integer getReorderquantity() {
			return reorderquantity;
		}

		public Integer getAllowscheme() {
			return allowscheme;
		}

		public Integer getCurrentstock() {
			return currentstock;
		}

		public Integer getLocked() {
			return locked;
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

		public Long getLocationid() {
			return locationid;
		}

		public BigDecimal getMaxcashdiscountpercent() {
			return maxcashdiscountpercent;
		}

		public BigDecimal getRetailermargin() {
			return retailermargin;
		}

		public BigDecimal getStockiestmargin() {
			return stockiestmargin;
		}

		public Integer getFreezemrp() {
			return freezemrp;
		}

		public Integer getAllownegative() {
			return allownegative;
		}

		public Integer getFreezesalerate() {
			return freezesalerate;
		}

		public Integer getMinsalequantity() {
			return minsalequantity;
		}

		public Integer getMaxsalequantity() {
			return maxsalequantity;
		}

		public BigDecimal getMaxproductdiscountpercent() {
			return maxproductdiscountpercent;
		}

		public Integer getAllowspecialquantity() {
			return allowspecialquantity;
		}

		public String getColorcode() {
			return colorcode;
		}

		public BigDecimal getStoretransferqty() {
			return storetransferqty;
		}

		public Integer getAllowexpbrk() {
			return allowexpbrk;
		}

		public String getComments() {
			return comments;
		}

		public Integer getActive() {
			return active;
		}

		public Integer getCompliance() {
			return compliance;
		}

		public String getCompliancedesc() {
			return compliancedesc;
		}

		public Integer getAllowproductdiscount() {
			return allowproductdiscount;
		}

		public BigDecimal getOnlinestockpercentlimit() {
			return onlinestockpercentlimit;
		}

		public BigDecimal getCesspercentage() {
			return cesspercentage;
		}

		public BigDecimal getAdditionalcessamount() {
			return additionalcessamount;
		}

		public String getAliasname() {
			return aliasname;
		}

		public BigDecimal getLastsaleqty() {
			return lastsaleqty;
		}

		public BigDecimal getLastpurchaseqty() {
			return lastpurchaseqty;
		}
	}

}