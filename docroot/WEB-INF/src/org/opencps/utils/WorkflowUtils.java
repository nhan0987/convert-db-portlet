
package org.opencps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ProcessWorkflow;
import org.opencps.processmgt.service.ProcessWorkflowLocalServiceUtil;

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

public class WorkflowUtils {

	private static Log _log = LogFactoryUtil.getLog(WorkflowUtils.class);

	public void fetchWorkflow(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ProcessWorkflow> List =
				ProcessWorkflowLocalServiceUtil.getProcessWorkflows(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_ProcessWorkflow, WebKeys.PROCESS_WORKFLOW,
					WebKeys.ProcessWorkflowColumns);
			int i = 1;
			for (ProcessWorkflow object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getProcessWorkflowId());

				JSONObject columnNames = WebKeys.getProcessWorkflowColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("serviceProcessId"), object.getProcessWorkflowId(),
					String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("preProcessStepId"), object.getProcessWorkflowId(),
					String.valueOf(object.getPreProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("postProcessStepId"), object.getProcessWorkflowId(),
					String.valueOf(object.getPostProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("autoEvent"), object.getProcessWorkflowId(),
					String.valueOf(object.getAutoEvent()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("actionName"), object.getProcessWorkflowId(),
					String.valueOf(object.getActionName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("assignUser"), object.getProcessWorkflowId(),
					String.valueOf(object.getAssignUser()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("actionUserId"), object.getProcessWorkflowId(),
					String.valueOf(object.getActionUserId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("requestPayment"), object.getProcessWorkflowId(),
					String.valueOf(object.getRequestPayment()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("paymentFee"), object.getProcessWorkflowId(),
					String.valueOf(object.getPaymentFee()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("generateReceptionNo"), object.getProcessWorkflowId(),
					String.valueOf(object.getGenerateReceptionNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("receptionNoPattern"), object.getProcessWorkflowId(),
					String.valueOf(object.getReceptionNoPattern()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("generateDealine"), object.getProcessWorkflowId(),
					String.valueOf(object.getGenerateDeadline()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("dealinePattern"), object.getProcessWorkflowId(),
					String.valueOf(object.getDeadlinePattern()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("isFinishStep"), object.getProcessWorkflowId(),
					String.valueOf(object.getIsFinishStep()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("preCondition"), object.getProcessWorkflowId(),
					String.valueOf(object.getPreCondition()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("isMutipled"), object.getProcessWorkflowId(),
					String.valueOf(object.getIsMultipled()));
				
				ExpandoValueLocalServiceUtil.addValue(
						companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("actionCode"), object.getProcessWorkflowId(),
						String.valueOf(object.getActionCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESS_WORKFLOW, WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("processWorkflowIdNew"), object.getProcessWorkflowId(),
					StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====ProcessWorkflowId:" + object.getProcessWorkflowId());
				i++;

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWorkflow(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_ProcessWorkflow, WebKeys.PROCESS_WORKFLOW);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			ProcessWorkflow processWorkflow = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
					WebKeys.EXTableName_ProcessWorkflow, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {
				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getProcessWorkflowColumnNames();

				JSONObject serviceProcessColNames = WebKeys.getServiceProcessColumnNames();

				JSONObject processStepColNames = WebKeys.getProcessStepColumnNames();

				JSONObject userColNames = WebKeys.getUserColumnNames();

				// /
				String serviceProcessId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("serviceProcessId"), row.getClassPK(),
						StringPool.BLANK);

				String serviceProcessIdNew = StringPool.BLANK;

				if (Validator.isNotNull(serviceProcessId)) {

					serviceProcessIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
							WebKeys.EXTableName_ServiceProcess,
							serviceProcessColNames.getString("serviceProcessIdNew"),
							Long.valueOf(serviceProcessId), StringPool.BLANK);
				}

				String preProcessStepId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("preProcessStepId"), row.getClassPK(),
						StringPool.BLANK);

				String preProcessStepIdNew = StringPool.BLANK;

				if (Validator.isNotNull(preProcessStepId)) {

					preProcessStepIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
							WebKeys.EXTableName_ProcessStep,
							processStepColNames.getString("processStepIdNew"),
							Long.valueOf(preProcessStepId), StringPool.BLANK);
				}
				String postProcessStepId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("postProcessStepId"), row.getClassPK(),
						StringPool.BLANK);

				String postProcessStepIdNew = StringPool.BLANK;

				if (Validator.isNotNull(postProcessStepId)) {

					postProcessStepIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
							WebKeys.EXTableName_ProcessStep,
							processStepColNames.getString("processStepIdNew"),
							Long.valueOf(postProcessStepId), StringPool.BLANK);
				}

				String autoEvent =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("autoEvent"),
						row.getClassPK(), StringPool.BLANK);

				String actionName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("actionName"),
						row.getClassPK(), StringPool.BLANK);

				String assignUser =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("assignUser"),
						row.getClassPK(), StringPool.BLANK);

				String actionUserId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("actionUserId"),
						row.getClassPK(), StringPool.BLANK);

				String actionUserIdNew = StringPool.BLANK;

				if (Validator.isNotNull(actionUserId)) {
					actionUserIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
							userColNames.getString("userIdNew"), Long.valueOf(actionUserId),
							StringPool.BLANK);
				}

				String requestPayment =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("requestPayment"), row.getClassPK(), StringPool.BLANK);

				String paymentFee =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("paymentFee"),
						row.getClassPK(), StringPool.BLANK);

				String generateReceptionNo =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("generateReceptionNo"), row.getClassPK(),
						StringPool.BLANK);

				String receptionNoPattern =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("receptionNoPattern"), row.getClassPK(),
						StringPool.BLANK);

				String generateDealine =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("generateDealine"), row.getClassPK(),
						StringPool.BLANK);

				String dealinePattern =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("dealinePattern"), row.getClassPK(), StringPool.BLANK);

				String isFinishStep =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("isFinishStep"),
						row.getClassPK(), StringPool.BLANK);

				String preCondition =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("preCondition"),
						row.getClassPK(), StringPool.BLANK);

				String isMutipled =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("isMutipled"),
						row.getClassPK(), StringPool.BLANK);
				
				String actionCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
							WebKeys.EXTableName_ProcessWorkflow, columnNames.getString("actionCode"),
							row.getClassPK(), StringPool.BLANK);

				// /
				long processWorkflowId =
					CounterLocalServiceUtil.increment(WebKeys.PROCESS_WORKFLOW);
				
				

				processWorkflow =
					ProcessWorkflowLocalServiceUtil.createProcessWorkflow(processWorkflowId);
				
				_log.info("====processWorkflowId:"+processWorkflowId);
				_log.info("====serviceProcessIdNew:"+serviceProcessIdNew);
				_log.info("====preProcessStepIdNew:"+preProcessStepIdNew);
				_log.info("====postProcessStepIdNew:"+postProcessStepIdNew);
				_log.info("====actionName:"+actionName);

				processWorkflow.setServiceProcessId(Validator.isNotNull(serviceProcessIdNew)
					? Long.valueOf(serviceProcessIdNew) : 0);
				processWorkflow.setPreProcessStepId(Validator.isNotNull(preProcessStepIdNew)
					? Long.valueOf(preProcessStepIdNew) : 0);
				processWorkflow.setPostProcessStepId(Validator.isNotNull(postProcessStepIdNew)
					? Long.valueOf(postProcessStepIdNew) : 0);
				processWorkflow.setAutoEvent(autoEvent);
				processWorkflow.setActionName(actionName);
				processWorkflow.setAssignUser(Validator.isNotNull(assignUser)
					? Boolean.valueOf(assignUser) : false);
				processWorkflow.setActionUserId(Validator.isNotNull(actionUserIdNew)
					? Long.valueOf(actionUserIdNew) : 0);
				processWorkflow.setRequestPayment(Validator.isNotNull(requestPayment)
					? Boolean.valueOf(requestPayment) : false);
				processWorkflow.setPaymentFee(paymentFee);
				processWorkflow.setGenerateReceptionNo(Validator.isNotNull(generateReceptionNo)
					? Boolean.valueOf(generateReceptionNo) : false);
				processWorkflow.setReceptionNoPattern(receptionNoPattern);
				processWorkflow.setGenerateDeadline(Validator.isNotNull(generateDealine)
					? Boolean.valueOf(generateDealine) : false);
				processWorkflow.setIsFinishStep(Validator.isNotNull(isFinishStep)
					? Boolean.valueOf(isFinishStep) : false);
				processWorkflow.setIsMultipled(Validator.isNotNull(isMutipled)
					? Boolean.valueOf(isMutipled) : false);
				processWorkflow.setPreCondition(preCondition);
				processWorkflow.setDeadlinePattern(dealinePattern);
				processWorkflow.setActionCode(actionCode);

				processWorkflow.setCompanyId(themeDisplay.getCompanyId());
				processWorkflow.setGroupId(serviceContext.getScopeGroupId());
				processWorkflow.setUserId(serviceContext.getUserId());
				
				processWorkflow.setModifiedDate(new Date());
				processWorkflow.setCreateDate(new Date());

				ProcessWorkflowLocalServiceUtil.addProcessWorkflow(processWorkflow);

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.PROCESS_WORKFLOW,
					WebKeys.EXTableName_ProcessWorkflow,
					columnNames.getString("processWorkflowIdNew"), row.getClassPK(),
					String.valueOf(processWorkflowId));

				_log.info("=====add Success===processWorkflowId:" + processWorkflowId);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchProcessWorkflow(ThemeDisplay themeDisplay,
			ProcessWorkflow object) {

		try {

			if (Validator.isNotNull(object)) {

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils.checkTable(companyId,
						WebKeys.EXTableName_ProcessWorkflow,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.ProcessWorkflowColumns);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getProcessWorkflowId());

				JSONObject columnNames = WebKeys
						.getProcessWorkflowColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("serviceProcessId"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("preProcessStepId"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getPreProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("postProcessStepId"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getPostProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("autoEvent"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getAutoEvent()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("actionName"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getActionName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("assignUser"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getAssignUser()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("actionUserId"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getActionUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("requestPayment"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getRequestPayment()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("paymentFee"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getPaymentFee()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("generateReceptionNo"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getGenerateReceptionNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("receptionNoPattern"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getReceptionNoPattern()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("generateDealine"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getGenerateDeadline()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("dealinePattern"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getDeadlinePattern()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("isFinishStep"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getIsFinishStep()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("preCondition"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getPreCondition()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("isMutipled"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getIsMultipled()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("actionCode"),
						object.getProcessWorkflowId(),
						String.valueOf(object.getActionCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_WORKFLOW,
						WebKeys.EXTableName_ProcessWorkflow,
						columnNames.getString("processWorkflowIdNew"),
						object.getProcessWorkflowId(), StringPool.BLANK);

				_log.info("=====ProcessWorkflowId:"
						+ object.getProcessWorkflowId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
