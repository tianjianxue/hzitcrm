<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/21
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的客户列表</title>

    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var $customerInfo_dataGrid;
        $(function () {
            $("#fileUpload").dialog('close');//隐藏对话框
            $customerInfo_dataGrid = $("#customerInfo_datagrid").datagrid({
                url: '${pageContext.request.contextPath}/userInfo/customerInfo',
                pagination: true,
                rownumbers: true,
                singleSelect: false,
                selectOnCheck: true,
                checkOnSelect: true,
                striped: true,
                //fitColumns:true,
                pageSize: 20,
                loadMsg: "正在拼命加载数据!",
                fit: true,//在面板中固定
                pageList: [10, 20, 30, 40, 50, 100],
                autoRowHeight: false,
                remoteSort: true,//服务器排序
                columns: [[{
                    field: "customer_id",
                    title: "客户编号",
                    checkbox: true
                }, {
                    field: 'realName',
                    title: '客户姓名',
                    width: 80,
                    sortable: true
                }, {
                    field: 'sex',
                    title: "性别",
                    sortable: true,
                    handler: function () {

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
                    field: "wechatNo",
                    title: "微信号",
                    sortable: true,
                    width: 150
                }, {
                    field: "qq",
                    title: "qq号码",
                    sortable: true,
                    width: 120
                }, {
                    field: "educationBg",
                    title: "学历",
                    sortable: true,
                    width: 70
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
                }, {
                    field: "workAge",
                    title: "工作年限",
                    sortable: true,
                    width: 80
                }, {
                    field: "workExperience",
                    title: "工作经历",
                    sortable: true,
                    width: 120
                }, {
                    field: "job",
                    title: "从事职业",
                    sortable: true,
                    width: 120
                }, {
                    field: "educateExperience",
                    title: "教育培训经历",
                    sortable: true,
                    width: 80
                }, {
                    field: "recruitChannel",
                    title: "应聘渠道",
                    sortable: true,
                    width: 120
                }, {
                    field: "customerLevel",
                    title: "客户级别",
                    sortable: true,
                    width: 120
                }, {
                    field: "userId",
                    title: "咨询师id",
                    sortable: true,
                    width: 80
                }, {
                    field: "targetSkill",
                    title: "客户感兴趣的目标技能",
                    sortable: true,
                    width: 150
                }, {
                    field: "introducer",
                    title: "推荐人",
                    sortable: true,
                    width: 80
                }, {
                    field: "memo",
                    title: "备注",
                    sortable: true
                }, {
                    field: "lastTime",
                    title: "最后跟进时间",
                    sortable: true
                }, {
                    field: "createTime",
                    title: "创建时间",
                    sortable: true
                }

                ]], toolbar: ['-', {
                    iconCls: 'icon-tip',
                    text: '客户详情',
                    handler: function () {
                        alert("查看该客户");
                    }
                }, '-', {
                    iconCls: 'icon-edit',
                    text: "编辑客户",
                    handler: function () {
                        editCustomerInf();
                    }
                }, '-', {
                    iconCls: 'icon-reload',
                    text: "清空选择",
                    handler: function () {
                        cancelSelection();
                    }
                }, '-', {
                    iconCls: 'icon-large-chart',
                    text: '导出',
                    handler: function () {
                        window.location.href = '${pageContext.request.contextPath}/exportCustomerInfo';
                    }
                }, '-', {
                    iconCls: 'icon-large-chart',
                    text: '导入',
                    handler: function () {
                        importCustomerInfo();
                    }
                }, '-']

            });
        });
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border : false">
<%--搜索--%>
<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;" align="center">
    <form id="">
        <table class="tableForm">
            <tr>
                <th style="align:right">客户名</th>
                <td><input name="ralName" type="text" class="easyui-textbox" /></td>
                <th style="align:right">&nbsp;咨询师</th>
                <td>
                    <input name="user_id" type="text" class="easyui-textbox" />
                </td>
                <th style="align:right">&nbsp;手机号码</th>
                <td><input name="tel" type="text" class="easyui-textbox" /></td>
                <th style="align:right">&nbsp;年龄</th>
                <td>
                    <input name="age" type="text" style="width:50px;" class="easyui-textbox" />&nbsp;至
                    <input name="age2" type="text" style="width:50px;" class="easyui-textbox" />
                </td>
                <th style="align:right">&nbsp;录入时间</th>
                <td>
                    <input name="createTime" class="easyui-datebox" />
                </td>
                <th></th>
                <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',title:'客户列表'">
    <div class="easyui-panel" data-options="fit:true" style="width:100%;height:100%">
        <table id="customerInfo_datagrid">
        </table>
    </div>
</div>
<%--文件上传--%>
<div id="fileUpload" data-options="modal:true" class="easyui-dialog" title="报表导入" data-options="iconCls:'icon-save'" style="width:350px;height:170px;padding:10px">
    <form action="${pageContext.request.contextPath}/importCustomreInfo" method="post" enctype="multipart/form-data">
        <div style="margin-top:10px;"></div>
        <div style="margin-bottom:20px">
            <input class="easyui-filebox" name="importCustomerInfo" data-options="prompt:'请选择文件...'" style="width:100%">
        </div>
        <div>
            <a href="#" class="easyui-linkbutton" style="width:100%">Upload</a>
        </div>
    </form>

</div>
</body>
</html>
