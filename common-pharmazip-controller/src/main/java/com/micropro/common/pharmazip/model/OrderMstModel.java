package com.micropro.common.pharmazip.model;

import java.sql.Date;
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

public class OrderMstModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a OrderMst entity")
	public static final class OrderMstBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERMASTER_BODY' (required)") OrdermasterBody ordermaster;

		@JsonCreator
		public OrderMstBody(
				@JsonProperty("ordermaster") OrdermasterBody ordermaster) {
			this.ordermaster = Objects.requireNonNull(ordermaster, "`ordermaster` is required");
		}

		public OrdermasterBody getOrdermaster() {
			return ordermaster;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Ordermaster entity")
	public static final class OrdermasterBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERMASTERID_BODY' (Primary Key)") Long ordermasterid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERNO_BODY' (required) (Size = 11)") Integer orderno;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERDATE_BODY' (required)") Date orderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SUPPLIERCODE_BODY' (Size = 20)") Long suppliercode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCODE_BODY' (Size = 20)") Long companycode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPGROUPCODE_BODY' (Size = 20)") Long compgroupcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDAYS_BODY' (Size = 11)") Integer orderdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CALMETHOD_BODY' (Size = 11)") Integer calmethod;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXCLUDELOCKBATCH_BODY' (Size = 11)") Integer excludelockbatch;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INCLUDESUBSTOCKIESTSALE_BODY' (Size = 11)") Integer includesubstockiestsale;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 50)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDBY_BODY' (Size = 20)") Long modifiedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFIEDON_BODY'") Timestamp modifiedon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PURCHASEVOUNO_BODY' (required) (Size = 11)") Integer purchasevouno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASEVOUDATE_BODY'") Timestamp purchasevoudate;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERSTATUS_BODY' (required) (Size = 1)") Integer orderstatus;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS_BODY' (Size = 250)") String address;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERDETAIL_BODY' (required)") List<OrderdetailBody> orderdetail;

		@JsonCreator
		public OrdermasterBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("ordermasterid") Long ordermasterid,
				@JsonProperty("orderno") Integer orderno,
				@JsonProperty("orderdate") @JsonFormat(pattern = "yyyy-MM-dd") Date orderdate,
				@JsonProperty("suppliercode") Long suppliercode,
				@JsonProperty("companycode") Long companycode,
				@JsonProperty("compgroupcode") Long compgroupcode,
				@JsonProperty("orderdays") Integer orderdays,
				@JsonProperty("calmethod") Integer calmethod,
				@JsonProperty("excludelockbatch") Integer excludelockbatch,
				@JsonProperty("includesubstockiestsale") Integer includesubstockiestsale,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifiedby") Long modifiedby,
				@JsonProperty("modifiedon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifiedon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("purchasevouno") Integer purchasevouno,
				@JsonProperty("purchasevoudate") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp purchasevoudate,
				@JsonProperty("orderstatus") Integer orderstatus,
				@JsonProperty("active") Integer active,
				@JsonProperty("address") String address,
				@JsonProperty("orderdetail") List<OrderdetailBody> orderdetail) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.ordermasterid = Objects.requireNonNull(ordermasterid, "`ordermasterid` is required");
			this.orderno = Objects.requireNonNull(orderno, "`orderno` is required");
			this.orderdate = Objects.requireNonNull(orderdate, "`orderdate` is required");
			this.suppliercode = suppliercode;
			this.companycode = companycode;
			this.compgroupcode = compgroupcode;
			this.orderdays = orderdays;
			this.calmethod = calmethod;
			this.excludelockbatch = excludelockbatch;
			this.includesubstockiestsale = includesubstockiestsale;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifiedby = modifiedby;
			this.modifiedon = modifiedon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.purchasevouno = Objects.requireNonNull(purchasevouno, "`purchasevouno` is required");
			this.purchasevoudate = purchasevoudate;
			this.orderstatus = Objects.requireNonNull(orderstatus, "`orderstatus` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
			this.address = address;
			this.orderdetail = orderdetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getOrdermasterid() {
			return ordermasterid;
		}

		public Integer getOrderno() {
			return orderno;
		}

		public Date getOrderdate() {
			return orderdate;
		}

		public Long getSuppliercode() {
			return suppliercode;
		}

		public Long getCompanycode() {
			return companycode;
		}

		public Long getCompgroupcode() {
			return compgroupcode;
		}

		public Integer getOrderdays() {
			return orderdays;
		}

		public Integer getCalmethod() {
			return calmethod;
		}

		public Integer getExcludelockbatch() {
			return excludelockbatch;
		}

		public Integer getIncludesubstockiestsale() {
			return includesubstockiestsale;
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

		public Long getModifiedby() {
			return modifiedby;
		}

		public Timestamp getModifiedon() {
			return modifiedon;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public Integer getPurchasevouno() {
			return purchasevouno;
		}

		public Timestamp getPurchasevoudate() {
			return purchasevoudate;
		}

		public Integer getOrderstatus() {
			return orderstatus;
		}

		public Integer getActive() {
			return active;
		}

		public String getAddress() {
			return address;
		}

		public List<OrderdetailBody> getOrderdetail() {
			return orderdetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Orderdetail entity")
	public static final class OrderdetailBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORDERMASTERID_BODY' (required) (Size = 20)") Long ordermasterid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDETAILID_BODY' (Primary Key)") Long orderdetailid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'PRODUCTCODE_BODY' (required) (Size = 20)") Long productcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QUANTITY_BODY' (Size = 11)") Integer quantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERFREE_BODY' (Size = 11)") Integer orderfree;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABQTY1_BODY' (Size = 11)") Integer orderslabqty1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABFREE1_BODY' (Size = 11)") Integer orderslabfree1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABQTY2_BODY' (Size = 11)") Integer orderslabqty2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABFREE2_BODY' (Size = 11)") Integer orderslabfree2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABQTY3_BODY' (Size = 11)") Integer orderslabqty3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERSLABFREE3_BODY' (Size = 11)") Integer orderslabfree3;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'MACINFO_BODY' (required) (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACTIVE_BODY' (required) (Size = 1)") Integer active;

		@JsonCreator
		public OrderdetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("ordermasterid") Long ordermasterid,
				@JsonProperty("orderdetailid") Long orderdetailid,
				@JsonProperty("productcode") Long productcode,
				@JsonProperty("quantity") Integer quantity,
				@JsonProperty("orderfree") Integer orderfree,
				@JsonProperty("orderslabqty1") Integer orderslabqty1,
				@JsonProperty("orderslabfree1") Integer orderslabfree1,
				@JsonProperty("orderslabqty2") Integer orderslabqty2,
				@JsonProperty("orderslabfree2") Integer orderslabfree2,
				@JsonProperty("orderslabqty3") Integer orderslabqty3,
				@JsonProperty("orderslabfree3") Integer orderslabfree3,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("active") Integer active) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.ordermasterid = Objects.requireNonNull(ordermasterid, "`ordermasterid` is required");
			this.orderdetailid = Objects.requireNonNull(orderdetailid, "`orderdetailid` is required");
			this.productcode = Objects.requireNonNull(productcode, "`productcode` is required");
			this.quantity = quantity;
			this.orderfree = orderfree;
			this.orderslabqty1 = orderslabqty1;
			this.orderslabfree1 = orderslabfree1;
			this.orderslabqty2 = orderslabqty2;
			this.orderslabfree2 = orderslabfree2;
			this.orderslabqty3 = orderslabqty3;
			this.orderslabfree3 = orderslabfree3;
			this.macinfo = Objects.requireNonNull(macinfo, "`macinfo` is required");
			this.sessionid = sessionid;
			this.description = description;
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.active = Objects.requireNonNull(active, "`active` is required");
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getOrdermasterid() {
			return ordermasterid;
		}

		public Long getOrderdetailid() {
			return orderdetailid;
		}

		public Long getProductcode() {
			return productcode;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public Integer getOrderfree() {
			return orderfree;
		}

		public Integer getOrderslabqty1() {
			return orderslabqty1;
		}

		public Integer getOrderslabfree1() {
			return orderslabfree1;
		}

		public Integer getOrderslabqty2() {
			return orderslabqty2;
		}

		public Integer getOrderslabfree2() {
			return orderslabfree2;
		}

		public Integer getOrderslabqty3() {
			return orderslabqty3;
		}

		public Integer getOrderslabfree3() {
			return orderslabfree3;
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
	}

}