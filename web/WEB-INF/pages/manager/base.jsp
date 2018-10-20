<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/19
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .b{
            background-image:url(/image/3.jpg);
            height: 100%;
            width: 100%;
            background-size: cover;
        }
    </style>
    <link rel="stylesheet" href="../../../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../bootstrap-table/dist/bootstrap-table.min.css">
    <script src="../../../js/jquery-1.10.2.min%20(1).js"></script>
    <script src="../../../bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../bootstrap-table/dist/bootstrap-table.min.js"></script>
</head>
<body class="b">
<div style="width: 100%;height: 30px;color: red;background-color: #00CC00">当前用户：${sessionScope.user.accName}</div>
<nav class="navbar navbar-default">
    <div class="container-fluid" style="background-color: #00CCFF">
        <ul class="nav navbar-nav">
            <li><a href="/man/showComputerResumes">应聘管理</a></li>
            <li><a href="/man/showAllDeptAndJob">部门职位</a></li>
            <li><a href="/man/showTrainTable">培训管理</a></li>
            <li><a href="/man/showEmploee">员工管理</a></li>
            <li><a href="/man/showpublishment">奖惩管理</a></li>
            <li><a href="">薪资管理</a></li>
            <li><a href="/main.jsp" id="exit">退出</a></li>
        </ul>
    </div>
</nav>
</body>
</html>