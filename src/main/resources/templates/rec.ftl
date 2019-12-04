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
                                <li class="active" style="pointer-events: none;">
                                    <a href="/recsys/recs">推荐</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" method="post" action="/recsys/search">
                                <div class="form-group">
                                    <input name="searchContent" type="text" class="form-control" placeholder="搜索"/>
                                </div>
                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="#">登出</a>
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
                                        <h2>${rec.eventName}</h2>
                                    </td>
                                    <td>
                                        <p>位置：${rec.regionId}<br/>时间：${rec.eventTime}</p>
                                    </td>
                                    <td>
                                        <p><a class="btn" href="#">查看详情 »</a></p>
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