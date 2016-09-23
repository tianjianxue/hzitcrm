<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/22
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>客户列表</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/uniform.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.grey.css" class="skin-color" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>



<div id="content">

    <div id="breadcrumb">
        <a href="${pageContext.request.contextPath}/index" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
        <a href="#" class="current">客户列表</a>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title">
                        <h5>客户列表</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered data-table">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>客户名</th>
                                <th>性别</th>
                                <th>籍贯</th>
                                <th>手机号</th>
                                <th>微信</th>
                                <th>qq</th>
                                <th>学历</th>
                                <th>毕业时间</th>
                                <th>毕业学校</th>

                                <th>专业</th>
                                <th>工作年限</th>
                                <th>工作经历</th>
                                <th>从事职业</th>
                                <th>培训经历</th>
                                <th>应聘渠道</th>
                                <th>客户状态</th>
                                <th>客户级别</th>
                                <th>咨询师</th>
                                <th>目标技能</th>

                                <th>推荐人</th>
                                <th>备注</th>
                                <th>最后跟进时间</th>
                                <th>创建时间</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.customerInfoList}" var="customerInfo" varStatus="vs">
                                    <tr>
                                        <td>${vs.count}</td>
                                        <td>${customerInfo.realName}</td>
                                        <td>${customerInfo.sex}</td>
                                        <td>${customerInfo.nativePlace}</td>
                                        <td>${customerInfo.tel}</td>
                                        <td>${customerInfo.wechatNo}</td>
                                        <td>${customerInfo.qq}</td>
                                        <td>${customerInfo.educationBg}</td>
                                        <td>${customerInfo.graduateTime}</td>
                                        <td>${customerInfo.graduateFrom}</td>

                                        <td>${customerInfo.majorIn}</td>
                                        <td>${customerInfo.workAge}</td>
                                        <td>${customerInfo.workExperience}</td>
                                        <td>${customerInfo.job}</td>
                                        <td>${customerInfo.educateExperience}</td>
                                        <td>${customerInfo.recruitChannel}</td>
                                        <td>${customerInfo.customerState}</td>
                                        <td>${customerInfo.customerLevel}</td>
                                        <td>${customerInfo.userId}</td>
                                        <td>${customerInfo.targetSkill}</td>

                                        <td>${customerInfo.introducer}</td>
                                        <td>${customerInfo.memo}</td>
                                        <td>${customerInfo.lastTime}</td>
                                        <td>${customerInfo.createTime}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="row-fluid">
            <div id="footer" class="span12">
                2016 &copy; <a href="#">合众艾特信息技术有限公司</a>
            </div>
        </div>
    </div>
</div>



<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/unicorn.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/unicorn.tables.js"></script>
</body>
</html>
