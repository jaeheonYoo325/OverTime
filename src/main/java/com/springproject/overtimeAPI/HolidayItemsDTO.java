package com.springproject.overtimeAPI;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="body")
public class HolidayItemsDTO {
	private List<HolidayItemDTO> items;
	

	@XmlElementWrapper(name="items")
	@XmlElement(name="item")
	public List<HolidayItemDTO> getItems() {
		return items;
	}

	public void setItems(List<HolidayItemDTO> items) {
		this.items = items;
	}
	
}
