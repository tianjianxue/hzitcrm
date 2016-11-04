<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/24
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模块列表</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var $module_datagrid;
        $(function () {
            $module_datagrid = $("#module_datagrid").datagrid({
                url: '${pageContext.request.contextPath}/module/moduleListData',
                pagination: true,
                rownumbers: true,
                singleSelect: true,
                selectOnCheck: true,
                checkOnSelect: true,
                striped: true,
                pageSize: 20,
                loadMsg: "正在拼命加载数据!",
                fit: true,//在面板中固定
                pageList: [10, 20, 30, 40, 50, 100],
                autoRowHeight: false,
                remoteSort: true,//服务器排序
                columns: [[{
                    field: "name",
                    title: "模块名称",
                    width:250
                }, {
                    field: 'moduleUrl',
                    title: '模块的url',
                    width:250
                },{
                    field:'operate',
                    title:'操作',
                    formatter:function(){
                        var a;
                        a = '<a href="http://www.baidu.com">修改</a>';
                        a = a +"&nbsp;";
                        a =  a +'<a href="#">删除</a>';
                        a = a +"&nbsp;";
                        a = a+ '<a href="#">分配权限</a>';
                        return a;
                    }
                }
                ]]
            });
        });
    </script>
</head>
<body>
<table id="module_datagrid" height="600px;"></table>--%>
</body>
</html>
