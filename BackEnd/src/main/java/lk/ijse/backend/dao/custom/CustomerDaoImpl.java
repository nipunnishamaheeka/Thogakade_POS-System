package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {
    private Connection connection;
    PreparedStatement pstm;
    @Override
    public boolean saveCustomer(Customer customer) throws SQLException {
        System.out.println("customer = " + customer);
        connection = DbConnection.getInstance().getConnection();

        pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
        pstm.setInt(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;

    }
}
