package ivanmerkush.entity;

import ivanmerkush.sql.Sql;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customers implements Serializable {
    static private Connection conn;
    private String DB_URL ="jdbc:mysql://localhost:3306/atelie?serverTimezone=UTC";
    private String USER = "root";
    private String PASS = "root";
    public Customers() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public List<Customer> getCustomers() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(" select * from customer");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_customer");
                String name = resultSet.getString("name");
                Date birth = resultSet.getDate("birth");
                Customer currentCustomer = new Customer(name, birth, id);
                customers.add(currentCustomer);
            }
            return customers;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getNamesOfCustomers() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(" select c.name from customer c");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<String> namesOfCustomers = new ArrayList<>();
            while (resultSet.next()) {
                namesOfCustomers.add(resultSet.getString("name"));
            }
            return namesOfCustomers;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}