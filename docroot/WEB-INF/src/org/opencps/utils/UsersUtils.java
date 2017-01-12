
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.announcements.model.AnnouncementsDelivery;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class UsersUtils {

	private static Log _log = LogFactoryUtil.getLog(UsersUtils.class);

	public void fetchUsers(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<User> List = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_User, WebKeys.USERS, WebKeys.UserColumns);

			_log.info("=====fetching...user");
			int i = 1;
			for (User object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getUserId());

				JSONObject ColumnNames = WebKeys.getUserColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("contactId"), object.getUserId(),
					String.valueOf(object.getContactId()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("password_"), object.getUserId(),
					String.valueOf(object.getPassword()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("passwordEncrypted"), object.getUserId(),
					String.valueOf(object.getPasswordEncrypted()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("reminderQueryQuestion"), object.getUserId(),
					String.valueOf(object.getReminderQueryQuestion()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("reminderQueryAnswer"), object.getUserId(),
					String.valueOf(object.getReminderQueryAnswer()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("screenName"), object.getUserId(),
					String.valueOf(object.getScreenName()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("emailAddress"), object.getUserId(),
					String.valueOf(object.getEmailAddress()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("greeting"), object.getUserId(),
					String.valueOf(object.getGreeting()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("firstName"), object.getUserId(),
					String.valueOf(object.getFirstName()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("middleName"), object.getUserId(),
					String.valueOf(object.getMiddleName()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("lastName"), object.getUserId(),
					String.valueOf(object.getLastName()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("jobTitle"), object.getUserId(),
					String.valueOf(object.getJobTitle()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("agreedToTermsOfUse"), object.getUserId(),
					String.valueOf(object.getAgreedToTermsOfUse()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("emailAddressVerified"), object.getUserId(),
					String.valueOf(object.getEmailAddressVerified()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("status"), object.getUserId(),
					String.valueOf(object.getStatus()));
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.USERS, WebKeys.EXTableName_User,
					ColumnNames.getString("userIdNew"), object.getUserId(), StringPool.BLANK);

				_log.info("**i=" + i);
				_log.info("=====UserId:" + object.getUserId());
				i++;
			}
			_log.info("=====fetdone...user");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String PASSWORD_DEFAULT = "cps12345";

	public void addUsers(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;
			String message = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_User, WebKeys.USERS);

			try {

				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User);
			}
			catch (NoSuchTableException nste) {

				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			_log.info("=====adding..userId");

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				User user = null;
				String screenName = StringPool.BLANK;

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getUserColumnNames();

				// JSONObject contactColNames = WebKeys.getContactColumnNames();

				// String contactId =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("contactId"), row.getClassPK(),
				// StringPool.BLANK);

				// String contactIdNew = StringPool.BLANK;
				//
				// if (Validator.isNotNull(contactId)) {
				// contactIdNew =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.CONTACTS,
				// WebKeys.EXTableName_Contact,
				// contactColNames.getString("contactIdNew"),
				// Long.valueOf(contactId), StringPool.BLANK);
				// }

				String password_ = PASSWORD_DEFAULT;

				// String passwordEncrypted =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("passwordEncrypted"), row.getClassPK(),
				// StringPool.BLANK);

				// String reminderQueryQuestion =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("reminderQueryQuestion"),
				// row.getClassPK(),
				// StringPool.BLANK);
				//
				// String reminderQueryAnswer =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("reminderQueryAnswer"),
				// row.getClassPK(),
				// StringPool.BLANK);

				screenName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("screenName"), row.getClassPK(), StringPool.BLANK);

				String emailAddress =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("emailAddress"), row.getClassPK(), StringPool.BLANK);

				// String greeting =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("greeting"), row.getClassPK(),
				// StringPool.BLANK);

				String firstName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("firstName"), row.getClassPK(), StringPool.BLANK);

				String middleName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("middleName"), row.getClassPK(), StringPool.BLANK);

				String lastName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("lastName"), row.getClassPK(), StringPool.BLANK);

				String jobTitle =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("jobTitle"), row.getClassPK(), StringPool.BLANK);

				// String agreedToTermsOfUse =
				// ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.USERS,
				// WebKeys.EXTableName_User,
				// columnNames.getString("agreedToTermsOfUse"),
				// row.getClassPK(),
				// StringPool.BLANK);

				String emailAddressVerified =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("emailAddressVerified"), row.getClassPK(),
						StringPool.BLANK);

				String status =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("status"), row.getClassPK(), StringPool.BLANK);

				_log.info("=====emailAddress:" + emailAddress);
				try {

					user =
						UserLocalServiceUtil.getUserByEmailAddress(
							themeDisplay.getCompanyId(), emailAddress);
				}
				catch (NoSuchUserException e) {

				}

				if (Validator.isNull(user)) {
					_log.info("=====Creating User=====");

					boolean autoPassword = false;
					boolean autoScreenName = true;
					boolean sendEmail = false;

					long[] groupIds = null;
					long[] organizationIds = null;
					long[] roleIds = null;
					long[] userGroupIds = null;

					int gender = 1;

					user =
						UserServiceUtil.addUserWithWorkflow(
							serviceContext.getCompanyId(), autoPassword, password_, password_,
							autoScreenName, screenName, emailAddress, 0L, StringPool.BLANK,
							LocaleUtil.getDefault(), firstName, middleName, lastName, 0, 0,
							(gender == 1), 1, 1, 1990, jobTitle, groupIds, organizationIds,
							roleIds, userGroupIds, new ArrayList<Address>(),
							new ArrayList<EmailAddress>(), new ArrayList<Phone>(),
							new ArrayList<Website>(), new ArrayList<AnnouncementsDelivery>(),
							sendEmail, serviceContext);

					user.setEmailAddressVerified(Validator.isNotNull(emailAddressVerified)
						? Boolean.valueOf(emailAddressVerified) : true);
					user.setStatus(Validator.isNotNull(status) ? Integer.valueOf(status) : 0);
					user.setPasswordEncrypted(false);
					user.setPasswordReset(true);

					UserLocalServiceUtil.updateUser(user);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("userIdNew"), row.getClassPK(),
						String.valueOf(user.getUserId()));

					_log.info("===addSuccess UserId:" + user.getUserId());
				}
				else {
					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.USERS, WebKeys.EXTableName_User,
						columnNames.getString("userIdNew"), row.getClassPK(),
						String.valueOf(user.getUserId()));
				}

			}
			_log.info("=====done..userId");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
