package com.hzit.crm.core.mapper.entity;

/**
 * 
 * @author ethen
 */
public class CustomerInfo {
	/**
	 *  客户id
	 */
	private Integer customerId;
	/**
	 *  客户姓名
	 */
	private String realName;
	/**
	 *  性别
	 */
	private Integer sex;
	/**
	 *  年龄
	 */
	private Integer age;
	/**
	 *  籍贯
	 */
	private String nativePlace;
	/**
	 *  联系电话
	 */
	private String tel;
	/**
	 *  微信账号
	 */
	private String wechatNo;
	/**
	 *  扣扣号
	 */
	private String qq;
	/**
	 *  学历
	 */
	private Integer educationBg;
	/**
	 *  毕业时间
	 */
	private String graduateTime;
	/**
	 *  毕业院校
	 */
	private String graduateFrom;
	/**
	 *  专业
	 */
	private String majorIn;
	/**
	 *  工作年限
	 */
	private Integer workAge;
	/**
	 *  工作经历
	 */
	private String workExperience;
	/**
	 *  从事职业
	 */
	private String job;
	/**
	 *  教育培训经历
	 */
	private String educateExperience;
	/**
	 *  应聘渠道
	 */
	private Integer recruitChannel;
	/**
	 *  客户状态
	 */
	private Integer customerState;
	/**
	 *  客户级别
	 */
	private String customerLevel;
	/**
	 *  咨询师id
	 */
	private Integer userId;
	/**
	 *  客户感兴趣的目标技能
	 */
	private String targetSkill;
	/**
	 *  推荐人
	 */
	private String introducer;
	/**
	 *  备注
	 */
	private String memo;
	/**
	 *  最后跟进时间 格式2015-10-09
	 */
	private String lastTime;
	/**
	 *  创建时间
	 */
	private String createTime;
	/**
	 * 客户id
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}
	
    /**
     * 客户id
     * @return
     */	
    public Integer getCustomerId(){
    	return customerId;
    }
	/**
	 * 客户姓名
	 * @param realName
	 */
	public void setRealName(String realName){
		this.realName = realName;
	}
	
    /**
     * 客户姓名
     * @return
     */	
    public String getRealName(){
    	return realName;
    }
	/**
	 * 性别
	 * @param sex
	 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
    /**
     * 性别
     * @return
     */	
    public Integer getSex(){
    	return sex;
    }
	/**
	 * 年龄
	 * @param age
	 */
	public void setAge(Integer age){
		this.age = age;
	}
	
    /**
     * 年龄
     * @return
     */	
    public Integer getAge(){
    	return age;
    }
	/**
	 * 籍贯
	 * @param nativePlace
	 */
	public void setNativePlace(String nativePlace){
		this.nativePlace = nativePlace;
	}
	
    /**
     * 籍贯
     * @return
     */	
    public String getNativePlace(){
    	return nativePlace;
    }
	/**
	 * 联系电话
	 * @param tel
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	
    /**
     * 联系电话
     * @return
     */	
    public String getTel(){
    	return tel;
    }
	/**
	 * 微信账号
	 * @param wechatNo
	 */
	public void setWechatNo(String wechatNo){
		this.wechatNo = wechatNo;
	}
	
    /**
     * 微信账号
     * @return
     */	
    public String getWechatNo(){
    	return wechatNo;
    }
	/**
	 * 扣扣号
	 * @param qq
	 */
	public void setQq(String qq){
		this.qq = qq;
	}
	
    /**
     * 扣扣号
     * @return
     */	
    public String getQq(){
    	return qq;
    }
	/**
	 * 学历
	 * @param educationBg
	 */
	public void setEducationBg(Integer educationBg){
		this.educationBg = educationBg;
	}
	
    /**
     * 学历
     * @return
     */	
    public Integer getEducationBg(){
    	return educationBg;
    }
	/**
	 * 毕业时间
	 * @param graduateTime
	 */
	public void setGraduateTime(String graduateTime){
		this.graduateTime = graduateTime;
	}
	
    /**
     * 毕业时间
     * @return
     */	
    public String getGraduateTime(){
    	return graduateTime;
    }
	/**
	 * 毕业院校
	 * @param graduateFrom
	 */
	public void setGraduateFrom(String graduateFrom){
		this.graduateFrom = graduateFrom;
	}
	
    /**
     * 毕业院校
     * @return
     */	
    public String getGraduateFrom(){
    	return graduateFrom;
    }
	/**
	 * 专业
	 * @param majorIn
	 */
	public void setMajorIn(String majorIn){
		this.majorIn = majorIn;
	}
	
    /**
     * 专业
     * @return
     */	
    public String getMajorIn(){
    	return majorIn;
    }
	/**
	 * 工作年限
	 * @param workAge
	 */
	public void setWorkAge(Integer workAge){
		this.workAge = workAge;
	}
	
    /**
     * 工作年限
     * @return
     */	
    public Integer getWorkAge(){
    	return workAge;
    }
	/**
	 * 工作经历
	 * @param workExperience
	 */
	public void setWorkExperience(String workExperience){
		this.workExperience = workExperience;
	}
	
    /**
     * 工作经历
     * @return
     */	
    public String getWorkExperience(){
    	return workExperience;
    }
	/**
	 * 从事职业
	 * @param job
	 */
	public void setJob(String job){
		this.job = job;
	}
	
    /**
     * 从事职业
     * @return
     */	
    public String getJob(){
    	return job;
    }
	/**
	 * 教育培训经历
	 * @param educateExperience
	 */
	public void setEducateExperience(String educateExperience){
		this.educateExperience = educateExperience;
	}
	
    /**
     * 教育培训经历
     * @return
     */	
    public String getEducateExperience(){
    	return educateExperience;
    }
	/**
	 * 应聘渠道
	 * @param recruitChannel
	 */
	public void setRecruitChannel(Integer recruitChannel){
		this.recruitChannel = recruitChannel;
	}
	
    /**
     * 应聘渠道
     * @return
     */	
    public Integer getRecruitChannel(){
    	return recruitChannel;
    }
	/**
	 * 客户状态
	 * @param customerState
	 */
	public void setCustomerState(Integer customerState){
		this.customerState = customerState;
	}
	
    /**
     * 客户状态
     * @return
     */	
    public Integer getCustomerState(){
    	return customerState;
    }
	/**
	 * 客户级别
	 * @param customerLevel
	 */
	public void setCustomerLevel(String customerLevel){
		this.customerLevel = customerLevel;
	}
	
    /**
     * 客户级别
     * @return
     */	
    public String getCustomerLevel(){
    	return customerLevel;
    }
	/**
	 * 咨询师id
	 * @param userId
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
    /**
     * 咨询师id
     * @return
     */	
    public Integer getUserId(){
    	return userId;
    }
	/**
	 * 客户感兴趣的目标技能
	 * @param targetSkill
	 */
	public void setTargetSkill(String targetSkill){
		this.targetSkill = targetSkill;
	}
	
    /**
     * 客户感兴趣的目标技能
     * @return
     */	
    public String getTargetSkill(){
    	return targetSkill;
    }
	/**
	 * 推荐人
	 * @param introducer
	 */
	public void setIntroducer(String introducer){
		this.introducer = introducer;
	}
	
    /**
     * 推荐人
     * @return
     */	
    public String getIntroducer(){
    	return introducer;
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
	/**
	 * 最后跟进时间 格式2015-10-09
	 * @param lastTime
	 */
	public void setLastTime(String lastTime){
		this.lastTime = lastTime;
	}
	
    /**
     * 最后跟进时间 格式2015-10-09
     * @return
     */	
    public String getLastTime(){
    	return lastTime;
    }
	/**
	 * 创建时间
	 * @param createTime
	 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public String getCreateTime(){
    	return createTime;
    }

	@Override
	public String toString() {
		return "CustomerInfo{" +
				"customerId=" + customerId +
				", realName='" + realName + '\'' +
				", sex=" + sex +
				", age=" + age +
				", nativePlace='" + nativePlace + '\'' +
				", tel='" + tel + '\'' +
				", wechatNo='" + wechatNo + '\'' +
				", qq='" + qq + '\'' +
				", educationBg=" + educationBg +
				", graduateTime='" + graduateTime + '\'' +
				", graduateFrom='" + graduateFrom + '\'' +
				", majorIn='" + majorIn + '\'' +
				", workAge=" + workAge +
				", workExperience='" + workExperience + '\'' +
				", job='" + job + '\'' +
				", educateExperience='" + educateExperience + '\'' +
				", recruitChannel=" + recruitChannel +
				", customerState=" + customerState +
				", customerLevel='" + customerLevel + '\'' +
				", userId=" + userId +
				", targetSkill='" + targetSkill + '\'' +
				", introducer='" + introducer + '\'' +
				", memo='" + memo + '\'' +
				", lastTime='" + lastTime + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}