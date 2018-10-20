<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/17
  Time: 16:00
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
            $("#first").hide()
            $("#add").click(function(){
                $("#first").show()
                $("#second").hide()
            })
            $("#first").submit(function(){
                var context=$("#context").val()
                var ttime=$("#time").val()
                var needTime=$("#needtime").val()
                if(context==""){
                    alert("培训名不能为空")
                    return false
                }
                if(ttime==""){
                    alert("培训时间不能为空")
                    return false
                }
                if(needTime==""){
                    alert("培训时长不能为空")
                    return false
                }
            })
            $("#cancel").click(function(){
                $("#first").hide()
            })
            $("#ssubmit").click(function(){
                var context=$("#scontext").val()
                var ttime=$("#stime").val()
                var needTime=$("#sneedtime").val()
                var dname=$("#sdept").val()
                if(context==""){
                    alert("培训名不能为空")
                }
                if(ttime==""){
                    alert("培训时间不能为空")
                }
                if(needTime==""){
                    alert("培训时长不能为空")
                }
                if(context!=""&&ttime!=""&&needTime!=""&&dname!=""){
                    var ctid=$("#ctid").val()
                   var args={"ctid":ctid,"context":context,"ttime":ttime,"needTime":needTime,"dname":dname}
                     $.post("/man/editComputerTrainTable",args,function(data){
                        alert("修改成功")
                         window.location.href="/man/showTrainTable"
                     })
                }
            })
            $("#scancel").click(function(){
                $("#second").hide()
            })
        })
    </script>
</head>
<body>
    <c:if test="${empty requestScope.computerTrainTables}">
        <h2>暂无培训消息</h2>
    </c:if>
    <c:if test="${!empty requestScope.computerTrainTables}">
        <c:forEach items="${requestScope.computerTrainTables}" var="traintables">
            <a href="/man/showTrainTable?ctid=${traintables.ctid}">${traintables.context}</a>
        </c:forEach>
    </c:if>
    <input type="button" value="新增" id="add">
    <form id="first" action="/man/saveComputerTrainTable" method="post">
        <table>
            <h2>培训--新增</h2>
            <tr>
                <td>培训名称</td>
                <td><input type="text" id="context" name="context"></td>
            </tr>
            <tr>
                <td>开始时间</td>
                <td><input type="date" id="time" name="ttime"></td>
            </tr>
            <tr>
                <td>培训时长</td>
                <td><input type="number" id="needtime" name="needTime"></td>
            </tr>
            <tr>
                <td>培训部门</td>
                <td>
                    <select id="dept" name="dname">
                        <c:forEach items="${requestScope.depts}" var="dept">
                            <option>${dept.dName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="确认" id="submit">
                </td>
                <td>
                    <input type="button" value="取消" id="cancel">
                </td>
            </tr>
        </table>
    </form>
<c:if test="${!empty requestScope.computerTrainTable}">
    <form id="second">
        <input type="hidden" id="ctid" value="${requestScope.computerTrainTable.ctid}">
        <table>
            <h2>培训--修改</h2>
            <tr>
                <td>培训名称</td>
                <td><input type="text" id="scontext" value="${requestScope.computerTrainTable.context}"></td>
            </tr>
            <tr>
                <td>开始时间</td>
                <td><input type="date" id="stime" value="<f:formatDate value="${requestScope.computerTrainTable.ttime}" pattern="yyyy-MM-dd"/>">
                </td>
            </tr>
            <tr>
                <td>培训时长</td>
                <td><input type="number" id="sneedtime" value="${requestScope.computerTrainTable.needTime}"></td>
            </tr>
            <tr>
                <td>培训部门</td>
                <td>
                    <select id="sdept">
                        <c:forEach items="${requestScope.depts}" var="dept">
                            <c:if test="${requestScope.computerTrainTable.dname==dept.dName}">
                                <option selected="selected">${dept.dName}</option>
                            </c:if>
                            <c:if test="${requestScope.computerTrainTable.dname!=dept.dName}">
                                <option>${dept.dName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="确认" id="ssubmit">
                </td>
                <td>
                    <input type="button" value="取消" id="scancel">
                </td>
            </tr>
        </table>
    </form>
</c:if>
</body>
</html>
