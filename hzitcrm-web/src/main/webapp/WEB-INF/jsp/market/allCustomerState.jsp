<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/10/29
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已报名学员</title>
    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var $customerInfo_dataGrid;
        $(function () {
            $("#fileUpload").dialog('close');//隐藏对话框
            $customerInfo_dataGrid = $("#customerInfo_datagrid").datagrid({
                url: '${pageContext.request.contextPath}/userInfo/allCustomerStateList',
                pagination: true,
                rownumbers: true,
                singleSelect: false,
                selectOnCheck: true,
                checkOnSelect: true,
                striped: true,
                pageSize: 20,
                loadMsg: "正在拼命加载数据!",
                fit: true,//在面板中固定
                pageList: [10, 20, 30, 40, 50, 100],
                autoRowHeight: false,
                remoteSort: true,//服务器排序
                columns: [[{
                    field: "customer_id",
                    title: "学员编号",
                    checkbox: true
                }, {
                    field: 'realName',
                    title: '学员姓名',
                    width: 80,
                    sortable: true
                }, {
                    field: 'sex',
                    title: "性别",
                    sortable: true,
                    formatter: function (value,data,index) {
                        var gender;
                        if(data.sex==1){
                            gender ="男";
                        }else if(data.sex==2){
                            gender="女";
                        }else{
                            gender = "非法数据";
                        }
                        return gender;
                    }
                }, {
                    field: "age",
                    title: "年龄",
                    sortable: true
                }, {
                    field: "nativePlace",
                    title: "籍贯",
                    sortable: true
                }, {
                    field: "tel",
                    title: "电话号码",
                    sortable: true
                }, {
                    field: "educationBg",
                    title: "学历",
                    sortable: true,
                    width: 70,
                    formatter:function(value,data,index){
                        //学历[1-小学 2-初中 3-高中 4-大专 5-本科 6-研究生 0-其他]
                        var education;
                        if(data.educationBg == 1){
                            education="小学";
                        }else if(data.educationBg==2){
                            education="初中";
                        }else if(data.educationBg ==3){
                            education ="高中";
                        }else if(data.educationBg==4){
                            education="大专";
                        }else if(data.educationBg ==5){
                            education="本科";
                        }else if(data.educationBg == 6){
                            education=="研究生";
                        }else if(data.educationBg ==0){
                            education="其他";
                        }
                        return education;
                    }
                }, {
                    field: "job",
                    title: "从事职业",
                    sortable: true,
                    width: 120
                }, {
                    field: "educateExperience",
                    title: "教育培训经历",
                    sortable: true,
                    width: 80
                }, {
                    field: "customerLevel",
                    title: "学员级别",
                    sortable: true,
                    width: 120,
                    formatter:function(value,data,index){
                        var customerLevel;
                        if(data.customerLevel==1){
                            customerLevel = "A";
                        }else if(data.customerLevel==2){
                            customerLevel = "B";
                        }else if(data.customerLevel==3){
                            customerLevel = "C";
                        }else if(data.customerLevel==4){
                            customerLevel = "D";
                        }else{
                            customerLevel = "非法数据";
                        }
                        return customerLevel;
                    }

                }, {
                    field: "targetSkill",
                    title: "学员感兴趣的目标技能",
                    sortable: true,
                    width: 150
                },{
                    field:'customerStateMsg',
                    title:'学员状态',
                    width:100
                },{
                    field:'companyName',
                    title:'所在报名公司',
                    width:100
                }
                ]], toolbar: ['-', {
                    iconCls: 'icon-tip',
                    text: '学员详情',
                    handler: function () {
                        viewCustomerInfo("查看", "view");
                    }
                },"-",{
                    iconCls: 'icon-reload',
                    text: "清空选择",
                    handler: function () {
                        cancelSelection();
                    }
                },'-' ]

            });
        });
    </script>
    <script>
        <%--查看用户详情--%>
        function viewCustomerInfo(msg, view) {
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr = $customerInfo_dataGrid.datagrid('getSelections');
            if (customerInfoArr.length === 1) {
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/customer/' + view + 'CustomerInfoui?customerId=' + customerInfo.customerId,
                    width: 800,
                    height: 430,
                    modal: true,
                    title: msg + '学员信息',
                    buttons: [{
                        text: '关闭',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            editDialog.dialog('close');//关闭窗口
                        }
                    }]
                }).panel('open').panel('refresh');
            } else if (customerInfoArr.length >= 2) {
                $.messager.alert('警告', '所选数据大于1条，请选择一条要' + msg + '的数据!');
            } else {
                $.messager.alert('警告', '请选择要' + msg + '的数据!');
            }
        }
        <%--取消选择--%>
        function cancelSelection(){
            $customerInfo_dataGrid.datagrid('clearSelections');
        }

    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border : false">
<%--搜索--%>
<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;font-size:16px;" align="center">
    <form id="" >
        <table class="tableForm" style="font-size: 16px;">
            <tr>
                <td style="align:right">
                    <select id="comboxQuery" class="easyui-combobox" name="search" style="width:170px;">
                        <option value="-1">请选择搜索条件</option>
                        <option value="realName">按照学员名称搜索</option>
                        <option value="nativePlace">按照籍贯搜索</option>
                        <option value="tel">按照电话号码搜索</option>
                    </select>
                </td>
                <td>
                    <input type="text" id="queryText" name="queryName" class="easyui-textbox">
                </td>
                <td></td>
                <td>
                    <a href="javascript:void(0);" onclick="queryFun()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" onclick="cancelSearchFun()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',title:'学员列表'">
    <div class="easyui-panel" data-options="fit:true" style="width:100%;height:100%">
        <table id="customerInfo_datagrid">
        </table>
    </div>
</div>
<script type="text/javascript">
    /*模糊查询*/
    function queryFun(){
        var $comboxQuery = $("#comboxQuery");
        var comboxValue = $comboxQuery.combobox('getValue');
        if(comboxValue==-1){
            $.messager.alert('警告','请选择搜索条件!');
            return false;
        }
        var url = "${pageContext.request.contextPath}/userInfo/allCustomerStateList?"+comboxValue+"="+$("#queryText").val()+"&currentUser=1";
        $customerInfo_dataGrid.datagrid('load',url);
    }
    <%--清空查询条件--%>
    function cancelSearchFun() {
        window.location.reload();
    }
</script>
</body>
</html>