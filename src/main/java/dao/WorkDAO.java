package dao;

import entities.WorkEntity;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

public class WorkDAO {

    public static void insert(WorkEntity workEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_WORK + " (" + TABLE_WORK_ID + ", " + TABLE_WORK_NAME_WORK + ", " + TABLE_WORK_COST_WORK + ") values (nextval('\"work_seq\"'),?,?);");
            statement.setString(1, workEntity.getNameWork());
            statement.setInt(2, workEntity.getCostWork());

            statement.executeUpdate();
        }
    }

    public static WorkEntity getById(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            WorkEntity workEntity = new WorkEntity();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_WORK + " WHERE " + TABLE_WORK_ID + " = ?;");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            workEntity.setIdWork(resultSet.getInt(TABLE_WORK_ID));
            workEntity.setNameWork(resultSet.getString(TABLE_WORK_NAME_WORK));
            workEntity.setCostWork(resultSet.getInt(TABLE_WORK_COST_WORK));
            return workEntity;
        }
    }

    public static void delete(WorkEntity workEntity) throws SQLException{
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_WORK + " WHERE " + TABLE_WORK_ID + " = ?;");
            statement.setInt(1, workEntity.getIdWork());

            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException{
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_WORK + " WHERE " + TABLE_WORK_ID + " = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    public static void update(WorkEntity workEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_WORK + " SET " + TABLE_WORK_NAME_WORK + " = ?, " + TABLE_WORK_COST_WORK + " = ? WHERE " + TABLE_WORK_ID + " = ?;");
            statement.setString(1, workEntity.getNameWork());
            statement.setInt(2, workEntity.getCostWork());
            statement.setInt(3, workEntity.getIdWork());

            statement.executeUpdate();
        }
    }

    public static List<WorkEntity> getAll() throws SQLException{
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM work");
            ArrayList<WorkEntity> workEntities = new ArrayList<>();

            while (resultSet.next())
                workEntities.add(new WorkEntity(
                        resultSet.getInt(TABLE_WORK_ID),
                        resultSet.getString(TABLE_WORK_NAME_WORK),
                        resultSet.getInt(TABLE_WORK_COST_WORK)));

            return workEntities;
        }
    }

    public static int findMaxId() throws SQLException{
        try(Connection connection = ConnectionDB.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet set = statement.executeQuery("SELECT " + TABLE_WORK_ID + " FROM " + TABLE_WORK + " WHERE " + TABLE_WORK_ID
                    + " = (SELECT MAX(" + TABLE_WORK_ID + ") FROM " + TABLE_WORK + ");");
            set.last();
            int maxId = set.getRow() != 0 ? set.getInt(TABLE_WORK_ID) : -1;
            return maxId;
        }
    }
}