<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
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
		<jsp:include page="/menu"></jsp:include>

		<div id="content">
			<div id="content-header">
				<h1>我的咨询者</h1>
			</div>
			<div id="breadcrumb">
				<a href="#" class="current">咨询者列表   ${list}</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="accordion" id="collapse-group">


					<% for(int i=0;i<10;i++) {%>
                            <div class="accordion-group widget-box">
                                <div class="accordion-heading">
									<div class="widget-title">
										<a data-parent="#collapse-group" href="#collapseGOne<%=i%>" data-toggle="collapse" >
											<span class="icon"><i class="icon-user" onclick="alert(00)"></i></span><h5 ><%=i%>李志强（13423712915），男，19岁，本科，中山大学计算机应用专业。最后跟近时间：2015-9-9日</h5>
											<span title="54 total posts" class="label label-info" style="float:right">意向客户</span>
										</a>
									</div>
								</div>
                                <div class="collapse  accordion-body" id="collapseGOne<%=i%>">
                                    <div class="widget-content">
										<ul class="recent-posts">
											<%for(int j=0;j<5;j++){%>
											<li>
												<div class="article-post">
													<span class="user-info">:2016-09-2:09:10:李春梅</span>
													<p>
														<a href="javascript:void(0)"><%=j%>明天对比一下华育，朋友想明天来学</a>
													</p>
												</div>
											</li>
											<%}%>

											<li>
												<form>
												<div class="article-post controls">
														<textarea name="txtjilu" style="width: 100%"></textarea>
												</div>
													<a href="#" class="btn btn-primary btn-mini">新增跟进记录</a>
												</form>
											</li>

										</ul>
                                    </div>
                                </div>
                            </div>
					<%}%>



                        </div>
					</div>

					<div class="span12">
						<div class="pagination alternate" style="text-align: center">
							<ul>
								<li class="disabled"><a href="#">Prev</a></li>
								<li class="active">
									<a href="#">1</a>
								</li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">6</a></li>
								<li><a href="#">Next</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
            
            <script src="../../jsp/js/jquery.min.js"></script>
            <script src="../../jsp/js/jquery.ui.custom.js"></script>
            <script src="../../jsp/js/bootstrap.min.js"></script>
            <script src="../../jsp/js/unicorn.js"></script>
	</body>
</html>
