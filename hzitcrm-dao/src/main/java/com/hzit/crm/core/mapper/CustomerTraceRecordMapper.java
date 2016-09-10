package com.hzit.crm.core.mapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface CustomerTraceRecordMapper {

	void insertCustomerTraceRecord(CustomerTraceRecord customerTraceRecord);

	void deleteCustomerTraceRecordByRecordId(Integer recordId);

	void updateCustomerTraceRecord(CustomerTraceRecord customerTraceRecord);

	Page<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map);

} 
