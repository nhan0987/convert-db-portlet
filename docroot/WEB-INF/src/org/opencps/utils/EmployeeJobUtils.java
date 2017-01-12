
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.modifier.model.EmployeeJob;
import org.opencps.modifier.service.EmployeeJobLocalServiceUtil;

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

public class EmployeeJobUtils {

	private static Log _log = LogFactoryUtil.getLog(EmployeeJobUtils.class);

	public void fetchEmployeeJob(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			// OrganizationLocalServiceUtil.getUserOrganizations(userId, start,
			// end)

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<EmployeeJob> List =
				EmployeeJobLocalServiceUtil.getEmployeeJobs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_EmployeeJob, WebKeys.EMPLOYEE_JOB,
					WebKeys.EmployeeJobColumns);

			for (EmployeeJob object : List) {

				long employeeJobId = CounterLocalServiceUtil.increment(WebKeys.EMPLOYEE_JOB);

				JSONObject columnNames = WebKeys.getEmployeeJobColumnNames();

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), employeeJobId);

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEE_JOB, WebKeys.EXTableName_EmployeeJob,
					columnNames.getString("employeeId"), employeeJobId,
					String.valueOf(object.getEmployeeId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEE_JOB, WebKeys.EXTableName_EmployeeJob,
					columnNames.getString("jobPosId"), employeeJobId,
					String.valueOf(object.getJobPosId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addEmployeeJob(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_EmployeeJob, WebKeys.EMPLOYEE_JOB);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEE_JOB,
						WebKeys.EXTableName_EmployeeJob);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.EMPLOYEE_JOB,
					WebKeys.EXTableName_EmployeeJob, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getEmployeeJobColumnNames();

				JSONObject jobPosColumnNames = WebKeys.getJobPosColumnNames();

				JSONObject employeeColumnNames = WebKeys.getEmployeeColumnNames();

				String jobPosId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEE_JOB,
						WebKeys.EXTableName_EmployeeJob, columnNames.getString("jobPosId"),
						row.getClassPK(), StringPool.BLANK);

				String jobPosIdNew = StringPool.BLANK;
				if (Validator.isNotNull(jobPosId)) {
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						jobPosColumnNames.getString("jobPosIdNew"), Long.valueOf(jobPosId),
						StringPool.BLANK);
				}

				String employeeId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEE_JOB,
						WebKeys.EXTableName_EmployeeJob, columnNames.getString("employeeId"),
						row.getClassPK(), StringPool.BLANK);

				String employeeIdNew = StringPool.BLANK;

				if (Validator.isNotNull(employeeId)) {

					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
						WebKeys.EXTableName_Employee,
						employeeColumnNames.getString("employeeIdNew"), Long.valueOf(employeeId),
						StringPool.BLANK);
				}

				if (Validator.isNotNull(employeeIdNew) && Validator.isNotNull(jobPosIdNew)) {

					EmployeeJobLocalServiceUtil.addEmployeeJob(
						Long.valueOf(employeeIdNew), Long.valueOf(jobPosIdNew));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
