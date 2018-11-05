/**
 * 
 */
package com.mindtree.MobilePhoneReviewApp.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author RShaw
 *
 */
@Entity
@Table(name="mobile_review")
public class MobileReview implements Comparable<MobileReview>{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="review_id")
	private Integer reviewId;
	
	@Column(name = "design_rating")
	private Short designRating;
	
	@Column(name = "features_rating")
	private Short featuresRating;
	
	@Column(name = "performance_rating")
	private Short performanceRating;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Model model;
	
	@Column(name="comment")
	private String comment;

	/**
	 * @return the reviewId
	 */
	public Integer getReviewId() {
		return reviewId;
	}

	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * @return the designRating
	 */
	public Short getDesignRating() {
		return designRating;
	}

	/**
	 * @param designRating the designRating to set
	 */
	public void setDesignRating(Short designRating) {
		this.designRating = designRating;
	}

	/**
	 * @return the featuresRating
	 */
	public Short getFeaturesRating() {
		return featuresRating;
	}

	/**
	 * @param featuresRating the featuresRating to set
	 */
	public void setFeaturesRating(Short featuresRating) {
		this.featuresRating = featuresRating;
	}

	/**
	 * @return the performanceRating
	 */
	public Short getPerformanceRating() {
		return performanceRating;
	}

	/**
	 * @param performanceRating the performanceRating to set
	 */
	public void setPerformanceRating(Short performanceRating) {
		this.performanceRating = performanceRating;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + designRating;
		result = prime * result + featuresRating;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + performanceRating;
		result = prime * result
				+ ((reviewId == null) ? 0 : reviewId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MobileReview other = (MobileReview) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (designRating != other.designRating)
			return false;
		if (featuresRating != other.featuresRating)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (performanceRating != other.performanceRating)
			return false;
		if (reviewId == null) {
			if (other.reviewId != null)
				return false;
		} else if (!reviewId.equals(other.reviewId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileReview [designRating=" + designRating
				+ ", featuresRating=" + featuresRating + ", performanceRating="
				+ performanceRating + ", model=" + model + ", comment="
				+ comment + "]";
	}

	public int compareTo(MobileReview mobileReview) {
		return designRating.compareTo(mobileReview.featuresRating);
	}
	
	
}
