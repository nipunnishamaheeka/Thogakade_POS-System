package lk.ijse.backend.DAO;

import lk.ijse.backend.dto.CustomerDto;

import java.sql.Connection;
import java.sql.SQLException;

public final class CustomerDAOImpl implements CustomerDAO{
    public static String SAVE_CUSTOMER = "INSERT INTO customer (cust_id,cust_name,cust_address,cust_salary) VALUES(?,?,?,?)";
    @Override
    public String saveCustomer(CustomerDto customer, Connection connection) {
        try{
            var ps = connection.prepareStatement(SAVE_CUSTOMER);
            ps.setInt(1, customer.getCust_id());
            ps.setString(2, customer.getCust_name());
            ps.setString(3, customer.getCust_address());
            ps.setDouble(4, customer.getCust_salary());


            if(ps.executeUpdate() != 0){
                return "Customer saved";
            }else{
                return "CUSTOMER not saved";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomer(String id, CustomerDto customer, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public CustomerDto getCustomer(String id, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) {
        return false;
    }
}
