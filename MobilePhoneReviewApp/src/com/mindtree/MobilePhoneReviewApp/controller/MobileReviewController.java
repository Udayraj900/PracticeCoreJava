/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;
import com.mindtree.MobilePhoneReviewApp.service.MobileReviewService;
import com.mindtree.MobilePhoneReviewApp.validator.MobileReviewValidator;

/**
 * @author RShaw
 * 
 */
@Controller
public class MobileReviewController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger
			.getLogger(MobileReviewController.class);

	@Autowired
	MobileReviewService reviewService;

	public List<Integer> populateRating() {
		List<Integer> rating = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			rating.add(i);
		}
		return rating;
	}

	public List<Brand> getAllBrands() throws ServiceException {
		return reviewService.getAllBrands();
	}

	@RequestMapping(value = "/loadModelList", method = RequestMethod.GET)
	@ResponseBody
	public List<Model> getModelByBrand(Integer brandId) throws ServiceException {
		return reviewService.getModelsByBrand(brandId);
	}

	@RequestMapping(value = "/MobileReviewForm", method = RequestMethod.GET)
	public String getReviewPage(org.springframework.ui.Model model)
			throws ServiceException {
		model.addAttribute("mobileReview", new MobileReview());
		model.addAttribute("brandList", getAllBrands());
		model.addAttribute("rating", populateRating());
		return "MobileReviewForm";
	}

	@RequestMapping(value = "/saveReview", method = RequestMethod.POST)
	public String saveReview(
			@ModelAttribute("mobileReview") MobileReview mobileReview,
			BindingResult result, Locale locale,
			org.springframework.ui.Model model) throws ServiceException {

		String page = "home";
		
		MobileReviewValidator reviewValidator = new MobileReviewValidator();
		if (reviewValidator.supports(MobileReview.class)) {
			reviewValidator.validate(mobileReview, result);
			if (result.hasErrors()) {
				page = "MobileReviewForm";
				model.addAttribute("mobileReview", mobileReview);
				model.addAttribute("brandList", getAllBrands());
				model.addAttribute("rating", populateRating());
			} else {
				reviewService.saveMobileReview(mobileReview);
				
			}
		}
		return page;
	}

}
