<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-2-3
  Time: 下午3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/test/weather.do" method="get">
    城市(拼音输入,例如武汉:wuhan)：<input type="text" name="city"/> 天数：<input type="text" name="days"/> <button>查询</button>
</form>
</body>
</html>
