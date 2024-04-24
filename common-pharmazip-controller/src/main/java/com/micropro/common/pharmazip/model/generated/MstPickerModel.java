package com.micropro.common.pharmazip.model.generated;

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

public class MstPickerModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstPicker entity")
	public static final class MstPickerBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'PICKER_BODY' (required)") PickerBody picker;

		@JsonCreator
		public MstPickerBody(
				@JsonProperty("picker") PickerBody picker) {
			this.picker = Objects.requireNonNull(picker, "`picker` is required");
		}

		public PickerBody getPicker() {
			return picker;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Picker entity")
	public static final class PickerBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERID_BODY' (Primary Key)") Long pickerid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERNAME_BODY' (Size = 100)") String pickername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERDESCRIPTION_BODY' (Size = 500)") String pickerdescription;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRINTPICKSLIP_BODY' (Size = 1)") Integer printpickslip;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRINTERNAME_BODY' (Size = 1000)") String printername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRINTERPATH_BODY' (Size = 1000)") String printerpath;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 250)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISDEFAULT_BODY' (Size = 1)") Integer isdefault;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PICKERLOCATIONLINK_BODY' (required)") List<PickerlocationlinkBody> pickerlocationlink;

		@JsonCreator
		public PickerBody(
				@JsonProperty("pickerid") Long pickerid,
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("pickername") String pickername,
				@JsonProperty("pickerdescription") String pickerdescription,
				@JsonProperty("printpickslip") Integer printpickslip,
				@JsonProperty("printername") String printername,
				@JsonProperty("printerpath") String printerpath,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("isdefault") Integer isdefault,
				@JsonProperty("pickerlocationlink") List<PickerlocationlinkBody> pickerlocationlink) {
			this.pickerid = Objects.requireNonNull(pickerid, "`pickerid` is required");
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.pickername = pickername;
			this.pickerdescription = pickerdescription;
			this.printpickslip = printpickslip;
			this.printername = printername;
			this.printerpath = printerpath;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.isdefault = isdefault;
			this.pickerlocationlink = pickerlocationlink;
		}

		public Long getPickerid() {
			return pickerid;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public String getPickername() {
			return pickername;
		}

		public String getPickerdescription() {
			return pickerdescription;
		}

		public Integer getPrintpickslip() {
			return printpickslip;
		}

		public String getPrintername() {
			return printername;
		}

		public String getPrinterpath() {
			return printerpath;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
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

		public Integer getActive() {
			return active;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public Integer getIsdefault() {
			return isdefault;
		}

		public List<PickerlocationlinkBody> getPickerlocationlink() {
			return pickerlocationlink;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Pickerlocationlink entity")
	public static final class PickerlocationlinkBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERLOCATIONLINKID_BODY' (Primary Key)") Long pickerlocationlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONID_BODY' (Size = 20)") Long locationid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PICKERID_BODY' (Size = 20)") Long pickerid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 250)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;

		@JsonCreator
		public PickerlocationlinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("pickerlocationlinkid") Long pickerlocationlinkid,
				@JsonProperty("locationid") Long locationid,
				@JsonProperty("pickerid") Long pickerid,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.pickerlocationlinkid = Objects.requireNonNull(pickerlocationlinkid, "`pickerlocationlinkid` is required");
			this.locationid = locationid;
			this.pickerid = pickerid;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getPickerlocationlinkid() {
			return pickerlocationlinkid;
		}

		public Long getLocationid() {
			return locationid;
		}

		public Long getPickerid() {
			return pickerid;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
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

		public Integer getActive() {
			return active;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}
	}

}