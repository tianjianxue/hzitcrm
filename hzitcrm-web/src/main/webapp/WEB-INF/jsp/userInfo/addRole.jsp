<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/28
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>分配角色</title>
</head>
<body>
<form id="addRoleForm">
    <table style="margin-top:10px;margin-left:5px;">
        <input type="hidden" value="${requestScope.userInfo.userId}" name="userId">
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" class="easyui-textbox" value="${requestScope.userInfo.name}" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td>
                角色
            </td>
            <td>
                <select class="easyui-combobox" name="roleId" style="width:170px;">
                    <c:forEach items="${requestScope.roleList}" var="role" varStatus="vs">
                    <option value="${role.roleId}">${role.roleName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
