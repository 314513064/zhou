<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <h3 style="text-align: center;width: 19rem">任务</h3>
    </div>
    <div class="misstion">

        <div class="alwaysFriend">
            <ul class="alwaysFriendItem">
              <c:forEach items="${caseList}" var ="casee">
            
                <li>
                		<a href="case/${casee.id}/todolist">
                	
	                    <div class="openTime" style="color:black">
	                        <div>
	                            <span><fmt:formatDate value="${casee.opentime}" pattern="MM月dd日 a HH:mm"/></span>
	                        </div>
	                        <div style="font-size: 1rem">
	                       		${casee.summary}
	                        </div>
	                        <span class="miss">任务：<i style="color:red">开庭</i></span>
	                    </div>
	                </a>
	                    
                </li>
              </c:forEach>
            </ul>
        </div>
        <div class="otherFriend">
            <span class="glyphicon glyphicon-chevron-down down"></span>
            <span class="glyphicon glyphicon-chevron-up up"></span>
            <ul class="otherFriendItem">
              <c:forEach items="${noticeList}" var ="notice">
            
                <li>
                   <c:if test="${notice.type==0}">	<!-- 上传通知，跳转到具体材料界面 -->	                	
                      <a href="case/${notice.case_id}/operation/${notice.operation_id}/material/${notice.material_id}/from_notice?readtype=notice&noticeId=${notice.id}">
                   </c:if>
                   <c:if test="${notice.type==1}">	<!-- 发送通知，跳转到我的审核界面 -->	                	
                      <a href="users/${user.id}/shenhes">
                   </c:if>                        
                        <div class="friendTips">
                            <div class="updateTime">
                                                                          时间：<fmt:formatDate value="${notice.create_time}" pattern="yyyy/MM/dd HH:mm:ss"/> 
                                                                                                  
                            </div>
                            <div class="tipsTitle">
                            	  ${notice.content}
                            </div>
                        </div>
                    </a>

                </li>
             </c:forEach>
                
            </ul>
        </div>
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
    $(function () {
        if($(".alwaysFriendItem li").length<3){
            $(".otherFriend").children("span").css('display','none')
        }
        $(".down").click(function () {
            $(".otherFriend").css({
                transform:'translateY(1rem)'
            });
            $(".alwaysFriendItem").animate({
                height:"20.222rem"
            });
            $(".down").css({
                display:"none"
            });
            $(".up").css({
                display:"block"
            });
        });
        $(".up").click(function () {
            $(".otherFriend").css({
                transform:'translateY(0)'
            });
            $(".alwaysFriendItem").animate({
                height:"11.5555rem"
            });
            $(".up").css({
                display:"none"
            });
            $(".down").css({
                display:"block"
            });
        })

/*         var time = ${casee.opentime};
        var y = time.getFullYear();
        var M = time.getMonth()+1;
        var d = time.getDate();
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds()
        var str = '今天是'+y+'年'+M+'月'+d+'日 '+h+':'+m+':'+s;
        console.log(str);
        $("#time").html(str); */
        
    })
</script>