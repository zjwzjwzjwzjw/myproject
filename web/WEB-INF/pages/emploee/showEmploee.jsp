<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/13
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script src="js/jquery-1.7.2.js"></script>
</head>
<body>
<form action="/emp/saveEmploee">
    <input type="hidden" name="uid" value="${sessionScope.user.id}">
    <input type="hidden" name="etype" value="${sessionScope.emploee.etype}">
    <table border="2px" cellpadding="0">
        <tr>
            <td colspan="5" style="text-align: center">个人信息</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td colspan="2">
                <input type="text" name="ename" value="${requestScope.emploee.ename}">
            </td>
            <td>性别</td>
            <td name="egender" >
                <input type="text" name="egender" value="${requestScope.emploee.egender}">
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td colspan="2">
                <input type="number" name="eage" value="${requestScope.emploee.eage}">
            </td>
            <td>学历</td>
            <td>
                <input type="text" name="eeducation" value="${requestScope.emploee.eeducation}">
            </td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td colspan="2">
                <input type="number" name="etel" value="${requestScope.emploee.etel}">
            </td>
            <td>邮箱</td>
            <td>
                <input type="text" name="eemail" value="${requestScope.emploee.eemail}">
            </td>
        </tr>
        <tr>
            <td >部门职位</td>
            <td>
                <input type="text" name="edept" value="${requestScope.emploee.edept}" readonly="readonly">
            </td>
            <td>
                <input type="text" name="ejob" value="${requestScope.emploee.ejob}" readonly="readonly">
            </td>
            <td>政治面貌</td>
            <td>
                <input type="text" name="epolicitalStatus" value="${requestScope.emploee.epolicitalStatus}">
            </td>
        </tr>
        <tr>
            <td>入职时间</td>
            <td colspan="2">
                <input type="date" value="<f:formatDate value='${requestScope.emploee.ebegintime}' pattern='yyyy-MM-dd'/>" readonly="readonly">
            </td>
            <td>爱好</td>
            <td>
                <input type="text" name="efavorite" value="${requestScope.emploee.efavorite}" >
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center"><input type="submit" value="保存"></td>
            <td colspan="2" style="text-align: center"><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a></td>
        </tr>
    </table>
</form>
</body>
</html>
