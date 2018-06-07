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
			
			#startModal:after {
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
									<div class="title">未到期商户</div>
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
                                                        <a class="delete_Btn" href="javascript:void(0)"  onclick="stop('${sellerInfo.sellerId}')" data-toggle="modal" data-target="#stop">暂停使用</a>
                                                    </td>
                                                </tr>
												</#list>

												</tbody>
											</table>

											<div class="page">
												<#if currentPage lte 1>
													<a class="prev" href="#">上一页</a>
												<#else>
													<a class="prev" href="/sell/admin/rdate?page=${currentPage - 1}&size=${size}">上一页</a>
												</#if>

												<#list 1..sellerInfoPage.getTotalPages() as index>
													<#if currentPage == index>
														<a class="active" href="#">${index}</a>
													<#else>
														<#if currentPage lte 0>
															<a href="/sell/admin/rdate?page=${index}&size=${size}">${index}</a>
														</#if>
													</#if>
												</#list>
												<#if currentPage gte sellerInfoPage.getTotalPages()>
													<a class="next" href="#">下一页</a>
												<#else>
													<a class="next" href="/sell/admin/rdate?page=${currentPage + 1}&size=${size}">下一页</a>
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

			<div class="modal fade" id="start" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="modal-title" id="myModalLabel">设置使用时间</h3>
						</div>
						<div class="modal-body">
							<form role="form" class="form-inline" id="startModal">
								<div class="form-group" id="inp_div">
									<label for="name">名称</label>
									<input type="text" class="form-control" id="extendTime" placeholder="请输入使用天数" required="required">
								</div>
								<div class="form-group" id="sub_div">
									<button type="button" class="btn btn-primary" onclick="confirm()">确认开始使用</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


            <div class="modal fade" id="stop" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3 class="modal-title" id="myModalLabel">停止试用</h3>
                            </div>
                            <div class="modal-body">
                                <form role="form" action="/sell/admin/suse" method="post" class="form-inline" id="startModal1">

                                    <input type="text" hidden id="sto" value="" name="sellerId"/>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal" data-backdrop="false">关闭</button>
                                        <button href="" type="button" class="btn btn-primary" onclick="stopTrial()">确定</button>
                                    </div>
								<#--<div class="form-group" id="sub_div">-->
								<#--<button type="button" class="btn btn-primary" >确认</button>-->
								<#--</div>-->
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
                function stop(sellerId) {
                    document.getElementById('sto').value=sellerId;
                }
                function stopTrial() {

                    $("#startModal1").submit();

                }
			</script>
	</body>

</html>