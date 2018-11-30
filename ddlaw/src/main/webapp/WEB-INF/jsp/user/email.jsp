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
    <div class="setTelHeader">
        <a href="javascript:;" onClick="javascript :history.back(-1);"><span class="glyphicon glyphicon-chevron-left"></span> 设置</a>
        <h3>手机号和邮箱</h3>
    </div>
    <div class="setTelItems">
        <div class="Email">
            <div class="country">
                <span>E-mail</span>
            </div>
            <input type="email" class="form-control" placeholder="输入您的E-mail"/>
        </div>
        <div class="phone">
            <div class="country">
                <span>手机号码</span>
            </div>
            <input type="email" class="form-control" placeholder="输入您的手机号"/>
        </div>
        <button class="btn btn-info" id="upDataTel">更新资料</button>
    </div>
    <div class="nav">
        <div><a href="users/${user.id}/works"><span class="glyphicon glyphicon-time"></span>任务</a></div>
        <div><a href="casetype"><span class="glyphicon glyphicon-list"></span>case</a></div>
        <div><a href="#"><span class="glyphicon glyphicon-plus"></span></a></div>
        <div><a href="#"><span class="glyphicon glyphicon-send"></span>讨论</a></div>
        <div><a href="users/${user.id}/my""><span class="glyphicon glyphicon-user"></span>我</a></div>
    </div>

</div>
</body>
</html>
<script src="lib/js/less.js"></script>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>