<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/23
  Time: 13:41
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
                    field:'companyName',
                    title:'所属公司',
                    width:250
                },{
                    field:'deptName',
                    title:'所属部门',
                    width:150
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
                ]], toolbar: ['-', {
                    iconCls: 'icon-add',
                    text: '添加员工',
                    handler: function () {
                        addUserInfoFun();
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: "编辑员工",
                    handler: function () {
                        editUserInfo();
                    }
                },'-',{
                    iconCls: 'icon-remove',
                    text: "重置密码",
                    handler: function () {
                        resetPassword();
                    }
                },'-',{
                    iconCls: 'icon-man',
                    text: "分配角色",
                    handler: function () {
                        //editUserInfo();
                        addRole();
                    }
                },'-']
            });
        });
    </script>
    <script type="text/javascript">

    </script>
    <script type="text/javascript">
        function addFun() {
            $('#admin_itsb_addForm input').val('');
            $('#userInfo_addDialog').dialog('open');
        }
    </script>
    <%--修改--%>
    <script type="text/javascript">
        function editUserInfo() {
            var d;
            var userInfo = $userInfoTable.datagrid('getSelected');
            var userInfoArr = $userInfoTable.datagrid('getSelections');
            if (userInfoArr.length === 1) {
                var editDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/userInfo/editui?userId='+userInfo.userId,
                    width: 300,
                    height: 240,
                    modal: true,
                    title:'修改员工信息',
                    buttons: [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var button = this;
                            $('#editUserInfoFrom').form('submit', {
                                url: '${pageContext.request.contextPath}/userInfo/edit',
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
                                    //$.messager.progress('close');	// 如果提交成功则隐藏进度条
                                    $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                    var obj = jQuery.parseJSON(result);
                                    //服务器处理成功后清空表单数据
                                    if (obj.success) {
                                        $("#userInfoTable").datagrid('load'),
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
            }else if (userInfoArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要修改的数据!');
            } else {
                $.messager.alert('警告', '请选择要修改的数据!');
            }

        }
    </script>
    <%--删除--%>
    <script type="text/javascript">
       function removeUserInfo(){
           var d;
           var userInfo = $userInfoTable.datagrid('getSelected');
           var userInfoArr = $userInfoTable.datagrid('getSelections');
           if (userInfoArr.length === 1) {
                //window.location.href="${pageContext.request.contextPath}/userInfo/removeUserInfo?userId="+userInfo.userId;
               $.ajax({
                   type:"get",
                   url:'${pageContext.request.contextPath}/userInfo/removeUserInfo?userId='+userInfo.userId,
                   success:function(data){
                       $.messager.show('警告',data);
                   }
               })
           }else if (userInfoArr.length >= 2) {
               $.messager.alert('警告', '所选数据大于1条，请选择一条要修改的数据!');
           } else {
               $.messager.alert('警告', '请选择要修改的数据!');
           }
       }
    </script>

    <%--添加员工--%>
    <script type="text/javascript">
        function addUserInfoFun(){
            var addDialog = $('<div/>').dialog({
                href: '${pageContext.request.contextPath}/userInfo/addui',
                width: 300,
                height: 240,
                modal: true,
                title:'添加员工信息',
                buttons: [{
                    text: '添加',
                    iconCls: 'icon-edit',
                    handler: function () {
                        var button = this;
                        $('#addUserInfoFrom').form('submit', {
                            url: '${pageContext.request.contextPath}/userInfo/addUserInfo',
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
                                //$.messager.progress('close');	// 如果提交成功则隐藏进度条
                                $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                var obj = jQuery.parseJSON(result);
                                //服务器处理成功后清空表单数据
                                if (obj.success) {
                                    $("#userInfoTable").datagrid('load'),
                                            addDialog.dialog('destroy');
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
                        addDialog.dialog('close');//关闭窗口
                    }
                }]
            }).panel('open').panel('refresh');
        }

    </script>
    <%--分配角色--%>
    <script type="text/javascript">
        function addRole() {
            var d;
            var userInfo = $userInfoTable.datagrid('getSelected');
            var userInfoArr = $userInfoTable.datagrid('getSelections');
            if (userInfoArr.length === 1) {
                var addRoleDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/userInfo/addRoleUI?userId='+userInfo.userId,
                    width: 260,
                    height: 170,
                    modal: true,
                    title:'分配角色',
                    buttons: [{
                        text: '分配',
                        iconCls: 'icon-man',
                        handler: function () {
                            var button = this;
                            $('#addRoleForm').form('submit', {
                                url: '${pageContext.request.contextPath}/userInfo/addRole',
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
                                    //$.messager.progress('close');	// 如果提交成功则隐藏进度条
                                    $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                    var obj = jQuery.parseJSON(result);
                                    //服务器处理成功后清空表单数据
                                    if (obj.success) {
                                        $("#userInfoTable").datagrid('load');
                                        addRoleDialog.dialog('destroy');
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
                            addRoleDialog.dialog('close');//关闭窗口
                        }
                    }]
                }).panel('open').panel('refresh');
            }else if (userInfoArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要分配角色的数据!');
            } else {
                $.messager.alert('警告', '请选择要分配角色的数据!');
            }

        }
    </script>
    <%--重置密码--%>
    <script type="text/javascript">
        function resetPassword(){
            var result =  window.confirm("是否要为该员工重置密码!");
            if(result == true){
                var userInfo = $userInfoTable.datagrid('getSelected');
                var userInfoArr = $userInfoTable.datagrid('getSelections');
                if (userInfoArr.length === 1) {
                    $.ajax({
                        type:'get',
                        url:'${pageContext.request.contextPath}/userInfo/resetPassword?userId='+userInfo.userId,
                        success:function(data) {
                            if(data=='true'){
                                $.messager.show({
                                    title:'消息',
                                    msg:'密码重置成功!',
                                    timeout:5000,
                                    showType:'slide'
                                });

                            }else{
                                $.messager.show({
                                    title:'消息',
                                    msg:'密码重置失败!',
                                    timeout:5000,
                                    showType:'slide'
                                });
                            }
                        }
                    });
                }else if(userInfoArr.length >= 2){
                    $.messager.alert('警告', '所选数据大于1条，请选择一条要分配角色的数据!');
                }else{
                    $.messager.alert('警告', '请选择要分配角色的数据!');
                }
            }

        }
    </script>
    <%--清空查询条件--%>
    <script>
        function cancelSearchFun() {
            $("#queryText").val('');
            //$userInfoTable.datagrid('load',{});//重新加载表格
            //$userInfoTable.datagrid('clearSelections');
            window.location.reload();//页面刷新
        }
    </script>
    <script type="text/javascript">
        function queryFun(){
            var queryText = $("#queryText").val();
            /*$userInfoTable.datagrid('load', {
             name:$("#queryText").val()
             });*/
            var url = "${pageContext.request.contextPath}/userInfo/listData?name="+queryText;
            $userInfoTable.datagrid('load',url);
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

<!--添加员工-->
<div id="userInfo_addDialog" class="easyui-dialog"
     data-options="closed:true,
                    modal:false,
                    title:'添加员工',
                    buttons:[{
				    text : '增加',
				    iconCls : 'icon-add',
				    handler : function() {
					$('#userInfo_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userInfo/addUserInfo',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#userInfoTable').datagrid('load');

								$('#userInfo_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]" style="width: 300px;height:180px;" align="center">
</div>
</body>
</html>
