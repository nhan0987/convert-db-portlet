package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.usermgt.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class WorkingUnitUtils {

	private static Log _log = LogFactoryUtil.getLog(WorkingUnitUtils.class);

	public void fetchWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<WorkingUnit> List = WorkingUnitLocalServiceUtil
					.getWorkingUnits(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_WorkingUnit, WebKeys.WORKING_UNIT,
					WebKeys.WorkingUnitColumns);
			int i = 1;
			for (WorkingUnit object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getWorkingunitId());

				JSONObject columnNames = WebKeys.getWorkingUnitColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("name"),
						object.getWorkingunitId(),
						String.valueOf(object.getName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("enName"),
						object.getWorkingunitId(),
						String.valueOf(object.getEnName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("govAgencyCode"),
						object.getWorkingunitId(),
						String.valueOf(object.getGovAgencyCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("parentWorkingUnitId"),
						object.getWorkingunitId(),
						String.valueOf(object.getParentWorkingUnitId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("sibling"),
						object.getWorkingunitId(),
						String.valueOf(object.getSibling()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("treeIndex"),
						object.getWorkingunitId(),
						String.valueOf(object.getTreeIndex()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("address"),
						object.getWorkingunitId(),
						String.valueOf(object.getAddress()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("cityCode"),
						object.getWorkingunitId(),
						String.valueOf(object.getCityCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("districtCode"),
						object.getWorkingunitId(),
						String.valueOf(object.getDistrictCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("wardCode"),
						object.getWorkingunitId(),
						String.valueOf(object.getWardCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("telNo"),
						object.getWorkingunitId(),
						String.valueOf(object.getTelNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("faxNo"),
						object.getWorkingunitId(),
						String.valueOf(object.getFaxNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("email"),
						object.getWorkingunitId(),
						String.valueOf(object.getEmail()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("website"),
						object.getWorkingunitId(),
						String.valueOf(object.getWebsite()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("isEmployer"),
						object.getWorkingunitId(),
						String.valueOf(object.getIsEmployer()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("mappingOrganizationId"),
						object.getWorkingunitId(),
						String.valueOf(object.getMappingOrganisationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.WORKING_UNIT, WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("workingUnitIdNew"),
						object.getWorkingunitId(), StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====WorkingunitId:" + object.getWorkingunitId());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_WorkingUnit,
					WebKeys.WORKING_UNIT);

			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
						WebKeys.EXTableName_WorkingUnit);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
					WebKeys.EXTableName_WorkingUnit, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {
				WorkingUnit workingUnit = null;

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getWorkingUnitColumnNames();

				JSONObject orgColumnNames = WebKeys
						.getOrganizationColumnNames();

				// JSONObject dictItemColNames =
				// WebKeys.getDictItemColumnNames();

				String name = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
						WebKeys.EXTableName_WorkingUnit,
						columnNames.getString("name"), row.getClassPK(),
						StringPool.BLANK);

				long mappingOrganizationIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_WorkingUnit,
								WebKeys.WORKING_UNIT,
								columnNames.getString("mappingOrganizationId"),
								row.getClassPK(),
								WebKeys.EXTableName_Organization,
								WebKeys.ORGANIZATION,
								orgColumnNames.getString("organizationIdNew"),
								themeDisplay);

				Organization organization = null;

				try {
					organization = OrganizationLocalServiceUtil
							.getOrganization(themeDisplay.getCompanyId(), name);
				} catch (NoSuchOrganizationException e) {

				}

				if (Validator.isNotNull(organization)) {

					try {

						workingUnit = WorkingUnitLocalServiceUtil
								.fetchByMappingOrganisationId(
										themeDisplay.getScopeGroupId(),
										organization.getOrganizationId());
					} catch (NoSuchWorkingUnitException e) {

					}

					if (Validator.isNull(workingUnit)) {

						String govAgencyCode = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.WORKING_UNIT,
										WebKeys.EXTableName_WorkingUnit,
										columnNames.getString("govAgencyCode"),
										row.getClassPK(), StringPool.BLANK);

						String enName = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("enName"),
								row.getClassPK(), StringPool.BLANK);

						String sibling = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("sibling"),
								row.getClassPK(), StringPool.BLANK);

						String address = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("address"),
								row.getClassPK(), StringPool.BLANK);

						String cityCode = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("cityCode"),
								row.getClassPK(), StringPool.BLANK);

						String districtCode = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.WORKING_UNIT,
										WebKeys.EXTableName_WorkingUnit,
										columnNames.getString("districtCode"),
										row.getClassPK(), StringPool.BLANK);

						String wardCode = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("wardCode"),
								row.getClassPK(), StringPool.BLANK);

						String telNo = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("telNo"),
								row.getClassPK(), StringPool.BLANK);

						String faxNo = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("faxNo"),
								row.getClassPK(), StringPool.BLANK);

						String email = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("email"),
								row.getClassPK(), StringPool.BLANK);
						String website = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("website"),
								row.getClassPK(), StringPool.BLANK);

						String isEmployer = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.WORKING_UNIT,
										WebKeys.EXTableName_WorkingUnit,
										columnNames.getString("isEmployer"),
										row.getClassPK(), StringPool.BLANK);

						String treeIndex = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.WORKING_UNIT,
										WebKeys.EXTableName_WorkingUnit,
										columnNames.getString("treeIndex"),
										row.getClassPK(), StringPool.BLANK);

						long workingunitId = CounterLocalServiceUtil
								.increment(WebKeys.WORKING_UNIT);

						workingUnit = WorkingUnitLocalServiceUtil
								.createWorkingUnit(workingunitId);

						workingUnit.setName(name);
						workingUnit.setEnName(enName);
						workingUnit.setGovAgencyCode(govAgencyCode);

						workingUnit.setTreeIndex(treeIndex);
						workingUnit.setParentWorkingUnitId(row.getClassPK());
						workingUnit
								.setSibling(Validator.isNotNull(sibling) ? Integer
										.valueOf(sibling) : 0);
						workingUnit.setAddress(address);
						workingUnit.setCityCode(cityCode);
						workingUnit.setDistrictCode(districtCode);
						workingUnit.setWardCode(wardCode);
						workingUnit.setTelNo(telNo);
						workingUnit.setFaxNo(faxNo);
						workingUnit.setEmail(email);
						workingUnit.setWebsite(website);
						workingUnit.setIsEmployer(Boolean.valueOf(isEmployer));

						workingUnit.setCompanyId(themeDisplay.getCompanyId());
						workingUnit
								.setGroupId(serviceContext.getScopeGroupId());
						workingUnit.setUserId(serviceContext.getUserId());

						workingUnit.setMappingOrganisationId(mappingOrganizationIdNew);

						WorkingUnitLocalServiceUtil
								.updateWorkingUnit(workingUnit);

						ExpandoValueLocalServiceUtil
								.addValue(themeDisplay.getCompanyId(),
										WebKeys.WORKING_UNIT,
										WebKeys.EXTableName_WorkingUnit,
										columnNames
												.getString("workingUnitIdNew"),
										row.getClassPK(), String
												.valueOf(workingunitId));

						_log.info("=====add Success==workingunitId::"
								+ workingunitId);
					} else {
						ExpandoValueLocalServiceUtil.addValue(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("workingUnitIdNew"),
								row.getClassPK(),
								String.valueOf(workingUnit.getWorkingunitId()));
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreeIndexWorkingUnit(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<WorkingUnit> List = WorkingUnitLocalServiceUtil
					.getWorkingUnits(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String parentItemId = StringPool.BLANK;
			String treeIndex = StringPool.BLANK;
			String parentItemIdNew = StringPool.BLANK;

			JSONObject columnNames = WebKeys.getWorkingUnitColumnNames();
			int i = 1;

			for (WorkingUnit object : List) {

				_log.info("*i:" + i);
				_log.info("=====WorkingunitId:" + object.getWorkingunitId());

				String classPK = object.getTreeIndex();

				if (Validator.isDigit(classPK)) {

					long classPKLong = Long.valueOf(classPK);

					parentItemIdNew = StringPool.BLANK;

					parentItemId = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							columnNames.getString("parentWorkingUnitId"),
							classPKLong, StringPool.BLANK);
					treeIndex = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							columnNames.getString("treeIndex"), classPKLong,
							StringPool.BLANK);

					if (Validator.isNotNull(treeIndex)) {

						treeIndex = CommonUtils.generateTreeIndex(treeIndex,
								StringPool.PERIOD,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("workingUnitIdNew"),
								WebKeys.WORKING_UNIT, companyId);

						object.setTreeIndex(treeIndex);
					}
					if (Validator.isNotNull(parentItemId)) {

						parentItemIdNew = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("workingUnitIdNew"),
								Long.valueOf(parentItemId), StringPool.BLANK);

						if (Validator.isNotNull(parentItemIdNew)) {
							object.setParentWorkingUnitId(Long
									.valueOf(parentItemIdNew));
						}
					}

					WorkingUnitLocalServiceUtil.updateWorkingUnit(object);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreeIndexWorkingUnit1(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<WorkingUnit> List = WorkingUnitLocalServiceUtil
					.getWorkingUnits(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String parentItemId = StringPool.BLANK;
			String treeIndex = StringPool.BLANK;
			String parentItemIdNew = StringPool.BLANK;

			JSONObject columnNames = WebKeys.getWorkingUnitColumnNames();
			int i = 1;

			for (WorkingUnit object : List) {

				_log.info("*i:" + i);
				_log.info("=====WorkingunitId:" + object.getWorkingunitId());

				long classPKLong = object.getParentWorkingUnitId();

				if (classPKLong > 0) {

					parentItemIdNew = StringPool.BLANK;

					parentItemId = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							columnNames.getString("parentWorkingUnitId"),
							classPKLong, StringPool.BLANK);

					if (Validator.isNotNull(parentItemId)) {

						parentItemIdNew = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(),
								WebKeys.WORKING_UNIT,
								WebKeys.EXTableName_WorkingUnit,
								columnNames.getString("workingUnitIdNew"),
								Long.valueOf(parentItemId), StringPool.BLANK);

						if (Validator.isNotNull(parentItemIdNew)) {
							object.setParentWorkingUnitId(Long
									.valueOf(parentItemIdNew));
						}
					} else {
						object.setParentWorkingUnitId(0);
					}

					WorkingUnitLocalServiceUtil.updateWorkingUnit(object);
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
