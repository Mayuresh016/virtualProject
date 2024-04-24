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

public class MstAgencyModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Agency entity")
	public static final class AgencyBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Primary Key)") Long agencyid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TITLE_BODY' (required) (Size = 100)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 10)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 100)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 100)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") String countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") String stateid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CITYID_BODY' (required) (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE_BODY' (Size = 30)") String mobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SUPPLIERID_BODY' (Size = 20)") Long supplierid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDAYS_BODY' (Size = 20)") Long orderdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDECODE_BODY' (Size = 50)") String edecode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDEUPLOAD_BODY' (Size = 1)") Integer edeupload;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IMSCODE_BODY' (Size = 20)") String imscode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKIESTMARGIN_BODY' (Size = 18,4)") BigDecimal stockiestmargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RETAILERMARGIN_BODY' (Size = 18,4)") BigDecimal retailermargin;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTEXPIRYRETURNINDAYS_BODY' (Size = 10)") Integer productexpiryreturnindays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOTSALEWITHINEXPIRYDAYS_BODY' (Size = 10)") Integer notsalewithinexpirydays;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 10)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PHONENO_BODY' (Size = 30)") String phoneno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOCKCALCULATIONRATE_BODY' (Size = 1)") Integer stockcalculationrate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 10)") String pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AIOCDAWACSDATAFLAG_BODY' (Size = 1)") Integer aiocdawacsdataflag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 100)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALOCK_BODY' (Size = 1)") Integer alock;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MARKETTEDBY_BODY' (Size = 50)") String markettedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MARKETTEDBYSHORTNAME_BODY' (Size = 10)") String markettedbyshortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AVERAGESALE_BODY' (Size = 18,4)") BigDecimal averagesale;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AVERAGEPURCHASE_BODY' (Size = 18,4)") BigDecimal averagepurchase;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AVERAGERETURN_BODY' (Size = 18,4)") BigDecimal averagereturn;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWEXPIRY_BODY' (Size = 1)") String allowexpiry;

		@JsonCreator
		public AgencyBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("title") String title,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("add1") String add1,
				@JsonProperty("add2") String add2,
				@JsonProperty("countryid") String countryid,
				@JsonProperty("stateid") String stateid,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("mobile") String mobile,
				@JsonProperty("email") String email,
				@JsonProperty("supplierid") Long supplierid,
				@JsonProperty("orderdays") Long orderdays,
				@JsonProperty("edecode") String edecode,
				@JsonProperty("edeupload") Integer edeupload,
				@JsonProperty("imscode") String imscode,
				@JsonProperty("stockiestmargin") BigDecimal stockiestmargin,
				@JsonProperty("retailermargin") BigDecimal retailermargin,
				@JsonProperty("productexpiryreturnindays") Integer productexpiryreturnindays,
				@JsonProperty("notsalewithinexpirydays") Integer notsalewithinexpirydays,
				@JsonProperty("active") Integer active,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("custom") String custom,
				@JsonProperty("phoneno") String phoneno,
				@JsonProperty("stockcalculationrate") Integer stockcalculationrate,
				@JsonProperty("pincode") String pincode,
				@JsonProperty("aiocdawacsdataflag") Integer aiocdawacsdataflag,
				@JsonProperty("comments") String comments,
				@JsonProperty("alock") Integer alock,
				@JsonProperty("markettedby") String markettedby,
				@JsonProperty("markettedbyshortname") String markettedbyshortname,
				@JsonProperty("averagesale") BigDecimal averagesale,
				@JsonProperty("averagepurchase") BigDecimal averagepurchase,
				@JsonProperty("averagereturn") BigDecimal averagereturn,
				@JsonProperty("allowexpiry") String allowexpiry) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.agencyid = Objects.requireNonNull(agencyid, "`agencyid` is required");
			this.title = Objects.requireNonNull(title, "`title` is required");
			this.shortname = shortname;
			this.add1 = add1;
			this.add2 = add2;
			this.countryid = countryid;
			this.stateid = stateid;
			this.cityid = Objects.requireNonNull(cityid, "`cityid` is required");
			this.mobile = mobile;
			this.email = email;
			this.supplierid = supplierid;
			this.orderdays = orderdays;
			this.edecode = edecode;
			this.edeupload = edeupload;
			this.imscode = imscode;
			this.stockiestmargin = stockiestmargin;
			this.retailermargin = retailermargin;
			this.productexpiryreturnindays = productexpiryreturnindays;
			this.notsalewithinexpirydays = notsalewithinexpirydays;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.custom = custom;
			this.phoneno = phoneno;
			this.stockcalculationrate = stockcalculationrate;
			this.pincode = pincode;
			this.aiocdawacsdataflag = aiocdawacsdataflag;
			this.comments = comments;
			this.alock = alock;
			this.markettedby = markettedby;
			this.markettedbyshortname = markettedbyshortname;
			this.averagesale = averagesale;
			this.averagepurchase = averagepurchase;
			this.averagereturn = averagereturn;
			this.allowexpiry = allowexpiry;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public String getTitle() {
			return title;
		}

		public String getShortname() {
			return shortname;
		}

		public String getAdd1() {
			return add1;
		}

		public String getAdd2() {
			return add2;
		}

		public String getCountryid() {
			return countryid;
		}

		public String getStateid() {
			return stateid;
		}

		public Long getCityid() {
			return cityid;
		}

		public String getMobile() {
			return mobile;
		}

		public String getEmail() {
			return email;
		}

		public Long getSupplierid() {
			return supplierid;
		}

		public Long getOrderdays() {
			return orderdays;
		}

		public String getEdecode() {
			return edecode;
		}

		public Integer getEdeupload() {
			return edeupload;
		}

		public String getImscode() {
			return imscode;
		}

		public BigDecimal getStockiestmargin() {
			return stockiestmargin;
		}

		public BigDecimal getRetailermargin() {
			return retailermargin;
		}

		public Integer getProductexpiryreturnindays() {
			return productexpiryreturnindays;
		}

		public Integer getNotsalewithinexpirydays() {
			return notsalewithinexpirydays;
		}

		public Integer getActive() {
			return active;
		}

		public String getLastoperation() {
			return lastoperation;
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

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public String getCustom() {
			return custom;
		}

		public String getPhoneno() {
			return phoneno;
		}

		public Integer getStockcalculationrate() {
			return stockcalculationrate;
		}

		public String getPincode() {
			return pincode;
		}

		public Integer getAiocdawacsdataflag() {
			return aiocdawacsdataflag;
		}

		public String getComments() {
			return comments;
		}

		public Integer getAlock() {
			return alock;
		}

		public String getMarkettedby() {
			return markettedby;
		}

		public String getMarkettedbyshortname() {
			return markettedbyshortname;
		}

		public BigDecimal getAveragesale() {
			return averagesale;
		}

		public BigDecimal getAveragepurchase() {
			return averagepurchase;
		}

		public BigDecimal getAveragereturn() {
			return averagereturn;
		}

		public String getAllowexpiry() {
			return allowexpiry;
		}
	}

}