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
    <link rel="stylesheet" href="lib/css/animate.css"/>
    <script src="js/index.js"></script>


</head>
<body>
<div class="wrap">
    <div class="setHeader">
        <a href="case.html"><span class="glyphicon glyphicon-chevron-left"></span> Case</a>
        <h3>NEW CASE</h3>
    </div>
    <div class="newCaseItem">
        <form action="case/new_step1" method="post">
            <div class="caseInfo">
                <table>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>审级
                            </div>
                        </td>
                        <td>
                            <select name="level" id="case" class="form-control">
                                <option value="一审">一审</option>
                                <option value="二审">二审</option>
                                <option value="三审">三审</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>案由
                            </div>
                        </td>
                        <td>
                            <textarea name="summary" id="reason" placeholder="请输入案由"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                开庭时间
                            </div>
                        </td>
                        <td>
                            <span class="glyphicon glyphicon-calendar"></span>
                            <input type="date" class="form-control" name="opentime"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                举证期限
                            </div>
                        </td>
                        <td>
                            <span class="glyphicon glyphicon-calendar"></span>
                            <input type="date" class="form-control" name="termtime"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="newConsignor">委托人 <span class="glyphicon glyphicon-plus" ></span></div>
            <div class="caseInfo consignor ">
                <table>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>身份
                            </div>
                        </td>
                        <td>
                            <select name="trusteeoppositions[0].identity" id="id" class="form-control" disabled>
                                <option value="0" selected>个人</option>
                                <option value="1">公司</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>姓名
                            </div>
                        </td>
                        <td>
                            <input name="trusteeoppositions[0].person_name" style="width: 11.111rem" type="text" placeholder="请输入您的姓名" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>性别
                            </div>
                        </td>
                        <td>
                            <select name="trusteeoppositions[0].sex" id="sex" class="form-control">
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>出生日期
                            </div>
                        </td>
                        <td>
                            <span class="glyphicon glyphicon-calendar"></span>
                            <input type="date" class="form-control" name="trusteeoppositions[0].person_birthday"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>住所
                            </div>
                        </td>
                        <td>
                            <input name="trusteeoppositions[0].person_address" type="text" style="width: 11.111rem" placeholder="请输入您的住所" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>身份证号码
                            </div>
                        </td>
                        <td>
                            <input type="text" name="trusteeoppositions[0].person_id_number" style="width: 11.111rem" placeholder="请输入您的身份证号码" class="form-control"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="newOppo">对方 <span class="glyphicon glyphicon-plus" ></span></div>
            <div class="caseInfo opposite">
                <table>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>身份
                            </div>
                        </td>
                        
                        <td>
                        	<input style="display:none" name="trusteeoppositions[1].type" value="1">
                            <select name="trusteeoppositions[1].identity" id="oppoId" class="form-control" disabled>
                                <option value="0">个人</option>
                                <option value="1" selected>公司</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>公司名称
                            </div>
                        </td>
                        <td>
                            <input name="trusteeoppositions[1].company_name" style="width: 11.111rem" type="text" placeholder="请输入您的姓名" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>地址
                            </div>
                        </td>
                        <td>
                            <input name="trusteeoppositions[1].company_address"  style="width: 11.111rem" type="text" placeholder="请输入您的地址" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                <span>*</span>法人代表
                            </div>
                        </td>
                        <td>
                            <input name="trusteeoppositions[1].company_represent"  style="width: 11.111rem" type="text" placeholder="请输入您的法人代表" class="form-control"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="newCourt">法院</div>
            <div class=" caseInfo court">
                <table>
                    <tr>
                        <td>
                            <div class="name">
                                名称
                            </div>
                        </td>
                        <td>
                            <input name="name" style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="name">
                                案号
                            </div>
                        </td>
                        <td>
                            <input name="number"  style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                庭室
                            </div>
                        </td>
                        <td>
                            <input name="chamber"  style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                地址
                            </div>
                        </td>
                        <td>
                            <input name="address" style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="newJudge">法官 <span class="glyphicon glyphicon-plus" ></span></div>
            <div class="caseInfo judge">
                <table>

                    <tr>
                        <td>
                            <div class="name">
                                姓名
                            </div>
                        </td>
                        <td>
                            <input name="judges[0].name" style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="name">
                                电话
                            </div>
                        </td>
                        <td>
                            <input name="judges[0].tel" style="width: 11.111rem" type="tel"  class="form-control"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="name">
                                主审
                            </div>
                        </td>
                        <td>
                            <input  name="judges[0].main" value="0"  style="width: 1rem;height: 1rem" type="checkbox"  class="form-control check"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="newLawyer">律师 <span class="glyphicon glyphicon-plus" ></span></div>
            <div class="caseInfo lawyer">
                <table>

                    <tr>
                        <td>
                            <div class="name">
                                姓名
                            </div>
                        </td>
                        <td>
                            <input name="layers[0].name"  style="width: 11.111rem" type="text"  class="form-control"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="name">
                                电话
                            </div>
                        </td>
                        <td>
                            <input name="layers[0].tel"  style="width: 11.111rem" type="tel"  class="form-control"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="name">
                                主办
                            </div>
                        </td>
                        <td>
                            <input name="layers[0].main" value="0" style="width: 1rem;height: 1rem" type="checkbox"  class="check form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                事务所
                            </div>
                        </td>
                        <td>
                            <input name="layers[0].firm" style="width: 11.111rem" type="tel"  class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="name">
                                律所案件号
                            </div>
                        </td>
                        <td>
                            <input name="layers[0].number"  style="width: 11.111rem" type="tel"  class="form-control"/>
                        </td>
                    </tr>
                </table>
            </div>
            <button type="submit" class="set btn btn-info ">创建
            </button>
        </form>
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
        var personIndex = 1;
        var judgeIndex = 0;
        var lawyerIndex = 0;
        $(".newConsignor").click(function () {
            personIndex++;
            $(".consignor").eq(0).after('<div class="caseInfo consignor ">'+
            '<table>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '<span>*</span>身份'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<select name="trusteeoppositions['+personIndex+'].identity" id="id" class="form-control" disabled>'+
            '<option value="0" selected>个人</option>'+
            '<option value="1">公司</option>'+
            '</select>'+
            '</td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '<span>*</span>姓名'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input name="trusteeoppositions['+personIndex+'].person_name" style="width: 11.111rem" type="text" placeholder="请输入您的姓名" class="form-control"/>'+
            '</td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '<span>*</span>性别'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<select name="trusteeoppositions['+personIndex+'].sex" id="sex" class="form-control">'+
            '<option value="one">男</option>'+
            '<option value="two">女</option>'+
            '</select>'+
            '</td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '<span>*</span>出生日期'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<span class="glyphicon glyphicon-calendar"></span>'+
            '<input name="trusteeoppositions['+personIndex+'].person_birthday" type="date" class="form-control"/>'+
            '</td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '<span>*</span>住所'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input  name="trusteeoppositions['+personIndex+'].person_address" type="text" style="width: 11.111rem" placeholder="请输入您的住所" class="form-control"/>'+
            ' </td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        <span>*</span>身份证号码'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input type="text"  name="trusteeoppositions['+personIndex+'].person_id_number" style="width: 11.111rem" placeholder="请输入您的身份证号码" class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '</table>'+
            '</div>')
        });
        $(".newOppo").click(function () {
            personIndex++;
            $(".opposite").eq(0).after('<div class="caseInfo opposite">'+
                    '<table>'+
                    '<tr>'+
                    '<td>'+
                    '<div class="name">'+
                    '<span>*</span>身份'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<select name="trusteeoppositions['+personIndex+'].identity" id="oppoId" class="form-control" disabled>'+
                    '<option value="0">个人</option>'+
            '<option value="1" selected>公司</option>'+
            '</select>'+
            '</td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        <span>*</span>公司名称'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input name="trusteeoppositions['+personIndex+'].company_name"  style="width: 11.111rem" type="text" placeholder="请输入您的姓名" class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        <span>*</span>地址'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input name="trusteeoppositions['+personIndex+'].company_address" style="width: 11.111rem" type="text" placeholder="请输入您的地址" class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        <span>*</span>法人代表'+
            '</div>'+
            '</td>'+
            '<td>'+
            '<input name="trusteeoppositions['+personIndex+'].company_represent"  style="width: 11.111rem" type="text" placeholder="请输入您的法人代表" class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '</table>'+
            '</div>')
        });
        $(".newJudge").click(function () {
            judgeIndex++;
            $(".judge").eq(0).after('<div class="caseInfo judge">'+
                    '<table>'+

                    '<tr>'+
                    '<td>'+
                    '<div class="name">'+
                    '姓名'+
                    '</div>'+
            '</td>'+
            '<td>'+
            '<input name="judges['+judgeIndex+'].judge" style="width: 11.111rem" type="text"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+

            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        电话'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input name="judges['+judgeIndex+'].tel" style="width: 11.111rem" type="tel"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+

            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        主审'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input name="judges['+judgeIndex+'].main" value = "0"  style="width: 1rem;height: 1rem" type="checkbox"  class="form-control check"/>'+
            '        </td>'+
            '</tr>'+
            '</table>'+
            '</div>')
        });
        $(".newLawyer").click(function () {
            lawyerIndex++;
            $(".lawyer").eq(0).after('<div class="caseInfo lawyer">'+
                    '<table>'+

                    '<tr>'+
                    '<td>'+
                    '<div class="name">'+
                    '姓名'+
                    '</div>'+
            '</td>'+
            '<td>'+
            '<input name="layers['+lawyerIndex+'].name" style="width: 11.111rem" type="text"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+

            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        电话'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input  name="layers['+lawyerIndex+'].tel" style="width: 11.111rem" type="tel"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+

            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        主办'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input  name="layers['+lawyerIndex+'].main" value="0" style="width: 1rem;height: 1rem" type="checkbox"  class="form-control check"/>'+
            '        </td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        事务所'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input   name="layers['+lawyerIndex+'].firm" style="width: 11.111rem" type="tel"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '<tr>'+
            '<td>'+
            '<div class="name">'+
            '        律所案件号'+
            '        </div>'+
            '</td>'+
            '<td>'+
            '<input name="layers['+lawyerIndex+'].number"  style="width: 11.111rem" type="tel"  class="form-control"/>'+
            '        </td>'+
            '</tr>'+
            '</table>'+
            '</div>')
        });
        $(".set").click(function () {
            $(".check:checked").attr("value",1);
        })
    })
</script>
