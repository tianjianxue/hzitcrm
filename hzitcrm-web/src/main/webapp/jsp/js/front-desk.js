//TODO consultant.jsp页面脚本
$(document).ready(function(){

    /**
     * 前台录入客户姓名，选择咨询师，点击提交
     */
    $("#next").click(function(){
        $.ajax({
            dataType:"json",
            type: "POST",
            url:"/registerCustomer",
            data:$('#form-wizard').serialize(),
            async: false,//改为true就报错！！目前尚不知道原理！！
            success: function(data) {
                var responseCode = data.responseCode;
                //alert(responseCode);
                if(responseCode!="00"){
                    alert(data.responseMsg);
                }else{
                    //刷新访客列表
                    refreshCustomerList;
                }

            },
            error:function(){
                alert();
                alert("网络繁忙,请稍后再试！");
            }
        });
    });

    /**
     * TODO 每隔20秒调后台，查询数据库已登记用户列表，局部刷新访客列表
     * 定时刷新访客列表[频率:20s/次]
     */
    setInterval(refreshCustomerList,20000);
});

    //TODO  刷新访客列表  等待时间待添加字段
    var refreshCustomerList =function(){
        var htmlContent ="";
        $.ajax({
            dataType:"json",
            cache: false,
            type: "POST",
            url:"/getCustomerInfoList",
            async: true,
            success: function(data) {
                var customerInfoList = data;
                for(var i=0;i<customerInfoList.length;i++){
                    htmlContent +="<li><i class='icon-user'></i> <strong>"+customerInfoList[i].realName+"</strong> <small>等待时间:"+customerInfoList[i].memo+"</small></li>";
                }
                $("#customerInfoList-ethen").html(htmlContent);
            }
        });
    };

