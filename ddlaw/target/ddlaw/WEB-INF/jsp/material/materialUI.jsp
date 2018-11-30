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
        <h3>材料</h3>
         <c:if test="${privilege_Id!=2}">
        	<span class="glyphicon glyphicon-plus" @click="change"></span>
		</c:if>
    </div>
    
   				 
    <div class="newMaterial" v-show= 'isShow'>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/pictureUI">图片上传</a></div>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/documentUI">文档上传</a></div>
        <div><a href="javascript:;">当得模板</a></div>
    </div>
	
	
    <div id="materialItem">
        <ul>
            <li v-for="item in folderArr" @click.native="check" id="{{item.materialType}}">
            	<a  href="{{item.url}}" style="color:#699">
            		<span class="glyphicon glyphicon-folder-open"></span>
                	{{item.typeName}}
            	</a>
                
            </li>
            
            <c:forEach items="${materialsAndPagebean[0]}" var ="material">
	            <li>
	               <a style="color:#699;font-size:1rem" href="case/${casee.id}/operation/${operation_id}/material/${material.id}">	            
		                ${material.title}
		                <i class="lastTime" style="opcity:0.5;color:black">
		                    上传时间:<fmt:formatDate value="${material.create_time}"  type="both" />
		                </i>
	                </a>
	            </li>
            
            </c:forEach>
            

        </ul>
    </div>
    
  	<div>
	
		共${materialsAndPagebean[1].totalrecord }条&nbsp;[${materialsAndPagebean[1].totalpage }]页
		<c:forEach var="pagenum" items="${materialsAndPagebean[1].pagebar}">	    									    			
			<c:if test="${pagenum==materialsAndPagebean[1].currentpage}">
				<span>${pagenum }</span>  											
			</c:if>	    	
			<c:if test="${pagenum!=materialsAndPagebean[1].currentpage}">
				<a href="case/${casee.id}/operation/${operation_id}/materials?material_type=100&operation_type=${operation_type}&currentpage=${pagenum}">${pagenum }</a> 
			</c:if>	    								
		</c:forEach>				
	</div>
    <br><br><br><br><br>

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
            folderArr:[]
        },
        methods:{
            change : function () {
                this.isShow = !this.isShow;
            }
        },
        ready: function () {
            switch(${operation_type}){
                case 1 : this.folderArr.push(
                        {'typeName':'律师费','materialType':'1','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=1&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'委托协议','materialType':'2','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=2&operation_type=${operation_type}&currentpage=1"}
                );
                    break;
                case 2 : this.folderArr.push(
                        {'typeName':'授权协议','materialType':'3','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=3&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'委托协议','materialType':'4','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=4&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'出庭函','materialType':'5','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=5&operation_type=${operation_type}&currentpage=1"}
                );
                    break;
                case 3 : this.folderArr.push(
                        {'typeName':'我方证据','materialType':'6','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=6&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'他方证据','materialType':'7','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=7&operation_type=${operation_type}&currentpage=1"}
                );
                    break;
                case 4 : this.folderArr.push(
                        {'typeName':'传票','materialType':'10','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=10&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'代理词','materialType':'11','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=11&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'庭审记录','materialType':'12','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=12&operation_type=${operation_type}&currentpage=1"},
                        {'typeName':'判决书','materialType':'13','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=13&operation_type=${operation_type}&currentpage=1"}
                );
                    break;
                case 5 : this.folderArr.push(
                        {'typeName':'结案陈词','materialType':'14','url':"case/${casee.id}/operation/${operation_id}/materials?material_type=14&operation_type=${operation_type}&currentpage=1"},
                );
                    break;
            }
        }
    })
</script>     