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
					<div class="row dataM1 peopleM4 teachDetail">
						<div class="col-md-12">
							<div class="panel peopPan1 peopPanM2">
								<div class="panel-heading clearfix">
									<div class="title">商户详情</div>

								</div>
								<div class="panel-body">
									<div class="pMainWrap addTeachM clearfix">
										<form class="form-horizontal" id="detail" action="#" method="post" >
											<div class="form-group static"><label for="exampleInputName" class="col-md-3 control-label">商户名称：</label>
												<div class="col-md-9">
													<input type="text" name="" class="form-control" id="exampleInputName" placeholder="" value="${(sellerInfo.merchantName)!''}"/>
												</div>
											</div>
											<div class="form-group static"><label for="exampleInputName2" class="col-md-3 control-label">姓名：</label>
												<div class="col-md-9">
													<input type="text" name="" class="form-control" id="exampleInputName2" placeholder="" value="${(sellerInfo.name)!''}"/>
												</div>
											</div>
                                            <div class="form-group static"><label for="exampleInputName2" class="col-md-3 control-label">电话号码：</label>
                                                <div class="col-md-9">
                                                    <input type="text" name="" class="form-control" id="exampleInputName2" placeholder="" value="${(sellerInfo.phone)!''}"/>
                                                </div>
                                            </div>
											<div class="form-group static"><label for="exampleInputName4" class="col-md-3 control-label">银行卡号：</label>
												<div class="col-md-9">
													<input type="number" name="" class="form-control" id="exampleInputName4" placeholder="" value="${(sellerInfo.idCard)!''}">
												</div>
											</div>
                                            <div class="form-group static"><label for="exampleInputName4" class="col-md-3 control-label">商户私钥：</label>
                                                <div class="col-md-9">
                                                    <input type="text" name="" class="form-control" id="exampleInputName4" placeholder="" value="${(sellerInfo.key)!''}">
                                                </div>
                                            </div>


										</form>
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
		<script src="/sell/ept/js/common.js"></script>
		<script>
            function submitForm() {
                var form = document.getElementById('detail');
                form.submit();
            }

		</script>
	</body>

</html>