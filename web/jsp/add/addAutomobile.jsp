<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Добавить автомобиль</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center" style=" width:75%">

    <h3 class="display-4">Добавление записи</h3>

    <form action="/automobile" method="post">
        <input type="hidden" name="action" value="add">
        <table class="table">
            <tr>
            <td>
            <td style="text-align: right"><b>Номер машины:</b></td>
            <td><input required type="text" pattern="^[А-ЯA-Z0-9\.]+$" maxlength="20" name="car_number"></td>
            </tr>

            <tr>
            <td>
            <td style="text-align: right"><b>Марка:</b></td>
            <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="30" name="brand"></td>
            </tr>

            <tr>
            <td>
            <td style="text-align: right"><b>Год выпуска:</b></td>
            <td><input required type="number" min="1950" max="2019" minlength="4" maxlength="4" name="manufacture_year"></td>
            </tr>

            <tr>
            <td>
            <td style="text-align: right"а><b>ФИО владельца:</b></td>
            <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё]+$" maxlength="255" name="owner_fio"></td>
            </tr>
        </table>
        <input type="submit" value="Добавить" class="main-form-button">
    </form>
    <input type="submit" value="Назад" onclick="document.location='/automobile'" class="main-form-button-back">
</div>
</body>
</html>