package com.hzit.crm.vo;
/**
 * 类描述：服务器处理客户端(easyui)请求所返回的json数据信息
 * @author 冼耀基
 * @version 1.0
 *
 */
public class EasyuiMessager {
	private boolean success = false;//是否成功
	private String msg = null;//提示信息
	private Object object = null;//其他信息
	
	public EasyuiMessager() {
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	

}
