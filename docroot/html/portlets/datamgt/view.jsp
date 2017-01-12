<%@page import="java.util.ArrayList"%>
<%@ include file="/html/init.jsp"%>

<liferay-ui:error key="info-required" message="UserName,Password,DomainTarget Required!"/>

<%
// 	String[] names = new String[] {
// 		"GetConfig", "AddConfig", "GetDossier","AddDossier","Test"
// 	};

	List<String> urls = new ArrayList<String>();
%>

<liferay-ui:tabs
	names="GetConfig,AddConfig,TestConfig,TestDossier,RemoveConfig"
	refresh="false" 
	tabsValues="GetConfig,AddConfig,TestConfig,TestDossier,RemoveConfig"
>
	<liferay-ui:section>
        <%@ include file="view_fetchconfig.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_addconfig.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_testconfig.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_testdossier.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_removeconfig.jsp"%>
    </liferay-ui:section>
</liferay-ui:tabs>