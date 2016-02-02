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
                <h4 style="float:left">个人信息</h4>
            </div>
        </div>
        <form action="${ctx}/user/saveUserInfo.do" method="post">
            <table class="table table-hover table-bordered table-striped" style="width: 80%;margin-left: 10%">
                <input type="hidden" name="id" value="">
                <tr>
                    <th class="left_tr">类型</th>
                    <th>你的信息</th>
                </tr>
                <tr>
                    <td class="left_tr">昵称</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="name" value="${user.name}"></td>
                </tr>
                <tr>
                    <td class="left_tr">真实姓名</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="realName" value="${user.realName}"></td>
                </tr>
                <tr>
                    <td class="left_tr">生日</td>
                    <td><input type="text" class="form-control my-date" style="width: 300px;" name="birthday" value="${userInfo.birthday}"></td>
                </tr>
                <tr>
                    <td class="left_tr">邮箱</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="email" value="${user.email}" disabled></td>
                </tr>
                <tr>
                    <td class="left_tr">手机号</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="mobile"></td>
                </tr>
                <tr>
                    <td class="left_tr">注册时间</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="registerDate" disabled
                        value="<fmt:formatDate value="${user.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"> </td>
                </tr>
                <tr>
                    <td class="left_tr">所在地</td>
                    <td>
                        <select name="provinceCode" class="form-control" style="width: 150px;float: left">
                            <option>省份</option>
                        </select>
                        <select name="cityCode" class="form-control" style="width: 150px;float: left">
                            <option>城市</option>
                        </select>
                        <select name="countyCode" class="form-control" style="width: 150px;float: left">
                            <option>区县</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">性别</td>
                    <td>
                        <input type="radio" name="gender" value="1">男
                        &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="gender" value="2">女
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">性取向</td>
                    <td>
                        <input type="radio" name="sexualOrientation" value="1">男
                        &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="sexualOrientation" value="2">女
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">感情状况</td>
                    <td>
                        <select name="maritalStatus" class="form-control" style="width: 300px;text-align: center">
                            <option value="">请选择</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">血型</td>
                    <td>
                        <select name="bloodType" class="form-control" style="width: 300px;text-align: center">
                            <option value="">请选择</option>
                            <option value="1">A型</option>
                            <option value="2">B型</option>
                            <option value="3">AB型</option>
                            <option value="4">O型</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">简介</td>
                    <td>
                        <textarea type="text" class="form-control" style="width: 300px;height: 70px;" name="profile"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="button" class="btn btn-large btn-block" style="width: 300px;margin-left: 30%;">
                            保存
                        </button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--right-->
</div>
</body>
<script>
    $(".my-date").datepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked',
        language: 'zh-CN'
    });
</script>
</html>