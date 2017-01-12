package com.web.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	@RequestMapping(value = "/t1", method = RequestMethod.GET)
	public ModelAndView t1(HttpServletRequest request) {
		//
		String title = "Welcome t1";
		String message = "我是DemoController下t1方法";
		//
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", title);
		mv.addObject("message", message);
		mv.setViewName("index/t1");
		return mv;
	}

	@RequestMapping(value = "/t2", method = RequestMethod.POST)
	public ModelAndView t2(HttpServletRequest request) throws UnsupportedEncodingException {
		//
		String title = "Welcome t2";
		String message = "我是DemoController下t2方法";
		//设置解码方式，对于简体中文，使用UTF-8解码  
		request.setCharacterEncoding("UTF-8"); 
		String name = request.getParameter("name");
		Integer age = Integer.valueOf(request.getParameter("age"));
		System.out.println(name);
		//
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", title);
		mv.addObject("message", message);
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("index/t2");
		return mv;
	}
}
