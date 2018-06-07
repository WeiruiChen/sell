<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>注册</title>
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/login.css" rel="stylesheet">
		<style type="text/css">
			.inputName{
				font: "微软雅黑";
				font-size: 17px;
				color: #363636;
			}
		</style>

  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

	</head>

	<body>
		<div class="loginsWrap modifysWrap">
			<h1 style="text-align: center; font-size: 50px; color: darkgreen;">后台管理系统</h1>
			<div class="loginWrap">
				<div class="bg-image"><img src="/sell/ept/images/loginBg1.jpg" alt=""></div>
				<div class="loginW modifyWrap">
					<h2></h2>
					<div class="itemsWrap">
						<div class="title">注册账号</div>
						<div class="itemWrap">
							<form id="register" action="/sell/admin/sellerRegister" method="post">
								<ul>
									<li>
										<label class="inputName">帐号：</label>
										<div class="item">
											<input name="username" type="text" placeholder="请输入您的账号">
										</div>
									</li>
									<li>
										<label class="inputName">姓名：</label>
										<div class="item">
											<input name="name" type="text" placeholder="请输入您的姓名">
										</div>
									</li>
									<li>
										<label class="inputName">手机：</label>
										<div class="item">
											<input name="phone" type="text" placeholder="请输入您的手机号">
										</div>
									</li>
									<li>
										<label class="inputName">商户名称：</label>
										<div class="item">
											<input name="merchantName" type="text" placeholder="请输入您的商户名称">
										</div>
									</li>
                                    <li>
                                        <label class="inputName" for="bank">银行类型：</label>
                                            <select id="bank">
                                                <option selected>工商银行</option>
                                                <option>农业银行</option>
                                                <option>中国银行</option>
                                                <option>建设银行</option>
                                                <option>招商银行</option>
                                                <option>邮储银行</option>
                                                <option>交通银行</option>
                                                <option>浦发银行</option>
                                                <option>民生银行</option>
                                                <option>兴业银行</option>
                                                <option>平安银行</option>
                                                <option>中信银行</option>
                                                <option>华夏银行</option>
                                                <option>广发银行</option>
                                                <option>光大银行</option>
                                                <option>北京银行</option>
                                                <option>宁波银行</option>
                                            </select>
                                    </li>
                                    <li>
                                    <li>
                                        <label class="inputName">银行卡号：</label>
										<div class="item">
											<input name="idCard" type="text" placeholder="请确认您的银行卡号">
										</div>
                                    </li>
									<li>
										<label class="inputName">输入密码：</label>
										<div class="item">
											<input id="pwd" name="password" type="password" placeholder="请输入您的密码">
										</div>
									</li>
									<li>
										<label class="inputName">确认密码：</label>
										<div class="item">
											<input  name="confirm_password" type="password" placeholder="请确认您的密码">
										</div>
									</li>

									<li class="re_teshu">
										<div class="sub reSub clearfix"><input class="sub_btn" type="submit" value="提交"></div>
									</li>
								</ul>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
        <script src="/sell/ept/js/jquery.validate.min.js"></script>
        <script type="text/javascript">
            $.validator.setDefaults({
                submitHandler: function() {
                    this.submit();
                }
            });
            $.validator.addMethod("isMobile", function(value, element) {
                var tel = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
                return tel.test(value) || this.optional(element);
            }, "请输入正确的手机号码");

            //联系电话(手机/电话皆可)验证
            $.validator.addMethod("isPhone", function(value, element) {
                var length = value.length;
                var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
                var tel = /^\d{3,4}-?\d{7,9}$/;
                return this.optional(element) || (tel.test(value) || mobile.test(value));
            }, "请正确填写您的联系电话");

            /**
             * 验证是否字母数字
             */
            $.validator.addMethod("isStringAndNum", function(value, element) {
                var stringAndNum = /^[a-zA-Z0-9]+$/;
                var stringNum=/^[0-9]+$/
                return this.optional(element) || stringAndNum.test(value) && !stringNum.test(value);
            }, "只能包括英文字母和数字组合");

            /**
             * 验证是否是汉字
             */
            $.validator.addMethod("isCharacter", function(value, element) {
                var tel = /^[\u4e00-\u9fa5]+$/;
                return this.optional(element) || (tel.test(value));
            }, "请输入汉字");

//            /**
//             * 验证邮箱
//             */
//            $.validator.addMethod("isEmail", function(value, element) {
//                var email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
//                return this.optional(element) || (email.test(value));
//            }, "请输入有效的邮箱");

            $().ready(function() {
                $("#register").validate({
                    rules: {
                        username: {
                            required: true,
                            minlength: 2,
                            maxlength: 16,
                            isStringAndNum: true
                        },
                        name: {
                            required: true,
                            minlength: 2,
                            maxlength: 16,
                            isCharacter: true

                        },
                        phone: {
                            required: true,
                            isMobile: true
                        },
                        merchantName: {
                            required: true
                        },
                        idCard: {
                            required: true,
                            number: true,
                            minlength: 16,
                            maxlength: 19,
                            creditcard: true
                        },
                        password: {
                            required: true,
                            isStringAndNum: true,
                            minlength: 6,
                            maxlength: 18
                        },
                        confirm_pwd: {
                            required: true,
                            equalTo: "#pwd",
                            isStringAndNum: true
                        }
                    },
                    messages: {
                        username: {
                            required: "请填写账号",
                            minlength: "字符长度不能小于2个字符",
                            maxlength: "字符长度不能大于16个字符"
                        },
                        name: {
                            required: "请填写姓名",
                            minlength: "字符长度不能小于2个字符",
                            maxlength: "字符长度不能大于16个字符"
                        },
                        phone: {
                            required: "请填写手机号"
                        },
                        merchantName: {
                            required: "请填写商户名称"
                        },
                        idCard: {
                            required: "请填写银行卡号",
                            number: "请输入数字",
                            minlength: "有效长度不少于16个数字",
                            maxlength: "有效长度不超过19个数字",
                            creditcard:"银行卡号格式不正确"
                        },
                        password: {
                            required: "请填写密码",
                            minlength: "字符长度不能小于6个字符",
                            maxlength: "字符长度不能大于18个字符"
                        },
                        confirm_password: {
                            required: "请再次输入密码",
                            equalTo: "两次输入密码不一致"
                        }
                    }
                });
            });
        </script>
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