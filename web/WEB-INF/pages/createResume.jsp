<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/11
  Time: 16:22
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
            var id=$("#id").val();
            $("#firstSelect").blur(function(){
                var dept = $(this).val();//得到第一个下拉列表的值
                $(".job").empty()
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
                        $(html).appendTo(".job")
                    }
                })
            })
            $("#sendResume").click(function(){
                var args={"id":id}
                var url="/sendResume"
                var result=""
                $.post(url,args,function(data){
                    if(data==""){
                        alert("您还没有写简历")
                    }
                    if(data=="1"){
                        alert("简历发布成功")
                    }
                    if(data=="2"){
                        alert("简历已发布")
                    }
                })
                return false
            })
            $("form").submit(function(){
                var name=$("#tname").val()
                var age=$("#age").val()
                var tel=$("#tel").val()
                var dept=$("#firstSelect").val()
                var job=$("#secondSelect").val()
                var education=$("#education").val()
                if(name==""){
                    alert("姓名不能为空")
                    return false;
                }
                if(age==""){
                    alert("年龄不能为空")
                    return false;
                }
                if(tel==""){
                    alert("联系方式不能为空")
                    return false;
                }
                if(dept==""){
                    alert("应聘部门不能为空")
                    return false;
                }
                if(job==""){
                    alert("应聘职位不能为空")
                    return false;
                }
                if(education==""){
                    alert("学历不能为空")
                    return false;
                }

            })
        })
    </script>
</head>
<body>
<form action="/saveResume">
    <input type="hidden" name="uid" id="id" value="${sessionScope.user.id}">
    <input type="hidden" id="name" value="${sessionScope.user.accName}">
    <table border="2px" cellspacing="0">
        <tr>
            <td colspan="4" style="text-align: center">简历</td>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td><input type="text" name="tname" id="tname" value="${requestScope.resume.tname}"></td>
            <td>性别</td>
            <td>
                <c:if test="${requestScope.resume.gender=='男'}">
                    <input type="radio" name="gender" checked="checked" value="男">男
                    <input type="radio" name="gender" value="女">女
                </c:if>
                <c:if test="${requestScope.resume.gender=='女'}">
                    <input type="radio" name="gender" value="男">男
                    <input type="radio" name="gender" checked="checked" value="女">女
                </c:if>
                <c:if test="${empty requestScope.resume}">
                    <input type="radio" name="gender" value="男">男
                    <input type="radio" name="gender" checked="checked" value="女">女
                </c:if>
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="number" name="age" id="age" value="${requestScope.resume.age}"></td>
            <td>学历</td>
            <td>
                <select name="education" id="education">
                    <option>中专</option>
                    <option>大专</option>
                    <option>本科</option>
                    <option>博士</option>
                    <option>研究生</option>
                    <option>硕士</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>联系方式</td>
            <td><input type="number" name="tel" id="tel" value="${requestScope.resume.tel}"></td>
            <td>EMAIL</td>
            <td><input type="text" name="email" value="${requestScope.resume.email}"></td>
        </tr>
        <tr>
            <td>应聘职位</td>
            <td><select name="dept" id="firstSelect">
                    <c:forEach items="${requestScope.depts}" var="dept">
                        <c:if test="${requestScope.resume.dept==dept.dName}">
                            <option selected="selected">${requestScope.resume.dept}</option>
                        </c:if>
                        <c:if test="${requestScope.resume.dept!=dept.dName}">
                            <option>${dept.dName}</option>
                        </c:if>
                    </c:forEach>
            </select>
                <select  id="secondSelect" name="job" class="job">
                    <c:forEach items="${requestScope.jobs}" var="job">
                        <c:if test="${requestScope.resume.job==job.jName}">
                            <option >${requestScope.resume.job}</option>
                        </c:if>
                    </c:forEach>
                    <c:if test="${empty requestScope.resume}">
                        <c:forEach items="${requestScope.jobs}" var="job">
                            <c:if test="${job.dId==1}">
                                <option>${job.jName}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>政治面貌</td>
            <td>
                <select name="policitalStatus">
                    <option>普通群众</option>
                    <option>共青团员</option>
                    <option>党员</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>上份工作</td>
            <td><input type="text" name="beforeJob" value="${requestScope.resume.beforeJob}"></td>
            <td>工作经验</td>
            <td><input type="text" name="workExperience" value="${requestScope.resume.workExperience}"></td>
        </tr>
        <tr>
            <td>期望薪资</td>
            <td>
                <select name="salary">
                    <option>3000-5000</option>
                    <option>5000-7000</option>
                    <option>7000-8000</option>
                </select>
            <td>兴趣爱好</td>
            <td><input type="text" name="favorite" value="${requestScope.resume.favorite}"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="保存"></td>
            <td><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a></td>
            <td colspan="2" style="text-align: center">
                <button><a href="#" style="text-underline: none" id="sendResume">发布简历</a></button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
