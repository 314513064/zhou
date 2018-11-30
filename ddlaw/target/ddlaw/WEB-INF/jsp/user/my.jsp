<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>

<body>
<div class="wrap">
    <div class="myHeader">
        <h3>我</h3>
    </div>
    <div class="myItem">
        <div class="myInfo">
            <img src="images/logo.png" alt="" />
            <span class="username"><a href="users/${user.id}/my/setting">${user.username}</a></span>
        </div>
        <div class="myFriend">
            <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;我的好友
            <span class="glyphicon glyphicon-chevron-right"></span>
        </div>
        <div class="myCheck">
	        <a href="users/${user.id}/shenhes" style="color:black">
	        	<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;我的审核
            	<span class="glyphicon glyphicon-chevron-right"></span>
	        </a>
            
        </div>
        <div class="news">
            <span class="glyphicon glyphicon-cd"></span>&nbsp;&nbsp;资讯
            <span class="glyphicon glyphicon-chevron-right"></span>
        </div>
    </div>


    <div class="nav">
        <div><a href="users/${user.id}/works"><span class="glyphicon glyphicon-time"></span>任务</a></div>
        <div><a href="casetype"><span class="glyphicon glyphicon-list"></span>case</a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-plus  plus"></span></a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-send"></span>讨论</a></div>
        <div><a href="javascript:;"><span class="glyphicon glyphicon-user"></span>我</a></div>
    </div>
</div>

</body>
</html>
<script src="lib/js/less.js"></script>

