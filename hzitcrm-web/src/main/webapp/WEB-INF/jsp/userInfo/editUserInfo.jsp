<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/27
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改员工信息</title>
</head>
<body>
<form id="editUserInfoFrom" method="post">
    <table>
        <input type="hidden" value="${requestScope.userInfo.userId}" name="userId">
        <tr>
            <td>员工名字</td>
            <td>
                <input name="name" class="easyui-textbox" value="${requestScope.userInfo.name}" data-options="required:true" missingMessage="员工姓名必填"/>
            </td>
        </tr>
        <tr>
            <td>微信号</td>
            <td>
                <input name="wechatNo" class="easyui-textbox" value="${requestScope.userInfo.wechatNo}" data-options="required:true" missingMessage="微信号必填"/>
            </td>
        </tr>
        <tr>
            <td>电话号码</td>
            <td>
                <input name="tel" class="easyui-textbox" value="${requestScope.userInfo.tel}" data-options="required:true" missingMessage="电话号码必填"/>
            </td>
        </tr>
        <tr>
            <td>公司</td>
            <td>
                <select name="companyId"  class="easyui-combobox" style="width:178px;">
                    <c:forEach items="${requestScope.companyList}" var="company" varStatus="vs">
                        <option value="${company.companyId}" <c:if test="${requestScope.userInfo.companyId==company.companyId}">selected</c:if>>${company.companyName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>部门</td>
            <td>
                <select name="deptId" class="easyui-combobox" style="width:178px;">
                    <c:forEach items="${requestScope.deptList}" var="dept" varStatus="vs">
                        <option value="${dept.id}" <c:if test="${requestScope.userInfo.deptId==dept.id}">selected</c:if>>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
