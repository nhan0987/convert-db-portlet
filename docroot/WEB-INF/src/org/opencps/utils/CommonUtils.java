package org.opencps.utils;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.model.Citizen;
import org.opencps.accountmgt.service.BusinessLocalServiceUtil;
import org.opencps.accountmgt.service.CitizenLocalServiceUtil;
import org.opencps.datamgt.NoSuchDictCollectionException;
import org.opencps.datamgt.NoSuchDictItemException;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.expando.model.DomainConfigExt;
import org.opencps.expando.model.ExpandoExt;
import org.opencps.expando.service.DomainConfigExtLocalServiceUtil;
import org.opencps.expando.service.ExpandoExtLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.util.AccountBean;
import org.opencps.util.PortletPropsValues;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class CommonUtils {

	private static Log _log = LogFactoryUtil.getLog(CommonUtils.class);

	public ExpandoTable checkTable(long companyId, String tableName,
			String className, String[] columnNames) throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(companyId,
					className, tableName);
		} catch (NoSuchTableException nste) {
			expandoTable = addTable(companyId, className, tableName);
			for (String columnName : columnNames) {

				ExpandoColumnLocalServiceUtil.addColumn(
						expandoTable.getTableId(), columnName,
						ExpandoColumnConstants.STRING);

				_log.error("Expando Column:[" + columnName
						+ "] Created Successfully.");
			}

		}
		return expandoTable;
	}

	public ExpandoTable addTable(long companyId, String className,
			String tableName) throws PortalException, SystemException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, className, tableName);
		_log.error("Expando Table Created Successfully.");
		return expandoTable;
	}

	public String createTreePath(String treePath, String orgId, String pattern) {

		StringBuilder treePathNew = new StringBuilder();

		if (Validator.isNotNull(orgId) && Validator.isNotNull(treePath)) {
			treePathNew.append(pattern);
		}
		if (Validator.isNotNull(orgId)) {
			treePathNew.append(orgId);
		}
		if (Validator.isNotNull(treePath)) {
			treePathNew.append(treePath);
		}

		return treePathNew.toString();

	}

	public String generateTreePath(String originTreePath, String pattern,
			String tableName, String columnName, String className,
			long companyId) {

		String[] orgIdStringArray = StringUtil.split(originTreePath, pattern);

		List<String> orgIdStringList = Arrays.asList(orgIdStringArray);

		StringBuilder treePathNew = new StringBuilder();

		for (String orgId : orgIdStringList) {
			String orgIdNew = StringPool.BLANK;
			_log.info("=====orgId:" + orgId);
			try {
				if (Validator.isNotNull(orgId)) {
					orgIdNew = ExpandoValueLocalServiceUtil.getData(companyId,
							className, tableName, columnName,
							Long.valueOf(orgId), StringPool.BLANK);
				}
			} catch (NumberFormatException | PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Validator.isNotNull(orgIdNew)) {
				treePathNew.append(pattern);
				treePathNew.append(orgIdNew);

			}

		}

		return treePathNew.toString();

	}

	public String generateOrgTreePath(String originTreePath, String pattern,
			String tableName, String columnName, String className,
			long companyId) {

		StringBuilder newTreePath = new StringBuilder();

		try {

			List<String> orgIdStringList = getOrgIdFromTreePath(originTreePath,
					pattern);

			for (String orgId : orgIdStringList) {

				String orgIdNew = StringPool.BLANK;

				try {
					if (Validator.isNotNull(orgId)) {
						orgIdNew = ExpandoValueLocalServiceUtil.getData(
								companyId, className, tableName, columnName,
								Long.valueOf(orgId), StringPool.BLANK);
					}
				} catch (NumberFormatException | PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Validator.isNotNull(orgIdNew)) {
					newTreePath.append(pattern);
					newTreePath.append(orgIdNew);

				}

			}

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newTreePath.toString();

	}

	public static List<String> getOrgIdFromTreePath(String treePath,
			String pattern) {

		String[] orgIdStringArray = StringUtil.split(treePath, pattern);

		List<String> orgIdStringList = Arrays.asList(orgIdStringArray);
		return orgIdStringList;
	}

	public static String generateTreeIndex(String originTreePath,
			String pattern, String tableName, String columnName,
			String className, long companyId) {

		StringBuilder newTreePath = new StringBuilder();

		try {

			List<String> orgIdStringList = getOrgIdFromTreePath(originTreePath,
					pattern);

			for (String orgId : orgIdStringList) {

				String orgIdNew = StringPool.BLANK;

				try {
					if (Validator.isNotNull(orgId)) {
						orgIdNew = ExpandoValueLocalServiceUtil.getData(
								companyId, className, tableName, columnName,
								Long.valueOf(orgId), StringPool.BLANK);
					}
				} catch (NumberFormatException | PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Validator.isNotNull(orgIdNew)) {

					if (newTreePath.toString().trim().length() > 0) {
						newTreePath.append(pattern);
					}
					newTreePath.append(orgIdNew);

				}

			}

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newTreePath.toString();

	}

	public boolean changeClassId(String tableName, String className) {

		try {

			ExpandoExt tableExt = null;
			ClassName classNameOb = null;

			tableExt = ExpandoExtLocalServiceUtil.getByName(tableName);

			if (Validator.isNotNull(tableExt)) {

				classNameOb = ClassNameLocalServiceUtil.getClassName(className);

				if (Validator.isNotNull(classNameOb)) {

					if (tableExt.getClassNameId() == classNameOb
							.getClassNameId()) {

					} else {

						tableExt.setClassNameId(classNameOb.getClassNameId());
						ExpandoExtLocalServiceUtil.updateExpandoExt(tableExt);
					}

				}
			}

		} catch (SystemException | PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public long getFileEntryId(ActionRequest actionRequest,
			ActionResponse actionResponse, long fileId, long folderId) {

		long fileEntryId = 0;

		DLUserFileEntryUtil dlUserFileEntryUtil = new DLUserFileEntryUtil();
		fileEntryId = dlUserFileEntryUtil.getFileEntry(actionRequest,
				actionResponse, fileId, folderId);

		return fileEntryId;
	}

	public DomainConfigExt getDomainConfigData(Long companyId) {

		ExpandoTable expandoTable = null;
		DomainConfigExt domainConfigExt = null;
		try {

			changeClassId(WebKeys.EXTableName_DOMAIN_CONFIG,
					WebKeys.DOMAIN_CONFIG);

			expandoTable = checkTable(companyId,
					WebKeys.EXTableName_DOMAIN_CONFIG, WebKeys.DOMAIN_CONFIG,
					WebKeys.DomainConfigColumns);

			if (Validator.isNotNull(expandoTable)) {

				ExpandoRow row = null;

				row = ExpandoRowLocalServiceUtil.getRow(companyId,
						WebKeys.DOMAIN_CONFIG,
						WebKeys.EXTableName_DOMAIN_CONFIG, 1);

				if (Validator.isNull(row)) {

					row = ExpandoRowLocalServiceUtil.addRow(
							expandoTable.getTableId(), 1);
				}

				if (Validator.isNotNull(row)) {

					JSONObject columnNames = WebKeys
							.getDomainConfigColumnNames();

					domainConfigExt = DomainConfigExtLocalServiceUtil
							.createDomainConfigExt(1);

					// String domainConfigId =
					// ExpandoValueLocalServiceUtil.getData(
					// companyId, WebKeys.DOMAIN_CONFIG,
					// WebKeys.EXTableName_DOMAIN_CONFIG,
					// columnNames.getString("domainConfigId"),
					// row.getClassPK(), StringPool.BLANK);

					String targetDomain = ExpandoValueLocalServiceUtil.getData(
							companyId, WebKeys.DOMAIN_CONFIG,
							WebKeys.EXTableName_DOMAIN_CONFIG,
							columnNames.getString("targetDomain"),
							row.getClassPK(), StringPool.BLANK);

					String userName = ExpandoValueLocalServiceUtil.getData(
							companyId, WebKeys.DOMAIN_CONFIG,
							WebKeys.EXTableName_DOMAIN_CONFIG,
							columnNames.getString("userName"),
							row.getClassPK(), StringPool.BLANK);

					String password = ExpandoValueLocalServiceUtil.getData(
							companyId, WebKeys.DOMAIN_CONFIG,
							WebKeys.EXTableName_DOMAIN_CONFIG,
							columnNames.getString("password"),
							row.getClassPK(), StringPool.BLANK);

					// domainConfigExt.setDomainConfigId(Validator.isNotNull(row)?Long.valueOf(domainConfigId):0);
					domainConfigExt.setTargetDomain(targetDomain);
					domainConfigExt.setUserName(userName);
					domainConfigExt.setPassword(password);

				}
			}

		} catch (Exception e) {

		}

		return domainConfigExt;

	}

	public AccountBean getItemNameByCode(String wardCode, String districtCode,
			String cityCode, ServiceContext serviceContext) {

		AccountBean accountBean = new AccountBean();

		DictItem dictItem = null;

		try {
			dictItem = DictItemLocalServiceUtil.getDictItemInuseByItemCode(
					serviceContext.getScopeGroupId(), ADMINISTRATIVE_REGION,
					wardCode);

			if (Validator.isNotNull(dictItem)) {
				accountBean.setWardName(dictItem.getItemName(serviceContext
						.getLocale()));
				dictItem = null;
			}

			dictItem = DictItemLocalServiceUtil.getDictItemInuseByItemCode(
					serviceContext.getScopeGroupId(), ADMINISTRATIVE_REGION,
					districtCode);

			if (Validator.isNotNull(dictItem)) {
				accountBean.setDistricName(dictItem.getItemName(serviceContext
						.getLocale()));
				dictItem = null;
			}

			dictItem = DictItemLocalServiceUtil.getDictItemInuseByItemCode(
					serviceContext.getScopeGroupId(), ADMINISTRATIVE_REGION,
					cityCode);

			if (Validator.isNotNull(dictItem)) {
				accountBean.setCityName(dictItem.getItemName(serviceContext
						.getLocale()));
				dictItem = null;
			}
		} catch (NoSuchDictCollectionException | NoSuchDictItemException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountBean;

	}

	public static String changeDomainUrl(String url, ThemeDisplay themeDisplay) {

		if (Validator.isNotNull(url)) {
			String SubStr1 = new String("/group/guest");

			url = url.substring(url.indexOf(SubStr1, 0));

			url = themeDisplay.getURLPortal() + url;

			return url;
		} else {
			return StringPool.BLANK;
		}
	}

	public void setUserGroup(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		try {

			UserGroup userGroupsCitizen = UserGroupLocalServiceUtil
					.getUserGroup(themeDisplay.getCompanyId(),
							PortletPropsValues.USERMGT_USERGROUP_NAME_CITIZEN);

			UserGroup userGroupsBusiness = UserGroupLocalServiceUtil
					.getUserGroup(themeDisplay.getCompanyId(),
							PortletPropsValues.USERMGT_USERGROUP_NAME_BUSINESS);

			UserGroup userGroupsEmployee = UserGroupLocalServiceUtil
					.getUserGroup(themeDisplay.getCompanyId(),
							PortletPropsValues.USERMGT_USERGROUP_NAME_EMPLOYEE);

			long userId = 0;

			List<Citizen> citizenList = CitizenLocalServiceUtil.getCitizens(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Citizen citizen : citizenList) {
				userId = 0;

				userId = citizen.getMappingUserId();

				if (userId > 0) {

					UserGroupLocalServiceUtil.addUserUserGroup(userId,
							userGroupsCitizen.getUserGroupId());

				}

			}
			// ////////////////////////////
			List<Business> businessList = BusinessLocalServiceUtil
					.getBusinesses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Business business : businessList) {
				userId = 0;

				userId = business.getMappingUserId();

				if (userId > 0) {

					UserGroupLocalServiceUtil.addUserUserGroup(userId,
							userGroupsBusiness.getUserGroupId());

				}

			}
			// ///////////////////////////
			List<Employee> employeeList = EmployeeLocalServiceUtil
					.getEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Employee employee : employeeList) {
				userId = 0;

				userId = employee.getMappingUserId();

				if (userId > 0) {

					UserGroupLocalServiceUtil.addUserUserGroup(userId,
							userGroupsEmployee.getUserGroupId());

				}

			}
		} catch (PortalException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
}
