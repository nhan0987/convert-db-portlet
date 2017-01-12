package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.dossiermgt.model.DossierFileLog;
import org.opencps.dossiermgt.service.DossierFileLogLocalServiceUtil;

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

public class DossierFileLogUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierFileLogUtils.class);

	public void fetchDossierFileLog(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierFileLog> List = DossierFileLogLocalServiceUtil
					.getDossierFileLogs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DOSSIER_FILE_LOG,
					WebKeys.DOSSIER_FILE_LOG, WebKeys.DOSSIER_FILE_LOGColumns);

			int i = 1;
			for (DossierFileLog object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierFileLogId());

				JSONObject columnNames = WebKeys
						.getDOSSIER_FILE_LOGColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("userId"),
						object.getDossierFileLogId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("userName"),
						object.getDossierFileLogId(),
						String.valueOf(object.getUserName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG, columnNames
								.getString("modifiedDate"), object
								.getDossierFileLogId(), DateTimeUtil
								.convertDateToString(object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("dossierId"),
						object.getDossierFileLogId(),
						String.valueOf(object.getDossierId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("stepId"),
						object.getDossierFileLogId(),
						String.valueOf(object.getStepId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("isUpdate"),
						object.getDossierFileLogId(),
						String.valueOf(object.getIsUpdate()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("fileName"),
						object.getDossierFileLogId(),
						String.valueOf(object.getFileName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("fileVersion"),
						object.getDossierFileLogId(),
						String.valueOf(object.getFileVersion()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("actionCode"),
						object.getDossierFileLogId(),
						String.valueOf(object.getActionCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("count_"),
						object.getDossierFileLogId(),
						String.valueOf(object.getCount_()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("oid"),
						object.getDossierFileLogId(),
						String.valueOf(object.getOId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("fileEntryId"),
						object.getDossierFileLogId(),
						String.valueOf(object.getFileEntryId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("logId"),
						object.getDossierFileLogId(),
						String.valueOf(object.getLogId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("actor"),
						object.getDossierFileLogId(),
						String.valueOf(object.getActor()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("dossierFileLogIdNew"),
						object.getDossierFileLogId(), StringPool.BLANK);

				_log.info("=====DossierFileLogId:"
						+ object.getDossierFileLogId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossierFileLog(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DOSSIER_FILE_LOG,
					WebKeys.DOSSIER_FILE_LOG);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
					WebKeys.EXTableName_DOSSIER_FILE_LOG, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("i:" + i);
				_log.info("=====row.getclassPK():" + row.getClassPK());

				JSONObject columnNames = WebKeys
						.getDOSSIER_FILE_LOGColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject dossierColumnNames = WebKeys.getDOSSIERColumnNames();

				JSONObject processStepColNames = WebKeys
						.getProcessStepColumnNames();

				JSONObject dossierLogColNames = WebKeys
						.getDOSSIER_LOGColumnNames();

				JSONObject fileEntryDossierFileColNames = WebKeys
						.getFILEENTRY_DOSSIERFILEColumnNames();

				String createDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("modifiedDate"),
						row.getClassPK(), StringPool.BLANK);

				// ///////////////////////////////////////////////////////////////////////

				long userIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						WebKeys.DOSSIER_FILE_LOG,
						columnNames.getString("userId"), row.getClassPK(),
						WebKeys.EXTableName_User, WebKeys.USERS,
						userColumnNames.getString("userIdNew"), themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long dossierIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						WebKeys.DOSSIER_FILE_LOG,
						columnNames.getString("dossierId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						dossierColumnNames.getString("dossierIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long stepIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						WebKeys.DOSSIER_FILE_LOG,
						columnNames.getString("stepId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						processStepColNames.getString("processStepIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				String userName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("userName"), row.getClassPK(),
						StringPool.BLANK);

				String isUpdate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("isUpdate"), row.getClassPK(),
						StringPool.BLANK);

				String fileName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("fileName"), row.getClassPK(),
						StringPool.BLANK);

				String fileVersion = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("fileVersion"), row.getClassPK(),
						StringPool.BLANK);

				String actionCode = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("actionCode"), row.getClassPK(),
						StringPool.BLANK);

				String count_ = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("count_"), row.getClassPK(),
						StringPool.BLANK);

				String oid = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("oid"), row.getClassPK(),
						StringPool.BLANK);

				String actor = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						columnNames.getString("actor"), row.getClassPK(),
						StringPool.BLANK);

				// ////////////////////////////////////////////////////////////////////////////

				long logIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						WebKeys.DOSSIER_FILE_LOG,
						columnNames.getString("logId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER_LOG, WebKeys.DOSSIER_LOG,
						dossierLogColNames.getString("dossierlogIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long fileEntryIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE_LOG,
						WebKeys.DOSSIER_FILE_LOG,
						columnNames.getString("fileEntryId"), row.getClassPK(),
						WebKeys.EXTableName_FILEENTRY_DOSSIERFILE,
						WebKeys.FILEENTRY_DOSSIERFILE,
						fileEntryDossierFileColNames.getString("fileEntryId"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				DossierFileLog dossierFileLog = null;

				if (dossierIdNew > 0) {

					dossierFileLog = DossierFileLogLocalServiceUtil
							.createDossierFileLog(CounterLocalServiceUtil
									.increment(WebKeys.DOSSIER_FILE_LOG));

					dossierFileLog.setModifiedDate(Validator
							.isNotNull(createDate) ? DateTimeUtil
							.convertStringToFullDate(createDate) : null);

					dossierFileLog.setUserId(userIdNew);
					dossierFileLog.setDossierId(dossierIdNew);
					dossierFileLog.setStepId(stepIdNew);
					dossierFileLog.setLogId(logIdNew);
					dossierFileLog.setUserName(userName);
					dossierFileLog
							.setIsUpdate(Validator.isNotNull(isUpdate) ? Boolean
									.valueOf(isUpdate) : false);
					dossierFileLog.setFileName(fileName);
					dossierFileLog.setFileVersion(Validator
							.isNotNull(fileVersion) ? Integer
							.valueOf(fileVersion) : 0);
					dossierFileLog.setActionCode(Validator
							.isNotNull(actionCode) ? Integer
							.valueOf(actionCode) : 0);
					dossierFileLog
							.setCount_(Validator.isNotNull(actionCode) ? Integer
									.valueOf(actionCode) : 0);
					dossierFileLog.setOId(oid);
					dossierFileLog.setFileEntryId(fileEntryIdNew);
					dossierFileLog
							.setActor(Validator.isNotNull(actor) ? Integer
									.valueOf(actor) : 0);
					dossierFileLog
							.setCount_(Validator.isNotNull(count_) ? Integer
									.valueOf(count_) : 0);

					DossierFileLogLocalServiceUtil
							.updateDossierFileLog(dossierFileLog);

					ExpandoValueLocalServiceUtil.addValue(themeDisplay
							.getCompanyId(), WebKeys.DOSSIER_FILE_LOG,
							WebKeys.EXTableName_DOSSIER_FILE_LOG, columnNames
									.getString("dossierFileLogIdNew"), row
									.getClassPK(), String
									.valueOf(dossierFileLog
											.getDossierFileLogId()));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchDossierFileLog2(ThemeDisplay themeDisplay,
			long dossierLogId, long dossierId) throws Exception {

		try {

			if (dossierLogId > 0 && dossierId > 0) {

				long companyId = themeDisplay.getCompanyId();

				List<DossierFileLog> List = new ArrayList<DossierFileLog>();
				List = DossierFileLogLocalServiceUtil.getFileLogs(dossierLogId,
						dossierId);

				if (List.size() > 0) {
					CommonUtils commonUtils = new CommonUtils();

					ExpandoTable expandoTable = commonUtils.checkTable(
							companyId, WebKeys.EXTableName_DOSSIER_FILE_LOG,
							WebKeys.DOSSIER_FILE_LOG,
							WebKeys.DOSSIER_FILE_LOGColumns);

					int i = 1;
					for (DossierFileLog object : List) {

						_log.info("*i:" + i);
						_log.info("=====dossierId:" + dossierId);

						ExpandoRowLocalServiceUtil.addRow(
								expandoTable.getTableId(),
								object.getDossierFileLogId());

						JSONObject columnNames = WebKeys
								.getDOSSIER_FILE_LOGColumnNames();

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("userId"),
								object.getDossierFileLogId(),
								String.valueOf(object.getUserId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("userName"),
								object.getDossierFileLogId(),
								String.valueOf(object.getUserName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("modifiedDate"), object
										.getDossierFileLogId(),
								DateTimeUtil.convertDateToString(
										object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("dossierId"),
								object.getDossierFileLogId(),
								String.valueOf(object.getDossierId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("stepId"),
								object.getDossierFileLogId(),
								String.valueOf(object.getStepId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("isUpdate"),
								object.getDossierFileLogId(),
								String.valueOf(object.getIsUpdate()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("fileName"),
								object.getDossierFileLogId(),
								String.valueOf(object.getFileName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("fileVersion"),
								object.getDossierFileLogId(),
								String.valueOf(object.getFileVersion()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("actionCode"),
								object.getDossierFileLogId(),
								String.valueOf(object.getActionCode()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("count_"),
								object.getDossierFileLogId(),
								String.valueOf(object.getCount_()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("oid"),
								object.getDossierFileLogId(),
								String.valueOf(object.getOId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("fileEntryId"),
								object.getDossierFileLogId(),
								String.valueOf(object.getFileEntryId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("logId"),
								object.getDossierFileLogId(),
								String.valueOf(object.getLogId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("actor"),
								object.getDossierFileLogId(),
								String.valueOf(object.getActor()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE_LOG,
								WebKeys.EXTableName_DOSSIER_FILE_LOG,
								columnNames.getString("dossierFileLogIdNew"),
								object.getDossierFileLogId(), StringPool.BLANK);

						_log.info("=====dossierFileLogId:"
								+ object.getDossierFileLogId());
						i++;

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
