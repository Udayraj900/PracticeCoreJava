package com.mindtree.MobilePhoneReviewApp.dao;

import java.util.List;

import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.FetchException;
import com.mindtree.MobilePhoneReviewApp.exception.PersistenceException;

public interface MobileReviewDao {

	List<Brand> getAllBrands()throws FetchException;

	List<Model> getModelsByBrand(Integer brandId)throws FetchException;

	void saveMobileReview(MobileReview mobileReview)throws PersistenceException;

}
