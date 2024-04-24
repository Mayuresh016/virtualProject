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

public class MstAgencyGroupModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Agencygroup entity")
	public static final class AgencygroupBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Primary Key)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 30)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 10)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 30)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 30)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") String countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") String stateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE_BODY' (Size = 20)") Long mobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 10)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GROUPCODE_BODY' (Size = 5)") String groupcode;

		@JsonCreator
		public AgencygroupBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("agencygroupid") Long agencygroupid,
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
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("custom") String custom,
				@JsonProperty("groupcode") String groupcode) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.agencyid = agencyid;
			this.agencygroupid = Objects.requireNonNull(agencygroupid, "`agencygroupid` is required");
			this.title = title;
			this.shortname = shortname;
			this.add1 = add1;
			this.add2 = add2;
			this.countryid = countryid;
			this.stateid = stateid;
			this.cityid = cityid;
			this.mobile = mobile;
			this.email = email;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
			this.custom = custom;
			this.groupcode = groupcode;
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

		public Long getAgencygroupid() {
			return agencygroupid;
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

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public Long getModifiedby() {
			return modifiedby;
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

		public String getCustom() {
			return custom;
		}

		public String getGroupcode() {
			return groupcode;
		}
	}

}