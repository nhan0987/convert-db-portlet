<%@page import="javax.portlet.PortletURL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ include file="/html/init.jsp"%>

<%
	String[] names = new String[] {
		"tab1", "tab2", "tab3"
	};

List<String> urls = new ArrayList<String>();

PortletURL viewServiceURL = renderResponse.createRenderURL();
viewServiceURL.setParameter("mvcPath", templatePath + "view_bak1.jsp");
urls.add(viewServiceURL.toString());

PortletURL viewService1URL = renderResponse.createRenderURL();
viewServiceURL.setParameter("mvcPath", templatePath + "view_bak1.jsp");
urls.add(viewService1URL.toString());

PortletURL viewService2URL = renderResponse.createRenderURL();
viewServiceURL.setParameter("mvcPath", templatePath + "view_bak1.jsp");
urls.add(viewService2URL.toString());
%>

<liferay-ui:tabs
	names="tab1,tab2,tab3"
	refresh="false" 
	tabsValues="tab1,tab2,tab3"
>
	<liferay-ui:section>
        <%@ include file="view_bak1.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_bak3.jsp"%>
    </liferay-ui:section>
    <liferay-ui:section>
        <%@ include file="view_bak4.jsp"%>
    </liferay-ui:section>
</liferay-ui:tabs>