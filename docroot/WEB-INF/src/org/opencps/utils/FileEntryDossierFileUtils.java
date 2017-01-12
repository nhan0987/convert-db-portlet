package org.opencps.utils;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class FileEntryDossierFileUtils {

	private static Log _log = LogFactoryUtil
			.getLog(FileEntryDossierFileUtils.class);

	public void saveFileEntryId(ActionRequest actionRequest,
			ActionResponse actionResponse, long fileEntryIdNew,
			long dossierFileId, long fileEntryIdOld) throws Exception {

		try {
			
			if(fileEntryIdOld >0 && fileEntryIdOld > 0){

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_FILEENTRY_DOSSIERFILE,
					WebKeys.FILEENTRY_DOSSIERFILE);
			

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_FILEENTRY_DOSSIERFILE,
					WebKeys.FILEENTRY_DOSSIERFILE,
					WebKeys.FILEENTRY_DOSSIERFILEColumns);

			ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
					fileEntryIdOld);

			JSONObject columnNames = WebKeys.getFILEENTRY_DOSSIERFILEColumnNames();

			ExpandoValueLocalServiceUtil.addValue(companyId,
					WebKeys.FILEENTRY_DOSSIERFILE, WebKeys.EXTableName_FILEENTRY_DOSSIERFILE,
					columnNames.getString("dossierFileId"), fileEntryIdOld,
					String.valueOf(dossierFileId));

			ExpandoValueLocalServiceUtil.addValue(companyId,
					WebKeys.FILEENTRY_DOSSIERFILE, WebKeys.EXTableName_FILEENTRY_DOSSIERFILE,
					columnNames.getString("fileEntryId"), fileEntryIdOld,
					String.valueOf(fileEntryIdNew));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
