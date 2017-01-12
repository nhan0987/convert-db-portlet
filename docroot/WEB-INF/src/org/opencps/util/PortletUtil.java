package org.opencps.util;

import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.util.StringPool;

public class PortletUtil {
	
	
	public static SplitDate splitDate(Date date) {

		return new SplitDate(date);
	};
	
	public static String getDossierDestinationFolder(long groupId, int year,
			int month, int day) {

		return String.valueOf(groupId) + StringPool.SLASH + "Dossiers"
				+ StringPool.SLASH + String.valueOf(year) + StringPool.SLASH
				+ String.valueOf(month) + StringPool.SLASH
				+ String.valueOf(day);
	}
	
	public static class SplitDate {

		public SplitDate(Date date) {

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				_miniSecond = calendar.get(Calendar.MILLISECOND);
				_second = calendar.get(Calendar.SECOND);
				_minute = calendar.get(Calendar.MINUTE);
				_hour = calendar.get(Calendar.HOUR);
				_dayOfMoth = calendar.get(Calendar.DAY_OF_MONTH);
				_dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
				_weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
				_weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
				_month = calendar.get(Calendar.MONTH);
				_year = calendar.get(Calendar.YEAR);
			}
		}

		public int getMiniSecond() {

			return _miniSecond;
		}

		public void setMiniSecond(int _miniSecond) {

			this._miniSecond = _miniSecond;
		}

		public int getSecond() {

			return _second;
		}

		public void setSecond(int _second) {

			this._second = _second;
		}

		public int getMinute() {

			return _minute;
		}

		public void setMinute(int _minute) {

			this._minute = _minute;
		}

		public int getHour() {

			return _hour;
		}

		public void setHour(int _hour) {

			this._hour = _hour;
		}

		public int getDayOfMoth() {

			return _dayOfMoth;
		}

		public void setDayOfMoth(int _dayOfMoth) {

			this._dayOfMoth = _dayOfMoth;
		}

		public int getDayOfYear() {

			return _dayOfYear;
		}

		public void setDayOfYear(int _dayOfYear) {

			this._dayOfYear = _dayOfYear;
		}

		public int getWeekOfMonth() {

			return _weekOfMonth;
		}

		public void setWeekOfMonth(int _weekOfMonth) {

			this._weekOfMonth = _weekOfMonth;
		}

		public int getWeekOfYear() {

			return _weekOfYear;
		}

		public void setWeekOfYear(int _weekOfYear) {

			this._weekOfYear = _weekOfYear;
		}

		public int getMonth() {

			return _month;
		}

		public void setMonth(int _month) {

			this._month = _month;
		}

		public int getYear() {

			return _year;
		}

		public void setYear(int _year) {

			this._year = _year;
		}

		private int _miniSecond;
		private int _second;
		private int _minute;
		private int _hour;
		private int _dayOfMoth;
		private int _dayOfYear;
		private int _weekOfMonth;
		private int _weekOfYear;
		private int _month;
		private int _year;
	}
}