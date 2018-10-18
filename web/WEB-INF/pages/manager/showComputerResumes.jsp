<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/15
  Time: 20:22
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
            $(".detail").click(function(){
                var crid=$(this).parent().parent().children()[0].innerHTML
                window.location.href="/man/showDetailResumes?crid="+crid
            })
            $(".del").click(function(){
                var crid=$(this).parent().parent().children()[0].innerHTML
                var flag=confirm("确认删除吗?")
                if(flag){
                    $.post("/man/delComputerResumes",{"crid":crid},function(data){
                    })
                    $(this).parent().parent().remove()
                }
            })
        })
    </script>
</head>
<body>
<c:if test="${empty  requestScope.computerResumes}">
    尚无简历哦!
</c:if>
<c:if test="${!empty  requestScope.computerResumes}">
<table border="2px" cellspacing="0">
    <tr>
        <td>序号</td>
        <td>应聘者名字</td>
        <td>投递时间</td>
        <td>简历状态</td>
        <td>面试状态</td>
        <td>操作</td>
        <c:forEach items="${requestScope.computerResumes}" var="cresume">
            <tr class="tab">
                <td>${cresume.crid}</td>
                <td>${cresume.tname}</td>
                <td>
                    <f:formatDate value="${cresume.sendTime}" pattern="yyyy-MM-dd"/>
                </td>
                <td>${cresume.crtype}</td>
                <td>${cresume.cstype}</td>
                <td><button class="detail">查看</button><button class="del">删除</button></td>
            </tr>
        </c:forEach>
    </tr>
</table>
</c:if>
<c:if test="${!empty  requestScope.todayInterview}">
    今日面试者:
    <table border="2px" cellspacing="0">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>面试时间</td>
            <td>面试</td>
        </tr>
        <c:forEach items="${requestScope.todayInterview}" var="interview">
            <td>${interview.iid}</td>
            <td>${interview.tname}</td>
            <td>
                <f:formatDate value="${interview.iinterviewtime}" pattern="yyyy-MM-dd"/>
            </td>
            <td><a href="/man/enterinterview?iid=${interview.iid}" class="enterinterview">面试</a></td>
        </c:forEach>
    </table>
</c:if>
<a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
</body>
</html>
