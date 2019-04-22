<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.WorkEntity" %>
<%@ page import="entities.ContractEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.ClientEntity" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Изменить запись о заказчике</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center" style=" width:100%">

    <h4 class="display-4">Изменение данных заказчика</h4>

        <%
            ClientEntity client = (ClientEntity) request.getAttribute("client");
        %>
        <form action="/client" method="post">
            <input type="hidden" name="action" value="update">
            <table id="tables" class="table" style="width: 75%">

                <tr>
                    <td>
                        <input type="text" name="id_client" readonly value="${client.getIdClient()}">
                    </td>
                </tr>

                <tr>
                    <td>
                    <td><b>ФИО заказчика:</b></td>
                    <td><input required type="text" pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="255" name="fio_client" value="${client.getClientFIO()}"
                               placeholder="${client.getClientFIO()}"></td>
                </tr>
            </table>

            <input type="submit" value="Обновить" class="main-form-button">
        </form>

    <button type="submit" onclick="document.location='/client'" class="btn btn-dark">Назад</button>
</div>
</body>
</html>