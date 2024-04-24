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

public class MstStateModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a State entity")
	public static final class StateBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Primary Key)") Long stateid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'COUNTRYID_BODY' (required) (Size = 20)") Long countryid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TITLE_BODY' (required) (Size = 50)") String title;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'STATENO_BODY' (required) (Size = 5)") Integer stateno;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISUT_BODY' (Size = 1)") Integer isut;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SEQUENCENO_BODY' (required) (Size = 20)") Integer sequenceno;

		@JsonCreator
		public StateBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("stateid") Long stateid,
				@JsonProperty("countryid") Long countryid,
				@JsonProperty("title") String title,
				@JsonProperty("stateno") Integer stateno,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("isut") Integer isut,
				@JsonProperty("sequenceno") Integer sequenceno) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.stateid = Objects.requireNonNull(stateid, "`stateid` is required");
			this.countryid = Objects.requireNonNull(countryid, "`countryid` is required");
			this.title = Objects.requireNonNull(title, "`title` is required");
			this.stateno = Objects.requireNonNull(stateno, "`stateno` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.isut = isut;
			this.sequenceno = Objects.requireNonNull(sequenceno, "`sequenceno` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Long getStateid() {
			return stateid;
		}

		public Long getCountryid() {
			return countryid;
		}

		public String getTitle() {
			return title;
		}

		public Integer getStateno() {
			return stateno;
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

		public Integer getIsut() {
			return isut;
		}

		public Integer getSequenceno() {
			return sequenceno;
		}
	}

}