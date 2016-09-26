package com.hzit.crm.service.impl;

import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.mapper.ModuleMapper;
import com.hzit.crm.service.ModuleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> searchModuleByWeCharNo(@Param("wechartno") String wechartno) {
        return moduleMapper.searchModuleByWeCharNo(wechartno);
    }
}
