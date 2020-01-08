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
                            <th>活动ID</th>
                            <th>名称</th>
                            <th>内容</th>
                            <th>类型</th>
                            <th>开始时间</th>
                            <th>地址</th>
                            <th>经纬度</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list eventPage.content as event>
                            <tr>
                                <td>${event.id}</td>
                                <td>${event.title}</td>
                                <td>${event.content}</td>
                                <td>${event.category}</td>
                                <td>${event.timeStr}</td>
                                <td>${event.address}</td>
                                <td>${event.geo}</td>
                                <td><a href="/back/event/index?eventId=${event.id}">修改</a></td>
                                <td><a href="/back/event/delete?eventId=${event.id}">删除</a></td>
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
                            <li><a href="/back/event/list?page=${page - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..eventPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/back/event/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if page gte eventPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/back/event/list?page=${page + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>