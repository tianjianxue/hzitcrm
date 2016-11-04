<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/22
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>学员列表</title>
    <meta charset="UTF-8"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script>
    </script>
    <script type="text/javascript">
        var $customerInfo_dataGrid;
        $(function() {
            /*查询*/
            var path = '${pageContext.request.contextPath}/customerInfo/ajaxList';
            $customerInfo_dataGrid = $("#customerInfo_datagrid").datagrid({
                    url : path,
                    pagination : true,
                    rownumbers : true,
                    singleSelect : false,
                    selectOnCheck : true,
                    checkOnSelect : true,
                    striped : true,
                    //fitColumns:true,
                    pageSize : 20,
                    loadMsg : "正在拼命加载数据!",
                    fit: true,//在面板中固定
                    pageList: [10, 20, 30, 40, 50, 100],
                    autoRowHeight: false,
                    remoteSort: true,//服务器排序
                    columns: [[{
                        field: "customer_id",
                        title: "学员编号",
                        checkbox: true
                    }, {
                        field: 'realName',
                        title: '学员姓名',
                        width: 80,
                        sortable: true
                    }, {
                        field: 'sex',
                        title: "性别",
                        sortable: true,
                        formatter: function (value,data,index) {
                            var gender;
                            if(data.sex==1){
                                gender ="男";
                            }else if(data.sex==2){
                                gender="女";
                            }else{
                                gender='<span style="color:red;">非法数据</span>';
                            }
                            return gender;
                        }
                    }, {
                        field: "age",
                        title: "年龄",
                        sortable: true
                    }, {
                        field: "nativePlace",
                        title: "籍贯",
                        sortable: true
                    }, {
                        field: "tel",
                        title: "电话号码",
                        sortable: true
                    }, {
                        field: "educationBg",
                        title: "学历",
                        sortable: true,
                        width: 70,
                        formatter:function(value,data,index){
                            //学历[1-小学 2-初中 3-高中 4-大专 5-本科 6-研究生 0-其他]
                            var education;
                            if(data.educationBg == 1){
                                education="小学";
                            }else if(data.educationBg==2){
                                education="初中";
                            }else if(data.educationBg ==3){
                                education ="高中";
                            }else if(data.educationBg==4){
                                education="大专";
                            }else if(data.educationBg ==5){
                                education="本科";
                            }else if(data.educationBg == 6){
                                education=="研究生";
                            }else if(data.educationBg ==0){
                                education="其他";
                            }
                            return education;
                        }

                    }, {
                        field: "graduateTime",
                        title: "毕业时间",
                        sortable: true,
                        width: 80
                    }, {
                        field: "graduateFrom",
                        title: "毕业学校",
                        sortable: true,
                        width: 120
                    }, {
                        field: "majorIn",
                        title: "专业",
                        sortable: true,
                        width: 100
                    },{
                        field: "recruitChannel",
                        title: "应聘渠道",
                        sortable: true,
                        width: 120,
                        formatter: function (value,data,index) {
                            //应聘渠道[1-智联 2-前程无忧 3-58同城 4-转介绍 5-中华英才 6-其他待定]
                            var channel;
                            if(data.recruitChannel==1){
                                channel ="智联";
                            }else if(data.recruitChannel==2){
                                channel="前程无忧";
                            }else if(data.recruitChannel==3){
                                channel="58同城";
                            }else if(data.recruitChannel==4){
                                channel ="转介绍";
                            }else if(data.recruitChannel==5){
                                channel="中华英才网";
                            }else{
                                channel="其他";
                            }
                            return channel;
                        }
                    },{
                        field: "customerLevel",
                        title: "学员级别",
                        sortable: true,
                        width: 70,
                        formatter:function(value,data,index){
                            var customerLevel;
                            if(data.customerLevel==1){
                                customerLevel = "A";
                            }else if(data.customerLevel==2){
                                customerLevel = "B";
                            }else if(data.customerLevel==3){
                                customerLevel = "C";
                            }else if(data.customerLevel==4){
                                customerLevel = "D";
                            }else{
                                customerLevel ='<span style="color:red;">非法数据</span>';
                            }
                            return customerLevel;
                        }
                    }, {
                        field: "name",
                        title: "咨询师",
                        sortable: true,
                        width: 80
                    }, {
                        field: "targetSkill",
                        title: "学员感兴趣的目标技能",
                        sortable: true,
                        width: 150
                    }, {
                        field: "introducer",
                        title: "推荐人",
                        sortable: true,
                        width: 80
                    }, {
                        field: "lastTime",
                        title: "最后跟进时间",
                        sortable: true
                    },{
                        field:'customerStateMsg',
                        title:'学员状态',
                        width:100,
                        formatter:function(value,data,index){
                            var msg = data.customerStateMsg;
                            if(data.customerStateMsg=='待面试'){
                                msg = '<span style="color:red">'+data.customerStateMsg+'</span>';
                            }else if(data.customerStateMsg=='已报名'){
                                msg = '<span style="color:orange">'+data.customerStateMsg+'</span>';
                            }else if(data.customerStateMsg=='已进班'){
                                msg = '<span style="color:#660099">'+data.customerStateMsg+'</span>';
                            }
                            return msg;
                        }
                    }
                    ]], toolbar: ['-', {
                        iconCls: 'icon-tip',
                        text: '学员详情',
                        handler: function () {
                            viewCustomerInfo("查看", "view");
                        }
                    }, '-', {
                        iconCls: 'icon-edit',
                        text: "编辑学员",
                        handler: function () {
                            editCustomerInf("修改", "edit");
                        }
                    },'-', {
                        iconCls: 'icon-reload',
                        text: "清空选择",
                        handler: function () {
                            cancelSearchFun();
                        }
                    }, '-']
                });
        });
    </script>

    <script type="text/javascript">
        function editCustomerInf(msg, edit) {
            var d;
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr = $customerInfo_dataGrid.datagrid('getSelections');
            /*编辑*/
            var path;
            var roleId = '${sessionScope.userinfo.roleId}';
            if(roleId ==2 || roleId ==3){ //2 咨询师  3咨询主管
                path = '${pageContext.request.contextPath}/customer/' + edit + 'CustomerInfoui?customerId=' + customerInfo.customerId;
            }
            if(roleId == 14 || roleId==15){// 14
                path = '${pageContext.request.contextPath}/customerInfo/editIntroducer?customerId='+ customerInfo.customerId;
            }
            if (customerInfoArr.length === 1) {
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href: path,
                    width: 800,
                    height: 430,
                    modal: true,
                    title: msg + '学员信息',
                    buttons: [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var button = this;
                            $('#customerInfo_form').form('submit', {
                                url: '${pageContext.request.contextPath}/customer/' + edit + 'CustomerInfo' + '?date=' + new Date().getTime(),
                                onSubmit : function(){//表单提交时调用
                                     var isValid = $(this).form('validate');//表单验证
                                     if (!isValid){
                                     return isValid;
                                     }else{
                                     $(button).linkbutton('disable');
                                     return isValid;    // 表单验证不通过时不允许提交表单
                                     }
                                 },
                                success: function (result) {//服务器处理成功时
                                    $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                    var obj = jQuery.parseJSON(result);
                                    //服务器处理成功后清空表单数据
                                    if (obj.success) {
                                        $("#customerInfo_datagrid").datagrid('load'),
                                                               editDialog.dialog('destroy');
                                    }
                                    $.messager.show({
                                        title: '提示',
                                        msg: obj.msg,
                                        timeout: 5000,
                                        showType: 'slide'
                                    });

                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    $.messager.show("网络或者其它原因导致的错误!");
                                }
                            });
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            editDialog.dialog('close');//关闭窗口
                        }
                    }]
                }).panel('open').panel('refresh');
            } else if (customerInfoArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要' + msg + '的数据!');
            } else {
                $.messager.alert('警告', '请选择要' + msg + '的数据!');
            }

        }
        <%--查看用户详情--%>
        function viewCustomerInfo(msg, view) {
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr = $customerInfo_dataGrid.datagrid('getSelections');
            if (customerInfoArr.length === 1) {
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/customer/' + view + 'CustomerInfoui?customerId=' + customerInfo.customerId,
                    width: 800,
                    height: 460,
                    modal: true,
                    title: msg + '学员信息',
                    buttons: [{
                        text: '关闭',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            editDialog.dialog('close');//关闭窗口
                        }
                    }]
                }).panel('open').panel('refresh');
            } else if (customerInfoArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要' + msg + '的数据!');
            } else {
                $.messager.alert('警告', '请选择要' + msg + '的数据!');
            }
        }

        $("#comboxQuery").click(function(){
            alert("hello");
        });
        <%--取消选择--%>
        function cancelSelection() {
            $customerInfo_dataGrid.datagrid('clearSelections');
        }
    </script>
    <script type="text/javascript">
        <%-- 导入学员信息--%>
        function importCustomerInfo() {
            $("#fileUpload").dialog('open');
        }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border : false">
<%--搜索--%>
<%--<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;" align="center">
    <form id="customerInfoSearchForm">
        <table class="tableForm">
            <tr>
                <td style="align:right">
                    <select id="comboxQuery" class="easyui-combobox" name="search">
                        <option value="-1">请选择搜索条件</option>
                        <option value="realName">按照学员名称搜索</option>
                        <option value="nativePlace">按照籍贯搜索</option>
                        <option value="tel">按照电话号码搜索</option>
                        <option value="wechartNo">按照微信号搜索</option>
                        <option value="qq">按照qq号搜索</option>
                        <option value="graduateFrom">按照毕业学校搜索</option>
                        <option value="createTime">按创建时间搜索</option>
                        <option value="lastTime">按最后跟进时间搜索</option>
                    </select>
                </td>
                <td>
                    <input type="text" id="queryText" name="queryName" class="easyui-textbox">
                </td>


                <td></td>
                <td>
                    <a href="javascript:void(0);" onclick="queryFun()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                    onclick="cancelSearchFun()">刷新</a>
                </td>
            </tr>
        </table>
    </form>
</div>--%>
<%--搜索--%>
<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;" align="center">
    <form id="customerInfoSearchForm">
        <table class="tableForm">
            <tr>
                <td>
                    <select id="comboxQueryCustomerLevel"  name="customerLevel" style="width:170px;">
                        <option value="-1">----请选择学员级别----</option>
                        <option value="1">A</option>
                        <option value="2">B</option>
                        <option value="3">C</option>
                        <option value="4">D</option>
                    </select>
                </td>
                <td>
                    <select id="comboxQueryCustomerState"  name="customerState" style="width:170px;">
                        <option value="-1">----请选择学员状态----</option>
                        <option value="1">待面试</option>
                        <option value="2">已面试</option>
                        <option value="3">试听</option>
                        <option value="4">有意向</option>
                        <option value="5">已报名</option>
                        <option value="6">已进班</option>
                        <c:if test="${sessionScope.userinfo.roleId==3}">
                            <option value="7">无效</option>
                        </c:if>
                    </select>
                </td>
                <td style="align:right">
                    <select id="comboxQuery"  name="search" style="width:170px;">
                        <option value="-1">----请选择搜索条件----</option>
                        <option value="realName">按照学员名称搜索</option>
                        <option value="nativePlace">按照籍贯搜索</option>
                        <option value="tel">按照电话号码搜索</option>
                        <option value="createTime">按创建时间搜索</option>
                        <option value="lastTime">按最后跟进时间搜索</option>
                    </select>
                </td>
                <td>
                    <input type="text" id="queryText" name="queryName">
                </td>
                <td></td>
                <td>
                    <a href="javascript:void(0);" onclick="queryFun()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                       onclick="cancelSearchFun()">刷新</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',title:'学员列表'">
    <div class="easyui-panel" data-options="fit:true" style="width:100%;height:100%">
        <table id="customerInfo_datagrid">
        </table>
    </div>
</div>

<%--学员信息搜索--%>
<script type="text/javascript">
    var path = "${pageContext.request.contextPath}/customerInfo/ajaxList";
    var customerLevel="";
    var customerState="";
    var url;
    $("#comboxQueryCustomerLevel").change(function(){//学员级别
        customerLevel =  $(this).children('option:selected').val();
        if(customerLevel == -1){
            layer.alert('亲，请选择学员级别!');
            return false;
        }else{
            url = path+"?customerLevel="+customerLevel;
        }
        $customerInfo_dataGrid.datagrid('load',url);
    });
    //获取学员状态改变事件
    $("#comboxQueryCustomerState").change(function(){//学员状态
        customerState =$(this).children('option:selected').val();//这就是selected的值
        if(customerState == -1){
            layer.alert('亲，请选择学员状态!');
            return false;
        }else{
            if(customerLevel.length>0){
                url = path+"?customerLevel="+customerLevel+"&customerState="+customerState;
            }else{
                url = path+"?customerState="+customerState;
            }
        }
        $customerInfo_dataGrid.datagrid('load',url);
    });

    function queryFun(){
        var $comboxQuery = $("#comboxQuery");
        var comboxValue = $("#comboxQuery").children('option:selected').val();
        if(comboxValue==-1){
            $.messager.alert('警告','请选择搜索条件!');
            return false;
        }
        if(customerState.length>0){
            console.log(url);
            url = url+"&"+comboxValue+"="+$("#queryText").val();
        }else{
            url = path+"?"+comboxValue+"="+$("#queryText").val();
        }
        $customerInfo_dataGrid.datagrid('load',url);
    }
    <%--清空查询条件--%>
    function cancelSearchFun() {
        $("#queryText").val('');
        $customerInfo_dataGrid.datagrid('load',path);
    }

</script>
</body>
</html>
