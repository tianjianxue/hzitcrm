package com.hzit.crm.core.entity;

/**
 * 
 * @author ethen
 */
public class UserInfo {
	/**
	 *  用户id
	 */
	private Integer userId;
	/**
	 *  用户名
	 */
	private String name;
	/**
	 *  角色id
	 */
	private Integer roleId;
	/**
	 *  微信号
	 */
	private String wechatNo;
	/**
	 *  手机号
	 */
	private String tel;
	/**
	 *  用户姓名
	 */
	private String realName;
	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
    /**
     * 用户id
     * @return
     */	
    public Integer getUserId(){
    	return userId;
    }
	/**
	 * 用户名
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * 用户名
     * @return
     */	
    public String getName(){
    	return name;
    }
	/**
	 * 角色id
	 * @param roleId
	 */
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	
    /**
     * 角色id
     * @return
     */	
    public Integer getRoleId(){
    	return roleId;
    }
	/**
	 * 微信号
	 * @param wechatNo
	 */
	public void setWechatNo(String wechatNo){
		this.wechatNo = wechatNo;
	}
	
    /**
     * 微信号
     * @return
     */	
    public String getWechatNo(){
    	return wechatNo;
    }
	/**
	 * 手机号
	 * @param tel
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	
    /**
     * 手机号
     * @return
     */	
    public String getTel(){
    	return tel;
    }
	/**
	 * 用户姓名
	 * @param realName
	 */
	public void setRealName(String realName){
		this.realName = realName;
	}
	
    /**
     * 用户姓名
     * @return
     */	
    public String getRealName(){
    	return realName;
    }
}