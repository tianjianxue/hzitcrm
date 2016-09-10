package com.hzit.crm.common;

/**
 * 客户状态枚举类
 * Created by yangxiaowei-pc on 2016/8/22.
 */
public enum CustomerStateEnum {
    //已登记
    REGISTERED(01,"已登记"),

    //已接单
    RECEIVED(02,"已接单"),

    //已交付定金
    PREPAYED(03,"已交付定金"),

    //成交
    SUCCESS(00,"成交"),

    //失效
    INVALID(04,"失效");


    CustomerStateEnum(Integer stateCode, String stateName) {
        this.stateCode = stateCode;
        this.stateName = stateName;
    }

    private Integer stateCode;
    private String stateName;


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }
}
