/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.MobilePhoneReviewApp.dao.MobileReviewDao;
import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.FetchException;
import com.mindtree.MobilePhoneReviewApp.exception.PersistenceException;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;
import com.mindtree.MobilePhoneReviewApp.service.MobileReviewService;

/**
 * @author RShaw
 * 
 */
@Service
public class MobileReviewServiceImpl implements MobileReviewService {

	@Autowired
	MobileReviewDao reviewDao;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Brand> getAllBrands() throws ServiceException {
		try {
			return reviewDao.getAllBrands();
		} catch (FetchException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Model> getModelsByBrand(Integer brandId) throws ServiceException {
		try {
			System.out.println("model by brand"+brandId);
			return reviewDao.getModelsByBrand(brandId);
			
		} catch (FetchException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = ServiceException.class)
	public void saveMobileReview(MobileReview mobileReview)
			throws ServiceException {
		try {
			System.out.println("mobileReview:::"+mobileReview);
			reviewDao.saveMobileReview(mobileReview);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

}
