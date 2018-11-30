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
</head>

<body>
<div class="wrap">
    <div class="setHeader">
        <a href="javascript:;" onClick="javascript :history.back(-1);"><span class="glyphicon glyphicon-chevron-left"></span> 我</a>
        <h3>设置</h3>
    </div>
    <div class="setItem">
        <div><a href="users/${user.id}/my/info">
            <span>个人资料</span>
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a></div>
        <div><a href="users/${user.id}/my/email">
            <span>手机号/邮箱</span>
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a></div>
        <div><a href="users/${user.id}/my/password">
            <span>密码设置</span>
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a></div>
        <button class="btn btn-info">
            <a href="logout">退出登陆</a>
        </button>
        
    </div>
    

</div>
</body>
</html>
<script src="lib/js/less.js"></script>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>