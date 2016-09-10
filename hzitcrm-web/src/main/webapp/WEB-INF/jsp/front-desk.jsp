<%--
  Created by IntelliJ IDEA.
  User: yangxiaowei-pc
  Date: 2016/8/21
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- container-fluid -->
<head>
  <title>来访记录</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="<%=basePath%>jsp/css/bootstrap.min.css" />
  <link rel="stylesheet" href="<%=basePath%>jsp/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="<%=basePath%>jsp/css/fullcalendar.css" />
  <link rel="stylesheet" href="<%=basePath%>jsp/css/unicorn.main.css" />
  <link rel="stylesheet" href="<%=basePath%>jsp/css/unicorn.grey.css" class="skin-color" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<div id="header">
  <h1><a href="./dashboard.html"></a></h1>
</div>


<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav btn-group">
    <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">登陆者名字</span></a></li>
    <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
  </ul>
</div>
<!--左侧动态生成的菜单    -->
<div id="sidebar">
  <a href="#" class="visible-phone"><i class="icon icon-home"></i> 手机查看</a>
  <ul>
    <li class="active"><a href="index.html"><i class="icon icon-home"></i><span>来访者登记</span></a></li>
    <li class="submenu">
      <a href="#"><i class="icon icon-th-list"></i> <span>表单</span> <span class="label">3</span></a>
      <ul>
        <li><a href="form-common.html">常规表单</a></li>
        <li><a href="form-validation.html">验证表单</a></li>
        <li><a href="form-wizard.html">指定模块</a></li>
      </ul>
    </li>
    <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>Buttons &amp; icons</span></a></li>
    <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Interface elements</span></a></li>
    <li><a href="tables.html"><i class="icon icon-th"></i> <span>Tables</span></a></li>
    <li><a href="grid.html"><i class="icon icon-th-list"></i> <span>Grid Layout</span></a></li>
    <li class="submenu">
      <a href="#"><i class="icon icon-file"></i> <span>Sample pages</span> <span class="label">4</span></a>
      <ul>
        <li><a href="invoice.html">Invoice</a></li>
        <li><a href="chat.html">Support chat</a></li>
        <li><a href="calendar.html">Calendar</a></li>
        <li><a href="gallery.html">Gallery</a></li>
      </ul>
    </li>
  </ul>
</div>


<!--主页的内容-->
<div id="content">
  <div id="content-header">
    <h1>来访者登记</h1>
  </div>
  <div id="breadcrumb">
    <a href="index.html" class="tip-bottom" data-original-title="Go to Home"><i class="icon-home"></i>来访者登记</a>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-file"></i></span><h5>来访者登记</h5></div>
          <div class="widget-content nopadding">


            <!--记录当前访问者的表单-->
            <form id="form-wizard" class="form-horizontal ui-formwizard" method="post" >
              <div class="control-group">
                <label class="control-label">应聘者名字</label>
                <div class="controls">
                  <input id="customerName" type="text" name="customerName" >
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">选择所属咨询师</label>
                <div class="controls">
                  <select name="consultantId">
                    <option></option>
                    <c:forEach var="consultant" items="${consultantList}">
                      <option value="${consultant.userId}">${consultant.realName}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <div class="controls">
                  <input id="next" class="btn btn-primary"  type="submit" value="登记" style=""/>
                </div>
              </div>
            </form>



          </div>
        </div>
      </div>
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>来访者列表</h5><span title="${customerInfoList.size()} total posts" class="label label-info tip-left">${customerInfoList.size()}</span></div>
          <div class="widget-content" >
            <div class="row-fluid">
              <div class="span12" >
                <ul class="site-stats" id="customerInfoList-ethen">
                <c:forEach var="customerInfo" items="${customerInfoList}" >
                  <li><i class="icon-user"></i> <strong>${customerInfo.realName}</strong> <small>等待时间:${customerInfo.memo}</small></li>
                </c:forEach>
              </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<script src="<%=basePath%>jsp/js/excanvas.min.js"></script>
<script src="<%=basePath%>jsp/js/jquery.min.js"></script>
<script src="<%=basePath%>jsp/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>jsp/js/bootstrap.min.js"></script>
<script src="<%=basePath%>jsp/js/jquery.flot.min.js"></script>
<script src="<%=basePath%>jsp/js/jquery.flot.resize.min.js"></script>
<script src="<%=basePath%>jsp/js/jquery.peity.min.js"></script>
<script src="<%=basePath%>jsp/js/fullcalendar.min.js"></script>
<%--<script src="<%=basePath%>jsp/js/unicorn.js"></script>
<script src="<%=basePath%>jsp/js/unicorn.dashboard.js"></script>--%>
<script src="<%=basePath%>jsp/js/front-desk.js"></script><%--added by yangxiaowei--%>

</body>
</html>
