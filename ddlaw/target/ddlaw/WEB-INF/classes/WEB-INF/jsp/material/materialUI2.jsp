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
        <h3>{{title}}</h3>
         <c:if test="${privilege_Id!=2}">
        	<span class="glyphicon glyphicon-plus" @click="change"></span>
		</c:if>
    </div>
    
    <div class="newMaterial" v-show= 'isShow'>
    
        <div><a href="case/${casee.id}/operation/${operation_id}/material/pictureUI2?material_type=${material_type}">照片上传</a></div>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/documentUI2?material_type=${material_type}">文档上传</a></div>       
        <div><a href="javascript:;">当得模板</a></div>
    </div>

    <div id="materialItem">
        <ul>
            
				<c:forEach items="${materialsAndPagebean[0]}" var="material">
					
						<li>
							<a	href="case/${casee.id}/operation/${operation_id}/material/${material.id}" style="float:left">
								<span style="font-size:1rem;display:block;width:10rem;height:4rem;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">${material.title}</span> 	
							</a>															
						<strong id="goResult" @click.stop="goResult($event,${material.id},${material.type})" v-if="${material.flag_output}==0" style="font-size: 0.777rem;color:#699;float: right ">输出结果</strong>
						<strong id="hasResult" v-if="${material.flag_output}==1" style="font-size: 0.777rem;color:#a5a5a5;float: right;font-weight: 300 ">已输出</strong>
							<i class="lastTime"> 上传时间:<fmt:formatDate value="${material.create_time}" type="both" />			
							</i>
						
						</li>
					
				</c:forEach>
            

        </ul>
	     <div>	
			共${materialsAndPagebean[1].totalrecord }条&nbsp;[${materialsAndPagebean[1].totalpage }]页
			<c:forEach var="pagenum" items="${materialsAndPagebean[1].pagebar}">	    									    			
				<c:if test="${pagenum==materialsAndPagebean[1].currentpage}">
					<span>${pagenum }</span>  											
				</c:if>	    	
				<c:if test="${pagenum!=materialsAndPagebean[1].currentpage}">
					<a href="case/${casee.id}/operation/${operation_id}/materials?material_type=${material_type}&operation_type=${operation_type}&currentpage=${pagenum}">${pagenum }</a> 
				</c:if>	    								
			</c:forEach>				
		</div>
    </div>

    <br><br><br><br><br>
    
    
    
	 <div class="circle-loader" v-show="loading">
        <div class="circle-line">
            <div class="circle circle-blue"></div>
            <div class="circle circle-blue"></div>
            <div class="circle circle-blue"></div>
        </div>
        <div class="circle-line">
            <div class="circle circle-yellow"></div>
            <div class="circle circle-yellow"></div>
            <div class="circle circle-yellow"></div>
        </div>
        <div class="circle-line">
            <div class="circle circle-red"></div>
            <div class="circle circle-red"></div>
            <div class="circle circle-red"></div>
        </div>
        <div class="circle-line">
            <div class="circle circle-green"></div>
            <div class="circle circle-green"></div>
            <div class="circle circle-green"></div>
        </div>
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
    new Vue({
        el:'.wrap',
        data:{
            isShow:false,
            title:"",            
            loading:false
            
        },
        methods:{
            change : function () {
                this.isShow = !this.isShow;
            },
            goResult : function (e,num,type1) {
            	
                this.loading = true;
                var that = this; 
                var mId = num;
                var type = type1;
                $.ajax({
                    url:'/ddlaw/case/${casee.id}/operation/${operation_id}/material/output',
                    method:'post',
                    data:{'id':mId,'type':type},
                    contentType: "application/x-www-form-urlencoded",
                    success: function (data) {
                    	//var res = JSON.parse(data);
                    	var res = data;
                        if(res.code==1022){
                            
                            that.loading = false;
                            setTimeout(function () {
                                window.location.href = "/ddlaw/case/${casee.id}/operation/${operation_id}/outputUI?operation_type=${operation_type}";  //转跳地址
                            },500)
                        }
                        else if(res.code==1023){
                            that.loading = false;
                            $(".error").fadeIn(500).delay(2000).fadeOut(500).text('输出失败，请重试');
                            return false;
                        }
                        else{}
                    },
                    error: function () {
                        that.loading = false;
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('输出失败，请重试');
                        return false;
                    }
                })

            }
        },
        ready: function () {
            switch(${material_type}){
                case 1 : this.title="律师费";
                    break;
                case 2 : this.title="委托协议";
                    break;
                case 3 : this.title="授权协议";
                    break;
                case 4 : this.title="委托协议";
                    break;
                case 5 : this.title="出庭函";
                    break;
                case 6 : this.title="我方证据";
                    break;
                case 7 : this.title="他方证据";
                    break;
                case 10 : this.title="传票";
                    break;
                case 11 : this.title="代理词";
                    break;
                case 12 : this.title="庭审记录";
                    break;
                case 13 : this.title="判决书";
                    break;
                case 14 : this.title="结案陈词";
                    break;
                case 15 : this.title="结案文件";
                    break;
            }
        }
        
    })
    

</script>     