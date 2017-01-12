package org.opencps.utils;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;



public class ArrayConditionUtils {
		
		public void getCondition(ActionRequest actionRequest,
				ActionResponse actionResponse, String contition,String pattern) {
			
			this.setConditionArray(null);
			this.setConditionList(null);

			if (Validator.isNotNull(contition)) {

				this.setConditionArray(StringUtil.split(contition.trim(),
						pattern));

				this.setConditionList(Arrays.asList(conditionArray));
			}

		}
		
		protected List<String> conditionList;
		
		public List<String> getConditionList() {
			return conditionList;
		}

		public void setConditionList(List<String> conditionList) {
			this.conditionList = conditionList;
		}

		public String[] getConditionArray() {
			return conditionArray;
		}

		public void setConditionArray(String[] conditionArray) {
			this.conditionArray = conditionArray;
		}

		protected String[] conditionArray;
	}