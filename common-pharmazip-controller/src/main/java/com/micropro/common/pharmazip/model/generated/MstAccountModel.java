package com.micropro.common.pharmazip.model.generated;

import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.Time;
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

public class MstAccountModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstAccount entity")
	public static final class MstAccountBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACCOUNT_BODY' (required)") AccountBody account;

		@JsonCreator
		public MstAccountBody(
				@JsonProperty("account") AccountBody account) {
			this.account = Objects.requireNonNull(account, "`account` is required");
		}

		public AccountBody getAccount() {
			return account;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Account entity")
	public static final class AccountBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Primary Key)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTNAME_BODY' (Size = 50)") String accountname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BIRTHDAY_BODY'") Date birthday;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOME_BODY' (Size = 20)") String custome;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISDEFAULT_BODY' (Size = 1)") Integer isdefault;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GOVSUPPLY_BODY' (Size = 1)") Integer govsupply;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ANNIVARSARY_BODY'") Date annivarsary;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARENTID_BODY' (Size = 20)") String parentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLUBID_BODY' (Size = 1000)") String clubid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCTAG_BODY' (Size = 1)") String acctag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCEFFECT_BODY' (Size = 1)") String acceffect;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCGROUPSEUENCE_BODY' (Size = 50)") String accgroupseuence;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FIRSTNAME_BODY' (Size = 50)") String firstname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTNAME_BODY' (Size = 50)") String lastname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SHORTNAME_BODY' (Size = 20)") String shortname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MARKETEDBY_BODY' (Size = 20)") String marketedby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCTYPE_BODY' (Size = 1)") Integer acctype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCMODE_BODY' (Size = 1)") Integer accmode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS1_BODY' (Size = 50)") String address1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS2_BODY' (Size = 50)") String address2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDRESS3_BODY' (Size = 50)") String address3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WEBSITE_BODY' (Size = 50)") String website;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LANDLINE_BODY' (Size = 20)") String landline;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE1_BODY' (Size = 20)") String mobile1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE2_BODY' (Size = 20)") String mobile2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Size = 20)") Integer areaid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Integer cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") Integer stateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") Integer countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISUT_BODY' (Size = 1)") Integer isut;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 10)") Integer pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWDISCOUNT_BODY' (Size = 1)") Integer allowdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MINDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal mindiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXDISCOUNTPERCENT_BODY' (Size = 18,4)") BigDecimal maxdiscountpercent;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITLIMIT_BODY' (Size = 18,4)") BigDecimal creditlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDAYS_BODY' (Size = 5)") Integer creditdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONALCREDITLIMIT_BODY' (Size = 18,4)") BigDecimal additionalcreditlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONACREDITDAYS_BODY' (Size = 5)") Integer additionacreditdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDITIONANOOFBILLS_BODY' (Size = 5)") Integer additionanoofbills;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKUPTODATE_BODY'") Date lockuptodate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTION_BODY' (Size = 1)") Integer action;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALEMANID_BODY' (Size = 1)") Integer salemanid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCIDENTITY_BODY' (Size = 1)") Integer accidentity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDAYS_BODY' (Size = 1)") Integer orderdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDATES_BODY' (Size = 5)") String orderdates;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTNO_BODY' (Size = 20)") String gstno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PANNO_BODY' (Size = 10)") String panno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTTYPE_BODY' (Size = 1)") Integer gsttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SEZTYPE_BODY' (Size = 1)") Integer seztype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLTYPE_BODY' (Size = 1)") Integer billtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PAYMENTTYPE_BODY' (Size = 1)") Integer paymenttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAILINVOICE_BODY' (Size = 1)") Integer mailinvoice;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNAME_BODY' (Size = 50)") String bankname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD_BODY' (Size = 50)") String bankadd;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTNO_BODY' (Size = 50)") Long accountno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IFSCCODE_BODY' (Size = 20)") String ifsccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTERNAME_BODY' (Size = 50)") String transportername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTERMOBILE_BODY' (Size = 10)") String transportermobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TRANSPORTERREMARKS_BODY' (Size = 250)") String transporterremarks;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DEFAULTDOCTOR_BODY' (Size = 1)") Integer defaultdoctor;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DDCOMMISSION_BODY' (Size = 18,4)") BigDecimal ddcommission;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LINKCODE_BODY' (Size = 20)") String linkcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCACTIVE_BODY' (Size = 1)") Integer accactive;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLOSINGBALANCE_BODY' (Size = 18,4)") BigDecimal closingbalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPENINGBALANCE_BODY' (Size = 18,4)") BigDecimal openingbalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EXPIRYWITHINDAYS_BODY' (Size = 5)") Integer expirywithindays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWNEWITEM_BODY' (Size = 1)") Integer allownewitem;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECATEGORYID_BODY' (Size = 20)") Long ratecategoryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLLECTIONDAYS_BODY' (Size = 20)") String collectiondays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLLECTIONDATES_BODY' (Size = 20)") String collectiondates;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMPANYCONSTITUTION_BODY' (Size = 1)") Integer companyconstitution;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTBILLDATE_BODY'") Date lastbilldate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPNAME_BODY' (Size = 50)") String cpname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPMOBILE_BODY' (Size = 20)") String cpmobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPEMAIL_BODY' (Size = 50)") String cpemail;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEID_BODY' (Size = 1)") Integer schemeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CARRYFORWARDBILLS_BODY' (Size = 1)") Integer carryforwardbills;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SMSOPTION_BODY' (Size = 1)") Integer smsoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAILOPTION_BODY' (Size = 1)") Integer emailoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WHATSAPPOPTION_BODY' (Size = 1)") Integer whatsappoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SLABWISEDISCOUNT_BODY' (Size = 1)") Integer slabwisediscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLORCODE_BODY' (Size = 10)") String colorcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VALUETAG_BODY' (Size = 1)") Integer valuetag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CNADJUSTAFTERDAYS_BODY' (Size = 5)") Integer cnadjustafterdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKED_BODY' (Size = 1)") Integer locked;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPUSERNAME_BODY' (Size = 20)") String appusername;
		private @ApiModelProperty(required = false, value = "REST representation of the 'APPPASSWORD_BODY' (Size = 20)") String apppassword;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOTES_BODY' (Size = 500)") String notes;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TOTALINVOICEAMOUNT_BODY' (Size = 18,4)") BigDecimal totalinvoiceamount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSAPPLICABLE_BODY' (Size = 1)") Integer tcsapplicable;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSINVOICEBALANCE_BODY' (Size = 18,4)") BigDecimal tcsinvoicebalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMILANID_BODY' (Size = 50)") String emilanid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDECODE_BODY' (Size = 50)") String edecode;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACCOUNTOPRDETAIL_BODY' (required)") List<AccountoprdetailBody> accountoprdetail;

		@JsonCreator
		public AccountBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("accountname") String accountname,
				@JsonProperty("birthday") @JsonFormat(pattern = "yyyy-MM-dd") Date birthday,
				@JsonProperty("custome") String custome,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("isdefault") Integer isdefault,
				@JsonProperty("govsupply") Integer govsupply,
				@JsonProperty("annivarsary") @JsonFormat(pattern = "yyyy-MM-dd") Date annivarsary,
				@JsonProperty("parentid") String parentid,
				@JsonProperty("clubid") String clubid,
				@JsonProperty("acctag") String acctag,
				@JsonProperty("acceffect") String acceffect,
				@JsonProperty("accgroupseuence") String accgroupseuence,
				@JsonProperty("firstname") String firstname,
				@JsonProperty("lastname") String lastname,
				@JsonProperty("shortname") String shortname,
				@JsonProperty("marketedby") String marketedby,
				@JsonProperty("acctype") Integer acctype,
				@JsonProperty("accmode") Integer accmode,
				@JsonProperty("address1") String address1,
				@JsonProperty("address2") String address2,
				@JsonProperty("address3") String address3,
				@JsonProperty("email") String email,
				@JsonProperty("website") String website,
				@JsonProperty("landline") String landline,
				@JsonProperty("mobile1") String mobile1,
				@JsonProperty("mobile2") String mobile2,
				@JsonProperty("areaid") Integer areaid,
				@JsonProperty("cityid") Integer cityid,
				@JsonProperty("stateid") Integer stateid,
				@JsonProperty("countryid") Integer countryid,
				@JsonProperty("isut") Integer isut,
				@JsonProperty("pincode") Integer pincode,
				@JsonProperty("allowdiscount") Integer allowdiscount,
				@JsonProperty("mindiscountpercent") BigDecimal mindiscountpercent,
				@JsonProperty("maxdiscountpercent") BigDecimal maxdiscountpercent,
				@JsonProperty("creditlimit") BigDecimal creditlimit,
				@JsonProperty("creditdays") Integer creditdays,
				@JsonProperty("additionalcreditlimit") BigDecimal additionalcreditlimit,
				@JsonProperty("additionacreditdays") Integer additionacreditdays,
				@JsonProperty("additionanoofbills") Integer additionanoofbills,
				@JsonProperty("lockuptodate") @JsonFormat(pattern = "yyyy-MM-dd") Date lockuptodate,
				@JsonProperty("action") Integer action,
				@JsonProperty("salemanid") Integer salemanid,
				@JsonProperty("accidentity") Integer accidentity,
				@JsonProperty("orderdays") Integer orderdays,
				@JsonProperty("orderdates") String orderdates,
				@JsonProperty("gstno") String gstno,
				@JsonProperty("panno") String panno,
				@JsonProperty("gsttype") Integer gsttype,
				@JsonProperty("seztype") Integer seztype,
				@JsonProperty("billtype") Integer billtype,
				@JsonProperty("paymenttype") Integer paymenttype,
				@JsonProperty("mailinvoice") Integer mailinvoice,
				@JsonProperty("bankname") String bankname,
				@JsonProperty("bankadd") String bankadd,
				@JsonProperty("accountno") Long accountno,
				@JsonProperty("ifsccode") String ifsccode,
				@JsonProperty("transportername") String transportername,
				@JsonProperty("transportermobile") String transportermobile,
				@JsonProperty("transporterremarks") String transporterremarks,
				@JsonProperty("defaultdoctor") Integer defaultdoctor,
				@JsonProperty("ddcommission") BigDecimal ddcommission,
				@JsonProperty("linkcode") String linkcode,
				@JsonProperty("accactive") Integer accactive,
				@JsonProperty("closingbalance") BigDecimal closingbalance,
				@JsonProperty("openingbalance") BigDecimal openingbalance,
				@JsonProperty("expirywithindays") Integer expirywithindays,
				@JsonProperty("allownewitem") Integer allownewitem,
				@JsonProperty("ratecategoryid") Long ratecategoryid,
				@JsonProperty("collectiondays") String collectiondays,
				@JsonProperty("collectiondates") String collectiondates,
				@JsonProperty("companyconstitution") Integer companyconstitution,
				@JsonProperty("lastbilldate") @JsonFormat(pattern = "yyyy-MM-dd") Date lastbilldate,
				@JsonProperty("cpname") String cpname,
				@JsonProperty("cpmobile") String cpmobile,
				@JsonProperty("cpemail") String cpemail,
				@JsonProperty("schemeid") Integer schemeid,
				@JsonProperty("carryforwardbills") Integer carryforwardbills,
				@JsonProperty("smsoption") Integer smsoption,
				@JsonProperty("emailoption") Integer emailoption,
				@JsonProperty("whatsappoption") Integer whatsappoption,
				@JsonProperty("slabwisediscount") Integer slabwisediscount,
				@JsonProperty("colorcode") String colorcode,
				@JsonProperty("valuetag") Integer valuetag,
				@JsonProperty("cnadjustafterdays") Integer cnadjustafterdays,
				@JsonProperty("locked") Integer locked,
				@JsonProperty("appusername") String appusername,
				@JsonProperty("apppassword") String apppassword,
				@JsonProperty("notes") String notes,
				@JsonProperty("totalinvoiceamount") BigDecimal totalinvoiceamount,
				@JsonProperty("tcsapplicable") Integer tcsapplicable,
				@JsonProperty("tcsinvoicebalance") BigDecimal tcsinvoicebalance,
				@JsonProperty("emilanid") String emilanid,
				@JsonProperty("edecode") String edecode,
				@JsonProperty("accountoprdetail") List<AccountoprdetailBody> accountoprdetail) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = oprid;
			this.accountid = Objects.requireNonNull(accountid, "`accountid` is required");
			this.accountname = accountname;
			this.birthday = birthday;
			this.custome = custome;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.macinfo = macinfo;
			this.description = description;
			this.sessionid = sessionid;
			this.isdefault = isdefault;
			this.govsupply = govsupply;
			this.annivarsary = annivarsary;
			this.parentid = parentid;
			this.clubid = clubid;
			this.acctag = acctag;
			this.acceffect = acceffect;
			this.accgroupseuence = accgroupseuence;
			this.firstname = firstname;
			this.lastname = lastname;
			this.shortname = shortname;
			this.marketedby = marketedby;
			this.acctype = acctype;
			this.accmode = accmode;
			this.address1 = address1;
			this.address2 = address2;
			this.address3 = address3;
			this.email = email;
			this.website = website;
			this.landline = landline;
			this.mobile1 = mobile1;
			this.mobile2 = mobile2;
			this.areaid = areaid;
			this.cityid = cityid;
			this.stateid = stateid;
			this.countryid = countryid;
			this.isut = isut;
			this.pincode = pincode;
			this.allowdiscount = allowdiscount;
			this.mindiscountpercent = mindiscountpercent;
			this.maxdiscountpercent = maxdiscountpercent;
			this.creditlimit = creditlimit;
			this.creditdays = creditdays;
			this.additionalcreditlimit = additionalcreditlimit;
			this.additionacreditdays = additionacreditdays;
			this.additionanoofbills = additionanoofbills;
			this.lockuptodate = lockuptodate;
			this.action = action;
			this.salemanid = salemanid;
			this.accidentity = accidentity;
			this.orderdays = orderdays;
			this.orderdates = orderdates;
			this.gstno = gstno;
			this.panno = panno;
			this.gsttype = gsttype;
			this.seztype = seztype;
			this.billtype = billtype;
			this.paymenttype = paymenttype;
			this.mailinvoice = mailinvoice;
			this.bankname = bankname;
			this.bankadd = bankadd;
			this.accountno = accountno;
			this.ifsccode = ifsccode;
			this.transportername = transportername;
			this.transportermobile = transportermobile;
			this.transporterremarks = transporterremarks;
			this.defaultdoctor = defaultdoctor;
			this.ddcommission = ddcommission;
			this.linkcode = linkcode;
			this.accactive = accactive;
			this.closingbalance = closingbalance;
			this.openingbalance = openingbalance;
			this.expirywithindays = expirywithindays;
			this.allownewitem = allownewitem;
			this.ratecategoryid = ratecategoryid;
			this.collectiondays = collectiondays;
			this.collectiondates = collectiondates;
			this.companyconstitution = companyconstitution;
			this.lastbilldate = lastbilldate;
			this.cpname = cpname;
			this.cpmobile = cpmobile;
			this.cpemail = cpemail;
			this.schemeid = schemeid;
			this.carryforwardbills = carryforwardbills;
			this.smsoption = smsoption;
			this.emailoption = emailoption;
			this.whatsappoption = whatsappoption;
			this.slabwisediscount = slabwisediscount;
			this.colorcode = colorcode;
			this.valuetag = valuetag;
			this.cnadjustafterdays = cnadjustafterdays;
			this.locked = locked;
			this.appusername = appusername;
			this.apppassword = apppassword;
			this.notes = notes;
			this.totalinvoiceamount = totalinvoiceamount;
			this.tcsapplicable = tcsapplicable;
			this.tcsinvoicebalance = tcsinvoicebalance;
			this.emilanid = emilanid;
			this.edecode = edecode;
			this.accountoprdetail = accountoprdetail;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public String getAccountname() {
			return accountname;
		}

		public Date getBirthday() {
			return birthday;
		}

		public String getCustome() {
			return custome;
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

		public String getLastoperation() {
			return lastoperation;
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

		public Integer getIsdefault() {
			return isdefault;
		}

		public Integer getGovsupply() {
			return govsupply;
		}

		public Date getAnnivarsary() {
			return annivarsary;
		}

		public String getParentid() {
			return parentid;
		}

		public String getClubid() {
			return clubid;
		}

		public String getAcctag() {
			return acctag;
		}

		public String getAcceffect() {
			return acceffect;
		}

		public String getAccgroupseuence() {
			return accgroupseuence;
		}

		public String getFirstname() {
			return firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public String getShortname() {
			return shortname;
		}

		public String getMarketedby() {
			return marketedby;
		}

		public Integer getAcctype() {
			return acctype;
		}

		public Integer getAccmode() {
			return accmode;
		}

		public String getAddress1() {
			return address1;
		}

		public String getAddress2() {
			return address2;
		}

		public String getAddress3() {
			return address3;
		}

		public String getEmail() {
			return email;
		}

		public String getWebsite() {
			return website;
		}

		public String getLandline() {
			return landline;
		}

		public String getMobile1() {
			return mobile1;
		}

		public String getMobile2() {
			return mobile2;
		}

		public Integer getAreaid() {
			return areaid;
		}

		public Integer getCityid() {
			return cityid;
		}

		public Integer getStateid() {
			return stateid;
		}

		public Integer getCountryid() {
			return countryid;
		}

		public Integer getIsut() {
			return isut;
		}

		public Integer getPincode() {
			return pincode;
		}

		public Integer getAllowdiscount() {
			return allowdiscount;
		}

		public BigDecimal getMindiscountpercent() {
			return mindiscountpercent;
		}

		public BigDecimal getMaxdiscountpercent() {
			return maxdiscountpercent;
		}

		public BigDecimal getCreditlimit() {
			return creditlimit;
		}

		public Integer getCreditdays() {
			return creditdays;
		}

		public BigDecimal getAdditionalcreditlimit() {
			return additionalcreditlimit;
		}

		public Integer getAdditionacreditdays() {
			return additionacreditdays;
		}

		public Integer getAdditionanoofbills() {
			return additionanoofbills;
		}

		public Date getLockuptodate() {
			return lockuptodate;
		}

		public Integer getAction() {
			return action;
		}

		public Integer getSalemanid() {
			return salemanid;
		}

		public Integer getAccidentity() {
			return accidentity;
		}

		public Integer getOrderdays() {
			return orderdays;
		}

		public String getOrderdates() {
			return orderdates;
		}

		public String getGstno() {
			return gstno;
		}

		public String getPanno() {
			return panno;
		}

		public Integer getGsttype() {
			return gsttype;
		}

		public Integer getSeztype() {
			return seztype;
		}

		public Integer getBilltype() {
			return billtype;
		}

		public Integer getPaymenttype() {
			return paymenttype;
		}

		public Integer getMailinvoice() {
			return mailinvoice;
		}

		public String getBankname() {
			return bankname;
		}

		public String getBankadd() {
			return bankadd;
		}

		public Long getAccountno() {
			return accountno;
		}

		public String getIfsccode() {
			return ifsccode;
		}

		public String getTransportername() {
			return transportername;
		}

		public String getTransportermobile() {
			return transportermobile;
		}

		public String getTransporterremarks() {
			return transporterremarks;
		}

		public Integer getDefaultdoctor() {
			return defaultdoctor;
		}

		public BigDecimal getDdcommission() {
			return ddcommission;
		}

		public String getLinkcode() {
			return linkcode;
		}

		public Integer getAccactive() {
			return accactive;
		}

		public BigDecimal getClosingbalance() {
			return closingbalance;
		}

		public BigDecimal getOpeningbalance() {
			return openingbalance;
		}

		public Integer getExpirywithindays() {
			return expirywithindays;
		}

		public Integer getAllownewitem() {
			return allownewitem;
		}

		public Long getRatecategoryid() {
			return ratecategoryid;
		}

		public String getCollectiondays() {
			return collectiondays;
		}

		public String getCollectiondates() {
			return collectiondates;
		}

		public Integer getCompanyconstitution() {
			return companyconstitution;
		}

		public Date getLastbilldate() {
			return lastbilldate;
		}

		public String getCpname() {
			return cpname;
		}

		public String getCpmobile() {
			return cpmobile;
		}

		public String getCpemail() {
			return cpemail;
		}

		public Integer getSchemeid() {
			return schemeid;
		}

		public Integer getCarryforwardbills() {
			return carryforwardbills;
		}

		public Integer getSmsoption() {
			return smsoption;
		}

		public Integer getEmailoption() {
			return emailoption;
		}

		public Integer getWhatsappoption() {
			return whatsappoption;
		}

		public Integer getSlabwisediscount() {
			return slabwisediscount;
		}

		public String getColorcode() {
			return colorcode;
		}

		public Integer getValuetag() {
			return valuetag;
		}

		public Integer getCnadjustafterdays() {
			return cnadjustafterdays;
		}

		public Integer getLocked() {
			return locked;
		}

		public String getAppusername() {
			return appusername;
		}

		public String getApppassword() {
			return apppassword;
		}

		public String getNotes() {
			return notes;
		}

		public BigDecimal getTotalinvoiceamount() {
			return totalinvoiceamount;
		}

		public Integer getTcsapplicable() {
			return tcsapplicable;
		}

		public BigDecimal getTcsinvoicebalance() {
			return tcsinvoicebalance;
		}

		public String getEmilanid() {
			return emilanid;
		}

		public String getEdecode() {
			return edecode;
		}

		public List<AccountoprdetailBody> getAccountoprdetail() {
			return accountoprdetail;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Accountoprdetail entity")
	public static final class AccountoprdetailBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTOPRDETAILID_BODY' (Primary Key)") Long accountoprdetailid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 50)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 50)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD3_BODY' (Size = 50)") String add3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") Long countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") Long stateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Size = 20)") Long areaid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 10)") String pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARENTID_BODY' (Size = 20)") Long parentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GROUPCODE_BODY' (Size = 20)") String groupcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTNO_BODY' (Size = 20)") String gstno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PANNO_BODY' (Size = 20)") String panno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NARCOTICSALLOWED_BODY' (Size = 1)") Integer narcoticsallowed;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NEAREXPIRYALLOWED_BODY' (Size = 1)") Integer nearexpiryallowed;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NEWITEMALLOWEDED_BODY' (Size = 1)") Integer newitemalloweded;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLTYPE_BODY' (Size = 1)") Integer billtype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITDAYS_BODY' (Size = 4)") Integer creditdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREDITLIMIT_BODY' (Size = 18,4)") BigDecimal creditlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BILLLIMIT_BODY' (Size = 20)") Long billlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTION_BODY' (Size = 1)") Integer action;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLLECTIONDAYS_BODY' (Size = 200)") String collectiondays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLLECTIONDATES_BODY' (Size = 200)") String collectiondates;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILENO_BODY' (Size = 50)") String mobileno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WHATSAPPNO_BODY' (Size = 50)") String whatsappno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTCOMPANYCONSTI_BODY' (Size = 1)") Integer accountcompanyconsti;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MINDISCOUNT_BODY' (Size = 18,4)") BigDecimal mindiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MAXDISCOUNT_BODY' (Size = 18,4)") BigDecimal maxdiscount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWDMINVOICE_BODY' (Size = 1)") Integer allowdminvoice;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPNAME_BODY' (Size = 50)") String cpname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPPHONE_BODY' (Size = 50)") String cpphone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPEMAIL_BODY' (Size = 50)") String cpemail;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLOURCODE_BODY' (Size = 50)") String colourcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VALUETAG_BODY' (Size = 1)") Integer valuetag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKUPTODATE_BODY'") Date lockuptodate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SCHEMEID_BODY' (Size = 20)") Long schemeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CFBILL_BODY' (Size = 1)") Integer cfbill;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDCRDAYS_BODY' (Size = 20)") Long addcrdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDBILLLIMIT_BODY' (Size = 20)") Long addbilllimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'RATECATEGORYID_BODY' (Size = 20)") Long ratecategoryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SMSOPTION_BODY' (Size = 1)") Integer smsoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAILOPTION_BODY' (Size = 1)") Integer emailoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'WHATSAPPOPTION_BODY' (Size = 1)") Integer whatsappoption;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELIVERYTYPE_BODY' (Size = 1)") Integer deliverytype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SLABDISC_BODY' (Size = 18,4)") BigDecimal slabdisc;
		private @ApiModelProperty(required = false, value = "REST representation of the 'USERNAME_BODY' (Size = 50)") String username;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PASSWORD_BODY' (Size = 50)") String password;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOTES_BODY' (Size = 500)") String notes;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADDCRLIMIT_BODY' (Size = 18,4)") BigDecimal addcrlimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACCOUNTID_BODY' (required) (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GSTTYPE_BODY' (Size = 1)") Integer gsttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DELIVERYTYPEID_BODY' (Size = 20)") Long deliverytypeid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PHONENO_BODY' (Size = 50)") String phoneno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CRNOTEADJAFTERDAYS_BODY' (Size = 5)") Integer crnoteadjafterdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOOFBILLS_BODY' (Size = 5)") Integer noofbills;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NOOFBILLSLIMIT_BODY' (Size = 5)") Integer noofbillslimit;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERCATEGORY_BODY' (Size = 1)") Integer customercategory;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MULTIPLESERIESREQ_BODY' (Size = 1)") Integer multipleseriesreq;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTBILLDATE_BODY'") Date lastbilldate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMILANUSERID_BODY' (Size = 20)") String emilanuserid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'GROUPOFACCOUNT_BODY' (Size = 20)") Long groupofaccount;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EDEAREACODE_BODY' (Size = 20)") String edeareacode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SALESMANID_BODY' (Size = 20)") Long salesmanid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPNAME1_BODY' (Size = 50)") String cpname1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPHONE1_BODY' (Size = 50)") String cphone1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CPEMAIL1_BODY' (Size = 50)") String cpemail1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCUMENTID_BODY' (Size = 20)") Long documentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LEDGERBALANCE_BODY' (Size = 18,4)") BigDecimal ledgerbalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LOCKED_BODY' (Size = 1)") Integer locked;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDAYS_BODY' (Size = 200)") String orderdays;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDATES_BODY' (Size = 200)") String orderdates;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISGROUP_BODY' (Size = 1)") Integer isgroup;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSAPPLICABLE_BODY' (Size = 1)") Integer tdsapplicable;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSLESSPER_BODY' (Size = 18,4)") BigDecimal tdslessper;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CALCULATIONON_BODY' (Size = 1)") Integer calculationon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TINNO_BODY' (Size = 50)") String tinno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPENINGBALANCE_BODY' (Size = 18,4)") BigDecimal openingbalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPENINGBALCRDR_BODY' (Size = 1)") Integer openingbalcrdr;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNAME_BODY' (Size = 500)") String bankname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKACCOUNTCODE_BODY' (Size = 100)") String bankaccountcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD1_BODY' (Size = 100)") String bankadd1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD2_BODY' (Size = 100)") String bankadd2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD3_BODY' (Size = 100)") String bankadd3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IFSCCODE_BODY' (Size = 100)") String ifsccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MICRCODE_BODY' (Size = 100)") String micrcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNOTES_BODY' (Size = 500)") String banknotes;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKCITYID_BODY' (Size = 20)") Long bankcityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTTYPE_BODY' (Size = 1)") String accounttype;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'ACCOUNTSERIALNO_BODY' (required) (Size = 10)") Integer accountserialno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTREVERSECODE_BODY' (Size = 20)") Long accountreversecode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSAPPLICABLE_BODY' (Size = 1)") Integer tcsapplicable;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TCSNETAMT_BODY' (Size = 18,4)") BigDecimal tcsnetamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'TDSNETAMT_BODY' (Size = 18,4)") BigDecimal tdsnetamt;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWSPECIALQTY_BODY' (Size = 1)") Integer allowspecialqty;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERDAY_BODY' (Size = 200)") String orderday;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ORDERTIME_BODY'") Time ordertime;
		private @ApiModelProperty(required = false, value = "REST representation of the 'INTERESTRATE_BODY' (Size = 18,4)") BigDecimal interestrate;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLOSINGBALANCE_BODY' (Size = 18,4)") BigDecimal closingbalance;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ISSUBSTOCKIEST_BODY' (Size = 1)") Integer issubstockiest;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DISTANCEINKM_BODY' (Size = 10)") Integer distanceinkm;

		@JsonCreator
		public AccountoprdetailBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("accountoprdetailid") Long accountoprdetailid,
				@JsonProperty("add1") String add1,
				@JsonProperty("add2") String add2,
				@JsonProperty("add3") String add3,
				@JsonProperty("countryid") Long countryid,
				@JsonProperty("stateid") Long stateid,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("areaid") Long areaid,
				@JsonProperty("pincode") String pincode,
				@JsonProperty("parentid") Long parentid,
				@JsonProperty("groupcode") String groupcode,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("gstno") String gstno,
				@JsonProperty("panno") String panno,
				@JsonProperty("narcoticsallowed") Integer narcoticsallowed,
				@JsonProperty("nearexpiryallowed") Integer nearexpiryallowed,
				@JsonProperty("newitemalloweded") Integer newitemalloweded,
				@JsonProperty("billtype") Integer billtype,
				@JsonProperty("creditdays") Integer creditdays,
				@JsonProperty("creditlimit") BigDecimal creditlimit,
				@JsonProperty("billlimit") Long billlimit,
				@JsonProperty("action") Integer action,
				@JsonProperty("collectiondays") String collectiondays,
				@JsonProperty("collectiondates") String collectiondates,
				@JsonProperty("mobileno") String mobileno,
				@JsonProperty("whatsappno") String whatsappno,
				@JsonProperty("email") String email,
				@JsonProperty("accountcompanyconsti") Integer accountcompanyconsti,
				@JsonProperty("mindiscount") BigDecimal mindiscount,
				@JsonProperty("maxdiscount") BigDecimal maxdiscount,
				@JsonProperty("allowdminvoice") Integer allowdminvoice,
				@JsonProperty("cpname") String cpname,
				@JsonProperty("cpphone") String cpphone,
				@JsonProperty("cpemail") String cpemail,
				@JsonProperty("colourcode") String colourcode,
				@JsonProperty("valuetag") Integer valuetag,
				@JsonProperty("lockuptodate") @JsonFormat(pattern = "yyyy-MM-dd") Date lockuptodate,
				@JsonProperty("schemeid") Long schemeid,
				@JsonProperty("cfbill") Integer cfbill,
				@JsonProperty("addcrdays") Long addcrdays,
				@JsonProperty("addbilllimit") Long addbilllimit,
				@JsonProperty("ratecategoryid") Long ratecategoryid,
				@JsonProperty("smsoption") Integer smsoption,
				@JsonProperty("emailoption") Integer emailoption,
				@JsonProperty("whatsappoption") Integer whatsappoption,
				@JsonProperty("deliverytype") Integer deliverytype,
				@JsonProperty("slabdisc") BigDecimal slabdisc,
				@JsonProperty("username") String username,
				@JsonProperty("password") String password,
				@JsonProperty("notes") String notes,
				@JsonProperty("addcrlimit") BigDecimal addcrlimit,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("gsttype") Integer gsttype,
				@JsonProperty("active") Integer active,
				@JsonProperty("deliverytypeid") Long deliverytypeid,
				@JsonProperty("phoneno") String phoneno,
				@JsonProperty("crnoteadjafterdays") Integer crnoteadjafterdays,
				@JsonProperty("noofbills") Integer noofbills,
				@JsonProperty("noofbillslimit") Integer noofbillslimit,
				@JsonProperty("customercategory") Integer customercategory,
				@JsonProperty("multipleseriesreq") Integer multipleseriesreq,
				@JsonProperty("lastbilldate") @JsonFormat(pattern = "yyyy-MM-dd") Date lastbilldate,
				@JsonProperty("emilanuserid") String emilanuserid,
				@JsonProperty("groupofaccount") Long groupofaccount,
				@JsonProperty("edeareacode") String edeareacode,
				@JsonProperty("salesmanid") Long salesmanid,
				@JsonProperty("cpname1") String cpname1,
				@JsonProperty("cphone1") String cphone1,
				@JsonProperty("cpemail1") String cpemail1,
				@JsonProperty("documentid") Long documentid,
				@JsonProperty("ledgerbalance") BigDecimal ledgerbalance,
				@JsonProperty("locked") Integer locked,
				@JsonProperty("orderdays") String orderdays,
				@JsonProperty("orderdates") String orderdates,
				@JsonProperty("isgroup") Integer isgroup,
				@JsonProperty("tdsapplicable") Integer tdsapplicable,
				@JsonProperty("tdslessper") BigDecimal tdslessper,
				@JsonProperty("calculationon") Integer calculationon,
				@JsonProperty("tinno") String tinno,
				@JsonProperty("openingbalance") BigDecimal openingbalance,
				@JsonProperty("openingbalcrdr") Integer openingbalcrdr,
				@JsonProperty("bankname") String bankname,
				@JsonProperty("bankaccountcode") String bankaccountcode,
				@JsonProperty("bankadd1") String bankadd1,
				@JsonProperty("bankadd2") String bankadd2,
				@JsonProperty("bankadd3") String bankadd3,
				@JsonProperty("ifsccode") String ifsccode,
				@JsonProperty("micrcode") String micrcode,
				@JsonProperty("banknotes") String banknotes,
				@JsonProperty("bankcityid") Long bankcityid,
				@JsonProperty("accounttype") String accounttype,
				@JsonProperty("accountserialno") Integer accountserialno,
				@JsonProperty("accountreversecode") Long accountreversecode,
				@JsonProperty("tcsapplicable") Integer tcsapplicable,
				@JsonProperty("tcsnetamt") BigDecimal tcsnetamt,
				@JsonProperty("tdsnetamt") BigDecimal tdsnetamt,
				@JsonProperty("allowspecialqty") Integer allowspecialqty,
				@JsonProperty("orderday") String orderday,
				@JsonProperty("ordertime") @JsonFormat(pattern = "HH:mm:ss") Time ordertime,
				@JsonProperty("interestrate") BigDecimal interestrate,
				@JsonProperty("closingbalance") BigDecimal closingbalance,
				@JsonProperty("issubstockiest") Integer issubstockiest,
				@JsonProperty("distanceinkm") Integer distanceinkm) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.accountoprdetailid = Objects.requireNonNull(accountoprdetailid, "`accountoprdetailid` is required");
			this.add1 = add1;
			this.add2 = add2;
			this.add3 = add3;
			this.countryid = countryid;
			this.stateid = stateid;
			this.cityid = cityid;
			this.areaid = areaid;
			this.pincode = pincode;
			this.parentid = parentid;
			this.groupcode = groupcode;
			this.identity = identity;
			this.gstno = gstno;
			this.panno = panno;
			this.narcoticsallowed = narcoticsallowed;
			this.nearexpiryallowed = nearexpiryallowed;
			this.newitemalloweded = newitemalloweded;
			this.billtype = billtype;
			this.creditdays = creditdays;
			this.creditlimit = creditlimit;
			this.billlimit = billlimit;
			this.action = action;
			this.collectiondays = collectiondays;
			this.collectiondates = collectiondates;
			this.mobileno = mobileno;
			this.whatsappno = whatsappno;
			this.email = email;
			this.accountcompanyconsti = accountcompanyconsti;
			this.mindiscount = mindiscount;
			this.maxdiscount = maxdiscount;
			this.allowdminvoice = allowdminvoice;
			this.cpname = cpname;
			this.cpphone = cpphone;
			this.cpemail = cpemail;
			this.colourcode = colourcode;
			this.valuetag = valuetag;
			this.lockuptodate = lockuptodate;
			this.schemeid = schemeid;
			this.cfbill = cfbill;
			this.addcrdays = addcrdays;
			this.addbilllimit = addbilllimit;
			this.ratecategoryid = ratecategoryid;
			this.smsoption = smsoption;
			this.emailoption = emailoption;
			this.whatsappoption = whatsappoption;
			this.deliverytype = deliverytype;
			this.slabdisc = slabdisc;
			this.username = username;
			this.password = password;
			this.notes = notes;
			this.addcrlimit = addcrlimit;
			this.lastoperation = lastoperation;
			this.createdon = createdon;
			this.createdby = createdby;
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.sessionid = sessionid;
			this.macinfo = macinfo;
			this.description = description;
			this.accountid = Objects.requireNonNull(accountid, "`accountid` is required");
			this.gsttype = gsttype;
			this.active = active;
			this.deliverytypeid = deliverytypeid;
			this.phoneno = phoneno;
			this.crnoteadjafterdays = crnoteadjafterdays;
			this.noofbills = noofbills;
			this.noofbillslimit = noofbillslimit;
			this.customercategory = customercategory;
			this.multipleseriesreq = multipleseriesreq;
			this.lastbilldate = lastbilldate;
			this.emilanuserid = emilanuserid;
			this.groupofaccount = groupofaccount;
			this.edeareacode = edeareacode;
			this.salesmanid = salesmanid;
			this.cpname1 = cpname1;
			this.cphone1 = cphone1;
			this.cpemail1 = cpemail1;
			this.documentid = documentid;
			this.ledgerbalance = ledgerbalance;
			this.locked = locked;
			this.orderdays = orderdays;
			this.orderdates = orderdates;
			this.isgroup = isgroup;
			this.tdsapplicable = tdsapplicable;
			this.tdslessper = tdslessper;
			this.calculationon = calculationon;
			this.tinno = tinno;
			this.openingbalance = openingbalance;
			this.openingbalcrdr = openingbalcrdr;
			this.bankname = bankname;
			this.bankaccountcode = bankaccountcode;
			this.bankadd1 = bankadd1;
			this.bankadd2 = bankadd2;
			this.bankadd3 = bankadd3;
			this.ifsccode = ifsccode;
			this.micrcode = micrcode;
			this.banknotes = banknotes;
			this.bankcityid = bankcityid;
			this.accounttype = accounttype;
			this.accountserialno = Objects.requireNonNull(accountserialno, "`accountserialno` is required");
			this.accountreversecode = accountreversecode;
			this.tcsapplicable = tcsapplicable;
			this.tcsnetamt = tcsnetamt;
			this.tdsnetamt = tdsnetamt;
			this.allowspecialqty = allowspecialqty;
			this.orderday = orderday;
			this.ordertime = ordertime;
			this.interestrate = interestrate;
			this.closingbalance = closingbalance;
			this.issubstockiest = issubstockiest;
			this.distanceinkm = distanceinkm;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getAccountoprdetailid() {
			return accountoprdetailid;
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

		public Long getCountryid() {
			return countryid;
		}

		public Long getStateid() {
			return stateid;
		}

		public Long getCityid() {
			return cityid;
		}

		public Long getAreaid() {
			return areaid;
		}

		public String getPincode() {
			return pincode;
		}

		public Long getParentid() {
			return parentid;
		}

		public String getGroupcode() {
			return groupcode;
		}

		public Integer getIdentity() {
			return identity;
		}

		public String getGstno() {
			return gstno;
		}

		public String getPanno() {
			return panno;
		}

		public Integer getNarcoticsallowed() {
			return narcoticsallowed;
		}

		public Integer getNearexpiryallowed() {
			return nearexpiryallowed;
		}

		public Integer getNewitemalloweded() {
			return newitemalloweded;
		}

		public Integer getBilltype() {
			return billtype;
		}

		public Integer getCreditdays() {
			return creditdays;
		}

		public BigDecimal getCreditlimit() {
			return creditlimit;
		}

		public Long getBilllimit() {
			return billlimit;
		}

		public Integer getAction() {
			return action;
		}

		public String getCollectiondays() {
			return collectiondays;
		}

		public String getCollectiondates() {
			return collectiondates;
		}

		public String getMobileno() {
			return mobileno;
		}

		public String getWhatsappno() {
			return whatsappno;
		}

		public String getEmail() {
			return email;
		}

		public Integer getAccountcompanyconsti() {
			return accountcompanyconsti;
		}

		public BigDecimal getMindiscount() {
			return mindiscount;
		}

		public BigDecimal getMaxdiscount() {
			return maxdiscount;
		}

		public Integer getAllowdminvoice() {
			return allowdminvoice;
		}

		public String getCpname() {
			return cpname;
		}

		public String getCpphone() {
			return cpphone;
		}

		public String getCpemail() {
			return cpemail;
		}

		public String getColourcode() {
			return colourcode;
		}

		public Integer getValuetag() {
			return valuetag;
		}

		public Date getLockuptodate() {
			return lockuptodate;
		}

		public Long getSchemeid() {
			return schemeid;
		}

		public Integer getCfbill() {
			return cfbill;
		}

		public Long getAddcrdays() {
			return addcrdays;
		}

		public Long getAddbilllimit() {
			return addbilllimit;
		}

		public Long getRatecategoryid() {
			return ratecategoryid;
		}

		public Integer getSmsoption() {
			return smsoption;
		}

		public Integer getEmailoption() {
			return emailoption;
		}

		public Integer getWhatsappoption() {
			return whatsappoption;
		}

		public Integer getDeliverytype() {
			return deliverytype;
		}

		public BigDecimal getSlabdisc() {
			return slabdisc;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public String getNotes() {
			return notes;
		}

		public BigDecimal getAddcrlimit() {
			return addcrlimit;
		}

		public String getLastoperation() {
			return lastoperation;
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

		public String getSessionid() {
			return sessionid;
		}

		public String getMacinfo() {
			return macinfo;
		}

		public String getDescription() {
			return description;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Integer getGsttype() {
			return gsttype;
		}

		public Integer getActive() {
			return active;
		}

		public Long getDeliverytypeid() {
			return deliverytypeid;
		}

		public String getPhoneno() {
			return phoneno;
		}

		public Integer getCrnoteadjafterdays() {
			return crnoteadjafterdays;
		}

		public Integer getNoofbills() {
			return noofbills;
		}

		public Integer getNoofbillslimit() {
			return noofbillslimit;
		}

		public Integer getCustomercategory() {
			return customercategory;
		}

		public Integer getMultipleseriesreq() {
			return multipleseriesreq;
		}

		public Date getLastbilldate() {
			return lastbilldate;
		}

		public String getEmilanuserid() {
			return emilanuserid;
		}

		public Long getGroupofaccount() {
			return groupofaccount;
		}

		public String getEdeareacode() {
			return edeareacode;
		}

		public Long getSalesmanid() {
			return salesmanid;
		}

		public String getCpname1() {
			return cpname1;
		}

		public String getCphone1() {
			return cphone1;
		}

		public String getCpemail1() {
			return cpemail1;
		}

		public Long getDocumentid() {
			return documentid;
		}

		public BigDecimal getLedgerbalance() {
			return ledgerbalance;
		}

		public Integer getLocked() {
			return locked;
		}

		public String getOrderdays() {
			return orderdays;
		}

		public String getOrderdates() {
			return orderdates;
		}

		public Integer getIsgroup() {
			return isgroup;
		}

		public Integer getTdsapplicable() {
			return tdsapplicable;
		}

		public BigDecimal getTdslessper() {
			return tdslessper;
		}

		public Integer getCalculationon() {
			return calculationon;
		}

		public String getTinno() {
			return tinno;
		}

		public BigDecimal getOpeningbalance() {
			return openingbalance;
		}

		public Integer getOpeningbalcrdr() {
			return openingbalcrdr;
		}

		public String getBankname() {
			return bankname;
		}

		public String getBankaccountcode() {
			return bankaccountcode;
		}

		public String getBankadd1() {
			return bankadd1;
		}

		public String getBankadd2() {
			return bankadd2;
		}

		public String getBankadd3() {
			return bankadd3;
		}

		public String getIfsccode() {
			return ifsccode;
		}

		public String getMicrcode() {
			return micrcode;
		}

		public String getBanknotes() {
			return banknotes;
		}

		public Long getBankcityid() {
			return bankcityid;
		}

		public String getAccounttype() {
			return accounttype;
		}

		public Integer getAccountserialno() {
			return accountserialno;
		}

		public Long getAccountreversecode() {
			return accountreversecode;
		}

		public Integer getTcsapplicable() {
			return tcsapplicable;
		}

		public BigDecimal getTcsnetamt() {
			return tcsnetamt;
		}

		public BigDecimal getTdsnetamt() {
			return tdsnetamt;
		}

		public Integer getAllowspecialqty() {
			return allowspecialqty;
		}

		public String getOrderday() {
			return orderday;
		}

		public Time getOrdertime() {
			return ordertime;
		}

		public BigDecimal getInterestrate() {
			return interestrate;
		}

		public BigDecimal getClosingbalance() {
			return closingbalance;
		}

		public Integer getIssubstockiest() {
			return issubstockiest;
		}

		public Integer getDistanceinkm() {
			return distanceinkm;
		}
	}

}