package com.yash.jsw.utility;
/**
 * @author kartavya.soni
 */
import java.io.Serializable;

public class CronExpressionUtility implements Serializable {
	private static final long serialVersionUID = -1676663054009319677L;
	
	private String startDate;
	private String time;
	private static boolean sun;
	private static boolean mon;
	private static boolean tue;
	private static boolean wed;
	private static boolean thu;
	private static boolean fri;
	private static boolean sat;
	private boolean yearly;
	private boolean monthly;
	private boolean weekly;
	private boolean daily;
	private boolean hourly;
	private boolean minutely;
	private boolean specificTime = true;
	
	private String minuteInterval;
	private String hourInterval;
	private String dayInterval;
	private String monthDate;
	private String month;
	private String day;
	
	/*public static void main(String[] args) {
		CronExpressionUtility pCron = new CronExpressionUtility();
		pCron.setSpecificTime(true);

		// pCron.setMinutely(true);
		// pCron.setEveryMinute("5");

		// pCron.setHourly(true);
		// pCron.setEveryHour("2");

		// pCron.setDaily(true);
		// pCron.setEveryDay(everyDay);

		// pCron.setWeekly(true);
		// pCron.setMON(true);
		// pCron.setTHU(true);

		//pCron.setMonthly(true);
		//pCron.setMonthDate("15");
		
		pCron.setYearly(true);
		pCron.setMonth("2");
		pCron.setMonthDate("5");

		pCron.setTime("03:02");
		System.out.println(pCron.getCronExpression());
	}*/
		
	public String getCronExpression() { // second minute hour day month week
		String cronExp = "", ss = "", mm = "", hh = "", dd = "", mmm = "", ww = "";
		String hour = "";
		String minutes = "";
		if (isSpecificTime()) {
			String newTime = getTime();
			String[] hhmm = newTime.split("\\:");
			hour = String.valueOf(Integer.parseInt(hhmm[0]));
			minutes = String.valueOf(Integer.parseInt(hhmm[1]));
		}
		if (isMinutely()) {
			ss = "0";
			mm = "0/" + getMinuteInterval();
			hh = "*";
			dd = "1/1";
			mmm = "*";
			ww = "?";
		} else if (isHourly()) {
			if (isSpecificTime()) {
				ss = "0";
				mm = minutes;
				hh = hour;
				dd = "1/1";
				mmm = "*";
				ww = "?";
			} else {
				ss = "0";
				mm = "0";
				hh = "0/" + getHourInterval();
				dd = "1/1";
				mmm = "*";
				ww = "?";
			}
		} else if (isDaily()) {
			ss = "0";
			mm = minutes;
			hh = hour;
			dd = "1/1";
			mmm = "*";
			ww = "?";
		} else if (isWeekly()) {
			ss = "0";
			mm = minutes;
			hh = hour;
			dd = "?";
			mmm = "*";
			ww = getDay();//getDayString();
		} else if (isMonthly()) {
			ss = "0";
			mm = minutes;
			hh = hour;
			dd = getMonthDate();
			mmm = "1/1";
			ww = "?";
		} else if (isYearly()) {
			ss = "0";
			mm = minutes;
			hh = hour;
			dd = getMonthDate();
			mmm = getMonth();
			ww = "?";
		}
		cronExp = generateCronExpression(ss, mm, hh, dd, mmm, ww);
		return cronExp;
	}

	private String generateCronExpression(String ss, String mm, String hh,
			String dd, String mmm, String ww) {
		return ss + " " + mm + " " + hh + " " + dd + " " + mmm + " " + ww;
	}


	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * The date set should be of the format (MM-DD-YYYY for example 25-04-2011)
	 * 
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * The time set should be of the format (HH:MM AM/PM for example 12:15 PM)
	 * 
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	public boolean isSUN() {
		return sun;
	}

	public void setSUN(boolean sUN) {
		sun = sUN;
	}

	public boolean isMON() {
		return mon;
	}

	/**
	 * @param mON
	 *            the mON to set
	 */
	public void setMON(boolean mON) {
		mon = mON;
	}

	public boolean isTUE() {
		return tue;
	}

	public void setTUE(boolean tUE) {
		tue = tUE;
	}

	public boolean isWED() {
		return wed;
	}

	public void setWED(boolean wED) {
		wed = wED;
	}

	public boolean isTHU() {
		return thu;
	}

	public void setTHU(boolean tHU) {
		thu = tHU;
	}

	public boolean isFRI() {
		return fri;
	}

	public void setFRI(boolean fRI) {
		fri = fRI;
	}

	public boolean isSAT() {
		return sat;
	}

	public void setSAT(boolean sAT) {
		sat = sAT;
	}

	public int hashCode() {
		return this.getCronExpression().hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof CronExpressionUtility) {
			if (((CronExpressionUtility) obj).getCronExpression().equalsIgnoreCase(
					this.getCronExpression())) {
				return true;
			}
		} else {
			return false;
		}
		return false;

	}

	public boolean isYearly() {
		return yearly;
	}

	public void setYearly(boolean yearly) {
		this.yearly = yearly;
	}

	public boolean isMonthly() {
		return monthly;
	}

	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}

	public boolean isWeekly() {
		return weekly;
	}

	public void setWeekly(boolean weekly) {
		this.weekly = weekly;
	}

	public boolean isDaily() {
		return daily;
	}

	public void setDaily(boolean daily) {
		this.daily = daily;
	}

	public boolean isHourly() {
		return hourly;
	}

	public void setHourly(boolean hourly) {
		this.hourly = hourly;
	}

	public boolean isMinutely() {
		return minutely;
	}

	public void setMinutely(boolean minutely) {
		this.minutely = minutely;
	}

	public boolean isSpecificTime() {
		return specificTime;
	}

	public void setSpecificTime(boolean specificTime) {
		this.specificTime = specificTime;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMinuteInterval() {
		return minuteInterval;
	}

	public void setMinuteInterval(String minuteInterval) {
		this.minuteInterval = minuteInterval;
	}

	public String getHourInterval() {
		return hourInterval;
	}

	public void setHourInterval(String hourInterval) {
		this.hourInterval = hourInterval;
	}

	public String getDayInterval() {
		return dayInterval;
	}

	public void setDayInterval(String dayInterval) {
		this.dayInterval = dayInterval;
	}
}
