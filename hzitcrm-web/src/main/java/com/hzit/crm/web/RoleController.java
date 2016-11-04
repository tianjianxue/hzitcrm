package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.entity.Role;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.service.ModuleService;
import com.hzit.crm.service.RoleModuleService;
import com.hzit.crm.service.RoleService;
import com.hzit.crm.vo.DataGrid;
import com.hzit.crm.vo.EasyuiMessager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述:角色控制器
 * Created by 冼耀基 on 2016/10/24.
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleModuleService roleModuleService;
    @RequestMapping("/role/roleList")
    protected String roleList(){
        return "/role/roleList";
    }

    /**
     * 返回json数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/role/roleListData")
    @ResponseBody
    protected DataGrid<Role> roleListData(String page, String rows){
        Pageable pageable = null;
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows));
        Map<String,String> map = null;

        return this.roleService.roleList(map,pageable);
    }

    /**
     * 跳转到添加角色页面
     * @return
     */
    @RequestMapping(value = "/role/addui",method = RequestMethod.GET)
    protected String add(){
        return "/role/addRole";
    }

    /**
     * 处理角色添加请求参数
     * @param role
     * @return
     */
    @RequestMapping(value = "/role/add",method = RequestMethod.POST)
    @ResponseBody
    protected EasyuiMessager add(Role role){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            roleService.save(role);
            easyuiMessager.setMsg("添加角色成功!");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            easyuiMessager.setMsg("添加角色失败!");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }

    /**
     * 跳到编辑页面
     * @param roleId
     * @return
     */
    @RequestMapping("/role/editRoleui")
    protected String edit(String roleId, Model map){
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("roleId",roleId);
        List<Role> roleList = roleService.searchRole(paramMap);
        if(roleList != null && roleList.size() >0){
            map.addAttribute("role",roleList.get(0));
        }else{
            map.addAttribute("role",null);
        }
        return "/role/editRole";
    }

    /**
     * 处理编辑请求参数
     * @param role
     * @return
     */
    @RequestMapping("/role/editRole")
    @ResponseBody
    protected EasyuiMessager edit(Role role){
        EasyuiMessager easyuiMessager= new EasyuiMessager();
        try{
            roleService.update(role);
            easyuiMessager.setSuccess(true);
            easyuiMessager.setMsg("修改角色成功！");
        }catch (Exception e){
            easyuiMessager.setSuccess(false);
            easyuiMessager.setMsg("修改角色失败！");
        }
        return easyuiMessager;
    }

    /**
     * 跳转到角色授权页
     * @param roleId
     * @param map
     * @return
     */
    @RequestMapping(value="/role/grantRole",method=RequestMethod.GET)
    protected String grantRole(String roleId,Model map){
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("roleId",roleId);
        List<Role> roleList = roleService.searchRole(paramMap);
        if(roleList != null && roleList.size() >0){
            map.addAttribute("role",roleList.get(0));
        }
        //获取模块信息
        List<Module> moduleList = moduleService.searchModuleByParams(null);
        if(moduleList != null && moduleList.size() >0){
            map.addAttribute("moduleList",moduleList);
        }

        //根据角色信息到角色模块中查找相应信息
        Map<String,String> roleParamMap = new HashMap<String, String>();
        roleParamMap.put("roleId",roleId);
        List<RoleModule> roleModuleList = roleModuleService.searchRoleModuleByParams(roleParamMap);
        Map<String,String> resultMap = null;
        if(moduleList != null && moduleList.size() >0){
            map.addAttribute("roleModuleList",roleModuleList);
            resultMap = new HashMap<String, String>();
            for(RoleModule roleModule : roleModuleList){
                resultMap.put(roleModule.getModuleId()+"",roleModule.getModuleId()+"");
            }
            map.addAttribute("roleModuleMap",resultMap);
        }
        return "/role/grantModule";
    }

    /**
     * 处理角色授权请求
     * @param roleId
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/role/grantRole2")
    @ResponseBody
    protected EasyuiMessager grantRole(String roleId,String moduleId){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        RoleModule roleModule = null;
        try{
            if(moduleId!=null){
                roleModuleService.insertRoleModule(roleModule,roleId,moduleId);
            }
            easyuiMessager.setMsg("角色授权成功!");
            easyuiMessager.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            easyuiMessager.setMsg("角色授权失败!");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }
}
