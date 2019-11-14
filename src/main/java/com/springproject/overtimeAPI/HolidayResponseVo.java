package com.springproject.overtimeAPI;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class HolidayResponseVo {
	private Map<String,String> header;
	private HolidayItemsDTO body;
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	
	public HolidayItemsDTO getBody() {
		return body;
	}
	
	public void setBody(HolidayItemsDTO body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "HolidayResponseVO [header="+header+",body="+body+"]";
	}
	
}
