package com.hzit.crm.service;

import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 吴文杰 on 2016/9/20.
 */
public interface ModuleService {
    /**
     * 通过用户的微信OPENID获取获取该用户所属的角色所拥有的模块
     * @param wechartno
     * @return
     */
    List<Module> searchModuleByWeCharNo(@Param("wechartno") String wechartno);

}
