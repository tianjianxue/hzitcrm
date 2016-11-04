<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/30
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fullcalendar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.grey.css" class="skin-color"/>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.ui.custom.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.uniform.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/unicorn.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title">
                            <span class="icon">
                                <i class="icon-align-justify"></i>
                            </span>
                    <h5>修改密码</h5>
                </div>
                <div class="widget-content nopadding">
                    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/userInfo/changePwd"
                          name="password_validate" id="password_validate" novalidate="novalidate" />
                    <div class="control-group">
                        <label class="control-label">请输入旧密码</label>
                        <div class="controls">
                            <input type="password" name="password" id="password" style="height:30px;" placeholder="请输入旧密码!(默认原始密码为:123456)"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请输入新密码</label>
                        <div class="controls">
                            <input type="password" name="pwd" id="pwd" style="height:30px;" placeholder="请输入新密码!"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">请确认新密码</label>
                        <div class="controls">
                            <input type="password" name="pwd2" id="pwd2" style="height:30px;" placeholder="请确认新密码!"/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="修改" class="btn btn-primary" />
                        <input type="reset" value="清空" class="btn btn-warning" />
                       <span style="color:red">${requestScope.msg}</span>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
