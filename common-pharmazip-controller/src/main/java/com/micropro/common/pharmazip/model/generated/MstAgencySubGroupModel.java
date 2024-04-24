package com.micropro.common.pharmazip.model.generated;

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

public class MstAgencySubGroupModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Agencysubgroup entity")
	public static final class AgencysubgroupBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 5)") Integer agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYSUBGROUPID_BODY' (Primary Key)") Long agencysubgroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 20)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 20)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 20)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") String countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") String stateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE_BODY' (Size = 20)") Long mobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;

		@JsonCreator
		public AgencysubgroupBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("agencygroupid") Integer agencygroupid,
				@JsonProperty("agencysubgroupid") Long agencysubgroupid,
				@JsonProperty("title") String title,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("add1") String add1,
				@JsonProperty("add2") String add2,
				@JsonProperty("countryid") String countryid,
				@JsonProperty("stateid") String stateid,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("mobile") Long mobile,
				@JsonProperty("email") String email,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("custom") String custom) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.agencygroupid = agencygroupid;
			this.agencysubgroupid = Objects.requireNonNull(agencysubgroupid, "`agencysubgroupid` is required");
			this.title = title;
			this.shortname = shortname;
			this.add1 = add1;
			this.add2 = add2;
			this.countryid = countryid;
			this.stateid = stateid;
			this.cityid = cityid;
			this.mobile = mobile;
			this.email = email;
			this.active = active;
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
			this.custom = custom;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Integer getAgencygroupid() {
			return agencygroupid;
		}

		public Long getAgencysubgroupid() {
			return agencysubgroupid;
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

		public Long getMobile() {
			return mobile;
		}

		public String getEmail() {
			return email;
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

		public String getCustom() {
			return custom;
		}
	}

}