package com.hzit.crm.web;

import com.fc.platform.commons.util.BeanMapper;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.service.ModuleService;
import com.hzit.crm.vo.ModuleVo;
import com.hzit.crm.vo.WxUserinfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴文杰 on 2016/9/10.
 */
@Controller
@RequestMapping("/layout")
public class MenuController
{
    @Autowired
    private ModuleService moduleService;
    @RequestMapping("/menu")
    public String login(HttpServletRequest request, ModelMap modelMap)
    {
        WxUserinfoVo wx=(WxUserinfoVo)request.getSession().getAttribute("userinfo");
        List<Module> mlist= moduleService.searchModuleByWeCharNo(wx.getUserid());
        List<ModuleVo> volist=new ArrayList<ModuleVo>();
        for(Module m : mlist){
                ModuleVo vo=new ModuleVo();
                BeanMapper.getMapperFacade().map(m,vo);
                vo.setHasChild( hashChilde(mlist,String.valueOf(vo.getModuleId())));
                volist.add(vo);
        }
        modelMap.put("volist",volist);
        return "layout/left";
    }

    private boolean hashChilde(List<Module> source,String target){
        for(Module m : source){
            if(m.getParentModuleId()!=null && m.getParentModuleId().equals(target)){
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/top")
    public String top(HttpServletRequest request, ModelMap modelMap)
    {
        WxUserinfoVo wx=(WxUserinfoVo)request.getSession().getAttribute("userinfo");
        List<Module> mlist= moduleService.searchModuleByWeCharNo(wx.getUserid());
        modelMap.put("mlist",mlist);
        return "layout/top";
    }
}
