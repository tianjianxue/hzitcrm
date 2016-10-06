<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/21
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>客户录入</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="/assets/css/unicorn.main.css" />
<link rel="stylesheet" href="/assets/css/unicorn.grey.css" class="skin-color" />
<script src="${pageContext.request.contextPath}/assets/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/unicorn.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
<%--引入日期组件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/datepicker/WdatePicker.js"></script>
<body>



<div id="content">

    <div id="breadcrumb">
        <a href="${pageContext.request.contextPath}/index" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
        <a href="#">录入客户信息</a>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/editCustomerInfo"  name="number_validate" id="number_validate" novalidate="novalidate" />
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>
								</span>
                        <h5>当前客户信息</h5>
                        <span class="label label-important">客户信息</span>
                    </div>
                    <div class="widget-content nopadding">

                        <div class="control-group">
                            <label class="control-label">客户真实姓名</label>
                            <div class="controls">
                                <input type="text" name="realName" id="realName" />
                                <span for="required" generated="true" class="help-inline">该字段必填</span>
                            </div>

                        </div>
                       <div class="control-group">
                            <label class="control-label">性别</label>
                            <div class="controls">
                                    <input type="radio" value="1" name="sex" checked="checked">男
                                    <input type="radio" value="2" name="sex">女
                                    <input type="radio" value="3" name="sex">保密
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">年龄</label>
                            <div class="controls">
                                <input type="text" name="age" id="age" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">籍贯</label>
                            <div class="controls">
                                <input type="text" name="nativePlace" id="nativePlace" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">电话号码</label>
                            <div class="controls">
                                <input type="text" name="tel" id="tel" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">微信号码</label>
                            <div class="controls">
                                <input type="text" name="wechatNo" id="wechatNo" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">qq</label>
                            <div class="controls">
                                <input type="text" name="qq" id="qq" />
                            </div>
                        </div>
                        <%--学历[1-小学 2-初中 3-高中 4-大专 5-本科 6-研究生 0-其他]--%>
                        <div class="control-group">
                            <label class="control-label">教育背景</label>
                            <div class="controls">
                                <select name="educationBg">
                                    <option value="1">小学</option>
                                    <option value="2">初中</option>
                                    <option value="3">高中</option>
                                    <option value="4">大专</option>
                                    <option value="5">本科</option>
                                    <option value="6">研究生</option>
                                    <option value="0">其他</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">入学时间</label>
                           <div class="controls">
                               <input type="text" name="graduateTime" readonly="readonly"
                                      onfocus="WdatePicker({'skin':'default','dateFmt':'yyyy-MM-dd'});" >
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">毕业院校</label>
                            <div class="controls">
                                <input type="text" name="graduateFrom" id="graduateFrom" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">专业</label>
                            <div class="controls">
                                <input type="text" name="majorIn" id="majorIn" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">工作时间</label>
                            <div class="controls">
                                <input type="text" name="workAge" id="workAge" />
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="submit" value="录入" class="btn btn-primary" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="span6">
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <div class="control-group">
                            <label class="control-label">工作经验</label>
                            <div class="controls">
                                <input type="text" name="workExperience" id="workExperience" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">工作</label>
                            <div class="controls">
                                <input type="text" name="job" id="job" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">教育培训经历</label>
                            <div class="controls">
                                <input type="text" name="educateExperience" id="educateExperience" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">应聘渠道</label>
                            <div class="controls">
                                <input type="text" name="recruitChannel" id="recruitChannel" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">客户状态</label>
                            <div class="controls">
                                <input type="text" name="customerState" id="customerState" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">客户级别</label>
                            <div class="controls">
                                <input type="text" name="customerLevel" id="customerLevel" />
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">所属咨询师</label>
                            <div class="controls">
                                <input type="text" name="userId" id="userId" />
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">客户感兴趣的目标技能</label>
                            <div class="controls">
                                <input type="text" name="targetSkill" id="targetSkill" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">推荐人</label>
                            <div class="controls">
                                <input type="text" name="introducer" id="introducer" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">备注</label>
                            <div class="controls">
                                <textarea name="memo" rows="5"></textarea>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">最后跟进时间</label>
                            <div class="controls">
                                <input type="text" name="lastTime" readonly="readonly"
                                       onfocus="WdatePicker({'skin':'default','dateFmt':'yyyy-MM-dd'});" >
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            </form>

        </div>

        <div class="row-fluid">
            <div id="footer" class="span12">
                2016 &copy;  <a href="#">合众信息技术有限公司</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
