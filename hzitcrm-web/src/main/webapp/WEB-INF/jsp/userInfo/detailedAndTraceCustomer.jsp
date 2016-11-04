<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/11/2
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>客户详情和客户跟进</title>
    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.0.min.js"></script>
   <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <style type="text/css">
        .divTraceRecord{
            width:100%;
            height:30px;
            font-size:16px;
            font-family: 微软雅黑;
            background-color: #EEEEEE;
            margin-top:1px;
        }
    </style>
</head>
<body>
<div style="margin:0 auto;width:95%;">
    <fieldset>
        <legend>学员信息</legend>
        <table cellpadding="2" style="margin-top:10px;">
            <tr>
                <td style="width:70px;">学员名</td>
                <td>
                    <input  type="text" name="realName" value="${requestScope.customerInfo.realName}"
                            class="easyui-textbox" readonly="readonly">
                </td>
                <td style="width:70px;">性别</td>
                <td>
                    <input type="radio" name="sex" value="1" readonly <c:if test="${requestScope.customerInfo.sex==1}">checked</c:if>>男
                    <input type="radio" name="sex" value="2" readonly <c:if test="${requestScope.customerInfo.sex==2}">checked</c:if>>女
                </td>

                <td style="width:70px;">年龄</td>
                <td>
                    <input type="text" name="age" value="${requestScope.customerInfo.age}" readonly="readonly" class="easyui-textbox">
                </td>
                <td style="width:70px;">籍贯</td>
                <td>
                    <input  name="nativePlace" value="${requestScope.customerInfo.nativePlace}" readonly="readonly" class="easyui-textbox">
                </td>
            </tr>

            <tr>
                <td>电话号码</td>
                <td>
                    <input type="text"  name="tel" value="${requestScope.customerInfo.tel}" readonly="readonly" class="easyui-textbox">
                </td>
                <td>微信号</td>
                <td>
                    <input type="text"  name="wechatNo" value="${requestScope.customerInfo.wechatNo}" readonly="readonly" class="easyui-textbox">
                </td>
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
            </tr>

            <tr>
                <td>毕业时间</td>
                <td>
                    <input type="text" class="easyui-textbox" name="graduateTime"
                           value="${requestScope.customerInfo.graduateTime}" readonly="readonly">
                </td>
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
            </tr>

            <tr>
                <td>学员状态</td>
                <td>
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
            </tr>

            <tr>
                <td>推荐人</td>
                <td>
                    <input type="text" class="easyui-textbox" name="introducer"
                           value="${requestScope.customerInfo.introducer}" readonly="readonly">
                </td>
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
                <td>
                    <span>备注</span>
                </td>
                <td>

                    <input class="easyui-textbox" name="memo" data-options="multiline:true" style="height:70px;width:170px;"
                           value="${requestScope.customerInfo.memo}"  readonly="readonly">
                </td>
            </tr>
        </table>
    </fieldset>
    <div id="customerTraceRecord" class="divTraceRecord" style="height: 100px;width:100%;" >
        <spa style="margin-left:30px;line-height:20px;">暂无记录</spa>
    </div>
    <form method="post" id="traceRecordForm">
        <input type="hidden"  name="userId" value="${sessionScope.userinfo.userId}">
        <input type="hidden"  name="customerId" value="${requestScope.customerInfo.customerId}">
        <textarea name="content"  id="contentTextArea" style="width:100%;height:100px;font-size:18px;" placeholder="请输入客户跟进记录!"></textarea>
        <br/>
        <a href="#" class="easyui-linkbutton"   data-options="iconCls:'icon-add'"
           style="width:90px;margin-top:20px;" onclick="addTraceRecord();">添加</a>
        <a href="#" class="easyui-linkbutton"   data-options="iconCls:'icon-no'"
           style="width:90px;margin-top:20px;" onclick="clearTextArea();">清空</a>
        <a href="#" class="easyui-linkbutton"   data-options="iconCls:'icon-cancel'"
           style="width:90px;margin-top:20px;" onclick="closeCurrentIframe();">关闭</a>
    </form>
</div>

<%--输出最近三天跟进记录--%>
<script type="text/javascript">
    $(function(){//页面加载时获取该咨询师最近3条跟进记录!!
            var $customerTraceRecord = $("#customerTraceRecord");
            $customerTraceRecord.html('');
            $.ajax({
                type:'get',
                url:'${pageContext.request.contextPath}/customerTrace/ThirdCustomerTrace?customerId='+${requestScope.customerInfo.customerId},
                success:function(data){
                    //alert(data.length);
                    if(data.length>0){
                        $customerTraceRecord.html('');//清空内容
                        for(var item in data){
                            var $div = $('<div style="padding-top:10px;"></div>');
                            var $content = $('<span style="margin-left:30px;line-height:20px;">跟进记录:&nbsp;'+data[item].content+'</span>');
                            var $recordTime = $('<span style=" padding-left:100px;line-height:20px;">&nbsp;&nbsp;最后跟进时间:&nbsp;'+data[item].recordDate+'</span><br/>');
                            $div.append($content);
                            $div.append($recordTime);
                            $customerTraceRecord.append($div);
                        }
                    }

                }
            })
    });
    var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    //关闭iframe
    function addTraceRecord(){
        //表单验证
        var $contentTextArea = $("#contentTextArea");
        if($contentTextArea.val().length >0){
            //异步获取数据
            $.ajax({
                method:'post',
                url:'${pageContext.request.contextPath}/customerTrace/addTraceRecord',
                data:$('#traceRecordForm').serialize(),
                success: function(msg){
                    $("#contentTextArea").val('');
                    if(msg.success){//添加成功
                        var $customerTraceRecord = $("#customerTraceRecord");
                        $.ajax({
                            type:'get',
                            url:'${pageContext.request.contextPath}/customerTrace/ThirdCustomerTrace?customerId='+${requestScope.customerInfo.customerId},
                            success:function(data){
                                if(data.length>0){

                                    $customerTraceRecord.html('');//清空内容
                                    for(var item in data){
                                        var $div = $('<div style="padding-top:10px;"></div>');
                                        var $content = $('<span style="margin-left:30px;line-height:20px;">跟进记录:&nbsp;'+data[item].content+'</span>');
                                        var $recordTime = $('<span style=" padding-left:100px;line-height:20px;">&nbsp;&nbsp;最后跟进时间:&nbsp;'+data[item].recordDate+'</span><br/>');
                                        $div.append($content);
                                        $div.append($recordTime);
                                        $customerTraceRecord.append($div);
                                    }
                                }
                            }
                        })
                        layer.open({
                            content: '跟进记录添加成功!您确定是否退出当前界面？',
                            btn: ['确认', '取消'],
                            shadeClose: false,
                            yes: function () {
                                parent.layer.close(index);//关闭层
                            }, no: function () {
                                window.location.reload();
                            }
                        });
                    }else{
                        parent.layer.msg(msg.msg);
                    }
                }
            })
        }else{
            layer.alert('请添加跟进记录!');
        }
    }
    //清空表单
    function clearTextArea(){
        $("#contentTextArea").val('');
    }
    //关闭当前层
    function closeCurrentIframe(){
        parent.layer.close(index);//关闭层
    }
</script>
</body>
</html>
