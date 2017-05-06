
package org.opencps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.servicemgt.model.TemplateFile;
import org.opencps.servicemgt.service.TemplateFileLocalServiceUtil;

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

public class TemplateFileUtils {

	private static Log _log = LogFactoryUtil.getLog(TemplateFileUtils.class);

	public void fetchTemplateFile(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<TemplateFile> templateFileList =
				TemplateFileLocalServiceUtil.getTemplateFiles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_TEMPLATE_FILE, WebKeys.TEMPLATE_FILE,
					WebKeys.TemplateFile_ColumnNames);
			int i = 1;
			for (TemplateFile templateFile : templateFileList) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), templateFile.getTemplatefileId());

				JSONObject serviceInfoColumnNames = WebKeys.getTemplateFileColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.TEMPLATE_FILE, WebKeys.EXTableName_TEMPLATE_FILE,
					serviceInfoColumnNames.getString("fileName"), templateFile.getTemplatefileId(),
					templateFile.getFileName());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.TEMPLATE_FILE, WebKeys.EXTableName_TEMPLATE_FILE,
					serviceInfoColumnNames.getString("fileNo"), templateFile.getTemplatefileId(),
					templateFile.getFileNo());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.TEMPLATE_FILE, WebKeys.EXTableName_TEMPLATE_FILE,
					serviceInfoColumnNames.getString("fileEntryId"),
					templateFile.getTemplatefileId(), String.valueOf(templateFile.getFileEntryId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.TEMPLATE_FILE, WebKeys.EXTableName_TEMPLATE_FILE,
					serviceInfoColumnNames.getString("templateFileIdNew"),
					templateFile.getTemplatefileId(), StringPool.BLANK);

				_log.info("**i=" + i);
				_log.info("=====TemplatefileId:" + templateFile.getTemplatefileId());
				i++;

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTemplateFile(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_TEMPLATE_FILE, WebKeys.TEMPLATE_FILE);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
					WebKeys.EXTableName_TEMPLATE_FILE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				TemplateFile templateFile = null;

				row = rows.get(i);
				
				_log.info("*i:"+i);
				_log.info("=====row:"+row.getClassPK());

				JSONObject columnNames = WebKeys.getTemplateFileColumnNames();

				String fileName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE, columnNames.getString("fileName"),
						row.getClassPK(), StringPool.BLANK);

				String fileNo =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE, columnNames.getString("fileNo"),
						row.getClassPK(), StringPool.BLANK);
				_log.info("=====fileNo:"+fileNo);
				try {
					templateFile =
						TemplateFileLocalServiceUtil.getTemplateFileByNo(
							themeDisplay.getScopeGroupId(), fileNo);
				}
				catch (Exception e) {

				}

				if (Validator.isNull(templateFile)) {

					long fileEntryIdNew = 0;

					String fileEntryId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
							WebKeys.EXTableName_TEMPLATE_FILE,
							columnNames.getString("fileEntryId"), row.getClassPK(),
							StringPool.BLANK);

					if (Validator.isNotNull(fileEntryId)) {

						fileEntryIdNew =
							commonUtils.getFileEntryId(
								actionRequest, actionResponse, Long.valueOf(fileEntryId),0);
					}

					long templateFileId = CounterLocalServiceUtil.increment(WebKeys.TEMPLATE_FILE);

					templateFile = TemplateFileLocalServiceUtil.createTemplateFile(templateFileId);

					templateFile.setFileName(fileName);
					templateFile.setFileNo(fileNo);
					templateFile.setFileEntryId(fileEntryIdNew);

					templateFile.setCompanyId(themeDisplay.getCompanyId());
					templateFile.setGroupId(themeDisplay.getScopeGroupId());
					templateFile.setUserId(serviceContext.getUserId());
					
					templateFile.setCreateDate(new Date());
					templateFile.setModifiedDate(new Date());

					TemplateFileLocalServiceUtil.addTemplateFile(templateFile);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						columnNames.getString("templateFileIdNew"), row.getClassPK(),
						String.valueOf(templateFile.getTemplatefileId()));
					_log.info("=====Add Success=====TemplateFileId:"+templateFile.getTemplatefileId());
				}
				else {

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						columnNames.getString("templateFileIdNew"), row.getClassPK(),
						String.valueOf(templateFile.getTemplatefileId()));
					
					_log.info("=====existed templateFileId:"+templateFile.getTemplatefileId());

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchTemplateFile2(ThemeDisplay themeDisplay,
			TemplateFile templateFile) {

		try {

			if (Validator.isNotNull(templateFile)) {

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils
						.checkTable(companyId,
								WebKeys.EXTableName_TEMPLATE_FILE,
								WebKeys.TEMPLATE_FILE,
								WebKeys.TemplateFile_ColumnNames);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						templateFile.getTemplatefileId());

				JSONObject serviceInfoColumnNames = WebKeys
						.getTemplateFileColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						serviceInfoColumnNames.getString("fileName"),
						templateFile.getTemplatefileId(),
						templateFile.getFileName());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						serviceInfoColumnNames.getString("fileNo"),
						templateFile.getTemplatefileId(),
						templateFile.getFileNo());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						serviceInfoColumnNames.getString("fileEntryId"),
						templateFile.getTemplatefileId(),
						String.valueOf(templateFile.getFileEntryId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.TEMPLATE_FILE,
						WebKeys.EXTableName_TEMPLATE_FILE,
						serviceInfoColumnNames.getString("templateFileIdNew"),
						templateFile.getTemplatefileId(), StringPool.BLANK);

				_log.info("=====TemplatefileId:"
						+ templateFile.getTemplatefileId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
