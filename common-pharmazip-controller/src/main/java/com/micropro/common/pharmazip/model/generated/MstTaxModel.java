package com.micropro.common.pharmazip.model.generated;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class MstTaxModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstTax entity")
	public static final class MstTaxBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'TAX_BODY' (required)") TaxBody tax;

		@JsonCreator
		public MstTaxBody(
				@JsonProperty("tax") TaxBody tax) {
			this.tax = Objects.requireNonNull(tax, "`tax` is required");
		}

		public TaxBody getTax() {
			return tax;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Tax entity")
	public static final class TaxBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXID_BODY' (Primary Key)") Long taxid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXDESCRIPTION_BODY' (Size = 200)") String taxdescription;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTTYPE_BODY' (Size = 1)") String gsttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGST_BODY' (Size = 18,4)") BigDecimal igst;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGST_BODY' (Size = 18,4)") BigDecimal sgst;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGST_BODY' (Size = 18,4)") BigDecimal cgst;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGST_BODY' (Size = 18,4)") BigDecimal ugst;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXVALUE_BODY' (Size = 18,4)") BigDecimal taxvalue;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TAXACCOUNT_BODY' (required)") List<TaxaccountBody> taxaccount;

		@JsonCreator
		public TaxBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("taxid") Long taxid,
				@JsonProperty("taxdescription") String taxdescription,
				@JsonProperty("gsttype") String gsttype,
				@JsonProperty("igst") BigDecimal igst,
				@JsonProperty("sgst") BigDecimal sgst,
				@JsonProperty("cgst") BigDecimal cgst,
				@JsonProperty("ugst") BigDecimal ugst,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("taxvalue") BigDecimal taxvalue,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("custom") String custom,
				@JsonProperty("taxaccount") List<TaxaccountBody> taxaccount) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.taxid = Objects.requireNonNull(taxid, "`taxid` is required");
			this.taxdescription = taxdescription;
			this.gsttype = gsttype;
			this.igst = igst;
			this.sgst = sgst;
			this.cgst = cgst;
			this.ugst = ugst;
			this.active = active;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.taxvalue = taxvalue;
			this.description = description;
			this.lastoperation = lastoperation;
			this.custom = custom;
			this.taxaccount = taxaccount;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTaxid() {
			return taxid;
		}

		public String getTaxdescription() {
			return taxdescription;
		}

		public String getGsttype() {
			return gsttype;
		}

		public BigDecimal getIgst() {
			return igst;
		}

		public BigDecimal getSgst() {
			return sgst;
		}

		public BigDecimal getCgst() {
			return cgst;
		}

		public BigDecimal getUgst() {
			return ugst;
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

		public String getMacinfo() {
			return macinfo;
		}

		public String getSessionid() {
			return sessionid;
		}

		public BigDecimal getTaxvalue() {
			return taxvalue;
		}

		public String getDescription() {
			return description;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public String getCustom() {
			return custom;
		}

		public List<TaxaccountBody> getTaxaccount() {
			return taxaccount;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Taxaccount entity")
	public static final class TaxaccountBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXACCOUNTID_BODY' (Primary Key)") Long taxaccountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TAXID_BODY' (Size = 20)") Long taxid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 200)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCPREFIX_BODY' (Size = 200)") String accprefix;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCTAG_BODY' (Size = 200)") String acctag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;

		@JsonCreator
		public TaxaccountBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("taxaccountid") Long taxaccountid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("taxid") Long taxid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("comments") String comments,
				@JsonProperty("accprefix") String accprefix,
				@JsonProperty("acctag") String acctag,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.taxaccountid = Objects.requireNonNull(taxaccountid, "`taxaccountid` is required");
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.taxid = taxid;
			this.accountid = accountid;
			this.comments = comments;
			this.accprefix = accprefix;
			this.acctag = acctag;
			this.lastoperation = lastoperation;
			this.active = active;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getTaxaccountid() {
			return taxaccountid;
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

		public Long getTaxid() {
			return taxid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public String getComments() {
			return comments;
		}

		public String getAccprefix() {
			return accprefix;
		}

		public String getAcctag() {
			return acctag;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getActive() {
			return active;
		}
	}

}