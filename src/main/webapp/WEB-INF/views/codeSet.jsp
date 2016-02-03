<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <link rel="stylesheet" href="${ctx}/static/css/user_info.css" type="text/css"/>
</head>

<body>
<!-- top -->
<jsp:include page="/WEB-INF/include/top.jsp"/>

<div class="container" style="position: relative;">
    <!--left-->
    <jsp:include page="/WEB-INF/include/left.jsp"/>
    <!-- right -->
    <div class="col-sm-9">

        <div class="panel panel-default">
            <div class="panel-heading" style="overflow:auto;">
                <h4 style="float:left">字典配置</h4>
            </div>
        </div>

    </div>
    <!--right-->
</div>
</body>
<script>
    $(function(){
        $.ajax({
            url: '${ctx}/bs/getProvinceMap.do',
            type: 'post',
            dataType: 'json',
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    var map = data.data;
                    $.each(map, function (key, values) {
                        $("#provinceCode").append("<option value='" + key + "'>" + values + "</option>");
                    });
                }
            }
        })
    })
</script>
</html>