<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/24
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
    <jsp:include page="../common.jsp"></jsp:include>
</head>
<script type="text/javascript">
    var $dept_datagrid;
    $(function () {
        $dept_datagrid = $("#dept_datagrid").datagrid({
            url: '${pageContext.request.contextPath}/dept/deptListData',
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
                field:'deptName',
                title:'',
                width:150
            }, {
                field:'description',
                title:'描述',
                width:150
            },{
                    field:'operate',
                    title:'操作',
                    formatter:function(){
                        var a;
                        a = '<a href="http://www.baidu.com">修改</a>';
                        a = a +"&nbsp;";
                        a =  a +'<a href="#">删除</a>';
                        a = a +"&nbsp;";
                        //a = a+ '<a href="#">分配权限</a>';
                        return a;
                    }
                }
            ]], toolbar: ['-', {
                iconCls: 'icon-add',
                text: '添加部门',
                handler: function () {
                    alert('hello');
                }
            },'-',]
        });
    });
</script>
<body>
<table id="dept_datagrid"></table>
</body>
</html>
