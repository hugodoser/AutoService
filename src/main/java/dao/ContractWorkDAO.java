package dao;

import entities.ContractWorkEntity;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

public class ContractWorkDAO {

    public static void insert(ContractWorkEntity contractWorkEntity) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_CONTRACT_WORK + " (" + TABLE_CONTRACT_WORK_ID_CONTRACT + ", " + TABLE_CONTRACT_WORK_ID_WORK + ") VALUES (?, ?);");

            statement.setInt(1, contractWorkEntity.getIdContract());
            statement.setInt(2, contractWorkEntity.getIdWork());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(ContractWorkEntity contractWorkEntity) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_CONTRACT_WORK + " WHERE " + TABLE_CONTRACT_WORK_ID_CONTRACT + " = ? AND "
                + TABLE_CONTRACT_WORK_ID_WORK +"=?;");

            statement.setInt(1, contractWorkEntity.getIdContract());
            statement.setInt(2, contractWorkEntity.getIdWork());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateContract(ContractWorkEntity contractWorkEntity) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_CONTRACT_WORK + " SET " + TABLE_CONTRACT_WORK_ID_CONTRACT + "= ?"
                + "WHERE " + TABLE_CONTRACT_WORK_ID_WORK +"=?;");

            statement.setInt(1, contractWorkEntity.getIdContract());
            statement.setInt(2, contractWorkEntity.getIdWork());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateWork(ContractWorkEntity contractWorkEntity) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_CONTRACT_WORK + " SET " + TABLE_CONTRACT_WORK_ID_WORK + "= ?"
                    + "WHERE " + TABLE_CONTRACT_WORK_ID_CONTRACT +"=?;");


            statement.setInt(1, contractWorkEntity.getIdWork());
            statement.setInt(2, contractWorkEntity.getIdContract());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ContractWorkEntity> getAll() {
        try(Connection connection = ConnectionDB.getConnection()) {
            List<ContractWorkEntity> contractWorkEntities = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CONTRACT_WORK + ";");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ContractWorkEntity contractWorks = new ContractWorkEntity(
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_WORK),
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_CONTRACT));
                contractWorkEntities.add(contractWorks);
            }
            return contractWorkEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ContractWorkEntity> getAllByContractId(int id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            List<ContractWorkEntity> contractWorkEntities = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CONTRACT_WORK + " WHERE " + TABLE_CONTRACT_WORK_ID_CONTRACT + " = ?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ContractWorkEntity contractWorks = new ContractWorkEntity(
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_WORK),
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_CONTRACT));
                contractWorkEntities.add(contractWorks);
            }
            return contractWorkEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ContractWorkEntity> getAllByWorkId(int id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            List<ContractWorkEntity> contractWorkEntities = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CONTRACT_WORK + " WHERE " + TABLE_CONTRACT_WORK_ID_WORK + " = ?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ContractWorkEntity contractWorks = new ContractWorkEntity(
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_WORK),
                        resultSet.getInt(TABLE_CONTRACT_WORK_ID_CONTRACT));
                contractWorkEntities.add(contractWorks);
            }
            return contractWorkEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}