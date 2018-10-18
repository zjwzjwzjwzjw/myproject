<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/13
  Time: 19:41
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
    <table border="2px" cellspacing="0">
        <tr>
            <td>金额</td>
            <td>原因</td>
            <td>时间</td>
        </tr>
        <c:forEach items="${requestScope.publishment}" var="pub">
            <tr>
                <td>${pub.publishmentSalary}</td>
                <td>${pub.pcontext}</td>
                <td>
                    <f:formatDate value="${pub.ptime}" pattern="yyyy-MM-dd"/>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3" style="text-align: center"><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a></td>
        </tr>
    </table>
</body>
</html>
