
<%@page import="org.opencps.utils.WebKeys"%>
<%@page import="org.opencps.expando.model.DomainConfigExt"%>
<%@page import="org.opencps.utils.CommonUtils"%>
<%@ include file="/html/init.jsp"%>


<portlet:actionURL var="updateDomainConfigExtUrl" name="updateDomainConfigExt">
</portlet:actionURL>

<portlet:actionURL var="addAllUrl" name="addAll">
</portlet:actionURL>

<%



CommonUtils commonUtils = new CommonUtils();

commonUtils.changeClassId(WebKeys.EXTableName_DOMAIN_CONFIG,WebKeys.DOMAIN_CONFIG);
DomainConfigExt domainConfigExt = commonUtils.getDomainConfigData(themeDisplay.getCompanyId());

%>


<aui:column columnWidth="50">

	<aui:form action="<%=updateDomainConfigExtUrl.toString()%>" method="post">
		<aui:input name="UserName" type="text" 
			value="<%=Validator.isNotNull(domainConfigExt)?domainConfigExt.getUserName():StringPool.BLANK %>" 
			title="Ten"/>
		<aui:input name="Password" type="text" 
			value="<%=Validator.isNotNull(domainConfigExt)?domainConfigExt.getPassword():StringPool.BLANK %>" 
			title="Password"/>
		<aui:input name="Domain" type="text" 
			value="<%=Validator.isNotNull(domainConfigExt)?domainConfigExt.getTargetDomain():StringPool.BLANK %>" 
			title="Domain" placeholder="http://qa.opencps.vn"/>
		<aui:button type="submit" name="Submit" />
	</aui:form>

</aui:column>
<%if(Validator.isNotNull(domainConfigExt)&& Validator.isNotNull(domainConfigExt.getUserName())
				&& Validator.isNotNull(domainConfigExt.getPassword()) &&Validator.isNotNull(domainConfigExt.getTargetDomain())) {%>
				
<portlet:actionURL var="addDictItemCollectionUrl" name="addDictItemCollection">
</portlet:actionURL>

<portlet:actionURL var="addNextUrl" name="addNext">
</portlet:actionURL>
	
<aui:column columnWidth="50">
	
	<aui:form action="<%=addAllUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addAll" />
	</aui:form>
	
	<aui:form action="<%=addDictItemCollectionUrl.toString()%>" method="post"
		name="name">
		<aui:input name="collectionCode" type="text"  title="DictItemCollection" placeholder="DM_DAI_DIEN|DM_CANG_BEN|DM_PHUONG_TIEN|DM_PHUONG_TIEN_TAU_BIEN|ADMINISTRATIVE_REGION" />
		<aui:button type="submit" name="name" value="addDictItemCollection" />
	</aui:form>
	
	<aui:form action="<%=addNextUrl.toString()%>" method="post"
		name="name">
		<aui:input name="name" type="text" value="value" title="Input" />
		<aui:button type="submit" name="name" value="addNext" />
	</aui:form>
	
</aui:column>
<%} %>
