
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.usermgt.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

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

public class EmployeeUtils {

	private static Log _log = LogFactoryUtil.getLog(EmployeeUtils.class);

	public void fetchEmployee(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Employee> List =
				EmployeeLocalServiceUtil.getEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Employee, WebKeys.EMPLOYEES,
					WebKeys.EmployeeColumns);

			for (Employee object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getEmployeeId());

				JSONObject columnNames = WebKeys.getEmployeeColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("workingUnitId"), object.getEmployeeId(),
					String.valueOf(object.getWorkingUnitId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("employeeNo"), object.getEmployeeId(),
					String.valueOf(object.getEmployeeNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("fullName"), object.getEmployeeId(),
					String.valueOf(object.getFullName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("birthDate"), object.getEmployeeId(),
					DateTimeUtil.convertDateToString(
						object.getBirthdate(), DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("gender"), object.getEmployeeId(),
					String.valueOf(object.getGender()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("telNo"), object.getEmployeeId(),
					String.valueOf(object.getTelNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("email"), object.getEmployeeId(),
					String.valueOf(object.getEmail()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("workingStatus"), object.getEmployeeId(),
					String.valueOf(object.getWorkingStatus()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("mainJobPosId"), object.getEmployeeId(),
					String.valueOf(object.getMainJobPosId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("mappingUserId"), object.getEmployeeId(),
					String.valueOf(object.getMappingUserId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("mappingUserId"), object.getEmployeeId(),
					String.valueOf(object.getMappingUserId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					columnNames.getString("employeeIdNew"), object.getEmployeeId(),
					StringPool.BLANK);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_Employee, WebKeys.EMPLOYEES);
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
						WebKeys.EXTableName_Employee);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.EMPLOYEES, WebKeys.EXTableName_Employee,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getEmployeeColumnNames();

				JSONObject workUnitColNames = WebKeys.getWorkingUnitColumnNames();

				JSONObject userColNames = WebKeys.getUserColumnNames();

				JSONObject jobPosColNames = WebKeys.getJobPosColumnNames();

				String email =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
						WebKeys.EXTableName_Employee, columnNames.getString("email"),
						row.getClassPK(), StringPool.BLANK);

				Employee employee = null;

				try {
					employee =
						EmployeeLocalServiceUtil.getEmployeeByEmail(
							themeDisplay.getScopeGroupId(), email);
				}
				catch (NoSuchEmployeeException e) {

				}

				if (Validator.isNull(employee)) {

					String workingUnitId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("workingUnitId"),
							row.getClassPK(), StringPool.BLANK);

					String workingUnitIdNew = StringPool.BLANK;

					if (Validator.isNotNull(workingUnitId)) {

						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.WORKING_UNIT,
							WebKeys.EXTableName_WorkingUnit,
							workUnitColNames.getString("workingUnitIdNew"),
							Long.valueOf(workingUnitId), StringPool.BLANK);
					}

					String employeeNo =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("employeeNo"),
							row.getClassPK(), StringPool.BLANK);

					String fullName =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("fullName"),
							row.getClassPK(), StringPool.BLANK);

					String gender =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("gender"),
							row.getClassPK(), StringPool.BLANK);

					String birthDate =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("birthDate"),
							row.getClassPK(), StringPool.BLANK);

					String telNo =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("telNo"),
							row.getClassPK(), StringPool.BLANK);

					String workingStatus =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("workingStatus"),
							row.getClassPK(), StringPool.BLANK);

					String mainJobPosId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("mainJobPosId"),
							row.getClassPK(), StringPool.BLANK);

					String mainJobPosIdNew = StringPool.BLANK;

					if (Validator.isNotNull(mainJobPosId)) {
						mainJobPosIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.JOB_POS,
								WebKeys.EXTableName_JobPos,
								jobPosColNames.getString("jobPosIdNew"),
								Long.valueOf(mainJobPosId), StringPool.BLANK);
					}

					String mappingUserId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
							WebKeys.EXTableName_Employee, columnNames.getString("mappingUserId"),
							row.getClassPK(), StringPool.BLANK);

					String mappingUserIdNew = StringPool.BLANK;

					if (Validator.isNotNull(mainJobPosId)) {

						mappingUserIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.USERS,
								WebKeys.EXTableName_User, userColNames.getString("userIdNew"),
								Long.valueOf(mappingUserId), StringPool.BLANK);
					}

					long employeeId = CounterLocalServiceUtil.increment(WebKeys.EMPLOYEES);

					employee = EmployeeLocalServiceUtil.createEmployee(employeeId);

					employee.setEmployeeNo(employeeNo);
					employee.setFullName(fullName);
					employee.setTelNo(telNo);
					employee.setEmail(email);
					if (Validator.isNotNull(gender)) {
						employee.setGender(Integer.valueOf(gender));
					}
					if (Validator.isNotNull(birthDate)) {
						employee.setBirthdate(DateTimeUtil.convertStringToFullDate(birthDate));
					}
					if (Validator.isNotNull(workingStatus)) {
						employee.setWorkingStatus(Integer.valueOf(workingStatus));
					}
					if (Validator.isNotNull(mappingUserIdNew)) {
						employee.setMappingUserId(Long.valueOf(mappingUserIdNew));
					}
					if (Validator.isNotNull(workingUnitIdNew)) {
						employee.setWorkingUnitId(Long.valueOf(workingUnitIdNew));
					}
					if (Validator.isNotNull(mainJobPosIdNew)) {
						employee.setMainJobPosId(Long.valueOf(mainJobPosIdNew));
					}
					employee.setCompanyId(themeDisplay.getCompanyId());
					employee.setGroupId(serviceContext.getScopeGroupId());
					employee.setUserId(serviceContext.getUserId());

					EmployeeLocalServiceUtil.addEmployee(employee);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
						WebKeys.EXTableName_Employee, columnNames.getString("employeeIdNew"),
						row.getClassPK(), String.valueOf(employeeId));
				}
				else {
					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.EMPLOYEES,
						WebKeys.EXTableName_Employee, columnNames.getString("employeeIdNew"),
						row.getClassPK(), String.valueOf(employee.getEmployeeId()));

				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
