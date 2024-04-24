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

public class MstInvoiceDoctypeModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Invoicedoctype entity")
	public static final class InvoicedoctypeBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SRNO_BODY' (required) (Size = 5)") Integer srno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INVOICEDOCTYPEID_BODY' (Primary Key)") Long invoicedoctypeid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ENUMVALUE_BODY' (required) (Size = 5)") Integer enumvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCKEY_BODY' (Size = 3)") String dockey;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTYPE_BODY' (Size = 4)") String doctype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEFAULTKEY_BODY' (Size = 1)") String defaultkey;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ISDEFAULT_BODY' (required) (Size = 1)") String isdefault;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCFILTER_BODY' (Size = 5)") String docfilter;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REFERENCE_BODY' (Size = 50)") String reference;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;

		@JsonCreator
		public InvoicedoctypeBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("srno") Integer srno,
				@JsonProperty("invoicedoctypeid") Long invoicedoctypeid,
				@JsonProperty("enumvalue") Integer enumvalue,
				@JsonProperty("dockey") String dockey,
				@JsonProperty("doctype") String doctype,
				@JsonProperty("defaultkey") String defaultkey,
				@JsonProperty("isdefault") String isdefault,
				@JsonProperty("docfilter") String docfilter,
				@JsonProperty("reference") String reference,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("description") String description,
				@JsonProperty("active") Integer active) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.srno = Objects.requireNonNull(srno, "`srno` is required");
			this.invoicedoctypeid = Objects.requireNonNull(invoicedoctypeid, "`invoicedoctypeid` is required");
			this.enumvalue = Objects.requireNonNull(enumvalue, "`enumvalue` is required");
			this.dockey = dockey;
			this.doctype = doctype;
			this.defaultkey = defaultkey;
			this.isdefault = Objects.requireNonNull(isdefault, "`isdefault` is required");
			this.docfilter = docfilter;
			this.reference = reference;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = lastoperation;
			this.description = description;
			this.active = active;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getSrno() {
			return srno;
		}

		public Long getInvoicedoctypeid() {
			return invoicedoctypeid;
		}

		public Integer getEnumvalue() {
			return enumvalue;
		}

		public String getDockey() {
			return dockey;
		}

		public String getDoctype() {
			return doctype;
		}

		public String getDefaultkey() {
			return defaultkey;
		}

		public String getIsdefault() {
			return isdefault;
		}

		public String getDocfilter() {
			return docfilter;
		}

		public String getReference() {
			return reference;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
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

		public String getDescription() {
			return description;
		}

		public Integer getActive() {
			return active;
		}
	}

}