package com.hzit.crm.core.mapper.entity;

/**
 * 
 * @author ethen
 */
public class RoleModule {
	/**
	 *  
	 */
	private Integer id;
	/**
	 *  
	 */
	private Integer roleId;
	/**
	 *  角色模块表
	 */
	private Integer moduleId;
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
	 * @param roleId
	 */
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getRoleId(){
    	return roleId;
    }
	/**
	 * 角色模块表
	 * @param moduleId
	 */
	public void setModuleId(Integer moduleId){
		this.moduleId = moduleId;
	}
	
    /**
     * 角色模块表
     * @return
     */	
    public Integer getModuleId(){
    	return moduleId;
    }
}