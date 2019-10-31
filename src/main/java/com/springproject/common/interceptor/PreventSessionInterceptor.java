package com.springproject.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproject.common.session.Session;
import com.springproject.employee.dto.EmployeeDto;

public class PreventSessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		EmployeeDto employeeDto = (EmployeeDto) session.getAttribute(Session.USER);
		
		if ( employeeDto != null ) {
			response.sendRedirect("/main/main.do");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
