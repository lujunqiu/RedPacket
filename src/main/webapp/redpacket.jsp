<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-2-3
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/test/insert.do" method="get">
    用户id:<input type="text" name="userId"/>
    红包总金额:<input type="text" name="amount"/>
    红包总数:<input type="text" name="total"/>
    备注:<input type="text" name="note"/>
    <button id="bt01">插入红包</button>
</form>

<form action="/test/showRedPacket.do" method="get">
    <button id="bt02">显示所有红包信息</button>
</form>
</body>
</html>
