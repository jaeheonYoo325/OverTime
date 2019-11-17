package com.springproject.overtimeAPI;

import java.net.URI;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class OverTimeCalculater {
	private OverTimeCalculater() {};
	private static OverTimeCalculater overTimeCalculater=new OverTimeCalculater();
	
	private final static String serviceKey="%2Bva9e2iMS%2Fl3yD2j60LdkTm1Cf5xuC%2BlIC%2BkPYhaKA88BhrY0qlKWYILDsrvUNCkFNumugquHlDXEQ4bz2P6tw%3D%3D";
	private final static String holidayURL="http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
	
	public static OverTimeCalculater getInstance() {
		return overTimeCalculater;
	}
	
	public void calculateOverTime(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		if(judgeOfHolidayAndSunDay(elementsOfOverTimeForCalculate)) {
			calculateOverTimeOfHolidayAndSunDay(elementsOfOverTimeForCalculate);
		}
		else {
			calculateOverTimeOfNotHolidayAndSunDay(elementsOfOverTimeForCalculate);
		}
	}

	public boolean judgeOfHolidayAndSunDay(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		return isThisDayHoliday(elementsOfOverTimeForCalculate)||isThisDaySunDay(elementsOfOverTimeForCalculate);
	}

	public boolean isThisDayHoliday(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		boolean isThisDayHoliday=false;
			try {
				URI requestURI=new URI(holidayURL+"?ServiceKey="+serviceKey+"&solYear="+elementsOfOverTimeForCalculate.getThisYear()+"&solMonth="+elementsOfOverTimeForCalculate.getThisMonth());
				RestTemplate restTemplate=new RestTemplate();
				HolidayResponseVo response=restTemplate.getForObject(requestURI,HolidayResponseVo.class);
				List<HolidayItemDTO> items=response.getBody().getItems();
				
				String thisDate=elementsOfOverTimeForCalculate.getThisYear()+elementsOfOverTimeForCalculate.getThisMonth()+elementsOfOverTimeForCalculate.getThisDay();
				for(HolidayItemDTO item : items) {
					if(thisDate.equals(item.getLocdate())) {
						isThisDayHoliday=true;
					}
				}
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			
		return isThisDayHoliday;
	}
	
	public boolean isThisDaySunDay(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
			boolean isThisDaySunDay=false;
			String inputDate = elementsOfOverTimeForCalculate.getThisYear()+elementsOfOverTimeForCalculate.getThisMonth()+elementsOfOverTimeForCalculate.getThisDay();

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			try {
				Date date = dateFormat.parse(inputDate);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				if((calendar.get(Calendar.DAY_OF_WEEK))==1) {
					isThisDaySunDay=true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return isThisDaySunDay;
	}
	
	public ElementsOfOverTimeForCalculate calculateOverTimeOfHolidayAndSunDay(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		if(elementsOfOverTimeForCalculate.isMeasureTimeExceed8Hours()) {
			elementsOfOverTimeForCalculate.setOvertimeOfHolidayExceed8Hours();
		}
		else {
			elementsOfOverTimeForCalculate.setOvertimeOfHolidayNotExceed8Hours();
		}
		return elementsOfOverTimeForCalculate;
	}

	public void calculateOverTimeOfNotHolidayAndSunDay(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		if(elementsOfOverTimeForCalculate.isCompleteTimeBeforeStartOfNightTime()) {
			setOvertimeOfCompleteTimeIsBeforeStartOfNightTime(elementsOfOverTimeForCalculate);
		}
		else {
			setOvertimeOfCompleteTimeIsAfterStartOfNightTime(elementsOfOverTimeForCalculate);
		}
	}
	
	public ElementsOfOverTimeForCalculate setOvertimeOfCompleteTimeIsBeforeStartOfNightTime(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		elementsOfOverTimeForCalculate.setOverTime_OfcompleteTimeIsBeforeStartOfNightTime();
		return elementsOfOverTimeForCalculate;
	}
	
	public ElementsOfOverTimeForCalculate setOvertimeOfCompleteTimeIsAfterStartOfNightTime(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		elementsOfOverTimeForCalculate.setOverTime_OfcompleteTimeIsAfterStartOfNightTime();
		return elementsOfOverTimeForCalculate;
	}
		
}