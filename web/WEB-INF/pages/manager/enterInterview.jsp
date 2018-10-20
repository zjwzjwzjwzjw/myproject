<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/16
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="base.jsp"%>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script>
        $(function(){
            var uid=$("#uid").val()
            var rid=$("#rid").val();
            var iid=$("#iid").val();
            var args={"iid":iid,"uid":uid,"rid":rid}
            $("#success").click(function(){
                var name=$("#name").val();
                var flag=confirm("确认录取"+name+"吗?")
                if(flag){
                    $.post("/man/success",args,function(data){
                        window.location.href="/man/showComputerResumes"
                    })
                }
            })
            $("#default").click(function(){
                var name=$("#name").val();
                var diff="aaa"
                var args1={"iid":iid,"uid":uid,"rid":rid,"diff":diff}
                var flag=confirm("确认淘汰"+name+"吗?")
                if(flag){
                    $.post("/man/success",args,function(data){
                        window.location.href="/man/showComputerResumes"
                    })
                }
            })
        })
    </script>
</head>
<body>
<table border="2px" cellspacing="0">
    <input type="hidden" id="uid" value="${requestScope.interviewTable.uid}">
    <input type="hidden" id="rid" value="${requestScope.interviewTable.rid}">
    <input type="hidden" id="iid" value="${requestScope.interviewTable.iid}">
    <tr>
        <td colspan="4" style="text-align: center">简历</td>
    </tr>
    <tr>
        <td>真实姓名</td>
        <td id="name">${requestScope.interviewTable.tname}</td>
        <td>性别</td>
        <td>${requestScope.interviewTable.gender}</td>
    </tr>
    <tr>
        <td>年龄</td>
        <td>${requestScope.interviewTable.age}</td>
        <td>学历</td>
        <td>${requestScope.interviewTable.education}</td>
    </tr>
    <tr>
        <td>联系方式</td>
        <td>${requestScope.interviewTable.tel}</td>
        <td>EMAIL</td>
        <td>${requestScope.interviewTable.email}</td>
    </tr>
    <tr>
        <td>应聘职位</td>
        <td>${requestScope.interviewTable.dept}&nbsp;${requestScope.interviewTable.job}
        </td>
        <td>政治面貌</td>
        <td>
            ${requestScope.interviewTable.policitalStatus}
        </td>
    </tr>
    <tr>
        <td>上份工作</td>
        <td>${requestScope.interviewTable.beforeJob}</td>
        <td>工作经验</td>
        <td>${requestScope.interviewTable.workExperience}</td>
    </tr>
    <tr>
        <td>期望薪资</td>
        <td>${requestScope.interviewTable.workExperience}</td>
        <td>兴趣爱好</td>
        <td>${requestScope.interviewTable.favorite}</td>
    </tr>
    <tr>
       <td colspan="2" id="success">录取</td>
        <td colspan="2" id="default">不录取</td>
    </tr>
</table>
</body>
</html>
