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

public class MstBookingScheduleModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Orderbookingschedule entity")
	public static final class OrderbookingscheduleBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERBOOKINGSCHEDULEID_BODY' (Primary Key)") Long orderbookingscheduleid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 50)") String accountid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SCHEDULEDATE_BODY' (required)") Date scheduledate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SCHEDULETIME_BODY' (required) (Size = 50)") String scheduletime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENT_BODY' (Size = 500)") String comment;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 10)") String lastoperation;

		@JsonCreator
		public OrderbookingscheduleBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("orderbookingscheduleid") Long orderbookingscheduleid,
				@JsonProperty("accountid") String accountid,
				@JsonProperty("scheduledate") @JsonFormat(pattern = "yyyy-MM-dd") Date scheduledate,
				@JsonProperty("scheduletime") String scheduletime,
				@JsonProperty("comment") String comment,
				@JsonProperty("description") String description,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.orderbookingscheduleid = Objects.requireNonNull(orderbookingscheduleid, "`orderbookingscheduleid` is required");
			this.accountid = accountid;
			this.scheduledate = Objects.requireNonNull(scheduledate, "`scheduledate` is required");
			this.scheduletime = Objects.requireNonNull(scheduletime, "`scheduletime` is required");
			this.comment = comment;
			this.description = description;
			this.macinfo = macinfo;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.sessionid = sessionid;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getOrderbookingscheduleid() {
			return orderbookingscheduleid;
		}

		public String getAccountid() {
			return accountid;
		}

		public Date getScheduledate() {
			return scheduledate;
		}

		public String getScheduletime() {
			return scheduletime;
		}

		public String getComment() {
			return comment;
		}

		public String getDescription() {
			return description;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
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

		public String getLastoperation() {
			return lastoperation;
		}
	}

}