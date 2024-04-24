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

public class CompanySupplierModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Companysupplier entity")
	public static final class CompanysupplierBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ID_BODY' (Primary Key)") Integer id;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'COMPANYCODE_BODY' (required) (Size = 5)") Integer companycode;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'SUPPLIERCODE_BODY' (required) (Size = 5)") Integer suppliercode;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 10)") String lastoperation;

		@JsonCreator
		public CompanysupplierBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("id") Integer id,
				@JsonProperty("companycode") Integer companycode,
				@JsonProperty("suppliercode") Integer suppliercode,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.id = Objects.requireNonNull(id, "`id` is required");
			this.companycode = Objects.requireNonNull(companycode, "`companycode` is required");
			this.suppliercode = Objects.requireNonNull(suppliercode, "`suppliercode` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Integer getId() {
			return id;
		}

		public Integer getCompanycode() {
			return companycode;
		}

		public Integer getSuppliercode() {
			return suppliercode;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getModifiedby() {
			return modifiedby;
		}

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getDescription() {
			return description;
		}

		public String getLastoperation() {
			return lastoperation;
		}
	}

}