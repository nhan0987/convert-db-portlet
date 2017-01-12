
<%@page
	import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page
	import="com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoRow"%>
<%@page import="java.util.List"%>
<%@page import="org.opencps.utils.WebKeys"%>
<%@ include file="/html/init.jsp"%>




<portlet:actionURL var="fetchDictCollectionUrl"
	name="fetchDictCollection">
</portlet:actionURL>
<portlet:actionURL var="addDictCollectionUrl" name="addDictCollection">
</portlet:actionURL>
<portlet:actionURL var="fetchDictItemUrl" name="fetchDictItem">
</portlet:actionURL>
<portlet:actionURL var="addDictItemUrl" name="addDictItem">
</portlet:actionURL>
<portlet:actionURL var="addTreeIndexUrl" name="addTreeIndex">
</portlet:actionURL>
<portlet:actionURL var="fetchServiceInfoUrl" name="fetchServiceInfo">
</portlet:actionURL>
<portlet:actionURL var="addServiceInfoUrl" name="addServiceInfo">
</portlet:actionURL>
<portlet:actionURL var="fetchTemplateFileUrl" name="fetchTemplateFile">
</portlet:actionURL>
<portlet:actionURL var="addTemplateFileUrl" name="addTemplateFile">
</portlet:actionURL>
<portlet:actionURL var="fetchServiceTemplateFileUrl"
	name="fetchServiceTemplateFile">
</portlet:actionURL>
<portlet:actionURL var="addServiceTemplateFileUrl"
	name="addServiceTemplateFile">
</portlet:actionURL>
<portlet:actionURL var="fetchDossierTemplateUrl"
	name="fetchDossierTemplate">
</portlet:actionURL>
<portlet:actionURL var="addDossierTemplateUrl" name="addDossierTemplate">
</portlet:actionURL>
<portlet:actionURL var="fetchDossierPartUrl" name="fetchDossierPart">
</portlet:actionURL>
<portlet:actionURL var="addDossierPartUrl" name="addDossierPart">
</portlet:actionURL>
<portlet:actionURL var="addDossierPartTreeIndexUrl"
	name="addDossierPartTreeIndex">
</portlet:actionURL>
<portlet:actionURL var="fetchServiceProcessUrl"
	name="fetchServiceProcess">
</portlet:actionURL>
<portlet:actionURL var="addServiceProcessUrl" name="addServiceProcess">
</portlet:actionURL>
<portlet:actionURL var="fetchServiceInfoProcessUrl"
	name="fetchServiceInfoProcess">
</portlet:actionURL>
<portlet:actionURL var="addServiceInfoProcessUrl"
	name="addServiceInfoProcess">
</portlet:actionURL>
<portlet:actionURL var="fetchContactsUrl" name="fetchContacts">
</portlet:actionURL>
<portlet:actionURL var="addContactUrl" name="addContact">
</portlet:actionURL>
<portlet:actionURL var="fetchUsersUrl" name="fetchUsers">
</portlet:actionURL>
<portlet:actionURL var="addUsersUrl" name="addUsers">
</portlet:actionURL>
<portlet:actionURL var="fetchOrganizationUrl" name="fetchOrganization">
</portlet:actionURL>
<portlet:actionURL var="addOrganizationUrl" name="addOrganization1">
</portlet:actionURL>
<portlet:actionURL var="addTreePathOrgUrl" name="addTreePathOrg">
</portlet:actionURL>
<portlet:actionURL var="fetchServiceConfigUrl" name="fetchServiceConfig">
</portlet:actionURL>
<portlet:actionURL var="addServiceConfigUrl" name="addServiceConfig">
</portlet:actionURL>
<portlet:actionURL var="fetchProcessStepUrl" name="fetchProcessStep">
</portlet:actionURL>
<portlet:actionURL var="addProcessStepUrl" name="addProcessStep">
</portlet:actionURL>
<portlet:actionURL var="fetchProcessStepDossierPartUrl"
	name="fetchProcessStepDossierPart">
</portlet:actionURL>
<portlet:actionURL var="addProcessStepDossierPartUrl"
	name="addProcessStepDossierPart">
</portlet:actionURL>
<portlet:actionURL var="fetchWorkflowUrl" name="fetchWorkflow">
</portlet:actionURL>
<portlet:actionURL var="addWorkflowUrl" name="addWorkflow">
</portlet:actionURL>
<portlet:actionURL var="fetchWorkflowOutputUrl"
	name="fetchWorkflowOutput">
</portlet:actionURL>
<portlet:actionURL var="addWorkflowOutputUrl" name="addWorkflowOutput">
</portlet:actionURL>
<portlet:actionURL var="fetchRoleUrl" name="fetchRole">
</portlet:actionURL>
<portlet:actionURL var="addRoleUrl" name="addRole">
</portlet:actionURL>
<portlet:actionURL var="fetchUsersOrgsUrl" name="fetchUsersOrgs">
</portlet:actionURL>
<portlet:actionURL var="addUsersOrgsUrl" name="addUsersOrgs">
</portlet:actionURL>
<portlet:actionURL var="fetchUsersRolesUrl" name="fetchUsersRoles">
</portlet:actionURL>
<portlet:actionURL var="addUsersRolesUrl" name="addUsersRoles">
</portlet:actionURL>
<portlet:actionURL var="fetchStepAllowanceUrl" name="fetchStepAllowance">
</portlet:actionURL>
<portlet:actionURL var="addStepAllowanceUrl" name="addStepAllowance">
</portlet:actionURL>
<portlet:actionURL var="fetchCitizenUrl" name="fetchCitizen">
</portlet:actionURL>
<portlet:actionURL var="addCitizenUrl" name="addCitizen">
</portlet:actionURL>
<portlet:actionURL var="fetchBusinessUrl" name="fetchBusiness">
</portlet:actionURL>
<portlet:actionURL var="addBusinessUrl" name="addBusiness">
</portlet:actionURL>
<portlet:actionURL var="fetchWorkingUnitUrl" name="fetchWorkingUnit">
</portlet:actionURL>
<portlet:actionURL var="addWorkingUnitUrl" name="addWorkingUnit">
</portlet:actionURL>
<portlet:actionURL var="addTreeIndexWorkingUnitUrl"
	name="addTreeIndexWorkingUnit">
</portlet:actionURL>
<portlet:actionURL var="fetchJobPosUrl" name="fetchJobPos">
</portlet:actionURL>
<portlet:actionURL var="addJobPosUrl" name="addJobPos">
</portlet:actionURL>
<portlet:actionURL var="fetchEmployeeUrl" name="fetchEmployee">
</portlet:actionURL>
<portlet:actionURL var="addEmployeeUrl" name="addEmployee">
</portlet:actionURL>
<portlet:actionURL var="fetchWorkJobUrl" name="fetchWorkJob">
</portlet:actionURL>
<portlet:actionURL var="addWorkJobUrl" name="addWorkJob">
</portlet:actionURL>
<portlet:actionURL var="fetchEmployeeJobUrl" name="fetchEmployeeJob">
</portlet:actionURL>
<portlet:actionURL var="addEmployeeJobUrl" name="addEmployeeJob">
</portlet:actionURL>
<portlet:actionURL var="fetchPaymentConfigUrl" name="fetchPaymentConfig">
</portlet:actionURL>
<portlet:actionURL var="addPaymentConfigUrl" name="addPaymentConfig">
</portlet:actionURL>
<portlet:actionURL var="fetchAllUrl" name="fetchAll">
</portlet:actionURL>
<portlet:actionURL var="addAllUrl" name="addAll">
</portlet:actionURL>




<aui:column columnWidth="50">
	<aui:form action="<%=fetchDictCollectionUrl.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="GetDictCollectionData" />
	</aui:form>
	<aui:form action="<%=fetchDictItemUrl.toString()%>" method="post"
		name="name">
		<aui:input name="collectionCode" type="text"  title="collectionCode" placeholder="DM_DAI_DIEN|DM_CANG_BEN|DM_PHUONG_TIEN|DM_PHUONG_TIEN_TAU_BIEN|ADMINISTRATIVE_REGION" />
		<aui:button type="submit" name="name" value="GetDictItemData" />
	</aui:form>
	<aui:form action="<%=fetchServiceInfoUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchServiceInfo" />
	</aui:form>
	<aui:form action="<%=fetchTemplateFileUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchTemplateFile" />
	</aui:form>
	<aui:form action="<%=fetchServiceTemplateFileUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchServiceTemplateFile" />
	</aui:form>
	<aui:form action="<%=fetchDossierTemplateUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossierTemplate" />
	</aui:form>
	<aui:form action="<%=fetchDossierPartUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossierPart" />
	</aui:form>
	<aui:form action="<%=fetchServiceProcessUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchServiceProcess" />
	</aui:form>
	<aui:form action="<%=fetchServiceInfoProcessUrl.toString() %>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchServiceInfoProcess" />
	</aui:form>
	<aui:form action="<%=fetchUsersUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchUsers" />
	</aui:form>
	<aui:form action="<%=fetchOrganizationUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchOrganization" />
	</aui:form>
	<aui:form action="<%=fetchServiceConfigUrl.toString()%>" method="post"
		name="name">
		<aui:input name="govAgencyCode" type="text" value="value" title="govAgencyCode" />
		<aui:button type="submit" name="name" value="fetchServiceConfig" />
	</aui:form>
	<aui:form action="<%=fetchProcessStepUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchProcessStep" />
	</aui:form>
	<aui:form action="<%=fetchProcessStepDossierPartUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name"
			value="fetchProcessStepDossierPart" />
	</aui:form>
	<aui:form action="<%=fetchWorkflowUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchWorkflow" />
	</aui:form>
	<aui:form action="<%=fetchWorkflowOutputUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchWorkflowOutput" />
	</aui:form>
	<aui:form action="<%=fetchRoleUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchRole" />
	</aui:form>
	<aui:form action="<%=fetchUsersOrgsUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchUsersOrgs" />
	</aui:form>
	<aui:form action="<%=fetchUsersRolesUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchUsersRoles" />
	</aui:form>
	<aui:form action="<%=fetchStepAllowanceUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchStepAllowance" />
	</aui:form>
	<aui:form action="<%=fetchCitizenUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchCitizen" />
	</aui:form>
	<aui:form action="<%=fetchBusinessUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchBusiness" />
	</aui:form>
	<aui:form action="<%=fetchWorkingUnitUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchWorkingUnit" />
	</aui:form>
	<aui:form action="<%=fetchJobPosUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchJobPos" />
	</aui:form>
	<aui:form action="<%=fetchEmployeeUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchEmployee" />
	</aui:form>
	<aui:form action="<%=fetchWorkJobUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchWorkJob" />
	</aui:form>
	<aui:form action="<%=fetchEmployeeJobUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchEmployeeJob" />
	</aui:form>
	<aui:form action="<%=fetchPaymentConfigUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchPaymentConfig" />
	</aui:form>
	<aui:form action="<%=fetchAllUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchChain" />
	</aui:form>
</aui:column>

<aui:column columnWidth="50">
	<aui:form action="<%=addDictCollectionUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="AddDictCollectionData" />
	</aui:form>
	<aui:form action="<%=addDictItemUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="collectionCode" value="value" title="DictItem" placeholder="DM_DAI_DIEN|DM_CANG_BEN|DM_PHUONG_TIEN|DM_PHUONG_TIEN_TAU_BIEN|ADMINISTRATIVE_REGION"/>
		<aui:button type="submit" name="name" value="AddDictItemData" />
	</aui:form>

	<aui:form action="<%=addTreeIndexUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addTreeIndex" />
	</aui:form>
	<!-- ---------------------------- -->
	<aui:form action="<%=addUsersUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addUsers" />
	</aui:form>
	<aui:form action="<%=addOrganizationUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addOrganization" />
	</aui:form>
	<aui:form action="<%=addTreePathOrgUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addTreePathOrg" />
	</aui:form>

	<!-- ---------------------------- -->
	<aui:form action="<%=addUsersOrgsUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addUsersOrgs" />
	</aui:form>

	<aui:form action="<%=addRoleUrl.toString()%>" method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addRole" />
	</aui:form>
	<aui:form action="<%=addUsersRolesUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addUsersRoles" />
	</aui:form>
	<!-- ---------------------------- -->

	<aui:form action="<%=addWorkingUnitUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addWorkingUnit" />
	</aui:form>
	<aui:form action="<%=addTreeIndexWorkingUnitUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addTreeIndexWorkingUnit" />
	</aui:form>

	<!-- ---------------------------- -->

	<aui:form action="<%=addCitizenUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addCitizen" />
	</aui:form>
	<aui:form action="<%=addBusinessUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addBusiness" />
	</aui:form>
	<aui:form action="<%=addEmployeeUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addEmployee" />
	</aui:form>

	<!-- ---------------------------- -->
	<aui:form action="<%=addTemplateFileUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addTemplateFile" />
	</aui:form>

	<aui:form action="<%=addServiceInfoUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addServiceInfo" />
	</aui:form>

	<aui:form action="<%=addServiceTemplateFileUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addServiceTemplateFile" />
	</aui:form>
	<!-- ---------------------------- -->

	<aui:form action="<%=addDossierTemplateUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierTemplate" />
	</aui:form>
	<aui:form action="<%=addDossierPartUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierPart" />
	</aui:form>
	<aui:form action="<%=addDossierPartTreeIndexUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierPartTreeIndex" />
	</aui:form>
	<!-- ---------------------------- -->

	<aui:form action="<%=addServiceProcessUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addServiceProcess" />
	</aui:form>



	<!-- ---------------------------- -->
	<aui:form action="<%=addProcessStepUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addProcessStep" />
	</aui:form>

	<aui:form action="<%=addStepAllowanceUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addStepAllowance" />
	</aui:form>

	<aui:form action="<%=addProcessStepDossierPartUrl.toString()%>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name"
			value="addProcessStepDossierPart" />
	</aui:form>
	<!-- ---------------------------- -->

	<aui:form action="<%=addWorkflowUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addWorkflow" />
	</aui:form>
	<aui:form action="<%=addWorkflowOutputUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addWorkflowOutput" />
	</aui:form>
	<!-- ---------------------------- -->
	<aui:form action="<%=addServiceConfigUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addServiceConfig" />
	</aui:form>


	<aui:form action="<%=addServiceInfoProcessUrl.toString() %>"
		method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addServiceInfoProcess" />
	</aui:form>

	<!-- ---------------------------- -->

	<aui:form action="<%=addJobPosUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addJobPos" />
	</aui:form>

	<aui:form action="<%=addWorkJobUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addWorkJob" />
	</aui:form>
	<!-- ---------------------------- -->

	<aui:form action="<%=addEmployeeJobUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addEmployeeJob" />
	</aui:form>
	<aui:form action="<%=addPaymentConfigUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addPaymentConfig" />
	</aui:form>
	<!-- ---------------------------- -->
	<aui:form action="<%=addAllUrl.toString()%>" method="post" name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addAll" />
	</aui:form>

</aui:column>

