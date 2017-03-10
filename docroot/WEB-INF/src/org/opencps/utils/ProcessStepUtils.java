
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ProcessStep;
import org.opencps.processmgt.service.ProcessStepLocalServiceUtil;

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

public class ProcessStepUtils {

	private static Log _log = LogFactoryUtil.getLog(ProcessStepUtils.class);

	public void fetchProcessStep(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ProcessStep> List =
				ProcessStepLocalServiceUtil.getProcessSteps(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_ProcessStep, WebKeys.PROCESS_STEP,
					WebKeys.ProcessStepColumns);
			int i = 1;

			_log.info("=====fetching...processStep");

			for (ProcessStep object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getProcessStepId());

				JSONObject columnNames = WebKeys.getProcessStepColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("serviceProcessId"), object.getProcessStepId(),
					String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("stepName"), object.getProcessStepId(),
					String.valueOf(object.getStepName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("sequenceNo"), object.getProcessStepId(),
					String.valueOf(object.getSequenceNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("dossierStatus"), object.getProcessStepId(),
					String.valueOf(object.getDossierStatus()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("dayDuration"), object.getProcessStepId(),
					String.valueOf(object.getDaysDuration()));
				
				ExpandoValueLocalServiceUtil.addValue(
						companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("dossierSubStatus"), object.getProcessStepId(),
						String.valueOf(object.getDossierSubStatus()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
					columnNames.getString("processStepIdNew"), object.getProcessStepId(),
					StringPool.BLANK);

				_log.info("*:" + i);
				_log.info("=====ProcessStepId:" + object.getProcessStepId());
				i++;

			}
			_log.info("=====fetdone...processStep");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProcessStep(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_ProcessStep, WebKeys.PROCESS_STEP);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			ProcessStep processStep = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
					WebKeys.EXTableName_ProcessStep, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getProcessStepColumnNames();

				JSONObject serviceProcessColNames = WebKeys.getServiceProcessColumnNames();

				// /
				String serviceProcessId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep, columnNames.getString("serviceProcessId"),
						row.getClassPK(), StringPool.BLANK);

				String serviceProcessIdNew = StringPool.BLANK;
				if (Validator.isNotNull(serviceProcessId)) {
					serviceProcessIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
							WebKeys.EXTableName_ServiceProcess,
							serviceProcessColNames.getString("serviceProcessIdNew"),
							Long.valueOf(serviceProcessId), StringPool.BLANK);
				}

				String stepName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep, columnNames.getString("stepName"),
						row.getClassPK(), StringPool.BLANK);
				String sequenceNo =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep, columnNames.getString("sequenceNo"),
						row.getClassPK(), StringPool.BLANK);
				String dossierStatus =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep, columnNames.getString("dossierStatus"),
						row.getClassPK(), StringPool.BLANK);
				String dayDuration =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
						WebKeys.EXTableName_ProcessStep, columnNames.getString("dayDuration"),
						row.getClassPK(), StringPool.BLANK);
				String dossierSubStatus =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
							WebKeys.EXTableName_ProcessStep, columnNames.getString("dossierSubStatus"),
							row.getClassPK(), StringPool.BLANK);
				

				// ///////////////
				long processStepId = CounterLocalServiceUtil.increment(WebKeys.PROCESS_STEP);

				processStep = ProcessStepLocalServiceUtil.createProcessStep(processStepId);

				processStep.setServiceProcessId(Validator.isNotNull(serviceProcessIdNew)
					? Long.valueOf(serviceProcessIdNew) : 0);
				processStep.setStepName(stepName);
				processStep.setSequenceNo(Validator.isNotNull(sequenceNo)
					? Integer.valueOf(sequenceNo) : 0);
				processStep.setDossierStatus(dossierStatus);
				processStep.setDaysDuration(Validator.isNotNull(dayDuration)
					? Integer.valueOf(dayDuration) : 0);
				processStep.setDossierSubStatus(dossierSubStatus);

				processStep.setCompanyId(themeDisplay.getCompanyId());
				processStep.setGroupId(serviceContext.getScopeGroupId());
				processStep.setUserId(serviceContext.getUserId());

				ProcessStepLocalServiceUtil.addProcessStep(processStep);

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
					WebKeys.EXTableName_ProcessStep, columnNames.getString("processStepIdNew"),
					row.getClassPK(), String.valueOf(processStepId));
				_log.info("=====add Success==processStepId:" + processStepId);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchProcessStep2(ThemeDisplay themeDisplay, ProcessStep object) {

		try {

			if (Validator.isNotNull(object)) {

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils.checkTable(companyId,
						WebKeys.EXTableName_ProcessStep, WebKeys.PROCESS_STEP,
						WebKeys.ProcessStepColumns);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getProcessStepId());

				JSONObject columnNames = WebKeys.getProcessStepColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("serviceProcessId"),
						object.getProcessStepId(),
						String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("stepName"),
						object.getProcessStepId(),
						String.valueOf(object.getStepName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("sequenceNo"),
						object.getProcessStepId(),
						String.valueOf(object.getSequenceNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("dossierStatus"),
						object.getProcessStepId(),
						String.valueOf(object.getDossierStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("dayDuration"),
						object.getProcessStepId(),
						String.valueOf(object.getDaysDuration()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("dossierSubStatus"),
						object.getProcessStepId(),
						String.valueOf(object.getDossierSubStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_STEP, WebKeys.EXTableName_ProcessStep,
						columnNames.getString("processStepIdNew"),
						object.getProcessStepId(), StringPool.BLANK);

				_log.info("=====ProcessStepId:" + object.getProcessStepId());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
