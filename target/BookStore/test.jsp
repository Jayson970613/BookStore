<%--
  Created by IntelliJ IDEA.
  User: Cucumber
  Date: 2019/9/12
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Hello</p>
<p>${book.id}</p>
<p>${book.title}</p>
<p>${book.price}</p>
<p>${book.publishDate }</p>
<%--<fmt:formatDate pattern="yyyy-MM-dd" value="${book.publishDate }"/>--%>
</body>
</html>
