
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Просмотр задачи</h1> 
        <table>
            <tr> 
                <td><label>Название:</label></td> <td>
                <c:out value="${task.name}" /></td> 
            </tr> 
            <tr> 
                <td>
                    <label>Описание:</label>
                </td> 
                <td>
                    <c:out value="${task.description}" />
                </td> 
            </tr> 
            <tr> 
                <td>
                    <label>Срок выполнения:</label>
                </td> 
                <td>
                    <c:out value="${task.dueDate}" />
                </td> 
            </tr> 
            <tfoot> 
                <th colspan="2" style="padding: 10px 0px;"> 
                    <c:url value="/tasks/${task.id}" var="taskDeleteUrl" /> 
                    <form id="deleteForm" action="${taskDeleteUrl}" method="GET">
                        <input type="hidden" name="delete" /> 
                        <input type="submit" value="Удалить" /> 
                    </form>
                </th> 
            </tfoot> 
        </table>
</body>
</html>
