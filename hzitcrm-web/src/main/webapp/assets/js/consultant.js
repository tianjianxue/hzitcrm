/**
 * 获取访客跟进记录改进
 * @21060903
 */
var toGetCustomerRecordList = function(customerId){
    //TODO 添加判断 当列表展开时才查询，隐藏时不做任何操作！！

    //用来选中是哪个客户
    var idContent = "#customerRecordList" + customerId;

    var param =  {"customerId":customerId,"pageNo":"1"};
    var jsonParam = JSON.stringify(param);
    //alert("客户的id="+jsonParam);
    $.ajax(
        {
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            type: "POST",
            url:"/getCustomerTraceRecordById",
            data:jsonParam,
            //async: false,
            success: function(data)
            {
                alert(data.customerId);
                var htmlContent = "<li><div class='article-post'>"
                    + "<span class='user-info'>2016-09-2:09:10-李春梅</span>"
                    + "<p><a href='javascript:void(0)'>明天对比一下华育，朋友想明天来学"
                    + "</a></p></div></li>";

                for(var i=0; i<data.length; i++)
                {
                    alert(data[i]);
                    var times   =   data[i].times;
                    var content =   data[i].content;
                    htmlContent +="<li><div class='article-post'><span class='user-info'>"
                    +times+"李春梅"
                    +"</span><p><a href='javascript:void(0)'>"
                    +content
                    +"</a></p></div></li>";
                }
                //填充访客跟进记录数据
                $(idContent).html(htmlContent);

            }
        }
    );

}






/**
 * 咨询师界面
 * Created by yangxiaowei-pc on 2016/8/30.
 */
//$(document).ready(function()
//{
//        /**
//         * 获取访客跟进记录
//         */
//        $("toGetCustomerRecordList").click(function()
//        {
//            //FIXME
//            var param = $("#toGetCustomerRecordList").attr("value");
//            param = {"customerId":param,"pageNo":"1"};
//            var jsonParam = $.toJSON(param);
//            alert("客户的id="+param);
//            $.ajax(
//                {
//                    dataType:"json",
//                    type: "POST",
//                    url:"/getCustomerTraceRecordById",
//                    data:jsonParam,
//                    async: true,
//                    success: function(data)
//                    {
//                        alert(555555);
//                        alert(data);
//                        var htmlContent = "<li><div class='article-post'>"
//                                        + "<span class='user-info'>2016-09-2:09:10-李春梅</span>"
//                                        + "<p><a href='javascript:void(0)'>明天对比一下华育，朋友想明天来学"
//                                        + "</a></p></div></li>";
//
//                        for(var i=0; i<data.length; i++)
//                        {
//                            var times   =   data[i].times;
//                            var content =   data[i].content;
//                            htmlContent +="<li><div class='article-post'><span class='user-info'>"
//                                        +times+"李春梅"
//                                        +"</span><p><a href='javascript:void(0)'>"
//                                        +content
//                                        +"</a></p></div></li>";
//                        }
//                        //填充访客跟进记录数据
//                        $("#customerRecordList").html(htmlContent);
//
//                    }
//                }
//            );
//        });
//
//
//
//
//});
