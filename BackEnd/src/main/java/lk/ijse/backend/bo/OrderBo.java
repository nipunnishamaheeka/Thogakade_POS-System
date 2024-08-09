package lk.ijse.backend.bo;

import lk.ijse.backend.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    boolean saveOrder(OrderDto dto) throws SQLException;
    List<OrderDto> getAllOrders() throws SQLException;
}
