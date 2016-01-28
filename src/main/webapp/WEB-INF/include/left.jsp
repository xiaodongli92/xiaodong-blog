<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<script>
$(function(){

})
</script>

<!--left-->
<div class="col-sm-3">
    <div class="col-nav-list">
        <div class="menu-group">
            <h3 class="menu-title">个人设置</h3>
            <ul class="collapse-ul collapse-show">
                <li ${lastUri=="userInfo.do"?'class="collapse-active"':''}><a href='${ctx}/user/userInfo.do'>个人信息</a></li>
            </ul>
        </div>
        <div class="btn-scroll-bar"></div>
    </div>
</div>
<!--left_end-->
