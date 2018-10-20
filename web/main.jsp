<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/10/11
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <base href="http://localhost:8080/">
    <style>
      .a{
        background-image: url("image/1.png");
        background-size:100%;
        background-repeat: no-repeat;
      }
    </style>
    <script src="js/jquery-1.7.2.js"></script>
    <script>
      $(function(){
        $(":text[name='accName']").blur(function(){
          var url="/checkUserName"
          var name=$(this).val()
          var args={"name":name}
          $.post(url,args,function(data){
            if(data==""){
              $("#checkName").text("用户名不存在")
            }else{
              $("#checkName").text("")
            }
          })
        })
        $("form").submit(function(){
          var name=$(":text[name='accName']").val();
          var password=$(":text[name='password']").val();
          if(name==""){
            $("#checkName").text("用户名不能为空");
          }
          if(password==""){
            $("#alarm").text("密码不能为空");
          }
          var args={"name":name,"password":password}
          var url="/checkUser"
          $.post(url,args,function(data){
            if(data=="") {
              $("#alarm").text("用户名或密码错误");
            }else{
              $("#alarm").text("");
              window.location.href="/login?accName="+name+"&password="+password
            }
          })
          return false;
        })
      })
    </script>
  </head>
  <body class="a">
    <form style="text-align: center;margin-top: 20%">
      <h1>登录</h1>
      账户名<input type="text" name="accName"><br>
      <span id="checkName" style="color: red"></span><br>
      密&nbsp;&nbsp;码:<input type="text" name="password"><br>
      <span style="color: red" id="alarm"></span><br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="登录">
      &nbsp;&nbsp;<input type="reset" value="取消"><br>
      快来加入我们吧!
      <a href="/add">注册</a>
    </form>
  </body>
</html>
