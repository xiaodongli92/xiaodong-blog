<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <title>文章详情</title>
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
                <h4 style="float:left">${article.title}</h4>
                <button type="button" onclick="javascript:history.go(-1)" class="btn btn-default" style="float: right">
                    返回
                </button>
                <button type="button" id="edit" class="btn btn-default" style="float: right;margin-right: 20px;">
                    编辑
                </button>
            </div>
        </div>
        <table class="table table-hover" style="width: 80%;margin-left: 10%;font-size: 20px;">
            <input id="articleId" type="hidden" value="${article.id}">
            <tr>
                <th style="width: 80px;">标题：</th>
                <td>${article.title}</td>
            </tr>
            <tr>
                <th>作者：</th>
                <td>${article.authorName}</td>
            </tr>
            <tr>
                <th>内容：</th>
                <td>
                    <span>${article.content}</span>
                </td>
            </tr>
        </table>
    </div>
    <!--right-->
</div>
</body>
<script>
$(function(){
    $("#edit").click(function(){
        var id = $("#articleId").val();
        location.href='${ctx}/article/toPage.do?id='+id;
    })
})
</script>
</html>