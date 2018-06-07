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
		<div id="container" class="container-fluid wrapOut">

			<#include "../common/nav1.ftl">

			<div class="mainWrap">
				<div class="main">
					<div class="row dataM1 peopleM4 teachDetail">
						<div class="col-md-12">
							<div class="panel peopPan1 peopPanM2">
								<div class="panel-heading clearfix">
									<div class="title">详情</div>
									<div class="controlBtns pull-right">
										<a href="javascript:void(0)" class="save_btn" onclick="submitForm()">保存</a>
										<a class="back_btn" href="javascript:history.back(-1);">返回</a>
									</div>
								</div>
								<div class="panel-body">
									<div class="pMainWrap addTeachM clearfix">
										<form class="form-horizontal" id="detail" action="/sell/seller/product/save" method="post" enctype="multipart/form-data">
											<div class="form-group static"><label for="exampleInputName" class="col-md-3 control-label">名称：</label>
												<div class="col-md-9">
													<input type="text" name="productName" class="form-control" id="exampleInputName" placeholder="" value="${(productInfo.productName)!''}">
												</div>
											</div>
											<div class="form-group static"><label for="exampleInputName2" class="col-md-3 control-label">价格：</label>
												<div class="col-md-9">
													<input type="number" name="productPrice" class="form-control" id="exampleInputName2" placeholder="" value="${(productInfo.productPrice)!''}">
												</div>
											</div>
											<div class="form-group static"><label for="exampleInputName3" class="col-md-3 control-label">库存：</label>
												<div class="col-md-9">
													<input type="number" name="productStock" class="form-control" id="exampleInputName3" placeholder="" value="${(productInfo.productStock)!''}">
												</div>
											</div>
											<div class="form-group static"><label for="exampleInputName4" class="col-md-3 control-label">描述：</label>
												<div class="col-md-9"><input type="text" name="productDescription" class="form-control" id="exampleInputName4" placeholder="" value="${(productInfo.productDescription)!''}"></div>
											</div>
											<div class="headPicW static clearfix"><label class="col-md-3 control-label">图片：</label>
												<div class="col-md-9">
                                                    <input type="file" onchange="imgPreview(this)" name="multipartFile"/>
                                                    <img class="headPic" id="dishPic" src="${(productInfo.productIcon)!''}"/>
												</div>
											</div>
											<div class="form-group static"><label class="col-md-3 control-label">类别：</label>
												<div class="col-md-9">
                                                    <select name="categoryType" class="form-control">
													<#list categoryList as category>
                                                        <option value="${category.categoryType}"
															<#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                                selected
															</#if>
                                                        >${category.categoryName}
                                                        </option>
													</#list>
                                                    </select>
												</div>
											</div>
                                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}"/>
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
        <script type="text/javascript">
            function imgPreview(fileDom) {
                //判断是否支持FileReader
                if(window.FileReader) {
                    var reader = new FileReader();
                } else {
                    alert("您的浏览器不支持图片预览功能，请使用google浏览器或其他浏览器！");
                }

                //获取文件
                var file = fileDom.files[0];
                var imageType = /^image\//;
                //是否是图片
                if(!imageType.test(file.type)) {
                    alert("请选择图片！");
                    return;
                }
                //读取完成
                reader.onload = function(e) {
                    //获取图片dom
                    var img = document.getElementById("dishPic");
                    //图片路径设置为读取的图片
                    img.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        </script>
	</body>

</html>