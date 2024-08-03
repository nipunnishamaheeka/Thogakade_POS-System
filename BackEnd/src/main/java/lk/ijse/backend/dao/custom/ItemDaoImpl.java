package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    Connection connection;
    PreparedStatement pstm;
    @Override
    public boolean addItem(Item item) throws SQLException {
       connection = DbConnection.getInstance().getConnection();

       pstm = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
      pstm.setInt(1,item.getId());
        pstm.setString(2,item.getName());
        pstm.setInt(3,item.getQty());
        pstm.setDouble(4,item.getPrice());

        return pstm.executeUpdate() > 0;



    }

    @Override
    public ItemDto searchItem(int id) throws SQLException {
        connection = DbConnection.getInstance().getConnection();

        pstm = connection.prepareStatement("SELECT * FROM item WHERE item_id=?");
        pstm.setInt(1, id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return new ItemDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }

        return null;

    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        connection = DbConnection.getInstance().getConnection();

        pstm = connection.prepareStatement("UPDATE item SET item_name = ? , qty = ? , item_price = ? WHERE item_id=?");
        pstm.setString(1, item.getName());
        pstm.setInt(2, item.getQty());
        pstm.setDouble(3, item.getPrice());
        pstm.setInt(4, item.getId());

        return pstm.executeUpdate() > 0;

    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        connection = DbConnection.getInstance().getConnection();

        pstm = connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet = pstm.executeQuery();

        List<Item> itemList = new ArrayList<>();

        while (resultSet.next()) {
            itemList.add(new Item(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }

        return itemList;
    }
    }

