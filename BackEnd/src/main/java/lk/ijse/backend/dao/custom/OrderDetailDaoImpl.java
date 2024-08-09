package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.OrderDetailDao;
import lk.ijse.backend.entity.OrderDetails;
import lk.ijse.backend.util.SQLUtil;

import java.sql.SQLException;

public class OrderDetailDaoImpl implements OrderDetailDao {
    public static String SAVE_ORDER_ITEM_DETAIL = "INSERT INTO order_item_detail (order_id, item_id, qty) VALUES(?,?,?)";

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return SQLUtil.execute(SAVE_ORDER_ITEM_DETAIL,
                entity.getOrderId(),
                entity.getItemId(),
                entity.getQty()
        );
    }
}
