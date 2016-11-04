package com.hzit.crm.core.entity;

/**
 * 
 * @author clark1230
 */
public class Classinfo {
	/**
	 *  班级ID
	 */
	private Integer classId;
	/**
	 *  
	 */
	private String classname;
	/**
	 *  班级类型
	 */
	private String classLx;
	/**
	 *  班级位置
	 */
	private String classWz;
	/**
	 *  
	 */
	private java.util.Date classTime;
	/**
	 *  班级状态
	 */
	private String classZt;
	/**
	 *  
	 */
	private Integer classTeacher;
	/**
	 *  
	 */
	private Integer classSchool;
	/**
	 * 班级ID
	 * @param classId
	 */
	public void setClassId(Integer classId){
		this.classId = classId;
	}
	
    /**
     * 班级ID
     * @return
     */	
    public Integer getClassId(){
    	return classId;
    }
	/**
	 * 
	 * @param classname
	 */
	public void setClassname(String classname){
		this.classname = classname;
	}
	
    /**
     * 
     * @return
     */	
    public String getClassname(){
    	return classname;
    }
	/**
	 * 班级类型
	 * @param classLx
	 */
	public void setClassLx(String classLx){
		this.classLx = classLx;
	}
	
    /**
     * 班级类型
     * @return
     */	
    public String getClassLx(){
    	return classLx;
    }
	/**
	 * 班级位置
	 * @param classWz
	 */
	public void setClassWz(String classWz){
		this.classWz = classWz;
	}
	
    /**
     * 班级位置
     * @return
     */	
    public String getClassWz(){
    	return classWz;
    }
	/**
	 * 
	 * @param classTime
	 */
	public void setClassTime(java.util.Date classTime){
		this.classTime = classTime;
	}
	
    /**
     * 
     * @return
     */	
    public java.util.Date getClassTime(){
    	return classTime;
    }
	/**
	 * 班级状态
	 * @param classZt
	 */
	public void setClassZt(String classZt){
		this.classZt = classZt;
	}
	
    /**
     * 班级状态
     * @return
     */	
    public String getClassZt(){
    	return classZt;
    }
	/**
	 * 
	 * @param classTeacher
	 */
	public void setClassTeacher(Integer classTeacher){
		this.classTeacher = classTeacher;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getClassTeacher(){
    	return classTeacher;
    }
	/**
	 * 
	 * @param classSchool
	 */
	public void setClassSchool(Integer classSchool){
		this.classSchool = classSchool;
	}
	
    /**
     * 
     * @return
     */	
    public Integer getClassSchool(){
    	return classSchool;
    }
}