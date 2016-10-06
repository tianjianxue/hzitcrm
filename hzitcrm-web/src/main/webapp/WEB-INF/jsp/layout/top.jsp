<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/20
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <h1><a href="#"></a></h1>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">${sessionScope.userinfo.username}</span></a>
        </li>
        <li class="btn btn-inverse"><a title="" href="logout"><i class="icon icon-share-alt"></i> <span
                class="text">退出</span></a></li>
    </ul>
</div>