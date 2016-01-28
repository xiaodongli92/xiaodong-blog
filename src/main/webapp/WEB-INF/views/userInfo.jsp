<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
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
                <h4 style="float:left">个人信息</h4>
            </div>
        </div>
        <table class="table table-hover table-bordered" style="width: 50%;">
            <tr>
                <th>类型</th>
                <th>你的信息</th>
                <th>隐秘性</th>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" class="form-control" style="width: 200px;" name="name"></td>
                <td>
                    <select class="form-control" style="width: 100px;">
                        <option value="0">隐藏</option>
                        <option value="1">公开</option>
                    </select>
                </td>
            </tr>
        </table>
    </div>
    <!--right-->
</div>
</body>
</html>