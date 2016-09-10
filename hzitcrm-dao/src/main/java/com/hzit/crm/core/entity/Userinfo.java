package com.hzit.crm.core.entity;

/**
 * 
 * @author wuwenjie
 */
public class Userinfo {
	/**
	 *  
	 */
	private Integer userid;
	/**
	 *  
	 */
	private String username;
	/**
	 *  
	 */
	private String upwd;
	/**
	 * 
	 * @param userid
	 */
	public void setUserid(Integer userid){
		this.userid = userid;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getUserid(){
    	return userid;
    }
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
    /**
     * 
     * @return
     */	
    public String getUsername(){
    	return username;
    }
	/**
	 * 
	 * @param upwd
	 */
	public void setUpwd(String upwd){
		this.upwd = upwd;
	}
	
    /**
     * 
     * @return
     */	
    public String getUpwd(){
    	return upwd;
    }
}