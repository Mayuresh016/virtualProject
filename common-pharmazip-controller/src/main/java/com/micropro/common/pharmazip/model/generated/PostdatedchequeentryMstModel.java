package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class PostdatedchequeentryMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Postdatedchequeentry entity")
	public static final class PostdatedchequeentryBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'POSTDATEDCHEQUEENTRYID_BODY' (Primary Key)") Long postdatedchequeentryid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PARTYCODE_BODY' (required) (Size = 20)") Long partycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKACCOUNTCODE_BODY' (Size = 20)") Long bankaccountcode;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CHEQUENO_BODY' (required) (Size = 10)") String chequeno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CHEQUEDATE_BODY'") Date chequedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTTYPE_BODY' (Size = 5)") String paymenttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCSERIES_BODY' (Size = 5)") String docseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCNO_BODY' (Size = 1)") String docno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCDATE_BODY'") Date docdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AMOUNT_BODY' (Size = 18,4)") BigDecimal amount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARK_BODY' (Size = 250)") String remark;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Date createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Date modifiedon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public PostdatedchequeentryBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("postdatedchequeentryid") Long postdatedchequeentryid,
				@JsonProperty("partycode") Long partycode,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("bankaccountcode") Long bankaccountcode,
				@JsonProperty("chequeno") String chequeno,
				@JsonProperty("chequedate") @JsonFormat(pattern = "yyyy-MM-dd") Date chequedate,
				@JsonProperty("paymenttype") String paymenttype,
				@JsonProperty("docseries") String docseries,
				@JsonProperty("docno") String docno,
				@JsonProperty("docdate") @JsonFormat(pattern = "yyyy-MM-dd") Date docdate,
				@JsonProperty("amount") BigDecimal amount,
				@JsonProperty("remark") String remark,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd") Date createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd") Date modifiedon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.postdatedchequeentryid = Objects.requireNonNull(postdatedchequeentryid, "`postdatedchequeentryid` is required");
			this.partycode = Objects.requireNonNull(partycode, "`partycode` is required");
			this.identity = identity;
			this.bankaccountcode = bankaccountcode;
			this.chequeno = Objects.requireNonNull(chequeno, "`chequeno` is required");
			this.chequedate = chequedate;
			this.paymenttype = paymenttype;
			this.docseries = docseries;
			this.docno = docno;
			this.docdate = docdate;
			this.amount = amount;
			this.remark = remark;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifiedon = modifiedon;
			this.modifiedby = modifiedby;
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

		public Long getPostdatedchequeentryid() {
			return postdatedchequeentryid;
		}

		public Long getPartycode() {
			return partycode;
		}

		public Integer getIdentity() {
			return identity;
		}

		public Long getBankaccountcode() {
			return bankaccountcode;
		}

		public String getChequeno() {
			return chequeno;
		}

		public Date getChequedate() {
			return chequedate;
		}

		public String getPaymenttype() {
			return paymenttype;
		}

		public String getDocseries() {
			return docseries;
		}

		public String getDocno() {
			return docno;
		}

		public Date getDocdate() {
			return docdate;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public String getRemark() {
			return remark;
		}

		public Date getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Date getModifiedon() {
			return modifiedon;
		}

		public Long getModifiedby() {
			return modifiedby;
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