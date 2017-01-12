package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ProcessOrder;
import org.opencps.processmgt.service.ProcessOrderLocalServiceUtil;

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

public class ProcessOrderUtils {

	private static Log _log = LogFactoryUtil.getLog(ProcessOrderUtils.class);

	public void fetchProcessOrders(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ProcessOrder> List = ProcessOrderLocalServiceUtil
					.getProcessOrders(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_PROCESS_ORDER, WebKeys.PROCESS_ORDER,
					WebKeys.PROCESS_ORDERColumns);

			int i = 1;
			for (ProcessOrder object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getProcessOrderId());

				JSONObject columnNames = WebKeys.getPROCESS_ORDERColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER, columnNames
								.getString("createDate"), object
								.getProcessOrderId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER, columnNames
								.getString("modifiedDate"), object
								.getProcessOrderId(), DateTimeUtil
								.convertDateToString(object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER, columnNames
								.getString("actionDateTime"), object
								.getProcessOrderId(), DateTimeUtil
								.convertDateToString(
										object.getActionDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("serviceProcessId"),
						object.getProcessOrderId(),
						String.valueOf(object.getServiceProcessId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("dossierId"),
						object.getProcessOrderId(),
						String.valueOf(object.getDossierId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("processStepId"),
						object.getProcessOrderId(),
						String.valueOf(object.getProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("actionUserId"),
						object.getProcessOrderId(),
						String.valueOf(object.getActionUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("actionNote"),
						object.getProcessOrderId(),
						String.valueOf(object.getActionNote()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("assignToUserId"),
						object.getProcessOrderId(),
						String.valueOf(object.getAssignToUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("processWorkflowId"),
						object.getProcessOrderId(),
						String.valueOf(object.getProcessWorkflowId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("dossierStatus"),
						object.getProcessOrderId(),
						String.valueOf(object.getDossierStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("processOrderIdNew"),
						object.getProcessOrderId(), StringPool.BLANK);

				_log.info("=====ProcessOrderId:" + object.getProcessOrderId());
				
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProcessOrders(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_PROCESS_ORDER,
					WebKeys.PROCESS_ORDER);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
					WebKeys.EXTableName_PROCESS_ORDER, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);
				
				_log.info("*i:"+i);
				_log.info("=====row.getClassPK():"+row.getClassPK());

				JSONObject columnNames = WebKeys.getPROCESS_ORDERColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject serviceProcessColumnNames = WebKeys
						.getServiceProcessColumnNames();

				JSONObject dossierColumnNames = WebKeys.getDOSSIERColumnNames();

				JSONObject processStepColumnNames = WebKeys
						.getProcessStepColumnNames();

				JSONObject processWorkflowColNames = WebKeys
						.getProcessWorkflowColumnNames();

				String createDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("createDate"), row.getClassPK(),
						StringPool.BLANK);

				String modifiedDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("modifiedDate"),
						row.getClassPK(), StringPool.BLANK);

				String actionDateTime = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("actionDateTime"),
						row.getClassPK(), StringPool.BLANK);

				String actionNote = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("actionNote"), row.getClassPK(),
						StringPool.BLANK);

				String dossierStatus = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("dossierStatus"),
						row.getClassPK(), StringPool.BLANK);

				// ///////////////////////////////////////////////////////////////////////

				long actionUserIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_PROCESS_ORDER,
						WebKeys.PROCESS_ORDER,
						columnNames.getString("actionUserId"),
						row.getClassPK(), WebKeys.EXTableName_User,
						WebKeys.USERS, userColumnNames.getString("userIdNew"),
						themeDisplay);

				long assignToUserIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_PROCESS_ORDER,
								WebKeys.PROCESS_ORDER,
								columnNames.getString("assignToUserId"),
								row.getClassPK(), WebKeys.EXTableName_User,
								WebKeys.USERS,
								userColumnNames.getString("userIdNew"),
								themeDisplay);

				// ///////////////////////////////////////////////////////////////////////////

				long serviceProcessIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_PROCESS_ORDER,
								WebKeys.PROCESS_ORDER, columnNames
										.getString("serviceProcessId"), row
										.getClassPK(),
								WebKeys.EXTableName_ServiceProcess,
								WebKeys.SERVICE_PROCESS,
								serviceProcessColumnNames
										.getString("serviceProcessIdNew"),
								themeDisplay);

				// ///////////////////////////////////////////////////////////////////////////

				long dossierIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_PROCESS_ORDER,
						WebKeys.PROCESS_ORDER,
						columnNames.getString("dossierId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						dossierColumnNames.getString("dossierIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long processStepIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_PROCESS_ORDER,
						WebKeys.PROCESS_ORDER,
						columnNames.getString("processStepId"),
						row.getClassPK(), WebKeys.EXTableName_ProcessStep,
						WebKeys.PROCESS_STEP,
						processStepColumnNames.getString("processStepIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long processWorkflowIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_PROCESS_ORDER,
								WebKeys.PROCESS_ORDER, columnNames
										.getString("processWorkflowId"), row
										.getClassPK(),
								WebKeys.EXTableName_ProcessWorkflow,
								WebKeys.PROCESS_WORKFLOW,
								processWorkflowColNames
										.getString("processWorkflowIdNew"),
								themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				ProcessOrder processOrder = null;

				long fileGroupId = 0;
				
				if(dossierIdNew >0){

				processOrder = ProcessOrderLocalServiceUtil
						.addProcessOrder(
								dossierIdNew,
								fileGroupId,
								serviceProcessIdNew,
								processStepIdNew,
								processWorkflowIdNew,
								actionUserIdNew,
								Validator.isNotNull(actionDateTime) ? DateTimeUtil
										.convertStringToFullDate(actionDateTime)
										: null, StringPool.BLANK,
								StringPool.BLANK, actionNote,
								assignToUserIdNew, dossierStatus, 0, 0,
								serviceContext);

				processOrder
						.setCreateDate(Validator.isNotNull(createDate) ? DateTimeUtil
								.convertStringToFullDate(createDate) : null);
				processOrder
						.setModifiedDate(Validator.isNotNull(modifiedDate) ? DateTimeUtil
								.convertStringToFullDate(modifiedDate) : null);

				ProcessOrderLocalServiceUtil.updateProcessOrder(processOrder);

				ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.PROCESS_ORDER,
						WebKeys.EXTableName_PROCESS_ORDER,
						columnNames.getString("processOrderIdNew"),
						row.getClassPK(),
						String.valueOf(processOrder.getProcessOrderId()));
				
				_log.info("=====processOrder.getProcessOrderId():"+processOrder.getProcessOrderId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
