<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/13
  Time: 20:18
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
            $(".dep").click(function(){
                var dept = $(this).text();//得到部门的名字
                $("#secondDiv").empty()
                var html="<h2>职位</h2>"
                $.ajax({
                    url:"/findJobs",
                    type:"POST",
                    dataType:"json",
                    data:{"dName":dept},
                    success:function(data){
                        $.each(data,function(idx,item){
                            html+="<a>"+item.jName+"</a><br>"
                        });
                        $(html).appendTo("#secondDiv")
                    }
                })
            })
        })
    </script>
</head>
<body>
    <table id="firstDiv" style="float: left">
        <h2>部门</h2>
        <c:forEach items="${requestScope.dept}" var="dept">
            <tr>
                <td class="dep">${dept.dName}</td>
            </tr>
        </c:forEach>
    </table>
    <div id="secondDiv">
    </div>
    <a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
</body>
</html>
