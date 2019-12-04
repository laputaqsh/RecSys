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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>地点ID</th>
                            <th>经度</th>
                            <th>纬度</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list regionPage.content as region>
                            <tr>
                                <td>${region.regionId}</td>
                                <td>${region.regionLongi}</td>
                                <td>${region.regionLati}</td>
                                <td>${region.createTime}</td>
                                <td>${region.updateTime}</td>
                                <td><a href="/recsys/back/region/index?regionId=${region.regionId}">修改</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if page lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/recsys/back/region/list/?page=${page - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..regionPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/recsys/back/region/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if page gte regionPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/recsys/back/region/list?page=${page + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>