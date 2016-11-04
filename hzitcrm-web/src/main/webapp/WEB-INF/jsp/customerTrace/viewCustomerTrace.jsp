<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/31
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>咨询师跟进详情</title>
</head>
<body>
<div style="margin:20px auto;text-align: center;font-size: 20px;">

    <table cellspacing="0px;" cellpadding="5px;" style="border-color:dodgerblue;color:dodgerblue;font-size: 18px;width:600px;margin-left:30px;" border="1">
        <tr>
            <td>编号</td>
            <td>跟进记录</td>
            <td>最后跟进时间</td>
        </tr>
        <c:forEach items="${requestScope.customerTraceRecordList}" var="customerTraceRecord" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${customerTraceRecord.content}</td>
                <td>${customerTraceRecord.recordDate}</td>
            </tr>

        </c:forEach>
    </table>

        <form id="viewCustomerTraceRecord" method="post">
            <input type="hidden" name="customerId" value="${requestScope.customerId}">
            <label>添加跟进记录</label>
            <input class="easyui-textbox" type="text" name="content" data-options="required:true" missingMessage="跟进记录必填">
        </form>
</div>
</body>
</html>
