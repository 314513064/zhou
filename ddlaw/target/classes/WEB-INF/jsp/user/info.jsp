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
    <div class="setInfoHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 设置</a>
        <h3>个人资料</h3>
    </div>
    <div class="setInfoItems">
        <div class="userName">
            <div class="country">
                <span>昵称</span>
            </div>
            <input value="${sessionScope.user.username}" type="text" class="form-control" placeholder="请给自己取个个性的名字吧"/>
        </div>
        <div class="sex">
            <div class="country">
                <span>性别</span>
            </div>
            <select name="sex" id="sex" class="form-control">
                <option value="man">男</option>
                <option value="woman">女</option>
                <option value="lock">保密</option>
            </select>
        </div>
        <div class="birth">
            <div class="country">
                <span>生日</span>
            </div>
            <input id="birthday" value =""type="date" class="form-control"/>
        </div>
        <div class="profession">
            <div class="country">
                <span>职业</span>
            </div>
            <select name="profession" id="profession" class="form-control">
                <option value="free">自由职业者</option>
                <option value="money">财务</option>
                <option value="law">法务工作者</option>
                <option value="other">其他</option>
            </select>
        </div>
        <div class="company">
            <div class="country">
                <span>公司类型</span>
            </div>
            <select name="company" id="company" class="form-control">
                <option value="country" >国企</option>
                <option value="people">民企</option>
                <option value="outside">外企</option>
                <option value="lock">保密</option>
            </select>
        </div>
        <div class="companyName">
            <div class="country">
                <span>公司名称</span>
            </div>
            <input value="${sessionScope.user.company_name}"type="text" class="form-control" placeholder="输入单位名称" id="companyName"/>
        </div>
        <button class="btn btn-info" id="upData">更新资料</button>
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
    	
    	var time1 = '${sessionScope.user.birthday}';
    	var time = new Date(time1);
        var y = time.getFullYear();
        var M = time.getMonth()+1;
        if(M<10){
        	M = '0'+M
        }
        
        var d = time.getDate();
        if(d<10){
        	d = '0'+d;
        }
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds()
        var str = y+'-'+M+'-'+d; 
        $("#birthday").val(str);
        
        $("#sex option").each(function(){
        	if ($(this).html()=='${user.sex}'){
        		$(this).prop('selected','selected');
        	}
        })
        $("#profession option").each(function(){
        	if ($(this).html()=='${user.job}'){
        		$(this).prop('selected','selected');
        	}
        })
        $("#company option").each(function(){
        	if ($(this).html()=='${user.company_type}'){
        		$(this).prop('selected','selected');
        	}
        })
        
        $("#upData").click(function () {
           var username = $(".setInfoItems .userName input").val();
           var sex = $(".setInfoItems #sex option:selected").html();
           var birthday = $(".setInfoItems .birth input").val();
           var profession = $(".setInfoItems #profession option:selected").html();
           var company = $(".setInfoItems #company option:selected").html();
           var companyName = $(".setInfoItems .companyName input").val();
            $.ajax({
                url:'users/${user.id}/my/info',
                method:'put',
                data:{
                    "username":username,
                   	 "sex":sex,
                    "birthday":birthday,
                    "job":profession,
                    "company_type":company,
                    "company_name":companyName 
                },
                contentType: "application/x-www-form-urlencoded",
                success: function (data) {
                    if(data.code==1005){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('修改信息成功');
                       setTimeout(function () {
                           location.reload();
                       },1500);
                    }
                    else if(data.code==1006){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('修改失败，请重试');
                        return false;
                    }
                    else{}
                }
            })
        })
    })
</script>