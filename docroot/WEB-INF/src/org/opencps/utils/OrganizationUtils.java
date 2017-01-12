
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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

public class OrganizationUtils {

	private static Log _log = LogFactoryUtil.getLog(OrganizationUtils.class);

	public void fetchOrganization(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Organization> List =
				OrganizationLocalServiceUtil.getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			// List<Organization> List =
			// OrganizationLocalServiceUtil.getOrganizations(
			// userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);

			// OrganizationLocalServiceUtil.getOrganizati

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Organization, WebKeys.ORGANIZATION,
					WebKeys.OrganizationColumns);

			_log.info("=====fetching..Organization");
			int i = 1;
			for (Organization object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getOrganizationId());

				JSONObject ColumnNames = WebKeys.getOrganizationColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("userName"), object.getOrganizationId(),
					String.valueOf(object.getUserName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("parentOrganizationId"), object.getOrganizationId(),
					String.valueOf(object.getParentOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("treePath"), object.getOrganizationId(),
					String.valueOf(object.getTreePath()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("type_"), object.getOrganizationId(),
					String.valueOf(object.getType()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("name"), object.getOrganizationId(),
					String.valueOf(object.getName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("statusId"), object.getOrganizationId(),
					String.valueOf(object.getStatusId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ORGANIZATION, WebKeys.EXTableName_Organization,
					ColumnNames.getString("organizationIdNew"), object.getOrganizationId(),
					StringPool.BLANK);
				_log.info("**i=" + i);
				_log.info("=====getOrganizationId:" + object.getOrganizationId());
				i++;
			}
			_log.info("=====fetch done..Organization");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrganization(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_Organization, WebKeys.ORGANIZATION);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
					WebKeys.EXTableName_Organization, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			_log.info("=====adding..Organization");

			for (int i = 0; i < rows.size(); i++) {

				Organization organization = null;
				String treePath = StringPool.BLANK;

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getOrganizationColumnNames();

				String userName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("userName"),
						row.getClassPK(), StringPool.BLANK);

				String type_ =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("type_"),
						row.getClassPK(), StringPool.BLANK);

				String name =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("name"),
						row.getClassPK(), StringPool.BLANK);

				String statusId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("statusId"),
						row.getClassPK(), StringPool.BLANK);
				try {
					organization =
						OrganizationLocalServiceUtil.getOrganization(
							themeDisplay.getCompanyId(), name);
				}
				catch (NoSuchOrganizationException e) {

				}

				long organizationId = 0;

				if (Validator.isNull(organization)) {

					_log.info("=====Creating Organization=====");

					organizationId = CounterLocalServiceUtil.increment(WebKeys.ORGANIZATION);

					organization = OrganizationLocalServiceUtil.createOrganization(organizationId);

					organization.setName(name);
					organization.setUserName(userName);
					organization.setName(name);
					organization.setType(type_);
					organization.setStatusId(Integer.valueOf(statusId));

					organization.setCompanyId(themeDisplay.getCompanyId());
					organization.setUserId(serviceContext.getUserId());

					OrganizationLocalServiceUtil.updateOrganization(organization);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("organizationIdNew"), row.getClassPK(),
						String.valueOf(organizationId));

					String parentOrganizationIdNew = StringPool.BLANK;

					String parentOrganizationId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization,
							columnNames.getString("parentOrganizationId"), row.getClassPK(),
							StringPool.BLANK);

					if (Validator.isNotNull(parentOrganizationId)) {

						parentOrganizationIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
								WebKeys.EXTableName_Organization,
								columnNames.getString("organizationIdNew"),
								Long.valueOf(parentOrganizationId), StringPool.BLANK);

						organization.setParentOrganizationId(Validator.isNotNull(parentOrganizationIdNew)
							? Long.valueOf(parentOrganizationIdNew) : 0);

					}

					treePath =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization, columnNames.getString("treePath"),
							row.getClassPK(), StringPool.BLANK);

					treePath =
						commonUtils.generateTreePath(
							treePath, StringPool.SLASH, WebKeys.EXTableName_Organization,
							columnNames.getString("organizationIdNew"), WebKeys.ORGANIZATION,
							themeDisplay.getCompanyId());

					organization.setTreePath(treePath);
					OrganizationLocalServiceUtil.updateOrganization(organization);

					_log.info("===addSuccess organizationId:" + organizationId);
				}
				else {
					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("organizationIdNew"), row.getClassPK(),
						String.valueOf(organization.getOrganizationId()));

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("parentOrganizationId"), row.getClassPK(),
						String.valueOf(organization.getParentOrganizationId()));

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("treePath"),
						row.getClassPK(), String.valueOf(organization.getTreePath()));

				}

			}
			_log.info("=====done..Organization");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrganization1(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;
			String message = null;
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization);
			}
			catch (NoSuchTableException nste) {
				message =
					"Table  not existed to show the data. please add data first and comeback to to see the data";
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
					WebKeys.EXTableName_Organization, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			_log.info("=====adding..Organization");

			for (int i = 0; i < rows.size(); i++) {

				Organization organization = null;
				String treePath = StringPool.BLANK;

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getOrganizationColumnNames();

				String userName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("userName"),
						row.getClassPK(), StringPool.BLANK);

				String type_ =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("type_"),
						row.getClassPK(), StringPool.BLANK);

				String name =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("name"),
						row.getClassPK(), StringPool.BLANK);

				String statusId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("statusId"),
						row.getClassPK(), StringPool.BLANK);
				try {
					organization =
						OrganizationLocalServiceUtil.getOrganization(
							themeDisplay.getCompanyId(), name);
				}
				catch (NoSuchOrganizationException e) {

				}

				long organizationId = 0;

				if (Validator.isNull(organization)) {

					_log.info("=====Creating Organization=====");

					organizationId = CounterLocalServiceUtil.increment(WebKeys.ORGANIZATION);

					organization = OrganizationLocalServiceUtil.createOrganization(organizationId);

					organization.setName(name);
					organization.setUserName(userName);
					organization.setName(name);
					organization.setType(type_);
					organization.setStatusId(Integer.valueOf(statusId));
					organization.setTreePath(String.valueOf(row.getClassPK()));

					organization.setCompanyId(themeDisplay.getCompanyId());
					organization.setUserId(serviceContext.getUserId());

					OrganizationLocalServiceUtil.updateOrganization(organization);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("organizationIdNew"), row.getClassPK(),
						String.valueOf(organizationId));

				}
				else {
					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("organizationIdNew"), row.getClassPK(),
						String.valueOf(organization.getOrganizationId()));

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization,
						columnNames.getString("parentOrganizationId"), row.getClassPK(),
						String.valueOf(organization.getParentOrganizationId()));

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
						WebKeys.EXTableName_Organization, columnNames.getString("treePath"),
						row.getClassPK(), String.valueOf(organization.getTreePath()));

				}

			}
			_log.info("=====done..Organization");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreePathOrg(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Organization> List =
				OrganizationLocalServiceUtil.getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String parentOrgId = StringPool.BLANK;
			String treePath = StringPool.BLANK;
			String orgIdNew = StringPool.BLANK;

			CommonUtils commonUtils = new CommonUtils();

			JSONObject columnNames = WebKeys.getOrganizationColumnNames();
			int i = 1;
			for (Organization object : List) {

				_log.info("*i:" + i);
				_log.info("=====orgId:" + object.getOrganizationId());

				String classPK = object.getTreePath();

				if (Validator.isDigit(classPK)) {

					long classPKLong = Long.valueOf(classPK);

					orgIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization,
							columnNames.getString("organizationIdNew"), classPKLong,
							StringPool.BLANK);
					parentOrgId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization,
							columnNames.getString("parentOrganizationId"), classPKLong,
							StringPool.BLANK);
					treePath =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization, columnNames.getString("treePath"),
							classPKLong, StringPool.BLANK);

					if (Validator.isNotNull(treePath)) {

						treePath =
							commonUtils.generateOrgTreePath(
								treePath, StringPool.SLASH, WebKeys.EXTableName_Organization,
								columnNames.getString("organizationIdNew"), WebKeys.ORGANIZATION,
								companyId);

						object.setTreePath(treePath);
					}
					if (Validator.isNotNull(parentOrgId)) {

						orgIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
								WebKeys.EXTableName_Organization,
								columnNames.getString("organizationIdNew"),
								Long.valueOf(parentOrgId), StringPool.BLANK);
						if (Validator.isNotNull(orgIdNew)) {
							object.setParentOrganizationId(Long.valueOf(orgIdNew));
						}
					}
					OrganizationLocalServiceUtil.updateOrganization(object);

				}
				i++;
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
