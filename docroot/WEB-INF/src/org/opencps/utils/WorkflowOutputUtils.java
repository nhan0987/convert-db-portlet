
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.WorkflowOutput;
import org.opencps.processmgt.service.WorkflowOutputLocalServiceUtil;

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

public class WorkflowOutputUtils {

	private static Log _log = LogFactoryUtil.getLog(WorkflowOutputUtils.class);

	public void fetchWorkflowOutput(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<WorkflowOutput> List =
				WorkflowOutputLocalServiceUtil.getWorkflowOutputs(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_WorkflowOutput, WebKeys.WORKFLOW_OUTPUT,
					WebKeys.WorkflowOutputColumns);
			int i = 1;
			for (WorkflowOutput object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getWorkflowOutputId());

				JSONObject columnNames = WebKeys.getWorkflowOutputColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("processWorkflowId"), object.getWorkflowOutputId(),
					String.valueOf(object.getProcessWorkflowId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("dossierPartId"), object.getWorkflowOutputId(),
					String.valueOf(object.getDossierPartId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("required"), object.getWorkflowOutputId(),
					String.valueOf(object.getRequired()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("esign"), object.getWorkflowOutputId(),
					String.valueOf(object.getEsign()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("postback"), object.getWorkflowOutputId(),
					String.valueOf(object.getPostback()));
				
				ExpandoValueLocalServiceUtil.addValue(
						companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
						columnNames.getString("pattern"), object.getWorkflowOutputId(),
						String.valueOf(object.getPattern()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKFLOW_OUTPUT, WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("workflowOutputIdNew"), object.getWorkflowOutputId(),
					StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====WorkflowOutputId:" + object.getWorkflowOutputId());
				i++;

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWorkflowOutput(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

		try {

			ExpandoTable expandoTable = null;
			String message = null;
			
			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_WorkflowOutput, WebKeys.WORKFLOW_OUTPUT);
			
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			WorkflowOutput workflowOutput = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
					WebKeys.EXTableName_WorkflowOutput, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getWorkflowOutputColumnNames();

				JSONObject processWorkflowColNames = WebKeys.getProcessWorkflowColumnNames();

				JSONObject dossierPartColNames = WebKeys.getDossierPartColumnNames();

				// /
				String processWorkflowId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput,
						columnNames.getString("processWorkflowId"), row.getClassPK(),
						StringPool.BLANK);

				String processWorkflowIdNew = StringPool.BLANK;

				if (Validator.isNotNull(processWorkflowId)) {

					processWorkflowIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
							WebKeys.EXTableName_ProcessWorkflow,
							processWorkflowColNames.getString("processWorkflowIdNew"),
							Long.valueOf(processWorkflowId), StringPool.BLANK);
				}

				String dossierPartId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput, columnNames.getString("dossierPartId"),
						row.getClassPK(), StringPool.BLANK);

				String dossierPartIdNew = StringPool.BLANK;

				if (Validator.isNotNull(dossierPartId)) {

					dossierPartIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							dossierPartColNames.getString("dossierPartIdNew"),
							Long.valueOf(dossierPartId), StringPool.BLANK);
				}

				String required =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput, columnNames.getString("required"),
						row.getClassPK(), StringPool.BLANK);

				String esign =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput, columnNames.getString("esign"),
						row.getClassPK(), StringPool.BLANK);

				String postback =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
						WebKeys.EXTableName_WorkflowOutput, columnNames.getString("postback"),
						row.getClassPK(), StringPool.BLANK);
				
				String pattern =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
							WebKeys.EXTableName_WorkflowOutput, columnNames.getString("pattern"),
							row.getClassPK(), StringPool.BLANK);

				// /
				long workflowOutputId = CounterLocalServiceUtil.increment(WebKeys.WORKFLOW_OUTPUT);

				workflowOutput =
					WorkflowOutputLocalServiceUtil.createWorkflowOutput(workflowOutputId);

				workflowOutput.setProcessWorkflowId(Validator.isNotNull(processWorkflowIdNew)
					? Long.valueOf(processWorkflowIdNew) : 0);
				workflowOutput.setDossierPartId(Validator.isNotNull(dossierPartIdNew)
					? Long.valueOf(dossierPartIdNew) : 0);
				workflowOutput.setRequired(Validator.isNotNull(required)
					? Boolean.valueOf(required) : false);
				workflowOutput.setEsign(Validator.isNotNull(esign) ? Boolean.valueOf(esign) : false);
				workflowOutput.setPostback(Validator.isNotNull(postback)
					? Boolean.valueOf(postback) : false);
				workflowOutput.setPattern(pattern);

				WorkflowOutputLocalServiceUtil.updateWorkflowOutput(workflowOutput);

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.WORKFLOW_OUTPUT,
					WebKeys.EXTableName_WorkflowOutput,
					columnNames.getString("workflowOutputIdNew"), row.getClassPK(),
					String.valueOf(processWorkflowId));

				_log.info("=====add Success===processWorkflowId:" + processWorkflowId);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
