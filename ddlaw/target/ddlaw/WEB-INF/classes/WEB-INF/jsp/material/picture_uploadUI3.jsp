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
            <h3>材料</h3>
            <strong><label for="submit">上传</label></strong>
        </div>
        <div id="materialUpload" >
            <form  enctype="multipart/form-data"  >
                <div class="row">
                    <div class="col-xs-3 text-left"><label for="title">标题</label></div>
                    <div class="col-xs-9 text-left" ><input type="text" class="form-control" id="title" name="title" placeholder="请输入标题"/></div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <label for="introduce">简介</label>
                    </div>
                    <div class="col-xs-12">
                        <textarea name="introduce" id="introduce" class="form-control container" rows="5" placeholder="请输入简介..."></textarea>
                    </div>
                </div>
                <h3>图片上传 <i style="font-size:0.5rem;color: #cccccc ">(仅限jpg,gif,png,bmp格式)</i> </h3>
                <div class="uploadPic">
                    <ul>
                        <li style="display: none" id="photo1" >
                            <div data-role="fieldcontain1">
                                <div id="localImag1">
                                    <img id="preview1" width="-1" height="-1" style="display: none" />
                                </div>
                            </div>
                        </li>
                        <li id="input1">
                                <div>
                                    <label class="ui_button ui_button_primary" for="file_head1" style="cursor: pointer"><span class="glyphicon glyphicon-plus"></span></label>
                                    <input type="file"  name="file_head" id="file_head1" onchange="setImagePreview1();" style="display: none">
                                </div>
                        </li>
                        <li style="display: none" id="photo2" >
                            <div data-role="fieldcontain1">
                                <div id="localImag2">
                                    <img id="preview2" width="-1" height="-1" style="display: none" />
                                </div>
                            </div>
                        </li>
                        <li id="input2" style="display: none">
                            <div>
                                <label class="ui_button ui_button_primary" for="file_head2" style="cursor: pointer"><span class="glyphicon glyphicon-plus"></span></label>
                                <input type="file"  name="file_head" id="file_head2" onchange="setImagePreview2();" style="display: none">
                            </div>
                        </li>
                        <li style="display: none" id="photo3" >
                            <div data-role="fieldcontain1">
                                <div id="localImag3">
                                    <img id="preview3" width="-1" height="-1" style="display: none" />
                                </div>
                            </div>
                        </li>
                        <li id="input3" style="display: none">
                            <div>
                                <label class="ui_button ui_button_primary" for="file_head3" style="cursor: pointer"><span class="glyphicon glyphicon-plus"></span></label>
                                <input type="file"  name="file_head" id="file_head3" onchange="setImagePreview3();" style="display: none">
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="selectType" style="clear: both" >
                    <label for="type">上传类型</label>
                    <select name="type"  id="type" class="form-control" >
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
    var ipt2 = document.getElementById("input2");
    var ipt3 = document.getElementById("input3");
    function setImagePreview1() {
        var preview,li, img_txt, localImag,ipt1 = document.getElementById('input1'), file_head = document.getElementById("file_head1"),
                picture = file_head.value;
        //判断图片格式
        if (!picture.match(/.jpg|.gif|.png|.bmp/i)) return alert("您上传的图片格式不正确，请重新选择！"),
                !1;
        //设置预览的格式
        if (preview = document.getElementById("preview1"),li = document.getElementById("photo1") ,file_head.files && file_head.files[0]) preview.style.display = "block",
                li.style.display = "block",
                li.style.border = 0,
                preview.style.width = "5.55rem",
                preview.style.height = "5.55rem",
                ipt1.style.display = "none",
                ipt2.style.display = "block",
                preview.src = window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1 ? window.webkitURL.createObjectURL(file_head.files[0]) : window.URL.createObjectURL(file_head.files[0]);
        else {
                    file_head.select(),
                    file_head.blur(),
                    img_txt = document.selection.createRange().text,
                    localImag = document.getElementById("localImag1"),
                    localImag.style.width = "5.55rem",
                    localImag.style.height = "5.55rem",
                    ipt1.style.display = "none";
                    ipt2.style.display = "block"


            try {
                localImag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)",
                        localImag.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = img_txt
            } catch(f) {
                return alert("您上传的图片格式不正确，请重新选择！"),
                        !1
            }
                    preview.style.display = "none",
                    document.selection.empty()

        }
    }
    function setImagePreview2() {
        var preview,li, img_txt, localImag, file_head = document.getElementById("file_head2"),
                picture = file_head.value;
        //判断图片格式
        if (!picture.match(/.jpg|.gif|.png|.bmp/i)) return alert("您上传的图片格式不正确，请重新选择！"),
                !1;
        //设置预览的格式
        if (preview = document.getElementById("preview2"),li = document.getElementById("photo2") ,file_head.files && file_head.files[0]) preview.style.display = "block",
                li.style.display = "block",
                li.style.border = 0,
                preview.style.width = "5.55rem",
                preview.style.height = "5.55rem",
                ipt2.style.display = "none",
                ipt3.style.display = "block",
                preview.src = window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1 ? window.webkitURL.createObjectURL(file_head.files[0]) : window.URL.createObjectURL(file_head.files[0]);
        else {
            file_head.select(),
                    file_head.blur(),
                    img_txt = document.selection.createRange().text,
                    localImag = document.getElementById("localImag2"),
                    localImag.style.width = "5.55rem",
                    localImag.style.height = "5.55rem",
                    ipt2.style.display = "none";

            try {
                localImag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)",
                        localImag.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = img_txt
            } catch(f) {
                return alert("您上传的图片格式不正确，请重新选择！"),
                        !1
            }
            preview.style.display = "none",
                    document.selection.empty()

        }
    }
    function setImagePreview3() {
        var preview,li, img_txt, localImag, file_head = document.getElementById("file_head3"),
                picture = file_head.value;
        //判断图片格式
        if (!picture.match(/.jpg|.gif|.png|.bmp/i)) return alert("您上传的图片格式不正确，请重新选择！"),
                !1;
        //设置预览的格式
        if (preview = document.getElementById("preview3"),li = document.getElementById("photo3") ,file_head.files && file_head.files[0]) preview.style.display = "block",
                li.style.display = "block",
                li.style.border = 0,
                preview.style.width = "5.55rem",
                preview.style.height = "5.55rem",
                ipt3.style.display = "none",
                preview.src = window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1 ? window.webkitURL.createObjectURL(file_head.files[0]) : window.URL.createObjectURL(file_head.files[0]);
        else {
            file_head.select(),
                    file_head.blur(),
                    img_txt = document.selection.createRange().text,
                    localImag = document.getElementById("localImag3"),
                    localImag.style.width = "5.55rem",
                    localImag.style.height = "5.55rem",
                    ipt3.style.display = "none";

            try {
                localImag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)",
                        localImag.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = img_txt
            } catch(f) {
                return alert("您上传的图片格式不正确，请重新选择！"),
                        !1
            }
            preview.style.display = "none",
                    document.selection.empty()

        }
    }


    $(function () {
        $('#submit').on('click', function () {
            if($("#title").val()=='') {
                $(".error").fadeIn(500).delay(1000).fadeOut(500).text('标题或内容不能为空');
                return false;
            }
            $('form').on('submit', function () {
            	
                $(this).ajaxSubmit({
                    type: 'post', // 提交方式 get/post
                    url: 'case/${casee.id}/operation/${operation_id}/material?file_type=0&flag_output=1', // 需要提交的 url
                    success: function (data) {
                    	//var res =JSON.parse(data);
                    	var res =data;
                    	if(res.code=='1014'){          
                            $(".error").fadeIn(500).delay(1000).fadeOut(500).text('上传成功');
                            setTimeout(function(){
                                window.location.href="case/${casee.id}/operation/${operation_id}/outputUI?operation_type=${operation_type}";//跳转的地址
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
   
    
    