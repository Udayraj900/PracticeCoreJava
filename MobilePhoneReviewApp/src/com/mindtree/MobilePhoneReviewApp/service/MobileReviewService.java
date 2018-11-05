/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.service;

import java.util.List;

import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;

/**
 * @author RShaw
 *
 */
public interface MobileReviewService {

	public List<Brand> getAllBrands() throws ServiceException  ;

	public List<Model> getModelsByBrand(Integer brandId)throws ServiceException;

	public void saveMobileReview(MobileReview mobileReview)throws ServiceException;

}
