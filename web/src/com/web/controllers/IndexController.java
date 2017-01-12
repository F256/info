package com.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping("/index")
	public String index(@RequestParam(value = "message", required = false, defaultValue = "Index value") String name, Model model) {
		model.addAttribute("message", name);
		return "welcome";
	}
}
