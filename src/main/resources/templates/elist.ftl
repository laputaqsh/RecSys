<html>
<#include "common/header.ftl">

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <nav class="navbar navbar-default navbar-static-top" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1"><span
                                        class="sr-only">导航栏</span><span class="icon-bar"></span><span
                                        class="icon-bar"></span><span class="icon-bar"></span></button>
                            <a class="navbar-brand" href="/recsys">RecSys</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="/recsys/index">主页</a>
                                </li>
                                <li class="active">
                                    <a href="/recsys/event/list">活动</a>
                                </li>
                                <li>
                                    <a href="/recsys/group/list">群组</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" method="post"
                                  action="/recsys/event/search">
                                <div class="form-group">
                                    <input name="searchContent" type="text" class="form-control" placeholder="请输入关键词"/>
                                </div>
                                <button type="submit" class="btn btn-default">搜索</button>
                            </form>

                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的<strong
                                                class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">我创建的活动</a>
                                        </li>
                                        <li>
                                            <a href="#">我加入的群组</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="/recsys/logout"><font color="red">退出登录</font></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>

                        </div>
                    </nav>
                    <div class="row clearfix">
                        <div class="col-md-2 column">
                        </div>
                        <div class="col-md-6 column">

                            <#list eventPage.content as event>

                                <tr>
                                    <td>
                                        <h3>${event.eventName}</h3>
                                    </td>
                                    <td>
                                        <p>${event.eventContent}</p>
                                    </td>
                                    <td>
                                        <a style="text-align:right;"
                                           href="/recsys/event/detail?eventId=${event.eventId}">查看详情 ></a>
                                    </td>
                                </tr>

                            </#list>

                            <ul class="pagination pull-right">
                                <li><a href="#">举办</a></li>
                                <li><a href="/recsys/event/list?page=1&size=${size}">首页</a></li>
                                <#if page lte 1>
                                    <li class="disabled"><a href="#">上一页</a></li>
                                <#else>
                                    <li><a href="/recsys/event/list?page=${page - 1}&size=${size}">上一页</a></li>
                                </#if>

                                <#list 1..eventPage.getTotalPages() as index>
                                    <#if page == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/recsys/event/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>

                                <#if page gte eventPage.getTotalPages()>
                                    <li class="disabled"><a href="#">下一页</a></li>
                                <#else>
                                    <li><a href="/recsys/event/list?page=${page + 1}&size=${size}">下一页</a></li>
                                </#if>
                                <li><a href="/recsys/event/list?page=${eventPage.getTotalPages()}&size=${size}">尾页</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-4 column">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>