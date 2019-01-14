<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/1/14
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>color</title>
    <script>
        function fun1() {
            document.getElementById("d").src="/Servlet2?a="+new Date().getTime();
        }
    </script>
</head>
<body>
<!-- http://localhost:8080/jsp/Servlet2-->
<!--http://localhost:8080-->
<img src="/Servlet2" id="d" onclick="fun1()">
</body>
</html>
