package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Dept;
import com.hzit.crm.service.DeptService;
import com.hzit.crm.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/24.
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/dept/deptList")
    protected String deptList(){
        return "/dept/deptList";
    }

    /**
     * 获取列表数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/dept/deptListData")
    @ResponseBody
    protected DataGrid<Dept> deptListData(String page, String rows){
        Pageable pageable = null;
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows));
        Map<String,String> map = null;
        return deptService.deptList(map,pageable);
    }

}
