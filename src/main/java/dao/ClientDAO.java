package dao;

import entities.AutomobileEntity;
import entities.ClientEntity;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

public class ClientDAO {

    public static void insert(ClientEntity clientEntity) throws SQLException{
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_CLIENT + " (" + TABLE_CLIENT_ID + ", " + TABLE_CLIENT_FIO_CLIENT + " ) values (nextval('\"client_seq\"'),?);");
            statement.setString(1, clientEntity.getClientFIO());

            statement.executeUpdate();
        }
    }

    public static String getNameById(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_ID + " = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            String fio_client = "";
            while(resultSet.next())
                fio_client = resultSet.getString(TABLE_CLIENT_FIO_CLIENT);
            return fio_client;
        }
    }

    public static ClientEntity getById(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ClientEntity clientEntity = new ClientEntity();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_ID + " = ?;");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            clientEntity.setIdClient(resultSet.getInt(TABLE_CLIENT_ID));
            clientEntity.setClientFIO(resultSet.getString(TABLE_CLIENT_FIO_CLIENT));
            return clientEntity;
        }
    }

    public static void delete(ClientEntity clientEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_ID + " = ?;");
            statement.setInt(1, clientEntity.getIdClient());

            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_ID + " = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    public static void update(ClientEntity clientEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_CLIENT + " SET " + TABLE_CLIENT_FIO_CLIENT + "= ? WHERE " + TABLE_CLIENT_ID + " = ?;");
            statement.setString(1, clientEntity.getClientFIO());
            statement.setInt(2, clientEntity.getIdClient());

            statement.executeUpdate();
        }
    }

    public static List<ClientEntity> getAll() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            List<ClientEntity> clients = new ArrayList<ClientEntity>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_CLIENT + ";");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClientEntity client = new ClientEntity(resultSet.getInt(TABLE_CLIENT_ID),
                        resultSet.getString(TABLE_CLIENT_FIO_CLIENT));
                clients.add(client);
            }
            return clients;
        }
    }
}