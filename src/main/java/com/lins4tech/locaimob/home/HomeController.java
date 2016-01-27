package com.lins4tech.locaimob.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
//		return principal != null ? "index" : "home/homeNotSignedIn";
		return "index";
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() {
		return "signin/signin";
	}
	
	@RequestMapping(value = "/cadastroCorretor", method = RequestMethod.GET)
	public String newCorretor() {
		return "corretor/NewCorretor";
	}
}
