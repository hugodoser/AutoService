<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static utils.Constants.*" %>
<%@ page import="entities.WorkEntity" %>
<%@ page import="entities.ContractEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.ClientEntity" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Изменить данные договора</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center">
    <h4 class="display-4">Изменение данных договора</h4>

    <%
        ContractEntity contract = (ContractEntity) request.getAttribute("contract");
        //Map<Integer,String> dictionary = (Map<Integer,String>) request.getAttribute("idPOList");
        ArrayList<WorkEntity> works = (ArrayList<WorkEntity>) request.getAttribute("works");
        List<ClientEntity> clients = ClientDAO.getAll();
    %>
    <form action="/contract" method="post">
        <input type="hidden" name="action" value="update">
        <table id="tables" class="table">

            <tr>
                <td>
                    <input type="hidden" name="id_contract" value="${contract.getIdContract()}">
                </td>
            </tr>

            <tr>
                <td>
                <td><b>Дата заключения договора:</b></td>
                <td><input required type="text"
                           pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                           name="data_contract" value="${contract.getDataContract()}"
                           placeholder="${contract.getDataContract()}"></td>
            </tr>

            <tr>
                <td>
                <td><b>Дата выполнения договора:</b></td>
                <td><input required type="text"
                           pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
                           name="data_execution_contract" value="${contract.getDataExecutionContract()}"
                           placeholder="${contract.getDataExecutionContract()}"></td>
            </tr>

            <tr>
                <td>
                <td><b>Номер машины:</b></td>
                <td><select class="custom-select" required name="car_number">
                    <c:forEach var="automobile" items="${automobiles}">
                        <option value="${automobile.getCarNumber()}"
                            ${automobile.getCarNumber()== car_number ? 'selected' : '' }>${automobile.getCarNumber()}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>

            <tr>
                <td>
                <td><b>Заказчик:</b></td>
                <td><select class="custom-select" required name="id_client">
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.getIdClient()}"
                            ${client.getClientFIO() == fio_client ? 'selected' : '' }>${client.getClientFIO()}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>

            <tr>
                <td>
                <td><b>Список работ:</b></td>
                <td>
                    <select id="works" name="works" class="custom-select" multiple required>
                        <%
                            for (WorkEntity workEntity : works) {
                                if (!contract.getWorkEntities().contains(workEntity)) {
                        %>
                        <option value="<%=workEntity.getIdWork()%>"><%=workEntity.getNameWork() + " : " + workEntity.getCostWork()%>
                        </option>
                        <%}%>
                        <%if (contract.getWorkEntities().contains(workEntity)) {%>
                        <option selected
                                value="<%=workEntity.getIdWork()%>"><%=workEntity.getNameWork() + " : " + workEntity.getCostWork()%>
                        </option>
                        <%}%>
                        <%}%>
                    </select>
                </td>
            </tr>

        </table>

        <input type="submit" value="Обновить" class="main-form-button">
    </form>

    <button type="submit" onclick="document.location='/contract'" class="btn btn-dark">Назад</button>

</div>
</body>
</html>