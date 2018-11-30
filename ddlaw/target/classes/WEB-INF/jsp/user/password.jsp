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
    <div class="setPassHeader">
        <a href="javascript:;" onClick="javascript :history.back(-1);"><span class="glyphicon glyphicon-chevron-left"></span> 设置</a>
        <h3>更改密码</h3>
    </div>
    <div class="setPassItems">
        <div class="beforePassword">
            <div class="country">
                <span>原密码</span>
            </div>
            <input type="password" class="form-control" placeholder="请输入您的原密码"/>
        </div>
        <div class="afterPassword">
            <div class="country">
                <span>新密码</span>
            </div>
            <input type="password" class="form-control" placeholder="请输入您的新密码"/>
        </div>
        <div class="rePassword">
            <div class="country">
                <span>确认新密码</span>
            </div>
            <input type="password" class="form-control" placeholder="请确认您的原密码"/>
        </div>

        <button class="btn btn-info" id="upDataPass">更新资料</button>
    </div>
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