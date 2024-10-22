package lk.ijse.backend.dao.custom;

import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.db.DbConnection;
import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.util.SQLUtil;

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
    public boolean save(Item item) throws SQLException {
//        System.out.println("item = " + item);
//       connection = DbConnection.getInstance().getConnection();
//
//       pstm = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
//      pstm.setString(1,item.getId());
//        pstm.setString(2,item.getName());
//        pstm.setString(3,item.getQty());
//        pstm.setDouble(4,item.getPrice());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO item VALUES (?,?,?,?)",
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getPrice()
        );



    }

    @Override
    public Item search(String  id) throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("SELECT * FROM item WHERE item_id=?");
//        pstm.setString(1, id);
//        ResultSet resultSet = pstm.executeQuery();
//
//        if (resultSet.next()) {
//            return new ItemDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            );
//        }
//
//        return null;

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE item_id=?", id);
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;

    }

    @Override
    public boolean update(Item item) throws SQLException {
//       try{
//           System.out.println("DAOItem = " + item);
//           connection = DbConnection.getInstance().getConnection();
//
//           System.out.println("connection = " + connection);
//           pstm = connection.prepareStatement("UPDATE item SET item_name = ? , qty = ? , item_price = ? WHERE item_id=?");
//
//           pstm.setString(1, item.getName());
//           pstm.setString(2, item.getQty());
//           pstm.setDouble(3, item.getPrice());
//           pstm.setString(4, item.getId());
//           System.out.println(pstm);
//
//           try{
//               return pstm.executeUpdate() > 0;
//           } catch (Exception e) {
//               throw new RuntimeException(e);
//           }
//
//
//       } catch (SQLException e) {
//          e.printStackTrace();
//              return false;
//       }
        return SQLUtil.execute("UPDATE item SET item_name = ? , qty = ? , item_price = ? WHERE item_id=?",
                item.getName(),
                item.getQty(),
                item.getPrice(),
                item.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("DELETE FROM item WHERE item_id=?");
//        pstm.setString(1, id);
//
//        return pstm.executeUpdate() > 0;
        return SQLUtil.execute("DELETE FROM item WHERE item_id=?", id);
    }

    @Override
    public List<Item> getAll() throws SQLException {
//        connection = DbConnection.getInstance().getConnection();
//
//        pstm = connection.prepareStatement("SELECT * FROM item");
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Item> itemList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            itemList.add(new Item(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            ));
//        }
//
//        return itemList;
//    }
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");
        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            itemList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return itemList;
    }
    }

