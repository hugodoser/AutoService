<%@ page import="entities.WorkEntity" %>
<%@ page import="dao.WorkDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Работы</title>
        <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>

        <div align="center" style=" width:100%">
            <h3 class="display-4">Список работ</h3>
            <img src="../images/spray-paint.png">

            <%List<WorkEntity> works = WorkDAO.getAll();%>

            <table id="tables" class="table table-striped" style="width: 75%">
                <thead>
                    <tr align="center">
                        <th scope="col">#</th>
                        <th scope="col">id_работы</th>
                        <th scope="col">Название работы:</th>
                        <th scope="col">Стоимость работы:</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (WorkEntity work : works) { %>
                    <tr align="center">
                        <td><input type="radio" name="r"></td>
                        <td><%=work.getIdWork()%></td>
                        <td><%=work.getNameWork()%></td>
                        <td><%=work.getCostWork()%></td>
                    </tr>
                </tbody>
                <%}%>
            </table>

            <form name="add" action="/work" method="post">
                <input type="hidden" name="action" value="showtoadd">
                <input type="submit" value="Добавить данные о работе" class="main-form-button">
            </form>

            <form name="update" action="/work" method="post">
                <input type="hidden" name="action" value="showtoupdate">
                <input type="hidden" name="id_work" value="">
                <input type="submit" value="Изменить данные о работе" class="main-form-button"
                       onclick="document.update.id_work.value = getSelectedId()">
            </form>

            <form name="delete" action="/work" method="post">
                <input type="hidden" name="action" value="delete">
                <input required type="hidden" name="id_work" value="">
                <input type="submit" value="Удалить работу" class="main-form-button"
                       onclick="document.delete.id_work.value = getSelectedId()">
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
                    var body = encodeURIComponent(table.rows[count + 1].cells[1].innerHTML);
                    return body;
                }
            }
        </script>
    </body>
</html>