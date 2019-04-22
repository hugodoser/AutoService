package dao;

import entities.ContractEntity;
import entities.ContractWorkEntity;
import entities.WorkEntity;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static utils.Constants.*;

public class ContractDAO {

    public static void insert(ContractEntity contract) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contract (id_contract, data_contract, data_execution_contract, car_number, id_client) VALUES (nextval('\"contract_seq\"'), ?, ?, ?, ?) RETURNING id_contract");

            statement.setDate(1, contract.getDataContract());
            statement.setDate(2, contract.getDataExecutionContract());
            statement.setString(3, contract.getCarNumber());
            statement.setInt(4, contract.getIdClient());
            ResultSet resultSet = statement.executeQuery();

            int id = 0;
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }

            contract.setIdContract(id);
            insertManyToManyFields(contract,connection);
        }
    }

    private static void insertManyToManyFields(ContractEntity contractEntity, Connection connection) throws SQLException {
        PreparedStatement statement;
        if (contractEntity.getWorkEntities().size() > 0) {
            statement = connection.prepareStatement("INSERT INTO contract_work (id_contract,id_work) VALUES(?,?)");
            for (WorkEntity work : contractEntity.getWorkEntities()) {
                statement.setInt(1, contractEntity.getIdContract());
                statement.setInt(2, work.getIdWork());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void update(ContractEntity contractEntity) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE contract SET  data_contract = ?, data_execution_contract = ?, car_number = ?, id_client = ? WHERE id_contract  = ?");

            statement.setDate(1, contractEntity.getDataContract());
            statement.setDate(2, contractEntity.getDataExecutionContract());
            statement.setString(3, contractEntity.getCarNumber());
            statement.setInt(4, contractEntity.getIdClient());
            statement.setInt(5, contractEntity.getIdContract());
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM contract_work WHERE id_contract=?");
            statement.setInt(1, contractEntity.getIdContract());
            statement.executeUpdate();

            insertManyToManyFields(contractEntity, connection);
        }
    }

    public static void delete(ContractEntity contractEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM contract WHERE id_contract = ?");
            statement.setInt(1, contractEntity.getIdContract());
            statement.executeUpdate();
        }
    }

    public static ContractEntity getById(int id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contract WHERE id_contract = ?;");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ContractEntity contractEntity = new ContractEntity(
                    resultSet.getInt("id_contract"),
                    resultSet.getDate("data_contract"),
                    resultSet.getDate("data_execution_contract"),
                    resultSet.getString("car_number"),
                    resultSet.getInt("id_client"),
                    null
            );
            statement = connection.prepareStatement("SELECT * FROM contract c left JOIN contract_work using (id_contract) left JOIN work using (id_work) where c.id_contract=?;");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ArrayList works = new ArrayList<WorkEntity>();
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                works.add(new WorkEntity(
                        resultSet.getInt("id_work"),
                        resultSet.getString("name_work"),
                        resultSet.getInt("cost_work"))
                );
            }
            contractEntity.setWorkEntities(works);

            return contractEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ContractEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM contract");
            List list = new LinkedList<ContractEntity>();

            while (resultSet.next())
                list.add(new ContractEntity(
                        resultSet.getInt("id_contract"),
                        resultSet.getDate("data_contract"),
                        resultSet.getDate("data_execution_contract"),
                        resultSet.getString("car_number"),
                        resultSet.getInt("id_client"))
                );
            return list;
        }
    }

    public static List<ContractEntity> selectAll() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM contract left JOIN contract_work using (id_contract) left JOIN work using (id_work) order by contract.id_contract;");
            int id = 0;
            List<ContractEntity> contracts = new ArrayList<>();

            while (resultSet.next()) {
                if (id != resultSet.getInt("id_contract")) {
                    contracts.add(new ContractEntity(
                            resultSet.getInt("id_contract"),
                            resultSet.getDate("data_contract"),
                            resultSet.getDate("data_execution_contract"),
                            resultSet.getString("car_number"),
                            resultSet.getInt("id_client"),
                            new ArrayList<>()));
                    id = contracts.get(contracts.size() - 1).getIdContract();
                }
                if (resultSet.getInt("id_work") > 0) {
                    WorkEntity workEntity = new WorkEntity(
                            resultSet.getInt("id_work"),
                            resultSet.getString("name_work"),
                            resultSet.getInt("cost_work"));
                    if (!contracts.get(contracts.size() - 1).getWorkEntities().contains(workEntity))
                        contracts.get(contracts.size() - 1).getWorkEntities().add(workEntity);
                }
            }
            return contracts;
        }
    }

    public static ContractEntity selectByID(int idContract) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contract WHERE id_contract = ?");
            statement.setInt(1, idContract);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ContractEntity contract = new ContractEntity(
                    resultSet.getInt("id_contract"),
                    resultSet.getDate("data_contract"),
                    resultSet.getDate("data_execution_contract"),
                    resultSet.getString("car_number"),
                    resultSet.getInt("id_client"),
                            null);
            statement = connection.prepareStatement("SELECT * FROM contract c left JOIN contract_work using (id_contract) left JOIN work using (id_work) where c.id_contract=?;");
            statement.setInt(1, idContract);
            statement.executeQuery();
            ArrayList works = new ArrayList<WorkEntity>();
            statement.setInt(1, idContract);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                works.add(new WorkEntity(
                        resultSet.getInt("id_work"),
                        resultSet.getString("name_work"),
                        resultSet.getInt("cost_work")));
            }
            contract.setWorkEntities(works);
            return contract;
        }
    }
}