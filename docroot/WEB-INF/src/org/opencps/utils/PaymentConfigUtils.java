package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.paymentmgt.model.PaymentConfig;
import org.opencps.paymentmgt.service.PaymentConfigLocalServiceUtil;

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

public class PaymentConfigUtils {

	private static Log _log = LogFactoryUtil.getLog(PaymentConfigUtils.class);

	public void fetchPaymentConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<PaymentConfig> List = PaymentConfigLocalServiceUtil
					.getPaymentConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_PaymentConfig, WebKeys.PAYMENTCONFIG,
					WebKeys.PaymentConfigColumns);
			int i = 1;

			for (PaymentConfig object : List) {

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getPaymentConfigId());

				JSONObject columnNames = WebKeys.getPaymentConfigColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("govAgencyOrganizationId"),
						object.getPaymentConfigId(),
						String.valueOf(object.getGovAgencyOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("govAgencyTaxNo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getGovAgencyTaxNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("invoiceTemplateNo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getInvoiceTemplateNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("invoiceIssueNo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getInvoiceIssueNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("invoiceLastNo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getInvoiceLastNo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("bankInfo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getBankInfo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("placeInfo"),
						object.getPaymentConfigId(),
						String.valueOf(object.getPlaceInfo()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("keypayDomain"),
						object.getPaymentConfigId(),
						String.valueOf(object.getKeypayDomain()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("keypayVersion"),
						object.getPaymentConfigId(),
						String.valueOf(object.getKeypayVersion()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("keypayMerchantCode"),
						object.getPaymentConfigId(),
						String.valueOf(object.getKeypayMerchantCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("keypaySecureKey"),
						object.getPaymentConfigId(),
						String.valueOf(object.getKeypaySecureKey()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("reportTemplate"),
						object.getPaymentConfigId(),
						String.valueOf(object.getReportTemplate()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("status"),
						object.getPaymentConfigId(),
						String.valueOf(object.getStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("paymentGateType"),
						object.getPaymentConfigId(),
						String.valueOf(object.getPaymentGateType()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("returnUrl"),
						object.getPaymentConfigId(),
						String.valueOf(object.getReturnUrl()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("paymentConfigIdNew"),
						object.getPaymentConfigId(), StringPool.BLANK);

				_log.info("*i:" + i);
				_log.info("=====PaymentConfigId:" + object.getPaymentConfigId());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPaymentConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_PaymentConfig,
					WebKeys.PAYMENTCONFIG);
			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig);
			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PAYMENTCONFIG,
					WebKeys.EXTableName_PaymentConfig, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				PaymentConfig paymentConfig = null;

				row = rows.get(i);
				_log.info("*i:" + i);
				JSONObject columnNames = WebKeys.getPaymentConfigColumnNames();

				JSONObject orgsColNames = WebKeys.getOrganizationColumnNames();

				String govAgencyOrganizationId = ExpandoValueLocalServiceUtil
						.getData(themeDisplay.getCompanyId(),
								WebKeys.PAYMENTCONFIG,
								WebKeys.EXTableName_PaymentConfig, columnNames
										.getString("govAgencyOrganizationId"),
								row.getClassPK(), StringPool.BLANK);

				String govAgencyOrganizationIdNew = StringPool.BLANK;

				if (Validator.isNotNull(govAgencyOrganizationId)) {

					govAgencyOrganizationIdNew = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.ORGANIZATION,
									WebKeys.EXTableName_Organization,
									orgsColNames.getString("organizationIdNew"),
									Long.valueOf(govAgencyOrganizationId),
									StringPool.BLANK);
				}

				String paymentGateType = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.PAYMENTCONFIG,
						WebKeys.EXTableName_PaymentConfig,
						columnNames.getString("paymentGateType"),
						row.getClassPK(), StringPool.BLANK);

				if (Validator.isNotNull(govAgencyOrganizationIdNew)) {

					if (Validator.isNotNull(paymentGateType)) {

						long paymentGateTypeLong = Long
								.valueOf(paymentGateType);

						if (paymentGateTypeLong > 0) {

							paymentConfig = PaymentConfigLocalServiceUtil
									.getPaymentConfigByGovAgency(
											themeDisplay.getScopeGroupId(),
											Long.valueOf(govAgencyOrganizationIdNew),
											paymentGateTypeLong);

							if (Validator.isNull(paymentConfig)) {

								String govAgencyTaxNo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("govAgencyTaxNo"),
												row.getClassPK(),
												StringPool.BLANK);

								String invoiceTemplateNo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("invoiceTemplateNo"),
												row.getClassPK(),
												StringPool.BLANK);

								String invoiceIssueNo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("invoiceIssueNo"),
												row.getClassPK(),
												StringPool.BLANK);

								String invoiceLastNo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("invoiceLastNo"),
												row.getClassPK(),
												StringPool.BLANK);

								String bankInfo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("bankInfo"),
												row.getClassPK(),
												StringPool.BLANK);

								String placeInfo = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("placeInfo"),
												row.getClassPK(),
												StringPool.BLANK);

								String keypayDomain = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("keypayDomain"),
												row.getClassPK(),
												StringPool.BLANK);

								String keypayVersion = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("keypayVersion"),
												row.getClassPK(),
												StringPool.BLANK);

								String keypayMerchantCode = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("keypayMerchantCode"),
												row.getClassPK(),
												StringPool.BLANK);

								String keypaySecureKey = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("keypaySecureKey"),
												row.getClassPK(),
												StringPool.BLANK);

								String reportTemplate = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("reportTemplate"),
												row.getClassPK(),
												StringPool.BLANK);

								String status = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames.getString("status"),
												row.getClassPK(),
												StringPool.BLANK);

								String returnUrl = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("returnUrl"),
												row.getClassPK(),
												StringPool.BLANK);

								long paymentConfigId = CounterLocalServiceUtil
										.increment(WebKeys.PAYMENTCONFIG);

								paymentConfig = PaymentConfigLocalServiceUtil
										.createPaymentConfig(paymentConfigId);

								if (Validator
										.isNotNull(govAgencyOrganizationIdNew)) {
									paymentConfig
											.setGovAgencyOrganizationId(Long
													.valueOf(govAgencyOrganizationIdNew));
								}
								if (Validator.isNotNull(status)) {
									paymentConfig.setStatus(Boolean
											.valueOf(status));
								}
								if (Validator.isNotNull(paymentGateType)) {
									paymentConfig.setPaymentGateType(Long
											.valueOf(paymentGateType));
								}
								paymentConfig.setGovAgencyTaxNo(govAgencyTaxNo);
								paymentConfig
										.setInvoiceTemplateNo(invoiceTemplateNo);
								paymentConfig.setInvoiceIssueNo(invoiceIssueNo);
								paymentConfig.setInvoiceLastNo(invoiceLastNo);
								paymentConfig.setBankInfo(bankInfo);
								paymentConfig.setPlaceInfo(placeInfo);
								paymentConfig.setKeypayDomain(keypayDomain);
								paymentConfig.setKeypayVersion(keypayVersion);
								paymentConfig
										.setKeypayMerchantCode(keypayMerchantCode);
								paymentConfig
										.setKeypaySecureKey(keypaySecureKey);
								paymentConfig.setReportTemplate(reportTemplate);
								paymentConfig.setReturnUrl(returnUrl);

								paymentConfig.setCompanyId(themeDisplay
										.getCompanyId());
								paymentConfig.setGroupId(serviceContext
										.getScopeGroupId());
								paymentConfig.setUserId(serviceContext
										.getUserId());

								PaymentConfigLocalServiceUtil
										.updatePaymentConfig(paymentConfig);

								ExpandoValueLocalServiceUtil
										.addValue(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("paymentConfigIdNew"),
												row.getClassPK(),
												String.valueOf(paymentConfig
														.getPaymentConfigId()));

								_log.info("=====addSuccess=====paymentConfigId:"
										+ paymentConfig
										.getPaymentConfigId());

							} else {
								ExpandoValueLocalServiceUtil
										.addValue(
												themeDisplay.getCompanyId(),
												WebKeys.PAYMENTCONFIG,
												WebKeys.EXTableName_PaymentConfig,
												columnNames
														.getString("paymentConfigIdNew"),
												row.getClassPK(),
												String.valueOf(paymentConfig
														.getPaymentConfigId()));

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
