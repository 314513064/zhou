<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
    String path = request.getContextPath(); 
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>

<head lang="en">
	<base href="<%=basePath%>"> 
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>当得法律</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
    <link rel="stylesheet/less" href="css/index.less"/>
    <script src="lib/js/vue.min.js"></script>
</head>
<body>
<div class="wrap">
    <div class="materialHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 返回</a>
        <h3>输出成果</h3>
        <span class="glyphicon glyphicon-plus" @click="change"></span>
    </div>
    <div class="newMaterial" v-show= 'isShow'>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/pictureUI3">图片上传</a></div>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/documentUI3">文档上传</a></div>
        <div id="result" @click='change'>删除</div>
    </div>

    <div id="materialItem">
        <ul>
        <c:forEach items="${materials}" var ="material">
            <li>
                <a href="case/${casee.id}/operation/${operation_id}/material/${material.id}" style="color: black">
                    <input type="checkbox" value="${material.id}" />
                    <c:choose >
                    	<c:when test="${material.type==2}">委托协议</c:when>
                    	<c:when test="${material.type==3}">授权协议</c:when>
                    	<c:when test="${material.type==4}">委托协议</c:when>
                    	<c:when test="${material.type==5}">出庭函</c:when>
                    	<c:when test="${material.type==6}">我方证据</c:when>
                    	<c:when test="${material.type==7}">他方证据</c:when>
                    	<c:when test="${material.type==10}">传票</c:when>
                    	<c:when test="${material.type==11}">代理词</c:when>
                    	<c:when test="${material.type==12}">庭审记录</c:when>
                    	<c:when test="${material.type==13}">判决书</c:when>
                    	<c:when test="${material.type==14}">结案陈词</c:when>
                    	<c:when test="${material.type==15}">结案文件</c:when>
                    </c:choose>
                    <i class="lastTime" style="color:black;opacity:0.6">
                    <input type="hidden" v-model='type=${material.type}'>
                       	上传时间： <fmt:formatDate value="${material.create_time}" type="both" />
                    </i>
                </a>
            </li>
            </c:forEach>

        </ul>
    </div>
	<div class='error'></div>
    <div class="nav">
        <div><a href="users/${user.id}/works"><span class="glyphicon glyphicon-time"></span>任务</a></div>
        <div><a href="casetype"><span class="glyphicon glyphicon-list"></span>case</a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-plus"></span></a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-send"></span>讨论</a></div>
        <div><a href="users/${user.id}/my""><span class="glyphicon glyphicon-user"></span>我</a></div>
    </div>
</div>

</body>
</html>
<script src="lib/js/less.js"></script>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<script>
    new Vue({
        el:'.wrap',
        data:{
            isShow:false,
            folderArr:[],
            show:true,
            title:'',
            type:''
        },
        methods:{
            change : function () {
                this.isShow = !this.isShow;
            }
        }
    });

    $(function () {
        var item=[];
        $("#result").click(function () {
            $("#materialItem li input:checked").each(function () {
                   item.push($(this).attr("value"));
                });
            if(confirm('确认删除吗？')){
                $.ajax({
                    url:'/ddlaw/case/${casee.id}/operation/${operation_id}/outputDelete',
                    method:'post',
                    data:JSON.stringify(item) ,
                    dataType:"json",
                    contentType: "application/json",
                    success: function (data) {
                    	console.log(data);
                        if(data.code==1024){
                            $(".error").fadeIn(500).delay(2000).fadeOut(500).text('成果删除成功！');
                            setTimeout(function(){
                                window.location.reload();
                            },2000)
                        }
                        else if(data.code==1025){
                            $(".error").fadeIn(500).delay(2000).fadeOut(500).text('删除失败，请重试');
                            return false;
                        }
                        else{}
                    }
                })
            }
        });
    })
</script>