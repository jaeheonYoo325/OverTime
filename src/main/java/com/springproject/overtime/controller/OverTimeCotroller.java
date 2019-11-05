package com.springproject.overtime.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.common.utils.HttpRequestHelper;
import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtime.service.OverTimeService;

@Controller
public class OverTimeCotroller {
	
	@Autowired
	private OverTimeService overTimeService;

	@GetMapping("/main/main.do")
	public String viewMainPage() {
		return HttpRequestHelper.getJspPath();
	}
	
	@GetMapping("/overTime/overTimeRequest.do")
	public String viewOverTimeRequestPage() {
		return HttpRequestHelper.getJspPath();
	}
	
	@PostMapping("/overTime/overTimeRequest.do")
	public String doOverTimeRequest(@ModelAttribute OverTimeDto overtimeDto, HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		ArrayList<String> measurer= new ArrayList<String>();
		ArrayList<String> measureDescription= new ArrayList<String>();
		
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
		
		boolean isOverTimeRequestSuccess=this.overTimeService.insertOverTimeRequestService(overtimeDto, measurer, measureDescription);
		
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
			return "redirect:/overTime/overTimeList.do";
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
			return null;
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
	
	@GetMapping("/overTime/overTimeList.do")
	public ModelAndView viewOverTimeListPage() {
		ModelAndView mv=new ModelAndView(HttpRequestHelper.getJspPath());
		return mv;
	}
}
