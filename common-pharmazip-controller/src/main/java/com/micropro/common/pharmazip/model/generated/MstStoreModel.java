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

public class MstStoreModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstStore entity")
	public static final class MstStoreBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'STORE_BODY' (required)") StoreBody store;

		@JsonCreator
		public MstStoreBody(
				@JsonProperty("store") StoreBody store) {
			this.store = Objects.requireNonNull(store, "`store` is required");
		}

		public StoreBody getStore() {
			return store;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Store entity")
	public static final class StoreBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STOREID_BODY' (Primary Key)") Long storeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 50)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 50)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISDEFAULT_BODY' (Size = 1)") String isdefault;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 50)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 10)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SLOCK_BODY' (Size = 1)") Integer slock;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LOCATION_BODY' (required)") List<LocationBody> location;

		@JsonCreator
		public StoreBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("title") String title,
				@JsonProperty("comments") String comments,
				@JsonProperty("active") Integer active,
				@JsonProperty("isdefault") String isdefault,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("custom") String custom,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("slock") Integer slock,
				@JsonProperty("location") List<LocationBody> location) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.storeid = Objects.requireNonNull(storeid, "`storeid` is required");
			this.title = title;
			this.comments = comments;
			this.active = active;
			this.isdefault = isdefault;
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
			this.custom = custom;
			this.shortname = shortname;
			this.slock = slock;
			this.location = location;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getStoreid() {
			return storeid;
		}

		public String getTitle() {
			return title;
		}

		public String getComments() {
			return comments;
		}

		public Integer getActive() {
			return active;
		}

		public String getIsdefault() {
			return isdefault;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
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

		public String getCustom() {
			return custom;
		}

		public String getShortname() {
			return shortname;
		}

		public Integer getSlock() {
			return slock;
		}

		public List<LocationBody> getLocation() {
			return location;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Location entity")
	public static final class LocationBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONID_BODY' (Primary Key)") Long locationid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'STOREID_BODY' (required) (Size = 20)") Long storeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TITLE_BODY' (Size = 200)") String title;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 50)") String comments;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISCOLDSTORAGE_BODY' (Size = 1)") Integer iscoldstorage;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 20)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOM_BODY' (Size = 20)") String custom;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCATIONSEQUENCE_BODY' (Size = 5)") Integer locationsequence;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISEXPIRED_BODY' (Size = 1)") Integer isexpired;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LLOCK_BODY' (Size = 1)") Integer llock;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISDEFAULT_BODY' (Size = 1)") String isdefault;

		@JsonCreator
		public LocationBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("locationid") Long locationid,
				@JsonProperty("storeid") Long storeid,
				@JsonProperty("title") String title,
				@JsonProperty("comments") String comments,
				@JsonProperty("iscoldstorage") Integer iscoldstorage,
				@JsonProperty("active") Integer active,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("custom") String custom,
				@JsonProperty("locationsequence") Integer locationsequence,
				@JsonProperty("isexpired") Integer isexpired,
				@JsonProperty("llock") Integer llock,
				@JsonProperty("isdefault") String isdefault) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.locationid = Objects.requireNonNull(locationid, "`locationid` is required");
			this.storeid = Objects.requireNonNull(storeid, "`storeid` is required");
			this.title = title;
			this.comments = comments;
			this.iscoldstorage = iscoldstorage;
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.macinfo = macinfo;
			this.description = description;
			this.sessionid = sessionid;
			this.lastoperation = lastoperation;
			this.custom = custom;
			this.locationsequence = locationsequence;
			this.isexpired = isexpired;
			this.llock = llock;
			this.isdefault = isdefault;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getLocationid() {
			return locationid;
		}

		public Long getStoreid() {
			return storeid;
		}

		public String getTitle() {
			return title;
		}

		public String getComments() {
			return comments;
		}

		public Integer getIscoldstorage() {
			return iscoldstorage;
		}

		public Integer getActive() {
			return active;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getModifyon() {
			return modifyon;
		}

		public Long getModifyby() {
			return modifyby;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public String getSessionid() {
			return sessionid;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public String getCustom() {
			return custom;
		}

		public Integer getLocationsequence() {
			return locationsequence;
		}

		public Integer getIsexpired() {
			return isexpired;
		}

		public Integer getLlock() {
			return llock;
		}

		public String getIsdefault() {
			return isdefault;
		}
	}

}