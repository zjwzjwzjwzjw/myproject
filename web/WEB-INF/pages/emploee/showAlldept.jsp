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
            $("#thirdDiv").hide()
            $("#dept").click(function(){
                $("#thirdDiv").hide()
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
            $("#searchEmp").click(function(){
                $("#thirdDiv").show()
                $("#secondDiv").hide()
            })
            $("#firstSelect").blur(function(){
                var dept = $(this).val();//得到第一个下拉列表的值
                $("#secondSelect").empty()
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
                        $("#secondSelect").append(html)
                    }
                })
            })
            $("#select").click(function(){
                var dept=$("#firstSelect").val()
                var job=$("#secondSelect").val()
                $("#tab").empty()
                $("#secondSelect").empty()
                $.ajax({
                    url:"/emp/findEmpByDeptAndJob",
                    type:"POST",
                    dataType:"json",
                    data:{"dept":dept,"job":job},
                    success:function(data){
                        $.each(data,function(idx,item){
                            $("#tab").append("<tr><td>员工名</td><td>部门</td><td>职位</td><td>联系电话</td><td>邮箱</td></tr><tr>" +
                                    "<td>"+item.ename +"</td>" +
                                    "<td>"+item.edept+"</td>" +
                                    "<td>"+item.ejob+"</td>" +
                                    "<td>"+item.etel+"</td>" +
                                    "<td>"+item.eemail+"</td>" +
                                    "</tr>")
                        });

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
        <button id="searchEmp">查看公司员工</button>
    </table>
    <div id="secondDiv">
    </div>
    <a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a>
    <div id="thirdDiv">
       部门 <select id="firstSelect">
            <c:forEach items="${requestScope.dept}" var="dept">
                    <option>${dept.dName}</option>
            </c:forEach>
        </select>
        职位<select  id="secondSelect">
                <c:forEach items="${requestScope.job}" var="job">
                    <c:if test="${job.dId==1}">
                        <option>${job.jName}</option>
                    </c:if>
                </c:forEach>
            </select>
        <button id="select">查询</button>
        <table border="2px" cellspacing="0" id="tab">
        </table>
    </div>
</body>
</html>
