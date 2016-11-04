package com.hzit.crm.core.entity;

import java.util.List;

/**
 * 
 * @author ethen
 */
public class RoleModule {
	private List<Role> roles;
	private List<Module> modules;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}