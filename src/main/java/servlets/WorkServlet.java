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

@WebServlet("/work")
public class WorkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("jsp/work.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if ("showtoupdate".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_work"));

                WorkEntity workEntity = WorkDAO.getById(id);

                request.setAttribute("work", workEntity);
                request.getRequestDispatcher("jsp/update/updateWork.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id_work"));
            WorkEntity workEntity = null;
            try {
                workEntity = WorkDAO.getById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            workEntity.setNameWork(request.getParameter("name_work"));
            workEntity.setCostWork(Integer.parseInt(request.getParameter("cost_work")));
            try {
                WorkDAO.update(workEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            doGet(request, response);
        } else if ("showtoadd".equals(action)) {
            try {
                List<WorkEntity> works = WorkDAO.getAll();
                request.setAttribute("works", works);

                request.getRequestDispatcher("jsp/add/addWork.jsp").forward(request, response);
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("add".equals(action)) {
            WorkEntity workEntity = new WorkEntity();
            workEntity.setNameWork(request.getParameter("name_work"));
            workEntity.setCostWork(Integer.parseInt((request.getParameter("cost_work"))));

            try {
                WorkDAO.insert(workEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            doGet(request, response);
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt((request.getParameter("id_work")));

                WorkEntity workEntity = new WorkEntity(id);
                try {
                    WorkDAO.delete(workEntity);
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