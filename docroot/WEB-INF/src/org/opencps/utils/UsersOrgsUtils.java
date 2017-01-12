
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.modifier.model.UsersOrgs;
import org.opencps.modifier.service.UsersOrgsLocalServiceUtil;
import org.opencps.modifier.service.persistence.UsersOrgsPK;

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

public class UsersOrgsUtils {

	private static Log _log = LogFactoryUtil.getLog(UsersOrgsUtils.class);

	public void fetchUsersOrgs(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			// OrganizationLocalServiceUtil.getUserOrganizations(userId, start,
			// end)

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<UsersOrgs> List =
				UsersOrgsLocalServiceUtil.getUsersOrgses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_UsersOrgs, WebKeys.USERS_ORGS,
					WebKeys.UsersOrgsColumns);

			for (UsersOrgs object : List) {

				long usersOrgsId = CounterLocalServiceUtil.increment(WebKeys.USERS_ORGS);

				JSONObject columnNames = WebKeys.getUsersOrgsColumnNames();

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), usersOrgsId);

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS_ORGS, WebKeys.EXTableName_UsersOrgs,
					columnNames.getString("organizationId"), usersOrgsId,
					String.valueOf(object.getOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS_ORGS, WebKeys.EXTableName_UsersOrgs,
					columnNames.getString("userId"), usersOrgsId,
					String.valueOf(object.getUserId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUsersOrgs(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {

			ExpandoTable expandoTable = null;
			String message = null;
			try {
				CommonUtils commonUtils = new CommonUtils();
				commonUtils.changeClassId(WebKeys.EXTableName_UsersOrgs, WebKeys.USERS_ORGS);

				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.USERS_ORGS,
						WebKeys.EXTableName_UsersOrgs);
			}
			catch (NoSuchTableException nste) {
				message =
					"Table  not existed to show the data. please add data first and comeback to to see the data";
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.USERS_ORGS, WebKeys.EXTableName_UsersOrgs,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getUsersOrgsColumnNames();

				JSONObject orgColumnNames = WebKeys.getOrganizationColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				String organizationId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS_ORGS,
						WebKeys.EXTableName_UsersOrgs, columnNames.getString("organizationId"),
						row.getClassPK(), StringPool.BLANK);

				String organizationIdNew = StringPool.BLANK;

				if (Validator.isNotNull(organizationId)) {
					organizationIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
							WebKeys.EXTableName_Organization,
							orgColumnNames.getString("organizationIdNew"),
							Long.valueOf(organizationId), StringPool.BLANK);
				}

				String userId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS_ORGS,
						WebKeys.EXTableName_UsersOrgs, columnNames.getString("userId"),
						row.getClassPK(), StringPool.BLANK);

				String userIdNew = StringPool.BLANK;

				if (Validator.isNotNull(userId)) {
					userIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
							userColumnNames.getString("userIdNew"), Long.valueOf(userId),
							StringPool.BLANK);
				}

				_log.info("=====organizationIdNew:" + organizationIdNew);
				_log.info("=====userIdNew:" + userIdNew);

				if (organizationIdNew.trim().length() > 0 && userIdNew.trim().length() > 0) {

					UsersOrgsLocalServiceUtil.addUsersOrgs(
						Long.valueOf(organizationIdNew), Long.valueOf(userIdNew));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
