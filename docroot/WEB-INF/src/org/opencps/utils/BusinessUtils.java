
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.accountmgt.NoSuchBusinessException;
import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.service.BusinessLocalServiceUtil;

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

public class BusinessUtils {

	private static Log _log = LogFactoryUtil.getLog(BusinessUtils.class);

	public void fetchBusiness(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Business> List =
				BusinessLocalServiceUtil.getBusinesses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_Business, WebKeys.BUSINESS,
					WebKeys.BusinessColumns);
			int i = 1;
			for (Business object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(), object.getBusinessId());

				JSONObject columnNames = WebKeys.getBusinessColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("name"), object.getBusinessId(),
					String.valueOf(object.getName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("enName"), object.getBusinessId(),
					String.valueOf(object.getEnName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("shortName"), object.getBusinessId(),
					String.valueOf(object.getShortName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("businessType"), object.getBusinessId(),
					String.valueOf(object.getBusinessType()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("idNumber"), object.getBusinessId(),
					String.valueOf(object.getIdNumber()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("address"), object.getBusinessId(),
					String.valueOf(object.getAddress()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("cityCode"), object.getBusinessId(),
					String.valueOf(object.getCityCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("districtCode"), object.getBusinessId(),
					String.valueOf(object.getDistrictCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("wardCode"), object.getBusinessId(),
					String.valueOf(object.getWardCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("telNo"), object.getBusinessId(),
					String.valueOf(object.getTelNo()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("email"), object.getBusinessId(),
					String.valueOf(object.getEmail()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("representativeName"), object.getBusinessId(),
					String.valueOf(object.getRepresentativeName()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("representativeRole"), object.getBusinessId(),
					String.valueOf(object.getRepresentativeRole()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("mappingOrganizationId"), object.getBusinessId(),
					String.valueOf(object.getMappingOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("mappingUserId"), object.getBusinessId(),
					String.valueOf(object.getMappingUserId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("accountStatus"), object.getBusinessId(),
					String.valueOf(object.getAccountStatus()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("attachFile"), object.getBusinessId(),
					String.valueOf(object.getAttachFile()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					columnNames.getString("businessIdNew"), object.getBusinessId(),
					StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====BusinessId:" + object.getBusinessId());
				i++;

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBusiness(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_Business, WebKeys.BUSINESS);

			try {

				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.BUSINESS, WebKeys.EXTableName_Business);

			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.BUSINESS, WebKeys.EXTableName_Business,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getBusinessColumnNames();

				JSONObject orgColumnNames = WebKeys.getOrganizationColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				String email =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.BUSINESS,
						WebKeys.EXTableName_Business, columnNames.getString("email"),
						row.getClassPK(), StringPool.BLANK);

				Business business = null;

				try {

					business = BusinessLocalServiceUtil.getBusiness(email);
				}
				catch (NoSuchBusinessException e) {

				}

				if (Validator.isNull(business)) {

					String name =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("name"),
							row.getClassPK(), StringPool.BLANK);

					String enName =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("enName"),
							row.getClassPK(), StringPool.BLANK);

					String shortName =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("shortName"),
							row.getClassPK(), StringPool.BLANK);

					String businessType =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("businessType"),
							row.getClassPK(), StringPool.BLANK);

					String idNumber =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("idNumber"),
							row.getClassPK(), StringPool.BLANK);

					String address =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("address"),
							row.getClassPK(), StringPool.BLANK);

					String cityCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("cityCode"),
							row.getClassPK(), StringPool.BLANK);

					String districtCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("districtCode"),
							row.getClassPK(), StringPool.BLANK);

					String wardCode =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("wardCode"),
							row.getClassPK(), StringPool.BLANK);

					String telNo =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("telNo"),
							row.getClassPK(), StringPool.BLANK);

					String representativeName =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business,
							columnNames.getString("representativeName"), row.getClassPK(),
							StringPool.BLANK);

					String representativeRole =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business,
							columnNames.getString("representativeRole"), row.getClassPK(),
							StringPool.BLANK);

					String mappingOrganizationId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business,
							columnNames.getString("mappingOrganizationId"), row.getClassPK(),
							StringPool.BLANK);

					String attachFile =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("attachFile"),
							row.getClassPK(), StringPool.BLANK);
					
					long attachFileIdNew = 0;
					
					if(Validator.isNotNull(attachFile)){
						attachFileIdNew = commonUtils.getFileEntryId(actionRequest, actionResponse, Long.valueOf(attachFile),0);
					}
					

					String mappingOrganizationIdNew = StringPool.BLANK;

					if (Validator.isNotNull(mappingOrganizationId)) {
						mappingOrganizationIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
								WebKeys.EXTableName_Organization,
								orgColumnNames.getString("organizationIdNew"),
								Long.valueOf(mappingOrganizationId), StringPool.BLANK);
					}

					String mappingUserId =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("mappingUserId"),
							row.getClassPK(), StringPool.BLANK);

					String mappingUserIdNew = StringPool.BLANK;

					if (Validator.isNotNull(mappingUserId)) {
						mappingUserIdNew =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.USERS,
								WebKeys.EXTableName_User, userColumnNames.getString("userIdNew"),
								Long.valueOf(mappingUserId), StringPool.BLANK);
					}

					String accountStatus =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.BUSINESS,
							WebKeys.EXTableName_Business, columnNames.getString("accountStatus"),
							row.getClassPK(), StringPool.BLANK);

					long businessId = CounterLocalServiceUtil.increment(WebKeys.BUSINESS);

					business = BusinessLocalServiceUtil.createBusiness(businessId);

					business.setName(name);
					business.setEnName(enName);
					business.setShortName(shortName);
					business.setBusinessType(businessType);
					business.setIdNumber(idNumber);
					business.setAddress(address);
					business.setCityCode(cityCode);
					business.setDistrictCode(districtCode);
					business.setWardCode(wardCode);
					business.setTelNo(telNo);
					business.setEmail(email);
					business.setRepresentativeName(representativeName);
					business.setRepresentativeRole(representativeRole);
					business.setAccountStatus(Validator.isNotNull(accountStatus)
						? Integer.valueOf(accountStatus) : 0);
					business.setAttachFile(attachFileIdNew);
					business.setUuid(PortalUUIDUtil.generate());

					business.setCompanyId(themeDisplay.getCompanyId());
					business.setGroupId(serviceContext.getScopeGroupId());
					business.setUserId(serviceContext.getUserId());

					if (mappingUserIdNew.trim().length() > 0) {
						business.setMappingUserId(Long.valueOf(mappingUserIdNew));
					}
					if (mappingOrganizationIdNew.trim().length() > 0) {
						business.setMappingOrganizationId(Long.valueOf(mappingOrganizationIdNew));
					}

					BusinessLocalServiceUtil.updateBusiness(business);

					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.BUSINESS,
						WebKeys.EXTableName_Business, columnNames.getString("businessIdNew"),
						row.getClassPK(), String.valueOf(businessId));
				}
				else {
					ExpandoValueLocalServiceUtil.addValue(
						themeDisplay.getCompanyId(), WebKeys.BUSINESS,
						WebKeys.EXTableName_Business, columnNames.getString("businessIdNew"),
						row.getClassPK(), String.valueOf(business.getBusinessId()));

				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
