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

public class IrndetailMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Irndetail entity")
	public static final class IrndetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IRNDETAILID_BODY' (Primary Key)") Long irndetailid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'TRANSACTIONMASTERID_BODY' (required) (Size = 20)") Long transactionmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERSERIES_BODY' (Size = 3)") String voucherseries;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERNO_BODY' (Size = 6)") String voucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VOUCHERDATE_BODY'") Date voucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'IRN_BODY' (required) (Size = 64)") String irn;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IACKNO_BODY' (Size = 20)") String iackno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IACKDT_BODY'") Timestamp iackdt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IMESSAGE_BODY' (Size = 200)") String imessage;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISTATUS_BODY' (Size = 10)") String istatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IREMARK_BODY' (Size = 50)") String iremark;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IQRCODE_BODY'") String iqrcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IQRINV_BODY'") String iqrinv;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IUUID_BODY' (Size = 100)") String iuuid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IQRURL_BODY' (Size = 100)") String iqrurl;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IINVURL_BODY'") String iinvurl;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IIRNST_BODY' (Size = 10)") String iirnst;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Date createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Date modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATUS_BODY' (Size = 100)") String status;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACKNOSTR_BODY' (Size = 20)") String acknostr;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWBDT_BODY'") Timestamp ewbdt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWBNO_BODY' (Size = 20)") String ewbno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWBSTATUS_BODY' (Size = 20)") String ewbstatus;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWBVALIDTILL_BODY' (Size = 20)") String ewbvalidtill;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IRP_BODY' (Size = 20)") String irp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SIGNEDINVOICE_BODY'") String signedinvoice;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IEWAYMESSAGE_BODY' (Size = 200)") String iewaymessage;

		@JsonCreator
		public IrndetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("irndetailid") Long irndetailid,
				@JsonProperty("transactionmasterid") Long transactionmasterid,
				@JsonProperty("voucherseries") String voucherseries,
				@JsonProperty("voucherno") String voucherno,
				@JsonProperty("voucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date voucherdate,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("irn") String irn,
				@JsonProperty("iackno") String iackno,
				@JsonProperty("iackdt") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp iackdt,
				@JsonProperty("imessage") String imessage,
				@JsonProperty("istatus") String istatus,
				@JsonProperty("iremark") String iremark,
				@JsonProperty("iqrcode") String iqrcode,
				@JsonProperty("iqrinv") String iqrinv,
				@JsonProperty("iuuid") String iuuid,
				@JsonProperty("iqrurl") String iqrurl,
				@JsonProperty("iinvurl") String iinvurl,
				@JsonProperty("iirnst") String iirnst,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd") Date createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd") Date modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("status") String status,
				@JsonProperty("acknostr") String acknostr,
				@JsonProperty("ewbdt") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp ewbdt,
				@JsonProperty("ewbno") String ewbno,
				@JsonProperty("ewbstatus") String ewbstatus,
				@JsonProperty("ewbvalidtill") String ewbvalidtill,
				@JsonProperty("irp") String irp,
				@JsonProperty("signedinvoice") String signedinvoice,
				@JsonProperty("iewaymessage") String iewaymessage) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.irndetailid = Objects.requireNonNull(irndetailid, "`irndetailid` is required");
			this.transactionmasterid = Objects.requireNonNull(transactionmasterid, "`transactionmasterid` is required");
			this.voucherseries = voucherseries;
			this.voucherno = voucherno;
			this.voucherdate = voucherdate;
			this.agencyid = agencyid;
			this.irn = Objects.requireNonNull(irn, "`irn` is required");
			this.iackno = iackno;
			this.iackdt = iackdt;
			this.imessage = imessage;
			this.istatus = istatus;
			this.iremark = iremark;
			this.iqrcode = iqrcode;
			this.iqrinv = iqrinv;
			this.iuuid = iuuid;
			this.iqrurl = iqrurl;
			this.iinvurl = iinvurl;
			this.iirnst = iirnst;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.status = status;
			this.acknostr = acknostr;
			this.ewbdt = ewbdt;
			this.ewbno = ewbno;
			this.ewbstatus = ewbstatus;
			this.ewbvalidtill = ewbvalidtill;
			this.irp = irp;
			this.signedinvoice = signedinvoice;
			this.iewaymessage = iewaymessage;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getIrndetailid() {
			return irndetailid;
		}

		public Long getTransactionmasterid() {
			return transactionmasterid;
		}

		public String getVoucherseries() {
			return voucherseries;
		}

		public String getVoucherno() {
			return voucherno;
		}

		public Date getVoucherdate() {
			return voucherdate;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public String getIrn() {
			return irn;
		}

		public String getIackno() {
			return iackno;
		}

		public Timestamp getIackdt() {
			return iackdt;
		}

		public String getImessage() {
			return imessage;
		}

		public String getIstatus() {
			return istatus;
		}

		public String getIremark() {
			return iremark;
		}

		public String getIqrcode() {
			return iqrcode;
		}

		public String getIqrinv() {
			return iqrinv;
		}

		public String getIuuid() {
			return iuuid;
		}

		public String getIqrurl() {
			return iqrurl;
		}

		public String getIinvurl() {
			return iinvurl;
		}

		public String getIirnst() {
			return iirnst;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Date getCreatedon() {
			return createdon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Date getModifyon() {
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

		public String getStatus() {
			return status;
		}

		public String getAcknostr() {
			return acknostr;
		}

		public Timestamp getEwbdt() {
			return ewbdt;
		}

		public String getEwbno() {
			return ewbno;
		}

		public String getEwbstatus() {
			return ewbstatus;
		}

		public String getEwbvalidtill() {
			return ewbvalidtill;
		}

		public String getIrp() {
			return irp;
		}

		public String getSignedinvoice() {
			return signedinvoice;
		}

		public String getIewaymessage() {
			return iewaymessage;
		}
	}

}