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
            <table class="table table-hover table-bordered" style="width: 80%;margin-left: 10%">
                <tr>
                    <th class="left_tr">类型</th>
                    <th>你的信息</th>
                </tr>
                <tr>
                    <td class="left_tr">昵称</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="name"></td>
                </tr>
                <tr>
                    <td class="left_tr">真实姓名</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="realName"></td>
                </tr>
                <tr>
                    <td class="left_tr">邮箱</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="email" disabled></td>
                </tr>
                <tr>
                    <td class="left_tr">手机号</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="mobile"></td>
                </tr>
                <tr>
                    <td class="left_tr">注册时间</td>
                    <td><input type="text" class="form-control" style="width: 300px;" name="registerDate" disabled></td>
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
                        <select name="maritalStatus" class="form-control" style="width: 300px;">
                            <option value="">——请选择——</option>
                            <option value="1">单身</option>
                            <option value="2">求交往</option>
                            <option value="3">暗恋中</option>
                            <option value="4">暧昧中</option>
                            <option value="5">恋爱中</option>
                            <option value="6">订婚</option>
                            <option value="7">已婚</option>
                            <option value="8">分居</option>
                            <option value="9">单身</option>
                            <option value="10">单身</option>
                            <option value="99">单身</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="button" class="btn btn-large btn-block" style="width: 300px;margin-left: 30%;">保存</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--right-->
</div>
</body>
</html>