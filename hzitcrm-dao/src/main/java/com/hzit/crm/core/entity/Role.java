package com.hzit.crm.core.entity;

/**
 * 
 * @author ethen
 */
public class Role {
	/**
	 *  角色id
	 */
	private Integer roleId;
	/**
	 *  角色名称
	 */
	private String roleName;
	/**
	 *  权限值
	 */
	private Integer totalAuthValue;
	/**
	 *  备注
	 */
	private String memo;
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
	 * 角色名称
	 * @param roleName
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	
    /**
     * 角色名称
     * @return
     */	
    public String getRoleName(){
    	return roleName;
    }
	/**
	 * 权限值
	 * @param totalAuthValue
	 */
	public void setTotalAuthValue(Integer totalAuthValue){
		this.totalAuthValue = totalAuthValue;
	}
	
    /**
     * 权限值
     * @return
     */	
    public Integer getTotalAuthValue(){
    	return totalAuthValue;
    }
	/**
	 * 备注
	 * @param memo
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}
	
    /**
     * 备注
     * @return
     */	
    public String getMemo(){
    	return memo;
    }
}