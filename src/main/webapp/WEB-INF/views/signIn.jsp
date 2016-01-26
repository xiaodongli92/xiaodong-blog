<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <%@ include file="/WEB-INF/include/head.inc" %>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="col-xs-7">
            <p class="navbar-text navbar-left">博客登录</p>
        </div>
    </div>
</nav>
<div class="login-from-box">
    <form class="form-horizontal" role="form" method="post" action="login" id="form1">
        <div class="form-group">
            <label for="email" class="col-xs-4 control-label">邮箱：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="email" placeholder="邮箱">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-xs-4 control-label">密码：</label>
            <div class="col-xs-8">
                <input type="password" class="form-control" id="password" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <div class=" col-xs-10 col-xs-offset-2"
                 style="background:#F7F7E7;margin-bottom:5px; font-size:12px; line-height:30px">
            </div>
        </div>
        <div class="form-group">
            <div class=" col-xs-12">
                <button type="button" id="btn1" class="btn btn-lg btn-primary btn-submit">登陆</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    $(function(){
        $("#btn1").click(function(){
            var email = $("#email").val();
            var password = $("#password").val();
            if (email==''){
                alert("用户名不能为空");
                $("#userName").focus();
                return false;
            }
            if (password==''){
                alert("密码不能为空");
                $("#password").focus();
                return false;
            }
            $.ajax({
                url: '${ctx}/signIn.do',
                type: 'post',
                dataType: 'json',
                data: {
                    email: email,
                    password: password
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.href='${ctx}/main.do';
                    }
                }
            })
        })
    })
</script>
</html>