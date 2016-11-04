package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/28.
 */
public interface CompanyService {
    void insertCompany(Company company);

    void deleteCompanyByCompanyId(Integer deptId);

    void updateCompany(Company company);

    Page<Company> searchCompanyByParams(@Param("map") Map<String, String> map, Pageable pageable);

    List<Company> searchCompanyByParams(@Param("map") Map<String, String> map);
}
