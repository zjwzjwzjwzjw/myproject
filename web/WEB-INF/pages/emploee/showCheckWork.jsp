<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/15
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty requestScope.checkworks}">
    <h2>暂无考勤</h2>
</c:if>
<c:if test="${!empty requestScope.checkworks}">
    <table>
        <tr>
            <td>员工ID</td>
            <td>日期</td>
            <td>上班时间</td>
            <td>下班时间</td>
            <td>已工作多少天</td>
            <td>需工作多少天</td>
            <td>状态</td>
        </tr>
        <c:forEach items="${requestScope.checkworks}" var="checkwork">
            <tr>
                <td>${checkwork.uid}</td>
                <td>${checkwork.cyear}-${checkwork.cmonth}-${checkwork.cdate}</td>
                <td>${checkwork.cbegintime}</td>
                <td>${checkwork.caftertime}</td>
                <td>${checkwork.cworkday}</td>
                <td>${checkwork.cneedworkday}</td>
                <td>${checkwork.cwtype}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${sessionScope.user.utype==1}">
    <a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
</c:if>
<c:if test="${sessionScope.user.utype==0}">
    <a href="/man/showEmploee">返回</a>
</c:if>
</body>
</html>
