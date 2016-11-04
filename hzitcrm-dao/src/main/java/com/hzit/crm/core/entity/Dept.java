package com.hzit.crm.core.entity;

/**
 * 
 * @author clark1230
 */
public class Dept {
	/**
	 *  
	 */
	private Integer id;
	/**
	 *  
	 */
	private String deptName;
	/**
	 *  
	 */
	private String description;
	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getId(){
    	return id;
    }
	/**
	 * 
	 * @param deptName
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	
    /**
     * 
     * @return
     */	
    public String getDeptName(){
    	return deptName;
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