package com.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	@RequestMapping("/index")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Welcome Index");
		mv.setViewName("welcome");
		return mv;
	}

}
