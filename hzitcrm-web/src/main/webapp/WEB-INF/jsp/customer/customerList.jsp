<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/22
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>客户列表</title>
    <meta charset="UTF-8" />

    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
<script type="text/javascript">
    $(function(){
        $("#customerInfo_datagrid").datagrid({
            url:'${pageContext.request.contextPath}/customerInfo/ajaxList',
            pagination:true,
            rownumbers:true,
            singleSelect: false,
            selectOnCheck: true,
            checkOnSelect: true,
            striped:true,
            //fitColumns:true,
            pageSize:20,
            loadMsg:"正在拼命加载数据!",
            fit:true,//在面板中固定
            pageList: [10, 20, 30, 40, 50,100],
            autoRowHeight:false,
            remoteSort:true,//服务器排序
            columns:[[
                {
                    field:'realName',
                    title:'客户姓名',
                    width:80,
                    sortable:true
                },{
                    field:'sex',
                    title:"性别",
                    sortable:true
                },{
                    field:"age",
                    title:"年龄",
                    sortable:true
                },{
                    field:"nativePlace",
                    title:"籍贯",
                    sortable:true
                },{
                    field:"tel",
                    title:"电话号码",
                    sortable:true
                },{
                    field:"wechatNo",
                    title:"微信号",
                    sortable:true,
                    width:150
                },{
                    field:"qq",
                    title:"qq号码",
                    sortable:true,
                    width:120
                },{
                    field:"educationBg",
                    title:"学历",
                    sortable:true,
                    width:70
                },{
                    field:"graduateTime",
                    title:"毕业时间",
                    sortable:true,
                    width:80
                },{
                    field:"graduateFrom",
                    title:"毕业学校",
                    sortable:true,
                    width:120
                },{
                    field:"majorIn",
                    title:"专业",
                    sortable:true,
                    width:100
                },{
                    field:"workAge",
                    title:"工作年限",
                    sortable:true,
                    width:80
                },{
                    field:"workExperience",
                    title:"工作经历",
                    sortable:true,
                    width:120
                },{
                    field:"job",
                    title:"从事职业",
                    sortable:true,
                    width:120
                },{
                    field:"educateExperience",
                    title:"教育培训经历",
                    sortable:true,
                    width:80
                },{
                    field:"recruitChannel",
                    title:"应聘渠道",
                    sortable:true,
                    width:120
                },{
                    field:"customerLevel",
                    title:"客户级别",
                    sortable:true,
                    width:120
                },{
                    field:"userId",
                    title:"咨询师id",
                    sortable:true,
                    width:120
                },{
                    field:"targetSkill",
                    title:"客户感兴趣的目标技能",
                    sortable:true,
                    width:120
                },{
                    field:"introducer",
                    title:"推荐人",
                    sortable:true,
                    width:120
                },{
                    field:"memo",
                    title:"备注",
                    sortable:true
                },{
                    field:"lastTime",
                    title:"最后跟进时间",
                    sortable:true
                },{
                    field:"createTime",
                    title:"创建时间",
                    sortable:true
                }

            ]]
        }) ;
    });
</script>
</head>
<body class="easyui-layout">
<div class="easyui-panel" style="width:100%;height:100%">
    <table id="customerInfo_datagrid">
    </table>
</div>
</body>
</body>
</html>
