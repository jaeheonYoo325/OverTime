package com.springproject.overtime.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.common.session.Session;
import com.springproject.common.utils.HttpRequestHelper;
import com.springproject.dtos.CategoryTypeDto;
import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MasterTableDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.dtos.RelatedChainDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.employee.service.EmployeeService;
import com.springproject.overtime.service.OverTimeService;
import com.springproject.overtimeAPI.ElementsOfOverTimeForCalculate;
import com.springproject.overtimeAPI.HolidayItemDTO;
import com.springproject.overtimeAPI.HolidayResponseVo;
import com.springproject.overtimeAPI.OverTimeCalculater;

@Controller
public class OverTimeCotroller {
	
	@Autowired
	private OverTimeService overTimeService;
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/main/main.do")
	public String viewMainPage() {
		return HttpRequestHelper.getJspPath();
	}
	
	@GetMapping("/overTime/overTimeRequest.do")
	public String viewOverTimeRequestPage(HttpSession session, HttpServletResponse response) {
		boolean isThisUserHaveRequestOfOverTimeAuthority=this.employeeService.checkIsThisUserHaveRequestOfOverTimeAuthorityService((EmployeeDto)session.getAttribute(Session.USER));
		
		if(isThisUserHaveRequestOfOverTimeAuthority) {
			return HttpRequestHelper.getJspPath();
		}
		else {
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('요청권한이없습니다')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@PostMapping("/overTime/overTimeRequest.do")
	public ModelAndView doOverTimeRequest(@Valid @ModelAttribute OverTimeDto overtimeDto, Errors errors, HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		ModelAndView mv = null;
		ArrayList<String> measurer= new ArrayList<String>();
		ArrayList<String> measureDescription= new ArrayList<String>();
		ArrayList<String> relatedChain= new ArrayList<String>();
		
		if ( errors.hasErrors() ) {
			mv = new ModelAndView(HttpRequestHelper.getJspPath());
			mv.addObject("overtimeDto", overtimeDto);
			return mv;
		}
		for(int i=0;;i++) {
			if(request.getParameter("measurer"+i)==null) {
				break;
			}
			measurer.add(request.getParameter("measurer"+i));
		}
		
		for(int i=0;;i++) {
			if(request.getParameter("measureDescription"+i)==null) {
				break;
			}
			measureDescription.add(request.getParameter("measureDescription"+i));
		}
		
		for(int i=0;;i++) {
			if(request.getParameter("relatedChain"+i)==null) {
				break;
			}
			relatedChain.add(request.getParameter("relatedChain"+i));
		}
		
		boolean isOverTimeRequestSuccess=this.overTimeService.insertOverTimeRequestService(overtimeDto, measurer, measureDescription, relatedChain);
		
		PrintWriter out;
		if (isOverTimeRequestSuccess) {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('요청완료')");
				out.println("window.close()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			mv = new ModelAndView("redirect:/overTime/overTimeList.do");
			return mv;
//			return "redirect:/overTime/overTimeList.do";
		} else {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('요청실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
	}
	
	
	@GetMapping("/search/searchEmployee.do")
	public ModelAndView viewSearchEmployeePage() {
		List<EmployeeDto> searchEmployees=overTimeService.selectAllEmployeeService();
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		mv.addObject("searchEmployees",searchEmployees);
		return mv;
	}
	
	@GetMapping("/search/searchMeasurer.do")
	public ModelAndView viewSearchMeasurerPage() {
		List<EmployeeDto> searchEmployees=overTimeService.selectAllEmployeeService();
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		mv.addObject("searchEmployees",searchEmployees);
		return mv;
	}
	
	@PostMapping("/search/searchEmployee.do")
	public ModelAndView doSearchEmployee(@ModelAttribute EmployeeDto employeeDto) {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		List<EmployeeDto> searchEmployees=overTimeService.selectEmployeeOfSearchService(employeeDto);
		mv.addObject("searchEmployees",searchEmployees);
		return mv;
	}
	
	@GetMapping("/search/searchCaller.do")
	public ModelAndView viewSearchCallerPage() {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		List<InterPhoneDto> interPhone=this.overTimeService.sellectAllInterPhoneService();
		mv.addObject("interPhone",interPhone);
		return mv;
	}
	
	@PostMapping("/search/searchCaller.do")
	public ModelAndView doSearchCaller(@ModelAttribute InterPhoneDto InterPhoneDto) {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		List<InterPhoneDto> interPhone=this.overTimeService.selectCallerOfSearchService(InterPhoneDto);
		mv.addObject("interPhone",interPhone);
		return mv;
	}
	
	@GetMapping("/search/searchChain.do")
	public ModelAndView viewSearchChainPage() {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		List<ChainTableDto> searchChain=this.overTimeService.selectAllChainService();
		mv.addObject("searchChain",searchChain);
		return mv;
	}
	
	@PostMapping("/search/searchChain.do")
	public ModelAndView doSearchChain(@ModelAttribute ChainTableDto chainTableDto) {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		List<ChainTableDto> searchChain=this.overTimeService.selectChainOfSearchService(chainTableDto);
		mv.addObject("searchChain",searchChain);
		return mv;
	}
	
	@RequestMapping("/overTime/overTimeList.do")
	public ModelAndView doOverTimeListAction(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		
		List<OverTimeDto> overTime=new ArrayList<OverTimeDto>();
		List<MeasurerDto> measurerOfAcceptNo=new ArrayList<MeasurerDto>();
		List<MeasureDescriptionDto> measureDescriptionOfAcceptNo=new ArrayList<MeasureDescriptionDto>();
		List<RelatedChainDto> relatedChainOfAcceptNo=new ArrayList<RelatedChainDto>();
		List<ChainTableDto> chain=this.overTimeService.selectAllChainService();
		
		CategoryTypeDto categoryTypeDto=new CategoryTypeDto();
		Map<Long, List<MeasurerDto>> measurerMap = new HashMap<Long, List<MeasurerDto>>(); 
		Map<Long, List<MeasureDescriptionDto>> measureDescriptionMap = new HashMap<Long, List<MeasureDescriptionDto>>();
		Map<Long, List<RelatedChainDto>> relatedChainMap = new HashMap<Long, List<RelatedChainDto>>();
		
		List<MasterTableDto> masterCodeOfCategory=this.overTimeService.selectMasterCodeOfCategoryService();
		Map<String, List<MasterTableDto>> categoryMasterCodesOfCodeTypeMap=this.overTimeService.selectCategoryMasterCodesOfCodeTypeService(masterCodeOfCategory);
		Map<String, List<MasterTableDto>> masterCodeOfSearchTypeMap=this.overTimeService.selectMasterCodeOfSearchTypeMapService(categoryTypeDto.getSearchTypeString());
		
		Long acceptNo=0L;
		
		if(request.getParameter("searchType") != null && request.getParameter("searchKeyword") != null && request.getParameter("categoryChain")!=null && request.getParameter("categoryAcceptDate")!=null && request.getParameter("categoryStatus")!=null) {
			String searchType = request.getParameter("searchType"); 
	    	String searchKeyword = request.getParameter("searchKeyword");
	    	String categoryChain = request.getParameter("categoryChain");
	    	String categoryAcceptDate = request.getParameter("categoryAcceptDate");
	    	String categoryStatus = request.getParameter("categoryStatus");
	    	
	    	categoryTypeDto.setSearchType(searchType);
	    	categoryTypeDto.setSearchKeyword(searchKeyword);
	    	categoryTypeDto.setCategoryChain(categoryChain);
	    	categoryTypeDto.setCategoryAcceptDate(categoryAcceptDate);
	    	categoryTypeDto.setCategoryStatus(categoryStatus);
	    	
	    	overTime=this.overTimeService.selectCategoryOverTimeRequestService(categoryTypeDto);
		}
		else {
			categoryTypeDto.setSearchType("검색타입");
			categoryTypeDto.setSearchKeyword("");
//			categoryTypeDto.setCategoryChain("관련체인");
			categoryTypeDto.setCategoryStatus("상태");
			categoryTypeDto.setCategoryAcceptDate("접수일자");
			
			overTime=this.overTimeService.selectAllOverTimeRequestService();
		}
		
		for(int i=0; i<overTime.size(); i++) {
			acceptNo=overTime.get(i).getAcceptNo();
			measurerOfAcceptNo=this.overTimeService.selectMeasurerOfAcceptNoService(acceptNo);
			measurerMap.put(acceptNo, measurerOfAcceptNo);
			
			measureDescriptionOfAcceptNo=this.overTimeService.selectMeasureDescriptionOfAcceptNoService(acceptNo);
			
			for(int j=0; j<measureDescriptionOfAcceptNo.size();j++) {
				String BeforeMeasureDescriptionReplacedStringForMultiLine = measureDescriptionOfAcceptNo.get(j).getMeasureDescription();
				String AfterMeasureDescriptionReplacedStringForMultiLine= BeforeMeasureDescriptionReplacedStringForMultiLine.replace("\n", "<br>");
				measureDescriptionOfAcceptNo.get(j).setMeasureDescription(AfterMeasureDescriptionReplacedStringForMultiLine);
			}
			
			measureDescriptionMap.put(acceptNo, measureDescriptionOfAcceptNo);
			
			relatedChainOfAcceptNo=this.overTimeService.selectRelatedChainOfAcceptNoService(acceptNo);
			relatedChainMap.put(acceptNo, relatedChainOfAcceptNo);
		}
		
		for(int i=0; i<overTime.size(); i++) {
			String BeforeAcceptDescriptionReplacedStringForMultiLine = overTime.get(i).getAcceptDescription();
			String AfterAcceptDescriptionReplacedStringForMultiLine = BeforeAcceptDescriptionReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setAcceptDescription(AfterAcceptDescriptionReplacedStringForMultiLine);
		}
		
		for(int i=0; i<overTime.size(); i++) {
			String BeforeCauseReplacedStringForMultiLine = overTime.get(i).getCause();
			String AfterCauseReplacedStringForMultiLine=BeforeCauseReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setCause(AfterCauseReplacedStringForMultiLine);
		}
		
		for(int i=0; i<overTime.size(); i++) {
			String BeforeMeasuresReplacedStringForMultiLine = overTime.get(i).getMeasures();
			String AfterMeasuresReplacedStringForMultiLine=BeforeMeasuresReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setMeasures(AfterMeasuresReplacedStringForMultiLine);
		}
		
      
		for(int i=0; i<overTime.size(); i++) {
			String BeforeAcceptDescriptionReplacedStringForMultiLine = overTime.get(i).getAcceptDescription();
			String AfterAcceptDescriptionReplacedStringForMultiLine=BeforeAcceptDescriptionReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setAcceptDescription(AfterAcceptDescriptionReplacedStringForMultiLine);
		}
       
		for(int i=0; i<overTime.size(); i++) {
			String BeforeCauseReplacedStringForMultiLine = overTime.get(i).getCause();
			String AfterCauseReplacedStringForMultiLine=BeforeCauseReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setCause(AfterCauseReplacedStringForMultiLine);
		}
       
		for(int i=0; i<overTime.size(); i++) {
			String BeforeMeasuresReplacedStringForMultiLine = overTime.get(i).getMeasures();
			String AfterMeasuresReplacedStringForMultiLine=BeforeMeasuresReplacedStringForMultiLine.replace("\n", "<br>");
			overTime.get(i).setMeasures(AfterMeasuresReplacedStringForMultiLine);
		}
		
		mv.addObject("overTime",overTime);
		mv.addObject("chain",chain);
		mv.addObject("categoryTypeDto",categoryTypeDto);
		mv.addObject("categoryMasterCodesOfCodeTypeMap",categoryMasterCodesOfCodeTypeMap);
		mv.addObject("measurerMap",measurerMap);
		mv.addObject("measureDescriptionMap",measureDescriptionMap);
		mv.addObject("masterCodeOfSearchTypeMap",masterCodeOfSearchTypeMap);
		mv.addObject("relatedChainMap",relatedChainMap);
		
		return mv;
	}
	
	@GetMapping("/overTime/myOverTime.do")
	public ModelAndView viewmyOverTimePage(HttpSession session) {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		OverTimeofEmployeeDto overTimeofEmployeeDto=this.overTimeService.selectOverTimeofEmployeeService((EmployeeDto)session.getAttribute(Session.USER));
		mv.addObject("overTimeofEmployeeDto",overTimeofEmployeeDto);
		return mv;
	}
	
	@GetMapping("/overTime/APItest")
	public void APItest() {
		String acceptDate="2019.11.17";
		String acceptTime="01:00";
		String measureTime="9.5";
		
		
		
		ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate=new ElementsOfOverTimeForCalculate(acceptDate, acceptTime, measureTime);
		System.out.println("======================================================================");
		System.out.println("thisYear"+elementsOfOverTimeForCalculate.getThisYear());
		System.out.println("thisMonth"+elementsOfOverTimeForCalculate.getThisMonth());
		System.out.println("thisDay"+elementsOfOverTimeForCalculate.getThisDay());
		System.out.println("completeTime"+elementsOfOverTimeForCalculate.getCompleteTime());
		System.out.println("======================================================================");
		
		OverTimeCalculater overTimeCalculater=OverTimeCalculater.getInstance();
		overTimeCalculater.calculateOverTime(elementsOfOverTimeForCalculate);
	    System.out.println("연장시간 : "+elementsOfOverTimeForCalculate.getExtensionOverTime());
	    System.out.println("야간시간 : "+elementsOfOverTimeForCalculate.getNightTimeOvertime());
	    System.out.println("휴일시간(8시간이상) :"+elementsOfOverTimeForCalculate.getHolidayOvertimeOfExceed8Hours());
	    System.out.println("휴일시간(8시간미만) :"+elementsOfOverTimeForCalculate.getHolidayOvertimeOfNotExceed8Hours());
	
	}
	
	@RequestMapping("/overTime/searchCallerAjax.do")
	@ResponseBody
	public Map<Object, Object> doSearchCallerAjaxAction(@RequestParam String caller) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<InterPhoneDto> interPhone = this.overTimeService.selectCallerOfSearchAjaxService(caller);
		
		
		if ( interPhone.size() > 0 ) {
			map.put("interPhone", interPhone);
		}
		
		return map;
	}
	
	@RequestMapping("/overTime/searchMeasurerAjax.do")
	@ResponseBody
	public Map<Object, Object> doSearchMeasurerAjaxAction(@RequestParam String measurer) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<EmployeeDto> searchEmployees = this.overTimeService.selectEmployeeOfSearchAjaxService(measurer);
		
		if ( searchEmployees.size() > 0 ) {
			map.put("searchEmployees", searchEmployees);
		}
		
		return map;
	}
	
	@RequestMapping("/overTime/searchRelatedChainAjax.do")
	@ResponseBody
	public Map<Object, Object> doSearchRelatedChainAjaxAction(@RequestParam String relatedChain) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<ChainTableDto> searchChain = this.overTimeService.selectChainOfSearchAjaxService(relatedChain);
		
		if ( searchChain.size() > 0) {
			map.put("searchChain", searchChain);
		}

		return map;
	}	

}
