<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/1/14
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>999</title>
</head>
<body>
<table border="1" width="100%">
    <c:forEach begin="1" end="9" var="n">
        <tr>
            <c:forEach begin="1" end="${n}" var="m">
                <td>
                        ${m} * ${n} =${m*n};
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
