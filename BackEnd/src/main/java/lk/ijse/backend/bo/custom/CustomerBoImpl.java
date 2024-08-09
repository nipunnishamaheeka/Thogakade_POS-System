package lk.ijse.backend.bo.custom;

import lk.ijse.backend.bo.CustomerBo;
import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.dao.DAOFactory;
import lk.ijse.backend.dao.custom.CustomerDaoImpl;
import lk.ijse.backend.dto.CustomerDto;
import lk.ijse.backend.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException {
        System.out.println("customerDto = " + customerDto);
        return customerDao.save(
                new Customer(
                        customerDto.getCustId(),
                        customerDto.getCustName(),
                        customerDto.getCustAddress(),
                        customerDto.getCustSalary()
                )
        );
    }

    @Override
    public CustomerDto searchCustomer(String id) throws IOException, SQLException {
        Customer customer = customerDao.search(id);
        if (customer != null) {
           // System.out.println(customer+"=============================== bo");
            return new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.update(
                new Customer(
                        customerDto.getCustId(),
                        customerDto.getCustName(),
                        customerDto.getCustAddress(),
                        customerDto.getCustSalary()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        List<Customer> customerList = customerDao.getAll();

        if (customerList != null) {
            return customerList.stream().map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary())).toList();
        } else {
            return null;
        }

    }
}
