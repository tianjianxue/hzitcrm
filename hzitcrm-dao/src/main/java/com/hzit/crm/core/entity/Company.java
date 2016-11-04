package com.hzit.crm.core.entity;

/**
 * 
 * @author clark1230
 */
public class Company {
	/**
	 *  
	 */
	private Integer companyId;
	/**
	 *  
	 */
	private String companyName;
	/**
	 *  
	 */
	private String description;
	/**
	 * 
	 * @param companyId
	 */
	public void setCompanyId(Integer companyId){
		this.companyId = companyId;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getCompanyId(){
    	return companyId;
    }
	/**
	 * 
	 * @param companyName
	 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
    /**
     * 
     * @return
     */	
    public String getCompanyName(){
    	return companyName;
    }
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
    /**
     * 
     * @return
     */	
    public String getDescription(){
    	return description;
    }
}