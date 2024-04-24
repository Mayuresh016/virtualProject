package com.micropro.common.pharmazip.model;

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

public class PurchaseImpsetupModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Purchaseimportsetup entity")
	public static final class PurchaseimportsetupBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ID_BODY' (Primary Key)") Integer id;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FILEDESCRIPTION_BODY' (Size = 50)") String filedescription;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 10)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FILETYPE_BODY' (Size = 3)") String filetype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DATEFORMATE_BODY' (Size = 30)") String dateformate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'HEADERROW_BODY' (Size = 20)") String headerrow;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLNO_BODY' (Size = 20)") String billno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDATE_BODY' (Size = 20)") String billdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLAMOUNT_BODY' (Size = 20)") String billamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRNO_BODY' (Size = 20)") String lrno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LRDATE_BODY' (Size = 20)") String lrdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DUEDATE_BODY' (Size = 20)") String duedate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERNO_BODY' (Size = 20)") String orderno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDATE_BODY' (Size = 20)") String orderdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORT_BODY' (Size = 20)") String transport;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTTYPE_BODY' (Size = 20)") String paymenttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASES_BODY' (Size = 20)") String cases;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DETAILROW_BODY' (Size = 20)") String detailrow;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SUPPLIERPRODUCTCODE_BODY' (Size = 20)") String supplierproductcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTCODE_BODY' (Size = 20)") String productcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTNAME_BODY' (Size = 20)") String productname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTPACK_BODY' (Size = 20)") String productpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESS_BODY' (Size = 20)") String cess;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTSHORTNAME_BODY' (Size = 20)") String productshortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHNO_BODY' (Size = 20)") String batchno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BATCHEXPDATE_BODY' (Size = 20)") String batchexpdate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'QUANTITY_BODY' (Size = 20)") String quantity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FREE_BODY' (Size = 20)") String free;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MRP_BODY' (Size = 20)") String mrp;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADERATE_BODY' (Size = 20)") String traderate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALERATE_BODY' (Size = 20)") String salerate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASERATE_BODY' (Size = 20)") String purchaserate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTDISCOUNTPERCENT_BODY' (Size = 20)") String productdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTDISCOUNTAMOUNT_BODY' (Size = 20)") String productdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTPERCENT_BODY' (Size = 20)") String cashdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CASHDISCOUNTAMOUNT_BODY' (Size = 20)") String cashdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXCISEDUTY_BODY' (Size = 20)") String exciseduty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADEDISCOUNTPERCENT_BODY' (Size = 20)") String tradediscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRADEDISCOUNTAMOUNT_BODY' (Size = 20)") String tradediscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PURCHASETAX_BODY' (Size = 20)") String purchasetax;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VATOFFREEAMOUNT_BODY' (Size = 20)") String vatoffreeamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGSTPERCENT_BODY' (Size = 20)") String sgstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SGSTAMOUNT_BODY' (Size = 20)") String sgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGSTPERCENT_BODY' (Size = 20)") String cgstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CGSTAMOUNT_BODY' (Size = 20)") String cgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGSTPERCENT_BODY' (Size = 20)") String igstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IGSTAMOUNT_BODY' (Size = 20)") String igstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CESSAMOUNT_BODY' (Size = 20)") String cessamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'HSNCODE_BODY' (Size = 20)") String hsncode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALDISCOUNTPERCENT_BODY' (Size = 20)") String specialdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FOOTERROW_BODY' (Size = 20)") String footerrow;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GROSSAMOUNT_BODY' (Size = 20)") String grossamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FOOTEREXCISEDUTY_BODY' (Size = 20)") String footerexciseduty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDUCATIONCESS_BODY' (Size = 20)") String educationcess;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEBITNOTEAMOUNT_BODY' (Size = 20)") String debitnoteamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERADDITIONAMOUNT_BODY' (Size = 20)") String otheradditionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SPECIALDISCOUNTAMOUNT_BODY' (Size = 20)") String specialdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLDISCOUNTAMOUNT_BODY' (Size = 20)") String billdiscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITNOTEAMOUNT_BODY' (Size = 20)") String creditnoteamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OTHERDEDUCTIONAMOUNT_BODY' (Size = 20)") String otherdeductionamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ROUNDOFFAMOUNT_BODY' (Size = 20)") String roundoffamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MESSAGE_BODY' (Size = 20)") String message;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGSTAMOUNT_BODY' (Size = 20)") String ugstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'UGSTPERCENT_BODY' (Size = 20)") String ugstpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 100)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALGSTAMOUNT_BODY' (Size = 20)") String totalgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALCGSTAMOUNT_BODY' (Size = 20)") String totalcgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALSGSTAMOUNT_BODY' (Size = 20)") String totalsgstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALUGSTAMOUNT_BODY' (Size = 20)") String totalugstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALIGSTAMOUNT_BODY' (Size = 20)") String totaligstamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALTRADEDISCOUNT_BODY' (Size = 20)") String totaltradediscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALSPECIALDISCOUNT_BODY' (Size = 20)") String totalspecialdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EWAYBILLNUMBER_BODY' (Size = 20)") String ewaybillnumber;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEDISCOUNTAMOUNT_BODY' (Size = 20)") String schemediscountamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEDISCOUNTPERCENT_BODY' (Size = 20)") String schemediscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SUPPLIERPRODUCTPACK_BODY' (Size = 20)") String supplierproductpack;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SUPPLIERPRODUCTNAME_BODY' (Size = 20)") String supplierproductname;

		@JsonCreator
		public PurchaseimportsetupBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("id") Integer id,
				@JsonProperty("filedescription") String filedescription,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("filetype") String filetype,
				@JsonProperty("dateformate") String dateformate,
				@JsonProperty("headerrow") String headerrow,
				@JsonProperty("billno") String billno,
				@JsonProperty("billdate") String billdate,
				@JsonProperty("billamount") String billamount,
				@JsonProperty("lrno") String lrno,
				@JsonProperty("lrdate") String lrdate,
				@JsonProperty("duedate") String duedate,
				@JsonProperty("orderno") String orderno,
				@JsonProperty("orderdate") String orderdate,
				@JsonProperty("transport") String transport,
				@JsonProperty("paymenttype") String paymenttype,
				@JsonProperty("cases") String cases,
				@JsonProperty("detailrow") String detailrow,
				@JsonProperty("supplierproductcode") String supplierproductcode,
				@JsonProperty("productcode") String productcode,
				@JsonProperty("productname") String productname,
				@JsonProperty("productpack") String productpack,
				@JsonProperty("cess") String cess,
				@JsonProperty("productshortname") String productshortname,
				@JsonProperty("batchno") String batchno,
				@JsonProperty("batchexpdate") String batchexpdate,
				@JsonProperty("quantity") String quantity,
				@JsonProperty("free") String free,
				@JsonProperty("mrp") String mrp,
				@JsonProperty("traderate") String traderate,
				@JsonProperty("salerate") String salerate,
				@JsonProperty("purchaserate") String purchaserate,
				@JsonProperty("productdiscountpercent") String productdiscountpercent,
				@JsonProperty("productdiscountamount") String productdiscountamount,
				@JsonProperty("cashdiscountpercent") String cashdiscountpercent,
				@JsonProperty("cashdiscountamount") String cashdiscountamount,
				@JsonProperty("exciseduty") String exciseduty,
				@JsonProperty("tradediscountpercent") String tradediscountpercent,
				@JsonProperty("tradediscountamount") String tradediscountamount,
				@JsonProperty("purchasetax") String purchasetax,
				@JsonProperty("vatoffreeamount") String vatoffreeamount,
				@JsonProperty("sgstpercent") String sgstpercent,
				@JsonProperty("sgstamount") String sgstamount,
				@JsonProperty("cgstpercent") String cgstpercent,
				@JsonProperty("cgstamount") String cgstamount,
				@JsonProperty("igstpercent") String igstpercent,
				@JsonProperty("igstamount") String igstamount,
				@JsonProperty("cessamount") String cessamount,
				@JsonProperty("hsncode") String hsncode,
				@JsonProperty("specialdiscountpercent") String specialdiscountpercent,
				@JsonProperty("footerrow") String footerrow,
				@JsonProperty("grossamount") String grossamount,
				@JsonProperty("footerexciseduty") String footerexciseduty,
				@JsonProperty("educationcess") String educationcess,
				@JsonProperty("debitnoteamount") String debitnoteamount,
				@JsonProperty("otheradditionamount") String otheradditionamount,
				@JsonProperty("specialdiscountamount") String specialdiscountamount,
				@JsonProperty("billdiscountamount") String billdiscountamount,
				@JsonProperty("creditnoteamount") String creditnoteamount,
				@JsonProperty("otherdeductionamount") String otherdeductionamount,
				@JsonProperty("roundoffamount") String roundoffamount,
				@JsonProperty("message") String message,
				@JsonProperty("ugstamount") String ugstamount,
				@JsonProperty("ugstpercent") String ugstpercent,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("totalgstamount") String totalgstamount,
				@JsonProperty("totalcgstamount") String totalcgstamount,
				@JsonProperty("totalsgstamount") String totalsgstamount,
				@JsonProperty("totalugstamount") String totalugstamount,
				@JsonProperty("totaligstamount") String totaligstamount,
				@JsonProperty("totaltradediscount") String totaltradediscount,
				@JsonProperty("totalspecialdiscount") String totalspecialdiscount,
				@JsonProperty("ewaybillnumber") String ewaybillnumber,
				@JsonProperty("schemediscountamount") String schemediscountamount,
				@JsonProperty("schemediscountpercent") String schemediscountpercent,
				@JsonProperty("supplierproductpack") String supplierproductpack,
				@JsonProperty("supplierproductname") String supplierproductname) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.id = Objects.requireNonNull(id, "`id` is required");
			this.filedescription = filedescription;
			this.shortname = shortname;
			this.filetype = filetype;
			this.dateformate = dateformate;
			this.headerrow = headerrow;
			this.billno = billno;
			this.billdate = billdate;
			this.billamount = billamount;
			this.lrno = lrno;
			this.lrdate = lrdate;
			this.duedate = duedate;
			this.orderno = orderno;
			this.orderdate = orderdate;
			this.transport = transport;
			this.paymenttype = paymenttype;
			this.cases = cases;
			this.detailrow = detailrow;
			this.supplierproductcode = supplierproductcode;
			this.productcode = productcode;
			this.productname = productname;
			this.productpack = productpack;
			this.cess = cess;
			this.productshortname = productshortname;
			this.batchno = batchno;
			this.batchexpdate = batchexpdate;
			this.quantity = quantity;
			this.free = free;
			this.mrp = mrp;
			this.traderate = traderate;
			this.salerate = salerate;
			this.purchaserate = purchaserate;
			this.productdiscountpercent = productdiscountpercent;
			this.productdiscountamount = productdiscountamount;
			this.cashdiscountpercent = cashdiscountpercent;
			this.cashdiscountamount = cashdiscountamount;
			this.exciseduty = exciseduty;
			this.tradediscountpercent = tradediscountpercent;
			this.tradediscountamount = tradediscountamount;
			this.purchasetax = purchasetax;
			this.vatoffreeamount = vatoffreeamount;
			this.sgstpercent = sgstpercent;
			this.sgstamount = sgstamount;
			this.cgstpercent = cgstpercent;
			this.cgstamount = cgstamount;
			this.igstpercent = igstpercent;
			this.igstamount = igstamount;
			this.cessamount = cessamount;
			this.hsncode = hsncode;
			this.specialdiscountpercent = specialdiscountpercent;
			this.footerrow = footerrow;
			this.grossamount = grossamount;
			this.footerexciseduty = footerexciseduty;
			this.educationcess = educationcess;
			this.debitnoteamount = debitnoteamount;
			this.otheradditionamount = otheradditionamount;
			this.specialdiscountamount = specialdiscountamount;
			this.billdiscountamount = billdiscountamount;
			this.creditnoteamount = creditnoteamount;
			this.otherdeductionamount = otherdeductionamount;
			this.roundoffamount = roundoffamount;
			this.message = message;
			this.ugstamount = ugstamount;
			this.ugstpercent = ugstpercent;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyby = modifyby;
			this.modifyon = modifyon;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
			this.totalgstamount = totalgstamount;
			this.totalcgstamount = totalcgstamount;
			this.totalsgstamount = totalsgstamount;
			this.totalugstamount = totalugstamount;
			this.totaligstamount = totaligstamount;
			this.totaltradediscount = totaltradediscount;
			this.totalspecialdiscount = totalspecialdiscount;
			this.ewaybillnumber = ewaybillnumber;
			this.schemediscountamount = schemediscountamount;
			this.schemediscountpercent = schemediscountpercent;
			this.supplierproductpack = supplierproductpack;
			this.supplierproductname = supplierproductname;
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

		public String getFiledescription() {
			return filedescription;
		}

		public String getShortname() {
			return shortname;
		}

		public String getFiletype() {
			return filetype;
		}

		public String getDateformate() {
			return dateformate;
		}

		public String getHeaderrow() {
			return headerrow;
		}

		public String getBillno() {
			return billno;
		}

		public String getBilldate() {
			return billdate;
		}

		public String getBillamount() {
			return billamount;
		}

		public String getLrno() {
			return lrno;
		}

		public String getLrdate() {
			return lrdate;
		}

		public String getDuedate() {
			return duedate;
		}

		public String getOrderno() {
			return orderno;
		}

		public String getOrderdate() {
			return orderdate;
		}

		public String getTransport() {
			return transport;
		}

		public String getPaymenttype() {
			return paymenttype;
		}

		public String getCases() {
			return cases;
		}

		public String getDetailrow() {
			return detailrow;
		}

		public String getSupplierproductcode() {
			return supplierproductcode;
		}

		public String getProductcode() {
			return productcode;
		}

		public String getProductname() {
			return productname;
		}

		public String getProductpack() {
			return productpack;
		}

		public String getCess() {
			return cess;
		}

		public String getProductshortname() {
			return productshortname;
		}

		public String getBatchno() {
			return batchno;
		}

		public String getBatchexpdate() {
			return batchexpdate;
		}

		public String getQuantity() {
			return quantity;
		}

		public String getFree() {
			return free;
		}

		public String getMrp() {
			return mrp;
		}

		public String getTraderate() {
			return traderate;
		}

		public String getSalerate() {
			return salerate;
		}

		public String getPurchaserate() {
			return purchaserate;
		}

		public String getProductdiscountpercent() {
			return productdiscountpercent;
		}

		public String getProductdiscountamount() {
			return productdiscountamount;
		}

		public String getCashdiscountpercent() {
			return cashdiscountpercent;
		}

		public String getCashdiscountamount() {
			return cashdiscountamount;
		}

		public String getExciseduty() {
			return exciseduty;
		}

		public String getTradediscountpercent() {
			return tradediscountpercent;
		}

		public String getTradediscountamount() {
			return tradediscountamount;
		}

		public String getPurchasetax() {
			return purchasetax;
		}

		public String getVatoffreeamount() {
			return vatoffreeamount;
		}

		public String getSgstpercent() {
			return sgstpercent;
		}

		public String getSgstamount() {
			return sgstamount;
		}

		public String getCgstpercent() {
			return cgstpercent;
		}

		public String getCgstamount() {
			return cgstamount;
		}

		public String getIgstpercent() {
			return igstpercent;
		}

		public String getIgstamount() {
			return igstamount;
		}

		public String getCessamount() {
			return cessamount;
		}

		public String getHsncode() {
			return hsncode;
		}

		public String getSpecialdiscountpercent() {
			return specialdiscountpercent;
		}

		public String getFooterrow() {
			return footerrow;
		}

		public String getGrossamount() {
			return grossamount;
		}

		public String getFooterexciseduty() {
			return footerexciseduty;
		}

		public String getEducationcess() {
			return educationcess;
		}

		public String getDebitnoteamount() {
			return debitnoteamount;
		}

		public String getOtheradditionamount() {
			return otheradditionamount;
		}

		public String getSpecialdiscountamount() {
			return specialdiscountamount;
		}

		public String getBilldiscountamount() {
			return billdiscountamount;
		}

		public String getCreditnoteamount() {
			return creditnoteamount;
		}

		public String getOtherdeductionamount() {
			return otherdeductionamount;
		}

		public String getRoundoffamount() {
			return roundoffamount;
		}

		public String getMessage() {
			return message;
		}

		public String getUgstamount() {
			return ugstamount;
		}

		public String getUgstpercent() {
			return ugstpercent;
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

		public String getDescription() {
			return description;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public String getTotalgstamount() {
			return totalgstamount;
		}

		public String getTotalcgstamount() {
			return totalcgstamount;
		}

		public String getTotalsgstamount() {
			return totalsgstamount;
		}

		public String getTotalugstamount() {
			return totalugstamount;
		}

		public String getTotaligstamount() {
			return totaligstamount;
		}

		public String getTotaltradediscount() {
			return totaltradediscount;
		}

		public String getTotalspecialdiscount() {
			return totalspecialdiscount;
		}

		public String getEwaybillnumber() {
			return ewaybillnumber;
		}

		public String getSchemediscountamount() {
			return schemediscountamount;
		}

		public String getSchemediscountpercent() {
			return schemediscountpercent;
		}

		public String getSupplierproductpack() {
			return supplierproductpack;
		}

		public String getSupplierproductname() {
			return supplierproductname;
		}
	}

}