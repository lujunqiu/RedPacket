<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-1-9
  Time: 下午1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>参数</title>
    <!--加载Query文件-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            var max = 30000;
            for (var i = 1; i <= max; i++){
                //jQuery的post请求，这是异步请求
                $.post({
                    //请求抢id为1的红包
                    url:
                    "./userRedPacket/grapRedPacket?redPacketId=2&userId="+i,
                    success: function (result) {
                    }
                });
            }
        });
    </script>
</head>
<body>
</body>
</html>
