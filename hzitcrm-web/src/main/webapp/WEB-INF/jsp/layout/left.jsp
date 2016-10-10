<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" >
    <ul style="display: block;">
        <c:forEach items="${volist}" var="menu">
            <c:if test="${menu.parentModuleId==0}">
                <li class="${menu.hasChild?'submenu':''}">
                    <a href="${menu.moduleUrl}"><i class="icon ${menu.moduleIco}"></i><span>${menu.name}</span></a>
                    <ul>
                        <c:forEach items="${volist}" var="sub_menu">
                            <c:if test="${menu.moduleId==sub_menu.parentModuleId}"><li><a href="${sub_menu.moduleUrl}">${sub_menu.name}</a></li></c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </c:forEach>
    </ul>
    <%--<ul style="display: block;">--%>
        <%--<li >--%>
            <%--<a href="${pageContext.request}/index"><i class="icon icon-home"></i><span>来访者登记</span></a>--%>
        <%--</li>--%>
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
        </ul>
        <%--<li>--%>
            <%--<a target="index_iframe" href="${pageContext.request.contextPath}/customerInfo/list"><i class="icon icon-pencil"></i>客户列表</a>--%>
        <%--</li>--%>
        <%--<li >--%>
            <%--<a target="index_iframe" href="${pageContext.request.contextPath}/userInfo/customerInfoList"><i class="icon icon-pencil"></i>我的客户</a>--%>
        <%--</li>--%>
        <%--<li><a href="#"><i class="icon icon-tint"></i> <span>客户跟踪</span></a></li>--%>

        <%--<li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Interface elements</span></a></li>--%>

        <%--<li><a href="tables.html"><i class="icon icon-th"></i> <span>Tables</span></a></li>--%>

        <%--<li><a href="grid.html"><i class="icon icon-th-list"></i> <span>Grid Layout</span></a></li>--%>

        <%--<li class="submenu">--%>
            <%--<a href="javasctip:void(0)"><i class="icon icon-file"></i> <span>Sample pages</span> <span class="label">4</span></a>--%>
            <%--<ul>--%>
                <%--<li><a href="invoice.html">Invoice</a></li>--%>
                <%--<li><a href="chat.html">Support chat</a></li>--%>
                <%--<li><a href="calendar.html">Calendar</a></li>--%>
                <%--<li><a href="gallery.html">Gallery</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    <%--</ul>--%>
</div>

