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
        <form action="${ctx}/user/saveUserInfo.do" method="post">
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
                <tr>
                    <td>邮箱</td>
                    <td><input type="text" class="form-control" style="width: 200px;" name="email"></td>
                    <td>
                        <select class="form-control" style="width: 100px;">
                            <option value="0">隐藏</option>
                            <option value="1">公开</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <button type="button" class="btn btn-large btn-block" style="width: 300px;">保存</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--right-->
</div>
</body>
</html>