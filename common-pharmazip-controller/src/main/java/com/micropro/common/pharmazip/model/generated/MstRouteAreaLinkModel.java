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

public class MstRouteAreaLinkModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Routearealink entity")
	public static final class RoutearealinkBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ROUTELINKID_BODY' (Primary Key)") Long routelinkid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ROUTEID_BODY' (required) (Size = 20)") Long routeid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'AREAID_BODY' (required) (Size = 20)") Long areaid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SEQUENCENO_BODY' (required) (Size = 5)") Integer sequenceno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENT_BODY' (Size = 100)") String comment;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELETEFLAG_BODY' (Size = 1)") Integer deleteflag;

		@JsonCreator
		public RoutearealinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("routelinkid") Long routelinkid,
				@JsonProperty("routeid") Long routeid,
				@JsonProperty("areaid") Long areaid,
				@JsonProperty("active") Integer active,
				@JsonProperty("sequenceno") Integer sequenceno,
				@JsonProperty("comment") String comment,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("deleteflag") Integer deleteflag) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.routelinkid = Objects.requireNonNull(routelinkid, "`routelinkid` is required");
			this.routeid = Objects.requireNonNull(routeid, "`routeid` is required");
			this.areaid = Objects.requireNonNull(areaid, "`areaid` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.sequenceno = Objects.requireNonNull(sequenceno, "`sequenceno` is required");
			this.comment = comment;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.deleteflag = deleteflag;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getRoutelinkid() {
			return routelinkid;
		}

		public Long getRouteid() {
			return routeid;
		}

		public Long getAreaid() {
			return areaid;
		}

		public Integer getActive() {
			return active;
		}

		public Integer getSequenceno() {
			return sequenceno;
		}

		public String getComment() {
			return comment;
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

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getDeleteflag() {
			return deleteflag;
		}
	}

}