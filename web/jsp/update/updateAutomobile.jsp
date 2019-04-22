<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="entities.AutomobileEntity" %>
<html>
<head>
    <title>Изменить запись об автомобиле</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center" style=" width:100%">

    <h3 class="display-4">Изменение данных об автомобиле</h3>

    <%
        AutomobileEntity automobile = (AutomobileEntity) request.getAttribute("automobile");
    %>

    <form action="/automobile" method="post">
        <input type="hidden" name="action" value="update">
        <table id="tables" class="table" style="width: 75%">

            <tr>
                <td>
                <td><b>Номер машины:</b></td>
                <td><input type="text" pattern="^[А-ЯA-Z0-9\.]+$" maxlength="20" name="car_number" value="${automobile.getCarNumber()}"
                           placeholder="${automobile.getCarNumber()}" readonly></td>
            </tr>

            <tr>
                <td>
                <td><b>Бренд:</b></td>
                <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="30" name="brand" value="${automobile.getBrand()}"
                           placeholder="${automobile.getBrand()}"></td>
            </tr>

            <tr>
                <td>
                <td><b>Год выпуска:</b></td>
                <td><input required type="text" min="1950" max="2019" minlength="4" maxlength="4" name="manufacture_year" value="${automobile.getManufactureYear()}"
                           placeholder="${automobile.getManufactureYear()}"></td>
            </tr>

            <tr>
                <td>
                <td><b>ФИО владельца:</b></td>
                <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё]+$" maxlength="255" name="owner_fio" value="${automobile.getOwnerFIO()}"
                           placeholder="${automobile.getOwnerFIO()}"></td>
            </tr>
        </table>

        <input type="submit" value="Обновить" class="main-form-button">
    </form>

    <button type="submit" onclick="document.location='/automobile'" class="btn btn-dark">Назад</button>
</div>
</body>
</html>