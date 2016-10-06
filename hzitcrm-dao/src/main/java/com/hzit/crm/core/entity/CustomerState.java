package com.hzit.crm.core.entity;

/**
 * 
 * @author clark1230
 */
public class CustomerState {
	/**
	 *  
	 */
	private Integer stateId;
	/**
	 *  
	 */
	private String customerState;
	/**
	 * 
	 * @param stateId
	 */
	public void setStateId(Integer stateId){
		this.stateId = stateId;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getStateId(){
    	return stateId;
    }
	/**
	 * 
	 * @param customerState
	 */
	public void setCustomerState(String customerState){
		this.customerState = customerState;
	}
	
    /**
     * 
     * @return
     */	
    public String getCustomerState(){
    	return customerState;
    }
}