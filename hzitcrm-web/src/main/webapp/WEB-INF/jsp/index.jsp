<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
    <title>咨询系统</title>
    <meta charset="UTF-8"/>
    <base href="<%=basePath %>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="assets/css/fullcalendar.css"/>
    <link rel="stylesheet" href="assets/css/unicorn.main.css"/>
    <link rel="stylesheet" href="assets/css/unicorn.grey.css" class="skin-color"/>
    <script src="assets/js/excanvas.min.js"></script>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery.ui.custom.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/unicorn.js"></script>
</head>
<body>

<%--
<div id="header">
    <h1><a href="./dashboard.html"></a></h1>
</div>


<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">登陆者名字</span></a>
        </li>
        <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span
                class="text">退出</span></a></li>
    </ul>
</div>--%>

<!--网页顶部-->
    <jsp:include page="layout/top.jsp"></jsp:include>
<!--左侧动态生成的菜单 -->
<div style="width:45px;">
    <%--<jsp:include page="layout/left.jsp"></jsp:include>--%>
        <div id="sidebar" >
            <ul style="display: block;">

                <c:forEach items="${requestScope.moduleList}" var="module" varStatus="vs">
                    <li>
                        <a target="index_iframe" href="${pageContext.request.contextPath}${module.moduleUrl}"><i class="icon icon-pencil"></i>${module.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
</div>
<div id="content" style="height:768px;">
    <iframe src="${pageContext.request.contextPath}/foreground" name="index_iframe" style="width:100%;height:768px;">

    </iframe>
</div>


</body>
</html>
