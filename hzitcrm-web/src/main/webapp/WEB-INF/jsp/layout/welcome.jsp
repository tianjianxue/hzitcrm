<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>前台</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/assets/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="/assets/css/fullcalendar.css"/>
    <link rel="stylesheet" href="/assets/css/unicorn.main.css"/>
    <link rel="stylesheet" href="/assets/css/unicorn.grey.css" class="skin-color"/>
    <script src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.ui.custom.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {

            <!--获取咨询师信息-->
            $.get("${pageContext.request.contextPath}/userInfoList", function (data) {
                var $option;
                var $index_select = $("#index_select");
                for (var item in data) {
                    $option = $("<option value='" + data[item].userId + "'></option>");
                    $option.html(data[item].name);
                    $index_select.append($option);
                }
            });
            <!--保存来访者信息-->
            $("#index_button_userinfo").click(function () {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/userInfo/add',
                    data: $("#form-userinfo").serialize(),
                    success: function (data) {
                        alert(data);
                    }
                });
            });

            function getTime() {
                var today = new Date();
                var s = today.getFullYear() + "年" + today.getMonth() + "月" + today.getDate() + "日" + today.getHours() + "时" + today.getMinutes() + "分" + today.getSeconds() + "秒" + "\t星期" + today.getDay();
                return s;
            }
            <!--获取要修改客户信息-->
            function editCustomerInfo(customerId,realName){
                console.log(customerId);
                $("#welcome_p").html("客户名称:"+realName+"----"+customerId);
                <!--异步方式获取咨询师信息-->
                $.get("${pageContext.request.contextPath}/userInfoList", function (data) {
                    var $option;
                    var $index_select = $("#welcome_userName");
                    $index_select.empty();
                    for (var item in data) {
                        $option = $("<option value='" + data[item].userId + "'></option>");
                        $option.html(data[item].name);
                        $index_select.append($option);
                    }
                });

            }


            <!--获取来访客户信息-->
            function getCustomerInfo() {
                $.ajax({
                    type: 'get',
                    url: '${pageContext.request.contextPath}/customerInfoList',
                    success: function (data) {
                        var showTime = getTime();//获取当前时间
                        var leftTime;//来访时间
                        var $ul = $("#index_customer_info");
                        var $li = null;
                        var currentDate;
                        var hour;
                        var minute;
                        var second;
                        var $totalCustomer = $("#index_span_total");
                        var $index_showTime = $("#index_showTime");
                        $ul.empty();//每次请求服务器时清空当前ul标签下的li标签
                        $index_showTime.empty();
                        $index_showTime.html("当前时间为:" + showTime);
                        $totalCustomer.html("当前访客人数:" + data.length);
                        for (var item in data) {
                            currentDate = new Date(Date.parse(data[item].createTime));
                            hour = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 / 60 / 60);
                            minute = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 / 60 - (hour * 60));
                            second = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 - (hour * 60 * 60 + minute * 60));
                            leftTime = hour + "小时" + minute + "分钟" + second + "秒";
                            $li = $('<a href="#myAlert"  data-toggle="modal" ><li><i class="icon-user"></i> <strong>' + data[item].realName + '</strong> <span style="color:orange;font-size:12px;">等待' + leftTime + '</span>' +
                                    '<span title="" class="label label-warning " style="float:right">待面试</span></li></a>');
                            $li.click(function(){
                                editCustomerInfo(data[item].customerId,data[item].realName);
                            });
                            $ul.append($li);
                        }
                    }
                });

            }

            <!--每隔1秒向服务器获取客户信息-->
            window.setInterval(getCustomerInfo, 1000);
        });

    </script>
</head>
<body>
<!--修改客户信息时弹出模态窗口-->
<div class="widget-content" >
    <div id="myAlert" class="modal hide" aria-hidden="true" style="display: none;">
        <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button">×</button>
            <h3>修改客户信息</h3>
        </div>
        <div class="modal-body">
            <p id="welcome_p">客户名称</p>
            所选咨询师:
            <select id="welcome_userName">

            </select>
        </div>
        <div class="modal-footer">
            <a data-dismiss="modal" class="btn btn-primary" href="#">确认修改</a>
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
    </div>
</div>
<!--头部-->
<div id="content-header" style="margin-top:0px;">
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
                    <form action="${pageContextPath.request.contxtPath}/userInfo/add" method="post"
                          id="form-userinfo" class="form-horizontal ui-formwizard">
                        <div class="control-group">
                            <label class="control-label">应聘者名字</label>
                            <div class="controls">
                                <input id="username" type="text" name="realName" style="height:30px;">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">选择所属面试官</label>
                            <div class="controls">
                                <select id="index_select" name="userId">
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <input id="index_button_userinfo" class="btn btn-primary" type="button" value="登记"
                                       style="">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--来访者列表-->
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title">
                        <span class="icon">
                            <i class="icon-signal"></i>
                        </span>
                    <h5>来访者列表</h5>
                    <span title="" style="width:110px;" class="label label-info tip-left"
                          id="index_span_total">0</span>
                    <span title="" style="width:280px;" class="label label-info tip-left"
                          id="index_showTime">当前时间为:</span>
                </div>
                <div class="widget-content">
                    <div class="row-fluid">
                        <div class="span12">
                            <ul class="site-stats" id="index_customer_info">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
