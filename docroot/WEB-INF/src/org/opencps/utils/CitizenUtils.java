
package org.opencps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.accountmgt.NoSuchCitizenException;
import org.opencps.accountmgt.model.Citizen;
import org.opencps.accountmgt.service.CitizenLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class CitizenUtils {

	private static Log _log = LogFactoryUtil.getLog(CitizenUtils.class);

	public void fetchCitizen(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Citizen> List =
				CitizenLocalServiceUtil.getCitizens(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Citizen, WebKeys.CITIZEN, WebKeys.CitizenColumns);
			int i = 1;
			for (Citizen object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getCitizenId());

				JSONObject columnNames = WebKeys.getCitizenColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("fullName"), object.getCitizenId(),
					String.valueOf(object.getFullName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("personalId"), object.getCitizenId(),
					String.valueOf(object.getPersonalId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("gender"), object.getCitizenId(),
					String.valueOf(object.getGender()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("birthDate"), object.getCitizenId(),
					DateTimeUtil.convertDateToString(
						object.getBirthdate(), DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("address"), object.getCitizenId(),
					String.valueOf(object.getAddress()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("cityCode"), object.getCitizenId(),
					String.valueOf(object.getCityCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("districtCode"), object.getCitizenId(),
					String.valueOf(object.getDistrictCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("wardCode"), object.getCitizenId(),
					String.valueOf(object.getWardCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("telNo"), object.getCitizenId(),
					String.valueOf(object.getTelNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("email"), object.getCitizenId(),
					String.valueOf(object.getEmail()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("mappingUserId"), object.getCitizenId(),
					String.valueOf(object.getMappingUserId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("accountStatus"), object.getCitizenId(),
					String.valueOf(object.getAccountStatus()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("attachFile"), object.getCitizenId(),
					String.valueOf(object.getAttachFile()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					columnNames.getString("citizenIdNew"), object.getCitizenId(), StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====CitizenId:" + object.getCitizenId());
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCitizen(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_Citizen, WebKeys.CITIZEN);
			try {

				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.CITIZEN, WebKeys.EXTableName_Citizen);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("=====row.getClassPK():" + row.getClassPK());

				JSONObject columnNames = WebKeys.getCitizenColumnNames();

				JSONObject userColNames = WebKeys.getUserColumnNames();

				String email =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
						columnNames.getString("email"), row.getClassPK(), StringPool.BLANK);

				Citizen citizen = null;

				try {

					citizen = CitizenLocalServiceUtil.getCitizen(email);
				}
				catch (NoSuchCitizenException e) {

				}

				if (Validator.isNull(citizen)) {

					String fullName =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("fullName"),
							row.getClassPK(), StringPool.BLANK);

					String personalId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("personalId"),
							row.getClassPK(), StringPool.BLANK);

					String gender =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("gender"),
							row.getClassPK(), StringPool.BLANK);

					String birthDate =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("birthDate"),
							row.getClassPK(), StringPool.BLANK);

					String address =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("address"),
							row.getClassPK(), StringPool.BLANK);

					String cityCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("cityCode"),
							row.getClassPK(), StringPool.BLANK);

					String districtCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("districtCode"),
							row.getClassPK(), StringPool.BLANK);

					String wardCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("wardCode"),
							row.getClassPK(), StringPool.BLANK);

					String telNo =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("telNo"),
							row.getClassPK(), StringPool.BLANK);

					String attachFile =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("attachFile"),
							row.getClassPK(), StringPool.BLANK);

					long attachFileIdNew = 0;

					if (Validator.isNotNull(attachFile)) {
						attachFileIdNew =
							commonUtils.getFileEntryId(
								actionRequest, actionResponse, Long.valueOf(attachFile),0);
					}

					String mappingUserId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("mappingUserId"),
							row.getClassPK(), StringPool.BLANK);

					String mappingUserIdNew = StringPool.BLANK;

					if (Validator.isNotNull(mappingUserId)) {
						mappingUserIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.USERS,
								WebKeys.EXTableName_User, userColNames.getString("userIdNew"),
								Long.valueOf(mappingUserId), StringPool.BLANK);
					}

					String accountStatus =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.CITIZEN,
							WebKeys.EXTableName_Citizen, columnNames.getString("accountStatus"),
							row.getClassPK(), StringPool.BLANK);

					long citizenId = CounterLocalServiceUtil.increment(WebKeys.CITIZEN);

					citizen = CitizenLocalServiceUtil.createCitizen(citizenId);

					citizen.setFullName(fullName);
					citizen.setPersonalId(personalId);
					citizen.setGender(Integer.valueOf(gender));
					citizen.setBirthdate(Validator.isNotNull(birthDate)
						? DateTimeUtil.convertStringToFullDate(birthDate) : new Date());
					citizen.setAddress(address);
					citizen.setCityCode(cityCode);
					citizen.setDistrictCode(districtCode);
					citizen.setWardCode(wardCode);
					citizen.setTelNo(telNo);
					citizen.setEmail(email);
					citizen.setAttachFile(attachFileIdNew);
					citizen.setAccountStatus(Validator.isNotNull(accountStatus)
						? Integer.valueOf(accountStatus) : 0);
					citizen.setUuid(PortalUUIDUtil.generate());
					citizen.setMappingUserId(Validator.isNotNull(mappingUserIdNew)
						? Long.valueOf(mappingUserIdNew) : 0);

					citizen.setCompanyId(themeDisplay.getCompanyId());
					citizen.setGroupId(themeDisplay.getScopeGroupId());
					citizen.setUserId(serviceContext.getUserId());

					if (mappingUserIdNew.trim().length() > 0) {
						citizen.setMappingUserId(Long.valueOf(mappingUserIdNew));
					}

					CitizenLocalServiceUtil.addCitizen(citizen);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
						columnNames.getString("citizenIdNew"), row.getClassPK(),
						String.valueOf(citizenId));
				}
				else {

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.CITIZEN, WebKeys.EXTableName_Citizen,
						columnNames.getString("citizenIdNew"), row.getClassPK(),
						String.valueOf(citizen.getCitizenId()));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
