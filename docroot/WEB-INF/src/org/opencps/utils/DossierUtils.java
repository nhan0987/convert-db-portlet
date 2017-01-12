package org.opencps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.accountmgt.model.Business;
import org.opencps.accountmgt.model.Citizen;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.util.AccountBean;
import org.opencps.util.AccountUtil;
import org.opencps.util.DLFolderUtil;
import org.opencps.util.PortletUtil;
import org.opencps.util.PortletUtil.SplitDate;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class DossierUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierUtils.class);

	public void fetchDossiers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<Dossier> List = DossierLocalServiceUtil.getDossiers(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
					WebKeys.DOSSIERColumns);

			int i = 1;
			for (Dossier object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getDossierId());

				JSONObject columnNames = WebKeys.getDOSSIERColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("userId"), object.getDossierId(),
						String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("createDate"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("modifiedDate"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("ownerOrganizationId"),
						object.getDossierId(),
						String.valueOf(object.getOwnerOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("serviceConfig"),
						object.getDossierId(),
						String.valueOf(object.getServiceConfigId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("serviceInfoId"),
						object.getDossierId(),
						String.valueOf(object.getServiceInfoId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("serviceDomainIndex"),
						object.getDossierId(),
						String.valueOf(object.getServiceDomainIndex()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("serviceAdministrationIndex"),
						object.getDossierId(),
						String.valueOf(object.getServiceAdministrationIndex()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("dossierTemplateId"),
						object.getDossierId(),
						String.valueOf(object.getDossierTemplateId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("govAgencyCode"),
						object.getDossierId(),
						String.valueOf(object.getGovAgencyCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("govAgencyName"),
						object.getDossierId(),
						String.valueOf(object.getGovAgencyName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("serviceMode"),
						object.getDossierId(),
						String.valueOf(object.getServiceMode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("counter"),
						object.getDossierId(),
						String.valueOf(object.getCounter()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("subjectId"),
						object.getDossierId(),
						String.valueOf(object.getSubjectId()));

				// ExpandoValueLocalServiceUtil.addValue(
				// companyId, WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
				// columnNames.getString("subjectName"), object.getDossierId(),
				// String.valueOf(object.getUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("note"), object.getDossierId(),
						String.valueOf(object.getNote()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("submitDateTime"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(
										object.getSubmitDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("receiveDateTime"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(
										object.getReceiveDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("estimateDateTime"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(
										object.getEstimateDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("finishDateTime"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(
										object.getFinishDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("estimateDateTime"), object
								.getDossierId(), DateTimeUtil
								.convertDateToString(
										object.getEstimateDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("receptionNo"),
						object.getDossierId(),
						String.valueOf(object.getReceptionNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("dossierStatus"),
						object.getDossierId(),
						String.valueOf(object.getDossierStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("dossierSource"),
						object.getDossierId(),
						String.valueOf(object.getDossierSource()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("oid"), object.getDossierId(),
						String.valueOf(object.getOid()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("keypayRedirectUrl"),
						object.getDossierId(),
						String.valueOf(object.getKeypayRedirectUrl()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("delayStatus"),
						object.getDossierId(),
						String.valueOf(object.getDelayStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.DOSSIER, WebKeys.EXTableName_DOSSIER,
						columnNames.getString("dossierIdNew"),
						object.getDossierId(), StringPool.BLANK);

				_log.info("=====DossierId:" + object.getDossierId());
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDossiers(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DOSSIER,
					WebKeys.DOSSIER);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DOSSIER,
						WebKeys.EXTableName_DOSSIER);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DOSSIER,
					WebKeys.EXTableName_DOSSIER, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("*i:" + i);
				_log.info("=====row.getClassPK()" + row.getClassPK());

				JSONObject columnNames = WebKeys.getDOSSIERColumnNames();

				JSONObject orgColumnNames = WebKeys
						.getOrganizationColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject dossierTmplColumnNames = WebKeys
						.getDossierTemplateColumnNames();

				JSONObject serviceConfigColumnNames = WebKeys
						.getServiceConfigColumnNames();

				JSONObject serviceInfoColumnNames = WebKeys
						.getServiceInfoColumnNames();

				JSONObject dictItemColNames = WebKeys.getDictItemColumnNames();

				long serviceConfigIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER,
								WebKeys.DOSSIER, columnNames
										.getString("serviceConfig"), row
										.getClassPK(),
								WebKeys.EXTableName_ServiceConfig,
								WebKeys.SERVICE_CONFIG,
								serviceConfigColumnNames
										.getString("serviceConfigNew"),
								themeDisplay);

				// ///////////////////////////////////////////////////////////////////////////

				if (serviceConfigIdNew > 0) {

					String createDate = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("createDate"),
							row.getClassPK(), StringPool.BLANK);

					String modifiedDate = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("modifiedDate"),
							row.getClassPK(), StringPool.BLANK);

					String govAgencyCode = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("govAgencyCode"),
									row.getClassPK(), StringPool.BLANK);

					String serviceMode = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("serviceMode"),
							row.getClassPK(), StringPool.BLANK);

					String counter = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("counter"), row.getClassPK(),
							StringPool.BLANK);

					String subjectId = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("subjectId"),
							row.getClassPK(), StringPool.BLANK);

					String subjectName = StringPool.BLANK;

					String note = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("note"), row.getClassPK(),
							StringPool.BLANK);

					String submitDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("submitDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String receiveDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("receiveDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String estimateDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("estimateDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String finishDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("finishDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String receptionNo = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("receptionNo"),
							row.getClassPK(), StringPool.BLANK);

					String dossierStatus = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("dossierStatus"),
									row.getClassPK(), StringPool.BLANK);

					String dossierSource = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("dossierSource"),
									row.getClassPK(), StringPool.BLANK);

					String oid = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("oid"), row.getClassPK(),
							StringPool.BLANK);

					String keypayRedirectUrl = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("keypayRedirectUrl"),
									row.getClassPK(), StringPool.BLANK);

					String delayStatus = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("delayStatus"),
							row.getClassPK(), StringPool.BLANK);

					String govAgencyName = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("govAgencyName"),
									row.getClassPK(), StringPool.BLANK);

					// ///////////////////////////////////////////////////////////////////////

					String cityCode = StringPool.BLANK;
					String cityName = StringPool.BLANK;
					String districtCode = StringPool.BLANK;
					String districtName = StringPool.BLANK;
					String wardName = StringPool.BLANK;
					String wardCode = StringPool.BLANK;

					String address = StringPool.BLANK;
					String contactName = StringPool.BLANK;
					String contactTelNo = StringPool.BLANK;
					String contactEmail = StringPool.BLANK;

					long userIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
							WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
							columnNames.getString("userId"), row.getClassPK(),
							WebKeys.EXTableName_User, WebKeys.USERS,
							userColumnNames.getString("userIdNew"),
							themeDisplay);

					if (userIdNew > 0) {

						AccountBean accountBean = null;

						if (Validator.isNotNull(userIdNew)) {
							accountBean = AccountUtil.getAccountBean(userIdNew,
									themeDisplay.getScopeGroupId(), null);

							Citizen citizen = null;
							Business bussines = null;

							if (accountBean.isCitizen()) {
								citizen = (Citizen) accountBean
										.getAccountInstance();

								districtCode = citizen.getDistrictCode();

								wardCode = citizen.getWardCode();
								cityCode = citizen.getCityCode();
								districtCode = citizen.getDistrictCode();

								contactName = citizen.getFullName();
								contactTelNo = citizen.getTelNo();
								contactEmail = citizen.getEmail();
								address = citizen.getAddress();

								accountBean = commonUtils.getItemNameByCode(
										wardCode, districtCode, cityCode,
										serviceContext);

								wardName = accountBean.getWardName();
								cityName = accountBean.getCityName();
								districtName = accountBean.getDistricName();

							} else if (accountBean.isBusiness()) {
								bussines = (Business) accountBean
										.getAccountInstance();

								wardCode = bussines.getWardCode();
								cityCode = bussines.getCityCode();
								districtCode = bussines.getDistrictCode();

								contactName = bussines.getName();
								contactTelNo = bussines.getTelNo();
								contactEmail = bussines.getEmail();
								address = bussines.getAddress();

								accountBean = commonUtils.getItemNameByCode(
										wardCode, districtCode, cityCode,
										serviceContext);

								wardName = accountBean.getWardName();
								cityName = accountBean.getCityName();
								districtName = accountBean.getDistricName();

							}

						}
					}

					subjectName = contactName;

					String dossierDestinationFolder = StringPool.BLANK;

					SplitDate splitDate = PortletUtil.splitDate(new Date());

					dossierDestinationFolder = PortletUtil
							.getDossierDestinationFolder(
									serviceContext.getScopeGroupId(),
									splitDate.getYear(), splitDate.getMonth(),
									splitDate.getDayOfMoth());

					dossierDestinationFolder += StringPool.SLASH + oid;

					DLFolder dossierFolder = DLFolderUtil.getTargetFolder(
							serviceContext.getUserId(),
							serviceContext.getScopeGroupId(),
							serviceContext.getScopeGroupId(), false, 0,
							dossierDestinationFolder, StringPool.BLANK, false,
							serviceContext);

					// ////////////////////////////////////////////////////////////////////////////

					long ownerOrganizationIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER,
									WebKeys.DOSSIER, columnNames
											.getString("ownerOrganizationId"),
									row.getClassPK(),
									WebKeys.EXTableName_Organization,
									WebKeys.ORGANIZATION, orgColumnNames
											.getString("organizationIdNew"),
									themeDisplay);

					// ////////////////////////////////////////////////////////////////////////////

					long govAgencyOrganizationIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER,
									WebKeys.DOSSIER, columnNames
											.getString("ownerOrganizationId"),
									row.getClassPK(),
									WebKeys.EXTableName_Organization,
									WebKeys.ORGANIZATION, orgColumnNames
											.getString("organizationIdNew"),
									themeDisplay);

					// ////////////////////////////////////////////////////////////////////////////

					long dossierTemplateIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER,
									WebKeys.DOSSIER, columnNames
											.getString("dossierTemplateId"),
									row.getClassPK(),
									WebKeys.EXTableName_DossierTemplate,
									WebKeys.DOSSIER_TEMPLATE,
									dossierTmplColumnNames
											.getString("dossierTemplateIdNew"),
									themeDisplay);

					// ///////////////////////////////////////////////////////////////////////////

					long serviceInfoIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(WebKeys.EXTableName_DOSSIER,
									WebKeys.DOSSIER, columnNames
											.getString("serviceInfoId"), row
											.getClassPK(),
									WebKeys.EXTableName_SERVICE_INFO,
									WebKeys.SERVICE_INFO,
									serviceInfoColumnNames
											.getString("serviceInfoIdNew"),
									themeDisplay);

					// ////////////////////////////////////////////////////////////////////////////

					keypayRedirectUrl = CommonUtils.changeDomainUrl(
							keypayRedirectUrl, themeDisplay);

					// ////////////////////////////////////////////////////////////////////////////

					String serviceDomainIndex = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.DOSSIER,
									WebKeys.EXTableName_DOSSIER,
									columnNames.getString("serviceDomainIndex"),
									row.getClassPK(), StringPool.BLANK);

					if (Validator.isNotNull(serviceDomainIndex)) {
						serviceDomainIndex = CommonUtils.generateTreeIndex(
								serviceDomainIndex, StringPool.PERIOD,
								WebKeys.EXTableName_DICTITEM,
								dictItemColNames.getString("dictItemNew"),
								WebKeys.DICTITEM, themeDisplay.getCompanyId());

					}
					// ////////////////////////////////////////////////////////////////////////////
					Dossier dossier = null;

					dossier = DossierLocalServiceUtil.addDossier(userIdNew,
							ownerOrganizationIdNew, dossierTemplateIdNew,
							StringPool.BLANK, serviceConfigIdNew,
							serviceInfoIdNew, serviceDomainIndex,
							govAgencyOrganizationIdNew, govAgencyCode,
							govAgencyName, Integer.valueOf(serviceMode),
							String.valueOf(serviceConfigIdNew), cityCode,
							cityName, districtCode, districtName, wardName,
							wardCode, subjectName, subjectId, address,
							contactName, contactTelNo, contactEmail, note,
							Integer.valueOf(dossierSource), dossierStatus,
							dossierFolder.getFolderId(), keypayRedirectUrl,
							serviceContext);

					dossier.setDelayStatus(Validator.isNotNull(delayStatus) ? Integer
							.valueOf(delayStatus) : 0);
					dossier.setCounter(Validator.isNotNull(counter) ? Integer
							.valueOf(counter) : 0);
					dossier.setReceptionNo(receptionNo);

					dossier.setCreateDate(Validator.isNotNull(createDate) ? DateTimeUtil
							.convertStringToFullDate(createDate) : null);
					dossier.setModifiedDate(Validator.isNotNull(modifiedDate) ? DateTimeUtil
							.convertStringToFullDate(modifiedDate) : null);

					dossier.setSubmitDatetime(Validator
							.isNotNull(submitDateTime) ? DateTimeUtil
							.convertStringToFullDate(submitDateTime) : null);
					dossier.setReceiveDatetime(Validator
							.isNotNull(receiveDateTime) ? DateTimeUtil
							.convertStringToFullDate(receiveDateTime) : null);
					dossier.setEstimateDatetime(Validator
							.isNotNull(estimateDateTime) ? DateTimeUtil
							.convertStringToFullDate(estimateDateTime) : null);
					dossier.setFinishDatetime(Validator
							.isNotNull(finishDateTime) ? DateTimeUtil
							.convertStringToFullDate(finishDateTime) : null);

					DossierLocalServiceUtil.updateDossier(dossier);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DOSSIER,
							WebKeys.EXTableName_DOSSIER,
							columnNames.getString("dossierIdNew"),
							row.getClassPK(),
							String.valueOf(dossier.getDossierId()));

					_log.info("=====add Success===dossier.getDossierId():"
							+ dossier.getDossierId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
