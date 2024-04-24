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

public class TimeExpiredModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Timeexpired entity")
	public static final class TimeexpiredBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 1)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 1)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TIMEEXPIREDID_BODY' (Primary Key)") Long timeexpiredid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'AGENCYID_BODY' (required) (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTPERCENTAGE_BODY' (Size = 18,4)") BigDecimal gstpercentage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LESSPERCENTAGEONMRP_BODY' (Size = 18,4)") BigDecimal lesspercentageonmrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARKS_BODY' (Size = 100)") String remarks;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECHARGED_BODY' (Size = 1)") Integer ratecharged;

		@JsonCreator
		public TimeexpiredBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("timeexpiredid") Long timeexpiredid,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("gstpercentage") BigDecimal gstpercentage,
				@JsonProperty("lesspercentageonmrp") BigDecimal lesspercentageonmrp,
				@JsonProperty("remarks") String remarks,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("ratecharged") Integer ratecharged) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.timeexpiredid = Objects.requireNonNull(timeexpiredid, "`timeexpiredid` is required");
			this.agencyid = Objects.requireNonNull(agencyid, "`agencyid` is required");
			this.gstpercentage = gstpercentage;
			this.lesspercentageonmrp = lesspercentageonmrp;
			this.remarks = remarks;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = lastoperation;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.ratecharged = ratecharged;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTimeexpiredid() {
			return timeexpiredid;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public BigDecimal getGstpercentage() {
			return gstpercentage;
		}

		public BigDecimal getLesspercentageonmrp() {
			return lesspercentageonmrp;
		}

		public String getRemarks() {
			return remarks;
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

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public Integer getRatecharged() {
			return ratecharged;
		}
	}

}