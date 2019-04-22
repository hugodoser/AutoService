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
import java.util.*;

import static utils.Constants.*;

@WebServlet(name = "ContractServlet", urlPatterns = {"/contract"})
public class ContractServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("jsp/contract.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("showtoupdate".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_contract"));
                ContractEntity contractEntity = ContractDAO.getById(id);

                ClientEntity clientEntity = null;
                try {
                    clientEntity = ClientDAO.getById(contractEntity.getIdClient());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                List<ClientEntity> clients = null;
                try {
                    clients = ClientDAO.getAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                List<WorkEntity> workEntities = null;
                try {
                    workEntities = WorkDAO.getAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("works", workEntities);

                List<AutomobileEntity> automobiles = null;

                automobiles = AutomobileDAO.getAll();

                request.setAttribute("automobiles", automobiles);

                request.setAttribute("car_number", contractEntity.getCarNumber());
                request.setAttribute("contract", contractEntity);
                request.setAttribute("id_client", clientEntity.getIdClient());
                request.setAttribute("fio_client", clientEntity.getClientFIO());
                request.setAttribute("clients", clients);

                request.getRequestDispatcher("jsp/update/updateContract.jsp").forward(request, response);
            }catch (NumberFormatException e) {
                doGet(request, response);
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id_contract"));
            ContractEntity contractEntity = ContractDAO.getById(id);
            contractEntity.setDataContract(Date.valueOf(request.getParameter("data_contract")));
            contractEntity.setDataExecutionContract(Date.valueOf(request.getParameter("data_execution_contract")));
            contractEntity.setCarNumber(request.getParameter("car_number"));
            contractEntity.setIdClient(Integer.parseInt(request.getParameter("id_client")));

            String [] works = request.getParameterValues("works");
            ArrayList<WorkEntity> workEntities = new ArrayList<>();
            if (works != null)
                for (String work : works) {
                    int temp = Integer.parseInt(work);
                    try {
                        workEntities.add(WorkDAO.getById(temp));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            contractEntity.setWorkEntities(workEntities);
            try {
                ContractDAO.update(contractEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            doGet(request, response);
        } else if ("showtoadd".equals(action)) {
            try {
                List<ClientEntity> clients = ClientDAO.getAll();
                request.setAttribute("clients", clients);

                List<AutomobileEntity> automobiles = AutomobileDAO.getAll();
                request.setAttribute("automobiles", automobiles);

                List<WorkEntity> works = WorkDAO.getAll();
                request.setAttribute("works", works);

                ContractEntity contract = new ContractEntity();
                request.setAttribute("contract", contract);
                request.getRequestDispatcher("jsp/add/addContract.jsp").forward(request, response);
            }
            catch (SQLException|NumberFormatException  e) {
                doGet(request,response);
            }
        } else if ("add".equals(action)) {
            ContractEntity contractEntity = new ContractEntity();
            contractEntity.setDataContract(Date.valueOf(request.getParameter("data_contract")));
            contractEntity.setDataExecutionContract(Date.valueOf(request.getParameter("data_execution_contract")));
            contractEntity.setCarNumber(request.getParameter("car_number"));
            contractEntity.setIdClient(Integer.parseInt((request.getParameter("id_client"))));

            String[] qeq = request.getParameterValues("works");
            ArrayList<WorkEntity> works = new ArrayList<>();
            for (String q: qeq) {
                int temp = Integer.parseInt(q);
                try {
                    works.add(WorkDAO.getById(temp));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            contractEntity.setWorkEntities(works);
            try {
                ContractDAO.insert(contractEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt((request.getParameter("id_contract")));

                ContractEntity contractEntity = new ContractEntity(id);
                try {
                    ContractDAO.delete(contractEntity);
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