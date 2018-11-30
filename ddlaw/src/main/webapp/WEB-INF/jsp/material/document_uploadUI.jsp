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
</head>
<body>
<div class="wrap">

    <div class="setHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 返回</a>
        <h3>文档上传</h3>
        <strong><label for="submit">上传</label></strong>
    </div>
    <div id="materialUpload" >
        <form enctype="multipart/form-data"  >
            
            <h3>文档上传 <i style="font-size:0.5rem;color: #cccccc ">(仅限.doc,.docx,.pdf,.txt格式)</i> </h3>
            <div class="uploadProject">

                <input type="file"  name="file_head" multiple="multiple" id="test"/>
                <ul id='content'></ul>

            </div>
            <div class="selectType" style="clear: both" >
                <label for="type">上传类型</label>
                <select name="type"  id="type" class="form-control" >
                    <option value="100" >其他</option>
                    <option v-for="item in materialArr " value="{{item.materialType}}" > {{item.typeName}}</option>
                </select>
            </div>
            <input type="submit" style="display: none" id="submit"/>
        </form>
        <div class="error"></div>
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
<script src="lib/js/jquery.form.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<script>
$(function () {
    $('#submit').on('click', function () {
      
        $('form').on('submit', function () {
        	
            $(this).ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: 'case/${casee.id}/operation/${operation_id}/material?file_type=1&flag_output=0', // 需要提交的 url
                success: function (data) {
                	/* var res =JSON.parse(data); */
                	 var res =data;
                	if(res.code=='1014'){          
                        $(".error").fadeIn(500).delay(1000).fadeOut(500).text('上传成功');
                        setTimeout(function(){
                            window.location.href="case/${casee.id}/operation/${operation_id}/materials?material_type=100&operation_type=${operation_type}";//跳转的地址
                        },2000)

                    }
                    else if(res.code=='1015'){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('上传失败，请重试');
                        return false;
                    }
                    else{}
                },
                error: function () {
                    $(".error").fadeIn(500).delay(2000).fadeOut(500).text('上传失败，请重试');
                    return false;
                }
            });
            return false; // 阻止表单自动提交事件
        });
    });

});//异步提交表单


var test = document.getElementById('test');
test.addEventListener('change', function() {
    var picture = test.value;
    if (!picture.match(/.doc|.docx|.pdf|.txt/i))
    {alert("您上传的文件格式不正确，请重新选择！");
        test.value = '';
    }
    var t_files = this.files;
    console.log(t_files);
    var str = '';
    for (var i = 0, len = t_files.length; i < len; i++) {
        console.log(t_files[i]);
        str += '<li>名称：' + t_files[i].name + '  大小:' + t_files[i].size / 1024 + 'KB</li>';
    };
    document.getElementById('content').innerHTML = str;
}, false);

new Vue({
    el:'.wrap',
    data:{
        materialArr:[]
    },

    ready: function () {
        switch(${operation_type}){
            case 1 : this.materialArr.push(
                    {'typeName':'委托协议','materialType':'2'}
            );
                break;
            case 2 : this.materialArr.push(
                    {'typeName':'授权协议','materialType':'3'},
                    {'typeName':'委托协议','materialType':'4'},
                    {'typeName':'出庭函','materialType':'5'}
            );
                break;
            case 3 : this.materialArr.push(
                    {'typeName':'我方证据','materialType':'6'},
                    {'typeName':'他方证据','materialType':'7'}
            );
                break;
            case 4 : this.materialArr.push(
                    {'typeName':'传票','materialType':'10'},
                    {'typeName':'代理词','materialType':'11'},
                    {'typeName':'庭审记录','materialType':'12'},
                    {'typeName':'判决书','materialType':'13'}
            );
                break;
            case 5 : this.materialArr.push(
                    {'typeName':'结案陈词','materialType':'14'},
                    {'typeName':'结案文件','materialType':'15'}
            );
                break;
        }
    }
})

</script>    