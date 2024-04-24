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

public class MstAreaModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Area entity")
	public static final class AreaBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Primary Key)") Long areaid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CITYID_BODY' (required) (Size = 20)") Long cityid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TITLE_BODY' (required) (Size = 50)") String title;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SEQUENCENO_BODY' (required) (Size = 20)") Integer sequenceno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 6)") Integer pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDEPINCODE_BODY' (Size = 500)") String edepincode;

		@JsonCreator
		public AreaBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("areaid") Long areaid,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("title") String title,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sequenceno") Integer sequenceno,
				@JsonProperty("pincode") Integer pincode,
				@JsonProperty("edepincode") String edepincode) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.areaid = Objects.requireNonNull(areaid, "`areaid` is required");
			this.cityid = Objects.requireNonNull(cityid, "`cityid` is required");
			this.title = Objects.requireNonNull(title, "`title` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.sequenceno = Objects.requireNonNull(sequenceno, "`sequenceno` is required");
			this.pincode = pincode;
			this.edepincode = edepincode;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Long getAreaid() {
			return areaid;
		}

		public Long getCityid() {
			return cityid;
		}

		public String getTitle() {
			return title;
		}

		public Integer getActive() {
			return active;
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

		public Integer getSequenceno() {
			return sequenceno;
		}

		public Integer getPincode() {
			return pincode;
		}

		public String getEdepincode() {
			return edepincode;
		}
	}

}