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
                    <form role="form" method="post" action="/recsys/back/event/save">
                        <div class="form-group">
                            <label>用户ID</label>
                            <input name="id" type="text" class="form-control"
                                   value="${(user.id)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>账号</label>
                            <input name="uid" type="textarea" class="form-control"
                                   value="${(user.uid)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>用户名</label>
                            <input name="name" type="number" class="form-control"
                                   value="${(user.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>头像</label>
                            <input name="avatar" type="text" class="form-control"
                                   value="${(user.avatar)!''}"/>
                        </div>
                        <input hidden type="text" name="user" value="${(user.id)!''}">
                        <!--为了修改的时候有id，但不想显示出来-->
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>