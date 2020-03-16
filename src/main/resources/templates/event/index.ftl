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
                            <label>名称</label>
                            <input name="title" type="text" class="form-control"
                                   value="${(event.title)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>内容</label><br/>
                            <textarea rows="10" cols="100">${(event.content)!''}</textarea>
                            <#--  <input name="content" type="textarea" rows="10" class="form-control"
                                   value="${(event.content)!''}"/>  -->
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="category" type="text" class="form-control"
                                   value="${(event.category)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>开始时间</label>
                            <input name="time" type="text" class="form-control"
                                   value="${(event.time)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input name="address" type="text" class="form-control"
                                   value="${(event.address)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>经纬度</label>
                            <input name="geo" type="text" class="form-control"
                                   value="${(event.geo)!''}"/>
                        </div>
                        <input hidden type="text" name="event" value="${(event.id)!''}">
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