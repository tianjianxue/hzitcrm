<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/31
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>查看学员报名</title>
</head>
<body>
<div style="margin-left: auto;margin-right:auto;text-align: center;font-size: 20px;">
    总报名人数:<span style="color:dodgerblue">${requestScope.total}人</span>
</div>
<table border="1" cellspacing="0px;" cellpadding="5px;" style="margin-left:35px;width:500px;color:dodgerblue;border-color: dodgerblue">
    <tr>
        <td>
            编号
        </td>
        <td>
            学员名称
        </td>
        <td>
            状态
        </td>
    </tr>
    <c:forEach items="${requestScope.customerInfoList}" var="customerInfo" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${customerInfo.realName}</td>
            <td>已报名</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
