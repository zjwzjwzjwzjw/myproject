<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/11
  Time: 13:44
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
            $(":text[name='accName']").blur(function(){
                var url="/checkUserName"
                var name=$(this).val()
                var args={"name":name}
                $.post(url,args,function(data){
                    if(data!=""){
                        $("#checkName").text("用户名已存在")
                    }else{
                        $("#checkName").text("")
                    }
                })
            })
            $("form").submit(function(){
                var name=$(":text[name='accName']").val();
                var passwordA=$(":text[name='passwordA']").val();
                var passwordB=$(":text[name='passwordB']").val();
                if(name==""){
                    $("#checkName").text("账户名不能为空");
                    return false;
                }
                if(passwordA==""){
                    $("#alarm").text("密码不能为空");
                    return false;
                }
                if(passwordA!=passwordB){
                    $("#alarm").text("两次密码不一致");
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<h1>注册</h1>
<form action="/addUser">
    账户名<input type="text" name="accName">
    <span id="checkName" style="color: red"></span><br>
    密码:<input type="text" name="passwordA"><br>
    密码:<input type="text" name="passwordB">
    <span style="color: red" id="alarm"></span>
    <input type="submit" value="注册"><br>
</form>
</body>
</html>
