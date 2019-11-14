package com.springproject.overtimeAPI;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ApiTest {
	private final static String serviceKey="%2Bva9e2iMS%2Fl3yD2j60LdkTm1Cf5xuC%2BlIC%2BkPYhaKA88BhrY0qlKWYILDsrvUNCkFNumugquHlDXEQ4bz2P6tw%3D%3D";
	private final static String holidayURL="http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
	public static boolean isThisDayHoliday(String thisYear,String thisMonth,String thisDay) {
		
		try {
			URI requestURI=new URI(holidayURL+"?ServiceKey="+serviceKey+"&solYear=2019&"+"solMonth="+thisMonth);
			RestTemplate restTemplate=new RestTemplate();
			HolidayResponseVo response=restTemplate.getForObject(requestURI,HolidayResponseVo.class);
			System.out.println("지금들고온 휴일정보는");
			List<HolidayItemDTO> items=response.getBody().getItems();
			
			for(HolidayItemDTO item : items) {
				//solday가 해당 년월일의 휴일에 포함되어있는지 아닌지 판단해서 true false리턴
			}
			
			// if 일요일이면 true(일요일판단 로직작성필요)
			
			return true;
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return true;
		
	}

}
