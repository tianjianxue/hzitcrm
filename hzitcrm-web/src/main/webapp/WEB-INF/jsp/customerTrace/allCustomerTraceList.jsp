<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/29
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>学员跟进列表</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <style>
        .divTraceRecord{
            width:100%;
            height:45px;
            font-size:16px;
            font-family: 微软雅黑;
            background-color: #EEEEEE;
            margin-top:1px;
        }
    </style>
    <script>
        //数据分页
        $(function(){
            var currentPage = '${requestScope.currentPage}';
            var totalPage = '${requestScope.totalPage}';
            totalPage = parseInt(totalPage);
            currentPage = parseInt(currentPage);
            var pageSize = 5;
            var userId = "1001";
            if(currentPage == 1){
                $("#aLast").linkbutton('disable');//上一页按钮失效
            }
            if(totalPage == currentPage){
                $("#aNext").linkbutton('disable');//下一页按钮失效
            }
            //首页
            $("#aBegin").click(function(){
                $("#aNext").linkbutton('enable');//下一页按钮起效
                $("#aLast").linkbutton('disable');//上一页按钮失效
                currentPage = 1;
                $("#spanCurrentPage").html(currentPage);
                goto();
            });
            //上一页
            $("#aLast").click(function(){
                if(currentPage >1){
                    currentPage = currentPage-1;
                    if(currentPage ==1){
                        $("#aLast").linkbutton('disable');
                    }
                    $("#aNext").linkbutton('enable');
                    $("#spanCurrentPage").html(currentPage);
                    goto();
                }

            });
            //下一页
            $("#aNext").click(function(){
                if(currentPage < '${requestScope.totalPage}'){
                    currentPage = currentPage+1;
                    if(currentPage == '${requestScope.totalPage}'){
                        $("#aNext").linkbutton('disable');
                    }
                    $("#aLast").linkbutton('enable');
                    $("#spanCurrentPage").html(currentPage);
                    goto();
                }else{
                    $("#aEnd").attr("disabled",true);
                }

            });
            //尾页
            $("#aEnd").click(function(){
                currentPage = '${requestScope.totalPage}';
                $("#aLast").linkbutton('enable');//上一页按钮启用
                $("#aNext").linkbutton('disable');//下一页按钮失效
                $("#spanCurrentPage").html(currentPage);
                goto();
            });

            $("#aGoTo").click(function(){
                var $showMsg = $("#showMsg");
                //获取分页数据
                var $textGoTo = $("#textGoTo");
                var match = $textGoTo.val().match(/^\d+$/);
                if(match == null){
                    //不是数字
                    $showMsg.html('请输入数字!');
                    return false;
                }
                if($textGoTo.val() <1){
                    //$showMsg.css('display',"block");
                    $showMsg.html('数字超出范围!');
                    return false;
                }else if($textGoTo.val() > totalPage){
                    //$showMsg.css('display',"block");
                    $showMsg.html('数字超出范围!');
                    return false;
                }
                currentPage = $textGoTo.val();
                goto();
            });

            function goto(){
                window.location.href='${pageContext.request.contextPath}/customerTrace/list' +
                        '?userId='+userId+'&page='+currentPage+'&pageSize='+pageSize;
            }
            $("#aGoTo").mouseout(function(){

            });
        });
    </script>
</head>
<body class="easyui-layout" background="${pageContext.request.contextPath}/assets/img/customerTraceBg.gif">
<a href="#" onclick="window.history.go(-1)" style="margin-left:15px;" class="easyui-linkbutton" data-options="iconCls:'icon-back'">后退</a>
<span style="margin-left:auto;margin-right: auto;text-align:center">学员跟进记录</span>
<div class="easyui-accordion" style="width:100%;height:90%" id="customerTraceAccordion" >
    <c:forEach items="${requestScope.customerTraceList}" var="customerTrace" varStatus="vs">
        <div id="accordionDiv${vs.count}" title="&nbsp;${customerTrace.realName}(${customerTrace.tel})&nbsp;性别:${customerTrace.sexMsg}&nbsp;${customerTrace.educationBg}
                     ,最后跟进时间:${customerTrace.lastTime}&nbsp;跟进情况:${customerTrace.customerStateMsg}"
             data-options="iconCls:'icon-man'" style="overflow:auto;padding:10px;">
            <input type="hidden" id="id${vs.count}" value="${customerTrace.customerId}">

            <span id="span${vs.count}" >

            </span>
        </div>
    </c:forEach>

</div>
<div>
    <a href="#" id="aBegin" class="easyui-linkbutton"  style="width:80px">首页</a>
    <a href="#" id="aLast" class="easyui-linkbutton"  style="width:80px">上一页</a>
    <a href="#" id="aNext" class="easyui-linkbutton"  style="width:80px">下一页</a>
    <a href="#" id="aEnd" class="easyui-linkbutton"  style="width:80px">尾页</a>
    <input type="text"  class="easyui-textbox" id="textGoTo" data-options="required:true"
           missingMessage="请输入数字!">
    <a href="#" class="easyui-linkbutton" id="aGoTo"  style="width:80px">跳转到</a>
    <span>当前${requestScope.currentPage}/${requestScope.totalPage}页</span>
    <span id="showMsg" style="color:red"></span>
</div>
<script>
    $(function(){
        $customerTraceAccordion = $("#customerTraceAccordion");
        $customerTraceAccordion.accordion('getSelected').panel('collapse');//页面加载时accordion展开时
        $customerTraceAccordion.accordion({
            onSelect:function(title,index){
                //获取学员id
                index = index+1;
                var id = "id"+index;
                var $hiddenValue = $("#"+id).val();
                //得到学员id，异步获取跟进记录

                $.ajax({
                    type:'GET',
                    url:'${pageContext.request.contextPath}/customerTrace/traceInfo',
                    data:'customerId='+$hiddenValue+'&userId=1001',
                    success:function (data) {
                        var $span;
                        $span = $("#span"+index);
                        $span.empty();
                        if(data.length > 0){
                            //获取span id
                            //清空span内容
                            for(var item in data){
                                var $divEle = $('<div class="divTraceRecord"></div>');
                                var $spanUser = $('<span style="margin-left: 25px;line-height: 20px;"></span>');
                                var $spanRecordMsg = $('<span style="color:dodgerblue;margin-left: 25px;"></span>');
                                //var $spanCustomerState = $('<span></span>');
                                $spanUser.html(data[item].recordDate+"&nbsp;&nbsp;&nbsp;"+data[item].userName);//面试官
                                $spanRecordMsg.html(data[item].content);//跟进信息
                                $divEle.append($spanUser);
                                $divEle.append($('<br/>'));
                                $divEle.append($spanRecordMsg);
                                $span.append($divEle);
                            }
                        }else{
                            $span.append($('<div class="divTraceRecord"><span style="margin-left:25px;line-height: 45px;color: dodgerblue">暂无跟进记录!</span></div>'));
                        }

                    }
                });
            }
        });
    });
</script>
</body>
</html>

