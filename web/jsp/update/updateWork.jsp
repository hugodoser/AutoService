<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.WorkEntity" %>

<html>
<head>
    <title>Изменить данные о работы</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center" style=" width:100%">

    <h4 class="display-4">Изменить данные о работе</h4>

    <%
        WorkEntity work = (WorkEntity) request.getAttribute("work");
    %>
    <form action="/work" method="post">
        <input type="hidden" name="action" value="update">
        <table id="tables" class="table" style="width: 75%">

            <tr>
                <td>
                    <input type="hidden" name="id_work" value="${work.getIdWork()}">
                </td>
            </tr>

            <tr>
                <td>
                <td><b>Название работы:</b></td>
                <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="100" name="name_work" value="${work.getNameWork()}"
                           placeholder="${work.getNameWork()}"></td>
            </tr>

            <tr>
                <td>
                <td><b>Стоимость работы:</b></td>
                <td><input required type="number" min="0" max="100000" name="cost_work" value="${work.getCostWork()}"
                           placeholder="${work.getCostWork()}"></td>
            </tr>
        </table>

        <input type="submit" value="Обновить" class="main-form-button">
    </form>

    <button type="submit" onclick="document.location='/work'" class="btn btn-dark">Назад</button>
</div>
</body>
</html>