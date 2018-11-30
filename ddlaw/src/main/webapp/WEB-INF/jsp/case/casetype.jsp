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
    <script>
        window.onload = function () {
            new Vue({
                el:'body',
                data:{
                    isShow : false
                },
                methods:{
                    new : function () {
                    this.isShow = !this.isShow;
                }
                }
            })
        }
    </script>
</head>
<body>
<div class="wrap">

    <div class="caseNav">
        <div style="border-bottom: 2px solid red">诉讼</div>
        <div>非诉</div>
        <div>咨询</div>
        <div>研究</div>
        <div @click='new'><span class="glyphicon glyphicon-plus"></span></div>
    </div>
    <div class="newCase" v-show= 'isShow'>
        <div><a href="case/new">诉讼案件</a></div>
        <div><a href="javascript:;">非诉讼项目</a></div>
        <div><a href="javascript:;">法律咨询</a></div>
        <div><a href="javascript:;">法律研究</a></div>
    </div>
    <div class="caseItem">
        <div><a href="cases">民事诉讼案件</a></div>
        <div><a href="javascript:;">刑事诉讼案件</a></div>
        <div><a href="javascript:;">行政诉讼案件</a></div>
    </div>




    <div class="nav">
        <div><a href="users/${user.id}/works"><span class="glyphicon glyphicon-time"></span>任务</a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-list"></span>case</a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-plus"></span></a></div>
        <div><a href="javascript:void(0);"><span class="glyphicon glyphicon-send"></span>讨论</a></div>
        <div><a href="users/${user.id}/my"><span class="glyphicon glyphicon-user"></span>我</a></div>
    </div>
</div>

</body>
</html>
<script src="lib/js/less.js"></script>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
