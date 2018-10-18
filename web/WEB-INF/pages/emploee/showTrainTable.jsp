<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/15
  Time: 11:24
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
            $(".attend").click(function(){
                 var trtype=$(this).parent().children()[3]
                $trtype=$(trtype)
                $trtype.text("已查看")
                var tbtype=$(this).parent().children()[4]
                $tbtype=$(tbtype)
                var tbtype=$tbtype.text()
                var tid=$(this).parent().children()[7]
                $tid=$(tid)
                var tid=$tid.val()
                if(tbtype=="已结束"){
                    alert("培训已结束")
                }else{
                    var flag=confirm("确认报名参加吗?")
                    if(flag){
                        var id=$("#uid").val()
                        var args={"uid":id,"tid":tid}
                        var url="/emp/updateTrainTable"
                        $.post(url,args,function(date){
                            alert("已报名,请按时参加")
                        })
                    }
                }
            })
        })
    </script>
</head>
<body>
<input type="hidden" name="uid" value="${sessionScope.user.id}">
    <form>
        <table>
            <tr>
                <td>培训时间</td>
                <td>培训内容</td>
                <td>培训时长</td>
                <td>是否查看过</td>
                <td>是否已结束</td>
                <td>是否参加</td>
                <td>受培训的部门</td>
            </tr>
            <c:forEach items="${requestScope.train}" var="train">
                <tr>
                    <td>
                        <f:formatDate value="${train.ttime}" pattern="yyyy-MM-dd"></f:formatDate>
                    </td>
                    <td>${train.context}</td>
                    <td>${train.needTime}</td>
                    <td>${train.trype}</td>
                    <td>${train.tbtype}</td>
                    <td class="attend">报名参加</td>
                    <td>${train.dname}</td>
                    <input type="hidden" value="${train.tid}">
                </tr>
                <tr>
                    <td colspan="7"><a href="/login?accName=${sessionScope.user.accName}&password=${sessionScope.user.password}" style="text-underline: none">返回</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
