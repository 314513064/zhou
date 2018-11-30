<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <div class="setHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 返回</a>
        <h3>材料发送</h3>
        <strong id="done">完成</strong>
    </div>
    <div class="Author">
        <div class="searchBar">
            <input type="search" class="form-control"/>
            <span class="glyphicon glyphicon-search"></span>
        </div>
        <div class="allFriend">
            <h4>联系人：</h4>
            
            
            
            <ul class="allFriendItem">
            	<c:forEach items="${friendList}" var ="user">
	                <li>
	                    <input type="checkbox" value="${user.id}"/>
	                    <span class="allFriendName">${user.username}</span>
	                </li>
                </c:forEach>
            </ul>
	        
	         
        </div>
    </div>
	<div class="error"></div>
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
$(function () {
    var list=[];

    $("#done").click(function () {
    	$(".allFriendItem li input:checked").each(function () {
            list.push($(this).attr("value"));
        });
    	 console.log(list);
        $.ajax({
            url:'case/${casee.id}/operation/${operation_id}/material/${material_id}/send',
            method:'post',
            data:JSON.stringify(list) ,
            dataType:"json",
            contentType:"application/json",
            success: function (data) {
                if(data.code==1016){
                    $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发送材料成功！');
                    setTimeout(function(){
                    	 window.location.href="case/${casee.id}/operation/${operation_id}/material/${material_id}";//跳转的地址
                    },1000)
                }
                else if(data.code==1017){
                    $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发送材料失败，请重试');
                    return false;
                }
                else{}
            }
        })
    })

})
</script>