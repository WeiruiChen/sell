<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>财务管理</title>
		<link href="/sell/ept/css/bootstrap.min.css" rel="stylesheet">
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/main.css" rel="stylesheet">

  		<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  		<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

	</head>

	<body>

			<#include "../common/nav1.ftl">

			<div class="mainWrap">
				<div class="main">
					<div class="row dataM1 dataM2">
						<div class="col-md-12 col-lg-12">
							<div class="item">
								<div class="conts">
									<h2>本周数据总计</h2>
									<div class="count count_2 clearfix">
										<ul>
											<li>
												<div class="column">
													<div class="columnL"><img src="/sell/ept/images/dataManage2_Pic4.png" alt=""><br><span>订单总数</span></div>
													<div class="columnR"><span id="count21" class="count">${orderDTOPage.getTotalElements()}</span></div>
												</div>
											</li>
											<li>
												<div class="column column2">
													<div class="columnL"><img src="/sell/ept/images/dataManage2_Pic5.png" alt=""><br><span>订单总金额</span></div>
													<div class="columnR"><span id="count22" class="count2">${count}</span></div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row peopleM1 peopleM3 resourceM" style="margin-top:20px ;">
						<div class="col-md-12">
							<div class="panel peopPan1">
								<div class="panel-heading">
									<div class="title">已接订单</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap clearfix">
										<div class="tableWrap">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>订单编号</th>
														<th>订单金额</th>
														<th>接单时间</th>
														<th>订单状态</th>
                                                        <th>支付状态</th>
													</tr>
												</thead>
												<tbody class="tds">
												<#list orderDTOPage.content as orderDTO>
                                                <tr>
                                                    <td>${orderDTO.orderId}</td>
                                                    <td>${orderDTO.orderAmount}</td>
                                                    <td>${orderDTO.createTime}</td>
                                                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                                                    <td>${orderDTO.getPayStatusEnum().message}</td>
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

											<#list 1..orderDTOPage.getTotalPages() as index>
												<#if currentPage == index>
                                                    <a class="active" href="#">${index}</a>
												<#else>
													<#if currentPage lte 0>
                                                   		 <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
													</#if>
												</#if>
											</#list>

											<#if currentPage gte orderDTOPage.getTotalPages()>
                                                <a class="next" href="#">下一页</a>
											<#else>
                                                <a class="next" href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
											</#if>

                                                <div class="count"><span>共&nbsp;${orderDTOPage.getTotalPages()}&nbsp;页</span> <span>当前第&nbsp;${currentPage}&nbsp;页</span> </div>
                                            </div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
		<script src="/sell/ept/js/bootstrap.min.js"></script>
		<script src="/sell/ept/js/WdatePicker/WdatePicker.js"></script>
		<script src="/sell/ept/js/countUp-jquery.js"></script>
		<script src="/sell/ept/js/echarts.min.js"></script>
		<script src="/sell/ept/js/common.js"></script>
		<script>
		</script>
	</body>

</html>