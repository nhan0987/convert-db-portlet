package org.opencps.datamgt.portlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.model.Citizen;
import org.opencps.accountmgt.service.BusinessLocalServiceUtil;
import org.opencps.accountmgt.service.CitizenLocalServiceUtil;
import org.opencps.datamgt.NoSuchDictCollectionException;
import org.opencps.datamgt.NoSuchDictItemException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierFileLog;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.expando.model.DomainConfigExt;
import org.opencps.expando.service.ExpandoExtLocalServiceUtil;
import org.opencps.paymentmgt.model.PaymentFile;
import org.opencps.paymentmgt.service.PaymentFileLocalServiceUtil;
import org.opencps.processmgt.NoSuchProcessOrderException;
import org.opencps.processmgt.NoSuchServiceProcessException;
import org.opencps.processmgt.model.ActionHistory;
import org.opencps.processmgt.model.ProcessOrder;
import org.opencps.processmgt.model.ProcessStep;
import org.opencps.processmgt.model.ProcessStepDossierPart;
import org.opencps.processmgt.model.ProcessWorkflow;
import org.opencps.processmgt.model.ServiceProcess;
import org.opencps.processmgt.model.StepAllowance;
import org.opencps.processmgt.model.WorkflowOutput;
import org.opencps.processmgt.service.ActionHistoryLocalServiceUtil;
import org.opencps.processmgt.service.ProcessOrderLocalServiceUtil;
import org.opencps.processmgt.service.ProcessStepDossierPartLocalServiceUtil;
import org.opencps.processmgt.service.ProcessStepLocalServiceUtil;
import org.opencps.processmgt.service.ProcessWorkflowLocalServiceUtil;
import org.opencps.processmgt.service.ServiceInfoProcessLocalServiceUtil;
import org.opencps.processmgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.processmgt.service.StepAllowanceLocalServiceUtil;
import org.opencps.processmgt.service.WorkflowOutputLocalServiceUtil;
import org.opencps.servicemgt.model.ServiceFileTemplate;
import org.opencps.servicemgt.model.ServiceInfo;
import org.opencps.servicemgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.servicemgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.servicemgt.service.TemplateFileLocalServiceUtil;
import org.opencps.utils.ActionHistoryUtils;
import org.opencps.utils.BusinessUtils;
import org.opencps.utils.CitizenUtils;
import org.opencps.utils.CommonUtils;
import org.opencps.utils.ContactUtils;
import org.opencps.utils.DLUserFileEntryUtil;
import org.opencps.utils.DictCollectionUtils;
import org.opencps.utils.DictItemUtils;
import org.opencps.utils.DossierFileLogUtils;
import org.opencps.utils.DossierFileUtils;
import org.opencps.utils.DossierLogUtils;
import org.opencps.utils.DossierPartUtils;
import org.opencps.utils.DossierTemplateUtils;
import org.opencps.utils.DossierUtils;
import org.opencps.utils.EmployeeJobUtils;
import org.opencps.utils.EmployeeUtils;
import org.opencps.utils.JobPosUtils;
import org.opencps.utils.OrganizationUtils;
import org.opencps.utils.PaymentConfigUtils;
import org.opencps.utils.PaymentFileUtils;
import org.opencps.utils.ProcessOrderUtils;
import org.opencps.utils.ProcessStepDossiderPartUtils;
import org.opencps.utils.ProcessStepUtils;
import org.opencps.utils.RoleUtils;
import org.opencps.utils.ServiceConfigUtils;
import org.opencps.utils.ServiceInfoProcessUtils;
import org.opencps.utils.ServiceInfoTemplateFileUtils;
import org.opencps.utils.ServiceInfoUtils;
import org.opencps.utils.ServiceProcessUtils;
import org.opencps.utils.StepAllowanceUtils;
import org.opencps.utils.TemplateFileUtils;
import org.opencps.utils.UsersOrgsUtils;
import org.opencps.utils.UsersRolesUtils;
import org.opencps.utils.UsersUtils;
import org.opencps.utils.WebKeys;
import org.opencps.utils.WorkJobsUtils;
import org.opencps.utils.WorkflowOutputUtils;
import org.opencps.utils.WorkflowUtils;
import org.opencps.utils.WorkingUnitUtils;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class NewPortlet
 */
public class DataMgtConvertPortlet extends MVCPortlet {

	public void fetchDictItem(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DictItemUtils dictItem = new DictItemUtils();
		dictItem.fetchDictItem(actionRequest, actionResponse);

	}

	public void addDictItem(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DictItemUtils dictItem = new DictItemUtils();
		dictItem.addDicItem1(actionRequest, actionResponse);
	}

	public void addTreeIndex(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DictItemUtils dictItem = new DictItemUtils();
		dictItem.addTreeIndex(actionRequest, actionResponse);
	}

	public void fetchDictCollection(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DictCollectionUtils utils = new DictCollectionUtils();
		utils.fetchDictCollection(actionRequest, actionResponse);

	}

	public void addDictCollection(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DictCollectionUtils utils = new DictCollectionUtils();
		utils.addDictCollection(actionRequest, actionResponse);

	}

	public void fetchServiceInfo(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoUtils serviceInfo = new ServiceInfoUtils();
		serviceInfo.fetchServiceInfo(actionRequest, actionResponse);

	}

	public void addServiceInfo(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoUtils serviceInfo = new ServiceInfoUtils();
		serviceInfo.addServiceInfo(actionRequest, actionResponse);

	}

	public void fetchTemplateFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		TemplateFileUtils utils = new TemplateFileUtils();
		utils.fetchTemplateFile(actionRequest, actionResponse);

	}

	public void addTemplateFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		TemplateFileUtils utils = new TemplateFileUtils();
		utils.addTemplateFile(actionRequest, actionResponse);

	}

	public void fetchServiceTemplateFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoTemplateFileUtils utils = new ServiceInfoTemplateFileUtils();
		utils.fetchServiceTemplateFile(actionRequest, actionResponse);

	}

	public void addServiceTemplateFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoTemplateFileUtils utils = new ServiceInfoTemplateFileUtils();
		utils.addServiceTemplateFile(actionRequest, actionResponse);

	}

	public void fetchDossierTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierTemplateUtils utils = new DossierTemplateUtils();
		utils.fetchDossierTemplate(actionRequest, actionResponse);

	}

	public void addDossierTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierTemplateUtils utils = new DossierTemplateUtils();
		utils.addDossierTemplate(actionRequest, actionResponse);

	}

	public void fetchDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierPartUtils utils = new DossierPartUtils();
		utils.fetchDossierPart(actionRequest, actionResponse);

	}

	public void addDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierPartUtils utils = new DossierPartUtils();
		utils.addDossierPart(actionRequest, actionResponse);

	}

	public void addDossierPartTreeIndex(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierPartUtils utils = new DossierPartUtils();
		utils.addTreeIndex(actionRequest, actionResponse);

	}

	public void fetchServiceProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceProcessUtils utils = new ServiceProcessUtils();
		utils.fetchServiceProcess(actionRequest, actionResponse);

	}

	public void addServiceProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceProcessUtils utils = new ServiceProcessUtils();
		utils.addServiceProcess(actionRequest, actionResponse);

	}

	public void fetchContacts(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ContactUtils utils = new ContactUtils();
		utils.fetchContacts(actionRequest, actionResponse);

	}

	public void addContact(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ContactUtils utils = new ContactUtils();
		utils.addContact(actionRequest, actionResponse);

	}

	public void fetchUsers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersUtils utils = new UsersUtils();
		utils.fetchUsers(actionRequest, actionResponse);

	}

	public void addUsers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersUtils utils = new UsersUtils();
		utils.addUsers(actionRequest, actionResponse);

	}

	public void fetchOrganization(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		OrganizationUtils utils = new OrganizationUtils();
		utils.fetchOrganization(actionRequest, actionResponse);

	}

	public void addOrganization(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		OrganizationUtils utils = new OrganizationUtils();
		utils.addOrganization(actionRequest, actionResponse);

	}

	public void addOrganization1(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		OrganizationUtils utils = new OrganizationUtils();
		utils.addOrganization1(actionRequest, actionResponse);

	}

	public void addTreePathOrg(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		OrganizationUtils utils = new OrganizationUtils();
		utils.addTreePathOrg(actionRequest, actionResponse);

	}

	public void fetchServiceConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceConfigUtils utils = new ServiceConfigUtils();
		utils.fetchServiceConfig(actionRequest, actionResponse);

	}

	public void addServiceConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceConfigUtils utils = new ServiceConfigUtils();
		utils.addServiceConfig(actionRequest, actionResponse);

	}

	public void fetchProcessStep(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessStepUtils utils = new ProcessStepUtils();
		utils.fetchProcessStep(actionRequest, actionResponse);

	}

	public void addProcessStep(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessStepUtils utils = new ProcessStepUtils();
		utils.addProcessStep(actionRequest, actionResponse);

	}

	public void fetchProcessStepDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessStepDossiderPartUtils utils = new ProcessStepDossiderPartUtils();
		utils.fetchProcessStepDossierPart(actionRequest, actionResponse);

	}

	public void addProcessStepDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessStepDossiderPartUtils utils = new ProcessStepDossiderPartUtils();
		utils.addProcessStepDossierPart(actionRequest, actionResponse);

	}

	public void fetchWorkflow(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkflowUtils utils = new WorkflowUtils();
		utils.fetchWorkflow(actionRequest, actionResponse);

	}

	public void addWorkflow(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkflowUtils utils = new WorkflowUtils();
		utils.addWorkflow(actionRequest, actionResponse);

	}

	public void fetchWorkflowOutput(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkflowOutputUtils utils = new WorkflowOutputUtils();
		utils.fetchWorkflowOutput(actionRequest, actionResponse);

	}

	public void addWorkflowOutput(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkflowOutputUtils utils = new WorkflowOutputUtils();
		utils.addWorkflowOutput(actionRequest, actionResponse);

	}

	public void fetchRole(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		RoleUtils utils = new RoleUtils();
		utils.fetchRole(actionRequest, actionResponse);

	}

	public void addRole(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		RoleUtils utils = new RoleUtils();
		utils.addRole(actionRequest, actionResponse);

	}

	public void fetchUsersOrgs(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersOrgsUtils utils = new UsersOrgsUtils();
		utils.fetchUsersOrgs(actionRequest, actionResponse);

	}

	public void addUsersOrgs(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersOrgsUtils utils = new UsersOrgsUtils();
		utils.addUsersOrgs(actionRequest, actionResponse);

	}

	public void fetchUsersRoles(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersRolesUtils utils = new UsersRolesUtils();
		utils.fetchUsersRoles(actionRequest, actionResponse);

	}

	public void addUsersRoles(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		UsersRolesUtils utils = new UsersRolesUtils();
		utils.addUsersRoles(actionRequest, actionResponse);

	}

	public void fetchStepAllowance(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		StepAllowanceUtils utils = new StepAllowanceUtils();
		utils.fetchStepAllowance(actionRequest, actionResponse);

	}

	public void addStepAllowance(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		StepAllowanceUtils utils = new StepAllowanceUtils();
		utils.addStepAllowance(actionRequest, actionResponse);

	}

	public void fetchCitizen(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		CitizenUtils utils = new CitizenUtils();
		utils.fetchCitizen(actionRequest, actionResponse);

	}

	public void addCitizen(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		CitizenUtils utils = new CitizenUtils();
		utils.addCitizen(actionRequest, actionResponse);

	}

	public void fetchBusiness(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		BusinessUtils utils = new BusinessUtils();
		utils.fetchBusiness(actionRequest, actionResponse);

	}

	public void addBusiness(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		BusinessUtils utils = new BusinessUtils();
		utils.addBusiness(actionRequest, actionResponse);

	}

	public void fetchWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkingUnitUtils utils = new WorkingUnitUtils();
		utils.fetchWorkingUnit(actionRequest, actionResponse);

	}

	public void addWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkingUnitUtils utils = new WorkingUnitUtils();
		utils.addWorkingUnit(actionRequest, actionResponse);

	}

	public void fetchJobPos(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		JobPosUtils utils = new JobPosUtils();
		utils.fetchJobPos(actionRequest, actionResponse);

	}

	public void addJobPos(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		JobPosUtils utils = new JobPosUtils();
		utils.addJobPos(actionRequest, actionResponse);

	}

	public void fetchEmployee(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		EmployeeUtils utils = new EmployeeUtils();
		utils.fetchEmployee(actionRequest, actionResponse);

	}

	public void addEmployee(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		EmployeeUtils utils = new EmployeeUtils();
		utils.addEmployee(actionRequest, actionResponse);

	}

	public void fetchWorkJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkJobsUtils utils = new WorkJobsUtils();
		utils.fetchWorkJob(actionRequest, actionResponse);

	}

	public void addWorkJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkJobsUtils utils = new WorkJobsUtils();
		utils.addWorkJob(actionRequest, actionResponse);

	}

	public void fetchEmployeeJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		EmployeeJobUtils utils = new EmployeeJobUtils();
		utils.fetchEmployeeJob(actionRequest, actionResponse);

	}

	public void addEmployeeJob(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		EmployeeJobUtils utils = new EmployeeJobUtils();
		utils.addEmployeeJob(actionRequest, actionResponse);

	}

	public void fetchPaymentConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		PaymentConfigUtils utils = new PaymentConfigUtils();
		utils.fetchPaymentConfig(actionRequest, actionResponse);

	}

	public void addPaymentConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		PaymentConfigUtils utils = new PaymentConfigUtils();
		utils.addPaymentConfig(actionRequest, actionResponse);

	}

	public void addServiceInfoProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoProcessUtils utils = new ServiceInfoProcessUtils();
		utils.addServiceInfoProcess(actionRequest, actionResponse);

	}

	public void fetchServiceInfoProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ServiceInfoProcessUtils utils = new ServiceInfoProcessUtils();
		utils.fetchServiceInfoProcess(actionRequest, actionResponse);

	}

	public void addTreeIndexWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		WorkingUnitUtils utils = new WorkingUnitUtils();
		utils.addTreeIndexWorkingUnit1(actionRequest, actionResponse);

	}

	public void fetchAll(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		fetchDictCollection(actionRequest, actionResponse);
		fetchDictItem(actionRequest, actionResponse);

		fetchUsers(actionRequest, actionResponse);
		fetchOrganization(actionRequest, actionResponse);

		fetchRole(actionRequest, actionResponse);
		fetchUsersOrgs(actionRequest, actionResponse);

		fetchUsersRoles(actionRequest, actionResponse);
		fetchTemplateFile(actionRequest, actionResponse);

		fetchServiceTemplateFile(actionRequest, actionResponse);
		fetchDossierTemplate(actionRequest, actionResponse);

		fetchServiceInfo(actionRequest, actionResponse);
		fetchServiceInfoProcess(actionRequest, actionResponse);

		fetchServiceProcess(actionRequest, actionResponse);
		fetchDossierPart(actionRequest, actionResponse);

		fetchProcessStep(actionRequest, actionResponse);
		fetchStepAllowance(actionRequest, actionResponse);

		fetchProcessStepDossierPart(actionRequest, actionResponse);
		fetchWorkflow(actionRequest, actionResponse);

		fetchWorkflowOutput(actionRequest, actionResponse);
		fetchServiceConfig(actionRequest, actionResponse);

		fetchWorkingUnit(actionRequest, actionResponse);
		fetchCitizen(actionRequest, actionResponse);

		fetchBusiness(actionRequest, actionResponse);
		fetchJobPos(actionRequest, actionResponse);

		fetchEmployeeJob(actionRequest, actionResponse);
		fetchWorkJob(actionRequest, actionResponse);

		fetchEmployee(actionRequest, actionResponse);
		fetchPaymentConfig(actionRequest, actionResponse);

	}

	public void addAll(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		CommonUtils commonUtils = new CommonUtils();
		DomainConfigExt domainConfigExt = commonUtils
				.getDomainConfigData(themeDisplay.getCompanyId());

		if (Validator.isNotNull(domainConfigExt)
				&& Validator.isNotNull(domainConfigExt.getUserName())
				&& Validator.isNotNull(domainConfigExt.getPassword())
				&& Validator.isNotNull(domainConfigExt.getTargetDomain())) {

			addDictCollection(actionRequest, actionResponse);
			addDictItem(actionRequest, actionResponse);
			addTreeIndex(actionRequest, actionResponse);

			addUsers(actionRequest, actionResponse);
			addOrganization1(actionRequest, actionResponse);
			addTreePathOrg(actionRequest, actionResponse);
			addUsersOrgs(actionRequest, actionResponse);

			addRole(actionRequest, actionResponse);
			addUsersRoles(actionRequest, actionResponse);

			addWorkingUnit(actionRequest, actionResponse);
			addTreeIndexWorkingUnit(actionRequest, actionResponse);

			addBusiness(actionRequest, actionResponse);
			addCitizen(actionRequest, actionResponse);
			addEmployee(actionRequest, actionResponse);

			addTemplateFile(actionRequest, actionResponse);
			addServiceInfo(actionRequest, actionResponse);
			addServiceTemplateFile(actionRequest, actionResponse);

			addDossierTemplate(actionRequest, actionResponse);
			addDossierPart(actionRequest, actionResponse);
			addDossierPartTreeIndex(actionRequest, actionResponse);

			addServiceProcess(actionRequest, actionResponse);

			addProcessStep(actionRequest, actionResponse);
			addStepAllowance(actionRequest, actionResponse);
			addProcessStepDossierPart(actionRequest, actionResponse);

			addWorkflow(actionRequest, actionResponse);
			addWorkflowOutput(actionRequest, actionResponse);

			addServiceConfig(actionRequest, actionResponse);
			addServiceInfoProcess(actionRequest, actionResponse);

			addJobPos(actionRequest, actionResponse);
			addWorkJob(actionRequest, actionResponse);

			addEmployeeJob(actionRequest, actionResponse);

			addPaymentConfig(actionRequest, actionResponse);

		} else {
			SessionErrors.add(actionRequest, "info-required");

		}

	}

	public void addDictItemCollection(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		CommonUtils commonUtils = new CommonUtils();
		DomainConfigExt domainConfigExt = commonUtils
				.getDomainConfigData(themeDisplay.getCompanyId());

		if (Validator.isNotNull(domainConfigExt)
				&& Validator.isNotNull(domainConfigExt.getUserName())
				&& Validator.isNotNull(domainConfigExt.getPassword())
				&& Validator.isNotNull(domainConfigExt.getTargetDomain())) {

			addDictCollection(actionRequest, actionResponse);
			addDictItem(actionRequest, actionResponse);
			addTreeIndex(actionRequest, actionResponse);

		} else {
			SessionErrors.add(actionRequest, "info-required");

		}

	}

	public void addNext(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		CommonUtils commonUtils = new CommonUtils();
		DomainConfigExt domainConfigExt = commonUtils
				.getDomainConfigData(themeDisplay.getCompanyId());

		if (Validator.isNotNull(domainConfigExt)
				&& Validator.isNotNull(domainConfigExt.getUserName())
				&& Validator.isNotNull(domainConfigExt.getPassword())
				&& Validator.isNotNull(domainConfigExt.getTargetDomain())) {

			addUsers(actionRequest, actionResponse);
			addOrganization1(actionRequest, actionResponse);
			addTreePathOrg(actionRequest, actionResponse);
			addUsersOrgs(actionRequest, actionResponse);

			addRole(actionRequest, actionResponse);
			addUsersRoles(actionRequest, actionResponse);

			addWorkingUnit(actionRequest, actionResponse);
			addTreeIndexWorkingUnit(actionRequest, actionResponse);

			addBusiness(actionRequest, actionResponse);
			addCitizen(actionRequest, actionResponse);
			addEmployee(actionRequest, actionResponse);

			addTemplateFile(actionRequest, actionResponse);
			addServiceInfo(actionRequest, actionResponse);
			addServiceTemplateFile(actionRequest, actionResponse);

			addDossierTemplate(actionRequest, actionResponse);
			addDossierPart(actionRequest, actionResponse);
			addDossierPartTreeIndex(actionRequest, actionResponse);

			addServiceProcess(actionRequest, actionResponse);

			addProcessStep(actionRequest, actionResponse);
			addStepAllowance(actionRequest, actionResponse);
			addProcessStepDossierPart(actionRequest, actionResponse);

			addWorkflow(actionRequest, actionResponse);
			addWorkflowOutput(actionRequest, actionResponse);

			addServiceConfig(actionRequest, actionResponse);
			addServiceInfoProcess(actionRequest, actionResponse);

			addJobPos(actionRequest, actionResponse);
			addWorkJob(actionRequest, actionResponse);

			addEmployeeJob(actionRequest, actionResponse);
			setUserGroupNew(actionRequest, actionResponse);
			addPaymentConfig(actionRequest, actionResponse);

		} else {
			SessionErrors.add(actionRequest, "info-required");

		}

	}

	public void getFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DLUserFileEntryUtil dlUserFileEntryUtil = new DLUserFileEntryUtil();
		dlUserFileEntryUtil.getFileEntry(actionRequest, actionResponse, 0, 0);

	}

	public void updateDomainConfigExt(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		String userName = ParamUtil.getString(actionRequest, "UserName");
		String password = ParamUtil.getString(actionRequest, "Password");
		String targetDomain = ParamUtil.getString(actionRequest, "Domain");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		CommonUtils commonUtils = new CommonUtils();
		DomainConfigExt domainConfigExt = null;

		domainConfigExt = commonUtils.getDomainConfigData(themeDisplay
				.getCompanyId());

		JSONObject columnNames = WebKeys.getDomainConfigColumnNames();

		if (Validator.isNull(domainConfigExt)) {

			ExpandoValueLocalServiceUtil.addValue(themeDisplay.getCompanyId(),
					WebKeys.DOMAIN_CONFIG, WebKeys.EXTableName_DOMAIN_CONFIG,
					columnNames.getString("domainConfigId"), 1,
					String.valueOf(1));
		} else {

		}

		ExpandoValueLocalServiceUtil.addValue(themeDisplay.getCompanyId(),
				WebKeys.DOMAIN_CONFIG, WebKeys.EXTableName_DOMAIN_CONFIG,
				columnNames.getString("targetDomain"), 1, targetDomain);

		ExpandoValueLocalServiceUtil.addValue(themeDisplay.getCompanyId(),
				WebKeys.DOMAIN_CONFIG, WebKeys.EXTableName_DOMAIN_CONFIG,
				columnNames.getString("userName"), 1, userName);

		ExpandoValueLocalServiceUtil.addValue(themeDisplay.getCompanyId(),
				WebKeys.DOMAIN_CONFIG, WebKeys.EXTableName_DOMAIN_CONFIG,
				columnNames.getString("password"), 1, password);

	}

	public void removeService(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		String serviceDomain1 = ParamUtil.getString(actionRequest,
				"serviceDomain");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		String[] arrayserviceDomain = StringUtil.split(serviceDomain1.trim(),
				StringPool.PIPE);

		List<String> serviceDomainList = Arrays.asList(arrayserviceDomain);

		try {

			if (serviceDomainList.size() > 0) {

				for (int i = 0; i < serviceDomainList.size(); i++) {

					DictItem dictItem_svDomain = null;
					DictCollection dictCol_svDomain = null;
					String serviceDomain = StringPool.BLANK;

					serviceDomain = serviceDomainList.get(i);

					try {

						dictCol_svDomain = DictCollectionLocalServiceUtil
								.getDictCollection(
										themeDisplay.getScopeGroupId(),
										WebKeys.SERVICE_DOMAIN);
					} catch (NoSuchDictCollectionException e) {

					}

					if (Validator.isNotNull(dictCol_svDomain)) {

						try {

							dictItem_svDomain = DictItemLocalServiceUtil
									.getDictItemInuseByItemCode(
											dictCol_svDomain
													.getDictCollectionId(),
											serviceDomain);
						} catch (NoSuchDictItemException e) {

						}

						if (Validator.isNotNull(dictItem_svDomain)) {

							List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();

							serviceInfoList = ServiceInfoLocalServiceUtil
									.getServiceInFosByG_DI(
											themeDisplay.getScopeGroupId(),
											dictItem_svDomain.getTreeIndex());

							if (serviceInfoList.size() > 0) {

								for (ServiceInfo serviceInfo : serviceInfoList) {

									// /////////////////////////////
									List<ServiceFileTemplate> serviceFileTemplateList = new ArrayList<ServiceFileTemplate>();

									serviceFileTemplateList = ServiceFileTemplateLocalServiceUtil
											.getServiceFileTemplatesByServiceInfo(serviceInfo
													.getServiceinfoId());

									_log.info("=====serviceInfo.getServiceinfoId():"
											+ serviceInfo.getServiceinfoId());

									if (serviceFileTemplateList.size() > 0) {

										for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplateList) {

											_log.info("=====serviceFileTemplate.getServiceinfoId():"
													+ serviceFileTemplate
															.getServiceinfoId());
											_log.info("=====serviceFileTemplate.getTemplatefileId():"
													+ serviceFileTemplate
															.getTemplatefileId());

											try {
												TemplateFileLocalServiceUtil
														.deleteTemplateFile(serviceFileTemplate
																.getTemplatefileId());
											} catch (Exception e) {

											}

											ServiceFileTemplateLocalServiceUtil
													.deleteServiceFileTemplate(serviceFileTemplate);
										}
									}

									// /////////////////////////////

									List<ServiceConfig> serviceConfigList = new ArrayList<ServiceConfig>();

									serviceConfigList = ServiceConfigLocalServiceUtil
											.getServiceConfigsByS_G(serviceInfo
													.getServiceinfoId(),
													themeDisplay
															.getScopeGroupId());

									if (serviceConfigList.size() > 0) {

										for (ServiceConfig serviceConfig : serviceConfigList) {

											DossierTemplate dossierTemplate = null;

											try {

												dossierTemplate = DossierTemplateLocalServiceUtil
														.getDossierTemplate(serviceConfig
																.getDossierTemplateId());
											} catch (Exception e) {

											}

											if (Validator
													.isNotNull(dossierTemplate)) {

												List<DossierPart> dossierPartList = new ArrayList<DossierPart>();

												dossierPartList = DossierPartLocalServiceUtil
														.getDossierParts(dossierTemplate
																.getDossierTemplateId());

												if (dossierPartList.size() > 0) {

													for (DossierPart dossierPart : dossierPartList) {

														DossierPartLocalServiceUtil
																.deleteDossierPart(dossierPart);

													}
												}
												DossierTemplateLocalServiceUtil
														.deleteDossierTemplate(dossierTemplate);
											}

											// ///////////////////////////////

											ServiceProcess serviceProcess = null;

											try {

												serviceProcess = ServiceProcessLocalServiceUtil
														.getServiceProcess(serviceConfig
																.getServiceProcessId());
											} catch (Exception e) {

											}

											if (Validator
													.isNotNull(serviceProcess)) {

												// //////////////////////////////

												List<ProcessWorkflow> processWorkflowList = new ArrayList<ProcessWorkflow>();

												processWorkflowList = ProcessWorkflowLocalServiceUtil
														.getWorkFlowByProcess(serviceProcess
																.getServiceProcessId());

												if (processWorkflowList.size() > 0) {

													for (ProcessWorkflow processWorkflow : processWorkflowList) {

														List<WorkflowOutput> workflowOutputList = new ArrayList<WorkflowOutput>();

														try {

															workflowOutputList = WorkflowOutputLocalServiceUtil
																	.getByProcessWF(processWorkflow
																			.getProcessWorkflowId());
														} catch (Exception e) {

														}

														if (workflowOutputList
																.size() > 0) {

															for (WorkflowOutput workflowOutput : workflowOutputList) {

																WorkflowOutputLocalServiceUtil
																		.deleteWorkflowOutput(workflowOutput
																				.getWorkflowOutputId());
															}
														}
														ProcessWorkflowLocalServiceUtil
																.deleteProcessWorkflow(processWorkflow
																		.getProcessWorkflowId());
													}
												}

												// /////////////////////////////

												List<ProcessStep> processStepList = new ArrayList<ProcessStep>();

												try {

													processStepList = ProcessStepLocalServiceUtil
															.getStepByProcess(serviceProcess
																	.getServiceProcessId());
												} catch (Exception e) {

												}

												if (processStepList.size() > 0) {

													for (ProcessStep processStep : processStepList) {

														List<StepAllowance> stepAllowanceList = new ArrayList<StepAllowance>();

														try {

															stepAllowanceList = StepAllowanceLocalServiceUtil
																	.getByProcessStep(processStep
																			.getProcessStepId());
														} catch (Exception e) {

														}

														if (stepAllowanceList
																.size() > 0) {

															for (StepAllowance stepAllowance : stepAllowanceList) {
																StepAllowanceLocalServiceUtil
																		.deleteStepAllowance(stepAllowance);
															}

														}
														List<ProcessStepDossierPart> processStepDossierPartList = new ArrayList<ProcessStepDossierPart>();

														try {

															processStepDossierPartList = ProcessStepDossierPartLocalServiceUtil
																	.getByStep(processStep
																			.getProcessStepId());
														} catch (Exception e) {

														}

														if (processStepDossierPartList
																.size() > 0) {

															for (ProcessStepDossierPart processStepDossierPart : processStepDossierPartList) {

																ProcessStepDossierPartLocalServiceUtil
																		.deleteProcessStepDossierPart(processStepDossierPart);
															}

														}

														ProcessStepLocalServiceUtil
																.deleteProcessStep(processStep
																		.getProcessStepId());
													}

												}

												ServiceInfoProcessLocalServiceUtil
														.deleteServiceInfoProcess(
																serviceConfig
																		.getServiceProcessId(),
																serviceConfig
																		.getServiceInfoId());

												ServiceProcessLocalServiceUtil
														.deleteServiceProcess(serviceProcess);

											}

											ServiceConfigLocalServiceUtil
													.deleteServiceConfig(serviceConfig);
										}
									}

									removeDossierContent(themeDisplay,
											serviceInfo.getServiceinfoId());

									ServiceInfoLocalServiceUtil
											.deleteServiceInfo(serviceInfo);
								}
							}
							DictItemLocalServiceUtil
									.deleteDictItem(dictItem_svDomain);
						}

					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	
	public void fetchByService(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		String serviceDomain1 = ParamUtil.getString(actionRequest,
				"serviceDomain");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		String[] arrayserviceDomain = StringUtil.split(serviceDomain1.trim(),
				StringPool.PIPE);

		List<String> serviceDomainList = Arrays.asList(arrayserviceDomain);

		try {

			if (serviceDomainList.size() > 0) {

				for (int i = 0; i < serviceDomainList.size(); i++) {

					DictItem dictItem_svDomain = null;
					DictCollection dictCol_svDomain = null;
					String serviceDomain = StringPool.BLANK;

					serviceDomain = serviceDomainList.get(i);

					try {

						dictCol_svDomain = DictCollectionLocalServiceUtil
								.getDictCollection(
										themeDisplay.getScopeGroupId(),
										WebKeys.SERVICE_DOMAIN);
					} catch (NoSuchDictCollectionException e) {

					}

					if (Validator.isNotNull(dictCol_svDomain)) {

						try {

							dictItem_svDomain = DictItemLocalServiceUtil
									.getDictItemInuseByItemCode(
											dictCol_svDomain
													.getDictCollectionId(),
											serviceDomain);
						} catch (NoSuchDictItemException e) {

						}

						if (Validator.isNotNull(dictItem_svDomain)) {

							List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();

							serviceInfoList = ServiceInfoLocalServiceUtil
									.getServiceInFosByG_DI(
											themeDisplay.getScopeGroupId(),
											dictItem_svDomain.getTreeIndex());

							if (serviceInfoList.size() > 0) {

								for (ServiceInfo serviceInfo : serviceInfoList) {

									// /////////////////////////////
									List<ServiceFileTemplate> serviceFileTemplateList = new ArrayList<ServiceFileTemplate>();

									serviceFileTemplateList = ServiceFileTemplateLocalServiceUtil
											.getServiceFileTemplatesByServiceInfo(serviceInfo
													.getServiceinfoId());

									_log.info("=====serviceInfo.getServiceinfoId():"
											+ serviceInfo.getServiceinfoId());

									if (serviceFileTemplateList.size() > 0) {

										for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplateList) {

											_log.info("=====serviceFileTemplate.getServiceinfoId():"
													+ serviceFileTemplate
															.getServiceinfoId());
											_log.info("=====serviceFileTemplate.getTemplatefileId():"
													+ serviceFileTemplate
															.getTemplatefileId());

											try {
												TemplateFileLocalServiceUtil
														.deleteTemplateFile(serviceFileTemplate
																.getTemplatefileId());
											} catch (Exception e) {

											}

											ServiceFileTemplateLocalServiceUtil
													.deleteServiceFileTemplate(serviceFileTemplate);
										}
									}

									// /////////////////////////////

									List<ServiceConfig> serviceConfigList = new ArrayList<ServiceConfig>();

									serviceConfigList = ServiceConfigLocalServiceUtil
											.getServiceConfigsByS_G(serviceInfo
													.getServiceinfoId(),
													themeDisplay
															.getScopeGroupId());

									if (serviceConfigList.size() > 0) {

										for (ServiceConfig serviceConfig : serviceConfigList) {

											DossierTemplate dossierTemplate = null;

											try {

												dossierTemplate = DossierTemplateLocalServiceUtil
														.getDossierTemplate(serviceConfig
																.getDossierTemplateId());
											} catch (Exception e) {

											}

											if (Validator
													.isNotNull(dossierTemplate)) {

												List<DossierPart> dossierPartList = new ArrayList<DossierPart>();

												dossierPartList = DossierPartLocalServiceUtil
														.getDossierParts(dossierTemplate
																.getDossierTemplateId());

												if (dossierPartList.size() > 0) {

													for (DossierPart dossierPart : dossierPartList) {

														DossierPartLocalServiceUtil
																.deleteDossierPart(dossierPart);

													}
												}
												DossierTemplateLocalServiceUtil
														.deleteDossierTemplate(dossierTemplate);
											}

											// ///////////////////////////////

											ServiceProcess serviceProcess = null;

											try {

												serviceProcess = ServiceProcessLocalServiceUtil
														.getServiceProcess(serviceConfig
																.getServiceProcessId());
											} catch (Exception e) {

											}

											if (Validator
													.isNotNull(serviceProcess)) {

												// //////////////////////////////

												List<ProcessWorkflow> processWorkflowList = new ArrayList<ProcessWorkflow>();

												processWorkflowList = ProcessWorkflowLocalServiceUtil
														.getWorkFlowByProcess(serviceProcess
																.getServiceProcessId());

												if (processWorkflowList.size() > 0) {

													for (ProcessWorkflow processWorkflow : processWorkflowList) {

														List<WorkflowOutput> workflowOutputList = new ArrayList<WorkflowOutput>();

														try {

															workflowOutputList = WorkflowOutputLocalServiceUtil
																	.getByProcessWF(processWorkflow
																			.getProcessWorkflowId());
														} catch (Exception e) {

														}

														if (workflowOutputList
																.size() > 0) {

															for (WorkflowOutput workflowOutput : workflowOutputList) {

																WorkflowOutputLocalServiceUtil
																		.deleteWorkflowOutput(workflowOutput
																				.getWorkflowOutputId());
															}
														}
														ProcessWorkflowLocalServiceUtil
																.deleteProcessWorkflow(processWorkflow
																		.getProcessWorkflowId());
													}
												}

												// /////////////////////////////

												List<ProcessStep> processStepList = new ArrayList<ProcessStep>();

												try {

													processStepList = ProcessStepLocalServiceUtil
															.getStepByProcess(serviceProcess
																	.getServiceProcessId());
												} catch (Exception e) {

												}

												if (processStepList.size() > 0) {

													for (ProcessStep processStep : processStepList) {

														List<StepAllowance> stepAllowanceList = new ArrayList<StepAllowance>();

														try {

															stepAllowanceList = StepAllowanceLocalServiceUtil
																	.getByProcessStep(processStep
																			.getProcessStepId());
														} catch (Exception e) {

														}

														if (stepAllowanceList
																.size() > 0) {

															for (StepAllowance stepAllowance : stepAllowanceList) {
																StepAllowanceLocalServiceUtil
																		.deleteStepAllowance(stepAllowance);
															}

														}
														List<ProcessStepDossierPart> processStepDossierPartList = new ArrayList<ProcessStepDossierPart>();

														try {

															processStepDossierPartList = ProcessStepDossierPartLocalServiceUtil
																	.getByStep(processStep
																			.getProcessStepId());
														} catch (Exception e) {

														}

														if (processStepDossierPartList
																.size() > 0) {

															for (ProcessStepDossierPart processStepDossierPart : processStepDossierPartList) {

																ProcessStepDossierPartLocalServiceUtil
																		.deleteProcessStepDossierPart(processStepDossierPart);
															}

														}

														ProcessStepLocalServiceUtil
																.deleteProcessStep(processStep
																		.getProcessStepId());
													}

												}

												ServiceInfoProcessLocalServiceUtil
														.deleteServiceInfoProcess(
																serviceConfig
																		.getServiceProcessId(),
																serviceConfig
																		.getServiceInfoId());

												ServiceProcessLocalServiceUtil
														.deleteServiceProcess(serviceProcess);

											}

											ServiceConfigLocalServiceUtil
													.deleteServiceConfig(serviceConfig);
										}
									}

									removeDossierContent(themeDisplay,
											serviceInfo.getServiceinfoId());

									ServiceInfoLocalServiceUtil
											.deleteServiceInfo(serviceInfo);
								}
							}
							DictItemLocalServiceUtil
									.deleteDictItem(dictItem_svDomain);
						}

					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	public void removeDossierContent(ThemeDisplay themeDisplay,
			long serviceInfoId) {

		try {

			if (serviceInfoId > 0) {
				List<Dossier> dossierList = new ArrayList<Dossier>();

				dossierList = DossierLocalServiceUtil
						.getDossiersByServiceInfo(serviceInfoId);

				if (dossierList.size() > 0) {

					for (Dossier dossier : dossierList) {

						ProcessOrder processOrder = null;

						try {
							processOrder = ProcessOrderLocalServiceUtil
									.findBy_Dossier(dossier.getDossierId());
						} catch (NoSuchProcessOrderException e) {

						}

						if (Validator.isNotNull(processOrder)) {

							List<ActionHistory> actionHistoryList = new ArrayList<ActionHistory>();

							actionHistoryList = ActionHistoryLocalServiceUtil
									.getActionHistoriesByG_PORD(
											themeDisplay.getScopeGroupId(),
											processOrder.getProcessOrderId(),
											QueryUtil.ALL_POS,
											QueryUtil.ALL_POS);

							if (actionHistoryList.size() > 0) {

								for (ActionHistory actionHistory : actionHistoryList) {

									ActionHistoryLocalServiceUtil
											.deleteActionHistory(actionHistory);
								}
							}
							ProcessOrderLocalServiceUtil
									.deleteProcessOrder(processOrder);
						}
						// /////////////////////////////////////////////////////////////
						List<DossierFile> dossierFileList = new ArrayList<DossierFile>();

						dossierFileList = DossierFileLocalServiceUtil
								.getDossierFileByDossierId(dossier
										.getDossierId());

						if (dossierFileList.size() > 0) {

							for (DossierFile dossierFile : dossierFileList) {

								DossierFileLocalServiceUtil
										.deleteDossierFile(dossierFile);
							}

						}
						// /////////////////////////////////////////////////////////////
						List<DossierLog> dossierLogList = new ArrayList<DossierLog>();

						dossierLogList = DossierLogLocalServiceUtil
								.getDossierLogByDossierId(dossier
										.getDossierId());

						if (dossierLogList.size() > 0) {

							for (DossierLog dossierLog : dossierLogList) {

								List<DossierFileLog> dossierFileLogList = new ArrayList<DossierFileLog>();

								try {
									dossierFileLogList = DossierFileLogLocalServiceUtil
											.getFileLogs(dossierLog
													.getDossierLogId(), dossier
													.getDossierId());
								} catch (Exception e) {

								}

								if (dossierFileLogList.size() > 0) {

									for (DossierFileLog dossierFileLog : dossierFileLogList) {

										DossierFileLogLocalServiceUtil
												.deleteDossierFileLog(dossierFileLog);
									}
								}

								DossierLogLocalServiceUtil
										.deleteDossierLog(dossierLog);
							}
						}
						// /////////////////////////////////////////////////////////////
						List<PaymentFile> paymentFileList = new ArrayList<PaymentFile>();

						paymentFileList = PaymentFileLocalServiceUtil
								.getPaymentFileByD_(dossier.getDossierId());

						if (paymentFileList.size() > 0) {

							for (PaymentFile paymentFile : paymentFileList) {

								PaymentFileLocalServiceUtil
										.deletePaymentFile(paymentFile);
							}
						}
						DossierLocalServiceUtil.deleteDossier(dossier);
					}

				}
			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

	// //////////////////////////////////////////////////////
	public void removeUser(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long userId = 0;

			List<Citizen> citizenList = CitizenLocalServiceUtil.getCitizens(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (citizenList.size() > 0) {

				for (Citizen citizen : citizenList) {
					userId = 0;

					userId = citizen.getMappingUserId();

					if (userId > 0) {

						try {

							UserLocalServiceUtil.deleteUser(userId);
						} catch (Exception e) {

						}

					}

					try {

						CitizenLocalServiceUtil.deleteCitizen(citizen);
					} catch (Exception e) {

					}

				}
			}

			// ////////////////////////////
			List<Business> businessList = BusinessLocalServiceUtil
					.getBusinesses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (businessList.size() > 0) {

				for (Business business : businessList) {
					userId = 0;

					userId = business.getMappingUserId();

					if (userId > 0) {

						try {

							UserLocalServiceUtil.deleteUser(userId);
						} catch (Exception e) {

						}
					}

					try {
						BusinessLocalServiceUtil.deleteBusiness(business);
					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {
			_log.equals(e);
		}
	}

	// /////////////////////////////////////////////////////
	public void fetchDossiers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierUtils utils = new DossierUtils();
		utils.fetchDossiers(actionRequest, actionResponse);
		;
	}

	public void addDossiers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierUtils utils = new DossierUtils();
		utils.addDossiers(actionRequest, actionResponse);
		;
	}

	// /////////////////////////////////////////////////////

	public void fetchDossierFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierFileUtils utils = new DossierFileUtils();
		utils.fetchDossierFile(actionRequest, actionResponse);
		;
	}

	public void addDossierFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierFileUtils utils = new DossierFileUtils();
		utils.addDossierFile(actionRequest, actionResponse);
		;
	}

	// /////////////////////////////////////////////////////

	public void fetchDossierLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierLogUtils utils = new DossierLogUtils();
		utils.fetchDossierLog(actionRequest, actionResponse);
		;
	}

	public void addDossierLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierLogUtils utils = new DossierLogUtils();
		utils.addDossierLog(actionRequest, actionResponse);
		;
	}

	// /////////////////////////////////////////////////////

	public void fetchDossierFileLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierFileLogUtils utils = new DossierFileLogUtils();
		utils.fetchDossierFileLog(actionRequest, actionResponse);
		;
	}

	public void addDossierFileLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		DossierFileLogUtils utils = new DossierFileLogUtils();
		utils.addDossierFileLog(actionRequest, actionResponse);
		;
	}

	// /////////////////////////////////////////////////////

	public void fetchProcessOrders(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessOrderUtils utils = new ProcessOrderUtils();
		utils.fetchProcessOrders2(actionRequest, actionResponse);

	}

	public void addProcessOrders(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ProcessOrderUtils utils = new ProcessOrderUtils();
		utils.addProcessOrders(actionRequest, actionResponse);

	}

	// /////////////////////////////////////////////////////

	public void fetchPaymentFiles(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		PaymentFileUtils utils = new PaymentFileUtils();
		utils.fetchPaymentFiles(actionRequest, actionResponse);

	}

	public void addPaymentFiles(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		PaymentFileUtils utils = new PaymentFileUtils();
		utils.addPaymentFiles(actionRequest, actionResponse);

	}

	// /////////////////////////////////////////////////////

	public void fetchActionhistory(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ActionHistoryUtils utils = new ActionHistoryUtils();
		utils.fetchActionhistory(actionRequest, actionResponse);

	}

	public void addActionhistory(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ActionHistoryUtils utils = new ActionHistoryUtils();
		utils.addActionHistory(actionRequest, actionResponse);

	}

	// /////////////////////////////////////////////////////
	public void fetchAllDossierContent(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		// fetchDossiers(actionRequest, actionResponse);
		// fetchDossierFile(actionRequest, actionResponse);
		// fetchDossierLog(actionRequest, actionResponse);
		// fetchDossierFileLog(actionRequest, actionResponse);
		// fetchProcessOrders(actionRequest, actionResponse);
		// fetchPaymentFiles(actionRequest, actionResponse);
		// fetchActionhistory(actionRequest, actionResponse);

		fetchProcessOrders(actionRequest, actionResponse);

	}

	public void addAllDossierContent(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		CommonUtils commonUtils = new CommonUtils();
		DomainConfigExt domainConfigExt = commonUtils
				.getDomainConfigData(themeDisplay.getCompanyId());

		if (Validator.isNotNull(domainConfigExt)
				&& Validator.isNotNull(domainConfigExt.getUserName())
				&& Validator.isNotNull(domainConfigExt.getPassword())
				&& Validator.isNotNull(domainConfigExt.getTargetDomain())) {

			addDossiers(actionRequest, actionResponse);
			addDossierFile(actionRequest, actionResponse);
			addDossierLog(actionRequest, actionResponse);
			addDossierFileLog(actionRequest, actionResponse);
			addProcessOrders(actionRequest, actionResponse);
			addPaymentFiles(actionRequest, actionResponse);
			addActionhistory(actionRequest, actionResponse);
		} else {
			SessionErrors.add(actionRequest, "info-required");
		}

	}

	public void removeExpando(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		_log.info("===Removing Expando data Start===");
		ExpandoExtLocalServiceUtil.deleteAllExpando();
		_log.info("===Removing Expando data Finish===");

	}

	public void setUserGroupNew(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		CommonUtils commonUtils = new CommonUtils();
		commonUtils.setUserGroup(actionRequest, actionResponse);

	}

	public void testServiceProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceProcess serviceProcess = null;

		try {

			serviceProcess = ServiceProcessLocalServiceUtil.getServiceProcess(
					themeDisplay.getScopeGroupId(), "dddfghjgh");
		} catch (NoSuchServiceProcessException e) {

		}

		_log.info("==serviceProcess:" + serviceProcess);

	}

	private static Log _log = LogFactoryUtil
			.getLog(DataMgtConvertPortlet.class);
}
