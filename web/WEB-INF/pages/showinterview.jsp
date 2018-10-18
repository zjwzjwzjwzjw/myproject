<%--  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/12
  Time: 22:33
  To change this template use File | Settings | File Templates.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script src="js/jquery-1.7.2.js"></script>
    <script>
        $(function(){
            var uid=$("#id").val();
            $(".detail").click(function(){
                var iid=$(this).parent().children()[0].innerHTML;
                var url="/updateInterview"
                var args={"iid":iid}
                $.post(url,args,function(data){
                    window.location.href="/showInterview?iid="+iid+"&uid="+uid
                })
            })
            $(".goInterview").click(function() {
                var itype = $(this).parent().children()[5].innerHTML;
                if (itype == "已过期") {
                    alert("面试已过期")
                }
                if(itype == "预约面试") {
                    alert("已预约面试")
                }
                if(itype == "通知面试"){
                    var flag = confirm("是否确认预约面试")
                    if (flag) {
                        var iid=$(this).parent().children()[0].innerHTML;
                        var url = "/updateInterviewItype"
                        var args = {"iid": iid}
                        $.post(url, args, function (data) {
                            alert("预约成功,请按时去面试,祝您好运")
                            window.location.href="/showInterview?uid="+uid
                        })
                    }
                }
            })
        })
    </script>
</head>
<body>
<input type="hidden" id="id" value="${sessionScope.user.id}">
<input type="hidden" id="name" value="${sessionScope.user.accName}">
<form>
    <c:if test="${empty requestScope.interview}">
        <a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}">返回</a>
    </c:if>
<c:if test="${!empty requestScope.interview}">
    <table border="2px" cellspacing="0">
        <tr>
            <td colspan="6" style="text-align: center">面试信息</td>
        </tr>
        <tr>
            <td>编号</td>
            <td>投递时间</td>
            <td>是否查看</td>
            <td>面试时间</td>
            <td>是否去面试</td>
            <td>是否录取</td>
            <td>查看详情</td>
            <td>返回</td>
        </tr>
        <c:forEach items="${requestScope.interview}" var="interview">
            <tr>
                <td>${interview.iid}</td>
                <td>
                    <f:formatDate value="${interview.time}" pattern="yyyy-MM-dd"/>
                </td>
                <td class="show">
                    <c:if test="${interview.iutype==0}" >未查看</c:if>
                    <c:if test="${interview.iutype==1}" >已查看</c:if>
                </td>
                <td>
                    <f:formatDate value="${interview.iinterviewtime}" pattern="yyyy-MM-dd"/>
                </td>
                <td class="goInterview">按时面试</td>
                <td class="type">${interview.itype}</td>
                <td class="detail">查看详情</td>
                <td><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}">返回</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
    <c:if test="${!empty requestScope.show}">
        <table border="2px" cellspacing="0">
            <tr>
                <td colspan="4" style="text-align: center">简历</td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>${requestScope.show.tname}</td>
                <td>性别</td>
                <td>${requestScope.show.gender}</td>
            </tr>
            <tr>
                <td>年龄</td>
                <td>${requestScope.show.age}</td>
                <td>学历</td>
                <td>${requestScope.show.education}</td>
            </tr>
            <tr>
                <td>联系方式</td>
                <td>${requestScope.show.tel}</td>
                <td>EMAIL</td>
                <td>${requestScope.show.email}</td>
            </tr>
            <tr>
                <td>应聘职位</td>
                <td>${requestScope.show.dept}&nbsp;${requestScope.show.job}
                </td>
                <td>政治面貌</td>
                <td>
                    ${requestScope.show.policitalStatus}
                </td>
            </tr>
            <tr>
                <td>上份工作</td>
                <td>${requestScope.show.beforeJob}</td>
                <td>工作经验</td>
                <td>${requestScope.show.workExperience}</td>
            </tr>
            <tr>
                <td>期望薪资</td>
                <td>${requestScope.show.workExperience}</td>
                <td>兴趣爱好</td>
                <td>${requestScope.show.favorite}</td>
            </tr>
        </table>
    </c:if>
</form>
</body>
</html>
