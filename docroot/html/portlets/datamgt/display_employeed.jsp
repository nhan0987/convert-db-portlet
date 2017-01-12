

<%@page
	import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page
	import="com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoRow"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portlet.expando.NoSuchTableException"%>
<%@page import="org.opencps.datamgt.model.DictItem"%>
<%@page import="org.opencps.datamgt.service.DictItemLocalServiceUtil"%>
<%@page
	import="com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoTable"%>
<%@ include file="/html/init.jsp"%>

<h1>Liferay Expando API Display Employees</h1>

<table style="width: 100%" border="1">
	<tr>
		<th>dictCollectionId</th>
		<th>itemCode</th>
		<th>itemName</th>
		<th>itemDescription</th>
		<th>parentItemId</th>
		<th>treeIndex</th>
		<th>issueStatus</th>
	</tr>
	<%
ExpandoTable expandoTable=null;
String message=null;
try {
 expandoTable = ExpandoTableLocalServiceUtil.getTable(
		themeDisplay.getCompanyId(), DictItem.class.getName(),DataMamagementPortlet.expandoTableName);
}
catch (NoSuchTableException nste) {
	message="Table  not existed to show the data. please add data first and comeback to to see the data";
}

if(expandoTable!=null){
	List<ExpandoRow> rows = ExpandoRowLocalServiceUtil.getRows(
			themeDisplay.getCompanyId(), DictItem.class.getName(),
			DataMamagementPortlet.expandoTableName, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	
	System.out.println("======themeDisplay.getCompanyId():"+themeDisplay.getCompanyId());
	System.out.println("======DictItem.class.getName():"+DictItem.class.getName());
	
	int i=0;

	for (ExpandoRow row : rows) {
		
%>
		<tr>
<%
		
		int k=0;
		for(String columnName:DataMamagementPortlet.columnNames){
		
		String data = ExpandoValueLocalServiceUtil.getData(
				themeDisplay.getCompanyId(),
				DictItem.class.getName(), DataMamagementPortlet.expandoTableName,
				DataMamagementPortlet.columnNames[k], row.getClassPK(), StringPool.BLANK);
		System.out.println("======data:"+data);
		System.out.println("======DataMamagementPortlet.columnNames["+i+"]:"+DataMamagementPortlet.columnNames[i]);
		System.out.println("======row.getClassPK():"+row.getClassPK());

%>
		<td><%=data %></td>
<%
		k++;
		}
%>
		</tr>
<%
	i++;
	}
}
%>
</table>
<h1><%=message!=null?message:StringPool.BLANK%></h1>