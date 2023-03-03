package com.example.nobita.todoApplication.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	@RequestMapping("/say-hello")
	@ResponseBody
	public String hello() {
		return "Hello! How are you?";
	}
	
	@RequestMapping("/")
	public String home() {
		return "hello";
	}
}
