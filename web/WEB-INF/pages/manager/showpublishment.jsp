<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/18
  Time: 19:33
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
           $(".del").click(function(){
               var pid=$(this).parent().children()[0].innerHTML
               var flag=confirm("确认删除"+pid+"号记录吗?")
               if(flag){
                   $.post("/man/delpublishment",{"pid":pid},function(data){
                       alert("删除成功")
                   })
                   $(this).parent().remove()
               }
           })
            $(".edit").click(function(){
                var pid=$(this).parent().children()[0].innerHTML
                var reason=$(this).parent().children()[3].innerHTML
                var money=$(this).parent().children()[4].innerHTML
                var pcontext = window.prompt('奖赏原因',reason);
                var salary = window.prompt('奖赏金额',money);
                if(pcontext==""||salary==""){
                   alert("理由或金额不能为空")
                }else{
                    $.post("/man/editpublishment",{"pid":pid,"pcontext":pcontext,"publishmentsalary":salary},function(data){
                        alert("修改成功")
                        widows.location.href="/man/showpublishment"
                    })
                }
            })
        })
    </script>
</head>
<body>
<c:if test="${empty requestScope.publishment}">
    <h2>暂无奖惩信息</h2>
</c:if>
<c:if test="${!empty requestScope.publishment}">
    <table border="2px" cellspacing="0">
        <tr>
            <td>奖惩ID</td>
            <td>员工ID</td>
            <td>奖惩时间</td>
            <td>奖惩理由</td>
            <td>金额</td>
            <td>修改</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${requestScope.publishment}" var="pub">
            <tr>
                <td>${pub.pid}</td>
                <td>${pub.uid}</td>
                <td>
                    <f:formatDate value="${pub.ptime}" pattern="yyyy-MM-dd"/>
                </td>
                <td>${pub.pcontext}</td>
                <td>${pub.publishmentSalary}</td>
                <td class="edit">修改</td>
                <td class="del">删除</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
</body>
</html>
