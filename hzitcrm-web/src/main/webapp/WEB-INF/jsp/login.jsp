<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/23
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>欢迎登录合众艾特学员咨询系统!</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<div id="logo">
    <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="" />
</div>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/login" method="post"/>
    <p>请输入用户名和密码!</p>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on">
                    <i class="icon-user"></i>
                </span>
                <input type="text" name="name" placeholder="请输入用户名!" value="冼耀基" required="required" />
            </div>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on">
                    <i class="icon-lock"></i>
                </span>
                <input type="password" name="password" placeholder="请输入密码!" value="123456" required="required" />
            </div>
        </div>
    </div>
    <div class="form-actions">
        <span class="pull-right"><input type="submit" class="btn btn-inverse" value="登录" /></span>
    </div>
    </form>
    <form id="recoverform" action="#" class="form-vertical" />
    <p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
            </div>
        </div>
    </div>
    <div class="form-actions">
        <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
        <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover" /></span>
    </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/unicorn.login.js"></script>
</body>
</html>
