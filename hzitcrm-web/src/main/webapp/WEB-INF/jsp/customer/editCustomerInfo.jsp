<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/23
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>修改客户信息</title>
</head>
<body>
<form id="customerInfo_form" method="post">
    <input type="hidden" name="customerId" value="${requestScope.customerInfo.customerId}">
    <table cellpadding="5" style="margin-top:10px;">
        <tr>
            <td>客户名</td>
            <td>
                <input class="easyui-textbox" type="text" name="realName" data-options="required:true"
                value="${requestScope.customerInfo.realName}" missingMessage="用户名必填">
            </td>

            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="1" <c:if test="${requestScope.customerInfo.sex==1}">checked</c:if>>男
                <input type="radio" name="sex" value="2" <c:if test="${requestScope.customerInfo.sex==2}">checked</c:if>>女
            </td>

            <td>年龄</td>
            <td>
                <input class="easyui-numberbox" type="text" name="age" data-options="required:true"
                       value="${requestScope.customerInfo.age}" missingMessage="年龄必填(数字)">
            </td>
        </tr>

        <tr>
            <td>籍贯</td>
            <td>
                <input class="easyui-textbox" name="nativePlace" data-options="required:true"
                       value="${requestScope.customerInfo.nativePlace}" missingMessage="籍贯必填">
            </td>
            <td>电话号码</td>
            <td>
                <input type="text" class="easyui-textbox" name="tel" data-options="required:true"
                value="${requestScope.customerInfo.tel}" missingMessage="电话号码必填">
            </td>
            <td>微信号</td>
            <td>
                <input type="text" class="easyui-textbox" name="wechatNo" data-options="required:true"
                       value="${requestScope.customerInfo.wechatNo}" missingMessage="微信号必填">
            </td>
        </tr>

        <tr>
            <td>qq</td>
            <td>
                <input type="text" class="easyui-textbox" name="qq" data-options="required:true"
                       value="${requestScope.customerInfo.qq}" missingMessage="qq号必填">
            </td>
            <td>学历</td>
            <td>
                <select class="easyui-combobox" name="educationBg" style="width:170px;">
                    <option value="1">小学</option>
                    <option value="2">初中</option>
                    <option value="3">高中</option>
                    <option value="4">大专</option>
                    <option value="5">本科</option>
                    <option value="6">研究生</option>
                    <option value="1">其他</option>
                </select>
            </td>
            <td>毕业时间</td>
            <td>
                <input type="text" class="easyui-textbox" name="graduateTime" data-options="required:true"
                       value="${requestScope.customerInfo.graduateTime}" onfocus="WdatePicker({'skin':'default','dateFmt':'yyyy-MM-dd '});">
            </td>
        </tr>

        <tr>
            <td>毕业学校</td>
            <td>
                <input type="text" class="easyui-textbox" name="graduateFrom" data-options="required:true"
                       value="${requestScope.customerInfo.graduateFrom}" missingMessage="毕业学校必填">
            </td>
            <td>专业</td>
            <td>
                <input type="text" class="easyui-textbox" name="majorIn" data-options="required:true"
                       value="${requestScope.customerInfo.majorIn}" missingMessage="专业必填">
            </td>
            <td>工作年限</td>
            <td>
                <input type="text" class="easyui-numberbox" name="workAge" data-options="required:true"
                       value="${requestScope.customerInfo.workAge}" missingMessage="工作年限(数字)">
            </td>
        </tr>

        <tr>
            <td>工作经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="workExperience" data-options="required:true"
                       value="${requestScope.customerInfo.workExperience}" missingMessage="工作经历必填">
            </td>
            <td>从事职业</td>
            <td>
                <input type="text" class="easyui-textbox" name="job" data-options="required:true"
                       value="${requestScope.customerInfo.job}" missingMessage="从事职业必填">
            </td>
            <td>教育培训经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="educateExperience" data-options="required:true"
                       value="${requestScope.customerInfo.educateExperience}"  missingMessage="教育培训经历必填">
            </td>
        </tr>
        <tr>
            <td>应聘渠道</td>
            <td>
                <select class="easyui-combobox" name="recruitChannel" style="width:170px;">
                    <option value="1">智联</option>
                    <option value="2">前程无忧</option>
                    <option value="3">58同城</option>
                    <option value="4">转介绍</option>
                    <option value="5">中华英才</option>
                    <option value="6">其他</option>
                </select>
            </td>

            <td>客户状态</td>
            <td>
                <input type="text" class="easyui-numberbox" name="customerState" data-options="required:true"
                       value="${requestScope.customerInfo.customerState}" missingMessage="客户状态必填(数字)">
            </td>

            <td>客户级别</td>
            <td>
                <input type="text" class="easyui-textbox" name="customerLevel" data-options="required:true"
                       value="${requestScope.customerInfo.customerLevel}" missingMessage="客户级别必填">
            </td>
        </tr>

        <tr>
            <td>咨询师</td>
            <td>
                <select name="userId" class="easyui-combobox" style="width:170px;">
                    <c:forEach items="${requestScope.userInfoList}" var="userInfo">
                            <option value="${userInfo.userId}" <c:if test="${userInfo.userId==customerInfo.userId}">selected</c:if>>${userInfo.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>感兴趣的技能</td>
            <td>
                <input type="text" class="easyui-textbox" name="targetSkill" data-options="required:true"
                       value="${requestScope.customerInfo.targetSkill}" missingMessage="客户感兴趣的技能必填">
            </td>
            <td>推荐人</td>
            <td>
                <input type="text" class="easyui-textbox" name="introducer" data-options="required:true"
                       value="${requestScope.customerInfo.introducer}" missingMessage="推荐人必填">
            </td>
        </tr>

        <tr>
            <td>备注</td>
            <td>
                <input class="easyui-textbox" name="memo" data-options="multiline:true,required:true" style="height:60px"
                       value="${requestScope.customerInfo.memo}"  missingMessage="备注必填">
            </td>
            <td>最后跟进时间</td>
            <td>
                <input type="text" class="easyui-textbox" name="lastTime" data-options="required:true"
                       value="${requestScope.customerInfo.lastTime}" onfocus="WdatePicker({'skin':'default','dateFmt':'yyyy-MM-dd hh:mm:ss'});">
            </td>
            <td>录入时间</td>
            <td>
                <input type="text" class="easyui-textbox"  data-options="required:true"
                       value="${requestScope.customerInfo.createTime}" readonly="readonly">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
