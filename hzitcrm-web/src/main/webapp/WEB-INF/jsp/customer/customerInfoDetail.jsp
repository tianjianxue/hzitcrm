<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/29
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>学员详情页</title>
</head>
<body>
    <input type="hidden" name="customerId" value="${requestScope.customerInfo.customerId}">
    <table cellpadding="2" style="margin-top:10px;">
        <tr>
            <td>学员名</td>
            <td>
                <input  type="text" name="realName" value="${requestScope.customerInfo.realName}"
                        class="easyui-textbox" readonly="readonly">
            </td>

            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="1" readonly <c:if test="${requestScope.customerInfo.sex==1}">checked</c:if>>男
                <input type="radio" name="sex" value="2" readonly <c:if test="${requestScope.customerInfo.sex==2}">checked</c:if>>女
            </td>

            <td>年龄</td>
            <td>
                <input type="text" name="age" value="${requestScope.customerInfo.age}" readonly="readonly" class="easyui-textbox">
            </td>
        </tr>

        <tr>
            <td>籍贯</td>
            <td>
                <input  name="nativePlace" value="${requestScope.customerInfo.nativePlace}" readonly="readonly" class="easyui-textbox">
            </td>
            <td>电话号码</td>
            <td>
                <input type="text"  name="tel" value="${requestScope.customerInfo.tel}" readonly="readonly" class="easyui-textbox">
            </td>
            <td>微信号</td>
            <td>
                <input type="text"  name="wechatNo" value="${requestScope.customerInfo.wechatNo}" readonly="readonly" class="easyui-textbox">
            </td>
        </tr>

        <tr>
            <td>qq</td>
            <td>
                <input type="text"  name="qq" value="${requestScope.customerInfo.qq}" readonly="readonly" class="easyui-textbox">
            </td>
            <td>学历</td>
            <td>
                <select class="easyui-combobox" class="easyui-textbox" name="educationBg" style="width:170px;" readonly >
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
                <input type="text" class="easyui-textbox" name="graduateTime"
                       value="${requestScope.customerInfo.graduateTime}" readonly="readonly">
            </td>
        </tr>

        <tr>
            <td>毕业学校</td>
            <td>
                <input type="text" class="easyui-textbox" name="graduateFrom"
                       value="${requestScope.customerInfo.graduateFrom}" readonly="readonly">
            </td>
            <td>专业</td>
            <td>
                <input type="text" class="easyui-textbox" name="majorIn"
                       value="${requestScope.customerInfo.majorIn}" readonly="readonly">
            </td>
            <td>工作年限</td>
            <td>
                <input type="text" class="easyui-numberbox" name="workAge"
                       value="${requestScope.customerInfo.workAge}" readonly="readonly">
            </td>
        </tr>

        <tr>
            <td>工作经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="workExperience"
                       value="${requestScope.customerInfo.workExperience}" readonly="readonly">
            </td>
            <td>从事职业</td>
            <td>
                <input type="text" class="easyui-textbox" name="job"
                       value="${requestScope.customerInfo.job}" readonly="readonly">
            </td>
            <td>教育培训经历</td>
            <td>
                <input type="text" class="easyui-textbox" name="educateExperience"
                       value="${requestScope.customerInfo.educateExperience}"  readonly="readonly">
            </td>
        </tr>
        <tr>
            <td>应聘渠道</td>
            <td>
                <select class="easyui-combobox" name="recruitChannel" style="width:170px;" readonly>
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
                <%--<input type="text" class="easyui-numberbox" name="customerState"
                       value="${requestScope.customerInfo.customerState}" readonly="readonly">--%>
                <select class="easyui-combobox" name="customerState" style="width:170px;" readonly>
                    <option value="1" <c:if test="${requestScope.customerInfo.customerState==1}">selected</c:if>>有意向</option>
                    <option value="2" <c:if test="${requestScope.customerInfo.customerState==2}">selected</c:if>>试听</option>
                    <option value="3" <c:if test="${requestScope.customerInfo.customerState==3}">selected</c:if>>已报名</option>
                    <option value="4" <c:if test="${requestScope.customerInfo.customerState==4}">selected</c:if>>已就业</option>
                    <option value="5" <c:if test="${requestScope.customerInfo.customerState==5}">selected</c:if>>无效</option>
                </select>
            </td>

            <td>学员级别</td>
            <td>
                <%--<input type="text" class="easyui-textbox" name="customerLevel"
                       value="${requestScope.customerInfo.customerLevel}" readonly="readonly">--%>
                <select class="easyui-combobox" name="customerLevel" style="width:170px;" readonly>
                    <option value="1" <c:if test="${requestScope.customerInfo.customerLevel==1}">selected</c:if>>A</option>
                    <option value="2" <c:if test="${requestScope.customerInfo.customerLevel==2}">selected</c:if>>B</option>
                    <option value="3" <c:if test="${requestScope.customerInfo.customerLevel==3}">selected</c:if>>C</option>
                    <option value="4" <c:if test="${requestScope.customerInfo.customerLevel==4}">selected</c:if>>D</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>咨询师</td>
            <td>
                <select name="userId" class="easyui-combobox" style="width:170px;" readonly>
                    <c:forEach items="${requestScope.userInfoList}" var="userInfo">
                        <option value="${userInfo.userId}" <c:if test="${userInfo.userId==customerInfo.userId}">selected</c:if>>${userInfo.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>感兴趣的技能</td>
            <td>
                <input type="text" class="easyui-textbox" name="targetSkill"
                       value="${requestScope.customerInfo.targetSkill}" readonly="readonly">
            </td>
            <td>邀约人</td>
            <td>
                <input type="text" class="easyui-textbox" name="introducer"
                       value="${requestScope.customerInfo.introducer}" readonly="readonly">
            </td>
        </tr>

        <tr>

            <td>最后跟进时间</td>
            <td>
                <input type="text" class="easyui-textbox" name="lastTime"
                       value="${requestScope.customerInfo.lastTime}" readonly="readonly">
            </td>
            <td>录入时间</td>
            <td>
                <input type="text" class="easyui-textbox"  data-options="required:true"
                       value="${requestScope.customerInfo.createTime}" readonly="readonly">
            </td>
        </tr>
        <tr >
            <td>
                <span>备注</span>
            </td>
            <td>

                <input class="easyui-textbox" name="memo" data-options="multiline:true" style="height:70px;width:170px;"
                       value="${requestScope.customerInfo.memo}"  readonly="readonly">
            </td>
        </tr>
    </table>
</body>
</html>
