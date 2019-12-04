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
                            <th>用户ID</th>
                            <th>密码</th>
                            <th>用户名</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list userPage.content as user>
                            <tr>
                                <td>${user.userId}</td>
                                <td>${user.userPw}</td>
                                <td>${user.userName}</td>
                                <td>${user.createTime}</td>
                                <td>${user.updateTime}</td>
                                <td><a href="/recsys/back/user/index?userId=${user.userId}&userPw=${user.userPw}">修改</a>
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
                            <li><a href="/recsys/back/user/list/?page=${page - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..userPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/recsys/back/user/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if page gte userPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/recsys/back/user/list?page=${page + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>