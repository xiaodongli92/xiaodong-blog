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
                <button type="button" id="my-article" class="btn btn-default" style="float: right">
                    我的文章
                </button>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 80%;margin-left: 10%;font-size: 20px;">
            <input type="hidden" value="${article.id}" id="articleId">
            <tr>
                <th>标题</th>
                <td>
                    <input type="text" class="form-control" style="width: 300px;" id="title" value="${article.title}">
                </td>
            </tr>
            <tr>
                <th>类别</th>
                <td>
                    <select class="form-control" style="width: 300px;" id="type" data-value="${article.typeCode}">
                        <option value="">请选择</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>内容</th>
                <td>
                    <textarea type="text" class="form-control" style="width: 600px;height: 600px;" id="content">${article.content}</textarea>
                </td>
            </tr>
            <tr>
                <th>显示</th>
                <td>
                    <select class="form-control" style="width: 200px;" id="status">
                        <option value="1" ${article.status==1?"selected":""}>公开</option>
                        <option value="0" ${article.status==0?"selected":""}>隐私</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" id="save" class="btn btn-default" style="width: 200px;">保存</button>
                </td>
            </tr>
        </table>
    </div>
    <!--right-->
</div>
</body>
<script>
$(function(){
    initSelect("articleType","type");
    $("#save").click(function(){
        var id = $("#articleId").val();
        var title = $("#title").val();
        var typeCode = $("#type").val();
        var content = $("#content").val();
        var status = $("#status").val();
        if (title == ''){
            alert("请填写标题");
            $("#title").focus();
            return false;
        }
        if (typeCode == ''){
            alert("请选择类别");
            $("#type").focus();
            return false;
        }
        if (content == ''){
            alert("请填写内容");
            $("#content").focus();
            return false;
        }
        if (id == '') {
            id = 0;
        }
        $.ajax({
            url: '${ctx}/article/saveArticle.do',
            type: 'post',
            dataType: 'json',
            data: {
                id: id,
                title: title,
                typeCode: typeCode,
                content: content,
                status: status
            },
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    location.href='${ctx}/article/list.do';
                }
            }
        })
    })
    $("#my-article").click(function(){
        location.href='${ctx}/article/list.do';
    })
})

function initSelect(codeSetValue,selectId){
    $.ajax({
        url: '${ctx}/bs/codeItemMap.do',
        type: 'post',
        dataType: 'json',
        data: {
            codeSetValue: codeSetValue
        },
        success: function(data){
            if (data.errorCode==1){
                alert(data.errorMessage);
            } else {
                var articleTypeMap=data.data;
                $.each(articleTypeMap,function(key,value){
                    $("#"+selectId).append("<option value="+key+">"+value+"</option>");
                })
                var typeCodeValue = $("#type").attr("data-value");
                $("#type").val(typeCodeValue);
            }
        }
    })
}
</script>
</html>