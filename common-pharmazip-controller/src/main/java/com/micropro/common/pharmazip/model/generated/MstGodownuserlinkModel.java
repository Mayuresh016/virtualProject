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

public class MstGodownuserlinkModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Godownuserlink entity")
	public static final class GodownuserlinkBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GODOWNUSERLINKID_BODY' (Primary Key)") Long godownuserlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOREID_BODY' (Size = 20)") Long storeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADUSERMSTID_BODY' (Size = 100)") Long adusermstid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADUMNAME_BODY' (Size = 20)") String adumname;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 20)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;

		@JsonCreator
		public GodownuserlinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("godownuserlinkid") Long godownuserlinkid,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("adusermstid") Long adusermstid,
				@JsonProperty("adumname") String adumname,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("description") String description,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.godownuserlinkid = Objects.requireNonNull(godownuserlinkid, "`godownuserlinkid` is required");
			this.storeid = storeid;
			this.adusermstid = adusermstid;
			this.adumname = adumname;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
			this.lastoperation = lastoperation;
			this.active = active;
			this.description = description;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getGodownuserlinkid() {
			return godownuserlinkid;
		}

		public Long getStoreid() {
			return storeid;
		}

		public Long getAdusermstid() {
			return adusermstid;
		}

		public String getAdumname() {
			return adumname;
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

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getActive() {
			return active;
		}

		public String getDescription() {
			return description;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}
	}

}