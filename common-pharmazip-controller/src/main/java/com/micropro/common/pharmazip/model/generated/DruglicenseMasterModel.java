package com.micropro.common.pharmazip.model.generated;

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

public class DruglicenseMasterModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Druglicensemaster entity")
	public static final class DruglicensemasterBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DRUGLICENSEMASTERID_BODY' (Primary Key)") Long druglicensemasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DRUGTYPEID_BODY' (Size = 5)") Integer drugtypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LICNO_BODY' (Size = 50)") String licno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FROMDATE_BODY'") Date fromdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TODATE_BODY'") Date todate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCCODE_BODY' (Size = 20)") Long acccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LICSTATUS_BODY' (Size = 1)") Integer licstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public DruglicensemasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("druglicensemasterid") Long druglicensemasterid,
				@JsonProperty("drugtypeid") Integer drugtypeid,
				@JsonProperty("licno") String licno,
				@JsonProperty("fromdate") @JsonFormat(pattern = "yyyy-MM-dd") Date fromdate,
				@JsonProperty("todate") @JsonFormat(pattern = "yyyy-MM-dd") Date todate,
				@JsonProperty("acccode") Long acccode,
				@JsonProperty("licstatus") Integer licstatus,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.druglicensemasterid = Objects.requireNonNull(druglicensemasterid, "`druglicensemasterid` is required");
			this.drugtypeid = drugtypeid;
			this.licno = licno;
			this.fromdate = fromdate;
			this.todate = todate;
			this.acccode = acccode;
			this.licstatus = licstatus;
			this.active = active;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = lastoperation;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getDruglicensemasterid() {
			return druglicensemasterid;
		}

		public Integer getDrugtypeid() {
			return drugtypeid;
		}

		public String getLicno() {
			return licno;
		}

		public Date getFromdate() {
			return fromdate;
		}

		public Date getTodate() {
			return todate;
		}

		public Long getAcccode() {
			return acccode;
		}

		public Integer getLicstatus() {
			return licstatus;
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
	}

}