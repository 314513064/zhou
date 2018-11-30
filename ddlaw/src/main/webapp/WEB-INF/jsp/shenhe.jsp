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
        <h3>我的审核</h3>
        	<span class="glyphicon glyphicon-plus" @click="change" style="visibility:hidden"></span>
        	
    </div>

    <div id="materialItem1">
        <ul>           
				<c:forEach items="${noticeList}" var="notice">
					
						<li>
							<a	href="case/${notice.case_id}/operation/${notice.operation_id}/material/${notice.material_id}/from_notice?readtype=notice&noticeId=${notice.id}" style="float:left">
							<span style="color:#699;font-size:1rem;display:block;width:10rem;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">${notice.casesummery}</span>
								<span style="color:red;font-size:0.777rem;display:block;width:10rem;height:2.3rem;overflow:hidden;text-overflow:ellipsis;">${notice.content}</span>
							</a>															
							<strong id="goResult"  v-if="${notice.flag}==0" style="font-size: 0.777rem;color:red;float: right ">未查看</strong>
							
													
							<strong id="hasResult" v-if="${notice.flag}==1" style="font-size: 0.777rem;color:#699;float: right;">已查看</strong>
							<i class="lastTime"> 通知时间:<fmt:formatDate
										value="${notice.create_time}" type="both" />			
							</i>
						
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
<script>
	new Vue({
		el:'.wrap'
	})
</script>
    