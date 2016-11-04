package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Classinfo;
import com.hzit.crm.core.mapper.ClassinfoMapper;
import com.hzit.crm.service.ClassInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
@Service
@Transactional
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    private ClassinfoMapper classinfoMapper;

    @Override
    public Page<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map, Pageable pageable) {
        return classinfoMapper.searchClassinfoByParams(map,pageable);
    }

    @Override
    public List<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map) {
        return classinfoMapper.searchClassinfoByParams(map);
    }
}
