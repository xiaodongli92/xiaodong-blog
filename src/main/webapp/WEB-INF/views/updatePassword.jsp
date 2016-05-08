<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <link rel="stylesheet" href="${ctx}/static/css/user_info.css" type="text/css"/>
    <style>
        .col-sm-9{
            font-size: 18px;
        }
    </style>
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
                <h4 style="float:left">修改密码</h4>
            </div>
        </div>
        <table class="table table-hover table-bordered table-striped" style="width: 80%;margin-left: 10%">
            <tr>
                <td class="left_tr">输入旧密码：</td>
                <td><input type="password" class="form-control" style="width: 300px;" id="oldPassword"></td>
            </tr>
            <tr>
                <td class="left_tr">设置新密码：</td>
                <td><input type="password" class="form-control" style="width: 300px;" id="newPassword1"></td>
            </tr>
            <tr>
                <td class="left_tr">重复新密码：</td>
                <td><input type="password" class="form-control" style="width: 300px;" id="newPassword2"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" id="save" class="btn btn-large btn-block" style="width: 300px;margin-left: 30%;">
                        保存
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <!--right-->
</div>
</body>
<script>
$(function(){
    $("#save").click(function(){
        var oldPassword = $("#oldPassword").val();
        var newPassword1 = $("#newPassword1").val();
        var newPassword2 = $("#newPassword2").val();
        if (oldPassword == ''){
            alert("请输入旧密码");
            $("#oldPassword").focus();
            return false;
        }
        if (newPassword1==''){
            alert("请输入设置新密码");
            $("#newPassword1").focus();
            return false;
        }
        if (newPassword2==''){
            alert("请输入重复新密码");
            $("#newPassword2").focus();
            return false;
        }
        if (newPassword1 != newPassword2){
            alert("两次新密码不一致，请重新输入");
            $("#newPassword1").focus();
            return false;
        }
        $.ajax({
            url: '${ctx}/user/updatePassword.do',
            type: 'post',
            dataType: 'json',
            data: {
                oldPassword: oldPassword,
                newPassword: newPassword1
            },
            success: function(data){
                console.log(data.errorCode);
                if (data.errorCode==1){
                    alert(data.errorMessage);
                }else{
                    alert("修改成功");
                    location.reload();
                }
            }
        })
    })
})
</script>
</html>