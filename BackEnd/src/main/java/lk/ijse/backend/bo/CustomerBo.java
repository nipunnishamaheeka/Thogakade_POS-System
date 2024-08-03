package lk.ijse.backend.bo;

import lk.ijse.backend.dto.CustomerDto;

import java.io.IOException;
import java.sql.SQLException;

public interface CustomerBo {
    boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException;
}
