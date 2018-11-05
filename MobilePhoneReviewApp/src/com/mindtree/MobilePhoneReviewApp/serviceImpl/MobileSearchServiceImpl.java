/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.MobilePhoneReviewApp.DTO.MobileSearchDTO;
import com.mindtree.MobilePhoneReviewApp.dao.MobileSearchDao;
import com.mindtree.MobilePhoneReviewApp.exception.DaoException;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;
import com.mindtree.MobilePhoneReviewApp.service.MobileSearchService;

/**
 * @author RShaw
 *
 */
@Service
public class MobileSearchServiceImpl implements MobileSearchService {
	
	private static Logger logger = Logger
			.getLogger(MobileSearchServiceImpl.class);
	
	
	@Autowired
	MobileSearchDao mobileSearchDao;


	@Override
	public List<MobileSearchDTO> calculateRatingByPhoneModel() throws ServiceException {
		try {
			return mobileSearchDao.calculateRatingByPhoneModel();
		} catch (DaoException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<MobileSearchDTO> findMobileOnCriteria(List<Integer> id, Long min,
			Long max) throws ServiceException {
		try {
			return mobileSearchDao.findMobileOnCriteria(id,min,max);
		} catch (DaoException e) {
			e.printStackTrace();
			return null;
		}
	}
}
