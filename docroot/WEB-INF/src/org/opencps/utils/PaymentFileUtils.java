package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.paymentmgt.model.PaymentFile;
import org.opencps.paymentmgt.service.PaymentFileLocalServiceUtil;

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

public class PaymentFileUtils {

	private static Log _log = LogFactoryUtil.getLog(PaymentFileUtils.class);

	public void fetchPaymentFiles(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<PaymentFile> List = PaymentFileLocalServiceUtil
					.getPaymentFiles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_PAYMENT_FILE, WebKeys.PAYMENT_FILE,
					WebKeys.PAYMENT_FILEColumns);

			int i = 1;
			for (PaymentFile object : List) {

				_log.info("*i:" + i);

				ExpandoRowLocalServiceUtil.addRow(expandoTable.getTableId(),
						object.getPaymentFileId());

				JSONObject columnNames = WebKeys.getPAYMENT_FILEColumnNames();

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("createDate"), object
								.getPaymentFileId(), DateTimeUtil
								.convertDateToString(object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("modifiedDate"), object
								.getPaymentFileId(), DateTimeUtil
								.convertDateToString(object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("requestDateTime"), object
								.getPaymentFileId(), DateTimeUtil
								.convertDateToString(
										object.getRequestDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("confirmDateTime"), object
								.getPaymentFileId(), DateTimeUtil
								.convertDateToString(
										object.getConfirmDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("dossierId"),
						object.getPaymentFileId(),
						String.valueOf(object.getDossierId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("ownerUserId"),
						object.getPaymentFileId(),
						String.valueOf(object.getOwnerUserId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("ownerOrganizationId"),
						object.getPaymentFileId(),
						String.valueOf(object.getOwnerOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("govAgencyOrganizationId"),
						object.getPaymentFileId(),
						String.valueOf(object.getGovAgencyOrganizationId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentName"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentName()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("amount"),
						object.getPaymentFileId(),
						String.valueOf(object.getAmount()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("requestNote"),
						object.getPaymentFileId(),
						String.valueOf(object.getRequestNote()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("keypayUrl"),
						object.getPaymentFileId(),
						String.valueOf(object.getKeypayUrl()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("keypayTransaction"),
						object.getPaymentFileId(),
						String.valueOf(object.getKeypayTransactionId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("keypayGoodCode"),
						object.getPaymentFileId(),
						String.valueOf(object.getKeypayGoodCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("keypayMerchantCode"),
						object.getPaymentFileId(),
						String.valueOf(object.getKeypayMerchantCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentStatus"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentMethod"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentMethod()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("confirmFileEntryId"),
						object.getPaymentFileId(),
						String.valueOf(object.getConfirmFileEntryId()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentOptions"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentOptions()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("syncStatus"),
						object.getPaymentFileId(),
						String.valueOf(object.getSyncStatus()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("oid"),
						object.getPaymentFileId(),
						String.valueOf(object.getOid()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentConfigId"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentConfig()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentGateStatusCode"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentGateStatusCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentGateResponseCode"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentGateResponseData()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentGateCheckCode"),
						object.getPaymentFileId(),
						String.valueOf(object.getPaymentGateCheckCode()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentGateCheckResponseData"),
						object.getPaymentFileId(), String.valueOf(object
								.getPaymentGateCheckResponseData()));

				ExpandoValueLocalServiceUtil.addValue(companyId,
						WebKeys.PAYMENT_FILE, WebKeys.EXTableName_PAYMENT_FILE,
						columnNames.getString("paymentFileIdNew"),
						object.getPaymentFileId(), StringPool.BLANK);

				_log.info("=====PaymentFileId:" + object.getPaymentFileId());

				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPaymentFiles(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_PAYMENT_FILE,
					WebKeys.PAYMENT_FILE);

			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
						WebKeys.EXTableName_PAYMENT_FILE);

			} catch (NoSuchTableException nste) {
				_log.error(WebKeys.TABLE_NONE_EXISTED);
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
					WebKeys.EXTableName_PAYMENT_FILE, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				_log.info("*i:" + i);
				_log.info("=====row.getClassPK():" + row.getClassPK());

				JSONObject columnNames = WebKeys.getPAYMENT_FILEColumnNames();

				JSONObject userColumnNames = WebKeys.getUserColumnNames();

				JSONObject orgColumnNames = WebKeys
						.getOrganizationColumnNames();

				JSONObject dossierColumnNames = WebKeys.getDOSSIERColumnNames();

				JSONObject paymentConfigConfigColumnNames = WebKeys
						.getPaymentConfigColumnNames();

				// ///////////////////////////////////////////////////////////////////////////

				long dossierIdNew = PrimaryKeyBeanUtils.getNewPrimaryKeyId(
						WebKeys.EXTableName_PAYMENT_FILE, WebKeys.PAYMENT_FILE,
						columnNames.getString("dossierId"), row.getClassPK(),
						WebKeys.EXTableName_DOSSIER, WebKeys.DOSSIER,
						dossierColumnNames.getString("dossierIdNew"),
						themeDisplay);

				// ////////////////////////////////////////////////////////////////////////////

				long paymentConfigIdNew = PrimaryKeyBeanUtils
						.getNewPrimaryKeyId(WebKeys.EXTableName_PAYMENT_FILE,
								WebKeys.PAYMENT_FILE, columnNames
										.getString("processStepId"), row
										.getClassPK(),
								WebKeys.EXTableName_PaymentConfig,
								WebKeys.PAYMENTCONFIG,
								paymentConfigConfigColumnNames
										.getString("paymentConfigIdNew"),
								themeDisplay);

				if (dossierIdNew > 0) {

					String createDate = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("createDate"),
							row.getClassPK(), StringPool.BLANK);

					String modifiedDate = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("modifiedDate"),
							row.getClassPK(), StringPool.BLANK);

					String requestDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("requestDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String confirmDateTime = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("confirmDateTime"),
									row.getClassPK(), StringPool.BLANK);

					String paymentName = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("paymentName"),
							row.getClassPK(), StringPool.BLANK);

					String amount = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("amount"), row.getClassPK(),
							StringPool.BLANK);

					String requestNote = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("requestNote"),
							row.getClassPK(), StringPool.BLANK);

					String keypayUrl = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("keypayUrl"),
							row.getClassPK(), StringPool.BLANK);

					String keypayTransaction = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("keypayTransaction"),
									row.getClassPK(), StringPool.BLANK);

					String keypayGoodCode = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("keypayGoodCode"),
									row.getClassPK(), StringPool.BLANK);

					String keypayMerchantCode = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("keypayMerchantCode"),
									row.getClassPK(), StringPool.BLANK);

					String paymentStatus = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("paymentStatus"),
									row.getClassPK(), StringPool.BLANK);

					String paymentMethod = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("paymentMethod"),
									row.getClassPK(), StringPool.BLANK);

					String paymentOptions = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("paymentOptions"),
									row.getClassPK(), StringPool.BLANK);

					String syncStatus = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("syncStatus"),
							row.getClassPK(), StringPool.BLANK);

					String oid = ExpandoValueLocalServiceUtil.getData(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("oid"), row.getClassPK(),
							StringPool.BLANK);

					String paymentGateStatusCode = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames
											.getString("paymentGateStatusCode"),
									row.getClassPK(), StringPool.BLANK);

					String paymentGateResponseCode = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames
											.getString("paymentGateResponseCode"),
									row.getClassPK(), StringPool.BLANK);

					String paymentGateCheckCode = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames
											.getString("paymentGateCheckCode"),
									row.getClassPK(), StringPool.BLANK);

					String paymentGateCheckResponseData = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames
											.getString("paymentGateCheckResponseData"),
									row.getClassPK(), StringPool.BLANK);

					// ///////////////////////////////////////////////////////////////////////

					long ownerUserIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(
									WebKeys.EXTableName_PROCESS_ORDER,
									WebKeys.PROCESS_ORDER,
									columnNames.getString("ownerUserId"),
									row.getClassPK(), WebKeys.EXTableName_User,
									WebKeys.USERS,
									userColumnNames.getString("userIdNew"),
									themeDisplay);

					// ///////////////////////////////////////////////////////////////////////////

					long ownerOrganizationIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(
									WebKeys.EXTableName_PAYMENT_FILE,
									WebKeys.PAYMENT_FILE, columnNames
											.getString("ownerOrganizationId"),
									row.getClassPK(),
									WebKeys.EXTableName_Organization,
									WebKeys.ORGANIZATION, orgColumnNames
											.getString("organizationIdNew"),
									themeDisplay);

					long govAgencyOrganizationIdNew = PrimaryKeyBeanUtils
							.getNewPrimaryKeyId(
									WebKeys.EXTableName_PAYMENT_FILE,
									WebKeys.PAYMENT_FILE,
									columnNames
											.getString("govAgencyOrganizationId"),
									row.getClassPK(),
									WebKeys.EXTableName_Organization,
									WebKeys.ORGANIZATION, orgColumnNames
											.getString("organizationIdNew"),
									themeDisplay);

					// ////////////////////////////////////////////////////////////////////////////
					String confirmFileEntryId = ExpandoValueLocalServiceUtil
							.getData(
									themeDisplay.getCompanyId(),
									WebKeys.PAYMENT_FILE,
									WebKeys.EXTableName_PAYMENT_FILE,
									columnNames.getString("confirmFileEntryId"),
									row.getClassPK(), StringPool.BLANK);

					long confirmFileEntryIdNew = 0;

					if (Validator.isNotNull(confirmFileEntryId)) {

						confirmFileEntryIdNew = commonUtils.getFileEntryId(
								actionRequest, actionResponse,
								Long.valueOf(confirmFileEntryId), 0);
					}
					// ////////////////////////////////////////////////////////////////////////////

					PaymentFile paymentFile = null;

					paymentFile = PaymentFileLocalServiceUtil
							.createPaymentFile(CounterLocalServiceUtil
									.increment(WebKeys.PAYMENT_FILE));

					paymentFile
							.setCreateDate(Validator.isNotNull(createDate) ? DateTimeUtil
									.convertStringToFullDate(createDate) : null);
					paymentFile.setModifiedDate(Validator
							.isNotNull(modifiedDate) ? DateTimeUtil
							.convertStringToFullDate(modifiedDate) : null);

					paymentFile.setRequestDatetime(Validator
							.isNotNull(requestDateTime) ? DateTimeUtil
							.convertStringToFullDate(requestDateTime) : null);

					paymentFile.setConfirmDatetime(Validator
							.isNotNull(confirmDateTime) ? DateTimeUtil
							.convertStringToFullDate(confirmDateTime) : null);

					paymentFile.setDossierId(dossierIdNew);
					paymentFile.setOwnerUserId(ownerUserIdNew);
					paymentFile.setOwnerOrganizationId(ownerOrganizationIdNew);
					paymentFile
							.setGovAgencyOrganizationId(govAgencyOrganizationIdNew);
					paymentFile.setPaymentConfig(paymentConfigIdNew);

					paymentFile.setUuid(PortalUUIDUtil.generate());
					paymentFile.setPaymentName(paymentName);
					paymentFile.setAmount(Validator.isNotNull(amount) ? Double
							.valueOf(amount) : 0);
					paymentFile.setRequestNote(requestNote);
					paymentFile.setKeypayUrl(keypayUrl);
					paymentFile.setKeypayTransactionId(Validator
							.isNotNull(keypayTransaction) ? Integer
							.valueOf(keypayTransaction) : 0);
					paymentFile.setKeypayGoodCode(keypayGoodCode);
					paymentFile.setKeypayMerchantCode(keypayMerchantCode);
					paymentFile.setPaymentStatus(Validator
							.isNotNull(paymentStatus) ? Integer
							.valueOf(paymentStatus) : 0);
					paymentFile.setPaymentMethod(Validator
							.isNotNull(paymentMethod) ? Integer
							.valueOf(paymentMethod) : 0);
					paymentFile.setPaymentOptions(paymentOptions);
					paymentFile
							.setSyncStatus(Validator.isNotNull(syncStatus) ? Integer
									.valueOf(syncStatus) : 0);
					paymentFile.setOid(oid);
					paymentFile.setPaymentGateCheckCode(Validator
							.isNotNull(paymentGateCheckCode) ? Integer
							.valueOf(paymentGateCheckCode) : 0);
					paymentFile
							.setPaymentGateCheckResponseData(paymentGateCheckResponseData);
					paymentFile
							.setPaymentGateResponseData(paymentGateResponseCode);
					paymentFile.setPaymentGateStatusCode(Validator
							.isNotNull(paymentGateStatusCode) ? Integer
							.valueOf(paymentGateStatusCode) : 0);
					paymentFile.setConfirmFileEntryId(confirmFileEntryIdNew);

					PaymentFileLocalServiceUtil.addPaymentFile(paymentFile);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.PAYMENT_FILE,
							WebKeys.EXTableName_PAYMENT_FILE,
							columnNames.getString("paymentFileIdNew"),
							row.getClassPK(),
							String.valueOf(paymentFile.getPaymentFileId()));

					_log.info("=====paymentFile.getPaymentFileId():"
							+ paymentFile.getPaymentFileId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchPaymentFiles2(ThemeDisplay themeDisplay, long dossierId)
			throws Exception {

		try {

			if (dossierId > 0) {

				long companyId = themeDisplay.getCompanyId();

				List<PaymentFile> List = new ArrayList<PaymentFile>();
				List = PaymentFileLocalServiceUtil
						.getPaymentFileByD_(dossierId);

				if (List.size() > 0) {

					CommonUtils commonUtils = new CommonUtils();

					ExpandoTable expandoTable = commonUtils.checkTable(
							companyId, WebKeys.EXTableName_PAYMENT_FILE,
							WebKeys.PAYMENT_FILE, WebKeys.PAYMENT_FILEColumns);

					int i = 1;
					for (PaymentFile object : List) {

						_log.info("*i:" + i);
						_log.info("=====dossierId:" + dossierId);

						ExpandoRowLocalServiceUtil.addRow(
								expandoTable.getTableId(),
								object.getPaymentFileId());

						JSONObject columnNames = WebKeys
								.getPAYMENT_FILEColumnNames();

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("createDate"), object
										.getPaymentFileId(),
								DateTimeUtil.convertDateToString(
										object.getCreateDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("modifiedDate"), object
										.getPaymentFileId(),
								DateTimeUtil.convertDateToString(
										object.getModifiedDate(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("requestDateTime"), object
										.getPaymentFileId(),
								DateTimeUtil.convertDateToString(
										object.getRequestDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("confirmDateTime"), object
										.getPaymentFileId(),
								DateTimeUtil.convertDateToString(
										object.getConfirmDatetime(),
										DateTimeUtil._VN_DATE_TIME_FORMAT));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("dossierId"),
								object.getPaymentFileId(),
								String.valueOf(object.getDossierId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("ownerUserId"),
								object.getPaymentFileId(),
								String.valueOf(object.getOwnerUserId()));

						ExpandoValueLocalServiceUtil
								.addValue(
										companyId,
										WebKeys.PAYMENT_FILE,
										WebKeys.EXTableName_PAYMENT_FILE,
										columnNames
												.getString("ownerOrganizationId"),
										object.getPaymentFileId(),
										String.valueOf(object
												.getOwnerOrganizationId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("govAgencyOrganizationId"),
								object.getPaymentFileId(), String
										.valueOf(object
												.getGovAgencyOrganizationId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentName"),
								object.getPaymentFileId(),
								String.valueOf(object.getPaymentName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("amount"),
								object.getPaymentFileId(),
								String.valueOf(object.getAmount()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("requestNote"),
								object.getPaymentFileId(),
								String.valueOf(object.getRequestNote()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("keypayUrl"),
								object.getPaymentFileId(),
								String.valueOf(object.getKeypayUrl()));

						ExpandoValueLocalServiceUtil
								.addValue(
										companyId,
										WebKeys.PAYMENT_FILE,
										WebKeys.EXTableName_PAYMENT_FILE,
										columnNames
												.getString("keypayTransaction"),
										object.getPaymentFileId(),
										String.valueOf(object
												.getKeypayTransactionId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("keypayGoodCode"),
								object.getPaymentFileId(),
								String.valueOf(object.getKeypayGoodCode()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("keypayMerchantCode"),
								object.getPaymentFileId(),
								String.valueOf(object.getKeypayMerchantCode()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentStatus"),
								object.getPaymentFileId(),
								String.valueOf(object.getPaymentStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentMethod"),
								object.getPaymentFileId(),
								String.valueOf(object.getPaymentMethod()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("confirmFileEntryId"),
								object.getPaymentFileId(),
								String.valueOf(object.getConfirmFileEntryId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentOptions"),
								object.getPaymentFileId(),
								String.valueOf(object.getPaymentOptions()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("syncStatus"),
								object.getPaymentFileId(),
								String.valueOf(object.getSyncStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("oid"),
								object.getPaymentFileId(),
								String.valueOf(object.getOid()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentConfigId"),
								object.getPaymentFileId(),
								String.valueOf(object.getPaymentConfig()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("paymentGateStatusCode"),
								object.getPaymentFileId(), String
										.valueOf(object
												.getPaymentGateStatusCode()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("paymentGateResponseCode"),
								object.getPaymentFileId(), String
										.valueOf(object
												.getPaymentGateResponseData()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE, columnNames
										.getString("paymentGateCheckCode"),
								object.getPaymentFileId(), String
										.valueOf(object
												.getPaymentGateCheckCode()));

						ExpandoValueLocalServiceUtil
								.addValue(
										companyId,
										WebKeys.PAYMENT_FILE,
										WebKeys.EXTableName_PAYMENT_FILE,
										columnNames
												.getString("paymentGateCheckResponseData"),
										object.getPaymentFileId(),
										String.valueOf(object
												.getPaymentGateCheckResponseData()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.PAYMENT_FILE,
								WebKeys.EXTableName_PAYMENT_FILE,
								columnNames.getString("paymentFileIdNew"),
								object.getPaymentFileId(), StringPool.BLANK);

						_log.info("=====paymentFileId:"
								+ object.getPaymentFileId());

						i++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
