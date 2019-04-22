<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background: #ffffff">

<div class="container" align="center">
    <h3 class="display-4" style="color: #000000">АИС "Автосервис"</h3>

    <form action="/automobile" method="get">
        <button type="submit" class="btn">Автомобили</button>
    </form>
    <form action="/client" method="get">
        <button type="submit" class="btn">Заказчики</button>
    </form>
    <form action="/contract" method="get">
        <button type="submit" class="btn btn-primary">Договоры</button>
    </form>
    <form action="/work" method="get">
        <button type="submit" class="btn">Список работ</button>
    </form>


</div>
</body>
<footer>
    <div align="center">
    <img src="images/logo.png">
    </div>
</footer>
</html>