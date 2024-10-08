package org.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DatabaseUtil {

    static String filePath = "src/test/resources/DBconfig.yaml";

    public static Connection connect_withDB() {
        String url = YamlReader.readYamlData(filePath, "url");
        String user = YamlReader.readYamlData(filePath, "user_name");
        String password = YamlReader.readYamlData(filePath, "password");
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<String> get_ColumnNames(String DBName, String tableName) {
        String Query = "Select * from " + tableName + ";";
        System.out.println(Query);
        try {
            Statement statement = Objects.requireNonNull(connect_withDB()).createStatement();
            statement.execute("USE " + DBName + ";");
            ResultSet resultSet = statement.executeQuery(Query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println(metaData.getColumnCount());
            ArrayList<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            statement.close();
            return columnNames;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<HashMap<String, String>> get_Multiple_row_data(String DBName, String tableName, String columnName) {
        String Query = "SELECT * FROM " + tableName + " ORDER BY " + columnName + " DESC;";
        ArrayList<HashMap<String, String>> rowDataList = new ArrayList<>();

        try (Connection connection = connect_withDB(); // Assuming connect_withDB() returns a valid Connection
             Statement statement = Objects.requireNonNull(connection).createStatement()) {

            statement.execute("USE " + DBName + ";");
            ResultSet resultSet = statement.executeQuery(Query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Iterate through the ResultSet to get all rows
            while (resultSet.next()) {
                HashMap<String, String> rowData = new HashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String col_Name = metaData.getColumnLabel(i);
                    String columnValue = resultSet.getString(i);
                    rowData.put(col_Name, columnValue);
                }
                rowDataList.add(rowData); // Add the current row data to the list
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rowDataList; // Return the complete list after processing all rows
    }

    public static void get_Single_row_Data(String DBName, String tableName, String columnName){

    }


    public static void main(String[] args) {
        DatabaseUtil db = new DatabaseUtil();
        get_Multiple_row_data("classicmodels", "customers", "customerNumber");
    }
}
