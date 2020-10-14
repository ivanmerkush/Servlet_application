package ivanmerkush.entity;

import ivanmerkush.sql.Sql;

import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable {
    static private Connection conn;
    private String DB_URL ="jdbc:mysql://localhost:3306/atelie?serverTimezone=UTC";
    private String USER = "root";
    private String PASS = "root";
    private SimpleDateFormat sdf;
    public Orders() throws SQLException {
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public List<Order> getOrdersByName(String name) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select o.clothing, o.price, o.term, o.id_customer from `order` o join customer c on o.id_customer = c.id_customer where c.name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                String clothing = resultSet.getString("clothing");
                int price = resultSet.getInt("price");
                Date term = resultSet.getDate("term");
                int idCustomer = resultSet.getInt("id_customer");
                orders.add(new Order(clothing, price, term, idCustomer));
            }
            return orders;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getOrdersByDate(String lowerDate, String upperDate) throws ParseException {
        Date lowerLimit = new java.sql.Date(sdf.parse(lowerDate).getTime());
        Date upperLimit = new java.sql.Date(sdf.parse(upperDate).getTime());
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select o.id_order, o.clothing, o.price, o.term, o.id_customer from `order` o where o.term between ? and ? order by term");
            preparedStatement.setDate(1, lowerLimit);
            preparedStatement.setDate(2, upperLimit);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int idOrder = resultSet.getInt("id_order");
                String clothing = resultSet.getString("clothing");
                int price = resultSet.getInt("price");
                Date term = resultSet.getDate("term");
                int idCustomer = resultSet.getInt("id_customer");
                orders.add(new Order(idOrder, clothing, price, term, idCustomer));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrders() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(" select * from `order`");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_order");
                String clothing = resultSet.getString("clothing");
                int price = resultSet.getInt("price");
                Date term = resultSet.getDate("term");
                int idCustomer = resultSet.getInt("id_customer");
                orders.add(new Order(id, clothing, price, term, idCustomer));
            }
            return orders;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
