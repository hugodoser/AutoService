<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Добавить заказчика</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center" style=" width:75%">

    <h3 class="display-4">Добавление заказчика</h3>

    <form action="/client" method="post">
        <input type="hidden" name="action" value="add">
        <table id="tables" class="table">
            <tr>
                <td>
                <td><b>ФИО заказчика:</b></td>
                <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="255" name="fio_client"></td>
            </tr>

        </table>
        <input type="submit" value="Добавить" class="main-form-button">
    </form>
    <input type="submit" value="Назад" onclick="document.location='/client'" class="main-form-button-back">
</div>
</body>
</html>