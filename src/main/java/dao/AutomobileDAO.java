package dao;

import entities.AutomobileEntity;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

public class AutomobileDAO {

    public static void insert(AutomobileEntity automobileEntity) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_AUTOMOBILE + " (" + TABLE_AUTOMOBILE_ID + ", " + TABLE_AUTOMOBILE_BRAND + ", "
                    + TABLE_AUTOMOBILE_MANUFACTURE_YEAR + ", "+ TABLE_AUTOMOBILE_OWNER_FIO +") values (?,?,?,?);");
            statement.setString(1, automobileEntity.getCarNumber());
            statement.setString(2, automobileEntity.getBrand());
            statement.setInt(3, automobileEntity.getManufactureYear());
            statement.setString(4, automobileEntity.getOwnerFIO());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AutomobileEntity getById(String id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            AutomobileEntity automobileEntity = new AutomobileEntity();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_AUTOMOBILE + " WHERE " + TABLE_AUTOMOBILE_ID + " = ?;");

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            automobileEntity.setCarNumber(resultSet.getString(TABLE_AUTOMOBILE_ID));
            automobileEntity.setBrand(resultSet.getString(TABLE_AUTOMOBILE_BRAND));
            automobileEntity.setManufactureYear(resultSet.getInt(TABLE_AUTOMOBILE_MANUFACTURE_YEAR));
            automobileEntity.setOwnerFIO(resultSet.getString(TABLE_AUTOMOBILE_OWNER_FIO));
            return automobileEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void delete(AutomobileEntity automobileEntity) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_AUTOMOBILE + " WHERE " + TABLE_AUTOMOBILE_ID + " = ?;");
            statement.setString(1, automobileEntity.getCarNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_AUTOMOBILE + " WHERE " + TABLE_AUTOMOBILE_ID + " = ?;");
            statement.setString(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(AutomobileEntity automobileEntity) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_AUTOMOBILE + " SET " + TABLE_AUTOMOBILE_BRAND + "= ?, " + TABLE_AUTOMOBILE_MANUFACTURE_YEAR + "= ?, "
                    + TABLE_AUTOMOBILE_OWNER_FIO + " = ? WHERE " + TABLE_AUTOMOBILE_ID + " = ?;");
            statement.setString(1, automobileEntity.getBrand());
            statement.setInt(2, automobileEntity.getManufactureYear());
            statement.setString(3, automobileEntity.getOwnerFIO());
            statement.setString(4, automobileEntity.getCarNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<AutomobileEntity> getAll() {
        try(Connection connection = ConnectionDB.getConnection()) {
            List<AutomobileEntity> automobiles = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_AUTOMOBILE + ";");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AutomobileEntity automobile = new AutomobileEntity(resultSet.getString(TABLE_AUTOMOBILE_ID),
                        resultSet.getString(TABLE_AUTOMOBILE_BRAND),
                        resultSet.getInt(TABLE_AUTOMOBILE_MANUFACTURE_YEAR),
                        resultSet.getString(TABLE_AUTOMOBILE_OWNER_FIO));
                automobiles.add(automobile);
            }
            return automobiles;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}