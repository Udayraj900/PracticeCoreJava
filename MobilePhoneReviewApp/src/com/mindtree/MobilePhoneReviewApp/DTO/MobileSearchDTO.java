package com.mindtree.MobilePhoneReviewApp.DTO;

public class MobileSearchDTO {

	
 private String brandName;
 
 private String modelName;
 
 private Double rating;
 
 private Long price;
 
 private Integer noOfReviews;

/**
 * @return the brandName
 */
public String getBrandName() {
	return brandName;
}

/**
 * @param brandName the brandName to set
 */
public void setBrandName(String brandName) {
	this.brandName = brandName;
}

/**
 * @return the modelName
 */
public String getModelName() {
	return modelName;
}

/**
 * @param modelName the modelName to set
 */
public void setModelName(String modelName) {
	this.modelName = modelName;
}

/**
 * @return the rating
 */
public Double getRating() {
	return rating;
}

/**
 * @param rating the rating to set
 */
public void setRating(Double rating) {
	this.rating = rating;
}

/**
 * @return the price
 */
public Long getPrice() {
	return price;
}

/**
 * @param price the price to set
 */
public void setPrice(Long price) {
	this.price = price;
}

/**
 * @return the noOfReviews
 */
public Integer getNoOfReviews() {
	return noOfReviews;
}

/**
 * @param noOfReviews the noOfReviews to set
 */
public void setNoOfReviews(Integer noOfReviews) {
	this.noOfReviews = noOfReviews;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "MobileSearchDTO [brandName=" + brandName + ", modelName="
			+ modelName + ", rating=" + rating + ", price=" + price
			+ ", noOfReviews=" + noOfReviews + "]";
}

/**
 * @param brandName
 * @param modelName
 * @param rating
 * @param price
 * @param noOfReviews
 */
public MobileSearchDTO(String brandName, String modelName, Double rating,
		Long price, Integer noOfReviews) {
	super();
	this.brandName = brandName;
	this.modelName = modelName;
	this.rating = rating;
	this.price = price;
	this.noOfReviews = noOfReviews;
}
 
 
}
