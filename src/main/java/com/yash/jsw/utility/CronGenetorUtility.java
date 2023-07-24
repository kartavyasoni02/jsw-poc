package com.yash.jsw.utility;

import com.yash.jsw.model.QuartzModel;
import static com.yash.jsw.utility.GlobalConstant.DAILY;
import static com.yash.jsw.utility.GlobalConstant.HOURLY;
import static com.yash.jsw.utility.GlobalConstant.MINUTELY;
import static com.yash.jsw.utility.GlobalConstant.MONTHLY;
import static com.yash.jsw.utility.GlobalConstant.WEEKLY;
import static com.yash.jsw.utility.GlobalConstant.YEARLY;

public class CronGenetorUtility {
	public static String generateCronExpression(QuartzModel model) {
		CronExpressionUtility cronExp = new CronExpressionUtility();
		populateCronIpFromExpression(model, cronExp);
		return cronExp.getCronExpression();
	}

	/**
	 * @param model
	 * @param cronExp
	 */
	private static void populateCronIpFromExpression(QuartzModel model,CronExpressionUtility cronExp) {
		
		if(model.getScheduleRadio().equalsIgnoreCase(YEARLY)){
			cronExp.setYearly(true);
		}else if(model.getScheduleRadio().equalsIgnoreCase(MONTHLY)){
			cronExp.setMonthly(true);
		}else if(model.getScheduleRadio().equalsIgnoreCase(WEEKLY)){
			cronExp.setWeekly(true);
		}else if(model.getScheduleRadio().equalsIgnoreCase(DAILY)){
			cronExp.setDaily(true);
		}else if(model.getScheduleRadio().equalsIgnoreCase(HOURLY)){
			cronExp.setHourly(true); cronExp.setSpecificTime(false);
		}else if(model.getScheduleRadio().equalsIgnoreCase(MINUTELY)){
			cronExp.setMinutely(true); cronExp.setSpecificTime(false);
		}
		cronExp.setMinuteInterval(model.getMinuteInterval());
		cronExp.setHourInterval(model.getHourInterval());
		cronExp.setDayInterval(model.getDayInterval());
		cronExp.setMonthDate(model.getMonthDate());
		cronExp.setMonth(model.getMonth());
		cronExp.setDay(model.getDayOfWeek());
		cronExp.setTime(model.getTime());
		
		cronExp.setStartDate(model.getStartDate());
		
	}
}
