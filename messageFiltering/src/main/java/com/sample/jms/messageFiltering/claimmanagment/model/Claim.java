/**
 * 
 */
package com.sample.jms.messageFiltering.claimmanagment.model;

import java.io.Serializable;

/**
 * @author reddyp
 *
 */
public class Claim implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hospitalId;
	private String doctorName;
	private String doctorType;
	private String insuranceProvider;
	private double claimAmount;
	
	/**
	 * @return the hospitalId
	 */
	public int getHospitalId() {
		return hospitalId;
	}
	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the doctorType
	 */
	public String getDoctorType() {
		return doctorType;
	}
	/**
	 * @param doctorType the doctorType to set
	 */
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	/**
	 * @return the insuranceProvider
	 */
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	/**
	 * @param insuranceProvider the insuranceProvider to set
	 */
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	/**
	 * @return the claimAmount
	 */
	public double getClaimAmount() {
		return claimAmount;
	}
	/**
	 * @param claimAmount the claimAmount to set
	 */
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	
	
	@Override
	public String toString() {
		return "Claim [hospitalId=" + hospitalId + ", doctorName=" + doctorName + ", doctorType=" + doctorType
				+ ", insuranceProvider=" + insuranceProvider + ", claimAmount=" + claimAmount + "]";
	}
	
	
}
