<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--  <meta charset="UTF-8"/>  -->
    <!--  <meta charset="GB2312"/> -->
 </head>
 <body>
 <h2>欢迎界面</h2>
 <form action="/test/message" method="post">
     <input type="text" name="phone"/><button id="bt01">获取验证码</button>
 </form>
 <form action="/test/validator" method="post">
     <input type="text" name="code"/><button>提交</button>
 </form>
 </body>
 </html>
