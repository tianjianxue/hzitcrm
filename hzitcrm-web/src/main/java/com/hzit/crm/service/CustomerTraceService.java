package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.vo.CustomerTraceRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/2.
 */
public interface CustomerTraceService {
   List<CustomerTraceRecord> customerTraceByUserId(String userId);
   List<CustomerTraceRecordVo> searchTraceRecord(Map<String,String> map);

   /**
    * 添加跟进记录
    * @param customerTraceRecord
     */
   void addTraceRecord(CustomerTraceRecord customerTraceRecord);

   List<CustomerTraceRecordVo> detailedCustomerTrace(CustomerInfo customerInfo);
   Page<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map, Pageable pageable);

   List<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map);
}
