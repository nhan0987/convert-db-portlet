package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;

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

public class DossierLogUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierLogUtils.class);

	public void fetchDossierLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierLog> List = DossierLogLocalServiceUtil.getDossierLogs(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DOSSIER_LOG, WebKeys.DOSSIER_LOG,
					WebKeys.DOSSIER_LOGColumns);

			int i = 1;
			for (DossierLog object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierLogId());

				JSONObject columnNames = WebKeys.getDOSSIER_LOGColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("userId"),
						object.getDossierLogId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("createDate"), object
								.getDossierLogId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("modifiedDate"), object
								.getDossierLogId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("dossierId"),
						object.getDossierLogId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("requestCommand"),
						object.getDossierLogId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("dossierStatus"),
						object.getDossierLogId(),
						String.valueOf(object.getDossierStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actionInfo"),
						object.getDossierLogId(),
						String.valueOf(object.getActionInfo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("messageInfo"),
						object.getDossierLogId(),
						String.valueOf(object.getMessageInfo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("updateDateTime"), object
								.getDossierLogId(), DateTimeUtil
								.convertDateToString(
										object.getUpdateDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("level"),
						object.getDossierLogId(),
						String.valueOf(object.getLevel()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actor"),
						object.getDossierLogId(),
						String.valueOf(object.getActor()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actorId"),
						object.getDossierLogId(),
						String.valueOf(object.getActorId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actorName"),
						object.getDossierLogId(),
						String.valueOf(object.getActorName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("syncStatus"),
						object.getDossierLogId(),
						String.valueOf(object.getSyncStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_LOG, WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("dossierLogIdNew"),
						object.getDossierLogId(), StringPool.BLANK);

				_log.info("=====DossierLogId:" + object.getDossierLogId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossierLog(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DOSSIER_LOG,
					WebKeys.DOSSIER_LOG);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
					WebKeys.EXTableName_DOSSIER_LOG, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getDOSSIER_LOGColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject dossierColumnNames = WebKeys.getDOSSIERColumnNames();

				String createDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("createDate"), row.getClassPK(),
						StringPool.BLANK);

				String modifiedDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("modifiedDate"),
						row.getClassPK(), StringPool.BLANK);

				String requestCommand = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("requestCommand"),
						row.getClassPK(), StringPool.BLANK);

				String dossierStatus = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("dossierStatus"),
						row.getClassPK(), StringPool.BLANK);

				String actionInfo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actionInfo"), row.getClassPK(),
						StringPool.BLANK);

				String messageInfo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("messageInfo"), row.getClassPK(),
						StringPool.BLANK);

				String updateDateTime = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("updateDateTime"),
						row.getClassPK(), StringPool.BLANK);

				String level = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("level"), row.getClassPK(),
						StringPool.BLANK);

				String actor = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actor"), row.getClassPK(),
						StringPool.BLANK);

				String actorName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("actorName"), row.getClassPK(),
						StringPool.BLANK);

				String syncStatus = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
						WebKeys.EXTableName_DOSSIER_LOG,
						columnNames.getString("syncStatus"), row.getClassPK(),
						StringPool.BLANK);

				// ///////////////////////////////////////////////////////////////////////

				long userIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_LOG, WebKeys.DOSSIER_LOG,
						columnNames.getString("userId"), row.getClassPK(),
						WebKeys.EXTableName_User, WebKeys.USERS,
						userColumnNames.getString("userIdNew"), themeDisplay);

				long actorIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_LOG, WebKeys.DOSSIER_LOG,
						columnNames.getString("actorId"), row.getClassPK(),
						WebKeys.EXTableName_User, WebKeys.USERS,
						userColumnNames.getString("userIdNew"), themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long dossierIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_LOG, WebKeys.DOSSIER_LOG,
						columnNames.getString("dossierId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						dossierColumnNames.getString("dossierIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				DossierLog dossierLog = null;

				if (dossierIdNew > 0) {

					dossierLog = DossierLogLocalServiceUtil
							.createDossierLog(CounterLocalServiceUtil
									.increment(WebKeys.DOSSIER_LOG));

					dossierLog
							.setCreateDate(Validator.isNotNull(createDate) ? DateTimeUtil
									.convertStringToFullDate(createDate) : null);

					dossierLog.setModifiedDate(Validator
							.isNotNull(modifiedDate) ? DateTimeUtil
							.convertStringToFullDate(modifiedDate) : null);
					dossierLog.setUserId(userIdNew);
					dossierLog.setDossierId(dossierIdNew);
					dossierLog.setRequestCommand(requestCommand);
					dossierLog.setDossierStatus(dossierStatus);
					dossierLog.setActionInfo(actionInfo);
					dossierLog.setMessageInfo(messageInfo);
					dossierLog.setUpdateDatetime(Validator
							.isNotNull(updateDateTime) ? DateTimeUtil
							.convertStringToFullDate(updateDateTime) : null);
					dossierLog.setLevel(Validator.isNotNull(level) ? Integer
							.valueOf(level) : 0);
					dossierLog.setActor(Validator.isNotNull(actor) ? Integer
							.valueOf(actor) : 0);
					dossierLog.setActorId(actorIdNew);
					dossierLog.setActorName(actorName);
					dossierLog
							.setSyncStatus(Validator.isNotNull(syncStatus) ? Integer
									.valueOf(syncStatus) : 0);

					DossierLogLocalServiceUtil.addDossierLog(dossierLog);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_LOG,
							WebKeys.EXTableName_DOSSIER_LOG,
							columnNames.getString("dossierLogIdNew"),
							row.getClassPK(),
							String.valueOf(dossierLog.getDossierLogId()));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchDossierLog2(ThemeDisplay themeDisplay, long dossierId) {

		try {

			if (dossierId > 0) {

				long companyId = themeDisplay.getCompanyId();

				List<DossierLog> List = new ArrayList<DossierLog>();

				List = DossierLogLocalServiceUtil
						.getDossierLogByDossierId(dossierId);

				if (List.size() > 0) {

					CommonUtils commonUtils = new CommonUtils();

					ExpandoTable expandoTable = commonUtils.checkTable(
							companyId, WebKeys.EXTableName_DOSSIER_LOG,
							WebKeys.DOSSIER_LOG, WebKeys.DOSSIER_LOGColumns);

					int i = 1;
					for (DossierLog object : List) {

						_log.info("*i:" + i);
						_log.info("=====dossierId:"+dossierId);

						ExpandoRowLocalServiceUtil.addRow(
								expandoTable.getTableId(),
								object.getDossierLogId());

						JSONObject columnNames = WebKeys
								.getDOSSIER_LOGColumnNames();

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("userId"),
								object.getDossierLogId(),
								String.valueOf(object.getUserId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG, columnNames
										.getString("createDate"), object
										.getDossierLogId(),
								DateTimeUtil.convertDateToString(
										object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG, columnNames
										.getString("modifiedDate"), object
										.getDossierLogId(),
								DateTimeUtil.convertDateToString(
										object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("dossierId"),
								object.getDossierLogId(),
								String.valueOf(object.getUserId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("requestCommand"),
								object.getDossierLogId(),
								String.valueOf(object.getUserId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("dossierStatus"),
								object.getDossierLogId(),
								String.valueOf(object.getDossierStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("actionInfo"),
								object.getDossierLogId(),
								String.valueOf(object.getActionInfo()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("messageInfo"),
								object.getDossierLogId(),
								String.valueOf(object.getMessageInfo()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG, columnNames
										.getString("updateDateTime"), object
										.getDossierLogId(),
								DateTimeUtil.convertDateToString(
										object.getUpdateDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("level"),
								object.getDossierLogId(),
								String.valueOf(object.getLevel()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("actor"),
								object.getDossierLogId(),
								String.valueOf(object.getActor()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("actorId"),
								object.getDossierLogId(),
								String.valueOf(object.getActorId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("actorName"),
								object.getDossierLogId(),
								String.valueOf(object.getActorName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("syncStatus"),
								object.getDossierLogId(),
								String.valueOf(object.getSyncStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_LOG,
								WebKeys.EXTableName_DOSSIER_LOG,
								columnNames.getString("dossierLogIdNew"),
								object.getDossierLogId(), StringPool.BLANK);

						_log.info("=====dossierLogId:"
								+ object.getDossierLogId());

						DossierFileLogUtils dossierFileLogUtils = new DossierFileLogUtils();
						dossierFileLogUtils.fetchDossierFileLog2(themeDisplay,
								object.getDossierLogId(), dossierId);
						i++;

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
