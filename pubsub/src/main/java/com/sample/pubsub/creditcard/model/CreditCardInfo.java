/**
 * 
 */
package com.sample.pubsub.creditcard.model;

import java.io.Serializable;

/**
 * @author reddyp
 *
 */
public class CreditCardInfo implements Serializable{
	
	private String swipedBy;
	private String swipeInfo;
	/**
	 * @return the swipedBy
	 */
	public String getSwipedBy() {
		return swipedBy;
	}
	/**
	 * @param swipedBy the swipedBy to set
	 */
	public void setSwipedBy(String swipedBy) {
		this.swipedBy = swipedBy;
	}
	/**
	 * @return the swipeInfo
	 */
	public String getSwipeInfo() {
		return swipeInfo;
	}
	/**
	 * @param swipeInfo the swipeInfo to set
	 */
	public void setSwipeInfo(String swipeInfo) {
		this.swipeInfo = swipeInfo;
	}
	
	@Override
	public String toString() {
		return "CreditCardInfo [swipedBy=" + swipedBy + ", swipeInfo=" + swipeInfo + "]";
	}
	
	
}
