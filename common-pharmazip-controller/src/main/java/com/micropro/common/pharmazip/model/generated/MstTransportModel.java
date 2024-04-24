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

public class MstTransportModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Transport entity")
	public static final class TransportBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTID_BODY' (Primary Key)") Long transportid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTNAME_BODY' (Size = 50)") String transportname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 100)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REGISTRATIONID_BODY' (Size = 20)") Long registrationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS1_BODY' (Size = 50)") String address1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS2_BODY' (Size = 50)") String address2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Size = 20)") Long areaid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 50)") String pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PHONE_BODY' (Size = 50)") String phone;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;

		@JsonCreator
		public TransportBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("transportid") Long transportid,
				@JsonProperty("transportname") String transportname,
				@JsonProperty("comments") String comments,
				@JsonProperty("registrationid") Long registrationid,
				@JsonProperty("address1") String address1,
				@JsonProperty("address2") String address2,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("areaid") Long areaid,
				@JsonProperty("pincode") String pincode,
				@JsonProperty("email") String email,
				@JsonProperty("phone") String phone,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.transportid = Objects.requireNonNull(transportid, "`transportid` is required");
			this.transportname = transportname;
			this.comments = comments;
			this.registrationid = registrationid;
			this.address1 = address1;
			this.address2 = address2;
			this.cityid = cityid;
			this.areaid = areaid;
			this.pincode = pincode;
			this.email = email;
			this.phone = phone;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = active;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTransportid() {
			return transportid;
		}

		public String getTransportname() {
			return transportname;
		}

		public String getComments() {
			return comments;
		}

		public Long getRegistrationid() {
			return registrationid;
		}

		public String getAddress1() {
			return address1;
		}

		public String getAddress2() {
			return address2;
		}

		public Long getCityid() {
			return cityid;
		}

		public Long getAreaid() {
			return areaid;
		}

		public String getPincode() {
			return pincode;
		}

		public String getEmail() {
			return email;
		}

		public String getPhone() {
			return phone;
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

		public Integer getActive() {
			return active;
		}
	}

}