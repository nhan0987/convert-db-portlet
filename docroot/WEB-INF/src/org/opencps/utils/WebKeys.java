
package org.opencps.utils;

import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.model.Citizen;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierFileLog;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.expando.model.DomainConfigExt;
import org.opencps.expando.model.FileEntryDossierFile;
import org.opencps.modifier.model.EmployeeJob;
import org.opencps.modifier.model.UsersOrgs;
import org.opencps.modifier.model.UsersRoles;
import org.opencps.modifier.model.WorkJob;
import org.opencps.paymentmgt.model.PaymentConfig;
import org.opencps.paymentmgt.model.PaymentFile;
import org.opencps.processmgt.model.ActionHistory;
import org.opencps.processmgt.model.ProcessOrder;
import org.opencps.processmgt.model.ProcessStep;
import org.opencps.processmgt.model.ProcessStepDossierPart;
import org.opencps.processmgt.model.ProcessWorkflow;
import org.opencps.processmgt.model.ServiceInfoProcess;
import org.opencps.processmgt.model.ServiceProcess;
import org.opencps.processmgt.model.StepAllowance;
import org.opencps.processmgt.model.WorkflowOutput;
import org.opencps.servicemgt.model.ServiceFileTemplate;
import org.opencps.servicemgt.model.ServiceInfo;
import org.opencps.servicemgt.model.TemplateFile;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;

public class WebKeys implements com.liferay.portal.kernel.util.WebKeys {
	
	public static final String SERVICE_DOMAIN = "SERVICE_DOMAIN";
	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	
	public static final String TABLE_NONE_EXISTED = "Table  not existed to show the data." +
		" please add data first and comeback to to see the data";

	public static final String DICTCOLLECION = DictCollection.class.getName();
	public static final String EXTableName_DICTCOLLECION = "opencps_dictcollection";
	public static final String[] DictCollectionColumnNames = {
		"collectionCode", "collectionName", "description", "dictCollectionIdNew"
	};

	public static JSONObject getDictCollectionColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("collectionCode", "collectionCode");
		jsonObject.put("collectionName", "collectionName");
		jsonObject.put("description", "description");
		jsonObject.put("dictCollectionIdNew", "dictCollectionIdNew");

		return jsonObject;

	}

	public static final String DICTITEM = DictItem.class.getName();
	public static final String EXTableName_DICTITEM = "opencps_dictitem";
	public static final String[] DictItemColumnNames = {
		"dictCollectionId",
		"itemCode",
		"itemName",
		"itemDescription",
		"parentItemId",
		"treeIndex",
		"issueStatus",
		"dictItemNew"
	};

	public static JSONObject getDictItemColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("dictCollectionId", "dictCollectionId");
		jsonObject.put("itemCode", "itemCode");
		jsonObject.put("itemName", "itemName");
		jsonObject.put("itemDescription", "itemDescription");
		jsonObject.put("parentItemId", "parentItemId");
		jsonObject.put("treeIndex", "treeIndex");
		jsonObject.put("issueStatus", "issueStatus");
		jsonObject.put("dictItemNew", "dictItemNew");

		return jsonObject;

	}

	public static final String SERVICE_INFO = ServiceInfo.class.getName();
	public static final String EXTableName_SERVICE_INFO = "opencps_serviceinfo";
	public static final String[] ServiceInfoColumnNames = {
		"serviceNo",
		"serviceName",
		"serviceProcess",
		"serviceMethod",
		"serviceDossier",
		"serviceCondition",
		"serviceDuration",
		"serviceActors",
		"serviceResults",
		"serviceRecords",
		"serviceIntructions",
		"serviceFee",
		"administrationCode",
		"administrationIndex",
		"domainCode", 
		"domainIndex",
		"activeSatus",
		"hasTemplateFiles",
		"fullName",
		"serviceInfoIdNew"
		
	};

	public static JSONObject getServiceInfoColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceNo", "serviceNo");
		jsonObject.put("serviceName", "serviceName");
		// jsonObject.put("shortName", "shortName");
		jsonObject.put("serviceMethod", "serviceMethod");
		jsonObject.put("serviceDossier", "serviceDossier");
		jsonObject.put("serviceCondition", "serviceCondition");
		jsonObject.put("serviceDuration", "serviceDuration");
		jsonObject.put("serviceActors", "serviceActors");
		jsonObject.put("serviceResults", "serviceResults");
		jsonObject.put("serviceRecords", "serviceRecords");
		jsonObject.put("serviceIntructions", "serviceIntructions");
		jsonObject.put("serviceProcess", "serviceProcess");
		jsonObject.put("administrationCode", "administrationCode");
		jsonObject.put("administrationIndex", "administrationIndex");
		jsonObject.put("domainCode", "domainCode");
		jsonObject.put("domainIndex", "domainIndex");
		jsonObject.put("activeSatus", "activeSatus");
		jsonObject.put("hasTemplateFiles", "hasTemplateFiles");
		jsonObject.put("fullName", "fullName");
		jsonObject.put("serviceFee", "serviceFee");
		jsonObject.put("serviceInfoIdNew", "serviceInfoIdNew");

		return jsonObject;

	}

	public static final String TEMPLATE_FILE = TemplateFile.class.getName();
	public static final String EXTableName_TEMPLATE_FILE = "opencps_templatefile";
	public static final String[] TemplateFile_ColumnNames = {
		"fileName", "fileNo", "fileEntryId", "templateFileIdNew"
	};

	public static JSONObject getTemplateFileColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("fileName", "fileName");
		jsonObject.put("fileNo", "fileNo");
		jsonObject.put("fileEntryId", "fileEntryId");
		jsonObject.put("templateFileIdNew", "templateFileIdNew");

		return jsonObject;

	}

	public static final String SERVICE_INFO_TEMPLATE_FILE = ServiceFileTemplate.class.getName();
	public static final String expandoTableName_ServiceInfo_TemplateFile =
		"opencps_service_templatefile";
	public static final String[] ServiceInfo_TemplateFile_ColumnNames = {
		"serviceInfoId", "templateFileId"
	};

	public static JSONObject getServiceInfoTemplateFileColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceInfoId", "serviceInfoId");
		jsonObject.put("templateFileId", "templateFileId");

		return jsonObject;

	}

	public static final String DOSSIER_TEMPLATE = DossierTemplate.class.getName();
	public static final String EXTableName_DossierTemplate = "opencps_dossiertemplate";
	public static final String[] DossierTemplateColumns = {
		"templateName", "description", "templateNo", "dossierTemplateIdNew"
	};

	public static JSONObject getDossierTemplateColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("templateName", "templateName");
		jsonObject.put("description", "description");
		jsonObject.put("templateNo", "templateNo");
		jsonObject.put("dossierTemplateIdNew", "dossierTemplateIdNew");

		return jsonObject;

	}

	public static final String DOSSIER_PART = DossierPart.class.getName();
	public static final String EXTableName_DossierPart = "opencps_dossierpart";
	public static final String[] DossierPartColumns = {
		"dossierTemplateId", "partNo", "partName", "partTip", "partType", "parentId", "sibling",
		"treeIndex", "formScript", "formReport", "spamleData", "required", "templateFileNo",
		"dossierPartIdNew"
	};

	public static JSONObject getDossierPartColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("dossierTemplateId", "dossierTemplateId");
		jsonObject.put("partNo", "partNo");
		jsonObject.put("partName", "partName");
		jsonObject.put("partTip", "partTip");
		jsonObject.put("partType", "partType");
		jsonObject.put("parentId", "parentId");
		jsonObject.put("sibling", "sibling");
		jsonObject.put("treeIndex", "treeIndex");
		jsonObject.put("formScript", "formScript");
		jsonObject.put("formReport", "formReport");
		jsonObject.put("spamleData", "spamleData");
		jsonObject.put("required", "required");
		jsonObject.put("templateFileNo", "templateFileNo");
		jsonObject.put("dossierPartIdNew", "dossierPartIdNew");

		return jsonObject;

	}
	public static final String SERVICE_PROCESS = ServiceProcess.class.getName();
	public static final String EXTableName_ServiceProcess = "opencps_serviceprocess";
	public static final String[] ServiceProcessColumns = {
		"processNo", "processName", "description", "dossierTemplateId", "serviceProcessIdNew"
	};

	public static JSONObject getServiceProcessColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("processNo", "processNo");
		jsonObject.put("processName", "processName");
		jsonObject.put("description", "description");
		jsonObject.put("dossierTemplateId", "dossierTemplateId");
		jsonObject.put("serviceProcessIdNew", "serviceProcessIdNew");

		return jsonObject;

	}

	public static final String SERVICE_INFO_PROCESS = ServiceInfoProcess.class.getName();
	public static final String EXTableName_ServiceInfoProcess = "opencps_serviceinfoprocess";
	public static final String[] ServiceInfoProcessColumns = {
		"serviceProcessId", "serviceInfoId"
	};

	public static JSONObject getServiceInfoProcessColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceProcessId", "serviceProcessId");
		jsonObject.put("serviceInfoId", "serviceInfoId");

		return jsonObject;

	}

	public static final String USERS = User.class.getName();
	public static final String EXTableName_User = "user_";
	public static final String[] UserColumns = {
		"contactId", "password_", "passwordEncrypted", "reminderQueryQuestion",
		"reminderQueryAnswer", "screenName", "emailAddress", "greeting", "firstName", "middleName",
		"lastName", "jobTitle", "agreedToTermsOfUse", "emailAddressVerified", "status", "userIdNew"
	};

	public static JSONObject getUserColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("contactId", "contactId");
		jsonObject.put("password_", "password_");
		jsonObject.put("passwordEncrypted", "passwordEncrypted");
		jsonObject.put("reminderQueryQuestion", "reminderQueryQuestion");
		jsonObject.put("reminderQueryAnswer", "reminderQueryAnswer");
		jsonObject.put("screenName", "screenName");
		jsonObject.put("emailAddress", "emailAddress");
		jsonObject.put("greeting", "greeting");
		jsonObject.put("firstName", "firstName");
		jsonObject.put("middleName", "middleName");
		jsonObject.put("lastName", "lastName");
		jsonObject.put("jobTitle", "jobTitle");
		jsonObject.put("agreedToTermsOfUse", "agreedToTermsOfUse");
		jsonObject.put("emailAddressVerified", "emailAddressVerified");
		jsonObject.put("status", "status");
		jsonObject.put("userIdNew", "userIdNew");

		return jsonObject;

	}

	public static final String CONTACTS = Contact.class.getName();
	public static final String EXTableName_Contact = "contact_";
	public static final String[] ContactColumns = {
		"emailAddress",
		"firstName",
		"middleName",
		"lastName",
		"male",
		"birthday",
		"jobTitle",
		"contactIdNew"
	};

	public static JSONObject getContactColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("emailAddress", "emailAddress");
		jsonObject.put("firstName", "firstName");
		jsonObject.put("middleName", "middleName");
		jsonObject.put("lastName", "lastName");
		jsonObject.put("male", "male");
		jsonObject.put("birthday", "birthday");
		jsonObject.put("jobTitle", "jobTitle");
		jsonObject.put("contactIdNew", "contactIdNew");

		return jsonObject;

	}

	public static final String ORGANIZATION = Organization.class.getName();
	public static final String EXTableName_Organization = "organization_";
	public static final String[] OrganizationColumns = {
		"userName", "parentOrganizationId", "treePath", "type_", "name", "statusId",
		"organizationIdNew"
	};

	public static JSONObject getOrganizationColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("userName", "userName");
		jsonObject.put("parentOrganizationId", "parentOrganizationId");
		jsonObject.put("treePath", "treePath");
		jsonObject.put("type_", "type_");
		jsonObject.put("name", "name");
		jsonObject.put("statusId", "statusId");
		jsonObject.put("organizationIdNew", "organizationIdNew");

		return jsonObject;

	}

	// public static final String USERS_ORGS = user.class.getName();
	// public static final String EXTableName_Organization = "contact_";
	// public static final String[] OrganizationColumns = {
	// "userName",
	// "parentOrganizationId",
	// "treePath",
	// "type_",
	// "name",
	// "statusId",
	// "organizationIdNew"
	// };
	//
	// public static JSONObject getOrganizationColumnNames() {
	//
	// JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	// jsonObject.put("userName", "userName");
	// jsonObject.put("parentOrganizationId", "parentOrganizationId");
	// jsonObject.put("treePath", "treePath");
	// jsonObject.put("type_", "type_");
	// jsonObject.put("name", "name");
	// jsonObject.put("statusId", "statusId");
	// jsonObject.put("organizationIdNew", "organizationIdNew");
	//
	// return jsonObject;
	//
	// }

	public static final String SERVICE_CONFIG = ServiceConfig.class.getName();
	public static final String EXTableName_ServiceConfig = "opencps_serviceconfig";
	public static final String[] ServiceConfigColumns = {
		"serviceInfoId", "serviceDomainIndex", "serviceAdministrationIndex", "dossierTemplateId",
		"govAgencyCode", "govAgencyName", "govAgencyOrganizationId", "sericeMode",
		"serviceProcessId", "domainCode", "serviceInstruction", "serviceLevel", "servicePortal",
		"serviceOnegate", "serviceBackOffice", "serviceCitizen", "serviceBusiness",
		"govAgencyIndex", "serviceConfigNew"
	};

	public static JSONObject getServiceConfigColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceInfoId", "serviceInfoId");
		jsonObject.put("serviceDomainIndex", "serviceDomainIndex");
		jsonObject.put("serviceAdministrationIndex", "serviceAdministrationIndex");
		jsonObject.put("dossierTemplateId", "dossierTemplateId");
		jsonObject.put("govAgencyCode", "govAgencyCode");
		jsonObject.put("govAgencyName", "govAgencyName");
		jsonObject.put("govAgencyOrganizationId", "govAgencyOrganizationId");

		jsonObject.put("sericeMode", "sericeMode");
		jsonObject.put("serviceProcessId", "serviceProcessId");
		jsonObject.put("domainCode", "domainCode");
		jsonObject.put("serviceInstruction", "serviceInstruction");
		jsonObject.put("serviceLevel", "serviceLevel");
		jsonObject.put("servicePortal", "servicePortal");
		jsonObject.put("serviceOnegate", "serviceOnegate");

		jsonObject.put("serviceBackOffice", "serviceBackOffice");
		jsonObject.put("serviceCitizen", "serviceCitizen");
		jsonObject.put("serviceBusiness", "serviceBusiness");
		jsonObject.put("govAgencyIndex", "govAgencyIndex");
		jsonObject.put("serviceConfigNew", "serviceConfigNew");
		return jsonObject;

	}

	public static final String PROCESS_STEP = ProcessStep.class.getName();
	public static final String EXTableName_ProcessStep = "opencps_processstep";
	public static final String[] ProcessStepColumns = {
		"serviceProcessId", "stepName", "sequenceNo", "dossierStatus", "dayDuration","dossierSubStatus",
		"processStepIdNew"
	};

	public static JSONObject getProcessStepColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceProcessId", "serviceProcessId");
		jsonObject.put("stepName", "stepName");
		jsonObject.put("sequenceNo", "sequenceNo");
		jsonObject.put("dossierStatus", "dossierStatus");
		jsonObject.put("dayDuration", "dayDuration");
		jsonObject.put("dossierSubStatus", "dossierSubStatus");
		jsonObject.put("processStepIdNew", "processStepIdNew");

		return jsonObject;

	}

	public static final String PROCESSSTEP_DOSSIERPART = ProcessStepDossierPart.class.getName();
	public static final String EXTableName_ProcessStepDossierPart =
		"opencps_processstep_dossierpart";
	public static final String[] ProcessStepDossierPartColumns = {
		"processStepId", "dossierPartId", "readOnly"
	};

	public static JSONObject getProcessStepDossierPartColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("processStepId", "processStepId");
		jsonObject.put("dossierPartId", "dossierPartId");
		jsonObject.put("readOnly", "readOnly");

		return jsonObject;

	}

	public static final String PROCESS_WORKFLOW = ProcessWorkflow.class.getName();
	public static final String EXTableName_ProcessWorkflow = "opencps_processworkflow";
	public static final String[] ProcessWorkflowColumns = {
		"serviceProcessId",
		"preProcessStepId",
		"postProcessStepId",
		"autoEvent",
		"actionName",
		"assignUser",
		"actionUserId",
		"requestPayment",
		"paymentFee",
		"generateReceptionNo",
		"receptionNoPattern",
		"generateDealine",
		"dealinePattern",
		"isFinishStep",
		"preCondition",
		"isMutipled",
		"actionCode",
		"processWorkflowIdNew"
	};

	public static JSONObject getProcessWorkflowColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("serviceProcessId", "serviceProcessId");
		jsonObject.put("preProcessStepId", "preProcessStepId");
		jsonObject.put("postProcessStepId", "postProcessStepId");

		jsonObject.put("autoEvent", "autoEvent");
		jsonObject.put("actionName", "actionName");
		jsonObject.put("assignUser", "assignUser");

		jsonObject.put("actionUserId", "actionUserId");
		jsonObject.put("requestPayment", "requestPayment");
		jsonObject.put("paymentFee", "paymentFee");

		jsonObject.put("generateReceptionNo", "generateReceptionNo");
		jsonObject.put("receptionNoPattern", "receptionNoPattern");
		jsonObject.put("generateDealine", "generateDealine");

		jsonObject.put("dealinePattern", "dealinePattern");
		jsonObject.put("isFinishStep", "isFinishStep");
		jsonObject.put("preCondition", "preCondition");

		jsonObject.put("isMutipled", "isMutipled");
		jsonObject.put("actionCode", "actionCode");
		
		jsonObject.put("processWorkflowIdNew", "processWorkflowIdNew");

		return jsonObject;

	}

	public static final String WORKFLOW_OUTPUT = WorkflowOutput.class.getName();
	public static final String EXTableName_WorkflowOutput = "opencps_workflowoutput";
	public static final String[] WorkflowOutputColumns = {
		"processWorkflowId",
		"dossierPartId",
		"required",
		"esign",
		"postback",
		"pattern",
		"workflowOutputIdNew"
	};

	public static JSONObject getWorkflowOutputColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("processWorkflowId", "processWorkflowId");
		jsonObject.put("dossierPartId", "dossierPartId");
		jsonObject.put("required", "required");

		jsonObject.put("esign", "esign");
		jsonObject.put("postback", "postback");
		jsonObject.put("pattern", "pattern");
		jsonObject.put("workflowOutputIdNew", "workflowOutputIdNew");

		return jsonObject;

	}
	
	public static final String ROLE = Role.class.getName();
	public static final String EXTableName_Role = "role_";
	public static final String[] RoleColumns = {
		"name",
		"title",
		"description",
		"type_",
		"subtype",
		"roleIdNew"
	};

	public static JSONObject getRoleColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("name", "name");
		jsonObject.put("title", "title");

		jsonObject.put("description", "description");
		jsonObject.put("type_", "type_");
		jsonObject.put("subtype", "subtype");
		jsonObject.put("roleIdNew", "roleIdNew");

		return jsonObject;

	}
	public static final String USERS_ORGS = UsersOrgs.class.getName();
	public static final String EXTableName_UsersOrgs = "users_orgs";
	public static final String[] UsersOrgsColumns = {
		"organizationId",
		"userId"
	};

	public static JSONObject getUsersOrgsColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("organizationId", "organizationId");
		jsonObject.put("userId", "userId");

		return jsonObject;

	}
	public static final String USERS_ROLES = UsersRoles.class.getName();
	public static final String EXTableName_UsersRoles = "users_roles";
	public static final String[] UsersRolesColumns = {
		"roleId",
		"userId"
	};

	public static JSONObject getUsersRolesColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("roleId", "roleId");
		jsonObject.put("userId", "userId");

		return jsonObject;

	}
	public static final String STEPALOWANCE = StepAllowance.class.getName();
	public static final String EXTableName_StepAllowance = "opencps_stepallowance";
	public static final String[] StepAllowanceColumns = {
		"processStepId",
		"roleId",
		"readOnly",
		"stepAllowanceIdNew"
	};

	public static JSONObject getStepAllowanceColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("processStepId", "processStepId");
		jsonObject.put("roleId", "roleId");
		jsonObject.put("readOnly", "readOnly");
		jsonObject.put("stepAllowanceIdNew", "stepAllowanceIdNew");

		return jsonObject;

	}
	public static final String CITIZEN = Citizen.class.getName();
	public static final String EXTableName_Citizen = "opencps_acc_citizen";
	public static final String[] CitizenColumns = {
		"fullName",
		"personalId",
		"gender",
		"birthDate",
		"address",
		"cityCode",
		"districtCode",
		"wardCode",
		"telNo",
		"email",
		"mappingUserId",
		"accountStatus",
		"attachFile",
		"citizenIdNew"
	};

	public static JSONObject getCitizenColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("fullName", "fullName");
		jsonObject.put("personalId", "personalId");
		jsonObject.put("gender", "gender");
		jsonObject.put("birthDate", "birthDate");
		jsonObject.put("address", "address");
		jsonObject.put("cityCode", "cityCode");
		jsonObject.put("districtCode", "districtCode");
		jsonObject.put("wardCode", "wardCode");
		jsonObject.put("telNo", "telNo");
		jsonObject.put("email", "email");
		jsonObject.put("mappingUserId", "mappingUserId");
		jsonObject.put("accountStatus", "accountStatus");
		jsonObject.put("attachFile", "attachFile");
		jsonObject.put("citizenIdNew", "citizenIdNew");
		

		return jsonObject;

	}
	
	
	
	public static final String BUSINESS = Business.class.getName();
	public static final String EXTableName_Business= "opencps_acc_business";
	public static final String[] BusinessColumns = {
		"name",
		"enName",
		"shortName",
		"businessType",
		"idNumber",
		"address",
		"cityCode",
		"districtCode",
		"wardCode",
		"telNo",
		"email",
		"representativeName",
		"representativeRole",
		"mappingOrganizationId",
		"mappingUserId",
		"accountStatus",
		"attachFile",
		"businessIdNew"
	};

	public static JSONObject getBusinessColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("name", "name");
		jsonObject.put("enName", "enName");
		jsonObject.put("shortName", "shortName");
		jsonObject.put("businessType", "businessType");
		jsonObject.put("idNumber", "idNumber");
		jsonObject.put("address", "address");
		jsonObject.put("cityCode", "cityCode");
		jsonObject.put("districtCode", "districtCode");
		jsonObject.put("wardCode", "wardCode");
		jsonObject.put("telNo", "telNo");
		jsonObject.put("email", "email");
		jsonObject.put("representativeName", "representativeName");
		jsonObject.put("representativeRole", "representativeRole");
		jsonObject.put("mappingOrganizationId", "mappingOrganizationId");
		jsonObject.put("mappingUserId", "mappingUserId");
		jsonObject.put("accountStatus", "accountStatus");
		jsonObject.put("attachFile", "attachFile");
		jsonObject.put("businessIdNew", "businessIdNew");
		

		return jsonObject;

	}
	
	public static final String WORKING_UNIT = WorkingUnit.class.getName();
	public static final String EXTableName_WorkingUnit= "opencps_workingunit";
	public static final String[] WorkingUnitColumns = {
		"name",
		"enName",
		"govAgencyCode",
		"parentWorkingUnitId",
		"sibling",
		"treeIndex",
		"address",
		"cityCode",
		"districtCode",
		"wardCode",
		"telNo",
		"faxNo",
		"email",
		"website",
		"isEmployer",
		"mappingOrganizationId",
		"workingUnitIdNew"
	};

	public static JSONObject getWorkingUnitColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("name", "name");
		jsonObject.put("enName", "enName");
		jsonObject.put("govAgencyCode", "govAgencyCode");
		jsonObject.put("parentWorkingUnitId", "parentWorkingUnitId");
		jsonObject.put("treeIndex", "treeIndex");
		jsonObject.put("sibling", "sibling");
		jsonObject.put("address", "address");
		jsonObject.put("cityCode", "cityCode");
		jsonObject.put("districtCode", "districtCode");
		jsonObject.put("wardCode", "wardCode");
		jsonObject.put("telNo", "telNo");
		jsonObject.put("faxNo", "faxNo");
		jsonObject.put("email", "email");
		jsonObject.put("website", "website");
		jsonObject.put("isEmployer", "isEmployer");
		jsonObject.put("mappingOrganizationId", "mappingOrganizationId");
		jsonObject.put("workingUnitIdNew", "workingUnitIdNew");
		

		return jsonObject;

	}
	public static final String JOB_POS = JobPos.class.getName();
	public static final String EXTableName_JobPos= "opencps_jobpos";
	public static final String[] JobPosColumns = {
		"title",
		"workingUnitId",
		"directWorkingUnitId",
		"leader",
		"mappingRoleId",
		"jobPosIdNew"
	};

	public static JSONObject getJobPosColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("title", "title");
		jsonObject.put("workingUnitId", "workingUnitId");
		jsonObject.put("directWorkingUnitId", "directWorkingUnitId");
		jsonObject.put("leader", "leader");
		jsonObject.put("mappingRoleId", "mappingRoleId");
		jsonObject.put("jobPosIdNew", "jobPosIdNew");
		

		return jsonObject;

	}
	
	public static final String EMPLOYEES = Employee.class.getName();
	public static final String EXTableName_Employee = "opencps_employee";
	public static final String[] EmployeeColumns = {
		"workingUnitId",
		"employeeNo",
		"fullName",
		"gender",
		"birthDate",
		"telNo",
		"email",
		"workingStatus",
		"mainJobPosId",
		"mappingUserId",
		"employeeIdNew"
	};

	public static JSONObject getEmployeeColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("workingUnitId", "workingUnitId");
		jsonObject.put("employeeNo", "employeeNo");
		jsonObject.put("fullName", "fullName");
		jsonObject.put("gender", "gender");
		jsonObject.put("birthDate", "birthDate");
		jsonObject.put("telNo", "telNo");
		jsonObject.put("email", "email");
		jsonObject.put("telNo", "telNo");
		jsonObject.put("workingStatus", "workingStatus");
		jsonObject.put("mainJobPosId", "mainJobPosId");
		jsonObject.put("mappingUserId", "mappingUserId");
		jsonObject.put("employeeIdNew", "employeeIdNew");
		

		return jsonObject;

	}
	
	public static final String WORKING_JOB = WorkJob.class.getName();
	public static final String EXTableName_WorkJob = "opencps_workingunit_jobpos";
	public static final String[] WorkJobColumns = {
		"jobPosId",
		"workingUnitId"
	};

	public static JSONObject getWorkJobColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("jobPosId", "jobPosId");
		jsonObject.put("workingUnitId", "workingUnitId");
		

		return jsonObject;

	}
	public static final String EMPLOYEE_JOB = EmployeeJob.class.getName();
	public static final String EXTableName_EmployeeJob = "opencps_employee_jobpos";
	public static final String[] EmployeeJobColumns = {
		"employeeId",
		"jobPosId"
	};

	public static JSONObject getEmployeeJobColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("employeeId", "employeeId");
		jsonObject.put("jobPosId", "jobPosId");

		return jsonObject;

	}
	
	public static final String DOMAIN_CONFIG = DomainConfigExt.class.getName();
	public static final String EXTableName_DOMAIN_CONFIG = "opencps_domain_config_ext";
	public static final String[] DomainConfigColumns = {
		"domainConfigId",
		"targetDomain",
		"userName",
		"password"
	};

	public static JSONObject getDomainConfigColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("domainConfigId", "domainConfigId");
		jsonObject.put("targetDomain", "targetDomain");
		jsonObject.put("userName", "userName");
		jsonObject.put("password", "password");

		return jsonObject;

	}
	
	public static final String PAYMENTCONFIG = PaymentConfig.class.getName();
	public static final String EXTableName_PaymentConfig = "opencps_payment_config";
	public static final String[] PaymentConfigColumns = {
		"govAgencyOrganizationId",
		"govAgencyTaxNo",
		"invoiceTemplateNo",
		"invoiceIssueNo",
		"invoiceLastNo",
		"bankInfo",
		"keypayDomain",
		"keypayVersion",
		"keypayMerchantCode",
		"keypaySecureKey",
		"reportTemplate",
		"status",
		"paymentGateType",
		"returnUrl",
		"paymentConfigIdNew",
		"placeInfo"
	};

	public static JSONObject getPaymentConfigColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("govAgencyOrganizationId", "govAgencyOrganizationId");
		jsonObject.put("govAgencyTaxNo", "govAgencyTaxNo");
		jsonObject.put("invoiceTemplateNo", "invoiceTemplateNo");
		jsonObject.put("invoiceIssueNo", "invoiceIssueNo");
		jsonObject.put("invoiceLastNo", "invoiceLastNo");
		jsonObject.put("bankInfo", "bankInfo");
		jsonObject.put("keypayDomain", "keypayDomain");
		jsonObject.put("keypayVersion", "keypayVersion");
		jsonObject.put("keypayMerchantCode", "keypayMerchantCode");
		jsonObject.put("keypaySecureKey", "keypaySecureKey");
		jsonObject.put("reportTemplate", "reportTemplate");
		jsonObject.put("status", "status");
		jsonObject.put("paymentGateType", "paymentGateType");
		jsonObject.put("returnUrl", "returnUrl");
		jsonObject.put("paymentConfigIdNew", "paymentConfigIdNew");
		jsonObject.put("placeInfo", "placeInfo");
		

		return jsonObject;

	}
	
	public static final String DOSSIER = Dossier.class.getName();
	public static final String EXTableName_DOSSIER= "opencps_dossier";
	public static final String[] DOSSIERColumns = {
		"userId",
		"createDate",
		"modifiedDate",
		"ownerOrganizationId",
		"serviceConfig",
		"serviceInfoId",
		"serviceDomainIndex",
		"serviceAdministrationIndex",
		"dossierTemplateId",
		"govAgencyCode",
		"govAgencyName",
		"serviceMode",
		"counter",
		"subjectId",
		"subjectName",
		"note",
		"submitDateTime",
		"receiveDateTime",
		"estimateDateTime",
		"finishDateTime",
		"receptionNo",
		"dossierStatus",
		"dossierSource",
		"oid",
		"keypayRedirectUrl",
		"delayStatus",
		"dossierIdNew"
		
	};

	public static JSONObject getDOSSIERColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("userId", "userId");
		jsonObject.put("createDate", "createDate");
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("ownerOrganizationId", "ownerOrganizationId");
		jsonObject.put("serviceInfoId", "serviceInfoId");
		jsonObject.put("serviceConfig", "serviceConfig");
		jsonObject.put("serviceDomainIndex", "serviceDomainIndex");
		jsonObject.put("serviceAdministrationIndex", "serviceAdministrationIndex");
		jsonObject.put("dossierTemplateId", "dossierTemplateId");
		jsonObject.put("govAgencyCode", "govAgencyCode");
		jsonObject.put("govAgencyName", "govAgencyName");
		jsonObject.put("serviceMode", "serviceMode");
		jsonObject.put("counter", "counter");
		jsonObject.put("subjectId", "subjectId");
		jsonObject.put("subjectName", "subjectName");
		jsonObject.put("note", "note");
		jsonObject.put("submitDateTime", "submitDateTime");
		jsonObject.put("receiveDateTime", "receiveDateTime");
		jsonObject.put("estimateDateTime", "estimateDateTime");
		jsonObject.put("finishDateTime", "finishDateTime");
		jsonObject.put("receptionNo", "receptionNo");
		jsonObject.put("dossierStatus", "dossierStatus");
		jsonObject.put("dossierSource", "dossierSource");
		jsonObject.put("oid", "oid");
		jsonObject.put("keypayRedirectUrl", "keypayRedirectUrl");
		jsonObject.put("delayStatus", "delayStatus");
		jsonObject.put("dossierIdNew", "dossierIdNew");
		

		return jsonObject;

	}
	
	public static final String DOSSIER_FILE = DossierFile.class.getName();
	public static final String EXTableName_DOSSIER_FILE= "opencps_dossier_file";
	public static final String[] DOSSIER_FILEColumns = {
		"userId",
		"createDate",
		"modifiedDate",
		"dossierId",
		"dossierPartId",
		"templateFileNo",
		"ownerOrganizationId",
		"displayName",
		"formData",
		"fileEntryId",
		"dossierFileType",
		"dossierFileNo",
		"dossierFileDate",
		"removed",
		"original",
		"syncStatus",
		"oid",
		"version",
		"signed",
		"dossierFileMark",
		"signCheck",
		"signInfo",
		"dossierFileIdNew"
	};

	public static JSONObject getDOSSIER_FILEColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("userId", "userId");
		jsonObject.put("createDate", "createDate");
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("ownerOrganizationId", "ownerOrganizationId");
		jsonObject.put("dossierId", "dossierId");
		jsonObject.put("dossierPartId", "dossierPartId");
		jsonObject.put("templateFileNo", "templateFileNo");
		jsonObject.put("displayName", "displayName");
		jsonObject.put("formData", "formData");
		jsonObject.put("fileEntryId", "fileEntryId");
		jsonObject.put("dossierFileType", "dossierFileType");
		jsonObject.put("dossierFileNo", "dossierFileNo");
		jsonObject.put("dossierFileDate", "dossierFileDate");
		jsonObject.put("removed", "removed");
		jsonObject.put("original", "original");
		jsonObject.put("syncStatus", "syncStatus");
		jsonObject.put("oid", "oid");
		jsonObject.put("version", "version");
		jsonObject.put("signed", "signed");
		jsonObject.put("dossierFileMark", "dossierFileMark");
		jsonObject.put("signCheck", "signCheck");
		jsonObject.put("signInfo", "signInfo");
		jsonObject.put("dossierFileIdNew", "dossierFileIdNew");
		

		return jsonObject;

	}
	public static final String DOSSIER_LOG = DossierLog.class.getName();
	public static final String EXTableName_DOSSIER_LOG= "opencps_dossier_log";
	public static final String[] DOSSIER_LOGColumns = {
		"userId",
		"createDate",
		"modifiedDate",
		"dossierId",
		"requestCommand",
		"dossierStatus",
		"actionInfo",
		"messageInfo",
		"updateDateTime",
		"level",
		"actor",
		"actorId",
		"actorName",
		"syncStatus",
		"dossierLogIdNew"
	};

	public static JSONObject getDOSSIER_LOGColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("userId", "userId");
		jsonObject.put("createDate", "createDate");
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("updateDateTime", "updateDateTime");
		jsonObject.put("dossierId", "dossierId");
		jsonObject.put("requestCommand", "requestCommand");
		jsonObject.put("dossierStatus", "dossierStatus");
		jsonObject.put("actionInfo", "actionInfo");
		jsonObject.put("messageInfo", "messageInfo");
		jsonObject.put("level", "level");
		jsonObject.put("actor", "actor");
		jsonObject.put("actorId", "actorId");
		jsonObject.put("actorName", "actorName");
		jsonObject.put("syncStatus", "syncStatus");
		jsonObject.put("dossierLogIdNew", "dossierLogIdNew");
		

		return jsonObject;

	}
	public static final String DOSSIER_FILE_LOG = DossierFileLog.class.getName();
	public static final String EXTableName_DOSSIER_FILE_LOG= "opencps_dossier_file_log";
	public static final String[] DOSSIER_FILE_LOGColumns = {
		"modifiedDate",
		"userId",
		"userName",
		"dossierId",
		"stepId",
		"isUpdate",
		"fileName",
		"fileVersion",
		"actionCode",
		"count_",
		"oid",
		"fileEntryId",
		"logId",
		"actor",
		"dossierFileLogIdNew"
	};

	public static JSONObject getDOSSIER_FILE_LOGColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("userId", "userId");
		jsonObject.put("userName", "userName");
		jsonObject.put("dossierId", "dossierId");
		jsonObject.put("stepId", "stepId");
		jsonObject.put("isUpdate", "isUpdate");
		jsonObject.put("fileName", "fileName");
		jsonObject.put("fileVersion", "fileVersion");
		jsonObject.put("actionCode", "actionCode");
		jsonObject.put("count_", "count_");
		jsonObject.put("oid", "oid");
		jsonObject.put("fileEntryId", "fileEntryId");
		jsonObject.put("logId", "logId");
		jsonObject.put("actor", "actor");
		jsonObject.put("dossierFileLogIdNew", "dossierFileLogIdNew");
		

		return jsonObject;

	}
	public static final String PROCESS_ORDER = ProcessOrder.class.getName();
	public static final String EXTableName_PROCESS_ORDER= "opencps_processorder";
	public static final String[] PROCESS_ORDERColumns = {
		"createDate",
		"modifiedDate",
		"serviceInfoId",
		"dossierTemplateId",
		"govAgencyOrganizationId",
		"govAgencyName",
		"govAgencyCode",
		"serviceProcessId",
		"dossierId",
		"processStepId",
		"actionUserId",
		"actionDateTime",
		"actionNote",
		"assignToUserId",
		"processWorkflowId",
		"dossierStatus",
		"processOrderIdNew"
		
	};

	public static JSONObject getPROCESS_ORDERColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("createDate", "createDate");
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("serviceInfoId", "serviceInfoId");
		jsonObject.put("dossierTemplateId", "dossierTemplateId");
		jsonObject.put("govAgencyOrganizationId", "govAgencyOrganizationId");
		jsonObject.put("govAgencyName", "govAgencyCode");
		jsonObject.put("serviceProcessId", "serviceProcessId");
		jsonObject.put("dossierId", "dossierId");
		jsonObject.put("processStepId", "processStepId");
		jsonObject.put("actionUserId", "actionUserId");
		jsonObject.put("actionDateTime", "actionDateTime");
		jsonObject.put("actionNote", "actionNote");
		jsonObject.put("assignToUserId", "assignToUserId");
		jsonObject.put("processWorkflowId", "processWorkflowId");
		jsonObject.put("dossierStatus", "dossierStatus");
		jsonObject.put("processOrderIdNew", "processOrderIdNew");
		

		return jsonObject;

	}
	public static final String PAYMENT_FILE = PaymentFile.class.getName();
	public static final String EXTableName_PAYMENT_FILE= "opencps_paymentfile";
	public static final String[] PAYMENT_FILEColumns = {
		"createDate",
		"modifiedDate",
		"requestDateTime",
		"dossierId",
		"ownerUserId",
		"ownerOrganizationId",
		"govAgencyOrganizationId",
		"paymentName",
		"amount",
		"requestNote",
		"keypayUrl",
		"keypayTransaction",
		"keypayGoodCode",
		"keypayMerchantCode",
		"paymentStatus",
		"paymentMethod",
		"confirmDateTime",
		"confirmFileEntryId",
		"paymentOptions",
		"syncStatus",
		"oid",
		"paymentConfigId",
		"paymentGateStatusCode",
		"paymentGateResponseCode",
		"paymentGateCheckCode",
		"paymentGateCheckResponseData",
		"paymentFileIdNew"
		
	};

	public static JSONObject getPAYMENT_FILEColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("createDate", "createDate");
		jsonObject.put("modifiedDate", "modifiedDate");
		jsonObject.put("requestDateTime", "requestDateTime");
		jsonObject.put("dossierId", "dossierId");
		jsonObject.put("ownerUserId", "ownerUserId");
		jsonObject.put("ownerOrganizationId", "ownerOrganizationId");
		jsonObject.put("govAgencyOrganizationId", "govAgencyOrganizationId");
		jsonObject.put("paymentName", "paymentName");
		jsonObject.put("amount", "amount");
		jsonObject.put("requestNote", "requestNote");
		jsonObject.put("keypayUrl", "keypayUrl");
		jsonObject.put("keypayTransaction", "keypayTransaction");
		jsonObject.put("keypayGoodCode", "keypayGoodCode");
		jsonObject.put("keypayMerchantCode", "keypayMerchantCode");
		jsonObject.put("paymentStatus", "paymentStatus");
		jsonObject.put("paymentMethod", "paymentMethod");
		jsonObject.put("confirmDateTime", "confirmDateTime");
		jsonObject.put("confirmFileEntryId", "confirmFileEntryId");
		jsonObject.put("paymentOptions", "paymentOptions");
		jsonObject.put("syncStatus", "syncStatus");
		jsonObject.put("oid", "oid");
		jsonObject.put("paymentConfigId", "paymentConfigId");
		jsonObject.put("paymentGateStatusCode", "paymentGateStatusCode");
		jsonObject.put("paymentGateResponseCode", "paymentGateResponseCode");
		jsonObject.put("paymentGateCheckCode", "paymentGateCheckCode");
		jsonObject.put("paymentGateCheckResponseData", "paymentGateCheckResponseData");
		jsonObject.put("paymentFileIdNew", "paymentFileIdNew");
		

		return jsonObject;

	}
	


	public static final String FILEENTRY_DOSSIERFILE = FileEntryDossierFile.class
			.getName();
	public static final String EXTableName_FILEENTRY_DOSSIERFILE = "opencps_fileentry_dossierFile";
	public static final String[] FILEENTRY_DOSSIERFILEColumns = {
			"fileEntryId", "dossierFileId"};
	public static JSONObject getFILEENTRY_DOSSIERFILEColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("fileEntryId", "fileEntryId");
		jsonObject.put("dossierFileId", "dossierFileId");
		

		return jsonObject;

}
	
	public static final String ACTION_HISTORY = ActionHistory.class.getName();
	public static final String EXTableName_ACTION_HISTORY= "opencps_actionhistory";
	public static final String[] ACTION_HISTORYColumns = {
		"processOrderId",
		"processWorkflowId",
		"actionDateTime",
		"stepName",
		"actionName",
		"actionNote",
		"actionUserId",
		"dayDoing",
		"dayDelay",
		"logId",
		"actionHistoryIdNew"
		
	};

	public static JSONObject getACTION_HISTORYColumnNames() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("processOrderId", "processOrderId");
		jsonObject.put("processWorkflowId", "processWorkflowId");
		jsonObject.put("actionDateTime", "actionDateTime");
		jsonObject.put("stepName", "stepName");
		jsonObject.put("actionName", "actionName");
		jsonObject.put("actionNote", "actionNote");
		jsonObject.put("actionUserId", "actionUserId");
		jsonObject.put("dayDoing", "dayDoing");
		jsonObject.put("dayDelay", "dayDelay");
		jsonObject.put("logId", "logId");
		jsonObject.put("actionHistoryIdNew", "actionHistoryIdNew");
		
		

		return jsonObject;

	}
	
	public static final String FETCH_DICTITEM = "fetchDictItem";
	public static final String ADD_DICTITEM = "addDictItem";
	
	public static final String FETCH_DICTCOLLECTION = "fetchDictCollection";
	public static final String ADD_DICTCOLLECTION = "addDictCollection";
	
	public static final String FETCH_SERVICE_INFO = "fetchServiceInfo";
	public static final String ADD_SERVICE_INFO = "addServiceInfo";
	
	public static final String FETCH_TEMPLATE_FILE = "fetchTemplateFile";
	public static final String ADD_TEMPLATE_FILE = "addTemplateFile";
	
	public static final String FETCH_SERVICE_TEMP_FILE = "fetchServiceTemplateFile";
	public static final String ADD_SERVICE_TEMP_FILE= "addServiceTemplateFile";
	
	public static final String FETCH_DOSSIER_TEMP = "fetchDossierTemplate";
	public static final String ADD_DOSSIER_TEMP = "addDossierTemplate";
	
	public static final String FETCH_DOSSIER_PART = "fetchDossierPart";
	public static final String ADD_DOSSIER_PART = "addDossierPart";
	
	public static final String FETCH_SERVICE_PROCESS = "fetchServiceProcess";
	public static final String ADD_SERVICE_PROCESS = "addServiceProcess";
	
	public static final String FETCH_CONTACT = "fetchContacts";
	public static final String ADD_CONTACT = "addContact";
	
	public static final String FETCH_USER = "fetchUsers";
	public static final String ADD_USER = "addUsers";
	
	public static final String FETCH_ORG = "fetchOrganization";
	public static final String ADD_ORG = "addOrganization";
	
	public static final String FETCH_SERVICE_CONFIG = "fetchServiceConfig";
	public static final String ADD_SERVICE_CONFIG = "addServiceConfig";
	
	public static final String FETCH_PROCESS_STEP = "fetchProcessStep";
	public static final String ADD_PROCESS_STEP = "addProcessStep";
	
	public static final String FETCH_PRO_STEP_DOSS_PART = "fetchProcessStepDossierPart";
	public static final String ADD_PRO_STEP_DOSS_PART = "addProcessStepDossierPart";
	
	public static final String FETCH_WORKFLOW = "fetchWorkflow";
	public static final String ADD_WORKFLOW = "addWorkflow";
	
	public static final String FETCH_WORKFLOW_OUTPUT = "fetchWorkflowOutput";
	public static final String ADD_WORKFLOW_OUTPUT = "addWorkflowOutput";
	
	public static final String FETCH_ROLE = "fetchRole";
	public static final String ADD_ROLE = "addRole";
	
	public static final String FETCH_USERS_ORGS = "fetchUsersOrgs";
	public static final String ADD_USERS_ORGS = "addUsersOrgs";
	
	public static final String FETCH_USERS_ROLES = "fetchUsersRoles";
	public static final String ADD_USERS_ROLES = "addUsersRoles";
	
	public static final String FETCH_STEP_ALLOW = "fetchStepAllowance";
	public static final String ADD_STEP_ALLOW = "addStepAllowance";
	
	public static final String FETCH_CITIZEN = "fetchCitizen";
	public static final String ADD_CITIZEN = "addCitizen";
	
	public static final String FETCH_BUSINESS = "fetchBusiness";
	public static final String ADD_BUSINESS = "addBusiness";
	
	public static final String FETCH_WORK_UNIT = "fetchWorkingUnit";
	public static final String ADD_WORK_UNIT = "addWorkingUnit";
	
	public static final String FETCH_JOB_POS = "fetchJobPos";
	public static final String ADD_JOB_POS = "addJobPos";
	
	public static final String FETCH_EMPLOYEE = "fetchEmployee";
	public static final String ADD_EMPLOYEE = "addEmployee";
	
	public static final String FETCH_WORK_JOB = "fetchWorkJob";
	public static final String ADD_WORK_JOB = "addWorkJob";
	
	public static final String FETCH_EM_JOB = "fetchEmployeeJob";
	public static final String ADD_EM_JOB = "addEmployeeJob";
	
	public static final String FETCH_PAY_CONFIG = "fetchPaymentConfig";
	public static final String ADD_PAY_CONFIG = "addPaymentConfig";
	
	
}
