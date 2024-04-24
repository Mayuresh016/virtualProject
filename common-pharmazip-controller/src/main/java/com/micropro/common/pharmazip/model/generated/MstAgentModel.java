package com.micropro.common.pharmazip.model.generated;

import java.sql.Timestamp;
import java.math.BigDecimal;
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

public class MstAgentModel {

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a MstAgent entity")
	public static final class MstAgentBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'AGENT_BODY' (required)") AgentBody agent;

		@JsonCreator
		public MstAgentBody(
				@JsonProperty("agent") AgentBody agent) {
			this.agent = Objects.requireNonNull(agent, "`agent` is required");
		}

		public AgentBody getAgent() {
			return agent;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Agent entity")
	public static final class AgentBody {

		private final @ApiModelProperty(required = true, value = "REST representation of the 'ORGID_BODY' (required) (Size = 5)") Integer orgid;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'OPRID_BODY' (required) (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTID_BODY' (Primary Key)") Long agentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CUSTOMERCODE_BODY' (Size = 20)") String customercode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PARENTID_BODY' (Size = 20)") Long parentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CLUBID_BODY' (Size = 100)") String clubid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'FIRSTNAME_BODY' (Size = 20)") String firstname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTNAME_BODY' (Size = 20)") String lastname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTTYPE_BODY' (Size = 1)") Integer agenttype;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD1_BODY' (Size = 50)") String add1;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD2_BODY' (Size = 50)") String add2;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ADD3_BODY' (Size = 50)") String add3;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MOBILE_BODY' (Size = 50)") String mobile;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALTERNATIVENO_BODY' (Size = 50)") String alternativeno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'EMAIL_BODY' (Size = 50)") String email;
		private @ApiModelProperty(required = false, value = "REST representation of the 'IDENTITY_BODY' (Size = 1)") Integer identity;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYID_BODY' (Size = 20)") Long agencyid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYGROUPID_BODY' (Size = 20)") Long agencygroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENCYSUBGROUPID_BODY' (Size = 20)") Long agencysubgroupid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AADHARCARDNO_BODY' (Size = 16)") Long aadharcardno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PANCARDNO_BODY' (Size = 20)") String pancardno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKNAME_BODY' (Size = 50)") String bankname;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKADD_BODY' (Size = 100)") String bankadd;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKACCNO_BODY' (Size = 20)") String bankaccno;
		private @ApiModelProperty(required = false, value = "REST representation of the 'BANKIFSCCODE_BODY' (Size = 20)") String bankifsccode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COUNTRYID_BODY' (Size = 20)") Long countryid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'STATEID_BODY' (Size = 20)") Long stateid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CITYID_BODY' (Size = 20)") Long cityid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AREAID_BODY' (Size = 20)") Long areaid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PINCODE_BODY' (Size = 20)") Long pincode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACTIVE_BODY' (Size = 1)") Integer active;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COLORCODE_BODY' (Size = 50)") String colorcode;
		private @ApiModelProperty(required = false, value = "REST representation of the 'VALUETAG_BODY' (Size = 1)") Integer valuetag;
		private @ApiModelProperty(required = false, value = "REST representation of the 'USERNAME_BODY' (Size = 100)") String username;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PASSWORD_BODY' (Size = 100)") String password;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMENTS_BODY' (Size = 500)") String comments;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDON_BODY' (required)") Timestamp createdon;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'CREATEDBY_BODY' (required) (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMVALUEONE_BODY' (Size = 18,4)") BigDecimal commvalueone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMPERCENTAGEONE_BODY' (Size = 18,4)") BigDecimal commpercentageone;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMVALUETWO_BODY' (Size = 18,4)") BigDecimal commvaluetwo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'COMMPERCENTAGETWO_BODY' (Size = 18,4)") BigDecimal commpercentagetwo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CALCULATECOMMON_BODY' (Size = 1)") Integer calculatecommon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCCOMMLESSPER_BODY' (Size = 20)") Long doccommlessper;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ALLOWDELIVERY_BODY' (Size = 1)") Integer allowdelivery;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'LASTOPERATION_BODY' (required) (Size = 20)") String lastoperation;
		private @ApiModelProperty(required = false, value = "REST representation of the 'NAME_BODY' (Size = 50)") String name;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'DOCTORPRODUCTLINK_BODY' (required)") List<DoctorproductlinkBody> doctorproductlink;
		private final @ApiModelProperty(required = true, value = "REST representation of the 'DOCTORCUSTOMERLINK_BODY' (required)") List<DoctorcustomerlinkBody> doctorcustomerlink;

		@JsonCreator
		public AgentBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("agentid") Long agentid,
				@JsonProperty("customercode") String customercode,
				@JsonProperty("parentid") Long parentid,
				@JsonProperty("clubid") String clubid,
				@JsonProperty("firstname") String firstname,
				@JsonProperty("lastname") String lastname,
				@JsonProperty("agenttype") Integer agenttype,
				@JsonProperty("add1") String add1,
				@JsonProperty("add2") String add2,
				@JsonProperty("add3") String add3,
				@JsonProperty("mobile") String mobile,
				@JsonProperty("alternativeno") String alternativeno,
				@JsonProperty("email") String email,
				@JsonProperty("identity") Integer identity,
				@JsonProperty("agencyid") Long agencyid,
				@JsonProperty("agencygroupid") Long agencygroupid,
				@JsonProperty("agencysubgroupid") Long agencysubgroupid,
				@JsonProperty("aadharcardno") Long aadharcardno,
				@JsonProperty("pancardno") String pancardno,
				@JsonProperty("bankname") String bankname,
				@JsonProperty("bankadd") String bankadd,
				@JsonProperty("bankaccno") String bankaccno,
				@JsonProperty("bankifsccode") String bankifsccode,
				@JsonProperty("countryid") Long countryid,
				@JsonProperty("stateid") Long stateid,
				@JsonProperty("cityid") Long cityid,
				@JsonProperty("areaid") Long areaid,
				@JsonProperty("pincode") Long pincode,
				@JsonProperty("active") Integer active,
				@JsonProperty("colorcode") String colorcode,
				@JsonProperty("valuetag") Integer valuetag,
				@JsonProperty("username") String username,
				@JsonProperty("password") String password,
				@JsonProperty("comments") String comments,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("commvalueone") BigDecimal commvalueone,
				@JsonProperty("commpercentageone") BigDecimal commpercentageone,
				@JsonProperty("commvaluetwo") BigDecimal commvaluetwo,
				@JsonProperty("commpercentagetwo") BigDecimal commpercentagetwo,
				@JsonProperty("calculatecommon") Integer calculatecommon,
				@JsonProperty("doccommlessper") Long doccommlessper,
				@JsonProperty("allowdelivery") Integer allowdelivery,
				@JsonProperty("lastoperation") String lastoperation,
				@JsonProperty("name") String name,
				@JsonProperty("doctorproductlink") List<DoctorproductlinkBody> doctorproductlink,
				@JsonProperty("doctorcustomerlink") List<DoctorcustomerlinkBody> doctorcustomerlink) {
			this.orgid = Objects.requireNonNull(orgid, "`orgid` is required");
			this.oprid = Objects.requireNonNull(oprid, "`oprid` is required");
			this.agentid = Objects.requireNonNull(agentid, "`agentid` is required");
			this.customercode = customercode;
			this.parentid = parentid;
			this.clubid = clubid;
			this.firstname = firstname;
			this.lastname = lastname;
			this.agenttype = agenttype;
			this.add1 = add1;
			this.add2 = add2;
			this.add3 = add3;
			this.mobile = mobile;
			this.alternativeno = alternativeno;
			this.email = email;
			this.identity = identity;
			this.agencyid = agencyid;
			this.agencygroupid = agencygroupid;
			this.agencysubgroupid = agencysubgroupid;
			this.aadharcardno = aadharcardno;
			this.pancardno = pancardno;
			this.bankname = bankname;
			this.bankadd = bankadd;
			this.bankaccno = bankaccno;
			this.bankifsccode = bankifsccode;
			this.countryid = countryid;
			this.stateid = stateid;
			this.cityid = cityid;
			this.areaid = areaid;
			this.pincode = pincode;
			this.active = active;
			this.colorcode = colorcode;
			this.valuetag = valuetag;
			this.username = username;
			this.password = password;
			this.comments = comments;
			this.createdon = Objects.requireNonNull(createdon, "`createdon` is required");
			this.createdby = Objects.requireNonNull(createdby, "`createdby` is required");
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.commvalueone = commvalueone;
			this.commpercentageone = commpercentageone;
			this.commvaluetwo = commvaluetwo;
			this.commpercentagetwo = commpercentagetwo;
			this.calculatecommon = calculatecommon;
			this.doccommlessper = doccommlessper;
			this.allowdelivery = allowdelivery;
			this.lastoperation = Objects.requireNonNull(lastoperation, "`lastoperation` is required");
			this.name = name;
			this.doctorproductlink = doctorproductlink;
			this.doctorcustomerlink = doctorcustomerlink;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getAgentid() {
			return agentid;
		}

		public String getCustomercode() {
			return customercode;
		}

		public Long getParentid() {
			return parentid;
		}

		public String getClubid() {
			return clubid;
		}

		public String getFirstname() {
			return firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public Integer getAgenttype() {
			return agenttype;
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

		public String getMobile() {
			return mobile;
		}

		public String getAlternativeno() {
			return alternativeno;
		}

		public String getEmail() {
			return email;
		}

		public Integer getIdentity() {
			return identity;
		}

		public Long getAgencyid() {
			return agencyid;
		}

		public Long getAgencygroupid() {
			return agencygroupid;
		}

		public Long getAgencysubgroupid() {
			return agencysubgroupid;
		}

		public Long getAadharcardno() {
			return aadharcardno;
		}

		public String getPancardno() {
			return pancardno;
		}

		public String getBankname() {
			return bankname;
		}

		public String getBankadd() {
			return bankadd;
		}

		public String getBankaccno() {
			return bankaccno;
		}

		public String getBankifsccode() {
			return bankifsccode;
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

		public Long getPincode() {
			return pincode;
		}

		public Integer getActive() {
			return active;
		}

		public String getColorcode() {
			return colorcode;
		}

		public Integer getValuetag() {
			return valuetag;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public String getComments() {
			return comments;
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

		public BigDecimal getCommvalueone() {
			return commvalueone;
		}

		public BigDecimal getCommpercentageone() {
			return commpercentageone;
		}

		public BigDecimal getCommvaluetwo() {
			return commvaluetwo;
		}

		public BigDecimal getCommpercentagetwo() {
			return commpercentagetwo;
		}

		public Integer getCalculatecommon() {
			return calculatecommon;
		}

		public Long getDoccommlessper() {
			return doccommlessper;
		}

		public Integer getAllowdelivery() {
			return allowdelivery;
		}

		public String getLastoperation() {
			return lastoperation;
		}

		public String getName() {
			return name;
		}

		public List<DoctorproductlinkBody> getDoctorproductlink() {
			return doctorproductlink;
		}

		public List<DoctorcustomerlinkBody> getDoctorcustomerlink() {
			return doctorcustomerlink;
		}
	}

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Doctorproductlink entity")
	public static final class DoctorproductlinkBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTORPRODUCTLINKID_BODY' (Primary Key)") Long doctorproductlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTID_BODY' (Size = 20)") Long agentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'PRODUCTID_BODY' (Size = 20)") Long productid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") String modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public DoctorproductlinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("doctorproductlinkid") Long doctorproductlinkid,
				@JsonProperty("agentid") Long agentid,
				@JsonProperty("productid") Long productid,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyby") String modifyby,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.doctorproductlinkid = Objects.requireNonNull(doctorproductlinkid, "`doctorproductlinkid` is required");
			this.agentid = agentid;
			this.productid = productid;
			this.createdby = createdby;
			this.createdon = createdon;
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

		public Long getDoctorproductlinkid() {
			return doctorproductlinkid;
		}

		public Long getAgentid() {
			return agentid;
		}

		public Long getProductid() {
			return productid;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
		}

		public String getModifyby() {
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

	@JsonInclude(Include.ALWAYS)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ApiModel(description = "A request body model used in POST requests to create a Doctorcustomerlink entity")
	public static final class DoctorcustomerlinkBody {

		private @ApiModelProperty(required = false, value = "REST representation of the 'ORGID_BODY' (Size = 5)") Integer orgid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'OPRID_BODY' (Size = 5)") Integer oprid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DOCTORCUSTOMERLINKID_BODY' (Primary Key)") Long doctorcustomerlinkid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'AGENTID_BODY' (Size = 20)") Long agentid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'ACCOUNTID_BODY' (Size = 20)") Long accountid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDBY_BODY' (Size = 20)") Long createdby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'CREATEDON_BODY'") Timestamp createdon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYON_BODY'") Timestamp modifyon;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MODIFYBY_BODY' (Size = 20)") Long modifyby;
		private @ApiModelProperty(required = false, value = "REST representation of the 'MACINFO_BODY' (Size = 50)") String macinfo;
		private @ApiModelProperty(required = false, value = "REST representation of the 'SESSIONID_BODY' (Size = 20)") String sessionid;
		private @ApiModelProperty(required = false, value = "REST representation of the 'DESCRIPTION_BODY' (Size = 500)") String description;
		private @ApiModelProperty(required = false, value = "REST representation of the 'LASTOPERATION_BODY' (Size = 20)") String lastoperation;

		@JsonCreator
		public DoctorcustomerlinkBody(
				@JsonProperty("orgid") Integer orgid,
				@JsonProperty("oprid") Integer oprid,
				@JsonProperty("doctorcustomerlinkid") Long doctorcustomerlinkid,
				@JsonProperty("agentid") Long agentid,
				@JsonProperty("accountid") Long accountid,
				@JsonProperty("createdby") Long createdby,
				@JsonProperty("createdon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp createdon,
				@JsonProperty("modifyon") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp modifyon,
				@JsonProperty("modifyby") Long modifyby,
				@JsonProperty("macinfo") String macinfo,
				@JsonProperty("sessionid") String sessionid,
				@JsonProperty("description") String description,
				@JsonProperty("lastoperation") String lastoperation) {
			this.orgid = orgid;
			this.oprid = oprid;
			this.doctorcustomerlinkid = Objects.requireNonNull(doctorcustomerlinkid, "`doctorcustomerlinkid` is required");
			this.agentid = agentid;
			this.accountid = accountid;
			this.createdby = createdby;
			this.createdon = createdon;
			this.modifyon = modifyon;
			this.modifyby = modifyby;
			this.macinfo = macinfo;
			this.sessionid = sessionid;
			this.description = description;
			this.lastoperation = lastoperation;
		}

		public Integer getOrgid() {
			return orgid;
		}

		public Integer getOprid() {
			return oprid;
		}

		public Long getDoctorcustomerlinkid() {
			return doctorcustomerlinkid;
		}

		public Long getAgentid() {
			return agentid;
		}

		public Long getAccountid() {
			return accountid;
		}

		public Long getCreatedby() {
			return createdby;
		}

		public Timestamp getCreatedon() {
			return createdon;
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
	}

}