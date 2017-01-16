package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

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

public class DossierFileUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierFileUtils.class);

	public void fetchDossierFile(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierFile> List = DossierFileLocalServiceUtil
					.getDossierFiles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DOSSIER_FILE, WebKeys.DOSSIER_FILE,
					WebKeys.DOSSIER_FILEColumns);

			int i = 1;
			for (DossierFile object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierFileId());

				JSONObject columnNames = WebKeys.getDOSSIER_FILEColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("userId"),
						object.getDossierFileId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("createDate"), object
								.getDossierFileId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("modifiedDate"), object
								.getDossierFileId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierId"),
						object.getDossierFileId(),
						String.valueOf(object.getDossierId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierPartId"),
						object.getDossierFileId(),
						String.valueOf(object.getDossierPartId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("templateFileNo"),
						object.getDossierFileId(),
						String.valueOf(object.getTemplateFileNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("ownerOrganizationId"),
						object.getDossierFileId(),
						String.valueOf(object.getOwnerOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("displayName"),
						object.getDossierFileId(),
						String.valueOf(object.getDisplayName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("formData"),
						object.getDossierFileId(),
						String.valueOf(object.getFormData()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("fileEntryId"),
						object.getDossierFileId(),
						String.valueOf(object.getFileEntryId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileType"),
						object.getDossierFileId(),
						String.valueOf(object.getDossierFileType()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileNo"),
						object.getDossierFileId(),
						String.valueOf(object.getDossierFileNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileDate"), object
								.getDossierFileId(), DateTimeUtil
								.convertDateToString(
										object.getDossierFileDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("removed"),
						object.getDossierFileId(),
						String.valueOf(object.getRemoved()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("original"),
						object.getDossierFileId(),
						String.valueOf(object.getOriginal()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("syncStatus"),
						object.getDossierFileId(),
						String.valueOf(object.getSyncStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("oid"),
						object.getDossierFileId(),
						String.valueOf(object.getOid()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("version"),
						object.getDossierFileId(),
						String.valueOf(object.getVersion()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signed"),
						object.getDossierFileId(),
						String.valueOf(object.getSigned()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileMark"),
						object.getDossierFileId(),
						String.valueOf(object.getDossierFileMark()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signCheck"),
						object.getDossierFileId(),
						String.valueOf(object.getSignCheck()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signInfo"),
						object.getDossierFileId(),
						String.valueOf(object.getSignInfo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_FILE, WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileIdNew"),
						object.getDossierId(), StringPool.BLANK);

				_log.info("=====DossierFileId:" + object.getDossierFileId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossierFile(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DOSSIER_FILE,
					WebKeys.DOSSIER_FILE);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
					WebKeys.EXTableName_DOSSIER_FILE, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("*i:" + i);
				_log.info("=====row.getClassPK():" + row.getClassPK());

				JSONObject columnNames = WebKeys.getDOSSIER_FILEColumnNames();

				JSONObject orgColumnNames = WebKeys
						.getOrganizationColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject dossierPartColumnNames = WebKeys
						.getDossierPartColumnNames();

				JSONObject dossierColumnNames = WebKeys.getDOSSIERColumnNames();

				String createDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("createDate"), row.getClassPK(),
						StringPool.BLANK);

				String modifiedDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("modifiedDate"),
						row.getClassPK(), StringPool.BLANK);

				String templateFileNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("templateFileNo"),
						row.getClassPK(), StringPool.BLANK);

				String displayName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("displayName"), row.getClassPK(),
						StringPool.BLANK);

				String formData = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("formData"), row.getClassPK(),
						StringPool.BLANK);

				String dossierFileType = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileType"),
						row.getClassPK(), StringPool.BLANK);

				String dossierFileNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileNo"),
						row.getClassPK(), StringPool.BLANK);

				String dossierFileDate = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileDate"),
						row.getClassPK(), StringPool.BLANK);

				String removed = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("removed"), row.getClassPK(),
						StringPool.BLANK);

				String original = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("original"), row.getClassPK(),
						StringPool.BLANK);

				String syncStatus = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("syncStatus"), row.getClassPK(),
						StringPool.BLANK);

				String oid = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("oid"), row.getClassPK(),
						StringPool.BLANK);

				String version = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("version"), row.getClassPK(),
						StringPool.BLANK);

				String signed = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signed"), row.getClassPK(),
						StringPool.BLANK);

				String dossierFileMark = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("dossierFileMark"),
						row.getClassPK(), StringPool.BLANK);

				String signCheck = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signCheck"), row.getClassPK(),
						StringPool.BLANK);

				String signInfo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
						WebKeys.EXTableName_DOSSIER_FILE,
						columnNames.getString("signInfo"), row.getClassPK(),
						StringPool.BLANK);

				// ///////////////////////////////////////////////////////////////////////

				long userIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE, WebKeys.DOSSIER_FILE,
						columnNames.getString("userId"), row.getClassPK(),
						WebKeys.EXTableName_User, WebKeys.USERS,
						userColumnNames.getString("userIdNew"), themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long ownerOrganizationIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER_FILE,
								WebKeys.DOSSIER_FILE,
								columnNames.getString("ownerOrganizationId"),
								row.getClassPK(),
								WebKeys.EXTableName_Organization,
								WebKeys.ORGANIZATION,
								orgColumnNames.getString("organizationIdNew"),
								themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long dossierIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE, WebKeys.DOSSIER_FILE,
						columnNames.getString("dossierId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						dossierColumnNames.getString("dossierIdNew"),
						themeDisplay);

				// ///////////////////////////////////////////////////////////////////////////

				long dossierPartIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_DOSSIER_FILE, WebKeys.DOSSIER_FILE,
						columnNames.getString("dossierPartId"),
						row.getClassPK(), WebKeys.EXTableName_DossierPart,
						WebKeys.DOSSIER_PART,
						dossierPartColumnNames.getString("dossierPartIdNew"),
						themeDisplay);

				// ///////////////////////////////////////////////////////////////////////////

				if (dossierIdNew > 0) {

					Dossier dossier = null;
					long fileEntryIdNew = 0;

					String fileEntryId = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
							WebKeys.EXTableName_DOSSIER_FILE,
							columnNames.getString("fileEntryId"),
							row.getClassPK(), StringPool.BLANK);

					if (dossierIdNew > 0 && Validator.isNotNull(fileEntryId)) {

						dossier = DossierLocalServiceUtil
								.getDossier(dossierIdNew);

						if (Validator.isNotNull(dossier)) {
							fileEntryIdNew = commonUtils.getFileEntryId(
									actionRequest, actionResponse,
									Long.valueOf(fileEntryId),
									dossier.getFolderId());
						}
					}

					// ////////////////////////////////////////////////////////////////////////////

					DossierFile dossierFile = null;

					dossierFile = DossierFileLocalServiceUtil
							.createDossierFile(CounterLocalServiceUtil
									.increment(WebKeys.DOSSIER_FILE));

					dossierFile
							.setCreateDate(Validator.isNotNull(createDate) ? DateTimeUtil
									.convertStringToFullDate(createDate) : null);

					dossierFile.setModifiedDate(Validator
							.isNotNull(modifiedDate) ? DateTimeUtil
							.convertStringToFullDate(modifiedDate) : null);
					dossierFile.setUserId(userIdNew);
					dossierFile.setDossierId(dossierIdNew);
					dossierFile.setDossierPartId(dossierPartIdNew);
					dossierFile.setTemplateFileNo(templateFileNo);
					dossierFile.setOwnerOrganizationId(ownerOrganizationIdNew);
					dossierFile.setDisplayName(displayName);
					dossierFile.setFormData(formData);
					dossierFile.setFileEntryId(fileEntryIdNew);
					dossierFile.setDossierFileMark(Validator
							.isNotNull(dossierFileMark) ? Integer
							.valueOf(dossierFileMark) : 0);
					dossierFile.setDossierFileType(Validator
							.isNotNull(dossierFileType) ? Integer
							.valueOf(dossierFileType) : 0);
					dossierFile.setDossierFileNo(dossierFileNo);
					dossierFile.setDossierFileDate(Validator
							.isNotNull(dossierFileDate) ? DateTimeUtil
							.convertStringToFullDate(dossierFileDate) : null);
					dossierFile
							.setRemoved(Validator.isNotNull(removed) ? Integer
									.valueOf(removed) : 0);
					dossierFile
							.setOriginal(Validator.isNotNull(original) ? Integer
									.valueOf(original) : 0);
					dossierFile
							.setSyncStatus(Validator.isNotNull(syncStatus) ? Integer
									.valueOf(syncStatus) : 0);
					dossierFile.setOid(oid);
					dossierFile
							.setVersion(Validator.isNotNull(version) ? Integer
									.valueOf(version) : 0);
					dossierFile.setSigned(Validator.isNotNull(signed) ? Boolean
							.valueOf(signed) : false);
					dossierFile
							.setSignCheck(Validator.isNotNull(signCheck) ? Integer
									.valueOf(signCheck) : 0);
					dossierFile.setSignInfo(signInfo);

					dossierFile.setCompanyId(themeDisplay.getCompanyId());
					dossierFile.setGroupId(themeDisplay.getScopeGroupId());

					DossierFileLocalServiceUtil.addDossierFile(dossierFile);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_FILE,
							WebKeys.EXTableName_DOSSIER_FILE,
							columnNames.getString("dossierFileIdNew"),
							row.getClassPK(),
							String.valueOf(dossierFile.getDossierFileId()));

					FileEntryDossierFileUtils fileEntryDossierFile = new FileEntryDossierFileUtils();

					fileEntryDossierFile.saveFileEntryId(
							actionRequest,
							actionResponse,
							fileEntryIdNew,
							dossierFile.getDossierFileId(),
							Validator.isNotNull(fileEntryId) ? Long
									.valueOf(fileEntryId) : 0);

					_log.info("=====add Success=====dossierFileId:"
							+ dossierFile.getDossierFileId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchDossierFile2(ThemeDisplay themeDisplay, long dossierId)
			throws Exception {

		try {

			if (dossierId > 0) {

				long companyId = themeDisplay.getCompanyId();

				List<DossierFile> List = new ArrayList<DossierFile>();
				List = DossierFileLocalServiceUtil
						.getDossierFileByDossierId(dossierId);

				if (List.size() > 0) {

					CommonUtils commonUtils = new CommonUtils();

					ExpandoTable expandoTable = commonUtils.checkTable(
							companyId, WebKeys.EXTableName_DOSSIER_FILE,
							WebKeys.DOSSIER_FILE, WebKeys.DOSSIER_FILEColumns);
					

					int i = 1;
					for (DossierFile object : List) {

						_log.info("*i:" + i);
						_log.info("=====dossierId:"+dossierId);

						ExpandoRowLocalServiceUtil.addRow(
								expandoTable.getTableId(),
								object.getDossierFileId());

						JSONObject columnNames = WebKeys
								.getDOSSIER_FILEColumnNames();

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("userId"),
								object.getDossierFileId(),
								String.valueOf(object.getUserId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE, columnNames
										.getString("createDate"), object
										.getDossierFileId(),
								DateTimeUtil.convertDateToString(
										object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE, columnNames
										.getString("modifiedDate"), object
										.getDossierFileId(),
								DateTimeUtil.convertDateToString(
										object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierId"),
								object.getDossierFileId(),
								String.valueOf(object.getDossierId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierPartId"),
								object.getDossierFileId(),
								String.valueOf(object.getDossierPartId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("templateFileNo"),
								object.getDossierFileId(),
								String.valueOf(object.getTemplateFileNo()));

						ExpandoValueLocalServiceUtil
								.addValue(
										companyId,
										WebKeys.DOSSIER_FILE,
										WebKeys.EXTableName_DOSSIER_FILE,
										columnNames
												.getString("ownerOrganizationId"),
										object.getDossierFileId(),
										String.valueOf(object
												.getOwnerOrganizationId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("displayName"),
								object.getDossierFileId(),
								String.valueOf(object.getDisplayName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("formData"),
								object.getDossierFileId(),
								String.valueOf(object.getFormData()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("fileEntryId"),
								object.getDossierFileId(),
								String.valueOf(object.getFileEntryId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierFileType"),
								object.getDossierFileId(),
								String.valueOf(object.getDossierFileType()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierFileNo"),
								object.getDossierFileId(),
								String.valueOf(object.getDossierFileNo()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE, columnNames
										.getString("dossierFileDate"), object
										.getDossierFileId(),
								DateTimeUtil.convertDateToString(
										object.getDossierFileDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("removed"),
								object.getDossierFileId(),
								String.valueOf(object.getRemoved()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("original"),
								object.getDossierFileId(),
								String.valueOf(object.getOriginal()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("syncStatus"),
								object.getDossierFileId(),
								String.valueOf(object.getSyncStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("oid"),
								object.getDossierFileId(),
								String.valueOf(object.getOid()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("version"),
								object.getDossierFileId(),
								String.valueOf(object.getVersion()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("signed"),
								object.getDossierFileId(),
								String.valueOf(object.getSigned()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierFileMark"),
								object.getDossierFileId(),
								String.valueOf(object.getDossierFileMark()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("signCheck"),
								object.getDossierFileId(),
								String.valueOf(object.getSignCheck()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("signInfo"),
								object.getDossierFileId(),
								String.valueOf(object.getSignInfo()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DOSSIER_FILE,
								WebKeys.EXTableName_DOSSIER_FILE,
								columnNames.getString("dossierFileIdNew"),
								object.getDossierId(), StringPool.BLANK);

						_log.info("=====dossierFileId:"
								+ object.getDossierFileId());
						i++;

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
