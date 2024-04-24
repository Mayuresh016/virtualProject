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

public class CustBnkmstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Custbnkmst entity")
	public static final class CustbnkmstBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTBNKMSTID_BODY' (Primary Key)") Long custbnkmstid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 500)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 500)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD3_BODY' (Size = 500)") String add3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNAME_BODY' (Size = 1000)") String bankname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKACCCODE_BODY' (Size = 50)") String bankacccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IFSCCODE_BODY' (Size = 100)") String ifsccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MICRCODE_BODY' (Size = 100)") String micrcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARRATION_BODY' (Size = 100)") String narration;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public CustbnkmstBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("custbnkmstid") Long custbnkmstid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("add1") String add1,
				@JsonProperty("add2") String add2,
				@JsonProperty("add3") String add3,
				@JsonProperty("bankname") String bankname,
				@JsonProperty("bankacccode") String bankacccode,
				@JsonProperty("ifsccode") String ifsccode,
				@JsonProperty("micrcode") String micrcode,
				@JsonProperty("narration") String narration,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.custbnkmstid = Objects.requireNonNull(custbnkmstid, "`custbnkmstid` is required");
			this.accountid = accountid;
			this.add1 = add1;
			this.add2 = add2;
			this.add3 = add3;
			this.bankname = bankname;
			this.bankacccode = bankacccode;
			this.ifsccode = ifsccode;
			this.micrcode = micrcode;
			this.narration = narration;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
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

		public Long getCustbnkmstid() {
			return custbnkmstid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public String getAdd1() {
			return add1;
		}

		public String getAdd2() {
			return add2;
		}

		public String getAdd3() {
			return add3;
		}

		public String getBankname() {
			return bankname;
		}

		public String getBankacccode() {
			return bankacccode;
		}

		public String getIfsccode() {
			return ifsccode;
		}

		public String getMicrcode() {
			return micrcode;
		}

		public String getNarration() {
			return narration;
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