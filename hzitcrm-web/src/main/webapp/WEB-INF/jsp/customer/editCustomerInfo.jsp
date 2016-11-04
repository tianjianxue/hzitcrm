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
    <title>修改学员信息</title>
    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.0.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
<form id="customerInfo_form" method="post">
    <input type="hidden" name="customerId" value="${requestScope.customerInfo.customerId}">
    <table cellpadding="2" style="margin-top:10px;">
        <tr>
            <td>学员名</td>
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
                <input type="text" class="easyui-textbox" name="wechatNo" value="${requestScope.customerInfo.wechatNo}">
            </td>
        </tr>

        <tr>
            <td>qq</td>
            <td>
                <input type="text" class="easyui-textbox" name="qq" value="${requestScope.customerInfo.qq}">
            </td>
            <td>学历</td>
            <td>
                <select class="easyui-combobox" name="educationBg" style="width:170px;">
                    <option value="1">小学</option>
                    <option value="2">初中</option>
                    <option value="3">高中</option>
                    <option value="4">中专</option>
                    <option value="5">大专</option>
                    <option value="6">本科</option>
                    <option value="7">研究生</option>
                    <option value="8">其他</option>
                </select>
            </td>
            <td>毕业时间</td>
            <td>
                <input name="graduateTime" type="text" value="${requestScope.customerInfo.graduateTime}" class="easyui-datebox"   required="required" >
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
                <input type="text" class="easyui-textbox" name="majorIn"
                       value="${requestScope.customerInfo.majorIn}" missingMessage="专业必填">
            </td>
            <td>工作年限</td>
            <td>
                <input type="text" class="easyui-numberbox" name="workAge" value="${requestScope.customerInfo.workAge}">
            </td>
        </tr>

        <tr>
            <td>工作经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="workExperience" value="${requestScope.customerInfo.workExperience}" >
            </td>
            <td>从事职业</td>
            <td>
                <input type="text" class="easyui-textbox" name="job" value="${requestScope.customerInfo.job}">
            </td>
            <td>教育培训经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="educateExperience" value="${requestScope.customerInfo.educateExperience}" >
            </td>
        </tr>
        <tr>
            <td>应聘渠道</td>
            <td>
                <select class="easyui-combobox" name="recruitChannel" style="width:170px;">
                    <option value="1" <c:if test="${requestScope.customerInfo.recruitChannel==1}">selected</c:if>>智联</option>
                    <option value="2" <c:if test="${requestScope.customerInfo.recruitChannel==2}">selected</c:if>>前程无忧</option>
                    <option value="3" <c:if test="${requestScope.customerInfo.recruitChannel==3}">selected</c:if>>58同城</option>
                    <option value="4" <c:if test="${requestScope.customerInfo.recruitChannel==4}">selected</c:if>>转介绍</option>
                    <option value="5" <c:if test="${requestScope.customerInfo.recruitChannel==5}">selected</c:if>>中华英才</option>
                    <option value="6" <c:if test="${requestScope.customerInfo.recruitChannel==6}">selected</c:if>>其他</option>
                </select>
            </td>

            <td>学员状态</td>
            <td>
                <select  id="customerState" name="customerState" style="width:170px;">
                    <c:forEach items="${requestScope.customerStateList}" var="customerState">
                        <option value="${customerState.stateId}" <c:if test="${requestScope.customerInfo.customerState==customerState.stateId}">selected</c:if>>${customerState.customerState}</option>
                    </c:forEach>
                </select>
            </td>

            <td>学员级别</td>
            <td>
                <select class="easyui-combobox" name="customerLevel" style="width:170px;">
                    <option value="1" <c:if test="${requestScope.customerInfo.customerLevel==A}">selected</c:if>>A</option>
                    <option value="2" <c:if test="${requestScope.customerInfo.customerLevel==B}">selected</c:if>>B</option>
                    <option value="3" <c:if test="${requestScope.customerInfo.customerLevel==C}">selected</c:if>>C</option>
                    <option value="4" <c:if test="${requestScope.customerInfo.customerLevel==D}">selected</c:if>>D</option>
                </select>
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
                <input type="text" class="easyui-textbox" name="targetSkill" value="${requestScope.customerInfo.targetSkill}" >
            </td>
            <td>邀约人</td>
            <td>
                <select class="easyui-combobox" style="width:170px;" readonly="">
                    <c:forEach items="${requestScope.yaoYueRenList}" var="yaoYueRen">
                        <option value="${yaoYueRen.userId}"
                                <c:if test="${requestScope.customerInfo.introducer==yaoYueRen.name}">selected</c:if>>${yaoYueRen.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>备注</td>
            <td>
                <input class="easyui-textbox" name="memo" data-options="multiline:true" style="height:60px"
                       value="${requestScope.customerInfo.memo}">
            </td>
            <td>最后跟进时间</td>
            <td>
                <input type="text" class="easyui-datebox" readonly="readonly"  name="lastTime" value="${requestScope.customerInfo.lastTime}">
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
