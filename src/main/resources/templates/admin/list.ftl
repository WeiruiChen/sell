<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商户名称</th>
                            <th>姓名</th>
                            <th>电话号码</th>
                            <th>身份证号码</th>
                            <th>审核状态</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

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
                            <td><a href="/sell/admin/detail?id=${sellerInfo.sellerId}">详情</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/admin/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..sellerInfoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/admin/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte sellerInfoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>