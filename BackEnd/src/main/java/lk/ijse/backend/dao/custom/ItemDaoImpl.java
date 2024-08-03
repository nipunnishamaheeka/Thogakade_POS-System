package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDaoImpl implements ItemDao {
    Connection connection;
    PreparedStatement pstm;
    @Override
    public boolean addItem(Item item) throws Exception {
       connection = DbConnection.getInstance().getConnection();

       pstm = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
      pstm.setInt(1,item.getId());
        pstm.setString(2,item.getName());
        pstm.setInt(3,item.getQty());
        pstm.setDouble(4,item.getPrice());

        return pstm.executeUpdate() > 0;



    }
}
