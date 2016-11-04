package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Classinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
public interface ClassInfoService {
    Page<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

    List<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map);
}
