package com.hzit.crm.core.entity;

/**
 * 
 * @author ethen
 */
public class CustomerTraceRecord {
	/**
	 *  客户id
	 */
	private Integer recordId;
	/**
	 *  客户ID
	 */
	private Integer customerId;
	/**
	 *  咨询师id
	 */
	private Integer userId;
	/**
	 *  访问渠道
	 */
	private Integer channel;
	/**
	 *  咨询日期
	 */
	private String recordDate;
	/**
	 *  时间戳
	 */
	private String times;
	/**
	 *  内容
	 */
	private String content;
	/**
	 * 客户id
	 * @param recordId
	 */
	public void setRecordId(Integer recordId){
		this.recordId = recordId;
	}
	
    /**
     * 客户id
     * @return
     */	
    public Integer getRecordId(){
    	return recordId;
    }
	/**
	 * 客户ID
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}
	
    /**
     * 客户ID
     * @return
     */	
    public Integer getCustomerId(){
    	return customerId;
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
	 * 访问渠道
	 * @param channel
	 */
	public void setChannel(Integer channel){
		this.channel = channel;
	}
	
    /**
     * 访问渠道
     * @return
     */	
    public Integer getChannel(){
    	return channel;
    }
	/**
	 * 咨询日期
	 * @param recordDate
	 */
	public void setRecordDate(String recordDate){
		this.recordDate = recordDate;
	}
	
    /**
     * 咨询日期
     * @return
     */	
    public String getRecordDate(){
    	return recordDate;
    }
	/**
	 * 时间戳
	 * @param times
	 */
	public void setTimes(String times){
		this.times = times;
	}
	
    /**
     * 时间戳
     * @return
     */	
    public String getTimes(){
    	return times;
    }
	/**
	 * 内容
	 * @param content
	 */
	public void setContent(String content){
		this.content = content;
	}
	
    /**
     * 内容
     * @return
     */	
    public String getContent(){
    	return content;
    }

	@Override
	public String toString() {
		return "CustomerTraceRecord{" +
				"recordId=" + recordId +
				", customerId=" + customerId +
				", userId=" + userId +
				", channel=" + channel +
				", recordDate='" + recordDate + '\'' +
				", times='" + times + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}