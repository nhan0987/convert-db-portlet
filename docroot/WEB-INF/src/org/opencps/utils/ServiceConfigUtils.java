
package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.dossiermgt.NoSuchServiceConfigException;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

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

public class ServiceConfigUtils {

	private static Log _log = LogFactoryUtil.getLog(ServiceConfigUtils.class);

	public void fetchServiceConfig(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<ServiceConfig> List =
				ServiceConfigLocalServiceUtil.getServiceConfigs(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable =
				commonUtils.checkTable(
					companyId, WebKeys.EXTableName_ServiceConfig, WebKeys.SERVICE_CONFIG,
					WebKeys.ServiceConfigColumns);

			_log.info("=====fetching...serviceConfig");
			int i = 1;
			for (ServiceConfig object : List) {

				ExpandoRowLocalServiceUtil.addRow(
					expandoTable.getTableId(), object.getServiceConfigId());

				JSONObject columnNames = WebKeys.getServiceConfigColumnNames();

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceInfoId"), object.getServiceConfigId(),
					String.valueOf(object.getServiceInfoId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceDomainIndex"), object.getServiceConfigId(),
					String.valueOf(object.getServiceDomainIndex()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceAdministrationIndex"),
					object.getServiceConfigId(),
					String.valueOf(object.getServiceAdministrationIndex()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("dossierTemplateId"), object.getServiceConfigId(),
					String.valueOf(object.getDossierTemplateId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("govAgencyCode"), object.getServiceConfigId(),
					String.valueOf(object.getGovAgencyCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("govAgencyName"), object.getServiceConfigId(),
					String.valueOf(object.getGovAgencyName()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("govAgencyOrganizationId"), object.getServiceConfigId(),
					String.valueOf(object.getGovAgencyOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("sericeMode"), object.getServiceConfigId(),
					String.valueOf(object.getServiceMode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceProcessId"), object.getServiceConfigId(),
					String.valueOf(object.getServiceProcessId()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("domainCode"), object.getServiceConfigId(),
					String.valueOf(object.getDomainCode()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceInstruction"), object.getServiceConfigId(),
					String.valueOf(object.getServiceInstruction()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceLevel"), object.getServiceConfigId(),
					String.valueOf(object.getServiceLevel()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("servicePortal"), object.getServiceConfigId(),
					String.valueOf(object.getServicePortal()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceOnegate"), object.getServiceConfigId(),
					String.valueOf(object.getServiceOnegate()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceBackOffice"), object.getServiceConfigId(),
					String.valueOf(object.getServiceBackoffice()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceCitizen"), object.getServiceConfigId(),
					String.valueOf(object.getServiceCitizen()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceBusiness"), object.getServiceConfigId(),
					String.valueOf(object.getServiceBusinees()));

				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("govAgencyIndex"), object.getServiceConfigId(),
					String.valueOf(object.getGovAgencyIndex()));
				// /
				ExpandoValueLocalServiceUtil.addValue(
					companyId, WebKeys.SERVICE_CONFIG, WebKeys.EXTableName_ServiceConfig,
					columnNames.getString("serviceConfigNew"), object.getServiceConfigId(),
					StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====ServiceConfigId:" + object.getServiceConfigId());
				i++;

			}
			_log.info("=====fetdone...serviceConfig");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addServiceConfig(ActionRequest actionRequest, ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_ServiceConfig, WebKeys.SERVICE_CONFIG);

			try {
				expandoTable =
					ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
						WebKeys.EXTableName_ServiceConfig);
			}
			catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows =
				ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
					WebKeys.EXTableName_ServiceConfig, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				ServiceConfig serviceConfig = null;

				_log.info("*i:" + i);

				row = rows.get(i);
				
				_log.info("=====row.getClassPK():"+row.getClassPK());

				JSONObject columnNames = WebKeys.getServiceConfigColumnNames();

				JSONObject serviceInfoColNames = WebKeys.getServiceInfoColumnNames();

				JSONObject dossierTemplateColNames = WebKeys.getDossierTemplateColumnNames();

				JSONObject orgsColNames = WebKeys.getOrganizationColumnNames();

				JSONObject serviceProcessColNames = WebKeys.getServiceProcessColumnNames();

				JSONObject dictItemColNames = WebKeys.getDictItemColumnNames();

				// /
				String serviceInfoId =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
						WebKeys.EXTableName_ServiceConfig, columnNames.getString("serviceInfoId"),
						row.getClassPK(), StringPool.BLANK);
				
				_log.info("=====serviceInfoId:"+serviceInfoId);

				String serviceInfoNew = StringPool.BLANK;

				if (Validator.isNotNull(serviceInfoId)) {
					serviceInfoNew =
						ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_INFO,
							WebKeys.EXTableName_SERVICE_INFO,
							serviceInfoColNames.getString("serviceInfoIdNew"),
							Long.valueOf(serviceInfoId), StringPool.BLANK);
				}
				_log.info("=====serviceInfoNew:"+serviceInfoNew);

				String govAgencyCode =
					ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
						WebKeys.EXTableName_ServiceConfig, columnNames.getString("govAgencyCode"),
						row.getClassPK(), StringPool.BLANK);
				
				_log.info("=====govAgencyCode:"+govAgencyCode);

				if (Validator.isNotNull(serviceInfoNew)) {

					try {

						// serviceConfig =
						// ServiceConfigLocalServiceUtil.getServiceConfigByG_S(
						// themeDisplay.getScopeGroupId(),
						// Long.valueOf(serviceInfoNew));

						serviceConfig =
							ServiceConfigLocalServiceUtil.getServiceConfigByG_S_G(
								themeDisplay.getScopeGroupId(), Long.valueOf(serviceInfoNew),
								govAgencyCode);
						//ServiceConfigLocalServiceUtil.getServiceConfigByG_S(themeDisplay.getScopeGroupId(), Long.valueOf(serviceInfoNew));
					}
					catch (NoSuchServiceConfigException e) {

					}

					if (Validator.isNull(serviceConfig)) {

						// /
						String dossierTemplateId =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("dossierTemplateId"), row.getClassPK(),
								StringPool.BLANK);

						String dossierTemplateIdNew = StringPool.BLANK;

						if (Validator.isNotNull(dossierTemplateId)) {
							dossierTemplateIdNew =
								ExpandoValueLocalServiceUtil.getData(
									themeDisplay.getCompanyId(), WebKeys.DOSSIER_TEMPLATE,
									WebKeys.EXTableName_DossierTemplate,
									dossierTemplateColNames.getString("dossierTemplateIdNew"),
									Long.valueOf(dossierTemplateId), StringPool.BLANK);
						}
						///
						String govAgencyOrganizationId =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("govAgencyOrganizationId"), row.getClassPK(),
								StringPool.BLANK);

						String govAgencyOrganizationIdNew = StringPool.BLANK;

						if (Validator.isNotNull(govAgencyOrganizationId)) {
							govAgencyOrganizationIdNew =
								ExpandoValueLocalServiceUtil.getData(
									themeDisplay.getCompanyId(), WebKeys.ORGANIZATION,
									WebKeys.EXTableName_Organization,
									orgsColNames.getString("organizationIdNew"),
									Long.valueOf(govAgencyOrganizationId), StringPool.BLANK);
						}
						// /

						String govAgencyName =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("govAgencyName"), row.getClassPK(),
								StringPool.BLANK);

						// /
						String sericeMode =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("sericeMode"), row.getClassPK(),
								StringPool.BLANK);
						// /
						String serviceProcessId =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceProcessId"), row.getClassPK(),
								StringPool.BLANK);

						String serviceProcessIdNew = StringPool.BLANK;

						if (Validator.isNotNull(serviceProcessId)) {

							serviceProcessIdNew =
								ExpandoValueLocalServiceUtil.getData(
									themeDisplay.getCompanyId(), WebKeys.SERVICE_PROCESS,
									WebKeys.EXTableName_ServiceProcess,
									serviceProcessColNames.getString("serviceProcessIdNew"),
									Long.valueOf(serviceProcessId), StringPool.BLANK);
						}
						// /
						String domainCode =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("domainCode"), row.getClassPK(),
								StringPool.BLANK);

						String domainCodeNew = StringPool.BLANK;
						if (Validator.isNotNull(domainCode)) {

							domainCodeNew =
								ExpandoValueLocalServiceUtil.getData(
									themeDisplay.getCompanyId(), WebKeys.DICTITEM,
									WebKeys.EXTableName_DICTITEM,
									dictItemColNames.getString("dictItemNew"),
									Long.valueOf(domainCode), StringPool.BLANK);
						}

						// /
						String serviceInstruction =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceInstruction"), row.getClassPK(),
								StringPool.BLANK);
						String serviceLevel =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceLevel"), row.getClassPK(),
								StringPool.BLANK);
						String servicePortal =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("servicePortal"), row.getClassPK(),
								StringPool.BLANK);
						String serviceOnegate =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceOnegate"), row.getClassPK(),
								StringPool.BLANK);
						String serviceBackOffice =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceBackOffice"), row.getClassPK(),
								StringPool.BLANK);
						String serviceCitizen =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceCitizen"), row.getClassPK(),
								StringPool.BLANK);
						String serviceBusiness =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceBusiness"), row.getClassPK(),
								StringPool.BLANK);

						// ///////////////
						long serviceConfigId =
							CounterLocalServiceUtil.increment(WebKeys.SERVICE_CONFIG);

						serviceConfig =
							ServiceConfigLocalServiceUtil.createServiceConfig(serviceConfigId);

						serviceConfig.setServiceInfoId(Validator.isNotNull(serviceInfoNew)
							? Long.valueOf(serviceInfoNew) : 0);
						serviceConfig.setDossierTemplateId(Validator.isNotNull(dossierTemplateIdNew)
							? Long.valueOf(dossierTemplateIdNew) : 0);

						serviceConfig.setGovAgencyCode(govAgencyCode);
						serviceConfig.setGovAgencyName(govAgencyName);

						serviceConfig.setGovAgencyOrganizationId(Validator.isNotNull(govAgencyOrganizationIdNew)
							? Long.valueOf(govAgencyOrganizationIdNew) : 0);
						serviceConfig.setServiceMode(Integer.valueOf(sericeMode));

						serviceConfig.setServiceProcessId(Validator.isNotNull(serviceProcessIdNew)
							? Long.valueOf(serviceProcessIdNew) : 0);
						serviceConfig.setDomainCode(domainCodeNew);

						serviceConfig.setServiceInstruction(serviceInstruction);
						serviceConfig.setServiceLevel(Validator.isNotNull(serviceLevel)
							? Integer.valueOf(serviceLevel) : 0);
						serviceConfig.setServicePortal(Validator.isNotNull(serviceLevel)
							? Boolean.valueOf(servicePortal) : false);
						serviceConfig.setServiceOnegate(Validator.isNotNull(serviceLevel)
							? Boolean.valueOf(serviceOnegate) : false);
						serviceConfig.setServiceBackoffice(Validator.isNotNull(serviceLevel)
							? Boolean.valueOf(serviceBackOffice) : false);
						serviceConfig.setServiceCitizen(Validator.isNotNull(serviceLevel)
							? Boolean.valueOf(serviceCitizen) : false);
						serviceConfig.setServiceBusinees(Validator.isNotNull(serviceLevel)
							? Boolean.valueOf(serviceBusiness) : false);

						serviceConfig.setCompanyId(themeDisplay.getCompanyId());
						serviceConfig.setUserId(serviceContext.getUserId());
						serviceConfig.setGroupId(serviceContext.getScopeGroupId());

						// //////////////////////
						String serviceDomainIndex =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceDomainIndex"), row.getClassPK(),
								StringPool.BLANK);
						
						_log.info("=====serviceDomainIndex(1):"+serviceDomainIndex);

						if (Validator.isNotNull(serviceDomainIndex)) {
							serviceDomainIndex =
								CommonUtils.generateTreeIndex(
									serviceDomainIndex, StringPool.PERIOD,
									WebKeys.EXTableName_DICTITEM,
									dictItemColNames.getString("dictItemNew"), WebKeys.DICTITEM,
									themeDisplay.getCompanyId());
							serviceConfig.setServiceDomainIndex(serviceDomainIndex);
						}
						_log.info("=====serviceDomainIndex(2):"+serviceDomainIndex);
						// //////////////////////////
						String serviceAdministrationIndex =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("serviceAdministrationIndex"),
								row.getClassPK(), StringPool.BLANK);
						_log.info("=====serviceAdministrationIndex(1):"+serviceAdministrationIndex);

						if (Validator.isNotNull(serviceAdministrationIndex)) {
							serviceAdministrationIndex =
								CommonUtils.generateTreeIndex(
									serviceAdministrationIndex, StringPool.PERIOD,
									WebKeys.EXTableName_DICTITEM,
									dictItemColNames.getString("dictItemNew"), WebKeys.DICTITEM,
									themeDisplay.getCompanyId());

							serviceConfig.setServiceAdministrationIndex(serviceAdministrationIndex);
						}
						_log.info("=====serviceAdministrationIndex(2):"+serviceAdministrationIndex);

						// //////////////////////
						String govAgencyIndex =
							ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
								WebKeys.EXTableName_ServiceConfig,
								columnNames.getString("govAgencyIndex"), row.getClassPK(),
								StringPool.BLANK);

						if (Validator.isNotNull(govAgencyIndex)) {
							govAgencyIndex =
								CommonUtils.generateTreeIndex(
									govAgencyIndex, StringPool.PERIOD,
									WebKeys.EXTableName_DICTITEM,
									dictItemColNames.getString("dictItemNew"), WebKeys.DICTITEM,
									themeDisplay.getCompanyId());

							serviceConfig.setGovAgencyIndex(govAgencyIndex);
						}

						ServiceConfigLocalServiceUtil.updateServiceConfig(serviceConfig);

						ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
							WebKeys.EXTableName_ServiceConfig,
							columnNames.getString("serviceConfigNew"), row.getClassPK(),
							String.valueOf(serviceConfigId));

						_log.info("=====add Success===serviceConfigId:" + serviceConfigId);
					}
					else {
						_log.info("=====sericeConfigId Existed===serviceConfigId:" + serviceConfig.getServiceConfigId());
						ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.SERVICE_CONFIG,
							WebKeys.EXTableName_ServiceConfig,
							columnNames.getString("serviceConfigNew"), row.getClassPK(),
							String.valueOf(serviceConfig.getServiceConfigId()));

					}
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
