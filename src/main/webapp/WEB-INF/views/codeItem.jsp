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
                <h4 style="float:left">代码列表【${codeSet.codeSetName}】</h4>
                <button type="button" id="addPop" data-toggle="modal" data-target="#add" class="btn btn-default" style="float: right">
                    添加代码
                </button>
                <button type="button" id="goBack" class="btn btn-default" style="float: right">
                    返回上级
                </button>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 100%">
            <tr>
                <th width="5%" class="alignCenter"><input id="all-checkbox" type="checkbox"></th>
                <th width="5%" class="alignCenter">序号</th>
                <th width="10%" class="alignCenter">上级代码</th>
                <th width="15%" class="alignCenter">代码名称</th>
                <th width="15%" class="alignCenter">代码名称(2)</th>
                <th width="10%" class="alignCenter">代码值</th>
                <th width="25%" class="alignCenter">备注</th>
                <th width="5%" class="alignCenter">状态</th>
                <th width="10%" class="alignCenter">操作</th>
            </tr>
            <input type="hidden" id="codeSetValue" value="${codeSet.codeSetValue}">
            <c:forEach var="codeItem" items="${codeItems}">
                <tr>
                    <td><input type="checkbox" class="my-checkbox" value="${codeItem.id}"></td>
                    <td>${codeItem.seq}</td>
                    <td>${codeItem.parentCode}</td>
                    <td>
                        <a href class="update" data-toggle="modal" data-target="#add">${codeItem.codeName}</a>
                    </td>
                    <td>${codeItem.codeName2}</td>
                    <td>${codeItem.codeValue}</td>
                    <td>${codeItem.remark}</td>
                    <td>${codeItem.status==1?"启用":"停用"}</td>
                    <td>
                        <a href="javascript:void(0)" class="delete">删除</a>
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
                <h4 class="modal-title fydTitle" style="text-align: left;">添加代码</h4>
            </div>
            <br>
            <input type="hidden" id="id" class="initPop">
            <div class="form-group">
                <label for="codeName" class="col-sm-4 control-label">代码名称：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control initPop" style="width: 300px;" id="codeName">
                </div>
            </div>
            <div class="form-group">
                <label for="codeName2" class="col-sm-4 control-label">代码名称2：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control initPop" style="width: 300px;" id="codeName2">
                </div>
            </div>
            <div class="form-group">
                <label for="codeValue" class="col-sm-4 control-label">代码值：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control initPop" style="width: 300px;" id="codeValue">
                </div>
            </div>
            <div class="form-group">
                <label for="parentCode" class="col-sm-4 control-label">上级代码：</label>
                <div class="col-sm-8">
                    <select style="width: 300px;" class="form-control" id="parentCode">
                        <option value="">请选择</option>
                    </select>
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
var parentMap;
    $(function(){
        var codeSet = $("#codeSetValue").val();
        $("#addSave").click(function(){
            var id = $("#id").val();
            if (id == ''){
                id=0;
            }
            if (codeSet == ''){
                alert("获取代码集失败，请刷新页面");
                return false;
            }
            var codeName = $("#codeName").val();
            var codeName2 = $("#codeName2").val();
            var codeValue = $("#codeValue").val();
            var parentCode = $("#parentCode").val();
            var seq = $("#seq").val();
            var remark = $("#remark").val();
            var status = $("#status").val();
            if (codeName==''){
                alert("代码名称不能为空");
                $("#codeName").focus();
                return false;
            }
            if (codeValue==''){
                alert("代码值不能为空");
                $("#codeValue").focus();
                return false;
            }
            $.ajax({
                url: '${ctx}/bs/saveCodeItem.do',
                type: 'post',
                dataType: 'json',
                data: {
                    id:id,
                    codeSet: codeSet,
                    parentCode: parentCode,
                    codeName: codeName,
                    codeName2: codeName2,
                    codeValue: codeValue,
                    seq: seq,
                    status: status,
                    remark: remark
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.reload();
                    }
                }
            })
        })
        $("#addPop").click(function(){
            initPop();
            initSelect(codeSet,"");
        })
        $("#goBack").click(function(){
            location.href='${ctx}/bs/codeSet.do'
        })
        $(".update").click(function(){
            initPop();
            var id = $(this).parents("tr").children("td").eq(0).find("input").val();
            var seq = $(this).parents("tr").children("td").eq(1).html();
            var parentCode = $(this).parents("tr").children("td").eq(2).html();
            var codeName = $(this).html();
            var codeName2 = $(this).parents("tr").children("td").eq(4).html();
            var codeValue = $(this).parents("tr").children("td").eq(5).html();
            var remark = $(this).parents("tr").children("td").eq(6).html();
            var status = $(this).parents("tr").children("td").eq(7).html();
            $("#id").val(id);
            $("#seq").val(seq);
            $("#parentCode").val(parentCode);
            $("#codeName").val(codeName);
            $("#codeName2").val(codeName2);
            $("#codeValue").val(codeValue);
            $("#remark").val(remark);
            $("#status").val(status=='启用'?1:0);
            initSelect(codeSet,codeValue);
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
                url: '${ctx}/bs/deleteCodeItem.do',
                type: 'post',
                dataType: 'json',
                data: {
                    id:id
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.reload();
                    }
                }
            })
        })
        $("#all-checkbox").bind("click",function(){
            if ($(this).is(':checked')){
                $(".my-checkbox").prop('checked',true);
            }else{
                $(".my-checkbox").prop('checked',false);
            }
        })
    })
    function initPop(){
        $(".initPop").val("");
        $("#seq").val(1);
        $("#status").val(1);
    }
    function initSelect(codeSet,codeValue){
        $("#parentCode").html("<option value=''>请选择</option>")
        $.ajax({
            url: '${ctx}/bs/parentCodeMap.do',
            type: 'post',
            dataType: 'json',
            data: {
                codeSetValue:codeSet
            },
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    parentMap = data.data;
                    $.each(parentMap, function (key, values) {
                        if (key!=codeValue){
                            $("#parentCode").append("<option value='" + key + "'>" + values + "</option>");
                        }
                    });
                }
            }
        })
    }
</script>
</html>