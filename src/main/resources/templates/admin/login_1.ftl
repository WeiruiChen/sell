<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>登录</title>
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/login.css" rel="stylesheet">

	</head>

	<body>
		<div class="loginsWrap">
			<h1 style="text-align: center; font-size: 50px; color: darkgreen;">管理员后台管理系统</h1>
			<div class="loginWrap">
				<div class="bg-image"><img src="/sell/ept/images/loginBg1.jpg" alt=""></div>
				<div class="loginW">
					<h2></h2>
					<div class="contWrap">
						<form action="/sell/admin/AdminLogin" method="post">
							<ul>
								<li>
									<div class="userWrap clearfix"><label></label>
										<input class="username" name="username" type="text" placeholder="请输入账号"></div>
								</li>
								<li>
									<div class="userWrap pswWrap clearfix"><label></label>
										<input class="username" name="password" type="password" placeholder="请输入登录密码"></div>
								</li>

								<li>
									<div class="sub clearfix"><input class="sub_btn" type="submit" value="立即登录"></div>
								</li>
							</ul>
						</form>
                        <div class="mt clearfix">
                            <a class="mtR" style="color: blue" href="/sell/seller/onSellerLogin">商户入口</a>
                        </div>
					</div>
				</div>
			</div>
			
		</div>
		<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
		<script>
			$(function() {
				function i() {
					var i = $(window).height();
					$(".loginsWrap").css("height", i - 75)
				}
				i(), $(window).resize(function() {
					i()
				})
			})
		</script>
	</body>

</html>