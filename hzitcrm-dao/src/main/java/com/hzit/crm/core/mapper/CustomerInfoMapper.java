package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.CustomerInfo;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface CustomerInfoMapper {

	void insertCustomerInfo(CustomerInfo customerInfo);

	void deleteCustomerInfoByCustomerId(Integer customerId);

	void updateCustomerInfo(CustomerInfo customerInfo);

	Page<CustomerInfo> searchCustomerInfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<CustomerInfo> searchCustomerInfoByParams(@Param("map") Map<String, String> map);

} 
