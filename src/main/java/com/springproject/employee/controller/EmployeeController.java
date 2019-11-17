package com.springproject.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.common.session.Session;
import com.springproject.common.utils.HttpRequestHelper;
import com.springproject.common.validator.employee.EmployeeValidator;
import com.springproject.department.dto.DepartmentDto;
import com.springproject.department.service.DepartmentService;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.RelatedChainDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.employee.service.EmployeeService;
import com.springproject.overtime.service.OverTimeService;

@Controller
public class EmployeeController {

	@Autowired
	private OverTimeService overTimeService;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/employee/employeeLogin.do")
	public String viewEmployeeLoginPage() {
		return HttpRequestHelper.getJspPath();
	}

	@PostMapping("/employee/employeeLogin.do")
	public ModelAndView doEmployeeLoginAction(
			@Validated(value = { EmployeeValidator.Login.class }) @ModelAttribute EmployeeDto employeeDto,
			Errors errors, HttpSession session, HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ModelAndView mv = null;

		if (errors.hasErrors()) {
			mv = new ModelAndView(HttpRequestHelper.getJspPath());
			mv.addObject("employeeDto", employeeDto);
			return mv;
		}

		EmployeeDto loginEmployeeDto = this.employeeService.selectOneEmployeeService(employeeDto);

		PrintWriter out;
		if (loginEmployeeDto != null) {
			loginEmployeeDto.setEmployeeNo(employeeDto.getEmployeeNo());
			loginEmployeeDto.setEmployeePassWord(employeeDto.getEmployeePassWord());
			session.setAttribute(Session.USER, loginEmployeeDto);


			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 성공하였습니다.')");
				out.println("window.location.href = '/main/main.do';");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		} else {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('사원번호와 비밀번호가 일치하지 않습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
	}

	@GetMapping("/employee/employeeLogout.do")
	public String doEmployeeLogoutAction(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/employeeLogin.do";
	}

	@GetMapping("/employee/employeeRegist.do")
	public String viewEmployeeRegistPage(HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
       boolean isThisUserHaveAuthorityOfEmployeeRegist=this.employeeService.checkisThisUserHaveAuthorityOfEmployeeRegistService((EmployeeDto)session.getAttribute(Session.USER));
       
       if(isThisUserHaveAuthorityOfEmployeeRegist) {
          return HttpRequestHelper.getJspPath();
       }
       else {
          try {
            PrintWriter out;
            out = response.getWriter();
            out.println("<script>");
            out.println("alert('사원등록권한이 없습니다')");
            out.println("history.back()");
            out.println("</script>");
         } catch (IOException e) {
            e.printStackTrace();
         }
         return null;
       }
	}

	@PostMapping("/employee/employeeRegist.do")
	public ModelAndView doEmployeeRegistAction(
			@Validated(value = { EmployeeValidator.Regist.class }) @ModelAttribute EmployeeDto employeeDto,
			Errors errors) {

		ModelAndView mv = new ModelAndView("redirect:/employee/employeeLogin.do");

		if (errors.hasErrors()) {
			mv.setViewName(HttpRequestHelper.getJspPath());
			mv.addObject("employeeDto", employeeDto);
			return mv;
		}
		boolean isSuccess = this.employeeService.insertOneEmployeeService(employeeDto);
		return mv;
	}

	@GetMapping("/search/searchDepartment.do")
	public String viewSearchDepartmentPopup() {
		return HttpRequestHelper.getJspPath();
	}

	@PostMapping("/search/searchDepartment.do")
	public ModelAndView doSearchDepartmentPopupAction(@ModelAttribute DepartmentDto departmentDto) {
		ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
		List<DepartmentDto> departmentDtoList = this.departmentService.selectSomeDepartmentService(departmentDto);
		mv.addObject("departmentDtoList", departmentDtoList);
		return mv;
	}

	@RequestMapping("/employee/employeeNoDuplicate.do")
	@ResponseBody
	public Map<Object, Object> doCheckDuplicateOfRecruitMemberEmail(@RequestParam String employeeNo) {

		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		count = this.employeeService.duplicateCheckOfEmployeeNoService(employeeNo);
		System.out.println("count : " + count);
		map.put("cnt", count);

		return map;
	}
	
	// 결재
	@GetMapping("/employee/myOverTimeWillApproval.do")
	public ModelAndView viewMyOverTimeApprovalPage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
		List<OverTimeApprovalDto> overTimeApproval = this.employeeService.selectMyOverTimeApprovalService((EmployeeDto)session.getAttribute(Session.USER));
		Map<Long, List<MeasurerDto>> overTimeMeasurerofAcceptNoMap = new HashMap<Long, List<MeasurerDto>>();		
		List<MeasurerDto> overTimeMeasurerOfAcceptNo = new ArrayList<MeasurerDto>();
		
		Map<Long, List<MeasureDescriptionDto>> overTimeMeasureDescriptionOfAcceptNoMap = new HashMap<Long, List<MeasureDescriptionDto>>();
		List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo =  new ArrayList<MeasureDescriptionDto>();
		
		Map<Long, List<RelatedChainDto>> overTimeRelatedChainOfAcceptNoMap = new HashMap<Long, List<RelatedChainDto>>();
		List<RelatedChainDto> overTimeRelatedChainOfAcceptNo = new ArrayList<RelatedChainDto>();
		List<OverTimeDto> overTimeRequestOfAcceptNo = new ArrayList<OverTimeDto>();
		
		Long acceptNo = 0L;
		
		for(int i = 0; i < overTimeApproval.size(); i++) {
			
			acceptNo = overTimeApproval.get(i).getAcceptNo();
			overTimeRequestOfAcceptNo.add(this.overTimeService.selectOverTimeRequestOfAcceptNoService(acceptNo));
			
			overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasurerofAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasurerOfAcceptNo);
			
			overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasureDescriptionOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasureDescriptionOfAcceptNo);
			
			overTimeRelatedChainOfAcceptNo = this.overTimeService.selectRelatedChainOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeRelatedChainOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeRelatedChainOfAcceptNo);
			
		}
		
		mv.addObject("overTimeApproval", overTimeApproval);
		mv.addObject("overTimeRequestOfAcceptNo", overTimeRequestOfAcceptNo);
		mv.addObject("overTimeMeasurerofAcceptNoMap", overTimeMeasurerofAcceptNoMap);
		mv.addObject("overTimeMeasureDescriptionOfAcceptNoMap", overTimeMeasureDescriptionOfAcceptNoMap);
		mv.addObject("overTimeRelatedChainOfAcceptNoMap", overTimeRelatedChainOfAcceptNoMap);
		
		return mv;
	}	

	@GetMapping("/employee/showOverTimeApprovalDetail.do/{acceptNo}/{overTimeApprovalDetailCode}")
	public ModelAndView viewMyOverTimeApprovalDetail(@PathVariable Long acceptNo, @PathVariable String overTimeApprovalDetailCode, HttpSession session) {
		
		ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
		OverTimeDto overTimeRequestOfAcceptNo = this.overTimeService.selectOverTimeRequestOfAcceptNoService(acceptNo);
		List<MeasurerDto> overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(acceptNo);
		List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(acceptNo);
		List<RelatedChainDto> overTimeRelatedChainOfAcceptNo = this.overTimeService.selectRelatedChainOfAcceptNoService(acceptNo);
				
		mv.addObject("overTimeRequestOfAcceptNo", overTimeRequestOfAcceptNo);
		mv.addObject("overTimeMeasurerOfAcceptNo", overTimeMeasurerOfAcceptNo);		
		mv.addObject("overTimeMeasureDescriptionOfAcceptNo", overTimeMeasureDescriptionOfAcceptNo);
		mv.addObject("overTimeApprovalDetailCode", overTimeApprovalDetailCode);
		mv.addObject("overTimeRelatedChainOfAcceptNo",overTimeRelatedChainOfAcceptNo);
		
		return mv;
	}
	
	@GetMapping("/employee/myOverTimeDoApprovaling.do/{acceptNo}")
	public void doMyOverTimeApprovalAction(@PathVariable Long acceptNo, HttpSession session, HttpServletResponse response) {
	
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		
		OverTimeApprovalDto overTimeApprovalDto = this.employeeService.selectMyOverTimeApprovalOfAcceptNoService(acceptNo);
		
		overTimeApprovalDto.setApprovalLineConfirm(((EmployeeDto)session.getAttribute(Session.USER)).getEmployeeNo());
		boolean isDoApprovalingSuccess = this.employeeService.myOverTimeDoApprovalingService(overTimeApprovalDto);
		PrintWriter out;
		
		if ( isDoApprovalingSuccess ) {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('결재완료')");
				out.println("window.location.href='/employee/myOverTimeWillApproval.do'");	
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('오류')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping("/employee/myOverTimeApproved.do")
	public ModelAndView viewMyOverTimeApprovedPage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
		List<OverTimeApprovalDto> overTimeApproved = this.employeeService.selectMyOverTimeApprovedService((EmployeeDto)session.getAttribute(Session.USER));
		
		Map<Long, List<MeasurerDto>> overTimeMeasurerofAcceptNoMap = new HashMap<Long, List<MeasurerDto>>();		
		List<MeasurerDto> overTimeMeasurerOfAcceptNo = new ArrayList<MeasurerDto>();
		
		Map<Long, List<MeasureDescriptionDto>> overTimeMeasureDescriptionOfAcceptNoMap = new HashMap<Long, List<MeasureDescriptionDto>>();
		List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo =  new ArrayList<MeasureDescriptionDto>();
		
		Map<Long, List<RelatedChainDto>> overTimeRelatedChainOfAcceptNoMap = new HashMap<Long, List<RelatedChainDto>>();
		List<RelatedChainDto> overTimeRelatedChainOfAcceptNo = new ArrayList<RelatedChainDto>();
		List<OverTimeDto> overTimeRequestOfAcceptNo = new ArrayList<OverTimeDto>();
		
		Long acceptNo = 0L;
		
		for(int i = 0 ; i < overTimeApproved.size(); i++) {
			acceptNo = overTimeApproved.get(i).getAcceptNo();
			overTimeRequestOfAcceptNo.add(this.overTimeService.selectOverTimeRequestOfAcceptNoService(acceptNo));
			
			overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasurerofAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasurerOfAcceptNo);
			
			overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasureDescriptionOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasureDescriptionOfAcceptNo);
			
			overTimeRelatedChainOfAcceptNo = this.overTimeService.selectRelatedChainOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeRelatedChainOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeRelatedChainOfAcceptNo);
		}
		
		mv.addObject("overTimeApproved", overTimeApproved);
		mv.addObject("overTimeRequestOfAcceptNo", overTimeRequestOfAcceptNo);
		mv.addObject("overTimeMeasurerofAcceptNoMap", overTimeMeasurerofAcceptNoMap);
		mv.addObject("overTimeMeasureDescriptionOfAcceptNoMap", overTimeMeasureDescriptionOfAcceptNoMap);
		mv.addObject("overTimeRelatedChainOfAcceptNoMap", overTimeRelatedChainOfAcceptNoMap);
		
		return mv;
	}
	
	
	@GetMapping("/employee/myOverTimeCompleted.do")
	public ModelAndView viewMyOverTimeCompletedPage(HttpSession session) {
		
		ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
		List<OverTimeApprovalDto> overTimeCompleted = this.employeeService.selectMyOverTimeCompletedService((EmployeeDto)session.getAttribute(Session.USER));
		
		Map<Long, List<MeasurerDto>> overTimeMeasurerofAcceptNoMap = new HashMap<Long, List<MeasurerDto>>();		
		List<MeasurerDto> overTimeMeasurerOfAcceptNo = new ArrayList<MeasurerDto>();
		
		Map<Long, List<MeasureDescriptionDto>> overTimeMeasureDescriptionOfAcceptNoMap = new HashMap<Long, List<MeasureDescriptionDto>>();
		List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo =  new ArrayList<MeasureDescriptionDto>();
		
		Map<Long, List<RelatedChainDto>> overTimeRelatedChainOfAcceptNoMap = new HashMap<Long, List<RelatedChainDto>>();
		List<RelatedChainDto> overTimeRelatedChainOfAcceptNo = new ArrayList<RelatedChainDto>();
		List<OverTimeDto> overTimeRequestOfAcceptNo = new ArrayList<OverTimeDto>();
		
		Long acceptNo = 0L;
		
		for(int i = 0; i < overTimeCompleted.size(); i++) {
			acceptNo = overTimeCompleted.get(i).getAcceptNo();
			overTimeRequestOfAcceptNo.add(this.overTimeService.selectOverTimeRequestOfAcceptNoService(acceptNo));
			
			overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasurerofAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasurerOfAcceptNo);
			
			overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeMeasureDescriptionOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeMeasureDescriptionOfAcceptNo);
			
			overTimeRelatedChainOfAcceptNo = this.overTimeService.selectRelatedChainOfAcceptNoService(overTimeRequestOfAcceptNo.get(i).getAcceptNo());
			overTimeRelatedChainOfAcceptNoMap.put(overTimeRequestOfAcceptNo.get(i).getAcceptNo(), overTimeRelatedChainOfAcceptNo);
		}
	    
		mv.addObject("overTimeCompleted", overTimeCompleted);
		mv.addObject("overTimeRequestOfAcceptNo", overTimeRequestOfAcceptNo);
		mv.addObject("overTimeMeasurerofAcceptNoMap", overTimeMeasurerofAcceptNoMap);
		mv.addObject("overTimeMeasureDescriptionOfAcceptNoMap", overTimeMeasureDescriptionOfAcceptNoMap);
		mv.addObject("overTimeRelatedChainOfAcceptNoMap", overTimeRelatedChainOfAcceptNoMap);
		
	    return mv;
	}
	
	@GetMapping("/employee/MyOverTimeDoReturning.do/{acceptNo}")
	public void doMyOverTimeReturnAction(@PathVariable Long acceptNo, HttpSession session, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		
		OverTimeApprovalDto overTimeApprovalDto = this.employeeService.selectMyOverTimeApprovalOfAcceptNoService(acceptNo);
		overTimeApprovalDto.setApprovalLineConfirm(((EmployeeDto)session.getAttribute(Session.USER)).getEmployeeNo());
		
		boolean isDoReturningSuccess = this.employeeService.myOverTimeDoReturningService(overTimeApprovalDto);
		
		PrintWriter out;
		if (isDoReturningSuccess) {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('반려완료')");
				out.println("window.location.href='/employee/myOverTimeWillApproval.do'");
				out.println("window.close()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('반려실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

   @GetMapping("/employee/myOverTimeReturned.do")
   public ModelAndView viewMyDeployReturnedPage(HttpSession session) {	   
      List<OverTimeApprovalDto> overTimeReturned = this.employeeService.selectMyOverTimeReturnedService((EmployeeDto)session.getAttribute(Session.USER));
      ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
      
      if ( overTimeReturned.size() > 0 ) {
    	 mv.addObject("overTimeReturned",overTimeReturned);
      }
      
      return mv;
   }
   
   @GetMapping("/employee/showMyApprovalReturnedDetail.do/{acceptNo}")
   public ModelAndView viewOverTimeUpdatePage(@PathVariable Long acceptNo) {
	   ModelAndView mv = new ModelAndView(HttpRequestHelper.getJspPath());
	   OverTimeDto overTimeRequestOfAcceptNo = this.overTimeService.selectOverTimeRequestOfAcceptNoService(acceptNo);
	   List<MeasurerDto> overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(acceptNo);
	   List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(acceptNo);
	   List<RelatedChainDto> overTimeRelatedChainOfAcceptNo=this.overTimeService.selectRelatedChainOfAcceptNoService(acceptNo);
				
	   mv.addObject("overTimeRequestOfAcceptNo", overTimeRequestOfAcceptNo);
	   mv.addObject("overTimeMeasurerOfAcceptNo", overTimeMeasurerOfAcceptNo);		
	   mv.addObject("overTimeMeasureDescriptionOfAcceptNo", overTimeMeasureDescriptionOfAcceptNo);
	   mv.addObject("overTimeRelatedChainOfAcceptNo",overTimeRelatedChainOfAcceptNo);
	   
	   return mv;
   }
   
   @PostMapping("/employee/showMyApprovalReturnedDetail.do")
   public ModelAndView doOverTimeUpdateAction(@Valid @ModelAttribute OverTimeDto overTimeDto, Errors errors, HttpServletResponse response, HttpServletRequest request) {
	   response.setCharacterEncoding("UTF-8"); 
	   response.setContentType("text/html; charset=UTF-8");
	   
	   ModelAndView mv = null;
	   ArrayList<String> measurer = new ArrayList<String>();
	   ArrayList<String> measureDescription = new ArrayList<String>();
	   ArrayList<String> relatedChain= new ArrayList<String>();
		

	   
	   Long acceptNo = overTimeDto.getAcceptNo();
	   	   
	   List<MeasurerDto> overTimeMeasurerOfAcceptNo = this.overTimeService.selectMeasurerOfAcceptNoService(acceptNo);
	   List<MeasureDescriptionDto> overTimeMeasureDescriptionOfAcceptNo = this.overTimeService.selectMeasureDescriptionOfAcceptNoService(acceptNo);
	   List<RelatedChainDto> overTimeRelatedChainOfAcceptNo = this.overTimeService.selectRelatedChainOfAcceptNoService(acceptNo);
	   
	   if ( errors.hasErrors() ) {
		   mv = new ModelAndView(HttpRequestHelper.getJspPath());
		   
		   mv.addObject("overTimeRequestOfAcceptNo", overTimeDto);
		   mv.addObject("overTimeMeasurerOfAcceptNo", overTimeMeasurerOfAcceptNo);		
		   mv.addObject("overTimeMeasureDescriptionOfAcceptNo", overTimeMeasureDescriptionOfAcceptNo);
		   mv.addObject("overTimeRelatedChainOfAcceptNo", overTimeRelatedChainOfAcceptNo);
		   
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

	   boolean isOverTimeReRequestSuccess = this.overTimeService.overTimeReRequestService(overTimeDto, measurer, measureDescription, relatedChain);
	   
	   PrintWriter out;
	   if (isOverTimeReRequestSuccess) {
		   try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정완료')");
				out.println("parent.location.reload();");
				out.println("parent.$('#popupLayer').bPopup().close();");
				out.println("parent.$('#popupLayer').html('');");
				out.println("</script>");
				
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   return mv;
	   } else {
		   try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back()");				
				out.println("</script>");
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   return mv;
		}	   
   	}
}
