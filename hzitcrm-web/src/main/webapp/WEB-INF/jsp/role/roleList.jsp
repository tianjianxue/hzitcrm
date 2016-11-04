<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/24
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
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
        var $role_datagrid;
        $(function () {
            $role_dataGrid = $("#role_datagrid").datagrid({
                url: '${pageContext.request.contextPath}/role/roleListData',
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
                    field: "roleName",
                    title: "角色名称",
                    width:250
                }, {
                    field: 'memo',
                    title: '备注',
                    width:250
                }/*,{
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
                }*/
                ]], toolbar: ['-', {
                    iconCls: 'icon-add',
                    text: '添加角色',
                    handler: function () {
                        addFun();
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: "编辑角色",
                    handler: function () {
                        editUserInfo();
                    }
                },'-',{
                    iconCls: 'icon-man',
                    text: "角色授权",
                    handler: function () {
                        grantUserInfo();
                    }
                },'-']
            });
        });
    </script>
    <script type="text/javascript">
        function addFun() {
            $('#roleAddForm input').val('');
            $('#role_addDialog').dialog('open');
        }
    </script>
    <%--编辑角色--%>
    <script type="text/javascript">
        function editUserInfo() {
            var d;
            var role = $role_dataGrid.datagrid('getSelected');
            var roleArr = $role_dataGrid.datagrid('getSelections');
            if (roleArr.length === 1) {
                var editDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/role/editRoleui?roleId='+role.roleId,
                    width: 300,
                    height: 150,
                    modal: true,
                    title:'修改角色信息',
                    buttons: [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var button = this;
                            $('#roleEditForm').form('submit', {
                                url: '${pageContext.request.contextPath}/role/editRole',
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
                                        $("#role_datagrid").datagrid('load'),
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
            }else if (roleArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要修改的数据!');
            } else {
                $.messager.alert('警告', '请选择要修改的数据!');
            }

        }
    </script>
    <%--角色授权--%>
    <script type="text/javascript">
        function grantUserInfo(){
            var d;
            var role = $role_dataGrid.datagrid('getSelected');
            var roleArr = $role_dataGrid.datagrid('getSelections');
            if (roleArr.length === 1) {
                var grantDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/role/grantRole?roleId='+role.roleId,
                    width: 650,
                    height: 470,
                    modal: true,
                    title:'角色授权',
                    buttons: [{
                        text: '授权',
                        iconCls: 'icon-man',
                        handler: function () {
                            var button = this;
                            $('#grantRoleForm').form('submit', {
                                url:'${pageContext.request.contextPath}/role/grantRole2',
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
                                        $("#role_datagrid").datagrid('load');
                                        grantDialog.dialog('destroy');
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
                            grantDialog.dialog('close');//关闭窗口
                        }
                    }]
                }).panel('open').panel('refresh');
            }else if (roleArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要授权的数据!');
            } else {
                $.messager.alert('警告', '请选择要授权的数据!');
            }
        }
    </script>
</head>
<body>
<table id="role_datagrid">
</table>
<%--<div data-options="region:'center',title:'角色列表'">
    <div class="easyui-panel" data-options="fit:true" style="width:100%;height:100%">

    </div>
</div>--%>

<!--添加角色-->
<div id="role_addDialog" class="easyui-dialog"
     data-options="closed:true,
                    modal:false,
                    title:'添加角色',
                    buttons:[{
				    text : '增加',
				    iconCls : 'icon-add',
				    handler : function() {
					$('#roleAddForm').form('submit', {
						url : '${pageContext.request.contextPath}/role/add',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#role_datagrid').datagrid('load');

								$('#role_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]" style="width: 300px;height:150px;" align="center">
    <%--添加表单--%>
    <form id="roleAddForm" method="post">
        <table>

            <tr>
                <th>角色名称</th>
                <td>
                    <input name="roleName" class="easyui-textbox" data-options="required:true" missingMessage="角色名必填"/>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <input name="memo" class="easyui-textbox" data-options="required:true" missingMessage="备注必填"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
