package lk.ijse.backend.DAO;

import lk.ijse.backend.dto.CustomerDto;

import java.sql.Connection;
import java.sql.SQLException;

public sealed interface CustomerDAO permits CustomerDAOImpl{
    String saveCustomer(CustomerDto customer, Connection connection);
    boolean updateCustomer(String id, CustomerDto customer, Connection connection) throws SQLException;
    CustomerDto getCustomer(String id, Connection connection) throws SQLException;
    boolean deleteCustomer(String id, Connection connection);

}
