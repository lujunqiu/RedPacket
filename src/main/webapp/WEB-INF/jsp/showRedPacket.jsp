<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-2-5
  Time: 下午8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<br>
<c:forEach items="${redPackets}" var="redPacket">
    红包总编号:${redPacket.id} 红包总金额:${redPacket.amount} 红包总数: ${redPacket.total} <c:if test="${redPacket.stock>0}">还有剩余，可以抢红包 <button  type="submit" id="bt" onclick="action(${redPacket.id})">get</button> </c:if><c:if test="${redPacket.stock<=0}">已经抢光了</c:if>   <br>
</c:forEach>
<br>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.js">
</script>
<script type="text/javascript">
    function action(id1){
            $.get({
                //请求抢id为1的红包
                url:
                "/test/grapRedPacket.do?redPacketId="+id1+"&userId=1",
                success: function (result) {
                    alert(result.message);
                }
            });
    }
</script>
</html>
