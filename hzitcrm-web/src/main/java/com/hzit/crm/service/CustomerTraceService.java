package com.hzit.crm.service;

import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.vo.CustomerTraceRecordVo;

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
}
