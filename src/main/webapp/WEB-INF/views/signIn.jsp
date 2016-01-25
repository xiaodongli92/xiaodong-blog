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
            <p class="navbar-text navbar-left">后台管理系统登录</p>
        </div>
    </div>
</nav>
<div class="login-from-box">
    <form class="form-horizontal" role="form" method="post" action="login" id="form1">
        <div class="form-group">
            <label for="userName" class="col-xs-4 control-label">用户名：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="userName" placeholder="用户名">
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
</html>