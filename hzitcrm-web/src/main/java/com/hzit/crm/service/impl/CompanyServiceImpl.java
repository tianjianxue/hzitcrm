package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Company;
import com.hzit.crm.core.mapper.CompanyMapper;
import com.hzit.crm.service.CompanyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/28.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public void insertCompany(Company company) {
        companyMapper.insertCompany(company);
    }

    @Override
    public void deleteCompanyByCompanyId(Integer deptId) {
        companyMapper.deleteCompanyByCompanyId(deptId);
    }

    @Override
    public void updateCompany(Company company) {
        companyMapper.updateCompany(company);
    }

    @Override
    public Page<Company> searchCompanyByParams(@Param("map") Map<String, String> map, Pageable pageable) {
        return null;
    }

    @Override
    public List<Company> searchCompanyByParams(@Param("map") Map<String, String> map) {
        return companyMapper.searchCompanyByParams(map);
    }
}
