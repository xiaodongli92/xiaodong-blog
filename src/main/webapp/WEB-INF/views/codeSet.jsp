<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <h4 style="float:left">字典配置</h4>
                <button type="button" id="addPop" data-toggle="modal" data-target="#add" class="btn btn-default" style="float: right">
                    添加代码集
                </button>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 100%">
            <tr>
                <th width="5" class="alignCenter"><input type="checkbox"></th>
                <th width="5%" class="alignCenter">序号</th>
                <th width="20%" class="alignCenter">代码集名称</th>
                <th width="10%" class="alignCenter">代码集标识</th>
                <th width="10%" class="alignCenter">状态</th>
                <th width="30%" class="alignCenter">备注</th>
                <th width="20%" class="alignCenter">操作</th>
            </tr>
            <c:forEach var="codeSet" items="${codeSets}">
                <tr>
                    <td><input type="checkbox" value="${codeSet.id}"></td>
                    <td>${codeSet.seq}</td>
                    <td>
                        <a href class="update" data-toggle="modal" data-target="#add">${codeSet.codeSetName}</a>
                    </td>
                    <td>${codeSet.codeSetValue}</td>
                    <td>${codeSet.status}</td>
                    <td>${codeSet.remark}</td>
                    <td>
                        <a href>进入代码列表</a>
                        <a href="javascript:void(0)" class="delete" style="margin-left: 20px;">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!--right-->
</div>
<!-- 弹窗start -->
<div class="modal fade form-horizontal" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title fydTitle" style="text-align: left;">添加代码集</h4>
            </div>
            <br>
            <input type="hidden" id="id" class="initPop">
            <div class="form-group">
                <label for="codeSetName" class="col-sm-4 control-label">代码集名称：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control initPop" style="width: 300px;" id="codeSetName">
                </div>
            </div>
            <div class="form-group">
                <label for="codeSetValue" class="col-sm-4 control-label">代码集标识：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control initPop" style="width: 300px;" id="codeSetValue">
                </div>
            </div>
            <div class="form-group">
                <label for="seq" class="col-sm-4 control-label">顺序号：</label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" style="width: 300px;" id="seq">
                </div>
            </div>
            <div class="form-group">
                <label for="status" class="col-sm-4 control-label">状态：</label>
                <div class="col-sm-8">
                    <select style="width: 300px;" class="form-control" id="status">
                        <option value="1">启用</option>
                        <option value="0">停用</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-sm-4 control-label">说明：</label>
                <div class="col-sm-8">
                    <textarea style="width: 300px;height: 60px;" class="form-control initPop" id="remark"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <button style="float: left" type="button" id="addSave" class="btn btn-default">
                        保存
                    </button>
                    <button style="float: left;margin-left: 95px;" type="button" class="btn btn-default"
                            data-dismiss="modal" aria-label="Close">
                        关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 弹窗end -->
</body>
<script>
    $(function(){
        $("#addSave").click(function(){
            var id = $("#id").val();
            if (id == ''){
                id=0;
            }
            var codeSetName = $("#codeSetName").val();
            var codeSetValue = $("#codeSetValue").val();
            var seq = $("#seq").val();
            var status = $("#status").val();
            var remark = $("#remark").val();
            $.ajax({
                url: '${ctx}/bs/saveCodeSet.do',
                type: 'post',
                dataType: 'json',
                data: {
                    id:id,
                    codeSetName: codeSetName,
                    codeSetValue: codeSetValue,
                    seq: seq,
                    status: status,
                    remark: remark
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.href='${ctx}/bs/codeSet.do';
                    }
                }
            })
        })
        $("#addPop").click(function(){
            initPop();
        })
        $(".update").click(function(){
            initPop();
            var id = $(this).parents("tr").children("td").eq(0).find("input").val();
            var codeSetName = $(this).html();
            var seq = $(this).parents("tr").children("td").eq(1).html();
            var codeSetValue = $(this).parents("tr").children("td").eq(3).html();
            var status = $(this).parents("tr").children("td").eq(4).html();
            var remark = $(this).parents("tr").children("td").eq(5).html();
            $("#id").val(id);
            $("#codeSetName").val(codeSetName);
            $("#codeSetValue").val(codeSetValue);
            $("#seq").val(seq);
            $("#status").val(status);
            $("#remark").val(remark);
        })
        $(".delete").click(function(){
            var id = $(this).parents("tr").children("td").eq(0).find("input").val();
            console.log(id);
            if (id==''){
                alert("删除失败，请刷新页面");
                return false;
            }
            var isOrNo = confirm("确认要删除？");
            if (!isOrNo){
                return false;
            }
            $.ajax({
                url: '${ctx}/bs/deleteCodeSet.do',
                type: 'post',
                dataType: 'json',
                data: {
                    id:id
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.href='${ctx}/bs/codeSet.do';
                    }
                }
            })
        })
    })
    function initPop(){
        $(".initPop").val("");
        $("#seq").val(1);
        $("#status").val(1);
    }
</script>
</html>