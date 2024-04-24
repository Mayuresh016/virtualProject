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

public class MstInvoiceseriesModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Invoiceseries entity")
	public static final class InvoiceseriesBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVOICESERIESID_BODY' (Primary Key)") Long invoiceseriesid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVOICESERIES_BODY' (Size = 20)") String invoiceseries;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ISDEFAULT_BODY' (required) (Size = 1)") String isdefault;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 20)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 500)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOREID_BODY' (Size = 20)") Long storeid;

		@JsonCreator
		public InvoiceseriesBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("invoiceseriesid") Long invoiceseriesid,
				@JsonProperty("invoiceseries") String invoiceseries,
				@JsonProperty("isdefault") String isdefault,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("description") String description,
				@JsonProperty("active") Integer active,
				@JsonProperty("comments") String comments,
				@JsonProperty("storeid") Long storeid) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.invoiceseriesid = Objects.requireNonNull(invoiceseriesid, "`invoiceseriesid` is required");
			this.invoiceseries = invoiceseries;
			this.isdefault = Objects.requireNonNull(isdefault, "`isdefault` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.lastoperation = lastoperation;
			this.description = description;
			this.active = active;
			this.comments = comments;
			this.storeid = storeid;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getInvoiceseriesid() {
			return invoiceseriesid;
		}

		public String getInvoiceseries() {
			return invoiceseries;
		}

		public String getIsdefault() {
			return isdefault;
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

		public String getDescription() {
			return description;
		}

		public Integer getActive() {
			return active;
		}

		public String getComments() {
			return comments;
		}

		public Long getStoreid() {
			return storeid;
		}
	}

}