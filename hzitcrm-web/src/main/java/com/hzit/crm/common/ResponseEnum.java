package com.hzit.crm.common;


/**
 * 请求状态 TODO    待完善
 * Created by yangxiaowei-pc on 2016/8/24.
 */
public enum ResponseEnum {

    SUCCESS("00","请求成功"),
    FAILURE("11","请求失败"),
    CUSTOMER_NAME_IS_BLANK("01","请填写用户信息!"),
    CONSULTANT_IS_BLANK("02","请选择咨询师!");


    private String responseCode;

    private  String responseMsg;

    ResponseEnum(String responseCode,String responseMsg) {
        this.responseMsg = responseMsg;
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
