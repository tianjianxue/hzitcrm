package com.hzit.crm.common;

import com.alibaba.druid.util.StringUtils;

/**
 * 角色枚举类
 * Created by yangxiaowei-pc on 2016/8/22.
 */
public enum RoleEnum {
    CEO(00,"总经理"),
    MANAGER(01,"经理"),
    CONSULTANT(02,"咨询师"),
    FRONTDESK(03,"前台");

    private Integer roleType;

    private String roleName;


    private RoleEnum(Integer roleType,String roleName){
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public static String getRoleName(Integer roleType){

        for(RoleEnum role : RoleEnum.values()){
            if(role.getRoleType()==roleType){
                return role.getRoleName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(RoleEnum.CONSULTANT.getRoleType());
    }


}
