/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;

/**
 * @author RShaw
 *
 */
public class MobileReviewValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.isAssignableFrom(MobileReview.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "model.brand.brandId", "error.brand.required","please select one value");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "model.modelId", "error.modelId.required","please select one value");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "designRating", "error.designRating.required","please select one value");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "performanceRating", "error.performanceRating.required","please select one value");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "featuresRating", "error.featuresRating.required","please select one value");
		
	}

}
