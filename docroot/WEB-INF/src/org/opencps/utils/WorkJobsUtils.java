
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.modifier.model.WorkJob;
import org.opencps.modifier.service.WorkJobLocalServiceUtil;

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

public class WorkJobsUtils {

	private static Log _log = LogFactoryUtil.getLog(WorkJobsUtils.class);

	public void fetchWorkJob(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			// OrganizationLocalServiceUtil.getUserOrganizations(userId, start,
			// end)

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<WorkJob> List =
				WorkJobLocalServiceUtil.getWorkJobs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_WorkJob, WebKeys.WORKING_JOB,
					WebKeys.WorkJobColumns);

			for (WorkJob object : List) {

				long workJobId = CounterLocalServiceUtil.increment(WebKeys.WORKING_JOB);

				JSONObject columnNames = WebKeys.getWorkJobColumnNames();

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), workJobId);

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKING_JOB, WebKeys.EXTableName_WorkJob,
					columnNames.getString("jobPosId"), workJobId,
					String.valueOf(object.getJobPosId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.WORKING_JOB, WebKeys.EXTableName_WorkJob,
					columnNames.getString("workingUnitId"), workJobId,
					String.valueOf(object.getWorkingUnitId()));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWorkJob(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_WorkJob, WebKeys.WORKING_JOB);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.WORKING_JOB,
						WebKeys.EXTableName_WorkJob);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.WORKING_JOB, WebKeys.EXTableName_WorkJob,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getWorkJobColumnNames();

				JSONObject jobPosColumnNames = WebKeys.getJobPosColumnNames();

				JSONObject workUnitColumnNames = WebKeys.getWorkingUnitColumnNames();

				String jobPosId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKING_JOB,
						WebKeys.EXTableName_WorkJob, columnNames.getString("jobPosId"),
						row.getClassPK(), StringPool.BLANK);

				String jobPosIdNew = StringPool.BLANK;
				if (Validator.isNotNull(jobPosId)) {
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						jobPosColumnNames.getString("jobPosIdNew"), Long.valueOf(jobPosId),
						StringPool.BLANK);
				}

				String workingUnitId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKING_JOB,
						WebKeys.EXTableName_WorkJob, columnNames.getString("workingUnitId"),
						row.getClassPK(), StringPool.BLANK);

				String workingUnitIdNew = StringPool.BLANK;
				if (Validator.isNotNull(workingUnitId)) {
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
						WebKeys.EXTableName_WorkingUnit,
						workUnitColumnNames.getString("workingUnitIdNew"),
						Long.valueOf(workingUnitId), StringPool.BLANK);
				}

				if (Validator.isNotNull(workingUnitIdNew) && Validator.isNotNull(jobPosIdNew)) {

					WorkJobLocalServiceUtil.addWorkJob(
						Long.valueOf(jobPosIdNew), Long.valueOf(workingUnitIdNew));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
