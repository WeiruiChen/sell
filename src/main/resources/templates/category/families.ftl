<!doctype html>
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

			<#include "../common/nav1.ftl">

			<div class="mainWrap">
				<div class="main">
					<div class="row peopleM1 peopleM3 resourceM">
						<div class="col-md-12">
							<div class="panel peopPan1">
								<div class="panel-heading">
									<div class="title">类别列表</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap clearfix">
										<div class="top">
											<a class="addTr" href="#"data-toggle="modal" data-target="#familyDetail">新增
												<font>+</font>
											</a>
										</div>
										<div class="tableWrap">
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>序号</th>
														<th>名称</th>
                                                        <th>type</th>
                                                        <th>创建时间</th>
                                                        <th>修改时间</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody class="tds">

													<#list categoryList as category>
                                                    <tr>
                                                        <td>${category.categoryId}</td>
                                                        <td>${category.categoryName}</td>
                                                        <td>${category.categoryType}</td>
                                                        <td>${category.createTime}</td>
                                                        <td>${category.updateTime}</td>
                                                        <td class="btns">
                                                            <a class="edit_Btn" href="javascript:void(0)" data-toggle="modal" onclick="getDetail(${category.categoryId})" data-target="#deleteFamily">修改</a>
                                                        </td>
                                                    </tr>
													</#list>


												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="familyDetail" tabindex="-2" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h2 class="modal-title" id="myModalLabel">增加类别</h2>
						</div>
						<div class="modal-body">
							<div class="pMainWrap addTeachM clearfix">
								<form id="add" class="form-horizontal" method="post" action="/sell/seller/category/save">
									<div class="form-group static"><label for="exampleInputName" class="col-md-3 control-label">名称：</label>
										<div class="col-md-9">
											<input type="text" name="categoryName" class="form-control" id="exampleInputName" placeholder="" >
										</div>
									</div>
									<div class="form-group static"><label for="exampleInputName4" class="col-md-3 control-label">type：</label>
										<div class="col-md-9">
											<input type="text" name="categoryType" class="form-control" id="exampleInputName4" placeholder="" >
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal" data-backdrop="false">取消</button>
							<button type="button" onclick="submitForm()"class="btn btn-primary">保存</button>
						</div>
					</div>
				</div>
			</div>

			<div class="modal fade" id="deleteFamily" tabindex="-2" role="dialog" aria-labelledby="basicModal" aria-hidden="true" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title" id="myModalLabel">修改类别</h2>
                        </div>
                        <div class="modal-body">
                            <div class="pMainWrap addTeachM clearfix">
                                <form id="edit" class="form-horizontal" method="post" action="/sell/seller/category/save">
                                    <div class="form-group static"><label for="exampleInputName" class="col-md-3 control-label">名称：</label>
                                        <div class="col-md-9">
											<input type="text" name="categoryName" class="form-control" id="xcategoryName" placeholder="" >
										</div>
                                    </div>
                                    <div class="form-group static"><label for="exampleInputName4" class="col-md-3 control-label">type：</label>
                                        <div class="col-md-9">
											<input type="text" name="categoryType" class="form-control" id="xcategoryType" placeholder="">
										</div>
                                    </div>
                                    <input hidden type="text" name="categoryId" id="xcategoryId">
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" data-backdrop="false">取消</button>
                            <button type="button" onclick="edit()" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </div>
			</div>



			<script src="/sell/ept/js/jquery-1.11.3.min.js"></script>
			<script src="/sell/ept/js/bootstrap.min.js"></script>
			<script src="/sell/ept/js/WdatePicker/WdatePicker.js"></script>
			<script src="/sell/ept/js/common.js"></script>
            <script>
                function submitForm() {
                    var form = document.getElementById('add');
                    form.submit();
                }

                function edit() {
                    var form = document.getElementById('edit');
                    form.submit();
                }

                function getDetail(id) {
                    $.getJSON("/sell/seller/category/index",{categoryId:id},function(data){
                        $("#xcategoryName").attr("value",data.categoryName);
                        $("#xcategoryType").attr("value",data.categoryType);
                        $("#xcategoryId").attr("value",data.categoryId);
                    })
				}

            </script>
	</body>

</html>