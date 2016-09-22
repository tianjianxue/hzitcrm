package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.CustomerState;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface CustomerStateMapper {

	void insertCustomerState(CustomerState customerState);

	void deleteCustomerStateByStateId(Integer stateId);

	void updateCustomerState(CustomerState customerState);

	Page<CustomerState> searchCustomerStateByParams(@Param("map")Map<String, String> map , Pageable pageable);

	List<CustomerState> searchCustomerStateByParams(@Param("map")Map<String, String> map);

} 
