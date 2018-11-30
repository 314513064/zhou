<%@page import="java.util.Date"%>
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
    <div class="setHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 返回</a>
        <h3>${ casee.summary}</h3>
        <a href="case/${casee.id}" style="color:black;margin-top:0.5rem"><strong style="font-size: 1.1rem;margin-left: 0.7rem;"><span class="glyphicon glyphicon-info-sign"></span></strong>
    	</a>
    </div>
    <div class="timeHeader">
        <div class="openTime">
            <div>
                开庭时间
            </div>
            <div>
           
                <span><fmt:formatDate value="${casee.opentime}"  type="both" pattern="yyyy年MM月dd日ahh:mm"/></span>
            </div>
        </div>
        <div class="proofTime">
            <div>
                举证期限
            </div>
            <div>
                <span><fmt:formatDate value="${casee.termtime}"  type="both" pattern="yyyy年MM月dd日" /></span>止
            </div>
        </div>
    </div>
    <div id="mainItem">
        <h4>办案流程</h4>
        <ul>
          <c:forEach items="${operations}" var ="operation">
	            <li class="operation">
	                <div class="listTime" > <input type="text" readonly value="<fmt:formatDate value='${operation.time}' pattern="yyyy年MM月dd日aHH:mm"/>" /></div>
	                <div class="listAction"><input type="text" readonly  value="${operation.name}"/></div>
	                <div class="todo">
	                    <ul style='margin-top:0.5rem'>
	                        <li><a href="case/${casee.id}/operation/${operation.id}/materials?material_type=100&operation_type=${operation.type}&currentpage=1"><span class="glyphicon glyphicon-folder-open"></span></a></li>
	                        <li><span class="glyphicon glyphicon-exclamation-sign"></span></li>
	                        <li><span class="glyphicon glyphicon-comment"></span></li>
	                        <li><a href="case/${casee.id}/operation/${operation.id}/outputUI?operation_type=${operation.type}"><span class="glyphicon glyphicon-share"></span></a></li>
	                    </ul>
	                </div>
	            </li>
          </c:forEach>
    
        </ul>
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
          