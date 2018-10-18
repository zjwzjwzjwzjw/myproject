<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/11
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/">
    <script src="js/jquery-1.7.2.js"></script>
    <script>
        $(function() {
            var id = $("#id").val()
            var utype=$("#utype").val()
            var args = {"uid": id}
            var url = "/checkInterview"
            if(utype==2) {
                $.post(url, args, function (data) {
                    if (data == "yes") {
                        var flag = confirm("您可以去面试了,是否去查看面试信息")
                        if (flag) {
                            window.location.href = "/showInterview?uid="+id;
                        }
                    }
                })
            }
            $("#message").click(function(){
                $.post(url,args,function(data){
                    if(data==""){
                        alert("您还没有写简历")
                    }else if(data=="1"){
                        alert("您的简历还未发布")
                    } else{
                        window.location.href="/showInterview?uid="+id;
                    }
                })
                return false;
            })
            if(utype!=2) {//员工操作
                $.post("/emp/searchcheckwork", {"uid": id}, function (data) {
                    if (data != "") {
                        alert("您有" + data + "次缺勤记录");
                    }
                })
                $.post("/emp/searchTrainTable", {"uid": id}, function (data) {
                    if (data != "") {
                        alert("您有" + data + "次培训记录还未查看");
                    }
                })
                var args1 = {"uid": id}
                var url1 = "/emp/toworking";
                /*检测今日是否打过上班卡*/
                $.post(url1, args1, function (data) {
                    if (data == "4") {
                        $("#toworking").attr("disabled", true);
                    }
                })
                var url2 = "/emp/endwork"//检测今日是否打过下班卡
                $.post(url2, args1, function (data) {
                    if (data == "5") {
                        $("#endwork").attr("disabled", true);
                    }
                })
            }
            if (utype==0) {//是管理员是才查看
                $.post("/man/findComputerResumes", {"uid": id}, function (data) {
                    if (data == "yes") {
                        alert("有未查看的简历,请及时查看")
                    }
                })
                $.post("/man/checkInterMessage", {"uid": id}, function (data) {
                    if (data == "yes") {
                        alert("今日有面试者")
                    }
                })
            }
            $("#exit").click(function(){
                var flag=confirm("确认退出吗?")
                if(!flag){
                    return false;
                }
            })
            $("#pub").click(function(){
                var args={"id":id};
                var url="/emp/searchPublishment"
                $.post(url,args,function(data){
                    if(data==""){
                        alert("您还没有奖惩记录")
                    }else{
                        window.location.href="/emp/findPublishment?id="+id;
                    }
                })
                return false;
            })
            $("#toworking").click(function(){
                var diff="aaa"
                var args={"uid":id,"diff":diff}
                var url="/emp/toworking"
                $.post(url,args,function(data){
                    if(data=="0"){
                        alert("今日休息,无需打卡")
                    }
                    if(data=="1"){
                        alert("旷工,扣50元")
                    }
                    if(data=="2"){
                        alert("迟到,扣20元")
                    }
                    if(data=="3"){
                        alert("打卡成功")
                    }
                })
                $("#toworking").attr("disabled",true);
            })
            $("#endwork").click(function(){
                var diff="aaa"
                var args={"uid":id,"diff":diff}
                var url="/emp/endwork"
                $.post(url,args,function(data){
                    if(data=="0"){
                        alert("今日休息,无需打卡")
                    }
                    if(data=="1"){
                        alert("早退,扣20元,并且缺少上班卡")
                    }
                    if(data=="2"){
                        alert("正常下班,缺少上班卡")
                    }
                    if(data=="3"){
                        alert("早退,扣20元")
                    }
                    if(data=="4"){
                        alert("正常下班")
                    }
                })
                $("#endwork").attr("disabled",true);
            })
            $("#salary").click(function(){
                var args={"uid":id}
                var url="/emp/findAllSalary"
                $.post(url,args,function(data){
                    if(data==""){
                        alert("您还没有工资信息")
                    }else{
                        window.location.href="/emp/showSalary?uid="+id;
                    }
                })
                return false;
            })
            $("#train").click(function(){
                var args={"uid":id}
                var url="/emp/searchTrainTable"
                $.post(url,args,function(data){
                    if(data==""){
                        alert("您还没有培训信息")
                    }else{
                        window.location.href="/emp/showTrainTable?uid="+id;
                    }
                })
                return false;
            })
        })
    </script>
</head>
<body>
<input type="hidden" id="id" value="${sessionScope.user.id}">
<input type="hidden" id="utype" value="${sessionScope.user.utype}">
<c:if test="${sessionScope.user.utype==2}">
    <a id="message" href="#">消息提醒</a>
    <a href="/showResume?id=${sessionScope.user.id}">查看简历</a>
    <a href="/editAccount">修改账户信息</a>
    <a href="/main.jsp" id="exit">退出</a>
</c:if>
<c:if test="${sessionScope.user.utype==1}">
    <a href="/emp/showEmp?id=${sessionScope.user.id}">基本信息</a>
    <a href="">我的考勤</a>
    <a  id="pub" href="#">我的奖惩</a>
    <a href="/emp/showAllDept">部门职位</a>
    <a href="#" id="salary">我的薪资</a>
    <a href="/editAccount">修改密码</a>
    <a href="#" id="train">我的培训</a>
    <a href="/main.jsp" id="exit">退出</a>
    <button id="toworking">上班打卡</button>
    <button id="endwork">下班打卡</button>
</c:if>
<c:if test="${sessionScope.user.utype==0}">
    <a href="/man/showComputerResumes">应聘管理</a>
    <a href="/man/showAllDeptAndJob">部门职位</a>
    <a href="/man/showTrainTable">培训管理</a>
    <a href="/man/showEmploee">员工管理</a>
    <a href="/man/showpublishment">奖惩管理</a>
    <a href="">薪资管理</a>
    <a href="/main.jsp" id="exit">退出</a>
</c:if>
</body>
</html>
