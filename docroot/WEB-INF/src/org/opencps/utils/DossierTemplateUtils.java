package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.dossiermgt.NoSuchDossierTemplateException;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class DossierTemplateUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierTemplateUtils.class);

	public void fetchDossierTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierTemplate> List = DossierTemplateLocalServiceUtil
					.getDossierTemplates(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DossierTemplate,
					WebKeys.DOSSIER_TEMPLATE, WebKeys.DossierTemplateColumns);
			int i = 1;
			for (DossierTemplate object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierTemplateId());

				JSONObject ColumnNames = WebKeys
						.getDossierTemplateColumnNames();

				ExpandoValueLocalServiceUtil
						.addValue(companyId, WebKeys.DOSSIER_TEMPLATE,
								WebKeys.EXTableName_DossierTemplate,
								ColumnNames.getString("templateName"),
								object.getDossierTemplateId(),
								object.getTemplateName());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_TEMPLATE,
						WebKeys.EXTableName_DossierTemplate,
						ColumnNames.getString("description"),
						object.getDossierTemplateId(), object.getDescription());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_TEMPLATE,
						WebKeys.EXTableName_DossierTemplate,
						ColumnNames.getString("templateNo"),
						object.getDossierTemplateId(), object.getTemplateNo());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_TEMPLATE,
						WebKeys.EXTableName_DossierTemplate,
						ColumnNames.getString("dossierTemplateIdNew"),
						object.getDossierTemplateId(), StringPool.BLANK);

				_log.info("**i=" + i);
				_log.info("=====DossierTemplateId:"
						+ object.getDossierTemplateId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossierTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DossierTemplate,
					WebKeys.DOSSIER_TEMPLATE);

			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
						WebKeys.EXTableName_DossierTemplate);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
					WebKeys.EXTableName_DossierTemplate, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				DossierTemplate dossierTemplate = null;

				JSONObject columnNames = WebKeys
						.getDossierTemplateColumnNames();

				String templateNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
						WebKeys.EXTableName_DossierTemplate,
						columnNames.getString("templateNo"), row.getClassPK(),
						StringPool.BLANK);

				try {

					dossierTemplate = DossierTemplateLocalServiceUtil
							.getDossierTemplate(templateNo);
				} catch (NoSuchDossierTemplateException e) {

				}

				if (Validator.isNull(dossierTemplate)) {
					String templateName = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(),
							WebKeys.DOSSIER_TEMPLATE,
							WebKeys.EXTableName_DossierTemplate,
							columnNames.getString("templateName"),
							row.getClassPK(), StringPool.BLANK);

					String description = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(),
							WebKeys.DOSSIER_TEMPLATE,
							WebKeys.EXTableName_DossierTemplate,
							columnNames.getString("description"),
							row.getClassPK(), StringPool.BLANK);

					long dossierTemplateId = CounterLocalServiceUtil
							.increment(WebKeys.DOSSIER_TEMPLATE);

					dossierTemplate = DossierTemplateLocalServiceUtil
							.createDossierTemplate(dossierTemplateId);

					dossierTemplate.setTemplateName(templateName);
					dossierTemplate.setDescription(description);
					dossierTemplate.setTemplateNo(templateNo);

					dossierTemplate.setCompanyId(themeDisplay.getCompanyId());
					dossierTemplate.setGroupId(themeDisplay.getScopeGroupId());
					dossierTemplate.setUserId(themeDisplay.getUserId());

					DossierTemplateLocalServiceUtil
							.updateDossierTemplate(dossierTemplate);

					ExpandoValueLocalServiceUtil.addValue(themeDisplay
							.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
							WebKeys.EXTableName_DossierTemplate, columnNames
									.getString("dossierTemplateIdNew"), row
									.getClassPK(), String
									.valueOf(dossierTemplate
											.getDossierTemplateId()));
				} else {

					ExpandoValueLocalServiceUtil.addValue(themeDisplay
							.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
							WebKeys.EXTableName_DossierTemplate, columnNames
									.getString("dossierTemplateIdNew"), row
									.getClassPK(), String
									.valueOf(dossierTemplate
											.getDossierTemplateId()));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
