package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.mapper.ModuleMapper;
import com.hzit.crm.service.ModuleService;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/25.
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> searchModuleByWeCharNo(@Param("wechartno") String wechartno) {
        return moduleMapper.searchModuleByWeCharNo(wechartno);
    }


    @Override
    public void save(Module module) {
        moduleMapper.insertModule(module);
    }

    @Override
    public void update(Module module) {
        moduleMapper.updateModule(module);
    }

    /**
     * 模块列表
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<Module> moduleList(Map<String, String> map, Pageable pageable) {
        DataGrid<Module> dataGrid = new DataGrid<Module>();
        Page<Module> page = moduleMapper.searchModuleByParams(map,pageable);
        dataGrid.setTotal(page.getTotalElements());
        dataGrid.setRows(page.getContent());
        return dataGrid;
    }

    @Override
    public List<Module> searchModuleByParams(@Param("map") Map<String, String> map) {
        return moduleMapper.searchModuleByParams(map);
    }
}
