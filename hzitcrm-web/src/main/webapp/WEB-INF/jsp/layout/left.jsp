<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" >
    <a href="#" class="visible-phone"><i class="icon icon-home"></i>手机查看</a>
    <ul style="display: block;">
        <li >
            <a href="${pageContext.request.contextPath}/index"><i class="icon icon-home"></i><span>来访者登记</span></a>
        </li>

        <li>
            <a target="index_iframe" href="${pageContext.request.contextPath}/customerInfo/list"><i class="icon icon-pencil"></i>客户列表</a>
        </li>
        <li >
            <a target="index_iframe" href="${pageContext.request.contextPath}/userInfo/customerInfoList"><i class="icon icon-pencil"></i>我的客户</a>
        </li>
        <li><a target="index_iframe" href="${pageContext.request.contextPath}/customerTrace/list"><i class="icon icon-tint"></i> <span>客户跟进</span></a></li>

        <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Interface elements</span></a></li>

        <li><a href="tables.html"><i class="icon icon-th"></i> <span>Tables</span></a></li>

        <li><a href="grid.html"><i class="icon icon-th-list"></i> <span>Grid Layout</span></a></li>

        <li class="submenu">
            <a href="#"><i class="icon icon-file"></i> <span>Sample pages</span> <span class="label">4</span></a>
            <ul>
                <li><a href="invoice.html">Invoice</a></li>
                <li><a href="chat.html">Support chat</a></li>
                <li><a href="calendar.html">Calendar</a></li>
                <li><a href="gallery.html">Gallery</a></li>
            </ul>
        </li>
    </ul>
</div>

