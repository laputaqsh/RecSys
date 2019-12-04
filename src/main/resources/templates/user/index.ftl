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
                    <form role="form" method="post" action="/recsys/back/user/save">
                        <div class="form-group">
                            <label>密码</label>
                            <input name="userPw" type="text" class="form-control"
                                   value="${(user.userPw)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input name="userName" type="text" class="form-control"
                                   value="${(user.userName)!''}"/>
                        </div>
                        <input hidden type="text" name="userId" value="${(user.userId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>