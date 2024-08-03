package lk.ijse.backend.bo.custom;

import lk.ijse.backend.bo.CustomerBo;
import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.dao.custom.CustomerDaoImpl;
import lk.ijse.backend.dto.CustomerDto;
import lk.ijse.backend.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException {
        System.out.println("customerDto = " + customerDto);
        return customerDao.saveCustomer(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );
    }
}
