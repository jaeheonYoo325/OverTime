package com.springproject.overtimeAPI;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ApiTest2 {
	private final static String serviceKey="%2Bva9e2iMS%2Fl3yD2j60LdkTm1Cf5xuC%2BlIC%2BkPYhaKA88BhrY0qlKWYILDsrvUNCkFNumugquHlDXEQ4bz2P6tw%3D%3D";
	private final static String holidayURL="http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
	private String requestURL=null;
	public static void requestHolidayInfo() {
		String solMonth="09";
		
		try {
			URI requestURI=new URI(holidayURL+"?ServiceKey="+serviceKey+"&solYear=2019&"+"solMonth="+solMonth);
			RestTemplate restTemplate=new RestTemplate();
			HolidayResponseVo response=restTemplate.getForObject(requestURI,HolidayResponseVo.class);
			System.out.println("지금들고온 휴일정보는");
			List<HolidayItemDTO> items=response.getBody().getItems();
			
			for(HolidayItemDTO item : items) {
				System.out.println(item.toString());
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}

}