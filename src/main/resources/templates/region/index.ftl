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
                    <form role="form" method="post" action="/recsys/back/region/save">
                        <div class="form-region">
                            <label>经度</label>
                            <input name="regionLongi" type="text" class="form-control"
                                   value="${(region.regionLongi)!''}"/>
                        </div>
                        <div class="form-region">
                            <label>纬度</label>
                            <input name="regionLati" type="text" class="form-control"
                                   value="${(region.regionLati)!''}"/>
                        </div>
                        <input hidden type="text" name="regionId" value="${(region.regionId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>