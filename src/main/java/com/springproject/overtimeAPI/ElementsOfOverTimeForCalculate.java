package com.springproject.overtimeAPI;

public class ElementsOfOverTimeForCalculate {
	private String acceptDate;
	private String acceptTime;
	private String measureTime;
	
	private String thisYear;
	private String thisMonth;
	private String thisDay;
	private String completeTime;
	
	private String extensionOverTime;
	private String nightTimeOvertime;
	private String holidayOvertimeOfExceed8Hours;
	private String holidayOvertimeOfNotExceed8Hours;
	
	public ElementsOfOverTimeForCalculate(String acceptTime) {
		setThisYearAndMonthAndDay(acceptTime);
		this.completeTime=calculateCompleteTime();
	}
	
	public String getExtensionOverTime() {
		return extensionOverTime;
	}
	public void setExtensionOverTime(String extensionOverTime) {
		this.extensionOverTime = extensionOverTime;
	}
	public String getNightTimeOvertime() {
		return nightTimeOvertime;
	}
	public void setNightTimeOvertime(String nightTimeOvertime) {
		this.nightTimeOvertime = nightTimeOvertime;
	}
	public String getHolidayOvertimeOfExceed8Hours() {
		return holidayOvertimeOfExceed8Hours;
	}
	public void setHolidayOvertimeOfExceed8Hours(String holidayOvertimeOfExceed8Hours) {
		this.holidayOvertimeOfExceed8Hours = holidayOvertimeOfExceed8Hours;
	}
	public String getHolidayOvertimeOfNotExceed8Hours() {
		return holidayOvertimeOfNotExceed8Hours;
	}
	public void setHolidayOvertimeOfNotExceed8Hours(String holidayOvertimeOfNotExceed8Hours) {
		this.holidayOvertimeOfNotExceed8Hours = holidayOvertimeOfNotExceed8Hours;
	}
	
	public String getMeasureTime() {
		return measureTime;
	}
	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}
	public String getThisYear() {
		return thisYear;
	}
	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}
	public String getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(String thisMonth) {
		this.thisMonth = thisMonth;
	}
	public String getThisDay() {
		return thisDay;
	}
	public void setThisDay(String thisDay) {
		this.thisDay = thisDay;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	
	public void setThisYearAndMonthAndDay(String acceptTime) {
		String[] thisDate=acceptDate.split(".");
		this.thisYear=thisDate[0];
		this.thisMonth=thisDate[1];
		this.thisDay=thisDate[2];
	}
	
	public String calculateCompleteTime() {
		String[] acceptTimeSplitedForCalculate=(this.acceptTime).split(":");
		Double hourOfAcceptTimeForCalculate=Double.parseDouble(acceptTimeSplitedForCalculate[0]);
		Double minuteOfAcceptTimeForCalculate=Double.parseDouble(acceptTimeSplitedForCalculate[1]);
		
		String measureTimeParsedToTimeString = parseMeasureTimeToTimeStirng();
		String[] measureTimeSplitedForCalculate=measureTimeParsedToTimeString.split(":");
		Double hourOfmeasureTimeForCalculate=Double.parseDouble(measureTimeSplitedForCalculate[0]);
		Double minuteOfmeasureTimeForCalculate=Double.parseDouble(measureTimeSplitedForCalculate[1]);
			
		Double hourOfCompleteTime=hourOfAcceptTimeForCalculate+hourOfmeasureTimeForCalculate;
		Double minuteOfCompleteTime=minuteOfAcceptTimeForCalculate+minuteOfmeasureTimeForCalculate;
		
		if(minuteOfCompleteTime>=60) {
			minuteOfCompleteTime-=60;
			hourOfCompleteTime+=1;
		}
		
		if(hourOfCompleteTime>=24) {
			hourOfCompleteTime-=24;
		}
		
		String hourOfCompleteTimeToString=hourOfCompleteTime.toString();
		if(hourOfCompleteTimeToString.length()==1) {
			hourOfCompleteTimeToString="0"+hourOfCompleteTimeToString;
		}
		String minuteOfCompleteTimeToString=minuteOfCompleteTime.toString();
		String completeTime=hourOfCompleteTimeToString+":"+minuteOfCompleteTimeToString;
		
		return completeTime;
	}
	
	public String parseMeasureTimeToTimeStirng() {
		String[] measureTimeSplited=(this.measureTime).split(".");
		String hourOfMeasureTime=measureTimeSplited[0];
		String minuteOfMeasureTime=measureTimeSplited[1];
		
		double hourOfmeasureTimeParseDouble=Double.parseDouble(hourOfMeasureTime);
		double minuteOfmeasureTimeParseDouble=(Double.parseDouble(minuteOfMeasureTime))*60;
		
		String measureTimeParsedToTimeString=Double.toString(hourOfmeasureTimeParseDouble)+":"+Double.toString(minuteOfmeasureTimeParseDouble);
		
		return measureTimeParsedToTimeString;
	}
		
	public boolean isMeasureTimeExceed8Hours() {
		Double measureTimeParsed=Double.parseDouble(this.measureTime);
		if(measureTimeParsed>8.0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setOvertimeOfHolidayExceed8Hours() {
		this.holidayOvertimeOfExceed8Hours=this.measureTime;
	}
	
	public void setOvertimeOfHolidayNotExceed8Hours() {
		this.holidayOvertimeOfNotExceed8Hours=this.measureTime;
	}
	
	public boolean isCompleteTimeBeforeStartOfNightTime() {
		String[] completeTimeSplited=(this.completeTime).split(":");
		Double completeHour=Double.parseDouble(completeTimeSplited[0]);
		
		if(completeHour>18 && completeHour<22) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setOverTime_OfcompleteTimeIsBeforeStartOfNightTime() {
		this.extensionOverTime=this.measureTime;
	}
	
	public void setOverTime_OfcompleteTimeIsAfterStartOfNightTime() {
		this.extensionOverTime=GapOfAcceptTimeBetweenStartOfNightTime();
		this.nightTimeOvertime=GapOfStartOfNightTimeBetweenCompleteTime();
	}
	
	public String GapOfAcceptTimeBetweenStartOfNightTime() {
		String[] acceptTimeSplited=(this.acceptTime).split(":");
		Double hourOfAcceptTime=Double.parseDouble(acceptTimeSplited[0]);
		Double minuteOfAcceptTime=Double.parseDouble(acceptTimeSplited[1]);
		
		String[] startOfNightTimeSplited=("22:00").split(":");
		Double hourOfstartOfNightTime=Double.parseDouble(startOfNightTimeSplited[0]);
		
		Double hour_GapOfAcceptTimeBetweenStartOfNightTime=hourOfstartOfNightTime-hourOfAcceptTime-1;
		Double minute_GapOfAcceptTimeBetweenStartOfNightTime=(60-minuteOfAcceptTime)/60;
		String overTime_GapOfAcceptTimeBetweenStartOfNightTime=Double.toString(hour_GapOfAcceptTimeBetweenStartOfNightTime+minute_GapOfAcceptTimeBetweenStartOfNightTime);
		
		return overTime_GapOfAcceptTimeBetweenStartOfNightTime;
	}
	
	public String GapOfStartOfNightTimeBetweenCompleteTime() {
		String[] completeTimeSplited=(this.completeTime).split(":");
		Double hourOfCompleteTime=Double.parseDouble(completeTimeSplited[0]);
		Double minuteOfCompleteTime=(Double.parseDouble(completeTimeSplited[1]))/60;
		
		String[] startOfNightTimeSplited=("22:00").split(":");
		Double hourOfstartOfNightTime=Double.parseDouble(startOfNightTimeSplited[0]);
		
		if(hourOfCompleteTime>22 && hourOfCompleteTime<24) {
			Double hour_GapOfStartOfNightTimeBetweenCompleteTime=hourOfCompleteTime-hourOfstartOfNightTime;
			String overTime_GapOfStartOfNightTimeBetweenCompleteTime=Double.toString(hour_GapOfStartOfNightTimeBetweenCompleteTime+minuteOfCompleteTime);
			return overTime_GapOfStartOfNightTimeBetweenCompleteTime;
		}
		
		else {
			String overTime_GapOfStartOfNightTimeBetweenCompleteTime=Double.toString( ((hourOfCompleteTime)+2)+minuteOfCompleteTime );
			return overTime_GapOfStartOfNightTimeBetweenCompleteTime;
		}
	}
}
