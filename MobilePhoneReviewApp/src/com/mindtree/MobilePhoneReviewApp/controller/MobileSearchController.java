package com.mindtree.MobilePhoneReviewApp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindtree.MobilePhoneReviewApp.DTO.MobileSearchDTO;
import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;
import com.mindtree.MobilePhoneReviewApp.service.MobileReviewService;
import com.mindtree.MobilePhoneReviewApp.service.MobileSearchService;

@Controller
public class MobileSearchController {

	private static Logger logger = Logger
			.getLogger(MobileSearchController.class);

	@Autowired
	MobileSearchService searchService;

	@Autowired
	MobileReviewService reviewService;

	@RequestMapping(value = "/SearchPhonePage", method = RequestMethod.GET)
	public String getSearchPage(org.springframework.ui.Model model)
			throws ServiceException {
		model.addAttribute("brandList", getAllBrands());
		model.addAttribute("mobileList", calculateRatingByPhoneModel());
		return "SearchPhonePage";
	}

	public List<Brand> getAllBrands() throws ServiceException {
		return reviewService.getAllBrands();
	}

	public List<MobileSearchDTO> calculateRatingByPhoneModel() {
		try {
			List<MobileSearchDTO> list = searchService
					.calculateRatingByPhoneModel();
			System.out.println("List:::" + Arrays.toString(list.toArray()));
			for (MobileSearchDTO mobileSearchDTO : list) {
				System.out.println("mobileSearchDTO: "
						+ mobileSearchDTO.getBrandName());
			}
			return list;
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/searchMobileOnCriteria", method = RequestMethod.GET)
	@ResponseBody
	public List<MobileSearchDTO> findMobileOnCriteria(
			@RequestParam("id") List<String> id, @RequestParam("min") Long min,
			@RequestParam("max") Long max) {

		try {
			List<Integer> ids = new ArrayList<>();

			for (String number : id) {
				ids.add(Integer.parseInt(number));
			}
			return searchService.findMobileOnCriteria(ids, min, max);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}

	}

}
