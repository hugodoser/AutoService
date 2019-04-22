package utils;

public class Constants {
    public static final String
            JDBC_DRIVER = "org.postgresql.Driver",
            JDBC_URL = "jdbc:postgresql://127.0.0.1:5432/CarRepairShop",
            JDBC_USER = "postgres",
            JDBC_PASSWORD = "9465",

    TABLE_AUTOMOBILE = "automobile",
            TABLE_AUTOMOBILE_ID = "car_number",
            TABLE_AUTOMOBILE_BRAND = "brand",
            TABLE_AUTOMOBILE_MANUFACTURE_YEAR = "manufacture_year",
            TABLE_AUTOMOBILE_OWNER_FIO = "owner_fio",

    TABLE_CLIENT = "client",
            TABLE_CLIENT_ID = "id_client",
            TABLE_CLIENT_FIO_CLIENT = "fio_client",

    TABLE_CONTRACT = "contract",
            TABLE_CONTRACT_ID = "id_contract",
            TABLE_CONTRACT_DATA_CONTRACT = "data_contract",
            TABLE_CONTRACT_DATA_EXECUTION_CONTRACT = "data_execution_contract",
            TABLE_CONTRACT_CAR_NUMBER = "car_number",
            TABLE_CONTRACT_ID_CLIENT = "id_client",

    TABLE_CONTRACT_WORK = "contract_work",
            TABLE_CONTRACT_WORK_ID_CONTRACT = "id_contract",
            TABLE_CONTRACT_WORK_ID_WORK = "id_work",

    TABLE_WORK = "work",
            TABLE_WORK_ID = "id_work",
            TABLE_WORK_NAME_WORK = "name_work",
            TABLE_WORK_COST_WORK = "cost_work";

}