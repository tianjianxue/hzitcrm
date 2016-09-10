<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>我的访客</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="../../jsp/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../jsp/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="../../jsp/css/unicorn.main.css" />
		<link rel="stylesheet" href="../../jsp/css/unicorn.grey.css" class="skin-color" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		%>

	</head>
	<body>
		
		
		<div id="header">
			<h1><a href="./dashboard.html"></a></h1>
		</div>
		<!--顶部的导航栏-->
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">登陆者微信昵称</span></a></li>
                <li class="btn btn-inverse"><a title="" href="../../jsp/login.html"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
            </ul>
        </div>

		<!--左边的菜单栏，预计做成动态菜单-->
		<div id="sidebar">
			<ul>
				<li><a href="consultant.jsp"><i class="icon icon-user"></i> <span>我的咨询者</span></a></li>
			</ul>
		</div>
		

		
		<div id="content">
			<div id="content-header">
				<h1>我的咨询者</h1>
			</div>
			<div id="breadcrumb">
				<a href="#" class="current">咨询者列表</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid"><%--展开填充数据 局部需要修改参考咨询师.jsp @20160830 --%>
							<c:forEach var="customerInfo" items="${customerPageMap.customerInfoList}" varStatus="num">
                            <div class="accordion-group widget-box">
                                <div class="accordion-heading">
									<div class="widget-title" onclick="toGetCustomerRecordList(${customerInfo.customerId})"><%--后台获取访客跟进数据--%>
										<a data-parent="#collapse-group" href="#collapseGOne${num.count}" data-toggle="collapse"  >	<%-- 此处跟展开有关！！ --%>
											<span class="icon"><i class="icon-user"></i></span>
											<h5 >${num.count} ${customerInfo.realName}（ ${customerInfo.tel}），男，19岁，本科，中山大学计算机应用专业。最后跟近时间：${customerInfo.lastTime}</h5>
											<span title="54 total posts" class="label label-info" style="float:right">意向客户</span>
										</a>
									</div>

									<div class="collapse  accordion-body" id="collapseGOne${num.count}">
										<div class="widget-content">
											<ul class="recent-posts">
												<%-- TODO 此处循环访客跟进记录 --%>
												<span id="customerRecordList${customerInfo.customerId}"> </span>

												<li>
													<%-- TODO 此处添加访客跟进内容 --%>
													<form id="addCustomerTraceItem" action="/addCustomerTraceItem" method="post">
														<div class="article-post controls">
															<input type="hidden" name="customerId" value="${customerInfo.customerId}" >
															<textarea name="txtjilu" style="width: 100%"></textarea>
														</div>
														<%-- a标签提交表单 --%>
														<a href="javascript:document:addCustomerTraceItem.submit();" class="btn btn-primary btn-mini">新增跟进记录</a>
													</form>
												</li>

											</ul>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>

                        </div>
					</div>

					<div class="span12">
						<div class="pagination alternate" style="text-align: center">
							<ul>
								<li class="disabled"><a href="#">Prev</a></li><%--上一页--%>
									<c:forEach  begin="1" end="${customerPageMap.totalPage}" varStatus="pagewhat">
										<li><a href="/changePageForCustomerList?pageNo=${pagewhat.count}&consultantId=${1001}" name="pageNo" >${pagewhat.count}
										</a></li>
									</c:forEach>
								<li><a href="#">Next</a></li>				  <%--下一页--%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
            
            <script src="<%=basePath%>jsp/js/jquery.min.js"></script>
            <script src="<%=basePath%>jsp/js/jquery.ui.custom.js"></script>
            <script src="<%=basePath%>jsp/js/bootstrap.min.js"></script>
            <script src="<%=basePath%>jsp/js/unicorn.js"></script>
			<script src="<%=basePath%>jsp/js/consultant.js"></script>
	</body>
</html>
