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
                <button type="button" id="importPop" data-toggle="modal" data-target="#import" class="btn btn-default" style="float: right">
                    导入json
                </button>
                <button type="button" id="export" class="btn btn-default" style="float: right">
                    导出json
                </button>
                <button type="button" id="addPop" data-toggle="modal" data-target="#add" class="btn btn-default" style="float: right">
                    添加代码集
                </button>
            </div>
        </div>
        <table class="table table-hover alignCenter" style="width: 100%">
            <tr>
                <th width="5" class="alignCenter"><input id="all-checkbox" type="checkbox"></th>
                <th width="5%" class="alignCenter">序号</th>
                <th width="20%" class="alignCenter">代码集名称</th>
                <th width="10%" class="alignCenter">代码集标识</th>
                <th width="10%" class="alignCenter">状态</th>
                <th width="30%" class="alignCenter">备注</th>
                <th width="20%" class="alignCenter">操作</th>
            </tr>
            <c:forEach var="codeSet" items="${codeSets}">
                <tr>
                    <td><input type="checkbox" value="${codeSet.id}" class="my-checkbox"></td>
                    <td>${codeSet.seq}</td>
                    <td>
                        <a href class="update" data-toggle="modal" data-target="#add">${codeSet.codeSetName}</a>
                    </td>
                    <td>${codeSet.codeSetValue}</td>
                    <td>${codeSet.status}</td>
                    <td>${codeSet.remark}</td>
                    <td>
                        <a href="javascript:void(0)" class="detail">进入代码列表</a>
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
<div class="modal fade form-horizontal" id="import" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title fydTitle" style="text-align: left;">导入代码集</h4>
            </div>
            <br><br><br>
            <form method="post" enctype="multipart/form-data" id="import-form" action="${ctx}/bs/importCodeItem.do">
                <div class="form-group">
                    <label for="file" class="col-sm-4 control-label">代码集文件(只能是txt文件)：</label>
                    <div class="col-sm-8">
                        <input type="file" class="form-control initPop" style="width: 300px;" name="file" id="file">
                    </div>
                </div>
                <br><br><br>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="button" class="btn btn-default" id="submit-import">上传</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 弹窗end -->
<div id="zhezhao" style="background-color:#7B7B7B;position:fixed;left:0;top:0;width:100%;height:100%;z-index:99999;display:none;opacity:0.50" ></div>
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
        var checkFileStatus = function (fileName) {
            jQuery.ajax({
                url:'${ctx}/bs/checkFile.do',
                type:'GET',
                dataType:'json',
                data:{
                    fileName:fileName,
                    random: Math.random()
                },
                success : function(data) {
                    console.log(data.errorCode);
                    if(data.errorCode==0) {
                        window.clearInterval(timer);
                        $("#zhezhao").hide();
                        location.href="${ctx}/bs/download.do?fileName="+fileName;
                    } else {
                        var timer = setTimeout(function(){
                            checkFileStatus(fileName);
                        },500)
                    }
                }
            });
        };
        $("#export").click(function(){
            var codeSets = "";
            $(".my-checkbox").each(function(){
                if ($(this).prop("checked")){
                    codeSets += ($(this).parents("tr").children("td").eq(3).html()+",");
                }
            })
            if (codeSets == ""){
                alert("请选择导出的代码集");
                return false;
            }
            codeSets = codeSets.substring(0,codeSets.length-1);
            console.log(codeSets);
            $.ajax({
                url: '${ctx}/bs/exportCodeItem.do',
                type: 'post',
                dataType: 'json',
                data: {
                    codeSets:codeSets
                },
                success: function(data) {
                    if (data.errorCode == 0) {
                        alert("文件生成中，生成时间可能较长，请耐心等待");
                        $("#zhezhao").show();
                        checkFileStatus(data.data.fileName);
                    } else {
                        alert (data.errorMessage);
                    }
                },
                error: function(){
                    alert ("系统嗝屁了,请稍后再试");
                }
            });
        })
        $("#submit-import").click(function(){
            $("#import-form").submit();
        })
        $("#all-checkbox").bind("click",function(){
            if ($(this).is(':checked')){
                $(".my-checkbox").prop('checked',true);
            }else{
                $(".my-checkbox").prop('checked',false);
            }
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
            var isOrNo = confirm("此代码集所有内容将会删除，确认要删除？");
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
        $(".detail").click(function(){
            var id = $(this).parents("tr").children("td").eq(0).find("input").val();
            if (id==''){
                alert("进入代码列表失败，请刷新页面");
                return false;
            }
            location.href='${ctx}/bs/codeItem.do?id='+id;
        })
    })
    function initPop(){
        $(".initPop").val("");
        $("#seq").val(1);
        $("#status").val(1);
    }
</script>
</html>