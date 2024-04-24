package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
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

public class GrnMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a GrnMst entity")
	public static final class GrnMstBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'GRNMASTER_BODY' (required)") GrnmasterBody grnmaster;

		@JsonCreator
		public GrnMstBody(
				@JsonProperty("grnmaster") GrnmasterBody grnmaster) {
			this.grnmaster = Objects.requireNonNull(grnmaster, "`grnmaster` is required");
		}

		public GrnmasterBody getGrnmaster() {
			return grnmaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Grnmaster entity")
	public static final class GrnmasterBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GRNMASTERID_BODY' (Primary Key)") Long grnmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GRNDATE_BODY'") Date grndate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOOFCASES_BODY' (Size = 20)") Long noofcases;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMOUNT_BODY' (Size = 18,4)") BigDecimal billamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'REMARKS_BODY' (Size = 100)") String remarks;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATUS_BODY' (Size = 1)") Integer status;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTID_BODY' (Size = 20)") Long transportid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRNO_BODY' (Size = 50)") String lrno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRDATE_BODY'") Date lrdate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRTYPE_BODY' (Size = 1)") Integer lrtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRGSTAMOUNT_BODY' (Size = 18,4)") BigDecimal lrgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRAMOUNT_BODY' (Size = 18,4)") BigDecimal lramount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PVOUCHERNO_BODY' (Size = 20)") String pvoucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PVOUCHERDATE_BODY'") Date pvoucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GRNNO_BODY' (Size = 20)") Integer grnno;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'GRNDETAIL_BODY' (required)") List<GrndetailBody> grndetail;

		@JsonCreator
		public GrnmasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("grnmasterid") Long grnmasterid,
				@JsonProperty("grndate") @JsonFormat(pattern = "yyyy-MM-dd") Date grndate,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("noofcases") Long noofcases,
				@JsonProperty("billamount") BigDecimal billamount,
				@JsonProperty("remarks") String remarks,
				@JsonProperty("status") Integer status,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("transportid") Long transportid,
				@JsonProperty("lrno") String lrno,
				@JsonProperty("lrdate") @JsonFormat(pattern = "yyyy-MM-dd") Date lrdate,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("lrtype") Integer lrtype,
				@JsonProperty("lrgstamount") BigDecimal lrgstamount,
				@JsonProperty("lramount") BigDecimal lramount,
				@JsonProperty("pvoucherno") String pvoucherno,
				@JsonProperty("pvoucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date pvoucherdate,
				@JsonProperty("grnno") Integer grnno,
				@JsonProperty("grndetail") List<GrndetailBody> grndetail) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.grnmasterid = Objects.requireNonNull(grnmasterid, "`grnmasterid` is required");
			this.grndate = grndate;
			this.billno = billno;
			this.billdate = billdate;
			this.noofcases = noofcases;
			this.billamount = billamount;
			this.remarks = remarks;
			this.status = status;
			this.accountid = accountid;
			this.transportid = transportid;
			this.lrno = lrno;
			this.lrdate = lrdate;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = active;
			this.lrtype = lrtype;
			this.lrgstamount = lrgstamount;
			this.lramount = lramount;
			this.pvoucherno = pvoucherno;
			this.pvoucherdate = pvoucherdate;
			this.grnno = grnno;
			this.grndetail = grndetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getGrnmasterid() {
			return grnmasterid;
		}

		public Date getGrndate() {
			return grndate;
		}

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public Long getNoofcases() {
			return noofcases;
		}

		public BigDecimal getBillamount() {
			return billamount;
		}

		public String getRemarks() {
			return remarks;
		}

		public Integer getStatus() {
			return status;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Long getTransportid() {
			return transportid;
		}

		public String getLrno() {
			return lrno;
		}

		public Date getLrdate() {
			return lrdate;
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

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getActive() {
			return active;
		}

		public Integer getLrtype() {
			return lrtype;
		}

		public BigDecimal getLrgstamount() {
			return lrgstamount;
		}

		public BigDecimal getLramount() {
			return lramount;
		}

		public String getPvoucherno() {
			return pvoucherno;
		}

		public Date getPvoucherdate() {
			return pvoucherdate;
		}

		public Integer getGrnno() {
			return grnno;
		}

		public List<GrndetailBody> getGrndetail() {
			return grndetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Grndetail entity")
	public static final class GrndetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'GRNMASTERID_BODY' (required) (Size = 20)") Long grnmasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GRNDETAILID_BODY' (Primary Key)") Long grndetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY'") Date billdate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'NOOFCASES_BODY' (required) (Size = 5)") Integer noofcases;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMOUNT_BODY' (Size = 18,4)") BigDecimal billamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PVOUCHERNO_BODY' (Size = 20)") String pvoucherno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PVOUCHERDATE_BODY'") Date pvoucherdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 50)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;

		@JsonCreator
		public GrndetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("grnmasterid") Long grnmasterid,
				@JsonProperty("grndetailid") Long grndetailid,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") @JsonFormat(pattern = "yyyy-MM-dd") Date billdate,
				@JsonProperty("noofcases") Integer noofcases,
				@JsonProperty("billamount") BigDecimal billamount,
				@JsonProperty("pvoucherno") String pvoucherno,
				@JsonProperty("pvoucherdate") @JsonFormat(pattern = "yyyy-MM-dd") Date pvoucherdate,
				@JsonProperty("active") Integer active,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.grnmasterid = Objects.requireNonNull(grnmasterid, "`grnmasterid` is required");
			this.grndetailid = Objects.requireNonNull(grndetailid, "`grndetailid` is required");
			this.billno = billno;
			this.billdate = billdate;
			this.noofcases = Objects.requireNonNull(noofcases, "`noofcases` is required");
			this.billamount = billamount;
			this.pvoucherno = pvoucherno;
			this.pvoucherdate = pvoucherdate;
			this.active = active;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getGrnmasterid() {
			return grnmasterid;
		}

		public Long getGrndetailid() {
			return grndetailid;
		}

		public String getBillno() {
			return billno;
		}

		public Date getBilldate() {
			return billdate;
		}

		public Integer getNoofcases() {
			return noofcases;
		}

		public BigDecimal getBillamount() {
			return billamount;
		}

		public String getPvoucherno() {
			return pvoucherno;
		}

		public Date getPvoucherdate() {
			return pvoucherdate;
		}

		public Integer getActive() {
			return active;
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

		public String getLastoperation() {
			return lastoperation;
		}
	}

}