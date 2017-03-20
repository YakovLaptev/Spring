
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Список задач</h1>
        <table> 
            <thead>
            <th>#</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Срок выполнения</th>
            <th>Действия</th> 
        </thead>
        <c:choose> 
            <c:when test="${fn:length(taskList) gt 0}"> 
                <c:forEach var="task" items="${taskList}" varStatus="status"> 
                    <c:url value="/tasks/${task.id}" var="showTaskUrl" /> 
                    <c:url value="/tasks/${task.id}?delete" var="taskDeleteUrl" /> 
                    <tr> 
                        <td><c:out value="${status.count}" /></td> 
                        <td><c:out value="${task.name}" /></td> 
                        <td><c:out value="${task.description}" /></td> 
                        <td><c:out value="${task.dueDate}" /></td>  
                        <td><a href="${showTaskUrl}">Просмотр</a> |
                            <a href="${taskDeleteUrl}">Удалить</a> 
                        </td> 
                    </tr> 
                </c:forEach> 
            </c:when> 
            <c:otherwise> 
                <tr> 
                    <td colspan="5">Список пуст</td> 
                </tr>
            </c:otherwise> 
        </c:choose> 
        </table>
        <a href="<c:url value='/tasks?addForm' />">Добавить</a>
    </body>
</html>
