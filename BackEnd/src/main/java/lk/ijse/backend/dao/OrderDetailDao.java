package lk.ijse.backend.dao;

import lk.ijse.backend.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailDao extends SuperDAO {
    boolean save(OrderDetails entity) throws SQLException;
}
