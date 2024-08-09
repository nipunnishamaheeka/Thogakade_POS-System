package lk.ijse.backend.bo.custom;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.backend.bo.OrderBo;
import lk.ijse.backend.dao.DAOFactory;
import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.dao.OrderDao;
import lk.ijse.backend.dao.OrderDetailDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.dto.OrderDto;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.entity.Order;
import lk.ijse.backend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDAO =
            (OrderDao) DAOFactory.getInstance()
                    .getDAO(DAOFactory.DAOType.Order);

    OrderDetailDao orderItemDetailDAO =
            (OrderDetailDao) DAOFactory.getInstance()
                    .getDAO(DAOFactory.DAOType.OrderDetail);

    ItemDao itemDao = (ItemDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        boolean isOrderSaved;
        boolean isOrderItemSaved = true;
        connection.setAutoCommit(false);

        // Save order
        isOrderSaved = orderDAO.save(new Order(
                dto.getId(),
                dto.getDate(),
                dto.getCustomerId(),
                Double.parseDouble(dto.getTotal()),
                dto.getDiscount(),
                Double.parseDouble(dto.getSubTotal()),
                Double.parseDouble(dto.getCash()),
                Double.parseDouble(dto.getBalance())
        ));

        // Save order items
        if (isOrderSaved) {
            for (ItemDto item : dto.getItems()) {

                int qty = Integer.parseInt(item.getQty());
                boolean isOrderItemDetailSaved = orderItemDetailDAO.save(new OrderDetails(
                        dto.getId(),
                        item.getId(),
                        qty
                ));
                if (!isOrderItemDetailSaved) {
                    connection.rollback(); // Rollback transaction on failure
                    isOrderItemSaved = false;
                    break;
                }
            }
        }
        System.out.println(dto.getItems()+"get Items");
        if (isOrderSaved){
            for (ItemDto item : dto.getItems()) {
                Item data = itemDao.search(item.getId());
                int newQty = Integer.parseInt(data.getQty());

                int qty = Integer.parseInt(item.getQty());
                data.setQty (String.valueOf(newQty - qty));
                itemDao.update(new Item(
                        data.getId(),
                        data.getName(),
                        data.getQty(),
                        data.getPrice()
                ));
            }
        }

        if (isOrderSaved && isOrderItemSaved) {
            connection.commit();
        } else {
            connection.rollback();
        }
        connection.setAutoCommit(true);
        return isOrderSaved;
    }

    @Override
    public List<OrderDto> getAllOrders() throws SQLException {
        List<Order> orderEntities = orderDAO.getAll();
        List<OrderDto> orderDTOS = new ArrayList<>();
        for (Order order : orderEntities) {
            orderDTOS.add(
                    new OrderDto(
                            order.getId(),
                            order.getDate(),
                            order.getCustomerId(),
                            null,
                            String.valueOf(order.getTotal()),
                            order.getDiscount(),
                            String.valueOf(order.getSubTotal()),
                            String.valueOf(order.getCash()),
                            String.valueOf(order.getBalance())
                    ));
        }
        return orderDTOS;
    }
}
