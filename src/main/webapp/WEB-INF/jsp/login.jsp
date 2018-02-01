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
        <td><input type="button" name="send" value="获取" onclick=sendSMS() /></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" name="regster" value="登录"  style="width:140px;" onclick=registerUser() /></td>
    </tr>
</table>
<!--  </form> -->

<script src="../jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" language="javascript">
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
                    alert("登录成功");
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