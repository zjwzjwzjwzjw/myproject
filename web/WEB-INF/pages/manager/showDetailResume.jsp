<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/15
  Time: 21:13
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
            $("#ok").click(function(){
                var date=$("#date").val()
                if(date==""){
                    alert("面试时间不能为空")
                }else{
                    var flag=confirm("确认通知面试者"+date+"来面试吗?")
                    if(flag){
                        var crid=$("#crid").val()
                        var args={"crid":crid,"date":date}
                        var url="/man/tellUserInterview"
                        $.post(url,args,function(data){
                            alert("已成功通知该面试者")
                        })
                    }
                }
            })
        })

    </script>
</head>
<body>
<form>
<table border="2px" cellspacing="0">
    <input type="hidden" id="crid" value="${requestScope.computerResumes.crid}">
    <tr>
        <td colspan="4" style="text-align: center">简历</td>
    </tr>
    <tr>
        <td>真实姓名</td>
        <td>${requestScope.computerResumes.tname}</td>
        <td>性别</td>
        <td>${requestScope.computerResumes.gender}</td>
    </tr>
    <tr>
        <td>年龄</td>
        <td>${requestScope.computerResumes.age}</td>
        <td>学历</td>
        <td>${requestScope.computerResumes.education}</td>
    </tr>
    <tr>
        <td>联系方式</td>
        <td>${requestScope.computerResumes.tel}</td>
        <td>EMAIL</td>
        <td>${requestScope.computerResumes.email}</td>
    </tr>
    <tr>
        <td>应聘职位</td>
        <td>${requestScope.computerResumes.dept},${requestScope.computerResumes.job}</td>
        <td>政治面貌</td>
        <td>${requestScope.computerResumes.policitalStatus}</td>
    </tr>
    <tr>
        <td>上份工作</td>
        <td>${requestScope.computerResumes.beforeJob}</td>
        <td>工作经验</td>
        <td>${requestScope.computerResumes.workExperience}</td>
    </tr>
    <tr>
        <td>期望薪资</td>
        <td>${requestScope.computerResumes.salary}
        <td>兴趣爱好</td>
        <td>${requestScope.computerResumes.favorite}</td>
    </tr>
    <tr>
        <td colspan="3">
            <input type="button" id="interview" value="面试">
            <input type="date" id="date" value="${requestScope.computerResumes.time}">
            <input type="button" id="ok" value="通知面试">
        </td>
        <td style="text-align: center">
            <button><a href="/man/showComputerResumes" style="text-underline: none">返回</a></button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
