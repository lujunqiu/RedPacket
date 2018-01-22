<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-1-9
  Time: 下午8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input id="myButton" type="button" value="click">
<script type="text/javascript" src="JS/jquery-1.4.2.js"></script>
<script type="text/javascript">
    $(function(){
        $("#myButton").click(function(){
            $.ajax({
                url:"./test/ajax",
                type:"post",
                dataType:"text",
                data:{
                    name:"lujunqiu"
                },
                success:function(responseText){
                    alert(responseText);
                },
                error:function(){
                    alert("system error");
                }
            });
        });
    });
</script>
</body>
</html>
