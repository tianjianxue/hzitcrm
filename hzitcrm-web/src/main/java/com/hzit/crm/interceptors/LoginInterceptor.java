package com.hzit.crm.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 冼耀基 on 2016/10/23.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean isFlag;
        //1. 获取请求资源，截取
        String uri = null;
        StringBuffer sb = request.getRequestURL();
        uri = sb.toString();
        // 截取 /....
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

        //2. 判断： 先放行一些资源：/loginui、/login
        if ("login".equals(requestPath) || "loginui".equals(requestPath)) {
            // 放行
            isFlag = true;
        }
        else {
            //3. 对其他资源进行拦截
            //3.1 先获取Session、获取session中的登陆用户(loginInfo)
            HttpSession session = request.getSession(false);
            //System.out.println(session.getAttribute("userinfo")+"------------------------");
            // 判断
            if (session != null) {
                Object obj = session.getAttribute("userinfo");
                //3.2如果获取的内容不为空，说明已经登陆，放行
                if (obj != null) {
                    // 放行
                    isFlag = true;
                } else {
                    isFlag = false;
                    //3.3如果获取的内容为空，说明没有登陆； 跳转到登陆
                    uri = "/loginui";
                    response.sendRedirect(request.getContextPath()+uri);
                }

            } else {
                // 肯定没有登陆
                isFlag = false;
                uri = "/loginui";
                response.sendRedirect(request.getContextPath()+uri);
            }
            //响应
            //request.getRequestDispatcher(uri).forward(request, response);

        }
        return isFlag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
