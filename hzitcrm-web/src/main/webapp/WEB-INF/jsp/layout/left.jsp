<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" >
    <ul style="display: block;">

        <c:forEach items="${requestScope.moduleList}" var="module" varStatus="vs">
            <li>
                <a target="index_iframe" href="${pageContext.request.contextPath}${module.moduleUrl}"><i class="icon icon-pencil"></i>${module.name}</a>
            </li>
        </c:forEach>
    </ul>

    <ul style="display: block;">
        <li>
            <a target="index_iframe" href="${pageContext.request.contextPath}/layout/welcome"><i class="icon icon-pencil"></i>前台登记</a>
        </li>
        <li>
            <a target="index_iframe" href="${pageContext.request.contextPath}/customerInfo/list"><i class="icon icon-pencil"></i>客户列表</a>
        </li>
        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/userInfo/customerInfoList"><i class="icon icon-pencil"></i>我的客户</a>
        </li>
        <li><a target="index_iframe" href="${pageContext.request.contextPath}/customerTrace/list"><i class="icon icon-tint"></i> <span>客户跟进</span></a></li>

        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/userInfo/list"><i class="icon icon-pencil"></i>用户列表</a>
        </li>
        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/role/roleList"><i class="icon icon-pencil"></i>角色列表</a>
        </li>
        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/module/moduleList"><i class="icon icon-pencil"></i>资源列表</a>
        </li>
        <li >
        </li>
        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/dept/deptList"><i class="icon icon-pencil"></i>部门列表</a>
        </li>
        </ul>
</div>

