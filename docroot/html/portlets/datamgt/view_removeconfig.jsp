
<%@page
	import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page
	import="com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoRow"%>
<%@page import="java.util.List"%>
<%@page import="org.opencps.utils.WebKeys"%>
<%@ include file="/html/init.jsp"%>




<portlet:actionURL var="removeServiceURL" name="removeService">
</portlet:actionURL>

<portlet:actionURL var="removeUserURL" name="removeUser">
</portlet:actionURL>

<portlet:actionURL var="testServiceProcessURL" name="testServiceProcess">
</portlet:actionURL>


<aui:column columnWidth="50">

	<aui:form action="<%=removeServiceURL.toString()%>" method="post"
		name="name">
		
		<aui:input name="serviceDomain" type="text" title="serviceDomain" placeholder="LV-DS|BGTVT-CDT|TCT|DTND.01|DTND.02"  />
		
		<aui:button type="submit" name="name" value="removeConfig" />
	</aui:form>

</aui:column>

<aui:column columnWidth="50">

	<aui:form action="<%=removeUserURL.toString()%>" method="post"
		name="name">
		
		<aui:input name="hidden" type="text" value="value" title="removeUser" />
		
		<aui:button type="submit" name="name" value="removeUser" />
	</aui:form>
	
	<aui:form action="<%=testServiceProcessURL.toString()%>" method="post"
		name="name">
		
		<aui:input name="hidden" type="text" value="value" title="testServiceProcess" />
		
		<aui:button type="submit" name="name" value="testServiceProcess" />
	</aui:form>

</aui:column>

