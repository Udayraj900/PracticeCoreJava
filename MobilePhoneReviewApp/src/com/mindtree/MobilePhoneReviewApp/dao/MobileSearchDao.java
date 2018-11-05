/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.dao;

import java.util.List;

import com.mindtree.MobilePhoneReviewApp.DTO.MobileSearchDTO;
import com.mindtree.MobilePhoneReviewApp.exception.DaoException;

/**
 * @author RShaw
 *
 */
public interface MobileSearchDao {

public List<MobileSearchDTO> calculateRatingByPhoneModel()throws DaoException;

public List<MobileSearchDTO> findMobileOnCriteria(List<Integer> id, Long min, Long max)throws DaoException;

}
