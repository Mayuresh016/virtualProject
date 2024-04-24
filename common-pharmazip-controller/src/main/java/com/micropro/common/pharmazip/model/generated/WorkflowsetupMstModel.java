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

public class WorkflowsetupMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Workflowsetup entity")
	public static final class WorkflowsetupBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WORKFLOWSETUPID_BODY' (Primary Key)") Long workflowsetupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVGENERATIONPRO_BODY' (Size = 1)") Integer invgenerationpro;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WORKFLOWBILLINGTYPE_BODY' (Size = 1)") Integer workflowbillingtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRINTTYPE_BODY' (Size = 1)") Integer printtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMPICKUP_BODY' (Size = 1)") Integer itempickup;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ITEMVERIFY_BODY' (Size = 1)") Integer itemverify;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VERIFYINGPRINT_BODY' (Size = 1)") Integer verifyingprint;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPATCHSTAGE_BODY' (Size = 1)") Integer dispatchstage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISPATCHSTAGEPRINT_BODY' (Size = 1)") Integer dispatchstageprint;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;

		@JsonCreator
		public WorkflowsetupBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("workflowsetupid") Long workflowsetupid,
				@JsonProperty("invgenerationpro") Integer invgenerationpro,
				@JsonProperty("workflowbillingtype") Integer workflowbillingtype,
				@JsonProperty("printtype") Integer printtype,
				@JsonProperty("itempickup") Integer itempickup,
				@JsonProperty("itemverify") Integer itemverify,
				@JsonProperty("verifyingprint") Integer verifyingprint,
				@JsonProperty("dispatchstage") Integer dispatchstage,
				@JsonProperty("dispatchstageprint") Integer dispatchstageprint,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.workflowsetupid = Objects.requireNonNull(workflowsetupid, "`workflowsetupid` is required");
			this.invgenerationpro = invgenerationpro;
			this.workflowbillingtype = workflowbillingtype;
			this.printtype = printtype;
			this.itempickup = itempickup;
			this.itemverify = itemverify;
			this.verifyingprint = verifyingprint;
			this.dispatchstage = dispatchstage;
			this.dispatchstageprint = dispatchstageprint;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getWorkflowsetupid() {
			return workflowsetupid;
		}

		public Integer getInvgenerationpro() {
			return invgenerationpro;
		}

		public Integer getWorkflowbillingtype() {
			return workflowbillingtype;
		}

		public Integer getPrinttype() {
			return printtype;
		}

		public Integer getItempickup() {
			return itempickup;
		}

		public Integer getItemverify() {
			return itemverify;
		}

		public Integer getVerifyingprint() {
			return verifyingprint;
		}

		public Integer getDispatchstage() {
			return dispatchstage;
		}

		public Integer getDispatchstageprint() {
			return dispatchstageprint;
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
	}

}