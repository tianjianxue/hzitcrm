<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/28
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>角色授权</title>
</head>
<body>
<form id="grantRoleForm" method="POST">

    <table style="margin-top:20px;margin-left:22px;border-color:darkgrey;font-family: 微软雅黑" cellspacing="0px;"   border="1">
        <input type="hidden" name="roleId" value="${requestScope.role.roleId}">
        <tr>
            <td style="width:60px;">角色:</td>
            <td colspan="2">
                <input type="text" style="font-size: 20px;text-align: center" class="easyui-textbox"  value="${requestScope.role.roleName}" readonly="readonly">
            </td>
        </tr>
        <c:forEach items="${requestScope.moduleList}" var="module" varStatus="vs">
            <tr>
                <td><span style="color:yellowgreen;">编号:${vs.count}</span></td>
                <td>
                      <span style="color:forestgreen;">
                          <input type="checkbox" name="moduleId" <c:if test="${module.moduleId==13}">checked</c:if> value="${module.moduleId}">${module.name}
                      </span>

                </td>
                <td>
                    <span style="color:darkgreen">${module.description}</span>
                </td>
            </tr>

        </c:forEach>

    </table>
</form>
</body>
</html>
