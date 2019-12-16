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
                                    <a href="/recsys">主页</a>
                                </li>
                                <li>
                                    <a href="/recsys/event/list">活动</a>
                                </li>
                                <li>
                                    <a href="/recsys/group/list">群组</a>
                                </li>
                                <li>
                                    <a href="/recsys/rank">排行</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" method="post"
                                  action="/recsys/event/search">
                                <div class="form-group">
                                    <input name="searchContent" type="text" class="form-control" placeholder="请输入关键词"/>
                                </div>
                                <button type="submit" class="btn btn-default">搜索</button>
                            </form>

                        </div>
                    </nav>
                    <div class="row clearfix">
                        <div class="col-md-8 column">
                            <div class="jumbotron">
                                <h1>
                                    Join us!
                                </h1>
                                <p>
                                    This is a template for a simple marketing or informational website. It includes a
                                    large callout called the hero unit and three supporting pieces of content. Use it as
                                    a starting point to create something more unique.
                                </p>
                                <p>
                                    <a class="btn btn-primary btn-large" href="#">Learn more</a>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-4 column">
                            <form role="form" method="post" action="/recsys/login">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">用户名</label>
                                    <input name="userId"
                                           type="text"
                                           class="form-control"
                                           id="exampleInputEmail1"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">密码</label>
                                    <input name="userPw" type="password"
                                           class="form-control"
                                           id="exampleInputPassword1"/>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox"/>记住我</label>
                                </div>
                                <button type="submit" class="btn btn-default">登录</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>