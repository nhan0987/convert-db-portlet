
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Contact;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class ContactUtils {

	private static Log _log = LogFactoryUtil.getLog(ContactUtils.class);

	public void fetchContacts(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Contact> List =
				ContactLocalServiceUtil.getContacts(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			CommonUtils commonUtils = new CommonUtils();
			
			ExpandoTable expandoTable =
							commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Contact, WebKeys.CONTACTS,
					WebKeys.ContactColumns);

			for (Contact object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getClassPK());

				JSONObject ColumnNames = WebKeys.getContactColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("emailAddress"), object.getClassPK(),
					object.getEmailAddress());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("firstName"), object.getClassPK(), object.getFirstName());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("middleName"), object.getClassPK(),
					object.getMiddleName());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("lastName"), object.getClassPK(), object.getLastName());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("male"), object.getClassPK(),
					String.valueOf(object.getMale()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("birthday"), object.getClassPK(),
					DateTimeUtil.convertDateToString(
						object.getBirthday(), DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("jobTitle"), object.getClassPK(), object.getJobTitle());

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					ColumnNames.getString("contactIdNew"), object.getClassPK(), StringPool.BLANK);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addContact(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {

			ExpandoTable expandoTable = null;
			String message = null;
			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact);
			}
			catch (NoSuchTableException nste) {
				message =
					"Table  not existed to show the data. please add data first and comeback to to see the data";
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			Contact contact = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			_log.info("=====adding..contactId");

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getContactColumnNames();

				String emailAddress =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("emailAddress"), row.getClassPK(), StringPool.BLANK);

				String firstName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("firstName"), row.getClassPK(), StringPool.BLANK);
				String middleName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("middleName"), row.getClassPK(), StringPool.BLANK);
				String lastName =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("lastName"), row.getClassPK(), StringPool.BLANK);
				String male =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("male"), row.getClassPK(), StringPool.BLANK);
				String birthday =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("birthday"), row.getClassPK(), StringPool.BLANK);
				String jobTitle =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
						columnNames.getString("jobTitle"), row.getClassPK(), StringPool.BLANK);

				long contactId = CounterLocalServiceUtil.increment(WebKeys.CONTACTS);

				contact = ContactLocalServiceUtil.createContact(contactId);

				contact.setEmailAddress(emailAddress);
				contact.setFirstName(firstName);
				contact.setMiddleName(middleName);
				contact.setLastName(lastName);
				if (Validator.isNotNull(male)) {
					contact.setMale(Boolean.valueOf(male));
				}
				if (Validator.isNotNull(birthday)) {
					contact.setBirthday(DateTimeUtil.convertStringToFullDate(birthday));
				}
				contact.setJobTitle(jobTitle);

				ContactLocalServiceUtil.updateContact(contact);

				ExpandoValueLocalServiceUtil.addValue(
					themeDisplay.getCompanyId(), WebKeys.CONTACTS, WebKeys.EXTableName_Contact,
					columnNames.getString("contactIdNew"), row.getClassPK(),
					String.valueOf(contact.getClassPK()));
			}
			_log.info("=====done..contactId");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
