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
        <div class="logo">
            <img src="images/logo.png" alt="当得法律"/>
            <span>当得法律</span>
        </div>
        <div id="login">
            <a href="#" class="faceLogin">
                <span class="glyphicon glyphicon-eye-open"></span>
                    刷脸登陆
            </a>
            <a href="login.html" class="telLogin">
                <span class="glyphicon glyphicon-cloud"></span>
                手机登陆
            </a>
        </div>
        <div id="register">
            <a href="register.html">没有账户？点击注册</a>
        </div>
    </div>
</body>
</html>
<script src="lib/js/less.js"></script>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>

