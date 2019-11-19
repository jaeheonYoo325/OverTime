package com.springproject.overtimeAPI;

public class ElementsOfOverTimeForCalculate {
	private String employeeNo;
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
	
	public ElementsOfOverTimeForCalculate(String employeeNo, String acceptDate,String acceptTime,String measureTime) {
		this.employeeNo=employeeNo;
		this.acceptDate=acceptDate;
		setThisYearAndMonthAndDay(acceptDate);
		this.acceptTime=acceptTime;
		this.measureTime=measureTime;
		this.completeTime=calculateCompleteTime();
		this.extensionOverTime="0";
		this.nightTimeOvertime="0";
		this.holidayOvertimeOfExceed8Hours="0";
		this.holidayOvertimeOfNotExceed8Hours="0";
	}
	
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	
	public void setThisYearAndMonthAndDay(String acceptDate) {
		String[] thisDate=acceptDate.split("\\.");
		this.thisYear=thisDate[0];
		this.thisMonth=thisDate[1];
		this.thisDay=thisDate[2];
	}
	
	public String calculateCompleteTime() {
		String[] acceptTimeSplitedForCalculate=(this.acceptTime).split(":");
		int hourOfAcceptTimeForCalculate=Integer.parseInt(acceptTimeSplitedForCalculate[0]);
		int minuteOfAcceptTimeForCalculate=Integer.parseInt(acceptTimeSplitedForCalculate[1]);

		String measureTimeParsedToTimeString = parseMeasureTimeToTimeStirng();
		
		String[] measureTimeSplitedForCalculate=measureTimeParsedToTimeString.split(":");
		int hourOfmeasureTimeForCalculate=Integer.parseInt(measureTimeSplitedForCalculate[0]);
		int minuteOfmeasureTimeForCalculate=Integer.parseInt(measureTimeSplitedForCalculate[1]);
		
		int hourOfCompleteTime=hourOfAcceptTimeForCalculate+hourOfmeasureTimeForCalculate;
		int minuteOfCompleteTime=minuteOfAcceptTimeForCalculate+minuteOfmeasureTimeForCalculate;
		
		if(minuteOfCompleteTime>=60) {
			minuteOfCompleteTime-=60;
			hourOfCompleteTime+=1;
		}
		
		if(hourOfCompleteTime>=24) {
			hourOfCompleteTime-=24;
		}
		
		String hourOfCompleteTimeToString=Integer.toString(hourOfCompleteTime);
		if(hourOfCompleteTimeToString.length()==1) {
			hourOfCompleteTimeToString="0"+hourOfCompleteTimeToString;
		}
		String minuteOfCompleteTimeToString=Integer.toString(minuteOfCompleteTime);
		String completeTime=hourOfCompleteTimeToString+":"+minuteOfCompleteTimeToString;
		
		return completeTime;
	}
	
	public String parseMeasureTimeToTimeStirng() {
		double measureTime=Double.parseDouble(this.measureTime);
		int integerOfmeasureTime=Integer.parseInt( ((this.measureTime).split("\\."))[0] );
		
		double decimalOfmeasureTime=measureTime-integerOfmeasureTime;
		double minuteOfmeasureTimeDouble=(decimalOfmeasureTime)*60;
		String minuteOfmeasureTimeDoubleToString=Double.toString(minuteOfmeasureTimeDouble);
		
		int minuteOfmeasureTimeInteger=Integer.parseInt( ((minuteOfmeasureTimeDoubleToString).split("\\."))[0] );
		
		String measureTimeParsedToTimeString=Integer.toString(integerOfmeasureTime)+":"+Integer.toString(minuteOfmeasureTimeInteger);
		
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
		Double hour_GapOfAcceptTimeBetweenStartOfNightTime = 0d; 
		Double minute_GapOfAcceptTimeBetweenStartOfNightTime = 0d;
		
		if (minuteOfAcceptTime == 00 ) {
			hour_GapOfAcceptTimeBetweenStartOfNightTime = hourOfstartOfNightTime-hourOfAcceptTime;
			minute_GapOfAcceptTimeBetweenStartOfNightTime = 0d;
		} else {
			hour_GapOfAcceptTimeBetweenStartOfNightTime = hourOfstartOfNightTime-hourOfAcceptTime-1;
			minute_GapOfAcceptTimeBetweenStartOfNightTime = (60-minuteOfAcceptTime)/60;
		}
		
		String overTime_GapOfAcceptTimeBetweenStartOfNightTime = Double.toString(hour_GapOfAcceptTimeBetweenStartOfNightTime+minute_GapOfAcceptTimeBetweenStartOfNightTime);
		return overTime_GapOfAcceptTimeBetweenStartOfNightTime;
	}
	
	public String GapOfStartOfNightTimeBetweenCompleteTime() {
		String[] completeTimeSplited=(this.completeTime).split(":");
		Double hourOfCompleteTime=Double.parseDouble(completeTimeSplited[0]);
		
		Double minuteOfCompleteTime=(Double.parseDouble(completeTimeSplited[1]))/60;
		
		String[] startOfNightTimeSplited=("22:00").split(":");
		Double hourOfstartOfNightTime=Double.parseDouble(startOfNightTimeSplited[0]);
		
		if(hourOfCompleteTime>=22 && hourOfCompleteTime<24) {
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
