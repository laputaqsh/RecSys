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
                            <th>群组ID</th>
                            <th>群组名</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list groupPage.content as group>
                            <tr>
                                <td>${group.groupId}</td>
                                <td>${group.groupName}</td>
                                <td>${group.createTime}</td>
                                <td>${group.updateTime}</td>
                                <td><a href="/recsys/back/group/index?groupId=${group.groupId}">修改</a>
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
                            <li><a href="/recsys/back/group/list/?page=${page - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..groupPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/recsys/back/group/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if page gte groupPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/recsys/back/group/list?page=${page + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>