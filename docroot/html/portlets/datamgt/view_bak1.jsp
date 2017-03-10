<%@page import="org.opencps.servicemgt.service.ServiceInfoLocalServiceUtil"%>
<%@page import="org.opencps.servicemgt.model.ServiceInfo"%>
<%@page import="org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil"%>
<%@page import="org.opencps.dossiermgt.model.ServiceConfig"%>
<%@page import="org.opencps.processmgt.service.ServiceProcessLocalServiceUtil"%>
<%@page import="org.opencps.processmgt.model.ServiceProcess"%>
<%@ include file="/html/init.jsp"%>
<%
	ServiceProcess  serviceProcess = null;
	ServiceConfig serviceConfig = null;
	ServiceInfo serviceInfo = null;

	try{
		serviceProcess = ServiceProcessLocalServiceUtil
		.getServiceProcess(themeDisplay.getScopeGroupId(),
		"123");
		
		serviceConfig =
		ServiceConfigLocalServiceUtil.getServiceConfigByG_S_G(
			themeDisplay.getScopeGroupId(), 1,
			"123");
		
		serviceInfo = ServiceInfoLocalServiceUtil
		.getServiceInfoByServiceNo("123");
	}catch(Exception e){
		
	}

	System.out.println("serviceProcess:"+serviceProcess);
	System.out.println("serviceConfig:"+serviceConfig);
	System.out.println("serviceInfo:"+serviceInfo);
%>