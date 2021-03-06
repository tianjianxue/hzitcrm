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
            window.setInterval(function(){
                window.location.reload();
            },1000*60*20);//每个20分钟刷新一下 // 1000*60*20
            <!--获取咨询师信息-->
            $.get("${pageContext.request.contextPath}/userInfoList", function (data) {
                var $option;
                var $index_select = $("#index_select");
                for (var item in data){
                    $option = $("<option value='" + data[item].userId + "'></option>");
                    $option.html(data[item].name);
                    $index_select.append($option);
                }
            });
            <!--保存来访者信息-->
            $("#index_button_userinfo").click(function () {
                //表单验证--form-userinfo
                var $formUserInfo = $("#form-userinfo");
                if($("#username").val() ===""){
                    alert('请输入学员姓名!');
                    return false;
                }
                if($("#index_select option:selected").val() == -1){
                    alert('请选择所属面试官!');
                    return false;
                }
               $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/userInfo/add',
                    data: $("#form-userinfo").serialize(),
                    success: function (data) {
                        var show ;
                        var msg;
                        var $showResult = $("#show_result");
                        //$showResult.empty();
                        if(data == "success"){//添加成功
                            show  = "alert alert-success";
                            msg = "学员信息添加成功!"
                        }else if(data == "failed"){//添加失败！
                            show = "alert alert-error";
                            msg = "学员信息添加失败!";
                        }else if(data == "nullValue"){
                            show = "alert alert-error";
                            msg = "请输入学员信息!";
                        }
                        $div = $('<div class="'+show+'"> <button class="close" data-dismiss="alert">x</button> <strong>'+msg+'</strong> </div>');
                        $showResult.append($div);
                        window.setTimeout(function(){
                            $showResult.empty();
                            window.location.reload();
                        },2000);
                    }
                });
            });

            /**
             * 获取当前时间
             * @returns {string}
             */
            function getTime() {
                var today = new Date();
                var s = today.getFullYear() + "年" + today.getMonth() + "月" + today.getDate() + "日" + today.getHours() + "时" + today.getMinutes() + "分" + today.getSeconds() + "秒";
                $("#index_showTime").html(s);
                return s;
            }

            <!--获取要修改学员信息-->
            function editCustomerInfo(){
                alert("hello");
                <!--异步方式获取面试官信息-->

            }
            <!--获取来访学员信息-->
            var $ajaxData = $.ajax({
                    type: 'get',
                    url: '${pageContext.request.contextPath}/customerInfoList',
                    async:false,
                    success: function (data) {
                        var leftTime;//来访时间
                        var $ul = $("#index_customer_info");
                        var $showTime = $("#index_showTime");
                        var $li = null;
                        var currentDate;
                        var hour;
                        var minute;
                        var second;
                        $showTime.empty();
                        var $totalCustomer = $("#index_span_total");
                        $ul.empty();//每次请求服务器时清空当前ul标签下的li标签
                        $totalCustomer.html("当前访客人数:" + data.length);
                        for (var item in data) {
                            $li = $('<li><a href="#myAlert"  id="aAlert'+item+'" data-toggle="modal" ><i class="icon-user"></i> <strong>'
                                    + data[item].realName + '</strong> ');
                            var $spanTime=$('<span id="spanWaitTime'+item+'" style="color:orange;font-size:12px;">等待' + myTime() + '</span>');
                            var $hidden = $('<input type="hidden" id="hiddenDate'+item+'" value="'+data[item].createTime+'">');
                            var $hiddenCustomerId = $('<input type="hidden" id="hiddenCustomerId'+item+'" value="'+data[item].customerId+'"/>');
                            var $hiddenName = $('<input type="hidden" id="hiddenName'+item+'" value="'+data[item].realName+'">');
                            var $hiddenUserId = $('<input type="hidden" id="hiddenUserId'+item+'" value="'+data[item].userId+'"/>');
                            $li.append($spanTime);
                            $li.append($hiddenName);
                            $li.append($hiddenCustomerId);
                            $li.append($hiddenUserId);
                            $li.append($hidden);

                            $spanState = $('<span title="" class="label label-warning " style="float:right">'+data[item].customerStateMsg+'</span></a></li>');
                            $li.append($spanState);
                            $ul.append($li);
                        }
                        //显示时间
                        function myTime(){
                            $showTime.html(getTime());//显示当前时间
                            for(var item in data){
                                currentDate = new Date(Date.parse($("#hiddenDate"+item).val()));
                                hour = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 / 60 / 60);
                                minute = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 / 60 - (hour * 60));
                                second = Math.floor((new Date().getTime() - currentDate.getTime()) / 1000 - (hour * 60 * 60 + minute * 60));
                                leftTime = hour + "小时" + minute + "分钟" + second + "秒";
                                $("#spanWaitTime"+item).html(leftTime);
                            }
                        }
                        window.setInterval(myTime,1000);//每秒执行一次
                        //修改学员所属面试官
                        var item ;
                        for(item in data){
                            $("#aAlert"+item).click(function(){
                                var $index_select = $("#welcome_userName");
                                var name = $("#hiddenName"+item).val();
                                var aIdValue = $(this).attr('id');
                                aIdValue = aIdValue.substr(aIdValue.length-1,aIdValue.length-1);
                                var $userId = $("#hiddenUserId"+aIdValue).val();//获取下拉列表的默认值
                                $("#welcome_p").html("学员名:"+'<span style="color:dodgerblue">'+$("#hiddenName"+aIdValue).val()+'</span>');//用户名
                                $("#hiddenCustomerId").val($("#hiddenCustomerId"+aIdValue).val());
                                $("#agaginChangeUser").val(aIdValue);//用于再次修改所属咨询师时用

                                $.get("${pageContext.request.contextPath}/userInfoList", function (data) {
                                    var $option;
                                    $index_select.empty();
                                    for (var item in data) {
                                        $option = $("<option value='" + data[item].userId + "'></option>");
                                        if($userId == data[item].userId){//该来访者默认所属咨询师
                                            $option= $("<option value='" + data[item].userId + "' selected='selected'></option>");;
                                        }
                                        $option.html(data[item].name);
                                        $index_select.append($option);
                                    }
                                });
                            });
                        }
                    }
                });
            //手动刷新数据
            //ajaxData
            $("#btnRefreshData").click(function(){
               window.location.reload();
            });
        });


        //修改学员所属咨询师
        function changeUser(){
            //用户再次修改所属咨询师时下拉列表的默认信息能够及时改变
            var welcome_userNameValue= $("#welcome_userName option:selected").val();
            $("#hiddenUserId"+$("#agaginChangeUser").val()).val(welcome_userNameValue);
            $.ajax({
                type:"POST",
                url:'${pageContext.request.contextPath}/customerInfo/changeUser',
                data:$("#editCustomerInfoForm").serialize(),
                success:function(data){
                    var msg;
                    var show;
                    if(data== 'true'){
                        show  = "alert alert-success";
                        msg = "修改成功!";
                    }else{
                        show = "alert alert-error";
                        msg = "修改失败!";
                    }
                    $div = $('<div class="'+show+'"> <button class="close" data-dismiss="alert">x</button> <strong>'+msg+'</strong> </div>');
                    $("#show_result").append($div);
                    window.setTimeout(function(){
                        $("#show_result").empty();
                    },3000);
                }
            });
        }

    </script>
    <script>

    </script>
</head>
<body>
<!--修改学员信息时弹出模态窗口-->
<div class="widget-content" >
    <div id="myAlert" class="modal hide" aria-hidden="true" style="display: none;">
        <!--再次修改所属咨询师时用-->
        <input type="hidden" value="" id="agaginChangeUser">
        <form id="editCustomerInfoForm" action="" method="post">
        <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button">×</button>
            <h3>修改学员信息</h3>
        </div>
        <div class="modal-body">
            <p style="font-size: medium" id="welcome_p">学员名称</p>
            <span style="color:dodgerblue;" id="showUserSpan"></span>
            <input type="hidden" name="customerId" id="hiddenCustomerId">
            <span style="font-size: medium">选择其他面试官:</span>
            <select id="welcome_userName" name="userId">

            </select>
        </div>
        <div class="modal-footer">
            <a data-dismiss="modal" onclick="changeUser()" class="btn btn-primary" href="#">确认修改</a>
            <a data-dismiss="modal" class="btn" href="#">取消</a>
        </div>
        </form>
    </div>
</div>
<!--头部-->
<%--<div id="content-header" style="margin-top:0px;">
    <h1>来访者登记</h1>
</div>--%>
<div id="breadcrumb">
    <a href="#" class="tip-bottom" data-original-title="Go to Home"><i class="icon-home"></i>来访者登记</a>
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
                                <input id="username" type="text" name="realName" style="height:30px;" required="required">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">选择所属面试官</label>
                            <div class="controls">
                                <select id="index_select" name="userId">
                                    <option value="-1">请选择面试官!</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                   <input id="index_button_userinfo" class="btn btn-primary " type="button" value="登记" style=""/>
                                   <input id="btnRefreshData" class="btn btn-warning" type="button" value="刷新"/>
                            </div>

                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <!--显示结果-->
                                <span id="show_result">

                            </span>
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
