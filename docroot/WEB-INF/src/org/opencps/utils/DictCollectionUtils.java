package org.opencps.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;

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

public class DictCollectionUtils {

	private static Log _log = LogFactoryUtil.getLog(DictCollectionUtils.class);

	public void fetchDictCollection(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();

			List<DictCollection> dictCollectionList = DictCollectionLocalServiceUtil
					.getDictCollections(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			CommonUtils commonUtils = new CommonUtils();

			ExpandoTable expandoTable = commonUtils.checkTable(companyId,
					WebKeys.EXTableName_DICTCOLLECION, WebKeys.DICTCOLLECION,
					WebKeys.DictCollectionColumnNames);

			String collectionCode = ParamUtil.getString(actionRequest,
					"collectionCode");

			ArrayConditionUtils arrayContitionUtils = new ArrayConditionUtils();
			arrayContitionUtils.getCondition(actionRequest, actionResponse,
					collectionCode, StringPool.PIPE);

			String[] arrayCollectionCode = arrayContitionUtils
					.getConditionArray();

			_log.info("=====fetching...dictCollection");

			for (DictCollection dictCollection : dictCollectionList) {

				if (ArrayUtil.contains(arrayCollectionCode,
						dictCollection.getCollectionCode())) {

					ExpandoRowLocalServiceUtil.addRow(
							expandoTable.getTableId(),
							dictCollection.getDictCollectionId());

					JSONObject columnNames = WebKeys
							.getDictCollectionColumnNames();

					ExpandoValueLocalServiceUtil.addValue(companyId,
							WebKeys.DICTCOLLECION,
							WebKeys.EXTableName_DICTCOLLECION,
							columnNames.getString("collectionCode"),
							dictCollection.getDictCollectionId(),
							String.valueOf(dictCollection.getCollectionCode()));

					ExpandoValueLocalServiceUtil.addValue(companyId,
							WebKeys.DICTCOLLECION,
							WebKeys.EXTableName_DICTCOLLECION,
							columnNames.getString("collectionName"),
							dictCollection.getDictCollectionId(),
							String.valueOf(dictCollection.getCollectionName()));

					ExpandoValueLocalServiceUtil.addValue(companyId,
							WebKeys.DICTCOLLECION,
							WebKeys.EXTableName_DICTCOLLECION,
							columnNames.getString("description"),
							dictCollection.getDictCollectionId(),
							String.valueOf(dictCollection.getDescription()));

					ExpandoValueLocalServiceUtil.addValue(companyId,
							WebKeys.DICTCOLLECION,
							WebKeys.EXTableName_DICTCOLLECION,
							columnNames.getString("dictCollectionIdNew"),
							dictCollection.getDictCollectionId(),
							StringPool.BLANK);
				}

			}
			_log.info("=====fetdone...dictCollection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDictCollection(ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			ExpandoTable expandoTable = null;
			String message = null;

			CommonUtils commonUtils = new CommonUtils();
			commonUtils.changeClassId(WebKeys.EXTableName_DICTCOLLECION,
					WebKeys.DICTCOLLECION);
			try {

				expandoTable = ExpandoTableLocalServiceUtil.getTable(
						themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
						WebKeys.EXTableName_DICTCOLLECION);
			} catch (NoSuchTableException nste) {
				_log.error("Table  not existed to show the data. please add data first and comeback to to see the data");

			}

			List<ExpandoRow> rows = new ArrayList<ExpandoRow>();
			ExpandoRow row = null;

			DictCollection dictCollection = null;

			rows = ExpandoRowLocalServiceUtil.getRows(
					themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
					WebKeys.EXTableName_DICTCOLLECION, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			_log.info("=====adding..dictCollection");

			for (int i = 0; i < rows.size(); i++) {

				row = rows.get(i);

				JSONObject columnNames = WebKeys.getDictCollectionColumnNames();

				String collectionCode = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
						WebKeys.EXTableName_DICTCOLLECION,
						columnNames.getString("collectionCode"),
						row.getClassPK(), StringPool.BLANK);

				String collectionName = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
						WebKeys.EXTableName_DICTCOLLECION,
						columnNames.getString("collectionName"),
						row.getClassPK(), StringPool.BLANK);

				String description = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
						WebKeys.EXTableName_DICTCOLLECION,
						columnNames.getString("desctiption"), row.getClassPK(),
						StringPool.BLANK);

				dictCollection = DictCollectionLocalServiceUtil
						.getDictCollection(themeDisplay.getScopeGroupId(),
								collectionCode);

				if (Validator.isNotNull(dictCollection)) {

					if (collectionCode.equals(dictCollection
							.getCollectionCode())) {

						ExpandoValueLocalServiceUtil.addValue(themeDisplay
								.getCompanyId(), WebKeys.DICTCOLLECION,
								WebKeys.EXTableName_DICTCOLLECION, columnNames
										.getString("dictCollectionIdNew"), row
										.getClassPK(), String
										.valueOf(dictCollection
												.getDictCollectionId()));

					}
				} else {
					long dictCollectionId = CounterLocalServiceUtil
							.increment(WebKeys.DICTCOLLECION);
					dictCollection = DictCollectionLocalServiceUtil
							.createDictCollection(dictCollectionId);

					dictCollection.setCollectionCode(collectionCode);
					dictCollection.setCollectionName(collectionName);
					dictCollection.setDescription(description);

					dictCollection.setCompanyId(themeDisplay.getCompanyId());
					dictCollection.setUserId(serviceContext.getUserId());
					dictCollection.setGroupId(serviceContext.getScopeGroupId());

					DictCollectionLocalServiceUtil
							.addDictCollection(dictCollection);

					ExpandoValueLocalServiceUtil.addValue(
							themeDisplay.getCompanyId(), WebKeys.DICTCOLLECION,
							WebKeys.EXTableName_DICTCOLLECION,
							columnNames.getString("dictCollectionIdNew"),
							row.getClassPK(), String.valueOf(dictCollectionId));
				}

			}
			_log.info("=====done..dictCollection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
