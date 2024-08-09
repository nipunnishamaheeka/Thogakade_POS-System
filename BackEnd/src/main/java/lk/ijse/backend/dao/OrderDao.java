package lk.ijse.backend.dao;

import lk.ijse.backend.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends SuperDAO{
    boolean save(Order entity) throws SQLException;
    List<Order> getAll() throws SQLException;
}
