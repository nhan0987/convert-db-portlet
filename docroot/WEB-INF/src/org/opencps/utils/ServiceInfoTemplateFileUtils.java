
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.servicemgt.model.ServiceFileTemplate;
import org.opencps.servicemgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.servicemgt.service.persistence.ServiceFileTemplatePK;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class ServiceInfoTemplateFileUtils {

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoTemplateFileUtils.class);

	public void fetchServiceTemplateFile(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ServiceFileTemplate> list =
				ServiceFileTemplateLocalServiceUtil.getServiceFileTemplates(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.expandoTableName_ServiceInfo_TemplateFile,
					WebKeys.SERVICE_INFO_TEMPLATE_FILE,
					WebKeys.ServiceInfo_TemplateFile_ColumnNames);

			for (ServiceFileTemplate object : list) {

				long serviceFileTemplateId =
					CounterLocalServiceUtil.increment(WebKeys.SERVICE_INFO_TEMPLATE_FILE);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), serviceFileTemplateId);

				JSONObject columnNames = WebKeys.getServiceInfoTemplateFileColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_INFO_TEMPLATE_FILE,
					WebKeys.expandoTableName_ServiceInfo_TemplateFile,
					columnNames.getString("serviceInfoId"), serviceFileTemplateId,
					String.valueOf(object.getServiceinfoId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_INFO_TEMPLATE_FILE,
					WebKeys.expandoTableName_ServiceInfo_TemplateFile,
					columnNames.getString("templateFileId"), serviceFileTemplateId,
					String.valueOf(object.getTemplatefileId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addServiceTemplateFile(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(
				WebKeys.expandoTableName_ServiceInfo_TemplateFile,
				WebKeys.SERVICE_INFO_TEMPLATE_FILE);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_TEMPLATE_FILE,
						WebKeys.expandoTableName_ServiceInfo_TemplateFile);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			ServiceFileTemplate serviceFileTemplate = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_TEMPLATE_FILE,
					WebKeys.expandoTableName_ServiceInfo_TemplateFile, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getServiceInfoTemplateFileColumnNames();

				JSONObject serviceInfoColumnNames = WebKeys.getServiceInfoColumnNames();

				JSONObject templateFileColumnNames = WebKeys.getTemplateFileColumnNames();

				String serviceInfoId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_TEMPLATE_FILE,
						WebKeys.expandoTableName_ServiceInfo_TemplateFile,
						columnNames.getString("serviceInfoId"), row.getClassPK(), StringPool.BLANK);

				String templateFileId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_TEMPLATE_FILE,
						WebKeys.expandoTableName_ServiceInfo_TemplateFile,
						columnNames.getString("templateFileId"), row.getClassPK(), StringPool.BLANK);

				String serviceInfoIdNew =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
						WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceInfoIdNew"),
						Long.valueOf(serviceInfoId), StringPool.BLANK);

				String templateFileIdNew =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						templateFileColumnNames.getString("templateFileIdNew"),
						Long.valueOf(templateFileId), StringPool.BLANK);

				_log.info("=====serviceInfoIdNew:" + serviceInfoIdNew);

				_log.info("=====templateFileIdNew:" + templateFileIdNew);

				if (serviceInfoIdNew.trim().length() > 0 && templateFileIdNew.trim().length() > 0) {
					// ServiceFileTemplatePK serviceFileTemplatePK =
					// new ServiceFileTemplatePK(
					// Long.valueOf(serviceInfoIdNew),
					// Long.valueOf(templateFileIdNew));

					ServiceFileTemplateLocalServiceUtil.addServiceFile(
						Long.valueOf(serviceInfoIdNew), Long.valueOf(templateFileIdNew));

					// ServiceFileTemplateLocalServiceUtil.createServiceFileTemplate(serviceFileTemplatePK);

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
