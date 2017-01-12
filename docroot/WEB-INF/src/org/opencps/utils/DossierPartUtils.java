package org.opencps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.NoSuchDossierPartException;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

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

public class DossierPartUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierPartUtils.class);

	public void fetchDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierPart> List = DossierPartLocalServiceUtil
					.getDossierParts(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DossierPart, WebKeys.DOSSIER_PART,
					WebKeys.DossierPartColumns);
			int i = 1;
			for (DossierPart object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierpartId());

				JSONObject ColumnNames = WebKeys.getDossierPartColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("dossierTemplateId"),
						object.getDossierpartId(),
						String.valueOf(object.getDossierTemplateId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("partNo"),
						object.getDossierpartId(), object.getPartNo());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("partName"),
						object.getDossierpartId(), object.getPartName());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("partTip"),
						object.getDossierpartId(), object.getPartTip());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("partType"),
						object.getDossierpartId(),
						String.valueOf(object.getPartType()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("parentId"),
						object.getDossierpartId(),
						String.valueOf(object.getParentId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("sibling"),
						object.getDossierpartId(),
						String.valueOf(object.getSibling()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("treeIndex"),
						object.getDossierpartId(), object.getTreeIndex());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("formScript"),
						object.getDossierpartId(), object.getFormScript());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("formReport"),
						object.getDossierpartId(), object.getFormReport());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("spamleData"),
						object.getDossierpartId(), object.getSampleData());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("required"),
						object.getDossierpartId(),
						String.valueOf(object.getRequired()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("templateFileNo"),
						object.getDossierpartId(), object.getTemplateFileNo());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER_PART, WebKeys.EXTableName_DossierPart,
						ColumnNames.getString("dossierPartIdNew"),
						object.getDossierpartId(), StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====ServiceProcessId:" + object.getDossierpartId());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossierPart(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DossierPart,
					WebKeys.DOSSIER_PART);

			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
					WebKeys.EXTableName_DossierPart, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			_log.info("=====adding..dossierPart");
			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);
				DossierPart dossierPart = null;
				String treeIndex = StringPool.BLANK;

				_log.info("*i:" + i);

				JSONObject columnNames = WebKeys.getDossierPartColumnNames();

				JSONObject dossierTplColNames = WebKeys
						.getDossierTemplateColumnNames();

				String dossierTemplateId = ExpandoValueLocalServiceUtil
						.getData(themeDisplay.getCompanyId(),
								WebKeys.DOSSIER_PART,
								WebKeys.EXTableName_DossierPart,
								columnNames.getString("dossierTemplateId"),
								row.getClassPK(), StringPool.BLANK);

				String dossierTemplateIdNew = StringPool.BLANK;

				if (Validator.isNotNull(dossierTemplateId)) {
					dossierTemplateIdNew = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER_TEMPLATE,
									WebKeys.EXTableName_DossierTemplate,
									dossierTplColNames
											.getString("dossierTemplateIdNew"),
									Long.valueOf(dossierTemplateId),
									StringPool.BLANK);
				}

				String partNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("partNo"), row.getClassPK(),
						StringPool.BLANK);

				String partName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("partName"), row.getClassPK(),
						StringPool.BLANK);

				String partTip = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("partTip"), row.getClassPK(),
						StringPool.BLANK);

				String partType = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("partType"), row.getClassPK(),
						StringPool.BLANK);

				String sibling = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("sibling"), row.getClassPK(),
						StringPool.BLANK);

				String formScript = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("formScript"), row.getClassPK(),
						StringPool.BLANK);

				String formReport = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("formReport"), row.getClassPK(),
						StringPool.BLANK);

				String spamleData = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("spamleData"), row.getClassPK(),
						StringPool.BLANK);

				String required = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("required"), row.getClassPK(),
						StringPool.BLANK);

				String templateFileNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
						WebKeys.EXTableName_DossierPart,
						columnNames.getString("templateFileNo"),
						row.getClassPK(), StringPool.BLANK);

				try {

					if (Validator.isNotNull(dossierTemplateIdNew)) {
						dossierPart = DossierPartLocalServiceUtil
								.getDossierPartByT_PN(
										Long.valueOf(dossierTemplateIdNew),
										partNo);
					}
				} catch (NoSuchDossierPartException e) {

				}
				if (Validator.isNull(dossierPart)) {

					long dossierPartId = CounterLocalServiceUtil
							.increment(WebKeys.DOSSIER_PART);

					dossierPart = DossierPartLocalServiceUtil
							.createDossierPart(dossierPartId);

					if (Validator.isNotNull(dossierTemplateIdNew)) {

						dossierPart.setDossierTemplateId(Long
								.valueOf(dossierTemplateIdNew));
					}

					dossierPart.setPartNo(partNo);
					dossierPart.setPartName(partName);
					dossierPart.setPartTip(String.valueOf(row.getClassPK()));
					dossierPart
							.setPartType(Validator.isNotNull(partType) ? Integer
									.valueOf(partType) : 0);
					dossierPart
							.setSibling(Validator.isNotNull(sibling) ? Double
									.valueOf(sibling) : 0);
					//dossierPart.setTreeIndex(String.valueOf(row.getClassPK()));
					dossierPart.setFormScript(formScript);
					dossierPart.setFormReport(formReport);
					dossierPart.setSampleData(spamleData);
					dossierPart
							.setRequired(Validator.isNotNull(required) ? Boolean
									.valueOf(required) : false);
					dossierPart.setTemplateFileNo(templateFileNo);

					dossierPart.setCompanyId(themeDisplay.getCompanyId());
					dossierPart.setUserId(serviceContext.getUserId());
					dossierPart.setGroupId(themeDisplay.getScopeGroupId());
					dossierPart.setCreateDate(new Date());

					DossierPartLocalServiceUtil.updateDossierPart(dossierPart);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("dossierPartIdNew"),
							row.getClassPK(),
							String.valueOf(dossierPart.getDossierpartId()));
				} else {

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("dossierPartIdNew"),
							dossierPart.getDossierpartId(),
							String.valueOf(dossierPart.getDossierpartId()));

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("parentId"),
							dossierPart.getDossierpartId(),
							String.valueOf(dossierPart.getParentId()));

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("treeIndex"),
							dossierPart.getDossierpartId(),
							dossierPart.getTreeIndex());

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreeIndex(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DossierPart> List = DossierPartLocalServiceUtil
					.getDossierParts(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String parentItemId = StringPool.BLANK;
			String treeIndex = StringPool.BLANK;
			String dossierPartIdNew = StringPool.BLANK;

			JSONObject columnNames = WebKeys.getDossierPartColumnNames();
			int i = 1;

			for (DossierPart object : List) {

				_log.info("*i:" + i);
				_log.info("=====DossierpartId:" + object.getDossierpartId());

				String classPK = object.getPartTip();

				if (Validator.isDigit(classPK)) {

					long classPKLong = Long.valueOf(classPK);

					dossierPartIdNew = StringPool.BLANK;

					parentItemId = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("parentId"), classPKLong,
							StringPool.BLANK);
					treeIndex = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER_PART,
							WebKeys.EXTableName_DossierPart,
							columnNames.getString("treeIndex"), classPKLong,
							StringPool.BLANK);

					if (Validator.isNotNull(treeIndex)) {

						treeIndex = CommonUtils.generateTreeIndex(treeIndex,
								StringPool.PERIOD,
								WebKeys.EXTableName_DossierPart,
								columnNames.getString("dossierPartIdNew"),
								WebKeys.DOSSIER_PART, companyId);

						object.setTreeIndex(treeIndex);
					}
					if (Validator.isNotNull(parentItemId)) {

						dossierPartIdNew = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.DOSSIER_PART,
										WebKeys.EXTableName_DossierPart,
										columnNames
												.getString("dossierPartIdNew"),
										Long.valueOf(parentItemId),
										StringPool.BLANK);

						if (Validator.isNotNull(dossierPartIdNew)) {
							object.setParentId(Long.valueOf(dossierPartIdNew));
						}
					}

					object.setPartTip(object.getPartName());
					object.setModifiedDate(new Date());
					DossierPartLocalServiceUtil.updateDossierPart(object);
				}
				
				
				

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
