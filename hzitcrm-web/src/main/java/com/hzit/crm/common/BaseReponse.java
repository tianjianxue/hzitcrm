package com.hzit.crm.common;


import org.apache.commons.lang3.StringUtils;

/**
 * 用来定义控制层的响应
 * Created by yangxiaowei-pc on 2016/8/24.
 */
public class BaseReponse {

    /**
     * 响应的状态码
     */
    private String responseCode;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 请求成功
     * @return
     */
    public boolean isSuccess(){
        return StringUtils.isNotBlank(responseCode)
                &&
               StringUtils.equals(ResponseEnum.SUCCESS.getResponseCode(),responseCode);
    }

    public void setResponseState(ResponseEnum rspEnum){
        this.responseCode = rspEnum.getResponseCode();
        this.responseMsg = rspEnum.getResponseMsg();
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

    @Override
    public String toString() {
        return "BaseReponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                '}';
    }


}
