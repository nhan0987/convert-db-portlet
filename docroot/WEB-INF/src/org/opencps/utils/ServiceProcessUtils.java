package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.NoSuchServiceProcessException;
import org.opencps.processmgt.model.ServiceProcess;
import org.opencps.processmgt.service.ServiceProcessLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class ServiceProcessUtils {

	private static Log _log = LogFactoryUtil.getLog(ServiceProcessUtils.class);

	public void fetchServiceProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ServiceProcess> List = ServiceProcessLocalServiceUtil
					.getServiceProcesses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_ServiceProcess,
					WebKeys.SERVICE_PROCESS, WebKeys.ServiceProcessColumns);
			int i = 1;

			for (ServiceProcess object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getServiceProcessId());

				JSONObject ColumnNames = WebKeys.getServiceProcessColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("processNo"),
						object.getServiceProcessId(),
						String.valueOf(object.getProcessNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("processName"),
						object.getServiceProcessId(),
						String.valueOf(object.getProcessName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("description"),
						object.getServiceProcessId(),
						String.valueOf(object.getDescription()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("dossierTemplateId"),
						object.getServiceProcessId(),
						String.valueOf(object.getDossierTemplateId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("serviceProcessIdNew"),
						object.getServiceProcessId(), StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====ServiceProcessId:"
						+ object.getServiceProcessId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addServiceProcess(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_ServiceProcess,
					WebKeys.SERVICE_PROCESS);

			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
					WebKeys.EXTableName_ServiceProcess, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);
				_log.info("*i:"+i);
				_log.info("=====row:"+row.getClassPK());

				JSONObject columnNames = WebKeys.getServiceProcessColumnNames();

				JSONObject dossierTplColNames = WebKeys
						.getDossierTemplateColumnNames();

				String processNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						columnNames.getString("processNo"), row.getClassPK(),
						StringPool.BLANK);

				ServiceProcess serviceProcess = null;

				try {

					serviceProcess = ServiceProcessLocalServiceUtil
							.getServiceProcess(themeDisplay.getScopeGroupId(),
									processNo);
				} catch (NoSuchServiceProcessException e) {

				}

				if (Validator.isNull(serviceProcess)) {

					String processName = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(),
							WebKeys.SERVICE_PROCESS,
							WebKeys.EXTableName_ServiceProcess,
							columnNames.getString("processName"),
							row.getClassPK(), StringPool.BLANK);

					String description = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(),
							WebKeys.SERVICE_PROCESS,
							WebKeys.EXTableName_ServiceProcess,
							columnNames.getString("description"),
							row.getClassPK(), StringPool.BLANK);

					String dossierTemplateId = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_PROCESS,
									WebKeys.EXTableName_ServiceProcess,
									columnNames.getString("dossierTemplateId"),
									row.getClassPK(), StringPool.BLANK);

					String dossierTemplateIdNew = StringPool.BLANK;

					if (Validator.isNotNull(dossierTemplateId)) {
						dossierTemplateIdNew = ExpandoValueLocalServiceUtil
								.getData(
										themeDisplay.getCompanyId(),
										WebKeys.DOSSIER_TEMPLATE,
										WebKeys.EXTableName_DossierTemplate,
										dossierTplColNames
												.getString("dossierTemplateIdNew"),
										Long.valueOf(dossierTemplateId),
										StringPool.BLANK);
					}

					long serviceProcessId = CounterLocalServiceUtil
							.increment(WebKeys.SERVICE_PROCESS);

					serviceProcess = ServiceProcessLocalServiceUtil
							.createServiceProcess(serviceProcessId);

					if (Validator.isNotNull(dossierTemplateIdNew)) {

						serviceProcess.setDossierTemplateId(Long
								.valueOf(dossierTemplateIdNew));
					}
					serviceProcess.setProcessNo(processNo);
					serviceProcess.setProcessName(processName);
					serviceProcess.setDescription(description);

					serviceProcess.setCompanyId(themeDisplay.getCompanyId());
					serviceProcess.setUserId(serviceContext.getUserId());
					serviceProcess.setGroupId(themeDisplay.getScopeGroupId());

					ServiceProcessLocalServiceUtil
							.addServiceProcess(serviceProcess);
				}
				
				_log.info("=====serviceProcess.getServiceProcessId():"+serviceProcess.getServiceProcessId());
				ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						columnNames.getString("serviceProcessIdNew"),
						row.getClassPK(),
						String.valueOf(serviceProcess.getServiceProcessId()));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchServiceProcess2(ThemeDisplay themeDisplay,
			ServiceProcess object) {

		try {

			if (Validator.isNotNull(object)) {

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils.checkTable(companyId,
						WebKeys.EXTableName_ServiceProcess,
						WebKeys.SERVICE_PROCESS, WebKeys.ServiceProcessColumns);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getServiceProcessId());

				JSONObject ColumnNames = WebKeys.getServiceProcessColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("processNo"),
						object.getServiceProcessId(),
						String.valueOf(object.getProcessNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("processName"),
						object.getServiceProcessId(),
						String.valueOf(object.getProcessName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("description"),
						object.getServiceProcessId(),
						String.valueOf(object.getDescription()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("dossierTemplateId"),
						object.getServiceProcessId(),
						String.valueOf(object.getDossierTemplateId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_PROCESS,
						WebKeys.EXTableName_ServiceProcess,
						ColumnNames.getString("serviceProcessIdNew"),
						object.getServiceProcessId(), StringPool.BLANK);

				_log.info("=====ServiceProcessId:"
						+ object.getServiceProcessId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
