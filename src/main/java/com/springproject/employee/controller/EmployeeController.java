package com.springproject.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.employee.service.EmployeeService;

@Controller
public class EmployeeController {

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
				out.println("window.location.href = 'http://localhost:8080/main/main.do';");
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
		return HttpRequestHelper.getJspPath();

//       boolean isThisUserHaveAuthorityOfEmployeeRegist=this.employeeService.checkisThisUserHaveAuthorityOfEmployeeRegistService((EmployeeDto)session.getAttribute(Session.USER));
//       if(isThisUserHaveAuthorityOfEmployeeRegist) {
//          return HttpRequestHelper.getJspPath();
//       }
//       else {
//          try {
//            PrintWriter out;
//            out = response.getWriter();
//            out.println("<script>");
//            out.println("alert('사원등록권한이 없습니다')");
//            out.println("history.back()");
//            out.println("</script>");
//         } catch (IOException e) {
//            e.printStackTrace();
//         }
//         return null;
//       }
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

}
