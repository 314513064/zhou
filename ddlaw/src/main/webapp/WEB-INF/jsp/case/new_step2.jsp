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
    <link rel="stylesheet" href="lib/css/animate.css"/>
</head>
<body>
<div class="wrap">
    <div class="setHeader">
        <a href="newCase.html"><span class="glyphicon glyphicon-chevron-left"></span>返回</a>
        <h3>流程</h3>
    </div>
    <div class="listItem">
        <h3 >预设流程</h3>

        <form action="case/new_step2" method="post" id="form" data-ajax="false">
            <ul id="action">
                <li class="shenpi">
                    <input type="hidden" name="operations[0].type" value="1" style="background:black" >
                    <div class="listTime" id="sp"> <input type="text" value="2018-3-3 10:00"  name="operations[0].time" readonly="readonly"/></div>
                    <div class="listAction"><input type="text" readonly="readonly" value="审批"  name="operations[0].name"/></div>
                    <div class="action hide animated">
                    <ul>
                        <li  data-toggle="modal" data-target="#myModal">编辑</li>
                    </ul>
                    </div>
                </li>
                <li class="weituo">
                    <input type="hidden" name="operations[1].type" value="2" >
                    <div class="listTime" id="wt"><input type="text" readonly="readonly" value="2018-3-3 10:00" name="operations[1].time"/></div>
                    <div class="listAction"><input type="text" readonly="readonly" value="委托"  name="operations[1].name"/></div>
                    <div class="action hide animated">
                        <ul>
                            <li data-toggle="modal" data-target="#myModal">编辑</li>
                        </ul>
                    </div>
                </li>
                <li class="chuli" v-for="item in chuliList" >
                    <input type="hidden" name="operations[{{item.id}}].type" value="3" >
                    <div class="listTime" id="cl"><input type="text" value="{{item.day}} {{item.time}}"  readonly="readonly" name="operations[{{item.id}}].time"/></div>
                    <div class="listAction" id="clc"><input type="text" readonly="readonly" value="{{item.name}}" id="chuliIpt" name="operations[{{item.id}}].name"/></div>
                    <div class="action hide animated">
                       <ul id="123" class="animated">
                            <li id="edit" @click='edit($index)' data-toggle="modal" data-target="#myModal1">编辑</li>
                            <li id="add" @click="add()">增加</li>
                            <li id="delete">删除</li>
                        </ul>
                	</div>
            	</li>
                <li class="shenli">
                    <input type="hidden" name="operations[3].type" value="4" >
                    <div class="listTime" id="sl"><input type="text" value="2018-3-3 10:00" name="operations[3].time" readonly="readonly"/></div>
                <div class="listAction"><input type="text" readonly="readonly" value="审理"  name="operations[3].name"/></div>
                <div class="action hide animated">
                    <ul>
                        <li data-toggle="modal" data-target="#myModal">编辑</li>
                    </ul>
                </div>
            </li>
                <li class="jiean">
                    <input type="hidden" name="operations[4].type" value="5" >
                    <div class="listTime" id="ja"><input type="text" value="2018-3-3 10:00" readonly="readonly" name="operations[4].time"/></div>
                <div class="listAction"><input type="text" readonly="readonly" value="结案" name="operations[4].name"/></div>
                <div class="action hide animated">
                    <ul>
                        <li data-toggle="modal" data-target="#myModal">编辑</li>
                    </ul>
                </div>
            </li>
            </ul>
            <button class="reset btn btn-default"  type="reset">恢复默认</button>
            <button class="next btn btn-default" type="submit" data-ajax="false">下一步</button>
        </form>
        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	        <div class="modal-dialog" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                    <h4 class="modal-title" id="myModalLabel1">编辑内容</h4>
	                </div>
	                <div class="modal-body">
				                    名称： <br/><br/><input type="text" placeholder="请输入流程名称" class="form-control name" v-model="name">
				                    年/月/日：<br/> <br/><input name="time" type="date" class="form-control date1" v-model="day"/>  <br/>
				                    时间：<br/> <br/><input type="time" class="form-control time" v-model="time"/>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
	                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click='yes'>确认修改</button>
	                </div>
	            </div>
	        </div>
    	</div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">编辑内容</h4>
                    </div>
                    <div class="modal-body">
                        年/月/日：<br/> <br/><input name="time" type="date" class="form-control date1"/>  <br/>
                        时间：<br/> <br/><input type="time" class="form-control time"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="yes">确认修改</button>
                    </div>
                </div>
            </div>
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
<script src="lib/js/vue.min.js"></script>
<script type="text/javascript" src="http://www.sz886.com/js/jquery-1.9.1.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery.mobile.custom.min.js"></script>
<script>
    $(function () {
        $(document).on("swipeleft","li",function () {
           $(this).children('.action').removeClass('hide').removeClass('zoomOutLeft').addClass('zoomInRight');
        }).on("swiperight","li",function () {
            $(this).children('.action').removeClass('hide').removeClass('zoomInRight').addClass('zoomOutLeft');
        });
        var name = "";
        var this_time = "";
        var time = "";
        var type = "";
        $(".shenpi ul li").click(function () {
           $(".date1").change(function () {
                this_time = $(this).val();
           });
            $(".time").change(function () {
                time = $(this).val();
            });
            type = "#sp input";
        });
        $(".weituo ul li").click(function () {
           $(".date1").change(function () {
                this_time = $(this).val();
           });
            $(".time").change(function () {
                time = $(this).val();
            });
            type = "#wt input"
        });
        $(".shenli ul li").click(function () {
           $(".date1").change(function () {
                this_time = $(this).val();
           });
            $(".time").change(function () {
                time = $(this).val();
            });
           type="#sl input"
        });
        $(".jiean ul li").click(function () {
            $(".date1").change(function () {
                this_time = $(this).val();
            });
            $(".time").change(function () {
                time = $(this).val();
            });
            type = "#ja input"
        });
        $("#edit").on("click",function () {
            $(".date1").change(function () {
                this_time = $(this).val();
            });
            $(".time").change(function () {
                time = $(this).val();
            });
            $(".name").change(function () {
                name = $(this).val();
            });
            type = "#cl input";

        });
        $("ul").on("click","#delete",function () {
            $(this).parent().parent().parent("li").remove();
        });
        $("#yes").click(function () {
            if(this_time==""||time==""){
                return false;
            }else if(name.length>0){
                $(type).val(""+this_time+" "+time+"");
                $("#chuliIpt").val(name);
            }
            else{
                $(type).val(""+this_time+" "+time+"");
            }
            
        });

    })
    new Vue({
        el:'.wrap',
        data:{
            chuliList:[
                {id:2,name:"案件处理",day:'2018-03-31',time:'00:00'}
            ],
            index:4,
            zhuangtai:0,
            name:'',
            day:'',
            time:''
        },
        methods:{
            add: function () {
                this.index++;
                this.chuliList.push({id:this.index,name:"案件处理",day:'1971-01-01',time:'00:00'});
            },
            edit: function (index) {
                this.zhuangtai = index
            },
            yes: function () {
                this.chuliList[this.zhuangtai].name = this.name,
                this.chuliList[this.zhuangtai].day =this.day,
                this.chuliList[this.zhuangtai].time = this.time
            }
        }
    })
</script>
      