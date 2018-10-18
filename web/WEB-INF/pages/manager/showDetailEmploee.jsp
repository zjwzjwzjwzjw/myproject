<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/18
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script src="js/jquery-1.7.2.js"></script>
    <script>
        $(function(){
            $("#first").hide()
            $("#reward").click(function(){
                $("#first").show()
            })
            $("#confirm").click(function(){
                var reason=$("#reason").val()
                var money=$("#money").val()
                var uid=$("#uid").val()
                if(reason==""){
                    alert("奖赏理由不能为空")
                }else{
                    var args={"uid":uid,"publishmentSalary":money,"pcontext":reason}
                    $.post("/man/savePublishment",args,function(data){
                        alert("已赏")
                        $("#first").hide()
                    })
                }
            })
        })
    </script>
</head>
<body>
<input type="hidden" id="uid" value="${requestScope.emploee.uid}">
<table>
    <tr>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td>${requestScope.emploee.ename}</td>
        <td>性别</td>
        <td>${requestScope.emploee.egender}</td>
    </tr>
    <tr>
        <td>年龄</td>
        <td>${requestScope.emploee.eage}</td>
        <td>学历</td>
        <td>${requestScope.emploee.eeducation}</td>
    </tr>
    <tr>
        <td>联系方式</td>
        <td>${requestScope.emploee.etel}</td>
        <td>邮箱</td>
        <td>${requestScope.emploee.eemail}</td>
    </tr>
    <tr>
        <td>入职时间</td>
        <td>
            <f:formatDate value="${requestScope.emploee.ebegintime}" pattern="yyyy-MM-dd"/>
        </td>
        <td>职位</td>
        <td>${requestScope.emploee.edept}${requestScope.emploee.ejob}</td>
    </tr>
</table>
<button id="reward">赏</button><button><a href="/man/showEmploee">返回</a></button>
<form id="first">
    <table>
        <h2>赏</h2>
        <tr>
            <td>奖赏理由</td>
            <td><input type="text" id="reason"></td>
        </tr>
        <tr>
            <td>金额</td>
            <td>
                <select id="money">
                    <option>50</option>
                    <option>100</option>
                    <option>200</option>
                </select>
            </td>
        </tr>
    </table>
    <input type="button" value="确定" id="confirm">
</form>
</body>
</html>
