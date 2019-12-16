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
                                <li class="active">
                                    <a href="/recsys/index">主页</a>
                                </li>
                                <li>
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

                            <#list recList as rec>
                                <tr>
                                    <td>
                                        <h3>${rec.eventName}</h3>
                                    </td>
                                    <td>
                                        <p>${rec.eventContent}</p>
                                    </td>
                                    <td>
                                        <a style="text-align:right;"
                                           href="/recsys/event/detail?eventId=${rec.eventId}">查看详情 ></a>
                                    </td>
                                </tr>
                            </#list>

                        </div>
                        <div class="col-md-4 column">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>