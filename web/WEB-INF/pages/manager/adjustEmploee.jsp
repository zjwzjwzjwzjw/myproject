<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/18
  Time: 9:36
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
           $("form").submit(function(){
               var dept=$("#dept").val()
               var job=$("#job").val()
               var name=$("#name").text()
               var flag=confirm("确认将"+name+"调动到"+dept+","+job+"职位吗?")
               if(flag){
                   var uid=$("#uid").val()
                   $.post("/man/editEmploee",{"uid":uid,"dept":dept,"job":job},function(data){
                       alert("调动成功")
                       window.location.href="/man/showEmploee"
                   })
               }
               return false;
           })
            $("#dept").blur(function(){
                var dept = $("#dept").val();//得到第一个下拉列表的值
                $("#job").empty()
                var html=""
                $.ajax({
                    url:"/findJobs",
                    type:"POST",
                    dataType:"json",
                    data:{"dName":dept},
                    success:function(data){
                        $.each(data,function(idx,item){
                            html+="<option>"+item.jName+"</option>"
                        });
                        $(html).appendTo("#job")
                    }
                })
            })
        })
    </script>
</head>
<body>
<form>
    <input type="hidden" id="uid" value="${requestScope.emploee.uid}">
    <table border="2px" cellspacing="0">
        <tr>
            <td colspan="2">姓名</td>
            <td colspan="2" id="name">${requestScope.emploee.ename}</td>
        </tr>
        <tr>
            <td>部门</td>
            <td>
                <select  id="dept">
                    <c:forEach items="${requestScope.depts}" var="dept">
                        <c:if test="${requestScope.emploee.edept==dept.dName}">
                            <option selected="selected">${requestScope.emploee.edept}</option>
                        </c:if>
                        <c:if test="${requestScope.emploee.edept!=dept.dName}">
                            <option>${dept.dName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            <td>职位</td>
            <td>
                <select id="job">
                    <option>${requestScope.emploee.ejob}</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="确认调动"> </td>
        </tr>
    </table>
</form>
<a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
</body>
</html>
