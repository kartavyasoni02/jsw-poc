package com.yash.jsw.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	private static final String DATE_FORMAT_WITH_TIME = "dd/MM/yyyy HH:mm:ss";

	public static String convertFromTimestampToFormStringWithTime(Timestamp dateTime) {
		String time = null;
		if (null != dateTime) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					DATE_FORMAT_WITH_TIME);
			time = dateFormat.format(new Date(dateTime.getTime()));
		}
		return time;
	}
	public static String convertFromTimestampLongToString(long time){
	    Date date = new Date(time);
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
				DATE_FORMAT_WITH_TIME);
	    return dateFormat.format(date);
	}
	public static String convertTimeToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sdt = df.format(date);
		return sdt;
	}
}
