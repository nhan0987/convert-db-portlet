
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.ProcessStep;
import org.opencps.processmgt.model.ProcessStepDossierPart;
import org.opencps.processmgt.model.ProcessWorkflow;
import org.opencps.processmgt.model.WorkflowOutput;
import org.opencps.processmgt.service.ProcessStepDossierPartLocalServiceUtil;
import org.opencps.processmgt.service.ProcessStepLocalServiceUtil;
import org.opencps.processmgt.service.ProcessWorkflowLocalServiceUtil;
import org.opencps.processmgt.service.ServiceInfoProcessLocalServiceUtil;
import org.opencps.processmgt.service.WorkflowOutputLocalServiceUtil;
import org.opencps.processmgt.service.persistence.ProcessStepDossierPartPK;
import org.opencps.processmgt.service.persistence.ServiceInfoProcessPK;

import com.liferay.counter.model.Counter;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class RoleUtils {

	private static Log _log = LogFactoryUtil.getLog(RoleUtils.class);

	public void fetchRole(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Role> List = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Role, WebKeys.ROLE, WebKeys.RoleColumns);
			int i = 1;
			for (Role object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getRoleId());

				JSONObject columnNames = WebKeys.getRoleColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("name"), object.getRoleId(),
					String.valueOf(object.getName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("title"), object.getRoleId(),
					String.valueOf(object.getTitle()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("description"), object.getRoleId(),
					String.valueOf(object.getDescription()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("type_"), object.getRoleId(),
					String.valueOf(object.getType()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("subtype"), object.getRoleId(),
					String.valueOf(object.getSubtype()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("roleIdNew"), object.getRoleId(), StringPool.BLANK);

				_log.info("**i=" + i);
				_log.info("=====RoleId:" + object.getRoleId());
				i++;

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addRole(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;
			

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_Role, WebKeys.ROLE);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {
				_log.info("*i:" + i);
				Role role = null;

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getRoleColumnNames();

				// /
				String name =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
						columnNames.getString("name"), row.getClassPK(), StringPool.BLANK);

				try {
					role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), name);
				}
				catch (Exception e) {

				}

				long roleId = 0;

				if (Validator.isNull(role)) {

					_log.info("=====creating role=====");

					String title =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							columnNames.getString("title"), row.getClassPK(), StringPool.BLANK);

					String description =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							columnNames.getString("description"), row.getClassPK(),
							StringPool.BLANK);

					String type_ =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							columnNames.getString("type_"), row.getClassPK(), StringPool.BLANK);
					String subtype =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							columnNames.getString("subtype"), row.getClassPK(), StringPool.BLANK);

					roleId = CounterLocalServiceUtil.increment(WebKeys.ROLE);

					role = RoleLocalServiceUtil.createRole(roleId);

					role.setClassPK(roleId);
					role.setName(name);
					role.setTitle(title);
					role.setDescription(description);
					role.setType(Integer.valueOf(type_));
					role.setSubtype(subtype);

					role.setCompanyId(themeDisplay.getCompanyId());
					role.setUserId(serviceContext.getUserId());
					role.setClassName(String.valueOf(expandoTable.getClassNameId()));

					RoleLocalServiceUtil.updateRole(role);

					_log.info("=====creating role success=====roleId:" + role.getRoleId());
				}
				else {
					roleId = role.getRoleId();
				}

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
					columnNames.getString("roleIdNew"), row.getClassPK(), String.valueOf(roleId));

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
