package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.datamgt.NoSuchDictItemException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
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

public class DictItemUtils {

	private static Log _log = LogFactoryUtil.getLog(DictItemUtils.class);

	public void fetchDictItem(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DictItem> dictItemList = DictItemLocalServiceUtil
					.getDictItems(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DICTITEM, WebKeys.DICTITEM,
					WebKeys.DictItemColumnNames);

			String collectionCode = ParamUtil.getString(actionRequest,
					"collectionCode");

			ArrayConditionUtils arrayContitionUtils = new ArrayConditionUtils();
			arrayContitionUtils.getCondition(actionRequest, actionResponse,
					collectionCode, StringPool.PIPE);

			String[] arrayCollectionCode = arrayContitionUtils
					.getConditionArray();

			_log.info("=====fetching...dictItem");
			int i = 1;
			for (DictItem dictItem : dictItemList) {
				_log.info("*i:" + i);
				_log.info("=====dictItemId:" + dictItem.getDictItemId());

				DictCollection dictCollection = null;

				if (dictItem.getDictCollectionId() > 0) {

					dictCollection = DictCollectionLocalServiceUtil
							.getDictCollection(dictItem.getDictCollectionId());

					if (!ArrayUtil.contains(arrayCollectionCode,
							dictCollection.getCollectionCode())) {

						ExpandoRowLocalServiceUtil.addRow(
								expandoTable.getTableId(),
								dictItem.getDictItemId());

						JSONObject columnNames = WebKeys
								.getDictItemColumnNames();

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("dictCollectionId"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getDictCollectionId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("itemCode"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getItemCode()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("itemName"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getItemName()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("itemDescription"),
								dictItem.getDictItemId(),
								dictItem.getItemDescription());

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("parentItemId"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getParentItemId()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("treeIndex"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getTreeIndex()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("issueStatus"),
								dictItem.getDictItemId(),
								String.valueOf(dictItem.getIssueStatus()));

						ExpandoValueLocalServiceUtil.addValue(companyId,
								WebKeys.DICTITEM, WebKeys.EXTableName_DICTITEM,
								columnNames.getString("dictItemNew"),
								dictItem.getDictItemId(), StringPool.BLANK);

						i++;
					}
				}
			}

			_log.info("=====fetdone...dictItem");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDicItem1(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;
			String message = null;
			try {
				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM);
			} catch (NoSuchTableException nste) {
				message = "Table  not existed to show the data. please add data first and comeback to to see the data";
			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DICTITEM,
					WebKeys.EXTableName_DICTITEM, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			_log.info("=====adding..dictItem");

			for (int i = 0; i < rows.size(); i++) {

				DictItem dictItem = null;

				row = rows.get(i);

				_log.info("*i:" + i);

				JSONObject columnNames = WebKeys.getDictItemColumnNames();

				JSONObject dictCollecColNames = WebKeys
						.getDictCollectionColumnNames();

				String dictCollectionId = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM,
						columnNames.getString("dictCollectionId"),
						row.getClassPK(), StringPool.BLANK);

				String dictCollectionIdNew = StringPool.BLANK;

				if (Validator.isNotNull(dictCollectionId)) {

					dictCollectionIdNew = ExpandoValueLocalServiceUtil
							.getData(themeDisplay.getCompanyId(),
									WebKeys.DICTCOLLECION,
									WebKeys.EXTableName_DICTCOLLECION,
									dictCollecColNames
											.getString("dictCollectionIdNew"),
									Long.valueOf(dictCollectionId),
									StringPool.BLANK);
				}

				String itemCode = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM,
						columnNames.getString("itemCode"), row.getClassPK(),
						StringPool.BLANK);

				String itemName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM,
						columnNames.getString("itemName"), row.getClassPK(),
						StringPool.BLANK);

				String itemDescription = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM,
						columnNames.getString("itemDescription"),
						row.getClassPK(), StringPool.BLANK);

				String issueStatus = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTITEM,
						WebKeys.EXTableName_DICTITEM,
						columnNames.getString("issueStatus"), row.getClassPK(),
						StringPool.BLANK);

				// String treeIndex = ExpandoValueLocalServiceUtil.getData(
				// themeDisplay.getCompanyId(), WebKeys.DICTITEM,
				// WebKeys.EXTableName_DICTITEM,
				// columnNames.getString("treeIndex"), row.getClassPK(),
				// StringPool.BLANK);

				try {

					if (Validator.isNotNull(dictCollectionIdNew)) {
						dictItem = DictItemLocalServiceUtil
								.getDictItemInuseByItemCode(
										Long.valueOf(dictCollectionIdNew),
										itemCode);
					}
				} catch (NoSuchDictItemException e) {

				}
				long dictItemId = 0;

				if (Validator.isNull(dictItem)) {

					_log.info("=====Creating DictItem=====");

					dictItemId = CounterLocalServiceUtil
							.increment(WebKeys.DICTITEM);

					dictItem = DictItemLocalServiceUtil
							.createDictItem(dictItemId);

					dictItem.setItemCode(itemCode);
					dictItem.setItemDescription(itemDescription);
					dictItem.setItemName(itemName);
					dictItem.setIssueStatus(Validator.isNotNull(issueStatus) ? Integer
							.valueOf(issueStatus) : 0);
					dictItem.setDictCollectionId(Validator
							.isNotNull(dictCollectionIdNew) ? Long
							.valueOf(dictCollectionIdNew) : 0);

					// dictItem.setTreeIndex(treeIndex);
					dictItem.setItemDescription(String.valueOf(row.getClassPK()));
					dictItem.setCompanyId(themeDisplay.getCompanyId());
					dictItem.setUserId(serviceContext.getUserId());
					dictItem.setGroupId(serviceContext.getScopeGroupId());

					DictItemLocalServiceUtil.addDictItem(dictItem);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DICTITEM,
							WebKeys.EXTableName_DICTITEM,
							columnNames.getString("dictItemNew"),
							row.getClassPK(), String.valueOf(dictItemId));

					_log.info("=====addSuccess=====dictItemId:" + dictItemId);

				} else {
					_log.info("=====dictItemId existed:"
							+ dictItem.getDictItemId());
					_log.info("=====with dictCollectionId:"
							+ dictCollectionIdNew);
					_log.info("=====with itemCode:" + itemCode);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DICTITEM,
							WebKeys.EXTableName_DICTITEM,
							columnNames.getString("dictItemNew"),
							row.getClassPK(),
							String.valueOf(dictItem.getDictItemId()));

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DICTITEM,
							WebKeys.EXTableName_DICTITEM,
							columnNames.getString("parentItemId"),
							row.getClassPK(),
							String.valueOf(dictItem.getParentItemId()));

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DICTITEM,
							WebKeys.EXTableName_DICTITEM,
							columnNames.getString("treeIndex"),
							row.getClassPK(), dictItem.getTreeIndex());

				}

			}

			_log.info("=====done..dictItem");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void addTreeIndex1(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			String parentItemId = StringPool.BLANK;
			String treeIndex = StringPool.BLANK;
			String parentItemIdNew = StringPool.BLANK;

			JSONObject columnNames = WebKeys.getDictItemColumnNames();

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow object = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DICTITEM,
					WebKeys.EXTableName_DICTITEM, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (int i = 0; i < rows.size(); i++) {

				_log.info("*i:" + i);

				object = rows.get(i);

				_log.info("=====row:" + object.getClassPK());

				String dictItemIdNewString = ExpandoValueLocalServiceUtil
						.getData(themeDisplay.getCompanyId(), WebKeys.DICTITEM,
								WebKeys.EXTableName_DICTITEM,
								columnNames.getString("dictItemNew"),
								object.getClassPK(), StringPool.BLANK);

				if (Validator.isNotNull(dictItemIdNewString)) {

					long dictItemIdNew = 0;
					dictItemIdNew = Long.valueOf(dictItemIdNewString);

					DictItem dictItem = null;

					if (dictItemIdNew > 0) {

						dictItem = DictItemLocalServiceUtil.getDictItem(object
								.getClassPK());

						parentItemIdNew = StringPool.BLANK;

						parentItemId = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.DICTITEM,
								WebKeys.EXTableName_DICTITEM,
								columnNames.getString("parentItemId"),
								object.getClassPK(), StringPool.BLANK);
						treeIndex = ExpandoValueLocalServiceUtil.getData(
								themeDisplay.getCompanyId(), WebKeys.DICTITEM,
								WebKeys.EXTableName_DICTITEM,
								columnNames.getString("treeIndex"),
								object.getClassPK(), StringPool.BLANK);

						if (Validator.isNotNull(treeIndex)) {

							treeIndex = CommonUtils.generateTreeIndex(
									treeIndex, StringPool.PERIOD,
									WebKeys.EXTableName_DICTITEM,
									columnNames.getString("dictItemNew"),
									WebKeys.DICTITEM, companyId);

							dictItem.setTreeIndex(treeIndex);
						}

						if (Validator.isNotNull(parentItemId)) {

							parentItemIdNew = ExpandoValueLocalServiceUtil
									.getData(themeDisplay.getCompanyId(),
											WebKeys.DICTITEM,
											WebKeys.EXTableName_DICTITEM,
											columnNames
													.getString("dictItemNew"),
											Long.valueOf(parentItemId),
											StringPool.BLANK);

							if (Validator.isNotNull(parentItemIdNew)) {
								dictItem.setParentItemId(Long
										.valueOf(parentItemIdNew));
							}
						}
						// dictItem.setItemDescription(StringPool.BLANK);
						DictItemLocalServiceUtil.updateDictItem(dictItem);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreeIndex(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DictItem> List = DictItemLocalServiceUtil.getDictItems(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String parentItemId = StringPool.BLANK;
			String treeIndex = StringPool.BLANK;
			String dictItemIdNew = StringPool.BLANK;

			JSONObject columnNames = WebKeys.getDictItemColumnNames();

			String collectionCode = ParamUtil.getString(actionRequest,
					"collectionCode");

			ArrayConditionUtils arrayContitionUtils = new ArrayConditionUtils();
			arrayContitionUtils.getCondition(actionRequest, actionResponse,
					collectionCode, StringPool.PIPE);

			String[] arrayCollectionCode = arrayContitionUtils
					.getConditionArray();

			int i = 1;

			for (DictItem object : List) {

				_log.info("*i:" + i);
				_log.info("=====dictId:" + object.getDictItemId());

				DictCollection dictCollection = null;

				if (object.getDictCollectionId() > 0) {

					dictCollection = DictCollectionLocalServiceUtil
							.getDictCollection(object.getDictCollectionId());

					if (!ArrayUtil.contains(arrayCollectionCode,
							dictCollection.getCollectionCode())) {

						String classPK = object.getItemDescription();

						if (Validator.isDigit(classPK)) {

							long classPKLong = Long.valueOf(classPK);

							dictItemIdNew = StringPool.BLANK;

							parentItemId = ExpandoValueLocalServiceUtil
									.getData(themeDisplay.getCompanyId(),
											WebKeys.DICTITEM,
											WebKeys.EXTableName_DICTITEM,
											columnNames
													.getString("parentItemId"),
											classPKLong, StringPool.BLANK);
							treeIndex = ExpandoValueLocalServiceUtil.getData(
									themeDisplay.getCompanyId(),
									WebKeys.DICTITEM,
									WebKeys.EXTableName_DICTITEM,
									columnNames.getString("treeIndex"),
									classPKLong, StringPool.BLANK);

							if (Validator.isNotNull(treeIndex)) {

								treeIndex = CommonUtils.generateTreeIndex(
										treeIndex, StringPool.PERIOD,
										WebKeys.EXTableName_DICTITEM,
										columnNames.getString("dictItemNew"),
										WebKeys.DICTITEM, companyId);

								object.setTreeIndex(treeIndex);
							}
							if (Validator.isNotNull(parentItemId)) {

								dictItemIdNew = ExpandoValueLocalServiceUtil
										.getData(
												themeDisplay.getCompanyId(),
												WebKeys.DICTITEM,
												WebKeys.EXTableName_DICTITEM,
												columnNames
														.getString("dictItemNew"),
												Long.valueOf(parentItemId),
												StringPool.BLANK);

								if (Validator.isNotNull(dictItemIdNew)) {
									object.setParentItemId(Long
											.valueOf(dictItemIdNew));
								}
							}
							object.setItemDescription(StringPool.BLANK);
							DictItemLocalServiceUtil.updateDictItem(object);
						}
					}
				}

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
