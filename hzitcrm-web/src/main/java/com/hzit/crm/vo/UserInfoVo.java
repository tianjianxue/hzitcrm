package com.hzit.crm.vo;

/**
 * 
 * @author clark1230
 */
public class UserInfoVo {
	/**
	 *  用户id
	 */
	private Integer userId;
	/**
	 *  用户名
	 */
	private String name;
	/**
	 *  
	 */
	private String password;
	/**
	 *  角色id
	 */
	private Integer roleId;
	/**
	 * 角色名称
     */
	private String roleName;
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
	 *  
	 */
	private Integer deptId;
	private String deptName;
	/**
	 *  
	 */
	private Integer companyId;
	private String companyName;
	/**
	 * 用户id
	 * @param userId
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
    /**
     * 用户id
     * @return Integer
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
     * @return String
     */	
    public String getName(){
    	return name;
    }
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
    /**
     * 
     * @return String
     */	
    public String getPassword(){
    	return password;
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
     * @return Integer
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
     * @return String
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
     * @return String
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
     * @return String
     */	
    public String getRealName(){
    	return realName;
    }
	/**
	 * 
	 * @param deptId
	 */
	public void setDeptId(Integer deptId){
		this.deptId = deptId;
	}
	
    /**
     * 
     * @return Integer
     */	
    public Integer getDeptId(){
    	return deptId;
    }
	/**
	 * 
	 * @param companyId
	 */
	public void setCompanyId(Integer companyId){
		this.companyId = companyId;
	}
	
    /**
     * 
     * @return Integer
     */	
    public Integer getCompanyId(){
    	return companyId;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}