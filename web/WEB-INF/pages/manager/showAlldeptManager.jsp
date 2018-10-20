<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/13
  Time: 20:18
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
            $("#thirdTable").hide()
            $("#fourTable").hide()
            $("#fiveTable").hide()
            $("#sixTable").hide()
            $("#sevenTable").hide()
            $(".dep").click(function(){
                $("#thirdTable").hide()
                $("#fourTable").hide()
                $("#fiveTable").hide()
                $("#sixTable").hide()
                $("#sevenTable").hide()
                var dept = $(this).text();//得到部门的名字
                $("#secondTable").empty()
                $("#secondTable").append("<h2>部门</h2>")
                $.ajax({
                    url:"/findJobs",
                    type:"POST",
                    dataType:"json",
                    data:{"dName":dept},
                    success:function(data){
                        $.each(data,function(idx,item){
                            $("#secondTable").append("<tr><td>"+item.jName+"</td></td></tr>")
                        });
                    }
                })
            })
            $(".delDept").click(function(){
                var deptname=$(this).parent().children()[0].innerHTML
                var flag=confirm("确认删除"+deptname)
                if(flag){
                    var url="/man/delDept"
                    $.post(url,{"deptName":deptname},function(data){
                        if(data=="yes"){
                            alert("删除成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }else{
                            alert("该部门存在在职员工,不能删除")
                        }
                    })
                }
            })
            $("#addDept").click(function(){
                $("#secondTable").hide()
                $("#thirdTable").show()
                $("#fourTable").hide()
                $("#fiveTable").hide()
                $("#sixTable").hide()
                $("#sevenTable").hide()
            })
            $("#confirm").click(function(){
                var deptName=$("#addDeptName").val()
                if(deptName==""){
                    alert("部门名不能为空")
                }else{
                    url="/man/addDept"
                    $.post(url,{"deptName":deptName},function(data){
                        if(data=="no"){
                            alert("部门名已存在")
                        }else{
                            alert("创建成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }
                    })
                }

            })
            $("#cancel").click(function(){
                $("#thirdTable").hide()
            })
            var deptname=""
            $(".editDept").click(function(){
                deptname=$(this).parent().children()[0].innerHTML
                $("#secondTable").hide()
                $("#fourTable").show()
                $("#thirdTable").hide()
                $("#fiveTable").hide()
                $("#sixTable").hide()
                $("#sevenTable").hide()
            })
            $("#edit").click(function (){
                var lastName=$("#editDeptName").val()
                if(lastName==""){
                    alert("部门名不能为空")
                }else {
                    var url = "/man/editDept"
                    $.post(url, {"deptName": deptname, "lastName": lastName}, function (data) {
                        if(data=="yes"){
                            alert("修改成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }else{
                            alert("部门名已存在")
                        }
                    })
                }
            })
            $("#cancelEdit").click(function(){
                $("#fourTable").hide()
            })
            $("#operatorJob").click(function(){
                $("#secondTable").hide()
                $("#fiveTable").show()
                $("#fourTable").hide()
                $("#thirdTable").hide()
                $("#sixTable").hide()
                $("#sevenTable").hide()
            })
            $(".delJob").click(function(){
                var jId=$(this).parent().children()[0].innerHTML
                var jobname=$(this).parent().children()[1].innerHTML
                var flag=confirm("确认删除"+jobname)
                if(flag){
                    var args={"jId":jId}
                    $.post("/man/delJob",args,function(data){
                        if(data=="no"){
                            alert("该职位存在在职员工不能删除")
                        }else{
                            alert("删除成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }
                    })
                }
            })
            var jId
            $(".editJob").click(function(){
                jId=$(this).parent().children()[0].innerHTML
                $("#secondTable").hide()
                $("#sixTable").show()
                $("#fourTable").hide()
                $("#thirdTable").hide()
                $("#fiveTable").hide()
                $("#sevenTable").hide()
            })
            $("#editJob").click(function (){
                var newJobName=$("#editJobName").val()
                if(newJobName==""){
                    alert("职位名不能为空")
                }else {
                    var url = "/man/editJob"
                    $.post(url, {"jId":jId,"newJobName":newJobName},function (data) {
                        if(data=="yes"){
                            alert("修改成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }else{
                            alert("职位已存在")
                        }
                    })
                }
            })
            $("#cancelEditJob").click(function(){
                $("#sixTable").hide()
            })
            $("#addJob").click(function(){
                $("#secondTable").hide()
                $("#sevenTable").show()
                $("#fourTable").hide()
                $("#thirdTable").hide()
                $("#fiveTable").hide()
                $("#sixTable").hide()
            })
            $("#addJobConfirm").click(function(){
                var deptname=$("#dept").val();
                var jobname=$("#addJobName").val();
                if(jobname==""){
                    alert("职位不能为空")
                }else{
                    $.post("/man/addJob",{"deptname":deptname,"jobname":jobname},function(data){
                        if(data=="yes"){
                            alert("增加成功")
                            window.location.href="/man/showAllDeptAndJob"
                        }else{
                            alert("该职位已存在")
                        }
                    })
                }
            })
            $("#cancelAddJob").click(function(){
                $("#sevenTable").hide()
            })
        })
    </script>
</head>
<body>
    <table id="firstTable" style="float: left">
        <h2>部门</h2>
        <c:forEach items="${requestScope.depts}" var="dept">
            <tr>
                <td class="dep">${dept.dName}</td>
                <td class="delDept">删除</td>
                <td class="editDept">修改</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3" id="addDept">新增部门</td>
        </tr>
        <tr>
            <td colspan="3" id="operatorJob"><button>职位操作</button></td>
        </tr>
    </table>
    <table id="secondTable">
    </table>
    <form id="thirdTable">
        <h2>新增部门</h2>
        新增部门名:<input type="text" id="addDeptName"><br>
        &nbsp;&nbsp;&nbsp;<input type="button" id="confirm" value="确认">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="cancel" value="取消">
    </form>
    <form id="fourTable">
        <h2>修改部门</h2>
        新的部门名:<input type="text" id="editDeptName"><br>
        &nbsp;&nbsp;&nbsp;<input type="button" id="edit" value="确认">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="cancelEdit" value="取消">
    </form>
<form id="fiveTable">
    <h2>职位操作</h2>
    <table>
        <tr>
            <td colspan="4">职位名</td>
        </tr>
        <c:forEach items="${requestScope.jobs}" var="job">
            <tr>
                <td>${job.jId}</td>
                <td>${job.jName}</td>
                <td class="delJob">删除</td>
                <td class="editJob">重命名</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4" id="addJob">新增</td>
        </tr>
    </table>
</form>
    <form id="sixTable">
        <h2>重命名</h2>
        新的职位名:<input type="text" id="editJobName"><br>
        &nbsp;&nbsp;&nbsp;<input type="button" id="editJob" value="确认">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="cancelEditJob" value="取消">
    </form>
    <form id="sevenTable">
        <h2>增加职位</h2>
        所属部门:<select id="dept">
        <c:forEach items="${requestScope.depts}" var="dept">
            <option>${dept.dName}</option>
        </c:forEach>
    </select>
        新增职位名:<input type="text" id="addJobName"><br>
        &nbsp;&nbsp;&nbsp;<input type="button" id="addJobConfirm" value="确认">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="cancelAddJob" value="取消">
    </form>
</body>
</html>
