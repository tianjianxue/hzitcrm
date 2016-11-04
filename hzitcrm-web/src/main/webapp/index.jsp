<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/19
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title></title>
    <base href="<%=basePath %>" />
    <script type="application/javascript">
      //window.location='https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id=wxb84c9584d983b952&redirect_uri=http://www.hzitxx.com:8082/weixinLogin&state=xxxx&usertype=member'
    </script>
  </head>
  <body>
      <a href="testLogin?weno=1">登录测试</a><br/>
      <a href="testLogin?weno=1">登录测试</a><br/>
      <a href="testLogin?weno=1">登录测试</a><br/>
      <a href="testLogin?weno=1">登录测试</a><br/>
  </body>
</html>
