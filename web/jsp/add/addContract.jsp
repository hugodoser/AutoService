<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static utils.Constants.*" %>
<%@ page import="entities.WorkEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.ContractEntity" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Добавить договор</title>
    <link rel="shortcut icon" href="/images/icon1.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center">
    <div class="row">
        <div class="col"></div>

        <div class="col-6">

            <h3 class="display-4">Добавление договора</h3>

            <%
                ContractEntity c = (ContractEntity) request.getAttribute("contract");
                ArrayList<WorkEntity> works = (ArrayList<WorkEntity>) request.getAttribute("works");
            %>

            <form action="/contract" method="post">
                <input type="hidden" name="action" value="add">
                <table id="tables" class="table">
                    <tr>
                    <td>
                    <td><b>Дата заключения договора:</b></td>
                    <td><input required type="text" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" placeholder="YYYY-MM-DD" name=<%=TABLE_CONTRACT_DATA_CONTRACT%>> </td>
                    </tr>

                    <tr>
                    <td>
                    <td><b>Дата выполнения договора:</b></td>
                    <td><input required type="text" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" placeholder="YYYY-MM-DD" name=<%=TABLE_CONTRACT_DATA_EXECUTION_CONTRACT%>></td>
                    </tr>

                    <tr>
                        <td>
                        <td><b>Номер машины:</b></td>
                        <td><select class="custom-select" required name="car_number">
                            <c:forEach var="automobile" items="${automobiles}">
                                <option value="${automobile.getCarNumber()}">
                                        ${automobile.getCarNumber()}
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
                                    <option value="${client.getIdClient()}">
                                            ${client.getClientFIO()}
                                    </option>
                                </c:forEach>
                    </select>
                    </td>
                    </tr>

                    <tr>
                        <td>
                        <td><b>Список работ:</b></td>
                        <td>
                            <select required id="works" name="works" class="custom-select" multiple>
                                <%
                                    for(WorkEntity workEntity:works)
                                    {%>
                                    <option value="<%=workEntity.getIdWork()%>"><%=workEntity.getNameWork() + " " + workEntity.getCostWork()%></option>
                                    <%}%>
                            </select>
                        </td>
                    </tr>

                </table>
                <input type="submit" value="Добавить" class="main-form-button">
            </form>
            <input type="submit" value="Назад" onclick="document.location='/contract'" class="main-form-button-back">

        </div>

        <div class="col"></div>
    </div>
</div>
</body>
</html>