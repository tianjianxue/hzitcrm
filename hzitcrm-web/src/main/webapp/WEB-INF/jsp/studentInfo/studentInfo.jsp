<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/30
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>学员进班</title>
</head>
<body>
<form id="studentInfoForm" method="post">
    <table style="margin-top:15px;">
        <input type="hidden" value="${requestScope.customerId}" name="customerId">
        <tr>
            <td>
                <label>选择班级</label>
            </td>
            <td>
                <select class="easyui-combobox" name="stedentClass" style="width:170px;" data-options="required:true">
                    <c:forEach items="${requestScope.classinfoList}" var="classinfo" varStatus="vs">
                        <option value="${classinfo.classId}">${classinfo.classname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label>贷款类型</label>
            </td>
            <td>
                <select class="easyui-combobox" name="studentStatus" style="width:170px;" data-options="required:true">
                    <option value="贷贷熊贷款">贷贷熊贷款</option>
                    <option value="一次性付款">一次性付款</option>
                    <option value="Runtime">Runtime</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label>进班时间</label>
            </td>
            <td>
                <input type="text" name="studentintime"  class="easyui-datebox" data-options="required:true"
                       missingMessage="学员贷款时间必填!">
            </td>
        </tr>
        <tr>
            <td>
                <label>备注</label>
            </td>
            <td>
                <input type="text" name="studentdes"  class="easyui-textbox"  data-options="multiline:true" style="height:80px;width:170px;">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
