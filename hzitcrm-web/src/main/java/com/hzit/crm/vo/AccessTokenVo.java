package com.hzit.crm.vo;

/**
 * Created by Administrator on 2016/7/11.
 */
public class AccessTokenVo {
    private String accessToken;
    private Long createTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
