package ivanmerkush.sql;

import ivanmerkush.entity.Customer;
import ivanmerkush.entity.Order;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Sql {
    static private Connection conn;
    String DB_URL ="jdbc:mysql://localhost:3306/atelie?serverTimezone=UTC";
    String USER = "root";
    String PASS = "root";
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public Sql() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

    }






    public String deleteOrder(String clothing, int price){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "delete from `order` where clothing = ? and price = ?");
            preparedStatement.setString(1, clothing);
            preparedStatement.setInt(2, price);
            if (preparedStatement.executeUpdate() > 0) {
                return "Order has been deleted";
            } else {
                return "Order not found";
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }


    public String addOrder(String name, String clothing, int price, Date term) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "select id_customer from customer where name = ?");

            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                int idCustomer = resultSet.getInt("id_customer");

                preparedStatement = conn.prepareStatement(
                        "insert into `order`(clothing, price, term, id_customer) " +
                                " values (?, ?, ?, ?)");

                preparedStatement.setString(1, clothing);
                preparedStatement.setInt(2, price);
                preparedStatement.setDate(3, term);
                preparedStatement.setInt(4, idCustomer);
                preparedStatement.execute();

                return "New order has been added";
            } else {
                return "There is no customer named " + name;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String deleteCustomer(String nameOfPrey) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(
                    "delete from customer where name = ?");
            preparedStatement.setString(1, nameOfPrey);
            if (preparedStatement.executeUpdate() > 0) {
                return "Customer has been deleted";
            } else {
                return "Customer not found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
    public String addCustomer(String name, Date sqlDate) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "insert into customer(name, birth) " +
                            " values (?, ? )");

            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.execute();
            return "New customer has been added";
        }
        catch(SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

}
