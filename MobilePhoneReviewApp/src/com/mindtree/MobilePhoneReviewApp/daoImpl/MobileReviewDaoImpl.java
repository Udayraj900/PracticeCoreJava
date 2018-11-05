/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mindtree.MobilePhoneReviewApp.dao.MobileReviewDao;
import com.mindtree.MobilePhoneReviewApp.entity.Brand;
import com.mindtree.MobilePhoneReviewApp.entity.MobileReview;
import com.mindtree.MobilePhoneReviewApp.entity.Model;
import com.mindtree.MobilePhoneReviewApp.exception.FetchException;
import com.mindtree.MobilePhoneReviewApp.exception.PersistenceException;

/**
 * @author RShaw
 *
 */
@Repository
public class MobileReviewDaoImpl implements MobileReviewDao {

	
	@Autowired
	HibernateTemplate hibernateTemplate;

	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> getAllBrands() throws FetchException {
		return hibernateTemplate.find("from Brand");
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Model> getModelsByBrand(Integer brandId) throws FetchException {
		return hibernateTemplate.find("from Model where brand.brandId="+brandId);
	}

	@Override
	public void saveMobileReview(MobileReview mobileReview)
			throws PersistenceException {
		hibernateTemplate.save(mobileReview);
	}
	
	
	
}
