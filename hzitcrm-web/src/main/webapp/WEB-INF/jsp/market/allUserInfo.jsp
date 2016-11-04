<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/29
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <%--<jsp:include page="../common.jsp"></jsp:include>--%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var $userInfoTable;
        $(function () {
            $userInfoTable = $("#userInfoTable").datagrid({
                url: '${pageContext.request.contextPath}/userInfo/listData',
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
                columns: [[{
                    field: 'name',
                    title: '用户名',
                    width:150
                },{
                    field:'wechatNo',
                    title:'微信号',
                    width:150
                },{
                    field:'tel',
                    title:'电话号码',
                    width:150
                },{
                    field:'deptName',
                    title:'所属部门',
                    width:150
                },{
                    field:'companyName',
                    title:'所属公司',
                    width:250
                },{
                    field:'roleName',
                    title:'所属角色',
                    width:150,
                    formatter:function(value,data,index){
                        var msg;
                        if(data.roleName==null){
                            msg = "<span style='color:red'>*****</span>"
                        }else{
                            msg = data.roleName;
                        }
                        return msg;
                    }
                },{
                    field:'roleId',
                    title:'角色分配情况',
                    width:150,
                    formatter:function(value,data,index){
                        var msg;
                        if(data.roleId==0){
                            msg = "<span style='color:red'>未分配角色</span>";
                        }else{
                            msg = "<span style='color:dodgerblue'>已分配角色</span>";
                        }
                        return msg;
                    }
                }
                ]]
            });
        });
    </script>
    <%--清空查询条件--%>
    <script>
        function cancelSearchFun() {
            //$("#queryText").val('');
            //$userInfoTable.datagrid('reload');//重新加载表格
            //$userInfoTable.datagrid('clearSelections');
            window.location.reload();
        }
    </script>
    <script type="text/javascript">
        function queryFun(){
            //var queryText = $("#queryText").val();
            $userInfoTable.datagrid('load', {
                name:$("#queryText").val()
            });
        }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border : false">
<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;" align="center">
    <form id="userInfoFrom">
        <table class="tableForm">
            <tr>
                <td>员工姓名</td>
                <td>
                    <input type="text" name="name" id="queryText" class="easyui-textbox" required="required"  missingMessage="请输入要查找的员姓名!">
                </td>
                <td>
                    <a href="javascript:void(0);" onclick="queryFun()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                       onclick="cancelSearchFun()">清空</a>
                </td>
            </tr>

        </table>
    </form>
</div>
<div data-options="region:'center',title:'员工列表'">
    <div class="easyui-panel" data-options="fit:true" style="width:100%;height:100%">
        <table  class="easyui-datagrid" id="userInfoTable" height="600px;">
        </table>
    </div>
</div>
</body>
</html>
