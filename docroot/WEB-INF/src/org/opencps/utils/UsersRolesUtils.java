
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.modifier.model.UsersRoles;
import org.opencps.modifier.service.UsersRolesLocalServiceUtil;
import org.opencps.modifier.service.persistence.UsersRolesPK;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class UsersRolesUtils {

	private static Log _log = LogFactoryUtil.getLog(UsersRolesUtils.class);

	public void fetchUsersRoles(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			// OrganizationLocalServiceUtil.getUserOrganizations(userId, start,
			// end)

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<UsersRoles> List =
				UsersRolesLocalServiceUtil.getUsersRoleses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_UsersRoles, WebKeys.USERS_ROLES,
					WebKeys.UsersRolesColumns);

			for (UsersRoles object : List) {

				long usersRolesId = CounterLocalServiceUtil.increment(WebKeys.USERS_ROLES);

				JSONObject columnNames = WebKeys.getUsersRolesColumnNames();

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), usersRolesId);

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS_ROLES, WebKeys.EXTableName_UsersRoles,
					columnNames.getString("roleId"), usersRolesId,
					String.valueOf(object.getRoleId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS_ROLES, WebKeys.EXTableName_UsersRoles,
					columnNames.getString("userId"), usersRolesId,
					String.valueOf(object.getUserId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUsersRoles(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;
			String message = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_UsersRoles, WebKeys.USERS_ROLES);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.USERS_ROLES,
						WebKeys.EXTableName_UsersRoles);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.USERS_ROLES,
					WebKeys.EXTableName_UsersRoles, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getUsersRolesColumnNames();

				JSONObject roleColumnNames = WebKeys.getRoleColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				String roleId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS_ROLES,
						WebKeys.EXTableName_UsersRoles, columnNames.getString("roleId"),
						row.getClassPK(), StringPool.BLANK);

				String roleIdNew =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
						roleColumnNames.getString("roleIdNew"), Long.valueOf(roleId),
						StringPool.BLANK);

				String userId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS_ROLES,
						WebKeys.EXTableName_UsersRoles, columnNames.getString("userId"),
						row.getClassPK(), StringPool.BLANK);

				String userIdNew =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						userColumnNames.getString("userIdNew"), Long.valueOf(userId),
						StringPool.BLANK);

				_log.info("=====roleIdNew:" + roleIdNew);
				_log.info("=====userIdNew:" + userIdNew);

				if (roleIdNew.trim().length() > 0 && userIdNew.trim().length() > 0) {

					UsersRolesLocalServiceUtil.addUsersRoles(
						Long.valueOf(roleIdNew), Long.valueOf(userIdNew));

				}

			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

}
