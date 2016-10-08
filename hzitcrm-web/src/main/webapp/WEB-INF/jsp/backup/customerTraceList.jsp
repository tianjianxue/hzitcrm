<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/26
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>客户跟进列表</title>
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
            var currentPage = 1;
            var pageSize = 5;
            var userId = "1001";
            //首页
            $("#aBegin").click(function(){
                $("#aNext").linkbutton('enable');//下一页按钮起效
                $("#aLast").linkbutton('disable');//上一页按钮失效
                currentPage = 1;
                $("#spanCurrentPage").html(currentPage);

                //异步加载数据
                $.ajax({
                    type:'POST',
                    url:'${pageContext.request.contextPath}/customerTrace/listData',
                    data:'userId='+userId+'&page='+currentPage+'&pageSize='+pageSize,
                    success:function(data){
                        //console.log(data);
                        //遍历数据
                        for(var item in data){
                            console.log(data[item]);

                        }
                    }
                })

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
            });
        });
    </script>
</head>
<body class="easyui-layout">
<h3>客户跟进记录 || ${requestScope.totalPage}</h3>
<div class="easyui-accordion" style="width:100%;height:90%;" id="customerTraceAccordion">

    <c:forEach items="${requestScope.customerTraceList}" var="customerTrace" varStatus="vs">
        <div id="divShowTitle${vs.count}" title="&nbsp;${customerTrace.realName}(${customerTrace.tel})&nbsp;${customerTrace.sex}&nbsp;${customerTrace.educationBg}
                     &nbsp;${customerTrace.lastTime}"
             data-options="iconCls:'icon-man'" style="overflow:auto;padding:10px;">
            <input type="hidden" id="id${vs.count}" value="${customerTrace.customerId}">

            <span id="span${vs.count}" >

        </span>
            <p style="font-size:16px;font-family: 微软雅黑;color:dodgerblue;">添加跟进记录</p>
            <form method="post" id="form${vs.count}">
                <input type="hidden" name="customerId" value="${customerTrace.customerId}">
                <input type="hidden" name="userId" value="1004">
                    <%--<input  type="text" name="content"   style="width:600px;height:100px;"
                           data-options="multiline:true,required:true" missingMessage="跟进记录必填!">--%>
                <textarea name="content" class="easyui-textbox" data-options="required:true,multiline:true" missingMessage="跟进记录必填!"
                          style="width:100%;height:80px;"></textarea>
                <br/>
                <a href="#" class="easyui-linkbutton" id="linkbutton${vs.count}"  data-options="iconCls:'icon-add'"
                   style="width:80px" onclick="addTraceRecord();">添加</a>
            </form>
        </div>
    </c:forEach>

</div>
<div>
    <a href="#" id="aBegin" class="easyui-linkbutton"  style="width:80px">首页</a>
    <a href="#" id="aLast" class="easyui-linkbutton"  style="width:80px">上一页</a>
    <a href="#" id="aNext" class="easyui-linkbutton"  style="width:80px">下一页</a>
    <a href="#" id="aEnd" class="easyui-linkbutton"  style="width:80px">尾页</a>
    <input type="text"  class="easyui-textbox" data-options="required:true">
    <a href="#" class="easyui-linkbutton"  style="width:80px">跳转到</a>
    <span>当前<span id="spanCurrentPage">1</span>/${requestScope.totalPage}页</span>
</div>
<script>
    $(function(){
        var page = 1;//当前页
        var pageSize = 5;//每页显示5条记录
        var userId = '${requestScope.userId}';

        $.ajax({
            type:'POST',
            url:'${pageContext.request.contextPath}/customerTrace/listData',
            data:'userId='+userId+'&page='+page+'&pageSize='+pageSize,
            success:function(data){
                /*var $accordion = $("#customerTraceAccordion");
                //获取服务器返回的数据
                var $div;
                var $hiddenId;
                var $p;
                var $span;
                var $a;
                var $form;
                for(var item in data){
                    $div = $('<div title="'+data[item].realName+'" data-options="" style="overflow:auto;padding:10px;"></div>');
                    $span = $('<span id="'+item+'"></span>');
                    $div.append($span);
                    $div.appendTo($accordion);
                    $.parser.parse($div);
                }*/
                console.log(data);
            }
        })
    });
</script>
<script>
    $(function(){
        $customerTraceAccordion = $("#customerTraceAccordion");
        $customerTraceAccordion.accordion('getSelected').panel('collapse');//页面加载时accordion展开时
        $customerTraceAccordion.accordion({
            onSelect:function(title,index){
                //获取客户id
                index = index+1;
                var id = "id"+index;
                var $hiddenValue = $("#"+id).val();
                //得到客户id，异步获取跟进记录

                $.ajax({
                    type:'GET',
                    url:'${pageContext.request.contextPath}/customerTrace/traceInfo',
                    data:'customerId='+$hiddenValue+'&userId=1001',
                    success:function (data) {
                        var $span;
                        $span = $("#span"+index);
                        $span.empty();
                        if(data.length > 0){
                            //console.log(data);
                            //获取span id
                            //清空span内容
                            for(var item in data){
                                var $divEle = $('<div class="divTraceRecord"></div>');
                                var $spanUser = $('<span style="margin-left: 25px;line-height: 20px;"></span>');
                                var $spanRecordMsg = $('<span style="color:dodgerblue;margin-left: 25px;"></span>');
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
    //添加跟进记录
    function addTraceRecord(){
        var index;
        var p = $('#customerTraceAccordion').accordion('getSelected');
        if (p){
            index = $('#customerTraceAccordion').accordion('getPanelIndex', p);
        }
        index = index+1;

        //获取文本框对象，校验表单信息
        var $inputText = $("#form"+index+" > input");//得到当前accordion中所有的input标签
        //表单校验
        var $formEle = $("#form"+index);
        //表单验证
        var isValid = $formEle.form('validate');
        if(isValid){
            //表单验证通过时提交表单
            $.ajax({
                type:'POST',
                url:'${pageContext.request.contextPath}/customerTrace/addTraceRecord',
                data: $formEle.serialize(),
                success:function (data) {
                    //$inputText[2].value="";//清空表单
                    alert(data);

                }
            });
        }else{
            $.messager.alert("警告","请输入跟进信息！");
        }
    }
</script>
</body>
</html>
