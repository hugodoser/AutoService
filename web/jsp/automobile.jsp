<%@ page import="java.util.List" %>
<%@ page import="dao.AutomobileDAO" %>
<%@ page import="entities.AutomobileEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Автомобили</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div align="center" style=" width:100%">
    <h3 class="display-4">Список автомобилей</h3>
    <img src="../images/elevator.png">
    <%List<AutomobileEntity> automobiles = AutomobileDAO.getAll();%>

    <table id="tables" class="table table-striped" style="width: 75%">
        <thead>
        <tr align="center">
            <th scope="col">#</th>
            <th scope="col">Номер машины:</th>
            <th scope="col">Бренд:</th>
            <th scope="col">Год выпуска:</th>
            <th scope="col">ФИО владельца:</th>
        </tr>
        </thead>
        <tbody>
        <% for (AutomobileEntity automobile : automobiles) { %>
        <tr align="center">
            <td><input type="radio" name="r"></td>
            <td><%=automobile.getCarNumber()%>
            </td>
            <td><%=automobile.getBrand()%>
            </td>
            <td><%=automobile.getManufactureYear()%>
            </td>
            <td><%=automobile.getOwnerFIO()%>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>

    <form name="add" action="/automobile" method="post">
        <input type="hidden" name="action" value="showtoadd">
        <input type="submit" value="Добавить данные об автомобиле" class="main-form-button">
    </form>

    <form name="update" action="/automobile" method="post">
        <input type="hidden" name="action" value="showtoupdate">
        <input type="hidden" name="car_number" value="">
        <input type="submit" value="Изменить данные об автомобиле" class="main-form-button"
               onclick="document.update.car_number.value = getSelectedId()">
    </form>

    <form name="delete" action="/automobile" method="post">
        <input type="hidden" name="action" value="delete">
        <input required type="hidden" name="car_number" value="">
        <input type="submit" value="Удалить данные об автомобиле" class="main-form-button"
               onclick="document.delete.car_number.value = getSelectedId()">
    </form>

    <button type="button" class="btn btn-dark" onclick="window.location='/'">Назад</button>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script>
    function getNumb() {
        var count;
        var z = document.getElementsByName("r");
        var table = document.getElementById('tables');
        for (var i = 0; i < z.length; i++) {
            if (z[i].checked) {
                count = i;
                break;
            }
        }
        return count;
    }

    function getSelectedId() {
        var count = getNumb();
        if (count == undefined) {
            alert("Выберите строку!")
            return;
        } else {
            var table = document.getElementById('tables');
            var body = table.rows[count + 1].cells[1].innerHTML;
            return body;
        }
    }
</script>
</body>
</html>