package com.hzit.crm.core.mapper.entity;

/**
 * 
 * @author ethen
 */
public class Module {
	/**
	 *  模块id
	 */
	private Integer moduleId;
	/**
	 *  模块名称
	 */
	private String name;
	/**
	 *  模块权限值
	 */
	private Integer authValue;
	/**
	 *  模块的URL
	 */
	private String moduleUrl;
	/**
	 *  父模块ID
	 */
	private String parentModuleId;
	/**
	 *  模块等级
	 */
	private String moduleLevel;
	/**
	 * 模块id
	 * @param moduleId
	 */
	public void setModuleId(Integer moduleId){
		this.moduleId = moduleId;
	}
	
    /**
     * 模块id
     * @return
     */	
    public Integer getModuleId(){
    	return moduleId;
    }
	/**
	 * 模块名称
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * 模块名称
     * @return
     */	
    public String getName(){
    	return name;
    }
	/**
	 * 模块权限值
	 * @param authValue
	 */
	public void setAuthValue(Integer authValue){
		this.authValue = authValue;
	}
	
    /**
     * 模块权限值
     * @return
     */	
    public Integer getAuthValue(){
    	return authValue;
    }
	/**
	 * 模块的URL
	 * @param moduleUrl
	 */
	public void setModuleUrl(String moduleUrl){
		this.moduleUrl = moduleUrl;
	}
	
    /**
     * 模块的URL
     * @return
     */	
    public String getModuleUrl(){
    	return moduleUrl;
    }
	/**
	 * 父模块ID
	 * @param parentModuleId
	 */
	public void setParentModuleId(String parentModuleId){
		this.parentModuleId = parentModuleId;
	}
	
    /**
     * 父模块ID
     * @return
     */	
    public String getParentModuleId(){
    	return parentModuleId;
    }
	/**
	 * 模块等级
	 * @param moduleLevel
	 */
	public void setModuleLevel(String moduleLevel){
		this.moduleLevel = moduleLevel;
	}
	
    /**
     * 模块等级
     * @return
     */	
    public String getModuleLevel(){
    	return moduleLevel;
    }
}