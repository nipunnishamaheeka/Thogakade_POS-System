package lk.ijse.backend.bo;

import lk.ijse.backend.dto.CustomerDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo {
    boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException;
    CustomerDto searchCustomer(int id) throws IOException, SQLException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException;
    List<CustomerDto> getAllCustomers() throws SQLException;

}
