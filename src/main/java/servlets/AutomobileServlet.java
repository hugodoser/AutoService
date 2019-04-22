package servlets;

import dao.AutomobileDAO;

import dao.WorkDAO;
import entities.AutomobileEntity;
import entities.WorkEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/automobile")
public class AutomobileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("jsp/automobile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if ("showtoupdate".equals(action)) {
            try {
                String car_number = (request.getParameter("car_number"));

                AutomobileEntity automobileEntity = AutomobileDAO.getById(car_number.trim());
                if (automobileEntity == null)
                    doGet(request, response);
                else {
                    request.setAttribute("automobile", automobileEntity);
                    request.getRequestDispatcher("jsp/update/updateAutomobile.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("update".equals(action)) {
            AutomobileEntity automobileEntity = new AutomobileEntity();
            String s = request.getParameter("car_number").trim();
            automobileEntity.setCarNumber(s);
            automobileEntity.setBrand(request.getParameter("brand"));
            automobileEntity.setManufactureYear(Integer.parseInt(request.getParameter("manufacture_year")));
            automobileEntity.setOwnerFIO(request.getParameter("owner_fio"));

            AutomobileDAO.update(automobileEntity);

            doGet(request, response);
        } else if ("showtoadd".equals(action)) {
            try {
                List<AutomobileEntity> automobiles = AutomobileDAO.getAll();
                request.setAttribute("automobiles", automobiles);

                request.getRequestDispatcher("jsp/add/addAutomobile.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("add".equals(action)) {
            AutomobileEntity automobileEntity = new AutomobileEntity();
            automobileEntity.setCarNumber(request.getParameter("car_number").trim());
            automobileEntity.setBrand(request.getParameter("brand"));
            automobileEntity.setManufactureYear(Integer.parseInt(request.getParameter("manufacture_year")));
            automobileEntity.setOwnerFIO(request.getParameter("owner_fio"));

            AutomobileDAO.insert(automobileEntity);

            doGet(request, response);
        } else if ("delete".equals(action)) {
            try {
                AutomobileEntity automobileEntity = new AutomobileEntity(request.getParameter("car_number").trim());

                AutomobileDAO.delete(automobileEntity);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        }
    }
}