package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.servicemgt.NoSuchServiceInfoException;
import org.opencps.servicemgt.model.ServiceInfo;
import org.opencps.servicemgt.service.ServiceInfoLocalServiceUtil;

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

public class ServiceInfoUtils {

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoUtils.class);

	public void fetchServiceInfo(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ServiceInfo> serviceInfoList = ServiceInfoLocalServiceUtil
					.getServiceInfos(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_SERVICE_INFO, WebKeys.SERVICE_INFO,
					WebKeys.ServiceInfoColumnNames);
			int i = 1;

			_log.info("=====fetching...serviceInfo");
			for (ServiceInfo serviceInfo : serviceInfoList) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						serviceInfo.getServiceinfoId());

				JSONObject serviceInfoColumnNames = WebKeys
						.getServiceInfoColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceNo"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceName"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceName()));

				// ExpandoValueLocalServiceUtil.addValue(
				// companyId, WebKeys.SERVICE_INFO,
				// WebKeys.EXTableName_SERVICE_INFO,
				// serviceInfoColumnNames.getString("shortName"),
				// serviceInfo.getServiceinfoId(),
				// String.valueOf(serviceInfo.getServiceName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceProcess"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceProcess()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceMethod"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceMethod()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceDossier"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceDossier()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceCondition"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceCondition()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceDuration"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceDuration()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceActors"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceActors()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceResults"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceResults()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceRecords"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceRecords()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceIntructions"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceInstructions()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("administrationCode"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getAdministrationCode()));

				ExpandoValueLocalServiceUtil
						.addValue(companyId, WebKeys.SERVICE_INFO,
								WebKeys.EXTableName_SERVICE_INFO,
								serviceInfoColumnNames
										.getString("administrationIndex"),
								serviceInfo.getServiceinfoId(), String
										.valueOf(serviceInfo
												.getAdministrationIndex()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("domainCode"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getDomainCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("activeSatus"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getActiveStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("hasTemplateFiles"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getHasTemplateFiles()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("fullName"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getFullName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceInfoIdNew"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(StringPool.BLANK));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceFee"),
						serviceInfo.getServiceinfoId(),
						serviceInfo.getServiceFee());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("domainIndex"),
						serviceInfo.getServiceinfoId(),
						serviceInfo.getDomainIndex());

				_log.info("*i=" + i);
				_log.info("=====ServiceinfoId:"
						+ serviceInfo.getServiceinfoId());
				i++;

			}

			_log.info("=====fetdone...ServiceInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addServiceInfo(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_SERVICE_INFO,
					WebKeys.SERVICE_INFO);
			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
						WebKeys.EXTableName_SERVICE_INFO);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
					WebKeys.EXTableName_SERVICE_INFO, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				ServiceInfo serviceInfo = null;

				row = rows.get(i);

				_log.info("*i:" + i);
				_log.info("=====row.getClassPK():" + row.getClassPK());

				JSONObject columnNames = WebKeys.getServiceInfoColumnNames();

				JSONObject dictItemColNames = WebKeys.getDictItemColumnNames();

				String serviceNo = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
						WebKeys.EXTableName_SERVICE_INFO,
						columnNames.getString("serviceNo"), row.getClassPK(),
						StringPool.BLANK);

				_log.info("=====serviceNo:" + serviceNo);

				try {

					serviceInfo = ServiceInfoLocalServiceUtil
							.getServiceInfoByServiceNo(serviceNo);
				} catch (NoSuchServiceInfoException e) {

				}

				if (Validator.isNull(serviceInfo)) {

					String serviceName = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("serviceName"),
							row.getClassPK(), StringPool.BLANK);

					String serviceMethod = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceMethod"),
									row.getClassPK(), StringPool.BLANK);

					String serviceDossier = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceDossier"),
									row.getClassPK(), StringPool.BLANK);

					String serviceCondition = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceCondition"),
									row.getClassPK(), StringPool.BLANK);

					String serviceDuration = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceDuration"),
									row.getClassPK(), StringPool.BLANK);

					String serviceActors = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceActors"),
									row.getClassPK(), StringPool.BLANK);

					String serviceResults = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceResults"),
									row.getClassPK(), StringPool.BLANK);

					String serviceRecords = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceRecords"),
									row.getClassPK(), StringPool.BLANK);

					String administrationCode = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("administrationCode"),
									row.getClassPK(), StringPool.BLANK);

					String administrationCodeNew = StringPool.BLANK;

					if (Validator.isNotNull(administrationCode)) {
						administrationCodeNew = ExpandoValueLocalServiceUtil
								.getData(themeDisplay.getCompanyId(),
										WebKeys.DICTITEM,
										WebKeys.EXTableName_DICTITEM,
										dictItemColNames
												.getString("dictItemNew"), Long
												.valueOf(administrationCode),
										StringPool.BLANK);
					}

					String domainCode = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("domainCode"),
							row.getClassPK(), StringPool.BLANK);

					String domainCodeNew = StringPool.BLANK;

					if (Validator.isNotNull(domainCode)) {
						domainCodeNew = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.DICTITEM,
								WebKeys.EXTableName_DICTITEM,
								dictItemColNames.getString("dictItemNew"),
								Long.valueOf(domainCode), StringPool.BLANK);
					}

					String activeSatus = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("activeSatus"),
							row.getClassPK(), StringPool.BLANK);

					String hasTemplateFiles = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("hasTemplateFiles"),
									row.getClassPK(), StringPool.BLANK);

					String fullName = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("fullName"),
							row.getClassPK(), StringPool.BLANK);

					String serviceProcess = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceProcess"),
									row.getClassPK(), StringPool.BLANK);

					String serviceFee = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("serviceFee"),
							row.getClassPK(), StringPool.BLANK);

					String serviceIntructions = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames.getString("serviceIntructions"),
									row.getClassPK(), StringPool.BLANK);

					long serviceInfoId = CounterLocalServiceUtil
							.increment(WebKeys.SERVICE_INFO);
					serviceInfo = ServiceInfoLocalServiceUtil
							.createServiceInfo(serviceInfoId);

					String administrationIndex = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.SERVICE_INFO,
									WebKeys.EXTableName_SERVICE_INFO,
									columnNames
											.getString("administrationIndex"),
									row.getClassPK(), StringPool.BLANK);

					if (Validator.isNotNull(administrationIndex)) {
						administrationIndex = CommonUtils.generateTreeIndex(
								administrationIndex, StringPool.PERIOD,
								WebKeys.EXTableName_DICTITEM,
								dictItemColNames.getString("dictItemNew"),
								WebKeys.DICTITEM, themeDisplay.getCompanyId());

						_log.info("=====administrationIndex:"
								+ administrationIndex);

						serviceInfo.setAdministrationIndex(administrationIndex);
					}
					String domainIndex = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("domainIndex"),
							row.getClassPK(), StringPool.BLANK);

					if (Validator.isNotNull(domainIndex)) {
						domainIndex = CommonUtils.generateTreeIndex(
								domainIndex, StringPool.PERIOD,
								WebKeys.EXTableName_DICTITEM,
								dictItemColNames.getString("dictItemNew"),
								WebKeys.DICTITEM, themeDisplay.getCompanyId());

						_log.info("=====domainIndex:" + domainIndex);

						serviceInfo.setDomainIndex(domainIndex);
					}

					serviceInfo.setServiceNo(serviceNo);
					serviceInfo.setServiceName(serviceName);
					serviceInfo.setServiceProcess(serviceProcess);
					serviceInfo.setServiceMethod(serviceMethod);
					serviceInfo.setServiceDossier(serviceDossier);
					serviceInfo.setServiceCondition(serviceCondition);
					serviceInfo.setServiceDuration(serviceDuration);
					serviceInfo.setServiceActors(serviceActors);
					serviceInfo.setServiceResults(serviceResults);
					serviceInfo.setServiceRecords(serviceRecords);
					serviceInfo.setServiceFee(serviceFee);
					serviceInfo.setServiceInstructions(serviceIntructions);
					serviceInfo.setAdministrationCode(administrationCodeNew);
					serviceInfo.setDomainCode(domainCodeNew);

					serviceInfo.setActiveStatus(Integer.valueOf(activeSatus));
					serviceInfo.setHasTemplateFiles(Integer
							.valueOf(hasTemplateFiles));
					serviceInfo.setFullName(fullName);

					serviceInfo.setCompanyId(themeDisplay.getCompanyId());
					serviceInfo.setUserId(serviceContext.getUserId());
					serviceInfo.setGroupId(themeDisplay.getScopeGroupId());

					ServiceInfoLocalServiceUtil.addServiceInfo(serviceInfo);

					_log.info("=====addsuccess=====serviceInfoId:"
							+ serviceInfo.getServiceinfoId());

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("serviceInfoIdNew"),
							row.getClassPK(),
							String.valueOf(serviceInfo.getServiceinfoId()));
				} else {

					_log.info("=====serviceInfoExisted=====serviceInfoId:"
							+ serviceInfo.getServiceinfoId());
					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							columnNames.getString("serviceInfoIdNew"),
							row.getClassPK(),
							String.valueOf(serviceInfo.getServiceinfoId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchServiceInfo2(ThemeDisplay themeDisplay,
			ServiceInfo serviceInfo) {

		try {

			if (Validator.isNotNull(serviceInfo)) {
				_log.info("=====fetching...serviceInfo");

				long companyId = themeDisplay.getCompanyId();

				CommonUtils commonUtils = new CommonUtils();

				ExpandoTable expandoTable = commonUtils.checkTable(companyId,
						WebKeys.EXTableName_SERVICE_INFO, WebKeys.SERVICE_INFO,
						WebKeys.ServiceInfoColumnNames);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						serviceInfo.getServiceinfoId());

				JSONObject serviceInfoColumnNames = WebKeys
						.getServiceInfoColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceNo"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceName"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceName()));

				// ExpandoValueLocalServiceUtil.addValue(
				// companyId, WebKeys.SERVICE_INFO,
				// WebKeys.EXTableName_SERVICE_INFO,
				// serviceInfoColumnNames.getString("shortName"),
				// serviceInfo.getServiceinfoId(),
				// String.valueOf(serviceInfo.getServiceName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceProcess"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceProcess()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceMethod"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceMethod()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceDossier"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceDossier()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceCondition"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceCondition()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceDuration"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceDuration()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceActors"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceActors()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceResults"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceResults()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceRecords"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceRecords()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceIntructions"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getServiceInstructions()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("administrationCode"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getAdministrationCode()));

				ExpandoValueLocalServiceUtil
						.addValue(companyId, WebKeys.SERVICE_INFO,
								WebKeys.EXTableName_SERVICE_INFO,
								serviceInfoColumnNames
										.getString("administrationIndex"),
								serviceInfo.getServiceinfoId(), String
										.valueOf(serviceInfo
												.getAdministrationIndex()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("domainCode"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getDomainCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("activeSatus"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getActiveStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("hasTemplateFiles"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getHasTemplateFiles()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("fullName"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(serviceInfo.getFullName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceInfoIdNew"),
						serviceInfo.getServiceinfoId(),
						String.valueOf(StringPool.BLANK));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("serviceFee"),
						serviceInfo.getServiceinfoId(),
						serviceInfo.getServiceFee());

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.SERVICE_INFO, WebKeys.EXTableName_SERVICE_INFO,
						serviceInfoColumnNames.getString("domainIndex"),
						serviceInfo.getServiceinfoId(),
						serviceInfo.getDomainIndex());

				_log.info("=====serviceinfoId:"
						+ serviceInfo.getServiceinfoId());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
