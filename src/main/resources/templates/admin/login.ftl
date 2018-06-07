<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陆</title>
<link href="/sell/css/style1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="web">
<p style="height:180px;"></p>
<p align="center"><img src="/sell/images/logzi.png" /></p>
<p style="height:40px;"></p>
<div class="login">
   <div class="banner">
   <iframe id="frame_banner"  src="/sell/seller/banner" height="218" width="100%"  allowtransparency="true" title="test"  scrolling="no" frameborder="0">
   </iframe>
   </div>
   <div class="logmain">
      <h1>&nbsp;</h1>
       <form method="post" action="/sell/admin/AdminLogin">
          <div class="logdv">
             <span class="logzi">账 号：</span>
            <input name="username" type="text" id="textarea" class="ipt" required/>
          </div>
          <div class="logdv">
          <span class="logzi">密 码：</span>
            <input name="password" type="password" id="textarea" class="ipt" required/>
          </div>


          <div class="logdv">
            <p class="logzi">&nbsp;</p>
            <a href="/sell/seller/onSellerLogin" class="more">商户登陆</a>
            <input name="" type="checkbox" value=""  class="cex"/>记住密码
          </div>
          <div class="logdv" style="height:40px;">
            <p class="logzi">&nbsp;</p>
            <input name="提交" type="submit" class="btnbg" value="点击登录" />
          </div>

       </form>
      <div>
        <a href="#" class="more">注册</a>
      </div>
   </div>
</div>
<p style="height:100px;"></p>
<p align="center">后台管理系统</p>
</div>
</body>
</html>
