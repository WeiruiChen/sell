<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>菜品管理</title>
		<link href="/sell/ept/css/bootstrap.min.css" rel="stylesheet">
		<link href="/sell/ept/css/common.css" rel="stylesheet">
		<link href="/sell/ept/css/main.css" rel="stylesheet">
	</head>

	<body>
		<div id="container" class="container-fluid wrapOut">

		<#include "../common/nav1.ftl">
			
			<div class="mainWrap">
				<div class="main">
					<div class="row peopleM1 peopleM3 resourceM">
						<div class="col-md-12">
							<div class="panel peopPan1">
								<div class="panel-heading">
									<div class="title">菜品列表</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap clearfix">
										<div class="top">
											<a class="addTr" href="/sell/seller/product/index">新增
												<font>+</font>
											</a>
										</div>
										<div class="tableWrap">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>图片</th>
														<th>名称</th>
														<th>类别</th>
														<th>描述</th>
														<th>价格</th>
                                                        <th>库存</th>
														<th>创建时间</th>
														<td>更新时间</td>
														<th>操作</th>
													</tr>
												</thead>
												<tbody class="tds">

													<#list productInfoPage.content as productInfo>
                                                    <tr>
                                                        <td hidden="hidden">${productInfo.productId}</td>
                                                        <td><img height="100" width="100" src="${(productInfo.productIcon)!''}" alt=""></td>
                                                        <td>${productInfo.productName}</td>
                                                        <td>${productInfo.categoryType}</td>
                                                        <td>${productInfo.productDescription}</td>
                                                        <td>${productInfo.productPrice}</td>
                                                        <td>${productInfo.productStock}</td>
                                                        <td>${productInfo.createTime}</td>
                                                        <td>${productInfo.updateTime}</td>
                                                        <td class="btns">
                                                            <a class="edit_Btn" href="/sell/seller/product/index?productId=${productInfo.productId}">详情</a>
															<#if productInfo.getProductStatusEnum().message == "在架">
                                                                <a class="delete_Btn" href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
															<#else>
                                                                <a class="delete_Btn" href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
															</#if>
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

											<#list 1..productInfoPage.getTotalPages() as index>
												<#if currentPage == index>
                                                    <a class="active" href="#">${index}</a>
												<#else>
                                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
												</#if>
											</#list>

											<#if currentPage gte productInfoPage.getTotalPages()>
                                                <a class="next" href="#">下一页</a>
											<#else>
                                                <a class="next" href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
											</#if>

                                                <div class="count"><span>共&nbsp;${productInfoPage.getTotalPages()}&nbsp;页</span> <span>当前第&nbsp;${currentPage}&nbsp;页</span> </div>
                                            </div>


										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    </body>
			<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
			<script src="/sell/ept/js/bootstrap.min.js"></script>
			<script src="/sell/ept/js/WdatePicker/WdatePicker.js"></script>
			<script src="/sell/ept/js/common.js"></script>
			<script>
				$(function() {})
			</script>



</html>