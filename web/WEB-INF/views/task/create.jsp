
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Добавление задачи</h1> 
        <c:url value="/tasks" var="addTaskUrl" /> 
        <form id="addTaskForm" action="${addTaskUrl}" method="POST">
            <table> 
                <tr> 
                    <td>
                        <label>Название:</label>
                    </td> 
                    <td>
                        <input name="name" type="text" value="${task.name}" /> 
                    </td> 
                </tr> 
                <tr> 
                    <td>
                        <label>Описание:</label>
                    </td> 
                    <td>
                        <input name="description" type="text" value="${task.description}" /> 
                    </td> 
                </tr>
                <tr> 
                    <td>
                        <label>Срок выполнения:</label>
                    </td>
                    <td>
                        <input name="due_date" type="text" value="${task.dueDate}" />
                    </td> 
                </tr>
                <tfoot>
                    <th colspan="2" style="padding: 10px 0px;"> 
                        <c:url value="/tasks" var="taskListUrl" /> 
                        <input type="submit" value="Добавить" /> 
                        <span>
                            <a href="${taskListUrl}">Отмена</a>
                        </span> 
                    </th>
                </tfoot>
            </table> 
        </form>
</body>
</html>
