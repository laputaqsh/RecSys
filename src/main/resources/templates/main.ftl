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
                                <li class="active" style="pointer-events: none;">
                                    <a href="/recsys">主页</a>
                                </li>
                                <li>
                                    <form class="navbar-form navbar-mid" method="post" action="/recsys/rec">
                                        <input hidden type="number" name="userId" value="${userId}"/>
                                        <button type="submit" class="btn btn-default"
                                                style="background-color: Transparent; border-style :none;">推荐
                                        </button>
                                    </form>
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
                            <h2>
                                Heading
                            </h2>
                            <p>
                                Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus
                                commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam
                                porta sem malesuada magna mollis euismod. Donec sed odio dui.
                            </p>
                            <p>
                                <a class="btn" href="#">View details »</a>
                            </p>
                            <h2>
                                Heading
                            </h2>
                            <p>
                                Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus
                                commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam
                                porta sem malesuada magna mollis euismod. Donec sed odio dui.
                            </p>
                            <p>
                                <a class="btn" href="#">View details »</a>
                            </p>
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