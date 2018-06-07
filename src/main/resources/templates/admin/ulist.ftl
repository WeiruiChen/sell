<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>商户管理</title>
		<link href="/sell/ept/css/bootstrap.min.css" rel="stylesheet">
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/main.css" rel="stylesheet">
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
									<div class="title">待审核商户</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap clearfix">
										<div class="tableWrap">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
                                                        <th>商户名称</th>
                                                        <th>姓名</th>
                                                        <th>电话号码</th>
                                                        <th>银行卡号</th>
                                                        <th>审核状态</th>
                                                        <th >操作</th>
													</tr>
												</thead>
												<tbody class="tds">

												<#list sellerInfoPage.content as sellerInfo>
                                                <tr>
                                                    <td>${sellerInfo.merchantName}</td>
                                                    <td>${sellerInfo.name}</td>
                                                    <td>${sellerInfo.phone}</td>
                                                    <td>${sellerInfo.idCard}</td>
                                                    <td>
														<#if sellerInfo.audit==0>
                                                            未审核
														<#elseif sellerInfo.audit==1>
                                                            审核未通过
														<#else >
                                                            审核通过
														</#if>

                                                    </td>
                                                    <td class="btns">
														<a class="edit_Btn" href="/sell/admin/detail?id=${sellerInfo.sellerId}" >详情</a>
													</td>
                                                </tr>
												</#list>

												</tbody>
											</table>
											<div class="page">
											<#if currentPage lte 1>
                                                <a class="prev" href="#">上一页</a>
											<#else>
                                               <a class="prev" href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a>
											</#if>

												<#list 1..sellerInfoPage.getTotalPages() as index>
													<#if currentPage == index>
														<a class="active" href="#">${index}</a>
													<#else>
													    <#if currentPage lte 0>
															<a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
														</#if>
													</#if>
												</#list>
											<#if currentPage gte sellerInfoPage.getTotalPages()>
                                                <a class="next" href="#">下一页</a>
											<#else>
                                                <a class="next" href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
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

			<div class="modal fade" id="orderDetail" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h5 class="modal-title" id="myModalLabel">订单详情</h5>
						</div>
						<div class="modal-body">
							<div class="panel-body">
								<div class="pMainWrap clearfix">
									<div class="tableWrap">
										<table id="product" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>菜品</th>
													<th>数量/份</th>
													<th>金额/元</th>
												</tr>
											</thead>
											<tbody class="tds">

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="receiveOrder" tabindex="-2" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h5 class="modal-title" id="myModalLabel">接受订单</h5>
						</div>
						<div class="modal-body">
							<h1>是否接受订单？</h1>
							<h3>点击确定则会接受订单<br> 
								点击关闭退出。
							</h3>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal" data-backdrop="false">关闭</button>
							<button href="" type="button" class="btn btn-primary">确定</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="cancelOrder" tabindex="-2" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h5 class="modal-title" id="myModalLabel">取消订单</h5>
						</div>
						<div class="modal-body">
							<h1>是否取消订单？</h1>
							<h3>点击确定则会取消订单<br> 点击关闭退出。
							</h3>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal" data-backdrop="false">关闭</button>
							<button type="button" class="btn btn-primary">确定</button>
						</div>
					</div>
				</div>
			</div>
			</div>

        <script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
        <script src="/sell/ept/js/bootstrap.min.js"></script>
        <script src="/sell/ept/js/WdatePicker/WdatePicker.js"></script>
        <script src="/sell/ept/js/common.js"></script>

        <script>

            function detail(id) {
                $.getJSON("/sell/seller/order/detail",{orderId:id},function(data){
                    var details=data.orderDetails;

                    var tb = document.getElementById('product');
                    var rowNum=tb.rows.length;
                    for (i=0;i<rowNum;i++)
                    {
                        tb.deleteRow(i);
                        rowNum=rowNum-1;
                        i=i-1;
                    }
                    for(var i in details){
                        var currentRows = document.getElementById("product").rows.length;
                        var insertTr = document.getElementById("product").insertRow(currentRows);
                        var insertTd = insertTr.insertCell(0);
                        insertTd.style.textAlign="center";
                        insertTd.innerHTML = details[i].productName;

                        insertTd = insertTr.insertCell(1);
                        insertTd.style.textAlign="center";
                        insertTd.innerHTML = details[i].productQuantity ;
                        insertTd = insertTr.insertCell(2);
                        insertTd.style.textAlign="center";
                        insertTd.innerHTML = details[i].productPrice;
                    }
                })
            }

        </script>

	</body>


</html>