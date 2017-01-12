
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.processmgt.model.StepAllowance;
import org.opencps.processmgt.service.StepAllowanceLocalServiceUtil;

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

public class StepAllowanceUtils {

	private static Log _log = LogFactoryUtil.getLog(StepAllowanceUtils.class);

	public void fetchStepAllowance(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			// OrganizationLocalServiceUtil.getUserOrganizations(userId, start,
			// end)

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<StepAllowance> List =
				StepAllowanceLocalServiceUtil.getStepAllowances(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_StepAllowance, WebKeys.STEPALOWANCE,
					WebKeys.StepAllowanceColumns);
			int i = 1;
			for (StepAllowance object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getStepAllowanceId());

				JSONObject columnNames = WebKeys.getStepAllowanceColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.STEPALOWANCE, WebKeys.EXTableName_StepAllowance,
					columnNames.getString("processStepId"), object.getStepAllowanceId(),
					String.valueOf(object.getProcessStepId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.STEPALOWANCE, WebKeys.EXTableName_StepAllowance,
					columnNames.getString("roleId"), object.getStepAllowanceId(),
					String.valueOf(object.getRoleId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.STEPALOWANCE, WebKeys.EXTableName_StepAllowance,
					columnNames.getString("readOnly"), object.getStepAllowanceId(),
					String.valueOf(object.getReadOnly()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.STEPALOWANCE, WebKeys.EXTableName_StepAllowance,
					columnNames.getString("stepAllowanceIdNew"), object.getStepAllowanceId(),
					StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====StepAllowanceId:" + object.getStepAllowanceId());
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStepAllowance(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;
			String message = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_StepAllowance, WebKeys.STEPALOWANCE);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
						WebKeys.EXTableName_StepAllowance);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			StepAllowance stepAllowance = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
					WebKeys.EXTableName_StepAllowance, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getStepAllowanceColumnNames();

				JSONObject processStepColumnNames = WebKeys.getProcessStepColumnNames();

				JSONObject roleColumnNames = WebKeys.getRoleColumnNames();

				String roleId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
						WebKeys.EXTableName_StepAllowance, columnNames.getString("roleId"),
						row.getClassPK(), StringPool.BLANK);

				String roleIdNew = StringPool.BLANK;

				if (Validator.isNotNull(roleId)) {

					roleIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							roleColumnNames.getString("roleIdNew"), Long.valueOf(roleId),
							StringPool.BLANK);
				}
				_log.info("=====roleId:"+roleId);
				_log.info("=====roleIdNew:"+roleIdNew);

				String processStepId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
						WebKeys.EXTableName_StepAllowance, columnNames.getString("processStepId"),
						row.getClassPK(), StringPool.BLANK);

				String processStepIdNew = StringPool.BLANK;

				if (Validator.isNotNull(processStepId)) {

					processStepIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PROCESS_STEP,
							WebKeys.EXTableName_ProcessStep,
							processStepColumnNames.getString("processStepIdNew"), Long.valueOf(processStepId),
							StringPool.BLANK);

				}
				_log.info("=====processStepId:"+processStepId);
				_log.info("=====processStepIdNew:"+processStepIdNew);
				

				String readOnly =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
						WebKeys.EXTableName_StepAllowance, columnNames.getString("readOnly"),
						row.getClassPK(), StringPool.BLANK);

				long stepAllowanceId = 0;

				if (roleIdNew.trim().length() > 0 && processStepIdNew.trim().length() > 0) {

					stepAllowanceId = CounterLocalServiceUtil.increment(WebKeys.STEPALOWANCE);

					stepAllowance =
						StepAllowanceLocalServiceUtil.createStepAllowance(stepAllowanceId);

					stepAllowance.setProcessStepId(Long.valueOf(processStepIdNew));
					stepAllowance.setRoleId(Long.valueOf(roleIdNew));
					stepAllowance.setReadOnly(Validator.isNotNull(readOnly)
						? Boolean.valueOf(readOnly) : true);

					StepAllowanceLocalServiceUtil.addStepAllowance(stepAllowance);

					_log.info("=====add Success===stepAllowanceId:" + stepAllowanceId);
				}
				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.STEPALOWANCE,
					WebKeys.EXTableName_StepAllowance, columnNames.getString("stepAllowanceIdNew"),
					row.getClassPK(), String.valueOf(stepAllowanceId));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
