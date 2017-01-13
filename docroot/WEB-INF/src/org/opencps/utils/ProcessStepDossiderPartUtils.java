
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ProcessStepDossierPart;
import org.opencps.processmgt.service.ProcessStepDossierPartLocalServiceUtil;

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

public class ProcessStepDossiderPartUtils {

	private static Log _log = LogFactoryUtil.getLog(ProcessStepDossiderPartUtils.class);

	public void fetchProcessStepDossierPart(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ProcessStepDossierPart> List =
				ProcessStepDossierPartLocalServiceUtil.getProcessStepDossierParts(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_ProcessStepDossierPart,
					WebKeys.PROCESSSTEP_DOSSIERPART, WebKeys.ProcessStepDossierPartColumns);

			_log.info("=====fetching...processStepDossierPart");

			for (ProcessStepDossierPart object : List) {

				long proStepDoPartId =
					CounterLocalServiceUtil.increment(WebKeys.PROCESSSTEP_DOSSIERPART);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), proStepDoPartId);

				JSONObject columnNames = WebKeys.getProcessStepDossierPartColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESSSTEP_DOSSIERPART,
					WebKeys.EXTableName_ProcessStepDossierPart,
					columnNames.getString("processStepId"), proStepDoPartId,
					String.valueOf(object.getProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESSSTEP_DOSSIERPART,
					WebKeys.EXTableName_ProcessStepDossierPart,
					columnNames.getString("dossierPartId"), proStepDoPartId,
					String.valueOf(object.getDossierPartId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.PROCESSSTEP_DOSSIERPART,
					WebKeys.EXTableName_ProcessStepDossierPart, columnNames.getString("readOnly"),
					proStepDoPartId, String.valueOf(object.getReadOnly()));

			}
			_log.info("=====fetdone...processStepDossierPart");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProcessStepDossierPart(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(
				WebKeys.EXTableName_ProcessStepDossierPart, WebKeys.PROCESSSTEP_DOSSIERPART);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PROCESSSTEP_DOSSIERPART,
					WebKeys.EXTableName_ProcessStepDossierPart, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getProcessStepDossierPartColumnNames();

				JSONObject processStepColNames = WebKeys.getProcessStepColumnNames();

				JSONObject dossierPartColNames = WebKeys.getDossierPartColumnNames();

				// /
				String processStepId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("processStepId"), row.getClassPK(), StringPool.BLANK);

				String processStepIdNew = StringPool.BLANK;

				if (Validator.isNotNull(processStepId)) {

					processStepIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
							WebKeys.EXTableName_ProcessStep,
							processStepColNames.getString("processStepIdNew"),
							Long.valueOf(processStepId), StringPool.BLANK);
				}
				String dossierPartId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("dossierPartId"), row.getClassPK(), StringPool.BLANK);

				String dossierPartIdNew = StringPool.BLANK;

				if (Validator.isNotNull(dossierPartId)) {

					dossierPartIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							dossierPartColNames.getString("dossierPartIdNew"),
							Long.valueOf(dossierPartId), StringPool.BLANK);
				}

				String readOnly =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("readOnly"), row.getClassPK(), StringPool.BLANK);

				if (processStepIdNew.trim().length() > 0 && dossierPartIdNew.trim().length() > 0) {

					ProcessStepDossierPartLocalServiceUtil.addPSDP(
						Long.valueOf(processStepIdNew), Long.valueOf(dossierPartIdNew),
						Validator.isNotNull(readOnly) ? Boolean.valueOf(readOnly) : true);

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchProcessStepDossierPart2(ThemeDisplay themeDisplay,
			ProcessStepDossierPart object) {

		try {

			if (Validator.isNotNull(object)) {

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils.checkTable(companyId,
						WebKeys.EXTableName_ProcessStepDossierPart,
						WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.ProcessStepDossierPartColumns);

				long proStepDoPartId = CounterLocalServiceUtil
						.increment(WebKeys.PROCESSSTEP_DOSSIERPART);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						proStepDoPartId);

				JSONObject columnNames = WebKeys
						.getProcessStepDossierPartColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("processStepId"),
						proStepDoPartId,
						String.valueOf(object.getProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("dossierPartId"),
						proStepDoPartId,
						String.valueOf(object.getDossierPartId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PROCESSSTEP_DOSSIERPART,
						WebKeys.EXTableName_ProcessStepDossierPart,
						columnNames.getString("readOnly"), proStepDoPartId,
						String.valueOf(object.getReadOnly()));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
