package servlets;

import dao.*;
import entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.Constants.*;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("jsp/client.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("showtoupdate".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_client"));

                ClientEntity clientEntity = ClientDAO.getById(id);

                request.setAttribute("client", clientEntity);

                request.getRequestDispatcher("jsp/update/updateClient.jsp").forward(request, response);
            }catch (NumberFormatException | SQLException e) {
                doGet(request, response);
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id_client"));
            ClientEntity clientEntity = null;
            try {
                clientEntity = ClientDAO.getById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            clientEntity.setClientFIO(request.getParameter("fio_client"));
            try {
                ClientDAO.update(clientEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("showtoadd".equals(action)) {
            try {
                List<ClientEntity> clients = ClientDAO.getAll();
                request.setAttribute("clients", clients);

                request.getRequestDispatcher("jsp/add/addClient.jsp").forward(request, response);
            }
            catch (SQLException|NumberFormatException  e) {
                doGet(request,response);
            }
        } else if ("add".equals(action)) {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setClientFIO(request.getParameter("fio_client"));
            try {
                ClientDAO.insert(clientEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt((request.getParameter("id_client")));

                ClientEntity clientEntity = new ClientEntity(id);
                try {
                    ClientDAO.delete(clientEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        }
    }
}