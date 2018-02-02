<%--
  Created by IntelliJ IDEA.
  User: qiu
  Date: 18-2-1
  Time: 下午8:40
  To change this template use File | Settings | File Templates.
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
</head>
<body>
<h1 align="center">登录页面</h1>
<!-- <form name="reg" action="#" method="post" > -->
<table  align="center" valign="middle">
    <tr>
        <td>手机号:</td>
        <td><input name="mobile" type="text" id="mobile" /></td>
    </tr>
    <tr>
        <td>验证码:</td>
        <td><input name="idcode" type="text" id="idcode" /></td>
        <td><button id="bt01" name="send" onclick="sendSMS();fun()">发送验证码</button></td>
        <%--<td><input type="button" name="send"  id = "bt01" value="获取" onclick=sendSMS();fun() /></td>--%>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" name="regster" value="登录"  style="width:140px;" onclick=registerUser() /></td>
    </tr>
</table>
<!--  </form> -->

<script src="../jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" language="javascript">
    function fun() {
        var bt01 = document.getElementById("bt01");
        bt01.disabled = true;   //当点击后倒计时时候不能点击此按钮
        var time = 60;   //倒计时5秒
        var timer = setInterval(fun1, 1000);    //设置定时器
        function fun1() {
            time--;
            if(time>=0) {
                bt01.innerHTML = time + "s后重新发送";
            }else{
                bt01.innerHTML = "重新发送验证码";
                bt01.disabled = false;    //倒计时结束能够重新点击发送的按钮
                clearTimeout(timer);    //清除定时器
                time = 5;   //设置循环重新开始条件
            }
        }
    }
    function sendSMS() {
        var mobile = document.getElementById("mobile").value;
        if(mobile==''){
            window.alert(" 请输入电话号码！");
            return;
        }
        $.ajax({
            type:'get',
            url:'/test/getcode.do?mobile='+mobile,
           // data: JSON.stringify({"code":0,"attribute":[{"key": "mobile", "value": mobile}]}),
            async:true,
            dataType:'json',
            cache:false,
            error:function () {
                alert('失败')
            },
            success:function(data){
                if(data.errcode==0){
                    alert("验证码已发送，请在"+data.detail+"s内输入");
                } else{
                    alert("验证码发送失败");
                }
            },
            error:function(XMLResponse){
                alert("error");
            }
        });
    }

    function registerUser() {
        var mobile = document.getElementById("mobile").value;
        var idcode = document.getElementById("idcode").value;
        console.log(idcode);
        console.log(mobile);
        if(mobile==''){
            alert(" 请输入电话号码！");
            return;
        }
        if(idcode==''){
            alert(" 请输入验证码！");
            return;
        }

        $.ajax({
            type:'get',
            url:'/test/validate.do?mobile='+mobile+'&idcode='+idcode,
            async:true,
            cache:false,
            dataType:'json',
            success:function(data){
                if(data.errcode==0){
//                    alert("登录成功");
                    window.open("/index.jsp")
                }
                else{
                    alert(data.detail);
                }
            },
            error:function(XMLResponse){
                alert("error");
            }
        });
    }

</script>
</body>
</html>