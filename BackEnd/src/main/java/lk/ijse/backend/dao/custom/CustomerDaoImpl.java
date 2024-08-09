package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.entity.Customer;
import lk.ijse.backend.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
//    private Connection connection;
//    PreparedStatement pstm;

    @Override
    public boolean save(Customer customer) throws SQLException {
//        System.out.println("customer = " + customer);
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
//        pstm.setString(1, customer.getId());
//        pstm.setString(2, customer.getName());
//        pstm.setString(3, customer.getAddress());
//        pstm.setDouble(4, customer.getSalary());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?)",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
    }

    @Override
    public Customer search(String id) throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("SELECT * FROM customer WHERE cust_id=?");
//        pstm.setString(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            return new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            );
//        }
//        return null;
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE cust_id=?", id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("UPDATE customer SET cust_name = ? , cust_address = ? , cust_salary = ? WHERE cust_id=?");
//        pstm.setString(1, customer.getName());
//        pstm.setString(2, customer.getAddress());
//        pstm.setDouble(3, customer.getSalary());
//        pstm.setString(4, customer.getId());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE customer SET cust_name = ? , cust_address = ? , cust_salary = ? WHERE cust_id=?",
                customer.getName(),
                customer.getAddress(),
                customer.getSalary(),
                customer.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("DELETE FROM customer WHERE cust_id=?");
//        pstm.setString(1, id);
//
//        return pstm.executeUpdate() > 0;
        return SQLUtil.execute("DELETE FROM customer WHERE cust_id=?", id);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("SELECT * FROM customer");
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Customer> customerList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            customerList.add(new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            ));
//        }
//
//        return customerList;
//    }
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customerList;
    }

}

