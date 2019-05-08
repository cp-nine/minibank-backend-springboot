<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Test Integrating API</h1>

<c:forEach items="${data}" var="cs">
    <p>${cs.cif}</p>
    <p>${cs.fname}</p>
</c:forEach>

</body>
</html>
