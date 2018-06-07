<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>修改密码</title>
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/login.css" rel="stylesheet">

	</head>

	<body>
		<div class="loginsWrap modifysWrap">
			<h1 style="text-align: center; font-size: 50px; color: darkgreen;">修改密码</h1>
			<div class="loginWrap">
				<div class="bg-image"><img src="/sell/ept/images/loginBg1.jpg" alt=""></div>
				<div class="loginW modifyWrap">
					<h2></h2>
					<div class="itemsWrap">
						<div class="title">重置密码</div>
						<div class="itemWrap">
							<form id="modify" action="/sell/admin/onPwd" method="post">
								<ul>
									<li>
										<div class="item"><input id="phone" name="phone" type="number" placeholder="请输入您的电话号码"></div>
									</li>
									<li>
										<div class="item"><input id="pwd" name="password" type="password" placeholder="请输入新密码"></div>
									</li>
									<li>
										<div class="item"><input name="confirm_password" type="password" placeholder="请确认新密码"></div>
									</li>

                                    <li>
                                        <div class="item"><input name="identifying" type="number" placeholder="请输入验证码"></div>
                                    </li>

                                    <li>
                                        <div class="mt clearfix">
                                            <button  style="color: blue;background: white;border: none" class="mtL"  href="javascript:void(0)" id="bt01">发送验证码</button>
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


            $().ready(function() {
                $("#modify").validate({
                    rules: {
                        phone: {
                            required: true,
                            isMobile: true
                        },
                        identifying: {
                            required: true,
                            minlength:6,
                            maxlength: 6
                        },
                        password: {
                            required: true,
                            isStringAndNum: true,
                            minlength: 6,
                            maxlength: 18
                        },
                        confirm_password: {
                            required: true,
                            equalTo: "#pwd",
                            isStringAndNum: true
                        }
                    },
                    messages: {
                        phone: {
                            required: "请填写手机号"
                        },
                        password: {
                            required: "请填写密码",
                            minlength: "字符长度不能小于6个字符",
                            maxlength: "字符长度不能大于18个字符"
                        },
                        identifying: {
                            required: "请填写验证码",
                            minlength: "字符长度不能小于6个字符",
                            maxlength: "字符长度不能大于6个字符"
                        },
                        confirm_password: {
                            required: "请再次输入密码",
                            equalTo: "两次输入密码不一致"
                        }
                    }
                });
            });
        </script>

        <script type="text/javascript">
            var bt01 = document.getElementById("bt01");
            bt01.onclick = function() {

                var phone=document.getElementById("phone").value;

                if(!!!phone){alert('请输入手机号码.');return false;}

                var phoneReg = /^1[3,5,8]\d{9}$/;
                if(!phoneReg.test(phone)){
                    alert('请输入正确的电话号码.');
                    return false;
                }

                //发起post请求
                $.post('/sell/admin/identifying', { phone: phone });

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
                        time = 60;   //设置循环重新开始条件
                    }
                }
            }
        </script>

	</body>

</html>