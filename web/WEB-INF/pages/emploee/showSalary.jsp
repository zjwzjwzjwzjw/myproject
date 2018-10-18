<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/15
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script src="js/jquery-1.7.2.js"></script>
</head>
<body>
<table border="2px" cellpadding="0">
    <tr>
        <td>基本工资</td>
        <td>加班工资</td>
        <td>绩效工资</td>
        <td>奖惩金额</td>
        <td>社保</td>
        <td>实发工资</td>
    </tr>
    <c:forEach items="${requestScope.salary}" var="salary">
        <tr>
            <td>${salary.baseSalary}</td>
            <td>${salary.overTimeSalary}</td>
            <td>${salary.performanceSalary}</td>
            <td>${salary.publishmentSalary}</td>
            <td>${salary.socical}</td>
            <td>${salary.actualSalary}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3" style="text-align: center"><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a></td>
    </tr>
</table>
</body>
</html>
