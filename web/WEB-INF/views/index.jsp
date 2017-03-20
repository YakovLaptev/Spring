
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Меню</h1>
         
        <a href="<c:url value='/tasks/' />">Список</a>
        <a href="<c:url value='/tasks?addForm' />">Добавить</a>
    </body>
</html>
