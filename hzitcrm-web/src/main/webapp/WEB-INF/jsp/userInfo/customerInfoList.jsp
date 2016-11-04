<%--
  Created by IntelliJ IDEA.
  User: 冼耀基
  Date: 2016/9/21
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的学员列表</title>
    <!--easyui-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/bootstrap/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/easyui/css/themes/icon.css">
    <!--easyui-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.0.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js" ></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/syUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script type="text/javascript">
        var $customerInfo_dataGrid;
        $(function () {
            $("#fileUpload").dialog('close');//隐藏对话框
            $customerInfo_dataGrid = $("#customerInfo_datagrid").datagrid({
                url: '${pageContext.request.contextPath}/userInfo/customerInfo',
                pagination: true,
                rownumbers: true,
                singleSelect: false,
                selectOnCheck: true,
                checkOnSelect: true,
                striped: true,
                //fitColumns:true,
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
                    width:80,
                    sortable: true,
                    formatter: function (value,data,index) {
                        var gender;
                        if(data.sex==1){
                            gender ="男";
                        }else if(data.sex==2){
                            gender="女";
                        }else{
                            gender = '<span style="color:red;">非法数据</span>';
                        }
                        return gender;
                    }
                }, {
                    field: "age",
                    title: "年龄",
                    width:80,
                    sortable: true
                }, {
                    field: "nativePlace",
                    title: "籍贯",
                    width:200,
                    sortable: true
                }, {
                    field: "tel",
                    title: "电话号码",
                    width:80,
                    sortable: true
                }, {
                    field: "educationBg",
                    title: "学历",
                    sortable: true,
                    width: 70,
                    formatter:function(value,data,index){
                        //学历[1-小学 2-初中 3-高中,4-中专 5-大专 6-本科 7-研究生 0-其他]
                        var education;
                        if(data.educationBg == 1){
                            education="小学";
                        }else if(data.educationBg == 2){
                            education="初中";
                        }else if(data.educationBg == 3){
                            education ="高中";
                        }else if(data.educationBg == 4){
                            ducation ="中专";
                        }else if(data.educationBg == 5){
                            education="大专";
                        }else if(data.educationBg == 6){
                            education="本科";
                        }else if(data.educationBg == 7){
                            education=="研究生";
                        }else if(data.educationBg == 0){
                            education="其他";
                        }
                        return education;
                    }
                }, {
                    field: "graduateTime",
                    title: "毕业时间",
                    sortable: true,
                    width: 100
                }, {
                    field: "graduateFrom",
                    title: "毕业学校",
                    sortable: true,
                    width: 120
                }, {
                    field: "majorIn",
                    title: "专业",
                    sortable: true,
                    width: 100
                }, {
                    field: "recruitChannel",
                    title: "应聘渠道",
                    sortable: true,
                    width: 120,
                    formatter: function (value,data,index) {
                        //应聘渠道[1-智联 2-前程无忧 3-58同城 4-转介绍 5-中华英才 6-其他待定]
                        var channel;
                        if(data.recruitChannel==1){
                            channel ="智联";
                        }else if(data.recruitChannel==2){
                            channel="前程无忧";
                        }else if(data.recruitChannel==3){
                            channel="58同城";
                        }else if(data.recruitChannel==4){
                            channel ="转介绍";
                        }else if(data.recruitChannel==5){
                            channel="中华英才网";
                        }else if(data.recruitChannel ==0){
                            channel="其他";
                        }else{
                            channel='<span style="color:red;">非法数据</span>';
                        }
                        return channel;
                    }
                }, {
                    field: "customerLevel",
                    title: "学员级别",
                    sortable: true,
                    width: 70,
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
                            customerLevel = '<span style="color:red;">非法数据</span>';
                        }
                        return customerLevel;
                    }

                }, {
                    field: "targetSkill",
                    title: "学员感兴趣的目标技能",
                    sortable: true,
                    width: 150
                }, {
                    field: "lastTime",
                    title: "最后跟进时间",
                    width:100,
                    sortable: true
                },{
                    field:'customerStateMsg',
                    title:'学员状态',
                    width:100,
                    formatter:function(value,data,index){
                        var msg = data.customerStateMsg;
                        if(data.customerStateMsg=='待面试'){
                            msg = '<span style="color:red">'+data.customerStateMsg+'</span>';
                        }else if(data.customerStateMsg=='已报名'){
                            msg = '<span style="color:orange">'+data.customerStateMsg+'</span>';
                        }else if(data.customerStateMsg=='已进班'){
                            msg = '<span style="color:#660099">'+data.customerStateMsg+'</span>';
                        }
                        return msg;
                    }

                }
                ]], toolbar: ['-', {
                    iconCls: 'icon-tip',
                    text: '学员详情',
                    handler: function () {
                        viewCustomerInfo("查看", "view");
                    }
                }, '-', {
                    iconCls: 'icon-edit',
                    text: "编辑学员",
                    handler: function () {
                        editCustomerInf();
                    }
                },'-',{
                    iconCls:'icon-man',
                    text:'面试学员',
                    handler: function(){
                        //customerState();
                        customerStateReview();
                    }
                },'-',{
                    iconCls: 'icon-man',
                    text: '学员跟进',
                    handler: function () {
                        customerInfoTrace();
                    }
                },'-',{
                    iconCls:'icon-man',
                    text:'学员跟进列表',
                    handler:function () {
                        customerInfoTraceList();
                    }
                },'-',{
                    iconCls: 'icon-reload',
                    text: "清空选择",
                    handler: function () {
                        cancelSelection();
                    }
                },'-',{
                    iconCls:'',
                    text:'学员跟进',
                    handler:function(){
                        newCustomerInfoTrace();
                    }
                }]

            });
        });
    </script>
    <script>
        /*编辑信息*/
        function editCustomerInf(){
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr =  $customerInfo_dataGrid.datagrid('getSelections');
            if(customerInfoArr.length == 1){
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href : '${pageContext.request.contextPath}/customer/editCustomerInfoui?customerId='+customerInfo.customerId,
                    width : 800,
                    height : 430,
                    modal : true,
                    cache:false,
                    title : '修改学员信息',
                    buttons : [ {
                        text : '编辑',
                        iconCls : 'icon-edit',
                        handler : function() {
                            var button = this;
                            $('#customerInfo_form').form('submit', {
                                url : '${pageContext.request.contextPath}/customer/editCustomerInfo'+'?date='+new Date().getTime(),
                                onSubmit : function(){//表单提交时调用
                                    var result= $('#customerState option:selected').val();
                                    if(result==7){
                                        return confirm("是否要把该学员的状态设为无效！该数据只有主管以上级别才能看!");
                                    }
                                     var isValid = $(this).form('validate');//表单验证
                                     if (!isValid){
                                        return isValid;
                                     }else{
                                        $(button).linkbutton('disable');
                                        return isValid;    // 表单验证不通过时不允许提交表单
                                     }

                                 },
                                success : function(result) {//服务器处理成功时
                                    $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                    var obj = jQuery.parseJSON(result);
                                    //服务器处理成功后清空表单数据
                                    if (obj.success) {
                                        $("#customerInfo_datagrid").datagrid('load'),
                                        editDialog.dialog('destroy');
                                    }
                                    $.messager.show({
                                        title : '提示',
                                        msg : obj.msg,
                                        timeout:5000,
                                        showType:'slide'
                                    });
                                },
                                error:function(XMLHttpRequest, textStatus, errorThrown) {
                                    $.messager.show("网络或者其它原因导致的错误!");
                                }
                            });
                        }
                    },{
                        text:'取消',
                        iconCls:'icon-cancel',
                        handler:function(){
                            editDialog.dialog('close');//关闭窗口
                        }
                    } ]
                })
            }else if(customerInfoArr.length >= 2){
                $.messager.alert('警告','所选数据大于1条，请选择一条要修改的数据!');
            }else{
                $.messager.alert('警告','请选择要修改的数据!');
            }
        }
        <%--查看用户详情--%>
        function viewCustomerInfo(msg, view) {
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr = $customerInfo_dataGrid.datagrid('getSelections');
            if (customerInfoArr.length === 1) {
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href: '${pageContext.request.contextPath}/customer/' + view + 'CustomerInfoui?customerId=' + customerInfo.customerId,
                    width: 800,
                    height: 460,
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
        <%--学员跟进列表--%>
        function customerInfoTraceList(){
            window.location.href='${pageContext.request.contextPath}/customerTrace/list';
        }
        /*学员跟进*/
        function customerInfoTrace(){
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr =  $customerInfo_dataGrid.datagrid('getSelections');
            if(customerInfoArr.length == 1){
                $('#customerInfo_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                var editDialog = $('<div/>').dialog({
                    href : '${pageContext.request.contextPath}/customerTrace/viewCustomerTrace?customerId='+customerInfo.customerId,
                    width : 800,
                    height : 450,
                    modal : true,
                    cache:false,
                    title : '添加跟进记录',
                    buttons : [ {
                        text : '跟进',
                        iconCls : 'icon-add',
                        handler : function() {
                            var button = this;
                            $('#viewCustomerTraceRecord').form('submit', {
                                url : '${pageContext.request.contextPath}/customerTrace/addTraceRecord',
                                onSubmit : function(){//表单提交时调用
                                    var isValid = $(this).form('validate');//表单验证
                                    if (!isValid){
                                        return isValid;
                                    }else{
                                        $(button).linkbutton('disable');
                                        return isValid;    // 表单验证不通过时不允许提交表单
                                    }

                                },
                                success : function(result) {//服务器处理成功时
                                    $(button).linkbutton('enable');   //提交完，并且处理完毕返回消息后，马上恢复掉保存按钮，enable
                                    var obj = jQuery.parseJSON(result);
                                    //服务器处理成功后清空表单数据
                                    if (obj.success) {
                                        $("#customerInfo_datagrid").datagrid('load'),
                                                editDialog.dialog('destroy');
                                    }
                                    $.messager.show({
                                        title : '提示',
                                        msg : obj.msg,
                                        timeout:5000,
                                        showType:'slide'
                                    });
                                },
                                error:function(XMLHttpRequest, textStatus, errorThrown) {
                                    $.messager.show("网络或者其它原因导致的错误!");
                                }
                            });
                        }
                    },{
                        text:'取消',
                        iconCls:'icon-cancel',
                        handler:function(){
                            editDialog.dialog('close');//关闭窗口
                        }
                    } ]
                })
            }else if(customerInfoArr.length >= 2){
                $.messager.alert('警告','所选数据大于1条，请选择一条要跟进的数据!');
            }else{
                $.messager.alert('警告','请选择要跟进的数据!');
            }
        }
        /*为该学员报名*/
        function customerStateReview(){
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr =  $customerInfo_dataGrid.datagrid('getSelections');
            if(customerInfo.getCustomerState!=1){
                layer.alert('亲，你已经面试过该学员了!');
                return false;
            }
            if(customerInfoArr.length == 1){
                $.ajax({
                    type:'get',
                    url:'${pageContext.request.contextPath}/userInfo/alterCustomerState?customerId='+customerInfo.customerId,
                    success:function(data){
                        $.messager.show({
                            title:'我的消息',
                            msg:data,
                            timeout:5000,
                            showType:'slide'
                        });
                        $("#customerInfo_datagrid").datagrid('load');
                    }
                })
            }else if(customerInfoArr.length >= 2){
                $.messager.alert('警告','所选数据大于1条，请选择一条要报名的数据!');
            }else{
                $.messager.alert('警告','请选择要报名的数据!');
            }
        }

        /*学员跟进*/
        function newCustomerInfoTrace(){
            var customerInfo = $customerInfo_dataGrid.datagrid('getSelected');
            var customerInfoArr =  $customerInfo_dataGrid.datagrid('getSelections');
            if(customerInfoArr.length == 1){
                //iframe层
                if(customerInfo.customerState != 1){
                    layer.open({
                        type: 2,
                        title: '学员跟进',
                        shadeClose: true,
                        shade: 0.8,
                        maxmin: true,
                        area: ['100%', '100%'],
                        content: '${pageContext.request.contextPath}/customerInfo/detailedAndTraceCustomer?customerId='+customerInfo.customerId //iframe的url
                    });
                }else{
                    layer.alert('亲，你还没有面试该学员呢!');
                }
            }else if(customerInfoArr.length >= 2){
                $.messager.alert('警告','所选数据大于1条，请选择一条要报名的数据!');
            }else{
                $.messager.alert('警告','请选择要报名的数据!');
            }

        }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border : false">
<%--搜索--%>
<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px;overflow: hidden;" align="center">
    <form id="customerInfoSearchForm">
        <table class="tableForm">
            <tr>
                <td>
                    <select id="comboxQueryCustomerLevel"  name="customerLevel" style="width:170px;">
                        <option value="-1">----请选择学员级别----</option>
                        <option value="1">A</option>
                        <option value="2">B</option>
                        <option value="3">C</option>
                        <option value="4">D</option>
                    </select>
                </td>
                <td>
                    <select id="comboxQueryCustomerState"  name="customerState" style="width:170px;">
                        <option value="-1">----请选择学员状态----</option>
                        <option value="1">待面试</option>
                        <option value="2">已面试</option>
                        <option value="3">试听</option>
                        <option value="4">有意向</option>
                        <option value="5">已报名</option>
                        <option value="6">已进班</option>
                    </select>
                </td>
                <td style="align:right">
                    <select id="comboxQuery"  name="search" style="width:170px;">
                        <option value="-1">----请选择搜索条件----</option>
                        <option value="realName">按照学员名称搜索</option>
                        <option value="nativePlace">按照籍贯搜索</option>
                        <option value="tel">按照电话号码搜索</option>
                        <option value="createTime">按创建时间搜索</option>
                        <option value="lastTime">按最后跟进时间搜索</option>
                    </select>
                </td>
                <td>
                    <input type="text" id="queryText" name="queryName">
                </td>
                <td></td>
                <td>
                    <a href="javascript:void(0);" onclick="queryFun()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                       onclick="cancelSearchFun()">刷新</a>
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
<%--文件上传--%>
<%--<div id="fileUpload" data-options="modal:true" class="easyui-dialog" title="报表导入" data-options="iconCls:'icon-save'" style="width:350px;height:170px;padding:10px">
    <form action="${pageContext.request.contextPath}/importCustomreInfo" method="post" enctype="multipart/form-data">
        <div style="margin-top:10px;"></div>
        <div style="margin-bottom:20px">
            <input class="easyui-filebox" name="importCustomerInfo" data-options="prompt:'请选择文件...'" style="width:100%">
        </div>
        <div>
            <a href="#" class="easyui-linkbutton" style="width:100%">上传文件</a>
        </div>
    </form>

</div>--%>

<%--学员信息搜索--%>
<script type="text/javascript">
    var path = "${pageContext.request.contextPath}/customerInfo/ajaxList";
    var customerLevel="";
    var customerState="";
    var url;
    $("#comboxQueryCustomerLevel").change(function(){//学员级别
        customerLevel =  $(this).children('option:selected').val();
        if(customerLevel == -1){
            layer.alert('亲，请选择学员级别!');

            return false;
        }else{
            url = path+"?customerLevel="+customerLevel;
        }
        $customerInfo_dataGrid.datagrid('load',url);
    });
    //获取学员状态改变事件
    $("#comboxQueryCustomerState").change(function(){//学员状态
        customerState =$(this).children('option:selected').val();//这就是selected的值
        if(customerState == -1){
            layer.alert('亲，请选择学员状态!');
            return false;
        }else{
            if(customerLevel.length>0){
                url = path+"?customerLevel="+customerLevel+"&customerState="+customerState;
            }else{
                url = path+"?customerState="+customerState;
            }
        }
        $customerInfo_dataGrid.datagrid('load',url);
    });

    function queryFun(){
        var $comboxQuery = $("#comboxQuery");
        var comboxValue = $("#comboxQuery").children('option:selected').val();
        if(comboxValue==-1){
            $.messager.alert('警告','请选择搜索条件!');
            return false;
        }
        if(customerState.length>0){
            console.log(url);
            url = url+"&"+comboxValue+"="+$("#queryText").val()+"&currentUser=1";
        }else{
            url = path+"?"+comboxValue+"="+$("#queryText").val()+"&currentUser=1";
        }
        $customerInfo_dataGrid.datagrid('load',url);
    }
    <%--清空查询条件--%>
    function cancelSearchFun() {
        $("#queryText").val('');
        $customerInfo_dataGrid.datagrid('load',path);
    }

</script>
</body>
</html>
