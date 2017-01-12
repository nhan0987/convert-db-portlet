package org.opencps.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class PrimaryKeyBeanUtils {

	private static Log _log = LogFactoryUtil.getLog(PrimaryKeyBeanUtils.class);

	public static long getNewPrimaryKeyId(String sourceTableName,
			String sourceClassName, String sourceColumName,long classPK,
			String targetTableName, String targetClassName,
			String targetColumName, ThemeDisplay themeDisplay) {
		
		String sourcePrimaryKey = StringPool.BLANK;
		String targetPrimaryKey = StringPool.BLANK;
		
		try {
			sourcePrimaryKey = ExpandoValueLocalServiceUtil.getData(
					themeDisplay.getCompanyId(), sourceClassName, sourceTableName, sourceColumName, classPK,
					StringPool.BLANK);
			
			if(Validator.isNotNull(sourcePrimaryKey)){
				
				targetPrimaryKey = ExpandoValueLocalServiceUtil.getData(
						themeDisplay.getCompanyId(), targetClassName, targetTableName, targetColumName, Long.valueOf(sourcePrimaryKey),
						StringPool.BLANK);
			}
		} catch (NumberFormatException | PortalException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Validator.isNotNull(targetPrimaryKey)){
			return Long.valueOf(targetPrimaryKey);
		}else{
			return 0;
		}
	}
}
