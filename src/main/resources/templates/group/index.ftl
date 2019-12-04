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
                    <form role="form" method="post" action="/recsys/back/group/save">
                        <div class="form-group">
                            <label>群族名</label>
                            <input name="groupName" type="text" class="form-control"
                                   value="${(group.groupName)!''}"/>
                        </div>
                        <input hidden type="text" name="groupId" value="${(group.groupId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>