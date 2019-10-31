package com.springproject.overtime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springproject.common.utils.HttpRequestHelper;

@Controller
public class OverTimeCotroller {

	@GetMapping("/main/main.do")
	public String viewMainPage() {
		return HttpRequestHelper.getJspPath();
	}
}
