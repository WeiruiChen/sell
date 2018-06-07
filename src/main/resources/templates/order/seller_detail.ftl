<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="#">
                        <div class="form-group">
                            <label>商户执照</label>
                            <img height="100" width="100" src="${(sellerInfo.businessLicense)!''}" alt="">
                            <#--<input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>-->
                        </div>
                        <div class="form-group">
                            <label>商户名称</label>
                            <input name="" type="text" class="form-control" value="${(sellerInfo.merchantName)!''}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input name="" type="text" class="form-control" value="${(sellerInfo.name)!''}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>电话号码</label>
                            <input name="" type="number" class="form-control" value="${(sellerInfo.phone)!''}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <input name="" type="text" class="form-control" value="${(sellerInfo.idCard)!''}" readonly/>
                        </div>
                        <div class="form-group">
                            <label>商户私钥</label>
                            <input name="" type="text" class="form-control" value="${(sellerInfo.key)!''}"   readonly/>
                        </div>

                        <div class="form-group">
                            <label>审核状态</label>
                            <input readonly name="" type="text" class="form-control" value="<#if sellerInfo.audit==0>未审核<#elseif sellerInfo.audit==1>审核未通过<#else >审核通过</#if>
                        "/>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>