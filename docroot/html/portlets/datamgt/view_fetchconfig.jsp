
<%@ include file="/html/init.jsp"%>





<portlet:actionURL var="fetchAllUrl" name="fetchAll">
</portlet:actionURL>

<portlet:actionURL var="removeExpandoURL" name="removeExpando">
</portlet:actionURL>



<aui:column columnWidth="50">
	
	<aui:form action="<%=fetchAllUrl.toString()%>" method="post"
		name="name">
		<aui:input name="collectionCode" type="text" title="collectionCode" placeholder="DM_DAI_DIEN|DM_CANG_BEN|DM_PHUONG_TIEN|DM_PHUONG_TIEN_TAU_BIEN|ADMINISTRATIVE_REGION" />
		<aui:button type="submit" name="name" value="fetchChain" />
	</aui:form>
</aui:column>

<aui:column columnWidth="50">
	
	<aui:form action="<%=removeExpandoURL.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="cleanDataExpando" />
	</aui:form>
</aui:column>



