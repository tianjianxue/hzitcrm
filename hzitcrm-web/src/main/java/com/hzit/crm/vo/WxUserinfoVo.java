package com.hzit.crm.vo;

/**
 * Created by Administrator on 2016/9/25.
 */
public class WxUserinfoVo {
    private String userid;
    private String username;
    private String userface;

    public WxUserinfoVo() {

    }

    public WxUserinfoVo(String userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public String getUserid() {

        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }
}
