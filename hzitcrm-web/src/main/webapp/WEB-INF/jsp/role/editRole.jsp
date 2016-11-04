<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/27
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑角色信息</title>
</head>
<body>
<form id="roleEditForm" method="post">
    <table>
        <input type="hidden" value="${requestScope.role.roleId}" name="roleId">
        <tr>
            <th>角色名称</th>
            <td>
                <input name="roleName" value="${requestScope.role.roleName}" class="easyui-textbox" data-options="required:true" missingMessage="角色名必填"/>
            </td>
        </tr>
        <tr>
            <th>备注</th>
            <td>
                <input name="memo" value="${requestScope.role.memo}" class="easyui-textbox" data-options="required:true" missingMessage="备注必填"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
