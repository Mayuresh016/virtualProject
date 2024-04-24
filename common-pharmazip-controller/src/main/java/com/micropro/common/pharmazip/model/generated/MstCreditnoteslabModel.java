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

public class MstCreditnoteslabModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Creditnoteslab entity")
	public static final class CreditnoteslabBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTESLABID_BODY' (Primary Key)") Long creditnoteslabid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'RATECHARGED_BODY' (required) (Size = 1)") Integer ratecharged;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'BELOWDAYSPER_BODY' (required) (Size = 18,4)") BigDecimal belowdaysper;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'BELOWDAYS_BODY' (required) (Size = 5)") Integer belowdays;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'BETWEENDAYPER_BODY' (required) (Size = 18,4)") BigDecimal betweendayper;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BETWEENDAY_BODY' (Size = 5)") Integer betweenday;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ABOVEDAYSPER_BODY' (required) (Size = 18,4)") BigDecimal abovedaysper;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ABOVEDAY_BODY' (required) (Size = 5)") Integer aboveday;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CRDESRICPTION_BODY' (Size = 250)") String crdesricption;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'COMMONNARRATIONID_BODY' (required) (Size = 20)") Long commonnarrationid;

		@JsonCreator
		public CreditnoteslabBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("creditnoteslabid") Long creditnoteslabid,
				@JsonProperty("ratecharged") Integer ratecharged,
				@JsonProperty("belowdaysper") BigDecimal belowdaysper,
				@JsonProperty("belowdays") Integer belowdays,
				@JsonProperty("betweendayper") BigDecimal betweendayper,
				@JsonProperty("betweenday") Integer betweenday,
				@JsonProperty("abovedaysper") BigDecimal abovedaysper,
				@JsonProperty("aboveday") Integer aboveday,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("crdesricption") String crdesricption,
				@JsonProperty("commonnarrationid") Long commonnarrationid) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.creditnoteslabid = Objects.requireNonNull(creditnoteslabid, "`creditnoteslabid` is required");
			this.ratecharged = Objects.requireNonNull(ratecharged, "`ratecharged` is required");
			this.belowdaysper = Objects.requireNonNull(belowdaysper, "`belowdaysper` is required");
			this.belowdays = Objects.requireNonNull(belowdays, "`belowdays` is required");
			this.betweendayper = Objects.requireNonNull(betweendayper, "`betweendayper` is required");
			this.betweenday = betweenday;
			this.abovedaysper = Objects.requireNonNull(abovedaysper, "`abovedaysper` is required");
			this.aboveday = Objects.requireNonNull(aboveday, "`aboveday` is required");
			this.sessionid = sessionid;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.macinfo = macinfo;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.crdesricption = crdesricption;
			this.commonnarrationid = Objects.requireNonNull(commonnarrationid, "`commonnarrationid` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getCreditnoteslabid() {
			return creditnoteslabid;
		}

		public Integer getRatecharged() {
			return ratecharged;
		}

		public BigDecimal getBelowdaysper() {
			return belowdaysper;
		}

		public Integer getBelowdays() {
			return belowdays;
		}

		public BigDecimal getBetweendayper() {
			return betweendayper;
		}

		public Integer getBetweenday() {
			return betweenday;
		}

		public BigDecimal getAbovedaysper() {
			return abovedaysper;
		}

		public Integer getAboveday() {
			return aboveday;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
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

		public String getCrdesricption() {
			return crdesricption;
		}

		public Long getCommonnarrationid() {
			return commonnarrationid;
		}
	}

}