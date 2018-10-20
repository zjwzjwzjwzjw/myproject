<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/18
  Time: 9:36
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
            $(".del").click(function(){
                var uid=$(this).parent().parent().children()[0].innerHTML
                var name=$(this).parent().parent().children()[1]
                $v=$(name)
                var ename=$v.text()
                var flag=confirm("确认删除"+ename+"吗?")
                if(flag){
                    var userVal = window.prompt('请输入理由','正常离职');
                    $.post("/man/delEmploee",{"uid":uid,"reason":userVal},function(data){
                        window.location.href="/man/showEmploee"
                    })
                }
                return false;
            })
            $(".salary").click(function(){
                var uid=$(this).parent().parent().children()[0].innerHTML
                $.post("/man/paySalary",{"uid":uid},function(data){
                    if(data=="1"){
                        alert("请于20号之后发放")
                    }
                    if(data=="2"){
                        alert("上月没有薪资")
                    }
                    if(data=="ok"){
                        alert("发放成功")
                    }
                    if(data=="no"){
                        alert("不能重复发放")
                    }
                })
                return false;
            })
        })
    </script>
</head>
<body>
    <table border="2px" cellspacing="0">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td colspan="4">操作</td>
        </tr>
        <c:forEach items="${requestScope.emploees}" var="emp">
            <c:if test="${emp.etype==1}">
                <tr>
                    <td>${emp.uid}</td>
                    <td><a href="/man/showDetailEmploee?uid=${emp.uid}">${emp.ename}</a></td>
                    <td><a href="/man/adjustEmploee?uid=${emp.uid}">人事调动</a></td>
                    <td><a href="/emp/showcheckwork?uid=${emp.uid}" class="checkwork">考勤</a></td>
                    <td><a href="#" class="salary">发放工资</a></td>
                    <td><a href="#" class="del">开除</a></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
