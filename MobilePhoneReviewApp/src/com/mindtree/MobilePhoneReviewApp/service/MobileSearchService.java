package com.mindtree.MobilePhoneReviewApp.service;

import java.util.List;

import com.mindtree.MobilePhoneReviewApp.DTO.MobileSearchDTO;
import com.mindtree.MobilePhoneReviewApp.exception.ServiceException;

public interface MobileSearchService {

public List<MobileSearchDTO> calculateRatingByPhoneModel()throws ServiceException;

public List<MobileSearchDTO> findMobileOnCriteria(List<Integer> id, Long min, Long max)throws ServiceException;

}
