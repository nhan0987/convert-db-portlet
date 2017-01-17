
<%@page
	import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page
	import="com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoRow"%>
<%@page import="java.util.List"%>
<%@page import="org.opencps.utils.WebKeys"%>
<%@ include file="/html/init.jsp"%>




<portlet:actionURL var="fetchDossiersURL" name="fetchDossiers">
</portlet:actionURL>
<portlet:actionURL var="addDossiersURL" name="addDossiers">
</portlet:actionURL>
<portlet:actionURL var="fetchDossierFileURL" name="fetchDossierFile">
</portlet:actionURL>
<portlet:actionURL var="addDossierFileURL" name="addDossierFile">
</portlet:actionURL>
<portlet:actionURL var="fetchDossierLogURL" name="fetchDossierLog">
</portlet:actionURL>
<portlet:actionURL var="addDossierLogURL" name="addDossierLog">
</portlet:actionURL>
<portlet:actionURL var="fetchActionhistoryURL" name="fetchActionhistory">
</portlet:actionURL>
<portlet:actionURL var="addActionhistoryURL" name="addActionhistory">
</portlet:actionURL>




<portlet:actionURL var="fetchDossierFileLogURL"
	name="fetchDossierFileLog">
</portlet:actionURL>
<portlet:actionURL var="addDossierFileLogURL" name="addDossierFileLog">
</portlet:actionURL>
<portlet:actionURL var="fetchProcessOrdersURL" name="fetchProcessOrders">
</portlet:actionURL>
<portlet:actionURL var="addProcessOrdersURL" name="addProcessOrders">
</portlet:actionURL>
<portlet:actionURL var="fetchPaymentFilesURL" name="fetchPaymentFiles">
</portlet:actionURL>
<portlet:actionURL var="addPaymentFilesURL" name="addPaymentFiles">
</portlet:actionURL>

<portlet:actionURL var="fetchAllDossierContentURL" name="fetchAllDossierContent">
</portlet:actionURL>
<portlet:actionURL var="addAllDossierContentURL" name="addAllDossierContent">
</portlet:actionURL>

<portlet:actionURL var="setUserGroupNewURL" name="setUserGroupNew">
</portlet:actionURL>




<aui:column columnWidth="50">

	<aui:form action="<%=fetchDossiersURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossiers" />
	</aui:form>

	<aui:form action="<%=fetchDossierFileURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossierFile" />
	</aui:form>


	<aui:form action="<%=fetchDossierLogURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossierLog" />
	</aui:form>

	<!-- ---------------------------- -->

	<aui:form action="<%=fetchDossierFileLogURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchDossierFileLog" />
	</aui:form>



	<aui:form action="<%=fetchProcessOrdersURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchProcessOrders" />
	</aui:form>

	<aui:form action="<%=fetchPaymentFilesURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchPaymentFiles" />
	</aui:form>
	
	<aui:form action="<%=fetchActionhistoryURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchActionhistory" />
	</aui:form>
	
	<aui:form action="<%=fetchAllDossierContentURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="fetchAllDossierContent" />
	</aui:form>

</aui:column>

<aui:column columnWidth="50">

	<aui:form action="<%=addDossiersURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossiers" />
	</aui:form>

	<aui:form action="<%=addDossierFileURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierFile" />
	</aui:form>


	<aui:form action="<%=addDossierLogURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierLog" />
	</aui:form>

	<!-- ---------------------------- -->

	<aui:form action="<%=addDossierFileLogURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addDossierFileLog" />
	</aui:form>

	<aui:form action="<%=addProcessOrdersURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addProcessOrders" />
	</aui:form>


	<aui:form action="<%=addPaymentFilesURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addPaymentFiles" />
	</aui:form>
	
	<aui:form action="<%=addActionhistoryURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addActionhistory" />
	</aui:form>
	
	<aui:form action="<%=addAllDossierContentURL.toString()%>" method="post"
		name="name">
		<aui:input name="hidden" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addAllDossierContent" />
	</aui:form>
	
<%-- 	<aui:form action="<%=setUserGroupNewURL.toString()%>" method="post" --%>
<%-- 		name="name"> --%>
<%-- 		<aui:input name="hidden" type="text" value="value" title="Input" /> --%>
<%-- 		<aui:button type="submit" name="name" value="setUserGroupNew" /> --%>
<%-- 	</aui:form> --%>

</aui:column>

