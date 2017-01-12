
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ServiceInfoProcess;
import org.opencps.processmgt.service.ServiceInfoProcessLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class ServiceInfoProcessUtils {

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoProcessUtils.class);

	public void fetchServiceInfoProcess(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ServiceInfoProcess> List =
				ServiceInfoProcessLocalServiceUtil.getServiceInfoProcesses(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_ServiceInfoProcess,
					WebKeys.SERVICE_INFO_PROCESS, WebKeys.ServiceInfoProcessColumns);

			for (ServiceInfoProcess object : List) {

				long serviceInfoProcessId =
					CounterLocalServiceUtil.increment(WebKeys.SERVICE_INFO_PROCESS);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), serviceInfoProcessId);

				JSONObject ColumnNames = WebKeys.getServiceInfoProcessColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_INFO_PROCESS,
					WebKeys.EXTableName_ServiceInfoProcess,
					ColumnNames.getString("serviceProcessId"), serviceInfoProcessId,
					String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_INFO_PROCESS,
					WebKeys.EXTableName_ServiceInfoProcess, ColumnNames.getString("serviceInfoId"),
					serviceInfoProcessId, String.valueOf(object.getServiceinfoId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addServiceInfoProcess(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(
				WebKeys.EXTableName_ServiceInfoProcess, WebKeys.SERVICE_INFO_PROCESS);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_PROCESS,
						WebKeys.EXTableName_ServiceInfoProcess);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_PROCESS,
					WebKeys.EXTableName_ServiceInfoProcess, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getServiceInfoProcessColumnNames();

				JSONObject serviceProcessColNames = WebKeys.getServiceProcessColumnNames();

				JSONObject serviceConfigColNames = WebKeys.getServiceConfigColumnNames();

				String serviceProcessId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_PROCESS,
						WebKeys.EXTableName_ServiceInfoProcess,
						columnNames.getString("serviceProcessId"), row.getClassPK(),
						StringPool.BLANK);

				String serviceConfigId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO_PROCESS,
						WebKeys.EXTableName_ServiceInfoProcess,
						columnNames.getString("serviceInfoId"), row.getClassPK(), StringPool.BLANK);

				String serviceConfigIdNew = StringPool.BLANK;

				if (Validator.isNotNull(serviceConfigId)) {

					serviceConfigIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
							WebKeys.EXTableName_ServiceConfig,
							serviceConfigColNames.getString("serviceConfigNew"),
							Long.valueOf(serviceConfigId), StringPool.BLANK);
				}

				String serviceProcessIdNew = StringPool.BLANK;

				if (Validator.isNotNull(serviceProcessId)) {

					serviceProcessIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
							WebKeys.EXTableName_ServiceProcess,
							serviceProcessColNames.getString("serviceProcessIdNew"),
							Long.valueOf(serviceProcessId), StringPool.BLANK);
				}

				if (serviceProcessIdNew.trim().length() > 0 && serviceConfigIdNew.trim().length() > 0) {

					ServiceInfoProcessLocalServiceUtil.addServiceInfoProcess(
						Long.valueOf(serviceProcessIdNew), Long.valueOf(serviceConfigIdNew));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
