/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mindtree.MobilePhoneReviewApp.DTO.MobileSearchDTO;
import com.mindtree.MobilePhoneReviewApp.dao.MobileSearchDao;
import com.mindtree.MobilePhoneReviewApp.exception.DaoException;

/**
 * @author RShaw
 * 
 */
@Repository
public class MobileSearchDaoImpl implements MobileSearchDao {

	private static Logger logger = Logger.getLogger(MobileSearchDaoImpl.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	/**
	 * @param hibernateTemplate
	 *            the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileSearchDTO> calculateRatingByPhoneModel()
			throws DaoException {
		List<Object[]> rows = hibernateTemplate
				.find(" select round(avg((a.designRating+a.featuresRating+a.performanceRating)/3.00),2) as rating,count(b.modelName) as noOfReviews,b.modelName as modelName,b.price as price,c.brandName as brandName from MobileReview a,Model b,Brand c where a.model.modelId = b.modelId and b.brand.brandId = c.brandId group by b.modelName,b.price,c.brandName order by rating DESC");

		List<MobileSearchDTO> result = new ArrayList<MobileSearchDTO>(
				rows.size());
		for (Object[] row : rows) {
			result.add(new MobileSearchDTO((String) row[4], (String) row[2],
					(Double) row[0], (Long) row[3], ((Long) row[1]).intValue()));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileSearchDTO> findMobileOnCriteria(List<Integer> id,
			Long min, Long max) throws DaoException {
		System.out.println("id;;" + id);

		String hql = "select round(avg((a.designRating+a.featuresRating+a.performanceRating)/3.00),2),count(b.modelName),b.modelName,b.price,c.brandName from MobileReview a,Model b,Brand c where a.model.modelId = b.modelId and b.brand.brandId = c.brandId and c.brandId in (:idList) and b.price  between '"
				+ min
				+ "' and '"
				+ max
				+ "' group by b.modelName,b.price,c.brandName";
		String[] params = { "idList" };
		Object[] values = { id };
		List<Object[]> rows = hibernateTemplate.findByNamedParam(hql, params,
				values);

		List<MobileSearchDTO> result = new ArrayList<MobileSearchDTO>(
				rows.size());
		for (Object[] row : rows) {
			result.add(new MobileSearchDTO((String) row[4], (String) row[2],
					(Double) row[0], (Long) row[3], ((Long) row[1]).intValue()));

		}
		return result;

	}
}
