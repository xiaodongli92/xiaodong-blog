<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <title>发布文章</title>
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
                <h4 style="float:left">发布文章</h4>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 80%;margin-left: 10%;font-size: 20px;">
            <tr>
                <th>标题</th>
                <td><input type="text" class="form-control" style="width: 300px;"></td>
            </tr>
            <tr>
                <th>内容</th>
                <td><textarea type="text" class="form-control" style="width: 600px;height: 600px;"></textarea></td>
            </tr>
            <tr>
                <th>显示</th>
                <td>
                    <select class="form-control" style="width: 200px;">
                        <option value="1">公开</option>
                        <option value="0">隐私</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" class="btn btn-default" style="width: 200px;">保存</button>
                </td>
            </tr>
        </table>
    </div>
    <!--right-->
</div>
</body>
</html>