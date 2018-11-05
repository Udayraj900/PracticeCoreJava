/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author RShaw
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value="/home.view")
	public String goToHomePage(){
		return "home";
	}
}
 