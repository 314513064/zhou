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
    <script src="lib/js/vue-resource.min.js"></script>
</head>
<body>
<div class="wrap">
    <div class="materialHeader">
        <a href='javascript:;' onClick='javascript :history.back(-1);'><span class="glyphicon glyphicon-chevron-left"></span> 返回</a>
        <h3>${material.title}</h3>
        <c:if test="${privilege_Id!=2}">
        	<span class="glyphicon glyphicon-plus" @click="change" style='float:right'></span>
        </c:if>
    </div>
    <div class="newMaterial" v-show= 'isShow'>
        <div><a href="case/${casee.id}/operation/${operation_id}/material/${material.id}/sendUI">发送</a></div>
        <div data-toggle="modal" data-target="#myModal" @click='pizhu'>批注</div>
        <div data-toggle="modal" data-target="#myModal1" @click='shenpi'>审批</div>
        <div data-toggle="modal" data-target="#myModal2" @click='xiangqing'>详情</div>
    </div>
    <div class="materialDetail">
        <!-- <h3 style="font-size: 1rem">文件:</h3>-->
        <div class="row projectGroup"> 
             <div  style="overflow: hidden;width:8rem;height:4rem;border-radius:8px;margin:4rem auto;text-align:center;line-height:4rem;border:2px solid #699"><a class="media" style='font-size:1rem;color:#699' target="_blank" href="${material.url}">文件预览</a></div>
         	 <div  style="overflow: hidden;width:8rem;height:4rem;border-radius:8px;margin:4rem auto;text-align:center;line-height:4rem;border:2px solid #699"><a class="media" style='font-size:1rem;color:#699' target="_blank" href="${material.url}">文件下载</a></div>
          <%--  <iframe src='${material.url}'  style='width:;height:100%;max-width:20rem;'></iframe> --%>
        </div> 
        
         <%-- <embed width="100%" height="100%" name="plugin" id="plugin" src='${material.url}' type="application/pdf"> --%>
        
    </div>

    <div class="error"></div>
    <!-- Modal -->
    <div style="margin-top: 5rem" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">批注</h4>
                </div>
                <div class="modal-body">
                    <textarea v-model="comment" name="comment" id="comment" class="form-control" rows="4"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="confirm" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-top: 5rem" class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">您的审批意见？</h4>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" @click="reject1">拒绝</button>
                    <button type="button" class="btn btn-primary" id="confirm1" data-dismiss="modal" @click="shenpi1">同意</button>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-top: 5rem" class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">详情</h4>
            </div>
            <div class="modal-body"  id="xiangqingBox">
                <h4 >批注情况</h4>
                <ul >
	                <c:forEach items="${xQ_commentVOs}" var ="xQ_commentVO">
	                    <li><span >${xQ_commentVO.user_name}</span>:${xQ_commentVO.comment.comment}</li>
	                 </c:forEach>
                </ul>
                <hr>
                <h4 style="margin-top:2rem;">审批情况</h4>
                <ul style="margin-bottom:2rem;">
                    <c:forEach items="${xQ_approveVOs}" var ="xQ_approveVO">
	                    <li><span style>${xQ_approveVO.user_name}:</span>
	                            <c:if test="${xQ_approveVO.approve.flag==1}">
									同意
	      						</c:if>
	      						<c:if test="${xQ_approveVO.approve.flag==0}">
									拒绝&nbsp;&nbsp;&nbsp; ${xQ_approveVO.approve.note}
	      						</c:if>
	                    		
	                    </li>
                  </c:forEach>
                </ul>
            </div>

        </div>
        </div>
    </div>
    <div id="shenpiBox"  class="animated" v-show="reject" >
	    <h4 class="text-center">拒绝理由</h4>
	    <textarea name="note" id="note" class="form-control" v-model="reason"></textarea>
	    <button class="form-control btn btn-info" @click='rejectConfirm' >确定</button>
	    <button class="form-control" @click="cansel">取消</button>
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
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/pdfobject.min.js"></script>
<script>
    new Vue({
        el:'.wrap',
        data:{
            isShow:false,
            imgArr:[],
            show:true,
            reject:false,
            reason:"",
            xiangqing1:false,
            comment:''
        },
        methods:{
            change : function () {
                this.isShow = !this.isShow;
            },
            pizhu: function () {
                this.isShow = false;
            },
            shenpi: function () {
                   this.isShow = false;

            },
            shenpi1: function () {
                this.$http({
                    url:'case/${casee.id}/operation/${operation_id}/material/${material.id}/approve?flag=1',
                    method:'post',
                    contentType: "application/x-www-form-urlencoded"
                }).then(function (data) {
                	console.log(data);
                    if(data.data.code==1020){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发布审批成功');
                    }
                    else if(data.data.code==1021){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('审批失败，请重试');
                        return false;
                    }
                    else{}
                },function () {
                    $(".error").fadeIn(500).delay(2000).fadeOut(500).text('审批失败，请重试');
                    $("#comment").val('');
                })
            },
            xiangqing: function () {
                this.isShow = false;
            },
            cansel: function () {
                this.reject = false;
            },
            close: function () {
                this.xiangqing1=false;
            },
            reject1: function () {
                this.reject = true;
            },
            rejectConfirm: function () {
                var _this = this.reason;
                this.$http.post('case/${casee.id}/operation/${operation_id}/material/${material.id}/approve?flag=0',{
                    'note':_this},{
                    contentType: "application/x-www-form-urlencoded",
                    emulateJSON: true
                }).then(function (data) {
                    if(data.data.code==1020){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发布拒绝理由成功');
                        this.reject = false;
                    }
                    else if(data.data.code==1021){
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发布失败，请重试');
                        return false;
                    }
                    else{}
                },function () {
                    $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发布失败，请重试');
                    this.reject = false;
                }).bind(this);

            }
        }
    });
    $(function () {
        $("#confirm").click(function () {
            var comment = $("#comment").val();
            if(comment.length==0){
                $(".error").fadeIn(500).delay(2000).fadeOut(500).text('所填内容不能为空！');
                $("#comment").focus();
            }
            else{
                $.ajax({
                    url:'case/${casee.id}/operation/${operation_id}/material/${material.id}/comment',
                    method:'post',
                    data:{'comment':comment},
                    	  //'material_id':${material.id}},
                    contentType: "application/x-www-form-urlencoded",
                    success: function (data) {
 
                        if(data.code==1018){
                            $(".error").fadeIn(500).delay(2000).fadeOut(500).text('发布批注成功');
                            $("#comment").val('');
                        }
                        else if(data.code==1019){
                            $(".error").fadeIn(500).delay(2000).fadeOut(500).text('批注失败，请重试');
                            $("#comment").val('');
                            return false;
                        }
                        else{}
                    },
                    error: function () {
                        $(".error").fadeIn(500).delay(2000).fadeOut(500).text('批注失败，请重试');
                        $("#comment").val('');
                    }
                })
            }
        });

    })
</script>
<script type="text/javascript">  
    if(PDFObject.supportsPDFs){  
        // PDF嵌入到网页  
        PDFObject.embed("index.pdf", "#pdf_viewer" );  
    } 
      
    // 还可以通过以下代码进行判断是否支持PDFObject预览  
    if(PDFObject.supportsPDFs){  
       console.log("Yay, this browser supports inline PDFs.");  
    } else {  
       console.log("Boo, inline PDFs are not supported by this browser");  
    }  
</script>  