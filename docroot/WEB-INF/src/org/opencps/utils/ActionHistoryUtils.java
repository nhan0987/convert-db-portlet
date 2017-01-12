package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ActionHistory;
import org.opencps.processmgt.service.ActionHistoryLocalServiceUtil;

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

public class ActionHistoryUtils {

	private static Log _log = LogFactoryUtil.getLog(ActionHistoryUtils.class);

	public void fetchActionhistory(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ActionHistory> List = ActionHistoryLocalServiceUtil
					.getActionHistories(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_ACTION_HISTORY, WebKeys.ACTION_HISTORY,
					WebKeys.ACTION_HISTORYColumns);

			int i = 1;
			for (ActionHistory object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getActionHistoryId());

				JSONObject columnNames = WebKeys.getACTION_HISTORYColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("processOrderId"),
						object.getActionHistoryId(),
						String.valueOf(object.getProcessOrderId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY, columnNames
								.getString("actionDateTime"), object
								.getActionHistoryId(), DateTimeUtil
								.convertDateToString(
										object.getActionDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("processWorkflowId"),
						object.getActionHistoryId(),
						String.valueOf(object.getProcessWorkflowId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("stepName"),
						object.getActionHistoryId(),
						String.valueOf(object.getStepName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionName"),
						object.getActionHistoryId(),
						String.valueOf(object.getActionName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionNote"),
						object.getActionHistoryId(),
						String.valueOf(object.getActionNote()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionUserId"),
						object.getActionHistoryId(),
						String.valueOf(object.getActionUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("logId"),
						object.getActionHistoryId(),
						String.valueOf(object.getLogId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("dayDoing"),
						object.getActionHistoryId(),
						String.valueOf(object.getDaysDoing()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("dayDelay"),
						object.getActionHistoryId(),
						String.valueOf(object.getDaysDelay()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionHistoryIdNew"),
						object.getActionHistoryId(), StringPool.BLANK);

				_log.info("=====ActionHistoryId:" + object.getActionHistoryId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addActionHistory(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_ACTION_HISTORY,
					WebKeys.ACTION_HISTORY);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
					WebKeys.EXTableName_ACTION_HISTORY, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("*i:" + i);
				_log.info("=====row.getClassPK()" + row.getClassPK());

				JSONObject columnNames = WebKeys.getACTION_HISTORYColumnNames();

				JSONObject processOrderColNames = WebKeys
						.getPROCESS_ORDERColumnNames();

				JSONObject processWorkFlowColNames = WebKeys
						.getProcessWorkflowColumnNames();

				JSONObject dossierLogColNames = WebKeys
						.getDOSSIER_LOGColumnNames();

				JSONObject userColNames = WebKeys.getUserColumnNames();

				String actionDateTime = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionDateTime"),
						row.getClassPK(), StringPool.BLANK);

				String stepName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("stepName"), row.getClassPK(),
						StringPool.BLANK);

				String actionName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionName"), row.getClassPK(),
						StringPool.BLANK);

				String actionNote = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("actionNote"), row.getClassPK(),
						StringPool.BLANK);

				String dayDoing = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("dayDoing"), row.getClassPK(),
						StringPool.BLANK);

				String dayDelay = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ACTION_HISTORY,
						WebKeys.EXTableName_ACTION_HISTORY,
						columnNames.getString("dayDelay"), row.getClassPK(),
						StringPool.BLANK);

				// ///////////////////////////////////////////////

				long processOrderId = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_ACTION_HISTORY,
						WebKeys.ACTION_HISTORY,
						columnNames.getString("processOrderId"),
						row.getClassPK(), WebKeys.EXTableName_PROCESS_ORDER,
						WebKeys.PROCESS_ORDER,
						processOrderColNames.getString("processOrderIdNew"),
						themeDisplay);

				// ///////////////////////////////////////////////

				long logId = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_ACTION_HISTORY,
						WebKeys.ACTION_HISTORY, columnNames.getString("logId"),
						row.getClassPK(), WebKeys.EXTableName_DOSSIER_LOG,
						WebKeys.DOSSIER_LOG,
						dossierLogColNames.getString("dossierLogIdNew"),
						themeDisplay);

				// ///////////////////////////////////////////////

				long processWorkflowId = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_ACTION_HISTORY,
								WebKeys.ACTION_HISTORY, columnNames
										.getString("processWorkflowId"), row
										.getClassPK(),
								WebKeys.EXTableName_ProcessWorkflow,
								WebKeys.PROCESS_WORKFLOW,
								processWorkFlowColNames
										.getString("processWorkflowIdNew"),
								themeDisplay);

				// ///////////////////////////////////////////////

				long actionUserId = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_ACTION_HISTORY,
						WebKeys.ACTION_HISTORY,
						columnNames.getString("actionUserId"),
						row.getClassPK(), WebKeys.EXTableName_User,
						WebKeys.USERS, userColNames.getString("userIdNew"),
						themeDisplay);

				// ///////////////////////////////////////////////

				ActionHistory actionHistory = null;

				if (logId > 0) {

					actionHistory = ActionHistoryLocalServiceUtil
							.createActionHistory(CounterLocalServiceUtil
									.increment(WebKeys.ACTION_HISTORY));

					actionHistory.setActionDatetime(Validator
							.isNotNull(actionDateTime) ? DateTimeUtil
							.convertStringToFullDate(actionDateTime) : null);
					actionHistory.setActionName(actionName);
					actionHistory.setActionNote(actionNote);
					actionHistory
							.setDaysDelay(Validator.isNotNull(dayDelay) ? Integer
									.valueOf(dayDelay) : 0);
					actionHistory
							.setDaysDoing(Validator.isNotNull(dayDoing) ? Integer
									.valueOf(dayDoing) : 0);
					actionHistory.setStepName(stepName);
					actionHistory.setProcessOrderId(processOrderId);
					actionHistory.setProcessWorkflowId(processWorkflowId);
					actionHistory.setActionUserId(actionUserId);
					actionHistory.setLogId(logId);
					actionHistory.setCreateDate(Validator
							.isNotNull(actionDateTime) ? DateTimeUtil
							.convertStringToFullDate(actionDateTime) : null);
					actionHistory.setModifiedDate(Validator
							.isNotNull(actionDateTime) ? DateTimeUtil
							.convertStringToFullDate(actionDateTime) : null);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(),
							WebKeys.ACTION_HISTORY,
							WebKeys.EXTableName_ACTION_HISTORY,
							columnNames.getString("actionHistoryIdNew"),
							row.getClassPK(),
							String.valueOf(actionHistory.getActionHistoryId()));

					_log.info("=====add Success===ActionHistoryId():"
							+ actionHistory.getActionHistoryId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
