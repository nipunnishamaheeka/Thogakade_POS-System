package lk.ijse.backend.bo.custom;

import lk.ijse.backend.bo.CustomerBo;
import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.dao.custom.CustomerDaoImpl;
import lk.ijse.backend.dto.CustomerDto;
import lk.ijse.backend.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public CustomerDto searchCustomer(int id) throws IOException, SQLException {
        Customer customer = customerDao.searchCustomer(id);
        if (customer != null) {
           // System.out.println(customer+"=============================== bo");
            return new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.updateCustomer(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        List<Customer> customerList = customerDao.getAllCustomers();

        if (customerList != null) {
            return customerList.stream().map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary())).toList();
        } else {
            return null;
        }

    }
}
