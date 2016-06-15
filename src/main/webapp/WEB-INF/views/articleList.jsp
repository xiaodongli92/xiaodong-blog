<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <title>文章列表</title>
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
                <h4 style="float:left">我的文章</h4>
                <button type="button" id="publish" class="btn btn-default" style="float: right">
                    发表文章
                </button>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 100%">
            <tr>
                <th width="5%" class="alignCenter">序号</th>
                <th width="20%" class="alignCenter">标题</th>
                <th width="15%" class="alignCenter">作者</th>
                <th width="30%" class="alignCenter">内容</th>
                <th width="10%" class="alignCenter">状态</th>
                <th width="10%" class="alignCenter">最后更新时间</th>
                <th width="10%" class="alignCenter">创建时间</th>
            </tr>
            <input type="hidden" id="codeSetValue" value="${codeSet.codeSetValue}">
            <c:forEach var="article" items="${list}">
                <tr class="my-tr">
                    <td>${article.id}</td>
                    <td>
                        <a href="javascript:void(0)" class="detail">
                            ${article.title}
                        </a>
                    </td>
                    <td>${article.authorName}</td>
                    <td>${article.content}</td>
                    <td>${article.status==1?"启用":"停用"}</td>
                    <td><fmt:formatDate value="${article.updateTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!--right-->
</div>
</body>
<script>
$(function(){
    initPage();
    $("#publish").click(function(){
        location.href='${ctx}/article/toPage.do';
    })
    $(".detail").click(function(){
        var id = $(this).parents("td").prev().html();
        location.href='${ctx}/article/detail.do?id='+id;
    })
    $(".my-tr").each(function(i,o){
        $(this).children("td").eq(0).html(i+1);
    })
})
function initPage(){
    $(".my-tr").each(function(){
        var content = $(this).find("td").eq(3).html();
        if (content.length>30){
            $(this).find("td").eq(3).html(content.substring(0,30)+"...");
        }
    })
}
</script>
</html>