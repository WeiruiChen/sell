<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>管理员</title>
		<link href="/sell/ept/css/bootstrap.min.css" rel="stylesheet">
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/main.css" rel="stylesheet">
		<style type="text/css">
			.modal {
				font-family: "微软雅黑";
				color: #090909;
			}
			
			#inp_div {
				float: left;
			}
			
			#sub_div {
				float: right;
			}
			
			#startModal3:after {
				content: "";
				display: block;
				height: 0;
				clear: both;
				zoom: 1;
			}
		</style>
	</head>

	<body>
		<div id="container" class="container-fluid wrapOut">

			<#include "nav1.ftl">

			<div class="mainWrap">
				<div class="main">
					<div class="row peopleM1 peopleM3 resourceM">
						<div class="col-md-12">
							<div class="panel peopPan1">
								<div class="panel-heading">
									<div class="title">到期商户</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap clearfix">
										<div class="tableWrap">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
                                                        <th>商户名称</th>
														<th>商家姓名</th>
														<th>联系电话</th>
														<th>到期时间</th>
														<th>停用/启用</th>
													</tr>
												</thead>
												<tbody class="tds">
												<#list sellerInfoPage.content as sellerInfo>
                                                <tr>
                                                    <td>${sellerInfo.merchantName}</td>
                                                    <td>${sellerInfo.name}</td>
                                                    <td>${sellerInfo.phone}</td>
                                                    <td>${(sellerInfo.trialTime)!''}</td>
                                                    <td class="btns">
                                                        <a class="confirm_Btn" href="javascript:void(0)" onclick="use('${sellerInfo.sellerId}')" data-toggle="modal" data-target="#start1">开始启用</a>
                                                    </td>
                                                </tr>
												</#list>

												</tbody>
											</table>

											<div class="page">
												<#if currentPage lte 1>
													<a class="prev" href="#">上一页</a>
												<#else>
													<a class="prev" href="/sell/admin/udate?page=${currentPage - 1}&size=${size}">上一页</a>
												</#if>

												<#list 1..sellerInfoPage.getTotalPages() as index>
													<#if currentPage == index>
														<a class="active" href="#">${index}</a>
													<#else>
														<#if currentPage lte 0>
															<a href="/sell/admin/udate?page=${index}&size=${size}">${index}</a>
														</#if>
													</#if>
												</#list>
												<#if currentPage gte sellerInfoPage.getTotalPages()>
													<a class="next" href="#">下一页</a>
												<#else>
													<a class="next" href="/sell/admin/udate?page=${currentPage + 1}&size=${size}">下一页</a>
												</#if>
													<div class="count"><span>共&nbsp;${sellerInfoPage.getTotalPages()}&nbsp;页</span> <span>当前第&nbsp;${currentPage}&nbsp;页</span> </div>
                                            </div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

                <div class="modal fade" id="start1" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title" id="myModalLabel">设置使用时间</h3>
                            </div>
                            <div class="modal-body">
                                <form role="form" action="/sell/admin/use" class="form-inline" id="startModal3" method="post">
                                    <div class="form-group" id="inp_div">
                                        <input type="text" hidden id="use" value="" name="sellerId"/>
                                        <label for="name">名称</label>
                                        <input type="text" class="form-control" name="date" id="extendTime1" placeholder="请输入使用天数" required="required">
                                    </div>
                                    <div class="form-group" id="sub_div">
                                        <button type="button" class="btn btn-primary" onclick="confirmuse()">确认开始使用</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

			<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
			<script src="/sell/ept/js/bootstrap.min.js"></script>
			<script src="/sell/ept/js/WdatePicker/WdatePicker.js"></script>
			<script src="/sell/ept/js/common.js"></script>
			<script type="text/javascript">
				function confirm() {
					var et = $("#extendTime").val();
					var isNum = /^[0-9]+$/; //匹配纯数字
					if(et != "" && isNum.test(et)) {
						$("#startModal").submit(); //验证成功进行表单提交
						return true;
					} else {
						alert("请输入整数");
						return false;
					}
				}
			</script>
			<script>
                function use(sellerId) {
                    document.getElementById('use').value=sellerId;
                }
                function confirmuse() {
                    var et = $("#extendTime1").val();
                    var isNum = /^[0-9]+$/; //匹配纯数字
                    if(et != "" && isNum.test(et)) {
                        $("#startModal3").submit(); //验证成功进行表单提交
                        return true;
                    } else {
                        alert("请输入整数");
                        return false;
                    }
                }
			</script>
	</body>

</html>