package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerState;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
public interface CustomerStateService {
    void insertCustomerState(CustomerState customerState);

    void deleteCustomerStateByStateId(Integer stateId);

    void updateCustomerState(CustomerState customerState);

    Page<CustomerState> searchCustomerStateByParams(@Param("map")Map<String, String> map , Pageable pageable);

    List<CustomerState> searchCustomerStateByParams(@Param("map")Map<String, String> map);

}
