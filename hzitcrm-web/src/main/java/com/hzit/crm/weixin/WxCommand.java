package com.hzit.crm.weixin;


import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzit.crm.vo.AccessTokenVo;
import com.hzit.crm.vo.WxUserinfoVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/12.
 */
@Service
public class WxCommand {

    @Value("${corpID}")
    private String corpID;
    @Value("${secret}")
    private String secret;


    private static AccessTokenVo accessToken;


    public   String  getAccessToken()
    {
        long now=System.currentTimeMillis();
        String accessTokenString;
        try{
        if(accessToken==null || (now-accessToken.getCreateTime())>7000000)
        {
            JsonNode node = NetUtils.getJson("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpID+"&corpsecret="+secret);
            accessToken=new AccessTokenVo();
            //获取新的accessToken
            accessTokenString=node.get("access_token").asText();
            accessToken.setCreateTime(System.currentTimeMillis());
            accessToken.setAccessToken(accessTokenString);
        }}catch (Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return accessToken.getAccessToken();
    }

    public WxUserinfoVo getWxUserJson(String auth_code){
        String url="https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token="+getAccessToken();
        String postdata="{\"auth_code\":\""+auth_code+"\"}";
        ObjectMapper mapper = new ObjectMapper();
        String response="";
        WxUserinfoVo wx=null;
        try {
            response=NetUtils.post(url,postdata,"application/json");
            System.out.println(response);
            JsonNode node=mapper.readValue(response,JsonNode.class);
            JsonNode user_info= node.get("user_info");
            if(user_info.has("userid")){
                wx=new WxUserinfoVo();
                wx.setUserid(user_info.get("userid").toString());
                wx.setUsername(user_info.get("name").toString());
                wx.setUserface(user_info.get("avatar").toString());
            }
            return wx;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


}
