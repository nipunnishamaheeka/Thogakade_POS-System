package lk.ijse.backend.dao;



import lk.ijse.backend.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    boolean saveCustomer(Customer customer) throws SQLException;
}