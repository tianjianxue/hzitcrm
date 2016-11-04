package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.Company;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface CompanyMapper {

	void insertCompany(Company company);

	void deleteCompanyByCompanyId(Integer companyId);

	void updateCompany(Company company);

	Page<Company> searchCompanyByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Company> searchCompanyByParams(@Param("map") Map<String, String> map);

} 
