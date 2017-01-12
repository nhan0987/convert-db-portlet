
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

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

public class JobPosUtils {

	private static Log _log = LogFactoryUtil.getLog(JobPosUtils.class);

	public void fetchJobPos(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<JobPos> List =
				JobPosLocalServiceUtil.getJobPoses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_JobPos, WebKeys.JOB_POS, WebKeys.JobPosColumns);

			for (JobPos object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getJobPosId());

				JSONObject columnNames = WebKeys.getJobPosColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("title"), object.getJobPosId(),
					String.valueOf(object.getTitle()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("workingUnitId"), object.getJobPosId(),
					String.valueOf(object.getWorkingUnitId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("directWorkingUnitId"), object.getJobPosId(),
					String.valueOf(object.getDirectWorkingUnitId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("leader"), object.getJobPosId(),
					String.valueOf(object.getLeader()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("mappingRoleId"), object.getJobPosId(),
					String.valueOf(object.getMappingRoleId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("jobPosIdNew"), object.getJobPosId(), StringPool.BLANK);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addJobPos(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_JobPos, WebKeys.JOB_POS);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			JobPos jobPos = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getJobPosColumnNames();

				JSONObject roleColumnNames = WebKeys.getRoleColumnNames();

				JSONObject workUnitColumnNames = WebKeys.getWorkingUnitColumnNames();

				String title =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						columnNames.getString("title"), row.getClassPK(), StringPool.BLANK);

				String workingUnitId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						columnNames.getString("workingUnitId"), row.getClassPK(), StringPool.BLANK);

				String workingUnitIdNew = StringPool.BLANK;

				if (Validator.isNotNull(workingUnitId)) {

					workingUnitIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							workUnitColumnNames.getString("workingUnitIdNew"),
							Long.valueOf(workingUnitId), StringPool.BLANK);
				}

				String directWorkingUnitId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						columnNames.getString("directWorkingUnitId"), row.getClassPK(),
						StringPool.BLANK);

				String directWorkingUnitIdNew = StringPool.BLANK;

				if (Validator.isNotNull(directWorkingUnitId)) {

					directWorkingUnitIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							workUnitColumnNames.getString("workingUnitIdNew"),
							Long.valueOf(directWorkingUnitId), StringPool.BLANK);
				}

				String leader =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						columnNames.getString("leader"), row.getClassPK(), StringPool.BLANK);

				String mappingRoleId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
						columnNames.getString("mappingRoleId"), row.getClassPK(), StringPool.BLANK);

				String mappingRoleIdNew = StringPool.BLANK;

				if (Validator.isNotNull(mappingRoleId)) {

					mappingRoleIdNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.ROLE, WebKeys.EXTableName_Role,
							roleColumnNames.getString("roleIdNew"), Long.valueOf(mappingRoleId),
							StringPool.BLANK);
				}

				long jobPosId = CounterLocalServiceUtil.increment(WebKeys.JOB_POS);

				jobPos = JobPosLocalServiceUtil.createJobPos(jobPosId);

				jobPos.setTitle(title);
				jobPos.setWorkingUnitId(Validator.isNotNull(workingUnitIdNew)
					? Long.valueOf(workingUnitIdNew) : 0);
				jobPos.setDirectWorkingUnitId(Validator.isNotNull(workingUnitIdNew)
					? Long.valueOf(directWorkingUnitIdNew) : 0);
				jobPos.setLeader(Validator.isNotNull(workingUnitIdNew)
					? Integer.valueOf(leader) : 0);

				jobPos.setCompanyId(themeDisplay.getCompanyId());
				jobPos.setGroupId(serviceContext.getScopeGroupId());
				jobPos.setUserId(serviceContext.getUserId());

				if (Validator.isNotNull(mappingRoleIdNew)) {
					jobPos.setMappingRoleId(Long.valueOf(mappingRoleIdNew));
				}

				JobPosLocalServiceUtil.updateJobPos(jobPos);

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.JOB_POS, WebKeys.EXTableName_JobPos,
					columnNames.getString("jobPosIdNew"), row.getClassPK(),
					String.valueOf(jobPosId));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
