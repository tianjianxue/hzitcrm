<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<!-- container-fluid -->
<head>
    <title>来访记录</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/assets/css/fullcalendar.css" />
    <link rel="stylesheet" href="/assets/css/unicorn.main.css" />
    <link rel="stylesheet" href="/assets/css/unicorn.grey.css" class="skin-color" />
    <script src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.ui.custom.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.flot.resize.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/fullcalendar.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/unicorn.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>--%>
    <script type="text/javascript">

        window.onload = function () {
           $.get("${pageContext.request.contextPath}/userInfoList",function(data){
               var $option ;
               var $index_select = $("#index_select");
               for(var item in data){
                   $option = $("<option value='"+data[item].name+"'></option>");
                   $option.html(data[item].name);
                   $index_select.append($option);
               }
           });
            $("#index_button_userinfo").click(function(){
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/userInfo/add',
                    data:$("#form-userinfo").serialize(),
                    success:function(data){
                        alert(data);
                    }
                });
            });
        }
    </script>

   <script type="text/javascript">
        $(function(){

        });
    </script>
</head>
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
<!--左侧动态生成的菜单 -->
<jsp:include page="layout/left.jsp"></jsp:include>

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
                        <form  action="${pageContextPath.request.contxtPath}/userInfo/add" method="post" id="form-userinfo" class="form-horizontal ui-formwizard"   >

                            <div class="control-group">
                                <label class="control-label">应聘者名字</label>
                                <div class="controls">
                                    <input id="username" type="text" name="username" >
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">选择所属咨询师</label>
                                <div class="controls">
                                    <select id="index_select" name="name">
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <input id="index_button_userinfo" class="btn btn-primary" type="button" value="登记" style="">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>来访者列表</h5><span title="54 total posts" class="label label-info tip-left">54</span></div>
                    <div class="widget-content">
                        <div class="row-fluid">
                            <div class="span12">
                                <ul class="site-stats">
                                    <li><i class="icon-user"></i> <strong>李凯</strong> <small>等待 30分钟12秒</small><span title="54 total posts" class="label label-info" style="float:right">接单中</span></li>
                                    <li><i class="icon-user"></i> <strong>1433</strong> <small>Total Users</small><span title="54 total posts" class=" label label-warning" style="float:right">等待中</span></li>
                                    <li><i class="icon-user"></i> <strong>1433</strong> <small>Total Users</small></li>
                                    <li><i class="icon-user"></i> <strong>1433</strong> <small>Total Users</small></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
